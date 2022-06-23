/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.senergetics.common.inventory.InventorySmartMachine;
import ru.simplemc.senergetics.common.inventory.container.ContainerSmartMachine;
import ru.simplemc.senergetics.common.inventory.container.ContainerSmartMachineMetalFormer;
import ru.simplemc.senergetics.common.tileentity.TileEntityMachine;
import ru.simplemc.senergetics.common.tileentity.component.process.smart.SmartMachineProcess;
import ru.simplemc.senergetics.common.tileentity.component.process.smart.SmartMachineProcessHolder;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartMachine;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartMetalFormer;

public class BlockMachineBaseSmart
extends BlockMachineBase {
    public BlockMachineBaseSmart(String name, int guiId, Class<? extends ItemBlock> itemClass) {
        this(name, guiId, false, itemClass);
    }

    public BlockMachineBaseSmart(String name, int guiId, boolean hasTopTexture, Class<? extends ItemBlock> itemClass) {
        super(name.replace("block_", "block_machine_"), guiId, hasTopTexture, itemClass);
    }

    @Override
    public List<ItemStack> generateBlockDrops(TileEntityMachine<?> tileEntity) {
        List<ItemStack> itemStacksToDrops = super.generateBlockDrops(tileEntity);
        if (tileEntity instanceof TileEntitySmartMachine) {
            SmartMachineProcessHolder processHolder = ((TileEntitySmartMachine)tileEntity).getProcessesHolder();
            for (SmartMachineProcess process : (SmartMachineProcess[])processHolder.getProcesses()) {
                if (process == null) continue;
                itemStacksToDrops.add(process.getProcessOutput().getItemStackInput());
            }
        }
        return itemStacksToDrops;
    }

    protected TileEntitySmartMachine<?> getTileEntityMachineAt(World world, int posX, int posY, int posZ) {
        TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);
        if (tileEntity instanceof TileEntityMachine) {
            return (TileEntitySmartMachine)tileEntity;
        }
        return null;
    }

    public ContainerSmartMachine<?> createContainer(World world, int posX, int posY, int posZ, EntityPlayer player) {
        TileEntityMachine tileEntitySmartMachine = this.getTileEntityMachineAt(world, posX, posY, posZ);
        if (tileEntitySmartMachine != null) {
            ContainerSmartMachine containerSmartMachine = tileEntitySmartMachine instanceof TileEntitySmartMetalFormer ? new ContainerSmartMachineMetalFormer((InventorySmartMachine<?>)((InventorySmartMachine)tileEntitySmartMachine.getInventory()), player) : new ContainerSmartMachine((InventorySmartMachine)tileEntitySmartMachine.getInventory(), player);
            return containerSmartMachine;
        }
        return null;
    }
}

