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

    /*public static StructureFeature<?, ?> CONFIGURED_RUN_DOWN_HOUSE = ExplorerStructures.RUN_DOWN_HOUSE.get().configured(IFeatureConfig.NONE);*/
    public static StructureFeature<?, ?> CONFIGURED_BLACKSTONE_DUNGEON = ExplorerStructures.BLACKSTONE_DUNGEON.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_SAKURA_TREE = ExplorerStructures.SAKURA_TREE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_TORII_GATE = ExplorerStructures.TORII_GATE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_TEMPLE_RUINS = ExplorerStructures.TEMPLE_RUINS.get().configured(IFeatureConfig.NONE);
    /*public static StructureFeature<?, ?> TEST_VILLAGE = Structure.VILLAGE.configured(new VillageConfig(() -> {
        return SavannaVillagePools.START;
    }, 6));*/

    public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        /*Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_run_down_house"), CONFIGURED_RUN_DOWN_HOUSE);*/
        Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_blackstone_dungeon"), CONFIGURED_BLACKSTONE_DUNGEON);
        Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_sakura_tree"), CONFIGURED_SAKURA_TREE);
        Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_torii_gate"), CONFIGURED_TORII_GATE);
        Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_temple_ruins"), CONFIGURED_TEMPLE_RUINS);


        FlatGenerationSettings.STRUCTURE_FEATURES.put(ExplorerStructures.BLACKSTONE_DUNGEON.get(), CONFIGURED_BLACKSTONE_DUNGEON);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ExplorerStructures.SAKURA_TREE.get(), CONFIGURED_SAKURA_TREE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ExplorerStructures.TORII_GATE.get(), CONFIGURED_TORII_GATE);
    }
}
