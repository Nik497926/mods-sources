/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import java.io.File;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import ru.simplemc.simplecore.mod.SimpleCoreRegistry;
import ru.simplemc.simplecore.mod.handler.network.NetworkHandler;
import ru.simplemc.simplecore.mod.manager.IntegrationManager;
import ru.simplemc.simplecore.mod.manager.RegistrationManager;

public class CommonProxy {
    private final RegistrationManager registrationManager = new RegistrationManager();
    protected IntegrationManager integrationManager;
    private Configuration configuration;

    public void preInit(FMLPreInitializationEvent event) {
        SimpleCoreRegistry.initItems();
        this.configuration = new Configuration(new File(event.getModConfigurationDirectory(), "simplecore.cfg"));
        this.configuration.load();
    }

    public void init(FMLInitializationEvent event) {
        this.registrationManager.onInit();
        NetworkHandler.registerMessages();
    }

    public void postInit(FMLPostInitializationEvent event) {
        this.registrationManager.onPostInit();
    }

    public boolean isIntegrated() {
        return this.integrationManager != null;
    }

    public void registerEventHandler(Object eventHandler) {
        MinecraftForge.EVENT_BUS.register(eventHandler);
        FMLCommonHandler.instance().bus().register(eventHandler);
    }

    public RegistrationManager getRegistrationManager() {
        return this.registrationManager;
    }

    public IntegrationManager getIntegrationManager() {
        return this.integrationManager;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }
}

