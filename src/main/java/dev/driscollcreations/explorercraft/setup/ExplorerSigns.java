package dev.driscollcreations.explorercraft.setup;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ExplorerSigns {

    public static class CustomStandingSignBlock extends StandingSignBlock
    {

        public CustomStandingSignBlock(Properties propertiesIn, WoodType woodTypeIn)
        {
            super(propertiesIn, woodTypeIn);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
            return new CustomSignBlockEntity(blockPos, blockState);
        }
    }

    public static class CustomWallSignBlock extends WallSignBlock
    {

        public CustomWallSignBlock(Properties propertiesIn, WoodType woodTypeIn)
        {
            super(propertiesIn, woodTypeIn);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
            return new CustomSignBlockEntity(blockPos, blockState);
        }
    }

    public static class CustomSignBlockEntity extends SignBlockEntity
    {
        public CustomSignBlockEntity(BlockPos blockPos, BlockState blockState) {
            super(blockPos, blockState);
        }

        @Override
        public BlockEntityType<CustomSignBlockEntity> getType()
        {
            return ExplorerTileEntities.EXPLORER_SIGNS.get();
        }
    }

}
