package com.stargazer.mineframe.blocks;

import com.stargazer.mineframe.utils.ModFlammableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

import java.util.Random;

public class SulfuricBloomTreeLeaves extends LeavesBlock {


    public SulfuricBloomTreeLeaves(Properties p_54422_) {
        super(p_54422_);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, Random pRandom) {
        super.animateTick(pState, pLevel, pPos, pRandom);
        double d0 = (double)pPos.getX() + pRandom.nextDouble();
        double d1 = (double)pPos.getY() - 0.05D;
        double d2 = (double)pPos.getZ() + pRandom.nextDouble();
        pLevel.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.GOLD_BLOCK.defaultBlockState()), d0, d1, d2, 0.0D, 0.0D, 0.0D);

    }
}
