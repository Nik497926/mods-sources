package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.Utils;

import java.util.ArrayList;

public class TileMirror extends TileThaumcraft implements IInventory
{
	public boolean linked = false;
	public int linkX;
	public int linkY;
	public int linkZ;
	public int linkDim;
	public int instability;
	int count = 0;
	int inc = 40;
	private ArrayList<ItemStack> outputStacks = new ArrayList<ItemStack>();

	@Override
	public boolean canUpdate()
	{
		return true;
	}

	public void restoreLink()
	{
		if (this.isDestinationValid())
		{
			MinecraftServer server = MinecraftServer.getServer();

			// TODO gamerforEA code start
			if (server == null)
				return;
			// TODO gamerforEA code end

			World targetWorld = server.worldServerForDimension(this.linkDim);
			if (targetWorld == null)
				return;

			TileEntity te = targetWorld.getTileEntity(this.linkX, this.linkY, this.linkZ);
			if (te instanceof TileMirror)
			{
				TileMirror tm = (TileMirror) te;
				tm.linked = true;
				tm.linkX = this.xCoord;
				tm.linkY = this.yCoord;
				tm.linkZ = this.zCoord;
				tm.linkDim = this.worldObj.provider.dimensionId;
				targetWorld.markBlockForUpdate(tm.xCoord, tm.yCoord, tm.zCoord);
				this.linked = true;
				this.markDirty();
				tm.markDirty();
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			}
		}

	}

	public void invalidateLink()
	{
		World targetWorld = DimensionManager.getWorld(this.linkDim);
		if (targetWorld != null)
			if (Utils.isChunkLoaded(targetWorld, this.linkX, this.linkZ))
			{
				TileEntity te = targetWorld.getTileEntity(this.linkX, this.linkY, this.linkZ);
				if (te instanceof TileMirror)
				{
					TileMirror tm = (TileMirror) te;
					tm.linked = false;
					this.markDirty();
					tm.markDirty();
					targetWorld.markBlockForUpdate(this.linkX, this.linkY, this.linkZ);
				}

			}
	}

	public boolean isLinkValid()
	{
		if (!this.linked)
			return false;
		World targetWorld = DimensionManager.getWorld(this.linkDim);
		if (targetWorld == null)
			return false;
		TileEntity te = targetWorld.getTileEntity(this.linkX, this.linkY, this.linkZ);
		if (te instanceof TileMirror)
		{
			TileMirror tm = (TileMirror) te;
			if (!tm.linked)
			{
				this.linked = false;
				this.markDirty();
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
				return false;
			}
			if (tm.linkX == this.xCoord && tm.linkY == this.yCoord && tm.linkZ == this.zCoord && tm.linkDim == this.worldObj.provider.dimensionId)
				return true;
			this.linked = false;
			this.markDirty();
			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			return false;
		}
		this.linked = false;
		this.markDirty();
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		return false;
	}

	public boolean isLinkValidSimple()
	{
		if (!this.linked)
			return false;
		World targetWorld = DimensionManager.getWorld(this.linkDim);
		if (targetWorld == null)
			return false;
		TileEntity te = targetWorld.getTileEntity(this.linkX, this.linkY, this.linkZ);
		if (te instanceof TileMirror)
		{
			TileMirror tm = (TileMirror) te;
			return tm.linked && tm.linkX == this.xCoord && tm.linkY == this.yCoord && tm.linkZ == this.zCoord && tm.linkDim == this.worldObj.provider.dimensionId;
		}
		return false;
	}

	public boolean isDestinationValid()
	{
		World targetWorld = DimensionManager.getWorld(this.linkDim);
		if (targetWorld == null)
			return false;
		TileEntity te = targetWorld.getTileEntity(this.linkX, this.linkY, this.linkZ);
		if (te instanceof TileMirror)
		{
			TileMirror tm = (TileMirror) te;
			return !tm.isLinkValid();
		}
		this.linked = false;
		this.markDirty();
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		return false;
	}

	public boolean transport(EntityItem ie)
	{
		ItemStack items = ie.getEntityItem();
		if (this.linked && this.isLinkValid())
		{
			MinecraftServer server = MinecraftServer.getServer();

			// TODO gamerforEA code start
			if (server == null)
				return false;
			// TODO gamerforEA code end

			World world = server.worldServerForDimension(this.linkDim);

			// TODO gamerforEA code start
			if (world == null)
				return false;
			// TODO gamerforEA code end

			TileEntity target = world.getTileEntity(this.linkX, this.linkY, this.linkZ);
			if (target instanceof TileMirror)
			{
				((TileMirror) target).addStack(items);
				this.addInstability(null, items.stackSize);
				ie.setDead();
				this.markDirty();
				target.markDirty();
				this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, ConfigBlocks.blockMirror, 1, 0);
				return true;
			}
			return false;
		}
		return false;
	}

	public void eject()
	{
		if (this.outputStacks.size() > 0 && this.count > 20)
		{
			int i = this.worldObj.rand.nextInt(this.outputStacks.size());
			if (this.outputStacks.get(i) != null)
			{
				ItemStack outItem = this.outputStacks.get(i).copy();
				outItem.stackSize = 1;
				if (this.spawnItem(outItem))
				{
					--this.outputStacks.get(i).stackSize;
					this.addInstability(null, 1);
					this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, ConfigBlocks.blockMirror, 1, 0);
					if (this.outputStacks.get(i).stackSize <= 0)
						this.outputStacks.remove(i);

					this.markDirty();
				}
			}
		}

	}

	public boolean spawnItem(ItemStack stack)
	{
		try
		{
			ForgeDirection face = ForgeDirection.getOrientation(this.getBlockMetadata());
			EntityItem ie2 = new EntityItem(this.worldObj, (double) this.xCoord + 0.5D - (double) face.offsetX * 0.3D, (double) this.yCoord + 0.5D - (double) face.offsetY * 0.3D, (double) this.zCoord + 0.5D - (double) face.offsetZ * 0.3D, stack);
			ie2.motionX = (double) ((float) face.offsetX * 0.15F);
			ie2.motionY = (double) ((float) face.offsetY * 0.15F);
			ie2.motionZ = (double) ((float) face.offsetZ * 0.15F);
			ie2.timeUntilPortal = 20;
			this.worldObj.spawnEntityInWorld(ie2);
			return true;
		}
		catch (Exception var4)
		{
			return false;
		}
	}

	protected void addInstability(World targetWorld, int amt)
	{
		this.instability += amt;
		if (targetWorld != null)
		{
			TileEntity te = targetWorld.getTileEntity(this.linkX, this.linkY, this.linkZ);
			if (te instanceof TileMirror)
			{
				((TileMirror) te).instability += amt;
				if (((TileMirror) te).instability < 0)
					((TileMirror) te).instability = 0;

				te.markDirty();
			}
		}

	}

	@Override
	public void readCustomNBT(NBTTagCompound nbttagcompound)
	{
		super.readCustomNBT(nbttagcompound);
		this.linked = nbttagcompound.getBoolean("linked");
		this.linkX = nbttagcompound.getInteger("linkX");
		this.linkY = nbttagcompound.getInteger("linkY");
		this.linkZ = nbttagcompound.getInteger("linkZ");
		this.linkDim = nbttagcompound.getInteger("linkDim");
		this.instability = nbttagcompound.getInteger("instability");
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbttagcompound)
	{
		super.writeCustomNBT(nbttagcompound);
		nbttagcompound.setBoolean("linked", this.linked);
		nbttagcompound.setInteger("linkX", this.linkX);
		nbttagcompound.setInteger("linkY", this.linkY);
		nbttagcompound.setInteger("linkZ", this.linkZ);
		nbttagcompound.setInteger("linkDim", this.linkDim);
		nbttagcompound.setInteger("instability", this.instability);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean receiveClientEvent(int i, int j)
	{
		if (i != 1)
			return super.receiveClientEvent(i, j);
		if (this.worldObj.isRemote)
		{
			ForgeDirection face = ForgeDirection.getOrientation(this.getBlockMetadata());

			for (int q = 0; q < Thaumcraft.proxy.particleCount(1); ++q)
			{
				double xx = (double) this.xCoord + 0.33D + (double) (this.worldObj.rand.nextFloat() * 0.33F) - (double) face.offsetX / 2.0D;
				double yy = (double) this.yCoord + 0.33D + (double) (this.worldObj.rand.nextFloat() * 0.33F) - (double) face.offsetY / 2.0D;
				double zz = (double) this.zCoord + 0.33D + (double) (this.worldObj.rand.nextFloat() * 0.33F) - (double) face.offsetZ / 2.0D;
				EntitySpellParticleFX var21 = new EntitySpellParticleFX(this.worldObj, xx, yy, zz, 0.0D, 0.0D, 0.0D);
				var21.motionX = (double) face.offsetX * 0.05D;
				var21.motionY = (double) face.offsetY * 0.05D;
				var21.motionZ = (double) face.offsetZ * 0.05D;
				var21.setAlphaF(0.5F);
				var21.setRBGColorF(0.0F, 0.0F, 0.0F);
				Minecraft.getMinecraft().effectRenderer.addEffect(var21);
			}
		}

		return true;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (!this.worldObj.isRemote)
		{
			int tickrate = this.instability / 50;
			if (tickrate == 0 || this.count % (tickrate * tickrate) == 0)
				this.eject();

			this.checkInstability();
			if (this.count++ % this.inc == 0)
				if (!this.isLinkValidSimple())
				{
					if (this.inc < 600)
						this.inc += 20;

					this.restoreLink();
				}
				else
					this.inc = 40;
		}

	}

	public void checkInstability()
	{
		if (this.instability > 0 && this.count % 20 == 0)
		{
			--this.instability;
			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		}

		if (this.instability > 0)
		{
			int amt = VisNetHandler.drainVis(this.worldObj, this.xCoord, this.yCoord, this.zCoord, Aspect.ORDER, Math.min(this.instability, 1));
			if (amt > 0)
			{
				MinecraftServer server = MinecraftServer.getServer();

				// TODO gamerforEA code start
				if (server == null)
					return;
				// TODO gamerforEA code end

				World targetWorld = server.worldServerForDimension(this.linkDim);

				// TODO gamerforEA code start
				if (targetWorld == null)
					return;
				// TODO gamerforEA code end

				this.addInstability(targetWorld, -amt);
			}
		}

	}

	@Override
	public void readFromNBT(NBTTagCompound nbtCompound)
	{
		super.readFromNBT(nbtCompound);
		NBTTagList nbttaglist = nbtCompound.getTagList("Items", 10);
		this.outputStacks = new ArrayList<ItemStack>();

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");
			this.outputStacks.add(ItemStack.loadItemStackFromNBT(nbttagcompound1));
		}

	}

	@Override
	public void writeToNBT(NBTTagCompound nbtCompound)
	{
		super.writeToNBT(nbtCompound);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.outputStacks.size(); ++i)
		{
			if (this.outputStacks.get(i) != null && this.outputStacks.get(i).stackSize > 0)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.outputStacks.get(i).writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbtCompound.setTag("Items", nbttaglist);
	}

	@Override
	public int getSizeInventory()
	{
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return null;
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		return null;
	}

	public void addStack(ItemStack stack)
	{
		this.outputStacks.add(stack);
		this.markDirty();
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		// TODO gamerforEA code start
		if (stack == null)
			return;
		// TODO gamerforEA code end

		MinecraftServer server = MinecraftServer.getServer();

		// TODO gamerforEA code start
		if (server == null)
		{
			this.spawnItem(stack.copy());
			return;
		}
		// TODO gamerforEA code end

		World world = server.worldServerForDimension(this.linkDim);

		// TODO gamerforEA code start
		if (world == null)
		{
			this.spawnItem(stack.copy());
			return;
		}
		// TODO gamerforEA code end

		TileEntity target = world.getTileEntity(this.linkX, this.linkY, this.linkZ);
		if (target instanceof TileMirror)
		{
			((TileMirror) target).addStack(stack.copy());
			this.addInstability(null, stack.stackSize);
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, ConfigBlocks.blockMirror, 1, 0);
		}
		else
			this.spawnItem(stack.copy());
	}

	@Override
	public String getInventoryName()
	{
		return "container.mirror";
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
	public boolean isUseableByPlayer(EntityPlayer var1)
	{
		return false;
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
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		MinecraftServer server = MinecraftServer.getServer();

		// TODO gamerforEA code start
		if (server == null)
			return false;
		// TODO gamerforEA code end

		World world = server.worldServerForDimension(this.linkDim);

		// TODO gamerforEA code start
		if (world == null)
			return false;
		// TODO gamerforEA code end

		TileEntity target = world.getTileEntity(this.linkX, this.linkY, this.linkZ);
		return target instanceof TileMirror;
	}
}
