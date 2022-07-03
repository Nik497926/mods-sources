/**
 * @author wendoxdc 22.08.2020
 */
package wendoxdc.nbtutils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "nbtutils by wendox")
public class NBTUtils {
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}

	@SubscribeEvent
	public void tick(TickEvent.ServerTickEvent event) {
		CustomFileNBT.tick(MinecraftServer.getServer().getEntityWorld().getTotalWorldTime());
	}
}
