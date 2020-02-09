package com.zathrox.explorercraft.common.entity;

import com.zathrox.explorercraft.core.registry.ExplorerBannerPattern;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import com.zathrox.explorercraft.core.registry.ExplorerItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
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
import net.minecraft.stats.Stats;
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
import net.minecraftforge.common.BasicTrade;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

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


    public boolean processInteract(PlayerEntity p_184645_1_, Hand held) {
        ItemStack itemStack = p_184645_1_.getHeldItem(held);
        boolean lvt_4_1_ = itemStack.getItem() == Items.NAME_TAG;
        if (lvt_4_1_) {
            itemStack.interactWithEntity(p_184645_1_, this, held);
            return true;
        } else if (itemStack.getItem() != Items.VILLAGER_SPAWN_EGG && this.isAlive() && !this.hasCustomer() && !this.isChild()) {
            if (held == Hand.MAIN_HAND) {
                p_184645_1_.addStat(Stats.TALKED_TO_VILLAGER);
            }

            if (this.getOffers().isEmpty()) {
                this.shakeHead();
                return super.processInteract(p_184645_1_, held);
            } else {
                if (!this.world.isRemote) {
                    this.setCustomer(p_184645_1_);
                    this.openMerchantContainer(p_184645_1_, this.getDisplayName(), 1);
                }

                return true;
            }
        } else {
            this.shakeHead();
            return super.processInteract(p_184645_1_, held);
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
            this.world.addEntity(new ExperienceOrbEntity(this.world, this.posX, this.posY + 0.5D, this.posZ, exp));
        }
    }

    @Override
    public boolean func_213705_dZ() {
        return false;
    }

    @Override
    public MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.populateTradeData();
        } else if (rand.nextInt(5) == 0) {
            this.offers.clear();
            this.populateTradeData();
        }
        return this.offers;
    }

    @Override
    protected void populateTradeData() {
        if (getRareLoot() != null && getCommonLoot() != null) {
            MerchantOffers offers = this.getOffers();
            offers.clear();
            int maxTrades = this.rand.nextInt(5) + 5;
            this.addTrades(offers, getCommonLoot(), maxTrades);
            int i = this.rand.nextInt(getRareLoot().size());
            MerchantOffer rareOffer = getRareLoot().get(i);
            if (rareOffer != null) {
                    offers.add(rareOffer);
            }
        }

    }

    public List<MerchantOffer> getRareLoot() {
        List<MerchantOffer> tradeList = new ArrayList<>();
        tradeList.add(new MerchantOffer(new ItemStack(ExplorerBlocks.DRAGON_HEART), new ItemStack(Items.IRON_INGOT, 64),  new ItemStack(ExplorerItems.WELSH_SHIELD), 1, 5, 5));
        tradeList.add(new MerchantOffer(new ItemStack(ExplorerBlocks.DRAGON_HEART), new ItemStack(Blocks.GRAY_WOOL, 64),  new ItemStack(ExplorerItems.WIZARD_HAT ), 1, 5, 1));
        tradeList.add(new MerchantOffer(new ItemStack(ExplorerBlocks.DRAGON_HEART), new ItemStack(ExplorerBlocks.ASH_LOG, 64),  new ItemStack(ExplorerItems.WIZARD_STAFF), 1, 5, 1));
        tradeList.add(new MerchantOffer(new ItemStack(ExplorerBlocks.DRAGON_HEART), new ItemStack(Items.WHITE_BANNER, 1),  createWelshFlagBanner(), 8, 5, 1));
        tradeList.add(new MerchantOffer(new ItemStack(ExplorerBlocks.DRAGON_HEART), new ItemStack(ExplorerBlocks.SLATE_CHISELED, 64),  new ItemStack(ExplorerBlocks.SLATE_WELSH, 64), 1, 5, 5));

        return tradeList;
    }

    public static VillagerTrades.ITrade[] getCommonLoot() {

        VillagerTrades.ITrade[] tradeListCommon = new VillagerTrades.ITrade[] {
                //=== Wandering Merchant Sales ===//
                new ItemsForGemTrade(Items.SEA_PICKLE, 2, 1, 5, 1),
                new ItemsForGemTrade(Items.SLIME_BALL, 4, 1, 5, 1),
                new ItemsForGemTrade(Items.GLOWSTONE, 2, 1, 5, 1),
                new ItemsForGemTrade(Items.NAUTILUS_SHELL, 5, 1, 5, 1),
                new ItemsForGemTrade(Items.FERN, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.SUGAR_CANE, 1, 1, 8, 1),
                new ItemsForGemTrade(Items.PUMPKIN, 1, 1, 4, 1),
                new ItemsForGemTrade(Items.KELP, 3, 1, 12, 1),
                new ItemsForGemTrade(Items.CACTUS, 3, 1, 8, 1),
                new ItemsForGemTrade(Items.POPPY, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.BLUE_ORCHID, 1, 1, 8, 1),
                new ItemsForGemTrade(Items.ALLIUM, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.AZURE_BLUET, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.RED_TULIP, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.ORANGE_TULIP, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.WHITE_TULIP, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.PINK_TULIP, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.OXEYE_DAISY, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.OXEYE_DAISY, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.CORNFLOWER, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.LILY_OF_THE_VALLEY, 1, 1, 7, 1),
                new ItemsForGemTrade(Items.WHEAT_SEEDS, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.BEETROOT_SEEDS, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.PUMPKIN_SEEDS, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.MELON_SEEDS, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.ACACIA_SAPLING, 5, 1, 8, 1),
                new ItemsForGemTrade(Items.BIRCH_SAPLING, 5, 1, 8, 1),
                new ItemsForGemTrade(Items.DARK_OAK_SAPLING, 5, 1, 8, 1),
                new ItemsForGemTrade(Items.JUNGLE_SAPLING, 5, 1, 8, 1),
                new ItemsForGemTrade(Items.OAK_SAPLING, 5, 1, 8, 1),
                new ItemsForGemTrade(Items.SPRUCE_SAPLING, 5, 1, 8, 1),
                new ItemsForGemTrade(Items.VINE, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.BROWN_MUSHROOM, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.RED_MUSHROOM, 1, 1, 12, 1),
                new ItemsForGemTrade(Items.LILY_PAD, 1, 2, 5, 1),
                new ItemsForGemTrade(Items.GUNPOWDER, 1, 1, 8, 1),
                //==== Random Vanilla Sales ===//
                new ItemsForGemTrade(Items.GHAST_TEAR, 2, 1, 3, 1),
                new ItemsForGemTrade(Items.FERMENTED_SPIDER_EYE, 1, 1, 3, 1),
                new ItemsForGemTrade(Items.BAMBOO, 3, 1, 12, 1),
                //==== Explorercraft Items ===//
                new ItemsForGemTrade(ExplorerItems.RICE, 2, 1, 5, 1),
                new ItemsForGemTrade(ExplorerItems.LEEK, 1, 1, 12, 1),
                new ItemsForGemTrade(ExplorerItems.CHEESE, 1, 1, 12, 1),
                new ItemsForGemTrade(ExplorerItems.DRIED_FRUITS, 1, 1, 12, 1),
                new ItemsForGemTrade(ExplorerBlocks.TATAMI, 5, 1, 1, 1),
                new ItemsForGemTrade(ExplorerItems.WELSHFLAG_BANNER_PATTERN, 5, 1, 1, 1),
                new ItemsForGemTrade(ExplorerBlocks.DAFFODIL, 1, 1, 8, 1),
                new ItemsForGemTrade(ExplorerBlocks.ASH_SAPLING, 10, 1, 8, 1),
                new ItemsForGemTrade(ExplorerBlocks.BAMBOO_SAPLING, 5, 1, 8, 1),
                new ItemsForGemTrade(ExplorerBlocks.CHERRY_SAPLING, 5, 1, 8, 1),
                new ItemsForGemTrade(ExplorerBlocks.MAPLE_SAPLING, 5, 1, 8, 1),
                new ItemsForGemTrade(ExplorerBlocks.WILLOW_SAPLING, 5, 1, 8, 1),
                new ItemsForGemTrade(ExplorerBlocks.LUPINE, 5, 1, 8, 1),
                new ItemsForGemTrade(ExplorerBlocks.GREEN_MUSHROOM, 5, 1, 8, 1),
                new ItemsForGemTrade(ExplorerBlocks.PINK_MUSHROOM, 5, 1, 8, 1),
                new ItemsForGemTrade(ExplorerBlocks.NOCTILUCAS, 5, 1, 8, 1),
                new ItemsForGemTrade(ExplorerItems.WELSHFLAG_BANNER_PATTERN, 20, 1, 1, 1)
        };
        return tradeListCommon;

/*        List<MerchantOffer> tradeList = new ArrayList<>();
        tradeList.add(new MerchantOffer(new ItemStack(ExplorerBlocks.JADE_BLOCK), new ItemStack(Items.GOLD_INGOT, 64),  new ItemStack(ExplorerItems.WELSH_SHIELD), 1, 5, 5));
        tradeList.add(new MerchantOffer(new ItemStack(ExplorerBlocks.RUBY_BLOCK), new ItemStack(Items.IRON_INGOT, 64),  new ItemStack(ExplorerItems.WELSH_SHIELD), 1, 5, 5));
        tradeList.add(new MerchantOffer(new ItemStack(ExplorerBlocks.AMETHYST_BLOCK), new ItemStack(Items.GOLD_INGOT, 64),  new ItemStack(ExplorerItems.WELSH_SHIELD), 1, 5, 5));
        return tradeList;*/
    }

    public static class ItemsForGemTrade implements VillagerTrades.ITrade {
        private final ItemStack field_221208_a;
        private final int amount;
        private final int field_221210_c;
        private final int field_221211_d;
        private final int field_221212_e;
        private final float field_221213_f;

        public ItemsForGemTrade(Block p_i50528_1_, int p_i50528_2_, int p_i50528_3_, int p_i50528_4_, int p_i50528_5_) {
            this(new ItemStack(p_i50528_1_), p_i50528_2_, p_i50528_3_, p_i50528_4_, p_i50528_5_);
        }

        public ItemsForGemTrade(Item p_i50529_1_, int p_i50529_2_, int p_i50529_3_, int p_i50529_4_) {
            this((ItemStack)(new ItemStack(p_i50529_1_)), p_i50529_2_, p_i50529_3_, 12, p_i50529_4_);
        }

        public ItemsForGemTrade(Item p_i50530_1_, int p_i50530_2_, int p_i50530_3_, int p_i50530_4_, int p_i50530_5_) {
            this(new ItemStack(p_i50530_1_), p_i50530_2_, p_i50530_3_, p_i50530_4_, p_i50530_5_);
        }

        public ItemsForGemTrade(ItemStack p_i50531_1_, int p_i50531_2_, int p_i50531_3_, int p_i50531_4_, int p_i50531_5_) {
            this(p_i50531_1_, p_i50531_2_, p_i50531_3_, p_i50531_4_, p_i50531_5_, 0.05F);
        }

        public ItemsForGemTrade(ItemStack p_i50532_1_, int amount, int p_i50532_3_, int p_i50532_4_, int p_i50532_5_, float p_i50532_6_) {
            this.field_221208_a = p_i50532_1_;
            this.amount = amount;
            this.field_221210_c = p_i50532_3_;
            this.field_221211_d = p_i50532_4_;
            this.field_221212_e = p_i50532_5_;
            this.field_221213_f = p_i50532_6_;
        }

        public MerchantOffer getOffer(Entity p_221182_1_, Random random) {
            int j = random.nextInt(getGems().size());
            return new MerchantOffer(new ItemStack(getGems().get(j), this.amount), new ItemStack(this.field_221208_a.getItem(), this.field_221210_c), this.field_221211_d, this.field_221212_e, this.field_221213_f);
        }
    }

    public static List<Item> getGems() {
        List<Item> gemList = new ArrayList<>();
        gemList.add(Items.DIAMOND);
        gemList.add(Items.EMERALD);
        gemList.add(ExplorerItems.AMETHYST);
        gemList.add(ExplorerItems.JADE);
        gemList.add(ExplorerItems.RUBY);
        return gemList;
    }


    @Override
    public void livingTick()
    {
    	if (this.world.isRemote)
        {
    		int x = MathHelper.floor(posX);
        	int y = MathHelper.floor(posY) + 1;
        	int z = MathHelper.floor(posZ);
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
            this.world.addParticle(ParticleTypes.ENTITY_EFFECT, this.posX + (double)f1 * 0.6D, this.posY + 1.8D, this.posZ + (double)f2 * 0.6D, d0, d1, d2);
            this.world.addParticle(ParticleTypes.ENTITY_EFFECT, this.posX - (double)f1 * 0.6D, this.posY + 1.8D, this.posZ - (double)f2 * 0.6D, d0, d1, d2);
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

                    this.wizardEntity.getMoveHelper().setMoveTo(livingEntity.posX, livingEntity.posY, livingEntity.posZ, 1.0D);
                } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && canSee) {
                    double d1 = livingEntity.posX - this.wizardEntity.posX;
                    double d2 = livingEntity.getBoundingBox().minY + (double)(livingEntity.getHeight() / 2.0F) - (this.wizardEntity.posY + (double)(this.wizardEntity.getHeight() / 2.0F));
                    double d3 = livingEntity.posZ - this.wizardEntity.posZ;
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
                            fireballEntity.posY = this.wizardEntity.posY + (double)(this.wizardEntity.getHeight() / 2.0F) + 0.5D;
                            this.wizardEntity.world.addEntity(fireballEntity);

                        }
                    }

                    this.wizardEntity.getLookController().setLookPositionWithEntity(livingEntity, 10.0F, 10.0F);
                } else if (this.field_223527_d < 5) {
                    this.wizardEntity.getMoveHelper().setMoveTo(livingEntity.posX, livingEntity.posY, livingEntity.posZ, 1.0D);
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
