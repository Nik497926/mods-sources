/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class InventoryUtils {
    private static final int[] emptySlotArray = new int[0];

    public static int putInInventory(IInventory inv, int side, ItemStack itemStackSource, boolean simulate) {
        int transfer;
        ItemStack itemStack;
        int[] slots;
        if (itemStackSource == null) {
            return 0;
        }
        int toTransfer = itemStackSource.stackSize;
        for (int i : slots = InventoryUtils.getInventorySlots(inv, side, true, false)) {
            if (toTransfer <= 0) break;
            if (!inv.isItemValidForSlot(i, itemStackSource) || inv instanceof ISidedInventory && !((ISidedInventory)inv).canInsertItem(i, itemStackSource, side) || (itemStack = inv.getStackInSlot(i)) == null || !InventoryUtils.isStackEqualStrict(itemStack, itemStackSource)) continue;
            transfer = Math.min(toTransfer, Math.min(inv.getInventoryStackLimit(), itemStack.getMaxStackSize()) - itemStack.stackSize);
            if (!simulate) {
                itemStack.stackSize += transfer;
            }
            toTransfer -= transfer;
        }
        for (int i : slots) {
            if (toTransfer <= 0) break;
            if (!inv.isItemValidForSlot(i, itemStackSource) || inv instanceof ISidedInventory && !((ISidedInventory)inv).canInsertItem(i, itemStackSource, side) || (itemStack = inv.getStackInSlot(i)) != null) continue;
            transfer = Math.min(toTransfer, Math.min(inv.getInventoryStackLimit(), itemStackSource.getMaxStackSize()));
            if (!simulate) {
                ItemStack dest = InventoryUtils.copyWithSize(itemStackSource, transfer);
                inv.setInventorySlotContents(i, dest);
            }
            toTransfer -= transfer;
        }
        if (!simulate && toTransfer != itemStackSource.stackSize) {
            inv.markDirty();
        }
        return itemStackSource.stackSize - toTransfer;
    }

    public static boolean areItemStacksEqual(ItemStack stack0, ItemStack stack1, boolean useOre, boolean ignoreDamage, boolean ignoreNBT) {
        boolean t2;
        if (stack0 == null && stack1 != null) {
            return false;
        }
        if (stack0 != null && stack1 == null) {
            return false;
        }
        if (stack0 == null && stack1 == null) {
            return true;
        }
        if (stack0.getItem() == null || stack1.getItem() == null) {
            return false;
        }
        boolean t1 = true;
        if (!ignoreNBT) {
            t1 = ItemStack.areItemStackTagsEqual((ItemStack)stack0, (ItemStack)stack1);
        }
        boolean bl = t2 = stack0.getItemDamage() != stack1.getItemDamage();
        if (ignoreDamage && stack0.isItemStackDamageable() && stack1.isItemStackDamageable()) {
            t2 = false;
        }
        if (t2 && ignoreDamage && (stack0.getItemDamage() == Short.MAX_VALUE || stack1.getItemDamage() == Short.MAX_VALUE)) {
            t2 = false;
        }
        return stack0.getItem() == stack1.getItem() && !t2 && t1;
    }

    public static TileEntityChest getDoubleChest(TileEntity tile) {
        if (tile instanceof TileEntityChest) {
            if (((TileEntityChest)tile).adjacentChestXNeg != null) {
                return ((TileEntityChest)tile).adjacentChestXNeg;
            }
            if (((TileEntityChest)tile).adjacentChestXPos != null) {
                return ((TileEntityChest)tile).adjacentChestXPos;
            }
            if (((TileEntityChest)tile).adjacentChestZNeg != null) {
                return ((TileEntityChest)tile).adjacentChestZNeg;
            }
            if (((TileEntityChest)tile).adjacentChestZPos != null) {
                return ((TileEntityChest)tile).adjacentChestZPos;
            }
        }
        return null;
    }

    public static int[] getInventorySlots(IInventory inv, int side, boolean checkInsert, boolean checkExtract) {
        int[] ret;
        ISidedInventory sidedInv;
        int inventoryStackLimit = inv.getInventoryStackLimit();
        if (inventoryStackLimit <= 0) {
            return emptySlotArray;
        }
        if (inv instanceof ISidedInventory) {
            sidedInv = (ISidedInventory)inv;
            ret = sidedInv.getAccessibleSlotsFromSide(side);
            if (ret == null) {
                return emptySlotArray;
            }
            if (ret.length == 0) {
                return emptySlotArray;
            }
            ret = Arrays.copyOf(ret, ret.length);
        } else {
            int size = inv.getSizeInventory();
            if (size <= 0) {
                return emptySlotArray;
            }
            sidedInv = null;
            ret = new int[size];
            int i = 0;
            while (i < ret.length) {
                ret[i] = i++;
            }
        }
        if (checkInsert || checkExtract) {
            int writeIdx = 0;
            for (int readIdx = 0; readIdx < ret.length; ++readIdx) {
                int slot = ret[readIdx];
                ItemStack stack = inv.getStackInSlot(slot);
                if (checkExtract && (stack == null || stack.stackSize <= 0 || sidedInv != null && !sidedInv.canExtractItem(slot, stack, side)) || checkInsert && stack != null && (stack.stackSize >= stack.getMaxStackSize() || stack.stackSize >= inventoryStackLimit || sidedInv != null && !sidedInv.canInsertItem(slot, stack, side))) continue;
                ret[writeIdx] = slot;
                ++writeIdx;
            }
            if (writeIdx != ret.length) {
                ret = Arrays.copyOf(ret, writeIdx);
            }
        }
        return ret;
    }

    public static ItemStack copyWithSize(ItemStack itemStack, int newSize) {
        ItemStack ret = itemStack.copy();
        ret.stackSize = newSize;
        return ret;
    }

    public static ItemStack getFromInventory(IInventory inv, int side, ItemStack itemStackDestination, int max, boolean ignoreMaxStackSize, boolean simulate) {
        if (itemStackDestination != null && !ignoreMaxStackSize) {
            max = Math.min(max, itemStackDestination.getMaxStackSize() - itemStackDestination.stackSize);
        }
        ItemStack ret = null;
        for (int i : InventoryUtils.getInventorySlots(inv, side, false, true)) {
            if (max <= 0) break;
            ItemStack stack = inv.getStackInSlot(i);
            assert (stack != null);
            if (itemStackDestination != null && !InventoryUtils.isStackEqualStrict(stack, itemStackDestination)) continue;
            if (ret == null) {
                ret = InventoryUtils.copyWithSize(stack, 0);
                if (itemStackDestination == null) {
                    if (!ignoreMaxStackSize) {
                        max = Math.min(max, ret.getMaxStackSize());
                    }
                    itemStackDestination = ret;
                }
            }
            int transfer = Math.min(max, stack.stackSize);
            if (!simulate) {
                stack.stackSize -= transfer;
                if (stack.stackSize == 0) {
                    inv.setInventorySlotContents(i, null);
                }
            }
            max -= transfer;
            ret.stackSize += transfer;
        }
        if (!simulate && ret != null) {
            inv.markDirty();
        }
        return ret;
    }

    public static boolean isStackEqual(ItemStack stack1, ItemStack stack2) {
        return stack1 == null && stack2 == null || stack1 != null && stack2 != null && stack1.getItem() == stack2.getItem() && (!stack1.getHasSubtypes() && !stack1.isItemStackDamageable() || stack1.getItemDamage() == stack2.getItemDamage());
    }

    public static boolean isStackEqualStrict(ItemStack stack1, ItemStack stack2) {
        return InventoryUtils.isStackEqual(stack1, stack2) && ItemStack.areItemStackTagsEqual((ItemStack)stack1, (ItemStack)stack2);
    }

    public static List<ItemStack> generateDropsFromTileEntity(TileEntity tileEntity) {
        List<ItemStack> itemStacksToDrops = tileEntity instanceof IInventory ? InventoryUtils.generateDropsFromInventory((IInventory)tileEntity) : new ArrayList<ItemStack>();
        return itemStacksToDrops;
    }

    public static List<ItemStack> generateDropsFromInventory(IInventory inventory) {
        ArrayList<ItemStack> itemStacksToDrops = new ArrayList<ItemStack>();
        for (int i = 0; i < inventory.getSizeInventory(); ++i) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack == null) continue;
            itemStacksToDrops.add(itemStack);
        }
        return itemStacksToDrops;
    }

    public static boolean dropItemsFromInventory(TileEntity tileEntity) {
        List<ItemStack> itemStacksToDrops = InventoryUtils.generateDropsFromTileEntity(tileEntity);
        if (!itemStacksToDrops.isEmpty()) {
            InventoryUtils.dropItemsAtPos(itemStacksToDrops, tileEntity.getWorldObj(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
            return true;
        }
        return false;
    }

    public static boolean dropItemsFromInventory(IInventory inventory, World world, int posX, int posY, int posZ) {
        List<ItemStack> itemStacksToDrops = InventoryUtils.generateDropsFromInventory(inventory);
        if (!itemStacksToDrops.isEmpty()) {
            InventoryUtils.dropItemsAtPos(itemStacksToDrops, world, posX, posY, posZ);
            return true;
        }
        return false;
    }

    public static void dropItemsAtPos(List<ItemStack> itemStackToDrop, World world, float posX, float posY, float posZ) {
        for (ItemStack itemStack : itemStackToDrop) {
            InventoryUtils.dropItemAtPos(itemStack, world, posX, posY, posZ);
        }
    }

    public static void dropItemAtPos(ItemStack itemStack, World world, float posX, float posY, float posZ) {
        float f = world.rand.nextFloat() * 0.8f + 0.1f;
        float f1 = world.rand.nextFloat() * 0.8f + 0.1f;
        float f2 = world.rand.nextFloat() * 0.8f + 0.1f;
        while (itemStack.stackSize > 0) {
            int j1 = world.rand.nextInt(21) + 10;
            if (j1 > itemStack.stackSize) {
                j1 = itemStack.stackSize;
            }
            itemStack.stackSize -= j1;
            EntityItem entityitem = new EntityItem(world, (double)(posX + f), (double)(posY + f1), (double)(posZ + f2), new ItemStack(itemStack.getItem(), j1, itemStack.getItemDamage()));
            if (itemStack.hasTagCompound()) {
                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemStack.getTagCompound().copy());
            }
            float f3 = 0.05f;
            entityitem.motionX = (float)world.rand.nextGaussian() * f3;
            entityitem.motionY = (float)world.rand.nextGaussian() * f3 + 0.2f;
            entityitem.motionZ = (float)world.rand.nextGaussian() * f3;
            world.spawnEntityInWorld((Entity)entityitem);
        }
    }
}

