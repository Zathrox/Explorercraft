package dev.driscollcreations.explorercraft.util;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.setup.ExplorerBannerPattern;
import dev.driscollcreations.explorercraft.setup.ExplorerCriteriaTriggers;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.SleepingBagBlock;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityEvents {

    @SubscribeEvent
    public void onPlayerSetSpawn(PlayerSetSpawnEvent evt) {
        PlayerEntity player = evt.getPlayer();
        World world = player.getCommandSenderWorld();
        BlockPos pos = evt.getNewSpawn();

        if (pos != null && !world.isClientSide()) {
            Block block = world.getBlockState(pos).getBlock();

            if (block instanceof SleepingBagBlock) {
                evt.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerSleep(PlayerSleepInBedEvent event) {
        PlayerEntity player = event.getPlayer();
        BlockState state = player.getCommandSenderWorld().getBlockState(event.getPos());
        if (event.getResultStatus() == null && state.getBlock() instanceof SleepingBagBlock) {
            if (player instanceof ServerPlayerEntity && player.isAlive()) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                if (!player.getCommandSenderWorld().isClientSide()) {
                    ExplorerCriteriaTriggers.SLEEP_IN_BAG.trigger(serverPlayer);
                }
            }
        }
    }

}
