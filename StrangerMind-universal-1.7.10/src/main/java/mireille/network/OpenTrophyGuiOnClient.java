package mireille.network;

import net.minecraft.item.*;
import io.netty.buffer.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.network.simpleimpl.*;
import net.minecraft.client.*;
import mireille.client.gui.*;
import mireille.*;
import net.minecraft.client.entity.*;

public class OpenTrophyGuiOnClient implements IMessage
{
    private ItemStack stack;
    private int index;
    
    public OpenTrophyGuiOnClient() {
    }
    
    public OpenTrophyGuiOnClient(final ItemStack stack, final int index) {
        this.stack = stack;
        this.index = index;
    }
    
    public void fromBytes(final ByteBuf buf) {
        try {
            this.stack = ByteBufUtils.readItemStack(buf);
            this.index = buf.readInt();
        }
        catch (IndexOutOfBoundsException var3) {
            System.out.println(var3);
        }
    }
    
    public void toBytes(final ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, this.stack);
        buf.writeInt(this.index);
    }
    
    public static class Handler implements IMessageHandler<OpenTrophyGuiOnClient, IMessage>
    {
        public IMessage onMessage(final OpenTrophyGuiOnClient message, final MessageContext ctx) {
            final EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
            if (message != null) {
                GuiTrophyBox.stack = message.stack;
                GuiTrophyBox.index = message.index;
                player.openGui((Object)StrangerMind.instance, 1, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
            }
            return null;
        }
    }
}
