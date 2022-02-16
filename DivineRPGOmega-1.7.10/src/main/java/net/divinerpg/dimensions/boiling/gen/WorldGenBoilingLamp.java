/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.boiling.gen;

import java.util.Random;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBoilingLamp
extends WorldGenerator {
    public boolean locationIsValidSpawn(World w, int x, int y, int z) {
        return w.getBlock(x, y, z) == Blocks.air && w.getBlock(x, y - 1, z) == JourneyBlocks.hotBlock && w.getBlock(x + 2, y - 1, z + 2) == JourneyBlocks.hotBlock && w.getBlock(x + 2, y, z + 2) == Blocks.air && w.getBlock(x + 1, y + 2, z + 1) == Blocks.air && w.getBlock(x + 1, y - 1, z + 1) == JourneyBlocks.hotBlock;
    }

    public boolean generate(World world, Random r, int x, int y, int z) {
        if (this.locationIsValidSpawn(world, x, y, z)) {
            int i = x;
            int j = y;
            int k = z;
            world.setBlock(i + 0, j + 0, k + 1, JourneyBlocks.sizzlingPost);
            world.setBlock(i + 1, j + 0, k + 0, JourneyBlocks.sizzlingPost);
            world.setBlock(i + 1, j + 0, k + 1, JourneyBlocks.brisonStone);
            world.setBlock(i + 1, j + 0, k + 2, JourneyBlocks.sizzlingPost);
            world.setBlock(i + 1, j + 1, k + 1, JourneyBlocks.sizzlingPost);
            world.setBlock(i + 1, j + 2, k + 1, JourneyBlocks.sizzlingPost);
            world.setBlock(i + 1, j + 3, k + 1, JourneyBlocks.boilingLamp);
            world.setBlock(i + 2, j + 0, k + 1, JourneyBlocks.sizzlingPost);
            return true;
        }
        return false;
    }
}

