/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.vanilla;

import java.util.Random;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNetherDungeons
extends WorldGenerator {
    private static WeightedRandomChestContent[] loot = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 4, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 1, 10), new WeightedRandomChestContent(JourneyItemsOther.hellcrustIngot, 0, 1, 2, 3), new WeightedRandomChestContent(JourneyItemsOther.flamingSpring, 0, 1, 1, 1)};

    public boolean generate(World world, Random r, int x, int y, int z) {
        int i = x;
        int j = y;
        int k = z;
        if (world.getBlock(i + 8, j - 1, k + 8) == Blocks.netherrack && world.getBlock(i + 8, j - 1, k) == Blocks.netherrack && world.getBlock(i, j - 1, k + 8) == Blocks.netherrack && world.getBlock(i, j - 1, k) == Blocks.netherrack && world.getBlock(i + 8, j + 1, k + 8) == Blocks.air && world.getBlock(i + 8, j + 1, k) == Blocks.air && world.getBlock(i, j + 1, k + 8) == Blocks.air && world.getBlock(i, j + 1, k) == Blocks.air) {
            world.setBlock(i, j, k + 1, Blocks.nether_brick);
            world.setBlock(i, j, k + 7, Blocks.nether_brick);
            world.setBlock(i, j + 1, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i, j + 1, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i, j + 2, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i, j + 2, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i, j + 3, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i, j + 3, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i, j + 4, k + 1, Blocks.nether_brick);
            world.setBlock(i, j + 4, k + 7, Blocks.nether_brick);
            world.setBlock(i + 1, j, k, Blocks.nether_brick);
            world.setBlock(i + 1, j, k + 1, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 1, j, k + 2, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 1, j, k + 3, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 1, j, k + 4, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 1, j, k + 5, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 1, j, k + 6, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 1, j, k + 7, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 1, j, k + 8, Blocks.nether_brick);
            world.setBlock(i + 1, j + 1, k, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 1, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 1, k + 2, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 1, k + 3, Blocks.iron_bars);
            world.setBlock(i + 1, j + 1, k + 4, Blocks.iron_bars);
            world.setBlock(i + 1, j + 1, k + 5, Blocks.iron_bars);
            world.setBlock(i + 1, j + 1, k + 6, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 1, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 1, k + 8, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 2, k, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 2, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 2, k + 2, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 2, k + 3, Blocks.iron_bars);
            world.setBlock(i + 1, j + 2, k + 4, Blocks.iron_bars);
            world.setBlock(i + 1, j + 2, k + 5, Blocks.iron_bars);
            world.setBlock(i + 1, j + 2, k + 6, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 2, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 2, k + 8, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 3, k, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 3, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 3, k + 2, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 3, k + 3, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 3, k + 4, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 3, k + 5, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 3, k + 6, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 3, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 3, k + 8, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 4, k, Blocks.nether_brick);
            world.setBlock(i + 1, j + 4, k + 1, Blocks.iron_bars);
            world.setBlock(i + 1, j + 4, k + 2, Blocks.iron_bars);
            world.setBlock(i + 1, j + 4, k + 3, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 4, k + 4, Blocks.iron_bars);
            world.setBlock(i + 1, j + 4, k + 5, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 1, j + 4, k + 6, Blocks.iron_bars);
            world.setBlock(i + 1, j + 4, k + 7, Blocks.iron_bars);
            world.setBlock(i + 1, j + 4, k + 8, Blocks.nether_brick);
            world.setBlock(i + 2, j, k + 1, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 2, j, k + 2, JourneyBlocks.nethicLamp);
            world.setBlock(i + 2, j, k + 3, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 2, j, k + 4, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 2, j, k + 5, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 2, j, k + 6, JourneyBlocks.nethicLamp);
            world.setBlock(i + 2, j, k + 7, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 2, j + 1, k + 1, Blocks.iron_bars);
            world.setBlock(i + 2, j + 1, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 2, j + 2, k + 1, Blocks.iron_bars);
            world.setBlock(i + 2, j + 2, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 2, j + 3, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 2, j + 3, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 2, j + 4, k + 1, Blocks.iron_bars);
            world.setBlock(i + 2, j + 4, k + 7, Blocks.iron_bars);
            world.setBlock(i + 3, j, k + 1, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 3, j, k + 2, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 3, j, k + 3, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 3, j, k + 4, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 3, j, k + 5, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 3, j, k + 6, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 3, j, k + 7, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 3, j + 1, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 3, j + 1, k + 7, Blocks.iron_bars);
            world.setBlock(i + 3, j + 2, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 3, j + 2, k + 7, Blocks.iron_bars);
            world.setBlock(i + 3, j + 3, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 3, j + 3, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 3, j + 4, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 3, j + 4, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 4, j, k + 1, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 4, j, k + 2, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 4, j, k + 3, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 4, j, k + 4, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 4, j, k + 5, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 4, j, k + 6, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 4, j, k + 7, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 4, j + 1, k + 4, (Block)Blocks.chest, 0, 2);
            TileEntityChest te = (TileEntityChest)world.getTileEntity(i + 4, j + 1, k + 4);
            if (te != null) {
                WeightedRandomChestContent.generateChestContents((Random)r, (WeightedRandomChestContent[])loot, (IInventory)te, (int)12);
            }
            world.setBlockMetadataWithNotify(i + 4, j + 1, k + 4, 2, 2);
            world.setBlock(i + 4, j + 1, k + 7, Blocks.iron_bars);
            world.setBlock(i + 4, j + 2, k + 7, Blocks.iron_bars);
            world.setBlock(i + 4, j + 3, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 4, j + 3, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 4, j + 4, k + 1, Blocks.iron_bars);
            world.setBlock(i + 4, j + 4, k + 7, Blocks.iron_bars);
            world.setBlock(i + 5, j, k + 1, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 5, j, k + 2, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 5, j, k + 3, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 5, j, k + 4, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 5, j, k + 5, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 5, j, k + 6, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 5, j, k + 7, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 5, j + 1, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 5, j + 1, k + 7, Blocks.iron_bars);
            world.setBlock(i + 5, j + 2, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 5, j + 2, k + 7, Blocks.iron_bars);
            world.setBlock(i + 5, j + 3, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 5, j + 3, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 5, j + 4, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 5, j + 4, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 6, j, k + 1, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 6, j, k + 2, JourneyBlocks.nethicLamp);
            world.setBlock(i + 6, j, k + 3, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 6, j, k + 4, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 6, j, k + 5, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 6, j, k + 6, JourneyBlocks.nethicLamp);
            world.setBlock(i + 6, j, k + 7, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 6, j + 1, k + 1, Blocks.iron_bars);
            world.setBlock(i + 6, j + 1, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 6, j + 2, k + 1, Blocks.iron_bars);
            world.setBlock(i + 6, j + 2, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 6, j + 3, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 6, j + 3, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 6, j + 4, k + 1, Blocks.iron_bars);
            world.setBlock(i + 6, j + 4, k + 7, Blocks.iron_bars);
            world.setBlock(i + 7, j, k, Blocks.nether_brick);
            world.setBlock(i + 7, j, k + 1, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 7, j, k + 2, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 7, j, k + 3, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 7, j, k + 4, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 7, j, k + 5, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 7, j, k + 6, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 7, j, k + 7, JourneyBlocks.largeNetherBrick);
            world.setBlock(i + 7, j, k + 8, Blocks.nether_brick);
            world.setBlock(i + 7, j + 1, k, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 1, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 1, k + 2, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 1, k + 3, Blocks.iron_bars);
            world.setBlock(i + 7, j + 1, k + 4, Blocks.iron_bars);
            world.setBlock(i + 7, j + 1, k + 5, Blocks.iron_bars);
            world.setBlock(i + 7, j + 1, k + 6, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 1, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 1, k + 8, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 2, k, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 2, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 2, k + 2, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 2, k + 3, Blocks.iron_bars);
            world.setBlock(i + 7, j + 2, k + 4, Blocks.iron_bars);
            world.setBlock(i + 7, j + 2, k + 5, Blocks.iron_bars);
            world.setBlock(i + 7, j + 2, k + 6, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 2, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 2, k + 8, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 3, k, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 3, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 3, k + 2, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 3, k + 3, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 3, k + 4, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 3, k + 5, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 3, k + 6, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 3, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 3, k + 8, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 4, k, Blocks.nether_brick);
            world.setBlock(i + 7, j + 4, k + 1, Blocks.iron_bars);
            world.setBlock(i + 7, j + 4, k + 2, Blocks.iron_bars);
            world.setBlock(i + 7, j + 4, k + 3, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 4, k + 4, Blocks.iron_bars);
            world.setBlock(i + 7, j + 4, k + 5, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 7, j + 4, k + 6, Blocks.iron_bars);
            world.setBlock(i + 7, j + 4, k + 7, Blocks.iron_bars);
            world.setBlock(i + 7, j + 4, k + 8, Blocks.nether_brick);
            world.setBlock(i + 8, j, k + 1, Blocks.nether_brick);
            world.setBlock(i + 8, j, k + 7, Blocks.nether_brick);
            world.setBlock(i + 8, j + 1, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 8, j + 1, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 8, j + 2, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 8, j + 2, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 8, j + 3, k + 1, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 8, j + 3, k + 7, JourneyBlocks.nethicDungeonBricks);
            world.setBlock(i + 8, j + 4, k + 1, Blocks.nether_brick);
        }
        return true;
    }
}

