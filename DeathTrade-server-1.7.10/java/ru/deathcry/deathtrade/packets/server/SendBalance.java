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

public class SendBalance
extends AbstractMessage {
    public double balance = 0.0;

    public SendBalance() {
    }

    public SendBalance(double balance) {
        this.balance = balance;
    }

    @Override
    protected void read(PacketBuffer buffer) {
        this.balance = buffer.readDouble();
    }

    @Override
    protected void write(PacketBuffer buffer) {
        buffer.writeDouble(this.balance);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if (Minecraft.getMinecraft().currentScreen instanceof BarterGui) {
            BarterGui gui = (BarterGui)Minecraft.getMinecraft().currentScreen;
            ((BarterContainer)gui.inventorySlots).holder.exchanger.setBalance(this.balance);
        }
    }
}

