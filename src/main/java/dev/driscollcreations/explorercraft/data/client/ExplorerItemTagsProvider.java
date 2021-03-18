package dev.driscollcreations.explorercraft.data.client;

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
    protected void registerTags() {
        copy(ExplorerTags.Blocks.ORES_JADE, ExplorerTags.Items.ORES_JADE);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);

        copy(ExplorerTags.Blocks.STORAGE_BLOCKS_JADE, ExplorerTags.Items.STORAGE_BLOCKS_JADE);
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

        getOrCreateBuilder(ExplorerTags.Items.JADE).add(BambooGroveItems.JADE.get());
        getOrCreateBuilder(Tags.Items.GEMS).addTag(ExplorerTags.Items.JADE);
    }
}
