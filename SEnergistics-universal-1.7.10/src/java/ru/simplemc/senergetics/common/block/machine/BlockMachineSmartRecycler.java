/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block.machine;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.senergetics.common.block.BlockMachineBaseSmart;
import ru.simplemc.senergetics.common.item.machine.ItemBlockMachineSmartRecycler;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartRecycler;

public class BlockMachineSmartRecycler
extends BlockMachineBaseSmart {
    public BlockMachineSmartRecycler() {
        super("block_smart_recycler", 7, ItemBlockMachineSmartRecycler.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntitySmartRecycler();
    }
}

