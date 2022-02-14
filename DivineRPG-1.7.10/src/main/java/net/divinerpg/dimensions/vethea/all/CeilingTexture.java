/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.vethea.all;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class CeilingTexture
extends WorldGenerator {
    private final Block block;
    private int height;

    public CeilingTexture(Block b) {
        this.block = b;
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        int var2 = rand.nextInt(4) + this.height;
        for (int i = 0; i < var2; ++i) {
            this.placeBlockCircle(world, rand, x, y + i, z, var2 - i);
        }
        return true;
    }

    public boolean generate(World world, Random rand, int x, int y, int z, int h) {
        this.height = h;
        return this.generate(world, rand, x, y, z);
    }

    void placeBlockCircle(World world, Random rand, int x, int y, int z, int radius) {
        float i = 0.0f;
        while (i < (float)(radius * 4)) {
            float j = 0.0f;
            while ((double)j < Math.PI * 2 * (double)i) {
                world.setBlock((int)Math.floor((double)x + Math.sin(j) * (double)i), y, (int)Math.floor((double)z + Math.cos(j) * (double)i), this.block);
                j = (float)((double)j + 0.5);
            }
            i = (float)((double)i + 0.5);
        }
    }
}

