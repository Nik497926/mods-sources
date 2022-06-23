/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityNetworked
extends TileEntity {
    private int tickCounter = 0;
    private boolean forceRenderUpdate = false;

    public void updateEntity() {
        ++this.tickCounter;
        if (this.worldObj.isRemote) {
            if (this.forceRenderUpdate) {
                this.processRenderUpdate();
            }
            this.processClientTick();
        } else {
            this.processCommonTick();
            if (this.canTick(this.tickCounter)) {
                this.sendDescriptionPacket();
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        this.readDescriptionPacket(packet.func_148857_g());
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeDescriptionPacket(compound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, compound);
    }

    protected void sendDescriptionPacket() {
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    public boolean canTick(int tick) {
        return this.tickCounter % tick == 0;
    }

    protected void processRenderUpdate() {
        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
    }

    protected abstract void processCommonTick();

    protected abstract void processClientTick();

    protected abstract void readDescriptionPacket(NBTTagCompound var1);

    protected abstract void writeDescriptionPacket(NBTTagCompound var1);

    public void setForceRenderUpdate(boolean forceRenderUpdate) {
        this.forceRenderUpdate = forceRenderUpdate;
    }
}

