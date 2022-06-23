/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block.machine;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.senergetics.common.block.BlockMachineBaseSmart;
import ru.simplemc.senergetics.common.item.machine.ItemBlockMachineSmartFurnace;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartFurnace;

public class BlockMachineSmartFurnace
extends BlockMachineBaseSmart {
    public BlockMachineSmartFurnace() {
        super("block_smart_furnace", 3, ItemBlockMachineSmartFurnace.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntitySmartFurnace();
    }
}

