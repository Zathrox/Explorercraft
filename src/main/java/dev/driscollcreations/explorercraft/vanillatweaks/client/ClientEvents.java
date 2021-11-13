package dev.driscollcreations.explorercraft.vanillatweaks.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.config.VanillaTweaksConfig;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.SleepingBagBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (VanillaTweaksConfig.swimmingHorse.get()) {
            if (Minecraft.getInstance().isPaused()) {
                return;
            }

            Player player = Minecraft.getInstance().player;
            if (player != null && player.getVehicle() instanceof AbstractHorse) {
                AbstractHorse horse = (AbstractHorse) player.getVehicle();
                if ((horse.isInWater())) {
                    horse.setDeltaMovement(horse.getDeltaMovement().add(0.0D, 0.0125f, 0.0D));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        //event.registerEntityRenderer(ExplorerEntities.ENDERREEPER.get(), EnderreeperRenderer::new);
        //event.registerEntityRenderer(ExplorerEntities.WIZARD.get(), WizardRenderer::new);
    }

    @SubscribeEvent
    public void onPlayerRenderPre(RenderPlayerEvent.Pre evt) {
        final Player player = evt.getPlayer();

        if (player instanceof RemotePlayer && player.getPose() == Pose.SLEEPING) {
            player.getSleepingPos().ifPresent(bedPos -> {
                PoseStack matrixStack = evt.getMatrixStack();
                Block bed = player.level.getBlockState(bedPos).getBlock();
                if (bed instanceof SleepingBagBlock) {
                    matrixStack.translate(0.0f, -0.375F, 0.0f);
                }
            });
        }
    }

    @SubscribeEvent
    public void onPlayerRenderPost(RenderPlayerEvent.Post evt) {
        final Player player = evt.getPlayer();

        if (player instanceof RemotePlayer && player.getPose() == Pose.SLEEPING) {
            player.getSleepingPos().ifPresent(bedPos -> {
                PoseStack matrixStack = evt.getMatrixStack();
                Block bed = player.level.getBlockState(bedPos).getBlock();
                if (bed instanceof SleepingBagBlock) {
                    matrixStack.translate(0.0f, 0.375F, 0.0f);
                }
            });
        }
    }

}
