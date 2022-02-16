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

public class MessageDivineAccumulator
implements IMessage {
    private double x;
    private double y;
    private double z;

    public MessageDivineAccumulator() {
    }

    public MessageDivineAccumulator(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
    }

    public void fromBytes(ByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
    }

    public static class Handler
    implements IMessageHandler<MessageDivineAccumulator, IMessage> {
        public IMessage onMessage(MessageDivineAccumulator msg, MessageContext ctx) {
            for (double r = 0.0; r < 4.0; r += 0.1) {
                for (double theta = 0.0; theta < Math.PI * 2; theta += 0.1308996938995747) {
                    DivineRPG.proxy.spawnParticle(null, msg.x + r * Math.cos(theta), msg.y, msg.z + r * Math.sin(theta), "eden", true);
                }
            }
            return null;
        }
    }
}

