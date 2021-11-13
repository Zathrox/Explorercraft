package dev.driscollcreations.explorercraft.data.recipes;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import dev.driscollcreations.explorercraft.cymru.blocks.CymruBlocks;
import dev.driscollcreations.explorercraft.cymru.items.CymruItems;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class ExplorerRecipeProvider extends RecipeProvider {

    public ExplorerRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        createJadeRecipes(consumer);
        createAmethystRecipes(consumer);
        createRubyRecipes(consumer);
        createBambooGroveFoodRecipes(consumer);
        createBambooGroveMiscRecipes(consumer);
        createBambooGroveWoodRecipes(consumer);
        createVanillaTweakRecipes(consumer);
        createVanillaTweakMarbleRecipes(consumer);
        createVanillaTweakBasaltRecipes(consumer);
        createCymruExpansionSlateRecipes(consumer);
        createCymruExpansionAshRecipes(consumer);
        createCymruExpansionRecipes(consumer);
    }

    private static void createVanillaTweakRecipes(Consumer<FinishedRecipe> consumer) {
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

    private static void createVanillaTweakMarbleRecipes(Consumer<FinishedRecipe> consumer) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_SLAB.get(), 2).unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_slab_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_STAIRS.get()).unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_stairs_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_WALL.get()).unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_walls_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_PILLAR.get()).unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_pillar_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_CHISELED.get()).unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "chiseled_marble_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_BRICKS.get()).unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_bricks_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_BRICK_SLAB.get(), 2).unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_brick_slab_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_BRICK_STAIRS.get()).unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_brick_stairs_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_BRICK_WALL.get()).unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_brick_wall_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_POLISHED.get()).unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_polished_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_POLISHED_SLAB.get(), 2).unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_polished_slab_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE.get()), VanillaTweaksBlocks.MARBLE_POLISHED_STAIRS.get()).unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_polished_stairs_from_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_BRICKS.get()), VanillaTweaksBlocks.MARBLE_BRICK_SLAB.get(), 2).unlockedBy("has_marble_bricks", has(VanillaTweaksBlocks.MARBLE_BRICKS.get())).save(consumer, "marble_brick_slab_from_marble_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_BRICKS.get()), VanillaTweaksBlocks.MARBLE_BRICK_STAIRS.get()).unlockedBy("has_marble_bricks", has(VanillaTweaksBlocks.MARBLE_BRICKS.get())).save(consumer, "marble_brick_stairs_from_marble_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_BRICKS.get()), VanillaTweaksBlocks.MARBLE_BRICK_WALL.get()).unlockedBy("has_marble_bricks", has(VanillaTweaksBlocks.MARBLE_BRICKS.get())).save(consumer, "marble_brick_wall_from_marble_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_BRICKS.get()), VanillaTweaksBlocks.MARBLE_CHISELED.get()).unlockedBy("has_marble_bricks", has(VanillaTweaksBlocks.MARBLE_BRICKS.get())).save(consumer, "chiseled_marble_from_marble_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_POLISHED.get()), VanillaTweaksBlocks.MARBLE_POLISHED_SLAB.get(), 2).unlockedBy("has_marble_polished", has(VanillaTweaksBlocks.MARBLE_POLISHED.get())).save(consumer, "marble_polished_slab_from_marble_polished_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_POLISHED.get()), VanillaTweaksBlocks.MARBLE_POLISHED_STAIRS.get()).unlockedBy("has_marble_polished", has(VanillaTweaksBlocks.MARBLE_POLISHED.get())).save(consumer, "marble_polished_stairs_from_marble_polished_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_POLISHED.get()), VanillaTweaksBlocks.MARBLE_PILLAR.get()).unlockedBy("has_marble", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_pillar_from_polished_marble_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_MOSSY.get()), VanillaTweaksBlocks.MARBLE_MOSSY_SLAB.get(), 2).unlockedBy("has_marble_mossy", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_mossy_slab_from_marble_mossy_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_MOSSY.get()), VanillaTweaksBlocks.MARBLE_MOSSY_STAIRS.get()).unlockedBy("has_marble_mossy", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_mossy_stairs_from_marble_mossy_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.MARBLE_MOSSY.get()), VanillaTweaksBlocks.MARBLE_MOSSY_WALL.get()).unlockedBy("has_marble_mossy", has(VanillaTweaksBlocks.MARBLE.get())).save(consumer, "marble_mossy_wall_from_marble_mossy_stonecutting");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(VanillaTweaksBlocks.MARBLE_BRICKS.get()),  VanillaTweaksBlocks.MARBLE_CRACKED.get().asItem(), 0.1F, 200).unlockedBy("has_marble_bricks", has(VanillaTweaksBlocks.MARBLE_BRICKS.get())).save(consumer);

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

    private static void createVanillaTweakBasaltRecipes(Consumer<FinishedRecipe> consumer) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT.get()), VanillaTweaksBlocks.BASALT_SLAB.get(), 2).unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_slab_from_basalt_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT.get()), VanillaTweaksBlocks.BASALT_STAIRS.get()).unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_stairs_from_basalt_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT.get()), VanillaTweaksBlocks.BASALT_WALL.get()).unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_walls_from_basalt_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT.get()), VanillaTweaksBlocks.BASALT_PILLAR.get()).unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_pillar_from_basalt_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT.get()), VanillaTweaksBlocks.BASALT_CHISELED.get()).unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "chiseled_basalt_from_basalt_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT.get()), VanillaTweaksBlocks.BASALT_BRICKS.get()).unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_bricks_from_basalt_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT.get()), VanillaTweaksBlocks.BASALT_BRICK_SLAB.get(), 2).unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_brick_slab_from_basalt_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT.get()), VanillaTweaksBlocks.BASALT_BRICK_STAIRS.get()).unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_brick_stairs_from_basalt_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT.get()), VanillaTweaksBlocks.BASALT_BRICK_WALL.get()).unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_brick_wall_from_basalt_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT.get()), VanillaTweaksBlocks.BASALT_POLISHED.get()).unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_polished_from_basalt_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT.get()), VanillaTweaksBlocks.BASALT_POLISHED_SLAB.get(), 2).unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_polished_slab_from_basalt_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT.get()), VanillaTweaksBlocks.BASALT_POLISHED_STAIRS.get()).unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_polished_stairs_from_basalt_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_BRICKS.get()), VanillaTweaksBlocks.BASALT_BRICK_SLAB.get(), 2).unlockedBy("has_basalt_bricks", has(VanillaTweaksBlocks.BASALT_BRICKS.get())).save(consumer, "basalt_brick_slab_from_basalt_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_BRICKS.get()), VanillaTweaksBlocks.BASALT_BRICK_STAIRS.get()).unlockedBy("has_basalt_bricks", has(VanillaTweaksBlocks.BASALT_BRICKS.get())).save(consumer, "basalt_brick_stairs_from_basalt_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_BRICKS.get()), VanillaTweaksBlocks.BASALT_BRICK_WALL.get()).unlockedBy("has_basalt_bricks", has(VanillaTweaksBlocks.BASALT_BRICKS.get())).save(consumer, "basalt_brick_wall_from_basalt_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_BRICKS.get()), VanillaTweaksBlocks.BASALT_CHISELED.get()).unlockedBy("has_basalt_bricks", has(VanillaTweaksBlocks.BASALT_BRICKS.get())).save(consumer, "chiseled_basalt_from_basalt_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_POLISHED.get()), VanillaTweaksBlocks.BASALT_POLISHED_SLAB.get(), 2).unlockedBy("has_basalt_polished", has(VanillaTweaksBlocks.BASALT_POLISHED.get())).save(consumer, "basalt_polished_slab_from_basalt_polished_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_POLISHED.get()), VanillaTweaksBlocks.BASALT_POLISHED_STAIRS.get()).unlockedBy("has_basalt_polished", has(VanillaTweaksBlocks.BASALT_POLISHED.get())).save(consumer, "basalt_polished_stairs_from_basalt_polished_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_POLISHED.get()), VanillaTweaksBlocks.BASALT_PILLAR.get()).unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_pillar_from_polished_basalt_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_MOSSY.get()), VanillaTweaksBlocks.BASALT_MOSSY_SLAB.get(), 2).unlockedBy("has_basalt_mossy", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_mossy_slab_from_basalt_mossy_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_MOSSY.get()), VanillaTweaksBlocks.BASALT_MOSSY_STAIRS.get()).unlockedBy("has_basalt_mossy", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_mossy_stairs_from_basalt_mossy_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_MOSSY.get()), VanillaTweaksBlocks.BASALT_MOSSY_WALL.get()).unlockedBy("has_basalt_mossy", has(VanillaTweaksBlocks.BASALT.get())).save(consumer, "basalt_mossy_wall_from_basalt_mossy_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_COBBLESTONE.get()), VanillaTweaksBlocks.BASALT_COBBLESTONE_SLAB.get(), 2).unlockedBy("has_basalt_cobblestone", has(VanillaTweaksBlocks.BASALT_COBBLESTONE.get())).save(consumer, "basalt_cobblestone_slab_from_basalt_cobblestone_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_COBBLESTONE.get()), VanillaTweaksBlocks.BASALT_COBBLESTONE_STAIRS.get()).unlockedBy("has_basalt_cobblestone", has(VanillaTweaksBlocks.BASALT_COBBLESTONE.get())).save(consumer, "basalt_cobblestone_stairs_from_basalt_cobblestone_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_COBBLESTONE.get()), VanillaTweaksBlocks.BASALT_COBBLESTONE_WALL.get()).unlockedBy("has_basalt_cobblestone", has(VanillaTweaksBlocks.BASALT_COBBLESTONE.get())).save(consumer, "basalt_cobblestone_walls_from_basalt_cobblestone_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get()), VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY_SLAB.get(), 2).unlockedBy("has_basalt_cobblestone_mossy", has(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get())).save(consumer, "basalt_cobblestone_mossy_slab_from_basalt_cobblestone_mossy_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get()), VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY_STAIRS.get()).unlockedBy("has_basalt_cobblestone_mossy", has(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get())).save(consumer, "basalt_cobblestone_mossy_stairs_from_basalt_cobblestone_mossy_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get()), VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY_WALL.get()).unlockedBy("has_basalt_cobblestone_mossy", has(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get())).save(consumer, "basalt_cobblestone_mossy_walls_from_basalt_cobblestone_mossy_stonecutting");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(VanillaTweaksBlocks.BASALT_BRICKS.get()),  VanillaTweaksBlocks.BASALT_CRACKED.get().asItem(), 0.1F, 200).unlockedBy("has_basalt_bricks", has(VanillaTweaksBlocks.BASALT_BRICKS.get())).save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(VanillaTweaksBlocks.BASALT_COBBLESTONE.get()),  VanillaTweaksBlocks.BASALT.get().asItem(), 0.1F, 200).unlockedBy("has_basalt_cobblestone", has(VanillaTweaksBlocks.BASALT_COBBLESTONE.get())).save(consumer);

        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_SLAB.get(), 6).define('#', VanillaTweaksBlocks.BASALT.get()).pattern("###").unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_STAIRS.get(), 4).define('#', VanillaTweaksBlocks.BASALT.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_WALL.get(), 6).define('#', VanillaTweaksBlocks.BASALT.get()).pattern("###").pattern("###").unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_CHISELED.get()).define('#', VanillaTweaksBlocks.BASALT_BRICK_SLAB.get()).pattern("#").pattern("#").unlockedBy("has_basalt_bricks", has(VanillaTweaksBlocks.BASALT_BRICKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_PILLAR.get(), 2).define('#', VanillaTweaksBlocks.BASALT_POLISHED.get()).pattern("#").pattern("#").unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).unlockedBy("has_basalt_polished", has(VanillaTweaksBlocks.BASALT_POLISHED.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_BRICKS.get(), 4).define('#', VanillaTweaksBlocks.BASALT.get()).pattern("##").pattern("##").unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_BRICK_SLAB.get(), 6).define('#', VanillaTweaksBlocks.BASALT_BRICKS.get()).pattern("###").unlockedBy("has_basalt_bricks", has(VanillaTweaksBlocks.BASALT_BRICKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_BRICK_STAIRS.get(), 4).define('#', VanillaTweaksBlocks.BASALT_BRICKS.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_basalt_bricks", has(VanillaTweaksBlocks.BASALT_BRICKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_BRICK_WALL.get(), 6).define('#', VanillaTweaksBlocks.BASALT_BRICKS.get()).pattern("###").pattern("###").unlockedBy("has_basalt_bricks", has(VanillaTweaksBlocks.BASALT_BRICKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_POLISHED.get(), 4).define('#', VanillaTweaksBlocks.BASALT.get()).pattern("##").pattern("##").unlockedBy("has_basalt", has(VanillaTweaksBlocks.BASALT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_POLISHED_SLAB.get(), 6).define('#', VanillaTweaksBlocks.BASALT_POLISHED.get()).pattern("###").unlockedBy("has_basalt_polished", has(VanillaTweaksBlocks.BASALT_POLISHED.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_POLISHED_STAIRS.get(), 4).define('#', VanillaTweaksBlocks.BASALT_POLISHED.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_basalt_polished", has(VanillaTweaksBlocks.BASALT_POLISHED.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(VanillaTweaksBlocks.BASALT_MOSSY.get()).requires(VanillaTweaksBlocks.BASALT.get()).requires(Blocks.VINE).unlockedBy("has_vine", has(Blocks.VINE)).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_MOSSY_SLAB.get(), 6).define('#', VanillaTweaksBlocks.BASALT_MOSSY.get()).pattern("###").unlockedBy("has_basalt_mossy", has(VanillaTweaksBlocks.BASALT_MOSSY.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_MOSSY_STAIRS.get(), 4).define('#', VanillaTweaksBlocks.BASALT_MOSSY.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_basalt_mossy", has(VanillaTweaksBlocks.BASALT_MOSSY.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_MOSSY_WALL.get(), 6).define('#', VanillaTweaksBlocks.BASALT_MOSSY.get()).pattern("###").pattern("###").unlockedBy("has_basalt_mossy", has(VanillaTweaksBlocks.BASALT_MOSSY.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_COBBLESTONE_SLAB.get(), 6).define('#', VanillaTweaksBlocks.BASALT_COBBLESTONE.get()).pattern("###").unlockedBy("has_basalt_cobblestone", has(VanillaTweaksBlocks.BASALT_COBBLESTONE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_COBBLESTONE_STAIRS.get(), 4).define('#', VanillaTweaksBlocks.BASALT_COBBLESTONE.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_basalt_cobblestone", has(VanillaTweaksBlocks.BASALT_COBBLESTONE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_COBBLESTONE_WALL.get(), 6).define('#', VanillaTweaksBlocks.BASALT_COBBLESTONE.get()).pattern("###").pattern("###").unlockedBy("has_basalt_cobblestone", has(VanillaTweaksBlocks.BASALT_COBBLESTONE.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get()).requires(VanillaTweaksBlocks.BASALT_COBBLESTONE.get()).requires(Blocks.VINE).unlockedBy("has_vine", has(Blocks.VINE)).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY_SLAB.get(), 6).define('#', VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get()).pattern("###").unlockedBy("has_basalt_cobblestone_mossy", has(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY_STAIRS.get(), 4).define('#', VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_basalt_cobblestone_mossy", has(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get())).save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY_WALL.get(), 6).define('#', VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get()).pattern("###").pattern("###").unlockedBy("has_basalt_cobblestone_mossy", has(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get())).save(consumer);

    }

    private static void createCymruExpansionSlateRecipes(Consumer<FinishedRecipe> consumer) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_SLAB.get(), 2).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "slate_slab_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_STAIRS.get()).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "slate_stairs_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_WALL.get()).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "slate_walls_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_PILLAR.get()).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "slate_pillar_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_CHISELED.get()).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "chiseled_slate_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_BRICKS.get()).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "slate_bricks_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_BRICK_SLAB.get(), 2).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "slate_brick_slab_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_BRICK_STAIRS.get()).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "slate_brick_stairs_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_BRICK_WALL.get()).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "slate_brick_wall_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_POLISHED.get()).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "slate_polished_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_POLISHED_SLAB.get(), 2).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "slate_polished_slab_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_POLISHED_STAIRS.get()).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "slate_polished_stairs_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_TILE.get()).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "slate_tile_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_TILE_SLAB.get(), 2).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "slate_tile_slab_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE.get()), CymruBlocks.SLATE_TILE_STAIRS.get()).unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer, "slate_tile_stairs_from_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE_BRICKS.get()), CymruBlocks.SLATE_BRICK_SLAB.get(), 2).unlockedBy("has_slate_bricks", has(CymruBlocks.SLATE_BRICKS.get())).save(consumer, "slate_brick_slab_from_slate_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE_BRICKS.get()), CymruBlocks.SLATE_BRICK_STAIRS.get()).unlockedBy("has_slate_bricks", has(CymruBlocks.SLATE_BRICKS.get())).save(consumer, "slate_brick_stairs_from_slate_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE_BRICKS.get()), CymruBlocks.SLATE_BRICK_WALL.get()).unlockedBy("has_slate_bricks", has(CymruBlocks.SLATE_BRICKS.get())).save(consumer, "slate_brick_wall_from_slate_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE_BRICKS.get()), CymruBlocks.SLATE_CHISELED.get()).unlockedBy("has_slate_bricks", has(CymruBlocks.SLATE_BRICKS.get())).save(consumer, "chiseled_slate_from_slate_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE_POLISHED.get()), CymruBlocks.SLATE_POLISHED_SLAB.get(), 2).unlockedBy("has_slate_polished", has(CymruBlocks.SLATE_POLISHED.get())).save(consumer, "slate_polished_slab_from_slate_polished_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE_POLISHED.get()), CymruBlocks.SLATE_POLISHED_STAIRS.get()).unlockedBy("has_slate_polished", has(CymruBlocks.SLATE_POLISHED.get())).save(consumer, "slate_polished_stairs_from_slate_polished_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE_POLISHED.get()), CymruBlocks.SLATE_PILLAR.get()).unlockedBy("has_slate_polished", has(CymruBlocks.SLATE_POLISHED.get())).save(consumer, "slate_pillar_from_polished_slate_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE_POLISHED.get()), CymruBlocks.SLATE_TILE.get()).unlockedBy("has_slate_polished", has(CymruBlocks.SLATE_POLISHED.get())).save(consumer, "slate_tile_from_slate_polished_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE_MOSSY.get()), CymruBlocks.SLATE_MOSSY_SLAB.get(), 2).unlockedBy("has_slate_mossy", has(CymruBlocks.SLATE_MOSSY.get())).save(consumer, "slate_mossy_slab_from_slate_mossy_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE_MOSSY.get()), CymruBlocks.SLATE_MOSSY_STAIRS.get()).unlockedBy("has_slate_mossy", has(CymruBlocks.SLATE_MOSSY.get())).save(consumer, "slate_mossy_stairs_from_slate_mossy_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE_MOSSY.get()), CymruBlocks.SLATE_MOSSY_WALL.get()).unlockedBy("has_slate_mossy", has(CymruBlocks.SLATE_MOSSY.get())).save(consumer, "slate_mossy_wall_from_slate_mossy_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE_TILE.get()), CymruBlocks.SLATE_TILE_SLAB.get(), 2).unlockedBy("has_slate_tile", has(CymruBlocks.SLATE_TILE.get())).save(consumer, "slate_tile_slab_from_slate_tile_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(CymruBlocks.SLATE_TILE.get()), CymruBlocks.SLATE_TILE_STAIRS.get()).unlockedBy("has_slate_tile", has(CymruBlocks.SLATE_TILE.get())).save(consumer, "slate_tile_stairs_from_slate_tile_stonecutting");

        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_SLAB.get(), 6).define('#', CymruBlocks.SLATE.get()).pattern("###").unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_STAIRS.get(), 4).define('#', CymruBlocks.SLATE.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_WALL.get(), 6).define('#', CymruBlocks.SLATE.get()).pattern("###").pattern("###").unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_CHISELED.get()).define('#', CymruBlocks.SLATE_BRICK_SLAB.get()).pattern("#").pattern("#").unlockedBy("has_slate_bricks", has(CymruBlocks.SLATE_BRICKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_PILLAR.get(), 2).define('#', CymruBlocks.SLATE_POLISHED.get()).pattern("#").pattern("#").unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).unlockedBy("has_slate_polished", has(CymruBlocks.SLATE_POLISHED.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_BRICKS.get(), 4).define('#', CymruBlocks.SLATE.get()).pattern("##").pattern("##").unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_BRICK_SLAB.get(), 6).define('#', CymruBlocks.SLATE_BRICKS.get()).pattern("###").unlockedBy("has_slate_bricks", has(CymruBlocks.SLATE_BRICKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_BRICK_STAIRS.get(), 4).define('#', CymruBlocks.SLATE_BRICKS.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_slate_bricks", has(CymruBlocks.SLATE_BRICKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_BRICK_WALL.get(), 6).define('#', CymruBlocks.SLATE_BRICKS.get()).pattern("###").pattern("###").unlockedBy("has_slate_bricks", has(CymruBlocks.SLATE_BRICKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_POLISHED.get(), 4).define('#', CymruBlocks.SLATE.get()).pattern("##").pattern("##").unlockedBy("has_slate", has(CymruBlocks.SLATE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_POLISHED_SLAB.get(), 6).define('#', CymruBlocks.SLATE_POLISHED.get()).pattern("###").unlockedBy("has_slate_polished", has(CymruBlocks.SLATE_POLISHED.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_POLISHED_STAIRS.get(), 4).define('#', CymruBlocks.SLATE_POLISHED.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_slate_polished", has(CymruBlocks.SLATE_POLISHED.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(CymruBlocks.SLATE_MOSSY.get()).requires(CymruBlocks.SLATE.get()).requires(Blocks.VINE).unlockedBy("has_vine", has(Blocks.VINE)).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_MOSSY_SLAB.get(), 6).define('#', CymruBlocks.SLATE_MOSSY.get()).pattern("###").unlockedBy("has_slate_mossy", has(CymruBlocks.SLATE_MOSSY.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_MOSSY_STAIRS.get(), 4).define('#', CymruBlocks.SLATE_MOSSY.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_slate_mossy", has(CymruBlocks.SLATE_MOSSY.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_MOSSY_WALL.get(), 6).define('#', CymruBlocks.SLATE_MOSSY.get()).pattern("###").pattern("###").unlockedBy("has_slate_mossy", has(CymruBlocks.SLATE_MOSSY.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_TILE.get(), 4).define('#', CymruBlocks.SLATE_POLISHED.get()).pattern("##").pattern("##").unlockedBy("has_slate_polished", has(CymruBlocks.SLATE_POLISHED.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_TILE_SLAB.get(), 6).define('#', CymruBlocks.SLATE_TILE.get()).pattern("###").unlockedBy("has_slate_tile", has(CymruBlocks.SLATE_TILE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(CymruBlocks.SLATE_TILE_STAIRS.get(), 4).define('#', CymruBlocks.SLATE_TILE.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_slate_tile", has(CymruBlocks.SLATE_TILE.get())).save(consumer);
    }

    private static void createCymruExpansionRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(Items.YELLOW_DYE).requires(CymruBlocks.DAFFODIL.get()).unlockedBy("has_daffodil", has(CymruBlocks.DAFFODIL.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(CymruItems.WELSH_RAREBIT.get(), 1)
                .requires(CymruItems.LEEK.get())
                .requires(CymruItems.CHEESE.get())
                .requires(Items.BREAD)
                .unlockedBy("has_item", has(CymruItems.WELSH_RAREBIT.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(CymruItems.WELSH_CAKES.get(), 1)
                .requires(CymruItems.DRIED_FRUIT.get())
                .requires(Items.WHEAT)
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .unlockedBy("has_item", has(CymruItems.WELSH_CAKES.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(CymruItems.LEEK_STEW.get(), 1)
                .requires(Items.BOWL)
                .requires(CymruItems.LEEK.get())
                .requires(CymruItems.LEEK.get())
                .requires(CymruItems.LEEK.get())
                .requires(CymruItems.LEEK.get())
                .requires(CymruItems.LEEK.get())
                .requires(CymruItems.LEEK.get())
                .unlockedBy("has_item", has(CymruItems.LEEK_STEW.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(CymruItems.CAWL_STEW.get(), 1)
                .requires(Items.BOWL)
                .requires(CymruItems.LEEK.get())
                .requires(CymruItems.COOKED_LAMB_SHANK.get())
                .requires(Items.CARROT)
                .requires(Items.POTATO)
                .unlockedBy("has_item", has(CymruItems.CAWL_STEW.get()))
                .save(consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(CymruItems.LAMB_SHANK.get()), CymruItems.COOKED_LAMB_SHANK.get(), 0.35F, 200).unlockedBy("has_lamb_shank", has(CymruItems.LAMB_SHANK.get())).save(consumer);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(CymruItems.LAMB_SHANK.get()), CymruItems.COOKED_LAMB_SHANK.get(), 0.35F, 100,  RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_lamb_shank", has(CymruItems.LAMB_SHANK.get())).save(consumer, "cooked_lamb_shank_from_smoking");
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(CymruItems.LAMB_SHANK.get()), CymruItems.COOKED_LAMB_SHANK.get(), 0.35F, 600,  RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_lamb_shank", has(CymruItems.LAMB_SHANK.get())).save(consumer, "cooked_lamb_shank_from_campfire");
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Items.APPLE), CymruItems.DRIED_FRUIT.get(), 0.55F, 100,  RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_apples", has(Items.APPLE)).save(consumer, "dried_fruits_from_apple_smoking");
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Items.SWEET_BERRIES), CymruItems.DRIED_FRUIT.get(), 0.15F, 100,  RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES)).save(consumer, "sweet_berry");


    }

    private static void createBambooGroveMiscRecipes(Consumer<FinishedRecipe> consumer) {

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

    private static void createBambooGroveWoodRecipes(Consumer<FinishedRecipe> consumer) {

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

        ShapedRecipeBuilder.shaped(BambooGroveItems.BAMBOO_SIGN.get(), 3)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS.get()))
                .group("sign")
                .save(consumer);

        ShapedRecipeBuilder.shaped(BambooGroveItems.BAMBOO_SIGN.get(), 3)
                .define('#', BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                .unlockedBy("has_item", has(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get()))
                .group("sign")
                .save(consumer, Explorercraft.getId("bamboo_sign_from_vertical_planks"));


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

        ShapedRecipeBuilder.shaped(BambooGroveItems.CHERRY_SIGN.get(), 3)
                .define('#', BambooGroveBlocks.CHERRY_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_PLANKS.get()))
                .group("sign")
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

        ShapedRecipeBuilder.shaped(BambooGroveItems.CHERRY_BLOSSOM_SIGN.get(), 3)
                .define('#', BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                .unlockedBy("has_item", has(BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get()))
                .group("sign")
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

        ShapedRecipeBuilder.shaped(BambooGroveItems.MAPLE_SIGN.get(), 3)
                .define('#', BambooGroveBlocks.MAPLE_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                .unlockedBy("has_item", has(BambooGroveBlocks.MAPLE_PLANKS.get()))
                .group("sign")
                .save(consumer);
    }


    private static void createJadeRecipes(Consumer<FinishedRecipe> consumer) {

        Item jade = BambooGroveItems.JADE.get();
        createJadeArmor(consumer, jade);
        createJadeTools(consumer, jade);

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

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BambooGroveBlocks.JADE_ORE.get().asItem()), jade, 1.0F, 200)
                .unlockedBy("has_item", has(jade)).save(consumer, Explorercraft.getId("jade_from_smelting"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BambooGroveBlocks.JADE_ORE.get().asItem()), jade, 1.0F, 200)
                .unlockedBy("has_item", has(jade)).save(consumer, Explorercraft.getId("jade_from_blasting"));
    }

    private static void createAmethystRecipes(Consumer<FinishedRecipe> consumer) {

        Item amethyst = BambooGroveItems.AMETHYST.get();
        createAmethystArmor(consumer, amethyst);
        createAmethystTools(consumer, amethyst);

        ShapelessRecipeBuilder.shapeless(amethyst, 9)
                .requires(VanillaTweaksBlocks.AMETHYST_BLOCK.get()).unlockedBy("has_item", has(amethyst))
                .save(consumer);

        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.AMETHYST_BLOCK.get(), 1)
                .define('#', amethyst)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(amethyst))
                .save(consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(VanillaTweaksBlocks.AMETHYST_ORE.get().asItem()), amethyst, 1.0F, 200)
                .unlockedBy("has_item", has(amethyst)).save(consumer, Explorercraft.getId("amethyst_from_smelting"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(VanillaTweaksBlocks.AMETHYST_ORE.get().asItem()), amethyst, 1.0F, 200)
                .unlockedBy("has_item", has(amethyst)).save(consumer, Explorercraft.getId("amethyst_from_blasting"));
    }

    private static void createRubyRecipes(Consumer<FinishedRecipe> consumer) {

        Item ruby = BambooGroveItems.RUBY.get();
        createRubyArmor(consumer, ruby);
        createRubyTools(consumer, ruby);

        ShapelessRecipeBuilder.shapeless(ruby, 9)
                .requires(VanillaTweaksBlocks.RUBY_BLOCK.get()).unlockedBy("has_item", has(ruby))
                .save(consumer);

        ShapedRecipeBuilder.shaped(VanillaTweaksBlocks.RUBY_BLOCK.get(), 1)
                .define('#', ruby)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(ruby))
                .save(consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(VanillaTweaksBlocks.RUBY_ORE.get().asItem()), ruby, 1.0F, 200)
                .unlockedBy("has_item", has(ruby)).save(consumer, Explorercraft.getId("ruby_from_smelting"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(VanillaTweaksBlocks.RUBY_ORE.get().asItem()), ruby, 1.0F, 200)
                .unlockedBy("has_item", has(ruby)).save(consumer, Explorercraft.getId("ruby_from_blasting"));
    }

    private static void createBambooGroveFoodRecipes(Consumer<FinishedRecipe> consumer) {

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

    private static void createJadeTools(Consumer<FinishedRecipe> consumer, Item material) {
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

    private static void createJadeArmor(Consumer<FinishedRecipe> consumer, Item material) {
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

    private static void createAmethystTools(Consumer<FinishedRecipe> consumer, Item material) {
        Tags.IOptionalNamedTag<Item> stick = Tags.Items.RODS_WOODEN;
        ShapedRecipeBuilder.shaped(VanillaTweaksItems.AMETHYST_AXE.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("##")
                .pattern("#|")
                .pattern(" |")
                .unlockedBy("has_item", has(material))
                .save(consumer);

        ShapedRecipeBuilder.shaped(VanillaTweaksItems.AMETHYST_HOE.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("##")
                .pattern(" |")
                .pattern(" |")
                .unlockedBy("has_item", has(material))
                .save(consumer);

        ShapedRecipeBuilder.shaped(VanillaTweaksItems.AMETHYST_PICKAXE.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("###")
                .pattern(" | ")
                .pattern(" | ")
                .unlockedBy("has_item", has(material))
                .save(consumer);

        ShapedRecipeBuilder.shaped(VanillaTweaksItems.AMETHYST_SHOVEL.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("#")
                .pattern("|")
                .pattern("|")
                .unlockedBy("has_item", has(material))
                .save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksItems.AMETHYST_SWORD.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("#")
                .pattern("#")
                .pattern("|")
                .unlockedBy("has_item", has(material))
                .save(consumer);
    }

    private static void createAmethystArmor(Consumer<FinishedRecipe> consumer, Item material) {
        ShapedRecipeBuilder.shaped(VanillaTweaksItems.AMETHYST_HELMET.get(), 1)
                .define('#', material)
                .pattern("###")
                .pattern("# #")
                .unlockedBy("has_item", has(material))
                .save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksItems.AMETHYST_CHESTPLATE.get(), 1)
                .define('#', material)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(material))
                .save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksItems.AMETHYST_LEGGINGS.get(), 1)
                .define('#', material)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy("has_item", has(material))
                .save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksItems.AMETHYST_BOOTS.get(), 1)
                .define('#', material)
                .pattern("# #")
                .pattern("# #")
                .unlockedBy("has_item", has(material))
                .save(consumer);
    }

    private static void createRubyTools(Consumer<FinishedRecipe> consumer, Item material) {
        Tags.IOptionalNamedTag<Item> stick = Tags.Items.RODS_WOODEN;
        ShapedRecipeBuilder.shaped(VanillaTweaksItems.RUBY_AXE.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("##")
                .pattern("#|")
                .pattern(" |")
                .unlockedBy("has_item", has(material))
                .save(consumer);

        ShapedRecipeBuilder.shaped(VanillaTweaksItems.RUBY_HOE.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("##")
                .pattern(" |")
                .pattern(" |")
                .unlockedBy("has_item", has(material))
                .save(consumer);

        ShapedRecipeBuilder.shaped(VanillaTweaksItems.RUBY_PICKAXE.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("###")
                .pattern(" | ")
                .pattern(" | ")
                .unlockedBy("has_item", has(material))
                .save(consumer);

        ShapedRecipeBuilder.shaped(VanillaTweaksItems.RUBY_SHOVEL.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("#")
                .pattern("|")
                .pattern("|")
                .unlockedBy("has_item", has(material))
                .save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksItems.RUBY_SWORD.get(), 1)
                .define('#', material)
                .define('|', stick)
                .pattern("#")
                .pattern("#")
                .pattern("|")
                .unlockedBy("has_item", has(material))
                .save(consumer);
    }

    private static void createRubyArmor(Consumer<FinishedRecipe> consumer, Item material) {
        ShapedRecipeBuilder.shaped(VanillaTweaksItems.RUBY_HELMET.get(), 1)
                .define('#', material)
                .pattern("###")
                .pattern("# #")
                .unlockedBy("has_item", has(material))
                .save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksItems.RUBY_CHESTPLATE.get(), 1)
                .define('#', material)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(material))
                .save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksItems.RUBY_LEGGINGS.get(), 1)
                .define('#', material)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy("has_item", has(material))
                .save(consumer);
        ShapedRecipeBuilder.shaped(VanillaTweaksItems.RUBY_BOOTS.get(), 1)
                .define('#', material)
                .pattern("# #")
                .pattern("# #")
                .unlockedBy("has_item", has(material))
                .save(consumer);
    }

    private static void createCymruExpansionAshRecipes(Consumer<FinishedRecipe> consumer) {
        //===== Ash Blocks
        ShapelessRecipeBuilder.shapeless(CymruBlocks.ASH_PLANKS.get(), 4)
                .requires(CymruBlocks.ASH_LOG.get())
                .unlockedBy("has_item", has(CymruBlocks.ASH_LOG.get()))
                .group("planks")
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(CymruBlocks.ASH_BUTTON.get(), 1)
                .requires(CymruBlocks.ASH_PLANKS.get()).unlockedBy("has_planks", has(CymruBlocks.ASH_PLANKS.get()))
                .group("wooden_button")
                .save(consumer);

        ShapedRecipeBuilder.shaped(CymruBlocks.ASH_DOOR.get(), 3)
                .define('#', CymruBlocks.ASH_PLANKS.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_planks", has(CymruBlocks.ASH_PLANKS.get()))
                .group("wooden_door")
                .save(consumer);

        ShapedRecipeBuilder.shaped(CymruBlocks.ASH_FENCE.get(), 1)
                .define('#', CymruBlocks.ASH_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("#X#")
                .pattern("#X#")
                .unlockedBy("has_item", has(CymruBlocks.ASH_PLANKS.get()))
                .group("wooden_fence")
                .save(consumer);

        ShapedRecipeBuilder.shaped(CymruBlocks.ASH_FENCE_GATE.get(), 1)
                .define('#', CymruBlocks.ASH_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("X#X")
                .pattern("X#X")
                .unlockedBy("has_item", has(CymruBlocks.ASH_PLANKS.get()))
                .group("wooden_fence_gate")
                .save(consumer);

        ShapedRecipeBuilder.shaped(CymruBlocks.ASH_PRESSURE_PLATE.get(), 1)
                .define('#', CymruBlocks.ASH_PLANKS.get())
                .pattern("##")
                .unlockedBy("has_item", has(CymruBlocks.ASH_PLANKS.get()))
                .group("wooden_pressure_plate")
                .save(consumer);

        ShapedRecipeBuilder.shaped(CymruBlocks.ASH_SLAB.get(), 6)
                .define('#', CymruBlocks.ASH_PLANKS.get())
                .pattern("###")
                .unlockedBy("has_item", has(CymruBlocks.ASH_PLANKS.get()))
                .group("wooden_slab")
                .save(consumer);

        ShapedRecipeBuilder.shaped(CymruBlocks.ASH_STAIRS.get(), 4)
                .define('#', CymruBlocks.ASH_PLANKS.get())
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_item", has(CymruBlocks.ASH_PLANKS.get()))
                .group("wooden_stairs")
                .save(consumer);

        ShapedRecipeBuilder.shaped(CymruBlocks.ASH_TRAPDOOR.get(), 2)
                .define('#', CymruBlocks.ASH_PLANKS.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(CymruBlocks.ASH_PLANKS.get()))
                .group("wooden_trapdoor")
                .save(consumer);

        ShapedRecipeBuilder.shaped(CymruBlocks.ASH_WOOD.get(), 3)
                .define('#', CymruBlocks.ASH_LOG.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_item", has(CymruBlocks.ASH_LOG.get()))
                .group("bark")
                .save(consumer);

        ShapedRecipeBuilder.shaped(CymruBlocks.ASH_STRIPPED_WOOD.get(), 3)
                .define('#', CymruBlocks.ASH_STRIPPED_LOG.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_item", has(CymruBlocks.ASH_LOG.get()))
                .group("bark")
                .save(consumer);

        ShapedRecipeBuilder.shaped(CymruItems.ASH_SIGN.get(), 3)
                .define('#', CymruBlocks.ASH_PLANKS.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                .unlockedBy("has_item", has(CymruBlocks.ASH_PLANKS.get()))
                .group("sign")
                .save(consumer);
    }
}
