package net.skidcode.gh.twilightforest.biome;

import java.util.Random;
import net.minecraft.class_239;
import net.minecraft.class_543;
import net.skidcode.gh.twilightforest.dimension.feature.TFGenMangroveTree;

public class TFBiomeGenSwamp
extends TFBiomeGenBase {
    @Override
    public class_239 method_797(Random random) {
        if (random.nextInt(10) == 0) {
            return new class_543();
        }
        return new TFGenMangroveTree();
    }
}