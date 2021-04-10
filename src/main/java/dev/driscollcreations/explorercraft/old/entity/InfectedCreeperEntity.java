package dev.driscollcreations.explorercraft.old.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.world.World;

public class InfectedCreeperEntity extends CreeperEntity {

    public InfectedCreeperEntity(EntityType<? extends CreeperEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
