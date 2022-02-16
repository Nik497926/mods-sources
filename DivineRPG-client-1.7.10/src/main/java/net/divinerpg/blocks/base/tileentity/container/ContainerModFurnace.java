/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.inventory.SlotFurnace
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.FurnaceRecipes
 */
package net.divinerpg.blocks.base.tileentity.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.blocks.base.tileentity.TileEntityModFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerModFurnace
extends Container {
    private TileEntityModFurnace tileFurnace;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerModFurnace(InventoryPlayer player, TileEntityModFurnace furnace) {
        int i;
        this.tileFurnace = furnace;
        this.addSlotToContainer(new Slot((IInventory)furnace, 0, 56, 17));
        this.addSlotToContainer(new Slot((IInventory)furnace, 1, 56, 53));
        this.addSlotToContainer((Slot)new SlotFurnace(player.player, (IInventory)furnace, 2, 116, 35));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)player, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate((Container)this, 0, this.tileFurnace.furnaceCookTime);
        par1ICrafting.sendProgressBarUpdate((Container)this, 1, this.tileFurnace.furnaceBurnTime);
        par1ICrafting.sendProgressBarUpdate((Container)this, 2, this.tileFurnace.currentItemBurnTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);
            if (this.lastCookTime != this.tileFurnace.furnaceCookTime) {
                icrafting.sendProgressBarUpdate((Container)this, 0, this.tileFurnace.furnaceCookTime);
            }
            if (this.lastBurnTime != this.tileFurnace.furnaceBurnTime) {
                icrafting.sendProgressBarUpdate((Container)this, 1, this.tileFurnace.furnaceBurnTime);
            }
            if (this.lastItemBurnTime == this.tileFurnace.currentItemBurnTime) continue;
            icrafting.sendProgressBarUpdate((Container)this, 2, this.tileFurnace.currentItemBurnTime);
        }
        this.lastCookTime = this.tileFurnace.furnaceCookTime;
        this.lastBurnTime = this.tileFurnace.furnaceBurnTime;
        this.lastItemBurnTime = this.tileFurnace.currentItemBurnTime;
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.tileFurnace.furnaceCookTime = par2;
        }
        if (par1 == 1) {
            this.tileFurnace.furnaceBurnTime = par2;
        }
        if (par1 == 2) {
            this.tileFurnace.currentItemBurnTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.tileFurnace.isUseableByPlayer(par1EntityPlayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotNumber);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (slotNumber == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (slotNumber != 1 && slotNumber != 0 ? (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null ? !this.mergeItemStack(itemstack1, 0, 1, false) : (this.tileFurnace.isItemFuel(itemstack1) ? !this.mergeItemStack(itemstack1, 1, 2, false) : (slotNumber >= 3 && slotNumber < 30 ? !this.mergeItemStack(itemstack1, 30, 39, false) : slotNumber >= 30 && slotNumber < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)))) : !this.mergeItemStack(itemstack1, 3, 39, false)) {
                return null;
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(player, itemstack1);
        }
        return itemstack;
    }
}

