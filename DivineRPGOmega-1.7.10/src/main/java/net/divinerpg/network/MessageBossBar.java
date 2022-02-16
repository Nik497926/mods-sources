/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 */
package net.divinerpg.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.divinerpg.client.ArcanaRenderer;
import net.divinerpg.utils.events.ClientTicker;

public class MessageBossBar
implements IMessage {
    private int time;
    private int level;

    public MessageBossBar() {
    }

    public MessageBossBar(int timeValue, int level) {
        this.time = timeValue;
        this.level = level;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.time);
        buf.writeInt(this.level);
    }

    public void fromBytes(ByteBuf buf) {
        this.time = buf.readInt();
        this.level = buf.readInt();
    }

    public static class Handler
    implements IMessageHandler<MessageBossBar, IMessage> {
        public IMessage onMessage(MessageBossBar msg, MessageContext ctx) {
            ArcanaRenderer.time = msg.time;
            ArcanaRenderer.level = msg.level;
            ClientTicker.bossRenderTick = 120;
            return null;
        }
    }
}

