/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade.packets.client;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import ru.deathcry.deathtrade.AbstractMessage;

public class ActionMessage
extends AbstractMessage {
    private int action;

    public ActionMessage() {
    }

    public ActionMessage(int action) {
        this.action = action;
    }

    @Override
    protected void read(PacketBuffer buffer) {
        this.action = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer) {
        buffer.writeInt(this.action);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
    }
}

