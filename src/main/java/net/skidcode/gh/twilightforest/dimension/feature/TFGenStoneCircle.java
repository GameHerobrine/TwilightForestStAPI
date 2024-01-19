package net.skidcode.gh.twilightforest.dimension.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class TFGenStoneCircle
extends TFGenerator {
    @Override
    public boolean method_1142(World world, Random rand, int x, int y, int z) {
        this.worldObj = world;
        for (int cx = -3; cx <= 3; ++cx) {
            for (int cz = -3; cz <= 3; ++cz) {
                for (int cy = 0; cy <= 4; ++cy) {
                    if (!world.method_1779(x + cx, y - 1, z + cz).method_905()) {
                        return false;
                    }
                    if (world.method_234(x + cx, y + cy, z + cz)) continue;
                    return false;
                }
            }
        }
        for (int cy = 0; cy <= 2; ++cy) {
            this.putBlock(x - 3, y + cy, z, Block.MOSSY_COBBLESTONE.id, true);
            this.putBlock(x + 3, y + cy, z, Block.MOSSY_COBBLESTONE.id, true);
            this.putBlock(x, y + cy, z - 3, Block.MOSSY_COBBLESTONE.id, true);
            this.putBlock(x, y + cy, z + 3, Block.MOSSY_COBBLESTONE.id, true);
            this.putBlock(x - 2, y + cy, z - 2, Block.MOSSY_COBBLESTONE.id, true);
            this.putBlock(x + 2, y + cy, z - 2, Block.MOSSY_COBBLESTONE.id, true);
            this.putBlock(x - 2, y + cy, z + 2, Block.MOSSY_COBBLESTONE.id, true);
            this.putBlock(x + 2, y + cy, z + 2, Block.MOSSY_COBBLESTONE.id, true);
        }
        return true;
    }
}