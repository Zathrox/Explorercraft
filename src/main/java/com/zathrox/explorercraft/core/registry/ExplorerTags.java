package com.zathrox.explorercraft.core.registry;

import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class ExplorerTags {

    public static class Items {

        public static final Tag<Item> EXPLORERCRAFT = makeTag(Explorercraft.MOD_ID, "explorercraft");
        public static final Tag<Item> ORES_AMETHYST = makeTag("forge", "ores/amethyst");
        public static final Tag<Item> ORES_JADE = makeTag("forge", "ores/jade");
        public static final Tag<Item> ORES_RUBY = makeTag("forge", "ores/ruby");
        public static final Tag<Item> GEMS_AMETHYST = makeTag("forge", "gems/amethyst");
        public static final Tag<Item> GEMS_JADE = makeTag("forge", "gems/jade");
        public static final Tag<Item> GEMS_RUBY = makeTag("forge", "gems/ruby");
        public static final Tag<Item> STORAGE_BLOCKS_AMETHYST = makeTag("forge", "storage_blocks/amethyst");
        public static final Tag<Item> STORAGE_BLOCKS_JADE = makeTag("forge", "storage_blocks/jade");
        public static final Tag<Item> STORAGE_BLOCKS_RUBY = makeTag("forge", "storage_blocks/ruby");

        private static Tag<Item> makeTag(String modid, String name) {
            return new ItemTags.Wrapper(new ResourceLocation(modid, name));
        }

    }

    public static class Blocks {

        public static final Tag<Block> EXPLORERCRAFT = makeTag(Explorercraft.MOD_ID, "explorercraft");
        public static final Tag<Block> ORES_AMETHYST = makeTag("forge", "ores/amethyst");
        public static final Tag<Block> ORES_JADE = makeTag("forge", "ores/jade");
        public static final Tag<Block> ORES_RUBY = makeTag("forge", "ores/ruby");
        public static final Tag<Block> STORAGE_BLOCKS_AMETHYST = makeTag("forge", "storage_blocks/amethyst");
        public static final Tag<Block> STORAGE_BLOCKS_JADE = makeTag("forge", "storage_blocks/jade");
        public static final Tag<Block> STORAGE_BLOCKS_RUBY = makeTag("forge", "storage_blocks/ruby");

        private static Tag<Block> makeTag(String modid, String name) {
            return new BlockTags.Wrapper(new ResourceLocation(modid, name));
        }

    }
}
