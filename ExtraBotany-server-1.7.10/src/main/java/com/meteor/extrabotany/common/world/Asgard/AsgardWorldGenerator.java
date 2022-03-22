/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.world.Asgard;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class AsgardWorldGenerator
implements IWorldGenerator {
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.dimensionId == 150) {
            this.generateSurface(world, rand, chunkX, chunkZ);
        }
    }

    private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
        int x = chunkX * 16;
        int z = chunkZ * 16;
        if (chunkX > -5 && chunkX <= 5 && chunkZ > -30 && chunkZ <= 30) {
            this.installMain(world, x, z);
        } else {
            this.installAnother(world, x, z);
        }
        world.getChunkFromChunkCoords(chunkX, chunkZ).setChunkModified();
    }

    private void installMain(World w, int x, int z) {
        for (int ix = 0; ix < 16; ++ix) {
            for (int iz = 0; iz < 16; ++iz) {
                for (int iy = 0; iy < 256; ++iy) {
                    w.setBlock(x + ix, iy, z + iz, iy <= 40 ? Blocks.bedrock : Blocks.air);
                }
            }
        }
    }

    private void installAnother(World w, int x, int z) {
        for (int ix = 0; ix < 16; ++ix) {
            for (int iz = 0; iz < 16; ++iz) {
                for (int iy = 0; iy < 256; ++iy) {
                    w.setBlock(x + ix, iy, z + iz, iy <= 2 ? Blocks.bedrock : (iy < 20 ? Blocks.air : (iy < 40 ? Blocks.water : Blocks.air)));
                }
            }
        }
    }

    private Block genOre() {
        return Blocks.stone;
    }
}

