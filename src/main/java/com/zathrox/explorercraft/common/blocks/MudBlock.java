package com.zathrox.explorercraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.PlantType;

public class MudBlock extends Block
{
    public MudBlock(Properties properties)
    {
        super(properties);
    }

    /* @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if(!(entityIn.getEntity() instanceof BrushStoogeEntity))
            entityIn.setMotion(entityIn.getMotion().mul(0.5D, 1.0D, 0.5D));
    }*/

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable)
    {
        PlantType type = plantable.getPlantType(world, pos.offset(facing));

        switch (type) {
            case Desert: return false;
            case Nether: return false;
            case Crop: return false;
            case Cave: return false;
            case Plains: return false;
            case Water: return false;
            case Beach:
                boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.south()).getMaterial() == Material.WATER);
                return hasWater;
        }
        return false;
    }
}
