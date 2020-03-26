package com.zathrox.explorercraft.common.world;

import com.zathrox.explorercraft.core.config.OreGenConfig;
import com.zathrox.explorercraft.core.registry.ExplorerBiomes;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {

    public static void setup() {
        for(Biome biome : ForgeRegistries.BIOMES)
        {
            //CountRangeConfig explorer_ore_placement = new CountRangeConfig(100, 20, 20, 100); //chance, min height, max height base, max height
            if(OreGenConfig.spawnAmethyst.get())biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration( new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ExplorerBlocks.AMETHYST_ORE.getDefaultState(), 5)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(3, 5, 0, 40))));
            if(OreGenConfig.spawnBasalt.get())biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration( new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ExplorerBlocks.BASALT.getDefaultState(), OreGenConfig.basaltVeinSize.get())).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(OreGenConfig.basaltChance.get(), 30, 0, 60))));
            if(OreGenConfig.spawnMarble.get())biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration( new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ExplorerBlocks.MARBLE.getDefaultState(), OreGenConfig.marbleVeinSize.get())).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(OreGenConfig.marbleChance.get(), 5, 0, 40))));

            if (biome == Biomes.DEEP_OCEAN || biome == Biomes.DEEP_COLD_OCEAN || biome == Biomes.DEEP_FROZEN_OCEAN || biome == Biomes.DEEP_LUKEWARM_OCEAN || biome == Biomes.DEEP_WARM_OCEAN) {
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ExplorerFeature.NOCTILUCA.withConfiguration(new CountConfig(20)).withPlacement(Placement.CHANCE_TOP_SOLID_HEIGHTMAP.configure(new ChanceConfig(16))));
            }
            if(OreGenConfig.spawnJade.get() && biome == ExplorerBiomes.BAMBOO_GROVE.get()) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ExplorerBlocks.JADE_ORE.getDefaultState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(3, 0, 0, 40))));
            }

            if(OreGenConfig.spawnRuby.get() && biome == ExplorerBiomes.FORESTED_MOUNTAIN.get()) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration( new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ExplorerBlocks.RUBY_ORE.getDefaultState(), 4)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 5, 0, 50))));
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration( new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ExplorerBlocks.RUBY_ORE.getDefaultState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 70, 0, 200))));
            }

            if(OreGenConfig.spawnSpawnChunkCaves.get()) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ExplorerFeature.SLIME_BLOCK.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(33, 5, 0, 40))));
            }

            //biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ExplorerBlocks.JADE_ORE.getDefaultState(), 20), Placement.COUNT_RANGE, new CountRangeConfig(20, 5, 0, 100)));
            //biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ExplorerBlocks.RUBY_ORE.getDefaultState(), 20), Placement.COUNT_RANGE, new CountRangeConfig(20, 5, 0, 100)));
        }

        //Biome biome = Biomes.BEACH;


    }


}
