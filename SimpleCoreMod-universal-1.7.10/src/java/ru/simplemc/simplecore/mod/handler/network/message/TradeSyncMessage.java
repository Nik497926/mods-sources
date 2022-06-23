/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import java.math.BigDecimal;
import net.minecraft.item.ItemStack;
import ru.simplemc.simplecore.mod.common.data.TradeData;
import ru.simplemc.simplecore.mod.common.data.TradeType;

public class TradeSyncMessage
implements IMessage {
    private TradeData.TradeStationPos pos;
    private ItemStack itemStack;
    private TradeType type;
    private String owner;
    private BigDecimal price;
    private BigDecimal turnover;
    private int transactions;
    private boolean infinity;
    private int availableTradeItemsCount;

    public TradeSyncMessage() {
    }

    public TradeSyncMessage(TradeData data) {
        this.pos = data.getPos();
        this.itemStack = data.getItemStack();
        this.type = data.getType();
        this.owner = data.getOwner();
        this.price = data.getPrice();
        this.turnover = data.getTurnover();
        this.transactions = data.getTransactions();
        this.infinity = data.isInfinity();
        this.availableTradeItemsCount = data.getAvailableTradeItemsCount();
    }

    public void fromBytes(ByteBuf buf) {
        this.pos = new TradeData.TradeStationPos(buf.readInt(), buf.readInt(), buf.readInt());
        this.itemStack = ByteBufUtils.readItemStack((ByteBuf)buf);
        this.type = TradeType.valueOf(ByteBufUtils.readUTF8String((ByteBuf)buf));
        this.owner = ByteBufUtils.readUTF8String((ByteBuf)buf);
        this.price = new BigDecimal(ByteBufUtils.readUTF8String((ByteBuf)buf));
        this.turnover = new BigDecimal(ByteBufUtils.readUTF8String((ByteBuf)buf));
        this.transactions = buf.readInt();
        this.infinity = buf.readBoolean();
        this.availableTradeItemsCount = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.pos.getX());
        buf.writeInt(this.pos.getY());
        buf.writeInt(this.pos.getZ());
        ByteBufUtils.writeItemStack((ByteBuf)buf, (ItemStack)this.itemStack);
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.type.toString());
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.owner);
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.price.toString());
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.turnover.toString());
        buf.writeInt(this.transactions);
        buf.writeBoolean(this.infinity);
        buf.writeInt(this.availableTradeItemsCount);
    }

    public TradeData.TradeStationPos getPos() {
        return this.pos;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public TradeType getType() {
        return this.type;
    }

    public String getOwner() {
        return this.owner;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getTurnover() {
        return this.turnover;
    }

    public int getTransactions() {
        return this.transactions;
    }

    public boolean isInfinity() {
        return this.infinity;
    }

    public int getAvailableTradeItemsCount() {
        return this.availableTradeItemsCount;
    }
}

