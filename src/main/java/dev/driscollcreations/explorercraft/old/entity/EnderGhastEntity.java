/*
package dev.driscollcreations.explorercraft.old.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.Random;

public class EnderGhastEntity extends GhastEntity {

    public EnderGhastEntity(EntityType<? extends GhastEntity> p_i50206_1_, World worldIn) {
        super(p_i50206_1_, worldIn);
        this.xpReward = 5;
        this.moveControl = new MoveHelperController(this);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(5, new RandomFlyGoal(this));
        this.goalSelector.addGoal(7, new LookAroundGoal(this));
        this.goalSelector.addGoal(7, new FireballAttackGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (p_213812_1_) -> {
            return Math.abs(p_213812_1_.getY() - this.getY()) <= 4.0D;
        }));
    }

    */
/**
     * Will return how many at most can spawn in a chunk at once.
     *//*

    @Override
    public int getMaxSpawnClusterSize() {
        return 1;
    }

    public static boolean spawnConditions(EntityType<EnderGhastEntity> entityType, IWorld world, SpawnReason reason, BlockPos blockPos, Random random) {
        return world.getDifficulty() != Difficulty.PEACEFUL && random.nextInt(10) == 0 && checkMobSpawnRules(entityType, world, reason, blockPos, random);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 3.1F;
    }

    static class FireballAttackGoal extends Goal {
        private final GhastEntity parentEntity;
        public int attackTimer;

        public FireballAttackGoal(GhastEntity ghast) {
            this.parentEntity = ghast;
        }

        */
/**
         * Returns whether the EntityAIBase should begin execution.
         *//*

        public boolean canUse() {
            return this.parentEntity.getTarget() != null;
        }

        */
/**
         * Execute a one shot task or start executing a continuous task
         *//*

        public void start() {
            this.attackTimer = 0;
        }

        */
/**
         * Reset the task's internal state. Called when this task is interrupted by another one
         *//*

        public void stop() {
            this.parentEntity.setCharging(false);
        }

        */
/**
         * Keep ticking a continuous task that has already been started
         *//*

        public void tick() {
            LivingEntity livingentity = this.parentEntity.getTarget();
            double d0 = 64.0D;
            if (livingentity.distanceToSqr(this.parentEntity) < 4096.0D && this.parentEntity.canSee(livingentity)) {
                World world = this.parentEntity.level;
                ++this.attackTimer;
                if (this.attackTimer == 10) {
                    world.levelEvent(null, 1015, new BlockPos(this.parentEntity), 0);
                }

                if (this.attackTimer == 20) {
                    double d1 = 4.0D;
                    Vec3d vec3d = this.parentEntity.getViewVector(1.0F);
                    double d2 = livingentity.getX() - (this.parentEntity.getX() + vec3d.x * 4.0D);
                    double d3 = livingentity.getY(0.5D) - (0.5D + this.parentEntity.getY(0.5D));
                    double d4 = livingentity.getZ() - (this.parentEntity.getZ() + vec3d.z * 4.0D);
                    world.levelEvent(null, 1016, new BlockPos(this.parentEntity), 0);
                    DragonFireballEntity fireballentity = new DragonFireballEntity(world, this.parentEntity, d2, d3, d4);
                    fireballentity.setPos(this.parentEntity.getX() + vec3d.x * 4.0D, this.parentEntity.getY(0.5D) + 0.5D, fireballentity.getZ() + vec3d.z * 4.0D);
                    world.addFreshEntity(fireballentity);
                    this.attackTimer = -40;
                }
            } else if (this.attackTimer > 0) {
                --this.attackTimer;
            }

            this.parentEntity.setCharging(this.attackTimer > 10);
        }
    }

    static class LookAroundGoal extends Goal {
        private final GhastEntity parentEntity;

        public LookAroundGoal(GhastEntity ghast) {
            this.parentEntity = ghast;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        */
/**
         * Returns whether the EntityAIBase should begin execution.
         *//*

        public boolean canUse() {
            return true;
        }

        */
/**
         * Keep ticking a continuous task that has already been started
         *//*

        public void tick() {
            if (this.parentEntity.getTarget() == null) {
                Vec3d vec3d = this.parentEntity.getDeltaMovement();
                this.parentEntity.yRot = -((float) MathHelper.atan2(vec3d.x, vec3d.z)) * (180F / (float) Math.PI);
                this.parentEntity.yBodyRot = this.parentEntity.yRot;
            } else {
                LivingEntity livingentity = this.parentEntity.getTarget();
                double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.parentEntity) < 4096.0D) {
                    double d1 = livingentity.getX() - this.parentEntity.getX();
                    double d2 = livingentity.getZ() - this.parentEntity.getZ();
                    this.parentEntity.yRot = -((float) MathHelper.atan2(d1, d2)) * (180F / (float) Math.PI);
                    this.parentEntity.yBodyRot = this.parentEntity.yRot;
                }
            }
        }
    }

    static class MoveHelperController extends MovementController {
        private final GhastEntity parentEntity;
        private int courseChangeCooldown;

        public MoveHelperController(GhastEntity ghast) {
            super(ghast);
            this.parentEntity = ghast;
        }

        public void tick() {
            if (this.operation == Action.MOVE_TO) {
                if (this.courseChangeCooldown-- <= 0) {
                    this.courseChangeCooldown += this.parentEntity.getRandom().nextInt(5) + 2;
                    Vec3d vec3d = new Vec3d(this.wantedX - this.parentEntity.getX(), this.wantedY - this.parentEntity.getY(), this.wantedZ - this.parentEntity.getZ());
                    double d0 = vec3d.length();
                    vec3d = vec3d.normalize();
                    if (this.canReach(vec3d, MathHelper.ceil(d0))) {
                        this.parentEntity.setDeltaMovement(this.parentEntity.getDeltaMovement().add(vec3d.scale(0.1D)));
                    } else {
                        this.operation = Action.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3d p_220673_1_, int p_220673_2_) {
            AxisAlignedBB axisalignedbb = this.parentEntity.getBoundingBox();

            for (int i = 1; i < p_220673_2_; ++i) {
                axisalignedbb = axisalignedbb.move(p_220673_1_);
                if (!this.parentEntity.level.noCollision(this.parentEntity, axisalignedbb)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class RandomFlyGoal extends Goal {
        private final GhastEntity parentEntity;

        public RandomFlyGoal(GhastEntity ghast) {
            this.parentEntity = ghast;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        */
/**
         * Returns whether the EntityAIBase should begin execution.
         *//*

        public boolean canUse() {
            MovementController movementcontroller = this.parentEntity.getMoveControl();
            if (!movementcontroller.hasWanted()) {
                return true;
            } else {
                double d0 = movementcontroller.getWantedX() - this.parentEntity.getX();
                double d1 = movementcontroller.getWantedY() - this.parentEntity.getY();
                double d2 = movementcontroller.getWantedZ() - this.parentEntity.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        */
/**
         * Returns whether an in-progress EntityAIBase should continue executing
         *//*

        public boolean canContinueToUse() {
            return false;
        }

        */
/**
         * Execute a one shot task or start executing a continuous task
         *//*

        public void start() {
            Random random = this.parentEntity.getRandom();
            double d0 = this.parentEntity.getX() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.parentEntity.getY() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.parentEntity.getZ() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.parentEntity.getMoveControl().setWantedPosition(d0, d1, d2, 1.5D);
        }
    }
}
*/
