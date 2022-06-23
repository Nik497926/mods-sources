/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import ru.simplemc.simplecore.mod.common.inventory.InventoryBase;
import ru.simplemc.simplecore.mod.common.tileentity.TileEntityTradeStation;
import ru.simplemc.simplecore.mod.util.ItemStackHelper;

public class InventoryTileEntityTradeStation
extends InventoryBase
implements ISidedInventory {
    private static final int[] ACCESSIBLE_SLOTS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103};
    private final TileEntityTradeStation tradeStation;

    public InventoryTileEntityTradeStation(TileEntityTradeStation tradeStation) {
        super(106);
        this.tradeStation = tradeStation;
    }

    public ItemStack getTradeItemStack() {
        return this.getStackInSlot(104);
    }

    public ItemStack getPurchaseSlotItemStack() {
        return this.getStackInSlot(105);
    }

    public void setPurchaseSlotItemStack(ItemStack itemStack) {
        this.setInventorySlotContents(105, itemStack);
    }

    public int getAvailableTradeItemsCount() {
        ItemStack tradeItemStack = this.getTradeItemStack();
        if (tradeItemStack == null) {
            return 0;
        }
        if (this.tradeStation.getData().isInfinity()) {
            return 100000;
        }
        int count = 0;
        for (int index : ACCESSIBLE_SLOTS) {
            ItemStack itemStackInSlot = this.getStackInSlot(index);
            if (itemStackInSlot == null || !ItemStackHelper.isStackEqualStrict(itemStackInSlot, tradeItemStack)) continue;
            count += itemStackInSlot.stackSize;
        }
        return Math.max(count / tradeItemStack.stackSize, 0);
    }

    public void extractItemStackForPurchase(ItemStack tradeItemStack, ItemStack purchasedItemStack) {
        ItemStack extractedItemStack = null;
        if (!this.tradeStation.getData().isInfinity()) {
            for (int index : ACCESSIBLE_SLOTS) {
                ItemStack slotItemStack = this.getStackInSlot(index);
                if (slotItemStack == null || !ItemStackHelper.isStackEqualStrict(slotItemStack, tradeItemStack) || slotItemStack.stackSize < tradeItemStack.stackSize) continue;
                extractedItemStack = this.decrStackSize(index, this.getTradeItemStack().stackSize);
                break;
            }
        } else {
            extractedItemStack = tradeItemStack.copy();
        }
        if (extractedItemStack != null && purchasedItemStack != null) {
            extractedItemStack.stackSize += purchasedItemStack.stackSize;
        }
        this.setPurchaseSlotItemStack(extractedItemStack);
    }

    @Override
    public ItemStack addItem(ItemStack itemStack) {
        return this.addItem(itemStack, ACCESSIBLE_SLOTS.length);
    }

    @Override
    public boolean canAddItem(ItemStack itemStackToAdd) {
        return this.canAddItem(itemStackToAdd, ACCESSIBLE_SLOTS.length);
    }

    public int[] getAccessibleSlotsFromSide(int index) {
        return ACCESSIBLE_SLOTS;
    }

    public boolean canInsertItem(int index, ItemStack itemStack, int side) {
        return index < 104;
    }

    public boolean canExtractItem(int index, ItemStack itemStack, int side) {
        return index < 104;
    }

    public void markDirty() {
        this.tradeStation.markDirty();
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return !this.tradeStation.isInvalid() && this.tradeStation.isSameTileEntityAtWorld(player.worldObj) && this.tradeStation.isPlayerInUseRange(player);
    }

    public TileEntityTradeStation getTradeStation() {
        return this.tradeStation;
    }
}

