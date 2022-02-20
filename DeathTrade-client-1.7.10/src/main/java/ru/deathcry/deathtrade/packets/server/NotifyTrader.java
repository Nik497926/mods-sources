/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade.packets.server;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import ru.deathcry.deathtrade.AbstractMessage;
import ru.deathcry.deathtrade.barter.BarterContainer;
import ru.deathcry.deathtrade.barter.BarterGui;

public class NotifyTrader
extends AbstractMessage {
    private boolean isReady = false;
    private boolean isAccept = false;
    private boolean forOpponent;

    public NotifyTrader() {
    }

    public NotifyTrader(boolean isReady, boolean isAccept, boolean isOpponent) {
        this.isReady = isReady;
        this.isAccept = isAccept;
        this.forOpponent = isOpponent;
    }

    @Override
    protected void read(PacketBuffer buffer) {
        this.isReady = buffer.readBoolean();
        this.isAccept = buffer.readBoolean();
        this.forOpponent = buffer.readBoolean();
    }

    @Override
    protected void write(PacketBuffer buffer) {
        buffer.writeBoolean(this.isReady);
        buffer.writeBoolean(this.isAccept);
        buffer.writeBoolean(this.forOpponent);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if (Minecraft.getMinecraft().currentScreen instanceof BarterGui) {
            BarterGui gui = (BarterGui)Minecraft.getMinecraft().currentScreen;
            if (this.forOpponent) {
                ((BarterContainer)gui.inventorySlots).holder.exchanger2.setReady(this.isReady);
                ((BarterContainer)gui.inventorySlots).holder.exchanger2.setAccept(this.isAccept);
            } else {
                ((BarterContainer)gui.inventorySlots).holder.exchanger.setReady(this.isReady);
                ((BarterContainer)gui.inventorySlots).holder.exchanger.setAccept(this.isAccept);
            }
        }
    }
}

