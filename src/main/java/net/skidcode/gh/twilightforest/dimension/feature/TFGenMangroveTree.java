package net.skidcode.gh.twilightforest.dimension.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class TFGenMangroveTree
extends TFGenerator {
    private Random treeRNG;
    private int x;
    private int y;
    private int z;
    private int height;
    private byte treeBlock;
    private byte treeMeta;
    private byte leafBlock;
    private byte leafMeta;

    @Override
    public boolean method_1142(World world, Random random, int treeX, int treeY, int treeZ) {
        this.worldObj = world;
        this.treeRNG = random;
        this.x = treeX;
        this.y = treeY;
        this.z = treeZ;
        this.treeBlock = (byte)Block.LOG.id;
        this.treeMeta = 0;
        this.leafBlock = (byte)Block.LEAVES.id;
        this.leafMeta = (byte)2;
        int blockUnder = world.getBlockId(this.x, this.y - 1, this.z);
        if (blockUnder != Block.WATER.id || this.y >= 128 - this.height - 1) {
            return false;
        }
        this.buildBranch(5, 6 + this.treeRNG.nextInt(3), 0.0, 0.0);
        int numBranches = this.treeRNG.nextInt(3);
        double offset = this.treeRNG.nextDouble();
        for (int b = 0; b < numBranches; ++b) {
            this.buildBranch(7 + b, 6 + this.treeRNG.nextInt(2), 0.3 * (double)b + offset, 0.25);
        }
        int numRoots = 3 + this.treeRNG.nextInt(2);
        offset = this.treeRNG.nextDouble();
        for (int i = 0; i < numRoots; ++i) {
            double rTilt = 0.75 + this.treeRNG.nextDouble() * 0.1;
            this.buildRoot(5, 8.0, 0.4 * (double)i + offset, rTilt);
        }
        this.addFirefly(5 + this.treeRNG.nextInt(5), this.treeRNG.nextDouble());
        return true;
    }

    void buildBranch(int height, double length, double angle, double tilt) {
        int[] src = new int[]{this.x, this.y + height, this.z};
        int[] dest = this.translate(src[0], src[1], src[2], length, angle, tilt);
        this.drawBresehnam(src[0], src[1], src[2], dest[0], dest[1], dest[2], this.treeBlock, this.treeMeta, true);
        this.putBlockAndMetadata(dest[0] + 1, dest[1], dest[2], this.treeBlock, this.treeMeta, true);
        this.putBlockAndMetadata(dest[0] - 1, dest[1], dest[2], this.treeBlock, this.treeMeta, true);
        this.putBlockAndMetadata(dest[0], dest[1], dest[2] + 1, this.treeBlock, this.treeMeta, true);
        this.putBlockAndMetadata(dest[0], dest[1], dest[2] - 1, this.treeBlock, this.treeMeta, true);
        int bSize = 2 + this.treeRNG.nextInt(3);
        this.drawCircle(dest[0], dest[1] - 1, dest[2], (byte)(bSize - 1), this.leafBlock, this.leafMeta, false);
        this.drawCircle(dest[0], dest[1], dest[2], (byte)bSize, this.leafBlock, this.leafMeta, false);
        this.drawCircle(dest[0], dest[1] + 1, dest[2], (byte)(bSize - 2), this.leafBlock, this.leafMeta, false);
    }

    void buildRoot(int height, double length, double angle, double tilt) {
        int[] src = new int[]{this.x, this.y + height, this.z};
        int[] dest = this.translate(src[0], src[1], src[2], length, angle, tilt);
        this.drawBresehnam(src[0], src[1], src[2], dest[0], dest[1], dest[2], this.treeBlock, this.treeMeta, true);
    }

    private void addFirefly(int height, double angle) {
        int iAngle = (int)(angle * 4.0);
        if (iAngle == 0) {
            this.putBlockAndMetadata(this.x + 1, this.y + height, this.z, Block.TORCH.id, 1, false);
        } else if (iAngle == 1) {
            this.putBlockAndMetadata(this.x - 1, this.y + height, this.z, Block.TORCH.id, 2, false);
        } else if (iAngle == 2) {
            this.putBlockAndMetadata(this.x, this.y + height, this.z + 1, Block.TORCH.id, 3, false);
        } else if (iAngle == 3) {
            this.putBlockAndMetadata(this.x, this.y + height, this.z - 1, Block.TORCH.id, 4, false);
        }
    }
}