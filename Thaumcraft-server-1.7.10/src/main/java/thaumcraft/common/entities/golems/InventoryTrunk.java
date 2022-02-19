package thaumcraft.common.entities.golems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryTrunk implements IInventory
{
	public ItemStack[] inventory;
	public EntityTravelingTrunk ent;
	public boolean inventoryChanged;
	public int slotCount;
	public int stacklimit = 64;

	public InventoryTrunk(EntityTravelingTrunk entity, int slots)
	{
		this.slotCount = slots;
		this.inventory = new ItemStack[36];
		this.inventoryChanged = false;
		this.ent = entity;
	}

	public InventoryTrunk(EntityTravelingTrunk entity, int slots, int lim)
	{
		this.slotCount = slots;
		this.inventory = new ItemStack[36];
		this.inventoryChanged = false;
		this.stacklimit = lim;
		this.ent = entity;
	}

	public int getInventorySlotContainItem(Item i)
	{
		// TODO gamerforEA code start
		if (this.ent.isDead)
			return -1;
		// TODO gamerforEA code end

		for (int j = 0; j < this.inventory.length; ++j)
		{
			if (this.inventory[j] != null && this.inventory[j].getItem() == i)
				return j;
		}

		return -1;
	}

	public int getFirstEmptyStack()
	{
		// TODO gamerforEA code start
		if (this.ent.isDead)
			return -1;
		// TODO gamerforEA code end

		for (int i = 0; i < this.inventory.length; ++i)
		{
			if (this.inventory[i] == null)
				return i;
		}

		return -1;
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		// TODO gamerforEA code start
		if (this.ent.isDead)
			return null;
		// TODO gamerforEA code end

		ItemStack[] aitemstack = this.inventory;
		if (aitemstack[i] != null)
		{
			if (aitemstack[i].stackSize <= j)
			{
				ItemStack itemstack = aitemstack[i];
				aitemstack[i] = null;
				return itemstack;
			}
			else
			{
				ItemStack itemstack1 = aitemstack[i].splitStack(j);
				if (aitemstack[i].stackSize == 0)
					aitemstack[i] = null;

				return itemstack1;
			}
		}
		else
			return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		// TODO gamerforEA code start
		if (this.ent.isDead)
			return;
		// TODO gamerforEA code end

		ItemStack[] aitemstack = this.inventory;
		aitemstack[i] = itemstack;
	}

	public NBTTagList writeToNBT(NBTTagList nbttaglist)
	{
		for (int i = 0; i < this.inventory.length; ++i)
		{
			if (this.inventory[i] != null)
			{
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				this.inventory[i].writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		return nbttaglist;
	}

	public void readFromNBT(NBTTagList nbttaglist)
	{
		this.inventory = new ItemStack[this.inventory.length];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;
			ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);
			if (itemstack.getItem() != null && j >= 0 && j < this.inventory.length)
				this.inventory[j] = itemstack;
		}

	}

	@Override
	public int getSizeInventory()
	{
		return this.slotCount;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		// TODO gamerforEA code start
		if (this.ent.isDead)
			return null;
		// TODO gamerforEA code end

		ItemStack[] aitemstack = this.inventory;
		return aitemstack[i];
	}

	@Override
	public int getInventoryStackLimit()
	{
		return this.stacklimit;
	}

	public void dropAllItems()
	{
		// TODO gamerforEA code start
		if (this.ent.isDead)
			return;
		// TODO gamerforEA code end

		for (int i = 0; i < this.inventory.length; ++i)
		{
			if (this.inventory[i] != null)
			{
				this.ent.entityDropItem(this.inventory[i], 0.0F);
				this.inventory[i] = null;
			}
		}

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return false;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1)
	{
		return null;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}

	@Override
	public String getInventoryName()
	{
		return "Inventory";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public void markDirty()
	{
		this.inventoryChanged = true;
	}

	@Override
	public void openInventory()
	{
		if (this.ent instanceof EntityTravelingTrunk)
			this.ent.setOpen(true);

	}

	@Override
	public void closeInventory()
	{
		if (this.ent instanceof EntityTravelingTrunk)
			this.ent.setOpen(false);

	}
}
