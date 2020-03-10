package com.zathrox.explorercraft.common.world;

import com.zathrox.explorercraft.common.world.feature.*;
import com.zathrox.explorercraft.common.world.feature.structure.SlateDungeonFeature;
import com.zathrox.explorercraft.common.world.feature.structure.TestFeature;
import com.zathrox.explorercraft.common.world.feature.structure.TestStructure;
import com.zathrox.explorercraft.common.world.feature.structure.WizardTowerFeature;
import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.Structures;
import net.minecraft.world.gen.placement.CountConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Locale;

public class ExplorercraftFeatureList {

    public static final FlowersFeature SNOWDONIA_FLOWERS = new SnowdoniaFlowersFeature(NoFeatureConfig::deserialize);
    public static final FlowersFeature LUPINE_FLOWERS = new LupineFlowerFeature(NoFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> WILD_LEEK = new WildLeekFeature(NoFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> SLIME_BLOCK = new SlimeBlockFeature(NoFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> ASH_TREE = new AshTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> BAMBOO_TREE = new BambooTreeFeature(NoFeatureConfig::deserialize, false, true);
    public static final Feature<NoFeatureConfig> CHERRY_TREE = new KwanzanCherryTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> MAPLE_TREE = new JapaneseMapleTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> TALL_DARK_OAK_TREE = new TallDarkOakTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> WILLOW_TREE = new WillowTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<DoublePlantConfig> TALL_CATTAIL =  new TallCattailFeature(DoublePlantConfig::deserialize);

    public static final Feature<NoFeatureConfig> RICE_PADDY = new RicePaddyFeature(NoFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> LOTUS_FLOWERS = new LotusFlowerFeature(NoFeatureConfig::deserialize);
    public static final Feature<CountConfig> NOCTILUCA = new NoctilucaFeature(CountConfig::deserialize);
    public static final Feature<NoFeatureConfig> WIZARD_TOWER = new WizardTowerFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> SLATE_DUNGEON = new SlateDungeonFeature(NoFeatureConfig::deserialize, false);

    public static final SurfaceBuilder<SurfaceBuilderConfig> MUD_SURFACE_BUILDER = new MudSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static final SurfaceBuilderConfig MUD_SURFACE = new SurfaceBuilderConfig(ExplorerBlocks.MUD.getDefaultState(), ExplorerBlocks.MUD.getDefaultState(), ExplorerBlocks.MUD.getDefaultState());

    //public static final Feature<NoFeatureConfig> TEST2 = new TestFeature(NoFeatureConfig::deserialize, false);
/*    public static final Structure<NoFeatureConfig> TEST  = (Structure<NoFeatureConfig>) Structures.register("Test", new TestStructure(NoFeatureConfig::deserialize));

    public static void registerFeatures(IForgeRegistry<Feature<?>> registry) {

        registry.register(WIZARD_TOWER.setRegistryName(Explorercraft.MOD_ID, "wizard_tower"));
        registry.register(TEST.setRegistryName(Explorercraft.MOD_ID, "test"));
        Feature.STRUCTURES.put("Wizard_Tower".toLowerCase(Locale.ROOT), (Structure<?>) WIZARD_TOWER);
        Feature.STRUCTURES.put("Test".toLowerCase(Locale.ROOT), TEST);

    }*/
}
