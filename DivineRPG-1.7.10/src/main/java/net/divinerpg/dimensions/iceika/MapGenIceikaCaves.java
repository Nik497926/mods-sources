/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.MapGenBase
 */
package net.divinerpg.dimensions.iceika;

import java.util.Random;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.MapGenBase;

public class MapGenIceikaCaves
extends MapGenBase {
    protected void func_151542_a(long l, int i, int j, Block[] b, double d, double d1, double d2) {
        this.func_151541_a(l, i, j, b, d, d1, d2, 1.0f + this.rand.nextFloat() * 6.0f, 0.0f, 0.0f, -1, -1, 0.5);
    }

    protected void func_151541_a(long l, int i, int j, Block[] b, double d, double d1, double d2, float f, float f1, float f2, int k, int l4, double d3) {
        double d4 = i * 16 + 8;
        double d5 = j * 16 + 8;
        float f3 = 0.0f;
        float f4 = 0.0f;
        Random random = new Random(l);
        if (l4 <= 0) {
            int j1 = this.range * 16 - 16;
            l4 = j1 - random.nextInt(j1 / 4);
        }
        boolean flag2 = false;
        if (k == -1) {
            k = l4 / 2;
            flag2 = true;
        }
        int k1 = random.nextInt(l4 / 2) + l4 / 4;
        boolean flag = random.nextInt(6) == 0;
        boolean bl = flag;
        while (k < l4) {
            double d6 = 1.5 + (double)(MathHelper.sin((float)((float)k * (float)Math.PI / (float)l4)) * f * 1.0f);
            double d7 = d6 * d3;
            float f5 = MathHelper.cos((float)f2);
            float f6 = MathHelper.sin((float)f2);
            d += (double)(MathHelper.cos((float)f1) * f5);
            d1 += (double)f6;
            d2 += (double)(MathHelper.sin((float)f1) * f5);
            f2 = flag ? (f2 = f2 * 0.92f) : (f2 = f2 * 0.7f);
            f2 += f4 * 0.1f;
            f1 += f3 * 0.1f;
            f4 *= 0.9f;
            f3 *= 0.75f;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0f;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0f;
            if (!flag2 && k == k1 && f > 1.0f && l4 > 0) {
                this.func_151541_a(random.nextLong(), i, j, b, d, d1, d2, random.nextFloat() * 0.5f + 0.5f, f1 - 1.5707964f, f2 / 3.0f, k, l4, 1.0);
                this.func_151541_a(random.nextLong(), i, j, b, d, d1, d2, random.nextFloat() * 0.5f + 0.5f, f1 + 1.5707964f, f2 / 3.0f, k, l4, 1.0);
                return;
            }
            if (flag2 || random.nextInt(4) != 0) {
                double d8 = d - d4;
                double d9 = d2 - d5;
                double d10 = l4 - k;
                double d11 = f + 2.0f + 16.0f;
                if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11) {
                    return;
                }
                if (d >= d4 - 16.0 - d6 * 2.0 && d2 >= d5 - 16.0 - d6 * 2.0 && d <= d4 + 16.0 + d6 * 2.0 && d2 <= d5 + 16.0 + d6 * 2.0) {
                    int j3;
                    int k2;
                    int i4 = MathHelper.floor_double((double)(d - d6)) - i * 16 - 1;
                    int l1 = MathHelper.floor_double((double)(d + d6)) - i * 16 + 1;
                    int j4 = MathHelper.floor_double((double)(d1 - d7)) - 1;
                    int i2 = MathHelper.floor_double((double)(d1 + d7)) + 1;
                    int k4 = MathHelper.floor_double((double)(d2 - d6)) - j * 16 - 1;
                    int j2 = MathHelper.floor_double((double)(d2 + d6)) - j * 16 + 1;
                    if (i4 < 0) {
                        i4 = 0;
                    }
                    if (l1 > 16) {
                        l1 = 16;
                    }
                    if (j4 < 1) {
                        j4 = 1;
                    }
                    if (i2 > 248) {
                        i2 = 248;
                    }
                    if (k4 < 0) {
                        k4 = 0;
                    }
                    if (j2 > 16) {
                        j2 = 16;
                    }
                    boolean flag3 = false;
                    for (k2 = i4; !flag3 && k2 < l1; ++k2) {
                        for (int l2 = k4; !flag3 && l2 < j2; ++l2) {
                            for (int i3 = i2 + 1; !flag3 && i3 >= j4 - 1; --i3) {
                                j3 = (k2 * 16 + l2) * 256 + i3;
                                if (i3 < 0 || i3 >= 256) continue;
                                Block block = b[j3];
                                if (this.isOceanBlock(b, j3, k2, i3, l2, i, j)) {
                                    flag3 = true;
                                }
                                if (i3 == j4 - 1 || k2 == i4 || k2 == l1 - 1 || l2 == k4 || l2 == j2 - 1) continue;
                                i3 = j4;
                            }
                        }
                    }
                    if (!flag3) {
                        for (k2 = i4; k2 < l1; ++k2) {
                            double d13 = ((double)(k2 + i * 16) + 0.5 - d) / d6;
                            for (j3 = k4; j3 < j2; ++j3) {
                                double d14 = ((double)(j3 + j * 16) + 0.5 - d2) / d6;
                                int k3 = (k2 * 16 + j3) * 256 + i2;
                                boolean flag1 = false;
                                if (d13 * d13 + d14 * d14 >= 1.0) continue;
                                for (int l3 = i2 - 1; l3 >= j4; --l3) {
                                    double d12 = ((double)l3 + 0.5 - d1) / d7;
                                    if (d12 > -0.7 && d13 * d13 + d12 * d12 + d14 * d14 < 1.0) {
                                        Block block1 = b[k3];
                                        if (this.isTopBlock(b, k3, k2, l3, j3, i, j)) {
                                            flag1 = true;
                                        }
                                        this.digBlock(b, k3, k2, l3, j3, i, j, flag1);
                                    }
                                    --k3;
                                }
                            }
                        }
                        if (flag2) break;
                    }
                }
            }
            ++k;
        }
    }

    protected void func_151538_a(World w, int x, int y, int z, int i, Block[] ba) {
        int i1 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);
        if (this.rand.nextInt(7) != 0) {
            i1 = 0;
        }
        for (int j1 = 0; j1 < i1; ++j1) {
            double d0 = x * 16 + this.rand.nextInt(16);
            double d1 = this.rand.nextInt(this.rand.nextInt(120) + 8);
            double d2 = y * 16 + this.rand.nextInt(16);
            int k1 = 1;
            if (this.rand.nextInt(4) == 0) {
                this.func_151542_a(this.rand.nextLong(), z, i, ba, d0, d1, d2);
                k1 += this.rand.nextInt(4);
            }
            for (int l1 = 0; l1 < k1; ++l1) {
                float f = this.rand.nextFloat() * (float)Math.PI * 2.0f;
                float f1 = (this.rand.nextFloat() - 0.5f) * 2.0f / 8.0f;
                float f2 = this.rand.nextFloat() * 2.0f + this.rand.nextFloat();
                if (this.rand.nextInt(10) == 0) {
                    f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0f + 1.0f;
                }
                this.func_151541_a(this.rand.nextLong(), z, i, ba, d0, d1, d2, f2, f, f1, 0, 0, 1.0);
            }
        }
    }

    protected boolean isOceanBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ) {
        return data[index] == Blocks.flowing_water || data[index] == Blocks.water;
    }

    private boolean isExceptionBiome(BiomeGenBase biome) {
        if (biome == BiomeGenBase.mushroomIsland) {
            return true;
        }
        if (biome == BiomeGenBase.beach) {
            return true;
        }
        return biome == BiomeGenBase.desert;
    }

    private boolean isTopBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16);
        return this.isExceptionBiome(biome) ? data[index] == Blocks.grass : data[index] == biome.topBlock;
    }

    protected void digBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16);
        Block top = this.isExceptionBiome(biome) ? IceikaBlocks.frozenGrass : biome.topBlock;
        Block filler = this.isExceptionBiome(biome) ? IceikaBlocks.frozenDirt : biome.fillerBlock;
        Block block = data[index];
        if (block == IceikaBlocks.frozenStone || block == filler || block == top) {
            if (y < 10) {
                data[index] = Blocks.lava;
            } else {
                data[index] = null;
                if (foundTop && data[index - 1] == filler) {
                    data[index - 1] = top;
                }
            }
        }
    }
}

