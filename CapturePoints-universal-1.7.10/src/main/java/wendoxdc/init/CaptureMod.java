package wendoxdc.init;

import java.awt.Color;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.scoreboard.ScoreboardSaveData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants.NBT;
import wendoxdc.events.ClientEvents;
import wendoxdc.events.ServerEvents;
import wendoxdc.modules.Capture;
import wendoxdc.nbtutils.CustomFileNBT;
import wendoxdc.nbtutils.INBTDataSaver;
import wendoxdc.network.NetworkManager;
import wendoxdc.network.PacketHandler;
import wendoxdc.utils.Util;

@Mod(modid = "CaptMod")
public class CaptureMod {
	public static boolean isOP;
	public static final boolean SERVER = true;
	public static NetworkManager network = NetworkManager.registerPacketHandler(new PacketHandler());

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
			clientInit();
		else
			serverInit();
	}

	public void serverInit() {
		if (!SERVER)
			return;
		register(new ServerEvents());
		CustomFileNBT.register(new INBTDataSaver() {
			@Override
			public void writeToNBT(NBTTagCompound compound) {
				if (!SERVER)
					return;
				NBTTagList list = new NBTTagList();
				for (Capture c : Capture.captures) {
					NBTTagCompound cmd = new NBTTagCompound();
					cmd.setString("name", c.name);
					cmd.setInteger("x", c.x);
					cmd.setInteger("z", c.z);
					cmd.setInteger("radius", c.radius);
					cmd.setInteger("color", c.color);
					cmd.setInteger("dimId", c.dimId);
					cmd.setInteger("maxTimeCapt", c.maxTimeCapt);
					cmd.setInteger("cooldown", c.cooldown);
					cmd.setInteger("minOnline", c.minOnline);
					cmd.setInteger("radiusCapt", c.radiusCapt);
					cmd.setInteger("maxHeight", c.maxHeight);
					cmd.setInteger("itemsDrop", c.itemsDrop);
					NBTTagList items = new NBTTagList();
					for (ItemStack s : c.items) {
						if (s != null) {
							NBTTagCompound com = new NBTTagCompound();
							s.writeToNBT(com);
							items.appendTag(com);
						}
					}
					cmd.setTag("items", items);
					list.appendTag(cmd);
				}
				compound.setTag("capturesmod", list);
			}

			@Override
			public long updateTime() {
				if (!SERVER)
					return 0;
				return 50;
			}

			@Override
			public void readFromNBT(NBTTagCompound compound) {
				if (!SERVER)
					return;
				if (Capture.captures.size() > 0)
					return;
				NBTTagList list = compound.getTagList("capturesmod", NBT.TAG_COMPOUND);
				for (int i = 0; i < list.tagCount(); i++) {
					NBTTagCompound cmd = list.getCompoundTagAt(i);
					Capture c = Capture.newCapt(cmd.getString("name"), cmd.getInteger("x"), cmd.getInteger("z"),
							cmd.getInteger("radius"), cmd.getInteger("color"), cmd.getInteger("dimId"),
							cmd.getInteger("maxTimeCapt"), cmd.getInteger("cooldown"), cmd.getInteger("minOnline"),
							cmd.getInteger("radiusCapt"), cmd.getInteger("maxHeight"), cmd.getInteger("itemsDrop"));
					NBTTagList items = cmd.getTagList("items", NBT.TAG_COMPOUND);
					for (int w = 0; w < items.tagCount(); w++) {
						c.items.add(ItemStack.loadItemStackFromNBT(items.getCompoundTagAt(w)));
					}
					Capture.captures.add(c);
				}
			}

			@Override
			public String fileName() {
				return "capts";
			}
		});
	}

	public void clientInit() {
		register(new ClientEvents());
		try {
			Class.forName("net.minecraft.client.Minecraft").getDeclaredField("timer");
			serverInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void register(Object o) {
		MinecraftForge.EVENT_BUS.register(o);
		FMLCommonHandler.instance().bus().register(o);
	}
}
