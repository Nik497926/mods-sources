/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core;

public class Sound {
    public String name;
    public float volume;
    public static Sound ATTACK_SHADOW = new Sound("botania:attack.shadow", 0.6f);
    public static Sound ATTACK_FROST = new Sound("botania:attack.frost", 0.6f);

    public Sound(String name, float volume) {
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return this.name;
    }

    public float getVolume() {
        return this.volume;
    }
}

