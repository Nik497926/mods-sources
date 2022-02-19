/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraftforge.common.MinecraftForge
 */
package info.jbcs.minecraft.vending.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import info.jbcs.minecraft.vending.gui.HintGui;
import info.jbcs.minecraft.vending.proxy.CommonProxy;
import info.jbcs.minecraft.vending.renderer.BlockVendingMachineRenderer;
import info.jbcs.minecraft.vending.renderer.TileEntityVendingMachineRenderer;
import info.jbcs.minecraft.vending.tileentity.ClientTicks;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy
extends CommonProxy {
    private Minecraft mc;

    @Override
    public void registerEventHandlers() {
        FMLCommonHandler.instance().bus().register((Object)new ClientTicks());
        MinecraftForge.EVENT_BUS.register((Object)new HintGui(Minecraft.getMinecraft()));
    }

    @Override
    public void registerRenderers() {
        BlockVendingMachineRenderer.id = RenderingRegistry.getNextAvailableRenderId();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVendingMachine.class, (TileEntitySpecialRenderer)new TileEntityVendingMachineRenderer());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockVendingMachineRenderer());
    }
}

