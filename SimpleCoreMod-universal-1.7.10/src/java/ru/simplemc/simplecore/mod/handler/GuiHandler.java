/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.simplecore.mod.client.gui.GuiContainerBackpack;
import ru.simplemc.simplecore.mod.client.gui.GuiContainerTradeStationInventory;
import ru.simplemc.simplecore.mod.client.gui.GuiContainerTradeStationPurchase;
import ru.simplemc.simplecore.mod.client.gui.GuiContainerTradeStationSettings;
import ru.simplemc.simplecore.mod.common.inventory.ContainerBackpack;
import ru.simplemc.simplecore.mod.common.inventory.ContainerTradeStationInventory;
import ru.simplemc.simplecore.mod.common.inventory.ContainerTradeStationPurchase;
import ru.simplemc.simplecore.mod.common.inventory.ContainerTradeStationSettings;
import ru.simplemc.simplecore.mod.common.item.ItemBackpack;
import ru.simplemc.simplecore.mod.common.tileentity.TileEntityTradeStation;

public class GuiHandler
implements IGuiHandler {
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        ItemStack itemStack;
        TileEntity tileEntity;
        if (id == 0 && (tileEntity = world.getTileEntity(x, y, z)) instanceof TileEntityTradeStation) {
            TileEntityTradeStation tradeStation = (TileEntityTradeStation)tileEntity;
            return new ContainerTradeStationPurchase(tradeStation.getInventory(), player.inventory);
        }
        if (id == 1 && (tileEntity = world.getTileEntity(x, y, z)) instanceof TileEntityTradeStation) {
            TileEntityTradeStation tradeStation = (TileEntityTradeStation)tileEntity;
            return new ContainerTradeStationSettings(tradeStation.getInventory(), player.inventory);
        }
        if (id == 2 && (tileEntity = world.getTileEntity(x, y, z)) instanceof TileEntityTradeStation) {
            TileEntityTradeStation tradeStation = (TileEntityTradeStation)tileEntity;
            return new ContainerTradeStationInventory(tradeStation.getInventory(), player.inventory);
        }
        if (id == 3 && (itemStack = player.getHeldItem()) != null && itemStack.getItem() instanceof ItemBackpack) {
            return new ContainerBackpack(player.inventory, itemStack);
        }
        return null;
    }

    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity;
        if (id == 0 && (tileEntity = world.getTileEntity(x, y, z)) instanceof TileEntityTradeStation) {
            TileEntityTradeStation tradeStation = (TileEntityTradeStation)tileEntity;
            return new GuiContainerTradeStationPurchase(new ContainerTradeStationPurchase(tradeStation.getInventory(), player.inventory));
        }
        if (id == 1 && (tileEntity = world.getTileEntity(x, y, z)) instanceof TileEntityTradeStation) {
            TileEntityTradeStation tradeStation = (TileEntityTradeStation)tileEntity;
            return new GuiContainerTradeStationSettings(new ContainerTradeStationSettings(tradeStation.getInventory(), player.inventory));
        }
        if (id == 2 && (tileEntity = world.getTileEntity(x, y, z)) instanceof TileEntityTradeStation) {
            TileEntityTradeStation tradeStation = (TileEntityTradeStation)tileEntity;
            return new GuiContainerTradeStationInventory(new ContainerTradeStationInventory(tradeStation.getInventory(), player.inventory));
        }
        if (id == 3) {
            return new GuiContainerBackpack(new ContainerBackpack(player.inventory, player.getHeldItem()));
        }
        return null;
    }
}

