/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.boiling.gen;

import java.util.Random;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBoilTrees
extends WorldGenerator {
    public boolean generate(World world, Random r, int x, int y, int z) {
        Block log = JourneyBlocks.boilingLog;
        Block leaves = JourneyBlocks.burningLeaves;
        int i = x - 3;
        int j = y - 1;
        int k = z - 3;
        int cx = i + 2;
        int cy = j + 3;
        int cz = k + 2;
        int t = world.rand.nextInt(2);
        if (world.getBlock(i + 2, j - 1, k + 2) == JourneyBlocks.hotBlock && world.getBlock(i + 2, j, k + 2) == Blocks.air && world.getBlock(cx, cy, cz) == Blocks.air && world.getBlock(cx - 2, cy, cz) == Blocks.air && world.getBlock(cx, cy, cz + 2) == Blocks.air && world.getBlock(cx, cy, cz - 2) == Blocks.air && world.getBlock(cx + 2, cy, cz) == Blocks.air) {
            switch (t) {
                case 0: {
                    world.setBlock(i + 0, j + 2, k + 2, leaves);
                    world.setBlock(i + 0, j + 3, k + 2, leaves);
                    world.setBlock(i + 1, j + 2, k + 2, leaves);
                    world.setBlock(i + 2, j + 0, k + 2, log);
                    world.setBlock(i + 2, j + 1, k + 2, log);
                    world.setBlock(i + 2, j + 2, k + 0, leaves);
                    world.setBlock(i + 2, j + 2, k + 1, leaves);
                    world.setBlock(i + 2, j + 2, k + 2, log);
                    world.setBlock(i + 2, j + 2, k + 3, leaves);
                    world.setBlock(i + 2, j + 2, k + 4, leaves);
                    world.setBlock(i + 2, j + 3, k + 0, leaves);
                    world.setBlock(i + 2, j + 3, k + 2, leaves);
                    world.setBlock(i + 2, j + 3, k + 4, leaves);
                    world.setBlock(i + 3, j + 2, k + 2, leaves);
                    world.setBlock(i + 4, j + 2, k + 2, leaves);
                    world.setBlock(i + 4, j + 3, k + 2, leaves);
                    break;
                }
                case 1: {
                    world.setBlock(i + 0, j + 5, k + 2, leaves);
                    world.setBlock(i + 0, j + 6, k + 2, leaves);
                    world.setBlock(i + 1, j + 4, k + 2, leaves);
                    world.setBlock(i + 2, j + 0, k + 2, log);
                    world.setBlock(i + 2, j + 1, k + 2, log);
                    world.setBlock(i + 2, j + 2, k + 2, log);
                    world.setBlock(i + 2, j + 3, k + 2, log);
                    world.setBlock(i + 2, j + 4, k + 0, leaves);
                    world.setBlock(i + 2, j + 4, k + 1, leaves);
                    world.setBlock(i + 2, j + 4, k + 2, log);
                    world.setBlock(i + 2, j + 4, k + 3, leaves);
                    world.setBlock(i + 2, j + 4, k + 4, leaves);
                    world.setBlock(i + 2, j + 5, k + 0, leaves);
                    world.setBlock(i + 2, j + 5, k + 2, leaves);
                    world.setBlock(i + 2, j + 5, k + 4, leaves);
                    world.setBlock(i + 2, j + 6, k + 0, leaves);
                    world.setBlock(i + 2, j + 6, k + 4, leaves);
                    world.setBlock(i + 3, j + 4, k + 2, leaves);
                    world.setBlock(i + 4, j + 4, k + 2, leaves);
                    world.setBlock(i + 4, j + 5, k + 2, leaves);
                    world.setBlock(i + 4, j + 6, k + 2, leaves);
                    break;
                }
                case 2: {
                    world.setBlock(i + 0, j + 5, k + 3, leaves);
                    world.setBlock(i + 0, j + 6, k + 3, leaves);
                    world.setBlock(i + 0, j + 7, k + 3, leaves);
                    world.setBlock(i + 0, j + 8, k + 3, leaves);
                    world.setBlock(i + 1, j + 5, k + 3, leaves);
                    world.setBlock(i + 1, j + 8, k + 3, leaves);
                    world.setBlock(i + 2, j + 5, k + 3, leaves);
                    world.setBlock(i + 3, j + 0, k + 3, log);
                    world.setBlock(i + 3, j + 1, k + 3, log);
                    world.setBlock(i + 3, j + 2, k + 3, log);
                    world.setBlock(i + 3, j + 3, k + 3, log);
                    world.setBlock(i + 3, j + 4, k + 3, log);
                    world.setBlock(i + 3, j + 5, k + 0, leaves);
                    world.setBlock(i + 3, j + 5, k + 1, leaves);
                    world.setBlock(i + 3, j + 5, k + 2, leaves);
                    world.setBlock(i + 3, j + 5, k + 3, log);
                    world.setBlock(i + 3, j + 5, k + 4, leaves);
                    world.setBlock(i + 3, j + 5, k + 5, leaves);
                    world.setBlock(i + 3, j + 5, k + 6, leaves);
                    world.setBlock(i + 3, j + 6, k + 0, leaves);
                    world.setBlock(i + 3, j + 6, k + 3, leaves);
                    world.setBlock(i + 3, j + 6, k + 6, leaves);
                    world.setBlock(i + 3, j + 7, k + 0, leaves);
                    world.setBlock(i + 3, j + 7, k + 6, leaves);
                    world.setBlock(i + 3, j + 8, k + 0, leaves);
                    world.setBlock(i + 3, j + 8, k + 1, leaves);
                    world.setBlock(i + 3, j + 8, k + 5, leaves);
                    world.setBlock(i + 3, j + 8, k + 6, leaves);
                    world.setBlock(i + 4, j + 5, k + 3, leaves);
                    world.setBlock(i + 5, j + 5, k + 3, leaves);
                    world.setBlock(i + 5, j + 8, k + 3, leaves);
                    world.setBlock(i + 6, j + 5, k + 3, leaves);
                    world.setBlock(i + 6, j + 6, k + 3, leaves);
                    world.setBlock(i + 6, j + 7, k + 3, leaves);
                    world.setBlock(i + 6, j + 8, k + 3, leaves);
                }
            }
            return true;
        }
        return false;
    }
}

