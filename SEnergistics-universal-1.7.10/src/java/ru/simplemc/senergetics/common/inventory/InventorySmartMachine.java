/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory;

import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartMachine;

public class InventorySmartMachine<T extends TileEntitySmartMachine<T>>
extends InventoryTileEntity<T> {
    public InventorySmartMachine(TileEntitySmartMachine<T> tileEntitySmartMachine) {
        super((T) tileEntitySmartMachine, 20);
    }
}

