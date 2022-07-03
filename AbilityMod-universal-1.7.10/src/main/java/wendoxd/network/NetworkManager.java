package wendoxd.network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.WorldServer;
import wendoxd.modules.Ability;
import wendoxd.utils.Util;

public class NetworkManager {

	private FMLEventChannel channel;
	private IPacketHandler packetHandler;

	private NetworkManager(IPacketHandler pktHandler) {
		packetHandler = pktHandler;
		channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(pktHandler.getChannel());
		channel.register(this);
	}

	public static NetworkManager registerPacketHandler(IPacketHandler packetHandler) {
		return new NetworkManager(packetHandler);
	}

	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent e) {
		if (e.packet.channel().equals(packetHandler.getChannel())) {
			ByteBuf buf = e.packet.payload();
			NetHandlerPlayServer netServerHandler = (NetHandlerPlayServer) e.handler;
			EntityPlayerMP player = netServerHandler.playerEntity;
			packetHandler.handleServerSide(new PacketBuffer(buf), buf.readByte(), (WorldServer) player.worldObj,
					player);
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onClientPacket(ClientCustomPacketEvent e) {
		if (e.packet.channel().equals(packetHandler.getChannel())) {
			ByteBuf buf = e.packet.payload();
			Minecraft mc = Minecraft.getMinecraft();
			EntityClientPlayerMP player = mc.thePlayer;
			packetHandler.handleClientSide(new PacketBuffer(buf), buf.readByte(), mc, (WorldClient) player.worldObj,
					player);
		}
	}

	public void sendToServer(FMLProxyPacket packet) {
		channel.sendToServer(packet);
	}

	public void sendToAll(FMLProxyPacket packet) {
		channel.sendToAll(packet);
	}

	public void sendTo(FMLProxyPacket packet, EntityPlayerMP player) {
		channel.sendTo(packet, player);
	}

	public void sendToAllAround(FMLProxyPacket packet, double x, double y, double z, double range, int dimension) {
		channel.sendToAllAround(packet, new TargetPoint(dimension, x, y, z, range));
	}

	public void sendToDimension(FMLProxyPacket packet, int id) {
		channel.sendToDimension(packet, id);
	}

	public <T> FMLProxyPacket createPacket(int id, T... data) {
		return createPacket(Unpooled.buffer(32), id, data);
	}

	public <T> FMLProxyPacket createPacket(ByteBuf buf, int id) {
		buf.writeByte(id);
		return new FMLProxyPacket(buf, packetHandler.getChannel());
	}

	public <T> FMLProxyPacket createPacket(ByteBuf buf, int id, T... data) {
		buf.writeByte(id);

		for (T value : data) {
			writeData(buf, value);
		}

		return new FMLProxyPacket(buf, packetHandler.getChannel());
	}

	public static UUID readUUID(ByteBuf buf) {
		return new UUID(buf.readLong(), buf.readLong());
	}

	private <T> void writeData(ByteBuf buf, T value) {
		if (value instanceof Byte) {
			buf.writeByte((Byte) value);
		} else if (value instanceof ArrayList) {
			ArrayList<Ability> abilities = (ArrayList<Ability>) value;
			if (abilities.size() == 0)
				return;
			buf.writeInt(abilities.size());
			for (Ability a : abilities) {
				ByteBufUtils.writeUTF8String(buf, a.name);
				ByteBufUtils.writeUTF8String(buf, a.desc);
				buf.writeInt(a.lvl);
				buf.writeInt(a.getSize());
				a.costs.forEach(w -> {
					buf.writeInt(w);
				});
				a.modifiers.forEach(w -> {
					buf.writeDouble(w);
				});
			}
		} else if (value instanceof Boolean) {
			buf.writeBoolean((Boolean) value);
		} else if (value instanceof Short) {
			buf.writeShort((Short) value);
		} else if (value instanceof Integer) {
			buf.writeInt((Integer) value);
		} else if (value instanceof Float) {
			buf.writeFloat((Float) value);
		} else if (value instanceof Double) {
			buf.writeDouble((Double) value);
		} else if (value instanceof Long) {
			buf.writeLong((Long) value);
		} else if (value instanceof Character) {
			buf.writeChar((Character) value);
		} else if (value instanceof String) {
			ByteBufUtils.writeUTF8String(buf, (String) value);
		} else if (value instanceof boolean[]) {
			boolean[] array = (boolean[]) value;
			buf.writeInt(array.length);
			for (boolean i : array) {
				buf.writeBoolean(i);
			}
		} else if (value instanceof byte[]) {
			byte[] bytes = (byte[]) value;
			buf.writeInt(bytes.length);
			for (byte b : bytes) {
				buf.writeInt(b);
			}
		} else if (value instanceof short[]) {
			short[] array = (short[]) value;
			buf.writeInt(array.length);
			for (short i : array) {
				buf.writeShort(i);
			}
		} else if (value instanceof int[]) {
			int[] array = (int[]) value;
			buf.writeInt(array.length);
			for (int i : array) {
				buf.writeInt(i);
			}
		} else if (value instanceof long[]) {
			long[] array = (long[]) value;
			buf.writeInt(array.length);
			for (long i : array) {
				buf.writeLong(i);
			}
		} else if (value instanceof float[]) {
			float[] array = (float[]) value;
			buf.writeInt(array.length);
			for (float i : array) {
				buf.writeFloat(i);
			}
		} else if (value instanceof double[]) {
			double[] array = (double[]) value;
			buf.writeInt(array.length);
			for (double i : array) {
				buf.writeDouble(i);
			}
		} else if (value instanceof char[]) {
			char[] array = (char[]) value;
			buf.writeInt(array.length);
			for (char i : array) {
				buf.writeChar(i);
			}
		} else if (value instanceof String[]) {
			String[] array = (String[]) value;
			buf.writeInt(array.length);
			for (String i : array) {
				ByteBufUtils.writeUTF8String(buf, i);
			}
		} else if (value instanceof ItemStack) {
			ByteBufUtils.writeItemStack(buf, (ItemStack) value);
		} else if (value instanceof NBTTagCompound) {
			ByteBufUtils.writeTag(buf, (NBTTagCompound) value);
		} else if (value instanceof UUID) {
			UUID uuid = (UUID) value;
			buf.writeLong(uuid.getMostSignificantBits());
			buf.writeLong(uuid.getLeastSignificantBits());
		} else if (value instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>) value;
			Iterator<?> iterator = collection.iterator();
			buf.writeInt(collection.size());
			while (iterator.hasNext()) {
				writeData(buf, iterator.next());
			}
		} else if (value instanceof ICustomValue) {
			ICustomValue val = (ICustomValue) value;
			val.send(buf);
		}
	}

	private <T> void writeArray(ByteBuf buf, T[] array) {
		buf.writeInt(array.length);
		for (T value : array) {
			writeData(buf, value);
		}
	}
}
