/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

public class EntityHandler {
    public static void knockBack(EntityLiving target, Entity attacker, float knockback, float buff) {
        double d1 = attacker.posX - target.posX;
        double d0 = attacker.posZ - target.posZ;
        while (d1 * d1 + d0 * d0 < 1.0E-4) {
            d1 = (Math.random() - Math.random()) * 0.01;
            d0 = (Math.random() - Math.random()) * 0.01;
        }
        target.attackedAtYaw = (float)(Math.atan2(d0, d1) * 180.0 / Math.PI) - target.rotationYaw;
        target.knockBack(attacker, knockback, d1, d0);
    }

    public static void knockBack(EntityPlayer target, Entity attacker, float knockback, float buff) {
        double d1 = attacker.posX - target.posX;
        double d0 = attacker.posZ - target.posZ;
        while (d1 * d1 * (double)buff + d0 * d0 * (double)buff < 1.0E-4) {
            d1 = (Math.random() - Math.random()) * 0.02 * (double)buff;
            d0 = (Math.random() - Math.random()) * 0.02 * (double)buff;
        }
        target.attackedAtYaw = (float)(Math.atan2(d0, d1) * 180.0 / Math.PI) - target.rotationYaw;
        target.knockBack(attacker, knockback, d1, d0);
    }
}

