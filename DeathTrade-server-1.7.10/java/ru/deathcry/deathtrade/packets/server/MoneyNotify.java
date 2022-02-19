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

public class MoneyNotify
extends AbstractMessage {
    private double money;

    public MoneyNotify() {
    }

    public MoneyNotify(double money) {
        this.money = money;
    }

    @Override
    protected void read(PacketBuffer buffer) {
        this.money = buffer.readDouble();
    }

    @Override
    protected void write(PacketBuffer buffer) {
        buffer.writeDouble(this.money);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if (Minecraft.getMinecraft().currentScreen instanceof BarterGui) {
            BarterGui gui = (BarterGui)Minecraft.getMinecraft().currentScreen;
            ((BarterContainer)gui.inventorySlots).holder.exchanger2.setMoneyPlaced(this.money);
        }
    }
}

