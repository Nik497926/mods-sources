/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade.barter;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.deathcry.deathtrade.barter.BarterHolder;
import ru.deathcry.deathtrade.barter.BarterInvHolder;
import ru.deathcry.deathtrade.barter.Barterer;
import ru.deathcry.deathtrade.packets.PacketDispatcher;
import ru.deathcry.deathtrade.packets.client.ActionMessage;

public class BarterContainer
extends Container {
    public boolean closed = false;
    public EntityPlayer player;
    public BarterHolder holder;

    public BarterContainer(EntityPlayer player, BarterHolder holder) {
        int i;
        int j;
        this.player = player;
        this.holder = holder;
        for (int j2 = 0; j2 < 3; ++j2) {
            for (int i2 = 0; i2 < 9; ++i2) {
                this.addSlotToContainer(new Slot((IInventory)player.inventory, i2 + j2 * 9 + 9, 11 + i2 * 18, 26 + j2 * 18));
            }
        }
        for (int i3 = 0; i3 < 9; ++i3) {
            this.addSlotToContainer(new Slot((IInventory)player.inventory, i3, 11 + i3 * 18, 80));
        }
        Barterer trader = holder.asBarter(player);
        Barterer opponent = holder.getOpponent(player);
        for (j = 0; j < 4; ++j) {
            for (i = 0; i < 9; ++i) {
                this.addSlotToContainer(new Slot((IInventory)trader.getHolderInventory(), i + j * 9, 193 + i * 18, 26 + j * 18));
            }
        }
        for (j = 0; j < 4; ++j) {
            for (i = 0; i < 9; ++i) {
                this.addSlotToContainer(new Slot((IInventory)opponent.getHolderInventory(), i + j * 9, 193 + i * 18, 129 + j * 18));
            }
        }
    }

    public ItemStack slotClick(int slot, int p_75144_2_, int p_75144_3_, EntityPlayer player) {
        if (slot >= 72) {
            return null;
        }
        if (slot >= 0 && player.worldObj.isRemote) {
            PacketDispatcher.sendToServer(new ActionMessage(2));
        }
        return super.slotClick(slot, p_75144_2_, p_75144_3_, player);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int fromSlot) {
        ItemStack previous = null;
        Slot slot = (Slot)this.inventorySlots.get(fromSlot);
        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();
            if (fromSlot < 36 ? !this.mergeItemStack(current, 36, 72, false) : !this.mergeItemStack(current, 0, 36, true)) {
                return null;
            }
            if (current.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }
            if (current.stackSize == previous.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(player, current);
        }
        return previous;
    }

    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    public void onContainerClosed(EntityPlayer player) {
        if (player.worldObj.isRemote) {
            return;
        }
        super.onContainerClosed(player);
        BarterInvHolder inv = this.holder.getHolder(player);
        for (int j = 0; j < inv.getSizeInventory(); ++j) {
            ItemStack item = inv.getStackInSlot(j);
            if (item == null) continue;
            if (!player.inventory.addItemStackToInventory(item)) {
                player.worldObj.spawnEntityInWorld((Entity)new EntityItem(player.worldObj, player.posX, player.posY + 1.0, player.posZ, item));
            }
            inv.setInventorySlotContents(j, null);
        }
        player.inventoryContainer.detectAndSendChanges();
    }
}

