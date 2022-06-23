/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import ru.simplemc.simplecore.mod.SimpleCore;
import ru.simplemc.simplecore.mod.client.gui.GuiContainerTradeStationPurchase;
import ru.simplemc.simplecore.mod.handler.network.message.TradeGuiNotifyMessage;

public class TradeGuiNotifyMessageHandler
implements IMessageHandler<TradeGuiNotifyMessage, IMessage> {
    public IMessage onMessage(TradeGuiNotifyMessage message, MessageContext ctx) {
        if (ctx.side.isServer()) {
            SimpleCore.LOGGER.error(this.getClass().getSimpleName() + " allowed only at client side!");
            return null;
        }
        GuiScreen screen = Minecraft.getMinecraft().currentScreen;
        if (screen instanceof GuiContainerTradeStationPurchase) {
            ((GuiContainerTradeStationPurchase)screen).getMessagesHandler().addMessages(message.getLines());
        }
        return null;
    }
}

