package mireille.common.container;

import net.minecraft.world.*;
import net.minecraft.inventory.*;
import mireille.common.items.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import mireille.core.*;

public class ContainerWorkbench extends Container
{
    private int recipeWidth;
    private int recipeHeight;
    public InventoryCrafting craftMatrix;
    public IInventory craftResult;
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;
    
    public ContainerWorkbench(final InventoryPlayer inv, final World world, final int x, final int y, final int z) {
        this.recipeWidth = 5;
        this.recipeHeight = 5;
        this.craftMatrix = new InventoryCrafting((Container)this, this.recipeWidth, this.recipeHeight);
        this.craftResult = (IInventory)new InventoryCraftResult();
        this.worldObj = world;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.addSlotToContainer((Slot)new SlotCrafting(inv.player, (IInventory)this.craftMatrix, this.craftResult, 0, 140, 45));
        for (int l = 0; l < this.recipeHeight; ++l) {
            for (int i1 = 0; i1 < this.recipeWidth; ++i1) {
                this.addSlotToContainer(new Slot((IInventory)this.craftMatrix, i1 + l * 5, 8 + i1 * 18, 8 + l * 18));
            }
        }
        for (int l = 0; l < 3; ++l) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlotToContainer(new Slot((IInventory)inv, i1 + l * 9 + 9, 8 + i1 * 18, 102 + l * 18));
            }
        }
        for (int l = 0; l < 9; ++l) {
            this.addSlotToContainer(new Slot((IInventory)inv, l, 8 + l * 18, 160));
        }
        this.onCraftMatrixChanged((IInventory)this.craftMatrix);
    }
    
    public void onCraftMatrixChanged(final IInventory inv) {
        this.craftResult.setInventorySlotContents(0, CraftingManager.findMatchingRecipe(this.craftMatrix, this.worldObj));
    }
    
    public void onContainerClosed(final EntityPlayer player) {
        super.onContainerClosed(player);
        if (!this.worldObj.isRemote) {
            for (int i = 0; i < this.recipeWidth * this.recipeHeight; ++i) {
                final ItemStack itemstack = this.craftMatrix.getStackInSlotOnClosing(i);
                if (itemstack != null) {
                    player.dropPlayerItemWithRandomChoice(itemstack, false);
                }
            }
        }
    }
    
    public boolean canInteractWith(EntityPlayer player) {
        return this.worldObj.getBlock(this.posX, this.posY, this.posZ) == ModBlocks.Workbench && player.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
     }
    
    public ItemStack transferStackInSlot(final EntityPlayer player, final int slotID) {
        ItemStack itemstack = null;
        Slot slot = (Slot)super.inventorySlots.get(slotID);
        if (slot != null && slot.getHasStack()) {
            final ItemStack itemstack2 = slot.getStack();
            itemstack = itemstack2.copy();
            if (slotID == 0) {
                if (!this.mergeItemStack(itemstack2, 21, 57, true)) {
                    return null;
                }
                slot.onSlotChange(itemstack2, itemstack);
            }
            else if (slotID >= 26 && slotID < 53) {
                if (!this.mergeItemStack(itemstack2, 53, 62, false)) {
                    return null;
                }
            }
            else if (slotID >= 53 && slotID < 62) {
                if (!this.mergeItemStack(itemstack2, 26, 53, false)) {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack2, 26, 62, false)) {
                return null;
            }
            if (itemstack2.stackSize == 0) {
                slot.putStack((ItemStack)null);
            }
            else {
                slot.onSlotChanged();
            }
            if (itemstack2.stackSize == itemstack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(player, itemstack2);
        }
        return itemstack;
    }
    
    public boolean func_94530_a(final ItemStack stack, final Slot slotID) {
        return slotID.inventory != this.craftResult && super.func_94530_a(stack, slotID);
    }
}
