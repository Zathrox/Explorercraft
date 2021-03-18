package dev.driscollcreations.explorercraft.bamboogrove.blocks.trees;

import dev.driscollcreations.explorercraft.setup.ExplorerFeature;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

import javax.annotation.Nullable;
import java.util.Random;

public class CherryTree extends Tree {

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return Feature.TREE.withConfiguration(ExplorerFeature.Configs.CHERRY_TREE_CONFIG);
    }

}
