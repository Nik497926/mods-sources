/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block.machine;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.senergetics.common.block.BlockMachineBaseSmart;
import ru.simplemc.senergetics.common.item.machine.ItemBlockMachineSmartRecycler;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartMetalFormer;

public class BlockMachineSmartMetalFormer
extends BlockMachineBaseSmart {
    public BlockMachineSmartMetalFormer() {
        super("block_smart_metal_former", 8, true, ItemBlockMachineSmartRecycler.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntitySmartMetalFormer();
    }
}

