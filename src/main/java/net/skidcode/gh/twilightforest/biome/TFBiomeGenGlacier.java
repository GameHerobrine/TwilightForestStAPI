package net.skidcode.gh.twilightforest.biome;

import java.util.Random;
import net.minecraft.class_239;
import net.minecraft.class_349;
import net.minecraft.class_512;

public class TFBiomeGenGlacier
extends TFBiomeGenBase {
    @Override
    public class_239 method_797(Random random) {
        if (random.nextInt(3) == 0) {
            return new class_349();
        }
        return new class_512();
    }
}