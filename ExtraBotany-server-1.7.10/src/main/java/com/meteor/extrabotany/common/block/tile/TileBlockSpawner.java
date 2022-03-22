/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileBlockSpawner
extends TileEntity {
    private Long last = 0L;
    private Long current = 0L;

    public void writeToNBT(NBTTagCompound cmp) {
        super.writeToNBT(cmp);
        cmp.setLong("last", this.last.longValue());
        cmp.setLong("current", this.current.longValue());
    }

    public void readFromNBT(NBTTagCompound cmp) {
        super.readFromNBT(cmp);
        this.last = cmp.getLong("last");
        this.current = cmp.getLong("current");
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, data);
    }

    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }

    public Long getLast() {
        return this.last;
    }

    public Long getCurrent() {
        return this.current;
    }
}

