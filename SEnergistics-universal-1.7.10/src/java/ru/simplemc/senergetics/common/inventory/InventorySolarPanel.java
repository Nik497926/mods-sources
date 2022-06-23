/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory;

import java.util.Optional;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;
import ru.simplemc.senergetics.common.item.electicity.panel.ItemPanelParticleCollector;
import ru.simplemc.senergetics.common.tileentity.electricity.TileEntitySolarPanel;

public class InventorySolarPanel
extends InventoryTileEntity<TileEntitySolarPanel> {
    public InventorySolarPanel(TileEntitySolarPanel tileEntity) {
        super(tileEntity, 10);
    }

    public void setParticleCollector(ItemStack itemStack) {
        this.setInventorySlotContents(9, itemStack);
    }

    public Optional<ItemStack> getParticleCollectorItemStack() {
        ItemStack itemStack = this.getStackInSlot(9);
        if (itemStack != null && itemStack.getItem() instanceof ItemPanelParticleCollector) {
            return Optional.of(itemStack);
        }
        return Optional.empty();
    }
}

