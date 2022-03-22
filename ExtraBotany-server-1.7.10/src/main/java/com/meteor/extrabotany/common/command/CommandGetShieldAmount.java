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

public class CommandGetShieldAmount
extends CommandBase {
    public String getCommandName() {
        return "getShieldAmount";
    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 1) {
            throw new WrongUsageException("commands.ExtraBotany.getShieldAmount.desc");
        }
        EntityPlayerMP player = args.length > 0 ? CommandBase.getPlayer(sender, args[0]) : CommandBase.getCommandSenderAsPlayer(sender);
        sender.addChatMessage(new ChatComponentTranslation("commands.ExtraBotany.getShieldAmount.success", player.getDisplayName(), Float.valueOf(PropertyHandler.getShieldAmount(player))));
    }

    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "commands.ExtraBotany.getShieldAmount.desc";
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            String[] names = MinecraftServer.getServer().getAllUsernames();
            return CommandBase.getListOfStringsMatchingLastWord(args, names);
        }
        return null;
    }
}

