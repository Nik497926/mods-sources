package mireille;

import net.minecraft.entity.player.*;
import java.io.*;
import net.minecraftforge.common.config.*;
import net.minecraft.util.*;

public class ModConfig
{
    public static float OnlinePictureMaxSize;
    public static boolean ArmorStatus;
    public static boolean HpAndHungerBar;
    public static boolean OreGenerator;
    public static int ArmorStatusMaxDistance;
    public static String KeepInventory;
    public static String ExpSave;
    public static String SaveInvMessage;
    public static String SaveExpMessage;
    public static String CommentOnlinePicture;
    public static String CommentArmorStatus;
    public static String CommentArmorStatus2;
    public static String CommentArmorStatus3;
    public static String CommentSaveInv;
    public static String CommentSaveExp;
    public static String ReloadGood;
    public static String ReloadBad;
    public static String ReloadBadPlayer;
    public static String CommentOreGenerator;
    
    public static void registerConfig(final String configDir) {
        registerConfig(configDir, null);
    }
    
    public static void registerConfig(final String configDir, final EntityPlayerMP player) {
        final String configName = "StrangerMind.cfg";
        final Configuration config = new Configuration(new File(configDir + configName));
        try {
            config.load();
            ModConfig.OreGenerator = config.getBoolean("OreGenerator", "General", ModConfig.OreGenerator, ModConfig.CommentOreGenerator);
            ModConfig.OnlinePictureMaxSize = config.getFloat("OnlinePictureMaxSize", "General", ModConfig.OnlinePictureMaxSize, 0.0f, 32.0f, ModConfig.CommentOnlinePicture);
            ModConfig.ArmorStatus = config.getBoolean("ArmorStatus", "ArmorStatus", ModConfig.ArmorStatus, ModConfig.CommentArmorStatus);
            ModConfig.HpAndHungerBar = config.getBoolean("HpAndHungerBar", "ArmorStatus", ModConfig.HpAndHungerBar, ModConfig.CommentArmorStatus2);
            ModConfig.ArmorStatusMaxDistance = config.getInt("ArmorStatusMaxDistance", "ArmorStatus", ModConfig.ArmorStatusMaxDistance, 1, 128, ModConfig.CommentArmorStatus3);
            ModConfig.KeepInventory = config.getString("keepInventory", "Antidrop", ModConfig.KeepInventory, ModConfig.CommentSaveInv);
            ModConfig.ExpSave = config.getString("ExpSave", "Antidrop", ModConfig.ExpSave, ModConfig.CommentSaveExp);
            if (player != null) {
                player.addChatMessage((IChatComponent)new ChatComponentText(ModConfig.ReloadGood.replace("%configName%", configName)));
            }
        }
        catch (Exception exception) {
            if (player == null) {
                System.out.println(ModConfig.ReloadBad.replace("%configName%", configName));
            }
            else {
                player.addChatMessage((IChatComponent)new ChatComponentText(ModConfig.ReloadBadPlayer.replace("%configName%", configName)));
            }
        }
        finally {
            config.save();
        }
    }
    
    static {
        ModConfig.OnlinePictureMaxSize = 32.0f;
        ModConfig.ArmorStatus = true;
        ModConfig.HpAndHungerBar = true;
        ModConfig.OreGenerator = true;
        ModConfig.ArmorStatusMaxDistance = 24;
        ModConfig.KeepInventory = "strangermind.invsave";
        ModConfig.ExpSave = "strangermind.expsave";
        ModConfig.SaveInvMessage = StrangerMind.resource("saveinvmessage");
        ModConfig.SaveExpMessage = StrangerMind.resource("saveexpmessage");
        ModConfig.CommentOnlinePicture = StrangerMind.resource("comment_op");
        ModConfig.CommentArmorStatus = StrangerMind.resource("comment_as");
        ModConfig.CommentArmorStatus2 = StrangerMind.resource("comment_as2");
        ModConfig.CommentArmorStatus3 = StrangerMind.resource("comment_as3");
        ModConfig.CommentSaveInv = StrangerMind.resource("comment_si");
        ModConfig.CommentSaveExp = StrangerMind.resource("comment_se");
        ModConfig.ReloadGood = StrangerMind.resource("reloadgood");
        ModConfig.ReloadBad = StrangerMind.resource("reloadbad");
        ModConfig.ReloadBadPlayer = StrangerMind.resource("reloadbadplayer");
        ModConfig.CommentOreGenerator = StrangerMind.resource("oregenerator");
    }
}
