package com.zathrox.explorercraft.core.data.provider;

import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import com.zathrox.explorercraft.core.registry.ExplorerItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ExplorercraftRecipesProvider extends RecipeProvider {

    public ExplorercraftRecipesProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        ShapelessRecipeBuilder.shapelessRecipe(Blocks.MOSSY_COBBLESTONE).addIngredient(Blocks.COBBLESTONE).addIngredient(ExplorerBlocks.WILLOW).addCriterion("has_willow", this.hasItem(ExplorerBlocks.WILLOW)).build(consumer, new ResourceLocation(Explorercraft.MOD_ID, "mossy_cobblestone_from_willow"));
        ShapelessRecipeBuilder.shapelessRecipe(Blocks.MOSSY_STONE_BRICKS).addIngredient(Blocks.STONE_BRICKS).addIngredient(ExplorerBlocks.WILLOW).addCriterion("has_willow", this.hasItem(Blocks.MOSSY_COBBLESTONE)).build(consumer, new ResourceLocation(Explorercraft.MOD_ID, "mossy_stone_bricks_from_willow"));
        ShapelessRecipeBuilder.shapelessRecipe(ExplorerBlocks.BASALT_MOSSY).addIngredient(ExplorerBlocks.BASALT_BRICKS).addIngredient(ExplorerBlocks.WILLOW).addCriterion("has_willow", this.hasItem(ExplorerBlocks.WILLOW)).build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ExplorerBlocks.BASALT_COBBLESTONE_MOSSY).addIngredient(ExplorerBlocks.BASALT_COBBLESTONE).addIngredient(ExplorerBlocks.WILLOW).addCriterion("has_willow", this.hasItem(ExplorerBlocks.WILLOW)).build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ExplorerBlocks.MARBLE_MOSSY).addIngredient(ExplorerBlocks.MARBLE_BRICKS).addIngredient(ExplorerBlocks.WILLOW).addCriterion("has_willow", this.hasItem(ExplorerBlocks.WILLOW)).build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ExplorerBlocks.SLATE_MOSSY).addIngredient(ExplorerBlocks.SLATE_BRICKS).addIngredient(ExplorerBlocks.WILLOW).addCriterion("has_willow", this.hasItem(ExplorerBlocks.WILLOW)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(Items.SADDLE, 1)
                .patternLine("LLL") //
                .patternLine("SLS") //
                .patternLine("I I") //
                .key('L', Items.LEATHER) //
                .key('S', Items.STRING)
                .key('I', Items.IRON_INGOT)
                .addCriterion("has_leather", hasItem(Items.LEATHER))
                .addCriterion("has_saddle", hasItem(Items.SADDLE))
                .build(consumer, new ResourceLocation(Explorercraft.MOD_ID, "saddle"));
        ShapedRecipeBuilder.shapedRecipe(ExplorerItems.JADE_BOW)
                .key('#', ExplorerBlocks.CHERRY_PLANKS)
                .key('X', Items.STRING)
                .key('J', ExplorerItems.JADE)
                .patternLine("J#X")
                .patternLine("# X")
                .patternLine("J#X")
                .addCriterion("has_string", this.hasItem(Items.STRING))
                .addCriterion("has_jade", this.hasItem(ExplorerItems.JADE))
                .addCriterion("has_cherry", this.hasItem(ExplorerBlocks.CHERRY_PLANKS))
                .build(consumer);
        /*ShapedRecipeBuilder.shapedRecipe(Items.DIAMOND, 5) //
                .patternLine("TTT") //
                .patternLine("TXT") //
                .patternLine("TTT") //
                .key('T', Blocks.DIRT) //
                .key('X', ExplorerBlocks.MUD) //
                .addCriterion("has_dirt", hasItem(Blocks.DIRT)) //
                .build(consumer, new ResourceLocation(Explorercraft.MOD_ID, "tutorial_diamonds"));

        ShapelessRecipeBuilder.shapelessRecipe(Blocks.DIAMOND_BLOCK, 2) //
                .addIngredient(ExplorerBlocks.MUD) //
                .addIngredient(Blocks.DIRT) //
                .addIngredient(Blocks.DIRT) //
                .addCriterion("has_dirt", hasItem(Blocks.DIRT)) //
                .build(consumer, new ResourceLocation(Explorercraft.MOD_ID, "tutorial_diamond_blocks"));*/


        /*CookingRecipeBuilder.smeltingRecipe(getFakeIngredient(Tags.Items.STORAGE_BLOCKS), Blocks.DIRT, 3, 20) //
                .addCriterion("has_storage_blocks", hasItem(Tags.Items.STORAGE_BLOCKS)) //
                .build(consumer, new ResourceLocation(Explorercraft.MOD_ID, "tutorial_dirt_fake"));*/
    }

    private Ingredient getFakeIngredient(Tag<Item> tag) {
        return Ingredient.fromItemListStream(Stream.of(new Ingredient.TagList(tag) {

            @Override
            public Collection<ItemStack> getStacks() {
                return Arrays.asList(new ItemStack(Items.ACACIA_BOAT));
            }
        }));
    }
}