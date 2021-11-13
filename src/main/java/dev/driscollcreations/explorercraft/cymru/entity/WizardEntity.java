//package dev.driscollcreations.explorercraft.cymru.entity;
//
//import com.google.common.collect.ImmutableMap;
//import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
//import dev.driscollcreations.explorercraft.util.ExplorerTrades;
//import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
//import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.entity.AgeableEntity;
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.Mob;
//import net.minecraft.entity.ai.attributes.Attributes;
//import net.minecraft.entity.ai.goal.*;
//import net.minecraft.entity.item.ExperienceOrbEntity;
//import net.minecraft.entity.merchant.IMerchant;
//import net.minecraft.entity.merchant.villager.AbstractVillager;
//import net.minecraft.entity.merchant.villager.VillagerTrades;
//import net.minecraft.entity.monster.CreeperEntity;
//import net.minecraft.entity.monster.IMob;
//import net.minecraft.entity.player.Player;
//import net.minecraft.entity.projectile.SmallFireballEntity;
//import net.minecraft.item.MerchantOffer;
//import net.minecraft.item.MerchantOffers;
//import net.minecraft.network.datasync.DataParameter;
//import net.minecraft.network.datasync.DataSerializers;
//import net.minecraft.network.datasync.EntityDataManager;
//import net.minecraft.particles.ParticleTypes;
//import net.minecraft.potion.Foods.;
//import net.minecraft.potion.Effects;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.sounds.SoundEvent;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.util.*;
//import net.minecraft.util.math.Math;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.Level;
//import net.minecraft.world.damagesource.DamageSource;
//import net.minecraft.world.effect.MobEffectInstance;
//import net.minecraft.world.effect.MobEffects;
//import net.minecraft.world.entity.*;
//import net.minecraft.world.entity.ai.attributes.Attributes;
//import net.minecraft.world.entity.ai.goal.Goal;
//import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
//import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
//import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
//import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
//import net.minecraft.world.entity.monster.Creeper;
//import net.minecraft.world.entity.npc.AbstractVillager;
//import net.minecraft.world.entity.npc.VillagerTrades;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.entity.projectile.SmallFireball;
//import net.minecraft.world.food.Foods;
//import net.minecraft.world.item.trading.Merchant;
//import net.minecraft.world.item.trading.MerchantOffer;
//import net.minecraft.world.item.trading.MerchantOffers;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.server.ServerLevel;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//import javax.annotation.Nullable;
//import java.util.EnumSet;
//
//public class WizardEntity extends AbstractVillager implements Merchant {
//
//    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.defineId(WizardEntity.class, DataSerializers.BOOLEAN);
//    public int spellTime;
//
//    public WizardEntity(EntityType<? extends WizardEntity> type, Level worldIn) {
//        super(type, worldIn);
//        this.maxUpStep = 1.0F;
//    }
//
//    protected void registerGoals() {
//        this.goalSelector.addGoal(0, new SwimGoal(this));
//        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
//        this.goalSelector.addGoal(1, new LookAtCustomerGoal(this));
//        this.goalSelector.addGoal(2, new WizardEntity.FireballAttackGoal(this));
//        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 0.35D));
//        this.goalSelector.addGoal(8, new OpenDoorGoal(this, true));
//        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 0.35D));
//        this.goalSelector.addGoal(9, new LookAtWithoutMovingGoal(this, Player.class, 3.0F, 1.0F));
//        this.goalSelector.addGoal(10, new LookAtGoal(this, Mob.class, 8.0F));
//        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)));
//        this.targetSelector.addGoal(10, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_234199_0_) -> {
//            return p_234199_0_ instanceof Mob && !(p_234199_0_ instanceof Creeper);
//        }));
//    }
//
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(ATTACKING, Boolean.FALSE);
//    }
//
//
//    /* TRADING CODE - Similar to wandering trader */
//    public boolean showProgressBar() {
//        return false;
//    }
//
//    public InteractionResult mobInteract(Player player, InteractionHand hand) {
//
//        if (this.isAlive() && !this.isTrading() && !this.isBaby()) {
//
//            if (this.getOffers().isEmpty()) {
//                this.setUnhappy();
//                return InteractionResult.sidedSuccess(this.level.isClientSide);
//            } else {
//                if (!this.level.isClientSide) {
//                    if (random.nextInt(5) == 0) {
//                        this.offers = null;
//                        this.setUnhappy();
//                    }
//                    this.setTradingPlayer(player);
//                    this.openTradingScreen(player, this.getDisplayName(), 1);
//                }
//
//                return InteractionResult.sidedSuccess(this.level.isClientSide);
//            }
//        } else {
//            return super.mobInteract(player, hand);
//        }
//    }
//
//    @Override
//    protected void rewardTradeXp(MerchantOffer merchantOffer) {
//        if (merchantOffer.shouldRewardExp()) {
//            int i = 3 + this.random.nextInt(4);
//            this.level.addFreshEntity(new ExperienceOrb(this.level, this.getX(), this.getY() + 0.5D, this.getZ(), i));
//        }
//    }
//
//    @Override
//    protected void updateTrades() {
//        VillagerTrades.ItemListing[] itemsForScales = WizardTrades.WIZARD_TRADES.get(1);
//        VillagerTrades.ItemListing[] scalesForItems = WizardTrades.WIZARD_TRADES.get(2);
//        VillagerTrades.ItemListing[] scalesForWelshItems = WizardTrades.WIZARD_TRADES.get(3);
//        if (itemsForScales != null && scalesForItems != null && scalesForWelshItems != null) {
//            MerchantOffers merchantoffers = this.getOffers();
//            this.addOffersFromItemListings(merchantoffers, itemsForScales, 5);
//            this.addOffersFromItemListings(merchantoffers, scalesForItems, 3);
//            this.addOffersFromItemListings(merchantoffers, scalesForWelshItems, 2);
//        }
//    }
//
//    private void setUnhappy() {
//        this.setUnhappyCounter(40);
//        if (!this.level.isClientSide()) {
//            this.playSound(SoundEvents.VILLAGER_NO, this.getSoundVolume(), this.getVoicePitch());
//        }
//    }
//
//    @Nullable
//    @Override
//    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob AgeableMob) {
//        return null;
//    }
//
//    @Override
//    public void tick() {
//        super.tick();
//        if (this.getUnhappyCounter() > 0) {
//            this.setUnhappyCounter(this.getUnhappyCounter() - 1);
//        }
//
//        if (this.level.isClientSide && this.isCharged()) {
//            double d0 = 0.7D;
//            double d1 = 0.5D;
//            double d2 = 0.2D;
//            double f = this.yBodyRot * ((float)Math.PI / 180F) + Math.cos((float)this.tickCount * 0.6662F) * 0.25F;
//            double f1 = Math.cos(f);
//            double f2 = Math.sin(f);
//            this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() + (double)f1 * 0.6D, this.getY() + 1.8D, this.getZ() + (double)f2 * 0.6D, d0, d1, d2);
//            this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() - (double)f1 * 0.6D, this.getY() + 1.8D, this.getZ() - (double)f2 * 0.6D, d0, d1, d2);
//        }
//    }
//
//    @Override
//    public void aiStep() {
//        if (!this.level.isClientSide) {
//            if (this.getHealth() > 0) {
//                if (this.getHealth() < 10) {
//                    spellTime++;
//                    if (spellTime > 31 && this.getHealth() <= 10) {
//                        this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100));
//                        spellTime = 1;
//                    }
//                }
//            }
//        }
//        super.aiStep();
//    }
//
//    /* ATTACKING CODE - Similar to blaze */
//    static class FireballAttackGoal extends Goal {
//        private final WizardEntity wizard;
//        private int attackStep;
//        private int attackTime;
//        private int lastSeen;
//
//        public FireballAttackGoal(WizardEntity wizard) {
//            this.wizard = wizard;
//            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
//        }
//
//        public boolean canUse() {
//            LivingEntity livingentity = this.wizard.getTarget();
//            return livingentity != null && livingentity.isAlive() && this.wizard.canAttack(livingentity);
//        }
//
//        public void start() {
//            this.attackStep = 0;
//        }
//
//        public void stop() {
//            this.wizard.setCharged(false);
//            this.lastSeen = 0;
//        }
//
//        public void tick() {
//            --this.attackTime;
//            LivingEntity livingentity = this.wizard.getTarget();
//            if (livingentity != null) {
//                boolean flag = this.wizard.getSensing().hasLineOfSight(livingentity);
//                if (flag) {
//                    this.lastSeen = 0;
//                } else {
//                    ++this.lastSeen;
//                }
//
//                double d0 = this.wizard.distanceToSqr(livingentity);
//                if (d0 < 4.0D) {
//                    if (!flag) {
//                        return;
//                    }
//
//                    if (this.attackTime <= 0) {
//                        this.attackTime = 20;
//                        this.wizard.doHurtTarget(livingentity);
//                    }
//
//                    this.wizard.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
//                } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
//                    double d1 = livingentity.getX() - this.wizard.getX();
//                    double d2 = livingentity.getY(0.5D) - this.wizard.getY(0.5D);
//                    double d3 = livingentity.getZ() - this.wizard.getZ();
//                    if (this.attackTime <= 0) {
//                        ++this.attackStep;
//                        if (this.attackStep == 1) {
//                            this.attackTime = 60;
//                        } else if (this.attackStep <= 4) {
//                            this.attackTime = 6;
//                        } else {
//                            this.attackTime = 100;
//                            this.attackStep = 0;
//                        }
//
//                        if (this.attackStep > 1) {
//                            double d = Math.sqrt(Math.sqrt(d0)) * 0.5F;
//                            this.wizard.level.levelEvent((Player) null, 1018, this.wizard.blockPosition(), 0);
//                            for (int i = 0; i < 1; ++i) {
//                                SmallFireball smallfireballentity = new SmallFireball(this.wizard.level, this.wizard, d1 + this.wizard.getRandom().nextGaussian() * (double) d, d2, d3 + this.wizard.getRandom().nextGaussian() * (double) d);
//                                smallfireballentity.setPos(smallfireballentity.getX(), this.wizard.getY(0.5D) + 0.5D, smallfireballentity.getZ());
//                                this.wizard.level.addFreshEntity(smallfireballentity);
//                            }
//                        }
//                    }
//                    this.wizard.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
//                } else if (this.lastSeen < 5) {
//                    this.wizard.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
//                }
//                this.wizard.setCharged(this.attackStep > 1);
//                super.tick();
//            }
//        }
//
//        private double getFollowDistance() {
//            return this.wizard.getAttributeValue(Attributes.FOLLOW_RANGE);
//        }
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public boolean isCharged() {
//        return this.entityData.get(ATTACKING);
//    }
//
//    private void setCharged(boolean attacking) {
//        this.entityData.set(ATTACKING, attacking);
//    }
//
//    /* WIZARD TRAITS AND SOUNDS CODE */
//    @Override
//    public boolean removeWhenFarAway(double p_213397_1_) {
//        return false;
//    }
//
//    protected SoundEvent getAmbientSound() {
//        return this.isTrading() ? SoundEvents.WANDERING_TRADER_TRADE : SoundEvents.WANDERING_TRADER_AMBIENT;
//    }
//
//    protected SoundEvent getHurtSound(DamageSource damageSource) {
//        return SoundEvents.WANDERING_TRADER_HURT;
//    }
//
//    protected SoundEvent getDeathSound() {
//        return SoundEvents.WANDERING_TRADER_DEATH;
//    }
//
//    protected SoundEvent getTradeUpdatedSound(boolean p_213721_1_) {
//        return p_213721_1_ ? SoundEvents.WANDERING_TRADER_YES : SoundEvents.WANDERING_TRADER_NO;
//    }
//
//    public SoundEvent getNotifyTradeSound() {
//        return SoundEvents.WANDERING_TRADER_YES;
//    }
//
//}