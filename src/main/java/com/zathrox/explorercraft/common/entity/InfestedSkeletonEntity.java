package com.zathrox.explorercraft.common.entity;

import com.zathrox.explorercraft.core.config.EntityConfig;
import com.zathrox.explorercraft.core.registry.ExplorerEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

public class InfestedSkeletonEntity extends SkeletonEntity {

    public InfestedSkeletonEntity(EntityType<? extends SkeletonEntity> p_i50194_1_, World p_i50194_2_) {
        super(p_i50194_1_, p_i50194_2_);
    }

    public static void addSpawn() {
        ForgeRegistries.BIOMES.getValues().stream().forEach(InfestedSkeletonEntity::processSpawning);
    }

    private static void processSpawning(Biome biome) {
        if(biome.getCategory() == Biome.Category.JUNGLE && biome.getPrecipitation() != Biome.RainType.SNOW && EntityConfig.infested_skeleton_enabled.get()) {
            biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(ExplorerEntities.INFESTED_SKELETON, 15, 1, 1));
        }
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(17.0D);
    }

    protected AbstractArrowEntity func_213624_b(ItemStack p_213624_1_, float p_213624_2_) {
        AbstractArrowEntity lvt_3_1_ = super.func_213624_b(p_213624_1_, p_213624_2_);
        if (lvt_3_1_ instanceof ArrowEntity) {
            ((ArrowEntity)lvt_3_1_).addEffect(new EffectInstance(Effects.POISON, 300));
        }
        return lvt_3_1_;
    }
}
