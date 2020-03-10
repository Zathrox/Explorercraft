package com.zathrox.explorercraft.common.blocks;

import com.zathrox.explorercraft.core.interfaces.NoAutomaticItemBlock;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import com.zathrox.explorercraft.core.registry.ExplorerItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@NoAutomaticItemBlock
public class LotusFlowerBlock extends BushBlock {


    public LotusFlowerBlock(Properties p_i48433_1_) {
        super(p_i48433_1_);
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() == ExplorerBlocks.LOTUS_STEM;
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(ExplorerItems.LOTUS_FLOWER);
    }
}