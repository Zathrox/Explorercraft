package com.zathrox.explorercraft.common.blocks;

import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.interfaces.NoAutomaticItemBlock;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

@NoAutomaticItemBlock
public class LotusStemBlock extends BushBlock implements ILiquidContainer {
    public static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);


    public LotusStemBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        worldIn.setBlockState(pos.up(), ExplorerBlocks.LOTUS_FLOWER.getDefaultState());
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        ResourceLocation mudBlocksTag = new ResourceLocation(Explorercraft.MOD_ID, "mud_blocks");
        boolean isInGroup = BlockTags.getCollection().getOrCreate(mudBlocksTag).contains(state.getBlock());
        return Block.hasSolidSide(state, worldIn, pos, Direction.UP) &&  isInGroup && state.getBlock() != Blocks.MAGMA_BLOCK;
    }

    public boolean isFlowerAboveWater(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() == ExplorerBlocks.LOTUS_FLOWER;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        return ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8 && context.getWorld().isAirBlock(blockpos.up()) ? super.getStateForPlacement(context) : null;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {

        if (facing == Direction.UP && facingState.getBlock() != ExplorerBlocks.LOTUS_FLOWER) {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
        }

        BlockState blockstate = super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        if (!blockstate.isAir()) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return blockstate;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!this.isFlowerAboveWater(state, worldIn, pos.up())) {
            worldIn.destroyBlock(pos, true);
        }
    }

    @Override
    public IFluidState getFluidState(BlockState p_204507_1_) {
        return Fluids.WATER.getStillFluidState(false);
    }

    @Override
    public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return false;
    }

    @Override
    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
        return false;
    }
}