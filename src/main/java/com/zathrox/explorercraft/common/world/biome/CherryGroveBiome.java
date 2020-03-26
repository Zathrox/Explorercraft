package com.zathrox.explorercraft.common.world.biome;

import com.zathrox.explorercraft.common.world.ExplorerFeature;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CherryGroveBiome extends Biome {

    public CherryGroveBiome() {
        super((new Builder()).surfaceBuilder(SurfaceBuilder.GIANT_TREE_TAIGA, SurfaceBuilder.GRASS_DIRT_SAND_CONFIG).precipitation(RainType.RAIN).category(Category.FOREST).depth(0.2F).scale(0.2F).temperature(0.95F).downfall(0.9F).waterColor(4159204).waterFogColor(329011).parent("bamboo_grove"));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ExplorerFeature.CHERRY_TREE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ExplorerFeature.MAPLE_TREE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ExplorerFeature.RICE_PADDY.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(new NoPlacementConfig())));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PANDA, 1, 1, 2));

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
    }

    @Override
    public boolean isMutation() {
        return true;
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
