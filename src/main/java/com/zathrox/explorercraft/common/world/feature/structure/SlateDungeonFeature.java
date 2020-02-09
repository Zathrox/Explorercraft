package com.zathrox.explorercraft.common.world.feature.structure;

import com.mojang.datafixers.Dynamic;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.block.material.Material;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Function;

public class SlateDungeonFeature extends Feature<NoFeatureConfig> {

    public static final MinecraftServer worldServer = ServerLifecycleHooks.getCurrentServer();
    private static final Random northChance = new Random();
    private static final Random eastChance = new Random();
    private static final Random southChance = new Random();
    private static final Random westChance = new Random();
    private static final Random roomRotation = new Random();

    public SlateDungeonFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn, boolean doBlockNotifyIn) {
        super(configFactoryIn, doBlockNotifyIn);

    }

    @Nullable
    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {

        int width = 9;
        int depth = 9;
        int height = 5;

        TemplateManager templateManager = ((ServerWorld)worldIn.getWorld()).getSaveHandler().getStructureTemplateManager();
        Template base = templateManager.getTemplate(new ResourceLocation(Explorercraft.MOD_ID, "slate_dungeon_base"));
        Template vertical_tunnel = templateManager.getTemplate(new ResourceLocation(Explorercraft.MOD_ID, "slate_dungeon_tunnel"));
        Template horizontal_tunnel = templateManager.getTemplate(new ResourceLocation(Explorercraft.MOD_ID, "slate_dungeon_tunnel2"));

        int north;

        if (base == null) {
            System.out.println("Could not find structure at " + new ResourceLocation(Explorercraft.MOD_ID, "structures/slate_dungeon_base"));
            return false;
        }

        int i = 3;
        int j = rand.nextInt(2) + 2;
        int k = -j - 1;
        int l = j + 1;
        int i1 = -1;
        int j1 = 4;
        int k1 = rand.nextInt(2) + 2;
        int l1 = -k1 - 1;
        int i2 = k1 + 1;
        int j2 = 0;

        for(int k2 = k; k2 <= l; ++k2) {
            for(int l2 = -1; l2 <= 4; ++l2) {
                for(int i3 = l1; i3 <= i2; ++i3) {
                    BlockPos blockpos = pos.add(k2, l2, i3);
                    Material material = worldIn.getBlockState(blockpos).getMaterial();
                    boolean flag = material.isSolid();
                    if (l2 == -1 && !flag) {
                        return false;
                    }

                    if (l2 == 4 && !flag) {
                        return false;
                    }

                    if ((k2 == k || k2 == l || i3 == l1 || i3 == i2) && l2 == 0 && worldIn.isAirBlock(blockpos) && worldIn.isAirBlock(blockpos.up())) {
                        ++j2;
                    }
                }
            }
        }
        if (j2 >= 1 && j2 <= 5) {
            PlacementSettings placementSettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk(null);
            base.addBlocksToWorldChunk(worldIn, pos, placementSettings);
            System.out.println("SD_BASE SPAWNED AT: " + pos);
            int offset = 16;
            if (northChance.nextInt(2) == 0) {
                assert vertical_tunnel != null;
                vertical_tunnel.addBlocksToWorldChunk(worldIn, pos.north(7), placementSettings);
                spawnRoom(worldIn, pos.north(offset), placementSettings, 5 ,templateManager);
            }
            if (eastChance.nextInt(3) == 0) {
                horizontal_tunnel.addBlocksToWorldChunk(worldIn, pos.east(9), placementSettings);
                spawnRoom(worldIn, pos.east(offset), placementSettings, 5 ,templateManager);
            }
            if (southChance.nextInt(3) == 0) {
                vertical_tunnel.addBlocksToWorldChunk(worldIn, pos.south(9), placementSettings);
                spawnRoom(worldIn, pos.south(offset), placementSettings, 5 ,templateManager);
            }
            if (westChance.nextInt(2) == 0) {
                horizontal_tunnel.addBlocksToWorldChunk(worldIn, pos.west(7), placementSettings);
                spawnRoom(worldIn, pos.west(offset), placementSettings, 5 ,templateManager);
            }
            return true;
        } else {
            return false;
        }

    }


    public void spawnRoom(IWorld worldIn, BlockPos pos, PlacementSettings placementSettings, int treasureChance, TemplateManager templateManager){
        Template treasure = templateManager.getTemplate(new ResourceLocation(Explorercraft.MOD_ID, "slate_dungeon_treasure"));
        Template crypt = templateManager.getTemplate(new ResourceLocation(Explorercraft.MOD_ID, "slate_dungeon_crypt"));
        Template crypt2 = templateManager.getTemplate(new ResourceLocation(Explorercraft.MOD_ID, "slate_dungeon_crypt2"));
        Template flag = templateManager.getTemplate(new ResourceLocation(Explorercraft.MOD_ID, "slate_dungeon_flag"));
        if(roomRotation.nextInt(treasureChance) == 0) {
            treasure.addBlocksToWorldChunk(worldIn, pos, placementSettings);
            System.out.println("TREASURE SPAWNED AT: " + pos);
        } else if(roomRotation.nextInt(treasureChance) == 1) {
            crypt2.addBlocksToWorldChunk(worldIn, pos, placementSettings);
            System.out.println("MARBLE CRYPT SPAWNED AT: " + pos);
        } else if(roomRotation.nextInt(treasureChance) == 2) {
            flag.addBlocksToWorldChunk(worldIn, pos, placementSettings);
            System.out.println("FLAG ROOM SPAWNED AT: " + pos);
        } else {
            crypt.addBlocksToWorldChunk(worldIn, pos, placementSettings);
            System.out.println("CRYPT SPAWNED AT: " + pos);

        }
    }
}