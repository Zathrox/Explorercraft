package dev.driscollcreations.explorercraft.data.client;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class ExplorercraftRecipeProvider extends RecipeProvider {

    public ExplorercraftRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        createBambooGroveJadeRecipes(consumer);
        createBambooGroveFoodRecipes(consumer);
        createBambooGroveMiscRecipes(consumer);
        createBambooGroveWoodRecipes(consumer);
    }

    private static void createBambooGroveMiscRecipes(Consumer<IFinishedRecipe> consumer) {

        ShapelessRecipeBuilder.shapelessRecipe(BambooGroveItems.RICE_STRAW.get(), 9)
                .addIngredient(BambooGroveBlocks.RICE_STRAW_BLOCK.get()).addCriterion("has_item", hasItem(BambooGroveItems.RICE_STRAW.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.RICE_STRAW_BLOCK.get(), 1)
                .key('#', BambooGroveItems.RICE_STRAW.get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveItems.JADE.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.TATAMI.get(), 8)
                .key('X', BambooGroveBlocks.BAMBOO_LOG.get())
                .key('#', BambooGroveItems.RICE_STRAW.get())
                .patternLine("XXX")
                .patternLine("###")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(BambooGroveItems.JADE.get()))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(BambooGroveBlocks.TATAMI_HALF.get(), 2)
                .addIngredient(BambooGroveBlocks.TATAMI.get()).addCriterion("has_item", hasItem(BambooGroveBlocks.TATAMI_HALF.get()))
                .build(consumer);
    }

    private static void createBambooGroveWoodRecipes(Consumer<IFinishedRecipe> consumer) {


        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_PLANKS.get(), 4)
                .key('#', BambooGroveBlocks.BAMBOO_LOG.get())
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.BAMBOO_LOG.get()))
                .setGroup("planks")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get(), 4)
                .key('#', BambooGroveBlocks.BAMBOO_LOG.get())
                .patternLine("##")
                .patternLine("##")
                .patternLine("##")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.BAMBOO_LOG.get()))
                .setGroup("planks")
                .build(consumer);


        ShapelessRecipeBuilder.shapelessRecipe(BambooGroveBlocks.BAMBOO_BUTTON.get(), 1)
                .addIngredient(BambooGroveBlocks.BAMBOO_PLANKS.get()).addCriterion("has_planks", hasItem(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .setGroup("wooden_button")
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(BambooGroveBlocks.BAMBOO_BUTTON.get(), 1)
                .addIngredient(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()).addCriterion("has_planks", hasItem(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .setGroup("wooden_button")
                .build(consumer, Explorercraft.getId("bamboo_button_from_vertical_planks"));

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_DOOR.get(), 1)
                .key('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .patternLine("##")
                .patternLine("##")
                .patternLine("##")
                .addCriterion("has_planks", hasItem(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .setGroup("wooden_door")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_DOOR.get(), 1)
                .key('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .patternLine("##")
                .patternLine("##")
                .patternLine("##")
                .addCriterion("has_planks", hasItem(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .setGroup("wooden_door")
                .build(consumer, Explorercraft.getId("bamboo_door_from_vertical_planks"));

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_FENCE.get(), 1)
                .key('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .key('X', Tags.Items.RODS_WOODEN)
                .patternLine("#X#")
                .patternLine("#X#")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .setGroup("wooden_fence")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_FENCE.get(), 1)
                .key('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .key('X', Tags.Items.RODS_WOODEN)
                .patternLine("#X#")
                .patternLine("#X#")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .setGroup("wooden_fence")
                .build(consumer, Explorercraft.getId("bamboo_fence_from_vertical_planks"));

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_FENCE_GATE.get(), 1)
                .key('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .key('X', Tags.Items.RODS_WOODEN)
                .patternLine("X#X")
                .patternLine("X#X")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .setGroup("wooden_fence_gate")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_FENCE_GATE.get(), 1)
                .key('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .key('X', Tags.Items.RODS_WOODEN)
                .patternLine("X#X")
                .patternLine("X#X")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .setGroup("wooden_fence_gate")
                .build(consumer, Explorercraft.getId("bamboo_fence_gate_from_vertical_planks"));

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_PRESSURE_PLATE.get(), 1)
                .key('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .patternLine("##")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .setGroup("wooden_pressure_plate")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_PRESSURE_PLATE.get(), 1)
                .key('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .patternLine("##")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .setGroup("wooden_pressure_plate")
                .build(consumer, Explorercraft.getId("bamboo_pressure_plate_from_vertical_planks"));

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_SLAB.get(), 6)
                .key('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .setGroup("wooden_slab")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_SLAB.get(), 6)
                .key('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .setGroup("wooden_slab")
                .build(consumer, Explorercraft.getId("bamboo_slab_from_vertical_planks"));

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_STAIRS.get(), 4)
                .key('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .setGroup("wooden_stairs")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_STAIRS.get(), 4)
                .key('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .setGroup("wooden_stairs")
                .build(consumer, Explorercraft.getId("bamboo_stairs_from_vertical_planks"));

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_TRAPDOOR.get(), 2)
                .key('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .setGroup("wooden_trapdoor")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.BAMBOO_TRAPDOOR.get(), 2)
                .key('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .setGroup("wooden_trapdoor")
                .build(consumer, Explorercraft.getId("bamboo_trapdoor_from_vertical_planks"));



        //===== Cherry Blocks
        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.CHERRY_PLANKS.get(), 4)
                .key('#', BambooGroveBlocks.CHERRY_LOG.get())
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.CHERRY_LOG.get()))
                .setGroup("planks")
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(BambooGroveBlocks.CHERRY_BUTTON.get(), 1)
                .addIngredient(BambooGroveBlocks.CHERRY_PLANKS.get()).addCriterion("has_planks", hasItem(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .setGroup("wooden_button")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.CHERRY_DOOR.get(), 1)
                .key('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .patternLine("##")
                .patternLine("##")
                .patternLine("##")
                .addCriterion("has_planks", hasItem(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .setGroup("wooden_door")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.CHERRY_FENCE.get(), 1)
                .key('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .key('X', Tags.Items.RODS_WOODEN)
                .patternLine("#X#")
                .patternLine("#X#")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .setGroup("wooden_fence")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.CHERRY_FENCE_GATE.get(), 1)
                .key('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .key('X', Tags.Items.RODS_WOODEN)
                .patternLine("X#X")
                .patternLine("X#X")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .setGroup("wooden_fence_gate")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.CHERRY_PRESSURE_PLATE.get(), 1)
                .key('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .patternLine("##")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .setGroup("wooden_pressure_plate")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.CHERRY_SLAB.get(), 6)
                .key('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .setGroup("wooden_slab")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.CHERRY_STAIRS.get(), 4)
                .key('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .setGroup("wooden_stairs")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.CHERRY_TRAPDOOR.get(), 2)
                .key('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .setGroup("wooden_trapdoor")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.CHERRY_WOOD.get(), 3)
                .key('#', BambooGroveBlocks.CHERRY_LOG.get())
                .patternLine("##")
                .patternLine("##")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.CHERRY_LOG.get()))
                .setGroup("bark")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.CHERRY_STRIPPED_WOOD.get(), 3)
                .key('#', BambooGroveBlocks.CHERRY_STRIPPED_LOG.get())
                .patternLine("##")
                .patternLine("##")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.CHERRY_LOG.get()))
                .setGroup("bark")
                .build(consumer);


        //===== Maple
        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.MAPLE_PLANKS.get(), 4)
                .key('#', BambooGroveBlocks.MAPLE_LOG.get())
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.MAPLE_LOG.get()))
                .setGroup("planks")
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(BambooGroveBlocks.MAPLE_BUTTON.get(), 1)
                .addIngredient(BambooGroveBlocks.MAPLE_PLANKS.get()).addCriterion("has_planks", hasItem(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .setGroup("wooden_button")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.MAPLE_DOOR.get(), 1)
                .key('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .patternLine("##")
                .patternLine("##")
                .patternLine("##")
                .addCriterion("has_planks", hasItem(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .setGroup("wooden_door")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.MAPLE_FENCE.get(), 1)
                .key('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .key('X', Tags.Items.RODS_WOODEN)
                .patternLine("#X#")
                .patternLine("#X#")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .setGroup("wooden_fence")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.MAPLE_FENCE_GATE.get(), 1)
                .key('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .key('X', Tags.Items.RODS_WOODEN)
                .patternLine("X#X")
                .patternLine("X#X")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .setGroup("wooden_fence_gate")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.MAPLE_PRESSURE_PLATE.get(), 1)
                .key('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .patternLine("##")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .setGroup("wooden_pressure_plate")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.MAPLE_SLAB.get(), 6)
                .key('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .setGroup("wooden_slab")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.MAPLE_STAIRS.get(), 4)
                .key('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .setGroup("wooden_stairs")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.MAPLE_TRAPDOOR.get(), 2)
                .key('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .setGroup("wooden_trapdoor")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.MAPLE_WOOD.get(), 3)
                .key('#', BambooGroveBlocks.MAPLE_LOG.get())
                .patternLine("##")
                .patternLine("##")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.MAPLE_LOG.get()))
                .setGroup("bark")
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.MAPLE_STRIPPED_WOOD.get(), 3)
                .key('#', BambooGroveBlocks.MAPLE_STRIPPED_LOG.get())
                .patternLine("##")
                .patternLine("##")
                .addCriterion("has_item", hasItem(BambooGroveBlocks.MAPLE_LOG.get()))
                .setGroup("bark")
                .build(consumer);
    }


    private static void createBambooGroveJadeRecipes(Consumer<IFinishedRecipe> consumer) {

        Item jade = BambooGroveItems.JADE.get();
        createArmorForMaterial(consumer, jade);
        createToolsForMaterial(consumer, jade);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveItems.JADE_BOW.get(), 1)
                .key('J', jade)
                .key('|', Items.STRING)
                .key('S', Tags.Items.RODS_WOODEN)
                .patternLine("JS|")
                .patternLine("S |")
                .patternLine("JS|")
                .addCriterion("has_item", hasItem(jade))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(jade, 9)
                .addIngredient(BambooGroveBlocks.JADE_BLOCK.get()).addCriterion("has_item", hasItem(jade))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveBlocks.JADE_BLOCK.get(), 1)
                .key('#', jade)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(jade))
                .build(consumer);

        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BambooGroveBlocks.JADE_ORE.get().asItem()), jade, 1.0F, 200)
                .addCriterion("has_item", hasItem(jade)).build(consumer, Explorercraft.getId("jade_from_smelting"));

        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BambooGroveBlocks.JADE_ORE.get().asItem()), jade, 1.0F, 200)
                .addCriterion("has_item", hasItem(jade)).build(consumer, Explorercraft.getId("jade_from_blasting"));
    }

    private static void createBambooGroveFoodRecipes(Consumer<IFinishedRecipe> consumer) {

        Item rice = BambooGroveItems.RICE.get();
        ShapelessRecipeBuilder.shapelessRecipe(BambooGroveItems.SALMON_SUSHI.get(), 1)
                .addIngredient(rice)
                .addIngredient(Items.COOKED_SALMON)
                .setGroup("sushi")
                .addCriterion("has_item", hasItem(BambooGroveItems.SALMON_SUSHI.get()))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(BambooGroveItems.SALMON_SUSHI.get(), 1)
                .addIngredient(rice)
                .addIngredient(Items.SALMON)
                .setGroup("sushi")
                .addCriterion("has_item", hasItem(BambooGroveItems.SALMON_SUSHI.get()))
                .build(consumer,  Explorercraft.getId("raw_salmon_sushi"));

        ShapelessRecipeBuilder.shapelessRecipe(BambooGroveItems.ONIGIRI.get(), 2)
                .addIngredient(rice)
                .addIngredient(Items.DRIED_KELP)
                .addCriterion("has_item", hasItem(BambooGroveItems.ONIGIRI.get()))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(BambooGroveItems.TAMAGO_SUSHI.get(), 1)
                .addIngredient(rice)
                .addIngredient(Items.EGG)
                .addIngredient(Items.DRIED_KELP)
                .addCriterion("has_item", hasItem(BambooGroveItems.TAMAGO_SUSHI.get()))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(BambooGroveItems.RICE_STEW.get(), 1)
                .addIngredient(Items.BOWL)
                .addIngredient(rice)
                .addIngredient(rice)
                .addIngredient(rice)
                .addIngredient(rice)
                .addIngredient(rice)
                .addIngredient(rice)
                .addCriterion("has_item", hasItem(BambooGroveItems.RICE_STEW.get()))
                .build(consumer);
    }

    private static void createToolsForMaterial(Consumer<IFinishedRecipe> consumer, Item material) {
        Tags.IOptionalNamedTag<Item> stick = Tags.Items.RODS_WOODEN;
        ShapedRecipeBuilder.shapedRecipe(BambooGroveItems.JADE_AXE.get(), 1)
                .key('#', material)
                .key('|', stick)
                .patternLine("##")
                .patternLine("#|")
                .patternLine(" |")
                .addCriterion("has_item", hasItem(material))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveItems.JADE_HOE.get(), 1)
                .key('#', material)
                .key('|', stick)
                .patternLine("##")
                .patternLine(" |")
                .patternLine(" |")
                .addCriterion("has_item", hasItem(material))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveItems.JADE_PICKAXE.get(), 1)
                .key('#', material)
                .key('|', stick)
                .patternLine("###")
                .patternLine(" | ")
                .patternLine(" | ")
                .addCriterion("has_item", hasItem(material))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BambooGroveItems.JADE_SHOVEL.get(), 1)
                .key('#', material)
                .key('|', stick)
                .patternLine("#")
                .patternLine("|")
                .patternLine("|")
                .addCriterion("has_item", hasItem(material))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BambooGroveItems.JADE_SWORD.get(), 1)
                .key('#', material)
                .key('|', stick)
                .patternLine("#")
                .patternLine("#")
                .patternLine("|")
                .addCriterion("has_item", hasItem(material))
                .build(consumer);
    }
    private static void createArmorForMaterial(Consumer<IFinishedRecipe> consumer, Item material) {
        ShapedRecipeBuilder.shapedRecipe(BambooGroveItems.JADE_HELMET.get(), 1)
                .key('#', material)
                .patternLine("###")
                .patternLine("# #")
                .addCriterion("has_item", hasItem(material))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BambooGroveItems.JADE_CHESTPLATE.get(), 1)
                .key('#', material)
                .patternLine("# #")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(material))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BambooGroveItems.JADE_LEGGINGS.get(), 1)
                .key('#', material)
                .patternLine("###")
                .patternLine("# #")
                .patternLine("# #")
                .addCriterion("has_item", hasItem(material))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BambooGroveItems.JADE_BOOTS.get(), 1)
                .key('#', material)
                .patternLine("# #")
                .patternLine("# #")
                .addCriterion("has_item", hasItem(material))
                .build(consumer);
    }
}
