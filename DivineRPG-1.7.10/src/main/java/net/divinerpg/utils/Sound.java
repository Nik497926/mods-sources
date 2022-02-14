/*
 * Decompiled with CFR 0.152.
 */
package net.divinerpg.utils;

import net.divinerpg.utils.SoundGenerator;

public class Sound {
    private String name;
    private boolean mob;
    private boolean item;

    public Sound(String name) {
        this(name, false, false);
    }

    public Sound(String name, boolean mob) {
        this(name, mob, false);
    }

    public Sound(boolean item, String name) {
        this(name, false, item);
    }

    public Sound(String name, boolean mob, boolean item) {
        this.name = name;
        this.mob = mob;
        this.item = item;
        SoundGenerator.addSound(this);
    }

    public String getName() {
        return this.name;
    }

    public String getPrefixedName() {
        return "divinerpg:" + this.name;
    }

    public boolean isMob() {
        return this.mob;
    }

    public boolean isItem() {
        return this.item;
    }
}

