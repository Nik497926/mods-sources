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
        blueenchantressRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("blueenchantress"), blue, blue, blue, blue, cyan, gaiaEssence, manaPowder, runeWater);
        candyflowerRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("candyflower"), red, orange, yellow, blue, green, purple, manaPowder, pixieDust);
        sunshinelilyRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("sunshinelily"), orange, orange, orange, yellow, blankCard, manaPowder, runeGreed, pixieDust);
        moonlightlilyRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("moonlightlily"), purple, purple, purple, gray, blankCard, manaPowder, runeGreed, pixieDust);
        geminiorchidRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("geminiorchid"), orange, orange, yellow, yellow, manaPowder, runeMana);
        ominivioletRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("omniviolet"), red, orange, yellow, blue, green, purple, runeEarth);
        pyschobloomRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("pyschobloom"), orange, orange, orange, magenta, magenta, manaPowder);
        stonesiaRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("stonesia"), lightGray, lightGray, gray, gray, black, manaPowder);
        bellflowerRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("bellflower"), orange, yellow, yellow, yellow, yellow, runeSpring, manaPowder);
        necrofleurRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("necrofleur"), purple, purple, magenta, magenta, blankCard, quartzElementium, manaPowder, redstoneRoot);
        numerondandelifeRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("numerondandelife"), petalGreen, petalGreen, petalRed, blankCard, shard, runeMana, redstoneRoot);
        woodieniaRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("woodienia"), brown, brown, brown, gray, blankCard, quartzElementium, manaPowder, redstoneRoot);
        numeronbalsamRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("numeronbalsam"), red, red, red, magenta, pink, blankCard, runeFire);
        icebirdiumRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("icebirdium"), lightBlue, lightBlue, lightBlue, cyan, blue, runeWater, runeWinter);
        volatililyRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("volatilily"), white, white, black, black, redstoneRoot, dog, runeWater);
        judasvowRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("judasvow"), red, red, orange, yellow, yellow, blankCard, runeAutumn, runeMana, shard);
        voiduimRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("voiduim"), lightGray, gray, gray, gaiaEssence, gaiaSpirit, shard, runeWrath, runeGreed);
        if (ConfigHandler.enableDiplopbamboo) {
            diplopbambooRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("diplopbamboo"), petalRed, petalGreen, petalPurple, brown, brown, brown, gaiaEssence, manaPowder);
        }
        artifaconiaRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("artifaconia"), brown, brown, lightBlue, lightBlue, runeMana, runeEnvy, runeSloth, gaiaEssence);
        infernoidisyRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("infernoidisy"), red, red, red, red, red, manaPowder);
        launchishRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("launchish"), green, green, green, green, green, manaPowder);
        annoyobloomRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("annoyobloom"), yellow, yellow, yellow, yellow, orange, manaPowder);
        manalinkuim = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("manalinkuim"), blue, blue, lightBlue, cyan, cyan, runeMana, runeLust, manaPowder);
    }
}

