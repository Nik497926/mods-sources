/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.tileentity.TileEntity
 */
package info.jbcs.minecraft.vending.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import info.jbcs.minecraft.vending.network.Message;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class SetMoneyMsg
extends Message {
    public int mode;
    public int x;
    public int y;
    public int z;
    public int money = 0;

    public SetMoneyMsg() {
    }

    public SetMoneyMsg(TileEntityVendingMachine tile) {
        this.z = tile.zCoord;
        this.y = tile.yCoord;
        this.x = tile.xCoord;
        this.money = tile.money;
        this.mode = tile.mode;
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.money = buffer.readInt();
        this.mode = buffer.readInt();
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.money);
        buffer.writeInt(this.mode);
    }

    public static class Handler
    implements IMessageHandler<SetMoneyMsg, IMessage> {
        public IMessage onMessage(SetMoneyMsg message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            TileEntity tileEntity = player.worldObj.getTileEntity(message.x, message.y, message.z);
            if (!(tileEntity instanceof TileEntityVendingMachine)) {
                return null;
            }
            TileEntityVendingMachine entity = (TileEntityVendingMachine)tileEntity;
            if (entity.ownerName.equals(player.getCommandSenderName()) || player.capabilities.isCreativeMode) {
                entity.money = Math.max(message.money, 0);
                entity.mode = message.mode;
                entity.markDirty();
            }
            player.worldObj.markBlockForUpdate(message.x, message.y, message.z);
            return null;
        }
    }
}

