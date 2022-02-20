/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade.packets.server;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import ru.deathcry.deathtrade.AbstractMessage;
import ru.deathcry.deathtrade.barter.BarterContainer;
import ru.deathcry.deathtrade.barter.BarterGui;

public class SendOpponentName
extends AbstractMessage {
    public String name = "";

    public SendOpponentName() {
    }

    public SendOpponentName(String name) {
        this.name = name;
    }

    @Override
    protected void read(PacketBuffer buffer) {
        this.name = ByteBufUtils.readUTF8String((ByteBuf)buffer);
    }

    @Override
    protected void write(PacketBuffer buffer) {
        ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)this.name);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if (Minecraft.getMinecraft().currentScreen instanceof BarterGui) {
            BarterGui gui = (BarterGui)Minecraft.getMinecraft().currentScreen;
            ((BarterContainer)gui.inventorySlots).holder.exchanger2.setName(this.name);
        }
    }
}

