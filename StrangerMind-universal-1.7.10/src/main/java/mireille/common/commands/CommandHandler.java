package mireille.common.commands;

import net.minecraft.command.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.relauncher.*;
import mireille.*;
import mireille.common.items.*;
import net.minecraft.server.*;
import mireille.network.*;
import net.minecraft.entity.player.*;
import cpw.mods.fml.common.network.simpleimpl.*;
import net.minecraft.util.*;
import java.util.*;

public class CommandHandler extends CommandBase
{
    public String getCommandName() {
        return "stmind";
    }
    
    public String getCommandUsage(final ICommandSender commandSender) {
        return "/stmind";
    }
    
    public void processCommand(final ICommandSender commandSender, final String[] args) {
        final EntityPlayerMP player = getCommandSenderAsPlayer(commandSender);
        if (args.length == 1 && args[0].toLowerCase().equals("reload")) {
            final Side var10000 = FMLCommonHandler.instance().getSide();
            FMLCommonHandler.instance().getSide();
            if (var10000 != Side.SERVER) {
                return;
            }
            ModConfig.registerConfig(StrangerMind.ConfigDirectory, player);
            CraftingManager.registerSunRecipes(StrangerMind.ConfigDirectory, player);
            final List players = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
            for (final Object o : players) {
                NetworkHandler.network.sendTo((IMessage)new UpdateConfig(CraftingManager.WorkbenchCrafts), (EntityPlayerMP)o);
            }
        }
        else if (args.length == 1 && args[0].toLowerCase().equals("help")) {
            player.addChatMessage((IChatComponent)new ChatComponentText(StrangerMind.resource("stming.chat.prefix") + "§b/stmind reload §a- \u041f\u0435\u0440\u0435\u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0430 \u043a\u043e\u043d\u0444\u0438\u0433\u043e\u0432."));
        }
        else {
            player.addChatMessage((IChatComponent)new ChatComponentText(StrangerMind.resource("stming.chat.prefix") + "§a\u041d\u0435\u0432\u0435\u0440\u043d\u0430\u044f \u043a\u043e\u043c\u0430\u043d\u0434\u0430. \u041f\u043e\u043c\u043e\u0449\u044c: §b/stmind help"));
        }
    }
    
    public boolean canCommandSenderUseCommand(final ICommandSender commandSender) {
        return true;
    }
}
