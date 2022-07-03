package wendoxdc.network;

import java.util.ArrayList;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import wendoxdc.events.ClientEvents;
import wendoxdc.events.ServerEvents;
import wendoxdc.gui.GuiEntry;
import wendoxdc.init.CaptureMod;
import wendoxdc.modules.CaptUtils;
import wendoxdc.modules.Capture;
import wendoxdc.nbtutils.CustomFileNBT;
import wendoxdc.utils.Util;

public class PacketHandler implements IPacketHandler {

	@SideOnly(Side.CLIENT)
	public void handleClientSide(PacketBuffer buf, byte id, Minecraft mc, WorldClient world,
			EntityClientPlayerMP player) {
		if (id == 0) {
			int size = buf.readInt();
			ClientEvents.captsInWorld.clear();
			for (int i = 0; i < size; i++) {
				String name = ByteBufUtils.readUTF8String(buf);
				String target = ByteBufUtils.readUTF8String(buf);
				int x = buf.readInt();
				int z = buf.readInt();
				int radius = buf.readInt();
				int color = buf.readInt();
				int dimId = buf.readInt();
				int maxTimeCapt = buf.readInt();
				int cooldown = buf.readInt();
				int currentCooldown = buf.readInt();
				int timeCapt = buf.readInt();
				int minOnline = buf.readInt();
				int radiusCapt = buf.readInt();
				Capture fuck = Capture.newCapt(name, x, z, radius, color, dimId, maxTimeCapt, cooldown, minOnline,
						radiusCapt, 0, 0);
				fuck.timeCapt = timeCapt;
				fuck.currentCooldown = currentCooldown;
				fuck.targetPlayer = target;
				fuck.onlineItsOk = buf.readBoolean();
				ClientEvents.captsInWorld.add(fuck);
			}
		}
		if (id == 2) {
			CaptureMod.isOP = buf.readBoolean();
		}
	}

	public void handleServerSide(PacketBuffer buf, byte id, WorldServer world, EntityPlayerMP player) {
		if (!CaptureMod.SERVER)
			return;
		if (Util.isOP(player)) {
			if (id == 0) {
				String name = ByteBufUtils.readUTF8String(buf);
				int x = buf.readInt();
				int z = buf.readInt();
				int radius = buf.readInt();
				int color = buf.readInt();
				int cooldown = buf.readInt();
				int minOnline = buf.readInt();
				int radiusCapt = buf.readInt();
				int maxHeight = buf.readInt();
				int itemsDrop = buf.readInt();
				int maxTimeCapt = buf.readInt();
				Capture.captures.add(Capture.newCapt(name, x, z, radius, color, player.dimension, maxTimeCapt, cooldown,
						minOnline, radiusCapt, maxHeight, itemsDrop));
			}
			if (id == 1) {
				try {
					String name = ByteBufUtils.readUTF8String(buf);
					CaptUtils.forName(Capture.captures, name).items.clear();
					CustomFileNBT.hasWriteUpdate = true;
				} catch (Exception e) {
					player.addChatComponentMessage(new ChatComponentText("ааа фак зе полис"));
					e.printStackTrace();
				}
			}
			if (id == 2) {
				String name = ByteBufUtils.readUTF8String(buf);
				try {
					if (player.getHeldItem() == null)
						throw new IllegalArgumentException("ты пидор епта тебе въебать?");
					CaptUtils.forName(Capture.captures, name).items.add(player.getHeldItem());
					CustomFileNBT.hasWriteUpdate = true;
				} catch (Exception e) {
					player.addChatComponentMessage(new ChatComponentText("уродец возьми в руки айтем"));
					e.printStackTrace();
				}
			}
			if (id == 3) {
				String name = ByteBufUtils.readUTF8String(buf);
				try {
					Capture.captures.remove(CaptUtils.forName(Capture.captures, name));
					CustomFileNBT.hasWriteUpdate = true;
				} catch (Exception e) {
					player.addChatComponentMessage(new ChatComponentText("уродец возьми в руки айтем"));
					e.printStackTrace();
				}
			}
		}
	}

	public String getChannel() {
		return "wendoxcaptures";
	}
}
