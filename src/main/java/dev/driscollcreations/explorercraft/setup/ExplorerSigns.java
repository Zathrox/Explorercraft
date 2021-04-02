package dev.driscollcreations.explorercraft.setup;

import net.minecraft.block.BlockState;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.IBlockReader;

public class ExplorerSigns {

    public static class CustomStandingSignBlock extends StandingSignBlock
    {

        public CustomStandingSignBlock(Properties propertiesIn, WoodType woodTypeIn)
        {
            super(propertiesIn, woodTypeIn);
        }

        @Override
        public boolean hasTileEntity(BlockState stateIn)
        {
            return true;
        }

        @Override
        public TileEntity newBlockEntity(IBlockReader worldIn)
        {
            return new CustomSignTileEntity();
        }
    }

    public static class CustomWallSignBlock extends WallSignBlock
    {

        public CustomWallSignBlock(Properties propertiesIn, WoodType woodTypeIn)
        {
            super(propertiesIn, woodTypeIn);
        }

        @Override
        public boolean hasTileEntity(BlockState stateIn)
        {
            return true;
        }

        @Override
        public TileEntity newBlockEntity(IBlockReader worldIn)
        {
            return new CustomSignTileEntity();
        }
    }

    public static class CustomSignTileEntity extends SignTileEntity
    {
        @Override
        public TileEntityType<CustomSignTileEntity> getType()
        {
            return ExplorerTileEntities.EXPLORER_SIGNS.get();
        }
    }

}
