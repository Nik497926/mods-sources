/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.tileentity.TileEntity
 */
package info.jbcs.minecraft.vending.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import info.jbcs.minecraft.vending.Vending;
import info.jbcs.minecraft.vending.network.Message;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class MsgWrench
extends Message {
    private int x;
    private int y;
    private int z;
    private boolean infinite;
    private String ownerName;

    public MsgWrench() {
    }

    public MsgWrench(TileEntity tileEntityVendingMachine, boolean infinite, String ownerName) {
        TileEntityVendingMachine entity = (TileEntityVendingMachine)tileEntityVendingMachine;
        this.x = entity.xCoord;
        this.y = entity.yCoord;
        this.z = entity.zCoord;
        this.infinite = infinite;
        this.ownerName = ownerName;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.infinite = buf.readBoolean();
        this.ownerName = ByteBufUtils.readUTF8String((ByteBuf)buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeBoolean(this.infinite);
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.ownerName);
    }

    public static class Handler
    implements IMessageHandler<MsgWrench, IMessage> {
        public IMessage onMessage(MsgWrench message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            if (player.inventory.getCurrentItem() == null || player.inventory.getCurrentItem().getItem() != Vending.itemWrench) {
                return null;
            }
            TileEntity tileEntity = player.worldObj.getTileEntity(message.x, message.y, message.z);
            if (!(tileEntity instanceof TileEntityVendingMachine)) {
                return null;
            }
            TileEntityVendingMachine entity = (TileEntityVendingMachine)tileEntity;
            entity.infinite = message.infinite;
            entity.ownerName = message.ownerName;
            player.worldObj.markBlockForUpdate(message.x, message.y, message.z);
            return null;
        }
    }
}

