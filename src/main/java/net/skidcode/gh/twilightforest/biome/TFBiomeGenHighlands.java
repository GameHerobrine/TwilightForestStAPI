package net.skidcode.gh.twilightforest.biome;

import java.util.Random;
import net.minecraft.class_186;
import net.minecraft.class_239;
import net.minecraft.class_250;
import net.minecraft.class_543;

public class TFBiomeGenHighlands
extends TFBiomeGenBase {
    @Override
    public class_239 method_797(Random random) {
        if (random.nextInt(10) == 0) {
            return new class_250();
        }
        if (random.nextInt(7) == 0) {
            return new class_186();
        }
        return new class_543();
    }
}