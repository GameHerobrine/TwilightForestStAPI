package net.skidcode.gh.twilightforest.dimension.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class TFGenMonolith
extends TFGenerator {
    @Override
    public boolean method_1142(World world, Random rand, int x, int y, int z) {
        int cy;
        int h3;
        int h2;
        int h1;
        int h0;
        this.worldObj = world;
        int ht = rand.nextInt(10) + 10;
        int dir = rand.nextInt(4);
        if (!this.isAreaClear(world, rand, x, y, z, 2, ht, 2)) {
            return false;
        }
        switch (dir) {
            case 0: {
                h0 = ht;
                h1 = (int)((double)ht * 0.75);
                h2 = (int)((double)ht * 0.75);
                h3 = (int)((double)ht * 0.5);
                break;
            }
            case 1: {
                h0 = (int)((double)ht * 0.5);
                h1 = ht;
                h2 = (int)((double)ht * 0.75);
                h3 = (int)((double)ht * 0.75);
                break;
            }
            case 2: {
                h0 = (int)((double)ht * 0.75);
                h1 = (int)((double)ht * 0.5);
                h2 = ht;
                h3 = (int)((double)ht * 0.75);
                break;
            }
            default: {
                h0 = (int)((double)ht * 0.75);
                h1 = (int)((double)ht * 0.75);
                h2 = (int)((double)ht * 0.5);
                h3 = ht;
            }
        }
        for (cy = 0; cy <= h0; ++cy) {
            this.putBlock(x + 0, y + cy - 1, z + 0, cy == ht ? (byte)Block.LAPIS_BLOCK.id : (byte)Block.OBSIDIAN.id, true);
        }
        for (cy = 0; cy <= h1; ++cy) {
            this.putBlock(x + 1, y + cy - 1, z + 0, cy == ht ? (byte)Block.LAPIS_BLOCK.id : (byte)Block.OBSIDIAN.id, true);
        }
        for (cy = 0; cy <= h2; ++cy) {
            this.putBlock(x + 0, y + cy - 1, z + 1, cy == ht ? (byte)Block.LAPIS_BLOCK.id : (byte)Block.OBSIDIAN.id, true);
        }
        for (cy = 0; cy <= h3; ++cy) {
            this.putBlock(x + 1, y + cy - 1, z + 1, cy == ht ? (byte)Block.LAPIS_BLOCK.id : (byte)Block.OBSIDIAN.id, true);
        }
        return true;
    }
}