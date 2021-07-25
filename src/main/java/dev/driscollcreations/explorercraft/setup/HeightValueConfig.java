package dev.driscollcreations.explorercraft.setup;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class HeightValueConfig implements IPlacementConfig {
    public static final Codec<HeightValueConfig> CODEC = RecordCodecBuilder.create((codec) -> {
        return codec.group(Codec.INT.fieldOf("height").orElse(0).forGetter((config) -> {
            return config.height;
        })).apply(codec, HeightValueConfig::new);
    });

    public final int height;

    public HeightValueConfig(int height) {
        this.height = height;
    }
}
