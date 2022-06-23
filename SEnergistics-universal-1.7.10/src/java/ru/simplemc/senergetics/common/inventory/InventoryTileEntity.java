/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class InventoryTileEntity<T extends TileEntity>
implements IInventory {
    protected final T tileEntity;
    protected final ItemStack[] inventory;

    public InventoryTileEntity(T tileEntity, int inventorySize) {
        this.inventory = new ItemStack[inventorySize];
        this.tileEntity = tileEntity;
    }

    public int getSizeInventory() {
        return this.inventory.length;
    }

    public ItemStack getStackInSlot(int slot) {
        return this.inventory.length > slot ? this.inventory[slot] : null;
    }

    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack itemStack = this.getStackInSlot(slot);
        if (itemStack != null) {
            if (this.inventory[slot].stackSize <= amount) {
                itemStack = this.inventory[slot];
                this.inventory[slot] = null;
            } else {
                itemStack = this.inventory[slot].splitStack(amount);
                if (this.inventory[slot].stackSize == 0) {
                    this.inventory[slot] = null;
                }
            }
            this.markDirty();
        }
        return itemStack;
    }

    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        if (this.inventory.length > slot) {
            this.inventory[slot] = itemStack;
            this.markDirty();
        }
    }

    public String getInventoryName() {
        return this.tileEntity.getBlockType().getLocalizedName();
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public void markDirty() {
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return !this.tileEntity.isInvalid() && player.worldObj.getTileEntity(((TileEntity)this.tileEntity).xCoord, ((TileEntity)this.tileEntity).yCoord, ((TileEntity)this.tileEntity).zCoord) == this.tileEntity && player.getDistanceSq((double)((TileEntity)this.tileEntity).xCoord + 0.5, (double)((TileEntity)this.tileEntity).yCoord + 0.5, (double)((TileEntity)this.tileEntity).zCoord + 0.5) <= 64.0;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return itemStack == null || itemStack.stackSize <= itemStack.getMaxStackSize();
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        NBTTagList inventoryTagList = new NBTTagList();
        for (int i = 0; i < this.inventory.length; ++i) {
            ItemStack stack = this.inventory[i];
            if (stack == null) continue;
            NBTTagCompound slotTag = new NBTTagCompound();
            slotTag.setShort("Slot", (short)i);
            stack.writeToNBT(slotTag);
            inventoryTagList.appendTag((NBTBase)slotTag);
        }
        tagCompound.setTag("Inventory", (NBTBase)inventoryTagList);
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
        if (tagCompound.hasKey("Inventory")) {
            NBTTagList inventoryTagList = tagCompound.getTagList("Inventory", 10);
            for (int i = 0; i < inventoryTagList.tagCount(); ++i) {
                NBTTagCompound stackTag = inventoryTagList.getCompoundTagAt(i);
                this.inventory[stackTag.getShort((String)"Slot")] = ItemStack.loadItemStackFromNBT((NBTTagCompound)stackTag);
            }
        }
    }

    public T getTileEntity() {
        return this.tileEntity;
    }
}

