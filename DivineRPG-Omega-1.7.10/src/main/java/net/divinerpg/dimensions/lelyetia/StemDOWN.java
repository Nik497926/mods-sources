/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package net.divinerpg.dimensions.lelyetia;

import java.util.Random;
import net.divinerpg.dimensions.lelyetia.gen.IVoxpondsStructure;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.minecraft.block.Block;

public class StemDOWN
implements IVoxpondsStructure {
    private Random rand = new Random();

    @Override
    public void generate(Block[][][] chunk, int i, int j, int k) {
        for (int y = j; y > j - 15; --y) {
            chunk[i][y][k] = JourneyBlocks.LelyetianStem;
            if (this.rand.nextInt(6) != 3 && y != j - 14) continue;
            chunk[i][y - 1][k] = JourneyBlocks.LelyetianStemCapDown;
            break;
        }
    }
}

