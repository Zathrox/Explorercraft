//package dev.driscollcreations.explorercraft.setup;
//
//import com.google.common.collect.ImmutableList;
//import com.google.common.collect.ImmutableMap;
//import com.sun.jna.Structure;
//import dev.driscollcreations.explorercraft.Explorercraft;
//import dev.driscollcreations.explorercraft.bamboogrove.world.structures.BlackstoneDungeonStructure;
//import dev.driscollcreations.explorercraft.bamboogrove.world.structures.RunDownHouseStructure;
//import dev.driscollcreations.explorercraft.bamboogrove.world.structures.SakuraTreeStructure;
//import dev.driscollcreations.explorercraft.bamboogrove.world.structures.ToriiGateStructure;
//import dev.driscollcreations.explorercraft.config.BambooGroveConfig;
//import dev.driscollcreations.explorercraft.cymru.world.SlateDungeonStructure;
//import dev.driscollcreations.explorercraft.cymru.world.WizardTowerStructure;
//import dev.driscollcreations.explorercraft.vanillatweaks.structures.TempleRuinStructure;
//import net.minecraft.util.registry.BuiltinRegistries;
//import net.minecraft.world.gen.feature.NoneFeatureConfiguration;
//import net.minecraft.world.gen.feature.StructureFeature;
//import net.minecraft.world.gen.feature.structure.SavannaVillagePools;
//import net.minecraft.world.gen.feature.structure.Structure;
//import net.minecraft.world.gen.feature.structure.VillageConfig;
//import net.minecraft.world.gen.settings.DimensionStructuresSettings;
//import net.minecraft.world.gen.settings.StructureSeparationSettings;
//import net.minecraft.world.level.levelgen.StructureSettings;
//import net.minecraft.world.level.levelgen.feature.StructureFeature;
//import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
//import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
//import net.minecraftforge.fml.RegistryObject;
//import net.minecraftforge.fmllegacy.RegistryObject;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Supplier;
//
//public class ExplorerStructures {
//
//    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Explorercraft.MOD_ID);
//
//    /*public static final RegistryObject<Structure<NoneFeatureConfiguration>> RUN_DOWN_HOUSE = registerStructure("run_down_house", () -> (new RunDownHouseStructure(NoneFeatureConfiguration.CODEC)));*/
//    //public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> BLACKSTONE_DUNGEON = registerStructure("blackstone_dungeon", () -> (new BlackstoneDungeonStructure(NoneFeatureConfiguration.CODEC)));
//    //public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> SAKURA_TREE = registerStructure("sakura_tree", () -> (new SakuraTreeStructure(NoneFeatureConfiguration.CODEC)));
//    //public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> TORII_GATE = registerStructure("torii_gate", () -> (new ToriiGateStructure(NoneFeatureConfiguration.CODEC)));
//    //public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> TEMPLE_RUINS = registerStructure("temple_ruins", () -> (new TempleRuinStructure(NoneFeatureConfiguration.CODEC)));
//    //public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> WIZARD_TOWER = registerStructure("wizard_tower", () -> (new WizardTowerStructure(NoneFeatureConfiguration.CODEC)));
//    ///public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> SLATE_DUNGEON = registerStructure("slate_dungeon", () -> (new SlateDungeonStructure(NoneFeatureConfiguration.CODEC)));
//
//
//    private static <T extends StructureFeature<?>> RegistryObject<T> registerStructure(String name, Supplier<T> structure) {
//        return STRUCTURES.register(name, structure);
//    }
//
//    public static void setupStructures() {
//        /*setupMapSpacingAndLand(
//                RUN_DOWN_HOUSE.get(), // The instance of the structure
//                new StructureSeparationSettings(10 *//* maximum distance apart in chunks between spawn attempts *//*,
//                        5 *//* minimum distance apart in chunks between spawn attempts *//*,
//                        1234567890 *//* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. *//*),
//                true);*/
//
////        setupMapSpacingAndLand(BLACKSTONE_DUNGEON.get(), new StructurePlaceSettings(30,15,564645743),false);
////        setupMapSpacingAndLand(SAKURA_TREE.get(), new StructurePlaceSettings(20, 6, 647785356), true);
////        setupMapSpacingAndLand(TORII_GATE.get(), new StructurePlaceSettings(40,20,539272656),true);
////        setupMapSpacingAndLand(TEMPLE_RUINS.get(), new StructurePlaceSettings(40,20,76345737),true);
////        setupMapSpacingAndLand(WIZARD_TOWER.get(), new StructurePlaceSettings(10,5,564574345),true);
////        setupMapSpacingAndLand(SLATE_DUNGEON.get(), new StructurePlaceSettings(20,8,564574345),false);
//
//    }
//
//    public static <F extends StructureFeature<?>> void setupMapSpacingAndLand(F structure, StructurePlaceSettings structureSeparationSettings, boolean transformSurroundingLand) {
//
//        StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);
//
//        if (transformSurroundingLand) {
//            StructureFeature.NOISE_AFFECTING_FEATURES = ImmutableList.<StructureFeature<?>>builder()
//                            .addAll(StructureFeature.NOISE_AFFECTING_FEATURES)
//                            .add(structure)
//                            .build();
//        }
//
//        StructureSettings.DEFAULTS = ImmutableMap.<StructureFeature<?>, StructureSeparationSettings>builder()
//                        .putAll(StructureSettings.DEFAULTS)
//                        .put(structure, structureSeparationSettings)
//                        .build();
//
//
//        BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
//            Map<Structure<?>, StructurePlaceSettings> structureMap = settings.getValue().structureSettings().structureConfig();
//
//            if(structureMap instanceof ImmutableMap){
//                Map<StructureFeature<?>, StructurePlaceSettings> tempMap = new HashMap<>(structureMap);
//                tempMap.put(structure, structureSeparationSettings);
//                settings.getValue().structureSettings().structureConfig = tempMap;
//            }
//            else{
//                structureMap.put(structure, structureSeparationSettings);
//            }
//        });
//    }
//}
