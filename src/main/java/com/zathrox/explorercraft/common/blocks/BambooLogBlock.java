package com.zathrox.explorercraft.common.blocks;

import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FourWayBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.LeadItem;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class BambooLogBlock extends FourWayBlock implements IPlantable {
    private final VoxelShape[] renderShapes;

    public BambooLogBlock(Block.Properties p_i48399_1_) {
        super(2.0F, 2.0F, 16.0F, 16.0F, 24.0F, p_i48399_1_);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with(NORTH, false)).with(EAST, false)).with(SOUTH, false)).with(WEST, false)).with(WATERLOGGED, false));
        this.renderShapes = this.makeShapes(2.0F, 1.0F, 16.0F, 6.0F, 15.0F);
    }

    public VoxelShape getRenderShape(BlockState p_196247_1_, IBlockReader p_196247_2_, BlockPos p_196247_3_) {
        return this.renderShapes[this.getIndex(p_196247_1_)];
    }

    @Override
    public boolean allowsMovement(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }

    public boolean func_220111_a(BlockState p_220111_1_, boolean p_220111_2_, Direction p_220111_3_) {
        if (p_220111_1_.getBlock() == ExplorerBlocks.BAMBOO_LEAVES) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (!state.isValidPosition(worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
        }
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState soil = worldIn.getBlockState(pos.down());
        if (soil.canSustainPlant(worldIn, pos.down(), Direction.UP, this)) return true;
        Block block = worldIn.getBlockState(pos.down()).getBlock();
        if (block == this) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onBlockActivated(BlockState p_220051_1_, World p_220051_2_, BlockPos p_220051_3_, PlayerEntity p_220051_4_, Hand p_220051_5_, BlockRayTraceResult p_220051_6_) {
        if (!p_220051_2_.isRemote) {
            return LeadItem.attachToFence(p_220051_4_, p_220051_2_, p_220051_3_);
        } else {
            ItemStack lvt_7_1_ = p_220051_4_.getHeldItem(p_220051_5_);
            return lvt_7_1_.getItem() == Items.LEAD || lvt_7_1_.isEmpty();
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        IBlockReader lvt_2_1_ = p_196258_1_.getWorld();
        BlockPos lvt_3_1_ = p_196258_1_.getPos();
        IFluidState lvt_4_1_ = p_196258_1_.getWorld().getFluidState(p_196258_1_.getPos());
        BlockPos lvt_5_1_ = lvt_3_1_.north();
        BlockPos lvt_6_1_ = lvt_3_1_.east();
        BlockPos lvt_7_1_ = lvt_3_1_.south();
        BlockPos lvt_8_1_ = lvt_3_1_.west();
        BlockState lvt_9_1_ = lvt_2_1_.getBlockState(lvt_5_1_);
        BlockState lvt_10_1_ = lvt_2_1_.getBlockState(lvt_6_1_);
        BlockState lvt_11_1_ = lvt_2_1_.getBlockState(lvt_7_1_);
        BlockState lvt_12_1_ = lvt_2_1_.getBlockState(lvt_8_1_);
        return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)super.getStateForPlacement(p_196258_1_).with(NORTH, this.func_220111_a(lvt_9_1_, lvt_9_1_.func_224755_d(lvt_2_1_, lvt_5_1_, Direction.SOUTH), Direction.SOUTH))).with(EAST, this.func_220111_a(lvt_10_1_, lvt_10_1_.func_224755_d(lvt_2_1_, lvt_6_1_, Direction.WEST), Direction.WEST))).with(SOUTH, this.func_220111_a(lvt_11_1_, lvt_11_1_.func_224755_d(lvt_2_1_, lvt_7_1_, Direction.NORTH), Direction.NORTH))).with(WEST, this.func_220111_a(lvt_12_1_, lvt_12_1_.func_224755_d(lvt_2_1_, lvt_8_1_, Direction.EAST), Direction.EAST))).with(WATERLOGGED, lvt_4_1_.getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction p_196271_2_, BlockState p_196271_3_, IWorld worldIn, BlockPos currentPos, BlockPos p_196271_6_) {
        if (!stateIn.isValidPosition(worldIn, currentPos)) {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
        }
        if ((Boolean)stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }

        return p_196271_2_.getAxis().getPlane() == Direction.Plane.HORIZONTAL ? (BlockState)stateIn.with((IProperty)FACING_TO_PROPERTY_MAP.get(p_196271_2_), this.func_220111_a(p_196271_3_, p_196271_3_.func_224755_d(worldIn, p_196271_6_, p_196271_2_.getOpposite()), p_196271_2_.getOpposite())) : super.updatePostPlacement(stateIn, p_196271_2_, p_196271_3_, worldIn, currentPos, p_196271_6_);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new IProperty[]{NORTH, EAST, WEST, SOUTH, WATERLOGGED});
    }

    @Override
    public BlockState getPlant(IBlockReader world, BlockPos pos) {
        return getDefaultState();
    }
}