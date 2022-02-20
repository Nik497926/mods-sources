/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.utils;

public class MathHelper {
    public static float clamp(float num, float min, float max) {
        if (num < min) {
            return min;
        }
        return num > max ? max : num;
    }
}

