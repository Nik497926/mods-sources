package ua.limon4ik.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import ua.limon4ik.blocks.AutoElvenTrade;
import ua.limon4ik.blocks.AutoManaPool;
import ua.limon4ik.blocks.AutoMatrixBlock;
import ua.limon4ik.blocks.AutoRunicAltar;
import ua.limon4ik.tiles.TileEntityAutoElvenTrade;
import ua.limon4ik.tiles.TileEntityAutoManaPool;
import ua.limon4ik.tiles.TileEntityAutoMatrix;
import ua.limon4ik.tiles.TileEntityAutoRunicAltar;

public class CommonProxy {
    public void init(FMLInitializationEvent e){
        GameRegistry.registerTileEntity(TileEntityAutoMatrix.class,"TileAutoMatrix");
        GameRegistry.registerTileEntity(TileEntityAutoElvenTrade.class,"TileEntityAutoElvenTrade");
        GameRegistry.registerTileEntity(TileEntityAutoManaPool.class,"TileEntityAutoManaPool");
        GameRegistry.registerTileEntity(TileEntityAutoRunicAltar.class,"TileEntityAutoRunicAltar");
    }
    public void preInit(FMLPreInitializationEvent e){
        GameRegistry.registerBlock(new AutoMatrixBlock(),"AutoMatrix");
        GameRegistry.registerBlock(new AutoElvenTrade(),"AutoElvenTrade");
        GameRegistry.registerBlock(new AutoRunicAltar(),"AutoRunicAltar");
        GameRegistry.registerBlock(new AutoManaPool(),"AutoManaPool");
    }
    public void postInit(FMLPostInitializationEvent e){

    }
}
