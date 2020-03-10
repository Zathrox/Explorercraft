package com.zathrox.explorercraft.common.entity.ai;

import com.zathrox.explorercraft.common.entity.EnderreeperEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

public class EnderreeperSwellGoal extends Goal {
    private final EnderreeperEntity enderreeperEntity;
    private LivingEntity livingEntity;

    public EnderreeperSwellGoal(EnderreeperEntity entity) {
        this.enderreeperEntity = entity;
        this.setMutexFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean shouldExecute() {
        LivingEntity player = this.enderreeperEntity.getAttackTarget();
        return this.enderreeperEntity.getEnderreeperState() > 0 || player != null && this.enderreeperEntity.getDistanceSq(player) < 9.0D;
    }

    @Override
    public void startExecuting() {
        this.enderreeperEntity.getNavigator().clearPath();
        this.livingEntity = this.enderreeperEntity.getAttackTarget();
    }

    @Override
    public void resetTask() {
        this.livingEntity = null;
    }

    @Override
    public void tick() {
        if (this.livingEntity == null) {
            this.enderreeperEntity.setEnderreeperState(-1);
        } else if (this.enderreeperEntity.getDistanceSq(this.livingEntity) > 49.0D) {
            this.enderreeperEntity.setEnderreeperState(-1);
        } else if (!this.enderreeperEntity.getEntitySenses().canSee(this.livingEntity)) {
            this.enderreeperEntity.setEnderreeperState(-1);
        } else {
            this.enderreeperEntity.setEnderreeperState(1);
        }
    }
}
