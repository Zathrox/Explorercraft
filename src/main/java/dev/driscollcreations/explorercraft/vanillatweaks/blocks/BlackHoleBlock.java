package dev.driscollcreations.explorercraft.vanillatweaks.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

public class BlackHoleBlock extends Block {

    public BlackHoleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos blockPos, Entity entity) {
        if (entity instanceof LivingEntity) {
            entity.hurt(DamageSource.MAGIC, (float)5);
        }

        super.entityInside(state, world, blockPos, entity);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

}
