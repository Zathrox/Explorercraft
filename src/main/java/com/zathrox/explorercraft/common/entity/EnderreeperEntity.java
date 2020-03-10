package com.zathrox.explorercraft.common.entity;

import com.zathrox.explorercraft.common.entity.ai.EnderreeperSwellGoal;
import com.zathrox.explorercraft.core.config.EntityConfig;
import com.zathrox.explorercraft.core.registry.ExplorerEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

public class EnderreeperEntity extends CreeperEntity
{
    //==== Enderman Related Parameters ====//
    private static final UUID ATTACKING_SPEED_BOOST_ID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier ATTACKING_SPEED_BOOST = (new AttributeModifier(ATTACKING_SPEED_BOOST_ID, "Attacking speed boost", (double)0.15F, AttributeModifier.Operation.ADDITION)).setSaved(false);
    private static final DataParameter<Boolean> SCREAMING = EntityDataManager.createKey(EnderreeperEntity.class, DataSerializers.BOOLEAN);

    private int lastCreepySound;
    private int targetChangeTime;
    
    //==== Creeper Related Parameters ====//
    private static final DataParameter<Integer> STATE = EntityDataManager.createKey(EnderreeperEntity.class, DataSerializers.VARINT);
    private int lastActiveTime;
    private int timeSinceIgnited;
    private int fuseTime = 25;
    private int explosionRadius = 5;

    public EnderreeperEntity(EntityType<? extends EnderreeperEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1.0F;
        this.setPathPriority(PathNodeType.WATER, -1.0F);
    }

    //==== Spawning ====//
    public static boolean spawnEnderreeper(EntityType<? extends MonsterEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        if (worldIn.getDimension().getType() == DimensionType.THE_END) {
            return worldIn.getDifficulty() != Difficulty.PEACEFUL && isValidLightLevel(worldIn, pos, randomIn) && canSpawnOn(type, worldIn, reason, pos, randomIn);
        } else if (worldIn.getDimension().getType() == DimensionType.OVERWORLD && EntityConfig.enderreeper_overworld_spawn_enabled.get()) {
            addSpawn();
            return worldIn.getDifficulty() != Difficulty.PEACEFUL && isValidLightLevel(worldIn, pos, randomIn) && canSpawnOn(type, worldIn, reason, pos, randomIn);
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

    }

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

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.3F);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(SCREAMING, false);
        this.dataManager.register(STATE, -1);
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
        if (SCREAMING.equals(key) && this.isScreaming() && this.world.isRemote) {
            this.playEndermanSound();
        }

        super.notifyDataManagerChange(key);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void livingTick() {
        if (this.world.isRemote) {
            for(int i = 0; i < 2; ++i) {
                this.world.addParticle(ParticleTypes.PORTAL, this.getPosX() + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth(), this.getPosY() + this.rand.nextDouble() * (double)this.getHeight() - 0.25D, this.getPosZ() + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth(), (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
            }
        }

        this.isJumping = false;
        super.livingTick();
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
                this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
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
        return this.dataManager.get(STATE);
    }

    /**
     * Sets the state of Enderreeper, -1 to idle and 1 to be 'in fuse'
     */
    public void setEnderreeperState(int state) {
        this.dataManager.set(STATE, state);
    }


    /**
     * Creates an explosion as determined by this Enderreeper's power and explosion radius.
     */
    private void explode() {
        if (!this.world.isRemote) {
            Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            float f = this.func_225509_J__() ? 2.0F : 1.0F;
            this.dead = true;
            this.playSound(SoundEvents.ENTITY_ENDERMAN_SCREAM, 1.0F, 1.0F);
            this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), (float)this.explosionRadius * f, explosion$mode);
            this.remove();
            spawnLingeringCloud();
        }
    }

    private void spawnLingeringCloud() {
        Collection<EffectInstance> collection = this.getActivePotionEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(this.world, this.getPosX(), this.getPosX(), this.getPosZ());
            areaeffectcloudentity.setRadius(5.5F);
            areaeffectcloudentity.setRadiusOnUse(-0.5F);
            areaeffectcloudentity.setWaitTime(10);
            areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
            areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float)areaeffectcloudentity.getDuration());
            areaeffectcloudentity.setColor(7078094);

            for(EffectInstance effectinstance : collection) {
                areaeffectcloudentity.addEffect(new EffectInstance(effectinstance));
            }

            this.world.addEntity(areaeffectcloudentity);
        }

    }

    //==== Client Code ====//
    /**
     * Params: (Float)Render tick. Returns the intensity of the Enderreepers's flash when it is ignited.
     */
    @OnlyIn(Dist.CLIENT)
    public float getEnderreeperFlashIntensity(float partialTicks) {
        return MathHelper.lerp(partialTicks, (float)this.lastActiveTime, (float)this.timeSinceIgnited) / (float)(this.fuseTime - 2);
    }

    //===== Sounds =====//
    public void playEndermanSound() {
        if (this.ticksExisted >= this.lastCreepySound + 400) {
            this.lastCreepySound = this.ticksExisted;
            if (!this.isSilent()) {
                this.world.playSound(this.getPosX(), this.getPosX() + (double)this.getEyeHeight(), this.getPosZ(), SoundEvents.ENTITY_ENDERMAN_STARE, this.getSoundCategory(), 2.5F, 1.0F, false);
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isScreaming() ? SoundEvents.ENTITY_ENDERMAN_SCREAM : SoundEvents.ENTITY_ENDERMAN_AMBIENT;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return rand.nextInt(4) == 0 ? SoundEvents.ENTITY_CREEPER_HURT : SoundEvents.ENTITY_ENDERMAN_HURT;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return rand.nextInt(2) == 0 ? SoundEvents.ENTITY_ENDERMAN_DEATH : SoundEvents.ENTITY_CREEPER_DEATH;
    }


    //===== Teleportation Code =====//
    /**
     * Teleport the enderman to a random nearby position
     */
    protected boolean teleportRandomly() {
        if (!this.world.isRemote() && this.isAlive()) {
            double d0 = this.getPosX() + (this.rand.nextDouble() - 0.5D) * 64.0D;
            double d1 = this.getPosY() + (double)(this.rand.nextInt(64) - 32);
            double d2 = this.getPosZ() + (this.rand.nextDouble() - 0.5D) * 64.0D;
            return this.teleportTo(d0, d1, d2);
        } else {
            return false;
        }
    }

    /**
     * Teleport the enderman to another entity
     */
    private boolean teleportToEntity(Entity p_70816_1_) {
        Vec3d vec3d = new Vec3d(this.getPosX() - p_70816_1_.getPosX(), this.getPosYHeight(0.5D) - p_70816_1_.getPosYEye(), this.getPosZ() - p_70816_1_.getPosZ());
        vec3d = vec3d.normalize();
        double d0 = 16.0D;
        double d1 = this.getPosX() + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
        double d2 = this.getPosY() + (double)(this.rand.nextInt(16) - 8) - vec3d.y * 16.0D;
        double d3 = this.getPosZ() + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
        return this.teleportTo(d1, d2, d3);
    }

    /**
     * Teleport the enderman
     */
    private boolean teleportTo(double x, double y, double z) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(x, y, z);

        while(blockpos$mutable.getY() > 0 && !this.world.getBlockState(blockpos$mutable).getMaterial().blocksMovement()) {
            blockpos$mutable.move(Direction.DOWN);
        }

        BlockState blockstate = this.world.getBlockState(blockpos$mutable);
        boolean flag = blockstate.getMaterial().blocksMovement();
        boolean flag1 = blockstate.getFluidState().isTagged(FluidTags.WATER);
        if (flag && !flag1) {
            net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this, x, y, z, 0);
            if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return false;
            boolean flag2 = this.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
            if (flag2) {
                this.world.playSound((PlayerEntity)null, this.prevPosX, this.prevPosY, this.prevPosZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
                this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }

            return flag2;
        } else {
            return false;
        }
    }

    //===== Attacking Code =====//
    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return true;
    }

    /**
     * Sets the active target the Task system uses for tracking
     */
    @Override
    public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
        IAttributeInstance iattributeinstance = this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        if (entitylivingbaseIn == null) {
            this.targetChangeTime = 0;
            this.dataManager.set(SCREAMING, false);
            iattributeinstance.removeModifier(ATTACKING_SPEED_BOOST);
        } else {
            this.targetChangeTime = this.ticksExisted;
            this.dataManager.set(SCREAMING, true);
            if (!iattributeinstance.hasModifier(ATTACKING_SPEED_BOOST)) {
                iattributeinstance.applyModifier(ATTACKING_SPEED_BOOST);
            }
        }
        super.setAttackTarget(entitylivingbaseIn); //Forge: Moved down to allow event handlers to write data manager values.
    }
    
    /**
     * Checks to see if this enderman should be attacking this player
     */
    private boolean shouldAttackPlayer(PlayerEntity player) {
        ItemStack itemstack = player.inventory.armorInventory.get(3);
        if (itemstack.getItem() == Blocks.CARVED_PUMPKIN.asItem()) {
            return false;
        } else {
            Vec3d vec3d = player.getLook(1.0F).normalize();
            Vec3d vec3d1 = new Vec3d(this.getPosX() - player.getPosX(), this.getBoundingBox().minY + (double)this.getEyeHeight() - (player.getPosX() + (double)player.getEyeHeight()), this.getPosZ() - player.getPosZ());
            double d0 = vec3d1.length();
            vec3d1 = vec3d1.normalize();
            double d1 = vec3d.dotProduct(vec3d1);
            return d1 > 1.0D - 0.025D / d0 ? player.canEntityBeSeen(this) : false;
        }
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (!(source instanceof IndirectEntityDamageSource) && source != DamageSource.FIREWORKS) {
            boolean flag = super.attackEntityFrom(source, amount);
            if (!this.world.isRemote() && source.isUnblockable() && this.rand.nextInt(10) != 0) {
                this.teleportRandomly();
            }
            return flag;
        } else {
            for(int i = 0; i < 64; ++i) {
                if (this.teleportRandomly()) {
                    return true;
                }
            }
            return false;
        }
    }

    private boolean switchWithEntity(Entity attackingEntity, boolean attacked, DamageSource source, float var4)
    {
        if (attackingEntity != null && attackingEntity instanceof LivingEntity)
        {
            double d1 = this.getPosX();
            double d2 = this.getPosX();
            double d3 = this.getPosZ();

            double mobPosX = attackingEntity.getPosX() ;
            double mobPosY = attackingEntity.getPosX();
            double mobPosZ = attackingEntity.getPosZ();
            this.setPosition(mobPosX, mobPosY, mobPosZ);

            if (attackingEntity instanceof PlayerEntity)
            {
                attackingEntity.setPositionAndUpdate(d1, d2 + 2.0D, d3);
            }
            else
            {
                attackingEntity.setPosition(d1, d2 + 2.0D, d3);
            }
            this.setAttackTarget((LivingEntity)attackingEntity);
            return this.teleportTo(mobPosX, mobPosY, mobPosZ);
        }
        else
        {
            return false;
        }
    }

    public boolean isScreaming() {
        return this.dataManager.get(SCREAMING);
    }

    static class FindPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
        private final EnderreeperEntity enderreeper;
        private PlayerEntity player;
        private int aggroTime;
        private int teleportTime;
        private final EntityPredicate field_220791_m;
        private final EntityPredicate field_220792_n = (new EntityPredicate()).setLineOfSiteRequired();

        public FindPlayerGoal(EnderreeperEntity enderreeper) {
            super(enderreeper, PlayerEntity.class, false);
            this.enderreeper = enderreeper;
            this.field_220791_m = (new EntityPredicate()).setDistance(this.getTargetDistance()).setCustomPredicate((p_220790_1_) -> {
                return enderreeper.shouldAttackPlayer((PlayerEntity)p_220790_1_);
            });
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            this.player = this.enderreeper.world.getClosestPlayer(this.field_220791_m, this.enderreeper);
            return this.player != null;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.aggroTime = 5;
            this.teleportTime = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            this.player = null;
            super.resetTask();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            if (this.player != null) {
                if (!this.enderreeper.shouldAttackPlayer(this.player)) {
                    return false;
                } else {
                    this.enderreeper.faceEntity(this.player, 6.0F, 6.0F);
                    return true;
                }
            } else {
                return this.nearestTarget != null && this.field_220792_n.canTarget(this.enderreeper, this.nearestTarget) ? true : super.shouldContinueExecuting();
            }
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.player != null) {
                if (--this.aggroTime <= 0) {
                    this.nearestTarget = this.player;
                    this.player = null;
                    super.startExecuting();
                }
            } else {
                if (this.nearestTarget != null && !this.enderreeper.isPassenger()) {
                    if (this.enderreeper.shouldAttackPlayer((PlayerEntity)this.nearestTarget)) {
                        if (this.nearestTarget.getDistanceSq(this.enderreeper) < 16.0D) {
                            this.enderreeper.teleportRandomly();
                        }
                        this.teleportTime = 0;
                    } else if (this.nearestTarget.getDistanceSq(this.enderreeper) > 256.0D && this.teleportTime++ >= 30 && this.enderreeper.teleportToEntity(this.nearestTarget)) {
                        this.teleportTime = 0;
                    }
                }
                super.tick();
            }
        }
    }
}
