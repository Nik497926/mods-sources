/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.item.Item
 *  net.minecraft.util.StatCollector
 */
package net.divinerpg.utils;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.divinerpg.utils.UsesColor;
import net.divinerpg.utils.Util;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;

public class TooltipLocalizer {
    protected static final String USES = "tooltip.uses";
    protected static final String INFINITEUSES = "tooltip.uses.infinite";
    protected static final String ARCANA = "tooltip.arcana";
    protected static final String ARCANAREGEN = "tooltip.arcana.regen";
    protected static final String MELEEDAM = "tooltip.damage.melee";
    protected static final String RANGEDDAM = "tooltip.damage.ranged";
    protected static final String RANGEDNMELEE = "tooltip.damage.both";
    protected static final String ARCANADAM = "tooltip.damage.arcana";
    protected static final String DAMAGEREDUCTION = "tooltip.damage.reduction";
    protected static final String AMMO = "tooltip.ammo";
    protected static final String INFINITEAMMO = "tooltip.ammo.infinite";
    protected static final String EXPLOSIVESHOTS = "tooltip.shots.explosive";
    protected static final String HOMINGSHOTS = "tooltip.shots.homing";
    protected static final String FULLSETPERKS = "tooltip.fullset";
    protected static final String NOPROT = "tooltip.noprotection";
    protected static final String VETHEAN = "tooltip.vethean";
    protected static final String EFFICIENCY = "tooltip.efficiency";
    protected static final String CANTBLOCK = "tooltip.noblock";
    protected static final String POISON = "tooltip.effect.poisons";
    protected static final String POISONARROW = "tooltip.effect.poisonarrow";
    protected static final String WITHERARROW = "tooltip.effect.witherarrow";
    protected static final String BURNS = "tooltip.effect.burns";
    protected static final String COOLDOWN = "tooltip.cooldown";
    protected static final String SLOWS = "tooltip.effect.slows";
    protected static final String WITHER = "tooltip.effect.wither";
    protected static final String HARMS = "tooltip.effect.harms";
    protected static final String BLINDNESS = "tooltip.effect.blindness";
    protected static final String INCINERATION = "tooltip.effect.incineration";
    protected static final String ANTIGRAVITY = "tooltip.effect.antigravity";
    protected static final String FOOD = "tooltip.food";
    protected static final String SATURATION = "tooltip.saturation";
    protected static final String LIGHTNING = "tooltip.effect.lightning";

    public static String usesRemaining(int uses, double max) {
        return StatCollector.translateToLocal((String)USES).replace("#", UsesColor.setColor(uses, max) + String.valueOf(uses));
    }

    public static String getSkin(String skinName) {
        return Util.COLORED + StatCollector.translateToLocal((String)("item.skin." + skinName));
    }

    public static String arcanaConsumed(int ar) {
        return StatCollector.translateToLocal((String)ARCANA).replace("#", Util.BLUE + String.valueOf(ar));
    }

    public static String meleeDam(double dam) {
        String out = Double.toString(dam);
        if (out.contains(".0")) {
            out = out.replace(".0", "");
        }
        return StatCollector.translateToLocal((String)MELEEDAM).replace("#", Util.RED + out);
    }

    public static String meleeDam(String dam) {
        String out = dam;
        if (out.contains(".0")) {
            out = out.replace(".0", "");
        }
        return StatCollector.translateToLocal((String)MELEEDAM).replace("#", Util.RED + out);
    }

    public static String rangedDam(double dam) {
        String out = Double.toString(dam);
        if (out.contains(".0")) {
            out = out.replace(".0", "");
        }
        return StatCollector.translateToLocal((String)RANGEDDAM).replace("#", Util.GREEN + out);
    }

    public static String bowDam(String dam) {
        return StatCollector.translateToLocal((String)RANGEDDAM).replace("#", Util.GREEN + dam);
    }

    public static String arcanaDam(double dam) {
        return StatCollector.translateToLocal((String)ARCANADAM).replace("#", Util.DARK_BLUE + String.valueOf((int)dam));
    }

    public static String cooldown(double cooldown) {
        String out = Double.toString(cooldown);
        if (out.contains(".0")) {
            out = out.replace(".0", "");
        }
        return StatCollector.translateToLocal((String)COOLDOWN).replace("#", Util.YELLOW + out);
    }

    public static String ammo(Item ammo) {
        return StatCollector.translateToLocal((String)AMMO).replace("|item|", Util.WHITE + StatCollector.translateToLocal((String)(ammo.getUnlocalizedName() + ".name")));
    }

    public static String infiniteAmmo() {
        return Util.GREEN + ChatFormatting.ITALIC + StatCollector.translateToLocal((String)INFINITEAMMO);
    }

    public static String infiniteUses() {
        return Util.GREEN + ChatFormatting.ITALIC + StatCollector.translateToLocal((String)INFINITEUSES);
    }

    public static String rangedAndMelee(double dam) {
        return StatCollector.translateToLocal((String)RANGEDNMELEE).replace("#", Util.GOLD + String.valueOf(dam));
    }

    public static String arcanaRegen(int ar) {
        return StatCollector.translateToLocal((String)ARCANAREGEN).replace("#", Util.DARK_BLUE + String.valueOf(ar));
    }

    public static String explosiveShots() {
        return Util.GOLD + StatCollector.translateToLocal((String)EXPLOSIVESHOTS);
    }

    public static String poisonShots() {
        return Util.GOLD + StatCollector.translateToLocal((String)POISONARROW);
    }

    public static String witherShots() {
        return Util.GOLD + StatCollector.translateToLocal((String)WITHERARROW);
    }

    public static String homingShots() {
        return Util.GOLD + StatCollector.translateToLocal((String)HOMINGSHOTS);
    }

    public static String damageReduction(double reduct, double fullReduct) {
        return StatCollector.translateToLocal((String)DAMAGEREDUCTION).replace("|percent|", Util.YELLOW + reduct + "%" + Util.WHITE).replace("|percentFull|", Util.YELLOW + fullReduct + "%" + Util.WHITE);
    }

    public static String fullsetPerks() {
        return Util.WHITE + StatCollector.translateToLocal((String)FULLSETPERKS);
    }

    public static String noProtection() {
        return Util.GOLD + StatCollector.translateToLocal((String)NOPROT);
    }

    public static String vethean() {
        return Util.GREEN + StatCollector.translateToLocal((String)VETHEAN);
    }

    public static String cantBlock() {
        return Util.GOLD + StatCollector.translateToLocal((String)CANTBLOCK);
    }

    public static String efficiency(double eff) {
        return Util.GOLD + StatCollector.translateToLocal((String)EFFICIENCY).replace("#", String.valueOf((int)eff));
    }

    public static String poison(float seconds) {
        String out = Float.toString(seconds);
        if (out.contains(".0")) {
            out = out.replace(".0", "");
        }
        return Util.GOLD + StatCollector.translateToLocal((String)POISON).replace("|seconds|", out);
    }

    public static String burn(int seconds) {
        return Util.GOLD + StatCollector.translateToLocal((String)BURNS).replace("|seconds|", String.valueOf(seconds));
    }

    public static String slow(double seconds) {
        String out = Double.toString(seconds);
        if (out.contains(".0")) {
            out = out.replace(".0", "");
        }
        return Util.GOLD + StatCollector.translateToLocal((String)SLOWS).replace("|seconds|", out);
    }

    public static String wither(double seconds) {
        String out = Double.toString(seconds);
        if (out.contains(".0")) {
            out = out.replace(".0", "");
        }
        return Util.GOLD + StatCollector.translateToLocal((String)WITHER).replace("|seconds|", out);
    }

    public static String harms(double seconds) {
        String out = Double.toString(seconds);
        if (out.contains(".0")) {
            out = out.replace(".0", "");
        }
        return Util.GOLD + StatCollector.translateToLocal((String)HARMS).replace("|seconds|", out);
    }

    public static String antigravity(double seconds) {
        String out = Double.toString(seconds);
        if (out.contains(".0")) {
            out = out.replace(".0", "");
        }
        return Util.GOLD + StatCollector.translateToLocal((String)ANTIGRAVITY).replace("|seconds|", out);
    }

    public static String blindness(float seconds) {
        String out = Double.toString(seconds);
        if (out.contains(".0")) {
            out = out.replace(".0", "");
        }
        return Util.GOLD + StatCollector.translateToLocal((String)BLINDNESS).replace("|seconds|", out);
    }

    public static String incineration(double seconds) {
        String out = Double.toString(seconds);
        if (out.contains(".0")) {
            out = out.replace(".0", "");
        }
        return Util.GOLD + StatCollector.translateToLocal((String)INCINERATION).replace("|seconds|", out);
    }

    public static String hunger(float hunger) {
        String out = Float.toString(hunger);
        if (out.contains(".0")) {
            out = out.replace(".0", "");
        }
        return StatCollector.translateToLocal((String)FOOD).replace("|hunger|", Util.GOLD + out + Util.GRAY);
    }

    public static String saturation(float saturation) {
        String out = Float.toString(saturation);
        if (out.contains(".0")) {
            out = out.replace(".0", "");
        }
        return StatCollector.translateToLocal((String)SATURATION).replace("|saturation|", Util.YELLOW + out + Util.GRAY);
    }

    public static String lightning() {
        return Util.GOLD + StatCollector.translateToLocal((String)LIGHTNING);
    }
}

