package dev.driscollcreations.explorercraft.data.tags;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import dev.driscollcreations.explorercraft.setup.ExplorerTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ExplorerItemTagsProvider extends ItemTagsProvider {

    public ExplorerItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, Explorercraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        copy(ExplorerTags.Blocks.ORES_JADE, ExplorerTags.Items.ORES_JADE);
        copy(ExplorerTags.Blocks.ORES_AMETHYST, ExplorerTags.Items.ORES_AMETHYST);
        copy(ExplorerTags.Blocks.ORES_RUBY, ExplorerTags.Items.ORES_RUBY);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);

        copy(ExplorerTags.Blocks.STORAGE_BLOCKS_JADE, ExplorerTags.Items.STORAGE_BLOCKS_JADE);
        copy(ExplorerTags.Blocks.STORAGE_BLOCKS_RUBY, ExplorerTags.Items.STORAGE_BLOCKS_RUBY);
        copy(ExplorerTags.Blocks.STORAGE_BLOCKS_AMETHYST, ExplorerTags.Items.STORAGE_BLOCKS_AMETHYST);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
        copy(ExplorerTags.Blocks.FENCES_WOODEN, ExplorerTags.Items.FENCES_WOODEN);
        copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        copy(ExplorerTags.Blocks.BUTTONS_WOODEN, ExplorerTags.Items.BUTTONS_WOODEN);
        copy(ExplorerTags.Blocks.DOORS_WOODEN, ExplorerTags.Items.DOORS_WOODEN);
        copy(ExplorerTags.Blocks.PRESSURE_PLATES_WOODEN, ExplorerTags.Items.PRESSURE_PLATES_WOODEN);
        copy(ExplorerTags.Blocks.SLABS_WOODEN, ExplorerTags.Items.SLABS_WOODEN);
        copy(ExplorerTags.Blocks.STAIRS_WOODEN, ExplorerTags.Items.STAIRS_WOODEN);
        copy(ExplorerTags.Blocks.TRAPDOORS_WOODEN, ExplorerTags.Items.TRAPDOORS_WOODEN);

        copy(ExplorerTags.Blocks.LEAVES, ExplorerTags.Items.LEAVES);
        copy(ExplorerTags.Blocks.LOGS, ExplorerTags.Items.LOGS);
        copy(ExplorerTags.Blocks.PLANKS, ExplorerTags.Items.PLANKS);
        copy(ExplorerTags.Blocks.WALLS, ExplorerTags.Items.WALLS);

        copy(ExplorerTags.Blocks.BAMBOO_LOGS, ExplorerTags.Items.BAMBOO_LOGS);
        copy(ExplorerTags.Blocks.CHERRY_LOGS, ExplorerTags.Items.CHERRY_LOGS);
        copy(ExplorerTags.Blocks.MAPLE_LOGS, ExplorerTags.Items.MAPLE_LOGS);

        tag(ExplorerTags.Items.JADE).add(BambooGroveItems.JADE.get());
        tag(Tags.Items.GEMS).addTag(ExplorerTags.Items.JADE);
        tag(ExplorerTags.Items.AMETHYST).add(BambooGroveItems.AMETHYST.get());
        tag(Tags.Items.GEMS).addTag(ExplorerTags.Items.AMETHYST);
        tag(ExplorerTags.Items.RUBY).add(BambooGroveItems.RUBY.get());
        tag(Tags.Items.GEMS).addTag(ExplorerTags.Items.RUBY);
    }
}
