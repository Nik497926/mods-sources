/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ru.simplemc.simplecore.mod.common.inventory.InventoryBase;
import ru.simplemc.simplecore.mod.common.item.ItemBackpack;

public class InventoryBackpack
extends InventoryBase {
    private final ItemStack backpackItemStack;
    private final InventoryPlayer inventoryPlayer;

    public InventoryBackpack(InventoryPlayer inventoryPlayer, ItemStack itemStack) {
        super(104);
        this.backpackItemStack = itemStack;
        this.inventoryPlayer = inventoryPlayer;
        this.readFromNBT(itemStack.getTagCompound());
    }

    public void markDirty() {
        if (this.isUseableByPlayer(this.inventoryPlayer.player)) {
            this.writeToNBT(this.backpackItemStack.getTagCompound());
            this.inventoryPlayer.player.setCurrentItemOrArmor(0, this.backpackItemStack);
        }
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        ItemStack handItemStack = player.getHeldItem();
        return handItemStack != null && handItemStack.getItem() instanceof ItemBackpack && ItemBackpack.isSameBackpacks(this.backpackItemStack, handItemStack);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        if (compound == null) {
            return;
        }
        super.readFromNBT(compound);
    }

    public ItemStack getBackpackItemStack() {
        return this.backpackItemStack;
    }

    public InventoryPlayer getInventoryPlayer() {
        return this.inventoryPlayer;
    }
}

