package thaumcraft.common.entities.ai.interact;

import com.gamerforea.thaumcraft.EventConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.GolemHelper;
import thaumcraft.common.entities.golems.Marker;

import java.util.Iterator;

public class AIUseItem extends EntityAIBase
{
	private EntityGolemBase theGolem;
	private int xx;
	private int yy;
	private int zz;
	private float movementSpeed;
	private float distance;
	private World theWorld;
	private Block block = Blocks.air;
	private int blockMd = 0;
	FakePlayer player;
	private int count = 0;
	private int color = -1;
	ItemInWorldManager im;
	int nextTick = 0;

	public AIUseItem(EntityGolemBase par1EntityCreature)
	{
		this.theGolem = par1EntityCreature;
		this.theWorld = par1EntityCreature.worldObj;
		this.setMutexBits(3);
		this.distance = MathHelper.ceiling_float_int(this.theGolem.getRange() / 3.0F);
		if (this.theWorld instanceof WorldServer)
			// TODO gamerforEA code replace, old code: this.player = FakePlayerFactory.get((WorldServer) this.theWorld, new GameProfile(null, "FakeThaumcraftGolem"));
			this.player = this.theGolem.fake.getPlayer();
		// TODO gamerforEA code end

		try
		{
			this.nextTick = this.theGolem.ticksExisted + this.theWorld.rand.nextInt(6);
		}
		catch (Exception ignored)
		{
		}
	}

	@Override
	public boolean shouldExecute()
	{
		boolean ignoreItem = false;
		ChunkCoordinates home = this.theGolem.getHomePosition();
		ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
		int cX = home.posX - facing.offsetX;
		int cY = home.posY - facing.offsetY;
		int cZ = home.posZ - facing.offsetZ;
		TileEntity tile = this.theGolem.worldObj.getTileEntity(cX, cY, cZ);
		if (!(tile instanceof IInventory))
			ignoreItem = true;

		int d = 5 - this.theGolem.ticksExisted;
		if (d < 1)
			d = 1;

		if ((this.theGolem.itemCarried != null || ignoreItem) && this.theGolem.ticksExisted >= this.nextTick && this.theGolem.getNavigator().noPath())
		{
			this.nextTick = this.theGolem.ticksExisted + d * 3;
			return this.findSomething();
		}
		else
			return false;
	}

	@Override
	public boolean continueExecuting()
	{
		return this.theWorld.getBlock(this.xx, this.yy, this.zz) == this.block && this.theWorld.getBlockMetadata(this.xx, this.yy, this.zz) == this.blockMd && this.count-- > 0 && !this.theGolem.getNavigator().noPath();
	}

	@Override
	public void updateTask()
	{
		this.theGolem.getLookHelper().setLookPosition(this.xx + 0.5D, this.yy + 0.5D, this.zz + 0.5D, 30.0F, 30.0F);
		double dist = this.theGolem.getDistanceSq(this.xx + 0.5D, this.yy + 0.5D, this.zz + 0.5D);
		if (dist <= 4.0D)
			this.click();

	}

	@Override
	public void resetTask()
	{
		this.count = 0;
		this.theGolem.getNavigator().clearPathEntity();
	}

	@Override
	public void startExecuting()
	{
		this.count = 200;

		// TODO gamerforEA code start
		if (EventConfig.golemAiOptimize && this.theGolem.getDistanceSq(this.xx + 0.5, this.yy + 0.5, this.zz + 0.5) <= 1)
			return;
		// TODO gamerforEA code end

		this.theGolem.getNavigator().tryMoveToXYZ(this.xx + 0.5, this.yy + 0.5, this.zz + 0.5, this.theGolem.getAIMoveSpeed());
	}

	void click()
	{
		ChunkCoordinates home = this.theGolem.getHomePosition();
		ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
		int cX = home.posX - facing.offsetX;
		int cY = home.posY - facing.offsetY;
		int cZ = home.posZ - facing.offsetZ;
		TileEntity tile = this.theGolem.worldObj.getTileEntity(cX, cY, cZ);
		boolean ignoreItem = !(tile instanceof IInventory);

		this.player.setPositionAndRotation(this.theGolem.posX, this.theGolem.posY, this.theGolem.posZ, this.theGolem.rotationYaw, this.theGolem.rotationPitch);
		this.player.setCurrentItemOrArmor(0, this.theGolem.itemCarried);
		this.player.setSneaking(this.theGolem.getToggles()[2]);
		Iterator<Integer> i$ = GolemHelper.getMarkedSides(this.theGolem, this.xx, this.yy, this.zz, this.theGolem.worldObj.provider.dimensionId, (byte) this.color).iterator();
		if (i$.hasNext())
		{
			Integer side = i$.next();
			int x = 0;
			int y = 0;
			int z = 0;
			if (this.theGolem.worldObj.isAirBlock(this.xx, this.yy, this.zz))
			{
				x = ForgeDirection.getOrientation(side).getOpposite().offsetX;
				y = ForgeDirection.getOrientation(side).getOpposite().offsetY;
				z = ForgeDirection.getOrientation(side).getOpposite().offsetZ;
			}

			if (this.im == null)
				this.im = new ItemInWorldManager(this.theGolem.worldObj);

			if (this.theGolem.itemCarried == null && !ignoreItem)
				this.resetTask();
			else
				try
				{
					if (this.theGolem.getToggles()[1])
					{
						this.theGolem.startLeftArmTimer();
						this.im.onBlockClicked(this.xx + x, this.yy + y, this.zz + z, side);
					}
					else if (this.im.activateBlockOrUseItem(this.player, this.theGolem.worldObj, this.theGolem.itemCarried, this.xx + x, this.yy + y, this.zz + z, side, 0.5F, 0.5F, 0.5F))
						this.theGolem.startRightArmTimer();

					this.theGolem.itemCarried = this.player.getCurrentEquippedItem();
					if (this.theGolem.itemCarried.stackSize <= 0)
						this.theGolem.itemCarried = null;

					for (int a = 1; a < this.player.inventory.mainInventory.length; ++a)
					{
						if (this.player.inventory.getStackInSlot(a) != null)
						{
							if (this.theGolem.itemCarried == null)
								this.theGolem.itemCarried = this.player.inventory.getStackInSlot(a).copy();
							else
								this.player.dropPlayerItemWithRandomChoice(this.player.inventory.getStackInSlot(a), false);

							this.player.inventory.setInventorySlotContents(a, null);
						}
					}

					this.theGolem.updateCarried();
					this.resetTask();
				}
				catch (Exception var14)
				{
					this.resetTask();
				}
		}
	}

	boolean findSomething()
	{
		for (byte col : this.theGolem.getColorsMatching(this.theGolem.itemCarried))
		{
			for (Marker marker : this.theGolem.getMarkers())
			{
				if ((marker.color == col || col == -1) && (!this.theGolem.getToggles()[0] || this.theGolem.worldObj.isAirBlock(marker.x, marker.y, marker.z)) && (this.theGolem.getToggles()[0] || !this.theGolem.worldObj.isAirBlock(marker.x, marker.y, marker.z)))
				{
					ForgeDirection opp = ForgeDirection.getOrientation(marker.side);
					if (this.theGolem.worldObj.isAirBlock(marker.x + opp.offsetX, marker.y + opp.offsetY, marker.z + opp.offsetZ))
					{
						this.color = col;
						this.xx = marker.x;
						this.yy = marker.y;
						this.zz = marker.z;
						this.block = this.theWorld.getBlock(this.xx, this.yy, this.zz);
						this.blockMd = this.theWorld.getBlockMetadata(this.xx, this.yy, this.zz);
						return true;
					}
				}
			}
		}

		return false;
	}
}
