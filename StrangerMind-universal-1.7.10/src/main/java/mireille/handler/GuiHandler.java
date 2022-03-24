package mireille.handler;

import cpw.mods.fml.common.network.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import mireille.common.tileentity.*;
import mireille.common.container.*;
import mireille.client.gui.*;
import mireille.client.gui.container.*;

public class GuiHandler implements IGuiHandler
{
    public Object getServerGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        switch (ID) {
            case 3: {
                return new ContainerSkinWorkbench(player.inventory, (TileSkinWorkbench)world.getTileEntity(x, y, z));
            }
            case 4: {
                return new ContainerWorkbench(player.inventory, world, x, y, z);
            }
            default: {
                return null;
            }
        }
    }
    
    public Object getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        switch (ID) {
            case 1: {
                return new GuiTrophyBox();
            }
            case 3: {
                return new GuiContainerSkinWorkbench(player.inventory, (TileSkinWorkbench)world.getTileEntity(x, y, z));
            }
            case 4: {
                return new GuiContainerWorkbench(player.inventory, world, x, y, z);
            }
            default: {
                return null;
            }
        }
    }
}
