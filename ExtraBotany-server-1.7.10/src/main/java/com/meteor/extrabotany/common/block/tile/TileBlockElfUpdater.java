/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.item.basic.awakeArmController;
import com.meteor.extrabotany.common.item.basic.elfirium;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGBoots;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGChest;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGHelm;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGLegs;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.tool.terrasteel.ItemTerraPick;

public class TileBlockElfUpdater
extends TileEntity
implements IInventory,
ISidedInventory {
    private ItemStack[] chestContents = new ItemStack[72];
    private String customName;
    private String ownerBlock;
    public int numPlayersUsing;
    private int playersUsing;
    private IInventory mainInv;
    private int needMaterial = 3;

    public int getSizeInventory() {
        return 4;
    }

    public ItemStack getStackInSlot(int i) {
        return this.chestContents[i];
    }

    public ItemStack decrStackSize(int i, int i2) {
        if (this.chestContents[i] != null) {
            if (this.chestContents[i].stackSize <= i2) {
                ItemStack itemstack = this.chestContents[i];
                this.chestContents[i] = null;
                this.markDirty();
                return itemstack;
            }
            ItemStack itemstack = this.chestContents[i].splitStack(i2);
            if (this.chestContents[i].stackSize == 0) {
                this.chestContents[i] = null;
            }
            this.markDirty();
            return itemstack;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int i) {
        if (this.chestContents[i] != null) {
            ItemStack itemstack = this.chestContents[i];
            this.chestContents[i] = null;
            return itemstack;
        }
        return null;
    }

    public void setInventorySlotContents(int i, ItemStack itemStack) {
        this.chestContents[i] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    public String getInventoryName() {
        return null;
    }

    public void onGuiButtonPress(int id) {
        ItemStack shema = this.getStackInSlot(0);
        ItemStack elfirium2 = this.getStackInSlot(1);
        ItemStack input = this.getStackInSlot(2);
        ItemStack output = this.getStackInSlot(3);
        if (this.checkItem(shema, 0).booleanValue() && this.checkItem(elfirium2, 1).booleanValue() && this.checkItem(input, 2).booleanValue() && this.checkItem(output, 3).booleanValue()) {
            if (input.getItem() instanceof ItemOGArmor) {
                String bind = ItemNBTHelper.getString((ItemStack)input, (String)"soulbind", (String)"");
                if (input.getItem() instanceof ItemOGHelm) {
                    output = new ItemStack(ModItems.awakeoghelm);
                }
                if (input.getItem() instanceof ItemOGChest) {
                    output = new ItemStack(ModItems.awakeogchest);
                }
                if (input.getItem() instanceof ItemOGLegs) {
                    output = new ItemStack(ModItems.awakeoglegs);
                }
                if (input.getItem() instanceof ItemOGBoots) {
                    output = new ItemStack(ModItems.awakeogboots);
                }
                ItemNBTHelper.setString((ItemStack)output, (String)"soulbind", (String)bind);
            } else if (input.getItem() instanceof ItemTerraPick) {
                int mana = ItemNBTHelper.getInt((ItemStack)input, (String)"mana", (int)0);
                output = new ItemStack(ModItems.awakepick);
                ItemNBTHelper.setInt((ItemStack)output, (String)"mana", (int)mana);
            }
            input = null;
            if (shema.stackSize == 1) {
                shema = null;
            } else {
                --shema.stackSize;
            }
            if (elfirium2.stackSize == 3) {
                elfirium2 = null;
            } else {
                elfirium2.stackSize -= 3;
            }
        }
        this.setInventorySlotContents(0, shema);
        this.setInventorySlotContents(1, elfirium2);
        this.setInventorySlotContents(2, input);
        this.setInventorySlotContents(3, output);
    }

    public Boolean checkItem(ItemStack item, int ind) {
        switch (ind) {
            case 0: {
                if (item != null && item.getItem() instanceof awakeArmController) {
                    return true;
                }
                return false;
            }
            case 1: {
                if (item != null && item.getItem() instanceof elfirium && item.stackSize >= this.needMaterial) {
                    return true;
                }
                return false;
            }
            case 2: {
                if (item != null && this.checkInput(item).booleanValue()) {
                    return true;
                }
                return false;
            }
            case 3: {
                if (item == null) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private Boolean checkInput(ItemStack item) {
        if (item.getItem() instanceof ItemOGArmor) {
            if (item.getItem() instanceof ItemAwakeOGArmor) {
                return false;
            }
            return true;
        }
        if (item.getItem() instanceof ItemTerraPick) {
            return true;
        }
        return false;
    }

    public void setBlockOwner(EntityPlayer player) {
        if (player != null) {
            this.ownerBlock = player.getDisplayName();
        }
    }

    public String getBlockOwner() {
        return this.ownerBlock;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.chestContents = new ItemStack[this.getSizeInventory()];
        if (nbt.hasKey("CustomName", 8)) {
            this.customName = nbt.getString("CustomName");
        }
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xFF;
            if (j < 0 || j >= this.chestContents.length) continue;
            this.chestContents[j] = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbttagcompound1);
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList nbttaglist = new NBTTagList();
        NBTTagCompound var1 = new NBTTagCompound();
        if (nbt != null) {
            var1 = new NBTTagCompound();
        }
        for (int i = 0; i < this.chestContents.length; ++i) {
            if (this.chestContents[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.chestContents[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
        nbt.setTag("Items", (NBTBase)nbttaglist);
        if (this.hasCustomInventoryName()) {
            nbt.setString("CustomName", this.customName);
        }
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

    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
    }

    public void updateEntity() {
        super.updateEntity();
    }

    public void openInventory() {
        this.triggerPlayerUsageChange(1);
    }

    public void closeInventory() {
        this.triggerPlayerUsageChange(-1);
    }

    public boolean receiveClientEvent(int p_145842_1_, int p_145842_2_) {
        if (p_145842_1_ == 1) {
            this.numPlayersUsing = p_145842_2_;
            return true;
        }
        return super.receiveClientEvent(p_145842_1_, p_145842_2_);
    }

    private void triggerPlayerUsageChange(int change) {
        if (this.worldObj != null) {
            this.playersUsing += change;
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, super.getBlockType(), 1, this.playersUsing);
        }
    }

    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return true;
    }

    public void invalidate() {
        super.invalidate();
        this.updateContainingBlockInfo();
    }

    public int[] getAccessibleSlotsFromSide(int i) {
        return new int[0];
    }

    public boolean canInsertItem(int i, ItemStack itemStack, int i1) {
        return false;
    }

    public boolean canExtractItem(int i, ItemStack itemStack, int i1) {
        return false;
    }
}

