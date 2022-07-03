/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.generating;

import com.meteor.extrabotany.common.lexicon.LexiconModData;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileGenerating;

public class SubTileBlueenchantress
extends SubTileGenerating {
    private int cd = 0;

    private boolean infiniteIst(Block bl, World w, int x, int y, int z) {
        if (w.getBlock(x + 1, y, z) == Blocks.water && w.getBlock(x + 1, y, z - 1) == Blocks.water && w.getBlock(x, y, z - 1) == Blocks.water) {
            return true;
        }
        if (w.getBlock(x + 1, y, z) == Blocks.water && w.getBlock(x + 1, y, z + 1) == Blocks.water && w.getBlock(x, y, z + 1) == Blocks.water) {
            return true;
        }
        if (w.getBlock(x - 1, y, z) == Blocks.water && w.getBlock(x - 1, y, z - 1) == Blocks.water && w.getBlock(x, y, z - 1) == Blocks.water) {
            return true;
        }
        return w.getBlock(x - 1, y, z) == Blocks.water && w.getBlock(x - 1, y, z + 1) == Blocks.water && w.getBlock(x, y, z + 1) == Blocks.water;
    }

    public int getMaxMana() {
        return 320;
    }

    public int getColor() {
        return 293324;
    }

    public LexiconEntry getEntry() {
        return LexiconModData.blueenchantress;
    }
}

