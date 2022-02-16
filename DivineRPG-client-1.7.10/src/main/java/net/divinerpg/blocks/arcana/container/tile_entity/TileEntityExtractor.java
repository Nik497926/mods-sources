/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.blocks.arcana.container.tile_entity;

import net.divinerpg.blocks.base.tileentity.TileEntityModFurnace;
import net.divinerpg.utils.items.ArcanaItems;
import net.divinerpg.utils.recipes.ExtractorRecipes;
import net.minecraft.item.ItemStack;

public class TileEntityExtractor
extends TileEntityModFurnace {
    public TileEntityExtractor() {
        super("Extractor", 100);
    }

    @Override
    public void updateEntity() {
        boolean flag = this.furnaceBurnTime > 0;
        boolean flag1 = false;
        if (this.furnaceBurnTime > 0) {
            --this.furnaceBurnTime;
        }
        if (!this.worldObj.isRemote) {
            if (this.furnaceBurnTime == 0 && this.canSmelt()) {
                this.currentItemBurnTime = this.furnaceBurnTime = this.getItemBurnTime(this.furnaceItemStacks[1]);
                if (this.furnaceBurnTime > 0) {
                    flag1 = true;
                    if (this.furnaceItemStacks[1] != null) {
                        --this.furnaceItemStacks[1].stackSize;
                        if (this.furnaceItemStacks[1].stackSize == 0) {
                            this.furnaceItemStacks[1] = this.furnaceItemStacks[1].getItem().getContainerItem(this.furnaceItemStacks[1]);
                        }
                    }
                }
            }
            if (this.isBurning() && this.canSmelt()) {
                ++this.furnaceCookTime;
                if (this.furnaceCookTime == this.speed) {
                    this.furnaceCookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            } else {
                this.furnaceCookTime = 0;
            }
            if (flag != this.furnaceBurnTime > 0) {
                flag1 = true;
            }
        }
        if (flag1) {
            this.markDirty();
        }
    }

    private boolean canSmelt() {
        if (this.furnaceItemStacks[0] == null) {
            return false;
        }
        ItemStack itemstack = ExtractorRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
        if (itemstack == null) {
            return false;
        }
        if (this.furnaceItemStacks[2] == null) {
            return true;
        }
        if (!this.furnaceItemStacks[2].isItemEqual(itemstack)) {
            return false;
        }
        int result = this.furnaceItemStacks[2].stackSize + itemstack.stackSize;
        return result <= this.getInventoryStackLimit() && result <= this.furnaceItemStacks[2].getMaxStackSize();
    }

    @Override
    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = ExtractorRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
            if (this.furnaceItemStacks[2] == null) {
                this.furnaceItemStacks[2] = itemstack.copy();
            } else if (this.furnaceItemStacks[2].getItem() == itemstack.getItem()) {
                this.furnaceItemStacks[2].stackSize += itemstack.stackSize;
            }
            --this.furnaceItemStacks[0].stackSize;
            if (this.furnaceItemStacks[0].stackSize <= 0) {
                this.furnaceItemStacks[0] = null;
            }
        }
    }

    @Override
    public int getItemBurnTime(ItemStack stack) {
        if (stack == null || stack.getItem() != ArcanaItems.chargedCollector) {
            return 0;
        }
        return 400;
    }
}

