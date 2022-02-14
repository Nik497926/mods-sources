/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IProgressUpdate
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.NoiseGeneratorOctaves
 *  net.minecraft.world.gen.feature.WorldGenFlowers
 *  net.minecraft.world.gen.feature.WorldGenMinable
 */
package net.divinerpg.dimensions.euca;

import java.util.List;
import java.util.Random;
import net.divinerpg.dimensions.arcana.ChunkPrimer;
import net.divinerpg.dimensions.euca.gen.EucaSmallSphereDungeon;
import net.divinerpg.dimensions.euca.gen.WorldGenBotSpawner;
import net.divinerpg.dimensions.euca.gen.WorldGenEucaWater;
import net.divinerpg.dimensions.euca.gen.WorldGenSmelteryNew;
import net.divinerpg.dimensions.euca.gen.trees.WorldGenEucaTree;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class ChunkProviderEuca
implements IChunkProvider {
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves noiseGen5;
    private NoiseGeneratorOctaves noiseGen6;
    private World worldObj;
    private double[] noiseArray;
    private BiomeGenBase[] biomesForGeneration;
    private double[] noise3;
    private double[] noise1;
    private double[] noise2;
    private double[] noise5;
    private double[] noise6;

    public ChunkProviderEuca(World var1, long var2) {
        this.worldObj = var1;
        this.rand = new Random(var2);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        new NoiseGeneratorOctaves(this.rand, 4);
        new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        new WorldGenEucaWater((Block)Blocks.flowing_water, true);
        new WorldGenEucaWater((Block)Blocks.flowing_water, false);
    }

    public boolean chunkExists(int x, int z) {
        return true;
    }

    public Chunk provideChunk(int x, int z) {
        ChunkPrimer primer = new ChunkPrimer();
        this.rand.setSeed((long)x * 391279512714L + (long)z * 132894987741L);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        this.generateTerrain(x, z, primer);
        this.replaceBlocksForBiome(primer);
        Chunk chunk = new Chunk(this.worldObj, primer.getChunkData(), primer.getChunkMetadata(), x, z);
        byte[] biomes = chunk.getBiomeArray();
        for (int k = 0; k < biomes.length; ++k) {
            biomes[k] = (byte)this.biomesForGeneration[k].biomeID;
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    public Chunk loadChunk(int x, int z) {
        return this.provideChunk(x, z);
    }

    public void generateTerrain(int chunkX, int chunkZ, ChunkPrimer primer) {
        int b0 = 2;
        int k = b0 + 1;
        int b1 = 33;
        int l = b0 + 1;
        this.noiseArray = this.initializeNoiseField(this.noiseArray, chunkX * b0, 0, chunkZ * b0, k, b1, l);
        for (int i1 = 0; i1 < b0; ++i1) {
            for (int j1 = 0; j1 < b0; ++j1) {
                for (int k1 = 0; k1 < 32; ++k1) {
                    double d0 = 0.25;
                    double d1 = this.noiseArray[(i1 * l + j1) * b1 + k1];
                    double d2 = this.noiseArray[(i1 * l + j1 + 1) * b1 + k1];
                    double d3 = this.noiseArray[((i1 + 1) * l + j1) * b1 + k1];
                    double d4 = this.noiseArray[((i1 + 1) * l + j1 + 1) * b1 + k1];
                    double d5 = (this.noiseArray[(i1 * l + j1) * b1 + k1 + 1] - d1) * d0;
                    double d6 = (this.noiseArray[(i1 * l + j1 + 1) * b1 + k1 + 1] - d2) * d0;
                    double d7 = (this.noiseArray[((i1 + 1) * l + j1) * b1 + k1 + 1] - d3) * d0;
                    double d8 = (this.noiseArray[((i1 + 1) * l + j1 + 1) * b1 + k1 + 1] - d4) * d0;
                    for (int l1 = 0; l1 < 4; ++l1) {
                        double d9 = 0.125;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for (int i2 = 0; i2 < 8; ++i2) {
                            double d14 = 0.125;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;
                            for (int j2 = 0; j2 < 8; ++j2) {
                                Block block = null;
                                if (d15 > 0.0) {
                                    block = JourneyBlocks.eucaStone;
                                }
                                int x = i2 + i1 * 8;
                                int y = l1 + k1 * 4;
                                int z = j2 + j1 * 8;
                                primer.setBlock(x, y, z, block);
                                d15 += d16;
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

    public final void replaceBlocksForBiome(ChunkPrimer primer) {
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                int b0 = 1;
                int k = -1;
                Block grass = JourneyBlocks.eucaGrass;
                Block stone = JourneyBlocks.eucaStone;
                for (int y = 127; y >= 0; --y) {
                    Block block = primer.getBlock(x, y, z);
                    if (block == null || block.getMaterial() == Material.air) {
                        k = -1;
                        continue;
                    }
                    if (block != JourneyBlocks.eucaStone) continue;
                    if (k == -1) {
                        k = b0;
                        if (y >= 0) {
                            primer.setBlock(x, y, z, grass);
                            continue;
                        }
                        primer.setBlock(x, y, z, stone);
                        if (y - 1 < 0 || primer.getBlock(x, y - 1, z) != JourneyBlocks.eucaStone) continue;
                        primer.setBlock(x, y - 1, z, stone);
                        continue;
                    }
                    if (k <= 0) continue;
                    --k;
                    primer.setBlock(x, y, z, stone);
                    if (primer.getBlock(x, y - 1, z) != JourneyBlocks.eucaStone) continue;
                    primer.setBlock(x, y - 1, z, stone);
                }
            }
        }
    }

    private double[] initializeNoiseField(double[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        if (var1 == null) {
            var1 = new double[var5 * var6 * var7];
        }
        double var8 = 684.412;
        double var10 = 684.412;
        this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, var2, var4, var5, var7, 1.121, 1.121, 0.5);
        this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, var2, var4, var5, var7, 1.0, 1.0, 0.5);
        this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, var2, var3, var4, var5, var6, var7, var8 / 80.0, var8 / 160.0, (var8 *= 2.0) / 80.0);
        this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, var2, var3, var4, var5, var6, var7, var8, var10, var8);
        this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, var2, var3, var4, var5, var6, var7, var8, var10, var8);
        int var12 = 0;
        int var13 = 0;
        for (int var15 = 0; var15 < var5; ++var15) {
            for (int var17 = 0; var17 < var7; ++var17) {
                double d;
                double var19 = (this.noise5[var13] + 64.0) / 64.0;
                double var21 = this.noise6[var13] / 6000.0;
                if (var21 < 0.0) {
                    var21 = -var21 * 0.3;
                }
                var21 = var21 * 3.0 - 2.0;
                if (d > 1.0) {
                    var21 = 1.0;
                }
                var21 /= 8.0;
                var21 = 0.0;
                if (var19 < 0.0) {
                    var19 = 0.0;
                }
                var19 += 0.5;
                var21 = var21 * (double)var6 / 8.0;
                ++var13;
                double var23 = (double)var6 / 2.0;
                for (int var25 = 0; var25 < var6; ++var25) {
                    double var37;
                    double var26 = 0.0;
                    double var28 = ((double)var25 - var23) * 8.0 / var19;
                    if (var28 < 0.0) {
                        var28 *= -1.0;
                    }
                    double var30 = this.noise1[var12] / 128.0 / 1.5;
                    double var32 = this.noise2[var12] / 128.0;
                    double var34 = this.noise3[var12] / 10.0 + 1.0;
                    var26 = var34 < 0.0 ? var30 : (var34 > 1.0 ? var32 : var30 + (var32 - var30) * var34);
                    var26 -= 8.0;
                    int var36 = 32;
                    if (var25 > var6 - var36) {
                        var37 = (float)(var25 - (var6 - var36)) / ((float)var36 - 1.0f);
                        var26 = var26 * (1.0 - var37) + -30.0 * var37;
                    }
                    var36 = 8;
                    if (var25 < 8) {
                        var37 = (float)(var36 - var25) / ((float)var36 - 1.0f);
                        var26 = var26 * (1.0 - var37) + -30.0 * var37;
                    }
                    var1[var12] = var26;
                    ++var12;
                }
            }
        }
        return var1;
    }

    public void populate(IChunkProvider ichunkprovider, int i, int j) {
        int yCoord;
        int iCoord;
        int z;
        int x;
        int times;
        int x1 = i * 16;
        int z1 = j * 16;
        for (times = 0; times < 1; ++times) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            iCoord = this.rand.nextInt(128) + 1;
            if (!this.isBlockTop(x, iCoord - 1, z, JourneyBlocks.eucaGrass)) continue;
            new WorldGenSmelteryNew().generate(this.worldObj, this.rand, x, iCoord, z);
        }
        for (times = 0; times < 1; ++times) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            iCoord = this.rand.nextInt(128) + 1;
            if (!this.isBlockTop(x, iCoord - 1, z, JourneyBlocks.eucaGrass)) continue;
            new WorldGenBotSpawner().generate(this.worldObj, this.rand, x, iCoord, z);
        }
        if (this.rand.nextInt(100) <= 3) {
            for (times = 0; times < 1; ++times) {
                x = x1 + this.rand.nextInt(16) + 8;
                z = z1 + this.rand.nextInt(16) + 8;
                iCoord = this.rand.nextInt(128) + 1;
                if (!this.isBlockTop(x, iCoord - 1, z, Blocks.air) || iCoord < 90) continue;
                new EucaSmallSphereDungeon().generate(this.worldObj, this.rand, x, iCoord, z);
            }
        }
        for (times = 0; times < 5; ++times) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            yCoord = this.rand.nextInt(128) + 1;
            if (!this.isBlockTop(x, yCoord - 1, z, JourneyBlocks.eucaGrass)) continue;
            new WorldGenEucaTree().generate(this.worldObj, this.rand, x, yCoord, z);
        }
        for (times = 0; times < 5; ++times) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            yCoord = this.rand.nextInt(255) + 1;
            new WorldGenFlowers(JourneyBlocks.eucaTallFlowers).generate(this.worldObj, this.rand, x, yCoord, z);
        }
        for (times = 0; times < 1; ++times) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            yCoord = this.rand.nextInt(255) + 1;
            new WorldGenFlowers(JourneyBlocks.eucaTallGrass).generate(this.worldObj, this.rand, x, yCoord, z);
        }
        for (times = 0; times < 1; ++times) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            yCoord = this.rand.nextInt(255) + 1;
            new WorldGenFlowers(JourneyBlocks.eucaBlueFlower).generate(this.worldObj, this.rand, x, yCoord, z);
        }
        for (times = 0; times < 20; ++times) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            yCoord = this.rand.nextInt(255) + 1;
            new WorldGenMinable(JourneyBlocks.celestiumOre, 6, JourneyBlocks.eucaStone).generate(this.worldObj, this.rand, x, yCoord, z);
        }
        for (times = 0; times < 10; ++times) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            yCoord = this.rand.nextInt(255) + 1;
            new WorldGenMinable(JourneyBlocks.koriteOre, 6, JourneyBlocks.eucaStone).generate(this.worldObj, this.rand, x, yCoord, z);
        }
        for (times = 0; times < 10; ++times) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            yCoord = this.rand.nextInt(255) + 1;
            new WorldGenMinable(JourneyBlocks.mekyumOre, 5, JourneyBlocks.eucaStone).generate(this.worldObj, this.rand, x, yCoord, z);
        }
        for (times = 0; times < 10; ++times) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            yCoord = this.rand.nextInt(255) + 1;
            new WorldGenMinable(JourneyBlocks.storonOre, 6, JourneyBlocks.eucaStone).generate(this.worldObj, this.rand, x, yCoord, z);
        }
    }

    public boolean isBlockTop(int x, int y, int z, Block grass) {
        return this.worldObj.getBlock(x, y, z) == grass && this.worldObj.getBlock(x, y + 1, z) == Blocks.air && this.worldObj.getBlock(x, y + 2, z) == Blocks.air && this.worldObj.getBlock(x, y + 3, z) == Blocks.air && this.worldObj.getBlock(x, y + 4, z) == Blocks.air && this.worldObj.getBlock(x, y + 5, z) == Blocks.air;
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2) {
        return true;
    }

    public boolean unloadQueuedChunks() {
        return false;
    }

    public boolean canSave() {
        return true;
    }

    public String makeString() {
        return "Euca";
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    public void recreateStructures(int i, int i1) {
    }

    public void saveExtraData() {
    }

    public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x, z);
        return biome.getSpawnableList(creatureType);
    }

    public ChunkPosition func_147416_a(World world, String s, int i, int i1, int i2) {
        return null;
    }
}

