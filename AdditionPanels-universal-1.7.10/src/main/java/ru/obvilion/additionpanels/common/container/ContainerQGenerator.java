package ru.obvilion.additionpanels.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import ru.justagod.cutter.GradleSide;
import ru.justagod.cutter.GradleSideOnly;
import ru.obvilion.additionpanels.common.tileentity.TileEntityQGenerator;

public class ContainerQGenerator extends Container {
    private TileEntityQGenerator tileentity;

    public ContainerQGenerator(InventoryPlayer inventoryplayer, TileEntityQGenerator tileentityqgenerator) {
        this.tileentity = tileentityqgenerator;

        int j;
        for(j = 0; j < 3; ++j) {
            for(int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(inventoryplayer, k + j * 9 + 9, 8 + k * 18, 67 + j * 18));
            }
        }

        for(j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(inventoryplayer, j, 8 + j * 18, 125));
        }

    }

    @GradleSideOnly(GradleSide.SERVER)
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (Object crafter : this.crafters) {
            ICrafting icrafting = (ICrafting) crafter;
            icrafting.sendProgressBarUpdate(this, 0, this.tileentity.production);
            icrafting.sendProgressBarUpdate(this, 1, this.tileentity.maxPacketSize);
            icrafting.sendProgressBarUpdate(this, 2, this.tileentity.active ? 1 : 0);
        }

    }

    @GradleSideOnly(GradleSide.CLIENT)
    public void updateProgressBar(int i, int j) {
        if (i == 0) {
            this.tileentity.production = j;
        }

        if (i == 1) {
            this.tileentity.maxPacketSize = j;
        }

        if (i == 2) {
            this.tileentity.active = j != 0;
        }

    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.tileentity.isUseableByPlayer(entityplayer);
    }
}
