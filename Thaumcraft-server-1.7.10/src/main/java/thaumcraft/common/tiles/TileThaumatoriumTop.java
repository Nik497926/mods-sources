package thaumcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;

public class TileThaumatoriumTop extends TileThaumcraft implements IAspectContainer, IEssentiaTransport, ISidedInventory
{
	public TileThaumatorium thaumatorium = null;

	@Override
	public boolean canUpdate()
	{
		return true;
	}

	@Override
	public void updateEntity()
	{
		if (this.thaumatorium == null)
		{
			TileEntity tile = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
			if (tile instanceof TileThaumatorium)
			{
				this.thaumatorium = (TileThaumatorium) tile;
				this.worldObj.notifyBlockChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
				this.markDirty();
			}
			else
				this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 9, 3);
		}
	}

	// TODO gamerforEA code start
	public TileThaumatorium getThaumatorium()
	{
		TileThaumatorium thaumatorium = this.thaumatorium;
		if (thaumatorium == null)
			return null;

		if (thaumatorium.isInvalid() || thaumatorium.xCoord != this.xCoord || thaumatorium.zCoord != this.zCoord || thaumatorium.yCoord + 1 != this.yCoord)
		{
			this.thaumatorium = null;
			return null;
		}

		return thaumatorium;
	}
	// TODO gamerforEA code end

	@Override
	public int addToContainer(Aspect tt, int am)
	{
		// TODO gamerforEA code replace, old code:
		// return this.thaumatorium == null ? am : this.thaumatorium.addToContainer(tt, am);
		TileThaumatorium thaumatorium = this.getThaumatorium();
		return thaumatorium == null ? am : thaumatorium.addToContainer(tt, am);
		// TODO gamerforEA code end
	}

	@Override
	public boolean takeFromContainer(Aspect tt, int am)
	{
		// TODO gamerforEA code replace, old code:
		// return this.thaumatorium != null && this.thaumatorium.takeFromContainer(tt, am);
		TileThaumatorium thaumatorium = this.getThaumatorium();
		return thaumatorium != null && thaumatorium.takeFromContainer(tt, am);
		// TODO gamerforEA code end
	}

	@Override
	public boolean takeFromContainer(AspectList ot)
	{
		return false;
	}

	@Override
	public boolean doesContainerContain(AspectList ot)
	{
		return false;
	}

	@Override
	public boolean doesContainerContainAmount(Aspect tt, int am)
	{
		// TODO gamerforEA code replace, old code:
		// return this.thaumatorium != null && this.thaumatorium.doesContainerContainAmount(tt, am);
		TileThaumatorium thaumatorium = this.getThaumatorium();
		return thaumatorium != null && thaumatorium.doesContainerContainAmount(tt, am);
		// TODO gamerforEA code end
	}

	@Override
	public int containerContains(Aspect tt)
	{
		// TODO gamerforEA code replace, old code:
		// return this.thaumatorium == null ? 0 : this.thaumatorium.containerContains(tt);
		TileThaumatorium thaumatorium = this.getThaumatorium();
		return thaumatorium == null ? 0 : thaumatorium.containerContains(tt);
		// TODO gamerforEA code end
	}

	@Override
	public boolean doesContainerAccept(Aspect tag)
	{
		return true;
	}

	@Override
	public boolean isConnectable(ForgeDirection face)
	{
		// TODO gamerforEA code replace, old code:
		// return this.thaumatorium != null && this.thaumatorium.isConnectable(face);
		TileThaumatorium thaumatorium = this.getThaumatorium();
		return thaumatorium != null && thaumatorium.isConnectable(face);
		// TODO gamerforEA code end
	}

	@Override
	public boolean canInputFrom(ForgeDirection face)
	{
		// TODO gamerforEA code replace, old code:
		// return this.thaumatorium != null && this.thaumatorium.canInputFrom(face);
		TileThaumatorium thaumatorium = this.getThaumatorium();
		return thaumatorium != null && thaumatorium.canInputFrom(face);
		// TODO gamerforEA code end
	}

	@Override
	public boolean canOutputTo(ForgeDirection face)
	{
		return false;
	}

	@Override
	public void setSuction(Aspect aspect, int amount)
	{
		/* TODO gamerforEA code replace, old code:
		if (this.thaumatorium != null)
			this.thaumatorium.setSuction(aspect, amount); */
		TileThaumatorium thaumatorium = this.getThaumatorium();
		if (thaumatorium != null)
			thaumatorium.setSuction(aspect, amount);
		// TODO gamerforEA code end
	}

	@Override
	public Aspect getSuctionType(ForgeDirection loc)
	{
		// TODO gamerforEA code replace, old code:
		// return this.thaumatorium == null ? null : this.thaumatorium.getSuctionType(loc);
		TileThaumatorium thaumatorium = this.getThaumatorium();
		return thaumatorium == null ? null : thaumatorium.getSuctionType(loc);
		// TODO gamerforEA code end
	}

	@Override
	public int getSuctionAmount(ForgeDirection loc)
	{
		// TODO gamerforEA code replace, old code:
		// return this.thaumatorium == null ? 0 : this.thaumatorium.getSuctionAmount(loc);
		TileThaumatorium thaumatorium = this.getThaumatorium();
		return thaumatorium == null ? 0 : thaumatorium.getSuctionAmount(loc);
		// TODO gamerforEA code end
	}

	@Override
	public Aspect getEssentiaType(ForgeDirection loc)
	{
		return null;
	}

	@Override
	public int getEssentiaAmount(ForgeDirection loc)
	{
		return 0;
	}

	@Override
	public int takeEssentia(Aspect aspect, int amount, ForgeDirection face)
	{
		// TODO gamerforEA code replace, old code:
		// return this.thaumatorium == null ? 0 : this.thaumatorium.takeEssentia(aspect, amount, face);
		TileThaumatorium thaumatorium = this.getThaumatorium();
		return thaumatorium == null ? 0 : thaumatorium.takeEssentia(aspect, amount, face);
		// TODO gamerforEA code end
	}

	@Override
	public int addEssentia(Aspect aspect, int amount, ForgeDirection face)
	{
		// TODO gamerforEA code replace, old code:
		// return this.thaumatorium == null ? 0 : this.thaumatorium.addEssentia(aspect, amount, face);
		TileThaumatorium thaumatorium = this.getThaumatorium();
		return thaumatorium == null ? 0 : thaumatorium.addEssentia(aspect, amount, face);
		// TODO gamerforEA code end
	}

	@Override
	public int getMinimumSuction()
	{
		return 0;
	}

	@Override
	public boolean renderExtendedTube()
	{
		return false;
	}

	@Override
	public AspectList getAspects()
	{
		// TODO gamerforEA code replace, old code:
		// return this.thaumatorium == null ? null : this.thaumatorium.essentia;
		TileThaumatorium thaumatorium = this.getThaumatorium();
		return thaumatorium == null ? null : thaumatorium.essentia;
		// TODO gamerforEA code end
	}

	@Override
	public void setAspects(AspectList aspects)
	{
		/* TODO gamerforEA code replace, old code:
		if (this.thaumatorium != null)
			this.thaumatorium.setAspects(aspects); */
		TileThaumatorium thaumatorium = this.getThaumatorium();
		if (thaumatorium != null)
			thaumatorium.setAspects(aspects);
		// TODO gamerforEA code end
	}

	@Override
	public int getSizeInventory()
	{
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		// TODO gamerforEA code replace, old code:
		// return this.thaumatorium == null ? null : this.thaumatorium.getStackInSlot(par1);
		TileThaumatorium thaumatorium = this.getThaumatorium();
		return thaumatorium == null ? null : thaumatorium.getStackInSlot(par1);
		// TODO gamerforEA code end
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		// TODO gamerforEA code replace, old code:
		// return this.thaumatorium == null ? null : this.thaumatorium.decrStackSize(par1, par2);
		TileThaumatorium thaumatorium = this.getThaumatorium();
		return thaumatorium == null ? null : thaumatorium.decrStackSize(par1, par2);
		// TODO gamerforEA code end
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		// TODO gamerforEA code replace, old code:
		// return this.thaumatorium == null ? null : this.thaumatorium.getStackInSlotOnClosing(par1);
		TileThaumatorium thaumatorium = this.getThaumatorium();
		return thaumatorium == null ? null : thaumatorium.getStackInSlotOnClosing(par1);
		// TODO gamerforEA code end
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		/* TODO gamerforEA code replace, old code:
		if (this.thaumatorium != null)
			this.thaumatorium.setInventorySlotContents(par1, par2ItemStack); */
		TileThaumatorium thaumatorium = this.getThaumatorium();
		if (thaumatorium != null)
			thaumatorium.setInventorySlotContents(par1, par2ItemStack);
		// TODO gamerforEA code end
	}

	@Override
	public String getInventoryName()
	{
		return "container.alchemyfurnace";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && par1EntityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
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
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return new int[] { 0 };
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return true;
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return true;
	}
}
