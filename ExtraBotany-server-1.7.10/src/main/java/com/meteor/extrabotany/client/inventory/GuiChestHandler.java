/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.inventory;

import com.meteor.extrabotany.client.gui.aspects.GuiAspects;
import com.meteor.extrabotany.client.gui.aspects.ServerContainerC;
import com.meteor.extrabotany.client.gui.autoDaisy.GuiDaisy;
import com.meteor.extrabotany.client.gui.autoDaisy.ServerContainerA;
import com.meteor.extrabotany.client.gui.autoTrade.GuiAutoTrade;
import com.meteor.extrabotany.client.gui.autoTrade.ServerContainerT;
import com.meteor.extrabotany.client.gui.autopool.GuiAutoPool;
import com.meteor.extrabotany.client.gui.flower.GuiFlower;
import com.meteor.extrabotany.client.gui.genDust.GuiGenDust;
import com.meteor.extrabotany.client.gui.genDust.ServerContainer;
import com.meteor.extrabotany.client.gui.terra.GuiTerra;
import com.meteor.extrabotany.client.gui.terra.ServerContainerB;
import com.meteor.extrabotany.common.block.tile.TileAutoDaisy;
import com.meteor.extrabotany.common.block.tile.TileAutoPlate;
import com.meteor.extrabotany.common.block.tile.TileAutoPool;
import com.meteor.extrabotany.common.block.tile.TileAutoTradeElf;
import com.meteor.extrabotany.common.block.tile.TileExtraAspect;
import com.meteor.extrabotany.common.block.tile.TileFlower;
import com.meteor.extrabotany.common.block.tile.TileGenDust;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiChestHandler
implements IGuiHandler {
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileFlower) {
            return new com.meteor.extrabotany.client.gui.flower.ServerContainer(player.inventory, (TileFlower)te);
        }
        if (te instanceof TileGenDust) {
            return new ServerContainer(player.inventory, (TileGenDust)te);
        }
        if (te instanceof TileAutoPool) {
            return new com.meteor.extrabotany.client.gui.autopool.ServerContainer(player.inventory, (TileAutoPool)te);
        }
        if (te instanceof TileAutoTradeElf) {
            return new ServerContainerT(player.inventory, (TileAutoTradeElf)te);
        }
        if (te instanceof TileAutoDaisy) {
            return new ServerContainerA(player.inventory, (TileAutoDaisy)te);
        }
        if (te instanceof TileAutoPlate) {
            return new ServerContainerB(player.inventory, (TileAutoPlate)te);
        }
        if (te instanceof TileExtraAspect) {
            return new ServerContainerC(player.inventory, (TileExtraAspect)te);
        }
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileFlower) {
            return new GuiFlower(player.inventory, (TileFlower)te);
        }
        if (te instanceof TileGenDust) {
            return new GuiGenDust(player.inventory, (TileGenDust)te);
        }
        if (te instanceof TileAutoPool) {
            return new GuiAutoPool(player.inventory, (TileAutoPool)te);
        }
        if (te instanceof TileAutoTradeElf) {
            return new GuiAutoTrade(player.inventory, (TileAutoTradeElf)te);
        }
        if (te instanceof TileAutoDaisy) {
            return new GuiDaisy(player.inventory, (TileAutoDaisy)te);
        }
        if (te instanceof TileAutoPlate) {
            return new GuiTerra(player.inventory, (TileAutoPlate)te);
        }
        if (te instanceof TileExtraAspect) {
            return new GuiAspects(player.inventory, (TileExtraAspect)te);
        }
        return null;
    }

    public static enum GuiIDs {
        CAMO_MINE,
        AWAKE,
        ELF,
        FLOWER;

    }
}

