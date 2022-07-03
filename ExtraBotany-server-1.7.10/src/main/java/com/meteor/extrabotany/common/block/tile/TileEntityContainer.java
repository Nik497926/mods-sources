/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityContainer
extends TileEntity
implements IInventory {
    private final int slotCount;
    private int playersUsing;
    public ItemStack[] inventory;

    public TileEntityContainer(int slotCount) {
        this.slotCount = slotCount;
        this.inventory = new ItemStack[slotCount];
    }

    protected boolean isItemStackEquals(ItemStack a, ItemStack b) {
        if (a == null && b != null) {
            return false;
        }
        if (a != null && b == null) {
            return false;
        }
        if (a == null && b == null) {
            return true;
        }
        return a.getItem() == b.getItem() && a.getItemDamage() == b.getItemDamage() && a.copy().stackSize + b.copy().stackSize <= a.getMaxStackSize() && ItemStack.areItemStackTagsEqual((ItemStack)a, (ItemStack)b);
    }

    public final void updateEntity() {
        super.updateEntity();
        if (this.worldObj.isRemote) {
            this.updateEntityClient();
        } else {
            this.updateEntityServer();
        }
    }

    public abstract void updateEntityServer();

    public abstract void updateEntityClient();

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.inventory[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
        nbt.setTag("Items", (NBTBase)nbttaglist);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            ItemStack v0;
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xFF;
            if (j < 0 || j >= this.inventory.length) continue;
            this.inventory[j] = v0 = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbttagcompound1);
        }
    }

    public int getSizeInventory() {
        return this.slotCount;
    }

    public ItemStack getStackInSlot(int ind) {
        return this.inventory[ind];
    }

    public ItemStack decrStackSize(int idSlot, int stackSize) {
        if (this.inventory[idSlot] != null) {
            if (this.inventory[idSlot].stackSize <= stackSize) {
                ItemStack itemstack = this.inventory[idSlot];
                this.inventory[idSlot] = null;
                this.markDirty();
                return itemstack;
            }
            ItemStack itemstack = this.inventory[idSlot].splitStack(stackSize);
            if (this.inventory[idSlot].stackSize == 0) {
                this.inventory[idSlot] = null;
            }
            this.markDirty();
            return itemstack;
        }
        return null;
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, data);
    }

    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }

    public ItemStack getStackInSlotOnClosing(int index) {
        if (this.inventory[index] != null) {
            ItemStack itemstack = this.inventory[index];
            this.inventory[index] = null;
            return itemstack;
        }
        return null;
    }

    public void setInventorySlotContents(int index, ItemStack item) {
        this.inventory[index] = item;
        if (item != null && item.stackSize > this.getInventoryStackLimit()) {
            item.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    public String getInventoryName() {
        return null;
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityPlayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public void openInventory() {
        this.triggerPlayerUsageChange(1);
    }

    public void closeInventory() {
        this.triggerPlayerUsageChange(-1);
    }

    private void triggerPlayerUsageChange(int change) {
        if (this.worldObj != null) {
            this.playersUsing += change;
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, super.getBlockType(), 1, this.playersUsing);
        }
    }

    public boolean canExecuteSlot(int id) {
        return this.getStackInSlot(id) == null || this.getStackInSlot((int)id).stackSize < this.getStackInSlot(id).getMaxStackSize();
    }

    public boolean canTakeFromSlot(int id) {
        return this.getStackInSlot(id) != null;
    }

    public boolean canExecuteSlot(int id, int count) {
        if (this.getStackInSlot(id) == null) {
            return true;
        }
        return this.getStackInSlot((int)id).copy().stackSize + count <= this.getStackInSlot(id).getMaxStackSize();
    }

    public void onBlockBreak() {
        for (ItemStack stack : this.inventory) {
            if (stack == null) continue;
            EntityItem ei = new EntityItem(this.worldObj, (double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5, stack);
            this.worldObj.spawnEntityInWorld((Entity)ei);
        }
    }

    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }
}

