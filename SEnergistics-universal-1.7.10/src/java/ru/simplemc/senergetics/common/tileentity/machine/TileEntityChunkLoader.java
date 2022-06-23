/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.common.inventory.InventoryChunkLoader;
import ru.simplemc.senergetics.common.item.tool.ItemChunkLoaderTicket;
import ru.simplemc.senergetics.common.tileentity.component.TileRedStoneComponent;
import ru.simplemc.senergetics.util.TranslateUtils;

public class TileEntityChunkLoader
extends TileEntity {
    private final InventoryChunkLoader inventory = new InventoryChunkLoader(this);
    private final TileRedStoneComponent redStoneComponent = new TileRedStoneComponent(this);
    private final List<ChunkCoordIntPair> chunkPositions = new ArrayList<ChunkCoordIntPair>();
    private String activeStatus = null;
    private int tickCounter = 0;
    private ForgeChunkManager.Ticket chunkLoaderTicket;

    public void updateEntity() {
        if (!this.worldObj.isRemote) {
            ++this.tickCounter;
            this.redStoneComponent.updateEntity();
            if (this.redStoneComponent.isPowered()) {
                this.setChunkLoaderTicket(null);
                return;
            }
            if (this.tickCounter % 20 == 0) {
                ItemStack ticketItemStack;
                ItemStack itemStack = this.inventory.getTicketItemStack();
                boolean canUnForceChunks = false;
                if (itemStack != null && itemStack.getItem() instanceof ItemChunkLoaderTicket) {
                    itemStack = ((ItemChunkLoaderTicket)itemStack.getItem()).onTicketUse(itemStack, 20);
                    this.inventory.setInventorySlotContents(0, itemStack);
                    if (itemStack != null) {
                        if (this.chunkLoaderTicket == null) {
                            this.setChunkLoaderTicket(this.createTicket());
                        }
                    } else {
                        canUnForceChunks = true;
                    }
                } else {
                    canUnForceChunks = true;
                }
                if (canUnForceChunks) {
                    this.setChunkLoaderTicket(null);
                }
                String currentActiveStatus = (ticketItemStack = this.inventory.getTicketItemStack()) != null && ticketItemStack.getItem() instanceof ItemChunkLoaderTicket ? "\u041e\u0441\u0442\u0430\u043b\u043e\u0441\u044c:<br>" + TranslateUtils.translateTicksToHumanTime(((ItemChunkLoaderTicket)ticketItemStack.getItem()).getAvailableTicks(ticketItemStack)) : "\u0412\u0441\u0442\u0430\u0432\u044c\u0442\u0435<br>\u0431\u0438\u043b\u0435\u0442";
                if (!(currentActiveStatus = currentActiveStatus.replace(", ", ",<br>").replace("\u00a7c", "")).equals(this.activeStatus)) {
                    this.activeStatus = currentActiveStatus;
                    this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                }
            }
        }
    }

    public void forceChunks(ForgeChunkManager.Ticket ticket) {
        this.chunkPositions.forEach(chunkCoordIntPair -> ForgeChunkManager.forceChunk((ForgeChunkManager.Ticket)ticket, (ChunkCoordIntPair)chunkCoordIntPair));
    }

    public ForgeChunkManager.Ticket createTicket() {
        ForgeChunkManager.Ticket ticket = ForgeChunkManager.requestTicket((Object)SEnergetics.getInstance(), (World)this.worldObj, (ForgeChunkManager.Type)ForgeChunkManager.Type.NORMAL);
        ticket.getModData().setInteger("chunkLoaderPosX", this.xCoord);
        ticket.getModData().setInteger("chunkLoaderPosY", this.yCoord);
        ticket.getModData().setInteger("chunkLoaderPosZ", this.zCoord);
        return ticket;
    }

    public void setChunkLoaderTicket(ForgeChunkManager.Ticket ticket) {
        if (ticket == null) {
            ForgeChunkManager.releaseTicket((ForgeChunkManager.Ticket)this.chunkLoaderTicket);
            this.chunkLoaderTicket = null;
            return;
        }
        this.chunkLoaderTicket = ticket;
        if (this.chunkPositions.isEmpty()) {
            ChunkCoordIntPair chunkPos = new ChunkCoordIntPair(this.xCoord >> 4, this.zCoord >> 4);
            this.chunkPositions.add(chunkPos);
            this.chunkPositions.add(new ChunkCoordIntPair(chunkPos.chunkXPos - 1, chunkPos.chunkZPos));
            this.chunkPositions.add(new ChunkCoordIntPair(chunkPos.chunkXPos + 1, chunkPos.chunkZPos));
            this.chunkPositions.add(new ChunkCoordIntPair(chunkPos.chunkXPos, chunkPos.chunkZPos - 1));
            this.chunkPositions.add(new ChunkCoordIntPair(chunkPos.chunkXPos, chunkPos.chunkZPos + 1));
            this.chunkPositions.add(new ChunkCoordIntPair(chunkPos.chunkXPos - 1, chunkPos.chunkZPos + 1));
            this.chunkPositions.add(new ChunkCoordIntPair(chunkPos.chunkXPos - 1, chunkPos.chunkZPos - 1));
            this.chunkPositions.add(new ChunkCoordIntPair(chunkPos.chunkXPos + 1, chunkPos.chunkZPos - 1));
            this.chunkPositions.add(new ChunkCoordIntPair(chunkPos.chunkXPos + 1, chunkPos.chunkZPos + 1));
        }
        this.forceChunks(this.chunkLoaderTicket);
    }

    @SideOnly(value=Side.CLIENT)
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound compound = packet.func_148857_g();
        if (compound.hasKey("ActiveStatus")) {
            this.activeStatus = compound.getString("ActiveStatus");
        }
    }

    public Packet getDescriptionPacket() {
        if (this.activeStatus == null) {
            return null;
        }
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString("ActiveStatus", this.activeStatus);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, compound);
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        this.inventory.readFromNBT(tagCompound);
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        this.inventory.writeToNBT(tagCompound);
    }

    public InventoryChunkLoader getInventory() {
        return this.inventory;
    }

    public TileRedStoneComponent getRedStoneComponent() {
        return this.redStoneComponent;
    }

    public List<ChunkCoordIntPair> getChunkPositions() {
        return this.chunkPositions;
    }

    public String getActiveStatus() {
        return this.activeStatus;
    }

    public int getTickCounter() {
        return this.tickCounter;
    }

    public ForgeChunkManager.Ticket getChunkLoaderTicket() {
        return this.chunkLoaderTicket;
    }
}

