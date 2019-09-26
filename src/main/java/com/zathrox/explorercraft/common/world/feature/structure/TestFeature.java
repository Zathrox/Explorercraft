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

public class TestFeature extends Feature<NoFeatureConfig> {

    public static final MinecraftServer worldServer = ServerLifecycleHooks.getCurrentServer();

    public TestFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn, boolean doBlockNotifyIn) {
        super(configFactoryIn, doBlockNotifyIn);
    }

    @Nullable
    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        //ServerWorld worldServer = ServerLifecycleHooks.getCurrentServer();
        MinecraftServer mcServer = worldIn.getWorld().getServer();
        TemplateManager templateManager = mcServer.getWorld(worldIn.getDimension().getType()).getStructureTemplateManager();
        Template template = templateManager.getTemplate(new ResourceLocation(Explorercraft.MOD_ID, "wizard_tower"));

        if (template == null) {
            System.out.println("Could not find structure at " + new ResourceLocation(Explorercraft.MOD_ID, "structures/test/test"));
            return false;
        }

        BlockPos worldHeight = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE, pos);
        if (canSpawnHere(worldIn, pos.down(), 12, 10, 20) && worldHeight.getY() >= 100) {

            PlacementSettings placementSettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk(null);
            template.addBlocksToWorldChunk(worldIn, pos, placementSettings);

            System.out.println(pos);

            return true;
        } else {

            return false;
        }

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
}
