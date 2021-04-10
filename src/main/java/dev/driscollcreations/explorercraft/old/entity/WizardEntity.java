/*
package dev.driscollcreations.explorercraft.old.entity;

import com.zathrox.explorercraft.core.registry.ExplorerBannerPattern;
import com.zathrox.explorercraft.core.util.ExplorerTrades;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
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
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WizardEntity extends AbstractVillagerEntity implements IMerchant {
    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.defineId(WizardEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.defineId(WizardEntity.class, DataSerializers.BOOLEAN);
    public int spellTime;

    public WizardEntity(EntityType<? extends WizardEntity> type, World worldIn) {
        super(type, worldIn);
        this.maxUpStep = 1.0F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new LookAtCustomerGoal(this));
        this.goalSelector.addGoal(2, new FireballAttackGoal(this));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 0.35D));
        this.goalSelector.addGoal(8, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, VillagerEntity.class, 8.0F));
        this.goalSelector.addGoal(9, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(10, new NearestAttackableTargetGoal<>(this, MobEntity.class, true));
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageableEntity) {
        return null;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SWINGING_ARMS, Boolean.valueOf(false));
        this.entityData.define(ATTACKING, Boolean.valueOf(false));
    }

    //Trading

    protected static final int BASE_TRADES = 0;
    protected static final int RARE_TRADES = 1;
    protected static final int WELSH_TRADES = 2;

    @Override
    public MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.updateTrades();
        }
        return this.offers;
    }

    @Override
    public void notifyTrade(MerchantOffer offer) {
        offer.increaseUses();
        this.ambientSoundTime = -this.getAmbientSoundInterval();
        this.rewardTradeXp(offer);
        if (this.random.nextInt(5) == 0) {
            this.offers.clear();
            this.getOffers();
            this.updateTrades();
            this.addParticlesAroundSelf(ParticleTypes.HAPPY_VILLAGER);
        }
    }

    @Override
    protected void rewardTradeXp(MerchantOffer merchantOffer) {
        if (merchantOffer.shouldRewardExp()) {
            int exp = 3 + this.random.nextInt(4);
            this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.getX(), this.getY() + 0.5D, this.getZ(), exp));
        }
    }

    @Override
    public boolean showProgressBar() {
        return false;
    }


    @Override
    protected void updateTrades() {
        MerchantOffers offers = this.getOffers();
        this.addOffersFromItemListings(offers, ExplorerTrades.WIZARD_TRADER.get(BASE_TRADES), Math.max(4, this.random.nextInt(6) + 1));
        this.addOffersFromItemListings(offers, ExplorerTrades.WIZARD_TRADER.get(RARE_TRADES), Math.max(1, this.random.nextInt(1) + 1));
        this.addOffersFromItemListings(offers, ExplorerTrades.WIZARD_TRADER.get(WELSH_TRADES), Math.max(1, this.random.nextInt(2) + 1));
    }

    @Override
    protected void addOffersFromItemListings(MerchantOffers offers, @Nullable VillagerTrades.ITrade[] trades, int max) {
        if (trades == null)
            return;
        List<Integer> randomIndexes = IntStream.range(0, trades.length).boxed().collect(Collectors.toList());
        Collections.shuffle(randomIndexes);
        randomIndexes = randomIndexes.subList(0, Math.min(trades.length, max));
        for (Integer index : randomIndexes) {
            VillagerTrades.ITrade trade = trades[index];
            MerchantOffer offer = trade.getOffer(this, this.random);
            if (offer != null) {
                offers.add(offer);
            }
        }
    }


    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        if (heldItem.getItem() == Items.NAME_TAG) {
            heldItem.interactLivingEntity(player, this, hand);
            return true;
        } else if (this.isAlive() && !this.isTrading() && !this.isBaby()) {

            if (this.getOffers().isEmpty()) {
                this.shakeHead();
                return super.checkAndHandleImportantInteractions(player, hand);
            } else {
                if (!this.level.isClientSide) {
                    this.setTradingPlayer(player);
                    this.openTradingScreen(player, this.getDisplayName(), 1);
                }
                return true;
            }
        } else {
            this.shakeHead();
            return super.checkAndHandleImportantInteractions(player, hand);
        }
    }

    private void shakeHead() {
        this.setUnhappyCounter(40);
        if (!this.level.isClientSide()) {
            this.playSound(SoundEvents.VILLAGER_NO, this.getSoundVolume(), this.getVoicePitch());
        }

    }

    public static ItemStack createWelshFlagBanner() {
        ItemStack banner = new ItemStack(Items.WHITE_BANNER);
        CompoundNBT tag = banner.getOrCreateTagElement("BlockEntityTag");
        ListNBT lvt_2_1_ = (new BannerPattern.Builder()).addPattern(BannerPattern.HALF_VERTICAL, DyeColor.GREEN).addPattern(ExplorerBannerPattern.WELSH_FLAG, DyeColor.RED).toListTag();
        tag.put("Patterns", lvt_2_1_);
        banner.setHoverName((new TranslationTextComponent("block.explorercraf.welsh_flag")).applyTextStyle(TextFormatting.DARK_RED));
        return banner;
    }


    @Override
    public void aiStep() {
        if (this.level.isClientSide) {
            int x = MathHelper.floor(getX());
            int y = MathHelper.floor(getY()) + 1;
            int z = MathHelper.floor(getZ());
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

    @Override
    protected void registerAttributes() {
        super.createLivingAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getUnhappyCounter() > 0) {
            this.setUnhappyCounter(this.getUnhappyCounter() - 1);
        }

        if (this.level.isClientSide && this.isAttacking()) {
            double d0 = 0.7D;
            double d1 = 0.5D;
            double d2 = 0.2D;
            float f = this.yBodyRot * 0.017453292F + MathHelper.cos((float) this.tickCount * 0.6662F) * 0.25F;
            float f1 = MathHelper.cos(f);
            float f2 = MathHelper.sin(f);
            this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() + (double) f1 * 0.6D, this.getY() + 1.8D, this.getZ() + (double) f2 * 0.6D, d0, d1, d2);
            this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() - (double) f1 * 0.6D, this.getY() + 1.8D, this.getZ() - (double) f2 * 0.6D, d0, d1, d2);
        }

    }

    @Override
    public boolean removeWhenFarAway(double p_213397_1_) {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return this.isTrading() ? SoundEvents.WANDERING_TRADER_TRADE : SoundEvents.WANDERING_TRADER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.WANDERING_TRADER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.WANDERING_TRADER_DEATH;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.74F;
    }

    static class FireballAttackGoal extends Goal {
        private final WizardEntity wizardEntity;
        private int attackStep;
        private int attackTime;
        private int lastSeen;

        public FireballAttackGoal(WizardEntity wizardIn) {
            this.wizardEntity = wizardIn;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity livingEntity = this.wizardEntity.getTarget();
            if (livingEntity instanceof WizardEntity) return false;
            return livingEntity != null && livingEntity.isAlive() && this.wizardEntity.canAttack(livingEntity);
        }

        public void start() {
            this.attackStep = 0;
        }

        public void stop() {
            this.wizardEntity.setAttacking(false);
            this.lastSeen = 0;
        }

        public void tick() {
            --this.attackTime;
            LivingEntity livingEntity = this.wizardEntity.getTarget();
            if (livingEntity != null) {
                boolean canSee = this.wizardEntity.getSensing().canSee(livingEntity);
                if (canSee) {
                    this.lastSeen = 0;
                } else {
                    ++this.lastSeen;
                }

                double d0 = this.wizardEntity.distanceToSqr(livingEntity);
                if (d0 < 4.0D) {
                    if (!canSee) {
                        return;
                    }

                    if (this.attackTime <= 0) {
                        this.attackTime = 20;
                        livingEntity.hurt(DamageSource.MAGIC, 6.0f);
                    }

                    this.wizardEntity.getMoveControl().setWantedPosition(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0D);
                } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && canSee) {
                    double d1 = livingEntity.getX() - this.wizardEntity.getX();
                    double d2 = livingEntity.getBoundingBox().minY + (double) (livingEntity.getBbHeight() / 2.0F) - (this.wizardEntity.getY() + (double) (this.wizardEntity.getBbHeight() / 2.0F));
                    double d3 = livingEntity.getZ() - this.wizardEntity.getZ();
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
                            this.wizardEntity.level.levelEvent(null, 1018, new BlockPos(this.wizardEntity), 0);
                            SmallFireballEntity fireballEntity = new SmallFireballEntity(this.wizardEntity.level, this.wizardEntity, d1, d2, d3);
                            fireballEntity.setPos(fireballEntity.getX(), this.wizardEntity.getY(0.5D) + 0.5D, fireballEntity.getZ());
                            this.wizardEntity.level.addFreshEntity(fireballEntity);

                        }
                    }

                    this.wizardEntity.getLookControl().setLookAt(livingEntity, 10.0F, 10.0F);
                } else if (this.lastSeen < 5) {
                    this.wizardEntity.getMoveControl().setWantedPosition(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0D);
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
    public boolean isAttacking() {
        return this.entityData.get(ATTACKING).booleanValue();
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, Boolean.valueOf(attacking));
    }
}
*/
