/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block.machine;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.senergetics.common.block.BlockMachineBaseSmart;
import ru.simplemc.senergetics.common.item.machine.ItemBlockMachineSmartExtractor;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartExtractor;

public class BlockMachineSmartExtractor
extends BlockMachineBaseSmart {
    public BlockMachineSmartExtractor() {
        super("block_smart_extractor", 4, ItemBlockMachineSmartExtractor.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntitySmartExtractor();
    }
}

