/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.network;

import com.meteor.extrabotany.ExtraBotany;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public abstract class MessageBase<REQ extends IMessage>
implements IMessage,
IMessageHandler<REQ, REQ> {
    public REQ onMessage(REQ message, MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            this.handleServerSide(message, (EntityPlayer)ctx.getServerHandler().playerEntity);
        } else {
            this.handleClientSide(message, ExtraBotany.proxy.getClientPlayer());
        }
        return null;
    }

    public abstract void handleClientSide(REQ var1, EntityPlayer var2);

    public abstract void handleServerSide(REQ var1, EntityPlayer var2);

    public void fromBytes(ByteBuf byteBuf) {
    }

    public void toBytes(ByteBuf byteBuf) {
    }
}

