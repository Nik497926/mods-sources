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
import net.divinerpg.DivineRPG;
import net.divinerpg.client.ArcanaRenderer;

public class MessageArcanaBar
implements IMessage {
    private float value;
    private boolean shouldRegen;

    public MessageArcanaBar() {
    }

    public MessageArcanaBar(float barValue, boolean shouldRegen) {
        this.value = barValue;
        this.shouldRegen = shouldRegen;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeFloat(this.value);
        buf.writeBoolean(this.shouldRegen);
    }

    public void fromBytes(ByteBuf buf) {
        this.value = buf.readFloat();
        this.shouldRegen = buf.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<MessageArcanaBar, IMessage> {
        public IMessage onMessage(MessageArcanaBar msg, MessageContext ctx) {
            ArcanaRenderer.value = msg.value;
            ArcanaRenderer.regen = msg.shouldRegen;
            DivineRPG.proxy.updateClientArcana(msg.value);
            return null;
        }
    }
}

