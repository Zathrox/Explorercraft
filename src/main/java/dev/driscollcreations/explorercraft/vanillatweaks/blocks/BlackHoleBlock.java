package dev.driscollcreations.explorercraft.vanillatweaks.blocks;

import dev.driscollcreations.explorercraft.vanillatweaks.tileentities.BlackHoleTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlackHoleBlock extends Block {

    public BlackHoleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, World world, BlockPos blockPos, Entity entity) {
        if (entity instanceof LivingEntity) {
            entity.hurt(DamageSource.MAGIC, (float)5);
        }

        super.entityInside(state, world, blockPos, entity);
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new BlackHoleTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
