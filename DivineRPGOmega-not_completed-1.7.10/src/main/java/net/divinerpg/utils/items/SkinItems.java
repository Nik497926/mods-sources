/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 */
package net.divinerpg.utils.items;

import net.divinerpg.items.base.ItemDivineArmor;
import net.divinerpg.items.base.ItemFullSword;
import net.divinerpg.items.base.ItemHealingSword;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.items.base.ItemPoisonousSword;
import net.divinerpg.utils.enums.ArmorInfo;
import net.divinerpg.utils.material.EnumArmor;
import net.divinerpg.utils.material.ToolMaterialMod;
import net.minecraft.item.Item;

public class SkinItems {
    public static int HEAD = 0;
    public static int BODY = 1;
    public static int LEGS = 2;
    public static int BOOTS = 3;
    public static Item redHaliteBlade = new ItemModSword(ToolMaterialMod.Halite, "redHaliteBlade", "redHalite");
    public static Item lunaThunderbirdSword = new ItemFullSword("lunaThunderbirdSword", ToolMaterialMod.premregensword, "thunderbird", 200, 3.5);
    public static Item celestiumpremSword = new ItemPoisonousSword(ToolMaterialMod.premcelestiumSword, "celestiumpremSword", 7.0f, 4.0f, 2.0f, 20, "fire");
    public static Item premfrossivence = new ItemHealingSword("premfrossivence", ToolMaterialMod.Frossivence, 1.7f, "enderCrystal");
    private static Object[] awakenedpremInfo = new Object[]{11, ArmorInfo.MELEE_DAMAGE};
    public static Item awakenedpremHelmet = new ItemDivineArmor(EnumArmor.AWAKEN_PREM, HEAD, awakenedpremInfo, "breathOfTheEnd");
    public static Item awakenedpremChestplate = new ItemDivineArmor(EnumArmor.AWAKEN_PREM, BODY, awakenedpremInfo, "breathOfTheEnd");
    public static Item awakenedpremLeggings = new ItemDivineArmor(EnumArmor.AWAKEN_PREM, LEGS, awakenedpremInfo, "breathOfTheEnd");
    public static Item awakenedpremBoots = new ItemDivineArmor(EnumArmor.AWAKEN_PREM, BOOTS, awakenedpremInfo, "breathOfTheEnd");

    public static void init() {
    }
}

