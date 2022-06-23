/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import ru.simplemc.simplecore.mod.SimpleCore;
import ru.simplemc.simplecore.mod.common.data.TradeData;
import ru.simplemc.simplecore.mod.common.tileentity.TileEntityTradeStation;
import ru.simplemc.simplecore.mod.handler.network.message.TradeSettingsApplyMessage;

public class TradeSettingsApplyMessageHandler
implements IMessageHandler<TradeSettingsApplyMessage, IMessage> {
    public IMessage onMessage(TradeSettingsApplyMessage message, MessageContext ctx) {
        TileEntity tileEntity;
        if (ctx.side.isClient()) {
            SimpleCore.LOGGER.error(this.getClass().getSimpleName() + " allowed only at server side!");
            return null;
        }
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        TradeData.TradeStationPos pos = message.getPos();
        World world = player.getEntityWorld();
        if (world.blockExists(pos.getX(), pos.getY(), pos.getZ()) && (tileEntity = world.getTileEntity(pos.getX(), pos.getY(), pos.getZ())) instanceof TileEntityTradeStation) {
            TileEntityTradeStation tradeStation = (TileEntityTradeStation)tileEntity;
            TradeData data = tradeStation.getData();
            if (data.hasOwnerAccess((EntityPlayer)player)) {
                data.setPrice(message.getPrice());
                data.setType(message.getType());
            } else {
                player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.trade_station.access_error", new Object[0]));
            }
        }
        return null;
    }
}

