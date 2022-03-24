package mireille.common.commands;

import net.minecraft.command.*;
import mireille.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;

public class CommandGetSerial extends CommandBase
{
    public String getCommandName() {
        return "stmindinfo";
    }
    
    public String getCommandUsage(final ICommandSender commandSender) {
        return "/stmindinfo";
    }
    
    public void processCommand(final ICommandSender commandSender, final String[] args) {
        final EntityPlayerMP player = getCommandSenderAsPlayer(commandSender);
        if (args.length == 1) {
            final EntityPlayerMP targetPlayer = getPlayer(commandSender, args[0]);
            if (targetPlayer == null) {
                player.addChatMessage((IChatComponent)new ChatComponentText(StrangerMind.resource("stmind.chat.prefix") + "Player not found."));
                return;
            }
            player.addChatMessage((IChatComponent)new ChatComponentText(StrangerMind.resource("stmind.chat.prefix") + "Ok."));
        }
        else {
            player.addChatMessage((IChatComponent)new ChatComponentText(StrangerMind.resource("stmind.chat.prefix") + "Args not one."));
        }
    }
    
    public boolean canCommandSenderUseCommand(final ICommandSender commandSender) {
        return true;
    }
}
