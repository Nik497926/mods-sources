/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.tileentity.TileEntitySign
 *  net.minecraft.world.World
 *  omegalevels.ItemGem$EnumType
 *  omegalevels.OmegaLevels
 */
package net.divinerpg.dimensions.base;

import java.util.Random;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.divinerpg.utils.items.ArcanaItems;
import net.divinerpg.utils.items.IceikaItems;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.world.World;
import omegalevels.ItemGem;
import omegalevels.OmegaLevels;

public class WorldGenAPI {
    Random rand = new Random();

    public static void addCube(int size, World w, int x, int y, int z, Block b) {
        for (int x1 = 0; x1 < size; ++x1) {
            for (int z1 = 0; z1 < size; ++z1) {
                for (int y1 = 0; y1 < size; ++y1) {
                    w.setBlock(x + x1, y + y1 + 1, z + z1, b);
                }
            }
        }
    }

    public static void addBlock(World w, int x, int y, int z, Block b) {
        WorldGenAPI.addCube(1, w, x, y, z, b);
    }

    public static void addHollowCube(int size, World w, int x, int y, int z, Block b) {
        int y1;
        int z1;
        int x1;
        for (x1 = 0; x1 < size; ++x1) {
            for (z1 = 0; z1 < size; ++z1) {
                for (y1 = 0; y1 < size; ++y1) {
                    w.setBlock(x + x1, y + y1 + 1, z + z1, b);
                }
            }
        }
        for (x1 = 0; x1 < size - 2; ++x1) {
            for (z1 = 0; z1 < size - 2; ++z1) {
                for (y1 = 0; y1 < size - 2; ++y1) {
                    w.setBlock(x + x1 + 1, y + y1 + 2, z + z1 + 1, Blocks.air);
                }
            }
        }
    }

    public static void addRectangle(int east, int south, int height, World w, int x, int y, int z, Block b) {
        for (int x1 = 0; x1 < east; ++x1) {
            for (int z1 = 0; z1 < south; ++z1) {
                for (int y1 = 0; y1 < height; ++y1) {
                    w.setBlock(x + x1, y + y1, z + z1, b);
                }
            }
        }
    }

    public static void addRectangleAdequately(int east, int south, int height, World w, int x, int y, int z, Block b) {
        for (int x1 = 0; x1 < east; ++x1) {
            for (int z1 = 0; z1 < south; ++z1) {
                for (int y1 = 0; y1 < height; ++y1) {
                    if (w.getBlock(x + x1, y + y1, z + z1) != Blocks.air) continue;
                    w.setBlock(x + x1, y + y1, z + z1, b, 0, 2);
                }
            }
        }
    }

    public static void addRectangleWithMetadata(int east, int south, int height, World w, int x, int y, int z, Block b, int meta) {
        for (int x1 = 0; x1 < east; ++x1) {
            for (int z1 = 0; z1 < south; ++z1) {
                for (int y1 = 0; y1 < height; ++y1) {
                    w.setBlock(x + x1, y + y1, z + z1, b, meta, 2);
                }
            }
        }
    }

    public static void addCornerlessRectangle(int east, int south, int height, World w, int x, int y, int z, Block b) {
        WorldGenAPI.addRectangle(east, south, height, w, x, y, z, b);
        WorldGenAPI.addRectangle(1, 1, height, w, x, y, z, Blocks.air);
        WorldGenAPI.addRectangle(1, 1, height, w, x + east - 1, y, z, Blocks.air);
        WorldGenAPI.addRectangle(1, 1, height, w, x, y, z + south - 1, Blocks.air);
        WorldGenAPI.addRectangle(1, 1, height, w, x + east - 1, y, z + south - 1, Blocks.air);
    }

    public static void placeIceikaChest(World w, int x, int y, int z, int meta) {
        Random r = new Random();
        w.setBlock(x, y, z, IceikaBlocks.decorativeFrostedChest, meta, 2);
        TileEntityChest chest = (TileEntityChest)w.getTileEntity(x, y, z);
        if (chest != null && !w.isRemote) {
            for (int i = 0; i < r.nextInt(4) + 1; ++i) {
                chest.setInventorySlotContents(r.nextInt(26) + 1, new ItemStack(IceikaItems.peppermints));
            }
        }
    }

    public static void placeBoilChest(World w, int x, int y, int z, int meta) {
        Random r = new Random();
        w.setBlock(x, y, z, (Block)Blocks.chest, meta, 1);
        TileEntityChest chest = (TileEntityChest)w.getTileEntity(x, y, z);
        if (chest != null && !w.isRemote) {
            if (r.nextInt(100) <= 75) {
                chest.setInventorySlotContents(0, new ItemStack(VanillaItemsOther.repairEssence, 6));
                chest.setInventorySlotContents(1, new ItemStack((Item)OmegaLevels.gem, 1, ItemGem.EnumType.RED.ordinal()));
                chest.setInventorySlotContents(2, new ItemStack(JourneyItemsOther.boilChunk, 4));
            } else {
                chest.setInventorySlotContents(0, new ItemStack(ArcanaItems.arcanium, 6));
                chest.setInventorySlotContents(1, new ItemStack(VanillaItemsOther.repairEssence, 6));
                chest.setInventorySlotContents(2, new ItemStack(VanillaItemsOther.boilKey, 1));
                chest.setInventorySlotContents(3, new ItemStack((Item)OmegaLevels.gem, 1, ItemGem.EnumType.RED.ordinal()));
                chest.setInventorySlotContents(4, new ItemStack(VanillaItemsOther.enchantChunk, 48));
                chest.setInventorySlotContents(5, new ItemStack(JourneyItemsOther.boilChunk, 4));
            }
        }
    }

    public static void placeEucaChest(World w, int x, int y, int z, int meta) {
        Random r = new Random();
        w.setBlock(x, y, z, (Block)Blocks.chest, meta, 1);
        TileEntityChest chest = (TileEntityChest)w.getTileEntity(x, y, z);
        if (chest != null && !w.isRemote) {
            if (r.nextInt(100) <= 75) {
                chest.setInventorySlotContents(0, new ItemStack(VanillaItemsOther.enchantChunk, 16));
                chest.setInventorySlotContents(1, new ItemStack(VanillaItemsOther.repairEssence, 9));
                chest.setInventorySlotContents(2, new ItemStack(ArcanaItems.arcanium, 6));
                chest.setInventorySlotContents(3, new ItemStack((Item)OmegaLevels.gem, 1, ItemGem.EnumType.RED.ordinal()));
            } else {
                chest.setInventorySlotContents(0, new ItemStack(VanillaItemsOther.enchantChunk, 32));
                chest.setInventorySlotContents(1, new ItemStack(VanillaItemsOther.repairEssence, 16));
                chest.setInventorySlotContents(2, new ItemStack(ArcanaItems.arcanium, 6));
                chest.setInventorySlotContents(3, new ItemStack((Item)OmegaLevels.gem, 2, ItemGem.EnumType.RED.ordinal()));
            }
        }
    }

    public static void placeSignWithText(World w, int x, int y, int z, int meta, String[] text, boolean standing) {
        if (standing) {
            w.setBlock(x, y, z, Blocks.standing_sign, meta, 2);
        } else {
            w.setBlock(x, y, z, Blocks.wall_sign, meta, 2);
        }
        TileEntitySign sign = (TileEntitySign)w.getTileEntity(x, y, z);
        if (sign != null && !w.isRemote) {
            sign.signText = text;
        }
    }
}

