/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.world.Asgard.Biome;

import com.meteor.extrabotany.common.world.Asgard.SchematicHelper;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class GenerateAsgardHelper {
    public static SchematicHelper.Schematic schematic;
    public static int width;
    public static int height;
    public static HashMap<String, Integer> names;
    public static ArrayList<int[]> coordBlock;
    public static ArrayList<Block> blocksBlock;
    public static ArrayList<int[]> dopBlock;
    public static boolean finish;

    public static void init(SchematicHelper.Schematic shem) {
        schematic = shem;
        width = shem.width;
        height = shem.length;
    }

    public static int getXChunk() {
        return (int)Math.ceil(width / 16);
    }

    public static int getZChunk() {
        return (int)Math.ceil(height / 16);
    }

    public static void generate(int chunkX, int chunkZ, World world) {
    }

    static {
        names = new HashMap();
        coordBlock = new ArrayList();
        blocksBlock = new ArrayList();
        dopBlock = new ArrayList();
        finish = false;
    }
}

