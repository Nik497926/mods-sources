/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade.packets.client;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import ru.deathcry.deathtrade.AbstractMessage;

public class MoneyChangeMessage
extends AbstractMessage {
    private double money;

    public MoneyChangeMessage() {
    }

    public MoneyChangeMessage(double money) {
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
    }
}

