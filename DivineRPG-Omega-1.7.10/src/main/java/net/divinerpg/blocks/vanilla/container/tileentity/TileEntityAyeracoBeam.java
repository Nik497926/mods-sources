/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 */
package net.divinerpg.blocks.vanilla.container.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityAyeracoBeam
extends TileEntity {
    @SideOnly(value=Side.CLIENT)
    private long time;
    @SideOnly(value=Side.CLIENT)
    private float beam;
    public ResourceLocation texture;

    public TileEntityAyeracoBeam(ResourceLocation tex) {
        this.texture = tex;
    }

    public TileEntityAyeracoBeam() {
    }

    @SideOnly(value=Side.CLIENT)
    public float renderBeam() {
        int var1 = (int)(this.worldObj.getTotalWorldTime() - this.time);
        this.time = this.worldObj.getTotalWorldTime();
        if (var1 > 1) {
            this.beam -= (float)var1 / 40.0f;
            if (this.beam < 0.0f) {
                this.beam = 0.0f;
            }
        }
        this.beam += 0.025f;
        if (this.beam > 1.0f) {
            this.beam = 1.0f;
        }
        return this.beam;
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, nbttagcompound);
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setString("texture", this.texture.toString());
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.texture = new ResourceLocation(tag.getString("texture"));
    }
}

