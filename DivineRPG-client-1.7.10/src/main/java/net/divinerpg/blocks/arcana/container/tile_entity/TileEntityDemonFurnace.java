/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 */
package net.divinerpg.blocks.arcana.container.tile_entity;

import java.util.Random;
import net.divinerpg.blocks.arcana.BlockDemonFurnace;
import net.divinerpg.blocks.base.tileentity.TileEntityInfiniteFurnace;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class TileEntityDemonFurnace
extends TileEntityInfiniteFurnace {
    private Random rand = new Random();

    public TileEntityDemonFurnace() {
        super("Demon Furnace", 100);
    }

    @Override
    public void addUpdate() {
        BlockDemonFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    @Override
    public void updateEntity() {
        int z;
        int y;
        int x;
        Block b;
        super.updateEntity();
        if (!this.worldObj.isRemote && this.isBurning() && this.rand.nextInt(50) == 0 && (b = this.worldObj.getBlock(x = this.xCoord + this.rand.nextInt(3) - 1, y = this.yCoord + this.rand.nextInt(3) - 1, z = this.zCoord + this.rand.nextInt(3) - 1)) == Blocks.air) {
            this.worldObj.setBlock(x, y, z, (Block)Blocks.fire);
        }
    }
}

