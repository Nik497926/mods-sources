/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import ru.simplemc.simplecore.mod.SimpleCore;
import ru.simplemc.simplecore.mod.common.data.TradeData;
import ru.simplemc.simplecore.mod.common.tileentity.TileEntityTradeStation;
import ru.simplemc.simplecore.mod.handler.network.message.TradeSyncMessage;

public class TradeSyncMessageHandler
implements IMessageHandler<TradeSyncMessage, IMessage> {
    public IMessage onMessage(TradeSyncMessage message, MessageContext ctx) {
        TileEntity tileEntity;
        if (ctx.side.isServer()) {
            SimpleCore.LOGGER.error(this.getClass().getSimpleName() + " allowed only at client side!");
            return null;
        }
        TradeData.TradeStationPos pos = message.getPos();
        WorldClient world = Minecraft.getMinecraft().theWorld;
        if (world != null && world.blockExists(pos.getX(), pos.getY(), pos.getZ()) && (tileEntity = world.getTileEntity(pos.getX(), pos.getY(), pos.getZ())) instanceof TileEntityTradeStation) {
            TradeData tradeData = ((TileEntityTradeStation)tileEntity).getData();
            tradeData.setPos(message.getPos());
            tradeData.setItemStack(message.getItemStack());
            tradeData.setType(message.getType());
            tradeData.setOwner(message.getOwner());
            tradeData.setPrice(message.getPrice());
            tradeData.setTurnover(message.getTurnover());
            tradeData.setInfinity(message.isInfinity());
            tradeData.setAvailableTradeItemsCount(message.getAvailableTradeItemsCount());
        }
        return null;
    }
}

