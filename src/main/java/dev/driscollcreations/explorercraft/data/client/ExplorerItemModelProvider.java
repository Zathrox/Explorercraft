package dev.driscollcreations.explorercraft.data.client;

import dev.driscollcreations.explorercraft.Explorercraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExplorerItemModelProvider extends ItemModelProvider {

    public ExplorerItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Explorercraft.MOD_ID, existingFileHelper);
    }

    List<String> treeTypes = Arrays.asList("bamboo", "cherry", "maple");
    List<String> oreTypes = Arrays.asList("amethyst", "jade", "ruby");

    @Override
    protected void registerModels() {
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        ModelFile itemHandheld = getExistingFile(mcLoc("item/handheld"));

        withExistingParent("jade_block", modLoc("block/jade_block"));
        withExistingParent("jade_ore", modLoc("block/jade_ore"));
        withExistingParent("ruby_block", modLoc("block/ruby_block"));
        withExistingParent("ruby_ore", modLoc("block/ruby_ore"));
        withExistingParent("amethyst_block", modLoc("block/amethyst_block"));
        withExistingParent("amethyst_ore", modLoc("block/amethyst_ore"));
        withExistingParent("slimey_stone", modLoc("block/slimey_stone"));
        withExistingParent("dissolved_stone", modLoc("block/dissolved_stone"));
        withExistingParent("black_hole", modLoc("block/black_hole"));
        withExistingParent("rice_straw_block", modLoc("block/rice_straw_block"));
        builderMod(itemGenerated, "item/rice");
        builderMod(itemGenerated, "item/rice_straw");
        builderMod(itemGenerated, "item/cherry_blossom");
        builderMod(itemGenerated, "item/rice_bowl");
        builderMod(itemGenerated, "item/onigiri");
        builderMod(itemGenerated, "item/salmon_sushi");
        builderMod(itemGenerated, "item/tamago_sushi");
        builderMod(itemGenerated, "item/leek");
        builderMod(itemGenerated, "item/dried_fruits");
        builderMod(itemGenerated, "item/cheese");
        builderMod(itemGenerated, "item/welsh_cakes");
        builderMod(itemGenerated, "item/welsh_rarebit");
        builderMod(itemGenerated, "item/lamb_shank_raw");
        builderMod(itemGenerated, "item/lamb_shank_cooked");
        builderMod(itemGenerated, "item/leek_bowl");
        builderMod(itemGenerated, "item/cawl_bowl");
        builderMod(itemGenerated, "item/welshflag_banner_pattern");
        builderMod(itemGenerated, "item/wales_banner_pattern");

        //-------- Sleeping bags
        List<String> sleepingBags = Arrays.asList("black", "blue", "brown", "cyan", "gray", "green", "leather", "light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow");
        for (String color : sleepingBags) {
            builderMod(itemGenerated, "item/sleeping_bag_"+color);
        }

        builderMod(itemGenerated, "item/noctiluca");

        for (String ore : oreTypes) {
            builderMod(itemGenerated, "item/"+ore);
            builderMod(itemGenerated, "item/"+ore+"_horse_armor");
            builderMod(itemHandheld, "item/"+ore+"_axe");
            builderMod(itemHandheld, "item/"+ore+"_hoe");
            builderMod(itemHandheld, "item/"+ore+"_pickaxe");
            builderMod(itemHandheld, "item/"+ore+"_shovel");
            builderMod(itemHandheld, "item/"+ore+"_sword");
            builderMod(itemGenerated, "item/"+ore+"_helmet");
            builderMod(itemGenerated, "item/"+ore+"_chestplate");
            builderMod(itemGenerated, "item/"+ore+"_leggings");
            builderMod(itemGenerated, "item/"+ore+"_boots");
        }

        withExistingParent("cherry_blossom_button", modLoc("block/cherry_blossom_button_inventory"));
        builderMod(itemGenerated, "item/cherry_blossom_door");
        fenceInventory("cherry_blossom_fence", modLoc("block/cherry_blossom_planks"));
        fenceGate("cherry_blossom_fence_gate", modLoc("block/cherry_blossom_planks"));
        withExistingParent("cherry_blossom_planks", modLoc("block/cherry_blossom_planks"));
        withExistingParent("cherry_blossom_pressure_plate", modLoc("block/cherry_blossom_pressure_plate"));
        withExistingParent("cherry_blossom_slab", modLoc("block/cherry_blossom_slab"));
        withExistingParent("cherry_blossom_stairs", modLoc("block/cherry_blossom_stairs"));
        withExistingParent("cherry_blossom_trapdoor", modLoc("block/cherry_blossom_trapdoor_bottom"));
        builderMod(itemGenerated, "item/cherry_blossom_sign");

        builderMod(itemGenerated, "item/cherry_panel");
        builderMod(itemGenerated, "item/bamboo_panel");

        for (String tree : treeTypes)
        {
            withExistingParent(tree+"_button", modLoc("block/"+tree+"_button_inventory"));
            builderMod(itemGenerated, "item/"+tree+"_door");
            fenceInventory(tree+"_fence", modLoc("block/"+tree+"_planks"));
            fenceGate(tree+"_fence_gate", modLoc("block/"+tree+"_planks"));
            withExistingParent(tree+"_leaves", modLoc("block/"+tree+"_leaves"));
            withExistingParent(tree+"_planks", modLoc("block/"+tree+"_planks"));
            if(tree.equals("bamboo")) {
                withExistingParent(tree+"_planks_vertical", modLoc("block/"+tree+"_planks_vertical"));
                bambooInventoryBuilder(tree+"_log", modLoc("block/"+tree+"_log"), modLoc("block/"+tree+"_log_top"));
            }
            withExistingParent(tree+"_pressure_plate", modLoc("block/"+tree+"_pressure_plate"));
            builderMod(itemGenerated, tree+"_sapling","block/"+tree+"_sapling");
            builderMc(itemGenerated, "potted_"+tree+"_sapling","item/flower_pot");

            builderMod(itemGenerated, "item/"+tree+"_sign");
            withExistingParent(tree+"_slab", modLoc("block/"+tree+"_slab"));
            withExistingParent(tree+"_stairs", modLoc("block/"+tree+"_stairs"));
            withExistingParent(tree+"_trapdoor", modLoc("block/"+tree+"_trapdoor_bottom"));
            if(!tree.equals("bamboo")) {
                withExistingParent(tree+"_log", modLoc("block/"+tree+"_log"));
                withExistingParent(tree+"_stripped_log", modLoc("block/"+tree+"_stripped_log"));
                withExistingParent(tree+"_wood", modLoc("block/"+tree+"_wood"));
                withExistingParent(tree+"_stripped_wood", modLoc("block/"+tree+"_stripped_wood"));
            }
        }

        //=== MARBLE
        withExistingParent("marble", modLoc("block/marble"));
        withExistingParent("marble_bricks", modLoc("block/marble_bricks"));
        withExistingParent("marble_polished", modLoc("block/marble_polished"));
        withExistingParent("marble_mossy", modLoc("block/marble_mossy"));
        withExistingParent("marble_cracked", modLoc("block/marble_cracked"));
        withExistingParent("marble_chiseled", modLoc("block/marble_chiseled"));
        withExistingParent("marble_pillar", modLoc("block/marble_pillar"));

        withExistingParent("marble_slab", modLoc("block/marble_slab"));
        withExistingParent("marble_brick_slab", modLoc("block/marble_brick_slab"));
        withExistingParent("marble_polished_slab", modLoc("block/marble_polished_slab"));
        withExistingParent("marble_mossy_slab", modLoc("block/marble_mossy_slab"));

        withExistingParent("marble_stairs", modLoc("block/marble_stairs"));
        withExistingParent("marble_brick_stairs", modLoc("block/marble_brick_stairs"));
        withExistingParent("marble_polished_stairs", modLoc("block/marble_polished_stairs"));
        withExistingParent("marble_mossy_stairs", modLoc("block/marble_mossy_stairs"));

        wallInventory("marble_wall", modLoc("block/marble"));
        wallInventory("marble_brick_wall", modLoc("block/marble_bricks"));
        wallInventory("marble_mossy_wall", modLoc("block/marble_mossy"));

        //=== BASALT
        withExistingParent("basalt", modLoc("block/basalt"));
        withExistingParent("basalt_bricks", modLoc("block/basalt_bricks"));
        withExistingParent("basalt_polished", modLoc("block/basalt_polished"));
        withExistingParent("basalt_mossy", modLoc("block/basalt_mossy"));
        withExistingParent("basalt_cracked", modLoc("block/basalt_cracked"));
        withExistingParent("basalt_chiseled", modLoc("block/basalt_chiseled"));
        withExistingParent("basalt_pillar", modLoc("block/basalt_pillar"));
        withExistingParent("basalt_cobblestone", modLoc("block/basalt_cobblestone"));
        withExistingParent("basalt_cobblestone_mossy", modLoc("block/basalt_cobblestone_mossy"));

        withExistingParent("basalt_slab", modLoc("block/basalt_slab"));
        withExistingParent("basalt_brick_slab", modLoc("block/basalt_brick_slab"));
        withExistingParent("basalt_polished_slab", modLoc("block/basalt_polished_slab"));
        withExistingParent("basalt_mossy_slab", modLoc("block/basalt_mossy_slab"));
        withExistingParent("basalt_cobblestone_slab", modLoc("block/basalt_cobblestone_slab"));
        withExistingParent("basalt_cobblestone_mossy_slab", modLoc("block/basalt_cobblestone_mossy_slab"));

        withExistingParent("basalt_stairs", modLoc("block/basalt_stairs"));
        withExistingParent("basalt_brick_stairs", modLoc("block/basalt_brick_stairs"));
        withExistingParent("basalt_polished_stairs", modLoc("block/basalt_polished_stairs"));
        withExistingParent("basalt_mossy_stairs", modLoc("block/basalt_mossy_stairs"));
        withExistingParent("basalt_cobblestone_stairs", modLoc("block/basalt_cobblestone_stairs"));
        withExistingParent("basalt_cobblestone_mossy_stairs", modLoc("block/basalt_cobblestone_mossy_stairs"));

        wallInventory("basalt_wall", modLoc("block/basalt"));
        wallInventory("basalt_brick_wall", modLoc("block/basalt_bricks"));
        wallInventory("basalt_mossy_wall", modLoc("block/basalt_mossy"));
        wallInventory("basalt_cobblestone_wall", modLoc("block/basalt_cobblestone"));
        wallInventory("basalt_cobblestone_mossy_wall", modLoc("block/basalt_cobblestone_mossy"));

        //=== SLATE
        withExistingParent("slate", modLoc("block/slate"));
        withExistingParent("slate_bricks", modLoc("block/slate_bricks"));
        withExistingParent("slate_polished", modLoc("block/slate_polished"));
        withExistingParent("slate_mossy", modLoc("block/slate_mossy"));
        withExistingParent("slate_tile", modLoc("block/slate_tile"));
        withExistingParent("slate_chiseled", modLoc("block/slate_chiseled"));
        withExistingParent("slate_pillar", modLoc("block/slate_pillar"));
        withExistingParent("slate_welsh", modLoc("block/slate_welsh"));

        withExistingParent("slate_slab", modLoc("block/slate_slab"));
        withExistingParent("slate_brick_slab", modLoc("block/slate_brick_slab"));
        withExistingParent("slate_polished_slab", modLoc("block/slate_polished_slab"));
        withExistingParent("slate_mossy_slab", modLoc("block/slate_mossy_slab"));
        withExistingParent("slate_tile_slab", modLoc("block/slate_tile_slab"));

        withExistingParent("slate_stairs", modLoc("block/slate_stairs"));
        withExistingParent("slate_brick_stairs", modLoc("block/slate_brick_stairs"));
        withExistingParent("slate_polished_stairs", modLoc("block/slate_polished_stairs"));
        withExistingParent("slate_mossy_stairs", modLoc("block/slate_mossy_stairs"));
        withExistingParent("slate_tile_stairs", modLoc("block/slate_tile_stairs"));

        wallInventory("slate_wall", modLoc("block/slate"));
        wallInventory("slate_brick_wall", modLoc("block/slate_bricks"));
        wallInventory("slate_mossy_wall", modLoc("block/slate_mossy"));

        //=== CYMRU EXPANSION
        withExistingParent("dragon_heart", modLoc("block/dragon_heart"));
        builderMod(itemGenerated,"daffodil","block/daffodil");
        builderMod(itemGenerated,"leek_wild", "block/leek_wild");
    }

    private ItemModelBuilder builderMod(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", modLoc(name));
    }
    private ItemModelBuilder builderMc(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", mcLoc(name));
    }

    private ItemModelBuilder builderMod(ModelFile itemGenerated, String name, String textureLoc) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", modLoc(textureLoc));
    }
    private ItemModelBuilder builderMc(ModelFile itemGenerated, String name, String textureLoc) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", mcLoc(textureLoc));
    }

    private ItemModelBuilder bambooInventoryBuilder(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, "explorercraft:" + BLOCK_FOLDER + "/bamboo_inventory")
                       .texture("side", side)
                       .texture("end", end);
    }

}
