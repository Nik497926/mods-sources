/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.handler;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import java.io.File;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
    private static Configuration config;
    public static int efficiencySunshinelily;
    public static int efficiencyMoonlightlily;
    public static int efficiencyBlueenchantress;
    public static int efficiencyCandyflower;
    public static int efficiencyGeminiorchid;
    public static int efficiencyOminiviolet;
    public static int efficiencyPyschobloom;
    public static int efficiencyStonesia;
    public static int efficiencyBellflower;
    public static int pyschobloomMax;
    public static int enchGaiaBlessing;
    public static int enchDivineFavor;
    public static int enchDivineMark;
    public static int idPotionFPS;
    public static int idPotionSPS;
    public static int idPotionRP;
    public static int idPotionC;
    public static int idPotionD;
    public static int extraShieldAmount;
    public static int shieldDisplayX;
    public static int shieldDisplayY;
    public static boolean disableShieldDisplay;
    public static boolean disableShieldRender;
    public static boolean disableEasterEgg;
    public static boolean enableDiplopbamboo;
    public static boolean enableGuns;
    public static boolean anotherShieldRender;

    public ConfigHandler(FMLPreInitializationEvent event) {
        config = new Configuration(new File(new File(event.getModConfigurationDirectory(), "extrameteorp"), "ExtraBotania.cfg"));
        config.load();
        ConfigHandler.registerConfig();
        config.save();
    }

    private static void registerConfig() {
        idPotionFPS = config.get("ids", "potionFPS", 100, StatCollector.translateToLocal((String)"config.ExtraBotany.potion.desc")).getInt();
        idPotionSPS = config.get("ids", "potionSPS", 101, StatCollector.translateToLocal((String)"config.ExtraBotany.potion.desc")).getInt();
        idPotionRP = config.get("ids", "potionRP", 102, StatCollector.translateToLocal((String)"config.ExtraBotany.potion.desc")).getInt();
        idPotionC = config.get("ids", "potionC", 103, StatCollector.translateToLocal((String)"config.ExtraBotany.potion.desc")).getInt();
        idPotionD = config.get("ids", "potionD", 104, StatCollector.translateToLocal((String)"config.ExtraBotany.potion.desc")).getInt();
        enchGaiaBlessing = config.get("ids", "enchGaiaBlessing", 203, StatCollector.translateToLocal((String)"config.ExtraBotany.ench.desc")).getInt();
        enchDivineFavor = config.get("ids", "enchDivineFavor", 204, StatCollector.translateToLocal((String)"config.ExtraBotany.ench.desc")).getInt();
        enchDivineMark = config.get("ids", "enchDivineMark", 205, StatCollector.translateToLocal((String)"config.ExtraBotany.ench.desc")).getInt();
        extraShieldAmount = config.get("common", "extraShieldAmount", 0, StatCollector.translateToLocal((String)"config.ExtraBotany.extraShieldAmount.desc")).getInt();
        efficiencySunshinelily = config.get("common", "efficiencySunshinelily", 3, StatCollector.translateToLocal((String)"config.ExtraBotany.efficiencySunshinelily.desc")).getInt();
        efficiencyMoonlightlily = config.get("common", "efficiencyMoonlightlily", 3, StatCollector.translateToLocal((String)"config.ExtraBotany.efficiencyMoonlightlily.desc")).getInt();
        efficiencyBlueenchantress = config.get("common", "efficiencyBlueenchantress", 100, StatCollector.translateToLocal((String)"config.ExtraBotany.efficiencyBlueenchantress.desc")).getInt();
        efficiencyCandyflower = config.get("common", "efficiencyCandyflower", 8, StatCollector.translateToLocal((String)"config.ExtraBotany.efficiencyCandyflower.desc")).getInt();
        efficiencyGeminiorchid = config.get("common", "efficiencyGeminiorchid", 4, StatCollector.translateToLocal((String)"config.ExtraBotany.efficiencyGeminiorchid.desc")).getInt();
        efficiencyOminiviolet = config.get("common", "efficiencyOminiviolet", 20, StatCollector.translateToLocal((String)"config.ExtraBotany.efficiencyOminiviolet.desc")).getInt();
        efficiencyStonesia = config.get("common", "efficiencyStonesia", 25, StatCollector.translateToLocal((String)"config.ExtraBotany.efficiencyStonesia.desc")).getInt();
        efficiencyPyschobloom = config.get("common", "efficiencyPyschobloom", 3, StatCollector.translateToLocal((String)"config.ExtraBotany.efficiencyPyschobloom.desc")).getInt();
        efficiencyBellflower = config.get("common", "efficiencyBellflower", 6, StatCollector.translateToLocal((String)"config.ExtraBotany.efficiencyBellflower.desc")).getInt();
        pyschobloomMax = config.get("common", "pyschobloomMax", 9, StatCollector.translateToLocal((String)"config.ExtraBotany.pyschobloomMax.desc")).getInt();
        shieldDisplayX = config.get("client", "shieldDisplayX", 0, StatCollector.translateToLocal((String)"config.ExtraBotany.shieldDisplayX.desc")).getInt();
        shieldDisplayY = config.get("client", "shieldDisplayY", 0, StatCollector.translateToLocal((String)"config.ExtraBotany.shieldDisplayY.desc")).getInt();
        disableShieldDisplay = config.get("client", "disableShieldDisplay", false, StatCollector.translateToLocal((String)"config.ExtraBotany.disableShieldDisplay.desc")).getBoolean();
        disableShieldRender = config.get("client", "disableShieldRender", false, StatCollector.translateToLocal((String)"config.ExtraBotany.disableShieldRender.desc")).getBoolean();
        disableEasterEgg = config.get("client", "disableShieldRender", false, StatCollector.translateToLocal((String)"config.ExtraBotany.disableEasterEgg.desc")).getBoolean();
        enableDiplopbamboo = config.get("common", "enableDiplopbamboo", false, StatCollector.translateToLocal((String)"config.ExtraBotany.enableDiplopbamboo.desc")).getBoolean();
        enableGuns = config.get("common", "enableGuns", false, StatCollector.translateToLocal((String)"config.ExtraBotany.enableGuns.desc")).getBoolean();
        anotherShieldRender = config.get("client", "anotherShieldRender", false, StatCollector.translateToLocal((String)"config.ExtraBotany.anotherShieldRender.desc")).getBoolean();
    }
}

