package dev.driscollcreations.explorercraft.data.tags;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.cymru.blocks.CymruBlocks;
import dev.driscollcreations.explorercraft.setup.ExplorerTags;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
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
    protected void addTags() {
        tag(ExplorerTags.Blocks.STORAGE_BLOCKS_JADE).add(BambooGroveBlocks.JADE_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ExplorerTags.Blocks.STORAGE_BLOCKS_JADE);

        tag(ExplorerTags.Blocks.STORAGE_BLOCKS_AMETHYST).add(VanillaTweaksBlocks.AMETHYST_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ExplorerTags.Blocks.STORAGE_BLOCKS_AMETHYST);

        tag(ExplorerTags.Blocks.STORAGE_BLOCKS_RUBY).add(VanillaTweaksBlocks.RUBY_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ExplorerTags.Blocks.STORAGE_BLOCKS_RUBY);

        tag(ExplorerTags.Blocks.BASE_STONE_OVERWORLD)
                .add(VanillaTweaksBlocks.MARBLE.get())
                .add(VanillaTweaksBlocks.BASALT.get())
                .add(CymruBlocks.SLATE.get());

        tag(Tags.Blocks.FENCES_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_FENCE.get())
                .add(BambooGroveBlocks.CHERRY_FENCE.get())
                .add(BambooGroveBlocks.CHERRY_BLOSSOM_FENCE.get())
                .add(BambooGroveBlocks.MAPLE_FENCE.get())
                .add(CymruBlocks.ASH_FENCE.get());

        tag(Tags.Blocks.FENCE_GATES_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_FENCE_GATE.get())
                .add(BambooGroveBlocks.CHERRY_FENCE_GATE.get())
                .add(BambooGroveBlocks.CHERRY_BLOSSOM_FENCE_GATE.get())
                .add(BambooGroveBlocks.MAPLE_FENCE_GATE.get())
                .add(CymruBlocks.ASH_FENCE_GATE.get());

        tag(ExplorerTags.Blocks.FENCES_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_FENCE.get())
                .add(BambooGroveBlocks.CHERRY_FENCE.get())
                .add(BambooGroveBlocks.CHERRY_BLOSSOM_FENCE.get())
                .add(BambooGroveBlocks.MAPLE_FENCE.get())
                .add(CymruBlocks.ASH_FENCE.get());

        tag(ExplorerTags.Blocks.BUTTONS_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_BUTTON.get())
                .add(BambooGroveBlocks.CHERRY_BUTTON.get())
                .add(BambooGroveBlocks.CHERRY_BLOSSOM_BUTTON.get())
                .add(BambooGroveBlocks.MAPLE_BUTTON.get())
                .add(CymruBlocks.ASH_BUTTON.get());

        tag(ExplorerTags.Blocks.BUTTONS)
                .add(BambooGroveBlocks.BAMBOO_BUTTON.get())
                .add(BambooGroveBlocks.CHERRY_BUTTON.get())
                .add(BambooGroveBlocks.CHERRY_BLOSSOM_BUTTON.get())
                .add(BambooGroveBlocks.MAPLE_BUTTON.get())
                .add(CymruBlocks.ASH_BUTTON.get());

        tag(ExplorerTags.Blocks.DOORS_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_DOOR.get())
                .add(BambooGroveBlocks.CHERRY_DOOR.get())
                .add(BambooGroveBlocks.CHERRY_BLOSSOM_DOOR.get())
                .add(BambooGroveBlocks.MAPLE_DOOR.get())
                .add(CymruBlocks.ASH_DOOR.get());

        tag(ExplorerTags.Blocks.DOORS)
                .add(BambooGroveBlocks.BAMBOO_DOOR.get())
                .add(BambooGroveBlocks.CHERRY_DOOR.get())
                .add(BambooGroveBlocks.CHERRY_BLOSSOM_DOOR.get())
                .add(BambooGroveBlocks.MAPLE_DOOR.get())
                .add(CymruBlocks.ASH_DOOR.get());

        tag(ExplorerTags.Blocks.PRESSURE_PLATES_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_PRESSURE_PLATE.get())
                .add(BambooGroveBlocks.CHERRY_PRESSURE_PLATE.get())
                .add(BambooGroveBlocks.CHERRY_BLOSSOM_PRESSURE_PLATE.get())
                .add(BambooGroveBlocks.MAPLE_PRESSURE_PLATE.get())
                .add(CymruBlocks.ASH_PRESSURE_PLATE.get());

        tag(ExplorerTags.Blocks.SLABS_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_SLAB.get())
                .add(BambooGroveBlocks.CHERRY_SLAB.get())
                .add(BambooGroveBlocks.CHERRY_BLOSSOM_SLAB.get())
                .add(BambooGroveBlocks.MAPLE_SLAB.get())
                .add(CymruBlocks.ASH_SLAB.get());

        tag(ExplorerTags.Blocks.STAIRS_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_STAIRS.get())
                .add(BambooGroveBlocks.CHERRY_STAIRS.get())
                .add(BambooGroveBlocks.CHERRY_BLOSSOM_STAIRS.get())
                .add(BambooGroveBlocks.MAPLE_STAIRS.get())
                .add(CymruBlocks.ASH_STAIRS.get());

        tag(ExplorerTags.Blocks.TRAPDOORS_WOODEN)
                .add(BambooGroveBlocks.BAMBOO_TRAPDOOR.get())
                .add(BambooGroveBlocks.CHERRY_TRAPDOOR.get())
                .add(BambooGroveBlocks.CHERRY_BLOSSOM_TRAPDOOR.get())
                .add(BambooGroveBlocks.MAPLE_TRAPDOOR.get())
                .add(CymruBlocks.ASH_TRAPDOOR.get());

        tag(ExplorerTags.Blocks.LEAVES)
                .add(BambooGroveBlocks.BAMBOO_LEAVES.get())
                .add(BambooGroveBlocks.CHERRY_LEAVES.get())
                .add(BambooGroveBlocks.MAPLE_LEAVES.get())
                .add(CymruBlocks.ASH_LEAVES.get());

        tag(ExplorerTags.Blocks.LOGS)
                .addTag(ExplorerTags.Blocks.BAMBOO_LOGS)
                .addTag(ExplorerTags.Blocks.CHERRY_LOGS)
                .addTag(ExplorerTags.Blocks.MAPLE_LOGS)
                .addTag(ExplorerTags.Blocks.ASH_LOGS);

        tag(ExplorerTags.Blocks.PLANKS)
                .add(BambooGroveBlocks.BAMBOO_PLANKS.get())
                .add(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .add(BambooGroveBlocks.CHERRY_PLANKS.get())
                .add(BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get())
                .add(BambooGroveBlocks.MAPLE_PLANKS.get())
                .add(CymruBlocks.ASH_PLANKS.get());

        tag(ExplorerTags.Blocks.WALLS)
                .add(VanillaTweaksBlocks.MARBLE_WALL.get())
                .add(VanillaTweaksBlocks.MARBLE_BRICK_WALL.get())
                .add(VanillaTweaksBlocks.MARBLE_MOSSY_WALL.get())
                .add(VanillaTweaksBlocks.BASALT_WALL.get())
                .add(VanillaTweaksBlocks.BASALT_BRICK_WALL.get())
                .add(VanillaTweaksBlocks.BASALT_MOSSY_WALL.get())
                .add(VanillaTweaksBlocks.BASALT_COBBLESTONE_WALL.get())
                .add(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY_WALL.get())
                .add(CymruBlocks.SLATE_WALL.get())
                .add(CymruBlocks.SLATE_BRICK_WALL.get())
                .add(CymruBlocks.SLATE_MOSSY_WALL.get());

        tag(ExplorerTags.Blocks.BAMBOO_LOGS)
                .add(BambooGroveBlocks.BAMBOO_LOG.get());

        tag(ExplorerTags.Blocks.CHERRY_LOGS)
                .add(BambooGroveBlocks.CHERRY_LOG.get())
                .add(BambooGroveBlocks.CHERRY_STRIPPED_LOG.get())
                .add(BambooGroveBlocks.CHERRY_WOOD.get())
                .add(BambooGroveBlocks.CHERRY_STRIPPED_WOOD.get());

        tag(ExplorerTags.Blocks.MAPLE_LOGS)
                .add(BambooGroveBlocks.MAPLE_LOG.get())
                .add(BambooGroveBlocks.MAPLE_STRIPPED_LOG.get())
                .add(BambooGroveBlocks.MAPLE_WOOD.get())
                .add(BambooGroveBlocks.MAPLE_STRIPPED_WOOD.get());

        tag(ExplorerTags.Blocks.ASH_LOGS)
                .add(CymruBlocks.ASH_LOG.get())
                .add(CymruBlocks.ASH_STRIPPED_LOG.get())
                .add(CymruBlocks.ASH_WOOD.get())
                .add(CymruBlocks.ASH_STRIPPED_WOOD.get());

    }
}
