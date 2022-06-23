/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import ru.simplemc.simplecore.mod.common.inventory.InventoryBackpack;
import ru.simplemc.simplecore.mod.common.inventory.slot.SlotBackpack;
import ru.simplemc.simplecore.mod.common.inventory.slot.SlotBlockedBackpack;
import ru.simplemc.simplecore.mod.common.item.ItemBackpack;

public class ContainerBackpack
extends Container {
    private final InventoryBackpack inventoryBackpack;
    private SlotBlockedBackpack slotBlockedBackpack;

    public ContainerBackpack(InventoryPlayer inventoryPlayer, ItemStack itemStack) {
        this.inventoryBackpack = new InventoryBackpack(inventoryPlayer, itemStack);
        this.addSlots();
    }

    private void addSlots() {
        int i;
        InventoryPlayer inventoryPlayer = this.inventoryBackpack.getInventoryPlayer();
        for (i = 0; i < 104; ++i) {
            this.addSlotToContainer(new SlotBackpack(this.inventoryBackpack, i, 12 + i % 13 * 18, 19 + i / 13 * 18));
        }
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 48 + j * 18, 174 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            Slot slot;
            int posY = 232;
            int posX = 48 + i * 18;
            if (this.isBlockedBackpackSlot(inventoryPlayer, i)) {
                this.slotBlockedBackpack = new SlotBlockedBackpack((IInventory)inventoryPlayer, i, posX, posY);
                slot = this.slotBlockedBackpack;
            } else {
                slot = new Slot((IInventory)inventoryPlayer, i, posX, posY);
            }
            this.addSlotToContainer(slot);
        }
    }

    private boolean isBlockedBackpackSlot(InventoryPlayer inventoryPlayer, int slot) {
        boolean isClientSide = inventoryPlayer.player.worldObj.isRemote;
        ItemStack itemStack = inventoryPlayer.getStackInSlot(slot);
        if (itemStack != null && itemStack.getItem() instanceof ItemBackpack) {
            if (isClientSide) {
                return slot == inventoryPlayer.currentItem || ItemBackpack.isSameBackpacks(itemStack, this.inventoryBackpack.getBackpackItemStack());
            }
            return ItemBackpack.isSameBackpacks(itemStack, this.inventoryBackpack.getBackpackItemStack());
        }
        return false;
    }

    public ItemStack slotClick(int slotTopIndex, int slotBottomIndex, int clickType, EntityPlayer player) {
        if (this.slotBlockedBackpack == null) {
            player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.backpack.container_error", new Object[0]));
            player.closeScreen();
            return null;
        }
        if ((clickType == 0 || clickType == 1) && this.slotBlockedBackpack.slotNumber == slotTopIndex) {
            return null;
        }
        if (clickType == 2 && slotTopIndex >= 0 && (slotTopIndex == this.slotBlockedBackpack.slotNumber || slotBottomIndex == this.slotBlockedBackpack.getButtonSlotNumber())) {
            return null;
        }
        return super.slotClick(slotTopIndex, slotBottomIndex, clickType, player);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        Slot slot = (Slot)this.inventorySlots.get(index);
        ItemStack transferredItemStack = null;
        if (slot != null && !(slot instanceof SlotBlockedBackpack) && slot.getHasStack()) {
            ItemStack slotItemStack = slot.getStack();
            transferredItemStack = slotItemStack.copy();
            if (slotItemStack.getItem() instanceof ItemBackpack ? (index < 131 ? !this.mergeItemStack(slotItemStack, 131, this.inventorySlots.size(), true) : !this.mergeItemStack(slotItemStack, 104, 131, false)) : (index < 104 ? !this.mergeItemStack(slotItemStack, 104, this.inventorySlots.size(), true) : !this.mergeItemStack(slotItemStack, 0, 104, false))) {
                return null;
            }
            if (slotItemStack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }
        return transferredItemStack;
    }

    public boolean canInteractWith(EntityPlayer player) {
        return this.inventoryBackpack.isUseableByPlayer(player);
    }

    public InventoryBackpack getInventoryBackpack() {
        return this.inventoryBackpack;
    }
}

