/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraftforge.common.AchievementPage
 */
package net.divinerpg.libs;

import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.ArcanaItems;
import net.divinerpg.utils.items.IceikaItems;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.divinerpg.utils.items.VanillaItemsArmor;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class DivineRPGAchievements {
    public static final Achievement divineIntervention = DivineRPGAchievements.addAchievement("divineIntervention", 0, 3, VanillaItemsOther.callWatcher, null, true);
    public static final Achievement divinePlan = DivineRPGAchievements.addAchievement("divinePlan", 1, 4, VanillaItemsWeapons.divineSword, null, true);
    public static final Achievement whatLiesWithin = DivineRPGAchievements.addAchievement("whatLiesWithin", 0, 5, ArcanaBlocks.arcanaPortalFrame, null, true);
    public static final Achievement perfectlyCorrupted = DivineRPGAchievements.addAchievement("perfectlyCorrupted", 6, 0, VanillaBlocks.altarOfCorruption, null, false);
    public static final Achievement totalDemonization = DivineRPGAchievements.addAchievement("totalDemonization", 3, 11, ArcanaBlocks.demonFurnace, whatLiesWithin, false);
    public static final Achievement greatBirdFire = DivineRPGAchievements.addAchievement("evilDisease", 3, 9, ArcanaItems.dungeonTokens, whatLiesWithin, false);
    public static final Achievement dungeonMaster = DivineRPGAchievements.addAchievement("dungeonMaster", 3, 4, ArcanaItems.dungeonTokens, whatLiesWithin, false);
    public static final Achievement eyeOfEvil = DivineRPGAchievements.addAchievement("eyeOfEvil", 1, 0, VanillaItemsOther.rupeeIngot, null, false);
    public static final Achievement tripleDanger = DivineRPGAchievements.addAchievement("tripleTheDanger", 3, 0, VanillaItemsOther.enderShards, null, false);
    public static final Achievement possibilities = DivineRPGAchievements.addAchievement("possibilities", 2, 3, VanillaBlocks.divineRock, null, true);
    public static final Achievement friendOrFoe = DivineRPGAchievements.addAchievement("friendOrFoe", 4, 3, TwilightItemsOther.edenSoul, possibilities, false);
    public static final Achievement darkAnotherDay = DivineRPGAchievements.addAchievement("darkAnotherDay", 6, 3, TwilightBlocks.mortumBlock, possibilities, true);
    public static final Achievement arrowToTheKnee = DivineRPGAchievements.addAchievement("arrowToTheKnee", 5, 4, TwilightItemsWeapons.twilightBow, darkAnotherDay, false);
    public static final Achievement tenHeadsIsBetterThanOne = DivineRPGAchievements.addAchievement("spawnDAR", 8, 4, TwilightItemsOther.densosCrystal, darkAnotherDay, true);
    public static final Achievement sixInOne = DivineRPGAchievements.addAchievement("sixInOne", 6, 5, TwilightItemsOther.eternalArcherCrystal, darkAnotherDay, true);
    public static final Achievement whenPigsFly = DivineRPGAchievements.addAchievement("whenPigsFly", 3, 2, VanillaItemsArmor.angelicHelmet, null, true);
    public static final Achievement offKey = DivineRPGAchievements.addAchievement("offKey", 7, 0, IceikaItems.soundOfMusic, null, false);
    public static final Achievement skyHigh = DivineRPGAchievements.addAchievement("skyHigh", 3, 5, ArcanaBlocks.elevantium, whatLiesWithin, false);
    public static final Achievement allHellLoose = DivineRPGAchievements.addAchievement("allHellLoose", 3, 10, ArcanaItems.stormSword, whatLiesWithin, true);
    public static final Achievement enrichment = DivineRPGAchievements.addAchievement("enrichment", 3, 7, ArcanaItems.staffOfEnrichment, whatLiesWithin, true);
    public static final Achievement hoterThanHell = DivineRPGAchievements.addAchievement("hotterThanHell", 3, 9, ArcanaBlocks.heatTrapOn, whatLiesWithin, false);
    public static final Achievement mealToRemember = DivineRPGAchievements.addAchievement("mealToRemember", 2, 0, ItemsFood.chickenDinner, null, false);
    public static final Achievement yuk = DivineRPGAchievements.addAchievement("yuk", 3, 8, ArcanaItems.weakArcanaPotion, whatLiesWithin, false);
    public static final Achievement littleCreature = DivineRPGAchievements.addAchievement("littleCreature", 3, 6, ArcanaItems.seimerSpawner, whatLiesWithin, false);
    public static final Achievement halloweenSpirit = DivineRPGAchievements.addAchievement("halloweenSpirit", 5, 0, VanillaItemsWeapons.scythe, null, false);
    public static final Achievement feedingOnTheFish = DivineRPGAchievements.addAchievement("feedingOnTheFish", 4, 0, VanillaItemsOther.sharkFin, null, false);
    public static final Achievement frozenLand = DivineRPGAchievements.addAchievement("frozenLand", 0, 1, IceikaBlocks.iceikaPortal, null, true);
    public static final Achievement frozenGoods = DivineRPGAchievements.addAchievement("frozenGoods", 8, 1, IceikaBlocks.frostedChest, frozenLand, false);
    public static final Achievement lilTinkerin = DivineRPGAchievements.addAchievement("lilTinkerin", 7, 2, IceikaItems.frozenMaul, frozenGoods, false);
    public static final Achievement lilGift = DivineRPGAchievements.addAchievement("lilGift", 9, 2, IceikaBlocks.presentBox, frozenGoods, false);
    public static final Achievement dramcryxDeath = DivineRPGAchievements.addAchievement("dramcryxDeath", 0, 0, VanillaItemsOther.jungleShards, null, false);
    public static final Achievement oneLampTwoLampRedLampBlueLamp = DivineRPGAchievements.addAchievement("oneLampTwoLampRedLampBlueLamp", 5, 2, VanillaBlocks.lamp1, 7, possibilities, false);
    public static final Achievement petCollector = DivineRPGAchievements.addAchievement("petCollector", 8, 0, VanillaItemsOther.overworldEgg, null, false);
    public static AchievementPage page = new AchievementPage("DivineRPG", new Achievement[]{divinePlan, divineIntervention, whatLiesWithin, friendOrFoe, tenHeadsIsBetterThanOne, sixInOne, perfectlyCorrupted, totalDemonization, greatBirdFire, dungeonMaster, eyeOfEvil, tripleDanger, possibilities, enrichment, hoterThanHell, mealToRemember, yuk, littleCreature, halloweenSpirit, feedingOnTheFish, frozenLand, frozenGoods, lilTinkerin, lilGift, skyHigh, whenPigsFly, offKey, allHellLoose, darkAnotherDay, arrowToTheKnee, dramcryxDeath, oneLampTwoLampRedLampBlueLamp, petCollector});

    private static Achievement addAchievement(String name, int x, int y, Block image, Achievement haveFirst, boolean isSpecial) {
        return isSpecial ? new Achievement(name, name, x, y, image, haveFirst).registerStat().setSpecial() : new Achievement(name, name, x, y, image, haveFirst).registerStat();
    }

    private static Achievement addAchievement(String name, int x, int y, Item image, Achievement haveFirst, boolean isSpecial) {
        return isSpecial ? new Achievement(name, name, x, y, image, haveFirst).registerStat().setSpecial() : new Achievement(name, name, x, y, image, haveFirst).registerStat();
    }

    private static Achievement addAchievement(String name, int x, int y, Block image, int meta, Achievement haveFirst, boolean isSpecial) {
        return isSpecial ? new Achievement(name, name, x, y, new ItemStack(image, 1, meta), haveFirst).registerStat().setSpecial() : new Achievement(name, name, x, y, new ItemStack(image, 1, meta), haveFirst).registerStat();
    }

    private static Achievement addAchievement(String name, int x, int y, Item image, int meta, Achievement haveFirst, boolean isSpecial) {
        return isSpecial ? new Achievement(name, name, x, y, new ItemStack(image, 1, meta), haveFirst).registerStat().setSpecial() : new Achievement(name, name, x, y, new ItemStack(image, 1, meta), haveFirst).registerStat();
    }

    public static void init() {
        AchievementPage.registerAchievementPage((AchievementPage)page);
    }
}

