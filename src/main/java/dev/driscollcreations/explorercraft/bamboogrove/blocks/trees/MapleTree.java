package dev.driscollcreations.explorercraft.bamboogrove.blocks.trees;

import dev.driscollcreations.explorercraft.setup.ExplorerFeature;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class MapleTree extends Tree {

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return ExplorerFeature.MAPLE_TREE.get().withConfiguration(ExplorerFeature.Configs.MAPLE_TREE_CONFIG);
    }

}
