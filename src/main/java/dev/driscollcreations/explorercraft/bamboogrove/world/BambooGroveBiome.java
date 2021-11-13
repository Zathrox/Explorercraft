package dev.driscollcreations.explorercraft.bamboogrove.world;

import com.google.common.collect.ImmutableSet;
import dev.driscollcreations.explorercraft.setup.ExplorerSurfaceBuilders;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Features;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraftforge.common.util.Lazy;

public class BambooGroveBiome {

    private static int calculateSkyColor(float p_244206_0_) {
        float lvt_1_1_ = p_244206_0_ / 3.0F;
        lvt_1_1_ = Mth.clamp(lvt_1_1_, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
    private static final Lazy<ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration>> BAMBOO_GROVE_SURFACE_BUILDER = () -> ExplorerSurfaceBuilders.BAMBOO_GROVE_LOG.get().configured(SurfaceBuilder.CONFIG_OCEAN_SAND);

    public static Biome makeBambooGrove()
    {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawns);
        BiomeDefaultFeatures.commonSpawns(spawns);

        BiomeGenerationSettings.Builder gen = new BiomeGenerationSettings.Builder();
        gen.surfaceBuilder(BAMBOO_GROVE_SURFACE_BUILDER::get);

        BiomeDefaultFeatures.addDefaultOverworldLandStructures(gen);
        gen.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
        BiomeDefaultFeatures.addDefaultCarvers(gen);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(gen);
        BiomeDefaultFeatures.addDefaultOres(gen);
        BiomeDefaultFeatures.addDefaultSoftDisks(gen);
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Feature.FLOWER.configured(Features.Configs.DEFAULT_FLOWER_CONFIG).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(2));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_BROWN_MUSHROOM.decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).countRandom(4));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_RED_MUSHROOM.decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).countRandom(4));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(Features.Configs.SUGAR_CANE_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(10));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured((new  RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(Blocks.PUMPKIN.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).noProjection().build()).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).countRandom(32));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(Features.Configs.DEFAULT_GRASS_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(2));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.configured(new ProbabilityFeatureConfiguration(0.3F)).count(48).decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP_SQUARE));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.JUNGLE_BUSH.decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(1, 0.0F, 0))));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured((new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(Blocks.LILY_PAD.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(4));
        BiomeDefaultFeatures.addSurfaceFreezing(gen);

        return new Biome.BiomeBuilder()
                       .precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.FOREST).depth(-0.08F).downfall(0.9F).scale(0.15F).temperature(1.5F)
                       .specialEffects(new BiomeSpecialEffects.Builder()
                                           .waterColor(4159204)
                                           .waterFogColor(329011)
                                           .foliageColorOverride(9430372)
                                           .grassColorOverride(14009444)
                                           .fogColor(12638463)
                                           .skyColor(calculateSkyColor(0.2F))
                                           .build())
                       .mobSpawnSettings(spawns.build())
                       .generationSettings(gen.build()).build();
    }

}