package com.zathrox.explorercraft.common.world;

import com.zathrox.explorercraft.core.config.OreGenConfig;
import com.zathrox.explorercraft.core.registry.ExplorerBiomes;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {

    public static void setup()
    {
        for(Biome biome : ForgeRegistries.BIOMES)
        {
            //CountRangeConfig explorer_ore_placement = new CountRangeConfig(100, 20, 20, 100); //chance, min height, max height base, max height
            if(OreGenConfig.spawnAmethyst.get())biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ExplorerBlocks.AMETHYST_ORE.getDefaultState(), 5), Placement.COUNT_RANGE, new CountRangeConfig(4, 5, 0, 40)));
            if(OreGenConfig.spawnBasalt.get()) biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ExplorerBlocks.BASALT.getDefaultState(), OreGenConfig.basaltVeinSize.get()), Placement.COUNT_RANGE, new CountRangeConfig(OreGenConfig.basaltChance.get(), 30, 0, 60)));
            if(OreGenConfig.spawnMarble.get()) biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ExplorerBlocks.MARBLE.getDefaultState(), OreGenConfig.marbleVeinSize.get()), Placement.COUNT_RANGE, new CountRangeConfig(OreGenConfig.marbleChance.get(), 5, 0, 40)));
            if (biome == Biomes.DEEP_OCEAN || biome == Biomes.DEEP_COLD_OCEAN || biome == Biomes.DEEP_FROZEN_OCEAN || biome == Biomes.DEEP_LUKEWARM_OCEAN || biome == Biomes.DEEP_WARM_OCEAN) {
                biome.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ExplorercraftFeatureList.NOCTILUCA, new CountConfig(20), Placement.CHANCE_TOP_SOLID_HEIGHTMAP, new ChanceConfig(16)));
            }
            if(OreGenConfig.spawnJade.get() && biome == ExplorerBiomes.BAMBOO_FOREST) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ExplorerBlocks.JADE_ORE.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(3, 0, 0, 40)));
            }

            if(OreGenConfig.spawnRuby.get() && biome == ExplorerBiomes.FORESTED_MOUNTAIN) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ExplorerBlocks.RUBY_ORE.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(3, 0, 0, 50)));
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ExplorerBlocks.RUBY_ORE.getDefaultState(), 4), Placement.COUNT_RANGE, new CountRangeConfig(50, 0, 0, 200)));
            }
            //biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ExplorerBlocks.JADE_ORE.getDefaultState(), 20), Placement.COUNT_RANGE, new CountRangeConfig(20, 5, 0, 100)));
            //biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ExplorerBlocks.RUBY_ORE.getDefaultState(), 20), Placement.COUNT_RANGE, new CountRangeConfig(20, 5, 0, 100)));
        }

        //Biome biome = Biomes.BEACH;


    }
}
