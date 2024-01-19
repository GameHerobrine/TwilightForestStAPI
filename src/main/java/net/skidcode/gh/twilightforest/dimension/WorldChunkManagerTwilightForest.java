package net.skidcode.gh.twilightforest.dimension;

import net.minecraft.class_153;
import net.minecraft.class_519;
import net.minecraft.world.World;
import net.skidcode.gh.twilightforest.biome.TFBiomeGenBase;

public class WorldChunkManagerTwilightForest
extends class_519 {
    public WorldChunkManagerTwilightForest() {
    }

    public WorldChunkManagerTwilightForest(World world) {
        super(world);
    }

    @Override
    public class_153[] method_1791(class_153[] abiomegenbase, int i1, int j1, int width, int length) {
        abiomegenbase = super.method_1791(abiomegenbase, i1, j1, width, length);
        for (int i = 0; i < width * length; ++i) {
            abiomegenbase[i] = TFBiomeGenBase.a(this.field_2235[i], this.field_2236[i]);
        }
        return abiomegenbase;
    }
}