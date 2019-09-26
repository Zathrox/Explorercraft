package com.zathrox.explorercraft.common.world.feature;

import com.mojang.datafixers.Dynamic;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class SnowdoniaFlowersFeature extends FlowersFeature {

    public SnowdoniaFlowersFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49889_1_) {
        super(p_i49889_1_);
    }

    public BlockState getRandomFlower(Random random, BlockPos pos) {
        return pos.getY() > 100 ? ExplorerBlocks.DAFFODIL.getDefaultState() : Blocks.LILY_OF_THE_VALLEY.getDefaultState();
    }
}