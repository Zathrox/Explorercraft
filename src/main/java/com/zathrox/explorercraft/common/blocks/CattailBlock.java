package com.zathrox.explorercraft.common.blocks;

import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.*;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;

public class CattailBlock extends BushBlock implements IGrowable, net.minecraftforge.common.IShearable {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public CattailBlock(Block.Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos.down()).getBlock();
        if (block == ExplorerBlocks.MUD) {
            BlockPos blockpos = pos.down();

            for(Direction direction : Direction.Plane.HORIZONTAL) {
                BlockState blockstate = worldIn.getBlockState(blockpos.offset(direction));
                IFluidState ifluidstate = worldIn.getFluidState(blockpos.offset(direction));
                BlockState blockstate1 = worldIn.getBlockState(blockpos.offset(direction, 2));
                IFluidState ifluidstate1 = worldIn.getFluidState(blockpos.offset(direction, 2));
                if (ifluidstate.isTagged(FluidTags.WATER) || blockstate.getBlock() == Blocks.FROSTED_ICE || ifluidstate1.isTagged(FluidTags.WATER) || blockstate1.getBlock() == Blocks.FROSTED_ICE) {
                    return true;
                }
            }
        }

        return false;

    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {
        TallCattailBlock doubleplantblock = (TallCattailBlock)ExplorerBlocks.TALL_CATTAIL;
        if (doubleplantblock.getDefaultState().isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up())) {
            doubleplantblock.placeAt(worldIn, pos, 2);
        }

    }

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XYZ;
    }
}
