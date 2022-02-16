/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package net.divinerpg.entities.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.entities.base.EntityDivineRPGBoss;

@SideOnly(value=Side.CLIENT)
public final class DivineBossStatus {
    public static float healthScale;
    public static int statusBarTime;
    public static int bossNumber;
    public static String bossName;

    public static void setBossStatus(EntityDivineRPGBoss b, int boss) {
        if (b.posX != 0.0 || b.posY != 0.0 || b.posZ != 0.0) {
            healthScale = b.getHealth() / b.getMaxHealth();
            statusBarTime = 100;
            bossNumber = boss;
        }
    }
}

