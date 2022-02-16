/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ContainerChest
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package net.divinerpg.blocks.iceika.tileentity;

import java.util.Iterator;
import java.util.List;
import net.divinerpg.blocks.iceika.BlockPresentBox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityPresentBox
extends TileEntity
implements IInventory {
    private ItemStack[] chestContents = new ItemStack[54];
    private String customName;
    public float lidAngle;
    public float prevLidAngle;
    public int numPlayersUsing;
    private int ticksSinceSync;

    public TileEntityPresentBox() {
        this.func_145976_a("Present Box");
    }

    public int getSizeInventory() {
        return 54;
    }

    public void closeInventory() {
        if (this.getBlockType() instanceof BlockPresentBox) {
            --this.numPlayersUsing;
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numPlayersUsing);
            this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
            this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());
        }
    }

    public ItemStack getStackInSlot(int slot) {
        return this.chestContents[slot];
    }

    public ItemStack decrStackSize(int slot, int amount) {
        if (this.chestContents[slot] != null) {
            if (this.chestContents[slot].stackSize <= amount) {
                ItemStack itemstack = this.chestContents[slot];
                this.chestContents[slot] = null;
                this.markDirty();
                return itemstack;
            }
            ItemStack itemstack = this.chestContents[slot].splitStack(amount);
            if (this.chestContents[slot].stackSize == 0) {
                this.chestContents[slot] = null;
            }
            this.markDirty();
            return itemstack;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int slot) {
        if (this.chestContents[slot] != null) {
            ItemStack itemstack = this.chestContents[slot];
            this.chestContents[slot] = null;
            return itemstack;
        }
        return null;
    }

    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.chestContents[slot] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : "container.chest";
    }

    public boolean hasCustomInventoryName() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void func_145976_a(String name) {
        this.customName = name;
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        NBTTagList nbttaglist = tag.getTagList("Items", 10);
        this.chestContents = new ItemStack[this.getSizeInventory()];
        if (tag.hasKey("CustomName", 8)) {
            this.customName = tag.getString("CustomName");
        }
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xFF;
            if (j < 0 || j >= this.chestContents.length) continue;
            this.chestContents[j] = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbttagcompound1);
        }
    }

    public void openInventory() {
        if (this.numPlayersUsing < 0) {
            this.numPlayersUsing = 0;
        }
        ++this.numPlayersUsing;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numPlayersUsing);
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.chestContents.length; ++i) {
            if (this.chestContents[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.chestContents[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
        tag.setTag("Items", (NBTBase)nbttaglist);
        if (this.hasCustomInventoryName()) {
            tag.setString("CustomName", this.customName);
        }
    }

    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean receiveClientEvent(int p_145842_1_, int p_145842_2_) {
        if (p_145842_1_ == 1) {
            this.numPlayersUsing = p_145842_2_;
            return true;
        }
        return super.receiveClientEvent(p_145842_1_, p_145842_2_);
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public void updateEntity() {
        super.updateEntity();
        ++this.ticksSinceSync;
        float f;

        if (!this.worldObj.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0) {
            this.numPlayersUsing = 0;
            f = 5.0F;
            List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)((float)this.xCoord - f), (double)((float)this.yCoord - f), (double)((float)this.zCoord - f), (double)((float)(this.xCoord + 1) + f), (double)((float)(this.yCoord + 1) + f), (double)((float)(this.zCoord + 1) + f)));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityPlayer entityplayer = (EntityPlayer)iterator.next();

                if (entityplayer.openContainer instanceof ContainerChest) {
                    IInventory iinventory = ((ContainerChest)entityplayer.openContainer).getLowerChestInventory();

                    if (iinventory == this) ++this.numPlayersUsing;
                }
            }
        }

        this.prevLidAngle = this.lidAngle;
        f = 0.1f;
        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0f) {
            double d1 = (double)this.xCoord + 0.5;
            double d2 = (double)this.zCoord + 0.5;
            this.worldObj.playSoundEffect(d1, (double)this.yCoord + 0.5, d2, "random.chestopen", 0.5f, this.worldObj.rand.nextFloat() * 0.1f + 0.9f);
        }
        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0f || this.numPlayersUsing > 0 && this.lidAngle < 1.0f) {
            float f2;
            float f1 = this.lidAngle;
            this.lidAngle = this.numPlayersUsing > 0 ? (this.lidAngle += f) : (this.lidAngle -= f);
            if (this.lidAngle > 1.0f) {
                this.lidAngle = 1.0f;
            }
            if (this.lidAngle < (f2 = 0.5f) && f1 >= f2) {
                double d2 = (double)this.xCoord + 0.5;
                double d0 = (double)this.zCoord + 0.5;
                this.worldObj.playSoundEffect(d2, (double)this.yCoord + 0.5, d0, "random.chestclosed", 0.5f, this.worldObj.rand.nextFloat() * 0.1f + 0.9f);
            }
            if (this.lidAngle < 0.0f) {
                this.lidAngle = 0.0f;
            }
        }
    }
}

