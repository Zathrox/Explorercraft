package com.zathrox.explorercraft.core.events;

import com.zathrox.explorercraft.common.entity.WizardEntity;
import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.common.blocks.BlockSleepingBag;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import com.zathrox.explorercraft.core.registry.ExplorerItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.NonNullList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.LakesFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.ChunkWatchEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import javax.annotation.Nullable;
import java.util.*;

@EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public final class ExplorerPlayerEvents {

    @SubscribeEvent
    public static void onPlayerSetSpawn(PlayerSetSpawnEvent event) {
        PlayerEntity player = event.getEntityPlayer();
        if(player.getEntityWorld().getBlockState(event.getNewSpawn()).getBlock() instanceof BlockSleepingBag) {
            event.setCanceled(true);
            System.out.println("Spawn Cancelled");
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