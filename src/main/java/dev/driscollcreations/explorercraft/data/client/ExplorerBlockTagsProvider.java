package dev.driscollcreations.explorercraft.data.client;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.setup.ExplorerTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ExplorerBlockTagsProvider extends BlockTagsProvider {

    public ExplorerBlockTagsProvider(DataGenerator generatorIn, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, Explorercraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        getOrCreateBuilder(ExplorerTags.Blocks.STORAGE_BLOCKS_JADE).add(BambooGroveBlocks.JADE_BLOCK.get());
        getOrCreateBuilder(Tags.Blocks.STORAGE_BLOCKS).addTag(ExplorerTags.Blocks.STORAGE_BLOCKS_JADE);

        getOrCreateBuilder(Tags.Blocks.FENCES_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_FENCE.get())
                .add(BambooGroveBlocks.CHERRY_FENCE.get())
                .add(BambooGroveBlocks.MAPLE_FENCE.get());

        getOrCreateBuilder(Tags.Blocks.FENCE_GATES_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_FENCE_GATE.get())
                .add(BambooGroveBlocks.CHERRY_FENCE_GATE.get())
                .add(BambooGroveBlocks.MAPLE_FENCE_GATE.get());

        getOrCreateBuilder(ExplorerTags.Blocks.FENCES_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_FENCE.get())
                .add(BambooGroveBlocks.CHERRY_FENCE.get())
                .add(BambooGroveBlocks.MAPLE_FENCE.get());

        getOrCreateBuilder(ExplorerTags.Blocks.BUTTONS_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_BUTTON.get())
                .add(BambooGroveBlocks.CHERRY_BUTTON.get())
                .add(BambooGroveBlocks.MAPLE_BUTTON.get());

        getOrCreateBuilder(ExplorerTags.Blocks.BUTTONS)
                .add(BambooGroveBlocks.BAMBOO_BUTTON.get())
                .add(BambooGroveBlocks.CHERRY_BUTTON.get())
                .add(BambooGroveBlocks.MAPLE_BUTTON.get());

        getOrCreateBuilder(ExplorerTags.Blocks.DOORS_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_DOOR.get())
                .add(BambooGroveBlocks.CHERRY_DOOR.get())
                .add(BambooGroveBlocks.MAPLE_DOOR.get());

        getOrCreateBuilder(ExplorerTags.Blocks.DOORS)
                .add(BambooGroveBlocks.BAMBOO_DOOR.get())
                .add(BambooGroveBlocks.CHERRY_DOOR.get())
                .add(BambooGroveBlocks.MAPLE_DOOR.get());

        getOrCreateBuilder(ExplorerTags.Blocks.PRESSURE_PLATES_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_PRESSURE_PLATE.get())
                .add(BambooGroveBlocks.CHERRY_PRESSURE_PLATE.get())
                .add(BambooGroveBlocks.MAPLE_PRESSURE_PLATE.get());

        getOrCreateBuilder(ExplorerTags.Blocks.SLABS_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_SLAB.get())
                .add(BambooGroveBlocks.CHERRY_SLAB.get())
                .add(BambooGroveBlocks.MAPLE_SLAB.get());

        getOrCreateBuilder(ExplorerTags.Blocks.STAIRS_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_STAIRS.get())
                .add(BambooGroveBlocks.CHERRY_STAIRS.get())
                .add(BambooGroveBlocks.MAPLE_STAIRS.get());

        getOrCreateBuilder(ExplorerTags.Blocks.TRAPDOORS_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_TRAPDOOR.get())
                .add(BambooGroveBlocks.CHERRY_TRAPDOOR.get())
                .add(BambooGroveBlocks.MAPLE_TRAPDOOR.get());

        getOrCreateBuilder(ExplorerTags.Blocks.LEAVES)
                .add(BambooGroveBlocks.BAMBOO_LEAVES.get())
                .add(BambooGroveBlocks.CHERRY_LEAVES.get())
                .add(BambooGroveBlocks.MAPLE_LEAVES.get());

        getOrCreateBuilder(ExplorerTags.Blocks.LOGS)
                .add(BambooGroveBlocks.BAMBOO_LOG.get())
                .add(BambooGroveBlocks.CHERRY_LOG.get())
                .add(BambooGroveBlocks.MAPLE_LOG.get());


    }
}
