package mireille.network;

import io.netty.buffer.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.network.simpleimpl.*;
import mireille.common.items.*;

public class UpdateConfig implements IMessage
{
    private String[] MirrorCrafts;
    
    public UpdateConfig() {
    }
    
    public UpdateConfig(final String[] MirrorCrafts) {
        this.MirrorCrafts = MirrorCrafts;
    }
    
    public void fromBytes(final ByteBuf buf) {
        try {
            final int count = buf.readInt();
            this.MirrorCrafts = new String[count];
            for (int i = 0; i < count; ++i) {
                this.MirrorCrafts[i] = ByteBufUtils.readUTF8String(buf);
            }
        }
        catch (IndexOutOfBoundsException var4) {
            System.out.println(var4);
        }
    }
    
    public void toBytes(final ByteBuf buf) {
        buf.writeInt(this.MirrorCrafts.length);
        for (final String mirrorCraft : this.MirrorCrafts) {
            ByteBufUtils.writeUTF8String(buf, mirrorCraft);
        }
    }
    
    public static class Handler implements IMessageHandler<UpdateConfig, IMessage>
    {
        public IMessage onMessage(final UpdateConfig message, final MessageContext ctx) {
            if (message != null) {
                CraftingManager.setDefault(message.MirrorCrafts);
            }
            return null;
        }
    }
}
