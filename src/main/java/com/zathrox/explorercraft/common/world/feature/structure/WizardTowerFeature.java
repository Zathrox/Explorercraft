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
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Function;

public class WizardTowerFeature extends Feature<NoFeatureConfig> {

    public static final MinecraftServer worldServer = ServerLifecycleHooks.getCurrentServer();

    public WizardTowerFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn, boolean doBlockNotifyIn) {
        super(configFactoryIn, doBlockNotifyIn);

    }

    @Nullable
    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {

        int width = 7;
        int depth = 8;
        int height = 20;
        MinecraftServer mcServer = worldIn.getWorld().getServer();
        TemplateManager templateManager = mcServer.getWorld(worldIn.getDimension().getType()).getStructureTemplateManager();
        Template template = templateManager.getTemplate(new ResourceLocation(Explorercraft.MOD_ID, "wizard_tower_v2"));

        if (template == null) {
            System.out.println("Could not find structure at " + new ResourceLocation(Explorercraft.MOD_ID, "structures/wizard_tower_v2"));
            return false;
        }

        BlockPos worldHeight = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE, pos);
        if (worldHeight.getY() <= 100) {
            return false;
        } else {
            PlacementSettings placementSettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk(null);
            Rotation rotation = Rotation.values()[rand.nextInt(Rotation.values().length)];
            if (rotation == Rotation.CLOCKWISE_90)
            {
                depth = -depth;
                if(canSpawnClockwise90(worldIn, pos, width, depth, height))
                {
                    template.addBlocksToWorldChunk(worldIn, pos.down(4), placementSettings.setRotation(rotation));
                    System.out.println("WIZARD TOWER GENERATED CLOCKWISE_90 AT: " + pos);
                }
            }
            else if (rotation == Rotation.CLOCKWISE_180)
            {
                depth = -depth;
                width = -width;
                if(canSpawnClockwise180(worldIn, pos, width, depth, height))
                {
                    template.addBlocksToWorldChunk(worldIn, pos.down(4), placementSettings.setRotation(rotation));
                    System.out.println("WIZARD TOWER GENERATED CLOCKWISE_180 AT: " + pos);
                    return true;
                }
            }
            else if (rotation == Rotation.COUNTERCLOCKWISE_90)
            {
                width = -width;
                if(canSpawnClockwise270(worldIn, pos, width, depth, height))
                {
                    template.addBlocksToWorldChunk(worldIn, pos.down(4), placementSettings.setRotation(rotation));
                    System.out.println("WIZARD TOWER GENERATED CLOCKWISE_270 AT: " + pos);
                    return true;
                }
            }
            else if (rotation == Rotation.NONE)
            {
                if( canSpawnHere(worldIn, pos, width, depth, height ) )
                {
                    template.addBlocksToWorldChunk(worldIn, pos.down(4), placementSettings.setRotation(rotation));
                    System.out.println("WIZARD TOWER GENERATED DEFAULT AT: " + pos);
                    return true;
                }
            }
        }

        return false;

    }

    public static boolean canReplace(IWorld world, BlockPos pos)
    {
        Material material = world.getBlockState(pos).getMaterial();
        // we think it's replaceable if it's air / liquid / snow, plants, or leaves
        return material.isSolid() || material == Material.PLANTS || material == Material.LEAVES || material == Material.ORGANIC || material != Material.AIR;
    }

    public static boolean canSpawnHere(IWorld world, BlockPos posAboveGround, int width, int depth, int height)
    {
        // check all the corners to see which ones are replaceable
        boolean corner1Air = canReplace(world, posAboveGround);
        boolean corner2Air = canReplace(world, posAboveGround.add(width, 0, 0));
        boolean corner4Air = canReplace(world, posAboveGround.add(0, 0, depth));
        boolean corner3Air = canReplace(world, posAboveGround.add(width, 0, depth));

        // if Y > 20 and all corners pass the test, it's okay to spawn the structure
        return posAboveGround.getY() > height && corner1Air && corner2Air && corner3Air && corner4Air;
    }

    private static boolean canSpawnClockwise90(IWorld world, BlockPos posAboveGround, int width, int depth, int height)
    {
        // check all the corners to see which ones are replaceable
        boolean corner1Air = canReplace(world, posAboveGround);
        boolean corner2Air = canReplace(world, posAboveGround.add(depth, 0, 0));
        boolean corner4Air = canReplace(world, posAboveGround.add(0, 0, width));
        boolean corner3Air = canReplace(world, posAboveGround.add(depth, 0, width));

        // if Y > 20 and all corners pass the test, it's okay to spawn the structure
        return posAboveGround.getY() > height && corner1Air && corner2Air && corner3Air && corner4Air;
    }

    private static boolean canSpawnClockwise180(IWorld world, BlockPos posAboveGround, int width, int depth, int height)
    {
        // check all the corners to see which ones are replaceable
        boolean corner1Air = canReplace(world, posAboveGround);
        boolean corner2Air = canReplace(world, posAboveGround.add(width, 0,  0));
        boolean corner4Air = canReplace(world, posAboveGround.add( 0, 0, depth));
        boolean corner3Air = canReplace(world, posAboveGround.add(width, 0, depth));

        // if Y > 20 and all corners pass the test, it's okay to spawn the structure
        return posAboveGround.getY() > height && corner1Air && corner2Air && corner3Air && corner4Air;
    }

    private static boolean canSpawnClockwise270(IWorld world, BlockPos posAboveGround, int width, int depth, int height)
    {
        // check all the corners to see which ones are replaceable
        boolean corner1Air = canReplace(world, posAboveGround);
        boolean corner2Air = canReplace(world, posAboveGround.add(depth, 0,  0));
        boolean corner4Air = canReplace(world, posAboveGround.add(0, 0, width));
        boolean corner3Air = canReplace(world, posAboveGround.add(depth, 0, width));;

        // if Y > 20 and all corners pass the test, it's okay to spawn the structure
        return posAboveGround.getY() > height && corner1Air && corner2Air && corner3Air && corner4Air;
    }
}