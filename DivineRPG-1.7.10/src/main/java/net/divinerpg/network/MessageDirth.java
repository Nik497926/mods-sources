/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 */
package net.divinerpg.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class MessageDirth
implements IMessage {
    public float f;

    public MessageDirth() {
    }

    public MessageDirth(float f) {
        this.f = f;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeFloat(this.f);
    }

    public void fromBytes(ByteBuf buf) {
        this.f = buf.readFloat();
    }

    public static class Handler
    implements IMessageHandler<MessageDirth, IMessage> {
        public IMessage onMessage(MessageDirth msg, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            mc.thePlayer.motionY = msg.f;
            return null;
        }
    }
}

