/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemDoor
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.vanilla;

import java.util.Random;
import net.divinerpg.dimensions.base.WorldGenAPI;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMageHouse
extends WorldGenerator {
    public boolean generate(World w, Random r, int x, int y, int z) {
        if (w.getBlock(x - 1, y - 1, z - 1) == Blocks.dirt && w.getBlock(x + 9, y - 1, z + 9) == Blocks.dirt && w.getBlock(x + 9, y - 1, z - 1) == Blocks.dirt && w.getBlock(x - 1, y - 1, z + 9) == Blocks.dirt && w.getBlock(x - 1, y + 3, z - 1) == Blocks.air && w.getBlock(x + 9, y + 3, z + 9) == Blocks.air && w.getBlock(x + 9, y + 3, z - 1) == Blocks.air && w.getBlock(x - 1, y + 3, z + 9) == Blocks.air) {
            WorldGenAPI.addRectangle(11, 11, 1, w, x - 1, y + 1, z - 1, (Block)Blocks.grass);
            WorldGenAPI.addRectangle(11, 11, 6, w, x - 1, y + 2, z - 1, Blocks.air);
            WorldGenAPI.addHollowCube(9, w, x, y, z, Blocks.end_stone);
            WorldGenAPI.addRectangle(9, 9, 4, w, x, y + 6, z, Blocks.air);
            WorldGenAPI.addRectangle(9, 9, 1, w, x, y + 5, z, Blocks.end_stone);
            WorldGenAPI.addRectangleWithMetadata(1, 7, 1, w, x, y + 3, z + 1, (Block)Blocks.stained_glass, 10);
            WorldGenAPI.addRectangleWithMetadata(1, 3, 2, w, x, y + 3, z + 3, (Block)Blocks.stained_glass, 10);
            WorldGenAPI.addRectangleWithMetadata(1, 7, 1, w, x + 8, y + 3, z + 1, (Block)Blocks.stained_glass, 10);
            WorldGenAPI.addRectangleWithMetadata(1, 3, 2, w, x + 8, y + 3, z + 3, (Block)Blocks.stained_glass, 10);
            WorldGenAPI.addRectangleWithMetadata(7, 1, 1, w, x + 1, y + 3, z, (Block)Blocks.stained_glass, 10);
            WorldGenAPI.addRectangleWithMetadata(3, 1, 2, w, x + 3, y + 3, z, (Block)Blocks.stained_glass, 10);
            WorldGenAPI.addRectangleWithMetadata(7, 1, 1, w, x + 1, y + 3, z + 8, (Block)Blocks.stained_glass, 10);
            WorldGenAPI.addRectangleWithMetadata(3, 1, 2, w, x + 3, y + 3, z + 8, (Block)Blocks.stained_glass, 10);
            ItemDoor.placeDoorBlock((World)w, (int)x, (int)(y + 2), (int)(z + 4), (int)0, (Block)Blocks.wooden_door);
            ItemDoor.placeDoorBlock((World)w, (int)(x + 8), (int)(y + 2), (int)(z + 4), (int)2, (Block)Blocks.wooden_door);
            ItemDoor.placeDoorBlock((World)w, (int)(x + 4), (int)(y + 2), (int)z, (int)1, (Block)Blocks.wooden_door);
            ItemDoor.placeDoorBlock((World)w, (int)(x + 4), (int)(y + 2), (int)(z + 8), (int)3, (Block)Blocks.wooden_door);
            w.setBlock(x + 1, y + 4, z + 2, Blocks.torch);
            w.setBlock(x + 1, y + 4, z + 6, Blocks.torch);
            w.setBlock(x + 7, y + 4, z + 2, Blocks.torch);
            w.setBlock(x + 7, y + 4, z + 6, Blocks.torch);
            WorldGenAPI.addRectangleWithMetadata(2, 1, 1, w, x + 1, y + 2, z + 1, Blocks.oak_stairs, 3);
            WorldGenAPI.addRectangleWithMetadata(2, 1, 1, w, x + 6, y + 2, z + 1, Blocks.oak_stairs, 3);
            WorldGenAPI.addRectangleWithMetadata(2, 1, 1, w, x + 1, y + 2, z + 7, Blocks.oak_stairs, 2);
            WorldGenAPI.addRectangleWithMetadata(2, 1, 1, w, x + 6, y + 2, z + 7, Blocks.oak_stairs, 2);
            w.setBlock(x + 1, y + 2, z + 2, Blocks.oak_stairs, 1, 2);
            w.setBlock(x + 7, y + 2, z + 2, Blocks.oak_stairs, 0, 2);
            w.setBlock(x + 1, y + 2, z + 6, Blocks.oak_stairs, 1, 2);
            w.setBlock(x + 7, y + 2, z + 6, Blocks.oak_stairs, 0, 2);
            WorldGenAPI.addBlock(w, x + 2, y + 1, z + 2, Blocks.fence);
            WorldGenAPI.addBlock(w, x + 6, y + 1, z + 6, Blocks.fence);
            WorldGenAPI.addBlock(w, x + 6, y + 1, z + 2, Blocks.fence);
            WorldGenAPI.addBlock(w, x + 2, y + 1, z + 6, Blocks.fence);
            WorldGenAPI.addBlock(w, x + 2, y + 2, z + 2, Blocks.wooden_pressure_plate);
            WorldGenAPI.addBlock(w, x + 6, y + 2, z + 6, Blocks.wooden_pressure_plate);
            WorldGenAPI.addBlock(w, x + 6, y + 2, z + 2, Blocks.wooden_pressure_plate);
            WorldGenAPI.addBlock(w, x + 2, y + 2, z + 6, Blocks.wooden_pressure_plate);
            WorldGenAPI.addBlock(w, x, y + 5, z, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x + 2, y + 5, z, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x + 4, y + 5, z, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x + 6, y + 5, z, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x + 8, y + 5, z, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x, y + 5, z + 2, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x, y + 5, z + 4, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x, y + 5, z + 6, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x, y + 5, z + 8, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x + 2, y + 5, z + 8, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x + 4, y + 5, z + 8, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x + 6, y + 5, z + 8, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x + 8, y + 5, z + 8, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x + 8, y + 5, z + 2, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x + 8, y + 5, z + 4, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x + 8, y + 5, z + 6, Blocks.end_stone);
            WorldGenAPI.addBlock(w, x + 8, y + 5, z + 8, Blocks.end_stone);
            return true;
        }
        return false;
    }
}

