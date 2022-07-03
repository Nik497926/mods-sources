/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.api;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.api.extrabotany.recipe.RecipeInfernoidisy;
import com.meteor.extrabotany.api.extrabotany.recipe.RecipeStonesia;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.core.handler.PropertyHandler;
import com.meteor.extrabotany.common.item.ModItems;
import com.valentin4311.candycraftmod.CandyCraft;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;

public class ExtraBotanyAPI {
    public static ModItems ModItems = new ModItems();
    public static ModBlocks ModBlocks = new ModBlocks();
    public static Set diplopbambooBlacklist = new LinkedHashSet();
    public static Set artifaconiaWhitelist = new LinkedHashSet();
    public static List infernoidisyRecipes = new ArrayList();
    public static List stonesiaRecipes = new ArrayList();
    public static Set sweetTier1 = new LinkedHashSet();
    public static Set sweetTier2 = new LinkedHashSet();
    public static Set sweetTier3 = new LinkedHashSet();
    public static Set sweetTier4 = new LinkedHashSet();
    public static Set sweetTier5 = new LinkedHashSet();
    public static Set sweetTier6 = new LinkedHashSet();
    public static DamageSource[] damageSource;

    public static void sT1(Item i) {
        sweetTier1.add(i);
    }

    public static void sT2(Item i) {
        sweetTier2.add(i);
    }

    public static void sT3(Item i) {
        sweetTier3.add(i);
    }

    public static void sT4(Item i) {
        sweetTier4.add(i);
    }

    public static void sT5(Item i) {
        sweetTier5.add(i);
    }

    public static void sT6(Item i) {
        sweetTier6.add(i);
    }

    public static boolean isRealDamage(DamageSource s) {
        return s.toString().startsWith("realDamage");
    }

    public static boolean isMagicDamage(DamageSource s) {
        return s.toString().startsWith("magicDamage");
    }

    public static void addShield(float shield, EntityPlayer player) {
        PropertyHandler.addShieldAmount(shield, player);
    }

    public static void setShield(float shield, EntityPlayer player) {
        PropertyHandler.setShieldAmount(shield, player);
    }

    public static void getShield(EntityPlayer player) {
        PropertyHandler.getShieldAmount(player);
    }

    public static void getMaxShield(EntityPlayer player) {
        PropertyHandler.getMaxShieldAmount(player);
    }

    public static void blacklistItemFromDiplopBamboo(Item item) {
        diplopbambooBlacklist.add(item);
    }

    public static boolean isItemBlacklistedFromDiplopBamboo(Item item) {
        return diplopbambooBlacklist.contains(item);
    }

    public static void whitelistItemFromArtifaconia(Item item) {
        artifaconiaWhitelist.add(item);
    }

    public static boolean isItemWhitelistedFromArtifaconia(Item item) {
        return artifaconiaWhitelist.contains(item);
    }

    public static RecipeInfernoidisy registerInfernoidisyRecipe(Object input, Block output, int outputMeta) {
        RecipeInfernoidisy recipe = new RecipeInfernoidisy(input, output, outputMeta);
        infernoidisyRecipes.add(recipe);
        return recipe;
    }

    public static RecipeStonesia registerStonesiaRecipe(Object input, int mana) {
        RecipeStonesia recipe = new RecipeStonesia(input, mana);
        stonesiaRecipes.add(recipe);
        return recipe;
    }

    public static RecipeInfernoidisy removeInfernoidisyRecipe(Object input, Block output, int outputMeta) {
        RecipeInfernoidisy recipe = new RecipeInfernoidisy(input, output, outputMeta);
        infernoidisyRecipes.remove(recipe);
        return recipe;
    }

    public static RecipeStonesia removeStonesiaRecipe(Object input, int mana) {
        RecipeStonesia recipe = new RecipeStonesia(input, mana);
        stonesiaRecipes.remove(recipe);
        return recipe;
    }

    static {
        if (ExtraBotany.candycraftLoaded) {
            ExtraBotanyAPI.sT1(CandyCraft.WaffleNugget);
            ExtraBotanyAPI.sT1(CandyCraft.SugarCrystal);
            ExtraBotanyAPI.sT2(CandyCraft.ChewingGum);
            ExtraBotanyAPI.sT2(CandyCraft.Lollipop);
            ExtraBotanyAPI.sT2(CandyCraft.CranberryFish);
            ExtraBotanyAPI.sT2(CandyCraft.Dragibus);
            ExtraBotanyAPI.sT2(CandyCraft.Gummy);
            ExtraBotanyAPI.sT2(CandyCraft.GummyBall);
            ExtraBotanyAPI.sT2(CandyCraft.PEZDust);
            ExtraBotanyAPI.sT2(CandyCraft.CandiedCherry);
            ExtraBotanyAPI.sT2(CandyCraft.ChocolateCoins);
            ExtraBotanyAPI.sT2(CandyCraft.Licorice);
            ExtraBotanyAPI.sT2(CandyCraft.CranberryScale);
            ExtraBotanyAPI.sT2(CandyCraft.HoneyShard);
            ExtraBotanyAPI.sT2(CandyCraft.CottonCandy);
            ExtraBotanyAPI.sT2(CandyCraft.MarshmallowFlower);
            ExtraBotanyAPI.sT2(CandyCraft.NougatPowder);
            ExtraBotanyAPI.sT3(CandyCraft.CranberryFishCooked);
            ExtraBotanyAPI.sT3(CandyCraft.HoneyComb);
            ExtraBotanyAPI.sT3(CandyCraft.Waffle);
            ExtraBotanyAPI.sT3(CandyCraft.PEZ);
        }
        ExtraBotanyAPI.sT2(Items.cookie);
        ExtraBotanyAPI.sT2(Items.sugar);
        ExtraBotanyAPI.sT6(Items.cake);
        damageSource = new DamageSource[]{new DamageSource("realDamage"), new DamageSource("realDamageHoly"), new DamageSource("realDamageCursed"), new DamageSource("realDamageGaia"), new DamageSource("magicDamage"), new DamageSource("magicDamageMissile"), new DamageSource("magicDamageLandmine")};
    }
}

