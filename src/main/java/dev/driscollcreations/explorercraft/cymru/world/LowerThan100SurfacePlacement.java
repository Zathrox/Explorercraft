package dev.driscollcreations.explorercraft.cymru.world;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.placement.NoPlacementConfig;

public class LowerThan100SurfacePlacement extends LowerThan100Placement<NoPlacementConfig> {

    public LowerThan100SurfacePlacement(Codec<NoPlacementConfig> codec) {
        super(codec);
    }

    protected Heightmap.Type type(NoPlacementConfig p_241858_1_) {
        return Heightmap.Type.WORLD_SURFACE_WG;
    }
}
