/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 */
package net.divinerpg.utils.material;

import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.IceikaItems;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public enum EnumArmor {
    ANGELIC(Util.addArmorMaterial("Angelic", 0, 10), "angelic", true, 57),
    DIVINE(Util.addArmorMaterial("Divine", 10000, 10, VanillaItemsOther.divineStone), "divine", false, 77),
    BEDROCK(Util.addArmorMaterial("Bedrock", 10000, 10, Item.getItemFromBlock((Block)Blocks.bedrock)), "bedrock", false, 65),
    REALMITE(Util.addArmorMaterial("Realmite", 5000, 10, VanillaItemsOther.realmiteIngot), "realmite", false, 47),
    ELITE_REALMITE(Util.addArmorMaterial("Elite Realmite", 0, 10), "eliteRealmite", true, 62),
    ARLEMITE(Util.addArmorMaterial("Arlemite", 0, 22), "arlemite", true, 61),
    RUPEE(Util.addArmorMaterial("Rupee", 0, 10), "rupee", true, 61),
    RUPEE_YELLOW(Util.addArmorMaterial("Rupee", 0, 10), "yellowRupee", true, 61),
    RUPEE_RED(Util.addArmorMaterial("Rupee", 0, 10), "redRupee", true, 61),
    RUPEE_GREEN(Util.addArmorMaterial("Rupee", 0, 10), "greenRupee", true, 61),
    RUPEE_GRAY(Util.addArmorMaterial("Rupee", 0, 10), "grayRupee", true, 61),
    LIGHTSTONE(Util.addArmorMaterial("lightstone", 0, 18, null), "lightstone", true, 81),
    RUPEE_BLUE(Util.addArmorMaterial("Rupee", 0, 10), "blueRupee", true, 61),
    KRAKEN(Util.addArmorMaterial("Kraken", 5000, 10, VanillaItemsOther.krakenSkin), "kraken", false, 57),
    WITHER_REAPER(Util.addArmorMaterial("Wither Reaper", 0, 22), "witherReaper", true, 0),
    SKELEMAN(Util.addArmorMaterial("Skeleman", 0, 22), "skeleman", true, 0),
    JACKOMAN(Util.addArmorMaterial("Jack O Man", 0, 22), "jackOMan", true, 0),
    INFERNO(Util.addArmorMaterial("Inferno", 6500, 10), "inferno", true, 59),
    AQUATIC(Util.addArmorMaterial("Aquastrive", 0, 10), "aquastrive", true, 657),
    SHADOW(Util.addArmorMaterial("Shadow", 0, 10), "shadow", true, 71),
    COOL(Util.addArmorMaterial("coolarmor", 0, 10), "coolarmor", true, 81),
    NETHERITE(Util.addArmorMaterial("Netherite", 5000, 10, VanillaItemsOther.netheriteIngot), "netherite", false, 55),
    ENDER(Util.addArmorMaterial("Ender", 7500, 10, VanillaItemsOther.enderStone), "ender", false, 63),
    ENDER_YELLOW(Util.addArmorMaterial("Ender", 7500, 10, VanillaItemsOther.enderStone), "yellowEnder", false, 63),
    ENDER_RED(Util.addArmorMaterial("Ender", 7500, 10, VanillaItemsOther.enderStone), "redEnder", false, 63),
    ENDER_GREEN(Util.addArmorMaterial("Ender", 7500, 10, VanillaItemsOther.enderStone), "greenEnder", false, 63),
    ENDER_GRAY(Util.addArmorMaterial("Ender", 7500, 10, VanillaItemsOther.enderStone), "grayEnder", false, 63),
    ENDER_BLUE(Util.addArmorMaterial("Ender", 7500, 10, VanillaItemsOther.enderStone), "blueEnder", false, 63),
    JUNGLE(Util.addArmorMaterial("Jungle", 5000, 10, VanillaItemsOther.jungleStone), "jungle", false, 63),
    FROZEN(Util.addArmorMaterial("Frozen", 3000, 10, IceikaItems.iceStone), "frozen", false, 64),
    CORRUPTED(Util.addArmorMaterial("Corrupted", 5000, 10, VanillaItemsOther.corruptedStone), "corrupted", false, 66),
    TERRAN(Util.addArmorMaterial("Terran", 3000, 10, VanillaItemsOther.terranStone), "terran", false, 65),
    EDEN(Util.addArmorMaterial("Eden", 0, 10), "eden", true, 69),
    WILD(Util.addArmorMaterial("Wild", 0, 10), "wildwood", true, 71),
    APALACHIA(Util.addArmorMaterial("Apalachia", 0, 10), "apalachia", true, 73),
    SKYTHERN(Util.addArmorMaterial("Skythern", 0, 10), "skythern", true, 75),
    MORTUM(Util.addArmorMaterial("Mortum", 0, 10), "mortum", true, 77),
    HALITE(Util.addArmorMaterial("Halite", 0, 12), "halite", true, 79),
    SANTA(Util.addArmorMaterial("Santa", 0, 10), "santa", true, 0, true),
    KORMA(Util.addArmorMaterial("Korma", 0, 22), "korma", true, 55),
    VEMOS(Util.addArmorMaterial("Vemos", 0, 22), "vemos", true, 62),
    DIAMOND(Util.addArmorMaterial("Diamond", 3000, 10), "diamond", false, 52),
    IRON(Util.addArmorMaterial("Iron", 2000, 12), "iron", false, 41),
    GOLD(Util.addArmorMaterial("Gold", 1000, 10), "gold", false, 37),
    DEGRADED(Util.addArmorMaterial("Degraded", 0, 0), "degraded", true, 14),
    FINISHED(Util.addArmorMaterial("Finished", 0, 0), "finished", true, 30),
    GLISTENING(Util.addArmorMaterial("Glistening", 0, 0), "glistening", true, 48),
    DEMONIZED(Util.addArmorMaterial("Demonized", 0, 0), "demonized", true, 58),
    TORMENTED(Util.addArmorMaterial("Tormented", 0, 0), "tormented", true, 55),
    AMAZONITE(Util.addArmorMaterial("Amazonite", 0, 10), "amazonite", true, 66),
    COPPER(Util.addArmorMaterial("Copper", 1500, 6), "copper", false, 52),
    NIGHTMARE(Util.addArmorMaterial("Nightmare", 5000, 11), "nightmare", false, 67),
    NETHERIUM(Util.addArmorMaterial("Netherium", 0, 15), "netherium", true, 77),
    AWAKEN(Util.addArmorMaterial("Awaken", 0, 18), "awakened", true, 80),
    AWAKEN_PREM(Util.addArmorMaterial("AwakenPrem", 0, 18), "awakenedprem", true, 81),
    FLAME(Util.addArmorMaterial("Flame", 0, 60), "flame", true, 82),
    ARCANA(Util.addArmorMaterial("Arcana", 0, 18), "arcanium", true, 77),
    ARCHER(Util.addArmorMaterial("Bow", 0, 18), "bow", true, 65),
    SAPPHIRE(Util.addArmorMaterial("Sapphire", 2000, 18, JourneyItemsOther.sapphire), "sapphire", false, 62),
    SHADIUM(Util.addArmorMaterial("Shadium", 2200, 18, JourneyItemsOther.shadiumIngot), "shadium", false, 66),
    LUNIUM(Util.addArmorMaterial("Lunium", 2000, 18, JourneyItemsOther.luniumIngot), "lunium", false, 64),
    BLOODCRUST(Util.addArmorMaterial("Bloodcrust", 3000, 18, JourneyItemsOther.hellcrustIngot), "bloodcrust", false, 72),
    DARK(Util.addArmorMaterial("Dark", 7000, 18, VanillaItemsOther.darkStone), "dark", false, 70),
    LIGHT(Util.addArmorMaterial("Light", 1150, 28, VanillaItemsOther.orbOfLight), "light", false, 73),
    CELESTIUM(Util.addArmorMaterial("Celestium", 4000, 18, JourneyItemsOther.celestiumIngot), "celestium", false, 79);

    private ItemArmor.ArmorMaterial armorMaterial;
    private String type;
    private boolean undamageable;
    private int damageReduction;
    private boolean clothing;

    private EnumArmor(ItemArmor.ArmorMaterial armorMaterial, String type, boolean undamageable, int damageReduction) {
        this.armorMaterial = armorMaterial;
        this.type = type;
        this.undamageable = undamageable;
        this.damageReduction = damageReduction;
        this.clothing = false;
    }

    private EnumArmor(ItemArmor.ArmorMaterial armorMaterial, String type, boolean undamageable, int damageReduction, boolean clothing) {
        this.armorMaterial = armorMaterial;
        this.type = type;
        this.undamageable = undamageable;
        this.damageReduction = damageReduction;
        this.clothing = clothing;
    }

    public ItemArmor.ArmorMaterial getArmorMaterial() {
        return this.armorMaterial;
    }

    public String getType() {
        return this.type;
    }

    public boolean isUndamageable() {
        return this.undamageable;
    }

    public int getDamageReduction() {
        return this.damageReduction;
    }

    public boolean isClothing() {
        return this.clothing;
    }
}

