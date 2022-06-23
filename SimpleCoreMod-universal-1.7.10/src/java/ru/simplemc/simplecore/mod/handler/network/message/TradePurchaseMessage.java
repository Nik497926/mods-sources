/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import ru.simplemc.simplecore.mod.common.data.TradeData;

public class TradePurchaseMessage
implements IMessage {
    private TradeData.TradeStationPos pos;

    public TradePurchaseMessage() {
    }

    public TradePurchaseMessage(TradeData data) {
        this.pos = data.getPos();
    }

    public void fromBytes(ByteBuf buf) {
        this.pos = new TradeData.TradeStationPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.pos.getX());
        buf.writeInt(this.pos.getY());
        buf.writeInt(this.pos.getZ());
    }

    public TradeData.TradeStationPos getPos() {
        return this.pos;
    }
}

