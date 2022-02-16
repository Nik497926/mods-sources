/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.arcana;

import net.divinerpg.blocks.arcana.BlockParasectaAltar;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityDramixAltar;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDramixAltar
extends BlockParasectaAltar {
    public BlockDramixAltar(String name) {
        super(name);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityDramixAltar();
    }
}

