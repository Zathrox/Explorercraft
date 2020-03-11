package com.zathrox.explorercraft.core.data.provider;

import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import com.zathrox.explorercraft.core.registry.ExplorerTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;

public class ExplorercraftBlockTagsProvider extends BlockTagsProvider {

    public ExplorercraftBlockTagsProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerTags() {
        getBuilder(Tags.Blocks.FENCES_WOODEN).add(ExplorerBlocks.ASH_FENCE).add(ExplorerBlocks.BAMBOO_FENCE).add(ExplorerBlocks.CHERRY_FENCE).add(ExplorerBlocks.INFECTED_WILLOW_FENCE).add(ExplorerBlocks.MAPLE_FENCE).add(ExplorerBlocks.WILLOW_FENCE);
        getBuilder(Tags.Blocks.FENCE_GATES_WOODEN).add(ExplorerBlocks.ASH_FENCE_GATE).add(ExplorerBlocks.BAMBOO_FENCE_GATE).add(ExplorerBlocks.CHERRY_FENCE_GATE).add(ExplorerBlocks.INFECTED_WILLOW_FENCE_GATE).add(ExplorerBlocks.MAPLE_FENCE_GATE).add(ExplorerBlocks.WILLOW_FENCE_GATE);
        //=== ORES
        getBuilder(Tags.Blocks.ORES).add(ExplorerTags.Blocks.ORES_AMETHYST).add(ExplorerTags.Blocks.ORES_JADE).add(ExplorerTags.Blocks.ORES_RUBY);
        getBuilder(ExplorerTags.Blocks.ORES_AMETHYST).add(ExplorerBlocks.AMETHYST_ORE);
        getBuilder(ExplorerTags.Blocks.ORES_JADE).add(ExplorerBlocks.JADE_ORE);
        getBuilder(ExplorerTags.Blocks.ORES_RUBY).add(ExplorerBlocks.RUBY_ORE);
        //=== STORAGE BLOCKS
        getBuilder(Tags.Blocks.STORAGE_BLOCKS).add(ExplorerTags.Blocks.STORAGE_BLOCKS_AMETHYST).add(ExplorerTags.Blocks.STORAGE_BLOCKS_JADE).add(ExplorerTags.Blocks.STORAGE_BLOCKS_RUBY);
        getBuilder(ExplorerTags.Blocks.STORAGE_BLOCKS_AMETHYST).add(ExplorerBlocks.AMETHYST_BLOCK);
        getBuilder(ExplorerTags.Blocks.STORAGE_BLOCKS_JADE).add(ExplorerBlocks.JADE_BLOCK);
        getBuilder(ExplorerTags.Blocks.STORAGE_BLOCKS_RUBY).add(ExplorerBlocks.RUBY_BLOCK);


    }
}
