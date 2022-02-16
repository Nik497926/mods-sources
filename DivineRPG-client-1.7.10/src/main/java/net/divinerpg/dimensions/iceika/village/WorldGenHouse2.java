/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemDoor
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.iceika.village;

import java.util.Random;
import net.divinerpg.dimensions.base.WorldGenAPI;
import net.divinerpg.entities.iceika.EntityWorkshopMerchant;
import net.divinerpg.entities.iceika.EntityWorkshopTinkerer;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenHouse2
extends WorldGenerator {
    public boolean locationIsValidSpawn(World w, int x, int y, int z) {
        for (int i = 0; i < 12; ++i) {
            for (int l = 0; l < 8; ++l) {
                if (w.getBlock(x + i, y, z + l) == IceikaBlocks.frozenGrass) continue;
                return false;
            }
        }
        return true;
    }

    public boolean generate(World world, Random var2, int x, int y, int z) {
        if (!this.locationIsValidSpawn(world, x, y, z)) {
            return false;
        }
        WorldGenAPI.addRectangle(12, 8, 1, world, x, y, z, IceikaBlocks.coalstone);
        WorldGenAPI.addRectangle(1, 8, 3, world, x, y + 1, z, IceikaBlocks.coalstone);
        WorldGenAPI.addRectangle(3, 1, 3, world, x + 1, y + 1, z, IceikaBlocks.coalstone);
        WorldGenAPI.addRectangle(1, 8, 3, world, x + 11, y + 1, z, IceikaBlocks.coalstone);
        WorldGenAPI.addRectangle(3, 1, 3, world, x + 8, y + 1, z, IceikaBlocks.coalstone);
        WorldGenAPI.addRectangle(1, 3, 3, world, x + 8, y + 1, z + 1, IceikaBlocks.coalstone);
        WorldGenAPI.addRectangle(1, 3, 3, world, x + 3, y + 1, z + 1, IceikaBlocks.coalstone);
        WorldGenAPI.addRectangle(4, 1, 3, world, x + 4, y + 1, z + 3, IceikaBlocks.coalstone);
        WorldGenAPI.addRectangle(2, 1, 2, world, x + 5, y + 1, z + 3, Blocks.air);
        ItemDoor.placeDoorBlock((World)world, (int)(x + 6), (int)(y + 1), (int)(z + 3), (int)1, (Block)IceikaBlocks.steelDoorBlock);
        ItemDoor.placeDoorBlock((World)world, (int)(x + 5), (int)(y + 1), (int)(z + 3), (int)1, (Block)IceikaBlocks.steelDoorBlock);
        WorldGenAPI.addRectangle(12, 1, 3, world, x, y + 1, z + 7, IceikaBlocks.coalstone);
        WorldGenAPI.addRectangle(2, 6, 1, world, x + 1, y + 4, z + 1, IceikaBlocks.snowBricks);
        WorldGenAPI.addRectangle(2, 6, 1, world, x + 9, y + 4, z + 1, IceikaBlocks.snowBricks);
        WorldGenAPI.addRectangle(6, 3, 1, world, x + 3, y + 4, z + 4, IceikaBlocks.snowBricks);
        WorldGenAPI.addRectangle(2, 1, 2, world, x + 1, y + 1, z, IceikaBlocks.snowBricks);
        WorldGenAPI.addRectangle(2, 1, 2, world, x + 9, y + 1, z, IceikaBlocks.snowBricks);
        WorldGenAPI.addRectangle(1, 6, 2, world, x, y + 1, z + 1, IceikaBlocks.snowBricks);
        WorldGenAPI.addRectangle(1, 6, 2, world, x + 11, y + 1, z + 1, IceikaBlocks.snowBricks);
        WorldGenAPI.addRectangle(1, 3, 2, world, x + 8, y + 1, z + 1, IceikaBlocks.snowBricks);
        WorldGenAPI.addRectangle(1, 3, 2, world, x + 3, y + 1, z + 1, IceikaBlocks.snowBricks);
        WorldGenAPI.addRectangle(10, 1, 2, world, x + 1, y + 1, z + 7, IceikaBlocks.snowBricks);
        WorldGenAPI.addRectangle(2, 6, 1, world, x + 1, y, z + 1, IceikaBlocks.workshopCarpet);
        WorldGenAPI.addRectangle(2, 6, 1, world, x + 9, y, z + 1, IceikaBlocks.workshopCarpet);
        WorldGenAPI.addRectangle(6, 3, 1, world, x + 3, y, z + 4, IceikaBlocks.workshopCarpet);
        WorldGenAPI.addRectangle(2, 1, 2, world, x + 1, y + 1, z + 1, IceikaBlocks.workshopBookcase);
        WorldGenAPI.addRectangle(2, 1, 2, world, x + 9, y + 1, z + 1, IceikaBlocks.workshopBookcase);
        WorldGenAPI.addRectangle(2, 1, 1, world, x + 9, y + 3, z + 1, IceikaBlocks.workshopLamp);
        WorldGenAPI.addRectangle(2, 1, 1, world, x + 1, y + 3, z + 1, IceikaBlocks.workshopLamp);
        WorldGenAPI.addRectangle(1, 2, 1, world, x, y + 2, z + 3, IceikaBlocks.frostedGlass);
        WorldGenAPI.addRectangle(1, 2, 1, world, x + 11, y + 2, z + 3, IceikaBlocks.frostedGlass);
        EntityWorkshopMerchant mer = new EntityWorkshopMerchant(world);
        EntityWorkshopTinkerer tink = new EntityWorkshopTinkerer(world);
        if (!world.isRemote) {
            mer.setLocationAndAngles(x + 2, y + 1, z + 4, 0.0f, 0.0f);
            tink.setLocationAndAngles(x + 10, y + 1, z + 4, 0.0f, 0.0f);
            world.spawnEntityInWorld((Entity)mer);
            world.spawnEntityInWorld((Entity)tink);
        }
        return true;
    }
}

