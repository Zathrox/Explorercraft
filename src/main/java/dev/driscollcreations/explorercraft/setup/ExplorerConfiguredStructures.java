package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.SavannaVillagePools;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExplorerConfiguredStructures {

    /**
     * Static instance of our structure so we can reference it and add it to biomes easily.
     */
    public static StructureFeature<?, ?> CONFIGURED_RUN_DOWN_HOUSE = ExplorerStructures.RUN_DOWN_HOUSE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_BLACKSTONE_DUNGEON = ExplorerStructures.BLACKSTONE_DUNGEON.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_SAKURA_TREE = ExplorerStructures.SAKURA_TREE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_TORII_GATE = ExplorerStructures.TORII_GATE.get().configured(IFeatureConfig.NONE);
    /*public static StructureFeature<?, ?> TEST_VILLAGE = Structure.VILLAGE.configured(new VillageConfig(() -> {
        return SavannaVillagePools.START;
    }, 6));*/

    /**
     * Registers the configured structure which is what gets added to the biomes.
     * Noticed we are not using a forge registry because there is none for configured structures.
     *
     * We can register configured structures at any time before a world is clicked on and made.
     * But the best time to register configured features by code is honestly to do it in FMLCommonSetupEvent.
     */
    public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_run_down_house"), CONFIGURED_RUN_DOWN_HOUSE);
        Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_blackstone_dungeon"), CONFIGURED_BLACKSTONE_DUNGEON);
        Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_sakura_tree"), CONFIGURED_SAKURA_TREE);
        Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_torii_gate"), CONFIGURED_TORII_GATE);
        /*Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "test_village"), TEST_VILLAGE);*/

        /* Ok so, this part may be hard to grasp but basically, just add your structure to this to
         * prevent any sort of crash or issue with other mod's custom ChunkGenerators. If they use
         * FlatGenerationSettings.STRUCTURES in it and you don't add your structure to it, the game
         * could crash later when you attempt to add the StructureSeparationSettings to the dimension.
         *
         * (It would also crash with superflat worldtype if you omit the below line
         * and attempt to add the structure's StructureSeparationSettings to the world)
         *
         * Note: If you want your structure to spawn in superflat, remove the FlatChunkGenerator check
         * in StructureTutorialMain.addDimensionalSpacing and then create a superflat world, exit it,
         * and re-enter it and your structures will be spawning. I could not figure out why it needs
         * the restart but honestly, superflat is really buggy and shouldn't be your main focus in my opinion.
         *
         * Requires AccessTransformer ( see resources/META-INF/accesstransformer.cfg )
         */
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ExplorerStructures.RUN_DOWN_HOUSE.get(), CONFIGURED_RUN_DOWN_HOUSE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ExplorerStructures.BLACKSTONE_DUNGEON.get(), CONFIGURED_BLACKSTONE_DUNGEON);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ExplorerStructures.SAKURA_TREE.get(), CONFIGURED_SAKURA_TREE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ExplorerStructures.TORII_GATE.get(), CONFIGURED_TORII_GATE);
    }
}
