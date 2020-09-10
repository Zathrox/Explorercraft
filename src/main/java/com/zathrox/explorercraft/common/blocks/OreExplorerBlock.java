package com.zathrox.explorercraft.common.blocks;

import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Random;

public class OreExplorerBlock extends OreBlock {

    private final int harvestLevel;

    public OreExplorerBlock(Properties properties, int harvestLevel) {

        super(properties);
        this.harvestLevel = harvestLevel;
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState state) {
        return ToolType.PICKAXE;
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return this.harvestLevel;
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