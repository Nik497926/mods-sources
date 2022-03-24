package mireille.common.items;

import net.minecraft.item.crafting.*;
import net.minecraft.item.*;
import net.minecraft.inventory.*;
import net.minecraft.world.*;
import net.minecraft.nbt.*;

public class RecipedWorkbench implements IRecipe
{
    public int recipeWidth;
    public int recipeHeight;
    public final ItemStack[] recipeItems;
    private ItemStack recipeOutput;
    private boolean field_92101_f;
    
    public RecipedWorkbench(final int w, final int h, final ItemStack[] stacks, final ItemStack result) {
        this.recipeWidth = w;
        this.recipeHeight = h;
        this.recipeItems = stacks;
        this.recipeOutput = result;
    }
    
    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }
    
    public boolean matches(final InventoryCrafting inv, final World world) {
        for (int i = 0; i < this.recipeItems.length; ++i) {
            final ItemStack itemstack1 = this.recipeItems[i];
            final ItemStack itemstack2 = inv.getStackInSlot(i);
            if (itemstack1 != null || itemstack2 != null) {
                if (itemstack1 == null || itemstack2 == null) {
                    return false;
                }
                if (!itemstack1.getItem().equals(itemstack2.getItem())) {
                    return false;
                }
                if (itemstack1.getItemDamage() != 32767 && itemstack1.getItemDamage() != itemstack2.getItemDamage()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public ItemStack getCraftingResult(final InventoryCrafting inv) {
        final ItemStack itemstack = this.getRecipeOutput().copy();
        if (this.field_92101_f) {
            for (int i = 0; i < inv.getSizeInventory(); ++i) {
                final ItemStack itemstack2 = inv.getStackInSlot(i);
                if (itemstack2 != null && itemstack2.hasTagCompound()) {
                    itemstack.setTagCompound((NBTTagCompound)itemstack2.stackTagCompound.copy());
                }
            }
        }
        return itemstack;
    }
    
    public int getRecipeSize() {
        return this.recipeItems.length;
    }
    
    public RecipedWorkbench func_92100_c() {
        this.field_92101_f = true;
        return this;
    }
}
