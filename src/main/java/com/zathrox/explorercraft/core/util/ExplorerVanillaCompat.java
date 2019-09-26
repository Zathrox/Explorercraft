package com.zathrox.explorercraft.core.util;

import com.google.common.collect.Maps;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.item.AxeItem;
import net.minecraft.util.IItemProvider;

public class ExplorerVanillaCompat {

    public static void setup() {

        //Flammability
        registerFlammable(ExplorerBlocks.BAMBOO_LEAVES, 30, 60);
        registerFlammable(ExplorerBlocks.BAMBOO_LOG, 5, 5);
        registerFlammable(ExplorerBlocks.BAMBOO_PLANKS, 5, 20);
        registerFlammable(ExplorerBlocks.BAMBOO_PLANKS_VERTICAL, 5, 20);
        registerFlammable(ExplorerBlocks.BAMBOO_SLAB, 5, 20);
        registerFlammable(ExplorerBlocks.BAMBOO_STAIRS, 5, 20);
        registerFlammable(ExplorerBlocks.BAMBOO_FENCE, 5, 20);
        registerFlammable(ExplorerBlocks.BAMBOO_FENCE_GATE, 5, 20);

        registerFlammable(ExplorerBlocks.ASH_LEAVES, 30, 60);
        registerFlammable(ExplorerBlocks.ASH_LOG, 5, 5);
        registerFlammable(ExplorerBlocks.ASH_WOOD, 5, 5);
        registerFlammable(ExplorerBlocks.ASH_STRIPPED_LOG, 5, 5);
        registerFlammable(ExplorerBlocks.ASH_STRIPPED_WOOD, 5, 5);
        registerFlammable(ExplorerBlocks.ASH_PLANKS, 5, 20);
        registerFlammable(ExplorerBlocks.ASH_SLAB, 5, 20);
        registerFlammable(ExplorerBlocks.ASH_STAIRS, 5, 20);
        registerFlammable(ExplorerBlocks.ASH_FENCE, 5, 20);
        registerFlammable(ExplorerBlocks.ASH_FENCE_GATE, 5, 20);

        registerFlammable(ExplorerBlocks.CHERRY_LEAVES, 30, 60);
        registerFlammable(ExplorerBlocks.CHERRY_LOG, 5, 5);
        registerFlammable(ExplorerBlocks.CHERRY_WOOD, 5, 5);
        registerFlammable(ExplorerBlocks.CHERRY_STRIPPED_LOG, 5, 5);
        registerFlammable(ExplorerBlocks.CHERRY_STRIPPED_WOOD, 5, 5);
        registerFlammable(ExplorerBlocks.CHERRY_PLANKS, 5, 20);
        registerFlammable(ExplorerBlocks.CHERRY_SLAB, 5, 20);
        registerFlammable(ExplorerBlocks.CHERRY_STAIRS, 5, 20);
        registerFlammable(ExplorerBlocks.CHERRY_FENCE, 5, 20);
        registerFlammable(ExplorerBlocks.CHERRY_FENCE_GATE, 5, 20);

        registerFlammable(ExplorerBlocks.MAPLE_LEAVES, 30, 60);
        registerFlammable(ExplorerBlocks.MAPLE_LOG, 5, 5);
        registerFlammable(ExplorerBlocks.MAPLE_WOOD, 5, 5);
        registerFlammable(ExplorerBlocks.MAPLE_STRIPPED_LOG, 5, 5);
        registerFlammable(ExplorerBlocks.MAPLE_STRIPPED_WOOD, 5, 5);
        registerFlammable(ExplorerBlocks.MAPLE_PLANKS, 5, 20);
        registerFlammable(ExplorerBlocks.MAPLE_SLAB, 5, 20);
        registerFlammable(ExplorerBlocks.MAPLE_STAIRS, 5, 20);
        registerFlammable(ExplorerBlocks.MAPLE_FENCE, 5, 20);
        registerFlammable(ExplorerBlocks.MAPLE_FENCE_GATE, 5, 20);

        registerFlammable(ExplorerBlocks.TATAMI, 5, 20);
        registerFlammable(ExplorerBlocks.TATAMI_HALF, 5, 20);

        registerFlammable(ExplorerBlocks.DAFFODIL, 60, 100);
        registerFlammable(ExplorerBlocks.LEEK_WILD, 60, 100);



        //Log Stripping
        registerStrippable(ExplorerBlocks.ASH_LOG, ExplorerBlocks.ASH_STRIPPED_LOG);
        registerStrippable(ExplorerBlocks.ASH_WOOD, ExplorerBlocks.ASH_STRIPPED_WOOD);
        registerStrippable(ExplorerBlocks.CHERRY_LOG, ExplorerBlocks.CHERRY_STRIPPED_LOG);
        registerStrippable(ExplorerBlocks.CHERRY_WOOD, ExplorerBlocks.CHERRY_STRIPPED_WOOD);
        registerStrippable(ExplorerBlocks.MAPLE_LOG, ExplorerBlocks.MAPLE_STRIPPED_LOG);
        registerStrippable(ExplorerBlocks.MAPLE_WOOD, ExplorerBlocks.MAPLE_STRIPPED_WOOD);

        //Compostable Blocks
        registerCompostable(0.3F, ExplorerBlocks.LEEKS);
        registerCompostable(0.3F, ExplorerBlocks.DAFFODIL);
        registerCompostable(0.3F, ExplorerBlocks.ASH_LEAVES);
        registerCompostable(0.3F, ExplorerBlocks.ASH_SAPLING);
        registerCompostable(0.3F, ExplorerBlocks.BAMBOO_LEAVES);
        registerCompostable(0.3F, ExplorerBlocks.BAMBOO_SAPLING);
        registerCompostable(0.3F, ExplorerBlocks.CHERRY_LEAVES);
        registerCompostable(0.3F, ExplorerBlocks.CHERRY_SAPLING);
        registerCompostable(0.3F, ExplorerBlocks.MAPLE_LEAVES);
        registerCompostable(0.3F, ExplorerBlocks.MAPLE_SAPLING);


    }

    public static void registerStrippable(Block LOG, Block stripped_LOG) {
        AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
        AxeItem.BLOCK_STRIPPING_MAP.put(LOG, stripped_LOG);
    }

    public static void registerCompostable(float chance, IItemProvider itemIn) {
        ComposterBlock.CHANCES.put(itemIn.asItem(), chance);
    }

    public static void registerFlammable(Block blockIn, int encouragement, int flammability)
    {
        FireBlock fireblock = (FireBlock)Blocks.FIRE;
        fireblock.setFireInfo(blockIn, encouragement, flammability);
    }

}
