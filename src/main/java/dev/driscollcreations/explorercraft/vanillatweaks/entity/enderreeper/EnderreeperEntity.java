package dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper;

import dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.ai.EnderreeperSwellGoal;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public class EnderreeperEntity extends MonsterEntity {
    //==== Enderman Related Parameters ====//
    private static final UUID ATTACKING_SPEED_BOOST_ID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier ATTACKING_SPEED_BOOST = (new AttributeModifier(ATTACKING_SPEED_BOOST_ID, "Attacking speed boost", 0.15F, AttributeModifier.Operation.ADDITION));
    private static final DataParameter<Boolean> SCREAMING = EntityDataManager.defineId(EnderreeperEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IGNITED = EntityDataManager.defineId(CreeperEntity.class, DataSerializers.BOOLEAN);

    private int lastCreepySound;
    private int targetChangeTime;

    //==== Creeper Related Parameters ====//
    private static final DataParameter<Integer> STATE = EntityDataManager.defineId(EnderreeperEntity.class, DataSerializers.INT);
    private int lastActiveTime;
    private int timeSinceIgnited;
    private int fuseTime = 25;
    private int explosionRadius = 5;

    public EnderreeperEntity(EntityType<? extends EnderreeperEntity> type, World worldIn) {
        super(type, worldIn);
        this.maxUpStep = 1.0F;
        this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
    }

    //==== Spawning ====//
    /*public static boolean spawnEnderreeper(EntityType<? extends MonsterEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        if (worldIn.dimensionType() == DimensionType.TH.THE_END) {
            return worldIn.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(worldIn, pos, randomIn) && checkMobSpawnRules(type, worldIn, reason, pos, randomIn);
        } else if (worldIn.getDimension().getType() == DimensionType.OVERWORLD && EntityConfig.enderreeper_overworld_spawn_enabled.get()) {
            addSpawn();
            return worldIn.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(worldIn, pos, randomIn) && checkMobSpawnRules(type, worldIn, reason, pos, randomIn);
        } else {
            return false;
        }
    }

    public static void addSpawn() {
        ForgeRegistries.BIOMES.getValues().stream().forEach(EnderreeperEntity::processSpawning);
    }

    private static void processSpawning(Biome biome) {

        if (biome != Biomes.MUSHROOM_FIELDS || biome != Biomes.MUSHROOM_FIELD_SHORE || biome != Biomes.NETHER) {
            biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(ExplorerEntities.ENDERREEPER, 1, 1, 1));
        }

    }*/

    //==== AI - GOALS - GENERAL ====//
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new EnderreeperSwellGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        //this.targetSelector.addGoal(1, new EnderreeperEntity.FindPlayerGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));

    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.8F;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                       .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 7.0D)
                .add(Attributes.FOLLOW_RANGE, 64.0D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SCREAMING, false);
        this.entityData.define(STATE, -1);
        this.entityData.define(IGNITED, false);
    }

    @Override
    public void onSyncedDataUpdated(DataParameter<?> key) {
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

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putShort("Fuse", (short)this.fuseTime);
        compound.putByte("ExplosionRadius", (byte)this.explosionRadius);
        compound.putBoolean("ignited", this.hasIgnited());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundNBT compound) {
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
            Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            float f = 1.0F;
            this.dead = true;
            this.playSound(SoundEvents.ENDERMAN_SCREAM, 1.0F, 1.0F);
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float) this.explosionRadius * f, explosion$mode);
            this.remove();
            spawnLingeringCloud();
        }
    }

    private void spawnLingeringCloud() {
        Collection<EffectInstance> collection = this.getActiveEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(this.level, this.getX(), this.getX(), this.getZ());
            areaeffectcloudentity.setRadius(5.5F);
            areaeffectcloudentity.setRadiusOnUse(-0.5F);
            areaeffectcloudentity.setWaitTime(10);
            areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
            areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float) areaeffectcloudentity.getDuration());
            areaeffectcloudentity.setFixedColor(7078094);

            for (EffectInstance effectinstance : collection) {
                areaeffectcloudentity.addEffect(new EffectInstance(effectinstance));
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
        return MathHelper.lerp(partialTicks, (float) this.lastActiveTime, (float) this.timeSinceIgnited) / (float) (this.fuseTime - 2);
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
        Vector3d vec3d = new Vector3d(this.getX() - p_70816_1_.getX(), this.getY(0.5D) - p_70816_1_.getEyeY(), this.getZ() - p_70816_1_.getZ());
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
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(x, y, z);

        while (blockpos$mutable.getY() > 0 && !this.level.getBlockState(blockpos$mutable).getMaterial().blocksMotion()) {
            blockpos$mutable.move(Direction.DOWN);
        }

        BlockState blockstate = this.level.getBlockState(blockpos$mutable);
        boolean flag = blockstate.getMaterial().blocksMotion();
        boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
        if (flag && !flag1) {
            net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this, x, y, z, 0);
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
        ModifiableAttributeInstance modifiableattributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
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
    private boolean shouldAttackPlayer(PlayerEntity player) {
        ItemStack itemstack = player.inventory.armor.get(3);
        if (itemstack.getItem() == Blocks.CARVED_PUMPKIN.asItem()) {
            return false;
        } else {
            Vector3d vec3d = player.getViewVector(1.0F).normalize();
            Vector3d vec3d1 = new Vector3d(this.getX() - player.getX(), this.getBoundingBox().minY + (double) this.getEyeHeight() - (player.getX() + (double) player.getEyeHeight()), this.getZ() - player.getZ());
            double d0 = vec3d1.length();
            vec3d1 = vec3d1.normalize();
            double d1 = vec3d.dot(vec3d1);
            return d1 > 1.0D - 0.025D / d0 && player.canSee(this);
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

            if (attackingEntity instanceof PlayerEntity) {
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

    static class FindPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
        private final EnderreeperEntity enderreeper;
        private PlayerEntity player;
        private int aggroTime;
        private int teleportTime;
        private final EntityPredicate startAggroTargetConditions;
        private final EntityPredicate continueAggroTargetConditions = (new EntityPredicate()).allowUnseeable();

        public FindPlayerGoal(EnderreeperEntity enderreeper) {
            super(enderreeper, PlayerEntity.class, false);
            this.enderreeper = enderreeper;
            this.startAggroTargetConditions = (new EntityPredicate()).range(this.getFollowDistance()).selector((p_220790_1_) -> {
                return enderreeper.shouldAttackPlayer((PlayerEntity) p_220790_1_);
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
                    if (this.enderreeper.shouldAttackPlayer((PlayerEntity) this.targetMob)) {
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
