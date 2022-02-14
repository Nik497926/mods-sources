/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.blocks.vethea.container;

import net.divinerpg.blocks.vethea.container.SlotDreamLamp;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDreamLamp
extends Container {
    private IInventory blockInv;
    private int numRows;

    public ContainerDreamLamp(IInventory player, IInventory block) {
        int j;
        this.blockInv = block;
        this.numRows = block.getSizeInventory() / 9;
        block.openInventory();
        int i = -19;
        this.addSlotToContainer(new SlotDreamLamp(block, 0, 80, 47));
        for (j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(player, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
            }
        }
        for (j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(player, j, 8 + j * 18, 161 + i));
        }
    }

    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (itemstack.getItem() != VetheaItems.acid) {
                return null;
            }
            if (index == 0 && !this.mergeItemStack(itemstack1, 1, this.inventorySlots.size(), false) || index != 0 && !this.mergeItemStack(itemstack1, 0, 1, false)) {
                return null;
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}

