package com.stargazer.mineframe.utils;

import com.stargazer.mineframe.setups.Registrations;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;

public class ModFlammablePillarBlocks extends RotatedPillarBlock {
    public ModFlammablePillarBlocks(Properties p_55926_) {
        super(p_55926_);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, Level world, BlockPos pos, Player player, ItemStack stack, ToolAction toolAction) {
        //for logs and wood
        if (stack.getItem() instanceof AxeItem){
            if (state.is(Registrations.SULFURIC_BLOOMTREE_LOG.get())){
                return Registrations.STRIPPED_SULFURIC_BLOOMTREE_LOG.get().defaultBlockState().setValue(AXIS,state.getValue(AXIS));
            }
            if (state.is(Registrations.SULFURIC_BLOOMTREE_WOOD.get())){
                return Registrations.STRIPPED_SULFURIC_BLOOMTREE_WOOD.get().defaultBlockState().setValue(AXIS,state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, world, pos, player, stack, toolAction);
    }
}
