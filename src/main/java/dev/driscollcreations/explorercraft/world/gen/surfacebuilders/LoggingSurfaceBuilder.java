package dev.driscollcreations.explorercraft.world.gen.surfacebuilders;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraftforge.common.util.Lazy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.function.Supplier;

/**
 * A {@link SurfaceBuilder} that delegates to another {@link SurfaceBuilder} and
 * logs a message the first time it's used to generate a biome.
 *
 * @author Choonster
 */
public class LoggingSurfaceBuilder<C extends SurfaceBuilderBaseConfiguration, S extends SurfaceBuilder<C>> extends SurfaceBuilder<C> {
    private static final Logger LOGGER = LogManager.getLogger();

    private final Lazy<S> delegatedSurfaceBuilder;

    private boolean logged = false;

    public LoggingSurfaceBuilder(final Supplier<S> delegatedSurfaceBuilder, final Codec<C> codec) {
        super(codec);
        this.delegatedSurfaceBuilder = Lazy.of(delegatedSurfaceBuilder);
    }

    @Override
    public void apply(Random random, ChunkAccess chunk, Biome biome, int x, int  z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, int p_164223_, long seed, C config) {
        delegatedSurfaceBuilder.get().apply(
                random, chunk, biome,
                x, z, startHeight, noise,
                defaultBlock, defaultFluid,
                seaLevel, 0,seed, config
        );

        if (!logged) {
            logged = true;
            final ChunkPos chunkPos = chunk.getPos();
            LOGGER.info("Generating {} at {},{}", biome.getRegistryName(), chunkPos.getMinBlockX(), chunkPos.getMinBlockZ());
        }
    }
}
