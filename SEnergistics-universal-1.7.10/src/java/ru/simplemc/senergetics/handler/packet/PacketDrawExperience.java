/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.handler.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import ru.simplemc.senergetics.common.inventory.container.ContainerSpawner;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntitySpawner;

public class PacketDrawExperience
implements IMessage,
IMessageHandler<PacketDrawExperience, IMessage> {
    private int posX;
    private int posY;
    private int posZ;
    private int dimensionId;

    public PacketDrawExperience() {
    }

    public PacketDrawExperience(ContainerSpawner containerSpawner) {
        this.posX = containerSpawner.spawner.xCoord;
        this.posY = containerSpawner.spawner.yCoord;
        this.posZ = containerSpawner.spawner.zCoord;
        this.dimensionId = containerSpawner.spawner.getWorldObj().provider.dimensionId;
    }

    public void fromBytes(ByteBuf buf) {
        this.posX = buf.readInt();
        this.posY = buf.readInt();
        this.posZ = buf.readInt();
        this.dimensionId = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.posX);
        buf.writeInt(this.posY);
        buf.writeInt(this.posZ);
        buf.writeInt(this.dimensionId);
    }

    public IMessage onMessage(PacketDrawExperience message, MessageContext ctx) {
        if (!ctx.side.isServer()) {
            throw new IllegalStateException("Received PacketDrawExperience " + message + "on client side!");
        }
        EntityPlayerMP playerMP = ctx.getServerHandler().playerEntity;
        if (playerMP.openContainer instanceof ContainerSpawner) {
            ContainerSpawner containerSpawner = (ContainerSpawner)playerMP.openContainer;
            TileEntitySpawner tileEntitySpawner = containerSpawner.spawner;
            if (this.isValidTileEntity(tileEntitySpawner, message)) {
                tileEntitySpawner.onPlayerDrawExperience(playerMP);
            }
        }
        return null;
    }

    private boolean isValidTileEntity(TileEntitySpawner tileEntitySpawner, PacketDrawExperience message) {
        return message.posX == tileEntitySpawner.xCoord && message.posY == tileEntitySpawner.yCoord && message.posZ == tileEntitySpawner.zCoord && message.dimensionId == tileEntitySpawner.getWorldObj().provider.dimensionId;
    }
}

