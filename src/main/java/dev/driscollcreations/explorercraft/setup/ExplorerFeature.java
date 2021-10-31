package dev.driscollcreations.explorercraft.setup;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.world.feature.BambooFoliagePlacer;
import dev.driscollcreations.explorercraft.bamboogrove.world.feature.BambooTruckPlacer;
import dev.driscollcreations.explorercraft.bamboogrove.world.feature.RicePaddyFeature;
import dev.driscollcreations.explorercraft.config.BambooGroveConfig;
import dev.driscollcreations.explorercraft.config.CymruConfig;
import dev.driscollcreations.explorercraft.config.VanillaTweaksConfig;
import dev.driscollcreations.explorercraft.cymru.blocks.CymruBlocks;
import dev.driscollcreations.explorercraft.cymru.world.AshTreeTrunkPlacer;
import dev.driscollcreations.explorercraft.cymru.world.HigherThan100SurfacePlacement;
import dev.driscollcreations.explorercraft.cymru.world.SnowdoniaFlowerBlockStateProvider;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import dev.driscollcreations.explorercraft.vanillatweaks.world.feature.NoctilucaFeature;
import dev.driscollcreations.explorercraft.vanillatweaks.world.feature.SlimeBlockFeature;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.ForestFlowerBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunkplacer.*;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.Level;

import java.util.Objects;
import java.util.OptionalInt;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID)
public class ExplorerFeature {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Explorercraft.MOD_ID);
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, Explorercraft.MOD_ID);

    //Register features here if we need new features registered and cant use vanilla
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> BAMBOO_TREE = FEATURES.register("bamboo_tree", () -> new TreeFeature(BaseTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> MAPLE_TREE = FEATURES.register("maple_tree", () -> new TreeFeature(BaseTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> ASH_TREE = FEATURES.register("ash_tree", () -> new TreeFeature(BaseTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> RICE_PADDY = FEATURES.register("rice_paddy", RicePaddyFeature::new);
    public static final RegistryObject<Feature<NoFeatureConfig>> SLIMEY_CHUNK = FEATURES.register("slimey_chunk", SlimeBlockFeature::new) ;
    public static final RegistryObject<Feature<FeatureSpreadConfig>> NOCTILUCAS = FEATURES.register("noctilucas", () -> new NoctilucaFeature(FeatureSpreadConfig.CODEC)) ;
    protected static final BlockState COAL_ORE = Blocks.COAL_ORE.defaultBlockState();
    public static final RegistryObject<FoliagePlacerType<BambooFoliagePlacer>> BAMBOO_FOLIAGE_TYPE = FOLIAGE_PLACER_TYPES.register("bamboo_foliage_placer", () -> new FoliagePlacerType<>(BambooFoliagePlacer.CODEC));

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        if (event.getName() == null) return;
        String biome = event.getName().toString();
        Random rand = new Random();
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        final RegistryKey<Biome> biomeRegistryKey = RegistryKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(event.getName(), "Biome registry name was null"));

        event.getGeneration().getStructures().add(() -> ExplorerConfiguredStructures.CONFIGURED_WIZARD_TOWER);

        if (VanillaTweaksConfig.spawnSlimeChunkCaves.get()) {
            if (biome.equals(Biomes.SWAMP.location().toString()) || biome.equals(Biomes.SWAMP_HILLS.location().toString()) || event.getCategory() == Biome.Category.SWAMP) {
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ExplorerFeature.Configured.SLIMEY_CHUNK_SWAMP);
            } else if (rand.nextInt(20) == 0) {
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ExplorerFeature.Configured.SLIMEY_CHUNK_GLOBAL);
            }
        }

        if (biome.equals(ExplorerBiomes.FORESTED_MOUNTAIN.get().getRegistryName().toString()) ) {
            if (VanillaTweaksConfig.spawnMarbleInForestedMountains.get()) {
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.MARBLE_MOUNTAIN);
            }
            if(VanillaTweaksConfig.spawnTempleRuins.get()) {
                event.getGeneration().getStructures().add(() -> ExplorerConfiguredStructures.CONFIGURED_TEMPLE_RUINS);
            }
            if (VanillaTweaksConfig.spawnRuby.get()) {
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.RUBY_ORE);
            }
        }

        if (BiomeDictionary.hasType(biomeRegistryKey, BiomeDictionary.Type.OVERWORLD)) {
            if (VanillaTweaksConfig.spawnMarbleInOverworld.get()) {
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.MARBLE_GENERAL);
            }
            if (VanillaTweaksConfig.spawnBasaltInOverworld.get()) {
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.BASALT_GENERAL);
            }
            if (VanillaTweaksConfig.spawnAmethyst.get()) {
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.AMETHYST_ORE);
            }

        }

        if (VanillaTweaksConfig.spawnNoctilucas.get()) {
            if (biome.equals(Biomes.DEEP_OCEAN.location().toString()) || biome.equals(Biomes.DEEP_COLD_OCEAN.location().toString()) || biome.equals(Biomes.DEEP_FROZEN_OCEAN.location().toString()) || biome.equals(Biomes.DEEP_LUKEWARM_OCEAN.location().toString()) || biome.equals(Biomes.DEEP_WARM_OCEAN.location().toString())) {
                Explorercraft.LOGGER.log(Level.DEBUG, "Generating noctilucas in " + biome);
                generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.NOCTILUCAS);
            }
        }

        if (biome.equals(ExplorerBiomes.SNOWDONIA.get().getRegistryName().toString()) ) {
            event.getGeneration().getStructures().add(() -> ExplorerConfiguredStructures.CONFIGURED_SLATE_DUNGEON);
            generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.SNOWDONIA_OAK);
            generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.SNOWDONIA_BIRCH);
            generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.ASH_TREE);
            generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.SNOWDONIA_FLOWERS);
            generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.SNOWDONIA_WILD_LEEKS);

            if (CymruConfig.spawnSlateInSnowdonia.get()) {
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.SLATE_GEN);
            }
            if(CymruConfig.spawnExtraCoalOre.get()) {
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ExplorerFeature.Configured.ORE_COAL_EXTRA);
            }
            if(CymruConfig.spawnExtraGoldOre.get()) {
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_GOLD_EXTRA);
            }
        }

        String bambooGroveName = ExplorerBiomes.BAMBOO_GROVE.get().getRegistryName().toString();
        if (biome.equals(bambooGroveName)) {
            Explorercraft.LOGGER.log(Level.DEBUG, "Generating " + biome + " features");
            generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configured(
                    new MultipleRandomFeatureConfig(ImmutableList.of(
                            ExplorerFeature.Configured.BAMBOO_TREE.weighted(0.90F),
                            ExplorerFeature.Configured.CHERRY_TREE.weighted(0.01F),
                            ExplorerFeature.Configured.MAPLE_TREE.weighted(0.01F)),
                            ExplorerFeature.Configured.BAMBOO_TREE))
                     .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                     .decorated(Placement.COUNT_EXTRA
                    .configured(new AtSurfaceWithExtraConfig(30, 0.1F, 1))));

            if (BambooGroveConfig.spawnJade.get()) {
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ExplorerFeature.Configured.JADE_ORE);
            }
            if (BambooGroveConfig.spawnRicePaddies.get()) {
                generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ExplorerFeature.Configured.RICE_PADDY);
            }
            if (BambooGroveConfig.spawnCherryBlossomStructures.get()) {
                event.getGeneration().getStructures().add(() -> ExplorerConfiguredStructures.CONFIGURED_SAKURA_TREE);
            }
            if (BambooGroveConfig.spawnToriiGates.get()) {
                event.getGeneration().getStructures().add(() -> ExplorerConfiguredStructures.CONFIGURED_TORII_GATE);
            }
        }
    }
    //Create the configurations here
    public static final class Configs {
        public static final BaseTreeFeatureConfig BAMBOO_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BambooGroveBlocks.BAMBOO_LOG.get().defaultBlockState()),
                new SimpleBlockStateProvider(BambooGroveBlocks.BAMBOO_LEAVES.get().defaultBlockState()),
                new BambooFoliagePlacer(FeatureSpread.fixed(1), FeatureSpread.fixed(1), 6),
                new BambooTruckPlacer(12, 10, 0),
                new TwoLayerFeature(2, 0, 2))).ignoreVines().build();

        public static final BaseTreeFeatureConfig CHERRY_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BambooGroveBlocks.CHERRY_LOG.get().defaultBlockState()),
                new SimpleBlockStateProvider(BambooGroveBlocks.CHERRY_LEAVES.get().defaultBlockState()),
                new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4),
                new FancyTrunkPlacer(6, 6, 0),
                new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build();

        public static final BaseTreeFeatureConfig MAPLE_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BambooGroveBlocks.MAPLE_LOG.get().defaultBlockState()),
                new SimpleBlockStateProvider(BambooGroveBlocks.MAPLE_LEAVES.get().defaultBlockState()),
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)),
                new ForkyTrunkPlacer(4, 2, 2),
                new TwoLayerFeature(1, 0, 2))).ignoreVines().build();

        public static final BaseTreeFeatureConfig ASH_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(CymruBlocks.ASH_LOG.get().defaultBlockState()),
                new SimpleBlockStateProvider(CymruBlocks.ASH_LEAVES.get().defaultBlockState()),
                new FancyFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.fixed(4), 4),
                new AshTreeTrunkPlacer(20, 2, 0),
                new TwoLayerFeature(1, 0, 1, OptionalInt.of(4)))).ignoreVines().build();

        public static final OreFeatureConfig JADE_ORE_CONFIG = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BambooGroveBlocks.JADE_ORE.get().defaultBlockState(), 8);
        public static final OreFeatureConfig RUBY_ORE_CONFIG = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, VanillaTweaksBlocks.RUBY_ORE.get().defaultBlockState(), 8);
        public static final OreFeatureConfig AMETHYST_ORE_CONFIG = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, VanillaTweaksBlocks.AMETHYST_ORE.get().defaultBlockState(), 8);
        public static final BlockClusterFeatureConfig SNOWDONIA_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new SnowdoniaFlowerBlockStateProvider(CymruBlocks.DAFFODIL.get().defaultBlockState())), SimpleBlockPlacer.INSTANCE)).tries(64).build();
        public static final BlockClusterFeatureConfig SNOWDONIA_WILD_LEEKS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CymruBlocks.LEEK_WILD.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build();

    }


    //Created the configured featered with the feature/config and register it
    public static final class Configured {
        public static final ConfiguredFeature<?, ?> BAMBOO_TREE = ExplorerFeature.BAMBOO_TREE.get().configured(Configs.BAMBOO_TREE_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
        public static final ConfiguredFeature<?, ?> CHERRY_TREE = Feature.TREE.configured(Configs.CHERRY_TREE_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
        public static final ConfiguredFeature<?, ?> MAPLE_TREE = ExplorerFeature.MAPLE_TREE.get().configured(Configs.MAPLE_TREE_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.01F, 1)));
        public static final ConfiguredFeature<?, ?> JADE_ORE = Feature.ORE.configured(Configs.JADE_ORE_CONFIG).range(24).squared();
        public static final ConfiguredFeature<?, ?> AMETHYST_ORE = Feature.ORE.configured(Configs.AMETHYST_ORE_CONFIG).range(40).squared();
        public static final ConfiguredFeature<?, ?> RUBY_ORE = Feature.ORE.configured(Configs.RUBY_ORE_CONFIG).range(20).squared();
        public static final ConfiguredFeature<?, ?> RICE_PADDY = ExplorerFeature.RICE_PADDY.get().configured(IFeatureConfig.NONE).decorated(Placement.TOP_SOLID_HEIGHTMAP.configured(IPlacementConfig.NONE));
        public static final ConfiguredFeature<?, ?> SLIMEY_CHUNK_SWAMP = ExplorerFeature.SLIMEY_CHUNK.get().configured(IFeatureConfig.NONE).range(40).squared().count(33);
        public static final ConfiguredFeature<?, ?> SLIMEY_CHUNK_GLOBAL = ExplorerFeature.SLIMEY_CHUNK.get().configured(IFeatureConfig.NONE).range(40).squared().count(20);
        public static final ConfiguredFeature<?, ?> NOCTILUCAS = ExplorerFeature.NOCTILUCAS.get().configured(new FeatureSpreadConfig(20)).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).chance(16);
        public static final ConfiguredFeature<?, ?> MARBLE_MOUNTAIN = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, VanillaTweaksBlocks.MARBLE.get().defaultBlockState(), VanillaTweaksConfig.marbleVeinSizeInForestedMountains.get())).range(150).squared().count(VanillaTweaksConfig.marbleChanceInForestedMountains.get());
        public static final ConfiguredFeature<?, ?> MARBLE_GENERAL = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, VanillaTweaksBlocks.MARBLE.get().defaultBlockState(), VanillaTweaksConfig.marbleVeinSizeInOverworld.get())).range(40).squared().count(VanillaTweaksConfig.marbleChanceInOverworld.get());
        public static final ConfiguredFeature<?, ?> BASALT_GENERAL = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, VanillaTweaksBlocks.BASALT.get().defaultBlockState(), VanillaTweaksConfig.basaltVeinSizeInOverworld.get())).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(30, 0, 64))).squared().count(VanillaTweaksConfig.basaltChanceInOverworld.get());
        public static final ConfiguredFeature<?, ?> ORE_COAL_EXTRA = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, COAL_ORE, 9)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(32, 32, 80))).squared().count(20);
        public static final ConfiguredFeature<?, ?> SLATE_GEN = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, CymruBlocks.SLATE.get().defaultBlockState(), CymruConfig.slateVeinSizeInSnowdonia.get())).range(150).squared().count(CymruConfig.slateChanceInSnowdonia.get());
        public static final ConfiguredFeature<?, ?> SNOWDONIA_OAK = Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(Features.OAK_BEES_005.weighted(0.2F), Features.FANCY_OAK_BEES_002.weighted(0.1F)), Features.OAK_BEES_002)).decorated(Placements.WORLD_SURFACE_LOWER_THAN_100).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(15, 0.1F, 1)));
        public static final ConfiguredFeature<?, ?> SNOWDONIA_BIRCH =  Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(Features.SUPER_BIRCH_BEES_0002.weighted(0.5F)), Features.BIRCH_BEES_0002)).decorated(Placements.WORLD_SURFACE_LOWER_THAN_100).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.5F, 1)));
        public static final ConfiguredFeature<?, ?> SNOWDONIA_FLOWERS = Feature.FLOWER.configured(Configs.SNOWDONIA_FLOWER_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(2);
        public static final ConfiguredFeature<?, ?> SNOWDONIA_WILD_LEEKS =  Feature.RANDOM_PATCH.configured(Configs.SNOWDONIA_WILD_LEEKS_CONFIG).decorated(Placements.WORLD_SURFACE_HIGHER_THAN_100).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(10);
        public static final ConfiguredFeature<?, ?> ASH_TREE = ExplorerFeature.ASH_TREE.get().configured(Configs.ASH_TREE_CONFIG).decorated(Placements.WORLD_SURFACE_HIGHER_THAN_100).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1)));

        public static void registerConfiguredFeatures() {
            register("bamboo_tree", BAMBOO_TREE);
            register("cherry_tree", CHERRY_TREE);
            register("maple_tree", MAPLE_TREE);
            register("jade_ore", JADE_ORE);
            register("amethyst_ore", AMETHYST_ORE);
            register("ruby_ore", RUBY_ORE);
            register("rice_paddy", RICE_PADDY);
            register("slimey_chunk_swamp", SLIMEY_CHUNK_SWAMP);
            register("slimey_chunk_global", SLIMEY_CHUNK_GLOBAL);
            register("noctilucas", NOCTILUCAS);
            register("marble_mountain", MARBLE_MOUNTAIN);
            register("marble_general", MARBLE_GENERAL);
            register("basalt_general", BASALT_GENERAL);
            register("ore_coal_extra", ORE_COAL_EXTRA);
            register("slate_generation", SLATE_GEN);
            register("snowdonia_flowers", SNOWDONIA_FLOWERS);
            register("snowdonia_oak", SNOWDONIA_OAK);
            register("snowdonia_birch", SNOWDONIA_BIRCH);
            register("snowdonia_wild_leeks", SNOWDONIA_WILD_LEEKS);
            register("ash_tree", ASH_TREE);

        }

        private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Explorercraft.MOD_ID, name), configuredFeature);
        }

    }

    public static final class Placements {
        public static final ConfiguredPlacement<NoPlacementConfig> WORLD_SURFACE_HIGHER_THAN_100 = ExplorerPlacement.HIGHER_THAN_100.get().configured(IPlacementConfig.NONE);
        public static final ConfiguredPlacement<NoPlacementConfig> WORLD_SURFACE_LOWER_THAN_100 = ExplorerPlacement.LOWER_THAN_100.get().configured(IPlacementConfig.NONE);
    }

}
