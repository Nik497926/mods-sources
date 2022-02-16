/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package net.divinerpg.dimensions.vanilla;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldGenOres {
    private int meta;

    public WorldGenOres() {
        this.meta = 0;
    }

    public WorldGenOres(int m) {
        this.meta = m;
    }

    public void generate(Block b, World w, Random r, Block genIn, int posX, int posY, int posZ, int veinSize) {
        int x = posX;
        int y = posY;
        int z = posZ;
        int[] arr = new int[veinSize];
        block8: for (int i = 0; i < veinSize; ++i) {
            arr[i] = r.nextInt(6);
            for (int j = 0; j < i; ++j) {
                if (arr[i] != arr[j]) continue;
                --i;
                continue block8;
            }
        }
        for (int k = 0; k < veinSize; ++k) {
            if (veinSize <= 6) {
                x = posX;
                y = posY;
                z = posZ;
            }
            if (k != 0) {
                switch (arr[k]) {
                    case 0: {
                        ++x;
                        break;
                    }
                    case 1: {
                        --x;
                        break;
                    }
                    case 2: {
                        ++z;
                        break;
                    }
                    case 3: {
                        --z;
                        break;
                    }
                    case 4: {
                        ++y;
                        break;
                    }
                    case 5: {
                        --y;
                    }
                }
            }
            if (!w.getBlock(x, y, z).isReplaceableOreGen(w, x, y, z, genIn)) continue;
            w.setBlock(x, y, z, b, this.meta, 2);
        }
    }
}

