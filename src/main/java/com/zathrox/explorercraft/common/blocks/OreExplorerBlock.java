package com.zathrox.explorercraft.common.blocks;

import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;

import java.util.Random;

public class OreExplorerBlock extends Block {
    public OreExplorerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return super.getHarvestLevel(state);
    }

    protected int getExperience(Random random) {
        if (this == ExplorerBlocks.AMETHYST_ORE) {
            return MathHelper.nextInt(random, 3, 7);
        } else if (this == ExplorerBlocks.JADE_ORE) {
            return MathHelper.nextInt(random, 3, 7);
        } else {
            return this == ExplorerBlocks.RUBY_ORE ? MathHelper.nextInt(random, 3, 7) : 0;
        }
    }

    @Override
    public boolean isBeaconBase(BlockState state, IWorldReader world, BlockPos pos, BlockPos beacon) {
        return true;
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.getExperience(RANDOM) : 0;
    }
}