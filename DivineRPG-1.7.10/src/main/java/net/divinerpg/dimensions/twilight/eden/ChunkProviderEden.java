/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFalling
 *  net.minecraft.block.BlockSand
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IProgressUpdate
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.SpawnerAnimals
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.MapGenBase
 *  net.minecraft.world.gen.MapGenCaves
 *  net.minecraft.world.gen.NoiseGeneratorOctaves
 *  net.minecraft.world.gen.feature.WorldGenFlowers
 *  net.minecraft.world.gen.feature.WorldGenLakes
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.terraingen.ChunkProviderEvent$ReplaceBiomeBlocks
 */
package net.divinerpg.dimensions.twilight.eden;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.List;
import java.util.Random;
import net.divinerpg.dimensions.twilight.eden.WorldGenEdenTree;
import net.divinerpg.dimensions.vethea.all.WorldGenConeUp;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;

public class ChunkProviderEden
implements IChunkProvider {
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves field_909_n;
    private NoiseGeneratorOctaves noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    private World worldObj;
    private double[] noiseArray;
    private double[] stoneNoise = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();
    private BiomeGenBase[] biomesForGeneration;
    double[] noise3;
    double[] noise1;
    double[] noise2;
    double[] noise5;
    double[] noise6;
    int[][] field_914_i = new int[32][32];
    private double[] generatedTemperatures;

    public ChunkProviderEden(World var1, long var2) {
        this.worldObj = var1;
        this.rand = new Random(var2);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.field_909_n = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
    }

    public boolean chunkExists(int i, int j) {
        return true;
    }

    public void generateTerrain(int var1, int var2, Block[] var3, BiomeGenBase[] var4) {
        int var6 = 2;
        int var7 = var6 + 1;
        int var8 = 33;
        int var9 = var6 + 1;
        this.noiseArray = this.initializeNoiseField(this.noiseArray, var1 * var6, 0, var2 * var6, var7, var8, var9);
        for (int var10 = 0; var10 < var6; ++var10) {
            for (int var11 = 0; var11 < var6; ++var11) {
                for (int var12 = 0; var12 < 32; ++var12) {
                    double var13 = 0.25;
                    double var15 = this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var17 = this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var19 = this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var21 = this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var23 = (this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1] - var15) * var13;
                    double var25 = (this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
                    double var27 = (this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1] - var19) * var13;
                    double var29 = (this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;
                    for (int var31 = 0; var31 < 4; ++var31) {
                        double var32 = 0.125;
                        double var34 = var15;
                        double var36 = var17;
                        double var38 = (var19 - var15) * var32;
                        double var40 = (var21 - var17) * var32;
                        for (int var42 = 0; var42 < 8; ++var42) {
                            int var43 = var42 + var10 * 8 << 11 | 0 + var11 * 8 << 7 | var12 * 4 + var31;
                            int var44 = 128;
                            double var45 = 0.125;
                            double var47 = var34;
                            double var49 = (var36 - var34) * var45;
                            for (int var51 = 0; var51 < 8; ++var51) {
                                Block var52 = null;
                                if (var47 > 0.0) {
                                    var52 = Blocks.stone;
                                }
                                var3[var43] = var52;
                                var43 += var44;
                                var47 += var49;
                            }
                            var34 += var38;
                            var36 += var40;
                        }
                        var15 += var23;
                        var17 += var25;
                        var19 += var27;
                        var21 += var29;
                    }
                }
            }
        }
    }

    public Chunk provideChunk(int par1, int par2) {
        this.rand.setSeed((long)par1 * 391279128714L + (long)par2 * 132894987741L);
        Block[] blocks = new Block[32768];
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.generateTerrain(par1, par2, blocks, this.biomesForGeneration);
        this.replaceBlocksForBiome(par1, par2, blocks, this.biomesForGeneration);
        for (int i = 0; i < Short.MAX_VALUE; ++i) {
            if (blocks[i] != TwilightBlocks.edenGrass || blocks[i + 1] == null) continue;
            blocks[i] = TwilightBlocks.edenDirt;
        }
        Chunk chunk = new Chunk(this.worldObj, blocks, par1, par2);
        byte[] abyte = chunk.getBiomeArray();
        for (int k = 0; k < abyte.length; ++k) {
            abyte[k] = (byte)this.biomesForGeneration[k].biomeID;
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    public void replaceBlocksForBiome(int var1, int var2, Block[] var3, BiomeGenBase[] var4) {
        int var5 = 63;
        double var6 = 0.03125;
        this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, var1 * 16, var2 * 16, 0, 16, 16, 1, var6 * 2.0, var6 * 2.0, var6 * 2.0);
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks((IChunkProvider)this, var1, var2, var3, var4);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.getResult() == Event.Result.DENY) {
            return;
        }
        for (int var8 = 0; var8 < 16; ++var8) {
            for (int var9 = 0; var9 < 16; ++var9) {
                BiomeGenBase var10 = var4[var9 + var8 * 16];
                float var11 = var10.getFloatTemperature(var9, var9, var9);
                int var12 = (int)(this.stoneNoise[var8 + var9 * 16] / 3.0 + 3.0 + this.rand.nextDouble() * 0.25);
                int var13 = -1;
                Block var14 = var10.topBlock;
                Block var15 = TwilightBlocks.edenGrass;
                for (int var16 = 127; var16 >= 0; --var16) {
                    int var17 = (var9 * 16 + var8) * 128 + var16;
                    if (var16 <= 0 + this.rand.nextInt(5)) {
                        var3[var17] = null;
                        continue;
                    }
                    Block var18 = var3[var17];
                    if (var18 == null) {
                        var13 = -1;
                    } else if (var18 == Blocks.stone) {
                        if (var13 == -1) {
                            if (var12 <= 0) {
                                var14 = TwilightBlocks.edenGrass;
                                var15 = TwilightBlocks.edenGrass;
                            } else if (var16 >= var5 - 4 && var16 <= var5 + 1) {
                                var14 = TwilightBlocks.edenGrass;
                                var15 = TwilightBlocks.twilightStone;
                            }
                            var3[var17] = var16 >= var5 - 1 ? var14 : var15;
                        } else if (var13 > 0) {
                            var3[var17] = var15;
                            if (--var13 == 0 && var15 == TwilightBlocks.edenGrass) {
                                var13 = -1;
                                var15 = TwilightBlocks.edenGrass;
                            }
                        }
                    }
                    if (var13 <= 0) continue;
                    var3[var17] = var15;
                    if (--var13 != 0 || var15 != TwilightBlocks.twilightStone) continue;
                    var13 = -1;
                    var15 = TwilightBlocks.twilightStone;
                }
            }
        }
    }

    public Chunk loadChunk(int i, int j) {
        return this.provideChunk(i, j);
    }

    private double[] initializeNoiseField(double[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        if (var1 == null) {
            var1 = new double[var5 * var6 * var7];
        }
        double var8 = 684.412;
        double var10 = 684.412;
        this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, var2, var4, var5, var7, 1.121, 1.121, 0.5);
        this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, var2, var4, var5, var7, 200.0, 200.0, 0.5);
        this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, var2, var3, var4, var5, var6, var7, var8 / 80.0, var8 / 160.0, (var8 *= 2.0) / 80.0);
        this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, var2, var3, var4, var5, var6, var7, var8, var10, var8);
        this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, var2, var3, var4, var5, var6, var7, var8, var10, var8);
        int var12 = 0;
        int var13 = 0;
        int var14 = 16 / var5;
        for (int var15 = 0; var15 < var5; ++var15) {
            int var16 = var15 * var14 + var14 / 2;
            for (int var17 = 0; var17 < var7; ++var17) {
                double d;
                int var18 = var17 * var14 + var14 / 2;
                double var19 = (this.noise5[var13] + 256.0) / 512.0;
                double var21 = this.noise6[var13] / 8000.0;
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
                var21 = var21 * (double)var6 / 16.0;
                ++var13;
                double var23 = (double)var6 / 2.0;
                for (int var25 = 0; var25 < var6; ++var25) {
                    double var37;
                    double var26 = 0.0;
                    double var28 = ((double)var25 - var23) * 8.0 / var19;
                    if (var28 < 0.0) {
                        var28 *= -1.0;
                    }
                    double var30 = this.noise1[var12] / 512.0;
                    double var32 = this.noise2[var12] / 512.0;
                    double var34 = (this.noise3[var12] / 10.0 + 1.0) / 2.0;
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
        int var16;
        int c;
        int var14;
        int var15;
        int var13;
        BlockFalling.fallInstantly = true;
        int var4 = i * 16;
        int var5 = j * 16;
        BiomeGenBase var6 = this.worldObj.getWorldChunkManager().getBiomeGenAt(var4 + 16, var5 + 16);
        this.rand.setSeed(this.worldObj.getSeed() * (long)(i + j) * (long)this.rand.nextInt());
        if (this.rand.nextInt(3) == 0) {
            var13 = var4 + this.rand.nextInt(16);
            var15 = var5 + this.rand.nextInt(16);
            var14 = this.worldObj.getHeightValue(var13, var15);
            boolean stone = true;
            for (int n = 0; n < 14; ++n) {
                for (int m = 0; m < 14; ++m) {
                    if (this.worldObj.getBlock(var13 + n, var14 - 6, var15 + m).isOpaqueCube()) continue;
                    stone = false;
                }
            }
            if (stone) {
                new WorldGenConeUp(VanillaBlocks.divineMossystone).generate(this.worldObj, this.rand, var13 + 7, var14 - 5, var15 + 7, this.rand.nextInt(5) + 5);
            }
        }
        if (this.rand.nextInt(4) == 0) {
            var13 = var4 + this.rand.nextInt(16) + 8;
            var14 = this.rand.nextInt(128);
            var15 = var5 + this.rand.nextInt(16) + 8;
            new WorldGenLakes(Blocks.water).generate(this.worldObj, this.rand, var13, var14 - 3, var15);
        }
        for (c = 0; c < 10; ++c) {
            var14 = var4 + this.rand.nextInt(16);
            var15 = this.rand.nextInt(128);
            var16 = var5 + this.rand.nextInt(16);
            new WorldGenMinable(TwilightBlocks.edenOre, 11, TwilightBlocks.twilightStone).generate(this.worldObj, this.rand, var14, var15, var16);
        }
        for (c = 0; c < 10; ++c) {
            var14 = var4 + this.rand.nextInt(16);
            var15 = this.rand.nextInt(128);
            var16 = var5 + this.rand.nextInt(16);
            new WorldGenMinable(TwilightBlocks.enchantOre, 11, TwilightBlocks.twilightStone).generate(this.worldObj, this.rand, var14, var15, var16);
        }
        for (c = 0; c < 30; ++c) {
            var14 = var4 + this.rand.nextInt(16);
            var15 = this.rand.nextInt(128);
            var16 = var5 + this.rand.nextInt(16);
            new WorldGenMinable(TwilightBlocks.lightOre, 6, TwilightBlocks.twilightStone).generate(this.worldObj, this.rand, var14, var15, var16);
        }
        for (c = 0; c < 3; ++c) {
            var14 = var4 + this.rand.nextInt(16) + 8;
            var16 = var5 + this.rand.nextInt(16) + 8;
            var15 = this.rand.nextInt(128) + 1;
            new WorldGenFlowers(TwilightBlocks.sunbloom).generate(this.worldObj, this.rand, var14, var15, var16);
        }
        for (c = 0; c < 2; ++c) {
            var14 = var4 + this.rand.nextInt(16) + 8;
            var16 = var5 + this.rand.nextInt(16) + 8;
            var15 = this.rand.nextInt(128) + 1;
            new WorldGenFlowers(TwilightBlocks.edenBrush).generate(this.worldObj, this.rand, var14, var15, var16);
        }
        for (var13 = 0; var13 < 3; ++var13) {
            var14 = var4 + this.rand.nextInt(16) + 8;
            var16 = var5 + this.rand.nextInt(16) + 8;
            var15 = this.rand.nextInt(128) + 1;
            new WorldGenFlowers(TwilightBlocks.sunBlossom).generate(this.worldObj, this.rand, var14, var15, var16);
        }
        WorldGenEdenTree var17 = new WorldGenEdenTree(true);
        for (int var18 = 0; var18 < 2; ++var18) {
            int var19 = var4 + this.rand.nextInt(16);
            int var20 = var5 + this.rand.nextInt(16);
            int var21 = this.worldObj.getHeightValue(var19, var20);
            var17.generate(this.worldObj, this.rand, var19, var21, var20);
        }
        SpawnerAnimals.performWorldGenSpawning((World)this.worldObj, (BiomeGenBase)var6, (int)(var4 + 8), (int)(var5 + 8), (int)16, (int)16, (Random)this.rand);
        BlockSand.fallInstantly = false;
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
        return true;
    }

    public boolean unloadQueuedChunks() {
        return false;
    }

    public boolean canSave() {
        return true;
    }

    public String makeString() {
        return "Eden";
    }

    public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i, int j, int k) {
        BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(i, k);
        return var5 == null ? null : var5.getSpawnableList(enumcreaturetype);
    }

    public ChunkPosition func_147416_a(World world, String s, int i, int j, int k) {
        return null;
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    public void recreateStructures(int i, int j) {
    }

    public void saveExtraData() {
    }
}

