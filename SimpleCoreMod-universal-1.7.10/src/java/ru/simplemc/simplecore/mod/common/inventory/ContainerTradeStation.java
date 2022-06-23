/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.inventory;

import javax.annotation.Nonnull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import ru.simplemc.simplecore.mod.common.inventory.InventoryTileEntityTradeStation;

public class ContainerTradeStation
extends Container {
    protected final InventoryTileEntityTradeStation inventoryTradeStation;
    protected final InventoryPlayer inventoryPlayer;

    public ContainerTradeStation(InventoryTileEntityTradeStation inventory, InventoryPlayer inventoryPlayer) {
        this.inventoryTradeStation = inventory;
        this.inventoryPlayer = inventoryPlayer;
    }

    public boolean canInteractWith(@Nonnull EntityPlayer player) {
        this.inventoryTradeStation.getTradeStation().setAlreadyUsed(true);
        return this.inventoryTradeStation.isUseableByPlayer(player);
    }

    public void onContainerClosed(@Nonnull EntityPlayer player) {
        this.inventoryTradeStation.getTradeStation().setAlreadyUsed(false);
        super.onContainerClosed(player);
    }

    public InventoryTileEntityTradeStation getInventoryTradeStation() {
        return this.inventoryTradeStation;
    }

    public InventoryPlayer getInventoryPlayer() {
        return this.inventoryPlayer;
    }
}

