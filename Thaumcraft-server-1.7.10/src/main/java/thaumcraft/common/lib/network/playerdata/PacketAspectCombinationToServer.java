package thaumcraft.common.lib.network.playerdata;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.container.ContainerResearchTable;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.tiles.TileResearchTable;

public class PacketAspectCombinationToServer
		implements IMessage, IMessageHandler<PacketAspectCombinationToServer, IMessage>
{
	private int dim;
	private int playerid;
	private int x;
	private int y;
	private int z;
	Aspect aspect1;
	Aspect aspect2;
	boolean ab1;
	boolean ab2;

	public PacketAspectCombinationToServer()
	{
	}

	public PacketAspectCombinationToServer(EntityPlayer player, int x, int y, int z, Aspect aspect1, Aspect aspect2, boolean ab1, boolean ab2, boolean ret)
	{
		this.dim = player.worldObj.provider.dimensionId;
		this.playerid = player.getEntityId();
		this.x = x;
		this.y = y;
		this.z = z;
		this.aspect1 = aspect1;
		this.aspect2 = aspect2;
		this.ab1 = ab1;
		this.ab2 = ab2;
	}

	@Override
	public void toBytes(ByteBuf buffer)
	{
		buffer.writeInt(this.dim);
		buffer.writeInt(this.playerid);
		buffer.writeInt(this.x);
		buffer.writeInt(this.y);
		buffer.writeInt(this.z);
		ByteBufUtils.writeUTF8String(buffer, this.aspect1.getTag());
		ByteBufUtils.writeUTF8String(buffer, this.aspect2.getTag());
		buffer.writeBoolean(this.ab1);
		buffer.writeBoolean(this.ab2);
	}

	@Override
	public void fromBytes(ByteBuf buffer)
	{
		this.dim = buffer.readInt();
		this.playerid = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.aspect1 = Aspect.getAspect(ByteBufUtils.readUTF8String(buffer));
		this.aspect2 = Aspect.getAspect(ByteBufUtils.readUTF8String(buffer));
		this.ab1 = buffer.readBoolean();
		this.ab2 = buffer.readBoolean();
	}

	@Override
	public IMessage onMessage(PacketAspectCombinationToServer message, MessageContext ctx)
	{
		/* TODO gamerforEA code replace, old code:
		World world = DimensionManager.getWorld(message.dim);
		if (world != null && (ctx.getServerHandler().playerEntity == null || ctx.getServerHandler().playerEntity.getEntityId() == message.playerid))
		{
			Entity player = world.getEntityByID(message.playerid);
			if (player instanceof EntityPlayer && message.aspect1 != null && message.aspect1 != null)
			{
				Aspect combo = ResearchManager.getCombinationResult(message.aspect1, message.aspect2);
				if ((Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.getCommandSenderName(), message.aspect1) > 0 || message.ab1) && (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.getCommandSenderName(), message.aspect2) > 0 || message.ab2))
				{
					TileEntity rt = player.worldObj.getTileEntity(message.x, message.y, message.z);
					if (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.getCommandSenderName(), message.aspect1) <= 0 && message.ab1)
					{
						if (rt instanceof TileResearchTable)
						{
							((TileResearchTable) rt).bonusAspects.remove(message.aspect1, 1);
							player.worldObj.markBlockForUpdate(message.x, message.y, message.z);
							rt.markDirty();
						}
					}
					else
					{
						Thaumcraft.proxy.playerKnowledge.addAspectPool(player.getCommandSenderName(), message.aspect1, (short) -1);
						PacketHandler.INSTANCE.sendTo(new PacketAspectPool(message.aspect1.getTag(), (short) 0, Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.getCommandSenderName(), message.aspect1)), (EntityPlayerMP) player);
					}

					if (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.getCommandSenderName(), message.aspect2) <= 0 && message.ab2)
					{
						if (rt instanceof TileResearchTable)
						{
							((TileResearchTable) rt).bonusAspects.remove(message.aspect2, 1);
							player.worldObj.markBlockForUpdate(message.x, message.y, message.z);
							rt.markDirty();
						}
					}
					else
					{
						Thaumcraft.proxy.playerKnowledge.addAspectPool(player.getCommandSenderName(), message.aspect2, (short) -1);
						PacketHandler.INSTANCE.sendTo(new PacketAspectPool(message.aspect2.getTag(), (short) 0, Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.getCommandSenderName(), message.aspect2)), (EntityPlayerMP) player);
					}

					if (combo != null)
						ScanManager.checkAndSyncAspectKnowledge((EntityPlayer) player, combo, 1);

					ResearchManager.scheduleSave((EntityPlayer) player);
				}
			}

			return null;
		} */
		Aspect aspect1 = message.aspect1;
		Aspect aspect2 = message.aspect2;
		if (aspect1 == null || aspect2 == null)
			return null;

		EntityPlayerMP player = ctx.getServerHandler().playerEntity;
		if (player == null || player.getEntityId() != message.playerid)
			return null;

		World world = player.worldObj;
		if (world == null || world.provider.dimensionId != message.dim)
			return null;

		Container openContainer = player.openContainer;
		if (!(openContainer instanceof ContainerResearchTable))
			return null;

		TileResearchTable tile = ((ContainerResearchTable) openContainer).tileEntity;
		if (tile == null || tile.xCoord != message.x || tile.yCoord != message.y || tile.zCoord != message.z)
			return null;

		boolean bonus1 = tile.bonusAspects.getAmount(aspect1) > 0;
		boolean bonus2 = tile.bonusAspects.getAmount(aspect2) > 0;
		if (bonus1 != message.ab1 || bonus2 != message.ab2)
			return null;

		String playerName = player.getCommandSenderName();
		short aspectPoolFor1 = Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(playerName, aspect1);
		short aspectPoolFor2 = Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(playerName, aspect2);
		if ((aspectPoolFor1 > 0 || bonus1) && (aspectPoolFor2 > 0 || bonus2))
		{
			if (aspectPoolFor1 <= 0)
			{
				tile.bonusAspects.remove(aspect1, 1);
				world.markBlockForUpdate(message.x, message.y, message.z);
				tile.markDirty();
			}
			else
			{
				Thaumcraft.proxy.playerKnowledge.addAspectPool(playerName, aspect1, (short) -1);

				aspectPoolFor1 = Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(playerName, aspect1);
				PacketHandler.INSTANCE.sendTo(new PacketAspectPool(aspect1.getTag(), (short) 0, aspectPoolFor1), player);
			}

			if (aspectPoolFor2 <= 0)
			{
				tile.bonusAspects.remove(aspect2, 1);
				world.markBlockForUpdate(message.x, message.y, message.z);
				tile.markDirty();
			}
			else
			{
				Thaumcraft.proxy.playerKnowledge.addAspectPool(playerName, aspect2, (short) -1);

				aspectPoolFor2 = Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(playerName, aspect2);
				PacketHandler.INSTANCE.sendTo(new PacketAspectPool(aspect2.getTag(), (short) 0, aspectPoolFor2), player);
			}

			Aspect comboAspect = ResearchManager.getCombinationResult(aspect1, aspect2);
			if (comboAspect != null)
				ScanManager.checkAndSyncAspectKnowledge(player, comboAspect, 1);

			ResearchManager.scheduleSave(player);
		}
		// TODO gamerforEA code end

		return null;
	}
}
