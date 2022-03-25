package ru.obvilion.additionpanels.common.container;

import ic2.core.ContainerFullInv;
import ic2.core.block.invslot.InvSlotArmor;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;
import ru.obvilion.additionpanels.common.tileentity.TileEntityAdvancedMFSU;

import java.util.List;

public class ContainerAdvancedMFSU extends ContainerFullInv<TileEntityAdvancedMFSU> {
    public ContainerAdvancedMFSU(EntityPlayer entityPlayer, TileEntityAdvancedMFSU tileEntity1) {
        super(entityPlayer, tileEntity1, 196);

        for(int col = 0; col < 4; ++col) {
            this.addSlotToContainer(new InvSlotArmor(entityPlayer.inventory, col, 8 + col * 18, 84));
        }

        this.addSlotToContainer(new SlotInvSlot(tileEntity1.chargeSlot, 0, 56, 17));
        this.addSlotToContainer(new SlotInvSlot(tileEntity1.dischargeSlot, 0, 56, 53));
    }

    public List<String> getNetworkedFields() {
        List<String> ret = super.getNetworkedFields();
        ret.add("energy");
        ret.add("redstoneMode");
        ret.add("chargeSlot");
        ret.add("dischargeSlot");
        return ret;
    }
}
