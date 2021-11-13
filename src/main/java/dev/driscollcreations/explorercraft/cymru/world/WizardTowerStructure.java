//package dev.driscollcreations.explorercraft.cymru.world;
//
//import com.mojang.serialization.Codec;
//import dev.driscollcreations.explorercraft.Explorercraft;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.SharedSeedRandom;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.ChunkPos;
//import net.minecraft.util.math.MutableBoundingBox;
//import net.minecraft.util.registry.DynamicRegistries;
//import net.minecraft.util.registry.Registry;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.provider.BiomeProvider;
//import net.minecraft.world.gen.ChunkGenerator;
//import net.minecraft.world.gen.GenerationStep;
//import net.minecraft.world.gen.Heightmap;
//import net.minecraft.world.gen.feature.NoneFeatureConfiguration;
//import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
//import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
//import net.minecraft.world.gen.feature.structure.Structure;
//import net.minecraft.world.gen.feature.structure.StructureStart;
//import net.minecraft.world.gen.feature.structure.VillageConfig;
//import net.minecraft.world.gen.feature.template.TemplateManager;
//import org.apache.logging.log4j.Level;
//
//public class WizardTowerStructure extends Structure<NoneFeatureConfiguration>
//
//{
//    public WizardTowerStructure(Codec<NoneFeatureConfiguration> codec) {
//        super(codec);
//    }
//
//    /**
//     * This is how the worldgen code knows what to call when it
//     * is time to create the pieces of the structure for generation.
//     */
//    @Override
//    public  IStartFactory<NoneFeatureConfiguration> getStartFactory() {
//        return WizardTowerStructure.Start::new;
//    }
//
//
//    /**
//     * Generation stage for when to generate the structure. there are 10 stages you can pick from!
//     * This surface structure stage places the structure before plants and ores are generated.
//     */
//    @Override
//    public GenerationStep.Decoration step() {
//        return GenerationStep.Decoration.SURFACE_STRUCTURES;
//    }
//
//
//
//
//    /**
//     * || ONLY WORKS IN FORGE 34.1.12+ ||
//     *
//     * This method allows us to have mobs that spawn naturally over time in our structure.
//     * No other mobs will spawn in the structure of the same entity classification.
//     * The reason you want to match the classifications is so that your structure's mob
//     * will contribute to that classification's cap. Otherwise, it may cause a runaway
//     * spawning of the mob that will never stop.
//     *
//     * NOTE: getDefaultSpawnList is for monsters only and getDefaultCreatureSpawnList is
//     *       for creatures only. If you want to add entities of another classification,
//     *       use the StructureSpawnListGatherEvent to add water_creatures, water_ambient,
//     *       ambient, or misc mobs. Use that event to add/remove mobs from structures
//     *       that are not your own.
//     *
//     * NOTE 2: getSpawnList and getCreatureSpawnList is the vanilla methods that Forge does
//     *         not hook up. Do not use those methods or else the mobs won't spawn. You would
//     *         have to manually implement spawning for them. Stick with Forge's Default form
//     *         as it is easier to use that.
//     */
//    /*private static final List<MobSpawnSettings.Spawners> STRUCTURE_MONSTERS = ImmutableList.of(
//            new MobSpawnSettings.Spawners(EntityType.ILLUSIONER, 100, 4, 9),
//            new MobSpawnSettings.Spawners(EntityType.VINDICATOR, 100, 4, 9)
//    );*/
//
//    /*@Override
//    public List<MobSpawnSettings.Spawners> getDefaultSpawnList() {
//        return STRUCTURE_MONSTERS;
//    }
//
//    private static final List<MobSpawnSettings.Spawners> STRUCTURE_CREATURES = ImmutableList.of(
//            new MobSpawnSettings.Spawners(EntityType.SHEEP, 30, 10, 15),
//            new MobSpawnSettings.Spawners(EntityType.RABBIT, 100, 1, 2)
//    );
//    @Override
//    public List<MobSpawnSettings.Spawners> getDefaultCreatureSpawnList() {
//        return STRUCTURE_CREATURES;
//    }*/
//
//
//    /*
//     * This is where extra checks can be done to determine if the structure can spawn here.
//     * This only needs to be overridden if you're adding additional spawn conditions.
//     *
//     * Notice how the biome is also passed in. Though, you are not going to
//     * do any biome checking here as you should've added this structure to
//     * the biomes you wanted already with the biome load event.
//     *
//     * Basically, this method is used for determining if the land is at a suitable height,
//     * if certain other structures are too close or not, or some other restrictive condition.
//     *
//     * For example, Pillager Outposts added a check to make sure it cannot spawn within 10 chunk of a Village.
//     * (Bedrock Edition seems to not have the same check)
//     *
//     *
//     * Also, please for the love of god, do not do dimension checking here. If you do and
//     * another mod's dimension is trying to spawn your structure, the locate
//     * command will make minecraft hang forever and break the game.
//     *
//     * Instead, use the addDimensionalSpacing method in StructureTutorialMain class.
//     * If you check for the dimension there and do not add your structure's
//     * spacing into the chunk generator, the structure will not spawn in that dimension!
//     */
//
//    @Override
//    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration featureConfig) {
//        int landHeight = chunkGenerator.getBaseHeight(chunkX << 4, chunkZ << 4, Heightmap.Type.WORLD_SURFACE_WG);
//        return landHeight > 150;
//    }
//
//    /**
//     * Handles calling up the structure's pieces class and height that structure will spawn at.
//     */
//    public static class Start extends StructureStart<NoneFeatureConfiguration> {
//        public Start(Structure<NoneFeatureConfiguration> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
//            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
//        }
//
//        @Override
//        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoneFeatureConfiguration config) {
//
//            // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
//            int x = (chunkX << 4) + 7;
//            int z = (chunkZ << 4) + 7;
//
//            /*
//             * We pass this into addPieces to tell it where to generate the structure.
//             * If addPieces's last parameter is true, blockpos's Y value is ignored and the
//             * structure will spawn at terrain height instead. Set that parameter to false to
//             * force the structure to spawn at blockpos's Y value instead. You got options here!
//             */
//            BlockPos blockpos = new BlockPos(x, 0, z);
//
//            /*
//             * If you are doing Nether structures, you'll probably want to spawn your structure on top of ledges.
//             * Best way to do that is to use getColumnSample to grab a column of blocks at the structure's x/z position.
//             * Then loop through it and look for land with air above it and set blockpos's Y value to it.
//             * Make sure to set the final boolean in JigsawManager.addPieces to false so
//             * that the structure spawns at blockpos's y value instead of placing the structure on the Bedrock roof!
//             */
//            //BlockGetter blockReader = chunkGenerator.getBaseColumn(blockpos.getX(), blockpos.getZ());
//
//            // All a structure has to do is call this method to turn it into a jigsaw based structure!
//            JigsawManager.addPieces(
//                    dynamicRegistryManager,
//                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
//                                                    // The path to the starting Template Pool JSON file to read.
//                                                    //
//                                                    // Note, this is "structure_tutorial:run_down_house/start_pool" which means
//                                                    // the game will automatically look into the following path for the template pool:
//                                                    // "resources/data/structure_tutorial/worldgen/template_pool/run_down_house/start_pool.json"
//                                                    // This is why your pool files must be in "data/<modid>/worldgen/template_pool/<the path to the pool here>"
//                                                    // because the game automatically will check in worldgen/template_pool for the pools.
//                                                    .get(new ResourceLocation(Explorercraft.MOD_ID, "wizard_tower")),
//
//                            // How many pieces outward from center can a recursive jigsaw structure spawn.
//                            // Our structure is only 1 piece outward and isn't recursive so any value of 1 or more doesn't change anything.
//                            // However, I recommend you keep this a decent value like 10 so people can use datapacks to add additional pieces to your structure easily.
//                            // But don't make it too large for recursive structures like villages or you'll crash server due to hundreds of pieces attempting to generate!
//                            10),
//                    AbstractVillagePiece::new,
//                    chunkGenerator,
//                    templateManagerIn,
//                    blockpos, // Position of the structure. Y value is ignored if last parameter is set to true.
//                    this.pieces, // The list that will be populated with the jigsaw pieces after this method.
//                    this.random,
//                    false, // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
//                    // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
//                    true);  // Place at heightmap (top land). Set this to false for structure to be place at the passed in blockpos's Y value instead.
//            // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
//
//
//            // **THE FOLLOWING TWO LINES ARE OPTIONAL**
//            //
//            // Right here, you can do interesting stuff with the pieces in this.components such as offset the
//            // center piece by 50 blocks up for no reason, remove repeats of a piece or add a new piece so
//            // only 1 of that piece exists, etc. But you do not have access to the piece's blocks as this list
//            // holds just the piece's size and positions. Blocks will be placed later in JigsawManager.
//            //
//            // In this case, we do `piece.offset` to raise pieces up by 1 block so that the house is not right on
//            // the surface of water or sunken into land a bit.
//            //
//            // Then we extend the bounding box down by 1 by doing `piece.getBoundingBox().minY` which will cause the
//            // land formed around the structure to be lowered and not cover the doorstep. You can raise the bounding
//            // box to force the structure to be buried as well. This bounding box stuff with land is only for structures
//            // that you added to Structure.NOISE_AFFECTING_FEATURES field handles adding land around the base of structures.
//            //
//            // By lifting the house up by 1 and lowering the bounding box, the land at bottom of house will now be
//            // flush with the surrounding terrain without blocking off the doorstep.
//            this.pieces.forEach(piece -> piece.move(0, 1, 0));
//            this.pieces.forEach(piece -> piece.getBoundingBox().y0 -= 1);
//
//
//            // Sets the bounds of the structure once you are finished.
//            this.calculateBoundingBox();
//
//            // I use to debug and quickly find out if the structure is spawning or not and where it is.
//            // This is returning the coordinates of the center starting piece.
//            Explorercraft.LOGGER.log(Level.DEBUG, "Wizard tower at " +
//                                                          this.pieces.get(0).getBoundingBox().x0 + " " +
//                                                          this.pieces.get(0).getBoundingBox().y0 + " " +
//                                                          this.pieces.get(0).getBoundingBox().z0);
//        }
//
//    }
//}
