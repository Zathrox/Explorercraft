package com.zathrox.explorercraft.core.data.provider;

import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import com.zathrox.explorercraft.core.registry.ExplorerItems;
import com.zathrox.explorercraft.core.registry.ExplorerTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

public class ExplorercraftItemTagsProvider extends ItemTagsProvider {

    public ExplorercraftItemTagsProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerTags() {
        //getBuilder(ExplorerTags.Items.EXPLORERCRAFT).add(ExplorerItems.RUBY).add(ExplorerBlocks.RUBY_BLOCK.asItem());
        this.copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
        this.copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        //=== ORES
        this.copy(Tags.Blocks.ORES, Tags.Items.ORES);
        this.copy(ExplorerTags.Blocks.ORES_AMETHYST, ExplorerTags.Items.ORES_AMETHYST);
        this.copy(ExplorerTags.Blocks.ORES_JADE, ExplorerTags.Items.ORES_JADE);
        this.copy(ExplorerTags.Blocks.ORES_RUBY, ExplorerTags.Items.ORES_RUBY);
        this.getBuilder(Tags.Items.MUSHROOMS).add(ExplorerBlocks.GREEN_MUSHROOM.asItem()).add(ExplorerBlocks.PINK_MUSHROOM.asItem());
        //=== GEMS
        this.getBuilder(Tags.Items.GEMS).add(ExplorerTags.Items.GEMS_AMETHYST).add(ExplorerTags.Items.GEMS_JADE).add(ExplorerTags.Items.GEMS_RUBY);
        this.getBuilder(ExplorerTags.Items.GEMS_AMETHYST).add(ExplorerItems.AMETHYST);
        this.getBuilder(ExplorerTags.Items.GEMS_JADE).add(ExplorerItems.JADE);
        this.getBuilder(ExplorerTags.Items.GEMS_RUBY).add(ExplorerItems.RUBY);

        //this.copy(Tags.Blocks.ORES, Tags.Items.ORES);
        //=== STORAGE BLOCKS
        this.copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
        this.copy(ExplorerTags.Blocks.STORAGE_BLOCKS_AMETHYST, ExplorerTags.Items.STORAGE_BLOCKS_AMETHYST);
        this.copy(ExplorerTags.Blocks.STORAGE_BLOCKS_JADE, ExplorerTags.Items.STORAGE_BLOCKS_JADE);
        this.copy(ExplorerTags.Blocks.STORAGE_BLOCKS_RUBY, ExplorerTags.Items.STORAGE_BLOCKS_RUBY);

    }
}
