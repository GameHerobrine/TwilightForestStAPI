package net.skidcode.gh.twilightforest.biome;
import java.util.Random;
import net.minecraft.class_153;
import net.minecraft.class_186;
import net.minecraft.class_239;
import net.minecraft.class_250;
import net.minecraft.class_288;
import net.minecraft.class_543;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.skidcode.gh.twilightforest.dimension.feature.TFGenCanopyTree;
import net.skidcode.gh.twilightforest.entity.EntityTwilightBighorn;
import net.skidcode.gh.twilightforest.entity.EntityTwilightBoar;
import net.skidcode.gh.twilightforest.entity.EntityTwilightDeer;

public class TFBiomeGenBase
extends class_153 {
    private static class_153[] biomeLookupTable = new class_153[4096];
    public static final class_153 twilightForest = new TFBiomeGenTwilightForest().method_792(30464).method_790("TwilightForest");
    public static final class_153 twilightClearings = new TFBiomeGenTwilightForest().method_792(0x99CC99).method_790("TwilightClearings");
    public static final class_153 twilightHighland = new TFBiomeGenHighlands().method_792(0x666666).method_790("TwilightHighland");
    public static final class_153 twilightMushroom = new TFBiomeGenTwilightForest().method_792(0x996633).method_790("TwilightMushroom");
    public static final class_153 twilightSwamp = new TFBiomeGenSwamp().method_792(0x999933).method_790("TwilightSwamp");
    public static final class_153 twilightSnow = new TFBiomeGenSnow().method_792(0xCCFFFF).method_790("TwilightSnow");
    public static final class_153 twilightGlacier = new TFBiomeGenGlacier().method_792(0xEEEEEE).method_790("TwilightGlacier");

    protected TFBiomeGenBase() {
        this.field_893.clear();
        this.field_895.clear();
        this.field_894.clear();
        this.field_894.add((Object)new class_288(EntityTwilightBighorn.class, 12));
        this.field_894.add((Object)new class_288(EntityTwilightBoar.class, 10));
        this.field_894.add((Object)new class_288(ChickenEntity.class, 10));
        this.field_894.add((Object)new class_288(EntityTwilightDeer.class, 15));
        this.field_894.add((Object)new class_288(WolfEntity.class, 2));
    }
    
    @Override
    protected TFBiomeGenBase method_792(int n) {
        this.field_889 = n;
        return this;
    }
    
    @Override
    public TFBiomeGenBase method_790(String string) {
        this.field_888 = string;
        return this;
    }
    
    public static void a() {
        for (int i = 0; i < 64; ++i) {
            for (int j = 0; j < 64; ++j) {
                TFBiomeGenBase.biomeLookupTable[i + j * 64] = TFBiomeGenBase.a((float)i / 63.0f, (float)j / 63.0f);
            }
        }
    }

    public static class_153 a(double temperature, double humidity) {
        int i = (int)(temperature * 63.0);
        int j = (int)(humidity * 63.0);
        return biomeLookupTable[i + j * 64];
    }

    @Override
    public class_239 method_797(Random random) {
        if (random.nextInt(5) == 0) {
            return new TFGenCanopyTree();
        }
        if (random.nextInt(10) == 0) {
            return new class_250();
        }
        if (random.nextInt(7) == 0) {
            return new class_186();
        }
        return new class_543();
    }

    public static class_153 a(float t, float h) {
        if ((double)(h *= t) > 0.75) {
            if ((double)h > 0.825) {
                return twilightSwamp;
            }
            return twilightClearings;
        }
        if ((double)h > 0.675 && (double)t < 0.75) {
            return twilightMushroom;
        }
        if ((double)t < 0.25) {
            if ((double)t < 0.1) {
                return twilightGlacier;
            }
            return twilightSnow;
        }
        if ((double)t > 0.75 && (double)h < 1.0 - (double)t) {
            return twilightHighland;
        }
        return twilightForest;
    }

    static {
        TFBiomeGenBase.a();
    }
}