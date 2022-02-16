/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.utils.recipes;

import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.TwilightItemsTools;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VetheaItems;
import net.divinerpg.utils.recipes.RecipeUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TwilightRecipeHelper
extends RecipeUtil {
    public static void init() {
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.edenFragments, TwilightItemsOther.edenSoul);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.wildwoodFragments, TwilightItemsOther.wildwoodSoul);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.apalachiaFragments, TwilightItemsOther.apalachiaSoul);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.skythernFragments, TwilightItemsOther.skythernSoul);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.mortumFragments, TwilightItemsOther.mortumSoul);
        TwilightRecipeHelper.addRecipe(TwilightItemsOther.edenGem, "GGG", Character.valueOf('G'), TwilightItemsOther.edenFragments);
        TwilightRecipeHelper.addRecipe(TwilightItemsOther.wildwoodGem, "GGG", Character.valueOf('G'), TwilightItemsOther.wildwoodFragments);
        TwilightRecipeHelper.addRecipe(TwilightItemsOther.apalachiaGem, "GGG", Character.valueOf('G'), TwilightItemsOther.apalachiaFragments);
        TwilightRecipeHelper.addRecipe(TwilightItemsOther.skythernGem, "GGG", Character.valueOf('G'), TwilightItemsOther.skythernFragments);
        TwilightRecipeHelper.addRecipe(TwilightItemsOther.mortumGem, "GGG", Character.valueOf('G'), TwilightItemsOther.mortumFragments);
        TwilightRecipeHelper.addSmelting(TwilightItemsOther.edenFragments, TwilightItemsOther.edenSparkles, 1.0f);
        TwilightRecipeHelper.addRecipe(TwilightItemsOther.edenChunk, "GGG", "G G", "G G", Character.valueOf('G'), TwilightItemsOther.edenGem);
        TwilightRecipeHelper.addRecipe(TwilightItemsOther.wildwoodChunk, "GGG", "G G", "G G", Character.valueOf('G'), TwilightItemsOther.wildwoodGem);
        TwilightRecipeHelper.addRecipe(TwilightItemsOther.apalachiaChunk, "GGG", "G G", "G G", Character.valueOf('G'), TwilightItemsOther.apalachiaGem);
        TwilightRecipeHelper.addRecipe(TwilightItemsOther.skythernChunk, "GGG", "G G", "G G", Character.valueOf('G'), TwilightItemsOther.skythernGem);
        TwilightRecipeHelper.addRecipe(TwilightItemsOther.mortumChunk, "GGG", "G G", "G G", Character.valueOf('G'), TwilightItemsOther.mortumGem);
        TwilightRecipeHelper.addShapelessRecipe(VanillaBlocks.divineRock, VanillaItemsOther.divineShards, Blocks.stone);
        TwilightRecipeHelper.addRecipe(TwilightBlocks.edenBlock, "III", "III", "III", Character.valueOf('I'), TwilightItemsOther.edenFragments);
        TwilightRecipeHelper.addRecipe(TwilightBlocks.wildwoodBlock, "III", "III", "III", Character.valueOf('I'), TwilightItemsOther.wildwoodFragments);
        TwilightRecipeHelper.addRecipe(TwilightBlocks.apalachiaBlock, "III", "III", "III", Character.valueOf('I'), TwilightItemsOther.apalachiaFragments);
        TwilightRecipeHelper.addRecipe(TwilightBlocks.skythernBlock, "III", "III", "III", Character.valueOf('I'), TwilightItemsOther.skythernFragments);
        TwilightRecipeHelper.addRecipe(TwilightBlocks.mortumBlock, "III", "III", "III", Character.valueOf('I'), TwilightItemsOther.mortumFragments);
        TwilightRecipeHelper.addSmelting(TwilightBlocks.edenOre, TwilightItemsOther.edenFragments, 0.7f);
        TwilightRecipeHelper.addSmelting(TwilightBlocks.wildwoodOre, TwilightItemsOther.wildwoodFragments, 1.0f);
        TwilightRecipeHelper.addSmelting(TwilightBlocks.apalachiaOre, TwilightItemsOther.apalachiaFragments, 1.3f);
        TwilightRecipeHelper.addSmelting(TwilightBlocks.skythernOre, TwilightItemsOther.skythernFragments, 1.7f);
        TwilightRecipeHelper.addSmelting(TwilightBlocks.mortumOre, TwilightItemsOther.mortumFragments, 2.0f);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.edenDust, TwilightBlocks.sunbloom);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.edenDust, TwilightBlocks.edenBrush);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.edenDust, TwilightBlocks.sunBlossom);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.wildwoodDust, TwilightBlocks.moonlightFern);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.wildwoodDust, TwilightBlocks.moonBud);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.wildwoodDust, TwilightBlocks.wildwoodTallgrass);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.apalachiaDust, TwilightBlocks.duskFlower);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.apalachiaDust, TwilightBlocks.apalachiaTallgrass);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.skythernDust, TwilightBlocks.skythernBrush);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.skythernDust, TwilightBlocks.dustBrambles);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.skythernDust, TwilightBlocks.dustLily);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.mortumDust, TwilightBlocks.eyePlant);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.mortumDust, TwilightBlocks.mortumBrush);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.mortumDust, TwilightBlocks.demonBrambles);
        TwilightRecipeHelper.addShapelessRecipe(new ItemStack(TwilightBlocks.edenPlanks, 4), TwilightBlocks.edenLogs);
        TwilightRecipeHelper.addShapelessRecipe(new ItemStack(TwilightBlocks.wildwoodPlanks, 4), TwilightBlocks.wildwoodLogs);
        TwilightRecipeHelper.addShapelessRecipe(new ItemStack(TwilightBlocks.apalachiaPlanks, 4), TwilightBlocks.apalachiaLogs);
        TwilightRecipeHelper.addShapelessRecipe(new ItemStack(TwilightBlocks.skythernPlanks, 4), TwilightBlocks.skythernLogs);
        TwilightRecipeHelper.addShapelessRecipe(new ItemStack(TwilightBlocks.mortumPlanks, 4), TwilightBlocks.mortumLogs);
        TwilightRecipeHelper.addStairRecipe(TwilightBlocks.edenPlanks, TwilightBlocks.edenStairs);
        TwilightRecipeHelper.addStairRecipe(TwilightBlocks.wildwoodPlanks, TwilightBlocks.wildwoodStairs);
        TwilightRecipeHelper.addStairRecipe(TwilightBlocks.apalachiaPlanks, TwilightBlocks.apalachiaStairs);
        TwilightRecipeHelper.addStairRecipe(TwilightBlocks.skythernPlanks, TwilightBlocks.skythernStairs);
        TwilightRecipeHelper.addStairRecipe(TwilightBlocks.mortumPlanks, TwilightBlocks.mortumStairs);
        TwilightRecipeHelper.addSlabRecipe(TwilightBlocks.edenPlanks, TwilightBlocks.edenSlab);
        TwilightRecipeHelper.addSlabRecipe(TwilightBlocks.wildwoodPlanks, TwilightBlocks.wildwoodSlab);
        TwilightRecipeHelper.addSlabRecipe(TwilightBlocks.apalachiaPlanks, TwilightBlocks.apalachiaSlab);
        TwilightRecipeHelper.addSlabRecipe(TwilightBlocks.skythernPlanks, TwilightBlocks.skythernSlab);
        TwilightRecipeHelper.addSlabRecipe(TwilightBlocks.mortumPlanks, TwilightBlocks.mortumSlab);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.edenHelmet, "III", "I I", Character.valueOf('I'), TwilightItemsOther.edenChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.edenChestplate, "I I", "III", "III", Character.valueOf('I'), TwilightItemsOther.edenChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.edenLeggings, "III", "I I", "I I", Character.valueOf('I'), TwilightItemsOther.edenChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.edenBoots, "   ", "I I", "I I", Character.valueOf('I'), TwilightItemsOther.edenChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.wildwoodHelmet, "III", "I I", Character.valueOf('I'), TwilightItemsOther.wildwoodChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.wildwoodChestplate, "I I", "III", "III", Character.valueOf('I'), TwilightItemsOther.wildwoodChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.wildwoodLeggings, "III", "I I", "I I", Character.valueOf('I'), TwilightItemsOther.wildwoodChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.wildwoodBoots, "   ", "I I", "I I", Character.valueOf('I'), TwilightItemsOther.wildwoodChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.apalachiaHelmet, "III", "I I", Character.valueOf('I'), TwilightItemsOther.apalachiaChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.apalachiaChestplate, "I I", "III", "III", Character.valueOf('I'), TwilightItemsOther.apalachiaChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.apalachiaLeggings, "III", "I I", "I I", Character.valueOf('I'), TwilightItemsOther.apalachiaChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.apalachiaBoots, "   ", "I I", "I I", Character.valueOf('I'), TwilightItemsOther.apalachiaChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.skythernHelmet, "III", "I I", Character.valueOf('I'), TwilightItemsOther.skythernChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.skythernChestplate, "I I", "III", "III", Character.valueOf('I'), TwilightItemsOther.skythernChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.skythernLeggings, "III", "I I", "I I", Character.valueOf('I'), TwilightItemsOther.skythernChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.skythernBoots, "   ", "I I", "I I", Character.valueOf('I'), TwilightItemsOther.skythernChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.mortumHelmet, "III", "I I", Character.valueOf('I'), TwilightItemsOther.mortumChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.mortumChestplate, "I I", "III", "III", Character.valueOf('I'), TwilightItemsOther.mortumChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.mortumLeggings, "III", "I I", "I I", Character.valueOf('I'), TwilightItemsOther.mortumChunk);
        TwilightRecipeHelper.addRecipe(TwilightItemsArmor.mortumBoots, "   ", "I I", "I I", Character.valueOf('I'), TwilightItemsOther.mortumChunk);
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsWeapons.edenBlade, " I ", " I ", " S ", Character.valueOf('I'), TwilightItemsOther.edenChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addRecipe(TwilightItemsWeapons.edenBow, " IX", "I X", " IX", Character.valueOf('I'), TwilightItemsOther.edenChunk, Character.valueOf('X'), Items.string);
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsWeapons.edenPhaser, " X ", "XXX", " S ", Character.valueOf('X'), TwilightItemsOther.edenChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addRecipe(TwilightItemsWeapons.edenBlitz, "X X", "X X", "XXX", Character.valueOf('X'), TwilightItemsOther.edenChunk);
        TwilightRecipeHelper.addShapelessRecipe(new ItemStack(TwilightItemsWeapons.edenSlicer, 20), TwilightItemsOther.edenGem);
        TwilightRecipeHelper.addOredictRecipe(new ItemStack(TwilightItemsWeapons.edenArrow, 4), " I ", " S ", " Z ", Character.valueOf('I'), TwilightItemsOther.edenFragments, Character.valueOf('S'), "stickWood", Character.valueOf('Z'), Items.feather);
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsWeapons.wildwoodBlade, " I ", " I ", " S ", Character.valueOf('I'), TwilightItemsOther.wildwoodChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addRecipe(TwilightItemsWeapons.wildwoodBow, " IX", "I X", " IX", Character.valueOf('I'), TwilightItemsOther.wildwoodChunk, Character.valueOf('X'), Items.string);
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsWeapons.wildwoodPhaser, " X ", "XXX", " S ", Character.valueOf('X'), TwilightItemsOther.wildwoodChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addRecipe(TwilightItemsWeapons.wildwoodBlitz, "X X", "X X", "XXX", Character.valueOf('X'), TwilightItemsOther.wildwoodChunk);
        TwilightRecipeHelper.addShapelessRecipe(new ItemStack(TwilightItemsWeapons.wildwoodSlicer, 20), TwilightItemsOther.wildwoodGem);
        TwilightRecipeHelper.addOredictRecipe(new ItemStack(TwilightItemsWeapons.wildwoodArrow, 4), " I ", " S ", " Z ", Character.valueOf('I'), TwilightItemsOther.wildwoodFragments, Character.valueOf('S'), "stickWood", Character.valueOf('Z'), Items.feather);
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsWeapons.apalachiaBlade, " I ", " I ", " S ", Character.valueOf('I'), TwilightItemsOther.apalachiaChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addRecipe(TwilightItemsWeapons.apalachiaBow, " IX", "I X", " IX", Character.valueOf('I'), TwilightItemsOther.apalachiaChunk, Character.valueOf('X'), Items.string);
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsWeapons.apalachiaPhaser, " X ", "XXX", " S ", Character.valueOf('X'), TwilightItemsOther.apalachiaChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addRecipe(TwilightItemsWeapons.apalachiaBlitz, "X X", "X X", "XXX", Character.valueOf('X'), TwilightItemsOther.apalachiaChunk);
        TwilightRecipeHelper.addShapelessRecipe(new ItemStack(TwilightItemsWeapons.apalachiaSlicer, 20), TwilightItemsOther.apalachiaGem);
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsWeapons.skythernBlade, " I ", " I ", " S ", Character.valueOf('I'), TwilightItemsOther.skythernChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addRecipe(TwilightItemsWeapons.skythernBow, " IX", "I X", " IX", Character.valueOf('I'), TwilightItemsOther.skythernChunk, Character.valueOf('X'), Items.string);
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsWeapons.skythernPhaser, " X ", "XXX", " S ", Character.valueOf('X'), TwilightItemsOther.skythernChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addRecipe(TwilightItemsWeapons.skythernBlitz, "X X", "X X", "XXX", Character.valueOf('X'), TwilightItemsOther.skythernChunk);
        TwilightRecipeHelper.addShapelessRecipe(new ItemStack(TwilightItemsWeapons.skythernSlicer, 20), TwilightItemsOther.skythernGem);
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsWeapons.mortumBlade, " I ", " I ", " S ", Character.valueOf('I'), TwilightItemsOther.mortumChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addRecipe(TwilightItemsWeapons.mortumBow, " IX", "I X", " IX", Character.valueOf('I'), TwilightItemsOther.mortumChunk, Character.valueOf('X'), Items.string);
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsWeapons.mortumPhaser, " X ", "XXX", " S ", Character.valueOf('X'), TwilightItemsOther.mortumChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addRecipe(TwilightItemsWeapons.mortumBlitz, "X X", "X X", "XXX", Character.valueOf('X'), TwilightItemsOther.mortumChunk);
        TwilightRecipeHelper.addShapelessRecipe(new ItemStack(TwilightItemsWeapons.mortumSlicer, 20), TwilightItemsOther.mortumGem);
        TwilightRecipeHelper.addOredictRecipe(new ItemStack(TwilightItemsWeapons.furyArrow, 4), " I ", " S ", " Z ", Character.valueOf('I'), TwilightItemsOther.mortumFragments, Character.valueOf('S'), "stickWood", Character.valueOf('Z'), Items.feather);
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.edenPickaxe, "III", " S ", " S ", Character.valueOf('I'), TwilightItemsOther.edenChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.edenShovel, " I ", " S ", " S ", Character.valueOf('I'), TwilightItemsOther.edenChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.edenAxe, "II ", "IS ", " S ", Character.valueOf('I'), TwilightItemsOther.edenChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.wildwoodPickaxe, "III", " S ", " S ", Character.valueOf('I'), TwilightItemsOther.wildwoodChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.wildwoodShovel, " I ", " S ", " S ", Character.valueOf('I'), TwilightItemsOther.wildwoodChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.wildwoodAxe, "II ", "IS ", " S ", Character.valueOf('I'), TwilightItemsOther.wildwoodChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.apalachiaPickaxe, "III", " S ", " S ", Character.valueOf('I'), TwilightItemsOther.apalachiaChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.apalachiaShovel, " I ", " S ", " S ", Character.valueOf('I'), TwilightItemsOther.apalachiaChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.apalachiaAxe, "II ", "IS ", " S ", Character.valueOf('I'), TwilightItemsOther.apalachiaChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.skythernPickaxe, "III", " S ", " S ", Character.valueOf('I'), TwilightItemsOther.skythernChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.skythernShovel, " I ", " S ", " S ", Character.valueOf('I'), TwilightItemsOther.skythernChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.skythernAxe, "II ", "IS ", " S ", Character.valueOf('I'), TwilightItemsOther.skythernChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.mortumPickaxe, "III", " S ", " S ", Character.valueOf('I'), TwilightItemsOther.mortumChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.mortumShovel, " I ", " S ", " S ", Character.valueOf('I'), TwilightItemsOther.mortumChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addOredictRecipe(TwilightItemsTools.mortumAxe, "II ", "IS ", " S ", Character.valueOf('I'), TwilightItemsOther.mortumChunk, Character.valueOf('S'), "stickWood");
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.baseSpawnCrystal, TwilightItemsOther.skythernSoul, TwilightItemsOther.skythernSoul, TwilightItemsOther.skythernSoul, TwilightItemsOther.skythernSoul, TwilightItemsOther.skythernSoul);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.reyvorCrystal, TwilightItemsOther.baseSpawnCrystal, TwilightItemsOther.mortumSoul, TwilightItemsOther.mortumSoul, TwilightItemsOther.edenSoul, TwilightItemsOther.wildwoodSoul);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.eternalArcherCrystal, TwilightItemsOther.baseSpawnCrystal, TwilightItemsOther.edenSoul, TwilightItemsOther.mortumSoul, TwilightItemsOther.edenSoul, TwilightItemsOther.wildwoodSoul);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.soulFiendCrystal, TwilightItemsOther.baseSpawnCrystal, TwilightItemsOther.apalachiaSoul, TwilightItemsOther.skythernSoul, TwilightItemsOther.wildwoodSoul, TwilightItemsOther.edenSoul);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.twilightDemonCrystal, TwilightItemsOther.baseSpawnCrystal, TwilightItemsOther.mortumSoul, TwilightItemsOther.mortumSoul, TwilightItemsOther.wildwoodSoul, TwilightItemsOther.skythernSoul);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.densosCrystal, TwilightItemsOther.baseSpawnCrystal, TwilightItemsOther.apalachiaSoul, TwilightItemsOther.mortumSoul, TwilightItemsOther.wildwoodSoul, TwilightItemsOther.skythernSoul);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.vamacheronCrystal, TwilightItemsOther.baseSpawnCrystal, TwilightItemsOther.mortumSoul, TwilightItemsOther.mortumSoul, TwilightItemsOther.mortumSoul, TwilightItemsOther.mortumSoul);
        TwilightRecipeHelper.addShapelessRecipe(TwilightItemsOther.karotCrystal, TwilightItemsOther.baseSpawnCrystal, TwilightItemsOther.apalachiaSoul, TwilightItemsOther.mortumSoul, TwilightItemsOther.mortumSoul, TwilightItemsOther.skythernSoul);
        TwilightRecipeHelper.addRecipe(TwilightBlocks.edenChest, "bbb", "b b", "bbb", Character.valueOf('b'), TwilightItemsOther.edenFragments);
        TwilightRecipeHelper.addRecipe(VetheaItems.nightmareBed, "MMM", "WWW", Character.valueOf('M'), TwilightBlocks.mortumBlock, Character.valueOf('W'), TwilightBlocks.mortumLogs);
        TwilightRecipeHelper.addRecipe(new ItemStack(TwilightBlocks.apalachiaRails, 8), "F F", "FFF", "F F", Character.valueOf('F'), TwilightItemsOther.apalachiaFragments);
    }
}

