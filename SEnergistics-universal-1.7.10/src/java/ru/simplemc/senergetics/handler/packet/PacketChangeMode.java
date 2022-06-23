/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.handler.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import ru.simplemc.senergetics.common.inventory.container.ContainerSmartMachine;
import ru.simplemc.senergetics.common.inventory.container.ContainerSmartMachineMetalFormer;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartMetalFormer;

public class PacketChangeMode
implements IMessage,
IMessageHandler<PacketChangeMode, IMessage> {
    private int mode;

    public void fromBytes(ByteBuf byteBuf) {
        this.mode = byteBuf.readInt();
    }

    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(this.mode);
    }

    public IMessage onMessage(PacketChangeMode packet, MessageContext ctx) {
        if (!ctx.side.isServer()) {
            throw new IllegalStateException("Received PacketChangeMode " + packet + "on client side!");
        }
        ContainerSmartMachine<?> containerSmartMachine = this.getCurrentPlayerContainer(ctx);
        if (containerSmartMachine instanceof ContainerSmartMachineMetalFormer) {
            ContainerSmartMachineMetalFormer container = (ContainerSmartMachineMetalFormer)containerSmartMachine;
            ((TileEntitySmartMetalFormer)((Object)container.getInventoryTileEntity().getTileEntity())).setMode(packet.mode);
        }
        return null;
    }

    private ContainerSmartMachine<?> getCurrentPlayerContainer(MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        if (player.openContainer instanceof ContainerSmartMachine) {
            return (ContainerSmartMachine)player.openContainer;
        }
        return null;
    }

    public PacketChangeMode() {
    }

    public PacketChangeMode(int mode) {
        this.mode = mode;
    }
}

