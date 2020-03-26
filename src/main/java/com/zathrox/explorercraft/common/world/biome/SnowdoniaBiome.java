package com.zathrox.explorercraft.common.world.biome;

import com.google.common.collect.ImmutableList;
import com.zathrox.explorercraft.common.world.ExplorerFeature;
import com.zathrox.explorercraft.core.config.EntityConfig;
import com.zathrox.explorercraft.core.config.GeneralConfig;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SnowdoniaBiome extends Biome {

    public static final TreeFeatureConfig BIRCHTREE = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.BIRCH_LOG.getDefaultState()), new SimpleBlockStateProvider(Blocks.BIRCH_LEAVES.getDefaultState()), new BlobFoliagePlacer(2, 0))).baseHeight(10).heightRandA(2).foliageHeight(3).ignoreVines().decorators(ImmutableList.of(new BeehiveTreeDecorator(0.02F))).setSapling((net.minecraftforge.common.IPlantable)Blocks.BIRCH_SAPLING).build();

    public SnowdoniaBiome(){
        super((new Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG).precipitation(RainType.RAIN).category(Category.FOREST).depth(-0.15F).scale(1.2F).temperature(0.7F).waterColor(4020182).waterFogColor(329011).downfall(0.8F).parent((String)null));
        this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));

        this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ExplorerFeature.WIZARD_TOWER.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP.configure(new HeightWithChanceConfig(EntityConfig.wizard_weight.get(), 1F))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, ExplorerFeature.SLATE_DUNGEON.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(GeneralConfig.slateDungeonRarity.get()))));

        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ExplorerBlocks.SLATE.getDefaultState(), 33)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 80))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.COAL_ORE.getDefaultState(), 17)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 128))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.getDefaultState(), 9)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 55))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GOLD_ORE.getDefaultState(), 12)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 0, 0, 64))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.REDSTONE_ORE.getDefaultState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 0, 0, 16))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIAMOND_ORE.getDefaultState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 16))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.LAPIS_ORE.getDefaultState(), 7)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(1, 16, 16))));


        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ExplorerFeature.NORMAL_TREE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(100, 64, 0, 40))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ExplorerFeature.BIRCH_TREE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(100, 64, 0, 40))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ExplorerFeature.FANCY_TREE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 64, 0, 40))));

        //this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(Feature.NORMAL_TREE, Feature.FANCY_TREE), new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG}, new float[]{0.8F, 0.5F}, Feature.NORMAL_TREE, IFeatureConfig.NO_FEATURE_CONFIG)).withPlacement(Placement.COUNT_BIASED_RANGE, new CountRangeConfig(400, 1, 0, 100))));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ExplorerFeature.ASH_TREE.withConfiguration( IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(GeneralConfig.ashTreeRarity.get(), 120, 0, 100))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ExplorerFeature.WILD_LEEK.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(5))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(ExplorerFeature.SNOWDONIA_FLOWER_CONFIG).withPlacement(Placement.NOISE_HEIGHTMAP_32.configure(new NoiseDependant(-0.8D, 15, 4))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.withConfiguration(new SeaGrassConfig(48, 0.3D)).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addSedimentDisks(this);
        DefaultBiomeFeatures.addGrass(this);
        DefaultBiomeFeatures.addMushrooms(this);
        DefaultBiomeFeatures.addReedsAndPumpkins(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);

        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SHEEP, 20, 5, 15));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PIG, 2, 1, 4));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.CHICKEN, 2, 1, 4));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.COW, 2, 1, 4));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.RABBIT, 4, 2, 3));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.FOX, 5, 1, 1));
        this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getGrassColor(double posX, double posZ) {
        return 39259;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getFoliageColor() {
        return 39259;
    }

}
