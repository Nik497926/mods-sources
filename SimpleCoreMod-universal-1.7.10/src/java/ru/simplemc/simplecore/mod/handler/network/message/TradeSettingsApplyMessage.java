/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import java.math.BigDecimal;
import ru.simplemc.simplecore.mod.common.data.TradeData;
import ru.simplemc.simplecore.mod.common.data.TradeType;

public class TradeSettingsApplyMessage
implements IMessage {
    private TradeData.TradeStationPos pos;
    private TradeType type;
    private BigDecimal price;

    public TradeSettingsApplyMessage() {
    }

    public TradeSettingsApplyMessage(TradeData updatedData) {
        this.price = updatedData.getPrice();
        this.type = updatedData.getType();
        this.pos = updatedData.getPos();
    }

    public void fromBytes(ByteBuf buf) {
        this.price = new BigDecimal(ByteBufUtils.readUTF8String((ByteBuf)buf));
        this.type = TradeType.valueOf(ByteBufUtils.readUTF8String((ByteBuf)buf));
        this.pos = new TradeData.TradeStationPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.price.toString());
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.type.toString());
        buf.writeInt(this.pos.getX());
        buf.writeInt(this.pos.getY());
        buf.writeInt(this.pos.getZ());
    }

    public TradeData.TradeStationPos getPos() {
        return this.pos;
    }

    public TradeType getType() {
        return this.type;
    }

    public BigDecimal getPrice() {
        return this.price;
    }
}

