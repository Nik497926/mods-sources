/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block.machine;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.senergetics.common.block.BlockMachineBaseSmart;
import ru.simplemc.senergetics.common.item.machine.ItemBlockMachineSmartCompressor;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartCompressor;

public class BlockMachineSmartCompressor
extends BlockMachineBaseSmart {
    public BlockMachineSmartCompressor() {
        super("block_smart_compressor", 6, ItemBlockMachineSmartCompressor.class);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntitySmartCompressor();
    }
}

