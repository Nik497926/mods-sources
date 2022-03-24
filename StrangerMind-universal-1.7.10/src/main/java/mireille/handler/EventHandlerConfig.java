package mireille.handler;

import cpw.mods.fml.common.gameevent.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.relauncher.*;
import mireille.common.items.*;
import mireille.network.*;
import cpw.mods.fml.common.network.simpleimpl.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import cpw.mods.fml.common.eventhandler.*;

public class EventHandlerConfig
{
    @SubscribeEvent
    public void playerLoginEvent(final PlayerEvent.PlayerLoggedInEvent event) {
        final EntityPlayer player = event.player;
        final World world = player.worldObj;
        if (!world.isRemote) {
            final Side var10000 = FMLCommonHandler.instance().getSide();
            FMLCommonHandler.instance().getSide();
            if (var10000 == Side.SERVER) {
                NetworkHandler.network.sendTo((IMessage)new UpdateConfig(CraftingManager.WorkbenchCrafts), (EntityPlayerMP)player);
            }
        }
    }
}
