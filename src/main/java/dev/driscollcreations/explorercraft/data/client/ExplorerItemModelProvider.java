package dev.driscollcreations.explorercraft.data.client;

import dev.driscollcreations.explorercraft.Explorercraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
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
    List<String> oreTypes = Arrays.asList("jade");

    @Override
    protected void registerModels() {
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        withExistingParent("jade_block", modLoc("block/jade_block"));
        withExistingParent("jade_ore", modLoc("block/jade_ore"));
        withExistingParent("slimey_stone", modLoc("block/slimey_stone"));
        withExistingParent("dissolved_stone", modLoc("block/dissolved_stone"));
        withExistingParent("rice_straw_block", modLoc("block/rice_straw_block"));
        builderMod(itemGenerated, "item/rice");
        builderMod(itemGenerated, "item/rice_straw");
        builderMod(itemGenerated, "item/cherry_blossom");
        builderMod(itemGenerated, "item/rice_bowl");
        builderMod(itemGenerated, "item/onigiri");
        builderMod(itemGenerated, "item/salmon_sushi");
        builderMod(itemGenerated, "item/tamago_sushi");

        //-------- Sleeping bags
        builderMod(itemGenerated, "item/sleeping_bag_black");
        builderMod(itemGenerated, "item/sleeping_bag_blue");
        builderMod(itemGenerated, "item/sleeping_bag_brown");
        builderMod(itemGenerated, "item/sleeping_bag_cyan");
        builderMod(itemGenerated, "item/sleeping_bag_gray");
        builderMod(itemGenerated, "item/sleeping_bag_green");
        builderMod(itemGenerated, "item/sleeping_bag_leather");
        builderMod(itemGenerated, "item/sleeping_bag_light_blue");
        builderMod(itemGenerated, "item/sleeping_bag_light_gray");
        builderMod(itemGenerated, "item/sleeping_bag_green");
        builderMod(itemGenerated, "item/sleeping_bag_lime");
        builderMod(itemGenerated, "item/sleeping_bag_magenta");
        builderMod(itemGenerated, "item/sleeping_bag_orange");
        builderMod(itemGenerated, "item/sleeping_bag_pink");
        builderMod(itemGenerated, "item/sleeping_bag_purple");
        builderMod(itemGenerated, "item/sleeping_bag_red");
        builderMod(itemGenerated, "item/sleeping_bag_white");
        builderMod(itemGenerated, "item/sleeping_bag_yellow");

        builderMod(itemGenerated, "item/noctiluca");

        for (String ore : oreTypes) {
            builderMod(itemGenerated, "item/"+ore);
            builderMod(itemGenerated, "item/"+ore+"_horse_armor");
            builderMod(itemGenerated, "item/"+ore+"_axe");
            builderMod(itemGenerated, "item/"+ore+"_hoe");
            builderMod(itemGenerated, "item/"+ore+"_pickaxe");
            builderMod(itemGenerated, "item/"+ore+"_shovel");
            builderMod(itemGenerated, "item/"+ore+"_sword");
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
