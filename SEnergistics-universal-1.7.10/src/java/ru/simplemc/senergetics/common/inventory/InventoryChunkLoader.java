/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory;

import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntityChunkLoader;

public class InventoryChunkLoader
extends InventoryTileEntity<TileEntityChunkLoader> {
    public InventoryChunkLoader(TileEntityChunkLoader tileEntity) {
        super(tileEntity, 1);
    }

    public ItemStack getTicketItemStack() {
        return this.getStackInSlot(0);
    }
}

