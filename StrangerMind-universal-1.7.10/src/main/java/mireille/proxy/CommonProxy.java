package mireille.proxy;

import mireille.handler.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.relauncher.*;
import net.minecraftforge.common.*;
import cpw.mods.fml.common.event.*;
import net.minecraft.command.*;
import mireille.common.commands.*;
import mireille.common.commands.CommandHandler;

public class CommonProxy
{
    public static final InventorySaveHandler antidropHandler;
    
    public void preInit(final FMLPreInitializationEvent event) {
    }
    
    public void Init(final FMLInitializationEvent event) {
        final Side var10000 = event.getSide();
        FMLCommonHandler.instance().getSide();
        if (var10000 == Side.SERVER) {
            MinecraftForge.EVENT_BUS.register((Object)CommonProxy.antidropHandler);
            FMLCommonHandler.instance().bus().register((Object)CommonProxy.antidropHandler);
        }
    }
    
    public void postInit(final FMLPostInitializationEvent event) {
    }
    
    public void registerRenderers() {
    }
    
    public void registerNEIStuff() {
    }
    
    public void serverStarting(final FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new CommandGetSerial());
        event.registerServerCommand((ICommand)new CommandHandler());
    }
    
    static {
        antidropHandler = new InventorySaveHandler();
    }
}
