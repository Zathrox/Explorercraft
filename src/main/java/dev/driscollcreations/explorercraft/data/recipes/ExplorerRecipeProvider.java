package dev.driscollcreations.explorercraft.data.recipes;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.item.crafting.StonecuttingRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class ExplorerRecipeProvider extends RecipeProvider {

    public ExplorerRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {

        createBambooGroveJadeRecipes(consumer);
        createBambooGroveFoodRecipes(consumer);
        createBambooGroveMiscRecipes(consumer);
        createBambooGroveWoodRecipes(consumer);
        createVanillaTweakRecipes(consumer);
        createVanillaTweakMarbleRecipes(consumer);
    }

    private static void createVanillaTweakRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Items.SADDLE, 1)
                .pattern("LLL")
                .pattern("SLS")
                .pattern("I I")
                .define('L', Items.LEATHER)
                .define('S', Items.STRING)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_leather", has(Items.LEATHER))
                .unlockedBy("has_saddle", has(Items.SADDLE))
                .save(consumer, new ResourceLocation(Explorercraft.MOD_ID, "saddle"));

        ShapedRecipeBuilder.shaped(Items.TRIDENT, 1)
                .pattern(" SS")
                .pattern(" HS")
                .pattern("C  ")
                .group("combat")
                .define('S', Items.PRISMARINE_SHARD)
                .define('C', Items.PRISMARINE_CRYSTALS)
                .define('H', Items.HEART_OF_THE_SEA)
                .unlockedBy("has_heart_of_sea", has(Items.HEART_OF_THE_SEA))
                .save(consumer, new ResourceLocation(Explorercraft.MOD_ID, "trident"));
    }

    private static void createVanillaTweakMarbleRecipes(Consumer<IFinishedRecipe> consumer) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_SLAB.get(), 2).unlocks("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_slab_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_STAIRS.get()).unlocks("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_stairs_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_WALL.get()).unlocks("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_walls_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_PILLAR.get()).unlocks("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_pillar_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_CHISELED.get()).unlocks("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "chiseled_marble_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_BRICKS.get()).unlocks("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_bricks_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_BRICK_SLAB.get(), 2).unlocks("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_brick_slab_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_BRICK_STAIRS.get()).unlocks("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_brick_stairs_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_BRICK_WALL.get()).unlocks("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_brick_wall_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_POLISHED.get()).unlocks("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_polished_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_POLISHED_SLAB.get(), 2).unlocks("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_polished_slab_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_POLISHED_STAIRS.get()).unlocks("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_polished_stairs_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_BRICKS.get()), VanillaTweaksBlocks.MARBLE_BRICK_SLAB.get(), 2).unlocks("has_marble_bricks", has(VanillaTweaksBlocks.MARBLE_BRICKS.get())).save(consumer, "marble_brick_slab_from_marble_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_BRICKS.get()), VanillaTweaksBlocks.MARBLE_BRICK_STAIRS.get()).unlocks("has_marble_bricks", has(VanillaTweaksBlocks.MARBLE_BRICKS.get())).save(consumer, "marble_brick_stairs_from_marble_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_BRICKS.get()), VanillaTweaksBlocks.MARBLE_BRICK_WALL.get()).unlocks("has_marble_bricks", has(VanillaTweaksBlocks.MARBLE_BRICKS.get())).save(consumer, "marble_brick_wall_from_marble_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_BRICKS.get()), VanillaTweaksBlocks.MARBLE_CHISELED.get()).unlocks("has_marble_bricks", has(VanillaTweaksBlocks.MARBLE_BRICKS.get())).save(consumer, "chiseled_marble_from_marble_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_POLISHED.get()), VanillaTweaksBlocks.MARBLE_POLISHED_SLAB.get(), 2).unlocks("has_marble_polished", has(VanillaTweaksBlocks.MARBLE_POLISHED.get())).save(consumer, "marble_polished_slab_from_marble_polished_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_POLISHED.get()), VanillaTweaksBlocks.MARBLE_POLISHED_STAIRS.get()).unlocks("has_marble_polished", has(VanillaTweaksBlocks.MARBLE_POLISHED.get())).save(consumer, "marble_polished_stairs_from_marble_polished_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_POLISHED.get()), VanillaTweaksBlocks.MARBLE_PILLAR.get()).unlocks("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_pillar_from_polished_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_MOSSY.get()), VanillaTweaksBlocks.MARBLE_MOSSY_SLAB.get(), 2).unlocks("has_marble_mossy", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_mossy_slab_from_marble_mossy_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_MOSSY.get()), VanillaTweaksBlocks.MARBLE_MOSSY_STAIRS.get()).unlocks("has_marble_mossy", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_mossy_stairs_from_marble_mossy_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_MOSSY.get()), VanillaTweaksBlocks.MARBLE_MOSSY_WALL.get()).unlocks("has_marble_mossy", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_mossy_wall_from_marble_mossy_stonecutting");
        CookingRecipeBuilder.smelting(Ingredient.of(VanillaTweaksBlocks.MARBLE_BRICKS.get()),  VanillaTweaksBlocks.MARBLE_CRACKED.get().asItem(), 0.1F, 200).unlockedBy("has_marble_bricks", has(VanillaTweaksBlocks.MARBLE_BRICKS.get())).save(consumer);

        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_SLAB.get(), 6).define('#', VanillaTweaksBlocks.MARBLE.get()).pattern("###").unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_STAIRS.get(), 4).define('#', VanillaTweaksBlocks.MARBLE.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_WALL.get(), 6).define('#', VanillaTweaksBlocks.MARBLE.get()).pattern("###").pattern("###").unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_CHISELED.get()).define('#', VanillaTweaksBlocks.MARBLE_BRICK_SLAB.get()).pattern("#").pattern("#").unlockedBy("has_marble_bricks", has(VanillaTweaksBlocks.MARBLE_BRICKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_PILLAR.get(), 2).define('#', VanillaTweaksBlocks.MARBLE_POLISHED.get()).pattern("#").pattern("#").unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).unlockedBy("has_marble_polished", has(VanillaTweaksBlocks.MARBLE_POLISHED.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_BRICKS.get(), 4).define('#', VanillaTweaksBlocks.MARBLE.get()).pattern("##").pattern("##").unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_BRICK_SLAB.get(), 6).define('#', VanillaTweaksBlocks.MARBLE_BRICKS.get()).pattern("###").unlockedBy("has_marble_bricks", has(VanillaTweaksBlocks.MARBLE_BRICKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_BRICK_STAIRS.get(), 4).define('#', VanillaTweaksBlocks.MARBLE_BRICKS.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_marble_bricks", has(VanillaTweaksBlocks.MARBLE_BRICKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_BRICK_WALL.get(), 6).define('#', VanillaTweaksBlocks.MARBLE_BRICKS.get()).pattern("###").pattern("###").unlockedBy("has_marble_bricks", has(VanillaTweaksBlocks.MARBLE_BRICKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_POLISHED.get(), 4).define('#', VanillaTweaksBlocks.MARBLE.get()).pattern("##").pattern("##").unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_POLISHED_SLAB.get(), 6).define('#', VanillaTweaksBlocks.MARBLE_POLISHED.get()).pattern("###").unlockedBy("has_marble_polished", has(VanillaTweaksBlocks.MARBLE_POLISHED.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_POLISHED_STAIRS.get(), 4).define('#', VanillaTweaksBlocks.MARBLE_POLISHED.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_marble_polished", has(VanillaTweaksBlocks.MARBLE_POLISHED.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(VanillaTweaksBlocks.MARBLE_MOSSY.get()).requires(VanillaTweaksBlocks.MARBLE.get()).requires(Blocks.VINE).unlockedBy("has_vine", has(Blocks.VINE)).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_MOSSY_SLAB.get(), 6).define('#', VanillaTweaksBlocks.MARBLE_MOSSY.get()).pattern("###").unlockedBy("has_marble_mossy", has(VanillaTweaksBlocks.MARBLE_MOSSY.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_MOSSY_STAIRS.get(), 4).define('#', VanillaTweaksBlocks.MARBLE_MOSSY.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_marble_mossy", has(VanillaTweaksBlocks.MARBLE_MOSSY.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.MARBLE_MOSSY_WALL.get(), 6).define('#', VanillaTweaksBlocks.MARBLE_MOSSY.get()).pattern("###").pattern("###").unlockedBy("has_marble_mossy", has(VanillaTweaksBlocks.MARBLE_MOSSY.get())).save(consumer);
    }

    private static void createBambooGroveMiscRecipes(Consumer<IFinishedRecipe> consumer) {

        ShapelessRecipeBuilder.shapeless(BambooGroveItems.RICE_STRAW.get(), 9)
                .requires(BambooGroveBlocks.RICE_STRAW_BLOCK.get()).unlockedBy("has_item", has(BambooGroveItems.RICE_STRAW.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.RICE_STRAW_BLOCK.get(), 1)
                .define('#', BambooGroveItems.RICE_STRAW.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveItems.JADE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.TATAMI.get(), 8)
                .define('X', BambooGroveBlocks.BAMBOO_LOG.get())
                .define('#', BambooGroveItems.RICE_STRAW.get())
                .pattern("XXX")
                .pattern("###")
                .pattern("XXX")
                .unlockedBy("has_item", has(BambooGroveItems.JADE.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(BambooGroveBlocks.TATAMI_HALF.get(), 2)
                .requires(BambooGroveBlocks.TATAMI.get()).unlockedBy("has_item", has(BambooGroveBlocks.TATAMI_HALF.get()))
                .save(consumer);
    }

    private static void createBambooGroveWoodRecipes(Consumer<IFinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_PLANKS.get(), 4)
                .define('#', BambooGroveBlocks.BAMBOO_LOG.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_LOG.get()))
                .group("planks")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get(), 4)
                .define('#', BambooGroveBlocks.BAMBOO_LOG.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_LOG.get()))
                .group("planks")
                .save(consumer);


        ShapelessRecipeBuilder.shapeless(BambooGroveBlocks.BAMBOO_BUTTON.get(), 1)
                .requires(BambooGroveBlocks.BAMBOO_PLANKS.get()).unlockedBy("has_planks", has(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .group("wooden_button")
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(BambooGroveBlocks.BAMBOO_BUTTON.get(), 1)
                .requires(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()).unlockedBy("has_planks", has(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .group("wooden_button")
                .save(consumer, Explorercraft.getId("bamboo_button_from_vertical_planks"));

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_DOOR.get(), 1)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_planks", has(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .group("wooden_door")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_DOOR.get(), 1)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_planks", has(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .group("wooden_door")
                .save(consumer, Explorercraft.getId("bamboo_door_from_vertical_planks"));

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_FENCE.get(), 1)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("#X#")
                .pattern("#X#")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .group("wooden_fence")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_FENCE.get(), 1)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("#X#")
                .pattern("#X#")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .group("wooden_fence")
                .save(consumer, Explorercraft.getId("bamboo_fence_from_vertical_planks"));

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_FENCE_GATE.get(), 1)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("X#X")
                .pattern("X#X")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .group("wooden_fence_gate")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_FENCE_GATE.get(), 1)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("X#X")
                .pattern("X#X")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .group("wooden_fence_gate")
                .save(consumer, Explorercraft.getId("bamboo_fence_gate_from_vertical_planks"));

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_PRESSURE_PLATE.get(), 1)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .pattern("##")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .group("wooden_pressure_plate")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_PRESSURE_PLATE.get(), 1)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .pattern("##")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .group("wooden_pressure_plate")
                .save(consumer, Explorercraft.getId("bamboo_pressure_plate_from_vertical_planks"));

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_SLAB.get(), 6)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .group("wooden_slab")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_SLAB.get(), 6)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .group("wooden_slab")
                .save(consumer, Explorercraft.getId("bamboo_slab_from_vertical_planks"));

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_STAIRS.get(), 4)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .group("wooden_stairs")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_STAIRS.get(), 4)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .group("wooden_stairs")
                .save(consumer, Explorercraft.getId("bamboo_stairs_from_vertical_planks"));

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_TRAPDOOR.get(), 2)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .group("wooden_trapdoor")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_TRAPDOOR.get(), 2)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .group("wooden_trapdoor")
                .save(consumer, Explorercraft.getId("bamboo_trapdoor_from_vertical_planks"));



        //===== Cherry Blocks
        ShapelessRecipeBuilder.shapeless(BambooGroveBlocks.CHERRY_PLANKS.get(), 4)
                .requires(BambooGroveBlocks.CHERRY_LOG.get())
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_LOG.get()))
                .group("planks")
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(BambooGroveBlocks.CHERRY_BUTTON.get(), 1)
                .requires(BambooGroveBlocks.CHERRY_PLANKS.get()).unlockedBy("has_planks", has(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .group("wooden_button")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_DOOR.get(), 3)
                .define('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_planks", has(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .group("wooden_door")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_FENCE.get(), 1)
                .define('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("#X#")
                .pattern("#X#")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .group("wooden_fence")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_FENCE_GATE.get(), 1)
                .define('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("X#X")
                .pattern("X#X")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .group("wooden_fence_gate")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_PRESSURE_PLATE.get(), 1)
                .define('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .pattern("##")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .group("wooden_pressure_plate")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_SLAB.get(), 6)
                .define('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .group("wooden_slab")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_STAIRS.get(), 4)
                .define('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .group("wooden_stairs")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_TRAPDOOR.get(), 2)
                .define('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .group("wooden_trapdoor")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_WOOD.get(), 3)
                .define('#', BambooGroveBlocks.CHERRY_LOG.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_LOG.get()))
                .group("bark")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_STRIPPED_WOOD.get(), 3)
                .define('#', BambooGroveBlocks.CHERRY_STRIPPED_LOG.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_LOG.get()))
                .group("bark")
                .save(consumer);

        //===== Panels
        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_PANEL.get(), 3)
                .define('X', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .define('#', Items.PAPER)
                .pattern("X#X")
                .pattern("X#X")
                .pattern("X#X")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .group("panel")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.BAMBOO_PANEL.get(), 3)
                .define('X', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .define('#', Items.PAPER)
                .pattern("X#X")
                .pattern("X#X")
                .pattern("X#X")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .group("panel")
                .save(consumer, Explorercraft.getId("bamboo_panel_from_vertical_planks"));

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_PANEL.get(), 3)
                .define('X', BambooGroveBlocks.CHERRY_PLANKS.get())
                .define('#', Items.PAPER)
                .pattern("X#X")
                .pattern("X#X")
                .pattern("X#X")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .group("panel")
                .save(consumer);

        //===== Cherry Blossom Blocks
        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get(), 8)
                .define('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .define('B', BambooGroveItems.CHERRY_BLOSSOM.get())
                .pattern("###")
                .pattern("#B#")
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveItems.CHERRY_BLOSSOM.get()))
                .group("planks")
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(BambooGroveBlocks.CHERRY_BLOSSOM_BUTTON.get(), 1)
                .requires(BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get()).unlockedBy("has_planks", has(BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get()))
                .group("wooden_button")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_BLOSSOM_DOOR.get(), 3)
                .define('#', BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_planks", has(BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get()))
                .group("wooden_door")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_BLOSSOM_FENCE.get(), 1)
                .define('#', BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("#X#")
                .pattern("#X#")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get()))
                .group("wooden_fence")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_BLOSSOM_FENCE_GATE.get(), 1)
                .define('#', BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("X#X")
                .pattern("X#X")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get()))
                .group("wooden_fence_gate")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_BLOSSOM_PRESSURE_PLATE.get(), 1)
                .define('#', BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get())
                .pattern("##")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get()))
                .group("wooden_pressure_plate")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_BLOSSOM_SLAB.get(), 6)
                .define('#', BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get())
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get()))
                .group("wooden_slab")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_BLOSSOM_STAIRS.get(), 4)
                .define('#', BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get())
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get()))
                .group("wooden_stairs")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.CHERRY_BLOSSOM_TRAPDOOR.get(), 2)
                .define('#', BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get()))
                .group("wooden_trapdoor")
                .save(consumer);

        //===== Maple
        ShapelessRecipeBuilder.shapeless(BambooGroveBlocks.MAPLE_PLANKS.get(), 4)
                .requires(BambooGroveBlocks.MAPLE_LOG.get())
                .unlockedBy("has_item", has(BambooGroveBlocks.MAPLE_LOG.get()))
                .group("planks")
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(BambooGroveBlocks.MAPLE_BUTTON.get(), 1)
                .requires(BambooGroveBlocks.MAPLE_PLANKS.get()).unlockedBy("has_planks", has(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .group("wooden_button")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.MAPLE_DOOR.get(), 3)
                .define('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_planks", has(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .group("wooden_door")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.MAPLE_FENCE.get(), 1)
                .define('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("#X#")
                .pattern("#X#")
                .unlockedBy("has_item", has(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .group("wooden_fence")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.MAPLE_FENCE_GATE.get(), 1)
                .define('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("X#X")
                .pattern("X#X")
                .unlockedBy("has_item", has(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .group("wooden_fence_gate")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.MAPLE_PRESSURE_PLATE.get(), 1)
                .define('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .pattern("##")
                .unlockedBy("has_item", has(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .group("wooden_pressure_plate")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.MAPLE_SLAB.get(), 6)
                .define('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .group("wooden_slab")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.MAPLE_STAIRS.get(), 4)
                .define('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .group("wooden_stairs")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.MAPLE_TRAPDOOR.get(), 2)
                .define('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .group("wooden_trapdoor")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.MAPLE_WOOD.get(), 3)
                .define('#', BambooGroveBlocks.MAPLE_LOG.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_item", has(BambooGroveBlocks.MAPLE_LOG.get()))
                .group("bark")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.MAPLE_STRIPPED_WOOD.get(), 3)
                .define('#', BambooGroveBlocks.MAPLE_STRIPPED_LOG.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_item", has(BambooGroveBlocks.MAPLE_LOG.get()))
                .group("bark")
                .save(consumer);
    }


    private static void createBambooGroveJadeRecipes(Consumer<IFinishedRecipe> consumer) {

        Item jade = BambooGroveItems.JADE.get();
        createArmorForMaterial(consumer, jade);
        createToolsForMaterial(consumer, jade);

        ShapedRecipeBuilder.shaped(BambooGroveItems.JADE_BOW.get(), 1)
                .define('J', jade)
                .define('|', Items.STRING)
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern("JS|")
                .pattern("S |")
                .pattern("JS|")
                .unlockedBy("has_jade", has(jade))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(jade, 9)
                .requires(BambooGroveBlocks.JADE_BLOCK.get()).unlockedBy("has_item", has(jade))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveBlocks.JADE_BLOCK.get(), 1)
                .define('#', jade)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(jade))
                .save(consumer);

        CookingRecipeBuilder.smelting(Ingredient.of(BambooGroveBlocks.JADE_ORE.get().asItem()), jade, 1.0F, 200)
                .unlockedBy("has_item", has(jade)).save(consumer, Explorercraft.getId("jade_from_smelting"));

        CookingRecipeBuilder.smelting(Ingredient.of(BambooGroveBlocks.JADE_ORE.get().asItem()), jade, 1.0F, 200)
                .unlockedBy("has_item", has(jade)).save(consumer, Explorercraft.getId("jade_from_blasting"));
    }

    private static void createBambooGroveFoodRecipes(Consumer<IFinishedRecipe> consumer) {

        Item rice = BambooGroveItems.RICE.get();
        ShapelessRecipeBuilder.shapeless(BambooGroveItems.SALMON_SUSHI.get(), 1)
                .requires(rice)
                .requires(Items.COOKED_SALMON)
                .group("sushi")
                .unlockedBy("has_item", has(BambooGroveItems.SALMON_SUSHI.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(BambooGroveItems.SALMON_SUSHI.get(), 1)
                .requires(rice)
                .requires(Items.SALMON)
                .group("sushi")
                .unlockedBy("has_item", has(BambooGroveItems.SALMON_SUSHI.get()))
                .save(consumer,  Explorercraft.getId("raw_salmon_sushi"));

        ShapelessRecipeBuilder.shapeless(BambooGroveItems.ONIGIRI.get(), 2)
                .requires(rice)
                .requires(Items.DRIED_KELP)
                .unlockedBy("has_item", has(BambooGroveItems.ONIGIRI.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(BambooGroveItems.TAMAGO_SUSHI.get(), 1)
                .requires(rice)
                .requires(Items.EGG)
                .requires(Items.DRIED_KELP)
                .unlockedBy("has_item", has(BambooGroveItems.TAMAGO_SUSHI.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(BambooGroveItems.RICE_STEW.get(), 1)
                .requires(Items.BOWL)
                .requires(rice)
                .requires(rice)
                .requires(rice)
                .requires(rice)
                .requires(rice)
                .requires(rice)
                .unlockedBy("has_item", has(BambooGroveItems.RICE_STEW.get()))
                .save(consumer);
    }

    private static void createToolsForMaterial(Consumer<IFinishedRecipe> consumer, Item material) {
        Tags.IOptionalNamedTag<Item> stick = Tags.Items.RODS_WOODEN;
        ShapedRecipeBuilder.shaped(BambooGroveItems.JADE_AXE.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("##")
                .pattern("#|")
                .pattern(" |")
                .unlockedBy("has_item", has(material))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveItems.JADE_HOE.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("##")
                .pattern(" |")
                .pattern(" |")
                .unlockedBy("has_item", has(material))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveItems.JADE_PICKAXE.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("###")
                .pattern(" | ")
                .pattern(" | ")
                .unlockedBy("has_item", has(material))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveItems.JADE_SHOVEL.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("#")
                .pattern("|")
                .pattern("|")
                .unlockedBy("has_item", has(material))
                .save(consumer);
        ShapedRecipeBuilder.shaped(BambooGroveItems.JADE_SWORD.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("#")
                .pattern("#")
                .pattern("|")
                .unlockedBy("has_item", has(material))
                .save(consumer);
    }
    private static void createArmorForMaterial(Consumer<IFinishedRecipe> consumer, Item material) {
        ShapedRecipeBuilder.shaped(BambooGroveItems.JADE_HELMET.get(), 1)
                .define('#', material)
                .pattern("###")
                .pattern("# #")
                .unlockedBy("has_item", has(material))
                .save(consumer);
        ShapedRecipeBuilder.shaped(BambooGroveItems.JADE_CHESTPLATE.get(), 1)
                .define('#', material)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(material))
                .save(consumer);
        ShapedRecipeBuilder.shaped(BambooGroveItems.JADE_LEGGINGS.get(), 1)
                .define('#', material)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy("has_item", has(material))
                .save(consumer);
        ShapedRecipeBuilder.shaped(BambooGroveItems.JADE_BOOTS.get(), 1)
                .define('#', material)
                .pattern("# #")
                .pattern("# #")
                .unlockedBy("has_item", has(material))
                .save(consumer);
    }
}
