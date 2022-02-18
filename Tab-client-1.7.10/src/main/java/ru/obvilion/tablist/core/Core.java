package ru.obvilion.tablist.core;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import ru.obvilion.tablist.events.GuiEvents;

@Mod(
    modid = "tablist",
    version = "1.1.0",
    name = "TabList Mod"
)
public class Core {
    Configuration config;
    public static String url = "https://obvilion.ru/api/users/%s/skin";
    public static String admins_prefixes = "&8;";
    public static boolean use_scoreboards = true;
    public static String copyright = "2022 (С)";
    public static boolean personal_calc = true;
    public static String server_name = "Minecraft Server";

    public Core() { }

    @EventHandler
    @SideOnly(Side.CLIENT)
    public void postClientInit(FMLPostInitializationEvent e) {
        this.register(new GuiEvents(), this);
    }

    @SubscribeEvent
    public void onConnect(ClientConnectedToServerEvent event) {
        if (Minecraft.getMinecraft().func_147104_D() != null && !Minecraft.getMinecraft().func_147104_D().serverName.equals("")) {
            server_name = Minecraft.getMinecraft().func_147104_D().serverName;
        }
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        config = new Configuration(new File("config/tablist.cfg"));
        config.load();

        server_name     = config.get("General", "DefaultServerName", "Minecraft Server").getString();
        url             = config.get("General", "SkinsPath", "https://obvilion.ru/api/users/%s/skin").getString();
        admins_prefixes = config.get("General", "AdminsPrefixes", "&4&l? &c;&4&l?&2&l? &a;&2&l? &a;&a&l? &a").getString();
        use_scoreboards = config.get("General", "UseScoreboards", true).getBoolean();
        copyright       = config.get("General", "Copyright", "&d%server_name% &8| &bObvilion.ru &8| &7Комплекс игровых серверов").getString().replace("&", "§");
        personal_calc   = config.get("General", "Calc Personal", true).getBoolean();

        config.save();
    }

    private void register(Object... objects) {
        for (Object o : objects) {
            MinecraftForge.EVENT_BUS.register(o);
            FMLCommonHandler.instance().bus().register(o);
        }
    }
}
