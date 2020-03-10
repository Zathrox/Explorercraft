package com.zathrox.explorercraft.common.world;

import com.zathrox.explorercraft.core.config.OreGenConfig;
import com.zathrox.explorercraft.core.registry.ExplorerBiomes;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import javafx.geometry.Side;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.registries.ForgeRegistries;

import java.beans.EventHandler;
import java.util.Random;

public class OreGeneration {

    public static void setup()
    {
        for(Biome biome : ForgeRegistries.BIOMES)
        {
            //CountRangeConfig explorer_ore_placement = new CountRangeConfig(100, 20, 20, 100); //chance, min height, max height base, max height
            if(OreGenConfig.spawnAmethyst.get())biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ExplorerBlocks.AMETHYST_ORE.getDefaultState(), 5), Placement.COUNT_RANGE, new CountRangeConfig(3, 5, 0, 40)));
            if(OreGenConfig.spawnBasalt.get()) biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ExplorerBlocks.BASALT.getDefaultState(), OreGenConfig.basaltVeinSize.get()), Placement.COUNT_RANGE, new CountRangeConfig(OreGenConfig.basaltChance.get(), 30, 0, 60)));
            if(OreGenConfig.spawnMarble.get()) biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ExplorerBlocks.MARBLE.getDefaultState(), OreGenConfig.marbleVeinSize.get()), Placement.COUNT_RANGE, new CountRangeConfig(OreGenConfig.marbleChance.get(), 5, 0, 40)));
            if (biome == Biomes.DEEP_OCEAN || biome == Biomes.DEEP_COLD_OCEAN || biome == Biomes.DEEP_FROZEN_OCEAN || biome == Biomes.DEEP_LUKEWARM_OCEAN || biome == Biomes.DEEP_WARM_OCEAN) {
                biome.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ExplorercraftFeatureList.NOCTILUCA, new CountConfig(20), Placement.CHANCE_TOP_SOLID_HEIGHTMAP, new ChanceConfig(16)));
            }
            if(OreGenConfig.spawnJade.get() && biome == ExplorerBiomes.BAMBOO_FOREST) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ExplorerBlocks.JADE_ORE.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(3, 0, 0, 40)));
            }

            if(OreGenConfig.spawnRuby.get() && biome == ExplorerBiomes.FORESTED_MOUNTAIN) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ExplorerBlocks.RUBY_ORE.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(2, 0, 0, 50)));
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ExplorerBlocks.RUBY_ORE.getDefaultState(), 4), Placement.COUNT_RANGE, new CountRangeConfig(10, 70, 0, 200)));
            }

            if(OreGenConfig.spawnSpawnChunkCaves.get()) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ExplorercraftFeatureList.SLIME_BLOCK, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_RANGE, new CountRangeConfig(33, 5, 0, 40)));
            }

            //biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ExplorerBlocks.JADE_ORE.getDefaultState(), 20), Placement.COUNT_RANGE, new CountRangeConfig(20, 5, 0, 100)));
            //biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ExplorerBlocks.RUBY_ORE.getDefaultState(), 20), Placement.COUNT_RANGE, new CountRangeConfig(20, 5, 0, 100)));
        }

        //Biome biome = Biomes.BEACH;


    }

    public static boolean spawnBlocksInSlimeChuck(IWorld world, BlockPos pos, Random randomIn) {
        if (world.getWorldInfo().getGenerator().handleSlimeSpawnReduction(randomIn, world) && randomIn.nextInt(4) != 1) {
            return false;
        } else {
            ChunkPos chunkpos = new ChunkPos(pos);
            boolean flag = SharedSeedRandom.seedSlimeChunk(chunkpos.x, chunkpos.z, world.getSeed(), 987234911L).nextInt(10) == 0;
            if (randomIn.nextInt(10) == 0 && flag && pos.getY() < 40) {
                world.getBiome(pos).addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.SLIME_BLOCK.getDefaultState(), 100), Placement.COUNT_RANGE, new CountRangeConfig(2, 0, 0, 70)));
                return true;
            }
            return false;
        }
    }




}
