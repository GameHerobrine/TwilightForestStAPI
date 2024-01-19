package net.skidcode.gh.twilightforest.dimension.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class TFGenHollowTree
extends TFGenerator {
    private Random treeRNG;
    private int x;
    private int y;
    private int z;
    private int height;
    private int diameter;
    private byte treeBlock;
    private byte leafBlock;
    static int treesMade = 0;
    static long totalTime = 0L;
    int leafBlobsMade;
    int leavesMade;

    @Override
    public boolean method_1142(World world, Random random, int treeX, int treeY, int treeZ) {
        long startTime = System.currentTimeMillis();
        this.worldObj = world;
        this.treeRNG = random;
        this.x = treeX;
        this.y = treeY;
        this.z = treeZ;
        this.treeBlock = (byte)Block.LOG.id;
        this.leafBlock = (byte)Block.LEAVES.id;
        this.leafBlobsMade = 0;
        this.height = this.treeRNG.nextInt(64) + 32;
        this.diameter = this.treeRNG.nextInt(4) + 1;
        if (this.y < 1 || this.y + this.height + this.diameter + 1 > 128) {
            return false;
        }
        for (int dx = -this.diameter; dx <= this.diameter; ++dx) {
            for (int dz = -this.diameter; dz <= this.diameter; ++dz) {
                for (int dy = 0; dy <= this.y + this.height; ++dy) {
                    int whatsThere = this.worldObj.getBlockId(dx + this.x, dy + this.y, dz + this.z);
                    if (whatsThere == 0 || whatsThere == Block.LEAVES.id) continue;
                    return false;
                }
            }
        }
        int crownRadius = this.diameter * 4 + 8;
        for (int dx = -crownRadius; dx <= crownRadius; ++dx) {
            for (int dz = -crownRadius; dz <= crownRadius; ++dz) {
                for (int dy = this.height - crownRadius; dy <= this.height + crownRadius; ++dy) {
                    int whatsThere = this.worldObj.getBlockId(dx + this.x, dy + this.y, dz + this.z);
                    if (whatsThere == 0 || whatsThere == Block.LEAVES.id) continue;
                    return false;
                }
            }
        }
        int j1 = world.getBlockId(this.x, this.y - 1, this.z);
        if (j1 != Block.GRASS_BLOCK.id && j1 != Block.DIRT.id || this.y >= 128 - this.height - 1) {
            return false;
        }
        this.buildTrunk();
        this.buildFullCrown();
        int numBranches = this.treeRNG.nextInt(3) + 3;
        for (int i = 0; i <= numBranches; ++i) {
            int branchHeight = (int)((double)this.height * this.treeRNG.nextDouble() * 0.9) + this.height / 10;
            double branchRotation = this.treeRNG.nextDouble();
            this.makeSmallBranch(branchHeight, 4.0, branchRotation, 0.35, true);
        }
        this.buildBranchRing(3, 2, 6, 0, 0.75, 0.0, 3, 5, 3, false);
        int numFireflies = this.treeRNG.nextInt(3) + 3;
        for (int i = 0; i <= numFireflies; ++i) {
            int fHeight = (int)((double)this.height * this.treeRNG.nextDouble() * 0.9) + this.height / 10;
            double fAngle = this.treeRNG.nextDouble();
            this.addFirefly(fHeight, fAngle);
        }
        ++treesMade;
        long timeSpent = System.currentTimeMillis() - startTime;
        totalTime += timeSpent;
        return true;
    }

    private void buildFullCrown() {
        int crownRadius = this.diameter * 4 + 4;
        int bvar = this.diameter + 2;
        this.buildBranchRing(this.height - crownRadius, 0, crownRadius, 0, 0.35, 0.0, bvar, bvar + 2, 2, true);
        this.buildBranchRing(this.height - crownRadius / 2, 0, crownRadius, 0, 0.28, 0.0, bvar, bvar + 2, 1, true);
        this.buildBranchRing(this.height, 0, crownRadius, 0, 0.15, 0.0, 2, 4, 2, true);
        this.buildBranchRing(this.height, 0, crownRadius / 2, 0, 0.05, 0.0, bvar, bvar + 2, 1, true);
    }

    private void buildWeakCrown() {
        int crownRadius = 8;
        int bvar = 2;
        this.buildBranchRing(this.height - crownRadius, 0, crownRadius, 0, 0.35, 0.0, bvar, bvar + 2, 1, true);
        this.buildBranchRing(this.height - crownRadius / 2, 0, crownRadius, 0, 0.28, 0.0, bvar, bvar + 2, 1, true);
        this.buildBranchRing(this.height, 0, crownRadius, 0, 0.15, 0.0, 2, 4, 1, true);
        this.buildBranchRing(this.height, 0, crownRadius / 2, 0, 0.05, 0.0, bvar, bvar + 2, 1, true);
    }

    private void buildBranchRing(int branchHeight, int heightVar, int length, int lengthVar, double tilt, double tiltVar, int minBranches, int maxBranches, int size, boolean leafy) {
        int numBranches = this.treeRNG.nextInt(maxBranches - minBranches) + minBranches;
        double branchRotation = 1.0 / (double)numBranches;
        double branchOffset = this.treeRNG.nextDouble();
        for (int i = 0; i <= numBranches; ++i) {
            int dHeight = heightVar > 0 ? branchHeight - heightVar + this.treeRNG.nextInt(2 * heightVar) : branchHeight;
            if (size == 2) {
                this.makeLargeBranch(dHeight, length, (double)i * branchRotation + branchOffset, tilt, leafy);
                continue;
            }
            if (size == 1) {
                this.makeMedBranch(dHeight, length, (double)i * branchRotation + branchOffset, tilt, leafy);
                continue;
            }
            if (size == 3) {
                this.makeRoot(dHeight, length, (double)i * branchRotation + branchOffset, tilt);
                continue;
            }
            this.makeSmallBranch(dHeight, length, (double)i * branchRotation + branchOffset, tilt, leafy);
        }
    }

    private void buildTrunk() {
        int dist;
        int az;
        int ax;
        int dy;
        int dz;
        int dx;
        int hollow = this.diameter / 2;
        for (dx = -this.diameter; dx <= this.diameter; ++dx) {
            for (dz = -this.diameter; dz <= this.diameter; ++dz) {
                for (dy = 0; dy <= this.height; ++dy) {
                    ax = Math.abs((int)dx);
                    dist = (int)((double)Math.max((int)ax, (int)(az = Math.abs((int)dz))) + (double)Math.min((int)ax, (int)az) * 0.5);
                    if (dist <= this.diameter && dist > hollow) {
                        this.putBlock(dx + this.x, dy + this.y, dz + this.z, this.treeBlock, true);
                    }
                    if (dist <= hollow) {
                        // empty if block
                    }
                    if (dist != hollow || dx != hollow) continue;
                    this.putBlockAndMetadata(dx + this.x, dy + this.y, dz + this.z, Block.LADDER.id, 4, true);
                }
            }
        }
        for (dx = -this.diameter; dx <= this.diameter; ++dx) {
            for (dz = -this.diameter; dz <= this.diameter; ++dz) {
                for (dy = -4; dy < 0; ++dy) {
                    ax = Math.abs((int)dx);
                    dist = (int)((double)Math.max((int)ax, (int)(az = Math.abs((int)dz))) + (double)Math.min((int)ax, (int)az) * 0.5);
                    if (dist > this.diameter || dist <= hollow) continue;
                    this.putBlock(dx + this.x, dy + this.y, dz + this.z, this.treeBlock, false);
                }
            }
        }
    }

    private void makeMedBranch(int branchHeight, double length, double angle, double tilt, boolean leafy) {
        int sx = this.x;
        int sy = this.y + branchHeight;
        int sz = this.z;
        int[] src = this.translate(sx, sy, sz, this.diameter, angle, 0.5);
        this.makeMedBranch(src[0], src[1], src[2], length, angle, tilt, leafy);
    }

    private void makeMedBranch(int sx, int sy, int sz, double length, double angle, double tilt, boolean leafy) {
        int[] src = new int[]{sx, sy, sz};
        int[] dest = this.translate(src[0], src[1], src[2], length, angle, tilt);
        this.drawBresehnam(src[0], src[1], src[2], dest[0], dest[1], dest[2], this.treeBlock, true);
        if (leafy) {
            this.drawBlob(dest[0], dest[1], dest[2], (byte)2, this.leafBlock, false);
        }
        int numShoots = this.treeRNG.nextInt(2) + 1;
        double angleInc = 0.8 / (double)numShoots;
        for (int i = 0; i <= numShoots; ++i) {
            double angleVar = angleInc * (double)i - 0.4;
            double outVar = this.treeRNG.nextDouble() * 0.8 + 0.2;
            double tiltVar = this.treeRNG.nextDouble() * 0.75 + 0.15;
            int[] bsrc = this.translate(src[0], src[1], src[2], length * outVar, angle, tilt);
            double slength = length * 0.4;
            this.makeSmallBranch(bsrc[0], bsrc[1], bsrc[2], slength, angle + angleVar, tilt * tiltVar, leafy);
        }
    }

    private void makeSmallBranch(int sx, int sy, int sz, double length, double angle, double tilt, boolean leafy) {
        int[] src = new int[]{sx, sy, sz};
        int[] dest = this.translate(src[0], src[1], src[2], length, angle, tilt);
        this.drawBresehnam(src[0], src[1], src[2], dest[0], dest[1], dest[2], this.treeBlock, true);
        if (leafy) {
            byte leafRad = (byte)(this.treeRNG.nextInt(2) + 1);
            this.drawBlob(dest[0], dest[1], dest[2], leafRad, this.leafBlock, false);
        }
    }

    private void makeSmallBranch(int branchHeight, double length, double angle, double tilt, boolean leafy) {
        int sx = this.x;
        int sy = this.y + branchHeight;
        int sz = this.z;
        int[] src = this.translate(sx, sy, sz, this.diameter, angle, 0.5);
        this.makeSmallBranch(src[0], src[1], src[2], length, angle, tilt, leafy);
    }

    private void makeRoot(int branchHeight, double length, double angle, double tilt) {
        int[] src = this.translate(this.x, this.y + branchHeight, this.z, this.diameter, angle, 0.5);
        int[] dest = this.translate(src[0], src[1], src[2], length, angle, tilt);
        this.drawBresehnam(src[0], src[1], src[2], dest[0], dest[1], dest[2], this.treeBlock, true);
        this.drawBresehnam(src[0], src[1] - 1, src[2], dest[0], dest[1] - 1, dest[2], this.treeBlock, true);
    }

    private void makeLargeBranch(int sx, int sy, int sz, double length, double angle, double tilt, boolean leafy) {
        int[] src = new int[]{sx, sy, sz};
        int[] dest = this.translate(src[0], src[1], src[2], length, angle, tilt);
        this.drawBresehnam(src[0], src[1], src[2], dest[0], dest[1], dest[2], this.treeBlock, true);
        int reinforcements = this.treeRNG.nextInt(3);
        for (int i = 0; i <= reinforcements; ++i) {
            int vx = (i & 2) == 0 ? 1 : 0;
            int vy = (i & 1) == 0 ? 1 : -1;
            int vz = (i & 2) == 0 ? 0 : 1;
            this.drawBresehnam(src[0] + vx, src[1] + vy, src[2] + vz, dest[0], dest[1], dest[2], this.treeBlock, true);
        }
        if (leafy) {
            this.drawBlob(dest[0], dest[1] + 1, dest[2], (byte)3, this.leafBlock, false);
        }
        int numMedBranches = this.treeRNG.nextInt((int)(length / 6.0)) + this.treeRNG.nextInt(2) + 1;
        for (int i = 0; i <= numMedBranches; ++i) {
            double outVar = this.treeRNG.nextDouble() * 0.3 + 0.3;
            double angleVar = this.treeRNG.nextDouble() * 0.225 * ((i & 1) == 0 ? 1.0 : -1.0);
            int[] bsrc = this.translate(src[0], src[1], src[2], length * outVar, angle, tilt);
            this.makeMedBranch(bsrc[0], bsrc[1], bsrc[2], length * 0.6, angle + angleVar, tilt, leafy);
        }
        int numSmallBranches = this.treeRNG.nextInt(2) + 1;
        for (int i = 0; i <= numSmallBranches; ++i) {
            double outVar = this.treeRNG.nextDouble() * 0.25 + 0.25;
            double angleVar = this.treeRNG.nextDouble() * 0.25 * ((i & 1) == 0 ? 1.0 : -1.0);
            int[] bsrc = this.translate(src[0], src[1], src[2], length * outVar, angle, tilt);
            this.makeSmallBranch(bsrc[0], bsrc[1], bsrc[2], Math.max((double)(length * 0.3), (double)2.0), angle + angleVar, tilt, leafy);
        }
    }

    private void makeLargeBranch(int branchHeight, double length, double angle, double tilt, boolean leafy) {
        int sx = this.x;
        int sy = this.y + branchHeight;
        int sz = this.z;
        int[] src = this.translate(sx, sy, sz, this.diameter, angle, 0.5);
        this.makeLargeBranch(src[0], src[1], src[2], length, angle, tilt, leafy);
    }

    private void addFirefly(int fHeight, double fAngle) {
        int[] src = this.translate(this.x, this.y + fHeight, this.z, this.diameter + 1, fAngle, 0.5);
        if ((fAngle %= 1.0) > 0.875 || fAngle <= 0.125) {
            if (this.worldObj.method_1780(src[0] + 1, src[1], src[2])) {
                this.putBlockAndMetadata(src[0], src[1], src[2], Block.TORCH.id, 2, false);
            }
        } else if (fAngle > 0.125 || fAngle <= 0.375) {
            if (this.worldObj.method_1780(src[0], src[1], src[2] - 1)) {
                this.putBlockAndMetadata(src[0], src[1], src[2], Block.TORCH.id, 3, false);
            }
        } else if (fAngle > 0.375 || fAngle <= 0.625) {
            if (this.worldObj.method_1780(src[0] - 1, src[1], src[2])) {
                this.putBlockAndMetadata(src[0], src[1], src[2], Block.TORCH.id, 1, false);
            }
        } else if ((fAngle > 0.625 || fAngle <= 0.875) && this.worldObj.method_1780(src[0], src[1], src[2] - 1)) {
            this.putBlockAndMetadata(src[0], src[1], src[2], Block.TORCH.id, 4, false);
        }
    }

    private void drawBlob(int sx, int sy, int sz, byte rad, byte blockValue, boolean priority) {
        for (byte dx = 0; dx <= rad; dx = (byte)(dx + 1)) {
            for (byte dy = 0; dy <= rad; dy = (byte)(dy + 1)) {
                for (byte dz = 0; dz <= rad; dz = (byte)(dz + 1)) {
                    byte dist = 0;
                    dist = dx >= dy && dx >= dz ? (byte)(dx + (byte)((double)Math.max((int)dy, (int)dz) * 0.5 + (double)Math.min((int)dy, (int)dz) * 0.25)) : (dy >= dx && dy >= dz ? (byte)(dy + (byte)((double)Math.max((int)dx, (int)dz) * 0.5 + (double)Math.min((int)dx, (int)dz) * 0.25)) : (byte)(dz + (byte)((double)Math.max((int)dx, (int)dy) * 0.5 + (double)Math.min((int)dx, (int)dy) * 0.25)));
                    if (dist > rad) continue;
                    this.putBlock(sx + dx, sy + dy, sz + dz, blockValue, priority);
                    this.putBlock(sx + dx, sy + dy, sz - dz, blockValue, priority);
                    this.putBlock(sx - dx, sy + dy, sz + dz, blockValue, priority);
                    this.putBlock(sx - dx, sy + dy, sz - dz, blockValue, priority);
                    this.putBlock(sx + dx, sy - dy, sz + dz, blockValue, priority);
                    this.putBlock(sx + dx, sy - dy, sz - dz, blockValue, priority);
                    this.putBlock(sx - dx, sy - dy, sz + dz, blockValue, priority);
                    this.putBlock(sx - dx, sy - dy, sz - dz, blockValue, priority);
                }
            }
        }
        ++this.leafBlobsMade;
    }
}