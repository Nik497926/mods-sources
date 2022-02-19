/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StatCollector
 */
package net.divinerpg.utils.enums;

import net.minecraft.util.StatCollector;

public enum ArmorInfo {
    FLAME_EFF(StatCollector.translateToLocal((String)"tooltip.flame")),
    FLAME_EFF2(StatCollector.translateToLocal((String)"tooltip.flame2")),
    DARK_EFF(StatCollector.translateToLocal((String)"tooltip.dark")),
    LIGHT_EFF(StatCollector.translateToLocal((String)"tooltip.light")),
    FIRE_PROTECTION(StatCollector.translateToLocal((String)"tooltip.fireprotect")),
    NO_FALL(StatCollector.translateToLocal((String)"tooltip.nofall")),
    FLY(StatCollector.translateToLocal((String)"tooltip.flight")),
    SPEED(StatCollector.translateToLocal((String)"tooltip.speed")),
    MELEE_DAMAGE(StatCollector.translateToLocal((String)"tooltip.meleedam")),
    RANGED_DAMAGE(StatCollector.translateToLocal((String)"tooltip.rangeddam")),
    HUNGER(StatCollector.translateToLocal((String)"tooltip.hunger")),
    DAMAGE_REDUCTION(StatCollector.translateToLocal((String)"tooltip.damreduction")),
    JUMP_HEIGHT(StatCollector.translateToLocal((String)"tooltip.jumpheight")),
    ORE_DROPS(StatCollector.translateToLocal((String)"tooltip.oredrop")),
    ARCANA_REGENPLUS(StatCollector.translateToLocal((String)"tooltip.arcanaplus")),
    HEALTH_REGEN(StatCollector.translateToLocal((String)"tooltip.healthregen")),
    UNDERWATER_HEALTH_REGEN(StatCollector.translateToLocal((String)"tooltip.underwaterregen")),
    BLOCK_PROTECTION(StatCollector.translateToLocal((String)"tooltip.blockprotection")),
    NIGHT_VISION(StatCollector.translateToLocal((String)"tooltip.nightvision")),
    EXPLOSION_PROTECTION(StatCollector.translateToLocal((String)"tooltip.explosionprotection")),
    RANGED_PROTECTION(StatCollector.translateToLocal((String)"tooltip.rangedprotection")),
    MELEE_PROTECTION(StatCollector.translateToLocal((String)"tooltip.meleeprotection")),
    ARCANA_PROTECTION(StatCollector.translateToLocal((String)"tooltip.arcanaprotection")),
    UNDERWATER(StatCollector.translateToLocal((String)"tooltip.underwater")),
    SCYTHE_DAMAGE(StatCollector.translateToLocal((String)"tooltip.schytedamage")),
    SWIM(StatCollector.translateToLocal((String)"tooltip.swim")),
    WITHER_PROTECTION(StatCollector.translateToLocal((String)"tooltip.witherprotection")),
    ARCANA_REGEN(StatCollector.translateToLocal((String)"tooltip.arcanaregen")),
    POISON_PROTECTION(StatCollector.translateToLocal((String)"tooltip.poisonprotection")),
    FREEZE(StatCollector.translateToLocal((String)"tooltip.freeze")),
    HASTE(StatCollector.translateToLocal((String)"tooltip.haste")),
    SMALL_REGEN(StatCollector.translateToLocal((String)"tooltip.smallregen")),
    CRITICAL_ARMOR(StatCollector.translateToLocal((String)"tooltip.critical_armor")),
    MANA_IMMUNE(StatCollector.translateToLocal((String)"tooltip.manaimmune"));

    private String info;

    private ArmorInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }

    public String toString() {
        return this.getInfo();
    }
}

