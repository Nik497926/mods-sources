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
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.HexUtils;
import thaumcraft.common.tiles.TileResearchTable;

public class PacketAspectPlaceToServer implements IMessage, IMessageHandler<PacketAspectPlaceToServer, IMessage>
{
	private int dim;
	private int playerid;
	private int x;
	private int y;
	private int z;
	Aspect aspect;
	byte q;
	byte r;

	public PacketAspectPlaceToServer()
	{
	}

	public PacketAspectPlaceToServer(EntityPlayer player, byte q, byte r, int x, int y, int z, Aspect aspect)
	{
		this.dim = player.worldObj.provider.dimensionId;
		this.playerid = player.getEntityId();
		this.x = x;
		this.y = y;
		this.z = z;
		this.aspect = aspect;
		this.q = q;
		this.r = r;
	}

	@Override
	public void toBytes(ByteBuf buffer)
	{
		buffer.writeInt(this.dim);
		buffer.writeInt(this.playerid);
		buffer.writeInt(this.x);
		buffer.writeInt(this.y);
		buffer.writeInt(this.z);
		ByteBufUtils.writeUTF8String(buffer, this.aspect == null ? "null" : this.aspect.getTag());
		buffer.writeByte(this.q);
		buffer.writeByte(this.r);
	}

	@Override
	public void fromBytes(ByteBuf buffer)
	{
		this.dim = buffer.readInt();
		this.playerid = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.aspect = Aspect.getAspect(ByteBufUtils.readUTF8String(buffer));
		this.q = buffer.readByte();
		this.r = buffer.readByte();
	}

	@Override
	public IMessage onMessage(PacketAspectPlaceToServer message, MessageContext ctx)
	{
		/* TODO gamerforEA code replace, old code:
		World world = DimensionManager.getWorld(message.dim);
		if (world != null && (ctx.getServerHandler().playerEntity == null || ctx.getServerHandler().playerEntity.getEntityId() == message.playerid))
		{
			Entity player = world.getEntityByID(message.playerid);
			if (player == null)
				return null;
			TileEntity rt = world.getTileEntity(message.x, message.y, message.z);
			if (rt instanceof TileResearchTable)
				((TileResearchTable) rt).placeAspect(message.q, message.r, message.aspect, (EntityPlayer) player);

			return null;
		} */
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

		if (tile.data == null)
			return null;

		HexUtils.Hex hex = new HexUtils.Hex(message.q, message.r);
		ResearchManager.HexEntry hexEntry = tile.data.hexEntries.get(hex.toString());
		if (hexEntry == null)
			return null;

		Aspect aspect = message.aspect;
		if (aspect == null)
		{
			if (hexEntry.type != 2)
				return null;
		}
		else
		{
			if (hexEntry.type != 0)
				return null;

			if (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.getCommandSenderName(), aspect) <= 0 && tile.bonusAspects.getAmount(aspect) <= 0)
				return null;
		}

		tile.placeAspect(message.q, message.r, aspect, player);
		// TODO gamerforEA code end

		return null;
	}
}
