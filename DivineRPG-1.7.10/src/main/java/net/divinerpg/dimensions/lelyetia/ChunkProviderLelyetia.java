/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IProgressUpdate
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldType
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.NoiseGenerator
 *  net.minecraft.world.gen.NoiseGeneratorOctaves
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraftforge.event.terraingen.TerrainGen
 */
package net.divinerpg.dimensions.lelyetia;

import java.util.List;
import java.util.Random;
import net.divinerpg.dimensions.lelyetia.LelyetiaChunkBuilder;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderLelyetia
implements IChunkProvider {
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves noiseGen5;
    private NoiseGeneratorOctaves noiseGen6;
    private NoiseGeneratorPerlin noiseGen4;
    private World worldObj;
    private WorldType type;
    private final double[] da;
    private final float[] parabolicField;
    private double[] stoneNoise = new double[256];
    private BiomeGenBase[] biomesForGeneration;
    private double[] gen1;
    private double[] gen2;
    private double[] gen3;
    private double[] gen4;
    private int[][] ia = new int[32][32];

    public ChunkProviderLelyetia(World par1World, long par2) {
        this.worldObj = par1World;
        this.type = par1World.getWorldInfo().getTerrainType();
        this.rand = new Random(par2);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorPerlin(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.da = new double[825];
        this.parabolicField = new float[25];
        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                float f;
                this.parabolicField[j + 2 + (k + 2) * 5] = f = 10.0f / MathHelper.sqrt_float((float)((float)(j * j + k * k) + 0.2f));
            }
        }
        NoiseGenerator[] noiseGens = new NoiseGenerator[]{this.noiseGen1, this.noiseGen2, this.noiseGen3, this.noiseGen4, this.noiseGen5, this.noiseGen6};
        noiseGens = TerrainGen.getModdedNoiseGenerators((World)par1World, (Random)this.rand, (NoiseGenerator[])noiseGens);
        this.noiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
        this.noiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
        this.noiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
        this.noiseGen4 = (NoiseGeneratorPerlin)noiseGens[3];
        this.noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
        this.noiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
    }

    public void generate(int i, int j, Block[] b) {
        LelyetiaChunkBuilder builder = new LelyetiaChunkBuilder();
        LelyetiaChunkBuilder.toTerrainArray(builder.buildChunk(), b);
    }

    public void replaceBlocksForBiome(int i, int j, Block[] ba, byte[] by, BiomeGenBase[] b) {
    }

    public final void genBiomeTerrain(World p_150560_1_, Random p_150560_2_, Block[] p_150560_3_, byte[] p_150560_4_, int p_150560_5_, int p_150560_6_, double p_150560_7_, BiomeGenBase b) {
        boolean flag = true;
        Block block = b.topBlock;
        byte b2 = (byte)(b.field_150604_aj & 0xFF);
        Block block2 = b.fillerBlock;
        int k = -1;
        int l = (int)(p_150560_7_ / 3.0 + 3.0 + p_150560_2_.nextDouble() * 0.25);
        int i1 = p_150560_5_ & 0xF;
        int j1 = p_150560_6_ & 0xF;
        int k2 = p_150560_3_.length / 256;
        for (int l2 = 255; l2 >= 0; --l2) {
            int i2 = (j1 * 16 + i1) * k2 + l2;
            if (l2 <= 0 + p_150560_2_.nextInt(5)) {
                p_150560_3_[i2] = Blocks.bedrock;
                continue;
            }
            Block block3 = p_150560_3_[i2];
            if (block3 != null && block3.getMaterial() != Material.air) {
                if (block3 != JourneyBlocks.StoneToxic) continue;
                if (k == -1) {
                    if (l <= 0) {
                        block = null;
                        b2 = 0;
                        block2 = JourneyBlocks.StoneToxic;
                    } else if (l2 >= 59 && l2 <= 64) {
                        block = b.topBlock;
                        b2 = (byte)(b.field_150604_aj & 0xFF);
                        block2 = b.fillerBlock;
                    }
                    if (l2 < 63 && (block == null || block.getMaterial() == Material.air)) {
                        if (b.getFloatTemperature(p_150560_5_, l2, p_150560_6_) < 0.15f) {
                            block = Blocks.water;
                            b2 = 0;
                        } else {
                            block = JourneyBlocks.StoneToxic;
                            b2 = 0;
                        }
                    }
                    k = l;
                    if (l2 >= 62) {
                        p_150560_3_[i2] = block;
                        p_150560_4_[i2] = b2;
                        continue;
                    }
                    if (l2 < 56 - l) {
                        block = null;
                        block2 = JourneyBlocks.StoneToxic;
                        p_150560_3_[i2] = JourneyBlocks.StoneToxic;
                        continue;
                    }
                    p_150560_3_[i2] = block2;
                    continue;
                }
                if (k <= 0) continue;
                p_150560_3_[i2] = block2;
                if (--k != 0 || block2 != JourneyBlocks.StoneToxic) continue;
                k = p_150560_2_.nextInt(4) + Math.max(0, l2 - 63);
                block2 = JourneyBlocks.StoneToxic;
                continue;
            }
            k = -1;
        }
    }

    public Chunk loadChunk(int par1, int par2) {
        return this.provideChunk(par1, par2);
    }

    public Chunk provideChunk(int par1, int par2) {
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        Block[] ablock = new Block[65536];
        byte[] abyte = new byte[65536];
        this.generate(par1, par2, ablock);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.replaceBlocksForBiome(par1, par2, ablock, abyte, this.biomesForGeneration);
        Chunk chunk = new Chunk(this.worldObj, ablock, abyte, par1, par2);
        byte[] abyte2 = chunk.getBiomeArray();
        for (int k = 0; k < abyte2.length; ++k) {
            abyte2[k] = (byte)this.biomesForGeneration[k].biomeID;
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    private void generate(int x, int y, int z) {
        double d0 = 684.412;
        double d2 = 684.412;
        double d3 = 512.0;
        double d4 = 512.0;
        this.gen4 = this.noiseGen6.generateNoiseOctaves(this.gen4, x, z, 5, 5, 200.0, 200.0, 0.5);
        this.gen1 = this.noiseGen3.generateNoiseOctaves(this.gen1, x, y, z, 5, 33, 5, 8.555150000000001, 4.277575000000001, 8.555150000000001);
        this.gen2 = this.noiseGen1.generateNoiseOctaves(this.gen2, x, y, z, 5, 33, 5, 684.412, 684.412, 684.412);
        this.gen3 = this.noiseGen2.generateNoiseOctaves(this.gen3, x, y, z, 5, 33, 5, 684.412, 684.412, 684.412);
        boolean flag1 = false;
        boolean flag2 = false;
        int l = 0;
        int i1 = 0;
        double d5 = 8.5;
        for (int j1 = 0; j1 < 5; ++j1) {
            for (int k1 = 0; k1 < 5; ++k1) {
                float f = 0.0f;
                float f2 = 0.0f;
                float f3 = 0.0f;
                int b0 = 2;
                BiomeGenBase biomegenbase = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];
                for (int l2 = -2; l2 <= 2; ++l2) {
                    for (int i2 = -2; i2 <= 2; ++i2) {
                        BiomeGenBase biomegenbase2 = this.biomesForGeneration[j1 + l2 + 2 + (k1 + i2 + 2) * 10];
                        float f4 = biomegenbase2.rootHeight;
                        float f5 = biomegenbase2.heightVariation;
                        if (this.type == WorldType.AMPLIFIED && f4 > 0.0f) {
                            f4 = 1.0f + f4 * 2.0f;
                            f5 = 1.0f + f5 * 4.0f;
                        }
                        float f6 = this.parabolicField[l2 + 2 + (i2 + 2) * 5] / (f4 + 2.0f);
                        if (biomegenbase2.rootHeight > biomegenbase.rootHeight) {
                            f6 /= 2.0f;
                        }
                        f += f5 * f6;
                        f2 += f4 * f6;
                        f3 += f6;
                    }
                }
                f /= f3;
                f2 /= f3;
                f = f * 0.9f + 0.1f;
                f2 = (f2 * 4.0f - 1.0f) / 8.0f;
                double d6 = this.gen4[i1] / 8000.0;
                if (d6 < 0.0) {
                    d6 = -d6 * 0.3;
                }
                if ((d6 = d6 * 3.0 - 2.0) < 0.0) {
                    if ((d6 /= 2.0) < -1.0) {
                        d6 = -1.0;
                    }
                    d6 /= 1.4;
                    d6 /= 2.0;
                } else {
                    if (d6 > 1.0) {
                        d6 = 1.0;
                    }
                    d6 /= 8.0;
                }
                ++i1;
                double d7 = f2;
                double d8 = f;
                d7 += d6 * 0.2;
                d7 = d7 * 8.5 / 8.0;
                double d9 = 8.5 + d7 * 4.0;
                for (int j2 = 0; j2 < 33; ++j2) {
                    double d10 = ((double)j2 - d9) * 12.0 * 128.0 / 256.0 / d8;
                    if (d10 < 0.0) {
                        d10 *= 4.0;
                    }
                    double d11 = this.gen2[l] / 512.0;
                    double d12 = this.gen3[l] / 512.0;
                    double d13 = (this.gen1[l] / 10.0 + 1.0) / 2.0;
                    double d14 = MathHelper.denormalizeClamp((double)d11, (double)d12, (double)d13) - d10;
                    if (j2 > 29) {
                        double d15 = (float)(j2 - 29) / 3.0f;
                        d14 = d14 * (1.0 - d15) + -10.0 * d15;
                    }
                    this.da[l] = d14;
                    ++l;
                }
            }
        }
    }

    public boolean chunkExists(int par1, int par2) {
        return true;
    }

    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        long p1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(this.worldObj.getSeed());
        this.rand.setSeed((long)par2 * p1 + (long)par3 * j1 ^ this.worldObj.getSeed());
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
        return true;
    }

    public void saveExtraData() {
    }

    public boolean unloadQueuedChunks() {
        return false;
    }

    public boolean canSave() {
        return true;
    }

    public String makeString() {
        return "Lelyetia";
    }

    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(par2, par4);
        return biomegenbase.getSpawnableList(par1EnumCreatureType);
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_) {
        return null;
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    public void recreateStructures(int par1, int par2) {
    }
}

