/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 */
package net.divinerpg.utils.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.divinerpg.blocks.base.BlockJourneyGrass;
import net.divinerpg.blocks.base.BlockJourneyLeaves;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.blocks.base.BlockModBar;
import net.divinerpg.blocks.base.BlockModFence;
import net.divinerpg.blocks.base.BlockModLeaves;
import net.divinerpg.blocks.base.BlockModLog;
import net.divinerpg.blocks.base.BlockModSpawner;
import net.divinerpg.blocks.base.BlockModStairs;
import net.divinerpg.blocks.base.BlockStupidSpawner;
import net.divinerpg.blocks.twilight.BlockTwilightFlower;
import net.divinerpg.blocks.twilight.BlockTwilightGrass;
import net.divinerpg.blocks.twilight.BlockTwilightSapling;
import net.divinerpg.blocks.vanilla.BlockOreDrop;
import net.divinerpg.dimensions.euca.gen.trees.WorldGenEucaTree;
import net.divinerpg.utils.blocks.BlockForagingStone;
import net.divinerpg.utils.blocks.BlockGenericPlant;
import net.divinerpg.utils.blocks.GeneratedBlock;
import net.divinerpg.utils.blocks.GrassLelyetiaDown;
import net.divinerpg.utils.blocks.GrassLelyetiaUp;
import net.divinerpg.utils.blocks.GrawAltar;
import net.divinerpg.utils.blocks.Leaves;
import net.divinerpg.utils.blocks.LuckyBlock;
import net.divinerpg.utils.blocks.WashBricks;
import net.divinerpg.utils.blocks.WoodAchony;
import net.divinerpg.utils.blocks.WoodChurry;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.material.EnumBlockType;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class JourneyBlocks {
    public static Block hellwingSpawner = new BlockModSpawner("BrisonScreamerSpawner", "BrisonScreamer", "hellBotSpawner").setHardness(5.0f);
    public static Block goldbotSpawner = new BlockModSpawner("goldbotSpawner", "GoldBot", "eucaspawner").setHardness(5.0f);
    public static Block silverbotSpawner = new BlockModSpawner("silverbotSpawner", "Silverbot", "eucaspawner").setHardness(5.0f);
    public static Block boilingBars = new BlockModBar("boilingBars", Material.iron, true, false);
    public static Block burningLeaves = new BlockModLeaves("burningLeaves", 0.2f, null).setBurningPlant();
    public static Block ash = new BlockOreDrop(EnumBlockType.DIRT, "ashBlock", 2.0f, 1, JourneyItemsOther.ash, 2);
    public static Block bloodcrustOre = new BlockMod("bloodcrustOre", 10.0f);
    public static Block screamerSpawner = new BlockStupidSpawner("screamerSpawner", "BrisonScreamer", "hellBotSpawner", true).setResistance(60.0f);
    public static Block sapphireOre = new BlockOreDrop(EnumBlockType.ROCK, "sapphireOre", 5.0f, 3, JourneyItemsOther.sapphire, 3);
    public static Block shadiumOre = new BlockOreDrop(EnumBlockType.ROCK, "shadiumOre", 5.0f, 3, JourneyItemsOther.shadiumDust, 2);
    public static Block luniumOre = new BlockOreDrop(EnumBlockType.ROCK, "luniumOre", 7.0f, 3, JourneyItemsOther.luniumDust, 2);
    public static Block hotBlock = new BlockMod(EnumBlockType.ROCK, "hotGround", 3.0f);
    public static Block rockDust = new BlockMod(EnumBlockType.ROCK, "rockDust", 2.0f);
    public static Block boilingPortalFrame = new BlockMod("boilingPortalFrame", 1.0f).setResistance(6000000.0f);
    public static Block LelyetiaPortalFrame = new BlockMod("LelyetiaPortalFrame", 1.0f).setResistance(600000.0f);
    public static Block boilingLog = new BlockModLog("boilingLog").setHardness(2.0f);
    public static Block burntGrass = new BlockTwilightGrass("burntGrass", hotBlock);
    public static Block flameFlower = new BlockTwilightFlower("flameFlower", hotBlock).setLightLevel(0.5f);
    public static Block infernoPlant = new BlockTwilightFlower("infernoPlant", hotBlock).setLightLevel(0.2f);
    public static Block sizzlingPost = new BlockModFence("sizzlingPost", Material.wood);
    public static Block brisonStone = new BlockMod(EnumBlockType.ROCK, "brisonStone", 1.0f);
    public static Block boilingLamp = new BlockMod(EnumBlockType.GLASS, "boilingLamp", 0.1f).setLightLevel(1.0f);
    public static Block smallBrisonBrick = new BlockMod(EnumBlockType.ROCK, "smallBrisonBrick", 2.0f);
    public static Block compactBrisonStone = new BlockMod("compactBrisonStone", false);
    public static Block eucaDirt = new BlockMod(EnumBlockType.DIRT, "eucaDirt", 1.0f);
    public static Block eucaGrass = new BlockJourneyGrass((BlockMod)eucaDirt, "eucaGrass", "euacaDirt", 5.0f);
    public static Block eucaStone = new BlockMod("eucaStone", 1.0f);
    public static Block eucaBricks = new BlockMod("eucaBricks", 1.0f);
    public static Block eucaPortalFrame = new BlockMod("eucaPortalFrame", 1.0f).setResistance(6000000.0f);
    public static Block eucaSapling = new BlockTwilightSapling("eucaSapling", eucaGrass, eucaDirt, new WorldGenEucaTree());
    public static Block eucaLeaves = new BlockJourneyLeaves("eucaLeaves", 1.0f);
    public static Block eucaLog = new BlockModLog("eucaLog");
    public static Block eucaSilverLeaves = new BlockModLeaves("eucaSilverLeaves", 1.0f);
    public static Block eucaGoldLeaves = new BlockJourneyLeaves("eucaGoldLeaves", 0.5f, eucaSapling);
    public static Block LelyetianWiggler = new BlockGenericPlant(Material.vine).setTallPlant().isBidirectional().setName("lelyetianWiggler");
    public static Block LelyetianWigglerTop = new BlockGenericPlant(Material.vine).setTallPlant().setChildPlant(LelyetianWiggler).setName("lelyetianWigglerTop").setBlockTextureName("divinerpg:lelyetianWigglerTop");
    public static Block LelyetianStem = new BlockGenericPlant(Material.vine).setTallPlant().isBidirectional().setName("lelyetianStem");
    public static Block LelyetianStemCapUp = new BlockGenericPlant(Material.vine).setTallPlant().setChildPlant(LelyetianStem).setName("lelyetianStemCapUp");
    public static Block LelyetianStemCapDown = new BlockGenericPlant(Material.vine).setTallPlant().setChildPlant(LelyetianStem).setDown().setName("lelyetianStemCapDown");
    public static Block GrawAltar = new GrawAltar(Material.rock).setBlockName("GrawAltar").setBlockTextureName("divinerpg:grawAltar");
    public static Block LelyetianWigglerBottom = new BlockGenericPlant(Material.vine).setTallPlant().setChildPlant(LelyetianWiggler).setDown().setName("lelyetianWigglerBottom");
    public static Block LelyetianGrassDown = new BlockGenericPlant(Material.vine).setDown().setName("lelyetianGrassDown");
    public static Block LelyetianGrassUp = new BlockGenericPlant(Material.vine).setName("lelyetianGrassUp");
    public static Block WhiteWashBricks = new WashBricks().setName("bricksWhitewash").setBlockTextureName("divinerpg:bricksWhitewash");
    public static Block BricksLelyetia = new GeneratedBlock(Material.rock).setName("bricksLelyetian");
    public static Block eucaGoldLog = new BlockModLog("eucaGoldLog");
    public static Block goldEucaPlank = new BlockMod(EnumBlockType.WOOD, "goldEucaPlank", 1.0f);
    public static Block eucaTallGrass = new BlockTwilightGrass("eucaTallGrass", eucaGrass);
    public static Block eucaTallFlowers = new BlockTwilightFlower("eucaTallFlowers", eucaGrass);
    public static Block eucaBlueFlower = new BlockTwilightFlower("eucaBlueFlower", eucaGrass);
    public static Block celestiumOre = new BlockMod("celestiumOre", 5.0f);
    public static Block LeavesChurry = new Leaves().setName("leavesChurry");
    public static Block WoodChurry = new WoodChurry(Material.wood).setBlockName("woodChurry");
    public static Block WoodAchony = new WoodAchony(Material.wood).setBlockName("woodAchony");
    public static Block LelyetianCore = new Leaves().setName("lelyetianCore");
    public static Block LeavesAchony = new Leaves().setName("leavesAchony");
    public static Block StoneToxic = new BlockForagingStone(Material.rock).setName("stoneToxic");
    public static Block StoneLelyetia = new GeneratedBlock(Material.rock).setName("stoneLelyetia");
    public static Block GrassLelyetiaUp = new GrassLelyetiaUp(Material.grass).setBlockName("GrassLelyetiaUp");
    public static Block GrassLelyetiaDown = new GrassLelyetiaDown(Material.grass).setBlockName("GrassLelyetiaDown");
    public static Block boilShingle = new BlockMod("boil_shingle", 5.0f);
    public static Block boil_cobble = new BlockMod("boil_cobble", 5.0f);
    public static Block boilPillar = new BlockMod("boil_pillar_side", 5.0f);
    public static Block boilBricks = new BlockMod("boil_bricks", 5.0f);
    public static Block boilOre = new BlockMod("boilOre", 10.0f).setDropItem(JourneyItemsOther.boilChunk).setRareOre(true);
    public static Block volcanicSand = new BlockMod("volcanic_sand", 10.0f);
    public static Block boilSquareBrick = new BlockMod("boil_square_brick", 10.0f);
    public static Block goldite_oak_log_side = new BlockMod("goldite_oak_log_side", 10.0f);
    public static Block eucagoldleaves = new BlockMod("eucagoldleaves", 10.0f);
    public static Block eucatile = new BlockMod("eucatile", 10.0f);
    public static Block euca_square_dungeon_bricks = new BlockMod("euca_square_dungeon_bricks", 10.0f).setHardness(15.0f).setLightLevel(3.0f);
    public static Block euca_runic_lamp = new BlockMod("euca_runic_lamp", 10.0f).setHardness(5.0f);
    public static Block euca_gold_stone = new BlockMod("euca_gold_stone", 10.0f);
    public static Block mekyumOre = new BlockMod("mekyumOre", 5.0f);
    public static Block koriteOre = new BlockMod("koriteOre", 5.0f);
    public static Block storonOre = new BlockMod("storonOre", 5.0f);
    public static Block celestiumBlock = new BlockMod("celestiumBlock", 5.0f);
    public static Block mekyumBlock = new BlockMod("mekyumBlock", 5.0f);
    public static Block koriteBlock = new BlockMod("koriteBlock", 5.0f);
    public static Block storonBlock = new BlockMod("storonBlock", 5.0f);
    public static Block nethicDungeonBricks = new BlockMod("nethicDungeonBricks", 8.0f);
    public static Block largeNetherBrick = new BlockMod("largeNetherBrick", 8.0f);
    public static Block nethicLamp = new BlockMod(EnumBlockType.GLASS, "nethicLamp", 0.2f, DivineRPGTabs.blocks).setLightLevel(0.7f);
    public static Block hellbotSpawner = new BlockStupidSpawner("hellbotSpawner", "DungeonHellBot", "hellBotSpawner", true).setResistance(60.0f);
    public static Block dungeonBrick = new BlockMod("dungeonBrick", 5.0f);
    public static Block dungeonBrickStairs = new BlockModStairs(dungeonBrick, "dungeonBrickStairs");
    public static Block dungeonChiseledBrick = new BlockMod("dungeonChiseledBrick", 5.0f);
    public static Block dungeonChiseledBrickStairs = new BlockModStairs(dungeonChiseledBrick, "dungeonChisledBrickStairs");
    public static Block dungeonLamp = new BlockMod(EnumBlockType.GLASS, "dungeonLamp", 0.2f, DivineRPGTabs.blocks).setLightLevel(0.7f);
    public static Block dungeonLampStairs = new BlockModStairs(dungeonLamp, "dungeonLampStairs").setLightLevel(0.7f);
    public static Block dungeonLampFence = new BlockModFence("dungeonLampFence", Material.rock).setLightLevel(0.7f);
    public static Block dungeonCrackedBrick = new BlockMod("dungeonCrackedBrick", 5.0f);
    public static Block dungeonCrackedBrickStairs = new BlockModStairs(dungeonCrackedBrick, "dungeonCrackedBrickStairs");
    public static Block dungeonBrickCarved = new BlockMod("dungeonBrickCarved", 5.0f);
    public static Block dungeonBrickCarvedStairs = new BlockModStairs(dungeonBrickCarved, "dungeonBrickCarvedStairs");
    public static Block mossyEssenceStone = new BlockMod("mossyEssenceStone", 5.0f);
    public static Block blueGems = new BlockOreDrop(EnumBlockType.ROCK, "blueGems", 8.0f, 3, JourneyItemsOther.blueGem, 9);
    public static Block greenGems = new BlockOreDrop(EnumBlockType.ROCK, "greenGems", 8.0f, 3, JourneyItemsOther.greenGem, 9);
    public static Block yellowGems = new BlockOreDrop(EnumBlockType.ROCK, "yellowGems", 8.0f, 3, JourneyItemsOther.yellowGem, 9);
    public static Block purpleGems = new BlockOreDrop(EnumBlockType.ROCK, "purpleGems", 8.0f, 3, JourneyItemsOther.purpleGem, 9);
    public static Block bloodcrustBlock = new BlockMod("bloodcrustBlock", 5.0f);
    public static Block shadiumBlock = new BlockMod("shadiumBlock", 5.0f);
    public static Block luniumBlock = new BlockMod("luniumBlock", 5.0f);
    public static Block sapphireBlock = new BlockMod("sapphireBlock", 5.0f);
    public static Block LuckyBlock = new LuckyBlock(EnumBlockType.ROCK, "block_neutronium", 5.0f);

    public static void init() {
        GameRegistry.registerBlock((Block)GrassLelyetiaUp, (String)"GrassLelyetiaUp");
        GameRegistry.registerBlock((Block)GrawAltar, (String)"grawAltar");
        GameRegistry.registerBlock((Block)WoodAchony, (String)"woodAchony");
        GameRegistry.registerBlock((Block)WoodChurry, (String)"woodChurry");
        GameRegistry.registerBlock((Block)GrassLelyetiaDown, (String)"GrassLelyetiaDown");
    }
}

