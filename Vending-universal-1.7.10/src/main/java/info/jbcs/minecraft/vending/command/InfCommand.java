/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MovingObjectPosition
 */
package info.jbcs.minecraft.vending.command;

import info.jbcs.minecraft.vending.General;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;

public class InfCommand
extends CommandBase {
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return p_71519_1_ instanceof EntityPlayer && ((EntityPlayer)p_71519_1_).capabilities.isCreativeMode;
    }

    public String getCommandName() {
        return "setinf";
    }

    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "/setinf <true/false> <player>";
    }

    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        if (this.canCommandSenderUseCommand(p_71515_1_) && p_71515_2_.length > 0) {
            boolean inf = Boolean.parseBoolean(p_71515_2_[0]);
            if (!p_71515_1_.getEntityWorld().isRemote && p_71515_1_ instanceof EntityPlayer) {
                MovingObjectPosition mop = General.getMovingObjectPositionFromPlayer(((EntityPlayer)p_71515_1_).worldObj, (EntityPlayer)p_71515_1_, false);
                if (mop == null) {
                    System.out.println("test_mop_null");
                    return;
                }
                TileEntity te = ((EntityPlayer)p_71515_1_).worldObj.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
                if (te == null) {
                    System.out.println("test_te_null");
                    return;
                }
                if (!(te instanceof TileEntityVendingMachine)) {
                    System.out.println("test_te_not_vend");
                    return;
                }
                ((TileEntityVendingMachine)te).infinite = inf;
                ((TileEntityVendingMachine)te).ownerName = p_71515_2_.length >= 2 ? p_71515_2_[1] : ((TileEntityVendingMachine)te).ownerName;
                te.getWorldObj().markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
                ((TileEntityVendingMachine)te).markDirty();
            }
        } else {
            p_71515_1_.addChatMessage((IChatComponent)new ChatComponentText(this.getCommandUsage(p_71515_1_)));
        }
    }
}

