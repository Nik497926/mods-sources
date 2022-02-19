package thaumcraft.common.tiles;

import com.gamerforea.eventhelper.util.EventUtils;
import com.gamerforea.thaumcraft.EventConfig;
import com.google.common.base.Strings;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.container.InventoryFake;
import thaumcraft.common.entities.EntitySpecialItem;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

import java.awt.*;

public class TileCrucible extends TileThaumcraft implements IFluidHandler, IWandable, IAspectContainer
{
	public short heat = 0;
	public AspectList aspects = new AspectList();
	public final int maxTags = 100;
	int bellows = -1;
	private int delay = 0;
	public FluidTank tank = new FluidTank(FluidRegistry.WATER, 0, 1000);
	private long counter = -100L;
	int prevcolor = 0;
	int prevx = 0;
	int prevy = 0;

	@Override
	public void readCustomNBT(NBTTagCompound nbttagcompound)
	{
		this.heat = nbttagcompound.getShort("Heat");
		this.tank.readFromNBT(nbttagcompound);
		if (nbttagcompound.hasKey("Empty"))
			this.tank.setFluid(null);

		this.aspects.readFromNBT(nbttagcompound);
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setShort("Heat", this.heat);
		this.tank.writeToNBT(nbttagcompound);
		this.aspects.writeToNBT(nbttagcompound);
	}

	@Override
	public void updateEntity()
	{
		++this.counter;
		int prevheat = this.heat;
		if (!this.worldObj.isRemote)
		{
			if (this.bellows < 0)
				this.getBellows();

			if (this.tank.getFluidAmount() <= 0)
			{
				if (this.heat > 0)
					--this.heat;
			}
			else
			{
				Material mat = this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord).getMaterial();
				Block bi = this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord);
				int md = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord - 1, this.zCoord);
				if (mat == Material.lava || mat == Material.fire || bi == ConfigBlocks.blockAiry && md == 1)
				{
					if (this.heat < 200)
					{
						this.heat = (short) (this.heat + 1 + this.bellows * 2);
						if (prevheat < 151 && this.heat >= 151)
						{
							this.markDirty();
							this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
						}
					}
				}
				else if (this.heat > 0)
				{
					--this.heat;
					if (this.heat == 149)
					{
						this.markDirty();
						this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
					}
				}
			}

			if (this.tagAmount() > 100 && this.counter % 5L == 0L)
			{
				AspectList tt = this.takeRandomFromSource();
				this.spill();
			}

			if (this.counter > 100L && this.heat > 150)
			{
				this.counter = 0L;
				if (this.tagAmount() > 0)
				{
					int s = this.aspects.getAspects().length;
					Aspect a = this.aspects.getAspects()[this.worldObj.rand.nextInt(s)];
					if (a.isPrimal())
						a = this.aspects.getAspects()[this.worldObj.rand.nextInt(s)];

					this.tank.drain(2, true);
					this.aspects.remove(a, 1);
					if (!a.isPrimal())
					{
						if (this.worldObj.rand.nextBoolean())
							this.aspects.add(a.getComponents()[0], 1);
						else
							this.aspects.add(a.getComponents()[1], 1);
					}
					else
						this.spill();
				}

				this.markDirty();
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			}
		}
		else if (this.tank.getFluidAmount() > 0)
			this.drawEffects();

		if (this.worldObj.isRemote && prevheat < 151 && this.heat >= 151)
			++this.heat;

	}

	private void drawEffects()
	{
		if (this.heat > 150)
		{
			Thaumcraft.proxy.crucibleFroth(this.worldObj, this.xCoord + 0.2F + this.worldObj.rand.nextFloat() * 0.6F, this.yCoord + this.getFluidHeight(), this.zCoord + 0.2F + this.worldObj.rand.nextFloat() * 0.6F);
			if (this.tagAmount() > 100)
				for (int a = 0; a < 2; ++a)
				{
					Thaumcraft.proxy.crucibleFrothDown(this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord + this.worldObj.rand.nextFloat());
					Thaumcraft.proxy.crucibleFrothDown(this.worldObj, this.xCoord + 1, this.yCoord + 1, this.zCoord + this.worldObj.rand.nextFloat());
					Thaumcraft.proxy.crucibleFrothDown(this.worldObj, this.xCoord + this.worldObj.rand.nextFloat(), this.yCoord + 1, this.zCoord);
					Thaumcraft.proxy.crucibleFrothDown(this.worldObj, this.xCoord + this.worldObj.rand.nextFloat(), this.yCoord + 1, this.zCoord + 1);
				}
		}

		if (this.worldObj.rand.nextInt(6) == 0 && this.aspects.size() > 0)
		{
			int color = this.aspects.getAspects()[this.worldObj.rand.nextInt(this.aspects.size())].getColor() + -16777216;
			int x = 5 + this.worldObj.rand.nextInt(22);
			int y = 5 + this.worldObj.rand.nextInt(22);
			this.delay = this.worldObj.rand.nextInt(10);
			this.prevcolor = color;
			this.prevx = x;
			this.prevy = y;
			Color c = new Color(color);
			float r = c.getRed() / 255.0F;
			float g = c.getGreen() / 255.0F;
			float b = c.getBlue() / 255.0F;
			Thaumcraft.proxy.crucibleBubble(this.worldObj, this.xCoord + x / 32.0F + 0.015625F, this.yCoord + 0.05F + this.getFluidHeight(), this.zCoord + y / 32.0F + 0.015625F, r, g, b);
		}

	}

	public void spill()
	{
		if (this.worldObj.rand.nextInt(4) == 0)
			if (this.worldObj.isAirBlock(this.xCoord, this.yCoord + 1, this.zCoord))
			{
				if (this.worldObj.rand.nextBoolean())
					this.worldObj.setBlock(this.xCoord, this.yCoord + 1, this.zCoord, ConfigBlocks.blockFluxGas, 0, 3);
				else
					this.worldObj.setBlock(this.xCoord, this.yCoord + 1, this.zCoord, ConfigBlocks.blockFluxGoo, 0, 3);
			}
			else
			{
				Block bi = this.worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord);
				int md = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord + 1, this.zCoord);
				if (bi == ConfigBlocks.blockFluxGoo && md < 7)
					this.worldObj.setBlock(this.xCoord, this.yCoord + 1, this.zCoord, ConfigBlocks.blockFluxGoo, md + 1, 3);
				else if (bi == ConfigBlocks.blockFluxGas && md < 7)
					this.worldObj.setBlock(this.xCoord, this.yCoord + 1, this.zCoord, ConfigBlocks.blockFluxGas, md + 1, 3);
				else
				{
					int x = -1 + this.worldObj.rand.nextInt(3);
					int y = -1 + this.worldObj.rand.nextInt(3);
					int z = -1 + this.worldObj.rand.nextInt(3);
					if (this.worldObj.isAirBlock(this.xCoord + x, this.yCoord + y, this.zCoord + z))
						if (this.worldObj.rand.nextBoolean())
							this.worldObj.setBlock(this.xCoord + x, this.yCoord + y, this.zCoord + z, ConfigBlocks.blockFluxGas, 0, 3);
						else
							this.worldObj.setBlock(this.xCoord + x, this.yCoord + y, this.zCoord + z, ConfigBlocks.blockFluxGoo, 0, 3);
				}
			}

	}

	public void spillRemnants()
	{
		if (this.tank.getFluidAmount() > 0 || this.aspects.visSize() > 0)
		{
			this.tank.setFluid(null);

			for (int a = 0; a < this.aspects.visSize() / 2; ++a)
			{
				this.spill();
			}

			this.aspects = new AspectList();
			this.markDirty();
			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, ConfigBlocks.blockMetalDevice, 2, 5);
		}

	}

	public void ejectItem(ItemStack items)
	{
		boolean first = true;

		while (items.stackSize > 0)
		{
			ItemStack spitout = items.copy();
			if (spitout.stackSize > spitout.getMaxStackSize())
				spitout.stackSize = spitout.getMaxStackSize();

			items.stackSize -= spitout.stackSize;
			EntitySpecialItem entityitem = new EntitySpecialItem(this.worldObj, this.xCoord + 0.5F, this.yCoord + 0.71F, this.zCoord + 0.5F, spitout);
			entityitem.motionY = 0.10000000149011612D;
			entityitem.motionX = first ? 0.0D : (double) ((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.01F);
			entityitem.motionZ = first ? 0.0D : (double) ((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.01F);
			this.worldObj.spawnEntityInWorld(entityitem);
			first = false;
		}
	}

	public void attemptSmelt(EntityItem entity)
	{
		ItemStack item = entity.getEntityItem();

		// TODO gamerforEA code start
		if (entity.isDead || item.stackSize <= 0)
			return;

		if (EventConfig.protectCrucible)
		{
			String throwerName = entity.func_145800_j();
			if (!Strings.isNullOrEmpty(throwerName))
			{
				MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
				if (server != null)
				{
					EntityPlayer player = server.getConfigurationManager().func_152612_a(throwerName);
					if (player != null && EventUtils.cantBreak(player, this.xCoord, this.yCoord, this.zCoord))
						return;
				}
			}
		}
		// TODO gamerforEA code end

		boolean bubble = false;
		boolean event = false;
		NBTTagCompound itemData = entity.getEntityData();
		String username = itemData.getString("thrower");
		int stacksize = item.stackSize;

		for (int a = 0; a < stacksize; ++a)
		{
			CrucibleRecipe rc = ThaumcraftCraftingManager.findMatchingCrucibleRecipe(username, this.aspects, item);
			if (rc != null && this.tank.getFluidAmount() > 0)
			{
				ItemStack out = rc.getRecipeOutput().copy();
				EntityPlayer p = this.worldObj.getPlayerEntityByName(username);
				if (p != null)
					FMLCommonHandler.instance().firePlayerCraftingEvent(p, out, new InventoryFake(new ItemStack[] { item }));

				this.aspects = rc.removeMatching(this.aspects);
				this.tank.drain(50, true);
				this.ejectItem(out);
				event = true;
				--stacksize;
				this.counter = -250L;
			}
			else
			{
				AspectList ot = ThaumcraftCraftingManager.getObjectTags(item);
				ot = ThaumcraftCraftingManager.getBonusTags(item, ot);
				if (ot == null || ot.size() == 0)
				{
					entity.motionY = 0.3499999940395355D;
					entity.motionX = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F;
					entity.motionZ = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F;
					this.worldObj.playSoundAtEntity(entity, "random.pop", 0.2F, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.7F + 1.0F);
					return;
				}

				for (Aspect tag : ot.getAspects())
				{
					this.aspects.add(tag, ot.getAmount(tag));
				}

				bubble = true;
				--stacksize;
				this.counter = -150L;
			}
		}

		if (bubble)
		{
			this.worldObj.playSoundAtEntity(entity, "thaumcraft:bubble", 0.2F, 1.0F + this.worldObj.rand.nextFloat() * 0.4F);
			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, ConfigBlocks.blockMetalDevice, 2, 1);
		}

		if (event)
		{
			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, ConfigBlocks.blockMetalDevice, 2, 5);
		}

		if (stacksize <= 0)
		{
			// TODO gamerforEA code start
			item.stackSize = 0;
			// TODO gamerforEA code end

			entity.setDead();
		}
		else
		{
			item.stackSize = stacksize;
			entity.setEntityItemStack(item);
		}

		this.markDirty();
	}

	public int tagAmount()
	{
		int tt = 0;
		if (this.aspects.size() <= 0)
			return 0;
		else
		{
			for (Aspect tag : this.aspects.getAspects())
			{
				tt += this.aspects.getAmount(tag);
			}

			return tt;
		}
	}

	public float getFluidHeight()
	{
		float base = 0.3F + 0.5F * ((float) this.tank.getFluidAmount() / (float) this.tank.getCapacity());
		float out = base + this.tagAmount() / 100.0F * (1.0F - base);
		if (out > 1.0F)
			out = 1.001F;

		if (out == 1.0F)
			out = 0.9999F;

		return out;
	}

	public AspectList takeRandomFromSource()
	{
		AspectList output = new AspectList();
		if (this.aspects.size() > 0)
		{
			Aspect tag = this.aspects.getAspects()[this.worldObj.rand.nextInt(this.aspects.getAspects().length)];
			output.add(tag, 1);
			this.aspects.remove(tag, 1);
		}

		this.markDirty();
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		return output;
	}

	@Override
	public boolean receiveClientEvent(int i, int j)
	{
		if (i == 1)
		{
			if (this.worldObj.isRemote)
				Thaumcraft.proxy.blockSparkle(this.worldObj, this.xCoord, this.yCoord, this.zCoord, -9999, 5);

			return true;
		}
		else if (i != 2)
			return super.receiveClientEvent(i, j);
		else
		{
			Thaumcraft.proxy.crucibleBoilSound(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			if (this.worldObj.isRemote)
				for (int q = 0; q < 10; ++q)
				{
					int x = 5 + this.worldObj.rand.nextInt(22);
					int y = 5 + this.worldObj.rand.nextInt(22);
					Thaumcraft.proxy.crucibleBoil(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this, j);
				}

			return true;
		}
	}

	public void getBellows()
	{
		this.bellows = 0;

		for (int a = 2; a < 6; ++a)
		{
			ForgeDirection dir = ForgeDirection.getOrientation(a);
			int xx = this.xCoord + dir.offsetX;
			int zz = this.zCoord + dir.offsetZ;
			Block bi = this.worldObj.getBlock(xx, this.yCoord, zz);
			int md = this.worldObj.getBlockMetadata(xx, this.yCoord, zz);
			if (bi == ConfigBlocks.blockWoodenDevice && md == 0)
				++this.bellows;
		}

	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		// TODO gamerforEA replace [resource.fluidID != FluidRegistry.WATER.getID()] to [resource.getFluid() != FluidRegistry.WATER]
		if (resource != null && resource.getFluid() != FluidRegistry.WATER)
			return 0;
		else
		{
			if (doFill)
			{
				this.markDirty();
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			}

			return this.tank.fill(resource, doFill);
		}
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if (resource != null && resource.isFluidEqual(this.tank.getFluid()))
		{
			if (doDrain)
			{
				this.markDirty();
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			}

			return this.tank.drain(resource.amount, doDrain);
		}
		else
			return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return this.tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return fluid != null && fluid.getID() == FluidRegistry.WATER.getID();
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { this.tank.getInfo() };
	}

	@Override
	public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
	{
		return 0;
	}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
	{
		if (!world.isRemote && player.isSneaking())
			this.spillRemnants();

		return wandstack;
	}

	@Override
	public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count)
	{
	}

	@Override
	public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count)
	{
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox()
	{
		return AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 1, this.zCoord + 1);
	}

	@Override
	public AspectList getAspects()
	{
		return this.aspects;
	}

	@Override
	public void setAspects(AspectList aspects)
	{
	}

	@Override
	public int addToContainer(Aspect tag, int amount)
	{
		return 0;
	}

	@Override
	public boolean takeFromContainer(Aspect tag, int amount)
	{
		return false;
	}

	@Override
	public boolean takeFromContainer(AspectList ot)
	{
		return false;
	}

	@Override
	public boolean doesContainerContainAmount(Aspect tag, int amount)
	{
		return false;
	}

	@Override
	public boolean doesContainerContain(AspectList ot)
	{
		return false;
	}

	@Override
	public int containerContains(Aspect tag)
	{
		return 0;
	}

	@Override
	public boolean doesContainerAccept(Aspect tag)
	{
		return true;
	}
}
