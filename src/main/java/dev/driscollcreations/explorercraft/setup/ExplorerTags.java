package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ExplorerTags {

    public static final class Blocks {
        public static final Tag.Named<Block> ORES_JADE = forge("ores/jade");
        public static final Tag.Named<Block> ORES_AMETHYST = forge("ores/amethyst");
        public static final Tag.Named<Block> ORES_RUBY = forge("ores/ruby");
        public static final Tag.Named<Block> STORAGE_BLOCKS_JADE = forge("storage_blocks/jade");
        public static final Tag.Named<Block> STORAGE_BLOCKS_AMETHYST = forge("storage_blocks/amethyst");
        public static final Tag.Named<Block> STORAGE_BLOCKS_RUBY = forge("storage_blocks/ruby");
        public static final Tag.Named<Block> FENCES_WOODEN = minecraft("wooden_fences");
        public static final Tag.Named<Block> BUTTONS_WOODEN = minecraft("wooden_buttons");
        public static final Tag.Named<Block> BUTTONS = minecraft("buttons");
        public static final Tag.Named<Block> DOORS_WOODEN = minecraft("wooden_doors");
        public static final Tag.Named<Block> DOORS = minecraft("doors");
        public static final Tag.Named<Block> PRESSURE_PLATES_WOODEN = minecraft("wooden_pressure_plates");
        public static final Tag.Named<Block> SLABS_WOODEN = minecraft("wooden_slabs");
        public static final Tag.Named<Block> STAIRS_WOODEN = minecraft("wooden_stairs");
        public static final Tag.Named<Block> TRAPDOORS_WOODEN = minecraft("wooden_trapdoors");
        public static final Tag.Named<Block> LEAVES = minecraft("leaves");
        public static final Tag.Named<Block> LOGS = minecraft("logs");
        public static final Tag.Named<Block> PLANKS = minecraft("planks");
        public static final Tag.Named<Block> WALLS = minecraft("walls");
        public static final Tag.Named<Block> BASE_STONE_OVERWORLD = minecraft("base_stone_overworld");
        public static final Tag.Named<Block> BAMBOO_LOGS = explorercraft("bamboo_logs");
        public static final Tag.Named<Block> CHERRY_LOGS = explorercraft("cherry_logs");
        public static final Tag.Named<Block> MAPLE_LOGS = explorercraft("maple_logs");
        public static final Tag.Named<Block> ASH_LOGS = explorercraft("ash_logs");

        private static Tag.Named<Block> forge(String path) {
            return BlockTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static Tag.Named<Block> minecraft(String path) {
            return BlockTags.bind(new ResourceLocation("minecraft", path).toString());
        }

        private static Tag.Named<Block> explorercraft(String path) {
            return BlockTags.bind(new ResourceLocation(Explorercraft.MOD_ID, path).toString());
        }
    }

    public static final class Items {
        public static final Tag.Named<Item> ORES_JADE = forge("ores/jade");
        public static final Tag.Named<Item> STORAGE_BLOCKS_JADE = forge("storage_blocks/jade");
        public static final Tag.Named<Item> ORES_AMETHYST = forge("ores/amethyst");
        public static final Tag.Named<Item> STORAGE_BLOCKS_AMETHYST = forge("storage_blocks/amethyst");
        public static final Tag.Named<Item> ORES_RUBY = forge("ores/ruby");
        public static final Tag.Named<Item> STORAGE_BLOCKS_RUBY = forge("storage_blocks/ruby");
        public static final Tag.Named<Item> FENCES_WOODEN = minecraft("wooden_fences");
        public static final Tag.Named<Item> BUTTONS_WOODEN = minecraft("wooden_buttons");
        public static final Tag.Named<Item> DOORS_WOODEN = minecraft("wooden_doors");
        public static final Tag.Named<Item> PRESSURE_PLATES_WOODEN = minecraft("wooden_pressure_plates");
        public static final Tag.Named<Item> SLABS_WOODEN = minecraft("wooden_slabs");
        public static final Tag.Named<Item> STAIRS_WOODEN = minecraft("wooden_stairs");
        public static final Tag.Named<Item> TRAPDOORS_WOODEN = minecraft("wooden_trapdoors");
        public static final Tag.Named<Item> LEAVES = minecraft("leaves");
        public static final Tag.Named<Item> LOGS = minecraft("logs");
        public static final Tag.Named<Item> PLANKS = minecraft("planks");
        public static final Tag.Named<Item> WALLS = minecraft("walls");
        public static final Tag.Named<Item> BAMBOO_LOGS = explorercraft("bamboo_logs");
        public static final Tag.Named<Item> CHERRY_LOGS = explorercraft("cherry_logs");
        public static final Tag.Named<Item> MAPLE_LOGS = explorercraft("maple_logs");
        public static final Tag.Named<Item> ASH_LOGS = explorercraft("ash_logs");

        public static final Tag.Named<Item> JADE = forge("gems/jade");
        public static final Tag.Named<Item> AMETHYST = forge("gems/amethyst");
        public static final Tag.Named<Item> RUBY = forge("gems/ruby");

        private static Tag.Named<Item> forge(String path) {
            return ItemTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static Tag.Named<Item> minecraft(String path) {
            return ItemTags.bind(new ResourceLocation("minecraft", path).toString());
        }

        private static Tag.Named<Item> explorercraft(String path) {
            return ItemTags.bind(new ResourceLocation(Explorercraft.MOD_ID, path).toString());
        }

    }
}
