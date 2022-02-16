/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package net.divinerpg.utils.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class DivineShapedRecipes
implements IRecipe {
    public final int recipeWidth;
    public final int recipeHeight;
    public final ItemStack[] recipeItems;
    private ItemStack recipeOutput;
    private boolean field_92101_f;

    public DivineShapedRecipes(int par1, int par2, ItemStack[] par3ArrayOfItemStack, ItemStack par4ItemStack) {
        this.recipeWidth = par1;
        this.recipeHeight = par2;
        this.recipeItems = par3ArrayOfItemStack;
        this.recipeOutput = par4ItemStack;
    }

    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World) {
        for (int i = 0; i <= 3 - this.recipeWidth; ++i) {
            for (int j = 0; j <= 5 - this.recipeHeight; ++j) {
                if (this.checkMatch(par1InventoryCrafting, i, j, true)) {
                    return true;
                }
                if (!this.checkMatch(par1InventoryCrafting, i, j, false)) continue;
                return true;
            }
        }
        return false;
    }

    private boolean checkMatch(InventoryCrafting par1InventoryCrafting, int par2, int par3, boolean par4) {
        for (int k = 0; k < 3; ++k) {
            for (int l = 0; l < 3; ++l) {
                ItemStack itemstack1;
                int i1 = k - par2;
                int j1 = l - par3;
                ItemStack itemstack = null;
                if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight) {
                    itemstack = par4 ? this.recipeItems[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth] : this.recipeItems[i1 + j1 * this.recipeWidth];
                }
                if ((itemstack1 = par1InventoryCrafting.getStackInRowAndColumn(k, l)) == null && itemstack == null) continue;
                if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null) {
                    return false;
                }
                if (itemstack.getItem() != itemstack1.getItem()) {
                    return false;
                }
                if (itemstack.getItemDamage() == Short.MAX_VALUE || itemstack.getItemDamage() == itemstack1.getItemDamage()) continue;
                return false;
            }
        }
        return true;
    }

    public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting) {
        ItemStack itemstack = this.getRecipeOutput().copy();
        if (this.field_92101_f) {
            for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); ++i) {
                ItemStack itemstack1 = par1InventoryCrafting.getStackInSlot(i);
                if (itemstack1 == null || !itemstack1.hasTagCompound()) continue;
                itemstack.setTagCompound((NBTTagCompound)itemstack1.stackTagCompound.copy());
            }
        }
        return itemstack;
    }

    public int getRecipeSize() {
        return this.recipeWidth * this.recipeHeight;
    }

    public DivineShapedRecipes func_92100_c() {
        this.field_92101_f = true;
        return this;
    }
}

