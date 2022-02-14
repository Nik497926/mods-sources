/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package net.divinerpg.utils.items;

import net.divinerpg.items.base.ItemGravitySword;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.items.base.ItemPoisonousSword;
import net.divinerpg.items.base.ItemSlowingSword;
import net.divinerpg.items.vanilla.ItemBurningSword;
import net.divinerpg.libs.Effects;
import net.divinerpg.utils.items.ItemModSwordDemonic;
import net.divinerpg.utils.items.ItemSpectralSword;
import net.divinerpg.utils.material.ToolMaterialMod;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class JourneyItemsWeapon {
    public static Item sapphireSword = new ItemModSword(ToolMaterialMod.sapphireSword, "sapphireSword");
    public static Item shadiumSword = new ItemModSword(ToolMaterialMod.shadiumSword, "shadiumSword");
    public static Item luniumSword = new ItemModSword(ToolMaterialMod.luniumSword, "luniumSword");
    public static Item bloodcrustSword = new ItemModSword(ToolMaterialMod.bloodcrustSword, "bloodcrustSword");
    public static Item withicBlade = new ItemModSword(ToolMaterialMod.withicBlade, "withicBlade");
    public static Item celestiumSword = new ItemPoisonousSword(ToolMaterialMod.celestiumSword, "celestiumSword", 7.0f, 3.0f, 2.0f, 30);
    public static Item cloudSlicer = new ItemGravitySword(ToolMaterialMod.cloudSlicer, "cloudSlicer");
    public static Item poisonSword = new ItemPoisonousSword(ToolMaterialMod.poisonSword, "poisonSword", 2.0f);
    public static Item dragonsTooth = new ItemModSword(ToolMaterialMod.dragonsTooth, "dragonsTooth");
    public static Item royalBlade = new ItemModSword(ToolMaterialMod.royalBlade, "royalBlade");
    public static Item coreMender = new ItemBurningSword(ToolMaterialMod.coreMender, "coreMender", 3, Effects.INCINERATION, 15);
    public static Item netherBeastSword = new ItemBurningSword(ToolMaterialMod.netherBeastSword, "netherBeastSword", 10, Effects.BLINDNESS, 8);
    public static Item koriteSword = new ItemModSword(ToolMaterialMod.koriteSword, "koriteSword");
    public static Item mekyumSword = new ItemSlowingSword(ToolMaterialMod.mekyumSword, "mekyumSword", 60.0f, 0.0f);
    public static Item spectralSword = new ItemSpectralSword(ToolMaterialMod.spectralSword, "spectralSword").setTextureName("divinerpg:spectralSword").setCreativeTab((CreativeTabs)DivineRPGTabs.swords);
    public static Item swordcoll = new ItemModSword(ToolMaterialMod.swordcoll, "swordcoll").setTextureName("divinerpg:swordcoll").setCreativeTab((CreativeTabs)DivineRPGTabs.swords);
    public static Item PyroArchergun = new ItemModSword(ToolMaterialMod.charredKnife, "charredKnife").setTextureName("divinerpg:charredKnife").setCreativeTab((CreativeTabs)DivineRPGTabs.swords);
    public static Item Monster = new ItemModSwordDemonic(ToolMaterialMod.demonicSword, "demonicSword", 7.0f, 3.0f, 2.0f, 30).setTextureName("divinerpg:demonicSword").setCreativeTab((CreativeTabs)DivineRPGTabs.swords);

    public static void init() {
    }
}

