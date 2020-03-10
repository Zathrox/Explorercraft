package com.zathrox.explorercraft.common.entity;

import com.zathrox.explorercraft.core.registry.ExplorerBannerPattern;
import com.zathrox.explorercraft.core.util.ExplorerTrades;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class WizardEntity extends AbstractVillagerEntity implements IMerchant
{
    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.<Boolean>createKey(WizardEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.<Boolean>createKey(WizardEntity.class, DataSerializers.BOOLEAN);
    public int spellTime;
	
    public WizardEntity(EntityType<? extends WizardEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1.0F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new LookAtCustomerGoal(this));
        this.goalSelector.addGoal(2, new WizardEntity.FireballAttackGoal(this));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 0.35D));
        this.goalSelector.addGoal(8, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, VillagerEntity.class, 8.0F));
        this.goalSelector.addGoal(9, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(10, new NearestAttackableTargetGoal(this, MobEntity.class, true));
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageableEntity) {
        return null;
    }

    protected void registerData()
    {
        super.registerData();
        this.dataManager.register(SWINGING_ARMS, Boolean.valueOf(false));
        this.dataManager.register(ATTACKING, Boolean.valueOf(false));
    }

    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getHeldItem(hand);
        if(heldItem.getItem() == Items.NAME_TAG)
        {
            heldItem.interactWithEntity(player, this, hand);
            return true;
        } else if (this.isAlive() && !this.hasCustomer() && !this.isChild()) {

            if (this.getOffers().isEmpty()) {
                this.shakeHead();
                return super.processInteract(player, hand);
            } else {
                if (!this.world.isRemote) {
                    this.setCustomer(player);
                    this.openMerchantContainer(player, this.getDisplayName(), 1);
                }
                return true;
            }
        } else {
            this.shakeHead();
            return super.processInteract(player, hand);
        }
    }

    private void shakeHead() {
        this.setShakeHeadTicks(40);
        if (!this.world.isRemote()) {
            this.playSound(SoundEvents.ENTITY_VILLAGER_NO, this.getSoundVolume(), this.getSoundPitch());
        }

    }

    public static ItemStack createWelshFlagBanner() {
        ItemStack banner = new ItemStack(Items.WHITE_BANNER);
        CompoundNBT tag = banner.getOrCreateChildTag("BlockEntityTag");
        ListNBT lvt_2_1_ = (new BannerPattern.Builder()).func_222477_a(BannerPattern.HALF_VERTICAL, DyeColor.GREEN).func_222477_a(ExplorerBannerPattern.WELSH_FLAG, DyeColor.RED).func_222476_a();
        tag.put("Patterns", lvt_2_1_);
        banner.setDisplayName((new TranslationTextComponent("block.explorercraf.welsh_flag", new Object[0])).applyTextStyle(TextFormatting.DARK_RED));
        return banner;
    }

    @Override
    protected void onVillagerTrade(MerchantOffer merchantOffer) {
        if (merchantOffer.getDoesRewardExp()) {
            int exp = 3 + this.rand.nextInt(4);
            this.world.addEntity(new ExperienceOrbEntity(this.world, this.getPosX(), this.getPosY() + 0.5D, this.getPosZ(), exp));
        }
    }

    @Override
    public boolean func_213705_dZ() {
        return false;
    }

    protected static final int BASE_TRADES = 0;
    protected static final int RARE_TRADES = 1;

    @Override
    public MerchantOffers getOffers() {
        if(this.offers == null)
        {
            this.offers = new MerchantOffers();
            this.populateTradeData();
        }
        return this.offers;
    }


    @Override
    protected void populateTradeData() {
        MerchantOffers offers = this.getOffers();
        this.addTrades(offers, ExplorerTrades.WIZARD_TRADER.get(BASE_TRADES), Math.max(4, this.rand.nextInt(6) + 1));
        this.addTrades(offers, ExplorerTrades.WIZARD_TRADER.get(RARE_TRADES), Math.max(2, this.rand.nextInt(3) + 1));
    }

    @Override
    public void livingTick()
    {
    	if (this.world.isRemote)
        {
    		int x = MathHelper.floor(getPosX());
        	int y = MathHelper.floor(getPosY()) + 1;
        	int z = MathHelper.floor(getPosZ());
    		if (this.getHealth() > 0)
			{
    			if (this.getHealth() < 10){
    				spellTime++;
    				if (spellTime > 31 && this.getHealth() <= 10)
    				{
    					this.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100));
    					spellTime = 1;
    				}
    			}
			}
        }

        super.livingTick();
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
    }

    @Override
	public void tick()
	{
		super.tick();
        if (this.getShakeHeadTicks() > 0) {
            this.setShakeHeadTicks(this.getShakeHeadTicks() - 1);
        }

        if (this.world.isRemote && this.isAttacking())
        {
            double d0 = 0.7D;
            double d1 = 0.5D;
            double d2 = 0.2D;
            float f = this.renderYawOffset * 0.017453292F + MathHelper.cos((float)this.ticksExisted * 0.6662F) * 0.25F;
            float f1 = MathHelper.cos(f);
            float f2 = MathHelper.sin(f);
            this.world.addParticle(ParticleTypes.ENTITY_EFFECT, this.getPosX() + (double)f1 * 0.6D, this.getPosY() + 1.8D, this.getPosZ() + (double)f2 * 0.6D, d0, d1, d2);
            this.world.addParticle(ParticleTypes.ENTITY_EFFECT, this.getPosX() - (double)f1 * 0.6D, this.getPosY() + 1.8D, this.getPosZ() - (double)f2 * 0.6D, d0, d1, d2);
        }

	}

    @Override
    public boolean canDespawn(double p_213397_1_) {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return this.hasCustomer() ? SoundEvents.ENTITY_WANDERING_TRADER_TRADE : SoundEvents.ENTITY_WANDERING_TRADER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.ENTITY_WANDERING_TRADER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WANDERING_TRADER_DEATH;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.74F;
    }

    static class FireballAttackGoal extends Goal {
        private final WizardEntity wizardEntity;
        private int attackStep;
        private int attackTime;
        private int field_223527_d;

        public FireballAttackGoal(WizardEntity wizardIn) {
            this.wizardEntity = wizardIn;
            this.setMutexFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean shouldExecute() {
            LivingEntity livingEntity = this.wizardEntity.getAttackTarget();
            if(livingEntity instanceof WizardEntity) return false;
            return livingEntity != null && livingEntity.isAlive() && this.wizardEntity.canAttack(livingEntity);
        }

        public void startExecuting() {
            this.attackStep = 0;
        }

        public void resetTask() {
            this.wizardEntity.setAttacking(false);
            this.field_223527_d = 0;
        }

        public void tick() {
            --this.attackTime;
            LivingEntity livingEntity = this.wizardEntity.getAttackTarget();
            if (livingEntity != null) {
                boolean canSee = this.wizardEntity.getEntitySenses().canSee(livingEntity);
                if (canSee) {
                    this.field_223527_d = 0;
                } else {
                    ++this.field_223527_d;
                }

                double d0 = this.wizardEntity.getDistanceSq(livingEntity);
                if (d0 < 4.0D) {
                    if (!canSee) {
                        return;
                    }

                    if (this.attackTime <= 0) {
                        this.attackTime = 20;
                        livingEntity.attackEntityFrom(DamageSource.MAGIC, 6.0f);
                    }

                    this.wizardEntity.getMoveHelper().setMoveTo(livingEntity.getPosX(), livingEntity.getPosY(), livingEntity.getPosZ(), 1.0D);
                } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && canSee) {
                    double d1 = livingEntity.getPosX() - this.wizardEntity.getPosX();
                    double d2 = livingEntity.getBoundingBox().minY + (double)(livingEntity.getHeight() / 2.0F) - (this.wizardEntity.getPosY() + (double)(this.wizardEntity.getHeight() / 2.0F));
                    double d3 = livingEntity.getPosZ() - this.wizardEntity.getPosZ();
                    if (this.attackTime <= 0) {
                        ++this.attackStep;
                        if (this.attackStep == 1) {
                            this.attackTime = 60;
                        } else if (this.attackStep <= 10) {
                            this.attackTime = 6;
                        } else {
                            this.attackTime = 100;
                            this.attackStep = 0;
                        }

                        if (this.attackStep > 1) {
                            float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                            this.wizardEntity.world.playEvent((PlayerEntity)null, 1018, new BlockPos(this.wizardEntity), 0);
                            SmallFireballEntity fireballEntity = new SmallFireballEntity(this.wizardEntity.world, this.wizardEntity, d1 , d2, d3);
                            fireballEntity.setPosition(fireballEntity.getPosX(), this.wizardEntity.getPosYHeight(0.5D) + 0.5D, fireballEntity.getPosZ());
                            this.wizardEntity.world.addEntity(fireballEntity);

                        }
                    }

                    this.wizardEntity.getLookController().setLookPositionWithEntity(livingEntity, 10.0F, 10.0F);
                } else if (this.field_223527_d < 5) {
                    this.wizardEntity.getMoveHelper().setMoveTo(livingEntity.getPosX(), livingEntity.getPosY(), livingEntity.getPosZ(), 1.0D);
                }
                this.wizardEntity.setAttacking(this.attackStep > 1);
                super.tick();
            }
        }

        private double getFollowDistance() {
            return this.wizardEntity.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getValue();
        }
    }


    @OnlyIn(Dist.CLIENT)
    public boolean isAttacking()
    {
        return ((Boolean)this.dataManager.get(ATTACKING)).booleanValue();
    }

    public void setAttacking(boolean attacking)
    {
        this.dataManager.set(ATTACKING, Boolean.valueOf(attacking));
    }
}
