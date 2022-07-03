/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.command;

import com.meteor.extrabotany.common.core.handler.PropertyHandler;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class CommandSetShieldAmount
extends CommandBase {
    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandName() {
        return "setShieldAmount";
    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length <= 0) {
            throw new WrongUsageException("commands.ExtraBotany.setShieldAmount.desc", new Object[0]);
        }
        EntityPlayerMP player = args.length > 1 ? CommandSetShieldAmount.getPlayer((ICommandSender)sender, (String)args[0]) : CommandSetShieldAmount.getCommandSenderAsPlayer((ICommandSender)sender);
        String s = args[1];
        int i = CommandSetShieldAmount.parseInt((ICommandSender)sender, (String)s);
        PropertyHandler.setShieldAmount(i, (EntityPlayer)player);
        sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.ExtraBotany.setShieldAmount.success", new Object[]{player.getDisplayName(), Float.valueOf(PropertyHandler.getShieldAmount((EntityPlayer)player))}));
    }

    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "commands.ExtraBotany.setShieldAmount.desc";
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            String[] names = MinecraftServer.getServer().getAllUsernames();
            return CommandBase.getListOfStringsMatchingLastWord((String[])args, (String[])names);
        }
        return null;
    }
}

