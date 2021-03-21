package dev.driscollcreations.explorercraft.data;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.data.client.*;
import dev.driscollcreations.explorercraft.data.loot.*;
import dev.driscollcreations.explorercraft.data.recipes.ExplorerRecipeProvider;
import dev.driscollcreations.explorercraft.data.tags.ExplorerBlockTagsProvider;
import dev.driscollcreations.explorercraft.data.tags.ExplorerItemTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        if (event.includeServer()) {
            gen.addProvider(new ExplorerBlockStateProvider(gen, existingFileHelper)); //== Creates BlockStates and model files
            gen.addProvider(new ExplorerItemModelProvider(gen, existingFileHelper)); //== Creates model files for items/blockItems
            gen.addProvider(new ExplorerLootTables(gen)); //== Creates loot tables
            gen.addProvider(new ExplorerRecipeProvider(gen)); //== Creates recipe files

            // Create the blockTags and then duplicates it for the Item variants
            ExplorerBlockTagsProvider blockTags = new ExplorerBlockTagsProvider(gen, existingFileHelper);
            gen.addProvider(blockTags);
            gen.addProvider(new ExplorerItemTagsProvider(gen, blockTags, existingFileHelper));
        }
    }
}
