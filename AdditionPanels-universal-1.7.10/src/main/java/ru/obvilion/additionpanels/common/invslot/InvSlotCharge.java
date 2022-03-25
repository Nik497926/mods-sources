package ru.obvilion.additionpanels.common.invslot;

import ic2.api.item.ElectricItem;
import ic2.core.block.TileEntityInventory;
import net.minecraft.item.ItemStack;

public class InvSlotCharge extends ic2.core.block.invslot.InvSlotCharge {
    public boolean ignoreTransferLimit;

    public InvSlotCharge(TileEntityInventory base1, int oldStartIndex1, int tier1,boolean ignoreTransferLimit) {
        super(base1, oldStartIndex1, tier1);
        this.ignoreTransferLimit = ignoreTransferLimit;
    }


    public double charge(double amount) {
        if (amount <= 0.0D) {
            throw new IllegalArgumentException("Amount must be > 0.");
        } else {
            ItemStack itemStack = this.get(0);
            return itemStack == null ? 0.0D : ElectricItem.manager.charge(itemStack, amount, this.tier, ignoreTransferLimit, false);
        }
    }

}
