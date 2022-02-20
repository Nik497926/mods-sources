/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade.barter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import ru.deathcry.deathtrade.barter.BarterInvHolder;
import ru.deathcry.deathtrade.barter.Barterer;

public class BarterHolder {
    private static final int READY_BUTTON = 0;
    private static final int ACCEPT_BUTTON = 1;
    private static final int SLOT_CLICK = 2;
    private static final int EXIT = 3;
    public Barterer exchanger;
    public Barterer exchanger2;

    public BarterHolder(EntityPlayer trader, EntityPlayer trader2) {
        this.exchanger = new Barterer(trader);
        this.exchanger2 = new Barterer(trader2);
    }

    public BarterHolder(String name) {
        this.exchanger = new Barterer(name);
        this.exchanger2 = new Barterer("");
    }

    public Barterer asBarter(EntityPlayer player) {
        if (this.exchanger.belongsTo(player.getDisplayName())) {
            return this.exchanger;
        }
        if (this.exchanger2.belongsTo(player.getDisplayName())) {
            return this.exchanger2;
        }
        return null;
    }

    public Barterer getOpponent(EntityPlayer player) {
        if (!this.exchanger.belongsTo(player.getDisplayName())) {
            return this.exchanger;
        }
        if (!this.exchanger2.belongsTo(player.getDisplayName())) {
            return this.exchanger2;
        }
        return null;
    }

    public BarterInvHolder getHolder(EntityPlayer player) {
        Barterer trader = this.asBarter(player);
        if (trader != null) {
            return trader.getHolderInventory();
        }
        return null;
    }

    private boolean trade() {
        return false;
    }

    private int getOccupiedSlots(IInventory inv) {
        return 0;
    }

    public void onAction(EntityPlayerMP causePlayer, int action) {
    }

    public void onMoney(EntityPlayerMP causePlayer, double money) {
    }
}

