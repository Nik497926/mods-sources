/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.manager.integration.bukkit.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CancellableEvent
extends Event
implements Cancellable {
    private static final HandlerList handlerList = new HandlerList();
    private boolean cancelled = false;

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean value) {
        this.cancelled = value;
    }

    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}

