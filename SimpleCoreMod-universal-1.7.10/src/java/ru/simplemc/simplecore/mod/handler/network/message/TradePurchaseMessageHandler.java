/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayerMP;
import ru.simplemc.simplecore.mod.SimpleCore;
import ru.simplemc.simplecore.mod.common.inventory.ContainerTradeStationPurchase;
import ru.simplemc.simplecore.mod.common.tileentity.TileEntityTradeStation;
import ru.simplemc.simplecore.mod.handler.network.message.TradePurchaseMessage;

public class TradePurchaseMessageHandler
implements IMessageHandler<TradePurchaseMessage, IMessage> {
    public IMessage onMessage(TradePurchaseMessage message, MessageContext ctx) {
        ContainerTradeStationPurchase container;
        TileEntityTradeStation tradeStation;
        if (ctx.side.isClient()) {
            SimpleCore.LOGGER.error(this.getClass().getSimpleName() + " allowed only at server side!");
            return null;
        }
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        if (player.openContainer instanceof ContainerTradeStationPurchase && (tradeStation = (container = (ContainerTradeStationPurchase)player.openContainer).getInventoryTradeStation().getTradeStation()).getData().getPos().equals(message.getPos())) {
            container.processingPurchase(player);
        }
        return null;
    }
}

