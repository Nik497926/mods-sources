/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.util.StatCollector
 */
package net.divinerpg.utils;

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
    protected static final String BURNS = "tooltip.effect.burns";
    protected static final String SLOWS = "tooltip.effect.slows";

    public static String usesRemaining(int uses) {
        return StatCollector.translateToLocal((String)USES).replace("#", String.valueOf(uses));
    }

    public static String arcanaConsumed(int ar) {
        return StatCollector.translateToLocal((String)ARCANA).replace("#", String.valueOf(ar));
    }

    public static String meleeDam(double dam) {
        return StatCollector.translateToLocal((String)MELEEDAM).replace("#", String.valueOf(dam));
    }

    public static String rangedDam(double dam) {
        return StatCollector.translateToLocal((String)RANGEDDAM).replace("#", String.valueOf(dam));
    }

    public static String bowDam(String dam) {
        return StatCollector.translateToLocal((String)RANGEDDAM).replace("#", dam);
    }

    public static String arcanaDam(double dam) {
        return StatCollector.translateToLocal((String)ARCANADAM).replace("#", String.valueOf(dam));
    }

    public static String ammo(Item ammo) {
        return StatCollector.translateToLocal((String)AMMO).replace("|item|", StatCollector.translateToLocal((String)(ammo.getUnlocalizedName() + ".name")));
    }

    public static String infiniteAmmo() {
        return StatCollector.translateToLocal((String)INFINITEAMMO);
    }

    public static String infiniteUses() {
        return StatCollector.translateToLocal((String)INFINITEUSES);
    }

    public static String rangedAndMelee(double dam) {
        return StatCollector.translateToLocal((String)RANGEDNMELEE).replace("#", String.valueOf(dam));
    }

    public static String arcanaRegen(int ar) {
        return StatCollector.translateToLocal((String)ARCANAREGEN).replace("#", String.valueOf(ar));
    }

    public static String explosiveShots() {
        return StatCollector.translateToLocal((String)EXPLOSIVESHOTS);
    }

    public static String homingShots() {
        return StatCollector.translateToLocal((String)HOMINGSHOTS);
    }

    public static String damageReduction(double reduct, double fullReduct) {
        return StatCollector.translateToLocal((String)DAMAGEREDUCTION).replace("|percent|", reduct + "%").replace("|percentFull|", fullReduct + "%");
    }

    public static String fullsetPerks() {
        return StatCollector.translateToLocal((String)FULLSETPERKS);
    }

    public static String noProtection() {
        return StatCollector.translateToLocal((String)NOPROT);
    }

    public static String vethean() {
        return Util.GREEN + StatCollector.translateToLocal((String)VETHEAN);
    }

    public static String cantBlock() {
        return StatCollector.translateToLocal((String)CANTBLOCK);
    }

    public static String efficiency(double eff) {
        return StatCollector.translateToLocal((String)EFFICIENCY).replace("#", String.valueOf(eff));
    }

    public static String poison(float seconds) {
        return StatCollector.translateToLocal((String)POISON).replace("|seconds|", String.valueOf(seconds));
    }

    public static String burn(int seconds) {
        return StatCollector.translateToLocal((String)BURNS).replace("|seconds|", String.valueOf(seconds));
    }

    public static String slow(double seconds) {
        return StatCollector.translateToLocal((String)SLOWS).replace("|seconds|", String.valueOf(seconds));
    }
}

