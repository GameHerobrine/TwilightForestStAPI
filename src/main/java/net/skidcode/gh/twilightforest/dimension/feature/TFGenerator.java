package net.skidcode.gh.twilightforest.dimension.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.class_239;
import net.minecraft.world.World;

public abstract class TFGenerator
extends class_239 {
    protected World worldObj;

    @Override
    public abstract boolean method_1142(World var1, Random var2, int var3, int var4, int var5);

    protected boolean putBlock(int dx, int dy, int dz, int blockValue, boolean priority) {
        return this.putBlockAndMetadata(dx, dy, dz, blockValue, 0, priority);
    }

    protected boolean putBlockAndMetadata(int dx, int dy, int dz, int blockValue, int metaValue, boolean priority) {
        if (priority) {
            this.worldObj.method_154(dx, dy, dz, blockValue, metaValue);
        } else {
            int whatsThere = this.worldObj.getBlockId(dx, dy, dz);
            if (whatsThere == 0) {
                this.worldObj.method_154(dx, dy, dz, blockValue, metaValue);
            } else {
                return false;
            }
        }
        return true;
    }

    protected void putBlockAndMetadata(int[] pixel, int blockValue, int metaValue, boolean priority) {
        this.putBlockAndMetadata(pixel[0], pixel[1], pixel[2], blockValue, metaValue, priority);
    }

    protected void putBlock(int[] pixel, int blockValue, boolean priority) {
        this.putBlockAndMetadata(pixel[0], pixel[1], pixel[2], blockValue, 0, priority);
    }

    protected int[] translate(int sx, int sy, int sz, double distance, double angle, double tilt) {
        int[] dest = new int[]{sx, sy, sz};
        double rangle = angle * 2.0 * Math.PI;
        double rtilt = tilt * Math.PI;
        dest[0] = (int)((long)dest[0] + Math.round((double)(Math.sin((double)rangle) * Math.sin((double)rtilt) * distance)));
        dest[1] = (int)((long)dest[1] + Math.round((double)(Math.cos((double)rtilt) * distance)));
        dest[2] = (int)((long)dest[2] + Math.round((double)(Math.cos((double)rangle) * Math.sin((double)rtilt) * distance)));
        return dest;
    }

    protected void drawBresehnam(int x1, int y1, int z1, int x2, int y2, int z2, byte blockValue, boolean priority) {
        this.drawBresehnam(x1, y1, z1, x2, y2, z2, blockValue, (byte)0, priority);
    }

    protected void drawBresehnam(int x1, int y1, int z1, int x2, int y2, int z2, byte blockValue, byte metaValue, boolean priority) {
        int[] pixel = new int[]{x1, y1, z1};
        int dx = x2 - x1;
        int dy = y2 - y1;
        int dz = z2 - z1;
        int x_inc = dx < 0 ? -1 : 1;
        int l = Math.abs((int)dx);
        int y_inc = dy < 0 ? -1 : 1;
        int m = Math.abs((int)dy);
        int z_inc = dz < 0 ? -1 : 1;
        int n = Math.abs((int)dz);
        int dx2 = l << 1;
        int dy2 = m << 1;
        int dz2 = n << 1;
        if (l >= m && l >= n) {
            int err_1 = dy2 - l;
            int err_2 = dz2 - l;
            for (int i = 0; i < l; ++i) {
                this.putBlockAndMetadata(pixel, blockValue, metaValue, priority);
                if (err_1 > 0) {
                    pixel[1] = pixel[1] + y_inc;
                    err_1 -= dx2;
                }
                if (err_2 > 0) {
                    pixel[2] = pixel[2] + z_inc;
                    err_2 -= dx2;
                }
                err_1 += dy2;
                err_2 += dz2;
                pixel[0] = pixel[0] + x_inc;
            }
        } else if (m >= l && m >= n) {
            int err_1 = dx2 - m;
            int err_2 = dz2 - m;
            for (int i = 0; i < m; ++i) {
                this.putBlockAndMetadata(pixel, blockValue, metaValue, priority);
                if (err_1 > 0) {
                    pixel[0] = pixel[0] + x_inc;
                    err_1 -= dy2;
                }
                if (err_2 > 0) {
                    pixel[2] = pixel[2] + z_inc;
                    err_2 -= dy2;
                }
                err_1 += dx2;
                err_2 += dz2;
                pixel[1] = pixel[1] + y_inc;
            }
        } else {
            int err_1 = dy2 - n;
            int err_2 = dx2 - n;
            for (int i = 0; i < n; ++i) {
                this.putBlockAndMetadata(pixel, blockValue, metaValue, priority);
                if (err_1 > 0) {
                    pixel[1] = pixel[1] + y_inc;
                    err_1 -= dz2;
                }
                if (err_2 > 0) {
                    pixel[0] = pixel[0] + x_inc;
                    err_2 -= dz2;
                }
                err_1 += dy2;
                err_2 += dx2;
                pixel[2] = pixel[2] + z_inc;
            }
        }
        this.putBlockAndMetadata(pixel, blockValue, metaValue, priority);
    }

    public void drawCircle(int sx, int sy, int sz, byte rad, byte blockValue, int metaValue, boolean priority) {
        for (byte dx = 0; dx <= rad; dx = (byte)(dx + 1)) {
            for (byte dz = 0; dz <= rad; dz = (byte)(dz + 1)) {
                int dist = (int)((double)Math.max((int)dx, (int)dz) + (double)Math.min((int)dx, (int)dz) * 0.5);
                if (dx == 3 && dz == 3) {
                    dist = 6;
                }
                if (dist > rad) continue;
                this.putBlockAndMetadata(sx + dx, sy, sz + dz, blockValue, metaValue, priority);
                this.putBlockAndMetadata(sx + dx, sy, sz - dz, blockValue, metaValue, priority);
                this.putBlockAndMetadata(sx - dx, sy, sz + dz, blockValue, metaValue, priority);
                this.putBlockAndMetadata(sx - dx, sy, sz - dz, blockValue, metaValue, priority);
            }
        }
    }

    public void drawDiameterCircle(int sx, int sy, int sz, int diam, int block, int meta, boolean priority) {
        byte rad = (byte)((diam - 1) / 2);
        if (diam % 2 == 1) {
            this.drawCircle(sx, sy, sz, rad, (byte)block, meta, priority);
        } else {
            this.drawCircle(sx, sy, sz, rad, (byte)block, meta, priority);
            this.drawCircle(sx + 1, sy, sz, rad, (byte)block, meta, priority);
            this.drawCircle(sx, sy, sz + 1, rad, (byte)block, meta, priority);
            this.drawCircle(sx + 1, sy, sz + 1, rad, (byte)block, meta, priority);
        }
    }

    protected byte randStone(Random rand, int howMuch) {
        return rand.nextInt(howMuch) >= 1 ? (byte)Block.COBBLESTONE.id : (byte)Block.MOSSY_COBBLESTONE.id;
    }

    protected boolean isAreaClear(World world, Random rand, int x, int y, int z, int dx, int dy, int dz) {
        boolean flag = true;
        for (int cx = 0; cx < dx; ++cx) {
            for (int cz = 0; cz < dy; ++cz) {
                Material m = world.method_1779(x + cx, y - 1, z + cz);
                if (m != Material.SOIL && m != Material.SOLID_ORGANIC && m != Material.STONE) {
                    flag = false;
                }
                for (int cy = 0; cy < dz; ++cy) {
                    if (world.method_234(x + cx, y + cy, z + cz)) continue;
                    flag = false;
                }
            }
        }
        return flag;
    }
}