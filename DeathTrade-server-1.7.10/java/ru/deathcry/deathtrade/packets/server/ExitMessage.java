/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade.packets.server;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import ru.deathcry.deathtrade.AbstractMessage;
import ru.deathcry.deathtrade.barter.BarterGui;

public class ExitMessage
extends AbstractMessage {
    @Override
    protected void read(PacketBuffer buffer) {
    }

    @Override
    protected void write(PacketBuffer buffer) {
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if (Minecraft.getMinecraft().currentScreen instanceof BarterGui) {
            Minecraft.getMinecraft().thePlayer.closeScreen();
        }
    }
}

