package com.lb.mineframe.items;

import com.lb.mineframe.renderers.ClipCrossbowRenderer;
import com.lb.mineframe.utils.KeyMaps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.network.PacketDistributor;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.function.Consumer;

public class ClipCrossbow extends Item implements IAnimatable, ISyncable {
    //animation util
    private final String animationControllerName = "controllerxbow";
    public AnimationFactory factory = new AnimationFactory(this);
    private final int animationReloadState = 1;
    private final int animationShotState = 0;

    //item fields
    private boolean reloadMark = false;

    public ClipCrossbow(Properties pProperties) {
        super(pProperties);
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new ClipCrossbowRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        Player player = (Player) pEntity;
        if (KeyMaps.reload_key.isDown() && !pLevel.isClientSide&&!player.getCooldowns().isOnCooldown(this)) {
            player.getCooldowns().addCooldown(this,160);
            final int id = GeckoLibUtil.guaranteeIDForStack(pStack, (ServerLevel) pLevel); //specify ID for the item.
            final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> pEntity);
            GeckoLibNetwork.syncAnimation(target, this, id, animationReloadState);
            CompoundTag itemTag = pStack.getOrCreateTag();
            CompoundTag itemParaTag = pStack.getOrCreateTagElement("item_para");
            itemParaTag.putInt("ammo_in_clip", 4);
            itemTag.put("item_para", itemParaTag);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        CompoundTag itemTag = this.getOrCreateItemTag(itemStack);
        CompoundTag itemParaTag = itemStack.getOrCreateTagElement("item_para");
        int arrowsInClip = itemParaTag.getInt("ammo_in_clip");
        if (!pLevel.isClientSide) {
            final ItemStack stack = pPlayer.getItemInHand(pUsedHand);
            final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerLevel) pLevel); //specify ID for the item.
            if (arrowsInClip != 0) {
                pPlayer.getCooldowns().addCooldown(this, 33);
                //this starts the animation
                final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> pPlayer);
                GeckoLibNetwork.syncAnimation(target, this, id, animationShotState);
                //shooting the arrow
                Arrow arrow = new Arrow(pLevel, pPlayer.getX(), pPlayer.getY() + 1.5, pPlayer.getZ());
                arrow.setCritArrow(true);
                arrow.shotFromCrossbow();
                arrow.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 3.0F, 0.0F);
                pLevel.addFreshEntity(arrow);
                pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, 1F);
                arrowsInClip--;
                itemParaTag.putInt("ammo_in_clip", arrowsInClip);
                itemTag.put("item_para", itemParaTag);
            } else {
                pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.DISPENSER_FAIL, SoundSource.AMBIENT, 1F, 1F);
                pPlayer.displayClientMessage(new TextComponent("Clip Is Empty"), true);
            }
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public CompoundTag getOrCreateItemTag(ItemStack itemStack) {
        if (itemStack.hasTag()) {
            return itemStack.getTag();
        }
        CompoundTag itemTag = itemStack.getOrCreateTag();
        CompoundTag itemParaTag = itemStack.getOrCreateTagElement("item_para");
        itemParaTag.putInt("ammo_in_clip", 4);
        itemTag.put("item_para", itemParaTag);
        return itemTag;
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        return false;
    }


    //ANIMATION CONTROLLING

    @SuppressWarnings({"rawtypes", "resource"})
    @Override
    public void onAnimationSync(int id, int state) {
        if (state == animationShotState) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, animationControllerName);
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.clip_crossbow.shoot", false));
            }
        }
        if (state == animationReloadState) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, animationControllerName);
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.clip_crossbow.reload", false));
            }
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, animationControllerName, 1, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }


}
