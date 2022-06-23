/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.simplecore.mod.common.block.BlockBaseFacing;

public abstract class BlockBaseContainer
extends BlockBaseFacing {
    public BlockBaseContainer(Material material, String name) {
        super(material, name);
    }

    public abstract TileEntity createNewTileEntity(World var1, int var2);
}

