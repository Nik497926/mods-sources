/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.utils.blocks;

import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.blocks.base.BlockModDoublePlant;
import net.divinerpg.blocks.base.BlockModGrass;
import net.divinerpg.blocks.base.BlockModLeaves;
import net.divinerpg.blocks.base.BlockModLog;
import net.divinerpg.blocks.base.BlockModSlab;
import net.divinerpg.blocks.base.BlockModStairs;
import net.divinerpg.blocks.base.BlockModTorch;
import net.divinerpg.blocks.base.BlockModVine;
import net.divinerpg.blocks.base.BlockRedstonelessRails;
import net.divinerpg.blocks.base.portal.BlockModPortal;
import net.divinerpg.blocks.twilight.BlockBlueFire;
import net.divinerpg.blocks.twilight.BlockBrambles;
import net.divinerpg.blocks.twilight.BlockEdenChest;
import net.divinerpg.blocks.twilight.BlockMoonbulb;
import net.divinerpg.blocks.twilight.BlockPinkGlowbone;
import net.divinerpg.blocks.twilight.BlockPurpleGlowbone;
import net.divinerpg.blocks.twilight.BlockSkyPlant;
import net.divinerpg.blocks.twilight.BlockTwilightFlower;
import net.divinerpg.blocks.twilight.BlockTwilightGrass;
import net.divinerpg.blocks.twilight.BlockTwilightSapling;
import net.divinerpg.dimensions.twilight.apalachia.WorldGenApalachiaTree;
import net.divinerpg.dimensions.twilight.eden.WorldGenEdenTree;
import net.divinerpg.dimensions.twilight.mortum.WorldGenMortumTree;
import net.divinerpg.dimensions.twilight.skythern.WorldGenSkythernTree;
import net.divinerpg.dimensions.twilight.wildwood.WorldGenWildwoodTree3;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TwilightBlocks {
    public static Block darkOre = new BlockMod("darkOre", 12.0f).setDropItem(VanillaItemsOther.darkStone).setRareOre(true);
    public static Block lightOre = new BlockMod("lightOre", 12.0f).setDropItem(VanillaItemsOther.orbOfLight).setRareOre(true);
    public static Block netheriumOre = new BlockMod("netheriumOre", 16.0f).setDropItem(VanillaItemsOther.netheriumShards).setRareOre(true);
    public static Block enchantOre = new BlockMod("enchantOre", 16.0f).setDropItem(VanillaItemsOther.enchantChunk).setRareOre(true);
    public static Block edenDirt = new BlockMod(EnumBlockType.DIRT, "edenDirt", 5.0f);
    public static Block wildwoodDirt = new BlockMod(EnumBlockType.DIRT, "wildwoodDirt", 5.0f);
    public static Block apalachiaDirt = new BlockMod(EnumBlockType.DIRT, "apalachiaDirt", 5.0f);
    public static Block skythernDirt = new BlockMod(EnumBlockType.DIRT, "skythernDirt", 5.0f);
    public static Block mortumDirt = new BlockMod(EnumBlockType.DIRT, "mortumDirt", 5.0f);
    public static Block edenGrass = new BlockModGrass((BlockMod)edenDirt, "edenGrass", "edenDirt", 5.0f);
    public static Block wildwoodGrass = new BlockModGrass((BlockMod)wildwoodDirt, "wildwoodGrass", "wildwoodDirt", 5.0f);
    public static Block apalachiaGrass = new BlockModGrass((BlockMod)apalachiaDirt, "apalachiaGrass", "apalachiaDirt", 5.0f);
    public static Block skythernGrass = new BlockModGrass((BlockMod)skythernDirt, "skythernGrass", "skythernDirt", 5.0f);
    public static Block mortumGrass = new BlockModGrass((BlockMod)mortumDirt, "mortumGrass", "mortumDirt", 5.0f);
    public static Block edenOre = new BlockMod("edenOre", 9.0f).toggleExp(true).setTwilightOre(true).setDropItem(TwilightItemsOther.edenFragments);
    public static Block wildwoodOre = new BlockMod("wildwoodOre", 9.0f).setTwilightOre(true).setDropItem(TwilightItemsOther.wildwoodFragments);
    public static Block apalachiaOre = new BlockMod("apalachiaOre", 9.0f).setTwilightOre(true).setDropItem(TwilightItemsOther.apalachiaFragments);
    public static Block skythernOre = new BlockMod("skythernOre", 9.0f).setTwilightOre(true).setDropItem(TwilightItemsOther.skythernFragments);
    public static Block mortumOre = new BlockMod("mortumOre", 9.0f).setTwilightOre(true).setDropItem(TwilightItemsOther.mortumFragments);
    public static Block edenBlock = new BlockMod("edenBlock", 9.0f).setResistance(6000000.0f);
    public static Block wildwoodBlock = new BlockMod("wildwoodBlock", 9.0f).setResistance(6000000.0f);
    public static Block apalachiaBlock = new BlockMod("apalachiaBlock", 9.0f).setResistance(6000000.0f);
    public static Block skythernBlock = new BlockMod("skythernBlock", 9.0f).setResistance(6000000.0f);
    public static Block mortumBlock = new BlockMod("mortumBlock", 9.0f).setResistance(6000000.0f);
    public static Block edenSapling = new BlockTwilightSapling("edenSapling", edenGrass, edenDirt, (WorldGenerator)new WorldGenEdenTree(true));
    public static Block wildwoodSapling = new BlockTwilightSapling("wildwoodSapling", wildwoodGrass, wildwoodDirt, new WorldGenWildwoodTree3(true), 3, 3);
    public static Block apalachiaSapling = new BlockTwilightSapling("apalachiaSapling", apalachiaGrass, apalachiaDirt, new WorldGenApalachiaTree(true));
    public static Block skythernSapling = new BlockTwilightSapling("skythernSapling", skythernGrass, skythernDirt, (WorldGenerator)new WorldGenSkythernTree(true));
    public static Block mortumSapling = new BlockTwilightSapling("mortumSapling", mortumGrass, mortumDirt, (WorldGenerator)new WorldGenMortumTree(true));
    public static Block edenLeaves = new BlockModLeaves("edenLeaves", 0.5f, edenSapling);
    public static Block wildwoodLeaves = new BlockModLeaves("wildwoodLeaves", 0.5f, wildwoodSapling);
    public static Block apalachiaLeaves = new BlockModLeaves("apalachiaLeaves", 0.5f, apalachiaSapling).setLightLevel(0.5f);
    public static Block skythernLeaves = new BlockModLeaves("skythernLeaves", 0.5f, skythernSapling);
    public static Block mortumLeaves = new BlockModLeaves("mortumLeaves", 0.5f, mortumSapling);
    public static Block edenLogs = new BlockModLog("edenLog");
    public static Block wildwoodLogs = new BlockModLog("wildwoodLog");
    public static Block apalachiaLogs = new BlockModLog("apalachiaLog");
    public static Block skythernLogs = new BlockModLog("skythernLog");
    public static Block mortumLogs = new BlockModLog("mortumLog");
    public static Block edenPlanks = new BlockMod(EnumBlockType.WOOD, "edenPlanks", 8.0f).setResistance(5.0f);
    public static Block wildwoodPlanks = new BlockMod(EnumBlockType.WOOD, "wildwoodPlanks", 8.0f).setResistance(5.0f);
    public static Block apalachiaPlanks = new BlockMod(EnumBlockType.WOOD, "apalachiaPlanks", 8.0f).setResistance(5.0f);
    public static Block skythernPlanks = new BlockMod(EnumBlockType.WOOD, "skythernPlanks", 8.0f).setResistance(5.0f);
    public static Block mortumPlanks = new BlockMod(EnumBlockType.WOOD, "mortumPlanks", 8.0f).setResistance(5.0f);
    public static Block edenStairs = new BlockModStairs(edenPlanks, "edenStairs");
    public static Block wildwoodStairs = new BlockModStairs(wildwoodPlanks, "wildwoodStairs");
    public static Block apalachiaStairs = new BlockModStairs(apalachiaPlanks, "apalachiaStairs");
    public static Block skythernStairs = new BlockModStairs(skythernPlanks, "skythernStairs");
    public static Block mortumStairs = new BlockModStairs(mortumPlanks, "mortumStairs");
    public static Block edenSlab = new BlockModSlab((BlockMod)edenPlanks, false, "Planks", null);
    public static Block edenSlabDouble = new BlockModSlab((BlockMod)edenPlanks, true, "Planks", edenSlab);
    public static Block wildwoodSlab = new BlockModSlab((BlockMod)wildwoodPlanks, false, "Planks", null);
    public static Block wildwoodSlabDouble = new BlockModSlab((BlockMod)wildwoodPlanks, true, "Planks", wildwoodSlab);
    public static Block apalachiaSlab = new BlockModSlab((BlockMod)apalachiaPlanks, false, "Planks", null);
    public static Block apalachiaSlabDouble = new BlockModSlab((BlockMod)apalachiaPlanks, true, "Planks", apalachiaSlab);
    public static Block skythernSlab = new BlockModSlab((BlockMod)skythernPlanks, false, "Planks", null);
    public static Block skythernSlabDouble = new BlockModSlab((BlockMod)skythernPlanks, true, "Planks", skythernSlab);
    public static Block mortumSlab = new BlockModSlab((BlockMod)mortumPlanks, false, "Planks", null);
    public static Block mortumSlabDouble = new BlockModSlab((BlockMod)mortumPlanks, true, "Planks", mortumSlab);
    public static Block twilightStone = new BlockMod("twilightStone", 6.0f);
    public static Block wildwoodVine = new BlockModVine("wildwoodVine");
    public static Block blueFire = new BlockBlueFire("blueFire");
    public static Block edenPortal = new BlockModPortal("edenPortal", ConfigurationHelper.eden, blueFire, VanillaBlocks.divineRock);
    public static Block wildwoodPortal = new BlockModPortal("wildwoodPortal", ConfigurationHelper.wildwood, blueFire, edenBlock);
    public static Block apalachiaPortal = new BlockModPortal("apalachiaPortal", ConfigurationHelper.apalachia, blueFire, wildwoodBlock);
    public static Block skythernPortal = new BlockModPortal("skythernPortal", ConfigurationHelper.skythern, blueFire, apalachiaBlock);
    public static Block mortumPortal = new BlockModPortal("mortumPortal", ConfigurationHelper.mortum, blueFire, skythernBlock);
    public static Block eucaPortal = new BlockModPortal("eucaPortal", ConfigurationHelper.euca, blueFire, JourneyBlocks.eucaPortalFrame);
    public static Block boilingPortal = new BlockModPortal("boilingPortal", ConfigurationHelper.boiling, blueFire, JourneyBlocks.boilingPortalFrame);
    public static Block lleyetiaPortal = new BlockModPortal("lelyetiaPortal", ConfigurationHelper.lelyetia, blueFire, JourneyBlocks.LelyetiaPortalFrame);
    public static Block edenTorch = new BlockModTorch("edenTorch", "greenFlame");
    public static Block edenChest = new BlockEdenChest("edenChest");
    public static Block apalachiaRails = new BlockRedstonelessRails("apalachiaRails");
    public static Block moonbulb = new BlockMoonbulb();
    public static Block purpleGlowbone = new BlockPurpleGlowbone();
    public static Block pinkGlowbone = new BlockPinkGlowbone();
    public static Block skyPlant = new BlockSkyPlant();
    public static Block sunbloom = new BlockTwilightFlower("sunbloom", edenGrass);
    public static Block edenBrush = new BlockTwilightGrass("edenBrush", edenGrass);
    public static Block sunBlossom = new BlockTwilightFlower("sunBlossom", edenGrass);
    public static Block moonlightFern = new BlockTwilightFlower("moonlightFern", wildwoodGrass);
    public static Block moonBud = new BlockTwilightFlower("moonBud", wildwoodGrass);
    public static Block wildwoodTallgrass = new BlockModDoublePlant("wildwoodTallgrass", wildwoodGrass);
    public static Block duskFlower = new BlockModDoublePlant("duskFlower", apalachiaGrass);
    public static Block duskBloom = new BlockTwilightFlower("duskBloom", apalachiaGrass);
    public static Block apalachiaTallgrass = new BlockTwilightGrass("apalachiaTallgrass", apalachiaGrass);
    public static Block skythernBrush = new BlockTwilightGrass("skythernBrush", skythernGrass);
    public static Block dustLily = new BlockTwilightFlower("dustLily", skythernGrass);
    public static Block dustBrambles = new BlockBrambles("dustBrambles", skythernGrass);
    public static Block demonBrambles = new BlockBrambles("demonBrambles", mortumGrass);
    public static Block eyePlant = new BlockTwilightFlower("eyePlant", mortumGrass);
    public static Block mortumBrush = new BlockTwilightGrass("mortumBrush", mortumGrass);

    public static void init() {
        Util.registerSlab(edenSlab, edenSlabDouble);
        Util.registerSlab(wildwoodSlab, wildwoodSlabDouble);
        Util.registerSlab(apalachiaSlab, apalachiaSlabDouble);
        Util.registerSlab(skythernSlab, skythernSlabDouble);
        Util.registerSlab(mortumSlab, mortumSlabDouble);
    }
}

