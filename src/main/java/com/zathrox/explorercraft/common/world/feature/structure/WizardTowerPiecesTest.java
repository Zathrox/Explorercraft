package com.zathrox.explorercraft.common.world.feature.structure;

import java.util.List;
import java.util.Random;

import com.zathrox.explorercraft.common.world.ExplorerFeature;
import com.zathrox.explorercraft.core.Explorercraft;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class WizardTowerPiecesTest
{
	public static void createPieces(TemplateManager templateManager, BlockPos blockPos, Rotation rotation, List<StructurePiece> components, Random random) {
        ResourceLocation wizardTowerRL = new ResourceLocation(Explorercraft.MOD_ID, "wizard_tower_v2");
		Template template = templateManager.getTemplate(wizardTowerRL);

        if (template == null) {
            System.out.println("Could not find structure at " + new ResourceLocation(Explorercraft.MOD_ID, "structures/wizard_tower_v2"));
            return;
        }
        components.add(new WizardTowerPiecesTest.Piece(templateManager, wizardTowerRL, blockPos, rotation));
	}

	public static class Piece extends TemplateStructurePiece {
		private final ResourceLocation resourceLocation;
		private final Rotation rotation;

	  public Piece(TemplateManager templateManager, ResourceLocation resourceLocation, BlockPos position, Rotation rotation) {
		 super(ExplorerFeature.WIZARD_TOWER_PIECE, 0);
		 
		 this.rotation = rotation;
		 this.templatePosition = position;
		 this.resourceLocation = resourceLocation;
		 this.func_207614_a(templateManager);
	  }

	  public Piece(TemplateManager templateManager, CompoundNBT data) {
		 super(IStructurePieceType.IGLU, data);
		 this.resourceLocation = new ResourceLocation(data.getString("Template"));
		 this.rotation = Rotation.valueOf(data.getString("Rot"));
		 this.func_207614_a(templateManager);
	  }

	  /**
	   * Sets up the placement setting for the pieces
	   */
	  private void func_207614_a(TemplateManager templateManager) {
		 Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
		 PlacementSettings placementSettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(rotation).setIgnoreEntities(false).setChunk(null);
		 this.setup(template, this.templatePosition, placementSettings);
	  }

	  /**
	   * (abstract) Helper method to read subclass data from NBT
	   */
	  protected void readAdditional(CompoundNBT tagCompound) {
		 super.readAdditional(tagCompound);
		 tagCompound.putString("Template", this.resourceLocation.toString());
		 tagCompound.putString("Rot", this.rotation.name());
	  }

	  /**
	   * Fires for each Structure Block that is set to data mode.
	   * Use this by placing a data block name "chest" where you
	   * want a chest and then replace it with a chest here and set
	   * the loottable. This also works for placing mobs, dispensers, 
	   * randomizing blocks, etc...
	   */
	  protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
//		 if ("chest".equals(function)) {
//			worldIn.setBlockState(pos, Blocks.CHEST.getDefaultState(), 3);
//			TileEntity tileentity = worldIn.getTileEntity(pos);
//			if (tileentity instanceof ChestTileEntity) {
//			   ((ChestTileEntity)tileentity).setLootTable(LootTables.CHESTS_IGLOO_CHEST, rand.nextLong());
//			}
//
//		 }
	  }

	  public boolean func_225577_a_(IWorld world, ChunkGenerator<?> chunkGenerator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos) {
		 
		 BlockPos blockposOffset = new BlockPos(3, -4, 2);
		 PlacementSettings placementSettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(rotation).setIgnoreEntities(false).setChunk(null);
		 BlockPos finalBlockpos = this.templatePosition.add(Template.transformedBlockPos(placementSettings, blockposOffset));
		 this.templatePosition = finalBlockpos;
		 
		 //not sure what this does
		 boolean validConstruction = super.func_225577_a_(world, chunkGenerator, random, boundingBox, chunkPos);
		 return validConstruction;
	  }
	}
}
