//package dev.driscollcreations.explorercraft.cymru.world;
//
//import com.mojang.serialization.Codec;
//import dev.driscollcreations.explorercraft.Explorercraft;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MutableBoundingBox;
//import net.minecraft.util.registry.DynamicRegistries;
//import net.minecraft.util.registry.Registry;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.gen.ChunkGenerator;
//import net.minecraft.world.gen.GenerationStep;
//import net.minecraft.world.gen.feature.NoneFeatureConfiguration;
//import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
//import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
//import net.minecraft.world.gen.feature.structure.Structure;
//import net.minecraft.world.gen.feature.structure.StructureStart;
//import net.minecraft.world.gen.feature.structure.VillageConfig;
//import net.minecraft.world.gen.feature.template.TemplateManager;
//import org.apache.logging.log4j.Level;
//
//public class SlateDungeonStructure extends Structure<NoneFeatureConfiguration>
//
//    {
//    public SlateDungeonStructure(Codec<NoneFeatureConfiguration> codec) {
//        super(codec);
//    }
//
//    /**
//     * This is how the worldgen code knows what to call when it
//     * is time to create the pieces of the structure for generation.
//     */
//    @Override
//    public  IStartFactory<NoneFeatureConfiguration> getStartFactory() {
//        return SlateDungeonStructure.Start::new;
//    }
//
//
//    /**
//     * Generation stage for when to generate the structure. there are 10 stages you can pick from!
//     * This surface structure stage places the structure before plants and ores are generated.
//     */
//    @Override
//    public GenerationStep.Decoration step() {
//        return GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
//    }
//
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
//            int x = (chunkX << 4) + 7;
//            int z = (chunkZ << 4) + 7;
//
//            BlockPos blockpos = new BlockPos(x, 5, z);
//
//            JigsawManager.addPieces(
//                    dynamicRegistryManager,
//                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(new ResourceLocation(Explorercraft.MOD_ID, "slate_dungeon")), 1),
//                    AbstractVillagePiece::new,
//                    chunkGenerator,
//                    templateManagerIn,
//                    blockpos,
//                    this.pieces,
//                    this.random,
//                    false,
//                    false);
//
//            //this.pieces.forEach(piece -> piece.move(0, 0, 0));
//            this.pieces.forEach(piece -> piece.getBoundingBox().y0 -= 1);
//
//
//            this.calculateBoundingBox();
//            Explorercraft.LOGGER.log(Level.DEBUG, "Slate dungeon at " +
//                                                                  this.pieces.get(0).getBoundingBox().x0 + " " +
//                                                                  this.pieces.get(0).getBoundingBox().y0 + " " +
//                                                                  this.pieces.get(0).getBoundingBox().z0);
//        }
//
//    }
//}
