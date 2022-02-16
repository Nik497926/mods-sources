/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.utils.recipes;

import net.divinerpg.utils.blocks.JourneyBlocks;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.JourneyItemsArmor;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.JourneyItemsTools;
import net.divinerpg.utils.items.JourneyItemsWeapon;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.recipes.RecipeUtil;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class JourneyRecipeHelper
extends RecipeUtil {
    public static void init() {
        JourneyRecipeHelper.addRecipe(JourneyItemsOther.sozerItemActv, "SIS", "QXQ", "SIS", Character.valueOf('I'), VanillaItemsOther.mystic, Character.valueOf('S'), VanillaItemsOther.mysticChunk, Character.valueOf('X'), VanillaItemsOther.mysticSoul, Character.valueOf('Q'), JourneyItemsOther.SpectralDust);
        JourneyRecipeHelper.addRecipe(JourneyItemsArmor.lightstoneHelmet, "JIJ", "SXS", "JIJ", Character.valueOf('I'), JourneyItemsOther.SpectralEye, Character.valueOf('S'), JourneyItemsOther.SpectralDust, Character.valueOf('J'), VanillaItemsOther.mysticSoul, Character.valueOf('X'), TwilightItemsArmor.awakenedHelmet);
        JourneyRecipeHelper.addRecipe(JourneyItemsArmor.lightstoneChestplate, "JIJ", "SXS", "JIJ", Character.valueOf('I'), JourneyItemsOther.SpectralEye, Character.valueOf('S'), JourneyItemsOther.SpectralDust, Character.valueOf('J'), VanillaItemsOther.mysticSoul, Character.valueOf('X'), TwilightItemsArmor.awakenedChestplate);
        JourneyRecipeHelper.addRecipe(JourneyItemsArmor.lightstoneLeggings, "JIJ", "SXS", "JIJ", Character.valueOf('I'), JourneyItemsOther.SpectralEye, Character.valueOf('S'), JourneyItemsOther.SpectralDust, Character.valueOf('J'), VanillaItemsOther.mysticSoul, Character.valueOf('X'), TwilightItemsArmor.awakenedLeggings);
        JourneyRecipeHelper.addRecipe(JourneyItemsArmor.lightstoneBoots, "JIJ", "SXS", "JIJ", Character.valueOf('I'), JourneyItemsOther.SpectralEye, Character.valueOf('S'), JourneyItemsOther.SpectralDust, Character.valueOf('J'), VanillaItemsOther.mysticSoul, Character.valueOf('X'), TwilightItemsArmor.awakenedBoots);
        JourneyRecipeHelper.addRecipe(JourneyItemsOther.LuckyPickaxe, "XIX", " J ", " J ", Character.valueOf('I'), VanillaItemsOther.mysticSoul, Character.valueOf('J'), Items.stick, Character.valueOf('X'), VanillaItemsOther.mysticChunk);
        JourneyRecipeHelper.addSmelting(JourneyItemsOther.ash, JourneyItemsOther.hellcrustIngot, 1.0f);
        JourneyRecipeHelper.addRecipe(JourneyItemsWeapon.bloodcrustSword, " I ", " I ", " S ", Character.valueOf('I'), JourneyItemsOther.bloodcrustIngot, Character.valueOf('S'), Items.blaze_rod);
        JourneyRecipeHelper.addRecipe(JourneyItemsWeapon.withicBlade, " I ", " I ", " S ", Character.valueOf('I'), JourneyItemsOther.hellcrustIngot, Character.valueOf('S'), JourneyItemsOther.withicDust);
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsWeapon.shadiumSword, " I ", " I ", " S ", Character.valueOf('I'), JourneyItemsOther.shadiumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsWeapon.luniumSword, " I ", " I ", " S ", Character.valueOf('I'), JourneyItemsOther.luniumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsWeapon.sapphireSword, " I ", " I ", " S ", Character.valueOf('I'), JourneyItemsOther.sapphire, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsWeapon.koriteSword, " I ", " I ", " S ", Character.valueOf('I'), JourneyItemsOther.koriteIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsWeapon.mekyumSword, " I ", " I ", " S ", Character.valueOf('I'), JourneyItemsOther.mekyumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsWeapon.celestiumSword), " B ", "IIB", "HI ", Character.valueOf('H'), JourneyItemsOther.celestiumHandle, Character.valueOf('I'), JourneyItemsOther.celestiumIngot, Character.valueOf('B'), JourneyItemsOther.celestiumBlade);
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.shadiumPickaxe, "III", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.shadiumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.shadiumAxe, "II ", "IS ", " S ", Character.valueOf('I'), JourneyItemsOther.shadiumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.shadiumShovel, " I ", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.shadiumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.sapphirePickaxe, "III", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.sapphire, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.sapphireAxe, "II ", "IS ", " S ", Character.valueOf('I'), JourneyItemsOther.sapphire, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.sapphireShovel, " I ", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.sapphire, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.luniumPickaxe, "III", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.luniumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.luniumAxe, "II ", "IS ", " S ", Character.valueOf('I'), JourneyItemsOther.luniumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.luniumShovel, " I ", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.luniumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addRecipe(JourneyItemsTools.bloodcrustPickaxe, "III", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.bloodcrustIngot, Character.valueOf('S'), Items.blaze_rod);
        JourneyRecipeHelper.addRecipe(JourneyItemsTools.bloodcrustAxe, "II ", "IS ", " S ", Character.valueOf('I'), JourneyItemsOther.bloodcrustIngot, Character.valueOf('S'), Items.blaze_rod);
        JourneyRecipeHelper.addRecipe(JourneyItemsTools.bloodcrustShovel, " I ", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.bloodcrustIngot, Character.valueOf('S'), Items.blaze_rod);
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.mekyumPickaxe, "III", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.mekyumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.mekyumAxe, "II ", "IS ", " S ", Character.valueOf('I'), JourneyItemsOther.mekyumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.mekyumShovel, " I ", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.mekyumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addShapelessRecipe(JourneyItemsTools.mekyumShickaxe, JourneyItemsTools.mekyumPickaxe, JourneyItemsTools.mekyumShovel, JourneyItemsTools.mekyumAxe);
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.koritePickaxe, "III", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.koriteIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.koriteAxe, "II ", "IS ", " S ", Character.valueOf('I'), JourneyItemsOther.koriteIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.koriteShovel, " I ", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.koriteIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addShapelessRecipe(JourneyItemsTools.koriteShickaxe, JourneyItemsTools.koritePickaxe, JourneyItemsTools.koriteShovel, JourneyItemsTools.koriteAxe);
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.storonPickaxe, "III", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.storonIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.storonAxe, "II ", "IS ", " S ", Character.valueOf('I'), JourneyItemsOther.storonIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.storonShovel, " I ", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.storonIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addShapelessRecipe(JourneyItemsTools.storonShickaxe, JourneyItemsTools.storonPickaxe, JourneyItemsTools.storonShovel, JourneyItemsTools.storonAxe);
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.celestiumPickaxe, "III", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.celestiumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.celestiumAxe, "II ", "IS ", " S ", Character.valueOf('I'), JourneyItemsOther.celestiumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsTools.celestiumShovel, " I ", " S ", " S ", Character.valueOf('I'), JourneyItemsOther.celestiumIngot, Character.valueOf('S'), "stickWood");
        JourneyRecipeHelper.addShapelessRecipe(JourneyItemsTools.celestiumShickaxe, JourneyItemsTools.celestiumPickaxe, JourneyItemsTools.celestiumShovel, JourneyItemsTools.celestiumAxe);
        JourneyRecipeHelper.addRecipe(JourneyItemsOther.bloodcrustClump, "III", Character.valueOf('I'), JourneyItemsOther.bloodcrustIngot);
        JourneyRecipeHelper.addRecipe(JourneyBlocks.bloodcrustBlock, "III", "III", "III", Character.valueOf('I'), JourneyItemsOther.bloodcrustIngot);
        JourneyRecipeHelper.addRecipe(JourneyItemsOther.goldClump, "III", "III", "III", Character.valueOf('I'), JourneyItemsOther.golderDust);
        JourneyRecipeHelper.addRecipe(VanillaItemsOther.gateKey, "PIP", "IPI", "PIP", Character.valueOf('I'), JourneyItemsOther.goldClump, Character.valueOf('P'), JourneyItemsOther.silverClump);
        JourneyRecipeHelper.addRecipe(JourneyItemsArmor.CoolSetHelmet, " X ", "IJI", " I ", Character.valueOf('I'), VanillaItemsOther.godlyCrystal, Character.valueOf('X'), VanillaItemsOther.mystic, Character.valueOf('J'), TwilightItemsArmor.awakenedHelmet);
        JourneyRecipeHelper.addRecipe(JourneyItemsArmor.CoolSetChestplate, " X ", "IJI", " I ", Character.valueOf('I'), VanillaItemsOther.godlyCrystal, Character.valueOf('X'), VanillaItemsOther.mystic, Character.valueOf('J'), TwilightItemsArmor.awakenedChestplate);
        JourneyRecipeHelper.addRecipe(JourneyItemsArmor.CoolSetLeggings, " X ", "IJI", " I ", Character.valueOf('I'), VanillaItemsOther.godlyCrystal, Character.valueOf('X'), VanillaItemsOther.mystic, Character.valueOf('J'), TwilightItemsArmor.awakenedLeggings);
        JourneyRecipeHelper.addRecipe(JourneyItemsArmor.CoolSetBoots, " X ", "IJI", " I ", Character.valueOf('I'), VanillaItemsOther.godlyCrystal, Character.valueOf('X'), VanillaItemsOther.mystic, Character.valueOf('J'), TwilightItemsArmor.awakenedBoots);
        JourneyRecipeHelper.addRecipe(JourneyBlocks.shadiumBlock, "III", "III", "III", Character.valueOf('I'), JourneyItemsOther.shadiumIngot);
        JourneyRecipeHelper.addRecipe(JourneyBlocks.luniumBlock, "III", "III", "III", Character.valueOf('I'), JourneyItemsOther.luniumIngot);
        JourneyRecipeHelper.addRecipe(JourneyBlocks.sapphireBlock, "III", "III", "III", Character.valueOf('I'), JourneyItemsOther.sapphire);
        JourneyRecipeHelper.addRecipe(JourneyBlocks.mekyumBlock, "III", "III", "III", Character.valueOf('I'), JourneyItemsOther.mekyumIngot);
        JourneyRecipeHelper.addRecipe(JourneyBlocks.storonBlock, "III", "III", "III", Character.valueOf('I'), JourneyItemsOther.storonIngot);
        JourneyRecipeHelper.addRecipe(JourneyBlocks.koriteBlock, "III", "III", "III", Character.valueOf('I'), JourneyItemsOther.koriteIngot);
        JourneyRecipeHelper.addRecipe(JourneyBlocks.celestiumBlock, "III", "III", "III", Character.valueOf('I'), JourneyItemsOther.celestiumIngot);
        JourneyRecipeHelper.addRecipe(JourneyItemsOther.reinforcedCrystalIngot, "III", "ICI", "III", Character.valueOf('I'), JourneyItemsOther.stoneClump, Character.valueOf('C'), JourneyItemsOther.caveCrystal);
        JourneyRecipeHelper.addOredictRecipe(JourneyItemsOther.reinforcedStoneIngot, "III", "ICI", "III", Character.valueOf('I'), JourneyItemsOther.stoneClump, Character.valueOf('C'), "stone");
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyBlocks.eucaPortalFrame, 10), "III", "III", "III", Character.valueOf('I'), JourneyItemsOther.eucaPortalGem);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyBlocks.boilingPortalFrame, 10), "IXI", "XXX", "IXI", Character.valueOf('I'), JourneyItemsOther.flamingSpring, Character.valueOf('X'), VanillaItemsOther.furyFire);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyBlocks.LelyetiaPortalFrame, 10), "III", "XQX", "III", Character.valueOf('I'), JourneyItemsOther.boilGem, Character.valueOf('X'), VanillaItemsOther.mysticSoul, Character.valueOf('Q'), VanillaItemsOther.godlyCrystal);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsOther.stoneClump, 4), "III", "III", "III", Character.valueOf('I'), JourneyItemsOther.caveDust);
        JourneyRecipeHelper.addOredictRecipe(new ItemStack(JourneyItemsOther.stoneClump, 16), "III", "III", "III", Character.valueOf('I'), "stone");
        JourneyRecipeHelper.addOredictRecipe(new ItemStack(JourneyItemsOther.stoneClump, 16), "III", "III", "III", Character.valueOf('I'), "cobblestone");
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyBlocks.goldEucaPlank, 4), "B", Character.valueOf('B'), JourneyBlocks.eucaLog);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyBlocks.goldEucaPlank, 4), "B", Character.valueOf('B'), JourneyBlocks.eucaGoldLog);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsOther.celestiumHandle), " DD", "DDD", "ID ", Character.valueOf('I'), JourneyItemsOther.celestiumIngot, Character.valueOf('D'), JourneyItemsOther.celestiumDust);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsOther.celestiumBlade), "ID ", "DID", " DI", Character.valueOf('I'), JourneyItemsOther.celestiumIngot, Character.valueOf('D'), JourneyItemsOther.celestiumDust);
        JourneyRecipeHelper.addSmelting(JourneyItemsOther.shadiumDust, JourneyItemsOther.shadiumIngot, 1.2f);
        JourneyRecipeHelper.addSmelting(JourneyItemsOther.luniumDust, JourneyItemsOther.luniumIngot, 1.4f);
        JourneyRecipeHelper.addSmelting(JourneyBlocks.shadiumOre, JourneyItemsOther.shadiumIngot, 1.4f);
        JourneyRecipeHelper.addSmelting(JourneyBlocks.luniumOre, JourneyItemsOther.luniumIngot, 1.6f);
        JourneyRecipeHelper.addSmelting(JourneyBlocks.bloodcrustOre, JourneyItemsOther.bloodcrustIngot, 1.0f);
        JourneyRecipeHelper.addSmelting(JourneyBlocks.mekyumOre, JourneyItemsOther.mekyumIngot, 2.0f);
        JourneyRecipeHelper.addSmelting(JourneyBlocks.koriteOre, JourneyItemsOther.koriteIngot, 2.0f);
        JourneyRecipeHelper.addSmelting(JourneyBlocks.storonOre, JourneyItemsOther.storonIngot, 2.0f);
        JourneyRecipeHelper.addSmelting(JourneyBlocks.celestiumOre, JourneyItemsOther.celestiumIngot, 5.0f);
        JourneyRecipeHelper.addSmelting(JourneyBlocks.boilOre, JourneyItemsOther.boilChunk, 5.0f);
        JourneyRecipeHelper.addSmelting(ItemsFood.rocMeat, ItemsFood.cookedRocMeat, 0.2f);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.sapphireBoots), "x x", "x x", Character.valueOf('x'), JourneyItemsOther.sapphire);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.sapphireChestplate), "x x", "xxx", "xxx", Character.valueOf('x'), JourneyItemsOther.sapphire);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.sapphireLeggings), "xxx", "x x", "x x", Character.valueOf('x'), JourneyItemsOther.sapphire);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.sapphireHelmet), "xxx", "x x", Character.valueOf('x'), JourneyItemsOther.sapphire);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.shadiumBoots), "x x", "x x", Character.valueOf('x'), JourneyItemsOther.shadiumIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.shadiumChestplate), "x x", "xxx", "xxx", Character.valueOf('x'), JourneyItemsOther.shadiumIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.shadiumLeggings), "xxx", "x x", "x x", Character.valueOf('x'), JourneyItemsOther.shadiumIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.shadiumHelmet), "xxx", "x x", Character.valueOf('x'), JourneyItemsOther.shadiumIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.luniumBoots), "x x", "x x", Character.valueOf('x'), JourneyItemsOther.luniumIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.luniumChestplate), "x x", "xxx", "xxx", Character.valueOf('x'), JourneyItemsOther.luniumIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.luniumLeggings), "xxx", "x x", "x x", Character.valueOf('x'), JourneyItemsOther.luniumIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.luniumHelmet), "xxx", "x x", Character.valueOf('x'), JourneyItemsOther.luniumIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.celestiumBoots), "x x", "x x", Character.valueOf('x'), JourneyItemsOther.celestiumIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.celestiumChestplate), "x x", "xxx", "xxx", Character.valueOf('x'), JourneyItemsOther.celestiumIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.celestiumLeggings), "xxx", "x x", "x x", Character.valueOf('x'), JourneyItemsOther.celestiumIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.celestiumHelmet), "xxx", "x x", Character.valueOf('x'), JourneyItemsOther.celestiumIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.flameBoots), "wqw", "x x", Character.valueOf('x'), VanillaItemsOther.mysticSoul, Character.valueOf('w'), VanillaItemsOther.mystic, Character.valueOf('q'), TwilightItemsArmor.awakenedBoots);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.flameChestPlate), "wqw", "xwx", "xwx", Character.valueOf('x'), VanillaItemsOther.mysticSoul, Character.valueOf('w'), VanillaItemsOther.mystic, Character.valueOf('q'), TwilightItemsArmor.awakenedChestplate);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.flameLeggings), "xwx", "wqw", "w w", Character.valueOf('x'), VanillaItemsOther.mysticSoul, Character.valueOf('w'), VanillaItemsOther.mystic, Character.valueOf('q'), TwilightItemsArmor.awakenedLeggings);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.flameHelmet), "xwx", "wqw", Character.valueOf('x'), VanillaItemsOther.mysticSoul, Character.valueOf('w'), VanillaItemsOther.mystic, Character.valueOf('q'), TwilightItemsArmor.awakenedHelmet);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.bloodcrustBoots), "x x", "i i", Character.valueOf('x'), JourneyItemsOther.bloodcrustIngot, Character.valueOf('i'), JourneyItemsOther.hellcrustIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.bloodcrustChestplate), "i i", "xix", "ixi", Character.valueOf('x'), JourneyItemsOther.bloodcrustIngot, Character.valueOf('i'), JourneyItemsOther.hellcrustIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.bloodcrustLeggings), "ixi", "x x", "i i", Character.valueOf('x'), JourneyItemsOther.bloodcrustIngot, Character.valueOf('i'), JourneyItemsOther.hellcrustIngot);
        JourneyRecipeHelper.addRecipe(new ItemStack(JourneyItemsArmor.bloodcrustHelmet), "ixi", "x x", Character.valueOf('x'), JourneyItemsOther.bloodcrustIngot, Character.valueOf('i'), JourneyItemsOther.hellcrustIngot);
    }
}

