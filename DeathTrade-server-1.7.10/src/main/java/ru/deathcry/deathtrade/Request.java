/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade;

import net.minecraft.entity.player.EntityPlayer;

class Request {
    public long time = System.currentTimeMillis();
    public EntityPlayer player;

    public Request(EntityPlayer player) {
        this.player = player;
    }

    public boolean isActual() {
        return System.currentTimeMillis() < 30000L + this.time;
    }
}

