package dev.driscollcreations.explorercraft.setup;

import com.google.common.collect.ImmutableList;
import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.world.feature.BambooFoliagePlacer;
import dev.driscollcreations.explorercraft.bamboogrove.world.feature.RicePaddyFeature;
import dev.driscollcreations.explorercraft.vanillatweaks.world.feature.SlimeBlockFeature;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.foliageplacer.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.Level;

import java.util.OptionalInt;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID)
public class ExplorerFeature {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Explorercraft.MOD_ID);
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, Explorercraft.MOD_ID);

    //Register features here if we need new features registered and cant use vanilla
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> BAMBOO_TREE = FEATURES.register("bamboo_tree", () -> new TreeFeature(BaseTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> MAPLE_TREE = FEATURES.register("maple_tree", () -> new TreeFeature(BaseTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> RICE_PADDY = FEATURES.register("rice_paddy", () -> new RicePaddyFeature());
    public static final RegistryObject<Feature<NoFeatureConfig>> SLIMEY_CHUNK = FEATURES.register("slimey_chunk", () -> new SlimeBlockFeature()) ;

    public static final RegistryObject<FoliagePlacerType<BambooFoliagePlacer>> BAMBOO_FOLIAGE_TYPE = FOLIAGE_PLACER_TYPES.register("bamboo_foliage_placer", () -> new FoliagePlacerType<>(BambooFoliagePlacer.CODEC));

    //Add which biome to generate one.
    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        if (event.getName() == null) return;
        ResourceLocation biome = event.getName();
        Random rand = new Random();
        BiomeGenerationSettingsBuilder generation = event.getGeneration();

        if(biome == Biomes.SWAMP.getRegistryName() || biome == Biomes.SWAMP_HILLS.getRegistryName()) {
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.SLIMEY_CHUNK_SWAMP);
        } else if (rand.nextInt(20) == 0) {
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.SLIMEY_CHUNK_GLOBAL);
        }

        if (biome.toString().contains("bamboo_grove")) {
            Explorercraft.LOGGER.log(Level.DEBUG, "Generating bamboo trees: " + biome);
            generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(Configured.BAMBOO_TREE.weighted(0.90F), Configured.CHERRY_TREE.weighted(0.01F), Configured.MAPLE_TREE.weighted(0.01F)), Configured.BAMBOO_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(30, 0.1F, 1))));
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.JADE_ORE);
            generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.RICE_PADDY);
            event.getGeneration().getStructures().add(() -> ExplorerConfiguredStructures.CONFIGURED_SAKURA_TREE);
            event.getGeneration().getStructures().add(() -> ExplorerConfiguredStructures.CONFIGURED_TORII_GATE);
            generation.addStructureStart(StructureFeatures.PILLAGER_OUTPOST);
        }
    }

/*
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> OAK = register("oak", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(Features.States.OAK_LOG),
            new SimpleBlockStateProvider(Features.States.OAK_LEAVES),
            new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 3),
            new StraightTrunkPlacer(4, 2, 0),
            new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> DARK_OAK = register("dark_oak", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(Features.States.DARK_OAK_LOG),
            new SimpleBlockStateProvider(Features.States.DARK_OAK_LEAVES),
            new DarkOakFoliagePlacer(FeatureSpread.create(0), FeatureSpread.create(0)),
            new DarkOakTrunkPlacer(6, 2, 1),
            new ThreeLayerFeature(1, 1, 0, 1, 2, OptionalInt.empty()))).setMaxWaterDepth(Integer.MAX_VALUE).setHeightmap(Heightmap.Type.MOTION_BLOCKING).setIgnoreVines().build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BIRCH = register("birch", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(Features.States.BIRCH_LOG),
            new SimpleBlockStateProvider(Features.States.BIRCH_LEAVES),
            new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 3),
            new StraightTrunkPlacer(5, 2, 0),
            new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> ACACIA = register("acacia", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Features.States.ACACIA_LOG), new SimpleBlockStateProvider(Features.States.ACACIA_LEAVES), new AcaciaFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0)), new ForkyTrunkPlacer(5, 2, 2), new TwoLayerFeature(1, 0, 2))).setIgnoreVines().build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SPRUCE = register("spruce", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(Features.States.SPRUCE_LOG), new SimpleBlockStateProvider(Features.States.SPRUCE_LEAVES),
            new SpruceFoliagePlacer(FeatureSpread.create(2, 1), FeatureSpread.create(0, 2), FeatureSpread.create(1, 1)),
            new StraightTrunkPlacer(5, 2, 1),
            new TwoLayerFeature(2, 0, 2))).setIgnoreVines().build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> PINE = register("pine", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Features.States.SPRUCE_LOG), new SimpleBlockStateProvider(Features.States.SPRUCE_LEAVES), new PineFoliagePlacer(FeatureSpread.create(1), FeatureSpread.create(1), FeatureSpread.create(3, 1)), new StraightTrunkPlacer(6, 4, 0), new TwoLayerFeature(2, 0, 2))).setIgnoreVines().build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> JUNGLE_TREE = register("jungle_tree", eFeature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Features.States.JUNGLE_LOG), new SimpleBlockStateProvider(Features.States.JUNGLE_LEAVES), new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 3), new StraightTrunkPlacer(4, 8, 0), new TwoLayerFeature(1, 0, 1))).setDecorators(ImmutableList.of(new CocoaTreeDecorator(0.2F), TrunkVineTreeDecorator.INSTANCE, LeaveVineTreeDecorator.INSTANCE)).setIgnoreVines().build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> FANCY_OAK = register("fancy_oak", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Features.States.OAK_LOG), new SimpleBlockStateProvider(Features.States.OAK_LEAVES), new FancyFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).setIgnoreVines().setHeightmap(Heightmap.Type.MOTION_BLOCKING).build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> JUNGLE_TREE_NO_VINE = register("jungle_tree_no_vine", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Features.States.JUNGLE_LOG), new SimpleBlockStateProvider(Features.States.JUNGLE_LEAVES), new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 3), new StraightTrunkPlacer(4, 8, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build()));
*/

    //Create the configurations here
    public static final class Configs {
        public static final BaseTreeFeatureConfig BAMBOO_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BambooGroveBlocks.BAMBOO_LOG.get().defaultBlockState()),
                new SimpleBlockStateProvider(BambooGroveBlocks.BAMBOO_LEAVES.get().defaultBlockState()),
                new BambooFoliagePlacer(FeatureSpread.fixed(1), FeatureSpread.fixed(1), 6),
                new StraightTrunkPlacer(12, 10, 0),
                new TwoLayerFeature(2, 0, 2))).ignoreVines().build();

        public static final BaseTreeFeatureConfig CHERRY_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BambooGroveBlocks.CHERRY_LOG.get().defaultBlockState()),
                new SimpleBlockStateProvider(BambooGroveBlocks.CHERRY_LEAVES.get().defaultBlockState()),
                new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4),
                new FancyTrunkPlacer(3, 11, 0),
                new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build();

        public static final BaseTreeFeatureConfig MAPLE_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BambooGroveBlocks.MAPLE_LOG.get().defaultBlockState()),
                new SimpleBlockStateProvider(BambooGroveBlocks.MAPLE_LEAVES.get().defaultBlockState()),
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)),
                new ForkyTrunkPlacer(4, 2, 2),
                new TwoLayerFeature(1, 0, 2))).ignoreVines().build();

        public static final OreFeatureConfig JADE_ORE_CONFIG = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BambooGroveBlocks.JADE_ORE.get().defaultBlockState(), 8);
    }


    //Created the configured featered with the feature/config and register it
    public static final class Configured {
        public static final ConfiguredFeature<?, ?> BAMBOO_TREE = ExplorerFeature.BAMBOO_TREE.get().configured(Configs.BAMBOO_TREE_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
        public static final ConfiguredFeature<?, ?> CHERRY_TREE = Feature.TREE.configured(Configs.CHERRY_TREE_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
        public static final ConfiguredFeature<?, ?> MAPLE_TREE = ExplorerFeature.MAPLE_TREE.get().configured(Configs.MAPLE_TREE_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
        public static final ConfiguredFeature<?, ?> JADE_ORE = Feature.ORE.configured(Configs.JADE_ORE_CONFIG).range(24).squared();
        public static final ConfiguredFeature<?, ?> RICE_PADDY = ExplorerFeature.RICE_PADDY.get().configured(IFeatureConfig.NONE).decorated(Placement.TOP_SOLID_HEIGHTMAP.configured(IPlacementConfig.NONE));
        public static final ConfiguredFeature<?, ?> SLIMEY_CHUNK_SWAMP = ExplorerFeature.SLIMEY_CHUNK.get().configured(IFeatureConfig.NONE).range(40).squared().count(33);
        public static final ConfiguredFeature<?, ?> SLIMEY_CHUNK_GLOBAL = ExplorerFeature.SLIMEY_CHUNK.get().configured(IFeatureConfig.NONE).range(40).squared().count(20);

        public static void registerConfiguredFeatures() {
            register("bamboo_tree", BAMBOO_TREE);
            register("cherry_treee", CHERRY_TREE);
            register("maple_tree", MAPLE_TREE);
            register("jade_ore", JADE_ORE);
            register("rice_paddy", RICE_PADDY);
            register("slimey_chunk_swamp", SLIMEY_CHUNK_SWAMP);
            register("slimey_chunk_global", SLIMEY_CHUNK_GLOBAL);

        }

        private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Explorercraft.MOD_ID, name), configuredFeature);
        }
    }

}
