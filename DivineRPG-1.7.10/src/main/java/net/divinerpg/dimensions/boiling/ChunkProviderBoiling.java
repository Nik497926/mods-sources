/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IProgressUpdate
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.NoiseGenerator
 *  net.minecraft.world.gen.NoiseGeneratorOctaves
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraft.world.gen.feature.WorldGenFlowers
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.terraingen.ChunkProviderEvent$ReplaceBiomeBlocks
 *  net.minecraftforge.event.terraingen.TerrainGen
 */
package net.divinerpg.dimensions.boiling;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.divinerpg.dimensions.ChunkPrimer;
import net.divinerpg.dimensions.boiling.WorldGenHornDungeon;
import net.divinerpg.dimensions.boiling.gen.WorldGenBoilTrees;
import net.divinerpg.dimensions.boiling.gen.WorldGenBoilingCastle;
import net.divinerpg.dimensions.boiling.gen.WorldGenBoilingLamp;
import net.divinerpg.dimensions.boiling.gen.WorldGenBrisonNetwork;
import net.divinerpg.dimensions.boiling.gen.WorldGenIsland;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderBoiling
implements IChunkProvider {
    private Random rand;
    private ArrayList<WorldGenerator> trees;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorPerlin noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    private World worldObj;
    private final double[] da;
    private final float[] parabolicField;
    private double[] stoneNoise = new double[256];
    private BiomeGenBase[] biomesForGeneration;
    double[] gen1;
    double[] gen2;
    double[] gen3;
    double[] gen4;

    public ChunkProviderBoiling(World worldIn, long p_i45636_2_) {
        this.worldObj = worldIn;
        this.rand = new Random(p_i45636_2_);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorPerlin(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.da = new double[825];
        this.parabolicField = new float[25];
        this.trees = new ArrayList(3);
        this.trees.add(new WorldGenBoilTrees());
        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                float f;
                this.parabolicField[j + 2 + (k + 2) * 5] = f = 10.0f / MathHelper.sqrt_float((float)((float)(j * j + k * k) + 0.2f));
            }
        }
        NoiseGenerator[] noiseGens = new NoiseGenerator[]{this.noiseGen1, this.noiseGen2, this.noiseGen3, this.noiseGen4, this.noiseGen5, this.noiseGen6, this.mobSpawnerNoise};
        noiseGens = TerrainGen.getModdedNoiseGenerators((World)worldIn, (Random)this.rand, (NoiseGenerator[])noiseGens);
        this.noiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
        this.noiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
        this.noiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
        this.noiseGen4 = (NoiseGeneratorPerlin)noiseGens[3];
        this.noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
        this.noiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
        this.mobSpawnerNoise = (NoiseGeneratorOctaves)noiseGens[6];
    }

    public boolean chunkExists(int x, int z) {
        return true;
    }

    public Chunk loadChunk(int x, int z) {
        return this.provideChunk(x, z);
    }

    public Chunk provideChunk(int x, int z) {
        ChunkPrimer primer = new ChunkPrimer();
        this.rand.setSeed((long)x * 391279512714L + (long)z * 132894987741L);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        this.setBlocksInChunk(x, z, primer);
        this.biomeBlocks(x, z, primer, this.biomesForGeneration);
        Chunk chunk = new Chunk(this.worldObj, primer.getChunkData(), primer.getChunkMetadata(), x, z);
        byte[] biomes = chunk.getBiomeArray();
        for (int k = 0; k < biomes.length; ++k) {
            biomes[k] = (byte)this.biomesForGeneration[k].biomeID;
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    public void biomeBlocks(int x, int z, ChunkPrimer c, BiomeGenBase[] b) {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks((IChunkProvider)this, x, z, c.getChunkData(), c.getChunkMetadata(), b);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.getResult() == Event.Result.DENY) {
            return;
        }
        double d0 = 0.03125;
        this.stoneNoise = this.noiseGen4.func_151599_a(this.stoneNoise, (double)(x * 16), (double)(z * 16), 16, 16, d0 * 1.0, d0 * 2.0, 1.0);
        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                this.generateTerrain(this.rand, c, x * 16 + k, z * 16 + l, this.stoneNoise[l + k * 16]);
            }
        }
    }

    void setBlocksInChunk(int x, int z, ChunkPrimer c) {
        int b0 = 20;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
        this.generate(x * 4, 0, z * 4);
        for (int k = 0; k < 4; ++k) {
            int l = k * 5;
            int i1 = (k + 1) * 5;
            for (int j1 = 0; j1 < 4; ++j1) {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;
                for (int k2 = 0; k2 < 32; ++k2) {
                    double d0 = 0.125;
                    double d1 = this.da[k1 + k2];
                    double d2 = this.da[l1 + k2];
                    double d3 = this.da[i2 + k2];
                    double d4 = this.da[j2 + k2];
                    double d5 = (this.da[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.da[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.da[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.da[j2 + k2 + 1] - d4) * d0;
                    for (int l2 = 0; l2 < 8; ++l2) {
                        double d9 = 0.25;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for (int i3 = 0; i3 < 4; ++i3) {
                            double d14 = 0.25;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;
                            for (int j3 = 0; j3 < 4; ++j3) {
                                double d;
                                int d17 = k2 * 8 + l2 - 30;
                                d15 += d16;
                                if (!(d > 0.0)) continue;
                                if (d17 < 0) {
                                    c.setBlock(k * 4 + i3, d17 + 30, j1 * 4 + j3, JourneyBlocks.hotBlock);
                                    continue;
                                }
                                c.setBlock(k * 4 + i3, d17, j1 * 4 + j3, JourneyBlocks.hotBlock);
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

    private void generate(int x, int y, int z) {
        this.gen4 = this.noiseGen6.generateNoiseOctaves(this.gen4, x, z, 5, 5, 200.0, 200.0, 0.5);
        this.gen1 = this.noiseGen3.generateNoiseOctaves(this.gen1, x, y, z, 5, 33, 5, 8.555150000000001, 4.277575000000001, 8.555150000000001);
        this.gen2 = this.noiseGen1.generateNoiseOctaves(this.gen2, x, y, z, 5, 33, 5, 684.412, 684.412, 684.412);
        this.gen3 = this.noiseGen2.generateNoiseOctaves(this.gen3, x, y, z, 5, 33, 5, 684.412, 684.412, 684.412);
        int l = 0;
        int i1 = 0;
        for (int j1 = 0; j1 < 5; ++j1) {
            for (int k1 = 0; k1 < 5; ++k1) {
                double d;
                float f = 0.0f;
                float f1 = 0.0f;
                float f2 = 0.0f;
                int b0 = 2;
                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        BiomeGenBase biomegenbase1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
                        float f3 = biomegenbase1.rootHeight;
                        float f4 = biomegenbase1.heightVariation;
                        float f5 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0f);
                        f += f4 * f5 / 2.0f;
                        f1 += f3 * f5 / 2.0f;
                        f2 += f5 / 2.0f;
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
                d12 = d12 * 3.0 - 2.0;
                if (d < 0.0) {
                    double d2;
                    d12 /= 2.0;
                    if (d2 < -1.0) {
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

    public final void generateTerrain(Random r, ChunkPrimer c, int x, int z, double s) {
        Block iblockstate = JourneyBlocks.hotBlock;
        Block iblockstate1 = JourneyBlocks.rockDust;
        int k = -1;
        int l = (int)(s / 3.0 + 3.0 + r.nextDouble() * 0.25);
        int i1 = x & 0xF;
        int j1 = z & 0xF;
        for (int k1 = 255; k1 >= 0; --k1) {
            if (k1 <= 1) {
                c.setBlock(j1, k1, i1, Blocks.bedrock);
                continue;
            }
            if (c.getBlock(j1, k1, i1) == null) {
                k = -1;
                continue;
            }
            if (c.getBlock(j1, k1, i1) != JourneyBlocks.rockDust) continue;
            if (k == -1) {
                if (l <= 0) {
                    iblockstate = null;
                    iblockstate1 = JourneyBlocks.rockDust;
                } else if (k1 >= 59 && k1 <= 64) {
                    iblockstate = JourneyBlocks.hotBlock;
                    iblockstate1 = JourneyBlocks.rockDust;
                }
                if (k1 <= 64 && (iblockstate == null || iblockstate.getMaterial() == Material.air)) {
                    iblockstate = JourneyBlocks.rockDust;
                }
                k = l;
                if (k1 > 64) {
                    c.setBlock(j1, k1, i1, iblockstate);
                    continue;
                }
                if (k1 < 56 - l) {
                    iblockstate = null;
                    iblockstate1 = JourneyBlocks.rockDust;
                    continue;
                }
                c.setBlock(j1, k1, i1, iblockstate1);
                continue;
            }
            if (k <= 0) continue;
            --k;
            c.setBlock(j1, k1, i1, iblockstate1);
        }
    }

    public void populate(IChunkProvider c, int cx, int cz) {
        int yCoord;
        int times;
        int z;
        int x;
        int y;
        int i;
        int x1 = cx * 16;
        int z1 = cz * 16;
        Random r = this.rand;
        for (i = 0; i < 12; ++i) {
            y = r.nextInt(130) + 1;
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            new WorldGenFlowers(JourneyBlocks.burntGrass).generate(this.worldObj, r, x, y, z);
        }
        for (i = 0; i < 4; ++i) {
            y = r.nextInt(130) + 1;
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            new WorldGenMinable(JourneyBlocks.boilOre, 4, JourneyBlocks.hotBlock).generate(this.worldObj, r, x, y, z);
        }
        for (i = 0; i < 4; ++i) {
            y = r.nextInt(130) + 1;
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            new WorldGenFlowers(JourneyBlocks.infernoPlant).generate(this.worldObj, r, x, y, z);
            new WorldGenFlowers(JourneyBlocks.flameFlower).generate(this.worldObj, r, x, y, z);
        }
        if (this.rand.nextInt(3) == 0) {
            for (times = 0; times < 2; ++times) {
                x = x1 + this.rand.nextInt(16) + 8;
                z = z1 + this.rand.nextInt(16) + 8;
                yCoord = this.rand.nextInt(128) + 1;
                if (yCoord <= 35) continue;
                new WorldGenBoilingLamp().generate(this.worldObj, this.rand, x, yCoord, z);
            }
        }
        for (times = 0; times < 1; ++times) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            yCoord = this.rand.nextInt(128) + 1;
            if (yCoord <= 35) continue;
            new WorldGenBrisonNetwork().generate(this.worldObj, this.rand, x, yCoord, z);
        }
        for (times = 0; times < 1; ++times) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            yCoord = this.rand.nextInt(128) + 1;
            if (!this.isBlockTop(x, yCoord - 1, z, JourneyBlocks.hotBlock)) continue;
            new WorldGenHornDungeon().generate(this.worldObj, this.rand, x, yCoord, z);
        }
        if (this.rand.nextInt(99) < 10) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            yCoord = this.rand.nextInt(130) + 1;
            if (yCoord > 50 && yCoord < 60) {
                new WorldGenIsland().generate(this.worldObj, this.rand, x, yCoord, z);
            }
        }
        if (this.rand.nextInt(30) < 1) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            yCoord = this.rand.nextInt(150) + 1;
            if (yCoord > 35) {
                new WorldGenBoilingCastle().generate(this.worldObj, this.rand, x, yCoord, z);
            }
        }
        for (times = 0; times < 3; ++times) {
            x = x1 + this.rand.nextInt(16) + 8;
            z = z1 + this.rand.nextInt(16) + 8;
            yCoord = this.rand.nextInt(129) + 1;
            this.trees.get(this.rand.nextInt(this.trees.size())).generate(this.worldObj, this.rand, x, yCoord, z);
        }
    }

    public boolean isBlockTop(int x, int y, int z, Block grass) {
        return this.worldObj.getBlock(x, y, z) == grass && this.worldObj.getBlock(x, y + 1, z) == Blocks.air && this.worldObj.getBlock(x, y + 2, z) == Blocks.air && this.worldObj.getBlock(x, y + 3, z) == Blocks.air && this.worldObj.getBlock(x, y + 4, z) == Blocks.air && this.worldObj.getBlock(x, y + 5, z) == Blocks.air;
    }

    public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
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
        return "Boiling";
    }

    public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x, z);
        return biome.getSpawnableList(creatureType);
    }

    public ChunkPosition func_147416_a(World world, String s, int i, int i1, int i2) {
        return null;
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    public void recreateStructures(int i, int i1) {
    }
}

