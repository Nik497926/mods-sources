/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package net.divinerpg.utils.items;

import net.divinerpg.items.base.ItemMod;
import net.divinerpg.items.euca.ItemPeculiarSmelting;
import net.divinerpg.items.vanilla.ItemNetherBeastOrb;
import net.divinerpg.utils.items.DarkBones;
import net.divinerpg.utils.items.ItemModSpectralEye;
import net.divinerpg.utils.items.LuckyPickaxe;
import net.divinerpg.utils.items.SpawnSozers;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class JourneyItemsOther {
    public static Item mekyumIngot;
    public static Item koriteIngot;
    public static Item storonIngot;
    public static Item shadiumDust;
    public static Item luniumDust;
    public static Item ash;
    public static Item bloodcrustIngot;
    public static Item bloodcrustClump;
    public static Item hellcrustIngot;
    public static Item flamingSpring;
    public static Item reinforcedStoneIngot;
    public static Item reinforcedCrystalIngot;
    public static Item stoneClump;
    public static Item caveCrystal;
    public static Item caveDust;
    public static Item sapphire;
    public static Item shadiumIngot;
    public static Item luniumIngot;
    public static Item celestiumIngot;
    public static Item withicDust;
    public static Item withicSpine;
    public static Item eucaPortalGem;
    public static Item celestiumDust;
    public static Item celestiumHandle;
    public static Item celestiumBlade;
    public static Item greenGem;
    public static Item purpleGem;
    public static Item blueGem;
    public static Item yellowGem;
    public static Item netherBeastOrb;
    public static Item gemOfPeculiarSmelting;
    public static Item boilGem;
    public static Item boilChunk;
    public static Item golderDust;
    public static Item goldClump;
    public static Item silverClump;
    public static Item HalcyonBeef;
    public static Item ActivationPortals;
    public static Item SpectralStone;
    public static Item MakerPortalSpectral;
    public static Item SpectralDust;
    public static Item BaronessSpawner;
    public static Item SpectralEye;
    public static Item CutaliisStone;
    public static Item FoodStone;
    public static Item ItemRestoreHealth;
    public static Item sozerItemActv;
    public static Item LuckyPickaxe;
    public static Item guardiansEye;
    public static Item doneSoul;
    public static Item DarkBones;
    public static Item RealmstoneLelyetia;

    public static void init() {
    }

    static {
        ash = new ItemMod("ash");
        LuckyPickaxe = new LuckyPickaxe("infinity_pickaxe").setTextureName("divinerpg:infinity_pickaxe").setFull3D();
        SpectralEye = new ItemModSpectralEye("spectralEye");
        SpectralDust = new ItemMod("spectraldust");
        mekyumIngot = new ItemMod("mekyumIngot");
        koriteIngot = new ItemMod("koriteIngot");
        storonIngot = new ItemMod("storonIngot");
        DarkBones = new DarkBones("darkBones");
        doneSoul = new ItemMod("nethengeicCallstone");
        shadiumDust = new ItemMod("shadiumDust");
        luniumDust = new ItemMod("luniumDust");
        sozerItemActv = new SpawnSozers("sozerItemActv").setTextureName("divinerpg:sozerItemActv").setCreativeTab((CreativeTabs)DivineRPGTabs.spawner);
        bloodcrustIngot = new ItemMod("bloodcrustIngot");
        bloodcrustClump = new ItemMod("bloodcrustClump");
        hellcrustIngot = new ItemMod("hellcrustIngot");
        flamingSpring = new ItemMod("flamingSpring");
        reinforcedStoneIngot = new ItemMod("reinforcedStoneIngot");
        reinforcedCrystalIngot = new ItemMod("reinforcedCrystalIngot");
        stoneClump = new ItemMod("stoneClump");
        caveCrystal = new ItemMod("caveCrystal");
        caveDust = new ItemMod("caveDust");
        sapphire = new ItemMod("sapphire");
        shadiumIngot = new ItemMod("shadiumIngot");
        luniumIngot = new ItemMod("luniumIngot");
        celestiumIngot = new ItemMod("celestiumIngot");
        withicDust = new ItemMod("withicDust");
        withicSpine = new ItemMod("withicSpine");
        golderDust = new ItemMod("golderDust");
        silverClump = new ItemMod("silverClump");
        goldClump = new ItemMod("goldClump");
        eucaPortalGem = new ItemMod("eucaPortalGem");
        celestiumDust = new ItemMod("celestiumDust");
        celestiumHandle = new ItemMod("celestiumHandle");
        celestiumBlade = new ItemMod("celestiumBlade");
        greenGem = new ItemMod("greenGem");
        purpleGem = new ItemMod("purpleGem");
        blueGem = new ItemMod("blueGem");
        yellowGem = new ItemMod("yellowGem");
        netherBeastOrb = new ItemNetherBeastOrb("netherBeastOrb");
        gemOfPeculiarSmelting = new ItemPeculiarSmelting("corallatorOrb");
        boilGem = new ItemMod("boilGem");
        boilChunk = new ItemMod("boilChunk");
    }
}

