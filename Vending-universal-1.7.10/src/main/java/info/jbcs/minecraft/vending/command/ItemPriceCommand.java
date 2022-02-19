/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 */
package info.jbcs.minecraft.vending.command;

import info.jbcs.minecraft.vending.EconomyControl;
import info.jbcs.minecraft.vending.Vending;
import info.jbcs.minecraft.vending.network.MsgPrice;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ItemPriceCommand
extends CommandBase {
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return p_71519_1_ instanceof EntityPlayer && ((EntityPlayer)p_71519_1_).capabilities.isCreativeMode;
    }

    public String getCommandName() {
        return "minprice";
    }

    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "/minprice <amount>";
    }

    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        if (this.canCommandSenderUseCommand(p_71515_1_) && p_71515_2_.length > 0) {
            int price = Integer.parseInt(p_71515_2_[0]);
            EconomyControl.setMinPrice(((EntityPlayer)p_71515_1_).getCurrentEquippedItem(), price);
            if (!((EntityPlayer)p_71515_1_).worldObj.isRemote) {
                Vending.instance.messagePipeline.sendToServer(new MsgPrice(EconomyControl.getNbtTagCompound()));
            }
        } else {
            p_71515_1_.addChatMessage((IChatComponent)new ChatComponentText(this.getCommandUsage(p_71515_1_)));
        }
    }
}

