/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 */
package net.divinerpg.blocks.vethea.container.tileentity;

import net.divinerpg.utils.recipes.RecipesInfusionTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInfusionTable
extends TileEntity
implements IInventory {
    private ItemStack[] inventory = new ItemStack[3];
    private boolean decreaseable = false;

    public int getSizeInventory() {
        return this.inventory.length;
    }

    public ItemStack getStackInSlot(int i) {
        return this.inventory[i];
    }

    public void updateEntity() {
        if (this.inventory[0] != null && this.inventory[1] != null) {
            Item item1 = this.inventory[0].getItem();
            Item item2 = this.inventory[1].getItem();
            int c = this.inventory[0].stackSize;
            if (item1 != null && item2 != null) {
                RecipesInfusionTable x = new RecipesInfusionTable();
                Item item = RecipesInfusionTable.getOutput(item1, item2, c);
                this.inventory[2] = item != null ? new ItemStack(item) : null;
            }
        } else {
            this.inventory[2] = null;
        }
        super.updateEntity();
    }

    public ItemStack decrStackSize(int i, int j) {
        if (this.inventory[i] != null) {
            if (this.inventory[i].stackSize <= j) {
                ItemStack itemstack = this.inventory[i];
                this.inventory[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = this.inventory[i].splitStack(j);
            if (this.inventory[i].stackSize == 0) {
                this.inventory[i] = null;
            }
            return itemstack1;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int var1) {
        return null;
    }

    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.inventory[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) < 64.0;
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }

    public String getInventoryName() {
        return "TeInfusionTable";
    }

    public boolean hasCustomInventoryName() {
        return true;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        NBTTagList nbttaglist = tag.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xFF;
            if (j < 0 || j >= this.inventory.length) continue;
            this.inventory[j] = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbttagcompound1);
        }
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] == null) continue;
            NBTTagCompound slottag = new NBTTagCompound();
            slottag.setByte("Slot", (byte)i);
            this.inventory[i].writeToNBT(slottag);
            list.appendTag((NBTBase)slottag);
        }
        tag.setTag("Items", (NBTBase)list);
    }
}

