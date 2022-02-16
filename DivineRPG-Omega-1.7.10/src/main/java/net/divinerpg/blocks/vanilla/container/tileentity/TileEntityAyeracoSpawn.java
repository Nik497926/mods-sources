/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumChatFormatting
 */
package net.divinerpg.blocks.vanilla.container.tileentity;

import net.divinerpg.entities.vanilla.EntityAyeracoBlue;
import net.divinerpg.entities.vanilla.EntityAyeracoGreen;
import net.divinerpg.entities.vanilla.EntityAyeracoPurple;
import net.divinerpg.entities.vanilla.EntityAyeracoRed;
import net.divinerpg.entities.vanilla.EntityAyeracoYellow;
import net.divinerpg.utils.MessageLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;

public class TileEntityAyeracoSpawn
extends TileEntity {
    private BeamCoords greenBeam = new BeamCoords();
    private BeamCoords blueBeam = new BeamCoords();
    private BeamCoords redBeam = new BeamCoords();
    private BeamCoords yellowBeam = new BeamCoords();
    private BeamCoords purpleBeam = new BeamCoords();
    public int spawnTick = 600;

    public void updateEntity() {
        super.updateEntity();
        if (!this.worldObj.isRemote) {
            if (this.spawnTick == 600) {
                this.setBeamLocation(this.greenBeam, 8, 8);
                this.setBeamLocation(this.blueBeam, 15, 0);
                this.setBeamLocation(this.redBeam, 5, -12);
                this.setBeamLocation(this.yellowBeam, -5, -12);
                this.setBeamLocation(this.purpleBeam, -8, 8);
                Util.sendMessageToAll(Util.addChatMessage(EnumChatFormatting.GREEN, MessageLocalizer.normal("message.ayeraco.green"), new Object[0]));
                this.worldObj.setBlock(this.greenBeam.beamX, this.greenBeam.beamY, this.greenBeam.beamZ, VanillaBlocks.ayeracoBeamGreen);
            } else if (this.spawnTick == 430) {
                Util.sendMessageToAll(Util.addChatMessage(EnumChatFormatting.BLUE, MessageLocalizer.normal("message.ayeraco.blue"), new Object[0]));
                this.worldObj.setBlock(this.blueBeam.beamX, this.blueBeam.beamY, this.blueBeam.beamZ, VanillaBlocks.ayeracoBeamBlue);
            } else if (this.spawnTick == 300) {
                Util.sendMessageToAll(Util.addChatMessage(EnumChatFormatting.RED, MessageLocalizer.normal("message.ayeraco.red"), new Object[0]));
                this.worldObj.setBlock(this.redBeam.beamX, this.redBeam.beamY, this.redBeam.beamZ, VanillaBlocks.ayeracoBeamRed);
            } else if (this.spawnTick == 210) {
                Util.sendMessageToAll(Util.addChatMessage(EnumChatFormatting.YELLOW, MessageLocalizer.normal("message.ayeraco.yellow"), new Object[0]));
                this.worldObj.setBlock(this.yellowBeam.beamX, this.yellowBeam.beamY, this.yellowBeam.beamZ, VanillaBlocks.ayeracoBeamYellow);
            } else if (this.spawnTick == 145) {
                Util.sendMessageToAll(Util.addChatMessage(EnumChatFormatting.DARK_PURPLE, MessageLocalizer.normal("message.ayeraco.purple"), new Object[0]));
                this.worldObj.setBlock(this.purpleBeam.beamX, this.purpleBeam.beamY, this.purpleBeam.beamZ, VanillaBlocks.ayeracoBeamPurple);
            } else if (this.spawnTick == 0) {
                EntityAyeracoGreen ayercoGreen = new EntityAyeracoGreen(this.worldObj);
                EntityAyeracoBlue ayercoBlue = new EntityAyeracoBlue(this.worldObj);
                EntityAyeracoRed ayercoRed = new EntityAyeracoRed(this.worldObj);
                EntityAyeracoYellow ayercoYellow = new EntityAyeracoYellow(this.worldObj);
                EntityAyeracoPurple ayercoPurple = new EntityAyeracoPurple(this.worldObj);
                ayercoGreen.setLocationAndAngles(this.greenBeam.beamX, this.greenBeam.beamY + 4, this.greenBeam.beamZ, this.worldObj.rand.nextFloat() * 360.0f, 0.0f);
                ayercoBlue.setLocationAndAngles(this.blueBeam.beamX, this.blueBeam.beamY + 4, this.blueBeam.beamZ, this.worldObj.rand.nextFloat() * 360.0f, 0.0f);
                ayercoRed.setLocationAndAngles(this.redBeam.beamX, this.redBeam.beamY + 4, this.redBeam.beamZ, this.worldObj.rand.nextFloat() * 360.0f, 0.0f);
                ayercoYellow.setLocationAndAngles(this.yellowBeam.beamX, this.yellowBeam.beamY + 4, this.yellowBeam.beamZ, this.worldObj.rand.nextFloat() * 360.0f, 0.0f);
                ayercoPurple.setLocationAndAngles(this.purpleBeam.beamX, this.purpleBeam.beamY + 4, this.purpleBeam.beamZ, this.worldObj.rand.nextFloat() * 360.0f, 0.0f);
                ayercoGreen.initOthers(ayercoBlue, ayercoRed, ayercoYellow, ayercoPurple);
                ayercoBlue.initOthers(ayercoGreen, ayercoRed, ayercoYellow, ayercoPurple);
                ayercoRed.initOthers(ayercoBlue, ayercoGreen, ayercoYellow, ayercoPurple);
                ayercoYellow.initOthers(ayercoBlue, ayercoGreen, ayercoRed, ayercoPurple);
                ayercoPurple.initOthers(ayercoBlue, ayercoGreen, ayercoRed, ayercoYellow);
                ayercoGreen.setBeamLocation(this.greenBeam.beamX, this.greenBeam.beamY, this.greenBeam.beamZ);
                ayercoBlue.setBeamLocation(this.blueBeam.beamX, this.blueBeam.beamY, this.blueBeam.beamZ);
                ayercoRed.setBeamLocation(this.redBeam.beamX, this.redBeam.beamY, this.redBeam.beamZ);
                ayercoYellow.setBeamLocation(this.yellowBeam.beamX, this.yellowBeam.beamY, this.yellowBeam.beamZ);
                ayercoPurple.setBeamLocation(this.purpleBeam.beamX, this.purpleBeam.beamY, this.purpleBeam.beamZ);
                this.worldObj.spawnEntityInWorld((Entity)ayercoGreen);
                this.worldObj.spawnEntityInWorld((Entity)ayercoBlue);
                this.worldObj.spawnEntityInWorld((Entity)ayercoRed);
                this.worldObj.spawnEntityInWorld((Entity)ayercoYellow);
                this.worldObj.spawnEntityInWorld((Entity)ayercoPurple);
                Util.sendMessageToAll(Util.addChatMessage(EnumChatFormatting.AQUA, MessageLocalizer.normal("message.ayeraco.spawn"), new Object[0]));
            }
            if (this.spawnTick == 0) {
                this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, Blocks.air);
            }
        }
        if (this.spawnTick > 0) {
            --this.spawnTick;
        }
    }

    private void setBeamLocation(BeamCoords beamCoords, int x, int z) {
        beamCoords.beamX = this.xCoord + x;
        beamCoords.beamY = this.yCoord;
        beamCoords.beamZ = this.zCoord + z;
        if (this.worldObj.getBlock(beamCoords.beamX, beamCoords.beamY, beamCoords.beamZ) == Blocks.air) {
            while (beamCoords.beamY > 0 && this.worldObj.getBlock(beamCoords.beamX, beamCoords.beamY - 1, beamCoords.beamZ) == Blocks.air) {
                --beamCoords.beamY;
            }
        } else {
            while (beamCoords.beamY < 200 && this.worldObj.getBlock(beamCoords.beamX, beamCoords.beamY, beamCoords.beamZ) != Blocks.air) {
                ++beamCoords.beamY;
            }
        }
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.spawnTick = tag.getInteger("spawnTick");
        this.greenBeam.beamX = tag.getInteger("greenBeamX");
        this.greenBeam.beamY = tag.getInteger("greenBeamY");
        this.greenBeam.beamZ = tag.getInteger("greenBeamZ");
        this.blueBeam.beamX = tag.getInteger("blueBeamX");
        this.blueBeam.beamY = tag.getInteger("blueBeamY");
        this.blueBeam.beamZ = tag.getInteger("blueBeamZ");
        this.redBeam.beamX = tag.getInteger("redBeamX");
        this.redBeam.beamY = tag.getInteger("redBeamY");
        this.redBeam.beamZ = tag.getInteger("redBeamZ");
        this.yellowBeam.beamX = tag.getInteger("yellowBeamX");
        this.yellowBeam.beamY = tag.getInteger("yellowBeamY");
        this.yellowBeam.beamZ = tag.getInteger("yellowBeamZ");
        this.purpleBeam.beamX = tag.getInteger("purpleBeamX");
        this.purpleBeam.beamY = tag.getInteger("purpleBeamY");
        this.purpleBeam.beamZ = tag.getInteger("purpleBeamZ");
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("spawnTick", this.spawnTick);
        tag.setInteger("greenBeamX", this.greenBeam.beamX);
        tag.setInteger("greenBeamY", this.greenBeam.beamY);
        tag.setInteger("greenBeamZ", this.greenBeam.beamZ);
        tag.setInteger("blueBeamX", this.blueBeam.beamX);
        tag.setInteger("blueBeamY", this.blueBeam.beamY);
        tag.setInteger("blueBeamZ", this.blueBeam.beamZ);
        tag.setInteger("redBeamX", this.redBeam.beamX);
        tag.setInteger("redBeamY", this.redBeam.beamY);
        tag.setInteger("redBeamZ", this.redBeam.beamZ);
        tag.setInteger("yellowBeamX", this.yellowBeam.beamX);
        tag.setInteger("yellowBeamY", this.yellowBeam.beamY);
        tag.setInteger("yellowBeamZ", this.yellowBeam.beamZ);
        tag.setInteger("purpleBeamX", this.purpleBeam.beamX);
        tag.setInteger("purpleBeamY", this.purpleBeam.beamY);
        tag.setInteger("purpleBeamZ", this.purpleBeam.beamZ);
    }

    public class BeamCoords {
        public int beamX = 0;
        public int beamY = 0;
        public int beamZ = 0;

        public boolean equals(Object o) {
            if (!(o instanceof BeamCoords)) {
                return false;
            }
            BeamCoords beamCoords = (BeamCoords)o;
            return beamCoords.beamX == this.beamX && beamCoords.beamY == this.beamY && beamCoords.beamZ == this.beamZ;
        }
    }
}

