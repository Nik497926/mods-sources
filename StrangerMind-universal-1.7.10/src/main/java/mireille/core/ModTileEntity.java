package mireille.core;

import cpw.mods.fml.common.registry.*;
import mireille.common.tileentity.*;

public class ModTileEntity
{
    public static void registerTileEntity() {
        GameRegistry.registerTileEntity((Class)TileEntityPicFrame.class, "TileEntityOPFrame");
        GameRegistry.registerTileEntity((Class)TileSkinWorkbench.class, "TileSkinWorkbench");
    }
}
