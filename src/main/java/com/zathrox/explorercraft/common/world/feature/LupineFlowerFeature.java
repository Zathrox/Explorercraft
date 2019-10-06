package com.zathrox.explorercraft.common.world.feature;

import com.mojang.datafixers.Dynamic;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class LupineFlowerFeature extends FlowersFeature {

    public LupineFlowerFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51426_1_) {
        super(p_i51426_1_);
    }

    public BlockState getRandomFlower(Random p_202355_1_, BlockPos p_202355_2_) {
        return ExplorerBlocks.LUPINE.getDefaultState();
    }
}
