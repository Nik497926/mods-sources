/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.enchantment.EnchantmentData
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryBasic
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeHooks
 */
package net.divinerpg.blocks.vanilla.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class ContainerAltarCorruption
extends Container {
    public IInventory tableInventory = new InventoryBasic("Altar", true, 1){

        public int getInventoryStackLimit() {
            return 1;
        }

        public void markDirty() {
            super.markDirty();
            ContainerAltarCorruption.this.onCraftMatrixChanged((IInventory)this);
        }
    };
    private World worldPointer;
    private int posX;
    private int posY;
    private int posZ;
    private Random rand = new Random();
    public long nameSeed;
    public int[] enchantLevels = new int[3];

    public ContainerAltarCorruption(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5) {
        int l;
        this.worldPointer = par2World;
        this.posX = par3;
        this.posY = par4;
        this.posZ = par5;
        this.addSlotToContainer(new Slot(this.tableInventory, 0, 25, 47){

            public boolean isItemValid(ItemStack par1ItemStack) {
                return true;
            }
        });
        for (l = 0; l < 3; ++l) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlotToContainer(new Slot((IInventory)par1InventoryPlayer, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }
        for (l = 0; l < 9; ++l) {
            this.addSlotToContainer(new Slot((IInventory)par1InventoryPlayer, l, 8 + l * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate((Container)this, 0, this.enchantLevels[0]);
        par1ICrafting.sendProgressBarUpdate((Container)this, 1, this.enchantLevels[1]);
        par1ICrafting.sendProgressBarUpdate((Container)this, 2, this.enchantLevels[2]);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);
            icrafting.sendProgressBarUpdate((Container)this, 0, this.enchantLevels[0]);
            icrafting.sendProgressBarUpdate((Container)this, 1, this.enchantLevels[1]);
            icrafting.sendProgressBarUpdate((Container)this, 2, this.enchantLevels[2]);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 >= 0 && par1 <= 2) {
            this.enchantLevels[par1] = par2;
        } else {
            super.updateProgressBar(par1, par2);
        }
    }

    public void onCraftMatrixChanged(IInventory par1IInventory) {
        if (par1IInventory == this.tableInventory) {
            ItemStack itemstack = par1IInventory.getStackInSlot(0);
            if (itemstack != null && itemstack.isItemEnchantable()) {
                this.nameSeed = this.rand.nextLong();
                if (!this.worldPointer.isRemote) {
                    int j;
                    boolean i = false;
                    float power = 0.0f;
                    for (j = -1; j <= 1; ++j) {
                        for (int k = -1; k <= 1; ++k) {
                            if (j == 0 && k == 0) continue;
                            power += 2.0f;
                            if (k == 0 || j == 0) continue;
                            power += ForgeHooks.getEnchantPower((World)this.worldPointer, (int)(this.posX + k * 2), (int)this.posY, (int)(this.posZ + j));
                            power += ForgeHooks.getEnchantPower((World)this.worldPointer, (int)(this.posX + k * 2), (int)(this.posY + 1), (int)(this.posZ + j));
                            power += ForgeHooks.getEnchantPower((World)this.worldPointer, (int)(this.posX + k), (int)this.posY, (int)(this.posZ + j * 2));
                            power += ForgeHooks.getEnchantPower((World)this.worldPointer, (int)(this.posX + k), (int)(this.posY + 1), (int)(this.posZ + j * 2));
                        }
                    }
                    for (j = 0; j < 3; ++j) {
                        this.enchantLevels[j] = EnchantmentHelper.calcItemStackEnchantability((Random)this.rand, (int)j, (int)((int)power), (ItemStack)itemstack);
                    }
                    this.detectAndSendChanges();
                }
            } else {
                for (int i = 0; i < 3; ++i) {
                    this.enchantLevels[i] = 0;
                }
            }
        }
    }

    public boolean enchantItem(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = this.tableInventory.getStackInSlot(0);
        if (this.enchantLevels[par2] > 0 && itemstack != null && (par1EntityPlayer.experienceLevel >= this.enchantLevels[par2] || par1EntityPlayer.capabilities.isCreativeMode)) {
            if (!this.worldPointer.isRemote) {
                boolean flag;
                List list = EnchantmentHelper.buildEnchantmentList((Random)this.rand, (ItemStack)itemstack, (int)this.enchantLevels[par2]);
                boolean bl = flag = itemstack.getItem() == Items.book;
                if (list != null) {
                    par1EntityPlayer.addExperienceLevel(-this.enchantLevels[par2]);
                    if (flag) {
                        itemstack.func_150996_a((Item)Items.enchanted_book);
                    }
                    int j = flag && list.size() > 1 ? this.rand.nextInt(list.size()) : -1;
                    for (int k = 0; k < list.size(); ++k) {
                        EnchantmentData enchantmentdata = (EnchantmentData)list.get(k);
                        if (flag && k == j) continue;
                        if (flag) {
                            Items.enchanted_book.addEnchantment(itemstack, enchantmentdata);
                            continue;
                        }
                        itemstack.addEnchantment(enchantmentdata.enchantmentobj, enchantmentdata.enchantmentLevel);
                    }
                    this.onCraftMatrixChanged(this.tableInventory);
                }
            }
            return true;
        }
        return false;
    }

    public void onContainerClosed(EntityPlayer par1EntityPlayer) {
        ItemStack itemstack;
        super.onContainerClosed(par1EntityPlayer);
        if (!this.worldPointer.isRemote && (itemstack = this.tableInventory.getStackInSlotOnClosing(0)) != null) {
            par1EntityPlayer.dropPlayerItemWithRandomChoice(itemstack, false);
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.worldPointer.getBlock(this.posX, this.posY, this.posZ) != VanillaBlocks.altarOfCorruption ? false : par1EntityPlayer.getDistanceSq((double)this.posX + 0.5, (double)this.posY + 0.5, (double)this.posZ + 0.5) <= 64.0;
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (par2 == 0) {
                if (!this.mergeItemStack(itemstack1, 1, 37, true)) {
                    return null;
                }
            } else {
                if (((Slot)this.inventorySlots.get(0)).getHasStack() || !((Slot)this.inventorySlots.get(0)).isItemValid(itemstack1)) {
                    return null;
                }
                if (itemstack1.hasTagCompound() && itemstack1.stackSize == 1) {
                    ((Slot)this.inventorySlots.get(0)).putStack(itemstack1.copy());
                    itemstack1.stackSize = 0;
                } else if (itemstack1.stackSize >= 1) {
                    ((Slot)this.inventorySlots.get(0)).putStack(new ItemStack(itemstack1.getItem(), 1, itemstack1.getItemDamage()));
                    --itemstack1.stackSize;
                }
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }
        return itemstack;
    }
}

