package net.skidcode.gh.twilightforest.dimension.feature;

import java.util.Random;
import net.minecraft.world.World;

public class TFGenSlab
extends TFGenerator {
    @Override
    public boolean method_1142(World world, Random rand, int x, int y, int z) {
        this.worldObj = world;
        int sx = 3 + rand.nextInt(7);
        int sz = 3 + rand.nextInt(7);
        int sy = (int)((double)rand.nextInt(2) * rand.nextDouble());
        if (!this.isAreaClear(world, rand, x, y, z, sx, sy, sz)) {
            return false;
        }
        for (int cx = 0; cx <= sx; ++cx) {
            for (int cz = 0; cz <= sz; ++cz) {
                for (int cy = 0; cy <= sy; ++cy) {
                    this.putBlock(x + cx, y + cy - 1, z + cz, this.randStone(rand, cy + 2), true);
                }
            }
        }
        return true;
    }
}