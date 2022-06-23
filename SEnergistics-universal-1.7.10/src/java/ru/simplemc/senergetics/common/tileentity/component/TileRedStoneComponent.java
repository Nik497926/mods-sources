/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.component;

import net.minecraft.tileentity.TileEntity;

public class TileRedStoneComponent {
    private final TileEntity tile;
    private int tickCounter = 0;
    private int powerLevel = 0;

    public TileRedStoneComponent(TileEntity tile) {
        this.tile = tile;
    }

    public void updateEntity() {
        if (++this.tickCounter % 4 == 0) {
            this.powerLevel = this.tile.getWorldObj().getStrongestIndirectPower(this.tile.xCoord, this.tile.yCoord, this.tile.zCoord);
        }
    }

    public boolean isPowered() {
        return this.powerLevel > 0;
    }

    public int getPowerLevel() {
        return this.powerLevel;
    }
}

