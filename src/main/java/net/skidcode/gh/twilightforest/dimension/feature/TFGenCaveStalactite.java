package net.skidcode.gh.twilightforest.dimension.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.world.World;

public class TFGenCaveStalactite
extends TFGenerator {
    public double size;
    public int bType;
    public boolean hang;
    public int dir;

    public TFGenCaveStalactite(int blockType, double sizeFactor, boolean down) {
        this.bType = blockType;
        this.size = sizeFactor;
        this.hang = down;
        this.dir = this.hang ? -1 : 1;
    }

    public static TFGenCaveStalactite makeRandomOreStalactite(Random rand, int caveSize) {
        int s1;
        if (caveSize >= 3) {
            int s3 = rand.nextInt(6);
            if (s3 == 0) {
                return new TFGenCaveStalactite(Block.DIAMOND_ORE.id, rand.nextDouble() * 0.5, true);
            }
            if (s3 == 1) {
                return new TFGenCaveStalactite(Block.LAPIS_ORE.id, rand.nextDouble() * 0.8, true);
            }
        }
        if (caveSize >= 2) {
            int s2 = rand.nextInt(6);
            if (s2 == 0) {
                return new TFGenCaveStalactite(Block.GOLD_ORE.id, rand.nextDouble() * 0.6, true);
            }
            if (s2 == 1 || s2 == 2) {
                return new TFGenCaveStalactite(Block.REDSTONE_ORE.id, rand.nextDouble() * 0.8, true);
            }
        }
        if ((s1 = rand.nextInt(5)) == 0 || s1 == 1) {
            return new TFGenCaveStalactite(Block.IRON_ORE.id, rand.nextDouble() * 0.7, true);
        }
        if (s1 == 2 || s1 == 3) {
            return new TFGenCaveStalactite(Block.COAL_ORE.id, rand.nextDouble() * 0.8, true);
        }
        return new TFGenCaveStalactite(Block.GLOWSTONE.id, rand.nextDouble() * 0.5, true);
    }

    @Override
    public boolean method_1142(World world, Random random, int x, int y, int z) {
        Material m;
        int ty;
        this.worldObj = world;
        int ceiling = 129;
        int floor = -1;
        for (ty = y; ty < 128; ++ty) {
            m = this.worldObj.method_1779(x, ty, z);
            if (m == Material.AIR) continue;
            if (m != Material.SOIL && m != Material.STONE) {
                return false;
            }
            ceiling = ty;
            break;
        }
        if (ceiling == 129) {
            return false;
        }
        for (ty = y; ty > 4; --ty) {
            m = this.worldObj.method_1779(x, ty, z);
            if (m == Material.AIR) continue;
            if (m != Material.SOIL && m != Material.STONE && !this.hang && m != Material.WATER && !this.hang && m != Material.LAVA) {
                return false;
            }
            floor = ty;
            break;
        }
        int length = (int)((double)(ceiling - floor) * this.size);
        return this.makeSpike(random, x, this.hang ? ceiling : floor, z, length);
    }

    public boolean makeSpike(Random random, int x, int y, int z, int length) {
        int dw = (int)((double)length / 4.5);
        for (int dx = -dw; dx <= dw; ++dx) {
            for (int dz = -dw; dz <= dw; ++dz) {
                int ax = Math.abs((int)dx);
                int az = Math.abs((int)dz);
                int dist = (int)((double)Math.max((int)ax, (int)az) + (double)Math.min((int)ax, (int)az) * 0.5);
                int dl = 0;
                if (dist == 0) {
                    dl = length;
                }
                if (dist > 0) {
                    dl = random.nextInt((int)((double)length / ((double)dist + 0.25)));
                }
                for (int dy = 0; dy != dl * this.dir; dy += this.dir) {
                    this.putBlock(x + dx, y + dy, z + dz, this.bType, false);
                }
            }
        }
        return true;
    }

    public boolean generateOld(World world, Random random, int i, int j, int k) {
        this.worldObj = world;
        if (!world.method_234(i, j, k)) {
            return false;
        }
        if (world.getBlockId(i, j + 1, k) != Block.STONE.id && world.getBlockId(i, j + 1, k) != Block.DIRT.id) {
            return false;
        }
        this.drawDiameterCircle(i, j + 1, k, 3, (byte)this.bType, 0, false);
        this.drawDiameterCircle(i, j, k, 3, (byte)this.bType, 0, false);
        this.drawDiameterCircle(i, j - 1, k, 3, (byte)this.bType, 0, false);
        this.drawDiameterCircle(i, j - 2, k, 2, (byte)this.bType, 0, false);
        this.drawDiameterCircle(i, j - 3, k, 2, (byte)this.bType, 0, false);
        this.drawDiameterCircle(i, j - 4, k, 2, (byte)this.bType, 0, false);
        this.drawDiameterCircle(i, j - 5, k, 1, (byte)this.bType, 0, false);
        this.drawDiameterCircle(i, j - 6, k, 1, (byte)this.bType, 0, false);
        this.drawDiameterCircle(i, j - 7, k, 1, (byte)this.bType, 0, false);
        return true;
    }
}