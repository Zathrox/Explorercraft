package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class ExplorerTags {

    public static final class Blocks {
        public static final ITag.INamedTag<Block> ORES_JADE = forge("ores/jade");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_JADE = forge("storage_blocks/jade");
        public static final ITag.INamedTag<Block> FENCES_WOODEN = minecraft("wooden_fences");
        public static final ITag.INamedTag<Block> BUTTONS_WOODEN = minecraft("wooden_buttons");
        public static final ITag.INamedTag<Block> BUTTONS = minecraft("buttons");
        public static final ITag.INamedTag<Block> DOORS_WOODEN = minecraft("wooden_doors");
        public static final ITag.INamedTag<Block> DOORS = minecraft("doors");
        public static final ITag.INamedTag<Block> PRESSURE_PLATES_WOODEN = minecraft("wooden_pressure_plates");
        public static final ITag.INamedTag<Block> SLABS_WOODEN = minecraft("wooden_slabs");
        public static final ITag.INamedTag<Block> STAIRS_WOODEN = minecraft("wooden_stairs");
        public static final ITag.INamedTag<Block> TRAPDOORS_WOODEN = minecraft("wooden_trapdoors");
        public static final ITag.INamedTag<Block> LEAVES = minecraft("leaves");
        public static final ITag.INamedTag<Block> LOGS = minecraft("logs");

        private static ITag.INamedTag<Block> forge(String path) {
            return BlockTags.makeWrapperTag(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Block> minecraft(String path) {
            return BlockTags.makeWrapperTag(new ResourceLocation("minecraft", path).toString());
        }

        private static ITag.INamedTag<Block> explorercraft(String path) {
            return BlockTags.makeWrapperTag(new ResourceLocation(Explorercraft.MOD_ID, path).toString());
        }
    }

    public static final class Items {
        public static final ITag.INamedTag<Item> ORES_JADE = forge("ores/adamantite");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_JADE = forge("storage_blocks/jade");
        public static final ITag.INamedTag<Item> FENCES_WOODEN = minecraft("wooden_fences");
        public static final ITag.INamedTag<Item> BUTTONS_WOODEN = minecraft("wooden_buttons");
        public static final ITag.INamedTag<Item> DOORS_WOODEN = minecraft("wooden_doors");
        public static final ITag.INamedTag<Item> PRESSURE_PLATES_WOODEN = minecraft("wooden_pressure_plates");
        public static final ITag.INamedTag<Item> SLABS_WOODEN = minecraft("wooden_slabs");
        public static final ITag.INamedTag<Item> STAIRS_WOODEN = minecraft("wooden_stairs");
        public static final ITag.INamedTag<Item> TRAPDOORS_WOODEN = minecraft("wooden_trapdoors");
        public static final ITag.INamedTag<Item> LEAVES = minecraft("leaves");
        public static final ITag.INamedTag<Item> LOGS = minecraft("logs");

        public static final ITag.INamedTag<Item> JADE = forge("gems/jade");

        private static ITag.INamedTag<Item> forge(String path) {
            return ItemTags.makeWrapperTag(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Item> minecraft(String path) {
            return ItemTags.makeWrapperTag(new ResourceLocation("minecraft", path).toString());
        }

        private static ITag.INamedTag<Item> explorercraft(String path) {
            return ItemTags.makeWrapperTag(new ResourceLocation(Explorercraft.MOD_ID, path).toString());
        }

    }
}
