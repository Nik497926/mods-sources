package thaumcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;

import java.util.WeakHashMap;

public class TileMagicWorkbench extends TileThaumcraft implements IInventory, ISidedInventory
{
	public ItemStack[] stackList = new ItemStack[11];
	public Container eventHandler;
	protected int count;

	// TODO gamerforEA code start
	public WeakHashMap<EntityPlayer, Container> containers = new WeakHashMap<>();
	// TODO gamerforEA code end

	@Override
	public int getSizeInventory()
	{
		return this.stackList.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return par1 >= this.getSizeInventory() ? null : this.stackList[par1];
	}

	public ItemStack getStackInRowAndColumn(int par1, int par2)
	{
		if (par1 >= 0 && par1 < 3)
		{
			int var3 = par1 + par2 * 3;
			return this.getStackInSlot(var3);
		}
		else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (this.stackList[par1] != null)
		{
			ItemStack var2 = this.stackList[par1];
			this.stackList[par1] = null;
			this.markDirty();
			return var2;
		}
		else
			return null;
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.stackList[par1] != null)
		{
			if (this.stackList[par1].stackSize <= par2)
			{
				ItemStack var3 = this.stackList[par1];
				this.stackList[par1] = null;

				/* TODO gamerforEA code replace, old code:
				if (this.eventHandler != null)
					this.eventHandler.onCraftMatrixChanged(this); */
				for (Container container : this.containers.values())
				{
					container.onCraftMatrixChanged(this);
				}
				// TODO gamerforEA code end

				this.markDirty();
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
				return var3;
			}
			else
			{
				ItemStack var3 = this.stackList[par1].splitStack(par2);
				if (this.stackList[par1].stackSize == 0)
					this.stackList[par1] = null;

				/* TODO gamerforEA code replace, old code:
				if (this.eventHandler != null)
					this.eventHandler.onCraftMatrixChanged(this); */
				for (Container container : this.containers.values())
				{
					container.onCraftMatrixChanged(this);
				}
				// TODO gamerforEA code end

				this.markDirty();
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
				return var3;
			}
		}
		else
			return null;
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.stackList[par1] = par2ItemStack;
		this.markDirty();
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);

		/* TODO gamerforEA code replace, old code:
		if (this.eventHandler != null)
			this.eventHandler.onCraftMatrixChanged(this); */
		for (Container container : this.containers.values())
		{
			container.onCraftMatrixChanged(this);
		}
		// TODO gamerforEA code end
	}

	public void setInventorySlotContentsSoftly(int par1, ItemStack par2ItemStack)
	{
		this.stackList[par1] = par2ItemStack;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return true;
	}

	@Override
	public void readCustomNBT(NBTTagCompound par1NBTTagCompound)
	{
		NBTTagList var2 = par1NBTTagCompound.getTagList("Inventory", 10);
		this.stackList = new ItemStack[this.getSizeInventory()];

		for (int var3 = 0; var3 < var2.tagCount(); ++var3)
		{
			NBTTagCompound var4 = var2.getCompoundTagAt(var3);
			int var5 = var4.getByte("Slot") & 255;
			if (var5 >= 0 && var5 < this.stackList.length)
				this.stackList[var5] = ItemStack.loadItemStackFromNBT(var4);
		}
	}

	@Override
	public void writeCustomNBT(NBTTagCompound par1NBTTagCompound)
	{
		NBTTagList var2 = new NBTTagList();

		for (int var3 = 0; var3 < this.stackList.length; ++var3)
		{
			if (this.stackList[var3] != null)
			{
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.stackList[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}

		par1NBTTagCompound.setTag("Inventory", var2);
	}

	@Override
	public String getInventoryName()
	{
		// TODO gamerforEA code replace, old code:
		// return null;
		return "";
		// TODO gamerforEA code end
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		if (i == 10 && itemstack != null)
		{
			if (!(itemstack.getItem() instanceof ItemWandCasting))
				return false;
			else
			{
				ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
				return !wand.isStaff(itemstack);
			}
		}
		else
			return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1)
	{
		return new int[] { 10 };
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j)
	{
		if (i == 10 && itemstack != null && itemstack.getItem() instanceof ItemWandCasting)
		{
			ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
			return !wand.isStaff(itemstack);
		}
		else
			return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j)
	{
		return i == 10;
	}
}
