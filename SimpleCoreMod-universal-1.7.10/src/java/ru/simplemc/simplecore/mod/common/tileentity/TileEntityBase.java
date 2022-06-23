/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class TileEntityBase
extends TileEntity {
    public boolean isPlayerInUseRange(EntityPlayer player) {
        return player.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) < 64.0;
    }

    public boolean isSameTileEntityAtWorld(World world) {
        return world.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this;
    }
}

