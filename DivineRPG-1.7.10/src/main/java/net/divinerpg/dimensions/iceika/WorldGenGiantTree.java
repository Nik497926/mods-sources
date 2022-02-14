/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.iceika;

import java.util.Random;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGiantTree
extends WorldGenerator {
    private void setBlockandMetadataIfChunkExists(World world, int x, int y, int z, int blockId, int metadata) {
        if (world.getChunkProvider().chunkExists(x >> 4, z >> 4)) {
            this.generate(world, world.rand, x, y, z);
        }
    }

    public WorldGenGiantTree(boolean doNotify) {
        super(doNotify);
    }

    public boolean generate(World world, Random rand, int i, int j, int k) {
        int height = 20 + rand.nextInt(10);
        int leafStart = 1 + rand.nextInt(12);
        int leafHeight = height - leafStart;
        int l = 2 + rand.nextInt(9);
        if (j < 1 || j + height + 1 > 256 || world.getBlock(i, j - 1, k) != IceikaBlocks.frozenGrass || j >= 256 - height - 1) {
            return false;
        }
        for (int y = j; y <= j + 1 + height; ++y) {
            if (y < 0 && y >= 256) {
                return false;
            }
            int k1 = 1;
            k1 = y - j < leafStart ? 0 : l;
            for (int x = i - k1; x <= i + k1; ++x) {
                for (int z = k - k1; z <= k + k1; ++z) {
                    if (!world.getChunkProvider().chunkExists(x >> 4, z >> 4)) {
                        return false;
                    }
                    Block b = world.getBlock(x, y, z);
                    if (world.getBlock(x, y, z) == Blocks.air || world.getBlock(x, y, z).isLeaves((IBlockAccess)world, x, y, z)) continue;
                    return false;
                }
            }
        }
        world.setBlock(i, j - 1, k, IceikaBlocks.frozenGrass);
        world.setBlock(i - 1, j - 1, k, IceikaBlocks.frozenGrass);
        world.setBlock(i, j - 1, k - 1, IceikaBlocks.frozenGrass);
        world.setBlock(i - 1, j - 1, k - 1, IceikaBlocks.frozenGrass);
        int l1 = rand.nextInt(2);
        int j2 = 1;
        boolean flag1 = false;
        for (int i3 = 0; i3 <= leafHeight; ++i3) {
            int k3 = j + height - i3;
            for (int i4 = i - l1; i4 <= i + l1; ++i4) {
                int k4 = i4 - i;
                for (int l4 = k - l1; l4 <= k + l1; ++l4) {
                    int i5 = l4 - k;
                    if (Math.abs(k4) == l1 && Math.abs(i5) == l1 && l1 > 0 || world.getBlock(i4, k3, l4) != Blocks.air && !world.getBlock(i4, k3, l4).canBeReplacedByLeaves((IBlockAccess)world, i4, k3, l4)) continue;
                    world.setBlock(i4, k3, l4, IceikaBlocks.brittleLeaves);
                    world.setBlock(i4 - 1, k3, l4, IceikaBlocks.brittleLeaves);
                    world.setBlock(i4, k3, l4 - 1, IceikaBlocks.brittleLeaves);
                    world.setBlock(i4 - 1, k3, l4 - 1, IceikaBlocks.brittleLeaves);
                }
            }
            if (l1 >= j2) {
                l1 = flag1 ? 1 : 0;
                flag1 = true;
                if (++j2 <= l) continue;
                j2 = l;
                continue;
            }
            ++l1;
        }
        int j3 = rand.nextInt(3);
        for (int l3 = 0; l3 < height - j3; ++l3) {
            if (world.getBlock(i, j + l3, k) != Blocks.air && !world.getBlock(i, j + l3, k).isLeaves((IBlockAccess)world, i, j + l3, k)) continue;
            world.setBlock(i, j + l3, k, IceikaBlocks.frozenWood);
            world.setBlock(i - 1, j + l3, k, IceikaBlocks.frozenWood);
            world.setBlock(i, j + l3, k - 1, IceikaBlocks.frozenWood);
            world.setBlock(i - 1, j + l3, k - 1, IceikaBlocks.frozenWood);
        }
        return true;
    }
}

