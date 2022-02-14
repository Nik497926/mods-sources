/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.vanilla;

import java.util.Random;
import net.divinerpg.dimensions.base.WorldGenAPI;
import net.divinerpg.entities.vanilla.EntityTempleGuardian;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTowerDungeon
extends WorldGenerator {
    public boolean generate(World w, Random r, int x, int y, int z) {
        int levels = 4;
        int height = levels * 4;
        if (w.getBlock(x - 1, y - 1, z - 1) == Blocks.netherrack && w.getBlock(x + 15, y - 1, z + 10) == Blocks.netherrack && w.getBlock(x + 15, y - 1, z - 1) == Blocks.netherrack && w.getBlock(x - 1, y - 1, z + 10) == Blocks.netherrack && w.getBlock(x - 1, y + 3, z - 1) == Blocks.air && w.getBlock(x + 15, y + 3, z + 10) == Blocks.air && w.getBlock(x + 15, y + 3, z - 1) == Blocks.air && w.getBlock(x - 1, y + 3, z + 10) == Blocks.air) {
            WorldGenAPI.addRectangle(17, 12, 1, w, x - 1, y, z - 1, Blocks.netherrack);
            WorldGenAPI.addCornerlessRectangle(15, 10, height + 10, w, x, y + 1, z, JourneyBlocks.dungeonBrick);
            WorldGenAPI.addRectangle(13, 8, height + 10, w, x + 1, y + 1, z + 1, Blocks.air);
            WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y, z + 1, JourneyBlocks.dungeonBrick);
            WorldGenAPI.addRectangle(2, 1, 3, w, x + 6, y + 1, z, Blocks.air);
            WorldGenAPI.addRectangle(2, 1, 3, w, x + 6, y + 1, z + 9, Blocks.air);
            WorldGenAPI.addRectangle(1, 2, 3, w, x, y + 1, z + 4, Blocks.air);
            WorldGenAPI.addRectangle(1, 2, 3, w, x + 14, y + 1, z + 4, Blocks.air);
            this.addLevel(w, x, y, z, 4, 1);
            this.addLevel(w, x, y, z, 4, 0);
            this.addLevel(w, x, y, z, 12, 1);
            this.addLevel(w, x, y, z, 12, 0);
            this.addLevel(w, x, y, z, 20, 1);
            WorldGenAPI.addRectangleWithMetadata(15, 1, 1, w, x, y + height * 2 - 6, z - 1, JourneyBlocks.dungeonBrickStairs, 6);
            WorldGenAPI.addRectangleWithMetadata(15, 1, 1, w, x, y + height * 2 - 6, z + 10, JourneyBlocks.dungeonBrickStairs, 7);
            WorldGenAPI.addRectangleWithMetadata(1, 10, 1, w, x + 15, y + height * 2 - 6, z, JourneyBlocks.dungeonBrickStairs, 5);
            WorldGenAPI.addRectangleWithMetadata(1, 10, 1, w, x - 1, y + height * 2 - 6, z, JourneyBlocks.dungeonBrickStairs, 12);
            WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y + height + 8, z + 1, JourneyBlocks.mossyEssenceStone);
            WorldGenAPI.addRectangle(15, 10, 1, w, x, y + height + 10, z, JourneyBlocks.dungeonBrick);
            WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y + height + 10, z + 1, Blocks.air);
            WorldGenAPI.addRectangle(4, 2, 1, w, x + 2, y + height + 8, z + 1, Blocks.air);
            WorldGenAPI.addRectangle(15, 10, 1, w, x, y + height + 11, z, JourneyBlocks.dungeonLampFence);
            WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y + height + 11, z + 1, Blocks.air);
            WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y + height + 12, z + 1, Blocks.air);
            for (int i = 0; i < 4; ++i) {
                w.setBlock(x + 5 - i, y + i + height + 5, z + 1, JourneyBlocks.dungeonBrickStairs, 1, 2);
                w.setBlock(x + 5 - i, y + i + height + 5, z + 2, JourneyBlocks.dungeonBrickStairs, 1, 2);
            }
            w.setBlock(x + 13, y + height + 9, z + 1, JourneyBlocks.dungeonLamp);
            w.setBlock(x + 1, y + height + 9, z + 8, JourneyBlocks.dungeonLamp);
            w.setBlock(x + 13, y + height + 9, z + 8, JourneyBlocks.dungeonLamp);
            int rand = w.rand.nextInt(4);
            switch (rand) {
                case 0: {
                    w.setBlock(x + 13, y + height + 10, z + 1, JourneyBlocks.blueGems);
                    w.setBlock(x + 1, y + height + 10, z + 8, JourneyBlocks.blueGems);
                    w.setBlock(x + 13, y + height + 10, z + 8, JourneyBlocks.blueGems);
                    break;
                }
                case 1: {
                    w.setBlock(x + 13, y + height + 10, z + 1, JourneyBlocks.yellowGems);
                    w.setBlock(x + 1, y + height + 10, z + 8, JourneyBlocks.yellowGems);
                    w.setBlock(x + 13, y + height + 10, z + 8, JourneyBlocks.yellowGems);
                    break;
                }
                case 2: {
                    w.setBlock(x + 13, y + height + 10, z + 1, JourneyBlocks.greenGems);
                    w.setBlock(x + 1, y + height + 10, z + 8, JourneyBlocks.greenGems);
                    w.setBlock(x + 13, y + height + 10, z + 8, JourneyBlocks.greenGems);
                    break;
                }
                case 3: {
                    w.setBlock(x + 13, y + height + 10, z + 1, JourneyBlocks.purpleGems);
                    w.setBlock(x + 1, y + height + 10, z + 8, JourneyBlocks.purpleGems);
                    w.setBlock(x + 13, y + height + 10, z + 8, JourneyBlocks.purpleGems);
                }
            }
            w.setBlock(x + 12, y + height + 9, z + 8, JourneyBlocks.dungeonLampStairs, 0, 2);
            w.setBlock(x + 12, y + height + 9, z + 7, JourneyBlocks.dungeonLampStairs, 0, 2);
            w.setBlock(x + 13, y + height + 9, z + 7, JourneyBlocks.dungeonLampStairs, 2, 2);
            w.setBlock(x + 12, y + height + 9, z + 2, JourneyBlocks.dungeonLampStairs, 0, 2);
            w.setBlock(x + 12, y + height + 9, z + 1, JourneyBlocks.dungeonLampStairs, 0, 2);
            w.setBlock(x + 13, y + height + 9, z + 2, JourneyBlocks.dungeonLampStairs, 3, 2);
            w.setBlock(x + 2, y + height + 9, z + 7, JourneyBlocks.dungeonLampStairs, 2, 2);
            w.setBlock(x + 1, y + height + 9, z + 7, JourneyBlocks.dungeonLampStairs, 2, 2);
            w.setBlock(x + 2, y + height + 9, z + 8, JourneyBlocks.dungeonLampStairs, 1, 2);
            this.addDifferentBlocks(w, x, y, z);
            if (!w.isRemote) {
                EntityTempleGuardian guard = new EntityTempleGuardian(w);
                guard.setLocationAndAngles(x + 5, y + height + 10, z + 5, 0.0f, 0.0f);
                w.spawnEntityInWorld((Entity)guard);
            }
            return true;
        }
        return false;
    }

    public void addDifferentBlocks(World w, int x, int y, int z) {
        int z1;
        int y1;
        int x1;
        Random r = new Random();
        for (x1 = 0; x1 < 15; ++x1) {
            block1: for (y1 = 0; y1 < 200; ++y1) {
                for (z1 = 0; z1 < 15; ++z1) {
                    if (w.getBlock(x + x1, y + y1, z + z1) != JourneyBlocks.dungeonBrick || r.nextInt(15) != 0) continue;
                    w.setBlock(x + x1, y + y1, z + z1, JourneyBlocks.dungeonChiseledBrick);
                    continue block1;
                }
            }
        }
        for (x1 = 0; x1 < 15; ++x1) {
            block4: for (y1 = 0; y1 < 200; ++y1) {
                for (z1 = 0; z1 < 15; ++z1) {
                    if (w.getBlock(x + x1, y + y1, z + z1) != JourneyBlocks.dungeonBrick || r.nextInt(10) != 0) continue;
                    w.setBlock(x + x1, y + y1, z + z1, JourneyBlocks.dungeonBrickCarved);
                    continue block4;
                }
            }
        }
        for (x1 = 0; x1 < 15; ++x1) {
            block7: for (y1 = 0; y1 < 200; ++y1) {
                for (z1 = 0; z1 < 15; ++z1) {
                    if (w.getBlock(x + x1, y + y1, z + z1) != JourneyBlocks.dungeonBrick || r.nextInt(10) != 0) continue;
                    w.setBlock(x + x1, y + y1, z + z1, JourneyBlocks.dungeonCrackedBrick);
                    continue block7;
                }
            }
        }
        for (int i = 0; i < 50; ++i) {
            for (int j = 0; j < 200; ++j) {
                for (int k = 0; k < 50; ++k) {
                    if (w.getBlock(i, j, k) != JourneyBlocks.dungeonBrick || r.nextInt(10) != 0) continue;
                    w.setBlock(i, j, k, JourneyBlocks.dungeonCrackedBrick);
                }
            }
        }
    }

    public void addLevel(World w, int x, int y, int z, int height, int lor) {
        int z1;
        int y1;
        int x1;
        int i;
        Random r = new Random();
        WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y + height, z + 1, JourneyBlocks.dungeonBrick);
        WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y + height + 4, z + 1, JourneyBlocks.dungeonBrick);
        WorldGenAPI.addRectangle(4, 2, 1, w, x + 2, y + height + 4, z + 1, Blocks.air);
        WorldGenAPI.addRectangle(4, 2, 1, w, x + 2, y + height, z + 7, Blocks.air);
        WorldGenAPI.addBlock(w, x + 13, y + height - 1, z + 1, JourneyBlocks.dungeonLamp);
        WorldGenAPI.addBlock(w, x + 1, y + height - 1, z + 1, JourneyBlocks.dungeonLamp);
        WorldGenAPI.addBlock(w, x + 1, y + height - 1, z + 8, JourneyBlocks.dungeonLamp);
        WorldGenAPI.addBlock(w, x + 13, y + height - 1, z + 8, JourneyBlocks.dungeonLamp);
        WorldGenAPI.addBlock(w, x + 13, y + height - 5, z + 1, JourneyBlocks.dungeonLamp);
        WorldGenAPI.addBlock(w, x + 1, y + height - 5, z + 1, JourneyBlocks.dungeonLamp);
        WorldGenAPI.addBlock(w, x + 1, y + height - 5, z + 8, JourneyBlocks.dungeonLamp);
        WorldGenAPI.addBlock(w, x + 13, y + height - 5, z + 8, JourneyBlocks.dungeonLamp);
        w.setBlock(x + 11, y + height - 3, z + 2, JourneyBlocks.hellbotSpawner);
        w.setBlock(x + 11, y + height - 3, z + 7, JourneyBlocks.hellbotSpawner);
        w.setBlock(x + 11, y + height + 1, z + 2, JourneyBlocks.hellbotSpawner);
        w.setBlock(x + 11, y + height + 1, z + 7, JourneyBlocks.hellbotSpawner);
        for (i = 0; i < 4; ++i) {
            if (lor == 0) {
                w.setBlock(x + 5 - i, y + i + 1 + height, z + 1, JourneyBlocks.dungeonBrickStairs, 1, 2);
                w.setBlock(x + 5 - i, y + i + 1 + height, z + 2, JourneyBlocks.dungeonBrickStairs, 1, 2);
                continue;
            }
            w.setBlock(x + 5 - i, y + i + 1 + height - 4, z + 7, JourneyBlocks.dungeonBrickStairs, 1, 2);
            w.setBlock(x + 5 - i, y + i + 1 + height - 4, z + 8, JourneyBlocks.dungeonBrickStairs, 1, 2);
        }
        w.setBlock(x + 2, y + 1 + height - 1, z + 7, JourneyBlocks.dungeonBrickStairs, 1, 2);
        w.setBlock(x + 2, y + 1 + height - 1, z + 8, JourneyBlocks.dungeonBrickStairs, 1, 2);
        for (x1 = 0; x1 < 15; ++x1) {
            block2: for (y1 = 0; y1 < 200; ++y1) {
                for (z1 = 0; z1 < 15; ++z1) {
                    if (w.getBlock(x + x1, y + y1, z + z1) != JourneyBlocks.dungeonBrickStairs || r.nextInt(15) != 0) continue;
                    w.setBlock(x + x1, y + y1, z + z1, JourneyBlocks.dungeonChiseledBrickStairs, 1, 2);
                    continue block2;
                }
            }
        }
        for (x1 = 0; x1 < 15; ++x1) {
            block5: for (y1 = 0; y1 < 200; ++y1) {
                for (z1 = 0; z1 < 15; ++z1) {
                    if (w.getBlock(x + x1, y + y1, z + z1) != JourneyBlocks.dungeonBrickStairs || r.nextInt(10) != 0) continue;
                    w.setBlock(x + x1, y + y1, z + z1, JourneyBlocks.dungeonBrickCarvedStairs, 1, 2);
                    continue block5;
                }
            }
        }
        for (x1 = 0; x1 < 15; ++x1) {
            block8: for (y1 = 0; y1 < 200; ++y1) {
                for (z1 = 0; z1 < 15; ++z1) {
                    if (w.getBlock(x + x1, y + y1, z + z1) != JourneyBlocks.dungeonBrickStairs || r.nextInt(10) != 0) continue;
                    w.setBlock(x + x1, y + y1, z + z1, JourneyBlocks.dungeonCrackedBrickStairs, 1, 2);
                    continue block8;
                }
            }
        }
        for (i = 0; i < 50; ++i) {
            for (int j = 0; j < 200; ++j) {
                for (int k = 0; k < 50; ++k) {
                    if (w.getBlock(i, j, k) != JourneyBlocks.dungeonBrickStairs || r.nextInt(10) != 0) continue;
                    w.setBlock(i, j, k, JourneyBlocks.dungeonCrackedBrickStairs, 1, 2);
                }
            }
        }
    }
}

