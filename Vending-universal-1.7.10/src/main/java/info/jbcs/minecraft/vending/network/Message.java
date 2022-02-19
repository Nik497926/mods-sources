/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  io.netty.buffer.ByteBuf
 */
package info.jbcs.minecraft.vending.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public abstract class Message
implements IMessage {
    public abstract void fromBytes(ByteBuf var1);

    public abstract void toBytes(ByteBuf var1);
}

