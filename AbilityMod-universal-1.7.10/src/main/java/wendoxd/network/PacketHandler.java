package wendoxd.network;

import java.util.ArrayList;
import java.util.HashMap;

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
import net.minecraft.item.ItemSword;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import wendoxd.events.ClientEvents;
import wendoxd.events.ServerEvents;
import wendoxd.extendedplayer.ExtendedPlayer;
import wendoxd.gui.GuiAbilities;
import wendoxd.init.AbilityMod;
import wendoxd.modules.Ability;
import wendoxd.utils.Util;

public class PacketHandler implements IPacketHandler {

	@SideOnly(Side.CLIENT)
	public void handleClientSide(PacketBuffer buf, byte id, Minecraft mc, WorldClient world,
			EntityClientPlayerMP player) {
		if (id == 0) {
			ClientEvents.abilities.clear();
			ClientEvents.abilities.addAll(readAbilities(buf));
		}
		if (id == 1) {
			ClientEvents.unlockedAbilities.clear();
			ClientEvents.unlockedAbilities.addAll(readAbilities(buf));
			if (mc.currentScreen != null && mc.currentScreen instanceof GuiAbilities) {
				mc.displayGuiScreen(new GuiAbilities());
				GuiAbilities.timeInGui = 75;
			}
			boolean speed = false, speedinwater = false, jump = false, distance = false;
			for (Ability a : ClientEvents.unlockedAbilities) {
				if (a.name.equals(Ability.SPEED.name)) {
					ClientEvents.speedModifier = Ability.getModifier(Ability.forName(Ability.SPEED.name, true), a);
					speed = true;
				}
				if (a.name.equals(Ability.SPEED_IN_WATER.name)) {
					ClientEvents.speedWaterModifier = Ability
							.getModifier(Ability.forName(Ability.SPEED_IN_WATER.name, true), a);
					speedinwater = true;
				}
				if (a.name.equals(Ability.JUMP.name)) {
					ClientEvents.jumpModifier = Ability.getModifier(Ability.forName(Ability.JUMP.name, true), a);
					jump = true;
				}
				if (a.name.equals(Ability.DISTANCE.name)) {
					ClientEvents.distance = Ability.getModifier(Ability.forName(Ability.SPEED.name, true), a);
					distance = true;
				}
			}
			if (!speed)
				ClientEvents.speedModifier = 1;
			if (!speedinwater)
				ClientEvents.speedWaterModifier = 1;
			if (!jump)
				ClientEvents.jumpModifier = 1;
			if (!distance)
				ClientEvents.distance = 1;
		}
		if (id == 2) {
			Ability.maxLvls = buf.readInt();
		}
		if (id == 3) {
			ClientEvents.currentLvls = buf.readInt();
		}
	}

	public void handleServerSide(PacketBuffer buf, byte id, WorldServer world, EntityPlayerMP player) {
		if (!AbilityMod.SERVER)
			return;
		ExtendedPlayer server = ExtendedPlayer.get(player);
		if (id == 0) {
			Ability toBuy = Ability.forName(ByteBufUtils.readUTF8String(buf), false);
			Ability a = null;
			if (server.getUnlocked(toBuy) != null)
				a = server.getUnlocked(toBuy);
			if (a != null && a.lvl == toBuy.getSize() - 1)
				return;
			if (server.getTotalLvls() == Ability.maxLvls)
				return;
			if (Ability.buy(toBuy, player, a == null ? 0 : a.lvl + 1))
				server.addLvl(toBuy);
		}
		if (id == 1) {
			server.syncAbilities();
		}
		if (id == 2) {
			int returnLvl = 0;
			for (Ability a : server.unlockedAbilities) {
				returnLvl = returnLvl + Ability.forName(a.name, false).costs.get(a.lvl);
			}
			player.experienceLevel = player.experienceLevel + returnLvl;
			player.playerNetServerHandler.sendPacket(
					new S1FPacketSetExperience(player.experience, player.experienceTotal, player.experienceLevel));
			server.clearAbilities();
		}
	}

	public String getChannel() {
		return "wendoxdchannel";
	}

	public static ArrayList<Ability> readAbilities(PacketBuffer buf) {
		ArrayList<Ability> abilities = new ArrayList<Ability>();
		int $ize = buf.readInt();
		for (int w = 0; w < $ize; w++) {
			String name = ByteBufUtils.readUTF8String(buf);
			String desc = ByteBufUtils.readUTF8String(buf);
			int currentLvl = buf.readInt();
			int size = buf.readInt();
			ArrayList<Integer> costs = new ArrayList<Integer>();
			ArrayList<Double> modifiers = new ArrayList<Double>();
			for (int i = 0; i < size; i++) {
				costs.add(buf.readInt());
			}
			for (int i = 0; i < size; i++) {
				modifiers.add(buf.readDouble());
			}
			Ability a = new Ability(name, desc, costs, modifiers);
			a.lvl = currentLvl;
			abilities.add(a);
		}
		return abilities;
	}
}
