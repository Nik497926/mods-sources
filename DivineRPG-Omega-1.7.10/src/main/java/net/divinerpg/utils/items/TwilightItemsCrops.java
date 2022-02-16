/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 */
package net.divinerpg.utils.items;

import net.divinerpg.items.base.ItemModFood;
import net.divinerpg.items.twilight.ItemTwilightSeeds;
import net.divinerpg.items.vanilla.ItemSkyFlower;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class TwilightItemsCrops {
    public static Item moonbulb = new ItemModFood(1, 1.0f, false, "moonbulb");
    public static Item moonbulbSeeds = new ItemTwilightSeeds("moonbulbSeeds", TwilightBlocks.moonbulb, (Block)Blocks.grass);
    public static Item purpleGlowbone = new ItemModFood(1, 1.0f, false, "purpleGlowbone");
    public static Item purpleGlowboneSeeds = new ItemTwilightSeeds("purpleGlowboneSeeds", TwilightBlocks.purpleGlowbone, (Block)Blocks.grass);
    public static Item pinkGlowbone = new ItemModFood(1, 1.0f, false, "pinkGlowbone");
    public static Item pinkGlowboneSeeds = new ItemTwilightSeeds("pinkGlowboneSeeds", TwilightBlocks.pinkGlowbone, (Block)Blocks.grass);
    public static Item skyFlower = new ItemSkyFlower();
    public static Item skyPlantSeeds = new ItemTwilightSeeds("skyPlantSeeds", TwilightBlocks.skyPlant, (Block)Blocks.grass);

    public static void init() {
    }
}

