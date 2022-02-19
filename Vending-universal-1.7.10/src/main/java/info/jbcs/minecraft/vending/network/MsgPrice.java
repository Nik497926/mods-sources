/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package info.jbcs.minecraft.vending.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import info.jbcs.minecraft.vending.EconomyControl;
import info.jbcs.minecraft.vending.network.Message;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class MsgPrice
extends Message {
    private NBTTagCompound tag;

    public MsgPrice() {
    }

    public MsgPrice(NBTTagCompound tag) {
        this.tag = tag;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.tag = ByteBufUtils.readTag((ByteBuf)buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag((ByteBuf)buf, (NBTTagCompound)this.tag);
    }

    public static class Handler
    implements IMessageHandler<MsgPrice, IMessage> {
        public IMessage onMessage(MsgPrice message, MessageContext ctx) {
            EconomyControl.setNBTTagCompoud((NBTTagList)message.tag.getTag("list"));
            EconomyControl.hasChanges = true;
            return null;
        }
    }
}

