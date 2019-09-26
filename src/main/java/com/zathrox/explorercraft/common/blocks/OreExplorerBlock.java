package com.zathrox.explorercraft.common.blocks;

import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;

import java.util.Random;

public class OreExplorerBlock extends Block {
    public OreExplorerBlock(Block.Properties properties) {
        super(properties);
    }

    protected int func_220281_a(Random p_220281_1_) {
        if (this == ExplorerBlocks.AMETHYST_ORE) {
            return MathHelper.nextInt(p_220281_1_, 3, 7);
        } else if (this == ExplorerBlocks.JADE_ORE) {
            return MathHelper.nextInt(p_220281_1_, 3, 7);
        } else {
            return this == ExplorerBlocks.RUBY_ORE ? MathHelper.nextInt(p_220281_1_, 3, 7) : 0;
        }
    }

    @Override
    public boolean isBeaconBase(BlockState state, IWorldReader world, BlockPos pos, BlockPos beacon) {
        return true;
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.func_220281_a(RANDOM) : 0;
    }
}