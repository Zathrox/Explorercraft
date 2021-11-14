package dev.driscollcreations.explorercraft.setup;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
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
import dev.driscollcreations.explorercraft.cymru.world.SnowdoniaFlowerBlockStateProvider;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import dev.driscollcreations.explorercraft.vanillatweaks.world.feature.NoctilucaFeature;
import dev.driscollcreations.explorercraft.vanillatweaks.world.feature.SlimeBlockFeature;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.ConfiguredDecorator;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
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
    public static final RegistryObject<Feature<TreeConfiguration>> BAMBOO_TREE = FEATURES.register("bamboo_tree", () -> new TreeFeature(TreeConfiguration.CODEC));
    public static final RegistryObject<Feature<TreeConfiguration>> MAPLE_TREE = FEATURES.register("maple_tree", () -> new TreeFeature(TreeConfiguration.CODEC));
    public static final RegistryObject<Feature<TreeConfiguration>> ASH_TREE = FEATURES.register("ash_tree", () -> new TreeFeature(TreeConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> RICE_PADDY = FEATURES.register("rice_paddy", RicePaddyFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SLIMEY_CHUNK = FEATURES.register("slimey_chunk", SlimeBlockFeature::new);
    public static final RegistryObject<Feature<CountConfiguration>> NOCTILUCAS = FEATURES.register("noctilucas", () -> new NoctilucaFeature(CountConfiguration.CODEC)) ;
    protected static final BlockState COAL_ORE = Blocks.COAL_ORE.defaultBlockState();
   public static final RegistryObject<FoliagePlacerType<BambooFoliagePlacer>> BAMBOO_FOLIAGE_TYPE = FOLIAGE_PLACER_TYPES.register("bamboo_foliage_placer", () -> new FoliagePlacerType<>(BambooFoliagePlacer.CODEC));

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        if (event.getName() == null) return;
        String biome = event.getName().toString();
        Random rand = new Random();
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        final ResourceKey<Biome> biomeResourceKey = ResourceKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(event.getName(), "Biome registry name was null"));

        //event.getGeneration().getStructures().add(() -> ExplorerConfiguredStructures.CONFIGURED_WIZARD_TOWER);

        if (VanillaTweaksConfig.spawnSlimeChunkCaves.get()) {
            if (biome.equals(Biomes.SWAMP.location().toString()) || biome.equals(Biomes.SWAMP_HILLS.location().toString()) || event.getCategory() == Biome.BiomeCategory.SWAMP) {
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ExplorerFeature.Configured.SLIMEY_CHUNK_SWAMP);
            } else if (rand.nextInt(20) == 0) {
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ExplorerFeature.Configured.SLIMEY_CHUNK_GLOBAL);
            }
        }

        if (biome.equals(ExplorerBiomes.FORESTED_MOUNTAIN.get().getRegistryName().toString()) ) {
            if (VanillaTweaksConfig.spawnMarbleInForestedMountains.get()) {
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Configured.MARBLE_MOUNTAIN);
            }
            if(VanillaTweaksConfig.spawnTempleRuins.get()) {
                //event.getGeneration().getStructures().add(() -> ExplorerConfiguredStructures.CONFIGURED_TEMPLE_RUINS);
            }
            if (VanillaTweaksConfig.spawnRuby.get()) {
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Configured.RUBY_ORE);
            }
        }

        if (BiomeDictionary.hasType(biomeResourceKey, BiomeDictionary.Type.OVERWORLD)) {
            if (VanillaTweaksConfig.spawnMarbleInOverworld.get()) {
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Configured.MARBLE_GENERAL);
            }
            if (VanillaTweaksConfig.spawnBasaltInOverworld.get()) {
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Configured.BASALT_GENERAL);
            }
            if (VanillaTweaksConfig.spawnAmethyst.get()) {
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Configured.AMETHYST_ORE);
            }

        }

        if (VanillaTweaksConfig.spawnNoctilucas.get()) {
            if (biome.equals(Biomes.DEEP_OCEAN.location().toString()) || biome.equals(Biomes.DEEP_COLD_OCEAN.location().toString()) || biome.equals(Biomes.DEEP_FROZEN_OCEAN.location().toString()) || biome.equals(Biomes.DEEP_LUKEWARM_OCEAN.location().toString()) || biome.equals(Biomes.DEEP_WARM_OCEAN.location().toString())) {
                Explorercraft.LOGGER.log(Level.DEBUG, "Generating noctilucas in " + biome);
                generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.NOCTILUCAS);
            }
        }

        if (biome.equals(ExplorerBiomes.SNOWDONIA.get().getRegistryName().toString()) ) {
//            event.getGeneration().getStructures().add(() -> ExplorerConfiguredStructures.CONFIGURED_SLATE_DUNGEON);
            generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.SNOWDONIA_OAK);
            generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.SNOWDONIA_BIRCH);
            generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.ASH_TREE);
            generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.SNOWDONIA_FLOWERS);
            generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.SNOWDONIA_WILD_LEEKS);

            if (CymruConfig.spawnSlateInSnowdonia.get()) {
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Configured.SLATE_GEN);
            }
            if(CymruConfig.spawnExtraCoalOre.get()) {
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ExplorerFeature.Configured.ORE_COAL_EXTRA);
            }
            if(CymruConfig.spawnExtraGoldOre.get()) {
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.ORE_GOLD_EXTRA);
            }
        }

        String bambooGroveName = ExplorerBiomes.BAMBOO_GROVE.get().getRegistryName().toString();
        if (biome.equals(bambooGroveName)) {
            Explorercraft.LOGGER.log(Level.DEBUG, "Generating " + biome + " features");
            generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configured(
                    new RandomFeatureConfiguration(ImmutableList.of(
                            ExplorerFeature.Configured.BAMBOO_TREE.weighted(0.90F),
                            ExplorerFeature.Configured.CHERRY_TREE.weighted(0.01F),
                            ExplorerFeature.Configured.MAPLE_TREE.weighted(0.01F)),
                            ExplorerFeature.Configured.BAMBOO_TREE))
                     .decorated(Features.Decorators.HEIGHTMAP_SQUARE)
                     .decorated(FeatureDecorator.COUNT_EXTRA
                    .configured(new FrequencyWithExtraChanceDecoratorConfiguration(30, 0.1F, 1))));

            if (BambooGroveConfig.spawnJade.get()) {
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ExplorerFeature.Configured.JADE_ORE);
            }
            if (BambooGroveConfig.spawnRicePaddies.get()) {
                generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ExplorerFeature.Configured.RICE_PADDY);
            }
            if (BambooGroveConfig.spawnCherryBlossomStructures.get()) {
                //event.getGeneration().getStructures().add(() -> ExplorerConfiguredStructures.CONFIGURED_SAKURA_TREE);
            }
            if (BambooGroveConfig.spawnToriiGates.get()) {
                //event.getGeneration().getStructures().add(() -> ExplorerConfiguredStructures.CONFIGURED_TORII_GATE);
            }
        }
    }
    //Create the configurations here
    public static final class Configs {
        public static final TreeConfiguration BAMBOO_TREE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                new SimpleStateProvider(BambooGroveBlocks.BAMBOO_LOG.get().defaultBlockState()),
                new BambooTruckPlacer(12, 10, 0),
                new SimpleStateProvider(BambooGroveBlocks.BAMBOO_LEAVES.get().defaultBlockState()),
                new SimpleStateProvider(BambooGroveBlocks.BAMBOO_SAPLING.get().defaultBlockState()),
                new BambooFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), 6),
                new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build();

        public static final TreeConfiguration CHERRY_TREE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                new SimpleStateProvider(BambooGroveBlocks.CHERRY_LOG.get().defaultBlockState()),
                new FancyTrunkPlacer(6, 6, 0),
                new SimpleStateProvider(BambooGroveBlocks.CHERRY_LEAVES.get().defaultBlockState()),
                new SimpleStateProvider(BambooGroveBlocks.CHERRY_SAPLING.get().defaultBlockState()),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().build();

        public static final TreeConfiguration MAPLE_TREE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                new SimpleStateProvider(BambooGroveBlocks.MAPLE_LOG.get().defaultBlockState()),
                new ForkingTrunkPlacer(4, 2, 2),
                new SimpleStateProvider(BambooGroveBlocks.MAPLE_LEAVES.get().defaultBlockState()),
                new SimpleStateProvider(BambooGroveBlocks.MAPLE_SAPLING.get().defaultBlockState()),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build();

        public static final TreeConfiguration ASH_TREE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                new SimpleStateProvider(CymruBlocks.ASH_LOG.get().defaultBlockState()),
                new AshTreeTrunkPlacer(20, 2, 0),
                new SimpleStateProvider(CymruBlocks.ASH_LEAVES.get().defaultBlockState()),
                new SimpleStateProvider(CymruBlocks.ASH_SAPLING.get().defaultBlockState()),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(4)))).ignoreVines().build();

        public static final OreConfiguration JADE_ORE_CONFIG = new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, BambooGroveBlocks.JADE_ORE.get().defaultBlockState(), 8);
        public static final OreConfiguration RUBY_ORE_CONFIG = new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, VanillaTweaksBlocks.RUBY_ORE.get().defaultBlockState(), 8);
        public static final OreConfiguration AMETHYST_ORE_CONFIG = new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, VanillaTweaksBlocks.AMETHYST_ORE.get().defaultBlockState(), 8);
        public static final RandomPatchConfiguration SNOWDONIA_FLOWER_CONFIG = (new RandomPatchConfiguration.GrassConfigurationBuilder((new SnowdoniaFlowerBlockStateProvider(CymruBlocks.DAFFODIL.get().defaultBlockState())), SimpleBlockPlacer.INSTANCE)).tries(64).build();
        public static final RandomPatchConfiguration SNOWDONIA_WILD_LEEKS_CONFIG = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(CymruBlocks.LEEK_WILD.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).noProjection().build();

    }


    //Created the configured featered with the feature/config and register it
    public static final class Configured {
        public static final ConfiguredFeature<?, ?> BAMBOO_TREE = ExplorerFeature.BAMBOO_TREE.get().configured(Configs.BAMBOO_TREE_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(10, 0.1F, 1)));
        public static final ConfiguredFeature<?, ?> CHERRY_TREE = Feature.TREE.configured(Configs.CHERRY_TREE_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(10, 0.1F, 1)));
        public static final ConfiguredFeature<?, ?> MAPLE_TREE = ExplorerFeature.MAPLE_TREE.get().configured(Configs.MAPLE_TREE_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(10, 0.01F, 1)));
        public static final ConfiguredFeature<?, ?> JADE_ORE = Feature.ORE.configured(Configs.JADE_ORE_CONFIG).rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(20)).squared();
        public static final ConfiguredFeature<?, ?> AMETHYST_ORE = Feature.ORE.configured(Configs.AMETHYST_ORE_CONFIG).rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(40)).squared();
        public static final ConfiguredFeature<?, ?> RUBY_ORE = Feature.ORE.configured(Configs.RUBY_ORE_CONFIG).rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(20)).squared();
        public static final ConfiguredFeature<?, ?> RICE_PADDY = ExplorerFeature.RICE_PADDY.get().configured(NoneFeatureConfiguration.NONE).decorated(Features.Decorators.HEIGHTMAP_TOP_SOLID);
        public static final ConfiguredFeature<?, ?> SLIMEY_CHUNK_SWAMP = ExplorerFeature.SLIMEY_CHUNK.get().configured(NoneFeatureConfiguration.NONE).rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(40)).squared().count(33);
        public static final ConfiguredFeature<?, ?> SLIMEY_CHUNK_GLOBAL = ExplorerFeature.SLIMEY_CHUNK.get().configured(NoneFeatureConfiguration.NONE).rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(40)).squared().count(20);
        public static final ConfiguredFeature<?, ?> NOCTILUCAS = ExplorerFeature.NOCTILUCAS.get().configured(new CountConfiguration(80)).decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP_SQUARE).countRandom(16);
        public static final ConfiguredFeature<?, ?> MARBLE_MOUNTAIN = Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, VanillaTweaksBlocks.MARBLE.get().defaultBlockState(), VanillaTweaksConfig.marbleVeinSizeInForestedMountains.get())).rangeUniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(150)).squared().count(VanillaTweaksConfig.marbleChanceInForestedMountains.get());
        public static final ConfiguredFeature<?, ?> MARBLE_GENERAL = Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, VanillaTweaksBlocks.MARBLE.get().defaultBlockState(), VanillaTweaksConfig.marbleVeinSizeInOverworld.get())).rangeUniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(40)).squared().count(VanillaTweaksConfig.marbleChanceInOverworld.get());
        public static final ConfiguredFeature<?, ?> BASALT_GENERAL = Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, VanillaTweaksBlocks.BASALT.get().defaultBlockState(), VanillaTweaksConfig.basaltVeinSizeInOverworld.get())).decorated(FeatureDecorator.RANGE.configured(Features.Decorators.RANGE_BOTTOM_TO_60)).squared().count(VanillaTweaksConfig.basaltChanceInOverworld.get());
        public static final ConfiguredFeature<?, ?> ORE_COAL_EXTRA = Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, COAL_ORE, 9)).rangeUniform(VerticalAnchor.absolute(30), VerticalAnchor.absolute(80)).squared().count(20);
        public static final ConfiguredFeature<?, ?> SLATE_GEN = Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, CymruBlocks.SLATE.get().defaultBlockState(), CymruConfig.slateVeinSizeInSnowdonia.get())).rangeUniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(150)).squared().count(CymruConfig.slateChanceInSnowdonia.get());
        public static final ConfiguredFeature<?, ?> SNOWDONIA_OAK = Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(Features.OAK_BEES_005.weighted(0.2F), Features.FANCY_OAK_BEES_002.weighted(0.1F)), Features.OAK_BEES_002)).decorated(Decorators.WORLD_SURFACE_LOWER_THAN_100).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(15, 0.1F, 1)));
        public static final ConfiguredFeature<?, ?> SNOWDONIA_BIRCH =  Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(Features.SUPER_BIRCH_BEES_0002.weighted(0.5F)), Features.BIRCH_BEES_0002)).decorated(Decorators.WORLD_SURFACE_LOWER_THAN_100).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(10, 0.5F, 1)));
        public static final ConfiguredFeature<?, ?> SNOWDONIA_FLOWERS = Feature.FLOWER.configured(Configs.SNOWDONIA_FLOWER_CONFIG).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(2);
        public static final ConfiguredFeature<?, ?> SNOWDONIA_WILD_LEEKS =  Feature.RANDOM_PATCH.configured(Configs.SNOWDONIA_WILD_LEEKS_CONFIG).decorated(Decorators.WORLD_SURFACE_HIGHER_THAN_100).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).rarity(10);
        public static final ConfiguredFeature<?, ?> ASH_TREE = ExplorerFeature.ASH_TREE.get().configured(Configs.ASH_TREE_CONFIG).decorated(Decorators.WORLD_SURFACE_HIGHER_THAN_100).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1F, 1)));

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

        private static <FC extends FeatureConfiguration> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Explorercraft.MOD_ID, name), configuredFeature);
        }

    }

    public static final class Decorators {
        public static final ConfiguredDecorator<HeightmapConfiguration> WORLD_SURFACE_HIGHER_THAN_100 = ExplorerPlacement.HIGHER_THAN_100.get().configured(new HeightmapConfiguration(Heightmap.Types.WORLD_SURFACE_WG));
        public static final ConfiguredDecorator<HeightmapConfiguration> WORLD_SURFACE_LOWER_THAN_100 = ExplorerPlacement.LOWER_THAN_100.get().configured(new HeightmapConfiguration(Heightmap.Types.WORLD_SURFACE_WG));
    }

}
