/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.recipe;

import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibOreDict;

public class ModPetalRecipe {
    public static final String white = LibOreDict.PETAL[0];
    public static final String orange = LibOreDict.PETAL[1];
    public static final String magenta = LibOreDict.PETAL[2];
    public static final String lightBlue = LibOreDict.PETAL[3];
    public static final String yellow = LibOreDict.PETAL[4];
    public static final String lime = LibOreDict.PETAL[5];
    public static final String pink = LibOreDict.PETAL[6];
    public static final String gray = LibOreDict.PETAL[7];
    public static final String lightGray = LibOreDict.PETAL[8];
    public static final String cyan = LibOreDict.PETAL[9];
    public static final String purple = LibOreDict.PETAL[10];
    public static final String blue = LibOreDict.PETAL[11];
    public static final String brown = LibOreDict.PETAL[12];
    public static final String green = LibOreDict.PETAL[13];
    public static final String red = LibOreDict.PETAL[14];
    public static final String black = LibOreDict.PETAL[15];
    public static final String runeWater = LibOreDict.RUNE[0];
    public static final String runeFire = LibOreDict.RUNE[1];
    public static final String runeEarth = LibOreDict.RUNE[2];
    public static final String runeAir = LibOreDict.RUNE[3];
    public static final String runeSpring = LibOreDict.RUNE[4];
    public static final String runeSummer = LibOreDict.RUNE[5];
    public static final String runeAutumn = LibOreDict.RUNE[6];
    public static final String runeWinter = LibOreDict.RUNE[7];
    public static final String runeMana = LibOreDict.RUNE[8];
    public static final String runeLust = LibOreDict.RUNE[9];
    public static final String runeGluttony = LibOreDict.RUNE[10];
    public static final String runeGreed = LibOreDict.RUNE[11];
    public static final String runeSloth = LibOreDict.RUNE[12];
    public static final String runeWrath = LibOreDict.RUNE[13];
    public static final String runeEnvy = LibOreDict.RUNE[14];
    public static final String runePride = LibOreDict.RUNE[15];
    public static final String redstoneRoot = "redstoneRoot";
    public static final String pixieDust = "elvenPixieDust";
    public static final String gaiaSpirit = "eternalLifeEssence";
    public static final String manaPowder = "powderMana";
    public static final String gaiaEssence = "EssenceGaia";
    public static final String blankCard = "CardBlank";
    public static final String quartzGaia = "QuartzGaia";
    public static final String quartzElementium = "QuartzElementium";
    public static final String petalRed = "LycorisRed";
    public static final String petalGreen = "LycorisGreen";
    public static final String petalPurple = "LycorisPurple";
    public static final String shard = "ShardPrismatic";
    public static final String dog = "dog";
    public static RecipePetals blueenchantressRecipe;
    public static RecipePetals candyflowerRecipe;
    public static RecipePetals moonlightlilyRecipe;
    public static RecipePetals sunshinelilyRecipe;
    public static RecipePetals geminiorchidRecipe;
    public static RecipePetals ominivioletRecipe;
    public static RecipePetals pyschobloomRecipe;
    public static RecipePetals stonesiaRecipe;
    public static RecipePetals bellflowerRecipe;
    public static RecipePetals necrofleurRecipe;
    public static RecipePetals numerondandelifeRecipe;
    public static RecipePetals woodieniaRecipe;
    public static RecipePetals numeronbalsamRecipe;
    public static RecipePetals icebirdiumRecipe;
    public static RecipePetals volatililyRecipe;
    public static RecipePetals judasvowRecipe;
    public static RecipePetals voiduimRecipe;
    public static RecipePetals diplopbambooRecipe;
    public static RecipePetals artifaconiaRecipe;
    public static RecipePetals infernoidisyRecipe;
    public static RecipePetals launchishRecipe;
    public static RecipePetals annoyobloomRecipe;
    public static RecipePetals manalinkuim;

    public static void init() {
        blueenchantressRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"blueenchantress"), (Object[])new Object[]{blue, blue, blue, blue, cyan, gaiaEssence, manaPowder, runeWater});
        candyflowerRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"candyflower"), (Object[])new Object[]{red, orange, yellow, blue, green, purple, manaPowder, pixieDust});
        sunshinelilyRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"sunshinelily"), (Object[])new Object[]{orange, orange, orange, yellow, blankCard, manaPowder, runeGreed, pixieDust});
        moonlightlilyRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"moonlightlily"), (Object[])new Object[]{purple, purple, purple, gray, blankCard, manaPowder, runeGreed, pixieDust});
        geminiorchidRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"geminiorchid"), (Object[])new Object[]{orange, orange, yellow, yellow, manaPowder, runeMana});
        ominivioletRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"omniviolet"), (Object[])new Object[]{red, orange, yellow, blue, green, purple, runeEarth});
        pyschobloomRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"pyschobloom"), (Object[])new Object[]{orange, orange, orange, magenta, magenta, manaPowder});
        stonesiaRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"stonesia"), (Object[])new Object[]{lightGray, lightGray, gray, gray, black, manaPowder});
        bellflowerRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"bellflower"), (Object[])new Object[]{orange, yellow, yellow, yellow, yellow, runeSpring, manaPowder});
        necrofleurRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"necrofleur"), (Object[])new Object[]{purple, purple, magenta, magenta, blankCard, quartzElementium, manaPowder, redstoneRoot});
        numerondandelifeRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"numerondandelife"), (Object[])new Object[]{petalGreen, petalGreen, petalRed, blankCard, shard, runeMana, redstoneRoot});
        woodieniaRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"woodienia"), (Object[])new Object[]{brown, brown, brown, gray, blankCard, quartzElementium, manaPowder, redstoneRoot});
        numeronbalsamRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"numeronbalsam"), (Object[])new Object[]{red, red, red, magenta, pink, blankCard, runeFire});
        icebirdiumRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"icebirdium"), (Object[])new Object[]{lightBlue, lightBlue, lightBlue, cyan, blue, runeWater, runeWinter});
        volatililyRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"volatilily"), (Object[])new Object[]{white, white, black, black, redstoneRoot, dog, runeWater});
        judasvowRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"judasvow"), (Object[])new Object[]{red, red, orange, yellow, yellow, blankCard, runeAutumn, runeMana, shard});
        voiduimRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"voiduim"), (Object[])new Object[]{lightGray, gray, gray, gaiaEssence, gaiaSpirit, shard, runeWrath, runeGreed});
        if (ConfigHandler.enableDiplopbamboo) {
            diplopbambooRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"diplopbamboo"), (Object[])new Object[]{petalRed, petalGreen, petalPurple, brown, brown, brown, gaiaEssence, manaPowder});
        }
        artifaconiaRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"artifaconia"), (Object[])new Object[]{brown, brown, lightBlue, lightBlue, runeMana, runeEnvy, runeSloth, gaiaEssence});
        infernoidisyRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"infernoidisy"), (Object[])new Object[]{red, red, red, red, red, manaPowder});
        launchishRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"launchish"), (Object[])new Object[]{green, green, green, green, green, manaPowder});
        annoyobloomRecipe = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"annoyobloom"), (Object[])new Object[]{yellow, yellow, yellow, yellow, orange, manaPowder});
        manalinkuim = BotaniaAPI.registerPetalRecipe((ItemStack)ItemBlockSpecialFlower.ofType((String)"manalinkuim"), (Object[])new Object[]{blue, blue, lightBlue, cyan, cyan, runeMana, runeLust, manaPowder});
    }
}

