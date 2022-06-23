/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.component;

import ic2.api.energy.prefab.BasicSink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEnergySinkComponent
extends BasicSink {
    private boolean standalone = false;

    public TileEnergySinkComponent(TileEntity parentTile, int capacity, int tier) {
        super(parentTile, capacity, tier);
    }

    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
        return !this.standalone && direction != ForgeDirection.UP;
    }

    public boolean canUseEnergy(double amount) {
        return this.standalone || super.canUseEnergy(amount);
    }

    public boolean useEnergy(double amount) {
        return this.standalone || super.useEnergy(amount);
    }

    public void writeToNBT(NBTTagCompound tag) {
        tag.setBoolean("Standalone", this.standalone);
        super.writeToNBT(tag);
    }

    public void readFromNBT(NBTTagCompound tag) {
        this.standalone = tag.getBoolean("Standalone");
        super.readFromNBT(tag);
    }

    public boolean isStandalone() {
        return this.standalone;
    }

    public void setStandalone(boolean standalone) {
        this.standalone = standalone;
    }
}

