/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.manager.integration.bukkit.event;

import java.math.BigDecimal;
import ru.simplemc.simplecore.mod.manager.integration.bukkit.action.EconomyEventAction;
import ru.simplemc.simplecore.mod.manager.integration.bukkit.event.CancellableEvent;

public class EconomyEvent
extends CancellableEvent {
    private final String playerName;
    private final BigDecimal amount;
    private final EconomyEventAction action;

    public EconomyEvent(String playerName, BigDecimal amount, EconomyEventAction action) {
        this.playerName = playerName;
        this.amount = amount;
        this.action = action;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public EconomyEventAction getAction() {
        return this.action;
    }
}

