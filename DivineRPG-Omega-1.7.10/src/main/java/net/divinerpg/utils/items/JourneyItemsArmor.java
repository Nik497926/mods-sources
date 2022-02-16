/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 */
package net.divinerpg.utils.items;

import net.divinerpg.items.base.ItemDivineArmor;
import net.divinerpg.items.base.ItemSpeedArmor;
import net.divinerpg.utils.enums.ArmorInfo;
import net.divinerpg.utils.material.EnumArmor;
import net.minecraft.item.Item;

public class JourneyItemsArmor {
    public static int HEAD = 0;
    public static int BODY = 1;
    public static int LEGS = 2;
    public static int BOOTS = 3;
    private static Object[] sapphireInfo = new Object[]{Float.valueOf(1.2f), ArmorInfo.HASTE};
    public static Item sapphireHelmet = new ItemDivineArmor(EnumArmor.SAPPHIRE, HEAD, sapphireInfo);
    public static Item sapphireChestplate = new ItemDivineArmor(EnumArmor.SAPPHIRE, BODY, sapphireInfo);
    public static Item sapphireLeggings = new ItemDivineArmor(EnumArmor.SAPPHIRE, LEGS, sapphireInfo);
    public static Item sapphireBoots = new ItemDivineArmor(EnumArmor.SAPPHIRE, BOOTS, sapphireInfo);
    private static Object[] shadiumInfo = new Object[]{ArmorInfo.UNDERWATER};
    public static Item shadiumHelmet = new ItemDivineArmor(EnumArmor.SHADIUM, HEAD, shadiumInfo);
    public static Item shadiumChestplate = new ItemDivineArmor(EnumArmor.SHADIUM, BODY, shadiumInfo);
    public static Item shadiumLeggings = new ItemDivineArmor(EnumArmor.SHADIUM, LEGS, shadiumInfo);
    public static Item shadiumBoots = new ItemDivineArmor(EnumArmor.SHADIUM, BOOTS, shadiumInfo);
    private static Object[] luniumInfo = new Object[]{20, ArmorInfo.RANGED_DAMAGE, ArmorInfo.UNDERWATER};
    public static Item luniumHelmet = new ItemDivineArmor(EnumArmor.LUNIUM, HEAD, luniumInfo);
    public static Item luniumChestplate = new ItemDivineArmor(EnumArmor.LUNIUM, BODY, luniumInfo);
    public static Item luniumLeggings = new ItemDivineArmor(EnumArmor.LUNIUM, LEGS, luniumInfo);
    public static Item luniumBoots = new ItemDivineArmor(EnumArmor.LUNIUM, BOOTS, luniumInfo);
    private static Object[] bloodcrustInfo = new Object[]{6, ArmorInfo.MELEE_DAMAGE, ArmorInfo.FIRE_PROTECTION, ArmorInfo.WITHER_PROTECTION};
    public static Item bloodcrustHelmet = new ItemDivineArmor(EnumArmor.BLOODCRUST, HEAD, bloodcrustInfo);
    public static Item bloodcrustChestplate = new ItemDivineArmor(EnumArmor.BLOODCRUST, BODY, bloodcrustInfo);
    public static Item bloodcrustLeggings = new ItemDivineArmor(EnumArmor.BLOODCRUST, LEGS, bloodcrustInfo);
    public static Item bloodcrustBoots = new ItemDivineArmor(EnumArmor.BLOODCRUST, BOOTS, bloodcrustInfo);
    private static Object[] celestiumInfo = new Object[]{8, ArmorInfo.MELEE_DAMAGE, 2, ArmorInfo.JUMP_HEIGHT, 2, ArmorInfo.SPEED};
    public static Item celestiumHelmet = new ItemSpeedArmor(EnumArmor.CELESTIUM, HEAD, celestiumInfo, 0.5f);
    public static Item celestiumChestplate = new ItemSpeedArmor(EnumArmor.CELESTIUM, BODY, celestiumInfo, 0.5f);
    public static Item celestiumLeggings = new ItemSpeedArmor(EnumArmor.CELESTIUM, LEGS, celestiumInfo, 0.5f);
    public static Item celestiumBoots = new ItemSpeedArmor(EnumArmor.CELESTIUM, BOOTS, celestiumInfo, 0.5f);
    private static Object[] CoolSetInfo = new Object[]{9, ArmorInfo.MELEE_DAMAGE};
    public static Item CoolSetHelmet = new ItemSpeedArmor(EnumArmor.COOL, HEAD, CoolSetInfo, 0.5f);
    public static Item CoolSetChestplate = new ItemSpeedArmor(EnumArmor.COOL, BODY, CoolSetInfo, 0.5f);
    public static Item CoolSetLeggings = new ItemSpeedArmor(EnumArmor.COOL, LEGS, CoolSetInfo, 0.5f);
    public static Item CoolSetBoots = new ItemSpeedArmor(EnumArmor.COOL, BOOTS, CoolSetInfo, 0.5f);
    private static Object[] lightstoneInfo = new Object[]{18, ArmorInfo.MELEE_DAMAGE, ArmorInfo.ARCANA_REGENPLUS};
    public static Item lightstoneHelmet = new ItemSpeedArmor(EnumArmor.LIGHTSTONE, HEAD, lightstoneInfo, 0.5f);
    public static Item lightstoneChestplate = new ItemSpeedArmor(EnumArmor.LIGHTSTONE, BODY, lightstoneInfo, 0.5f);
    public static Item lightstoneLeggings = new ItemSpeedArmor(EnumArmor.LIGHTSTONE, LEGS, lightstoneInfo, 0.5f);
    public static Item lightstoneBoots = new ItemSpeedArmor(EnumArmor.LIGHTSTONE, BOOTS, lightstoneInfo, 0.5f);
    private static Object[] flameInfo = new Object[]{12, ArmorInfo.MELEE_DAMAGE, ArmorInfo.FLAME_EFF, ArmorInfo.FLAME_EFF2};
    public static Item flameHelmet = new ItemDivineArmor(EnumArmor.FLAME, HEAD, flameInfo);
    public static Item flameChestPlate = new ItemDivineArmor(EnumArmor.FLAME, BODY, flameInfo);
    public static Item flameLeggings = new ItemDivineArmor(EnumArmor.FLAME, LEGS, flameInfo);
    public static Item flameBoots = new ItemDivineArmor(EnumArmor.FLAME, BOOTS, flameInfo);

    public static void init() {
    }
}

