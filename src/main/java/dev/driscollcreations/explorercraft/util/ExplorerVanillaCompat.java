package dev.driscollcreations.explorercraft.util;

import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import dev.driscollcreations.explorercraft.cymru.blocks.CymruBlocks;
import dev.driscollcreations.explorercraft.cymru.items.CymruItems;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;

public class ExplorerVanillaCompat {


    public static void register() {

        //Flammability
        registerFlammable(BambooGroveBlocks.BAMBOO_LEAVES.get(), 30, 60);
        registerFlammable(BambooGroveBlocks.BAMBOO_LOG.get(), 5, 5);
        registerFlammable(BambooGroveBlocks.BAMBOO_PLANKS.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.BAMBOO_SLAB.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.BAMBOO_STAIRS.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.BAMBOO_FENCE.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.BAMBOO_FENCE_GATE.get(), 5, 20);


        registerFlammable(CymruBlocks.ASH_LEAVES.get(), 30, 60);
        registerFlammable(CymruBlocks.ASH_LOG.get(), 5, 5);
        registerFlammable(CymruBlocks.ASH_WOOD.get(), 5, 5);
        registerFlammable(CymruBlocks.ASH_STRIPPED_LOG.get(), 5, 5);
        registerFlammable(CymruBlocks.ASH_STRIPPED_WOOD.get(), 5, 5);
        registerFlammable(CymruBlocks.ASH_PLANKS.get(), 5, 20);
        registerFlammable(CymruBlocks.ASH_SLAB.get(), 5, 20);
        registerFlammable(CymruBlocks.ASH_STAIRS.get(), 5, 20);
        registerFlammable(CymruBlocks.ASH_FENCE.get(), 5, 20);
        registerFlammable(CymruBlocks.ASH_FENCE_GATE.get(), 5, 20);

        registerFlammable(BambooGroveBlocks.CHERRY_LEAVES.get(), 30, 60);
        registerFlammable(BambooGroveBlocks.CHERRY_LOG.get(), 5, 5);
        registerFlammable(BambooGroveBlocks.CHERRY_WOOD.get(), 5, 5);
        registerFlammable(BambooGroveBlocks.CHERRY_STRIPPED_LOG.get(), 5, 5);
        registerFlammable(BambooGroveBlocks.CHERRY_STRIPPED_WOOD.get(), 5, 5);
        registerFlammable(BambooGroveBlocks.CHERRY_PLANKS.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.CHERRY_SLAB.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.CHERRY_STAIRS.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.CHERRY_FENCE.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.CHERRY_FENCE_GATE.get(), 5, 20);


        registerFlammable(BambooGroveBlocks.MAPLE_LEAVES.get(), 30, 60);
        registerFlammable(BambooGroveBlocks.MAPLE_LOG.get(), 5, 5);
        registerFlammable(BambooGroveBlocks.MAPLE_WOOD.get(), 5, 5);
        registerFlammable(BambooGroveBlocks.MAPLE_STRIPPED_LOG.get(), 5, 5);
        registerFlammable(BambooGroveBlocks.MAPLE_STRIPPED_WOOD.get(), 5, 5);
        registerFlammable(BambooGroveBlocks.MAPLE_PLANKS.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.MAPLE_SLAB.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.MAPLE_STAIRS.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.MAPLE_FENCE.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.MAPLE_FENCE_GATE.get(), 5, 20);

        registerFlammable(BambooGroveBlocks.TATAMI.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.TATAMI_HALF.get(), 5, 20);
        registerFlammable(BambooGroveBlocks.RICE_STRAW_BLOCK.get(), 60, 100);

        /*registerFlammable(ExplorerBlocks.WILLOW_LEAVES, 30, 60);
        registerFlammable(ExplorerBlocks.WILLOW_LOG, 5, 5);
        registerFlammable(ExplorerBlocks.WILLOW_WOOD, 5, 5);
        registerFlammable(ExplorerBlocks.WILLOW_STRIPPED_LOG, 5, 5);
        registerFlammable(ExplorerBlocks.WILLOW_STRIPPED_WOOD, 5, 5);
        registerFlammable(ExplorerBlocks.WILLOW_PLANKS, 5, 20);
        registerFlammable(ExplorerBlocks.WILLOW_SLAB, 5, 20);
        registerFlammable(ExplorerBlocks.WILLOW_STAIRS, 5, 20);
        registerFlammable(ExplorerBlocks.WILLOW_FENCE, 5, 20);
        registerFlammable(ExplorerBlocks.WILLOW_FENCE_GATE, 5, 20);

        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_LOG, 5, 5);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_WOOD, 5, 5);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_STRIPPED_LOG, 5, 5);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_STRIPPED_WOOD, 5, 5);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_PLANKS, 5, 20);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_SLAB, 5, 20);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_STAIRS, 5, 20);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_FENCE, 5, 20);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_FENCE_GATE, 5, 20);

        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_PINK_LOG, 5, 5);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_PINK_WOOD, 5, 5);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_PINK_STRIPPED_LOG, 5, 5);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_PINK_STRIPPED_WOOD, 5, 5);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_PINK_PLANKS, 5, 20);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_PINK_SLAB, 5, 20);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_PINK_STAIRS, 5, 20);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_PINK_FENCE, 5, 20);
        registerFlammable(ExplorerBlocks.INFECTED_WILLOW_PINK_FENCE_GATE, 5, 20);*/


        registerFlammable(CymruBlocks.DAFFODIL.get(), 60, 100);
        registerFlammable(CymruBlocks.LEEK_WILD.get(), 60, 100);
        /*registerFlammable(ExplorerBlocks.LUPINE.get(), 60, 100);
        registerFlammable(ExplorerBlocks.CATTAIL.get(), 60, 100);
        registerFlammable(ExplorerBlocks.TALL_CATTAIL.get(), 60, 100);*/




        //Log Stripping
        registerStrippable(CymruBlocks.ASH_LOG.get(), CymruBlocks.ASH_STRIPPED_LOG.get());
        registerStrippable(CymruBlocks.ASH_WOOD.get(), CymruBlocks.ASH_STRIPPED_WOOD.get());
        registerStrippable(BambooGroveBlocks.CHERRY_LOG.get(), BambooGroveBlocks.CHERRY_STRIPPED_LOG.get());
        registerStrippable(BambooGroveBlocks.CHERRY_WOOD.get(), BambooGroveBlocks.CHERRY_STRIPPED_WOOD.get());
        registerStrippable(BambooGroveBlocks.MAPLE_LOG.get(), BambooGroveBlocks.MAPLE_STRIPPED_LOG.get());
        registerStrippable(BambooGroveBlocks.MAPLE_WOOD.get(), BambooGroveBlocks.MAPLE_STRIPPED_WOOD.get());
/*        registerStrippable(ExplorerBlocks.WILLOW_LOG, ExplorerBlocks.WILLOW_STRIPPED_LOG);
        registerStrippable(ExplorerBlocks.WILLOW_WOOD, ExplorerBlocks.WILLOW_STRIPPED_WOOD);
        registerStrippable(ExplorerBlocks.INFECTED_WILLOW_LOG, ExplorerBlocks.INFECTED_WILLOW_STRIPPED_LOG);
        registerStrippable(ExplorerBlocks.INFECTED_WILLOW_WOOD, ExplorerBlocks.INFECTED_WILLOW_STRIPPED_WOOD);
        registerStrippable(ExplorerBlocks.INFECTED_WILLOW_PINK_LOG, ExplorerBlocks.INFECTED_WILLOW_PINK_STRIPPED_LOG);
        registerStrippable(ExplorerBlocks.INFECTED_WILLOW_PINK_WOOD, ExplorerBlocks.INFECTED_WILLOW_PINK_STRIPPED_WOOD);*/

        //Compostable Blocks


        registerCompostable(0.3F, BambooGroveBlocks.BAMBOO_LEAVES.get());
        registerCompostable(0.3F, BambooGroveBlocks.CHERRY_LEAVES.get());
        registerCompostable(0.3F, BambooGroveBlocks.MAPLE_LEAVES.get());
        registerCompostable(0.85F, BambooGroveBlocks.RICE_STRAW_BLOCK.get());
        registerCompostable(0.65F, BambooGroveItems.RICE_STRAW.get());
        registerCompostable(0.65F, BambooGroveItems.RICE_STEW.get());
        registerCompostable(0.3F, BambooGroveItems.RICE.get());

        registerCompostable(0.3F, CymruBlocks.LEEKS.get());
        registerCompostable(0.3F, CymruBlocks.DAFFODIL.get());
        registerCompostable(0.3F, CymruBlocks.ASH_LEAVES.get());

        /*registerCompostable(0.3F, ExplorerBlocks.LUPINE);
        registerCompostable(0.3F, ExplorerBlocks.WILLOW_LEAVES);
        registerCompostable(0.65F, ExplorerBlocks.GREEN_MUSHROOM);
        registerCompostable(0.65F, ExplorerBlocks.PINK_MUSHROOM);
        registerCompostable(0.65F, ExplorerBlocks.TALL_CATTAIL);
        registerCompostable(0.65F, ExplorerBlocks.WILLOW);
        registerCompostable(0.65F, ExplorerBlocks.CATTAIL);*/
        registerCompostable(0.65F, CymruItems.LEEK_STEW.get());
        registerCompostable(0.65F, CymruItems.CAWL_STEW.get());
        registerCompostable(0.65F, CymruItems.WELSH_CAKES.get());
        registerCompostable(0.65F, CymruItems.WELSH_RAREBIT.get());
        registerCompostable(0.65F, VanillaTweaksBlocks.NOCTILUCAS.get());
        registerCompostable(0.65F, CymruItems.CHEESE.get());
        registerCompostable(0.65F, CymruItems.DRIED_FRUIT.get());


    }

    public static void registerStrippable(Block LOG, Block stripped_LOG) {
//        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
//        AxeItem.STRIPPABLES.put(LOG, stripped_LOG);
    }

    public static void registerCompostable(float chance, ItemLike itemIn) {
        ComposterBlock.COMPOSTABLES.put(itemIn.asItem(), chance);
    }

    public static void registerFlammable(Block blockIn, int encouragement, int flammability) {
        //FireBlock fireblock = (FireBlock) Blocks.FIRE;
        //fireblock.setFlammable(blockIn, encouragement, flammability);
    }

}
