/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraftforge.fluids.Fluid
 */
package net.divinerpg;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.divinerpg.blocks.base.ModFluid;
import net.divinerpg.items.arcana.EntityLightning;
import net.divinerpg.network.MessageArcanaBar;
import net.divinerpg.network.MessageDivineAccumulator;
import net.divinerpg.utils.LogHelper;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.divinerpg.utils.proxies.CommonProxy;
import net.minecraftforge.fluids.Fluid;

@Mod(modid="divinerpg", name="DivineRPG", version="1.4.1.4")
public class DivineRPG {
    @Mod.Instance(value="divinerpg")
    public static DivineRPG instance;
    @SidedProxy(clientSide="net.divinerpg.utils.proxies.ClientProxy", serverSide="net.divinerpg.utils.proxies.CommonProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper network;
    public static Fluid tarFluid;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LogHelper.info("Loading DivineRPG...");
        ConfigurationHelper.init(event.getModConfigurationDirectory());

        network = NetworkRegistry.INSTANCE.newSimpleChannel("DivineRPGNetwork");
        network.registerMessage(MessageArcanaBar.Handler.class, MessageArcanaBar.class, 0, Side.CLIENT);
        network.registerMessage(MessageDivineAccumulator.Handler.class, MessageDivineAccumulator.class, 1, Side.CLIENT);

        proxy.preInitServer(event);
        proxy.preInitClient(event);
        proxy.renderThings();
        Util.registerProjectile(EntityLightning.class, "FakeLightningFix");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        LogHelper.info("DivineRPG has finished loading!");
    }

    static {
        tarFluid = new ModFluid("Tar", 10, 5000);
    }
}

