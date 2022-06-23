/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.slot;

import ic2.core.upgrade.IUpgradeItem;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;
import ru.simplemc.senergetics.common.tileentity.TileEntityMachine;

public class SlotMachineUpgrade
extends Slot {
    private final TileEntityMachine<?> machine;

    public SlotMachineUpgrade(InventoryTileEntity<? extends TileEntityMachine> inventory, int index, int posX, int posY) {
        super(inventory, index, posX, posY);
        this.machine = inventory.getTileEntity();
    }

    public boolean isItemValid(ItemStack itemStack) {
        if (itemStack != null && itemStack.getItem() instanceof IUpgradeItem) {
            return ((IUpgradeItem)itemStack.getItem()).isSuitableFor(itemStack, this.machine.getUpgradableProperties());
        }
        return false;
    }

    public int getSlotStackLimit() {
        return 64;
    }
}

