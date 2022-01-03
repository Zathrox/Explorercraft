package dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper;

import dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.ai.EnderreeperSwellGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.UUID;
import java.util.function.Predicate;

public class EnderreeperEntity extends Monster {
    //==== Enderman Related Parameters ====//
    private static final UUID ATTACKING_SPEED_BOOST_ID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier ATTACKING_SPEED_BOOST = (new AttributeModifier(ATTACKING_SPEED_BOOST_ID, "Attacking speed boost", 0.15F, AttributeModifier.Operation.ADDITION));
    private static final EntityDataAccessor<Boolean> SCREAMING = SynchedEntityData.defineId(EnderreeperEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IGNITED = SynchedEntityData.defineId(EnderreeperEntity.class, EntityDataSerializers.BOOLEAN);

    private int lastCreepySound;
    private int targetChangeTime;

    //==== Creeper Related Parameters ====//
    private static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(EnderreeperEntity.class, EntityDataSerializers.INT);
    private int lastActiveTime;
    private int timeSinceIgnited;
    private int fuseTime = 25;
    private int explosionRadius = 5;

    public EnderreeperEntity(EntityType<? extends EnderreeperEntity> type, Level worldIn) {
        super(type, worldIn);
        this.maxUpStep = 1.0F;
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    }

    //==== AI - GOALS - GENERAL ====//
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new EnderreeperSwellGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));

    }
    
    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.8F;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SCREAMING, false);
        this.entityData.define(STATE, -1);
        this.entityData.define(IGNITED, false);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (SCREAMING.equals(key) && this.isScreaming() && this.level.isClientSide) {
            this.playEndermanSound();
        }
        super.onSyncedDataUpdated(key);
    }

    public boolean hasIgnited() {
        return this.entityData.get(IGNITED);
    }

    public void ignite() {
        this.entityData.set(IGNITED, true);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void aiStep() {
        if (this.level.isClientSide) {
            for (int i = 0; i < 2; ++i) {
                this.level.addParticle(ParticleTypes.PORTAL, this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), this.getY() + this.random.nextDouble() * (double) this.getBbHeight() - 0.25D, this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
            }
        }
        this.jumping = false;
        super.aiStep();
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putShort("Fuse", (short)this.fuseTime);
        compound.putByte("ExplosionRadius", (byte)this.explosionRadius);
        compound.putBoolean("ignited", this.hasIgnited());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Fuse", 99)) {
            this.fuseTime = compound.getShort("Fuse");
        }

        if (compound.contains("ExplosionRadius", 99)) {
            this.explosionRadius = compound.getByte("ExplosionRadius");
        }

        if (compound.getBoolean("ignited")) {
            this.ignite();
        }

    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void tick() {
        if (this.isAlive()) {
            this.lastActiveTime = this.timeSinceIgnited;
            if (this.hasIgnited()) {
                this.setEnderreeperState(1);
            }

            int i = this.getEnderreeperState();
            if (i > 0 && this.timeSinceIgnited == 0) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
            }

            this.timeSinceIgnited += i;
            if (this.timeSinceIgnited < 0) {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime) {
                this.timeSinceIgnited = this.fuseTime;
                this.explode();
            }
        }

        super.tick();
    }

    /**
     * Returns the current state of Enderreeper, -1 is idle, 1 is 'in fuse'
     */
    public int getEnderreeperState() {
        return this.entityData.get(STATE);
    }

    /**
     * Sets the state of Enderreeper, -1 to idle and 1 to be 'in fuse'
     */
    public void setEnderreeperState(int state) {
        this.entityData.set(STATE, state);
    }


    /**
     * Creates an explosion as determined by this Enderreeper's power and explosion radius.
     */
    private void explode() {
        if (!this.level.isClientSide) {
            Explosion.BlockInteraction explosion$BlockInteraction = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
            float f = 1.0F;
            this.dead = true;
            this.playSound(SoundEvents.ENDERMAN_SCREAM, 1.0F, 1.0F);
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float) this.explosionRadius * f, explosion$BlockInteraction);
            this.discard();
            spawnLingeringCloud();
        }
    }

    private void spawnLingeringCloud() {
        Collection<MobEffectInstance> collection = this.getActiveEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloud areaeffectcloudentity = new AreaEffectCloud(this.level, this.getX(), this.getX(), this.getZ());
            areaeffectcloudentity.setRadius(5.5F);
            areaeffectcloudentity.setRadiusOnUse(-0.5F);
            areaeffectcloudentity.setWaitTime(10);
            areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
            areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float) areaeffectcloudentity.getDuration());
            areaeffectcloudentity.setFixedColor(7078094);

            for (MobEffectInstance effectinstance : collection) {
                areaeffectcloudentity.addEffect(new MobEffectInstance(effectinstance));
            }
            this.level.addFreshEntity(areaeffectcloudentity);
        }

    }

    //==== Client Code ====//

    /**
     * Params: (Float)Render tick. Returns the intensity of the Enderreepers's flash when it is ignited.
     */
    @OnlyIn(Dist.CLIENT)
    public float getEnderreeperFlashIntensity(float partialTicks) {
        return Mth.lerp(partialTicks, (float) this.lastActiveTime, (float) this.timeSinceIgnited) / (float) (this.fuseTime - 2);
    }

    //===== Sounds =====//
    public void playEndermanSound() {
        if (this.tickCount >= this.lastCreepySound + 400) {
            this.lastCreepySound = this.tickCount;
            if (!this.isSilent()) {
                this.level.playLocalSound(this.getX(), this.getX() + (double) this.getEyeHeight(), this.getZ(), SoundEvents.ENDERMAN_STARE, this.getSoundSource(), 2.5F, 1.0F, false);
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isScreaming() ? SoundEvents.ENDERMAN_SCREAM : SoundEvents.ENDERMAN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return random.nextInt(4) == 0 ? SoundEvents.CREEPER_HURT : SoundEvents.ENDERMAN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return random.nextInt(2) == 0 ? SoundEvents.ENDERMAN_DEATH : SoundEvents.CREEPER_DEATH;
    }


    //===== Teleportation Code =====//

    /**
     * Teleport the enderman to a random nearby position
     */
    protected boolean teleportRandomly() {
        if (!this.level.isClientSide() && this.isAlive()) {
            double d0 = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
            double d1 = this.getY() + (double) (this.random.nextInt(64) - 32);
            double d2 = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
            return this.teleport(d0, d1, d2);
        } else {
            return false;
        }
    }

    /**
     * Teleport the enderman to another entity
     */
    private boolean teleportToEntity(Entity p_70816_1_) {
        Vec3 vec3d = new Vec3(this.getX() - p_70816_1_.getX(), this.getY(0.5D) - p_70816_1_.getEyeY(), this.getZ() - p_70816_1_.getZ());
        vec3d = vec3d.normalize();
        double d0 = 16.0D;
        double d1 = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
        double d2 = this.getY() + (double) (this.random.nextInt(16) - 8) - vec3d.y * 16.0D;
        double d3 = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
        return this.teleport(d1, d2, d3);
    }

    /**
     * Teleport the enderman
     */
    private boolean teleport(double x, double y, double z) {
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos(x, y, z);

        while (blockpos$mutable.getY() > 0 && !this.level.getBlockState(blockpos$mutable).getMaterial().blocksMotion()) {
            blockpos$mutable.move(Direction.DOWN);
        }

        BlockState blockstate = this.level.getBlockState(blockpos$mutable);
        boolean flag = blockstate.getMaterial().blocksMotion();
        boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
        if (flag && !flag1) {
            net.minecraftforge.event.entity.EntityTeleportEvent.EnderEntity event = net.minecraftforge.event.ForgeEventFactory.onEnderTeleport(this, x, y, z);
            if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return false;
            boolean flag2 = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
            if (flag2) {
                this.level.playSound(null, this.xo, this.yo, this.zo, SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
                this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }
            return flag2;
        } else {
            return false;
        }
    }

    //===== Attacking Code =====//
    @Override
    public boolean doHurtTarget(Entity entityIn) {
        return true;
    }

    /**
     * Sets the active target the Task system uses for tracking
     */
    @Override
    public void setTarget(@Nullable LivingEntity entitylivingbaseIn) {
        AttributeInstance modifiableattributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (entitylivingbaseIn == null) {
            this.targetChangeTime = 0;
            this.entityData.set(SCREAMING, false);
            modifiableattributeinstance.removeModifier(ATTACKING_SPEED_BOOST);
        } else {
            this.targetChangeTime = this.tickCount;
            this.entityData.set(SCREAMING, true);
            if (!modifiableattributeinstance.hasModifier(ATTACKING_SPEED_BOOST)) {
                modifiableattributeinstance.addTransientModifier(ATTACKING_SPEED_BOOST);
            }
        }
        super.setTarget(entitylivingbaseIn); //Forge: Moved down to allow event handlers to write data manager values.
    }

    /**
     * Checks to see if this enderman should be attacking this player
     */
    private boolean shouldAttackPlayer(Player player) {
        ItemStack itemstack = player.getInventory().armor.get(3);
        if (itemstack.getItem() == Blocks.CARVED_PUMPKIN.asItem()) {
            return false;
        } else {
            Vec3 vec3d = player.getViewVector(1.0F).normalize();
            Vec3 vec3d1 = new Vec3(this.getX() - player.getX(), this.getEyeY() - player.getEyeY(), this.getZ() - player.getZ());
            double d0 = vec3d1.length();
            vec3d1 = vec3d1.normalize();
            double d1 = vec3d.dot(vec3d1);
            return d1 > 1.0D - 0.025D / d0 && player.hasLineOfSight(this);
        }
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (!(source instanceof IndirectEntityDamageSource)) {
            boolean flag = super.hurt(source, amount);
            if (!this.level.isClientSide() && source.isBypassArmor() && this.random.nextInt(10) != 0) {
                this.teleportRandomly();
            }
            return flag;
        } else {
            for (int i = 0; i < 64; ++i) {
                if (this.teleportRandomly()) {
                    return true;
                }
            }
            return false;
        }
    }

    private boolean switchWithEntity(Entity attackingEntity, boolean attacked, DamageSource source, float var4) {
        if (attackingEntity != null && attackingEntity instanceof LivingEntity) {
            double d1 = this.getX();
            double d2 = this.getX();
            double d3 = this.getZ();

            double mobPosX = attackingEntity.getX();
            double mobPosY = attackingEntity.getX();
            double mobPosZ = attackingEntity.getZ();
            this.setPos(mobPosX, mobPosY, mobPosZ);

            if (attackingEntity instanceof Player) {
                attackingEntity.teleportTo(d1, d2 + 2.0D, d3);
            } else {
                attackingEntity.setPos(d1, d2 + 2.0D, d3);
            }
            this.setTarget((LivingEntity) attackingEntity);
            return this.teleport(mobPosX, mobPosY, mobPosZ);
        } else {
            return false;
        }
    }

    public boolean isScreaming() {
        return this.entityData.get(SCREAMING);
    }

    static class FindPlayerGoal extends NearestAttackableTargetGoal<Player> {
        private final EnderreeperEntity enderreeper;
        private Player player;
        private int aggroTime;
        private int teleportTime;
        private final TargetingConditions startAggroTargetConditions;
        private final TargetingConditions continueAggroTargetConditions = TargetingConditions.forCombat().ignoreLineOfSight();

        public FindPlayerGoal(EnderreeperEntity enderreeper, @Nullable Predicate<LivingEntity> predicate) {
            super(enderreeper, Player.class, 10, false, false, predicate);
            this.enderreeper = enderreeper;
            this.startAggroTargetConditions = TargetingConditions.forCombat().range(this.getFollowDistance()).selector((p_32578_) -> {
                return enderreeper.shouldAttackPlayer((Player)p_32578_);
            });
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean canUse() {
            this.player = this.enderreeper.level.getNearestPlayer(this.startAggroTargetConditions, this.enderreeper);
            return this.player != null;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.aggroTime = 5;
            this.teleportTime = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            this.player = null;
            super.stop();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            if (this.player != null) {
                if (!this.enderreeper.shouldAttackPlayer(this.player)) {
                    return false;
                } else {
                    this.enderreeper.lookAt(this.player, 6.0F, 6.0F);
                    return true;
                }
            } else {
                return this.targetMob != null && this.continueAggroTargetConditions.test(this.enderreeper, this.targetMob) || super.canContinueToUse();
            }
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.player != null) {
                if (--this.aggroTime <= 0) {
                    this.targetMob = this.player;
                    this.player = null;
                    super.start();
                }
            } else {
                if (this.targetMob != null && !this.enderreeper.isPassenger()) {
                    if (this.enderreeper.shouldAttackPlayer((Player) this.targetMob)) {
                        if (this.targetMob.distanceToSqr(this.enderreeper) < 16.0D) {
                            this.enderreeper.teleportRandomly();
                        }
                        this.teleportTime = 0;
                    } else if (this.targetMob.distanceToSqr(this.enderreeper) > 256.0D && this.teleportTime++ >= 30 && this.enderreeper.teleportToEntity(this.targetMob)) {
                        this.teleportTime = 0;
                    }
                }
                super.tick();
            }
        }
    }
}
