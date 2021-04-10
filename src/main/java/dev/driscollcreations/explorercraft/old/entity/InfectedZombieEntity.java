/*
package dev.driscollcreations.explorercraft.old.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.ZombieEntity;
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
        return SoundEvents.ZOMBIE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }

    @Override
    protected void registerAttributes() {
        super.createLivingAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.22F);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0D);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3.5D);
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        boolean flag = super.doHurtTarget(entityIn);
        if (flag && this.getMainHandItem().isEmpty() && entityIn instanceof LivingEntity) {
            float f = this.level.getCurrentDifficultyAt(new BlockPos(this)).getEffectiveDifficulty();
            if (random.nextInt(5) == 0) {
                ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.POISON, 140 * (int) f));
            } else {
                ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.CONFUSION, 140 * (int) f));
            }
        }

        return flag;
    }

    protected boolean convertsInWater() {
        return true;
    }

    protected void doUnderWaterConversion() {
        this.func_213698_b(EntityType.ZOMBIE);
        this.level.levelEvent(null, 1041, new BlockPos(this), 0);
    }

    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }
}
*/
