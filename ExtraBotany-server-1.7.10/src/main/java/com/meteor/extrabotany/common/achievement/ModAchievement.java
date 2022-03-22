/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.achievement;

import com.meteor.extrabotany.common.achievement.AchievementMod;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import vazkii.botania.common.achievement.AchievementTriggerer;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class ModAchievement {
    public static Achievement Relic_aphroditegrace;
    public static Achievement Relic_dice20;
    public static Achievement Relic_athenabless;
    public static Achievement Relic_excaliber;
    public static Achievement Relic_excaliberfake;
    public static Achievement Relic_hestiachastity;
    public static Achievement Relic_maxwelldemon;
    public static Achievement Relic_vhandgun;
    public static Achievement Relic_vpowerbattleaxe;
    public static Achievement Relic_vrangerboots;
    public static Achievement Relic_cronusphantom;
    public static Achievement Relic_hermestravelclothing;
    public static Achievement Relic_olympusguard;
    public static Achievement Relic_cthulhueye;
    public static Achievement Relic_hermes;
    public static Achievement Relic_claymore;
    public static Achievement Relic_combat;
    public static Achievement Relic_slience;
    public static Achievement Relic_loki;
    public static Achievement Relic_angel;
    public static Achievement Relic_ship;
    public static Achievement Gaia_gaia3Kill;
    public static Achievement Gaia_gaia3NoArmor;
    public static Achievement Gaia_gaia3DarkKill;
    public static Achievement Gaia_gaia3DarkNoArmor;
    public static Achievement F_annoy;
    public static Achievement F_artifa;
    public static Achievement F_diplop;
    public static Achievement F_ice;
    public static Achievement F_launch;
    public static Achievement F_necro;
    public static Achievement F_numeb;
    public static Achievement F_numed;
    public static Achievement F_void;
    public static Achievement F_vola;
    public static Achievement F_woo;
    public static Achievement F_blue;
    public static Achievement F_candy;
    public static Achievement F_gemini;
    public static Achievement F_moon;
    public static Achievement F_omni;
    public static Achievement F_pyscho;
    public static Achievement F_stone;
    public static Achievement F_sun;
    public static Achievement F_infer;
    public static Achievement F_judas;
    public static Achievement F_mana;
    public static Achievement O_blank;
    public static Achievement thousandUse;

    public static void init() {
        O_blank = new AchievementMod("blankcard", 1, 0, new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1, 1), null);
        thousandUse = new AchievementMod("manareader", -2, -2, new ItemStack(com.meteor.extrabotany.common.item.ModItems.reader), O_blank);
        F_annoy = new AchievementMod("annoyobloom", 0, 1, ItemBlockSpecialFlower.ofType("annoyobloom"), O_blank);
        F_artifa = new AchievementMod("artifaconia", 2, 1, ItemBlockSpecialFlower.ofType("artifaconia"), O_blank);
        F_diplop = new AchievementMod("diplopbamboo", 0, 2, ItemBlockSpecialFlower.ofType("diplopbamboo"), O_blank);
        F_ice = new AchievementMod("icebirdium", 2, 2, ItemBlockSpecialFlower.ofType("icebirdium"), O_blank);
        F_launch = new AchievementMod("launchish", 0, 3, ItemBlockSpecialFlower.ofType("launchish"), O_blank);
        F_necro = new AchievementMod("necrofleur", 2, 3, ItemBlockSpecialFlower.ofType("necrofleur"), O_blank);
        F_numeb = new AchievementMod("numeronbalsam", 0, 4, ItemBlockSpecialFlower.ofType("numeronbalsam"), O_blank);
        F_numed = new AchievementMod("numerondandelife", 2, 4, ItemBlockSpecialFlower.ofType("numerondandelife"), O_blank);
        F_void = new AchievementMod("voiduim", 0, 5, ItemBlockSpecialFlower.ofType("voiduim"), O_blank);
        F_vola = new AchievementMod("volatilily", 2, 5, ItemBlockSpecialFlower.ofType("volatilily"), O_blank);
        F_woo = new AchievementMod("woodienia", 0, 6, ItemBlockSpecialFlower.ofType("woodienia"), O_blank);
        F_blue = new AchievementMod("blueenchantress", 2, 6, ItemBlockSpecialFlower.ofType("blueenchantress"), O_blank);
        F_candy = new AchievementMod("candyflower", 0, 7, ItemBlockSpecialFlower.ofType("candyflower"), O_blank);
        F_gemini = new AchievementMod("geminiorchid", 2, 7, ItemBlockSpecialFlower.ofType("geminiorchid"), O_blank);
        F_moon = new AchievementMod("moonlightlily", 0, 8, ItemBlockSpecialFlower.ofType("moonlightlily"), O_blank);
        F_omni = new AchievementMod("omniviolet", 2, 8, ItemBlockSpecialFlower.ofType("omniviolet"), O_blank);
        F_pyscho = new AchievementMod("pyschobloom", 0, 9, ItemBlockSpecialFlower.ofType("pyschobloom"), O_blank);
        F_stone = new AchievementMod("stonesia", 2, 9, ItemBlockSpecialFlower.ofType("stonesia"), O_blank);
        F_sun = new AchievementMod("sunshinelily", 0, 10, ItemBlockSpecialFlower.ofType("sunshinelily"), O_blank);
        F_infer = new AchievementMod("infernoidisy", 2, 10, ItemBlockSpecialFlower.ofType("infernoidisy"), O_blank);
        F_judas = new AchievementMod("judasvow", 0, 11, ItemBlockSpecialFlower.ofType("judasvow"), O_blank);
        F_mana = new AchievementMod("manalinkuim", 2, 11, ItemBlockSpecialFlower.ofType("manalinkuim"), O_blank);
        Gaia_gaia3Kill = new AchievementMod("gaia_kill", -8, 8, ModItems.gaiaHead, null);
        Gaia_gaia3NoArmor = new AchievementMod("gaia_noarmor", -6, 8, com.meteor.extrabotany.common.item.ModItems.dungeonbox, null);
        Gaia_gaia3DarkKill = new AchievementMod("gaia_dark", -8, 10, com.meteor.extrabotany.common.item.ModItems.dog, Gaia_gaia3Kill);
        Gaia_gaia3DarkNoArmor = new AchievementMod("gaia_darknoarmor", -6, 10, com.meteor.extrabotany.common.item.ModItems.boxs, Gaia_gaia3Kill);
        Relic_maxwelldemon = new AchievementMod("maxwelldemon", -9, 14, com.meteor.extrabotany.common.item.ModItems.maxwelldemon, Gaia_gaia3Kill);
        Relic_hermes = new AchievementMod("hermeswand", -9, 17, com.meteor.extrabotany.common.item.ModItems.hermeswand, Gaia_gaia3Kill);
        Relic_claymore = new AchievementMod("coronaclaymore", -5, 18, com.meteor.extrabotany.common.item.ModItems.heliacalclaymore, Gaia_gaia3Kill);
        Relic_combat = new AchievementMod("valkyriecombatuniform", -7, 18, com.meteor.extrabotany.common.item.ModItems.valkyriecombatuniform, Gaia_gaia3Kill);
        Relic_angel = new AchievementMod("angelwand", -5, 19, com.meteor.extrabotany.common.item.ModItems.angelwand, Gaia_gaia3Kill);
        Relic_slience = new AchievementMod("eternalslience", -9, 19, com.meteor.extrabotany.common.item.ModItems.eternalslience, Gaia_gaia3Kill);
        int pageIndex = AchievementPage.getAchievementPages().size();
        AchievementPage extrabotanyPage = new AchievementPage("ExtraBotania", (Achievement[]) AchievementMod.achievements.toArray(new Achievement[AchievementMod.achievements.size()]));
        AchievementPage.registerAchievementPage(extrabotanyPage);
        FMLCommonHandler.instance().bus().register(new AchievementTriggerer());
    }
}

