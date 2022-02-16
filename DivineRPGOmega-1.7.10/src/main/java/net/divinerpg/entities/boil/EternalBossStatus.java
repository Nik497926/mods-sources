/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 */
package net.divinerpg.entities.boil;

import net.minecraft.entity.EntityLiving;

public final class EternalBossStatus {
    public static float healthScale;
    public static int statusBarTime;
    public static int selected;
    public static String bossName;
    public static boolean hasColorModifier;

    public static void setBossStatus(EntityLiving b, boolean colour, int image) {
        healthScale = b.getHealth() / b.getMaxHealth();
        statusBarTime = 100;
        hasColorModifier = colour;
        selected = image;
    }
}

