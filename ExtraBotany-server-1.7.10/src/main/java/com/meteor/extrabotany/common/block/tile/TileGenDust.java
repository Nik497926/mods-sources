/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.tile.TileEntityContainer;
import com.meteor.extrabotany.common.item.ModItems;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileGenDust
extends TileEntityContainer
implements ISidedInventory {
    private int cd = 3600;

    public TileGenDust() {
        super(1);
    }

    @Override
    public void updateEntityServer() {
        if (this.cd > 0) {
            --this.cd;
            return;
        }
        if (this.getStackInSlot(0) == null || this.getStackInSlot((int)0).stackSize < this.getStackInSlot(0).getMaxStackSize()) {
            this.cd = 3600;
            ItemStack put = new ItemStack(ModItems.dust, 1, 2);
            if (this.getStackInSlot(0) != null) {
                put.stackSize += this.getStackInSlot((int)0).stackSize;
            }
            this.setInventorySlotContents(0, put);
        }
    }

    @Override
    public void updateEntityClient() {
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
    }

    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[]{0};
    }

    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return false;
    }

    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return true;
    }
}

