package wendoxd.init;

import java.awt.Font;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.ScoreboardSaveData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import wendoxd.events.ClientEvents;
import wendoxd.events.ServerEvents;
import wendoxd.modules.Ability;
import wendoxd.network.ICustomValue;
import wendoxd.network.NetworkManager;
import wendoxd.network.PacketHandler;
import wendoxd.utils.CustomFont;
import wendoxd.utils.Util;

@Mod(modid = "WCaseMod")
public class AbilityMod {
	public static final boolean SERVER = true;
	public static NetworkManager network = NetworkManager.registerPacketHandler(new PacketHandler());

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			clientInit();
			ClientRegistry.registerKeyBinding(ClientEvents.openGui);
		} else
			serverInit();
	}

	public void serverInit() {
		register(new ServerEvents());
		Ability.readAllAbilities();
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
