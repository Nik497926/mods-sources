/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.euca.gen;

import java.util.Random;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenEucaWater
extends WorldGenerator {
    private final Block block;
    private final boolean update;

    public WorldGenEucaWater(Block block, boolean update) {
        this.block = block;
        this.update = update;
    }

    public boolean generate(World w, Random r, int x, int y, int z) {
        Block genOn = JourneyBlocks.eucaStone;
        if (w.getBlock(x, y + 1, z) != genOn) {
            return false;
        }
        if (w.getBlock(x, y, z).getMaterial() != Material.air && w.getBlock(x, y, z) != genOn) {
            return false;
        }
        int i = 0;
        if (w.getBlock(x - 1, y, z) == genOn) {
            ++i;
        }
        if (w.getBlock(x + 1, y, z) == genOn) {
            ++i;
        }
        if (w.getBlock(x, y, z - 1) == genOn) {
            ++i;
        }
        if (w.getBlock(x, y, z + 1) == genOn) {
            ++i;
        }
        if (w.getBlock(x, y - 1, z) == genOn) {
            ++i;
        }
        int j = 0;
        if (w.isAirBlock(x - 1, y, z)) {
            ++j;
        }
        if (w.isAirBlock(x + 1, y, z)) {
            ++j;
        }
        if (w.isAirBlock(x, y, z - 1)) {
            ++j;
        }
        if (w.isAirBlock(x, y, z + 1)) {
            ++j;
        }
        if (w.isAirBlock(x, y - 1, z)) {
            ++j;
        }
        if (!this.update && i == 4 && j == 1 || i == 5) {
            w.setBlock(x, y, z, this.block, 0, 2);
        }
        return true;
    }
}

