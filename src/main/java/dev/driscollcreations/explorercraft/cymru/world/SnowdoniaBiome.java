package dev.driscollcreations.explorercraft.cymru.world;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Features;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class SnowdoniaBiome {

    private static int calculateSkyColor(float p_244206_0_) {
        float lvt_1_1_ = p_244206_0_ / 3.0F;
        lvt_1_1_ = Mth.clamp(lvt_1_1_, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    public static Biome makeSnowdoniaBiome()
    {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 20, 5, 10));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 1, 1, 1));
        BiomeDefaultFeatures.commonSpawns(spawns);

        BiomeGenerationSettings.Builder gen = new BiomeGenerationSettings.Builder();
        //gen.surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        BiomeDefaultFeatures.addDefaultOverworldLandStructures(gen);
        gen.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
        BiomeDefaultFeatures.addDefaultCarvers(gen);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(gen);
        BiomeDefaultFeatures.addDefaultOres(gen);
        BiomeDefaultFeatures.addDefaultSoftDisks(gen);

        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_BROWN_MUSHROOM.decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).countRandom(4));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_RED_MUSHROOM.decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).countRandom(4));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(Features.Configs.SUGAR_CANE_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(10));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(Features.Configs.DEFAULT_GRASS_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(2));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.configured(new ProbabilityFeatureConfiguration(0.3F)).count(48).decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP_SQUARE));
        BiomeDefaultFeatures.addSurfaceFreezing(gen);

        return new Biome.BiomeBuilder()
                       .precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.FOREST).depth(-0.15F).downfall(0.8F).scale(1.2F).temperature(0.7F)
                       .specialEffects(new BiomeSpecialEffects.Builder()
                                           .waterColor(4020182)
                                           .waterFogColor(329011)
                                           .foliageColorOverride(39259)
                                           .grassColorOverride(39259)
                                           .fogColor(12638463)
                                           .skyColor(calculateSkyColor(0.2F))
                                           .build())
                       .mobSpawnSettings(spawns.build())
                       .generationSettings(gen.build()).build();
    }

}