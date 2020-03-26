package com.zathrox.explorercraft.common.world.biome;

import com.google.common.collect.ImmutableList;
import com.zathrox.explorercraft.common.world.ExplorerFeature;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BambooGroveBiome extends Biome {

    public BambooGroveBiome(){
        super((new Builder()).surfaceBuilder(SurfaceBuilder.GIANT_TREE_TAIGA, SurfaceBuilder.GRASS_DIRT_SAND_CONFIG).precipitation(RainType.RAIN).category(Category.FOREST).depth(-0.08F).scale(0.15F).temperature(0.95F).downfall(0.9F).waterColor(4159204).waterFogColor(329011).parent((String)null));

        //this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(new Feature[]{ExplorerFeature.BAMBOO_TREE, ExplorerFeature.BAMBOO_TREE, ExplorerFeature.BAMBOO_TREE}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG}, new float[]{0.90F, 0.05F, 0.1F}, ExplorerFeature.BAMBOO_TREE, IFeatureConfig.NO_FEATURE_CONFIG)).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(30, 0.1F, 1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(ExplorerFeature.BAMBOO_TREE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_227227_a_(0.90F), ExplorerFeature.CHERRY_TREE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_227227_a_(0.01F), ExplorerFeature.MAPLE_TREE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_227227_a_(0.01F)), ExplorerFeature.BAMBOO_TREE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG))).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(30, 0.1F, 1))));

/*        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ExplorerFeature.BAMBOO_TREE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(30, 0.1F, 1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ExplorerFeature.CHERRY_TREE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(1, 0.0F, 0))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ExplorerFeature.MAPLE_TREE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(1, 0.0F, 0))));*/
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,Feature.JUNGLE_GROUND_BUSH.withConfiguration(DefaultBiomeFeatures.JUNGLE_GROUND_BUSH_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(1, 0.0F, 0))));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ExplorerFeature.RICE_PADDY.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(new NoPlacementConfig())));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.LILY_PAD_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(4))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.withConfiguration(new SeaGrassConfig(48, 0.3D)).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        DefaultBiomeFeatures.addDoubleFlowers(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addSedimentDisks(this);
        DefaultBiomeFeatures.addBamboo(this);
        DefaultBiomeFeatures.addDefaultFlowers(this);
        DefaultBiomeFeatures.addGrass(this);
        DefaultBiomeFeatures.addMushrooms(this);
        DefaultBiomeFeatures.addReedsAndPumpkins(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
        this.getRiver();

        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PIG, 10, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.COW, 8, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PANDA, 1, 1, 2));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 5, 1, 1));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getGrassColor(double posX, double posZ) {
        int i = super.getGrassColor(posX, posZ);
        return (i) + 9430372 >> 1;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getFoliageColor() {
        return 9430372;
    }
}
