package com.zathrox.explorercraft.common.world.feature.structure;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.zathrox.explorercraft.core.Explorercraft;

import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class WizardTowerStructureTest extends Structure<NoFeatureConfig>{

	public WizardTowerStructureTest(Function<Dynamic<?>, ? extends NoFeatureConfig> config){
		super(config);
	}


	/**
	 * Will check to see if the chunk coordinate is valid for spawning by using algorithm that spaces out each valid
	 * position between a range.
	 */
	@Override
	protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ){
		//This will make each valid coordinate for the Wizard Tower be between 4 to 8 chunks from each other
		int maxDistance = 8;
		int minDistance = 4;
		if (minDistance == 0)
		{
			minDistance = 1;
		}
		int k = x + maxDistance * spacingOffsetsX;
		int l = z + maxDistance * spacingOffsetsZ;
		int i1 = k < 0 ? k - maxDistance + 1 : k;
		int j1 = l < 0 ? l - maxDistance + 1 : l;
		int targetChunkX = i1 / maxDistance;
		int targetChunkZ = j1 / maxDistance;
		((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), targetChunkX, targetChunkZ, 54354657); //the number here is a seed modifier. Have this always be unique compared to other structures so the valid chunk position is always different.
		targetChunkX = targetChunkX * maxDistance;
		targetChunkZ = targetChunkZ * maxDistance;
		targetChunkX = targetChunkX + random.nextInt(maxDistance - minDistance);
		targetChunkZ = targetChunkZ + random.nextInt(maxDistance - minDistance);
		return new ChunkPos(targetChunkX, targetChunkZ);
	}


	/**
	 * This determines if the spot given is a valid chunk to spawn in by algorithm and that the biome contains the structure
	 * as well.
	 */
	@Override
	public boolean func_225558_a_(BiomeManager biomeManager, ChunkGenerator<?> chunkGenerator, Random random, int chunkPosX, int chunkPosZ, Biome biome){
		//check if the algorithm says the chunk is a valid spot 
		ChunkPos chunkpos = this.getStartPositionForPosition(chunkGenerator, random, chunkPosX, chunkPosZ, 0, 0);
		if (chunkPosX == chunkpos.x && chunkPosZ == chunkpos.z){
			int landHeight = chunkGenerator.func_222529_a(chunkPosX * 16, chunkPosZ * 16, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES);

			//check if the biome actually has the structure added to it
			//and also checks to make sure one corner of chunk isn't too high (not a perfect check)
			if (chunkGenerator.hasStructure(biome, this) && landHeight <= 100){
				return true;
			}
		}

		return false;
	}


	@Override
	public Structure.IStartFactory getStartFactory(){
		return WizardTowerStructureTest.Start::new;
	}


	@Override
	public String getStructureName(){
		//what the /locate command will see. 
		//If you do not add the mod's mod id, then forge will
		//put it under minecraft's namespace which would make it be "minecraft:wizard_tower_structure"
		return Explorercraft.MOD_ID + ":wizard_tower_structure";
	}


	/**
	 * legacy code that mojang hasn't removed yet. Does nothing
	 */
	@Override
	public int getSize(){
		return 8;
	}

	public static class Start extends StructureStart{
		public Start(Structure<?> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn){
			super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
		}

		/**
		 * Begins construction of the structure layout and everything and passes it into the components variable so that
		 * Minecraft can read the pieces from there and create the full structure piece by piece later.
		 */
		@Override
		public void init(ChunkGenerator<?> chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn){
			int x = chunkX * 16;
			int z = chunkZ * 16;
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)]; //random rotation of the structure
			 
			//not correctly aligned to tower's door. You might have to move this elsewhere or something to make it work 
			int xOffset = 9;
			int zOffset = 5;
			if (rotation == Rotation.CLOCKWISE_90)
			{
				xOffset = -xOffset;
			}
			else if (rotation == Rotation.CLOCKWISE_180)
			{
				xOffset = -xOffset;
				zOffset = -zOffset;
			}
			else if (rotation == Rotation.COUNTERCLOCKWISE_90)
			{
				zOffset = -zOffset;
			}
			int landHeight = chunkGenerator.func_222529_a(x+xOffset, z+zOffset, Heightmap.Type.WORLD_SURFACE);
			System.out.println(x+xOffset +", "+ (z+zOffset));
			
			BlockPos blockpos = new BlockPos(x, landHeight, z); //pass in position so the piece knows where to do the height offset check at
			WizardTowerPiecesTest.createPieces(templateManagerIn, blockpos, rotation, this.components, this.rand); //create the layout of structure
			this.recalculateStructureSize(); //Sets the bounding box correctly to the entire structure now
		}
	}
}
