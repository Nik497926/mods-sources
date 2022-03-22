/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.network;

import com.meteor.extrabotany.common.core.network.MessageXYZ;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import ru.justagod.cutter.invoke.Invoke;

public class MessageHandleGuiButtonPress
extends MessageXYZ<MessageHandleGuiButtonPress> {
    private int id;

    public MessageHandleGuiButtonPress() {
    }

    public MessageHandleGuiButtonPress(TileEntity te, int id) {
        super(te);
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        this.id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        buf.writeInt(this.id);
    }

    @Override
    public void handleClientSide(MessageHandleGuiButtonPress message, EntityPlayer player) {
    }

    @Override
    public void handleServerSide(MessageHandleGuiButtonPress message, EntityPlayer player) {
        TileEntity te = message.getTileEntity(player.worldObj);
        Invoke.server(() -> {});
    }
}

