package thaumcraft.common.tiles;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.world.dim.TeleporterThaumcraft;

import java.util.List;

public class TileEldritchPortal extends TileEntity
{
	public int opencount = -1;
	private int count = 0;

	@Override
	public boolean canUpdate()
	{
		return true;
	}

	@Override
	public double getMaxRenderDistanceSquared()
	{
		return 9216.0D;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox()
	{
		return AxisAlignedBB.getBoundingBox(this.xCoord - 1, this.yCoord - 1, this.zCoord - 1, this.xCoord + 2, this.yCoord + 2, this.zCoord + 2);
	}

	@Override
	public void updateEntity()
	{
		++this.count;
		if (this.worldObj.isRemote && (this.count % 250 == 0 || this.count == 0))
			this.worldObj.playSound(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, "thaumcraft:evilportal", 1.0F, 1.0F, false);

		if (this.worldObj.isRemote && this.opencount < 30)
			++this.opencount;

		if (!this.worldObj.isRemote && this.count % 5 == 0)
		{
			List ents = this.worldObj.getEntitiesWithinAABB(EntityPlayerMP.class, AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 1, this.zCoord + 1).expand(0.5D, 1.0D, 0.5D));
			if (ents.size() > 0)
				for (Object e : ents)
				{
					EntityPlayerMP player = (EntityPlayerMP) e;
					if (player.ridingEntity == null && player.riddenByEntity == null)
					{
						MinecraftServer mServer = FMLCommonHandler.instance().getMinecraftServerInstance();
						if (player.timeUntilPortal > 0)
							player.timeUntilPortal = 100;
						else if (player.dimension != Config.dimensionOuterId)
						{
							player.timeUntilPortal = 100;
							WorldServer worldServer = mServer.worldServerForDimension(Config.dimensionOuterId);
							// TODO gamerforEA code start
							if (worldServer != null)
								// TODO gamerforEA code end
								player.mcServer.getConfigurationManager().transferPlayerToDimension(player, Config.dimensionOuterId, new TeleporterThaumcraft(worldServer));
							if (!ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), "ENTEROUTER"))
							{
								PacketHandler.INSTANCE.sendTo(new PacketResearchComplete("ENTEROUTER"), player);
								Thaumcraft.proxy.getResearchManager().completeResearch(player, "ENTEROUTER");
							}
						}
						else
						{
							player.timeUntilPortal = 100;
							WorldServer worldServer = mServer.worldServerForDimension(0);
							// TODO gamerforEA code start
							if (worldServer != null)
								// TODO gamerforEA code end
								player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterThaumcraft(worldServer));
						}
					}
				}
		}

	}
}
