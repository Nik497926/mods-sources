/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class TradeGuiNotifyMessage
implements IMessage {
    private String[] lines;

    public TradeGuiNotifyMessage() {
    }

    public TradeGuiNotifyMessage(String ... lines) {
        this.lines = lines;
    }

    public void fromBytes(ByteBuf buf) {
        this.lines = ByteBufUtils.readUTF8String((ByteBuf)buf).split("\n");
    }

    public void toBytes(ByteBuf buf) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.lines.length; ++i) {
            builder.append(this.lines[i]);
            if (i == this.lines.length - 1) continue;
            builder.append("\n");
        }
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)builder.toString());
    }

    public String[] getLines() {
        return this.lines;
    }
}

