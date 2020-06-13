package com.zathrox.explorercraft.core.events;

import com.zathrox.explorercraft.common.blocks.BlockSleepingBag;
import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.registry.ExplorerEnchantments;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Map;

@EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public final class ExplorerPlayerEvents {

   /* @SubscribeEvent
    public static void onPlayerSetSpawn(PlayerSetSpawnEvent event) {
        ModList.get().getModContainerById("better_respawn");
        PlayerEntity player = event.getPlayer();
        if (player.getEntityWorld().getBlockState(event.getNewSpawn()).getBlock() instanceof BlockSleepingBag) {
            event.setCanceled(true);
            System.out.println("Spawn Cancelled");
        }
    }*/

    @SubscribeEvent
    public void onPlayerSetSpawn(PlayerSetSpawnEvent evt) {
        PlayerEntity player = evt.getPlayer();
        World world = player.getEntityWorld();
        BlockPos pos = evt.getNewSpawn();

        if (pos != null && !world.isRemote) {
            Block block = world.getBlockState(pos).getBlock();

            if (block instanceof BlockSleepingBag) {
                evt.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void setCreatureAttribute(LivingAttackEvent event) {
        LivingEntity damagedEntity = event.getEntityLiving();
        Entity player = event.getSource().getTrueSource();
        if (damagedEntity instanceof EndermanEntity) {
            if (player instanceof PlayerEntity) {
                ItemStack item =  ((PlayerEntity) player).getHeldItemMainhand();
                if(item !=null && item.getItem() instanceof SwordItem && item.isEnchanted())
                {
                    Map<Enchantment, Integer> enchTag =  EnchantmentHelper.getEnchantments(item);
                    if (enchTag.containsKey(ExplorerEnchantments.ENDERBANE.get())) {
                        //Do what you want your enchant to do, probably shock someone.
                        System.out.println("ENDERBANE ENCHANTMENT USED");
                        int damage = ((PlayerEntity) player).getHeldItemMainhand().getMaxDamage();
                        System.out.println("Damage:" + damage);
                        int level = EnchantmentHelper.getEnchantmentLevel(ExplorerEnchantments.ENDERBANE.get(), item);
                        System.out.println("Level:" + level);
                        float newDamage = level * 2.5F;
                        System.out.println("newDamage:" + newDamage);
                        damagedEntity.attackEntityFrom(DamageSource.MAGIC, newDamage);
                        System.out.println("health:" + damagedEntity.getHealth());
                    }

                }
            }

        }
    }

    /*
    @SubscribeEvent
    public static void EntityChangeSpawn(LivingSpawnEvent event)
    {
        if (event.getWorld().isRemote()) {
            return;
        }
        Entity entity = event.getEntity();
        if (entity instanceof ZombieEntity) {
            if (event.getWorld().getBiome(entity.getPosition()) == ExplorerBiomes.SNOWDONIA) {
                PillagerEntity pigman = EntityType.PILLAGER.create(event.getWorld().getWorld());
                pigman.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
                event.getWorld().getWorld().addEntity(pigman);
                entity.remove();
            }
        }
    }*/

/*
    //Cancels the spawn of zombies/vanilla mods
    @ForgeSubscribe
    public void onEntitySpawn(EntityJoinWorldEvent event)
    {
        if(event.entity instanceof EntitySkeleton || event.entity instanceof EntityZombie || event.entity instanceof EntitySpider) {
            event.setCanceled(true);
        }
    }


    @SubscribeEvent
    public static void EntityInteractSpecific(PlayerInteractEvent.EntityInteractSpecific event) {
        PlayerEntity player = event.getEntityPlayer();
        ItemStack item = player.getHeldItemMainhand();
        ItemStack itemstack1 = item.isEmpty() ? ItemStack.EMPTY : item.copy();
        Entity target = event.getTarget();
        if (item.getItem() == Items.POISONOUS_POTATO)
        {
            if(!event.getWorld().isRemote())
            {
                ZombiePigmanEntity pigman = (ZombiePigmanEntity) EntityType.ZOMBIE_PIGMAN.create(event.getWorld());
                pigman.setLocationAndAngles(target.posX, target.posY, target.posZ, target.rotationYaw, target.rotationPitch);
                event.getWorld().addEntity(pigman);
                target.remove();
                item.setCount(item.getCount()-1);
            }
        }
    }*/
}