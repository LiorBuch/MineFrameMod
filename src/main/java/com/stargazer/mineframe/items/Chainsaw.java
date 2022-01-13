package com.stargazer.mineframe.items;

import com.stargazer.mineframe.renderers.ChainsawRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
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

public class Chainsaw extends AxeItem implements IAnimatable, ISyncable {
    //Animation utils
    private final String animationControllerName = "controllerchainsaw";
    public AnimationFactory factory = new AnimationFactory(this);
    private final int animationCutState = 1;

    public Chainsaw(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new ChainsawRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);

    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        if (!pLevel.isClientSide) {
            final int id = GeckoLibUtil.guaranteeIDForStack(pStack, (ServerLevel) pLevel); //specify ID for the item.
            final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> pEntity);
            GeckoLibNetwork.syncAnimation(target, this, id, animationCutState);
        }
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (pLevel.getBlockState(pPos).is(BlockTags.LOGS)) {
            isNeighborWood(pPos, pLevel, 100);
            return true;
        }
        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }

    public void isNeighborWood(BlockPos blockPos, Level level, int allowedCuts) {
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        int cutsRemaining = allowedCuts;
        if (allowedCuts != 0) {
            if (level.getBlockState(new BlockPos(x + 1, y, z)).is(BlockTags.LOGS)) {
                level.destroyBlock(new BlockPos(x + 1, y, z), true);
                cutsRemaining--;
                isNeighborWood(new BlockPos(x + 1, y, z), level, cutsRemaining);
            }
            if (level.getBlockState(new BlockPos(x - 1, y, z)).is(BlockTags.LOGS)) {
                level.destroyBlock(new BlockPos(x - 1, y, z), true);
                cutsRemaining--;
                isNeighborWood(new BlockPos(x - 1, y, z), level, cutsRemaining);
            }
            if (level.getBlockState(new BlockPos(x, y + 1, z)).is(BlockTags.LOGS)) {
                level.destroyBlock(new BlockPos(x, y + 1, z), true);
                cutsRemaining--;
                isNeighborWood(new BlockPos(x, y + 1, z), level, cutsRemaining);
            }
            if (level.getBlockState(new BlockPos(x, y, z + 1)).is(BlockTags.LOGS)) {
                level.destroyBlock(new BlockPos(x, y, z + 1), true);
                cutsRemaining--;
                isNeighborWood(new BlockPos(x, y, z + 1), level, cutsRemaining);
            }
            if (level.getBlockState(new BlockPos(x, y, z - 1)).is(BlockTags.LOGS)) {
                level.destroyBlock(new BlockPos(x, y, z - 1), true);
                cutsRemaining--;
                isNeighborWood(new BlockPos(x, y, z - 1), level, cutsRemaining);
            }
        }
    }

    //ANIMATION CONTROLLING
    @Override
    public void onAnimationSync(int id, int state) {
        if (state == animationCutState) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, animationControllerName);
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.chainsaw.work", false));
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
