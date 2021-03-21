package dev.driscollcreations.explorercraft.bamboogrove.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class PanelBlock extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);

    public PanelBlock(AbstractBlock.Properties p_i48413_1_) {
        super(p_i48413_1_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    public VoxelShape getShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos, ISelectionContext context) {
        Direction direction = blockState.getValue(FACING);
        switch(direction) {
            case EAST:
            default:
                return EAST_AABB;
            case SOUTH:
                return SOUTH_AABB;
            case WEST:
                return WEST_AABB;
            case NORTH:
                return NORTH_AABB;
        }
    }

    @Override
    public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        DoubleBlockHalf doubleblockhalf = p_196271_1_.getValue(HALF);
        if (p_196271_2_.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (p_196271_2_ == Direction.UP)) {
            return p_196271_3_.is(this) && p_196271_3_.getValue(HALF) != doubleblockhalf ? p_196271_1_.setValue(FACING, p_196271_3_.getValue(FACING)) : Blocks.AIR.defaultBlockState();
        } else {
            return doubleblockhalf == DoubleBlockHalf.LOWER && p_196271_2_ == Direction.DOWN && !p_196271_1_.canSurvive(p_196271_4_, p_196271_5_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
        }
    }

    @Override
    public void playerWillDestroy(World world, BlockPos blockPos, BlockState blockState, PlayerEntity entity) {
        if (!world.isClientSide && entity.isCreative()) {
            DoubleBlockHalf doubleblockhalf = blockState.getValue(HALF);
            if (doubleblockhalf == DoubleBlockHalf.UPPER) {
                BlockPos blockpos = blockPos.below();
                BlockState blockstate = world.getBlockState(blockpos);
                if (blockstate.getBlock() == blockState.getBlock() && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
                    world.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
                    world.levelEvent(entity, 2001, blockpos, Block.getId(blockstate));
                }
            }
        }

        super.playerWillDestroy(world, blockPos, blockState, entity);
    }

    @Override
    public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        BlockPos blockpos = p_196258_1_.getClickedPos();
        if (blockpos.getY() < 255 && p_196258_1_.getLevel().getBlockState(blockpos.above()).canBeReplaced(p_196258_1_)) {
            return this.defaultBlockState().setValue(FACING, p_196258_1_.getHorizontalDirection()).setValue(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    @Override
    public void setPlacedBy(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, LivingEntity p_180633_4_, ItemStack p_180633_5_) {
        p_180633_1_.setBlock(p_180633_2_.above(), p_180633_3_.setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }

    @Override
    public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        BlockPos blockpos = p_196260_3_.below();
        BlockState blockstate = p_196260_2_.getBlockState(blockpos);
        return p_196260_1_.getValue(HALF) == DoubleBlockHalf.LOWER ? blockstate.isFaceSturdy(p_196260_2_, blockpos, Direction.UP) : blockstate.is(this);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState p_149656_1_) {
        return PushReaction.DESTROY;
    }

    @Override
    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
        return p_185471_2_ == Mirror.NONE ? p_185471_1_ : p_185471_1_.rotate(p_185471_2_.getRotation(p_185471_1_.getValue(FACING)));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public long getSeed(BlockState p_209900_1_, BlockPos p_209900_2_) {
        return MathHelper.getSeed(p_209900_2_.getX(), p_209900_2_.below(p_209900_1_.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), p_209900_2_.getZ());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(HALF, FACING);
    }
}
