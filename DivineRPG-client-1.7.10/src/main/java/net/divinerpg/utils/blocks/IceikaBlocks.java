/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 */
package net.divinerpg.utils.blocks;

import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.blocks.base.BlockModBook;
import net.divinerpg.blocks.base.BlockModDoor;
import net.divinerpg.blocks.base.BlockModGlass;
import net.divinerpg.blocks.base.BlockModGrass;
import net.divinerpg.blocks.base.BlockModLeaves;
import net.divinerpg.blocks.base.BlockModLog;
import net.divinerpg.blocks.base.BlockModSlab;
import net.divinerpg.blocks.base.BlockModSpawner;
import net.divinerpg.blocks.base.BlockModStairs;
import net.divinerpg.blocks.base.portal.BlockModPortal;
import net.divinerpg.blocks.iceika.BlockCandyCane;
import net.divinerpg.blocks.iceika.BlockCoalstoneFurnace;
import net.divinerpg.blocks.iceika.BlockFrostedChest;
import net.divinerpg.blocks.iceika.BlockIceikaFire;
import net.divinerpg.blocks.iceika.BlockPresentBox;
import net.divinerpg.blocks.iceika.BlockWinterberryBush;
import net.divinerpg.blocks.iceika.BlockXmasLights;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class IceikaBlocks {
    public static Block frozenDirt = new BlockMod(EnumBlockType.DIRT, "frozenDirt", 2.5f);
    public static Block frozenGrass = new BlockModGrass((BlockMod)frozenDirt, "frozenGrass", "frozenDirt", 2.5f);
    public static Block frozenStone = new BlockMod("frozenStone", 6.0f);
    public static Block frozenWood = new BlockModLog("frozenLog").setHardness(5.0f);
    public static Block brittleLeaves = new BlockModLeaves("brittleLeaves", 0.1f);
    public static Block frozenPlanks = new BlockMod(EnumBlockType.WOOD, "frozenPlanks", 6.0f).setResistance(5.0f);
    public static Block frozenStairs = new BlockModStairs(frozenPlanks, "frozenStairs");
    public static Block frozenSlab = new BlockModSlab((BlockMod)frozenPlanks, false, "Planks", null);
    public static Block frozenSlabDouble = new BlockModSlab((BlockMod)frozenPlanks, true, "Planks", frozenSlab);
    public static Block winterberryBush = new BlockWinterberryBush(false, "winterberryBush").setHardness(0.2f);
    public static Block winterberryBushRipe = new BlockWinterberryBush(true, "ripeWinterberryBush").setHardness(0.2f);
    public static Block icyStone = new BlockMod("icyStone", -1.0f);
    public static Block icyBricks = new BlockMod("icyBricks", -1.0f);
    public static Block frostedChest = new BlockFrostedChest("frostedChest", true).setHardness(2.5f);
    public static Block decorativeFrostedChest = new BlockFrostedChest("decorativeFrostedChest", false).setHardness(2.5f);
    public static Block rollumSpawner = new BlockModSpawner("rollumSpawner", "Rollum").setHardness(5.0f);
    public static Block frostArcherSpawner = new BlockModSpawner("frostArcherSpawner", "FrostArcher").setHardness(5.0f);
    public static Block snowBricks = new BlockMod("snowBricks", 6.0f);
    public static Block coalstone = new BlockMod("coalstone", 3.0f);
    public static Block coalstoneStairs = new BlockModStairs(coalstone, "coalstoneStairs");
    public static Block workshopCarpet = new BlockMod(EnumBlockType.WOOL, "workshopCarpet", 0.1f);
    public static Block frostedGlass = new BlockModGlass("frostedGlass", 1.0f);
    public static Block workshopBookcase = new BlockModBook(EnumBlockType.ROCK, "workshopBookcase").setHardness(1.5f);
    public static Block workshopLamp = new BlockMod(EnumBlockType.GLASS, "workshopLamp", 0.3f).setLightLevel(1.0f);
    public static Block steelDoorBlock = new BlockModDoor(EnumBlockType.IRON, "steelDoorBlock", 7.0f, true);
    public static Block coalstoneFurnace = new BlockCoalstoneFurnace("coalstoneFurnace", false);
    public static Block coalstoneFurnaceOn = new BlockCoalstoneFurnace("coalstoneFurnaceActive", true);
    public static Block redXmasLights = new BlockXmasLights("redChristmasLights");
    public static Block greenXmasLights = new BlockXmasLights("greenChristmasLights");
    public static Block blueXmasLights = new BlockXmasLights("blueChristmasLights");
    public static Block yellowXmasLights = new BlockXmasLights("yellowChristmasLights");
    public static Block purpleXmasLights = new BlockXmasLights("purpleChristmasLights");
    public static Block candyCane = new BlockCandyCane();
    public static Block presentBox = new BlockPresentBox();
    public static Block iceikaFire = new BlockIceikaFire("iceikaFire");
    public static Block iceikaPortal = new BlockModPortal("iceikaPortal", ConfigurationHelper.iceika, iceikaFire, Blocks.snow);

    public static void init() {
        Util.registerSlab(frozenSlab, frozenSlabDouble);
    }
}

