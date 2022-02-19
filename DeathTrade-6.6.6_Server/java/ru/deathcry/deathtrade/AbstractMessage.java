/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade;

import com.google.common.base.Throwables;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import ru.deathcry.deathtrade.Main;

public abstract class AbstractMessage<T extends AbstractMessage<T>>
implements IMessage,
IMessageHandler<T, IMessage> {
    protected abstract void read(PacketBuffer var1) throws IOException;

    protected abstract void write(PacketBuffer var1) throws IOException;

    protected boolean isValidOnSide(Side side) {
        return true;
    }

    protected boolean requiresMainThread() {
        return true;
    }

    public void fromBytes(ByteBuf buffer) {
        try {
            this.read(new PacketBuffer(buffer));
        }
        catch (IOException e) {
            throw Throwables.propagate((Throwable)e);
        }
    }

    public void toBytes(ByteBuf buffer) {
        try {
            this.write(new PacketBuffer(buffer));
        }
        catch (IOException e) {
            throw Throwables.propagate((Throwable)e);
        }
    }

    public IMessage onMessage(T msg, MessageContext ctx) {
        ((AbstractMessage)msg).process(Main.proxy.getPlayerEntity(ctx), ctx.side);
        return null;
    }

    public abstract void process(EntityPlayer var1, Side var2);
}

