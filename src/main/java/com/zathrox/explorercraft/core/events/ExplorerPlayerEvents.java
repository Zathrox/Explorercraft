package com.zathrox.explorercraft.core.events;

import com.zathrox.explorercraft.common.blocks.BlockSleepingBag;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public final class ExplorerPlayerEvents {

    @SubscribeEvent
    public static void onPlayerSetSpawn(PlayerSetSpawnEvent event) {
        PlayerEntity player = event.getPlayer();
        if (player.getEntityWorld().getBlockState(event.getNewSpawn()).getBlock() instanceof BlockSleepingBag) {
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