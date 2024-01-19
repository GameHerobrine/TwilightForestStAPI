package net.skidcode.gh.twilightforest.dimension.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class TFGenOutsideStalagmite
extends TFGenCaveStalactite {
    public TFGenOutsideStalagmite() {
        super((byte)Block.STONE.id, 1.0, false);
    }

    @Override
    public boolean method_1142(World world, Random rand, int x, int y, int z) {
        this.worldObj = world;
        int length = rand.nextInt(10) + 5;
        if (!this.isAreaClear(world, rand, x, y, z, 1, length, 1)) {
            return false;
        }
        return this.makeSpike(rand, x, y - 1, z, length);
    }
}