/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.slot;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class SlotSoulSand
extends Slot {
    public SlotSoulSand(IInventory inventory, int slotIndex, int posX, int posY) {
        super(inventory, slotIndex, posX, posY);
    }

    public boolean isItemValid(ItemStack itemStack) {
        return SlotSoulSand.isMatches(itemStack);
    }

    public int getSlotStackLimit() {
        return 64;
    }

    public static boolean isMatches(ItemStack itemStack) {
        return itemStack != null && itemStack.getItem() instanceof ItemBlock && Block.getBlockFromItem((Item)itemStack.getItem()) == Blocks.soul_sand;
    }
}

