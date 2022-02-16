/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.vethea;

import java.util.Random;
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenVetheanFlower
extends WorldGenerator {
    private Block flower;

    public WorldGenVetheanFlower(Block f) {
        this.flower = f;
    }

    public boolean generate(World w, Random r, int x, int y, int z) {
        if (w.getBlock(x, y - 1, z) == VetheaBlocks.dreamGrass) {
            for (int l = 0; l < 30; ++l) {
                int k;
                int j;
                int i = x + r.nextInt(8) - r.nextInt(8);
                if (!w.isAirBlock(i, j = y + r.nextInt(4) - r.nextInt(4), k = z + r.nextInt(8) - r.nextInt(8)) || j >= 200 || !this.flower.canBlockStay(w, i, j, k)) continue;
                w.setBlock(i, j, k, this.flower);
            }
        }
        return true;
    }
}

