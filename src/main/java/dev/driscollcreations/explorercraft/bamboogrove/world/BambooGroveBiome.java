package dev.driscollcreations.explorercraft.bamboogrove.world;

import com.google.common.collect.ImmutableSet;
import dev.driscollcreations.explorercraft.setup.ExplorerSurfaceBuilders;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BambooGroveBiome {

    private static final Method GET_SKY_COLOR_WITH_TEMPERATURE_MODIFIER = ObfuscationReflectionHelper.findMethod(BiomeMaker.class, "getSkyColorWithTemperatureModifier", float.class);
    private static final Lazy<ConfiguredSurfaceBuilder<SurfaceBuilderConfig>> BAMBOO_GROVE_SURFACE_BUILDER = () -> ExplorerSurfaceBuilders.BAMBOO_GROVE_LOG.get().func_242929_a(SurfaceBuilder.GRASS_DIRT_SAND_CONFIG);

    public static Biome makeBambooGrove()
    {
        MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();
        DefaultBiomeFeatures.withPassiveMobs(spawns);
        DefaultBiomeFeatures.withBatsAndHostiles(spawns);

        BiomeGenerationSettings.Builder gen = new BiomeGenerationSettings.Builder();
        gen.withSurfaceBuilder(BAMBOO_GROVE_SURFACE_BUILDER::get);

        DefaultBiomeFeatures.withStrongholdAndMineshaft(gen);
        gen.withStructure(StructureFeatures.RUINED_PORTAL);
        DefaultBiomeFeatures.withCavesAndCanyons(gen);
        DefaultBiomeFeatures.withMonsterRoom(gen);
        DefaultBiomeFeatures.withCommonOverworldBlocks(gen);
        DefaultBiomeFeatures.withOverworldOres(gen);
        DefaultBiomeFeatures.withDisks(gen);
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(Features.Configs.NORMAL_FLOWER_CONFIG).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(2));
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_BROWN_MUSHROOM.withPlacement(Features.Placements.PATCH_PLACEMENT).chance(4));
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_RED_MUSHROOM.withPlacement(Features.Placements.PATCH_PLACEMENT).chance(4));
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(Features.Configs.SUGAR_CANE_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).count(10));
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.PUMPKIN.getDefaultState()), SimpleBlockPlacer.PLACER)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).preventProjection().build()).withPlacement(Features.Placements.PATCH_PLACEMENT).chance(32));
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(Features.Configs.GRASS_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).count(2));
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.withConfiguration(new ProbabilityConfig(0.3F)).count(48).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.JUNGLE_BUSH.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.0F, 0))));
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_PAD.getDefaultState()), SimpleBlockPlacer.PLACER)).tries(10).build()).withPlacement(Features.Placements.PATCH_PLACEMENT).count(4));
        DefaultBiomeFeatures.withFrozenTopLayer(gen);

        final int skyColour;
        try {
            skyColour = (int) GET_SKY_COLOR_WITH_TEMPERATURE_MODIFIER.invoke(null, 2);
        } catch (final IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Unable to get sky colour", e);
        }

        return new Biome.Builder()
                       .precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(-0.08F).downfall(0.9F).scale(0.15F).temperature(1.5F)
                       .setEffects(new BiomeAmbience.Builder()
                                           .setWaterColor(4159204)
                                           .setWaterFogColor(329011)
                                           .withFoliageColor(9430372)
                                           .withGrassColor(9430372)
                                           .setFogColor(12638463)
                                           .withSkyColor(skyColour)
                                           .build())
                       .withMobSpawnSettings(spawns.build())
                       .withGenerationSettings(gen.build()).build();
    }

}