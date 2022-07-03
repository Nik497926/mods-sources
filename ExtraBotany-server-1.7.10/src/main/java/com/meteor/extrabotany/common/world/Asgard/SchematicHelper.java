/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.world.Asgard;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SchematicHelper {
    public static final SchematicHelper instance = new SchematicHelper();
    public static rotations signRotations = new rotations(2, 5, 3, 4);
    public static rotations stairsRotations = new rotations(2, 1, 3, 0);
    public static rotations chestRotations = new rotations(4, 2, 5, 3);
    public static rotations pumpkinRotations = new rotations(2, 3, 0, 1);
    public static rotations torchRotations = new rotations(4, 1, 3, 2);
    public static rotations doorRotations = new rotations(1, 2, 3, 0);

    public Schematic get(String schemname) {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("assets/extrabotania/schem/" + schemname);
            NBTTagCompound nbtdata = CompressedStreamTools.readCompressed((InputStream)is);
            byte[] localBlocks = nbtdata.getByteArray("Blocks");
            byte[] localMetadata = nbtdata.getByteArray("Data");
            boolean extra = false;
            byte[] extraBlocks = null;
            byte[] extraBlocksNibble = null;
            if (nbtdata.hasKey("AddBlocks")) {
                extra = true;
                extraBlocksNibble = nbtdata.getByteArray("AddBlocks");
                extraBlocks = new byte[extraBlocksNibble.length * 2];
                for (int i = 0; i < extraBlocksNibble.length; ++i) {
                    extraBlocks[i * 2 + 0] = (byte)(extraBlocksNibble[i] >> 4 & 0xF);
                    extraBlocks[i * 2 + 1] = (byte)(extraBlocksNibble[i] & 0xF);
                }
            } else if (nbtdata.hasKey("Add")) {
                extra = true;
                extraBlocks = nbtdata.getByteArray("Add");
            }
            int width = nbtdata.getShort("Width");
            int height = nbtdata.getShort("Height");
            int length = nbtdata.getShort("Length");
            int[] trueIDs = new int[localBlocks.length];
            for (int x = 0; x < width; ++x) {
                for (int y = 0; y < height; ++y) {
                    for (int z = 0; z < length; ++z) {
                        int index = x + (y * length + z) * width;
                        trueIDs[index] = localBlocks[index] & 0xFF | (extra ? (extraBlocks[index] & 0xFF) << 8 : 0);
                    }
                }
            }
            NBTTagCompound trueBlock = nbtdata.getCompoundTag("SchematicaMapping");
            Set<String> s = trueBlock.func_150296_c();
            HashMap<Integer, String> names = new HashMap<Integer, String>();
            for (String item : s) {
                names.put(trueBlock.getInteger(item), item);
            }
            NBTTagList tileentities = nbtdata.getTagList("TileEntities", 10);
            is.close();
            return new Schematic(tileentities, (short)width, (short)height, (short)length, localBlocks, localMetadata, names, trueIDs);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static int[] blockCoordsRotation(int blockX, int blockZ, int rotation) {
        int[] coords = new int[]{blockX, blockZ};
        switch (rotation) {
            case 1: {
                coords[0] = -blockZ;
                coords[1] = blockX;
                break;
            }
            case 2: {
                coords[0] = -blockX;
                coords[1] = -blockZ;
                break;
            }
            case 3: {
                coords[0] = blockZ;
                coords[1] = -blockX;
            }
        }
        return coords;
    }

    public static int rotateMeta(int blockId, int meta, int rotation) {
        if (rotation > 0) {
            if (Block.getIdFromBlock((Block)Blocks.torch) == blockId || Block.getIdFromBlock((Block)Blocks.redstone_torch) == blockId) {
                return torchRotations.getMeta((torchRotations.getSide(meta) + rotation) % 4);
            }
            if (meta < 4 && Block.getBlockById((int)blockId) instanceof BlockDoor) {
                return doorRotations.getMeta((doorRotations.getSide(meta) + rotation) % 4);
            }
            if (Block.getIdFromBlock((Block)Blocks.wall_sign) == blockId) {
                return signRotations.getMeta((signRotations.getSide(meta) + rotation) % 4);
            }
            if (Block.getIdFromBlock((Block)Blocks.ladder) == blockId) {
                return signRotations.getMeta((signRotations.getSide(meta) + rotation) % 4);
            }
            if (Block.getIdFromBlock((Block)Blocks.chest) == blockId || Block.getIdFromBlock((Block)Blocks.ender_chest) == blockId) {
                return chestRotations.getMeta((chestRotations.getSide(meta) + rotation) % 4);
            }
            if (Block.getIdFromBlock((Block)Blocks.furnace) == blockId || Block.getIdFromBlock((Block)Blocks.lit_furnace) == blockId) {
                return signRotations.getMeta((signRotations.getSide(meta) + rotation) % 4);
            }
            if (Block.getIdFromBlock((Block)Blocks.pumpkin) == blockId || Block.getIdFromBlock((Block)Blocks.lit_pumpkin) == blockId) {
                return pumpkinRotations.getMeta((pumpkinRotations.getSide(meta) + rotation) % 4);
            }
            if (Block.getBlockById((int)blockId) instanceof BlockStairs) {
                return stairsRotations.getMeta((stairsRotations.getSide(meta) + rotation) % 4);
            }
        }
        return meta;
    }

    public static class rotations {
        public int[] rs;

        public rotations(int ... r) {
            this.rs = r;
        }

        public int getSide(int meta) {
            for (int i = 0; i < this.rs.length; ++i) {
                if (this.rs[i] != meta) continue;
                return i;
            }
            return -1;
        }

        public int getMeta(int side) {
            return this.rs[side];
        }
    }

    public static class Schematic {
        public NBTTagList tileentities;
        public short width;
        public short height;
        public short length;
        public byte[] blocks;
        public byte[] data;
        public int[] trueIDs;
        public HashMap<Integer, String> nameBlocks;

        public Schematic(NBTTagList tileentities, short width, short height, short length, byte[] blocks, byte[] data, HashMap<Integer, String> names, int[] trueID) {
            this.tileentities = tileentities;
            this.width = width;
            this.height = height;
            this.length = length;
            this.blocks = blocks;
            this.data = data;
            this.nameBlocks = names;
            this.trueIDs = trueID;
        }
    }
}

