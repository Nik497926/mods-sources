/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.info.Info;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import ru.simplemc.senergetics.common.tileentity.TileEntityNetworked;

public abstract class TileEntityNetworkingEnergySink
extends TileEntityNetworked
implements IEnergySink {
    private double energyCapacity;
    private double energyStored = 0.0;
    private int sinkTier;
    private boolean isAddedToEnergyNetwork = false;

    @Override
    public void updateEntity() {
        if (!this.isAddedToEnergyNetwork) {
            this.onLoaded();
        }
        super.updateEntity();
    }

    @SideOnly(value=Side.CLIENT)
    public int getEnergyCapacityFillPercent() {
        int percent = Math.min((int)(this.energyStored / this.energyCapacity * 100.0), 100);
        if (percent >= 95) {
            percent = 100;
        }
        return percent;
    }

    public void onLoaded() {
        if (!this.isAddedToEnergyNetwork && !FMLCommonHandler.instance().getEffectiveSide().isClient() && Info.isIc2Available()) {
            MinecraftForge.EVENT_BUS.post((Event)new EnergyTileLoadEvent((IEnergyTile)this));
            this.isAddedToEnergyNetwork = true;
        }
    }

    public void invalidate() {
        super.invalidate();
        this.onChunkUnload();
    }

    public void onChunkUnload() {
        if (this.isAddedToEnergyNetwork && Info.isIc2Available()) {
            MinecraftForge.EVENT_BUS.post((Event)new EnergyTileUnloadEvent((IEnergyTile)this));
            this.isAddedToEnergyNetwork = false;
        }
    }

    public boolean canUseEnergy(double amount) {
        return this.energyStored >= amount;
    }

    public boolean useEnergy(double amount) {
        if (this.canUseEnergy(amount) && !FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            this.energyStored -= amount;
            return true;
        }
        return false;
    }

    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
        return true;
    }

    public double getDemandedEnergy() {
        return this.energyCapacity - this.energyStored;
    }

    public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
        if (amount <= 0.0) {
            return 0.0;
        }
        if (this.energyStored >= this.energyCapacity) {
            return amount;
        }
        if (this.energyStored + amount >= this.energyCapacity) {
            double demandedEnergy = this.getDemandedEnergy();
            this.energyStored = this.energyCapacity;
            return amount - demandedEnergy;
        }
        this.energyStored += amount;
        return 0.0;
    }

    public void setEnergyCapacity(double energyCapacity) {
        this.energyCapacity = energyCapacity;
        if (this.energyCapacity < this.energyStored) {
            this.energyStored = this.energyCapacity;
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setDouble("energyStored", this.energyStored);
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.energyStored = compound.getDouble("energyStored");
    }

    @Override
    protected void readDescriptionPacket(NBTTagCompound compound) {
        this.energyStored = compound.getDouble("energyStored");
        this.energyCapacity = compound.getDouble("energyCapacity");
    }

    @Override
    protected void writeDescriptionPacket(NBTTagCompound compound) {
        compound.setDouble("energyStored", this.energyStored);
        compound.setDouble("energyCapacity", this.energyCapacity);
    }

    public double getEnergyCapacity() {
        return this.energyCapacity;
    }

    public double getEnergyStored() {
        return this.energyStored;
    }

    public void setEnergyStored(double energyStored) {
        this.energyStored = energyStored;
    }

    public int getSinkTier() {
        return this.sinkTier;
    }

    public void setSinkTier(int sinkTier) {
        this.sinkTier = sinkTier;
    }
}

