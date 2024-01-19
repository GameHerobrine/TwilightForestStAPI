package net.skidcode.gh.twilightforest.dimension.feature;

import java.util.Random;
import net.minecraft.world.World;

public class TFGenStream
extends TFGenerator {
    @Override
    public boolean method_1142(World world, Random random, int x, int y, int z) {
        this.worldObj = world;
        int streamLength = 80 + random.nextInt(20);
        double streamAngle = random.nextDouble();
        return false;
    }
}