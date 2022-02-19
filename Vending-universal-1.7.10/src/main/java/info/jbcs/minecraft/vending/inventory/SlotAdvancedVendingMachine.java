/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package info.jbcs.minecraft.vending.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class SlotAdvancedVendingMachine
extends Slot {
    public SlotAdvancedVendingMachine(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    public void onPickupFromSlot(EntityPlayer player, ItemStack itemstack) {
        super.onPickupFromSlot(player, itemstack);
        player.inventory.setItemStack(null);
        this.putStack(new ItemStack(itemstack.getItem(), itemstack.stackSize, itemstack.getItemDamage()));
    }

    public IIcon getBackgroundIconIndex() {
        if (this.inventory.getStackInSlot(10) != null) {
            return this.inventory.getStackInSlot(10).getIconIndex();
        }
        return null;
    }

    public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
        return false;
    }

    public boolean isItemValid(ItemStack par1ItemStack) {
        return false;
    }
}

