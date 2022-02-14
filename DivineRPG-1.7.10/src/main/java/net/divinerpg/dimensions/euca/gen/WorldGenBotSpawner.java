/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.euca.gen;

import java.util.Random;
import net.divinerpg.dimensions.base.WorldGenAPI;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBotSpawner
extends WorldGenerator {
    public boolean locationIsValidSpawn(World w, int i, int j, int k) {
        for (int p = 0; p < 11; ++p) {
            for (int l = 0; l < 11; ++l) {
                if (w.getBlock(i + p, j, k + l) != Blocks.air) continue;
            }
        }
        return false;
    }

    public boolean generate(World w, Random r, int x, int y, int z) {
        if (!w.isRemote) {
            if (this.locationIsValidSpawn(w, x, --y, z)) {
                return true;
            }
            int height = r.nextInt(10) + 10;
            WorldGenAPI.addRectangle(1, 1, 1, w, x + 3, y + height + 2, z + 1, JourneyBlocks.goldbotSpawner);
            WorldGenAPI.addRectangle(1, 1, 1, w, x + 3, y + height + 3, z + 1, JourneyBlocks.silverbotSpawner);
            WorldGenAPI.addRectangle(1, 1, height + 1, w, x + 3, y + 1, z + 1, JourneyBlocks.eucaBricks);
            WorldGenAPI.addRectangle(3, 1, height, w, x + 2, y + 1, z + 1, JourneyBlocks.eucaBricks);
            WorldGenAPI.addRectangle(1, 3, height, w, x + 3, y + 1, z, JourneyBlocks.eucaBricks);
            WorldGenAPI.addRectangle(7, 3, 1, w, x, y + height, z, JourneyBlocks.eucatile);
            WorldGenAPI.addRectangle(3, 7, 1, w, x + 2, y + height, z - 2, JourneyBlocks.eucatile);
            WorldGenAPI.addRectangle(5, 5, 1, w, x + 1, y + height, z - 1, JourneyBlocks.eucatile);
        }
        return false;
    }
}

