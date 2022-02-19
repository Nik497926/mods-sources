/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import ru.deathcry.deathtrade.EventHandlerer;
import ru.deathcry.deathtrade.Main;
import ru.deathcry.deathtrade.ModGuiHandler;
import ru.deathcry.deathtrade.packets.PacketDispatcher;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        PacketDispatcher.registerPackets();
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)Main.instance, (IGuiHandler)new ModGuiHandler());
        EventHandlerer handler = new EventHandlerer();
        MinecraftForge.EVENT_BUS.register((Object)handler);
        FMLCommonHandler.instance().bus().register((Object)handler);
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity;
    }

    public WorldServer getThreadFromContext(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity.getServerForPlayer();
    }
}

