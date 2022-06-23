/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import ru.simplemc.senergetics.common.inventory.InventorySmartMachine;
import ru.simplemc.senergetics.common.inventory.container.ContainerSmartMachine;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartMetalFormer;

public class ContainerSmartMachineMetalFormer
extends ContainerSmartMachine<TileEntitySmartMetalFormer> {
    private int mode = -1;

    public ContainerSmartMachineMetalFormer(InventorySmartMachine<?> inventory, EntityPlayer player) {
        super((InventorySmartMachine<TileEntitySmartMetalFormer>) inventory, player);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        int mode = ((TileEntitySmartMetalFormer)((Object)this.getInventoryTileEntity().getTileEntity())).getMode();
        for (Object o : this.crafters) {
            ICrafting crafter = (ICrafting)o;
            crafter.sendProgressBarUpdate((Container)this, 8, mode);
        }
    }

    @Override
    public void updateProgressBar(int index, int value) {
        super.updateProgressBar(index, value);
        if (index == 8) {
            this.mode = value;
        }
    }

    public int getMode() {
        return this.mode;
    }
}

