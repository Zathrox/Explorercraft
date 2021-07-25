package dev.driscollcreations.explorercraft.cymru.blocks.trees;

import dev.driscollcreations.explorercraft.setup.ExplorerFeature;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class AshTree extends Tree {

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random randomIn, boolean largeHive) {
        return ExplorerFeature.ASH_TREE.get().configured(ExplorerFeature.Configs.ASH_TREE_CONFIG);
    }

}
