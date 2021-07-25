package dev.driscollcreations.explorercraft.cymru.world;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class SnowdoniaBiome {

    private static int calculateSkyColor(float p_244206_0_) {
        float lvt_1_1_ = p_244206_0_ / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    public static Biome makeSnowdoniaBiome()
    {
        MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.SHEEP, 20, 5, 10));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.RABBIT, 4, 2, 3));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.FOX, 1, 1, 1));
        DefaultBiomeFeatures.commonSpawns(spawns);

        BiomeGenerationSettings.Builder gen = new BiomeGenerationSettings.Builder();
        gen.surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        DefaultBiomeFeatures.addDefaultOverworldLandStructures(gen);
        gen.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
        DefaultBiomeFeatures.addDefaultCarvers(gen);
        DefaultBiomeFeatures.addDefaultMonsterRoom(gen);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(gen);
        DefaultBiomeFeatures.addDefaultOres(gen);
        DefaultBiomeFeatures.addDefaultSoftDisks(gen);

        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_BROWN_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(4));
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_RED_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(4));
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(Features.Configs.SUGAR_CANE_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(10));
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(Features.Configs.DEFAULT_GRASS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(2));
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.configured(new ProbabilityConfig(0.3F)).count(48).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));
        DefaultBiomeFeatures.addSurfaceFreezing(gen);

        return new Biome.Builder()
                       .precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(-0.15F).downfall(0.8F).scale(1.2F).temperature(0.7F)
                       .specialEffects(new BiomeAmbience.Builder()
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