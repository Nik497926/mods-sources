package ru.obvilion.additionpanels.common.container;

import ic2.core.block.machine.container.ContainerElectricMachine;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;
import ru.obvilion.additionpanels.common.tileentity.TileEntityFruitGenerator;

public class ContainerFruitGenerator extends ContainerElectricMachine<TileEntityFruitGenerator> {
    public ContainerFruitGenerator(EntityPlayer player, TileEntityFruitGenerator tileEntity) {
        super(player, tileEntity, 203, 8, 80);
        int row;
        for(row = 0; row < 4; ++row) {
            this.addSlotToContainer(new SlotInvSlot(tileEntity.upgradeSlot, row, 152, 26 + row * 18));
        }

        for(row = 0; row < 2; ++row) {
            for(int col = 0; col < 6; ++col) {
                this.addSlotToContainer(new SlotInvSlot(tileEntity.inputSlot, col + row * 5, 36 + col * 18, 26 + row * 18));
            }
        }

        for(row = 0; row < 2; ++row) {
            for(int col = 0; col < 6; ++col) {
                this.addSlotToContainer(new SlotInvSlot(tileEntity.outputSlot, col + row * 5, 36 + col * 18, 80 + row * 18));
            }
        }
    }

}
