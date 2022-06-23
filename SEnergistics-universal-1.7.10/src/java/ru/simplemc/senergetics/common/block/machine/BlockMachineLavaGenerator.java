/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.senergetics.common.block.BlockMachineBase;
import ru.simplemc.senergetics.common.inventory.container.ContainerLavaGenerator;
import ru.simplemc.senergetics.common.item.machine.ItemBlockMachineLavaGenerator;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntityLavaGenerator;

public class BlockMachineLavaGenerator
extends BlockMachineBase {
    public BlockMachineLavaGenerator() {
        super("block_machine_lava_generator", 10, ItemBlockMachineLavaGenerator.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityLavaGenerator();
    }

    protected TileEntityLavaGenerator getTileEntityMachineAt(World world, int posX, int posY, int posZ) {
        TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);
        if (tileEntity instanceof TileEntityLavaGenerator) {
            return (TileEntityLavaGenerator)tileEntity;
        }
        return null;
    }

    public ContainerLavaGenerator createContainer(World world, int posX, int posY, int posZ, EntityPlayer player) {
        TileEntityLavaGenerator tileEntityLavaGenerator = this.getTileEntityMachineAt(world, posX, posY, posZ);
        if (tileEntityLavaGenerator != null) {
            return new ContainerLavaGenerator(tileEntityLavaGenerator.getInventory(), player);
        }
        return null;
    }
}

