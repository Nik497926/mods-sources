/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler.keybindig;

import net.minecraft.client.settings.KeyBinding;

public abstract class KeyBindingBase
extends KeyBinding {
    private boolean isRunning = false;

    public KeyBindingBase(String description, int keyCode) {
        super(description, keyCode, "key.simplecore.category");
    }

    public void run() {
        if (this.isActive() && !this.isRunning) {
            this.isRunning = true;
            this.action();
            this.isRunning = false;
        }
    }

    protected abstract boolean isActive();

    protected abstract void action();
}

