/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.machine;

import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;
import ru.simplemc.senergetics.common.tileentity.TileEntityMachine;

public class TileEntitySpawnerNew
extends TileEntityMachine<TileEntitySpawnerNew> {
    @Override
    protected InventoryTileEntity<TileEntitySpawnerNew> createInventory() {
        return new InventoryTileEntity<TileEntitySpawnerNew>(this, 46);
    }

    public int[] getAccessibleSlotsFromSide(int i) {
        return new int[0];
    }

    public boolean canInsertItem(int i, ItemStack itemStack, int i1) {
        return false;
    }

    public boolean canExtractItem(int i, ItemStack itemStack, int i1) {
        return false;
    }

    @Override
    protected void processCommonTick() {
    }

    @Override
    protected void processClientTick() {
    }
}

