/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.inventory;

import com.meteor.extrabotany.client.gui.ContainerMod;
import com.meteor.extrabotany.client.inventory.SlotChest;
import com.meteor.extrabotany.common.block.tile.TileBlockElfUpdater;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAwakeElf
extends ContainerMod {
    private final TileBlockElfUpdater te;
    private int lastTimer = -1;
    private String lastTarget = "";
    private final Integer fx = 0;
    private final Integer fy = 10;
    public Boolean valid = true;

    public ContainerAwakeElf(InventoryPlayer playerInventory, TileBlockElfUpdater te) {
        this.addSlotToContainer(new SlotChest(te, 0, 39, 52, this.valid));
        this.addSlotToContainer(new SlotChest(te, 1, 39, 120, this.valid));
        this.addSlotToContainer(new SlotChest(te, 2, 75, 86, this.valid));
        this.addSlotToContainer(new SlotChest(te, 3, 183, 86, this.valid));
        this.addPlayerSlots(playerInventory, 39, 167);
        this.te = te;
    }

    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.te.isUseableByPlayer(entityPlayer);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        super.updateProgressBar(id, value);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (slotIndex < 54 && !this.mergeItemStack(itemstack1, 54, 42, true)) {
                return null;
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(player, itemstack1);
        }
        return null;
    }
}

