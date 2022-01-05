package com.lb.mineframe.items;

import com.lb.mineframe.MineFrame;
import com.lb.mineframe.entities.thrown.HomingFireworkProjectile;
import com.lb.mineframe.renderers.AntiAirFireworkLauncherRenderer;
import com.lb.mineframe.utils.KeyMaps;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
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

public class AntiAirFireworkLauncher extends Item implements IAnimatable, ISyncable {

    //animation util
    private final String animationControllerName = "controlleraalauncher";
    public AnimationFactory factory = new AnimationFactory(this);
    private final int animationReloadState = 0;

    public AntiAirFireworkLauncher(Properties pProperties) {
        super(pProperties);
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new AntiAirFireworkLauncherRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }

    public CompoundTag getOrCreateItemTag(ItemStack itemStack) {
        if (itemStack.hasTag()) {
            return itemStack.getTag();
        }
        CompoundTag itemTag = itemStack.getOrCreateTag();
        CompoundTag itemParaTag = itemStack.getOrCreateTagElement("item_meta");
        itemParaTag.putInt("target_id", -1);
        itemParaTag.putInt("ammo", 0);
        itemTag.put("item_meta", itemParaTag);
        return itemTag;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        Player player = (Player) pEntity;
        if (KeyMaps.reload_key.isDown() && !pLevel.isClientSide && !player.getCooldowns().isOnCooldown(this)) {
            player.getCooldowns().addCooldown(this, 111);
            final int id = GeckoLibUtil.guaranteeIDForStack(pStack, (ServerLevel) pLevel); //specify ID for the item.
            final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> pEntity);
            GeckoLibNetwork.syncAnimation(target, this, id, animationReloadState);
        }
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        super.releaseUsing(pStack, pLevel, pLivingEntity, pTimeCharged);
        MineFrame.LOGGER.info("Released ====================");
        CompoundTag itemParaTag = pStack.getOrCreateTagElement("item_meta");
        int targetID = itemParaTag.getInt("target_id");
        MineFrame.LOGGER.info("this is the targets ID: ==========" + targetID + "******" + pLevel.getEntity(targetID));
        Entity entity = pLevel.getEntity(targetID);
        if (entity != null) {
            HomingFireworkProjectile homingFireworkProjectile = new HomingFireworkProjectile(pLevel,pLivingEntity.getX(), pLivingEntity.getY()+0.4, pLivingEntity.getZ(), createFireworkRocket(),targetID);
            homingFireworkProjectile.shootFromRotation(pLivingEntity, pLivingEntity.getXRot(), pLivingEntity.getYRot(), 0.0F, 1.0F, 0.0F);
            pLevel.addFreshEntity(homingFireworkProjectile);
            itemParaTag.putInt("target_id", -1);
        }
    }

    public ItemStack createFireworkRocket(){

        CompoundTag itemTag = new CompoundTag();
        CompoundTag fireworkTag = new CompoundTag();
        ListTag explosionListTag = new ListTag();
        CompoundTag explosionTag = new CompoundTag();

        explosionTag.putIntArray("Colors", new int[]{0xeb3434});
        explosionTag.putByte("Flicker", (byte) 0);
        explosionTag.putByte("Trail", (byte) 0);
        explosionTag.putByte("Type", (byte) 1);

        explosionListTag.add(explosionTag);

        fireworkTag.put("Explosions", explosionListTag);
        fireworkTag.putByte("Flight", (byte) 120);

        itemTag.put("Fireworks", fireworkTag);


        ItemStack firework = new ItemStack(Items.FIREWORK_ROCKET, 1);
        firework.setTag(itemTag);
        return firework;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.startUsingItem(pUsedHand);
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        CompoundTag itemTag = this.getOrCreateItemTag(itemStack);
        CompoundTag itemParaTag = itemStack.getOrCreateTagElement("item_meta");
        int targetID = itemParaTag.getInt("target_id");
        //search for entity
        float pPartialTicks = 1;
        double rayLength = 5000;
        Vec3 viewVector = pPlayer.getViewVector(1.0F);
        Vec3 eyePosition = pPlayer.getEyePosition(pPartialTicks);
        Vec3 vec32 = eyePosition.add(viewVector.x * rayLength, viewVector.y * rayLength, viewVector.z * rayLength);
        float f = 1.0F;
        AABB aabb = pPlayer.getBoundingBox().expandTowards(viewVector.scale(rayLength)).inflate(1.0D, 1.0D, 1.0D);
        EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult(pPlayer, eyePosition, vec32, aabb, (p_172770_)
                -> !p_172770_.isSpectator(), rayLength); // add effect check

        //use entity
        if (entityHitResult != null) {
            var foundEntity = entityHitResult.getEntity();
            if (foundEntity != null) {
                int foundID = foundEntity.getId();
                if (foundID != targetID) {
                    targetID = foundEntity.getId();
                    itemParaTag.putInt("target_id", targetID);
                    itemTag.put("item_meta", itemParaTag);
                    pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.AMBIENT, 1F, 1F);
                    MineFrame.LOGGER.info("*******************" + targetID);
                }
            }
        }

        return InteractionResultHolder.consume(itemStack);
    }

    //ANIMATION HANDLING
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

    public void onAnimationSync(int id, int state) {
        if (state == animationReloadState) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, animationControllerName);
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.anti_air_firework_launcher.reload", false));
            }
        }
    }

}
