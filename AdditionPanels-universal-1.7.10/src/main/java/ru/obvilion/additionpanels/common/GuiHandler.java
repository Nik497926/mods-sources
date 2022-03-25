package ru.obvilion.additionpanels.common;

import appeng.helpers.IInterfaceHost;
import appeng.tile.misc.TileInterface;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.justagod.cutter.GradleSide;
import ru.justagod.cutter.GradleSideOnly;
import ru.obvilion.additionpanels.client.gui.GuiInterface;
import ru.obvilion.additionpanels.client.gui.GuiQGenerator;
import ru.obvilion.additionpanels.client.gui.GuiSolarPanel;
import ru.obvilion.additionpanels.common.container.ContainerInterface;
import ru.obvilion.additionpanels.common.tileentity.*;

public class GuiHandler implements IGuiHandler {
    public GuiHandler() {
    }

    @GradleSideOnly(GradleSide.SERVER)
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int X, int Y, int Z) {
        TileEntity te = world.getTileEntity(X, Y, Z);
        if (te != null) {
            if (te instanceof TileEntityQGenerator){
                return ((TileEntityQGenerator) te).getGuiContainer(player.inventory);
            }
            if (te instanceof TileEntityAdvancedMFSU){
                return ((TileEntityAdvancedMFSU) te).getGuiContainer(player);
            }
            if (te instanceof TileEntityСobblestoneGenerator){
                return ((TileEntityСobblestoneGenerator) te).getGuiContainer(player);
            }
            if (te instanceof TileEntityFruitGenerator){
                return ((TileEntityFruitGenerator) te).getGuiContainer(player);
            }
            if (te instanceof TileEntitySolarPanel){
                return ((TileEntitySolarPanel) te).getGuiContainer(player.inventory);
            }
            if (te instanceof TileInterface){
                return new ContainerInterface(player.inventory, ((IInterfaceHost) world.getTileEntity(X, Y, Z)));
            }
        }
        return null;
    }

    @GradleSideOnly(GradleSide.CLIENT)
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int X, int Y, int Z) {
        TileEntity te = world.getTileEntity(X, Y, Z);
        if (te != null) {
            if (te instanceof TileEntityQGenerator){
                return new GuiQGenerator(player.inventory, ((TileEntityQGenerator) te));
            }
            if (te instanceof TileEntityAdvancedMFSU){
                return ((TileEntityAdvancedMFSU) te).getGui(player, false);
            }
            if (te instanceof TileEntityСobblestoneGenerator){
                return ((TileEntityСobblestoneGenerator) te).getGui(player, false);
            }
            if (te instanceof TileEntityFruitGenerator){
                return ((TileEntityFruitGenerator) te).getGui(player, false);
            }
            if (te instanceof TileEntitySolarPanel){
                return new GuiSolarPanel(player.inventory, ((TileEntitySolarPanel) te));
            }
            if (te instanceof TileInterface){
                return new GuiInterface(player.inventory, ((IInterfaceHost) world.getTileEntity(X, Y, Z)));
            }
        }
        return null;

    }
}
