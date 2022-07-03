/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.recipe;

import com.meteor.extrabotany.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.lib.LibOreDict;

public class ModRuneRecipe {
    public static RecipeRuneAltar castsoulsteelRecipe;
    public static RecipeRuneAltar blockcompressterraRecipe;
    public static RecipeRuneAltar blocksoulsteel;
    public static RecipeRuneAltar blockelfirium;

    public static void init() {
        new ItemStack(ModItems.material, 1, 11);
        String runeWater = LibOreDict.RUNE[0];
        String runeFire = LibOreDict.RUNE[1];
        String runeEarth = LibOreDict.RUNE[2];
        String runeAir = LibOreDict.RUNE[3];
        String runeSpring = LibOreDict.RUNE[4];
        String runeSummer = LibOreDict.RUNE[5];
        String runeAutumn = LibOreDict.RUNE[6];
        String runeWinter = LibOreDict.RUNE[7];
        String runeMana = LibOreDict.RUNE[8];
        String runeLust = LibOreDict.RUNE[9];
        String runeGluttony = LibOreDict.RUNE[10];
        String runeGreed = LibOreDict.RUNE[11];
        String runeSloth = LibOreDict.RUNE[12];
        String runeWrath = LibOreDict.RUNE[13];
        String runeEnvy = LibOreDict.RUNE[14];
        String runePride = LibOreDict.RUNE[15];
        castsoulsteelRecipe = BotaniaAPI.registerRuneAltarRecipe((ItemStack)new ItemStack(ModItems.castsoulsteel, 1, 0), (int)3000000, (Object[])new Object[]{"FragmentResonance", "EssenceGaia", "EssenceGaia", "gaiaIngot"});
        blockcompressterraRecipe = BotaniaAPI.registerRuneAltarRecipe((ItemStack)new ItemStack(Item.getItemFromBlock((Block)com.meteor.extrabotany.common.block.ModBlocks.compressedTerra), 1, 0), (int)500000, (Object[])new Object[]{new ItemStack(ModItems.material, 1, 12), new ItemStack(ModBlocks.storage, 1, 1), new ItemStack(ModBlocks.storage, 1, 1), new ItemStack(ModItems.material, 1, 9), new ItemStack(ModBlocks.storage, 1, 1), new ItemStack(ModBlocks.storage, 1, 1)});
        blocksoulsteel = BotaniaAPI.registerRuneAltarRecipe((ItemStack)new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.blocksoulsteel, 1, 0), (int)500000, (Object[])new Object[]{new ItemStack(ModItems.material, 1, 11), new ItemStack(ModItems.material, 1, 11), new ItemStack(ModItems.material, 1, 11), new ItemStack(ModItems.material, 1, 11), new ItemStack(ModItems.material, 1, 11), new ItemStack(ModItems.material, 1, 11), new ItemStack(ModItems.material, 1, 11), new ItemStack(ModItems.material, 1, 11), new ItemStack(ModItems.material, 1, 9)});
        blockelfirium = BotaniaAPI.registerRuneAltarRecipe((ItemStack)new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.blockelfirium, 1, 0), (int)1000000, (Object[])new Object[]{new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.blocksoulsteel), new ItemStack(ModItems.material, 1, 11), new ItemStack(ModItems.material, 1, 11), new ItemStack(ModItems.material, 1, 11), new ItemStack(ModItems.material, 1, 11)});
    }
}

