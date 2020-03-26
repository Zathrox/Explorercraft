package com.zathrox.explorercraft.common.world;

import com.zathrox.explorercraft.common.world.blockstateProvider.SnowdoniaFlowerBlockStateProvider;
import com.zathrox.explorercraft.common.world.feature.*;
import com.zathrox.explorercraft.common.world.feature.structure.SlateDungeonFeature;
import com.zathrox.explorercraft.common.world.feature.structure.WizardTowerFeature;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.placement.CountConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ExplorerFeature {

    public static final BlockClusterFeatureConfig SNOWDONIA_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SnowdoniaFlowerBlockStateProvider(), new SimpleBlockPlacer())).tries(64).build();
    public static final BlockClusterFeatureConfig GREEN_GLOWSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ExplorerBlocks.GREEN_MUSHROOM.getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig PINK_GLOWSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ExplorerBlocks.PINK_MUSHROOM.getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig LUPINE_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).func_227407_a_(ExplorerBlocks.LUPINE.getDefaultState(), 2), new SimpleBlockPlacer())).tries(64).build();
    public static final BlockClusterFeatureConfig CATTAIL_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ExplorerBlocks.CATTAIL.getDefaultState()), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig TALL_CATTAIL_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ExplorerBlocks.TALL_CATTAIL.getDefaultState()), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();

    public static final Placement<FrequencyConfig> COUNT_HEIGHT_100 = register("count_height_100", new AtHeight100(FrequencyConfig::deserialize));

    public static final Feature<NoFeatureConfig> FANCY_TREE = new ECBigTreeFeature(NoFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> BIRCH_TREE = new ECBirchTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> NORMAL_TREE = new ECTreeFeature(NoFeatureConfig::deserialize);

    public static final Feature<NoFeatureConfig> WILD_LEEK = new WildLeekFeature(NoFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> RICE_PADDY = new RicePaddyFeature(NoFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> LOTUS_FLOWERS = new LotusFlowerFeature(NoFeatureConfig::deserialize);

    public static final Feature<NoFeatureConfig> ASH_TREE = new AshTreeFeature(NoFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> BAMBOO_TREE = new BambooTreeFeature(NoFeatureConfig::deserialize, true);
    public static final Feature<NoFeatureConfig> CHERRY_TREE = new KwanzanCherryTreeFeature(NoFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> MAPLE_TREE = new JapaneseMapleTreeFeature(NoFeatureConfig::deserialize);
    public static final Feature<CountConfig> NOCTILUCA = new NoctilucaFeature(CountConfig::deserialize);

    public static final Feature<NoFeatureConfig> WIZARD_TOWER = new WizardTowerFeature(NoFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> SLATE_DUNGEON = new SlateDungeonFeature(NoFeatureConfig::deserialize);

    public static final Feature<NoFeatureConfig> SLIME_BLOCK = new SlimeBlockFeature(NoFeatureConfig::deserialize);


    public static final Feature<NoFeatureConfig> TALL_DARK_OAK_TREE = new TallDarkOakTreeFeature(NoFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> WILLOW_TREE = new WillowTreeFeature(NoFeatureConfig::deserialize);



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

    private static <T extends IPlacementConfig, G extends Placement<T>> G register(String key, G p_214999_1_) {
        return (G)(Registry.<Placement<?>>register(Registry.DECORATOR, key, p_214999_1_));
    }

}
