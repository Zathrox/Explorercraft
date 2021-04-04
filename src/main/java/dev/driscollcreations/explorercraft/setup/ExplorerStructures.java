package dev.driscollcreations.explorercraft.setup;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.world.structures.BlackstoneDungeonStructure;
import dev.driscollcreations.explorercraft.bamboogrove.world.structures.RunDownHouseStructure;
import dev.driscollcreations.explorercraft.bamboogrove.world.structures.SakuraTreeStructure;
import dev.driscollcreations.explorercraft.bamboogrove.world.structures.ToriiGateStructure;
import dev.driscollcreations.explorercraft.config.BambooGroveConfig;
import dev.driscollcreations.explorercraft.vanillatweaks.structures.TempleRuinStructure;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.SavannaVillagePools;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ExplorerStructures {

    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Explorercraft.MOD_ID);

    /*public static final RegistryObject<Structure<NoFeatureConfig>> RUN_DOWN_HOUSE = registerStructure("run_down_house", () -> (new RunDownHouseStructure(NoFeatureConfig.CODEC)));*/
    public static final RegistryObject<Structure<NoFeatureConfig>> BLACKSTONE_DUNGEON = registerStructure("blackstone_dungeon", () -> (new BlackstoneDungeonStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> SAKURA_TREE = registerStructure("sakura_tree", () -> (new SakuraTreeStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TORII_GATE = registerStructure("torii_gate", () -> (new ToriiGateStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TEMPLE_RUINS = registerStructure("temple_ruins", () -> (new TempleRuinStructure(NoFeatureConfig.CODEC)));


    private static <T extends Structure<?>> RegistryObject<T> registerStructure(String name, Supplier<T> structure) {
        return STRUCTURES.register(name, structure);
    }

    public static void setupStructures() {
        /*setupMapSpacingAndLand(
                RUN_DOWN_HOUSE.get(), // The instance of the structure
                new StructureSeparationSettings(10 *//* maximum distance apart in chunks between spawn attempts *//*,
                        5 *//* minimum distance apart in chunks between spawn attempts *//*,
                        1234567890 *//* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. *//*),
                true);*/

        setupMapSpacingAndLand(BLACKSTONE_DUNGEON.get(), new StructureSeparationSettings(30,15,564645743),false);
        setupMapSpacingAndLand(SAKURA_TREE.get(), new StructureSeparationSettings(20, 6, 647785356), true);
        setupMapSpacingAndLand(TORII_GATE.get(), new StructureSeparationSettings(40,20,539272656),true);
        setupMapSpacingAndLand(TEMPLE_RUINS.get(), new StructureSeparationSettings(40,20,76345737),true);

    }

    public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure, StructureSeparationSettings structureSeparationSettings, boolean transformSurroundingLand) {

        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        if (transformSurroundingLand) {
            Structure.NOISE_AFFECTING_FEATURES = ImmutableList.<Structure<?>>builder()
                            .addAll(Structure.NOISE_AFFECTING_FEATURES)
                            .add(structure)
                            .build();
        }

        DimensionStructuresSettings.DEFAULTS = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                        .putAll(DimensionStructuresSettings.DEFAULTS)
                        .put(structure, structureSeparationSettings)
                        .build();


        WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().structureSettings().structureConfig();

            if(structureMap instanceof ImmutableMap){
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureSeparationSettings);
                settings.getValue().structureSettings().structureConfig = tempMap;
            }
            else{
                structureMap.put(structure, structureSeparationSettings);
            }
        });
    }
}
