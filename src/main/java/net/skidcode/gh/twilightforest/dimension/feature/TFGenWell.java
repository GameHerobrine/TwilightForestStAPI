package net.skidcode.gh.twilightforest.dimension.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class TFGenWell
extends TFGenerator {
    @Override
    public boolean method_1142(World world, Random rand, int x, int y, int z) {
        if (rand.nextInt(4) == 0) {
            return this.generate4x4Well(world, rand, x, y, z);
        }
        return this.generate3x3Well(world, rand, x, y, z);
    }

    public boolean generate3x3Well(World world, Random rand, int x, int y, int z) {
        int dblock;
        this.worldObj = world;
        if (!this.isAreaClear(world, rand, x, y, z, 3, 4, 3)) {
            return false;
        }
        this.putBlock(x + 0, y, z + 0, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 1, y, z + 0, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 2, y, z + 0, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 0, y, z + 2, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 1, y, z + 2, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 2, y, z + 2, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 0, y, z + 1, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 2, y, z + 1, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 1, y, z + 1, Block.WATER.id, true);
        this.putBlock(x + 0, y + 1, z + 0, Block.FENCE.id, true);
        this.putBlock(x + 2, y + 1, z + 0, Block.FENCE.id, true);
        this.putBlock(x + 0, y + 1, z + 2, Block.FENCE.id, true);
        this.putBlock(x + 2, y + 1, z + 2, Block.FENCE.id, true);
        this.putBlock(x + 0, y + 2, z + 0, Block.FENCE.id, true);
        this.putBlock(x + 2, y + 2, z + 0, Block.FENCE.id, true);
        this.putBlock(x + 0, y + 2, z + 2, Block.FENCE.id, true);
        this.putBlock(x + 2, y + 2, z + 2, Block.FENCE.id, true);
        this.putBlockAndMetadata(x + 0, y + 3, z + 0, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 1, y + 3, z + 0, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 2, y + 3, z + 0, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 0, y + 3, z + 2, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 1, y + 3, z + 2, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 2, y + 3, z + 2, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 0, y + 3, z + 1, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 2, y + 3, z + 1, Block.SLAB.id, 2, true);
        this.putBlock(x + 1, y + 3, z + 1, Block.PLANKS.id, true);
        for (int dy = -1; dy >= -20 && ((dblock = world.getBlockId(x + 1, y + dy, z + 1)) == Block.DIRT.id || dblock == Block.GRASS_BLOCK.id || dblock == Block.GRAVEL.id || dblock == Block.STONE.id) && world.method_1779(x + 1, y + dy - 1, z + 1).method_905(); --dy) {
            this.putBlock(x + 1, y + dy, z + 1, Block.WATER.id, true);
        }
        return true;
    }

    public boolean generate4x4Well(World world, Random rand, int x, int y, int z) {
        this.worldObj = world;
        if (!this.isAreaClear(world, rand, x, y, z, 4, 4, 4)) {
            return false;
        }
        this.putBlock(x + 0, y, z + 0, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 1, y, z + 0, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 2, y, z + 0, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 3, y, z + 0, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 0, y, z + 3, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 1, y, z + 3, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 2, y, z + 3, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 3, y, z + 3, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 0, y, z + 1, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 0, y, z + 2, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 3, y, z + 1, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 3, y, z + 2, Block.MOSSY_COBBLESTONE.id, true);
        this.putBlock(x + 1, y, z + 1, Block.WATER.id, true);
        this.putBlock(x + 2, y, z + 1, Block.WATER.id, true);
        this.putBlock(x + 1, y, z + 2, Block.WATER.id, true);
        this.putBlock(x + 2, y, z + 2, Block.WATER.id, true);
        this.putBlock(x + 0, y + 1, z + 0, Block.FENCE.id, true);
        this.putBlock(x + 3, y + 1, z + 0, Block.FENCE.id, true);
        this.putBlock(x + 0, y + 1, z + 3, Block.FENCE.id, true);
        this.putBlock(x + 3, y + 1, z + 3, Block.FENCE.id, true);
        this.putBlock(x + 0, y + 2, z + 0, Block.FENCE.id, true);
        this.putBlock(x + 3, y + 2, z + 0, Block.FENCE.id, true);
        this.putBlock(x + 0, y + 2, z + 3, Block.FENCE.id, true);
        this.putBlock(x + 3, y + 2, z + 3, Block.FENCE.id, true);
        this.putBlockAndMetadata(x + 0, y + 3, z + 0, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 1, y + 3, z + 0, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 2, y + 3, z + 0, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 3, y + 3, z + 0, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 0, y + 3, z + 3, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 1, y + 3, z + 3, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 2, y + 3, z + 3, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 3, y + 3, z + 3, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 0, y + 3, z + 1, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 0, y + 3, z + 2, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 3, y + 3, z + 1, Block.SLAB.id, 2, true);
        this.putBlockAndMetadata(x + 3, y + 3, z + 2, Block.SLAB.id, 2, true);
        this.putBlock(x + 1, y + 3, z + 1, Block.PLANKS.id, true);
        this.putBlock(x + 2, y + 3, z + 1, Block.PLANKS.id, true);
        this.putBlock(x + 1, y + 3, z + 2, Block.PLANKS.id, true);
        this.putBlock(x + 2, y + 3, z + 2, Block.PLANKS.id, true);
        for (int dx = 1; dx <= 2; ++dx) {
            for (int dz = 1; dz <= 2; ++dz) {
                int dblock;
                for (int dy = -1; dy >= -20 && ((dblock = world.getBlockId(x + dx, y + dy, z + dz)) == Block.DIRT.id || dblock == Block.GRASS_BLOCK.id || dblock == Block.GRAVEL.id || dblock == Block.STONE.id) && world.method_1779(x + dx, y + dy - 1, z + dz).method_905(); --dy) {
                    this.putBlock(x + dx, y + dy, z + dz, Block.WATER.id, true);
                }
            }
        }
        return true;
    }
}