package com.zathrox.explorercraft.common.world;

import com.zathrox.explorercraft.common.world.feature.structure.test.WizardTowerPiecesOld;
import com.zathrox.explorercraft.common.world.feature.structure.WizardTowerPieces;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

public class ExplorerStructurePieces {

    // Structure Pieces
    public static final IStructurePieceType WIZARD_TOWER = register("wizard_tower", WizardTowerPiecesOld.WizardTowerPiece::new);
    public static final IStructurePieceType WIZARD_TOWER_PIECE  = register("wizard_tower_piece", WizardTowerPieces.Piece::new);

    private static IStructurePieceType register(String key, IStructurePieceType type) {
        return Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(Explorercraft.MOD_ID, key), type);
    }
}
