package com.stargazer.mineframe.items;

import com.stargazer.mineframe.renderers.DrillRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.OreBlock;
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

public class Drill extends PickaxeItem implements IAnimatable, ISyncable {
    private final String animationControllerName = "controllerdrill";
    public AnimationFactory factory = new AnimationFactory(this);
    private final int animationDrillState = 1;

    public Drill(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new DrillRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        if (!pLevel.isClientSide) {
            final int id = GeckoLibUtil.guaranteeIDForStack(pStack, (ServerLevel) pLevel); //specify ID for the item.
            final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> pEntity);
            GeckoLibNetwork.syncAnimation(target, this, id, animationDrillState);
            if (!pLevel.isClientSide()) {
                for (int i = 0; i < 3; i++) {
                    pLevel.addParticle(ParticleTypes.FLAME, pEntity.getX(), pEntity.getY() , pEntity.getZ(), 0d, 0.05d, 0d);
                }
            }
        }
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);

    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (!(pState.getBlock().equals(Blocks.STONE))&& pState.getBlock() instanceof OreBlock) {
            isNeighborOre(pPos, pLevel, 20,pState);
            return true;
        }
        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }

    public void isNeighborOre(BlockPos blockPos, Level level, int allowedBreaks,BlockState typeOfOre) {
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        int breaksRemaining = allowedBreaks;
        if (allowedBreaks != 0) {
            if (level.getBlockState(new BlockPos(x + 1, y, z)).getBlock() == typeOfOre.getBlock()) {
                level.destroyBlock(new BlockPos(x + 1, y, z), true);
                breaksRemaining--;
                isNeighborOre(new BlockPos(x + 1, y, z), level, breaksRemaining,typeOfOre);
            }
            if (level.getBlockState(new BlockPos(x - 1, y, z)).getBlock() == typeOfOre.getBlock()) {
                level.destroyBlock(new BlockPos(x - 1, y, z), true);
                breaksRemaining--;
                isNeighborOre(new BlockPos(x - 1, y, z), level, breaksRemaining,typeOfOre);
            }
            if (level.getBlockState(new BlockPos(x, y + 1, z)).getBlock() == typeOfOre.getBlock()) {
                level.destroyBlock(new BlockPos(x, y + 1, z), true);
                breaksRemaining--;
                isNeighborOre(new BlockPos(x, y + 1, z), level, breaksRemaining,typeOfOre);
            }
            if (level.getBlockState(new BlockPos(x, y, z + 1)).getBlock() == typeOfOre.getBlock()) {
                level.destroyBlock(new BlockPos(x, y, z + 1), true);
                breaksRemaining--;
                isNeighborOre(new BlockPos(x, y, z + 1), level, breaksRemaining,typeOfOre);
            }
            if (level.getBlockState(new BlockPos(x, y, z - 1)).getBlock() == typeOfOre.getBlock()) {
                level.destroyBlock(new BlockPos(x, y, z - 1), true);
                breaksRemaining--;
                isNeighborOre(new BlockPos(x, y, z - 1), level, breaksRemaining,typeOfOre);
            }
            if (level.getBlockState(new BlockPos(x, y-1, z)).getBlock() == typeOfOre.getBlock()) {
                level.destroyBlock(new BlockPos(x, y-1, z - 1), true);
                breaksRemaining--;
                isNeighborOre(new BlockPos(x, y-1, z), level, breaksRemaining,typeOfOre);
            }
        }
    }



    //ANIMATION CONTROLLING
    @Override
    public void onAnimationSync(int id, int state) {
        if (state == animationDrillState) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, animationControllerName);
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.drill.use", false));
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
