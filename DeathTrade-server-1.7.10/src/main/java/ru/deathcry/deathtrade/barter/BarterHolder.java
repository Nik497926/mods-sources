/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade.barter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import ru.deathcry.deathtrade.CommandHandler;
import ru.deathcry.deathtrade.Main;
import ru.deathcry.deathtrade.Economy;
import ru.deathcry.deathtrade.packets.PacketDispatcher;
import ru.deathcry.deathtrade.packets.server.ExitMessage;
import ru.deathcry.deathtrade.packets.server.MoneyNotify;
import ru.deathcry.deathtrade.packets.server.NotifyTrader;

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
        int firstFree = this.exchanger.getPlayerInventory().getSizeInventory() - this.getOccupiedSlots(this.exchanger.getPlayerInventory()) - 4;
        int secondFree = this.exchanger2.getPlayerInventory().getSizeInventory() - this.getOccupiedSlots(this.exchanger2.getPlayerInventory()) - 4;
        int firstNeeded = this.getOccupiedSlots(this.exchanger2.getHolderInventory());
        int secondNeeded = this.getOccupiedSlots(this.exchanger.getHolderInventory());
        if (firstFree < firstNeeded) {
            this.exchanger.getPlayer().addChatMessage((IChatComponent)CommandHandler.locMessage("message.your_inv_full", new Object[0]));
            this.exchanger2.getPlayer().addChatMessage((IChatComponent)CommandHandler.locMessage("message.player_inv_full", new Object[0]));
            return false;
        }
        if (secondFree < secondNeeded) {
            this.exchanger2.getPlayer().addChatMessage((IChatComponent)CommandHandler.locMessage("message.your_inv_full", new Object[0]));
            this.exchanger.getPlayer().addChatMessage((IChatComponent)CommandHandler.locMessage("message.player_inv_full", new Object[0]));
            return false;
        }
        if (this.exchanger.getMoneyPlaced() > 0.0 && Economy.getPlayerBalance(this.exchanger.getPlayer()) < this.exchanger.getMoneyPlaced()) {
            this.exchanger.getPlayer().addChatMessage((IChatComponent)CommandHandler.locMessage("message.not_enough_money", new Object[0]));
            this.exchanger2.getPlayer().addChatMessage((IChatComponent)CommandHandler.locMessage("message.player_not_enough_money", new Object[0]));
            return false;
        }
        if (this.exchanger2.getMoneyPlaced() > 0.0 && Economy.getPlayerBalance(this.exchanger2.getPlayer()) < this.exchanger2.getMoneyPlaced()) {
            this.exchanger.getPlayer().addChatMessage((IChatComponent)CommandHandler.locMessage("message.player_not_enough_money", new Object[0]));
            this.exchanger2.getPlayer().addChatMessage((IChatComponent)CommandHandler.locMessage("message.not_enough_money", new Object[0]));
            return false;
        }
        for (int i = 0; i < this.exchanger.getHolderInventory().getSizeInventory(); ++i) {
            ItemStack tmp = this.exchanger.getHolderInventory().getStackInSlot(i);
            this.exchanger.getHolderInventory().setInventorySlotContents(i, this.exchanger2.getHolderInventory().getStackInSlot(i));
            this.exchanger2.getHolderInventory().setInventorySlotContents(i, tmp);
        }
        if (this.exchanger.getMoneyPlaced() > 0.0) {
            Economy.transferBalance(this.exchanger.getPlayer(), this.exchanger2.getPlayer(), this.exchanger.getMoneyPlaced());
        }
        if (this.exchanger2.getMoneyPlaced() > 0.0) {
            Economy.transferBalance(this.exchanger2.getPlayer(), this.exchanger.getPlayer(), this.exchanger2.getMoneyPlaced());
        }
        this.exchanger.getPlayer().addChatMessage((IChatComponent)CommandHandler.locMessage("message.trade_success", new Object[0]));
        this.exchanger2.getPlayer().addChatMessage((IChatComponent)CommandHandler.locMessage("message.trade_success", new Object[0]));
        return true;
    }

    private int getOccupiedSlots(IInventory inv) {
        int count = 0;
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            if (inv.getStackInSlot(i) == null) continue;
            ++count;
        }
        return count;
    }

    public void onAction(EntityPlayerMP causePlayer, int action) {
        Barterer causer = this.asBarter((EntityPlayer)causePlayer);
        Barterer opponent = this.getOpponent((EntityPlayer)causePlayer);
        EntityPlayerMP opponentPlayer = (EntityPlayerMP)opponent.getPlayer();
        switch (action) {
            case 0: {
                causer.setReady(!causer.isReady());
                PacketDispatcher.sendTo(new NotifyTrader(causer.isReady(), causer.isAccept(), false), causePlayer);
                PacketDispatcher.sendTo(new NotifyTrader(causer.isReady(), causer.isAccept(), true), opponentPlayer);
                break;
            }
            case 1: {
                causer.setAccept(!causer.isAccept());
                if (causer.isAccept() && opponent.isAccept()) {
                    this.trade();
                    PacketDispatcher.sendTo(new ExitMessage(), causePlayer);
                    PacketDispatcher.sendTo(new ExitMessage(), opponentPlayer);
                    break;
                }
                PacketDispatcher.sendTo(new NotifyTrader(causer.isReady(), causer.isAccept(), false), causePlayer);
                PacketDispatcher.sendTo(new NotifyTrader(causer.isReady(), causer.isAccept(), true), opponentPlayer);
                break;
            }
            case 2: {
                causer.setReady(false);
                opponent.setReady(false);
                PacketDispatcher.sendTo(new NotifyTrader(false, false, false), causePlayer);
                PacketDispatcher.sendTo(new NotifyTrader(false, false, true), causePlayer);
                PacketDispatcher.sendTo(new NotifyTrader(false, false, false), opponentPlayer);
                PacketDispatcher.sendTo(new NotifyTrader(false, false, true), opponentPlayer);
                break;
            }
            case 3: {
                opponent.setReady(false);
                PacketDispatcher.sendTo(new NotifyTrader(false, false, true), opponentPlayer);
                PacketDispatcher.sendTo(new ExitMessage(), opponentPlayer);
                Main.trades.remove(causePlayer);
            }
        }
    }

    public void onMoney(EntityPlayerMP causePlayer, double money) {
        Barterer causer = this.asBarter((EntityPlayer)causePlayer);
        if (money == causer.getMoneyPlaced()) {
            return;
        }
        causer.setMoneyPlaced(money);
        this.onAction(causePlayer, 2);
        EntityPlayer opponentPlayer = this.getOpponent((EntityPlayer)causePlayer).getPlayer();
        PacketDispatcher.sendTo(new MoneyNotify(money), (EntityPlayerMP)opponentPlayer);
    }
}

