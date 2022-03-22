/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.network;

import com.meteor.extrabotany.common.core.network.MessageBase;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class MessageXYZ<REQ extends IMessage>
extends MessageBase<REQ> {
    protected int x;
    protected int y;
    protected int z;

    public MessageXYZ() {
    }

    public MessageXYZ(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MessageXYZ(TileEntity te) {
        this(te.xCoord, te.yCoord, te.zCoord);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    protected TileEntity getTileEntity(World world) {
        return world.getTileEntity(this.x, this.y, this.z);
    }
}

