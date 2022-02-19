/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import ru.deathcry.deathtrade.Main;
import ru.deathcry.deathtrade.MySQL;
import ru.deathcry.deathtrade.Request;
import ru.deathcry.deathtrade.barter.BarterHolder;
import ru.deathcry.deathtrade.packets.PacketDispatcher;
import ru.deathcry.deathtrade.packets.server.SendBalance;
import ru.deathcry.deathtrade.packets.server.SendOpponentName;

public class CommandHandler
implements ICommand {
    private final List aliases;
    private final Map<EntityPlayer, Request> pendings = new HashMap<EntityPlayer, Request>();

    public CommandHandler() {
        this.aliases = new ArrayList();
        this.aliases.add("trade");
    }

    public int compareTo(Object o) {
        return 0;
    }

    public String getCommandName() {
        return "trade";
    }

    public String getCommandUsage(ICommandSender var1) {
        return "trade help";
    }

    public List getCommandAliases() {
        return this.aliases;
    }

    public void processCommand(ICommandSender sender, String[] argString) {
        World world = sender.getEntityWorld();
        if (!(sender instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)sender;
        if (argString.length == 0) {
            sender.addChatMessage((IChatComponent)CommandHandler.locMessage("message.no_argss", new Object[0]));
            return;
        }
        switch (argString[0].toLowerCase()) {
            case "help": {
                sender.addChatMessage((IChatComponent)CommandHandler.locMessage("message.available_commands", new Object[0]));
                sender.addChatMessage((IChatComponent)new ChatComponentText("- /trade accept"));
                sender.addChatMessage((IChatComponent)new ChatComponentText("- /trade cancel"));
                sender.addChatMessage((IChatComponent)new ChatComponentText("- /trade [player]"));
                break;
            }
            case "cancel": {
                if (!this.pendings.containsKey(player)) {
                    sender.addChatMessage((IChatComponent)CommandHandler.locMessage("message.no_requests", new Object[0]));
                    break;
                }
                player.addChatMessage((IChatComponent)CommandHandler.locMessage("message.request_cancelled", new Object[0]));
                this.pendings.get((Object)player).player.addChatMessage((IChatComponent)CommandHandler.locMessage("message.request_rejected", new Object[0]));
                this.pendings.remove(player);
                break;
            }
            case "accept": {
                if (!this.pendings.containsKey(player)) {
                    sender.addChatMessage((IChatComponent)CommandHandler.locMessage("message.no_requests", new Object[0]));
                    break;
                }
                if (!this.pendings.get(player).isActual()) {
                    sender.addChatMessage((IChatComponent)CommandHandler.locMessage("message.request_time_out", new Object[0]));
                    break;
                }
                EntityPlayer tradePlayer = this.pendings.get((Object)player).player;
                if (Main.trades.containsKey(tradePlayer)) {
                    sender.addChatMessage((IChatComponent)CommandHandler.locMessage("message.already_trading", new Object[0]));
                    break;
                }
                this.createNewTrade(player, tradePlayer, world);
                this.pendings.remove(player);
                break;
            }
            default: {
                EntityPlayer tradePlayer;
                if (this.pendings.containsKey(player) && this.pendings.get((Object)player).player.getDisplayName().equalsIgnoreCase(argString[0])) {
                    tradePlayer = this.pendings.get((Object)player).player;
                    if (this.pendings.get(player).isActual() && !Main.trades.containsKey(tradePlayer)) {
                        this.createNewTrade(player, tradePlayer, world);
                        this.pendings.remove(player);
                        break;
                    }
                }
                if (player.getDisplayName().equalsIgnoreCase(argString[0])) {
                    sender.addChatMessage((IChatComponent)CommandHandler.locMessage("message.cant_trade_yourself", new Object[0]));
                    break;
                }
                tradePlayer = this.findPlayerByName(argString[0]);
                if (tradePlayer == null) {
                    sender.addChatMessage((IChatComponent)CommandHandler.locMessage("message.player_dont_exist", new Object[0]));
                    break;
                }
                if (Main.trades.containsKey(tradePlayer)) {
                    sender.addChatMessage((IChatComponent)CommandHandler.locMessage("message.already_trading", new Object[0]));
                    break;
                }
                this.pendings.put(tradePlayer, new Request(player));
                player.addChatMessage((IChatComponent)CommandHandler.locMessage("message.request_sent", tradePlayer.getDisplayName()));
                tradePlayer.addChatMessage((IChatComponent)CommandHandler.locMessage("message.request_recive", player.getDisplayName()));
            }
        }
    }

    private void createNewTrade(EntityPlayer acceptor, EntityPlayer sender, World world) {
        BarterHolder holder = new BarterHolder(acceptor, sender);
        Main.trades.put(acceptor, holder);
        Main.trades.put(sender, holder);
        acceptor.openGui((Object)Main.instance, 0, world, 0, 0, 0);
        PacketDispatcher.sendTo(new SendOpponentName(sender.getDisplayName()), (EntityPlayerMP)acceptor);
        PacketDispatcher.sendTo(new SendBalance(MySQL.getPlayerBalance(acceptor.getDisplayName())), (EntityPlayerMP)acceptor);
        sender.openGui((Object)Main.instance, 0, world, 0, 0, 0);
        PacketDispatcher.sendTo(new SendOpponentName(acceptor.getDisplayName()), (EntityPlayerMP)sender);
        PacketDispatcher.sendTo(new SendBalance(MySQL.getPlayerBalance(sender.getDisplayName())), (EntityPlayerMP)sender);
    }

    public boolean canCommandSenderUseCommand(ICommandSender var1) {
        return true;
    }

    public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
        ArrayList<String> l = new ArrayList<String>(Arrays.asList(MinecraftServer.getServer().getAllUsernames()));
        l.add("accept");
        l.add("cancel");
        String[] s = l.toArray(new String[l.size()]);
        return var2.length == 1 ? CommandHandler.getListOfStringsMatchingLastWord(var2, s) : null;
    }

    public static List getListOfStringsMatchingLastWord(String[] args, String ... p_71530_1_) {
        String s1 = args[args.length - 1];
        ArrayList<String> arraylist = new ArrayList<String>();
        String[] astring1 = p_71530_1_;
        int i = p_71530_1_.length;
        for (int j = 0; j < i; ++j) {
            String s2 = astring1[j];
            if (!CommandHandler.doesStringStartWith(s1, s2)) continue;
            arraylist.add(s2);
        }
        return arraylist;
    }

    public static boolean doesStringStartWith(String search, String base) {
        return base.regionMatches(true, 0, search, 0, search.length());
    }

    public boolean isUsernameIndex(String[] var1, int var2) {
        return true;
    }

    public static ChatComponentText locMessage(String unlocal, Object ... args) {
        return new ChatComponentText(CommandHandler.localize(unlocal, args));
    }

    public static String localize(String unlocal, Object ... args) {
        return String.format(StatCollector.translateToLocal((String)unlocal), args);
    }

    public EntityPlayer findPlayerByName(String playername) {
        for (Object object : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
            if (!((EntityPlayer)object).getDisplayName().equalsIgnoreCase(playername)) continue;
            return (EntityPlayer)object;
        }
        return null;
    }
}

