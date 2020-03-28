package com.zathrox.explorercraft.common.world;

import java.util.Locale;

import com.zathrox.explorercraft.common.world.blockstateProvider.SnowdoniaFlowerBlockStateProvider;
import com.zathrox.explorercraft.common.world.feature.AshTreeFeature;
import com.zathrox.explorercraft.common.world.feature.BambooTreeFeature;
import com.zathrox.explorercraft.common.world.feature.ECBigTreeFeature;
import com.zathrox.explorercraft.common.world.feature.ECBirchTreeFeature;
import com.zathrox.explorercraft.common.world.feature.ECTreeFeature;
import com.zathrox.explorercraft.common.world.feature.JapaneseMapleTreeFeature;
import com.zathrox.explorercraft.common.world.feature.KwanzanCherryTreeFeature;
import com.zathrox.explorercraft.common.world.feature.LotusFlowerFeature;
import com.zathrox.explorercraft.common.world.feature.MudSurfaceBuilder;
import com.zathrox.explorercraft.common.world.feature.NoctilucaFeature;
import com.zathrox.explorercraft.common.world.feature.RicePaddyFeature;
import com.zathrox.explorercraft.common.world.feature.SlimeBlockFeature;
import com.zathrox.explorercraft.common.world.feature.TallDarkOakTreeFeature;
import com.zathrox.explorercraft.common.world.feature.WildLeekFeature;
import com.zathrox.explorercraft.common.world.feature.WillowTreeFeature;
import com.zathrox.explorercraft.common.world.feature.structure.SlateDungeonFeature;
import com.zathrox.explorercraft.common.world.feature.structure.WizardTowerFeature;
import com.zathrox.explorercraft.common.world.feature.structure.WizardTowerPiecesTest;
import com.zathrox.explorercraft.common.world.feature.structure.WizardTowerStructureTest;
import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.CountConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ExplorerFeature {

    //Surfacebuilders
    public static final SurfaceBuilder<SurfaceBuilderConfig> MUD_SURFACE_BUILDER = new MudSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static final SurfaceBuilderConfig MUD_SURFACE = new SurfaceBuilderConfig(ExplorerBlocks.MUD.getDefaultState(), ExplorerBlocks.MUD.getDefaultState(), ExplorerBlocks.MUD.getDefaultState());

	public static void surfacebuilderRegistering(final RegistryEvent.Register<SurfaceBuilder<?>> event){
		IForgeRegistry<SurfaceBuilder<?>> registry = event.getRegistry();
		register(registry, MUD_SURFACE_BUILDER, "mud_surface_builder");
	}
	
	
    //Placement
    public static final Placement<FrequencyConfig> COUNT_HEIGHT_100 = register("count_height_100", new AtHeight100(FrequencyConfig::deserialize));

    private static <T extends IPlacementConfig, G extends Placement<T>> G register(String key, G p_214999_1_) {
        return (G)(Registry.<Placement<?>>register(Registry.DECORATOR, key, p_214999_1_));
    }

    
	//Feature configs
    public static final BlockClusterFeatureConfig SNOWDONIA_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SnowdoniaFlowerBlockStateProvider(), new SimpleBlockPlacer())).tries(64).build();
    public static final BlockClusterFeatureConfig GREEN_GLOWSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ExplorerBlocks.GREEN_MUSHROOM.getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig PINK_GLOWSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ExplorerBlocks.PINK_MUSHROOM.getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig LUPINE_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).func_227407_a_(ExplorerBlocks.LUPINE.getDefaultState(), 2), new SimpleBlockPlacer())).tries(64).build();
    public static final BlockClusterFeatureConfig CATTAIL_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ExplorerBlocks.CATTAIL.getDefaultState()), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig TALL_CATTAIL_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ExplorerBlocks.TALL_CATTAIL.getDefaultState()), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
    
    
    //Features & Structures
	public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, Explorercraft.MOD_ID);

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

    
    
	//feature registering
	public static void featureRegistering(final RegistryEvent.Register<Feature<?>> event){
		
		IForgeRegistry<Feature<?>> registry = event.getRegistry();
		
		//features
		register(registry, FANCY_TREE, "fancy_tree");
		register(registry, BIRCH_TREE, "birch_tree");
		register(registry, NORMAL_TREE, "normal_tree");

		register(registry, WILD_LEEK, "wild_leek");
		register(registry, RICE_PADDY, "rice_paddy");
		register(registry, LOTUS_FLOWERS, "lotus_flowers");

		register(registry, ASH_TREE, "ash_tree");
		register(registry, BAMBOO_TREE, "bamboo_tree");
		register(registry, CHERRY_TREE, "cherry_tree");
		register(registry, MAPLE_TREE, "maple_tree");
		register(registry, NOCTILUCA, "noctiluca");

		register(registry, WIZARD_TOWER, "wizard_tower");
		register(registry, SLATE_DUNGEON, "slate_dungeon");

		register(registry, SLIME_BLOCK, "slime_block");

		register(registry, TALL_DARK_OAK_TREE, "tall_dark_oak_tree");
		register(registry, WILLOW_TREE, "willow_tree");

		
		
		//Ok so thanks to a Forge oversight, if you want your structure to spawn in 
		//the Overworld in your modded biomes, you need to add it to at least one 
		//vanilla biome as well. From what I can tell, Forge forgot to make a workaround
		//for how vanilla will check if a structure exists in a biomeprovider/chunkgenerator
		//and so it only checks vanilla biomes to see if the world has a structure instead 
		//of all biomes that's actually spawning in the Overwolrd. (this works perfectly fine 
		//in modded worldtypes, dimension, chunkgenerators/biomeproviders, etc. Only an issue in
		//vanilla. Time for me to open up a new Forge issue lol)
		//structures
		register(registry, WIZARD_TOWER_STRUCTURE, "wizard_tower_piece");
		
		//Adds the structure to all biome's feature list. This makes it so the structure
		//does not get cut off if it ends up spawning on the edge of a biome and part of
		//it goes into the other biome that it doesn't have the structure added by .addStructure()
		//
		//To specify a biome for the structure to be able to spawn in, do .addStructure() but again
		//be sure to also do .addFeature() so the biomes can know to actually make the structure's pieces.
		//
		//A way to think about is .addStructure specifies valid biomes to spawn in and .addFeature says 
		//which biomes can actually generate the parts of the structure.
		for(Biome biome : ForgeRegistries.BIOMES){
			biome.addFeature(Decoration.SURFACE_STRUCTURES, WIZARD_TOWER_STRUCTURE.withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG));
		}
		// Add the following to any biome you want to spawn this structure in
		// biome.addStructure(WIZARD_TOWER_STRUCTURE.withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG));
		
		//register the pieces of the structures
		register(WIZARD_TOWER_PIECE, "wizard_tower_piece");
	}

	
	//True Structure test example
	public static final Structure<NoFeatureConfig> WIZARD_TOWER_STRUCTURE = new WizardTowerStructureTest(NoFeatureConfig::deserialize);
	public static IStructurePieceType WIZARD_TOWER_PIECE = WizardTowerPiecesTest.Piece::new;
	
	/**
	 * For registering structure pieces so Minecraft doesn't spam the logs saying the pieces aren't registered yet
	 */
	public static IStructurePieceType register(IStructurePieceType pieceType, String key){
		return Registry.register(Registry.STRUCTURE_PIECE, key.toLowerCase(Locale.ROOT), pieceType);
	}
	
	/**
	 * Helper method to quickly register features, blocks, items, structures, biomes, anything that can be registered.
	 * 
	 * If you want to use the regular registry event, you can use this to quickly register stuff.
	 *     
	 * Example: 
	 * 	public static final RegistryObject<Feature<NoFeatureConfig>> WILLOW_TREE;
	 * 	registeryEvent(event e){
	 * 		WILLOW_TREE =  register(e.registry, WILLOW_TREE, "willow_tree");
	 * 	}	
	 */
	public static <T extends IForgeRegistryEntry<T>> T register(IForgeRegistry<T> registry, T entry, String registryKey){
		entry.setRegistryName(new ResourceLocation(Explorercraft.MOD_ID, registryKey.toLowerCase().replace(' ', '_')));
		registry.register(entry);
		return entry;
	}
}
