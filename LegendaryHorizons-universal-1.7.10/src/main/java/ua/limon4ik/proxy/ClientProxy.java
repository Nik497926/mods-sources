package ua.limon4ik.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;

import ua.limon4ik.client.render.tiles.RenderAutoMatrixBlock;
import ua.limon4ik.tiles.TileEntityAutoMatrix;

public class ClientProxy extends CommonProxy {
    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        //MinecraftForge.EVENT_BUS.register(new EventHandler());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAutoMatrix.class,new RenderAutoMatrixBlock());
        //MinecraftForgeClient.registerItemRenderer(SResearches.find("AutoMatrixBlock").getItem(),new RenderAutoMatrixItem());
    }
}
