package dev.driscollcreations.explorercraft.cymru.entity;

import com.google.common.collect.ImmutableMap;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.util.ExplorerTrades;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class WizardEntity extends AbstractVillagerEntity implements IMerchant {

    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.defineId(WizardEntity.class, DataSerializers.BOOLEAN);
    public int spellTime;

    public WizardEntity(EntityType<? extends WizardEntity> type, World worldIn) {
        super(type, worldIn);
        this.maxUpStep = 1.0F;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new LookAtCustomerGoal(this));
        this.goalSelector.addGoal(2, new WizardEntity.FireballAttackGoal(this));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 0.35D));
        this.goalSelector.addGoal(8, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 0.35D));
        this.goalSelector.addGoal(9, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)));
        this.targetSelector.addGoal(10, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (p_234199_0_) -> {
            return p_234199_0_ instanceof IMob && !(p_234199_0_ instanceof CreeperEntity);
        }));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, Boolean.FALSE);
    }


    /* TRADING CODE - Similar to wandering trader */
    public boolean showProgressBar() {
        return false;
    }

    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {

        if (this.isAlive() && !this.isTrading() && !this.isBaby()) {

            if (this.getOffers().isEmpty()) {
                this.setUnhappy();
                return ActionResultType.sidedSuccess(this.level.isClientSide);
            } else {
                if (!this.level.isClientSide) {
                    if (random.nextInt(5) == 0) {
                        this.offers = null;
                        this.setUnhappy();
                    }
                    this.setTradingPlayer(player);
                    this.openTradingScreen(player, this.getDisplayName(), 1);
                }

                return ActionResultType.sidedSuccess(this.level.isClientSide);
            }
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    protected void rewardTradeXp(MerchantOffer merchantOffer) {
        if (merchantOffer.shouldRewardExp()) {
            int i = 3 + this.random.nextInt(4);
            this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }
    }

    @Override
    protected void updateTrades() {
        VillagerTrades.ITrade[] itemsForScales = WizardTrades.WIZARD_TRADES.get(1);
        VillagerTrades.ITrade[] scalesForItems = WizardTrades.WIZARD_TRADES.get(2);
        VillagerTrades.ITrade[] scalesForWelshItems = WizardTrades.WIZARD_TRADES.get(3);
        if (itemsForScales != null && scalesForItems != null && scalesForWelshItems != null) {
            MerchantOffers merchantoffers = this.getOffers();
            this.addOffersFromItemListings(merchantoffers, itemsForScales, 5);
            this.addOffersFromItemListings(merchantoffers, scalesForItems, 3);
            this.addOffersFromItemListings(merchantoffers, scalesForWelshItems, 2);
        }
    }

    private void setUnhappy() {
        this.setUnhappyCounter(40);
        if (!this.level.isClientSide()) {
            this.playSound(SoundEvents.VILLAGER_NO, this.getSoundVolume(), this.getVoicePitch());
        }
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld serverWorld, AgeableEntity ageableEntity) {
        return null;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getUnhappyCounter() > 0) {
            this.setUnhappyCounter(this.getUnhappyCounter() - 1);
        }

        if (this.level.isClientSide && this.isCharged()) {
            double d0 = 0.7D;
            double d1 = 0.5D;
            double d2 = 0.2D;
            float f = this.yBodyRot * ((float)Math.PI / 180F) + MathHelper.cos((float)this.tickCount * 0.6662F) * 0.25F;
            float f1 = MathHelper.cos(f);
            float f2 = MathHelper.sin(f);
            this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() + (double)f1 * 0.6D, this.getY() + 1.8D, this.getZ() + (double)f2 * 0.6D, d0, d1, d2);
            this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() - (double)f1 * 0.6D, this.getY() + 1.8D, this.getZ() - (double)f2 * 0.6D, d0, d1, d2);
        }
    }

    @Override
    public void aiStep() {
        if (!this.level.isClientSide) {
            if (this.getHealth() > 0) {
                if (this.getHealth() < 10) {
                    spellTime++;
                    if (spellTime > 31 && this.getHealth() <= 10) {
                        this.addEffect(new EffectInstance(Effects.REGENERATION, 100));
                        spellTime = 1;
                    }
                }
            }
        }
        super.aiStep();
    }

    /* ATTACKING CODE - Similar to blaze */
    static class FireballAttackGoal extends Goal {
        private final WizardEntity wizard;
        private int attackStep;
        private int attackTime;
        private int lastSeen;

        public FireballAttackGoal(WizardEntity wizard) {
            this.wizard = wizard;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity livingentity = this.wizard.getTarget();
            return livingentity != null && livingentity.isAlive() && this.wizard.canAttack(livingentity);
        }

        public void start() {
            this.attackStep = 0;
        }

        public void stop() {
            this.wizard.setCharged(false);
            this.lastSeen = 0;
        }

        public void tick() {
            --this.attackTime;
            LivingEntity livingentity = this.wizard.getTarget();
            if (livingentity != null) {
                boolean flag = this.wizard.getSensing().canSee(livingentity);
                if (flag) {
                    this.lastSeen = 0;
                } else {
                    ++this.lastSeen;
                }

                double d0 = this.wizard.distanceToSqr(livingentity);
                if (d0 < 4.0D) {
                    if (!flag) {
                        return;
                    }

                    if (this.attackTime <= 0) {
                        this.attackTime = 20;
                        this.wizard.doHurtTarget(livingentity);
                    }

                    this.wizard.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
                } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
                    double d1 = livingentity.getX() - this.wizard.getX();
                    double d2 = livingentity.getY(0.5D) - this.wizard.getY(0.5D);
                    double d3 = livingentity.getZ() - this.wizard.getZ();
                    if (this.attackTime <= 0) {
                        ++this.attackStep;
                        if (this.attackStep == 1) {
                            this.attackTime = 60;
                        } else if (this.attackStep <= 4) {
                            this.attackTime = 6;
                        } else {
                            this.attackTime = 100;
                            this.attackStep = 0;
                        }

                        if (this.attackStep > 1) {
                            float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                            this.wizard.level.levelEvent((PlayerEntity) null, 1018, this.wizard.blockPosition(), 0);
                            for (int i = 0; i < 1; ++i) {
                                SmallFireballEntity smallfireballentity = new SmallFireballEntity(this.wizard.level, this.wizard, d1 + this.wizard.getRandom().nextGaussian() * (double) f, d2, d3 + this.wizard.getRandom().nextGaussian() * (double) f);
                                smallfireballentity.setPos(smallfireballentity.getX(), this.wizard.getY(0.5D) + 0.5D, smallfireballentity.getZ());
                                this.wizard.level.addFreshEntity(smallfireballentity);
                            }
                        }
                    }
                    this.wizard.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
                } else if (this.lastSeen < 5) {
                    this.wizard.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
                }
                this.wizard.setCharged(this.attackStep > 1);
                super.tick();
            }
        }

        private double getFollowDistance() {
            return this.wizard.getAttributeValue(Attributes.FOLLOW_RANGE);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isCharged() {
        return this.entityData.get(ATTACKING);
    }

    private void setCharged(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    /* WIZARD TRAITS AND SOUNDS CODE */
    @Override
    public boolean removeWhenFarAway(double p_213397_1_) {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return this.isTrading() ? SoundEvents.WANDERING_TRADER_TRADE : SoundEvents.WANDERING_TRADER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.WANDERING_TRADER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.WANDERING_TRADER_DEATH;
    }

    protected SoundEvent getTradeUpdatedSound(boolean p_213721_1_) {
        return p_213721_1_ ? SoundEvents.WANDERING_TRADER_YES : SoundEvents.WANDERING_TRADER_NO;
    }

    public SoundEvent getNotifyTradeSound() {
        return SoundEvents.WANDERING_TRADER_YES;
    }

}