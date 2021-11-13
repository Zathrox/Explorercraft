package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ExplorerConfiguredStructures {

    /*public static StructureFeature<?, ?> CONFIGURED_RUN_DOWN_HOUSE = ExplorerStructures.RUN_DOWN_HOUSE.get().configured(NoneFeatureConfiguration.NONE);*/
//    public static ConfiguredStructureFeature<?, ?> CONFIGURED_BLACKSTONE_DUNGEON = ExplorerStructures.BLACKSTONE_DUNGEON.get().configured(NoneFeatureConfiguration.NONE);
//    public static ConfiguredStructureFeature<?, ?> CONFIGURED_SAKURA_TREE = ExplorerStructures.SAKURA_TREE.get().configured(NoneFeatureConfiguration.NONE);
//    public static ConfiguredStructureFeature<?, ?> CONFIGURED_TORII_GATE = ExplorerStructures.TORII_GATE.get().configured(NoneFeatureConfiguration.NONE);
//    public static ConfiguredStructureFeature<?, ?> CONFIGURED_TEMPLE_RUINS = ExplorerStructures.TEMPLE_RUINS.get().configured(NoneFeatureConfiguration.NONE);
//    public static ConfiguredStructureFeature<?, ?> CONFIGURED_WIZARD_TOWER = ExplorerStructures.WIZARD_TOWER.get().configured(NoneFeatureConfiguration.NONE);
//    public static ConfiguredStructureFeature<?, ?> CONFIGURED_SLATE_DUNGEON = ExplorerStructures.SLATE_DUNGEON.get().configured(NoneFeatureConfiguration.NONE);
    /*public static StructureFeature<?, ?> TEST_VILLAGE = Structure.VILLAGE.configured(new VillageConfig(() -> {
        return SavannaVillagePools.START;
    }, 6));*/

    public static void registerConfiguredStructures() {
        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        /*Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_run_down_house"), CONFIGURED_RUN_DOWN_HOUSE);*/
//        Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_blackstone_dungeon"), CONFIGURED_BLACKSTONE_DUNGEON);
//        Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_sakura_tree"), CONFIGURED_SAKURA_TREE);
//        Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_torii_gate"), CONFIGURED_TORII_GATE);
//        Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_temple_ruins"), CONFIGURED_TEMPLE_RUINS);
//        Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_wizard_tower"), CONFIGURED_WIZARD_TOWER);
//        Registry.register(registry, new ResourceLocation(Explorercraft.MOD_ID, "configured_slate_dungeon"), CONFIGURED_SLATE_DUNGEON);


//        FlatGenerationSettings.STRUCTURE_FEATURES.put(ExplorerStructures.BLACKSTONE_DUNGEON.get(), CONFIGURED_BLACKSTONE_DUNGEON);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(ExplorerStructures.SAKURA_TREE.get(), CONFIGURED_SAKURA_TREE);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(ExplorerStructures.TORII_GATE.get(), CONFIGURED_TORII_GATE);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(ExplorerStructures.WIZARD_TOWER.get(), CONFIGURED_WIZARD_TOWER);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(ExplorerStructures.SLATE_DUNGEON.get(), CONFIGURED_SLATE_DUNGEON);
    }
}
