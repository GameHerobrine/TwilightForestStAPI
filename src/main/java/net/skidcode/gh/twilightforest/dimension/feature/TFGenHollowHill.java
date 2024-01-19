package net.skidcode.gh.twilightforest.dimension.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.class_104;
import net.minecraft.class_225;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TFGenHollowHill
extends TFGenerator {
    int hsize;
    int radius;
    int sn;
    int mg;
    int tc;
    int hx;
    int hy;
    int hz;
    Random hillRNG;

    public TFGenHollowHill(int size) {
        this.hsize = size;
        this.radius = (this.hsize * 2 + 1) * 8 - 6;
        int area = (int)(Math.PI * (double)this.radius * (double)this.radius);
        this.sn = area / 16;
        int[] mga = new int[]{0, 3, 9, 18};
        this.mg = mga[this.hsize];
        int[] tca = new int[]{0, 2, 6, 12};
        this.tc = tca[this.hsize];
    }

    @Override
    public boolean method_1142(World world, Random rand, int x, int y, int z) {
        int[] dest;
        int i;
        this.worldObj = world;
        this.hx = x;
        this.hy = y;
        this.hz = z;
        this.hillRNG = rand;
        for (i = 0; i < this.mg; ++i) {
            dest = this.getCoordsInHill2D();
            this.placeMobSpawner(dest[0], this.hy + rand.nextInt(4), dest[1]);
        }
        for (i = 0; i < this.tc; ++i) {
            dest = this.getCoordsInHill2D();
            this.placeTreasureChest(dest[0], this.hy, dest[1]);
        }
        for (i = 0; i < this.sn; ++i) {
            dest = this.getCoordsInHill2D();
            TFGenCaveStalactite stalag = TFGenCaveStalactite.makeRandomOreStalactite(rand, this.hsize);
            stalag.method_1142(this.worldObj, rand, dest[0], this.hy + 1, dest[1]);
        }
        for (i = 0; i < this.sn; ++i) {
            dest = this.getCoordsInHill2D();
            new TFGenCaveStalactite(Block.STONE.id, rand.nextDouble(), true).method_1142(this.worldObj, rand, dest[0], this.hy + 1, dest[1]);
        }
        for (i = 0; i < this.sn; ++i) {
            dest = this.getCoordsInHill2D();
            new TFGenCaveStalactite(Block.STONE.id, rand.nextDouble() * 0.7, false).method_1142(this.worldObj, rand, dest[0], this.hy + 1, dest[1]);
        }
        return true;
    }

    boolean isInHill(int cx, int cz) {
        int dx = this.hx - cx;
        int dz = this.hz - cz;
        int dist = (int)Math.sqrt((double)(dx * dx + dz * dz));
        return dist < this.radius;
    }

    int[] getCoordsInHill2D() {
        int rz;
        int rx;
        while (!this.isInHill(rx = this.hx + this.hillRNG.nextInt(2 * this.radius) - this.radius, rz = this.hz + this.hillRNG.nextInt(2 * this.radius) - this.radius)) {
        }
        int[] coords = new int[]{rx, rz};
        return coords;
    }

    protected boolean placeMobSpawner(int dx, int dy, int dz) {
        this.worldObj.setBlock(dx, dy, dz, Block.SPAWNER.id);
        class_104 ms = (class_104)this.worldObj.method_1777(dx, dy, dz);
        if (ms != null) {
            ms.method_2035(this.getMobID(this.hsize));
        }
        return true;
    }

    protected String getMobID(int level) {
        if (level == 1) {
            return "Spider";
        }
        if (level == 2) {
            if (this.hillRNG.nextInt(4) != 0) {
                return "Zombie";
            }
            return this.getMobID(1);
        }
        if (level == 3) {
            if (this.hillRNG.nextInt(4) != 0) {
                return "Skeleton";
            }
            return this.getMobID(2);
        }
        return "Spider";
    }

    protected boolean placeTreasureChest(int dx, int dy, int dz) {
        this.worldObj.setBlock(dx, dy, dz, Block.CHEST.id);
        class_225 tec = (class_225)this.worldObj.method_1777(dx, dy, dz);
        if (tec != null && tec.size() > 0) {
            int ni = this.hillRNG.nextInt(4) + this.hillRNG.nextInt(4) + 2;
            for (int i = 0; i < ni; ++i) {
                tec.setStack(i, this.getTreasure(this.hsize));
            }
        }
        return true;
    }

    protected ItemStack getTreasure(int level) {
        if (level == 1) {
            switch (this.hillRNG.nextInt(6)) {
                case 0: {
                    return new ItemStack(Item.IRON_INGOT, this.hillRNG.nextInt(4) + 1);
                }
                case 1: {
                    return new ItemStack(Item.BUCKET);
                }
                case 2: {
                    return new ItemStack(Item.BREAD);
                }
                case 4: {
                    return new ItemStack(Item.WHEAT, this.hillRNG.nextInt(3) + 1);
                }
            }
            return new ItemStack(Block.TORCH, this.hillRNG.nextInt(16) + 1);
        }
        if (level == 2) {
            switch (this.hillRNG.nextInt(8)) {
                case 0: 
                case 1: 
                case 2: {
                    return this.getTreasure(1);
                }
                case 4: {
                    return new ItemStack(Item.GOLD_INGOT, this.hillRNG.nextInt(6) + 1);
                }
                case 5: {
                    return new ItemStack(Item.SADDLE);
                }
                case 6: {
                    return new ItemStack(Item.DYE, this.hillRNG.nextInt(10) + 1, this.hillRNG.nextInt(16));
                }
            }
            return new ItemStack(Item.MUSHROOM_STEW);
        }
        if (level == 3) {
            switch (this.hillRNG.nextInt(8)) {
                case 0: 
                case 1: 
                case 2: {
                    return this.getTreasure(2);
                }
                case 4: {
                    return new ItemStack(Item.GOLDEN_APPLE);
                }
                case 5: {
                    return new ItemStack(Item.RECORD_THIRTEEN);
                }
                case 6: {
                    return new ItemStack(Item.SADDLE);
                }
            }
            return new ItemStack(Item.DIAMOND);
        }
        return new ItemStack(Item.COAL, this.hillRNG.nextInt(16) + 1);
    }
}