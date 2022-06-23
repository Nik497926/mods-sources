/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block.machine;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.senergetics.common.block.BlockMachineBase;
import ru.simplemc.senergetics.common.inventory.container.ContainerMolecularCollector;
import ru.simplemc.senergetics.common.item.machine.ItemBlockMolecularCollector;
import ru.simplemc.senergetics.common.tileentity.TileEntityMachine;
import ru.simplemc.senergetics.common.tileentity.component.process.molecular.MolecularProcess;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntityMolecularCollector;

public class BlockMolecularCollector
extends BlockMachineBase {
    public BlockMolecularCollector() {
        super("block_molecular_collector", 11, ItemBlockMolecularCollector.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityMolecularCollector();
    }

    @Override
    public List<ItemStack> generateBlockDrops(TileEntityMachine<?> tileEntity) {
        List<ItemStack> itemStacksToDrops = super.generateBlockDrops(tileEntity);
        if (tileEntity instanceof TileEntityMolecularCollector) {
            MolecularProcess[] processes;
            for (MolecularProcess process : processes = (MolecularProcess[])((TileEntityMolecularCollector)tileEntity).getProcessHolder().getProcesses()) {
                if (process == null) continue;
                itemStacksToDrops.add(process.getOutput().getItemStackInput());
            }
        }
        return itemStacksToDrops;
    }

    protected TileEntityMolecularCollector getTileEntityMachineAt(World world, int posX, int posY, int posZ) {
        TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);
        if (tileEntity instanceof TileEntityMolecularCollector) {
            return (TileEntityMolecularCollector)tileEntity;
        }
        return null;
    }

    public ContainerMolecularCollector createContainer(World world, int posX, int posY, int posZ, EntityPlayer player) {
        TileEntityMolecularCollector tileEntityMolecularCollector = this.getTileEntityMachineAt(world, posX, posY, posZ);
        if (tileEntityMolecularCollector != null) {
            return new ContainerMolecularCollector(tileEntityMolecularCollector.getInventory(), player);
        }
        return null;
    }
}

