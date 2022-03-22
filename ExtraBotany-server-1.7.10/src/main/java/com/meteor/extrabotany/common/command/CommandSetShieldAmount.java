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
            throw new WrongUsageException("commands.ExtraBotany.setShieldAmount.desc");
        }
        EntityPlayerMP player = args.length > 1 ? CommandSetShieldAmount.getPlayer(sender, args[0]) : CommandSetShieldAmount.getCommandSenderAsPlayer(sender);
        String s = args[1];
        int i = CommandSetShieldAmount.parseInt(sender, s);
        PropertyHandler.setShieldAmount(i, player);
        sender.addChatMessage(new ChatComponentTranslation("commands.ExtraBotany.setShieldAmount.success", player.getDisplayName(), Float.valueOf(PropertyHandler.getShieldAmount(player))));
    }

    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "commands.ExtraBotany.setShieldAmount.desc";
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            String[] names = MinecraftServer.getServer().getAllUsernames();
            return CommandBase.getListOfStringsMatchingLastWord(args, names);
        }
        return null;
    }
}

