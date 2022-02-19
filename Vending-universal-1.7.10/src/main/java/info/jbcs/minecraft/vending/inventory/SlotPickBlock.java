/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package info.jbcs.minecraft.vending.inventory;

import info.jbcs.minecraft.vending.inventory.ContainerPickBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotPickBlock
extends Slot {
    ContainerPickBlock container;

    public SlotPickBlock(ContainerPickBlock c, int index, int x, int y) {
        super((IInventory)c.inventory, index, x, y);
        this.container = c;
    }

    void click(EntityPlayer player, ItemStack itemstack, int count) {
        int newSize;
        player.inventory.setItemStack(null);
        if (itemstack == null) {
            return;
        }
        if (this.container.gui == null) {
            return;
        }
        this.putStack(new ItemStack(itemstack.getItem(), itemstack.stackSize, itemstack.getItemDamage()));
        if (this.container.resultSlot == this) {
            newSize = itemstack.stackSize - count;
        } else {
            newSize = itemstack.stackSize;
            ItemStack otherstack = this.container.resultSlot.getStack();
            newSize = otherstack != null && otherstack.getItem() == itemstack.getItem() && otherstack.getItemDamage() == itemstack.getItemDamage() ? otherstack.stackSize + count : count;
        }
        if (newSize > 64) {
            newSize = 64;
        }
        this.container.resultSlot.putStack(newSize <= 0 ? null : new ItemStack(itemstack.getItem(), newSize, itemstack.getItemDamage()));
    }

    public void onPickupFromSlot(EntityPlayer player, ItemStack itemstack) {
        super.onPickupFromSlot(player, itemstack);
        this.click(player, itemstack, 1);
    }

    public ItemStack transferStackInSlot(EntityPlayer player) {
        this.click(player, this.getStack(), 64);
        return null;
    }
}

