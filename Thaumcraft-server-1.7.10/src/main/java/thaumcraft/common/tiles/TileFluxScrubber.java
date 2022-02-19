package thaumcraft.common.tiles;

import com.gamerforea.thaumcraft.EventConfig;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.BlockCoordinates;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;

import java.util.ArrayList;
import java.util.Collections;

public class TileFluxScrubber extends TileThaumcraft implements IEssentiaTransport
{
	public int essentia = 0;
	public int charges = 0;
	public int power = 0;
	public ForgeDirection facing = ForgeDirection.getOrientation(0);
	public int count = 0;
	ArrayList<BlockCoordinates> checklist = new ArrayList();

	// TODO gamerforEA code start
	private int workTimer;
	// TODO gamerforEA code end

	@Override
	public boolean canUpdate()
	{
		return true;
	}

	@Override
	public void updateEntity()
	{
		if (this.count == 0)
			this.count = this.worldObj.rand.nextInt(1000);

		if (!this.worldObj.isRemote)
		{
			// TODO gamerforEA code start
			this.workTimer++;
			// TODO gamerforEA code end

			if (this.charges >= 4)
			{
				this.charges -= 4;
				if (this.worldObj.rand.nextInt(4) == 0)
				{
					++this.essentia;
					if (this.essentia > 4)
						this.essentia = 4;
					this.markDirty();
				}
			}

			if (this.power < 5)
				this.power += VisNetHandler.drainVis(this.worldObj, this.xCoord, this.yCoord, this.zCoord, Aspect.AIR, 10);

			if (this.power >= 5)
			{
				// TODO gamerforEA code start
				if (this.workTimer < EventConfig.fluxScrubberSkipTicks * 20)
					return;
				this.workTimer = 0;
				// TODO gamerforEA code end

				this.checkFlux();
			}
		}
	}

	boolean isFlux(int x, int y, int z)
	{
		Material mat = this.worldObj.getBlock(x, y, z).getMaterial();
		return mat == Config.fluxGoomaterial;
	}

	private void checkFlux()
	{
		int distance = 16;
		if (this.checklist.isEmpty())
		{
			for (int xOffset = -distance; xOffset <= distance; ++xOffset)
			{
				for (int yOffset = -distance; yOffset <= distance; ++yOffset)
				{
					for (int zOffset = -distance; zOffset <= distance; ++zOffset)
					{
						int x = this.xCoord + xOffset;
						int y = this.yCoord + yOffset;
						int z = this.zCoord + zOffset;

						// TODO gamerforEA code start
						if (this.getDistanceFrom(x + 0.5, y + 0.5, z + 0.5) >= distance * distance)
							continue;
						// TODO gamerforEA code end

						this.checklist.add(new BlockCoordinates(x, y, z));
					}
				}
			}

			Collections.shuffle(this.checklist, this.worldObj.rand);
		}

		int cc = 0;
		while (cc < 16 && this.checklist.size() > 0)
		{
			++cc;
			BlockCoordinates coords = this.checklist.remove(0);
			int x = coords.x;
			int y = coords.y;
			int z = coords.z;

			// TODO gamerforEA code replace, old code:
			// if (!this.worldObj.isAirBlock(x, y, z) && this.isFlux(x, y, z) && this.getDistanceFrom(x + 0.5, y + 0.5, z + 0.5) < distance * distance)
			if (this.worldObj.blockExists(x, y, z) && !this.worldObj.isAirBlock(x, y, z) && this.isFlux(x, y, z))
			// TODO gamerforEA code end
			{
				this.power -= 5;
				int lmd = this.worldObj.getBlockMetadata(x, y, z);
				if (lmd > 0)
					this.worldObj.setBlockMetadataWithNotify(x, y, z, lmd - 1, 3);
				else
					this.worldObj.setBlockToAir(x, y, z);

				PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(x, y, z, 14483711), new TargetPoint(this.worldObj.provider.dimensionId, (double) x, (double) y, (double) z, 32.0D));
				++this.charges;
				this.markDirty();
				return;
			}
		}

	}

	@Override
	public void readCustomNBT(NBTTagCompound nbttagcompound)
	{
		this.facing = ForgeDirection.getOrientation(nbttagcompound.getInteger("facing"));
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setInteger("facing", this.facing.ordinal());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);
		this.charges = nbttagcompound.getInteger("charges");
		this.power = nbttagcompound.getInteger("power");
		this.essentia = nbttagcompound.getInteger("essentia");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("charges", this.charges);
		nbttagcompound.setInteger("power", this.power);
		nbttagcompound.setInteger("essentia", this.essentia);
	}

	@Override
	public boolean isConnectable(ForgeDirection face)
	{
		return face == this.facing;
	}

	@Override
	public boolean canOutputTo(ForgeDirection face)
	{
		return face == this.facing;
	}

	@Override
	public boolean canInputFrom(ForgeDirection face)
	{
		return false;
	}

	@Override
	public void setSuction(Aspect aspect, int amount)
	{
	}

	@Override
	public boolean renderExtendedTube()
	{
		return false;
	}

	@Override
	public int getMinimumSuction()
	{
		return 0;
	}

	@Override
	public Aspect getSuctionType(ForgeDirection face)
	{
		return null;
	}

	@Override
	public int getSuctionAmount(ForgeDirection face)
	{
		return 0;
	}

	@Override
	public Aspect getEssentiaType(ForgeDirection loc)
	{
		return Aspect.MAGIC;
	}

	@Override
	public int getEssentiaAmount(ForgeDirection loc)
	{
		return this.essentia;
	}

	@Override
	public int takeEssentia(Aspect aspect, int amount, ForgeDirection loc)
	{
		int re = Math.min(this.essentia, amount);
		this.essentia -= re;
		this.markDirty();
		return re;
	}

	@Override
	public int addEssentia(Aspect aspect, int amount, ForgeDirection loc)
	{
		return 0;
	}
}
