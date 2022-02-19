/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 */
package info.jbcs.minecraft.vending.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerTileEntity<T extends TileEntity>
extends Container {
    public final IInventory playerInventory;
    public final T entity;
    public int playerSlotsCount;

    public ContainerTileEntity(IInventory playerInv, T tileEntity, int startX, int startY) {
        this.playerInventory = playerInv;
        this.entity = tileEntity;
        for (int k = 0; k < 3; ++k) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.addSlotToContainer(new Slot(playerInv, j1 + k * 9 + 9, startX + j1 * 18, startY + k * 18));
            }
        }
        for (int l = 0; l < 9; ++l) {
            this.addSlotToContainer(new Slot(playerInv, l, startX + l * 18, startY + 142 - 84));
        }
        this.playerSlotsCount = this.inventorySlots.size();
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return ((IInventory)this.entity).isUseableByPlayer(entityplayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i < this.playerSlotsCount ? !this.mergeItemStack(itemstack1, this.playerSlotsCount, this.inventorySlots.size(), true) : !this.mergeItemStack(itemstack1, 0, this.playerSlotsCount, false)) {
                return null;
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}

