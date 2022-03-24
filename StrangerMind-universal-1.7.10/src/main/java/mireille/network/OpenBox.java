package mireille.network;

import net.minecraft.item.*;
import io.netty.buffer.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.network.simpleimpl.*;
import java.util.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import mireille.common.items.*;

public class OpenBox implements IMessage
{
    private ItemStack box;
    
    public OpenBox() {
    }
    
    public OpenBox(final ItemCase box) {
        this.box = new ItemStack((Item)box);
    }
    
    public void fromBytes(final ByteBuf buf) {
        try {
            this.box = ByteBufUtils.readItemStack(buf);
        }
        catch (IndexOutOfBoundsException var3) {
            System.out.println(var3);
        }
    }
    
    public void toBytes(final ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, this.box);
    }
    
    public static class Handler implements IMessageHandler<OpenBox, IMessage>
    {
        public IMessage onMessage(final OpenBox message, final MessageContext ctx) {
            final EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            if (message.box != null && player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem().equals(message.box.getItem())) {
                final ItemCase Case = (ItemCase)message.box.getItem();
                if (!Case.isGiveAll()) {
                    final Random rand = new Random();
                    final int numb = rand.nextInt(Case.getSize());
                    final ItemBox itemBox = Case.getCurrentItem(numb);
                    final ItemStack itemStack = itemBox.getItemStack();
                    itemStack.stackSize = 1;
                    player.setCurrentItemOrArmor(0, itemStack);
                    NetworkHandler.network.sendTo((IMessage)new OpenTrophyGuiOnClient(message.box, numb), player);
                }
                else {
                    for (int i = 0; i < Case.getSize(); ++i) {
                        final ItemStack itemStack2 = Case.getCurrentItem(i).getItemStack();
                        itemStack2.stackSize = 1;
                        if (i == 0) {
                            player.setCurrentItemOrArmor(0, Case.getCurrentItem(0).getItemStack());
                        }
                        else if (player.inventory.getFirstEmptyStack() == -1) {
                            player.worldObj.spawnEntityInWorld((Entity)new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, itemStack2));
                        }
                        else {
                            player.inventory.addItemStackToInventory(itemStack2);
                        }
                        NetworkHandler.network.sendTo((IMessage)new OpenTrophyGuiOnClient(message.box, -1), player);
                    }
                }
            }
            return null;
        }
    }
}
