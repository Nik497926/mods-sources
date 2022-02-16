/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.blocks.vethea.container;

import net.divinerpg.blocks.vethea.container.SlotInfusion;
import net.divinerpg.blocks.vethea.container.tileentity.TileEntityInfusionTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerInfusionTable
extends Container {
    public TileEntityInfusionTable tileentity;

    public ContainerInfusionTable(InventoryPlayer inv, TileEntityInfusionTable te) {
        this.tileentity = te;
        this.addSlotToContainer(new Slot((IInventory)te, 0, 18, 39));
        this.addSlotToContainer(new Slot((IInventory)te, 1, 18, 59));
        this.addSlotToContainer((Slot)new SlotInfusion(inv.player, te, 2, 63, 49));
        this.bindPlayerInventory(inv);
    }

    public void bindPlayerInventory(InventoryPlayer inventoryplayer) {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)inventoryplayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)inventoryplayer, i, 8 + i * 18, 142));
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (par2 == 0) {
                if (!this.mergeItemStack(itemstack1, 1, 37, true)) {
                    return null;
                }
            } else {
                if (((Slot)this.inventorySlots.get(0)).getHasStack() || !((Slot)this.inventorySlots.get(0)).isItemValid(itemstack1)) {
                    return null;
                }
                if (itemstack1.hasTagCompound() && itemstack1.stackSize == 1) {
                    ((Slot)this.inventorySlots.get(0)).putStack(itemstack1.copy());
                    itemstack1.stackSize = 0;
                } else if (itemstack1.stackSize >= 1) {
                    ((Slot)this.inventorySlots.get(0)).putStack(new ItemStack(itemstack1.getItem(), 1, itemstack1.getItemDamage()));
                    --itemstack1.stackSize;
                }
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }
        return itemstack;
    }

    public boolean canInteractWith(EntityPlayer player) {
        return this.tileentity.isUseableByPlayer(player);
    }
}

