/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package info.jbcs.minecraft.vending.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import info.jbcs.minecraft.vending.inventory.ContainerAdvancedVendingMachine;
import info.jbcs.minecraft.vending.network.Message;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MsgAdvVenSetItem
extends Message {
    private int id;
    private int count;
    private int damage;

    public MsgAdvVenSetItem() {
    }

    public MsgAdvVenSetItem(int id, int count, int damage) {
        this.id = id;
        this.count = count;
        this.damage = damage;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.id = buf.readInt();
        this.count = buf.readInt();
        this.damage = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.id);
        buf.writeInt(this.count);
        buf.writeInt(this.damage);
    }

    public static class Handler
    implements IMessageHandler<MsgAdvVenSetItem, IMessage> {
        public IMessage onMessage(MsgAdvVenSetItem message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            Container con = player.openContainer;
            if (con == null || !(con instanceof ContainerAdvancedVendingMachine)) {
                return null;
            }
            ContainerAdvancedVendingMachine container = (ContainerAdvancedVendingMachine)con;
            ((TileEntityVendingMachine)container.entity).setBoughtItem(message.id == 0 ? null : new ItemStack(Item.getItemById((int)message.id), message.count, message.damage));
            return null;
        }
    }
}

