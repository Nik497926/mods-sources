/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade.barter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import ru.deathcry.deathtrade.barter.BarterInvHolder;

public class Barterer {
    private BarterInvHolder invHolder;
    private boolean isAccept;
    private boolean isReady;
    private double balance = 0.0;
    private double moneyPlaced = 0.0;
    private String moneyString = "0.00";
    private EntityPlayer player;
    private String playerName;

    public Barterer(EntityPlayer player) {
        this(player.getDisplayName(), player, false, false);
    }

    public Barterer(String name) {
        this(name, null, false, false);
    }

    public Barterer(String name, EntityPlayer player, boolean isReady, boolean isAccept) {
        this.playerName = name;
        this.player = player;
        this.isReady = isReady;
        this.isAccept = isAccept;
        this.invHolder = new BarterInvHolder();
    }

    public boolean belongsTo(String playerName) {
        return this.playerName.equals(playerName);
    }

    public double getBalance() {
        return this.balance;
    }

    public BarterInvHolder getHolderInventory() {
        return this.invHolder;
    }

    public double getMoneyPlaced() {
        return this.moneyPlaced;
    }

    public String getMoneyString() {
        return this.moneyString;
    }

    public String getName() {
        return this.playerName;
    }

    public EntityPlayer getPlayer() {
        return this.player;
    }

    public IInventory getPlayerInventory() {
        return this.player.inventory;
    }

    public boolean isAccept() {
        return this.isAccept;
    }

    public boolean isReady() {
        return this.isReady;
    }

    public void setAccept(boolean accept) {
        this.isAccept = this.isReady ? accept : false;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setMoneyPlaced(double money) {
        this.moneyPlaced = money;
        this.moneyString = String.format("%.2f", this.moneyPlaced);
    }

    public void setName(String name) {
        this.playerName = name;
    }

    public void setReady(boolean ready) {
        this.isAccept = false;
        this.isReady = ready;
    }
}

