package com.stargazer.mineframe.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ModFlammableBlock extends Block {
    private boolean flameable;
    private int flammmability;
    private int fireSpreadSpeed;

    public ModFlammableBlock(Properties p_49795_, boolean flameable, int flammmability, int fireSpreadSpeed) {
        super(p_49795_);
        this.flameable = flameable;
        this.flammmability = flammmability;
        this.fireSpreadSpeed = fireSpreadSpeed;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return flameable;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return flammmability;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return fireSpreadSpeed;
    }
}
