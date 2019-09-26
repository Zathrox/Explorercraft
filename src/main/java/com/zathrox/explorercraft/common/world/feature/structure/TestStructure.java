package com.zathrox.explorercraft.common.world.feature.structure;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;


public class TestStructure extends ScatteredStructure<NoFeatureConfig> {
    public TestStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51491_1_) {
        super(p_i51491_1_);
    }

    public String getStructureName() {
        return "Test";
    }

    public int getSize() {
        return 3;
    }

    public IStartFactory getStartFactory() {
        return TestStructure.Start::new;
    }

    protected int getSeedModifier() {
        return 14357618;
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> p_i50678_1_, int p_i50678_2_, int p_i50678_3_, Biome p_i50678_4_, MutableBoundingBox p_i50678_5_, int p_i50678_6_, long p_i50678_7_) {
            super(p_i50678_1_, p_i50678_2_, p_i50678_3_, p_i50678_4_, p_i50678_5_, p_i50678_6_, p_i50678_7_);
        }

        public void init(ChunkGenerator<?> p_214625_1_, TemplateManager p_214625_2_, int p_214625_3_, int p_214625_4_, Biome p_214625_5_) {
            NoFeatureConfig lvt_6_1_ = (NoFeatureConfig)p_214625_1_.getStructureConfig(p_214625_5_, Feature.IGLOO);
            int lvt_7_1_ = p_214625_3_ * 16;
            int lvt_8_1_ = p_214625_4_ * 16;
            BlockPos lvt_9_1_ = new BlockPos(lvt_7_1_, 90, lvt_8_1_);
            Rotation lvt_10_1_ = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            TestPieces.func_207617_a(p_214625_2_, lvt_9_1_, lvt_10_1_, this.components, this.rand, lvt_6_1_);
            this.recalculateStructureSize();
        }
    }
}
