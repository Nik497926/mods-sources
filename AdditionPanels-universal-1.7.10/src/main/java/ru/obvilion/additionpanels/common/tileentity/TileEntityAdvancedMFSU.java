package ru.obvilion.additionpanels.common.tileentity;

import ic2.core.block.invslot.InvSlotCharge;

public class TileEntityAdvancedMFSU extends ic2.core.block.wiring.TileEntityElectricBlock {

    public final InvSlotCharge chargeSlot;


    public TileEntityAdvancedMFSU() {

        super(2147483647, 500000, 1000000000);
        this.chargeSlot = new ru.obvilion.additionpanels.common.invslot.InvSlotCharge(this, 0, this.getSourceTier(), true);
    }


    public String getInventoryName() {
        return "tile.advancedmfsu.name";
    }


}

