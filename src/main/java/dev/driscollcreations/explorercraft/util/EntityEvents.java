package dev.driscollcreations.explorercraft.util;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.setup.ExplorerCriteriaTriggers;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.SleepingBagBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityEvents {


    @SubscribeEvent
    public void onPlayerSetSpawn(PlayerSetSpawnEvent evt) {
        Player player = evt.getPlayer();
        Level world = player.getCommandSenderWorld();
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
        Player player = event.getPlayer();
        BlockState state = player.getCommandSenderWorld().getBlockState(event.getPos());
        if (event.getResultStatus() == null && state.getBlock() instanceof SleepingBagBlock) {
            if (player instanceof ServerPlayer && player.isAlive()) {
                ServerPlayer serverPlayer = (ServerPlayer) player;
                if (!player.getCommandSenderWorld().isClientSide()) {
                    ExplorerCriteriaTriggers.SLEEP_IN_BAG.trigger(serverPlayer);
                }
            }
        }
    }

}
