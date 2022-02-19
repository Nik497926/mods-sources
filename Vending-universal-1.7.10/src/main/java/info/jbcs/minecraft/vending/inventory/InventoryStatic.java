/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.ironchest.TileEntityIronChest
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package info.jbcs.minecraft.vending.inventory;

import cpw.mods.ironchest.TileEntityIronChest;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class InventoryStatic
implements IInventory {
    public final ItemStack[] items;
    TileEntityVendingMachine tile;

    public InventoryStatic(int size) {
        this.items = new ItemStack[size];
    }

    public InventoryStatic(int size, TileEntityVendingMachine tile) {
        this.items = new ItemStack[size];
        this.tile = tile;
    }

    public String getInventoryName() {
        return null;
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return false;
    }

    public void onInventoryChanged(int slot) {
    }

    public int getSizeInventory() {
        return this.items.length;
    }

    public ItemStack getStackInSlot(int i) {
        return this.items[i];
    }

    public ItemStack decrStackSize(int i, int j) {
        if (this.items[i] != null) {
            if (this.items[i].stackSize <= j) {
                ItemStack itemstack = this.items[i];
                this.items[i] = null;
                this.onInventoryChanged();
                this.onInventoryChanged(i);
                return itemstack;
            }
            ItemStack itemstack1 = this.items[i].splitStack(j);
            if (this.items[i].stackSize == 0) {
                this.items[i] = null;
            }
            this.onInventoryChanged();
            this.onInventoryChanged(i);
            return itemstack1;
        }
        return null;
    }

    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.items[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
        this.onInventoryChanged();
        this.onInventoryChanged(i);
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        NBTTagList nbtTagList = nbtTagCompound.getTagList("Items", 10);
        for (int i = 0; i < nbtTagList.tagCount(); ++i) {
            NBTTagCompound nbtTagCompound1 = nbtTagList.getCompoundTagAt(i);
            int j = nbtTagCompound1.getByte("slot") & 0xFF;
            this.items[j] = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbtTagCompound1);
        }
        this.onInventoryChanged();
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("slot", (byte)i);
            this.items[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
        nbttagcompound.setTag("Items", (NBTBase)nbttaglist);
    }

    private int getFirstEmptyStack(int start, int end) {
        for (int i = start; i <= end; ++i) {
            if (this.items[i] != null) continue;
            return i;
        }
        return -1;
    }

    private int storeItemStack(ItemStack itemstack, int start, int end) {
        for (int i = start; i <= end; ++i) {
            if (this.items[i] == null || this.items[i].getItem() != itemstack.getItem() || !this.items[i].isStackable() || this.items[i].stackSize >= this.items[i].getMaxStackSize() || this.items[i].stackSize >= this.getInventoryStackLimit() || this.items[i].getHasSubtypes() && this.items[i].getItemDamage() != itemstack.getItemDamage()) continue;
            if (this.items[i].hasTagCompound() || itemstack.hasTagCompound()) {
                if (!this.items[i].hasTagCompound() || !itemstack.hasTagCompound() || !this.items[i].getTagCompound().equals((Object)itemstack.getTagCompound())) continue;
                return i;
            }
            return i;
        }
        return -1;
    }

    private int storePartialItemStack(ItemStack itemstack, int start, int end) {
        int l;
        Item i = itemstack.getItem();
        int j = itemstack.stackSize;
        int k = this.storeItemStack(itemstack, start, end);
        if (k < 0) {
            k = this.getFirstEmptyStack(start, end);
        }
        if (k < 0) {
            return j;
        }
        if (this.items[k] == null) {
            this.items[k] = new ItemStack(i, 0, itemstack.getItemDamage());
            if (itemstack.hasTagCompound()) {
                this.items[k].setTagCompound(itemstack.getTagCompound());
            }
        }
        if ((l = j) > this.items[k].getMaxStackSize() - this.items[k].stackSize) {
            l = this.items[k].getMaxStackSize() - this.items[k].stackSize;
        }
        if (l > this.getInventoryStackLimit() - this.items[k].stackSize) {
            l = this.getInventoryStackLimit() - this.items[k].stackSize;
        }
        if (l == 0) {
            return j;
        }
        this.items[k].stackSize += l;
        this.items[k].animationsToGo = 5;
        this.onInventoryChanged();
        this.onInventoryChanged(k);
        return j -= l;
    }

    public boolean addItemStackToInventory(ItemStack itemstack, int start, int end) {
        if (itemstack == null) {
            return true;
        }
        if (!itemstack.isItemDamaged()) {
            int i;
            do {
                i = itemstack.stackSize;
                itemstack.stackSize = this.storePartialItemStack(itemstack, start, end);
            } while (itemstack.stackSize > 0 && itemstack.stackSize < i);
            return itemstack.stackSize < i;
        }
        int j = this.getFirstEmptyStack(start, end);
        if (j >= 0) {
            this.items[j] = ItemStack.copyItemStack((ItemStack)itemstack);
            if (itemstack.hasTagCompound()) {
                this.items[j].setTagCompound(itemstack.getTagCompound());
            }
            this.items[j].animationsToGo = 5;
            itemstack.stackSize = 0;
            this.onInventoryChanged();
            this.onInventoryChanged(j);
            return true;
        }
        return false;
    }

    public boolean addItemStackToInventory(ItemStack itemstack) {
        return this.addItemStackToInventory(itemstack, 0, this.items.length - 1);
    }

    public boolean takeItemsFromChest(ItemStack itemStack, int damage, int count) {
        ItemStack res = null;
        ItemStack[] chest = this.getChest();
        if (chest == null) {
            return false;
        }
        for (int i = 0; i < chest.length; ++i) {
            if (chest[i] == null || chest[i].getItem() != itemStack.getItem() || chest[i].getItemDamage() != damage || itemStack.hasTagCompound() && !itemStack.getTagCompound().equals((Object)chest[i].getTagCompound())) continue;
            if (res == null) {
                res = new ItemStack(itemStack.getItem(), 0, damage);
            }
            while (chest[i] != null && res.stackSize < count && chest[i].stackSize > 0) {
                ++res.stackSize;
                --chest[i].stackSize;
                if (chest[i].stackSize != 0) continue;
                chest[i] = null;
            }
            if (res.stackSize < count) continue;
            return true;
        }
        return false;
    }

    private ItemStack[] getChest() {
        if (this.tile == null) {
            return null;
        }
        TileEntity tileEntity = this.tile.getWorldObj().getTileEntity(this.tile.xCoord, this.tile.yCoord - 1, this.tile.zCoord);
        if (tileEntity instanceof TileEntityIronChest) {
            return ((TileEntityIronChest)tileEntity).getContents();
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int i) {
        return null;
    }

    public boolean hasCustomInventoryName() {
        return true;
    }

    public void onInventoryChanged() {
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }

    public boolean isEmpty() {
        for (ItemStack item : this.items) {
            if (item == null) continue;
            return false;
        }
        return true;
    }

    public void clear() {
        for (int i = 0; i < this.items.length; ++i) {
            this.items[i] = null;
        }
    }

    public void throwItems(World world, int x, int y, int z) {
        if (world.isRemote) {
            return;
        }
        for (int i = 0; i < this.items.length; ++i) {
            ItemStack itemstack = this.items[i];
            if (itemstack == null) continue;
            this.items[i] = null;
            float xx = world.rand.nextFloat() * 0.8f + 0.1f;
            float yy = world.rand.nextFloat() * 0.8f + 0.1f;
            float zz = world.rand.nextFloat() * 0.8f + 0.1f;
            while (itemstack.stackSize > 0) {
                int c = world.rand.nextInt(21) + 10;
                if (c > itemstack.stackSize) {
                    c = itemstack.stackSize;
                }
                itemstack.stackSize -= c;
                EntityItem entityitem = new EntityItem(world, (double)((float)x + xx), (double)((float)y + yy), (double)((float)z + zz), new ItemStack(itemstack.getItem(), c, itemstack.getItemDamage()));
                float f3 = 0.05f;
                entityitem.motionX = (float)world.rand.nextGaussian() * f3;
                entityitem.motionY = (float)world.rand.nextGaussian() * f3 + 0.2f;
                entityitem.motionZ = (float)world.rand.nextGaussian() * f3;
                world.spawnEntityInWorld((Entity)entityitem);
            }
        }
        this.onInventoryChanged();
    }
}

