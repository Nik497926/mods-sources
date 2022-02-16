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
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenConeDown
extends WorldGenerator {
    private Block block;

    public WorldGenConeDown(Block b) {
        this.block = b;
    }

    public boolean generate(World par1, Random par2, int par3, int par4, int par5) {
        int var2 = par2.nextInt(3);
        for (int i = 0; i < var2; ++i) {
            this.placeBlockCircle(par1, par3, par4 - i, par5, var2 - i);
        }
        return true;
    }

    void placeBlockCircle(World par1World, int x, int y, int z, int radius) {
        for (int i = 0; i < radius * 4; ++i) {
            float j = 0.0f;
            while ((double)j < Math.PI * 2 * (double)i) {
                par1World.setBlock((int)Math.floor((double)x + Math.sin(j) * (double)i), y, (int)Math.floor((double)z + Math.cos(j) * (double)i), this.block);
                par1World.setBlock((int)Math.floor((double)x + Math.sin(j) * (double)i), y - 1, (int)Math.floor((double)z + Math.cos(j) * (double)i), VetheaBlocks.dreamGrass);
                j = (float)((double)j + 0.5);
            }
        }
    }
}

