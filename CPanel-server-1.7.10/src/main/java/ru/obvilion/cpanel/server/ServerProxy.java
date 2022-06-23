package ru.obvilion.cpanel.server;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import ru.obvilion.cpanel.CommonProxy;
import ru.obvilion.cpanel.server.network.NetworkHandler;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy extends CommonProxy {
    public FMLEventChannel channel;

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        EventHandler handler = new EventHandler();
        MinecraftForge.EVENT_BUS.register(handler);
        FMLCommonHandler.instance().bus().register(handler);

        channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("ACS");
        channel.register(new NetworkHandler());
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }
}
