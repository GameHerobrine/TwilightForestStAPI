package net.skidcode.gh.twilightforest.dimension;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SandBlock;
import net.minecraft.class_105;
import net.minecraft.class_153;
import net.minecraft.class_192;
import net.minecraft.class_209;
import net.minecraft.class_231;
import net.minecraft.class_239;
import net.minecraft.class_415;
import net.minecraft.class_43;
import net.minecraft.class_51;
import net.minecraft.class_517;
import net.minecraft.class_529;
import net.minecraft.class_538;
import net.minecraft.class_62;
import net.minecraft.class_623;
import net.minecraft.class_624;
import net.minecraft.class_82;
import net.minecraft.world.World;
import net.modificationstation.stationapi.impl.world.chunk.FlattenedChunk;
import net.skidcode.gh.twilightforest.biome.TFBiomeGenBase;
import net.skidcode.gh.twilightforest.dimension.feature.*;

public class ChunkProviderTwilightForest
extends class_538
implements class_51 {
    private Random rand;
    private class_209 noiseK;
    private class_209 noiseL;
    private class_209 field_910_m;
    public class_209 a;
    public class_209 b;
    public class_209 c;
    private World worldObj;
    private double[] terrainLevel;
    private double[] dirtNoise = new double[256];
    private class_105 caveGen = new class_415();
    private class_153[] biomesForGeneration;
    double[] d;
    double[] valuesK;
    double[] f;
    double[] g;
    double[] h;
    int[][] field_914_i = new int[32][32];
    private double[] generatedTemperatures;

    public ChunkProviderTwilightForest(World world, long l) {
        super(world, l);
        this.worldObj = world;
        this.rand = new Random(l);
        this.noiseK = new class_209(this.rand, 16);
        this.noiseL = new class_209(this.rand, 16);
        this.field_910_m = new class_209(this.rand, 8);
        this.a = new class_209(this.rand, 10);
        this.b = new class_209(this.rand, 16);
        this.c = new class_209(this.rand, 8);
    }

    @Override
    public class_43 method_1807(int i, int j) {
        return this.method_1806(i, j);
    }

    @Override
    public class_43 method_1806(int cx, int cz) {
        this.rand.setSeed((long)cx * 341873128712L + (long)cz * 132897987541L);
        byte[] abyte0 = new byte[32768];
        FlattenedChunk chunk = new FlattenedChunk(this.worldObj, cx, cz);
        //class_43 chunk = new class_43(this.worldObj, abyte0, cx, cz);
        this.biomesForGeneration = this.worldObj.method_1781().method_1791(this.biomesForGeneration, cx * 16, cz * 16, 16, 16);
        double[] temperature = this.worldObj.method_1781().field_2235;
        double[] humidity = this.worldObj.method_1781().field_2236;
        this.method_1798(cx, cz, abyte0, this.biomesForGeneration, temperature);
        this.terraform(cx, cz, abyte0, this.biomesForGeneration, temperature, humidity);
        this.addGlaciers(cx, cz, abyte0, this.biomesForGeneration, temperature);
        this.raiseHills(cx, cz, abyte0);
        this.replaceBlocksForBiome(cx, cz, abyte0, this.biomesForGeneration, temperature);
        chunk.fromLegacy(abyte0);
        chunk.method_873();
        return chunk;
    }

    @Override
    public void method_1798(int chunkX, int chunkZ, byte[] storage, class_153[] abiomegenbase, double[] temperature) {
        int seventeen = 17;
        this.terrainLevel = this.generateTerrainLevels(this.terrainLevel, chunkX * 4, 0, chunkZ * 4, 5, seventeen, 5);
        for (int x = 0; x < 4; ++x) {
            for (int z = 0; z < 4; ++z) {
                for (int q = 0; q < 16; ++q) {
                    double oneEighth = 0.125;
                    double d1 = this.terrainLevel[((x + 0) * 5 + (z + 0)) * seventeen + (q + 0)];
                    double d2 = this.terrainLevel[((x + 0) * 5 + (z + 1)) * seventeen + (q + 0)];
                    double d3 = this.terrainLevel[((x + 1) * 5 + (z + 0)) * seventeen + (q + 0)];
                    double d4 = this.terrainLevel[((x + 1) * 5 + (z + 1)) * seventeen + (q + 0)];
                    double d5 = (this.terrainLevel[((x + 0) * 5 + (z + 0)) * seventeen + (q + 1)] - d1) * oneEighth;
                    double d6 = (this.terrainLevel[((x + 0) * 5 + (z + 1)) * seventeen + (q + 1)] - d2) * oneEighth;
                    double d7 = (this.terrainLevel[((x + 1) * 5 + (z + 0)) * seventeen + (q + 1)] - d3) * oneEighth;
                    double d8 = (this.terrainLevel[((x + 1) * 5 + (z + 1)) * seventeen + (q + 1)] - d4) * oneEighth;
                    for (int yy = 0; yy < 8; ++yy) {
                        double oneQuarter = 0.25;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * oneQuarter;
                        double d13 = (d4 - d2) * oneQuarter;
                        for (int xx = 0; xx < 4; ++xx) {
                            int index = xx + x * 4 << 11 | 0 + z * 4 << 7 | q * 8 + yy;
                            int c = 128;
                            double anotherQuarter = 0.25;
                            double earthiness = d10;
                            double d16 = (d11 - d10) * anotherQuarter;
                            for (int zz = 0; zz < 4; ++zz) {
                                int terrainHere = 0;
                                if (earthiness > 0.0) {
                                    terrainHere = Block.STONE.id;
                                }
                                storage[index] = (byte)terrainHere;
                                index += c;
                                earthiness += d16;
                            }
                            d10 += d12;
                            d11 += d13;
                        }
                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void terraform(int cx, int cz, byte[] storage, class_153[] biomes, double[] temp, double[] humidity) {
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                double f;
                double squishMultiplyer = 0.25;
                class_153 biome = biomes[x * 16 + z];
                double t = temp[x * 16 + z];
                double h = humidity[x * 16 + z];
                if (biome == TFBiomeGenBase.twilightHighland) {
                    f = (1.0 - h * 4.0) * (1.0 - (1.0 - t) * 4.0);
                    squishMultiplyer = 0.35 + 0.25 * f;
                }
                if (biome == TFBiomeGenBase.twilightSwamp) {
                    f = (1.0 - (1.0 - h) * 4.0) * (1.0 - (1.0 - t) * 4.0);
                    squishMultiplyer = 0.24 - 0.04 * f;
                }
                int oldGround = -1;
                int newGround = -1;
                for (int y = 127; y >= 0; --y) {
                    int index = (x * 16 + z) * 128 + y;
                    byte currentTerrain = storage[index];
                    if (currentTerrain == 0) continue;
                    if (newGround == -1) {
                        oldGround = y;
                        newGround = (int)((double)oldGround * squishMultiplyer);
                    }
                    if (y < newGround) continue;
                    storage[index] = 0;
                }
            }
        }
    }

    public void raiseHills(int cx, int cz, byte[] storage) {
        if (!ChunkProviderTwilightForest.nearHollowHill(cx, cz, 0L)) {
            return;
        }
        int[] chill = ChunkProviderTwilightForest.nearestHillCenter(cx, cz, 0L);
        int hsize = ChunkProviderTwilightForest.nearestHillSize(cx, cz, 0L);
        double hdiam = (double)(hsize * 2 + 1) * 16.0;
        int hx = chill[0];
        int hz = chill[1];
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                int oldGround = -1;
                int newGround = -1;
                int dx = x - hx;
                int dz = z - hz;
                int dist = (int)Math.sqrt((double)(dx * dx + dz * dz));
                int hheight = (int)(Math.cos((double)((double)dist / hdiam * Math.PI)) * (hdiam / 3.0));
                for (int y = 0; y <= 127; ++y) {
                    int index = (x * 16 + z) * 128 + y;
                    byte currentTerrain = storage[index];
                    if (currentTerrain != 0 && currentTerrain != Block.ICE.id) continue;
                    if (newGround == -1) {
                        oldGround = y;
                        newGround = oldGround + hheight;
                    }
                    if (y > newGround) continue;
                    storage[index] = (byte)Block.STONE.id;
                }
                int hollow = hheight - 4;
                if (hollow < 0) {
                    hollow = 0;
                }
                for (int y = 0; y <= 127; ++y) {
                    int index = (x * 16 + z) * 128 + y;
                    if (y <= 16 || y >= 16 + hollow) continue;
                    storage[index] = 0;
                }
            }
        }
    }

    public static boolean nearHollowHill(int cx, int cz, long seed) {
        for (int rad = 1; rad <= 3; ++rad) {
            for (int x = -rad; x <= rad; ++x) {
                for (int z = -rad; z <= rad; ++z) {
                    if (ChunkProviderTwilightForest.hillSize(x + cx, z + cz, seed) != rad) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public static int[] nearestHillCenter(int cx, int cz, long seed) {
        for (int rad = 1; rad <= 3; ++rad) {
            for (int x = -rad; x <= rad; ++x) {
                for (int z = -rad; z <= rad; ++z) {
                    if (ChunkProviderTwilightForest.hillSize(x + cx, z + cz, seed) != rad) continue;
                    int[] center = new int[]{x * 16 + 8, z * 16 + 8};
                    return center;
                }
            }
        }
        int[] no = new int[]{0, 0};
        return no;
    }

    public static boolean isHollowHill(int cx, int cz, long seed) {
        return ChunkProviderTwilightForest.hillSize(cx, cz, seed) > 0;
    }

    public static int nearestHillSize(int cx, int cz, long seed) {
        for (int rad = 1; rad <= 3; ++rad) {
            for (int x = -rad; x <= rad; ++x) {
                for (int z = -rad; z <= rad; ++z) {
                    if (ChunkProviderTwilightForest.hillSize(x + cx, z + cz, seed) != rad) continue;
                    return rad;
                }
            }
        }
        return -1;
    }

    public static int hillSize(int cx, int cz, long seed) {
        Random hillRNG = new Random(seed + (long)(cx * 25117) + (long)(cz * 151121));
        int hn = hillRNG.nextInt();
        int hs = -1;
        if (!(cx % 7 != 4 && cx % 7 != -4 || cz % 7 != 4 && cz % 7 != -4 || (hs = Math.abs((int)(hn % 6))) != 0 && hs <= 3)) {
            hs = -1;
        }
        return hs;
    }

    public void replaceBlocksForBiome(int chunkX, int chunkY, byte[] storage, class_153[] biomes, double[] temps) {
        boolean isHill = ChunkProviderTwilightForest.nearHollowHill(chunkX, chunkY, 0L);
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                class_153 biome = biomes[x + z * 16];
                byte top = biome.field_890;
                byte filler = biome.field_891;
                int fillLevel = 1 + (int)(this.rand.nextDouble() * this.rand.nextDouble() * 3.0 + 0.65);
                int topLevel = -1;
                for (int y = 127; y >= 0; --y) {
                    int index = (z * 16 + x) * 128 + y;
                    if (y <= 8) {
                        if (isHill) {
                            byte mb = (byte)Block.BEDROCK.id;
                            switch (y) {
                                case 1: 
                                case 2: {
                                    mb = (byte)Block.OBSIDIAN.id;
                                    break;
                                }
                                case 3: {
                                    mb = (byte)Block.GLOWSTONE.id;
                                    break;
                                }
                                case 4: 
                                case 5: 
                                case 6: {
                                    mb = 0;
                                    break;
                                }
                                case 7: 
                                case 8: {
                                    mb = (byte)Block.GLOWSTONE.id;
                                }
                            }
                            storage[index] = mb;
                            continue;
                        }
                        storage[index] = (byte)Block.BEDROCK.id;
                        continue;
                    }
                    byte cb = storage[index];
                    if (cb == 0 || cb != Block.STONE.id) continue;
                    if (topLevel == -1) {
                        topLevel = y;
                        storage[index] = top;
                        continue;
                    }
                    if (y >= topLevel || y < topLevel - fillLevel) continue;
                    storage[index] = filler;
                }
            }
        }
    }

    public void addGlaciers(int chunkX, int chunkY, byte[] storage, class_153[] biomes, double[] temps) {
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                class_153 biome = biomes[x + z * 16];
                if (biome != TFBiomeGenBase.twilightGlacier) continue;
                int topLevel = -1;
                for (int y = 127; y >= 0; --y) {
                    int index = (z * 16 + x) * 128 + y;
                    byte cb = storage[index];
                    if (cb != Block.STONE.id) continue;
                    topLevel = y;
                    break;
                }
                double t = Math.min((double)temps[z * 16 + x], (double)0.1);
                int gHeight = 10 + (int)((0.1 - t) * 10.0);
                int gTop = topLevel + gHeight + 1;
                for (int y = topLevel + 1; y <= gTop && y < 128; ++y) {
                    int index = (z * 16 + x) * 128 + y;
                    storage[index] = (byte)Block.ICE.id;
                }
            }
        }
    }

    private double[] generateTerrainLevels(double[] storage, int chunkX, int chunkY, int chunkZ, int width, int height, int length) {
        if (storage == null) {
            storage = new double[width * height * length];
        }
        double d = 684.412;
        double d1 = 684.412;
        double[] temperature = this.worldObj.method_1781().field_2235;
        double[] humidity = this.worldObj.method_1781().field_2236;
        this.g = this.a.method_1515(this.g, chunkX, chunkZ, width, length, 1.121, 1.121, 0.5);
        this.h = this.b.method_1515(this.h, chunkX, chunkZ, width, length, 200.0, 200.0, 0.5);
        this.d = this.field_910_m.method_1514(this.d, chunkX, chunkY, chunkZ, width, height, length, d / 80.0, d1 / 160.0, d / 80.0);
        this.valuesK = this.noiseK.method_1514(this.valuesK, chunkX, chunkY, chunkZ, width, height, length, d, d1, d);
        this.f = this.noiseL.method_1514(this.f, chunkX, chunkY, chunkZ, width, height, length, d, d1, d);
        int index2 = 0;
        int index1 = 0;
        int i2 = 16 / width;
        for (int j2 = 0; j2 < width; ++j2) {
            int k2 = j2 * i2 + i2 / 2;
            for (int l2 = 0; l2 < length; ++l2) {
                double d6;
                int i3 = l2 * i2 + i2 / 2;
                double localTemperature = temperature[k2 * 16 + i3];
                double localHumidity = humidity[k2 * 16 + i3] * localTemperature;
                double inverseHumidity = 1.0 - localHumidity;
                inverseHumidity *= inverseHumidity;
                inverseHumidity *= inverseHumidity;
                inverseHumidity = 1.0 - inverseHumidity;
                double d5 = (this.g[index1] + 256.0) / 512.0;
                if ((d5 *= inverseHumidity) > 1.0) {
                    d5 = 1.0;
                }
                if ((d6 = this.h[index1] / 8000.0) < 0.0) {
                    d6 = -d6 * 0.3;
                }
                if ((d6 = d6 * 3.0 - 2.0) < 0.0) {
                    if ((d6 /= 2.0) < -1.0) {
                        d6 = -1.0;
                    }
                    d6 /= 1.4;
                    d6 /= 2.0;
                    d5 = 0.0;
                } else {
                    if (d6 > 1.0) {
                        d6 = 1.0;
                    }
                    d6 /= 8.0;
                }
                if (d5 < 0.0) {
                    d5 = 0.0;
                }
                d5 += 0.5;
                d6 = d6 * (double)height / 16.0;
                double d7 = (double)height / 2.0 + d6 * 4.0;
                ++index1;
                for (int j3 = 0; j3 < height; ++j3) {
                    double result = 0.0;
                    double d9 = ((double)j3 - d7) * 12.0 / d5;
                    if (d9 < 0.0) {
                        d9 *= 4.0;
                    }
                    double d10 = this.valuesK[index2] / 512.0;
                    double d11 = this.f[index2] / 512.0;
                    double d12 = (this.d[index2] / 10.0 + 1.0) / 2.0;
                    result = d12 < 0.0 ? d10 : (d12 > 1.0 ? d11 : d10 + (d11 - d10) * d12);
                    result -= d9;
                    if (j3 > height - 4) {
                        double d13 = (float)(j3 - (height - 4)) / 3.0f;
                        result = result * (1.0 - d13) + -10.0 * d13;
                    }
                    storage[index2] = result;
                    ++index2;
                }
            }
        }
        return storage;
    }

    @Override
    public boolean method_1802(int i, int j) {
        return true;
    }

    @Override
    public void method_1803(class_51 ichunkprovider, int chunkX, int chunkZ) {
        int i;
        int i8;
        int l4;
        int i1;
        SandBlock.field_375 = true;
        int mapX = chunkX * 16;
        int mapZ = chunkZ * 16;
        class_153 biomegenbase = this.worldObj.method_1781().method_1787(mapX + 16, mapZ + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long l1 = this.rand.nextLong() / 2L * 2L + 1L;
        long l2 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)chunkX * l1 + (long)chunkZ * l2 ^ this.worldObj.getSeed());
        if (this.rand.nextInt(4) != 0 || new class_624(Block.WATER.id).method_1142(this.worldObj, this.rand, i1 = mapX + this.rand.nextInt(16) + 8, l4 = this.rand.nextInt(128), i8 = mapZ + this.rand.nextInt(16) + 8)) {
            // empty if block
        }
        if (biomegenbase == TFBiomeGenBase.twilightSwamp) {
            for (int i2 = 0; i2 < 6; ++i2) {
                int rx = mapX + this.rand.nextInt(16) + 8;
                int rz = mapZ + this.rand.nextInt(16) + 8;
                int ry = this.worldObj.method_222(rx, rz);
                new class_624(Block.WATER.id).method_1142(this.worldObj, this.rand, rx, ry, rz);
            }
        }
        if (this.rand.nextInt(64) == 0) {
            int j1 = mapX + this.rand.nextInt(16) + 8;
            int i5 = this.rand.nextInt(this.rand.nextInt(120) + 8);
            int j8 = mapZ + this.rand.nextInt(16) + 8;
            if (i5 < 64 || this.rand.nextInt(10) == 0) {
                new class_624(Block.LAVA.id).method_1142(this.worldObj, this.rand, j1, i5, j8);
            }
        }
        if (this.rand.nextInt(4) == 0) {
            int fx = mapX + this.rand.nextInt(16) + 8;
            int fz = mapZ + this.rand.nextInt(16) + 8;
            int fy = this.worldObj.method_228(fx, fz);
            TFGenerator rf = this.randomFeature(this.rand, (TFBiomeGenBase)biomegenbase);
            if (rf.method_1142(this.worldObj, this.rand, fx, fy, fz)) {
                System.out.println(rf + " success at " + fx + ", " + fy + ", " + fz);
            }
        }
        if (this.rand.nextInt(4) == 0) {
            int treeX = mapX + this.rand.nextInt(16) + 8;
            int treeZ = mapZ + this.rand.nextInt(16) + 8;
            new TFGenHollowTree().method_1142(this.worldObj, this.rand, treeX, this.worldObj.method_228(treeX, treeZ), treeZ);
        }
        for (int j2 = 0; j2 < 20; ++j2) {
            int l5 = mapX + this.rand.nextInt(16);
            int i9 = this.rand.nextInt(128);
            int l11 = mapZ + this.rand.nextInt(16);
            new class_82(Block.DIRT.id, 32).method_1142(this.worldObj, this.rand, l5, i9, l11);
        }
        for (int k2 = 0; k2 < 10; ++k2) {
            int i6 = mapX + this.rand.nextInt(16);
            int j9 = this.rand.nextInt(128);
            int i12 = mapZ + this.rand.nextInt(16);
            new class_82(Block.GRAVEL.id, 32).method_1142(this.worldObj, this.rand, i6, j9, i12);
        }
        for (int i3 = 0; i3 < 20; ++i3) {
            int j6 = mapX + this.rand.nextInt(16);
            int k9 = this.rand.nextInt(128);
            int j12 = mapZ + this.rand.nextInt(16);
            new class_82(Block.COAL_ORE.id, 16).method_1142(this.worldObj, this.rand, j6, k9, j12);
        }
        for (int j3 = 0; j3 < 20; ++j3) {
            int k6 = mapX + this.rand.nextInt(16);
            int l9 = this.rand.nextInt(64);
            int k12 = mapZ + this.rand.nextInt(16);
            new class_82(Block.IRON_ORE.id, 8).method_1142(this.worldObj, this.rand, k6, l9, k12);
        }
        for (int j4 = 0; j4 < 1; ++j4) {
            int k7 = mapX + this.rand.nextInt(16);
            int l10 = this.rand.nextInt(16) + this.rand.nextInt(16);
            int k13 = mapZ + this.rand.nextInt(16);
            new class_82(Block.LAPIS_ORE.id, 6).method_1142(this.worldObj, this.rand, k7, l10, k13);
        }
        int k4 = (int)((this.c.method_1513((double)mapX * 0.5, (double)mapZ * 0.5) / 8.0 + this.rand.nextDouble() * 4.0 + 4.0) / 3.0);
        int treeFreq = k4 + 20;
        if (biomegenbase == TFBiomeGenBase.twilightSwamp) {
            treeFreq -= 18;
        }
        if (biomegenbase == TFBiomeGenBase.twilightSnow) {
            treeFreq -= 10;
        }
        if (biomegenbase == TFBiomeGenBase.twilightHighland) {
            treeFreq -= 10;
        }
        if (biomegenbase == TFBiomeGenBase.twilightGlacier) {
            treeFreq -= 10;
        }
        if (biomegenbase == TFBiomeGenBase.twilightClearings) {
            treeFreq = 0;
        }
        for (int i11 = 0; i11 < treeFreq; ++i11) {
            int l13 = mapX + this.rand.nextInt(16) + 8;
            int j14 = mapZ + this.rand.nextInt(16) + 8;
            class_239 worldgenerator = biomegenbase.method_797(this.rand);
            worldgenerator.method_1143(1.0, 1.0, 1.0);
            worldgenerator.method_1142(this.worldObj, this.rand, l13, this.worldObj.method_222(l13, j14), j14);
        }
        int yellowChance = 2;
        if (biomegenbase == TFBiomeGenBase.twilightForest) {
            yellowChance = 2;
        }
        for (int i14 = 0; i14 < yellowChance; ++i14) {
            int k14 = mapX + this.rand.nextInt(16) + 8;
            int l16 = this.rand.nextInt(128);
            int k19 = mapZ + this.rand.nextInt(16) + 8;
            new class_231(Block.DANDELION.id).method_1142(this.worldObj, this.rand, k14, l16, k19);
        }
        int grassFreq = 7;
        if (biomegenbase == TFBiomeGenBase.twilightClearings) {
            grassFreq = 3;
        }
        if (biomegenbase == TFBiomeGenBase.twilightSwamp) {
            grassFreq = 1;
        }
        for (int l14 = 0; l14 < grassFreq; ++l14) {
            int grassType = 1;
            if ((biomegenbase == TFBiomeGenBase.twilightForest || biomegenbase == TFBiomeGenBase.twilightMushroom || biomegenbase == TFBiomeGenBase.twilightSwamp) && this.rand.nextInt(3) != 0) {
                grassType = 2;
            }
            int l19 = mapX + this.rand.nextInt(16) + 8;
            int k22 = this.rand.nextInt(128);
            int j24 = mapZ + this.rand.nextInt(16) + 8;
            new class_623(Block.GRASS.id, grassType).method_1142(this.worldObj, this.rand, l19, k22, j24);
        }
        if (this.rand.nextInt(2) == 0) {
            int j15 = mapX + this.rand.nextInt(16) + 8;
            int j17 = this.rand.nextInt(128);
            int j20 = mapZ + this.rand.nextInt(16) + 8;
            new class_231(Block.ROSE.id).method_1142(this.worldObj, this.rand, j15, j17, j20);
        }
        if (this.rand.nextInt(3) == 0) {
            int k15 = mapX + this.rand.nextInt(16) + 8;
            int k17 = this.rand.nextInt(64);
            int k20 = mapZ + this.rand.nextInt(16) + 8;
            new class_231(Block.BROWN_MUSHROOM.id).method_1142(this.worldObj, this.rand, k15, k17, k20);
        }
        if (this.rand.nextInt(6) == 0) {
            int l15 = mapX + this.rand.nextInt(16) + 8;
            int l17 = this.rand.nextInt(64);
            int l20 = mapZ + this.rand.nextInt(16) + 8;
            new class_231(Block.RED_MUSHROOM.id).method_1142(this.worldObj, this.rand, l15, l17, l20);
        }
        if (biomegenbase == TFBiomeGenBase.twilightMushroom) {
            int rz;
            int ry;
            for (i = 0; i < 48; ++i) {
                int rx = mapX + this.rand.nextInt(16) + 8;
                ry = this.rand.nextInt(64);
                rz = mapZ + this.rand.nextInt(16) + 8;
                new class_231(Block.BROWN_MUSHROOM.id).method_1142(this.worldObj, this.rand, rx, ry, rz);
            }
            for (i = 0; i < 24; ++i) {
                int rx = mapX + this.rand.nextInt(16) + 8;
                ry = this.rand.nextInt(64);
                rz = mapZ + this.rand.nextInt(16) + 8;
                new class_231(Block.RED_MUSHROOM.id).method_1142(this.worldObj, this.rand, rx, ry, rz);
            }
        }
        for (i = 0; i < 3; ++i) {
            int rx = mapX + this.rand.nextInt(16) + 8;
            int rz = mapZ + this.rand.nextInt(16) + 8;
            int ry = this.worldObj.method_222(rx, rz);
            new class_192().method_1142(this.worldObj, this.rand, rx, ry, rz);
        }
        if (this.rand.nextInt(32) == 0) {
            int j16 = mapX + this.rand.nextInt(16) + 8;
            int j18 = this.rand.nextInt(128);
            int j21 = mapZ + this.rand.nextInt(16) + 8;
            new class_517().method_1142(this.worldObj, this.rand, j16, j18, j21);
        }
        for (int l18 = 0; l18 < 50; ++l18) {
            int l21 = mapX + this.rand.nextInt(16) + 8;
            int k23 = this.rand.nextInt(this.rand.nextInt(120) + 8);
            int l24 = mapZ + this.rand.nextInt(16) + 8;
            new class_529(Block.FLOWING_WATER.id).method_1142(this.worldObj, this.rand, l21, k23, l24);
        }
        for (int i19 = 0; i19 < 10; ++i19) {
            int i22 = mapX + this.rand.nextInt(16) + 8;
            int l23 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(112) + 8) + 8);
            int i25 = mapZ + this.rand.nextInt(16) + 8;
            new class_529(Block.FLOWING_LAVA.id).method_1142(this.worldObj, this.rand, i22, l23, i25);
        }
        this.generatedTemperatures = this.worldObj.method_1781().method_1790(this.generatedTemperatures, mapX + 8, mapZ + 8, 16, 16);
        for (int j19 = mapX + 8; j19 < mapX + 8 + 16; ++j19) {
            for (int j22 = mapZ + 8; j22 < mapZ + 8 + 16; ++j22) {
                int i24 = j19 - (mapX + 8);
                int j25 = j22 - (mapZ + 8);
                int k25 = this.worldObj.method_228(j19, j22);
                double d1 = this.generatedTemperatures[i24 * 16 + j25] - (double)(k25 - 64) / 64.0 * 0.3;
                if (!(d1 < 0.5) || k25 <= 0 || k25 >= 128 || !this.worldObj.method_234(j19, k25, j22) || !this.worldObj.method_1779(j19, k25 - 1, j22).method_907() || this.worldObj.method_1779(j19, k25 - 1, j22) == Material.field_997) continue;
                this.worldObj.setBlock(j19, k25, j22, Block.SNOW.id);
            }
        }
        int hsize = ChunkProviderTwilightForest.nearestHillSize(chunkX, chunkZ, 0L);
        if (ChunkProviderTwilightForest.isHollowHill(chunkX, chunkZ, 0L) && hsize > 0) {
            new TFGenHollowHill(hsize).method_1142(this.worldObj, this.rand, mapX + 8, 17, mapZ + 8);
        }
        SandBlock.field_375 = false;
    }

    public TFGenerator randomFeature(Random rand, TFBiomeGenBase biome) {
        int rf = rand.nextInt(6);
        switch (rf) {
            case 0: {
                return new TFGenStoneCircle();
            }
            case 1: {
                return new TFGenWell();
            }
            case 2: {
                return new TFGenWitchHut();
            }
            case 3: {
                return new TFGenOutsideStalagmite();
            }
            case 4: {
                return new TFGenFoundation();
            }
            case 5: {
                return new TFGenMonolith();
            }
        }
        return new TFGenStoneCircle();
    }

    @Override
    public boolean method_1804(boolean flag, class_62 iprogressupdate) {
        return true;
    }

    @Override
    public boolean method_1801() {
        return false;
    }

    @Override
    public boolean method_1805() {
        return true;
    }

    @Override
    public String method_1808() {
        return "RandomTwilightForestLevelSource";
    }
}