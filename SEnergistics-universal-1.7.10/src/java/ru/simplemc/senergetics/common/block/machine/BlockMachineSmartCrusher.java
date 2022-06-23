/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block.machine;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.senergetics.common.block.BlockMachineBaseSmart;
import ru.simplemc.senergetics.common.item.machine.ItemBlockMachineSmartCrusher;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartCrusher;

public class BlockMachineSmartCrusher
extends BlockMachineBaseSmart {
    public BlockMachineSmartCrusher() {
        super("block_smart_crusher", 5, true, ItemBlockMachineSmartCrusher.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntitySmartCrusher();
    }
}

