package wendoxdc.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import wendoxdc.init.CaptureMod;
import wendoxdc.modules.Capture;
import wendoxdc.utils.Util;

public class ServerEvents {
	public static int currentOnline;

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event) {
		if (!CaptureMod.SERVER)
			return;
	}

	@SubscribeEvent
	public void tick(TickEvent.ServerTickEvent event) {
		if (!CaptureMod.SERVER)
			return;
		if (event.phase == TickEvent.Phase.END) {
			Capture.captures.forEach(w -> {
				w.update();
			});
			if (MinecraftServer.getServer().getEntityWorld().getTotalWorldTime() % 20 == 0) {
				ArrayList<EntityPlayerMP> players = new ArrayList<EntityPlayerMP>();
				for (WorldServer server : DimensionManager.getWorlds()) {
					server.playerEntities.forEach(w -> {
						CaptureMod.network.sendTo(CaptureMod.network.createPacket(2, Util.isOP((EntityPlayerMP) w)),
								(EntityPlayerMP) w);
					});
					players.addAll(server.playerEntities);
				}
				currentOnline = players.size();
				players.forEach(w -> {
					CaptureMod.network.sendTo(CaptureMod.network.createPacket(0, Capture.captures), w);
				});
			}
		}
	}
}

