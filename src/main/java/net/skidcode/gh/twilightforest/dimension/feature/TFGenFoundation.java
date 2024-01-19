package net.skidcode.gh.twilightforest.dimension.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class TFGenFoundation
extends TFGenerator {
    @Override
    public boolean method_1142(World world, Random rand, int x, int y, int z) {
        int sz;
        this.worldObj = world;
        int sx = 5 + rand.nextInt(5);
        if (!this.isAreaClear(world, rand, x, y, z, sx, 4, sz = 5 + rand.nextInt(5))) {
            return false;
        }
        for (int cx = 0; cx <= sx; ++cx) {
            for (int cz = 0; cz <= sz; ++cz) {
                if (cx == 0 || cx == sx || cz == 0 || cz == sz) {
                    int ht = rand.nextInt(4) + 1;
                    for (int cy = 0; cy <= ht; ++cy) {
                        this.putBlock(x + cx, y + cy - 1, z + cz, this.randStone(rand, cy + 1), true);
                    }
                    continue;
                }
                if (rand.nextInt(3) == 0) continue;
                this.putBlock(x + cx, y - 1, z + cz, (byte)Block.PLANKS.id, true);
            }
        }
        return true;
    }
}