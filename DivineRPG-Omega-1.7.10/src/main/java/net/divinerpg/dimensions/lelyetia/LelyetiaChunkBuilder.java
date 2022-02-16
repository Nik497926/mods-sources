/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package net.divinerpg.dimensions.lelyetia;

import java.util.Random;
import net.divinerpg.dimensions.lelyetia.AchonyTreeU1;
import net.divinerpg.dimensions.lelyetia.AchonyTreeU2;
import net.divinerpg.dimensions.lelyetia.ChurryTree1;
import net.divinerpg.dimensions.lelyetia.ChurryTree2;
import net.divinerpg.dimensions.lelyetia.ChurryTreeU1;
import net.divinerpg.dimensions.lelyetia.ChurryTreeU2;
import net.divinerpg.dimensions.lelyetia.GrawPillar;
import net.divinerpg.dimensions.lelyetia.LelyetiaHole1;
import net.divinerpg.dimensions.lelyetia.LelyetiaHole2;
import net.divinerpg.dimensions.lelyetia.LelyetiaHole3;
import net.divinerpg.dimensions.lelyetia.LelyetiaHole4;
import net.divinerpg.dimensions.lelyetia.LelyetiaHole5;
import net.divinerpg.dimensions.lelyetia.LelyetiaHole6;
import net.divinerpg.dimensions.lelyetia.LelyetiaHole7;
import net.divinerpg.dimensions.lelyetia.StemDOWN;
import net.divinerpg.dimensions.lelyetia.StemUP;
import net.divinerpg.dimensions.lelyetia.WigglerDOWN;
import net.divinerpg.dimensions.lelyetia.WigglerUP;
import net.divinerpg.dimensions.lelyetia.gen.AchonyTree1;
import net.divinerpg.dimensions.lelyetia.gen.AchonyTree2;
import net.divinerpg.dimensions.lelyetia.gen.IVoxpondsStructure;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.minecraft.block.Block;

public class LelyetiaChunkBuilder {
    private int achonyUP = 0;
    private int achonyDOWN = 0;
    private int churryUP = 0;
    private int churryDOWN = 0;
    private static Random rand = new Random();
    private static IVoxpondsStructure AchonyTree1 = new AchonyTree1();
    private static IVoxpondsStructure AchonyTree2 = new AchonyTree2();
    private static IVoxpondsStructure AchonyTreeU1 = new AchonyTreeU1();
    private static IVoxpondsStructure AchonyTreeU2 = new AchonyTreeU2();
    private static IVoxpondsStructure ChurryTree1 = new ChurryTree1();
    private static IVoxpondsStructure ChurryTree2 = new ChurryTree2();
    private static IVoxpondsStructure ChurryTreeU1 = new ChurryTreeU1();
    private static IVoxpondsStructure ChurryTreeU2 = new ChurryTreeU2();
    private static IVoxpondsStructure LelyetiaHole1 = new LelyetiaHole1();
    private static IVoxpondsStructure LelyetiaHole2 = new LelyetiaHole2();
    private static IVoxpondsStructure LelyetiaHole3 = new LelyetiaHole3();
    private static IVoxpondsStructure LelyetiaHole4 = new LelyetiaHole4();
    private static IVoxpondsStructure LelyetiaHole5 = new LelyetiaHole5();
    private static IVoxpondsStructure LelyetiaHole6 = new LelyetiaHole6();
    private static IVoxpondsStructure LelyetiaHole7 = new LelyetiaHole7();
    private static IVoxpondsStructure Pillar = new GrawPillar();
    private static IVoxpondsStructure StemUP = new StemUP();
    private static IVoxpondsStructure StemDown = new StemDOWN();
    private static IVoxpondsStructure WigglerUP = new WigglerUP();
    private static IVoxpondsStructure WigglerDown = new WigglerDOWN();
    private boolean genning;

    public Block[][][] buildChunk() {
        Block[][][] chunk = new Block[16][256][16];
        this.genning = rand.nextInt(11) < 7;
        for (int x = 0; x < 16; ++x) {
            for (int y = 0; y < 65; ++y) {
                for (int z = 0; z < 16; ++z) {
                    if (this.genning) {
                        if (y > 55 && y < 59) {
                            chunk[x][y][z] = JourneyBlocks.StoneLelyetia;
                        }
                        if (y == 55) {
                            chunk[x][y][z] = JourneyBlocks.GrassLelyetiaDown;
                        }
                        if (y == 59) {
                            chunk[x][y][z] = JourneyBlocks.GrassLelyetiaUp;
                        }
                        if (rand.nextInt(24) == 7 && y == 54) {
                            if (rand.nextInt(2) == 1) {
                                StemDown.generate(chunk, x, y, z);
                            } else {
                                WigglerDown.generate(chunk, x, y, z);
                            }
                        }
                        if (y == 3 && rand.nextInt(75) == 41 && this.churryDOWN < 2 && x < 8 && z < 8) {
                            if (rand.nextInt(2) == 1) {
                                ChurryTreeU1.generate(chunk, x, y + 12, z);
                            } else {
                                ChurryTreeU2.generate(chunk, x, y, z);
                            }
                            ++this.churryDOWN;
                        }
                        if (y == 30 && rand.nextInt(75) == 41 && this.achonyDOWN < 2 && x < 10 && z < 10) {
                            if (rand.nextInt(2) == 1) {
                                AchonyTreeU1.generate(chunk, x, y, z);
                            } else {
                                AchonyTreeU2.generate(chunk, x, y - 7, z);
                            }
                            ++this.achonyDOWN;
                        }
                    } else if (x == 0 && y == 55 && z == 0) {
                        int pick = rand.nextInt(7);
                        if (pick == 1) {
                            LelyetiaHole1.generate(chunk, x, y, z);
                        } else if (pick == 2) {
                            LelyetiaHole2.generate(chunk, x, y, z);
                        } else if (pick == 3) {
                            LelyetiaHole3.generate(chunk, x, y, z);
                        } else if (pick == 4) {
                            LelyetiaHole4.generate(chunk, x, y, z);
                        } else if (pick == 5) {
                            LelyetiaHole5.generate(chunk, x, y, z);
                        } else if (pick == 6) {
                            LelyetiaHole6.generate(chunk, x, y, z);
                        } else {
                            LelyetiaHole7.generate(chunk, x, y, z);
                        }
                    }
                    if (rand.nextInt(5) == 3 && y > 50 && chunk[x][y + 1][z] == JourneyBlocks.GrassLelyetiaDown && chunk[x][y][z] == null) {
                        chunk[x][y][z] = JourneyBlocks.LelyetianGrassDown;
                    }
                    if (rand.nextInt(5) == 3 && y > 50 && chunk[x][y - 1][z] == JourneyBlocks.GrassLelyetiaUp && chunk[x][y][z] == null) {
                        chunk[x][y][z] = JourneyBlocks.LelyetianGrassUp;
                    }
                    if (rand.nextInt(24) == 7 && y == 60 && chunk[x][y - 1][z] == JourneyBlocks.GrassLelyetiaUp) {
                        if (rand.nextInt(2) == 1) {
                            StemUP.generate(chunk, x, y, z);
                        } else {
                            WigglerUP.generate(chunk, x, y, z);
                        }
                    }
                    if (y > 50 && rand.nextInt(75) == 41 && this.churryUP < 2 && x > 4 && z > 4 && x < 12 && z < 12 && chunk[x][y - 1][z] == JourneyBlocks.GrassLelyetiaUp) {
                        if (rand.nextInt(2) == 1) {
                            ChurryTree1.generate(chunk, x - 4, y, z - 4);
                        } else {
                            ChurryTree2.generate(chunk, x - 4, y, z - 4);
                        }
                        ++this.churryUP;
                    }
                    if (y == 60 && rand.nextInt(6000) == 760 && x > 4 && z > 4 && x < 11 && z < 11 && chunk[x][y - 1][z] == JourneyBlocks.GrassLelyetiaUp) {
                        Pillar.generate(chunk, x - 3, y, z - 3);
                    }
                    if (y <= 50 || rand.nextInt(75) != 41 || this.achonyUP >= 2 || x <= 2 || z <= 2 || x >= 13 || z >= 13 || chunk[x][y - 1][z] != JourneyBlocks.GrassLelyetiaUp) continue;
                    if (rand.nextInt(2) == 1) {
                        AchonyTree1.generate(chunk, x - 3, y, z - 3);
                    } else {
                        AchonyTree2.generate(chunk, x - 3, y, z - 3);
                    }
                    ++this.achonyUP;
                }
            }
        }
        return chunk;
    }

    public static void toTerrainArray(Block[][][] chunk, Block[] output) {
        for (int x = 0; x < 16; ++x) {
            for (int y = 0; y < 256; ++y) {
                for (int z = 0; z < 16; ++z) {
                    output[z << 12 | x << 8 | y] = chunk[x][y][z];
                }
            }
        }
    }
}

