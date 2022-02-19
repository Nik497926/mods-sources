package thaumcraft.common.entities.ai.fluid;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.GolemHelper;
import thaumcraft.common.tiles.TileEssentiaReservoir;
import thaumcraft.common.tiles.TileJarFillable;

public class AIEssentiaEmpty extends EntityAIBase
{
	private EntityGolemBase theGolem;
	private int jarX;
	private int jarY;
	private int jarZ;
	private ForgeDirection markerOrientation;
	private World theWorld;

	public AIEssentiaEmpty(EntityGolemBase par1EntityCreature)
	{
		this.theGolem = par1EntityCreature;
		this.theWorld = par1EntityCreature.worldObj;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute()
	{
		ChunkCoordinates home = this.theGolem.getHomePosition();
		if (this.theGolem.getNavigator().noPath() && this.theGolem.essentia != null && this.theGolem.essentiaAmount != 0)
		{
			ChunkCoordinates jarloc = GolemHelper.findJarWithRoom(this.theGolem);
			if (jarloc == null)
				return false;
			else if (this.theGolem.getDistanceSq(jarloc.posX + 0.5D, jarloc.posY + 0.5D, jarloc.posZ + 0.5D) > 4.0D)
				return false;
			else
			{
				this.jarX = jarloc.posX;
				this.jarY = jarloc.posY;
				this.jarZ = jarloc.posZ;
				return true;
			}
		}
		else
			return false;
	}

	@Override
	public boolean continueExecuting()
	{
		return false;
	}

	@Override
	public void startExecuting()
	{
		TileEntity tile = this.theWorld.getTileEntity(this.jarX, this.jarY, this.jarZ);

		// TODO gamerforEA code start
		if (tile != null && this.theGolem.fake.cantBreak(this.jarX, this.jarY, this.jarZ))
			return;
		// TODO gamerforEA code end

		if (tile instanceof TileJarFillable)
		{
			TileJarFillable jar = (TileJarFillable) tile;
			this.theGolem.essentiaAmount = jar.addToContainer(this.theGolem.essentia, this.theGolem.essentiaAmount);
			if (this.theGolem.essentiaAmount == 0)
				this.theGolem.essentia = null;

			this.theWorld.playSoundAtEntity(this.theGolem, "game.neutral.swim", 0.2F, 1.0F + (this.theWorld.rand.nextFloat() - this.theWorld.rand.nextFloat()) * 0.3F);
			this.theGolem.updateCarried();
			this.theWorld.markBlockForUpdate(this.jarX, this.jarY, this.jarZ);
		}
		else if (tile instanceof TileEssentiaReservoir)
		{
			TileEssentiaReservoir trans = (TileEssentiaReservoir) tile;
			if (trans.getSuctionAmount(trans.facing) > 0 && (trans.getSuctionType(trans.facing) == null || trans.getSuctionType(trans.facing) == this.theGolem.essentia))
			{
				int added = trans.addEssentia(this.theGolem.essentia, this.theGolem.essentiaAmount, trans.facing);
				if (added > 0)
				{
					this.theGolem.essentiaAmount -= added;
					if (this.theGolem.essentiaAmount == 0)
						this.theGolem.essentia = null;

					this.theWorld.playSoundAtEntity(this.theGolem, "game.neutral.swim", 0.2F, 1.0F + (this.theWorld.rand.nextFloat() - this.theWorld.rand.nextFloat()) * 0.3F);
					this.theGolem.updateCarried();
					this.theWorld.markBlockForUpdate(this.jarX, this.jarY, this.jarZ);
				}
			}
		}
		else if (tile instanceof IEssentiaTransport)
			for (Integer side : GolemHelper.getMarkedSides(this.theGolem, tile, (byte) -1))
			{
				IEssentiaTransport trans = (IEssentiaTransport) tile;
				if (trans.canInputFrom(ForgeDirection.getOrientation(side)) && trans.getSuctionAmount(ForgeDirection.getOrientation(side)) > 0 && (trans.getSuctionType(ForgeDirection.getOrientation(side)) == null || trans.getSuctionType(ForgeDirection.getOrientation(side)) == this.theGolem.essentia))
				{
					int added = trans.addEssentia(this.theGolem.essentia, this.theGolem.essentiaAmount, ForgeDirection.getOrientation(side));
					if (added > 0)
					{
						this.theGolem.essentiaAmount -= added;
						if (this.theGolem.essentiaAmount == 0)
							this.theGolem.essentia = null;

						this.theWorld.playSoundAtEntity(this.theGolem, "game.neutral.swim", 0.2F, 1.0F + (this.theWorld.rand.nextFloat() - this.theWorld.rand.nextFloat()) * 0.3F);
						this.theGolem.updateCarried();
						this.theWorld.markBlockForUpdate(this.jarX, this.jarY, this.jarZ);
						break;
					}
				}
			}
	}
}
