package dev.driscollcreations.explorercraft.bamboogrove.world;

import com.google.common.collect.ImmutableSet;
import dev.driscollcreations.explorercraft.setup.ExplorerSurfaceBuilders;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.MathHelper;
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

    private static int calculateSkyColor(float p_244206_0_) {
        float lvt_1_1_ = p_244206_0_ / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
    private static final Lazy<ConfiguredSurfaceBuilder<SurfaceBuilderConfig>> BAMBOO_GROVE_SURFACE_BUILDER = () -> ExplorerSurfaceBuilders.BAMBOO_GROVE_LOG.get().configured(SurfaceBuilder.CONFIG_OCEAN_SAND);

    public static Biome makeBambooGrove()
    {
        MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();
        DefaultBiomeFeatures.farmAnimals(spawns);
        DefaultBiomeFeatures.commonSpawns(spawns);

        BiomeGenerationSettings.Builder gen = new BiomeGenerationSettings.Builder();
        gen.surfaceBuilder(BAMBOO_GROVE_SURFACE_BUILDER::get);

        DefaultBiomeFeatures.addDefaultOverworldLandStructures(gen);
        gen.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
        DefaultBiomeFeatures.addDefaultCarvers(gen);
        DefaultBiomeFeatures.addDefaultMonsterRoom(gen);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(gen);
        DefaultBiomeFeatures.addDefaultOres(gen);
        DefaultBiomeFeatures.addDefaultSoftDisks(gen);
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.configured(Features.Configs.DEFAULT_FLOWER_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(2));
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_BROWN_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(4));
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_RED_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(4));
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(Features.Configs.SUGAR_CANE_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(10));
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.PUMPKIN.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(32));
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(Features.Configs.DEFAULT_GRASS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(2));
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.configured(new ProbabilityConfig(0.3F)).count(48).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.JUNGLE_BUSH.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.0F, 0))));
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_PAD.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(4));
        DefaultBiomeFeatures.addSurfaceFreezing(gen);

        return new Biome.Builder()
                       .precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(-0.08F).downfall(0.9F).scale(0.15F).temperature(1.5F)
                       .specialEffects(new BiomeAmbience.Builder()
                                           .waterColor(4159204)
                                           .waterFogColor(329011)
                                           .foliageColorOverride(9430372)
                                           .grassColorOverride(9430372)
                                           .fogColor(12638463)
                                           .skyColor(calculateSkyColor(0.2F))
                                           .build())
                       .mobSpawnSettings(spawns.build())
                       .generationSettings(gen.build()).build();
    }

}