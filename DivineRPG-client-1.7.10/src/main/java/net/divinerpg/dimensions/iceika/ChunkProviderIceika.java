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
 *  net.minecraft.world.gen.MapGenBase
 *  net.minecraft.world.gen.NoiseGenerator
 *  net.minecraft.world.gen.NoiseGeneratorOctaves
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraft.world.gen.feature.WorldGenLakes
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.event.terraingen.TerrainGen
 */
package net.divinerpg.dimensions.iceika;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.divinerpg.dimensions.iceika.MapGenIceikaCaves;
import net.divinerpg.dimensions.iceika.WorldGenArcherDungeon;
import net.divinerpg.dimensions.iceika.WorldGenGiantTree;
import net.divinerpg.dimensions.iceika.WorldGenRollumDungeon;
import net.divinerpg.dimensions.iceika.village.WorldGenHouse1;
import net.divinerpg.dimensions.iceika.village.WorldGenHouse2;
import net.divinerpg.dimensions.iceika.village.WorldGenHouse3;
import net.divinerpg.dimensions.iceika.village.WorldGenHouse4;
import net.divinerpg.dimensions.iceika.village.WorldGenHouse5;
import net.divinerpg.dimensions.iceika.village.WorldGenLibrary;
import net.divinerpg.dimensions.iceika.village.WorldGenLights;
import net.divinerpg.dimensions.iceika.village.WorldGenWorkShop;
import net.divinerpg.items.base.WorldGenSantaHouse;
import net.divinerpg.utils.blocks.IceikaBlocks;
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
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderIceika
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
    private MapGenBase caveGenerator = new MapGenIceikaCaves();
    private BiomeGenBase[] biomesForGeneration;
    private double[] gen1;
    private double[] gen2;
    private double[] gen3;
    private double[] gen4;
    private int[][] ia = new int[32][32];
    private ArrayList<WorldGenerator> lights;
    private ArrayList<WorldGenerator> village;
    private ArrayList<WorldGenerator> dungeons;
    private ArrayList<WorldGenerator> rareHouses;

    public ChunkProviderIceika(World par1World, long par2) {
        this.worldObj = par1World;
        this.type = par1World.getWorldInfo().getTerrainType();
        this.rand = new Random(par2);
        this.lights = new ArrayList(2);
        this.lights.add(new WorldGenLights.Light1());
        this.lights.add(new WorldGenLights.Light2());
        this.lights.add(new WorldGenLights.Light3());
        this.village = new ArrayList(5);
        this.village.add(new WorldGenLibrary());
        this.village.add(new WorldGenWorkShop());
        this.village.add(new WorldGenHouse1());
        this.village.add(new WorldGenHouse2());
        this.village.add(new WorldGenHouse3());
        this.village.add(new WorldGenHouse4());
        this.village.add(new WorldGenHouse5());
        this.rareHouses = new ArrayList(0);
        this.rareHouses.add(new WorldGenSantaHouse());
        this.dungeons = new ArrayList(1);
        this.dungeons.add(new WorldGenRollumDungeon());
        this.dungeons.add(new WorldGenArcherDungeon());
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
        byte b0 = 63;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, i * 4 - 2, j * 4 - 2, 10, 10);
        this.generate(i * 4, 0, j * 4);

        for(int k = 0; k < 4; ++k) {
            int l = k * 5;
            int i1 = (k + 1) * 5;

            for(int j1 = 0; j1 < 4; ++j1) {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;

                for(int k2 = 0; k2 < 32; ++k2) {
                    double d0 = 0.125D;
                    double d1 = this.da[k1 + k2];
                    double d2 = this.da[l1 + k2];
                    double d3 = this.da[i2 + k2];
                    double d4 = this.da[j2 + k2];
                    double d5 = (this.da[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.da[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.da[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.da[j2 + k2 + 1] - d4) * d0;

                    for(int l2 = 0; l2 < 8; ++l2) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for(int i3 = 0; i3 < 4; ++i3) {
                            int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
                            short short1 = 256;
                            j3 -= short1;
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;

                            for(int k3 = 0; k3 < 4; ++k3) {
                                if((d15 += d16) > 0.0D)
                                    b[j3 += short1] = IceikaBlocks.frozenStone;

                                else if(k2 * 8 + l2 < 62)
                                    b[j3 += short1] = IceikaBlocks.frozenDirt;

                                else if(k2 * 8 + l2 < b0)
                                    b[j3 += short1] = IceikaBlocks.frozenGrass;

                                else
                                    b[j3 += short1] = null;
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

    public void replaceBlocksForBiome(int i, int j, Block[] ba, byte[] by, BiomeGenBase[] b) {
        double d0 = 0.03125;
        this.stoneNoise = this.noiseGen4.func_151599_a(this.stoneNoise, (double)(i * 16), (double)(j * 16), 16, 16, d0 * 2.0, d0 * 2.0, 1.0);
        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                BiomeGenBase biomegenbase = b[l + k * 16];
                this.genBiomeTerrain(this.worldObj, this.rand, ba, by, i * 16 + k, j * 16 + l, this.stoneNoise[l + k * 16], biomegenbase);
            }
        }
    }

    public final void genBiomeTerrain(World w, Random rand, Block[] blocks, byte[] bytes, int i, int j, double d, BiomeGenBase b) {
        boolean flag = true;
        Block block = b.topBlock;
        byte b0 = (byte)(b.field_150604_aj & 0xFF);
        Block block1 = b.fillerBlock;
        int k = -1;
        int l = (int)(d / 3.0 + 3.0 + rand.nextDouble() * 0.25);
        int i1 = i & 0xF;
        int j1 = j & 0xF;
        int k1 = blocks.length / 256;
        for (int l1 = 255; l1 >= 0; --l1) {
            int i2 = (j1 * 16 + i1) * k1 + l1;
            if (l1 <= 0 + rand.nextInt(5)) {
                blocks[i2] = Blocks.bedrock;
                continue;
            }
            Block block2 = blocks[i2];
            if (block2 != null && block2.getMaterial() != Material.air) {
                if (block2 != IceikaBlocks.frozenStone) continue;
                if (k == -1) {
                    if (l <= 0) {
                        block = null;
                        b0 = 0;
                        block1 = IceikaBlocks.frozenStone;
                    } else if (l1 >= 59 && l1 <= 64) {
                        block = b.topBlock;
                        b0 = (byte)(b.field_150604_aj & 0xFF);
                        block1 = b.fillerBlock;
                    }
                    if (l1 < 63 && (block == null || block.getMaterial() == Material.air)) {
                        if (b.getFloatTemperature(i, l1, j) < 0.15f) {
                            block = Blocks.ice;
                            b0 = 0;
                        } else {
                            block = IceikaBlocks.frozenStone;
                            b0 = 0;
                        }
                    }
                    k = l;
                    if (l1 >= 62) {
                        blocks[i2] = block;
                        bytes[i2] = b0;
                        continue;
                    }
                    if (l1 < 56 - l) {
                        block = null;
                        block1 = IceikaBlocks.frozenStone;
                        blocks[i2] = IceikaBlocks.frozenStone;
                        continue;
                    }
                    blocks[i2] = block1;
                    continue;
                }
                if (k <= 0) continue;
                blocks[i2] = block1;
                if (--k != 0 || block1 != IceikaBlocks.frozenStone) continue;
                k = rand.nextInt(4) + Math.max(0, l1 - 63);
                block1 = IceikaBlocks.frozenStone;
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
        this.caveGenerator.func_151539_a((IChunkProvider)this, this.worldObj, par1, par2, ablock);
        Chunk chunk = new Chunk(this.worldObj, ablock, abyte, par1, par2);
        byte[] abyte1 = chunk.getBiomeArray();
        for (int k = 0; k < abyte1.length; ++k) {
            abyte1[k] = (byte)this.biomesForGeneration[k].biomeID;
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    private void generate(int x, int y, int z) {
        double d0 = 684.412;
        double d1 = 684.412;
        double d2 = 512.0;
        double d3 = 512.0;
        this.gen4 = this.noiseGen6.generateNoiseOctaves(this.gen4, x, z, 5, 5, 200.0, 200.0, 0.5);
        this.gen1 = this.noiseGen3.generateNoiseOctaves(this.gen1, x, y, z, 5, 33, 5, 8.555150000000001, 4.277575000000001, 8.555150000000001);
        this.gen2 = this.noiseGen1.generateNoiseOctaves(this.gen2, x, y, z, 5, 33, 5, 684.412, 684.412, 684.412);
        this.gen3 = this.noiseGen2.generateNoiseOctaves(this.gen3, x, y, z, 5, 33, 5, 684.412, 684.412, 684.412);
        boolean flag1 = false;
        boolean flag = false;
        int l = 0;
        int i1 = 0;
        double d4 = 8.5;
        for (int j1 = 0; j1 < 5; ++j1) {
            for (int k1 = 0; k1 < 5; ++k1) {
                float f = 0.0f;
                float f1 = 0.0f;
                float f2 = 0.0f;
                int b0 = 2;
                BiomeGenBase biomegenbase = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];
                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        BiomeGenBase biomegenbase1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
                        float f3 = biomegenbase1.rootHeight;
                        float f4 = biomegenbase1.heightVariation;
                        if (this.type == WorldType.AMPLIFIED && f3 > 0.0f) {
                            f3 = 1.0f + f3 * 2.0f;
                            f4 = 1.0f + f4 * 4.0f;
                        }
                        float f5 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0f);
                        if (biomegenbase1.rootHeight > biomegenbase.rootHeight) {
                            f5 /= 2.0f;
                        }
                        f += f4 * f5;
                        f1 += f3 * f5;
                        f2 += f5;
                    }
                }
                f /= f2;
                f1 /= f2;
                f = f * 0.9f + 0.1f;
                f1 = (f1 * 4.0f - 1.0f) / 8.0f;
                double d12 = this.gen4[i1] / 8000.0;
                if (d12 < 0.0) {
                    d12 = -d12 * 0.3;
                }
                if ((d12 = d12 * 3.0 - 2.0) < 0.0) {
                    if ((d12 /= 2.0) < -1.0) {
                        d12 = -1.0;
                    }
                    d12 /= 1.4;
                    d12 /= 2.0;
                } else {
                    if (d12 > 1.0) {
                        d12 = 1.0;
                    }
                    d12 /= 8.0;
                }
                ++i1;
                double d13 = f1;
                double d14 = f;
                d13 += d12 * 0.2;
                d13 = d13 * 8.5 / 8.0;
                double d5 = 8.5 + d13 * 4.0;
                for (int j2 = 0; j2 < 33; ++j2) {
                    double d6 = ((double)j2 - d5) * 12.0 * 128.0 / 256.0 / d14;
                    if (d6 < 0.0) {
                        d6 *= 4.0;
                    }
                    double d7 = this.gen2[l] / 512.0;
                    double d8 = this.gen3[l] / 512.0;
                    double d9 = (this.gen1[l] / 10.0 + 1.0) / 2.0;
                    double d10 = MathHelper.denormalizeClamp((double)d7, (double)d8, (double)d9) - d6;
                    if (j2 > 29) {
                        double d11 = (float)(j2 - 29) / 3.0f;
                        d10 = d10 * (1.0 - d11) + -10.0 * d11;
                    }
                    this.da[l] = d10;
                    ++l;
                }
            }
        }
    }

    public boolean chunkExists(int par1, int par2) {
        return true;
    }

    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
        boolean foundGround;
        int i;
        int y;
        int z;
        int x;
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        long p1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(this.worldObj.getSeed());
        this.rand.setSeed((long)par2 * p1 + (long)par3 * j1 ^ this.worldObj.getSeed());
        if (this.rand.nextInt(5) == 0) {
            x = var4 + this.rand.nextInt(16);
            z = var5 + this.rand.nextInt(16);
            y = this.rand.nextInt(50);
            this.dungeons.get(this.rand.nextInt(this.dungeons.size())).generate(this.worldObj, this.rand, x, y, z);
        }
        for (i = 0; i < 2; ++i) {
            x = var4 + this.rand.nextInt(16);
            z = var5 + this.rand.nextInt(16);
            foundGround = false;
            y = 32;
            while (i < 128) {
                if (this.worldObj.getBlock(x, i, z) == IceikaBlocks.frozenGrass) {
                    y = i;
                    foundGround = true;
                    break;
                }
                ++i;
            }
            if (!foundGround || this.rand.nextInt(4) != 3) continue;
            this.lights.get(this.rand.nextInt(this.lights.size())).generate(this.worldObj, this.rand, x, y, z);
        }
        for (i = 0; i < 2; ++i) {
            x = var4 + this.rand.nextInt(16);
            z = var5 + this.rand.nextInt(16);
            foundGround = false;
            y = 32;
            while (i < 128) {
                if (this.worldObj.getBlock(x, i, z) == IceikaBlocks.frozenGrass) {
                    y = i;
                    foundGround = true;
                    break;
                }
                ++i;
            }
            if (!foundGround || this.rand.nextInt(4) != 3) continue;
            this.village.get(this.rand.nextInt(this.village.size())).generate(this.worldObj, this.rand, x, y, z);
        }
        if (this.rand.nextInt(8) == 0) {
            x = var4 + this.rand.nextInt(16);
            z = var5 + this.rand.nextInt(16);
            foundGround = false;
            y = 32;
            while (i < 128) {
                if (this.worldObj.getBlock(x, i, z) == IceikaBlocks.frozenGrass) {
                    y = i;
                    foundGround = true;
                    break;
                }
                ++i;
            }
            if (foundGround) {
                this.rareHouses.get(this.rand.nextInt(this.rareHouses.size())).generate(this.worldObj, this.rand, x, y, z);
            }
        }
        if (this.rand.nextInt(4) == 0) {
            int var12 = var4 + this.rand.nextInt(16) + 8;
            int var13 = this.rand.nextInt(128);
            int var14 = var5 + this.rand.nextInt(16) + 8;
            new WorldGenLakes(Blocks.ice).generate(this.worldObj, this.rand, var12, var13, var14);
        }
        for (i = 0; i < 5; ++i) {
            x = var4 + this.rand.nextInt(16);
            z = var5 + this.rand.nextInt(16);
            y = this.worldObj.getHeightValue(x, z);
            new WorldGenGiantTree(false).generate(this.worldObj, this.rand, x, y, z);
        }
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
        return "Iceika";
    }

    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(par2, par4);
        return biomegenbase.getSpawnableList(par1EnumCreatureType);
    }

    public ChunkPosition func_147416_a(World w, String s, int x, int y, int z) {
        return null;
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    public void recreateStructures(int par1, int par2) {
    }
}

