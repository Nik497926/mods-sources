package thaumcraft.common.tiles;

import com.gamerforea.thaumcraft.EventConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.visnet.TileVisNode;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;

import java.awt.*;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

public class TileVisRelay extends TileVisNode implements IWandable
{
	public short orientation = 1;
	public byte color = -1;
	public static HashMap<Integer, WeakReference<TileVisRelay>> nearbyPlayers = new HashMap<>();
	protected Object beam1 = null;
	protected int pulse;
	public float pRed = 0.5F;
	public float pGreen = 0.5F;
	public float pBlue = 0.5F;
	public static final int[] colors = { 16777086, 16727041, '郿', 'ꀀ', 15650047, 5592439 };
	protected int px;
	protected int py;
	protected int pz;
	protected boolean parentLoaded = false;

	// TODO gamerforEA code start
	private int timer;

	public void reset()
	{
		this.timer = 0;
		this.removeThisNode();
		this.nodeRefresh = true;
		this.markDirty();
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}
	// TODO gamerforEA code end

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox()
	{
		return AxisAlignedBB.getBoundingBox((double) this.xCoord, (double) this.yCoord, (double) this.zCoord, (double) (this.xCoord + 1), (double) (this.yCoord + 1), (double) (this.zCoord + 1));
	}

	@Override
	public byte getAttunement()
	{
		return this.color;
	}

	@Override
	public int getRange()
	{
		return 8;
	}

	@Override
	public boolean isSource()
	{
		return false;
	}

	@Override
	public void parentChanged()
	{
		if (this.worldObj != null && this.worldObj.isRemote)
			this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);

	}

	@Override
	public void invalidate()
	{
		this.beam1 = null;
		super.invalidate();
	}

	@Override
	public void updateEntity()
	{
		this.drawEffect();
		super.updateEntity();
		if (!this.worldObj.isRemote)
		{
			if (this.nodeCounter % 20 == 0)
			{
				List<EntityPlayer> players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double) this.xCoord, (double) this.yCoord, (double) this.zCoord, (double) (this.xCoord + 1), (double) (this.yCoord + 1), (double) (this.zCoord + 1)).expand(5.0D, 5.0D, 5.0D));
				if (players != null && players.size() > 0)
					for (EntityPlayer player : players)
					{
						int entityId = player.getEntityId();
						WeakReference<TileVisRelay> tileRef = nearbyPlayers.get(entityId);
						TileVisRelay tile = tileRef == null ? null : tileRef.get();
						if (tile == null || tile.getDistanceFrom(player.posX, player.posY, player.posZ) >= this.getDistanceFrom(player.posX, player.posY, player.posZ))
							nearbyPlayers.put(entityId, new WeakReference<>(this));
					}
			}

			// TODO gamerforEA code start
			this.timer++;
			if (EventConfig.visRelayUpdatePeriod > 0 && this.timer >= EventConfig.visRelayUpdatePeriod)
				this.reset();
			// TODO gamerforEA code end
		}
	}

	protected void drawEffect()
	{
		if (this.worldObj.isRemote)
		{
			if (this.parentLoaded)
			{
				if (this.px == 0 && this.py == 0 && this.pz == 0)
					this.setParent(null);
				else
				{
					TileEntity tile = this.getWorldObj().getTileEntity(this.xCoord - this.px, this.yCoord - this.py, this.zCoord - this.pz);
					if (tile instanceof TileVisNode)
						this.setParent(new WeakReference<>((TileVisNode) tile));
				}

				this.parentLoaded = false;
				this.parentChanged();
			}

			if (VisNetHandler.isNodeValid(this.getParent()))
			{
				TileVisNode tile = this.getParent().get();
				double xx = (double) tile.xCoord + 0.5D;
				double yy = (double) tile.yCoord + 0.5D;
				double zz = (double) tile.zCoord + 0.5D;
				ForgeDirection d1 = ForgeDirection.UNKNOWN;
				if (tile instanceof TileVisRelay)
					d1 = ForgeDirection.getOrientation(((TileVisRelay) tile).orientation);

				ForgeDirection d2 = ForgeDirection.getOrientation(this.orientation);
				this.beam1 = Thaumcraft.proxy.beamPower(this.worldObj, xx - (double) d1.offsetX * 0.05D, yy - (double) d1.offsetY * 0.05D, zz - (double) d1.offsetZ * 0.05D, (double) this.xCoord + 0.5D - (double) d2.offsetX * 0.05D, (double) this.yCoord + 0.5D - (double) d2.offsetY * 0.05D, (double) this.zCoord + 0.5D - (double) d2.offsetZ * 0.05D, this.pRed, this.pGreen, this.pBlue, this.pulse > 0, this.beam1);
			}

			if (this.pRed < 1.0F)
				this.pRed += 0.025F;

			if (this.pRed > 1.0F)
				this.pRed = 1.0F;

			if (this.pGreen < 1.0F)
				this.pGreen += 0.025F;

			if (this.pGreen > 1.0F)
				this.pGreen = 1.0F;

			if (this.pBlue < 1.0F)
				this.pBlue += 0.025F;

			if (this.pBlue > 1.0F)
				this.pBlue = 1.0F;
		}

		if (this.pulse > 0)
			--this.pulse;

	}

	@Override
	public void triggerConsumeEffect(Aspect aspect)
	{
		this.addPulse(aspect);
	}

	protected void addPulse(Aspect aspect)
	{
		int c = -1;
		if (aspect == Aspect.AIR)
			c = 0;
		else if (aspect == Aspect.FIRE)
			c = 1;
		else if (aspect == Aspect.WATER)
			c = 2;
		else if (aspect == Aspect.EARTH)
			c = 3;
		else if (aspect == Aspect.ORDER)
			c = 4;
		else if (aspect == Aspect.ENTROPY)
			c = 5;

		if (c >= 0 && this.pulse == 0)
		{
			this.pulse = 5;
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 0, c);
		}

	}

	@Override
	public boolean receiveClientEvent(int i, int j)
	{
		if (i != 0)
			return super.receiveClientEvent(i, j);
		if (this.worldObj.isRemote)
		{
			Color c = new Color(colors[j]);
			this.pulse = 5;
			this.pRed = (float) c.getRed() / 255.0F;
			this.pGreen = (float) c.getGreen() / 255.0F;
			this.pBlue = (float) c.getBlue() / 255.0F;

			for (WeakReference<TileVisNode> vr = this.getParent(); VisNetHandler.isNodeValid(vr) && vr.get() instanceof TileVisRelay && ((TileVisRelay) vr.get()).pulse == 0; vr = vr.get().getParent())
			{
				TileVisNode tile = vr.get();
				((TileVisRelay) tile).pRed = this.pRed;
				((TileVisRelay) tile).pGreen = this.pGreen;
				((TileVisRelay) tile).pBlue = this.pBlue;
				((TileVisRelay) tile).pulse = 5;
			}
		}

		return true;
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbttagcompound)
	{
		super.readCustomNBT(nbttagcompound);
		this.orientation = nbttagcompound.getShort("orientation");
		this.color = nbttagcompound.getByte("color");
		this.px = nbttagcompound.getByte("px");
		this.py = nbttagcompound.getByte("py");
		this.pz = nbttagcompound.getByte("pz");
		this.parentLoaded = true;
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbttagcompound)
	{
		super.writeCustomNBT(nbttagcompound);
		nbttagcompound.setShort("orientation", this.orientation);
		nbttagcompound.setByte("color", this.color);
		if (VisNetHandler.isNodeValid(this.getParent()))
		{
			TileVisNode tile = this.getParent().get();
			nbttagcompound.setByte("px", (byte) (this.xCoord - tile.xCoord));
			nbttagcompound.setByte("py", (byte) (this.yCoord - tile.yCoord));
			nbttagcompound.setByte("pz", (byte) (this.zCoord - tile.zCoord));
		}
		else
		{
			nbttagcompound.setByte("px", (byte) 0);
			nbttagcompound.setByte("py", (byte) 0);
			nbttagcompound.setByte("pz", (byte) 0);
		}
	}

	@Override
	public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
	{
		if (!this.worldObj.isRemote)
		{
			++this.color;
			if (this.color > 5)
				this.color = -1;

			this.removeThisNode();
			this.nodeRefresh = true;
			this.markDirty();
			world.markBlockForUpdate(x, y, z);
			world.playSoundEffect((double) x, (double) y, (double) z, "thaumcraft:crystal", 0.2F, 1.0F);
		}

		return 0;
	}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
	{
		return null;
	}

	@Override
	public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count)
	{
	}

	@Override
	public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count)
	{
	}
}
