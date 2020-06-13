package com.zathrox.explorercraft.common.world.feature.structure.test;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.zathrox.explorercraft.common.world.ExplorerStructurePieces;
import com.zathrox.explorercraft.common.world.feature.template.Processors;
import com.zathrox.explorercraft.common.world.feature.template.RedGlassSingleJigsawPiece;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.monster.ShulkerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTables;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WizardTowerPiecesOld {

    public static void func_215139_a(ChunkGenerator<?> generator, TemplateManager templateManager, BlockPos pos, List<StructurePiece> pieces, SharedSeedRandom seedRandom) {
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(Explorercraft.MOD_ID, "wizard_tower_v2"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new RedGlassSingleJigsawPiece(Explorercraft.MOD_ID + ":wizard_tower_v2", Collections.singletonList(Processors.RED_GLASS_AND_STRUCTURE_BLOCK)), 1)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.func_214889_a(new ResourceLocation(Explorercraft.MOD_ID, "wizard_tower_v2"), 7, WizardTowerPiece::new, generator, templateManager, pos, pieces, seedRandom);
    }

    public static class WizardTowerPiece extends AbstractVillagePiece {

        public WizardTowerPiece(TemplateManager templateManager, JigsawPiece jigsawPiece, BlockPos pos, int p_i50560_4_, Rotation rotation, MutableBoundingBox mutableBoundingBox) {
            super(ExplorerStructurePieces.WIZARD_TOWER, templateManager, jigsawPiece, pos.down(4), p_i50560_4_, rotation, mutableBoundingBox);
        }

        public WizardTowerPiece(TemplateManager templateManager, CompoundNBT compoundNBT) {
            super(templateManager, compoundNBT, ExplorerStructurePieces.WIZARD_TOWER);
        }

    }
}
