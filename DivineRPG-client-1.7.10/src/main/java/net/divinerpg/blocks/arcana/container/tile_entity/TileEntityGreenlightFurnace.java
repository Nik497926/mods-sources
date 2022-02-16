/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.blocks.arcana.container.tile_entity;

import net.divinerpg.blocks.arcana.BlockGreenlightFurnace;
import net.divinerpg.blocks.base.tileentity.TileEntityModFurnace;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.item.ItemStack;

public class TileEntityGreenlightFurnace
extends TileEntityModFurnace {
    public TileEntityGreenlightFurnace() {
        super("Greenlight Furnace", 140);
    }

    @Override
    public void updateBlock() {
        BlockGreenlightFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    @Override
    public int getItemBurnTime(ItemStack stack) {
        if (stack == null || stack.getItem() != ArcanaItems.firestock) {
            return 0;
        }
        return 1600;
    }
}

