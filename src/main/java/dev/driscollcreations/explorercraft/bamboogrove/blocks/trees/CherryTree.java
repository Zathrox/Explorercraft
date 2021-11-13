package dev.driscollcreations.explorercraft.bamboogrove.blocks.trees;

import dev.driscollcreations.explorercraft.setup.ExplorerFeature;
import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

public class CherryTree extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random randomIn, boolean largeHive) {
        return Feature.TREE.configured(Features.OAK.config);
    }

}
