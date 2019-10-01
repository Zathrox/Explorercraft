package com.zathrox.explorercraft.common.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InfectedZombieEntity extends ZombieEntity {
    public InfectedZombieEntity(EntityType<? extends InfectedZombieEntity> p_i50204_1_, World p_i50204_2_) {
        super(p_i50204_1_, p_i50204_2_);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.22F);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0D);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3.5D);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = super.attackEntityAsMob(entityIn);
        if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof LivingEntity) {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
            if (rand.nextInt(5) == 0) {
                ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.POISON, 140 * (int) f));
            } else {
                ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.NAUSEA, 140 * (int) f));
            }
        }

        return flag;
    }

    protected boolean shouldDrown() {
        return true;
    }

    protected void onDrowned() {
        this.func_213698_b(EntityType.ZOMBIE);
        this.world.playEvent((PlayerEntity)null, 1041, new BlockPos(this), 0);
    }

    protected ItemStack getSkullDrop() {
        return ItemStack.EMPTY;
    }
}
