/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import net.frozor.accessories.CommonProxy;
import net.frozor.accessories.client.ui.UIItem;
import net.minecraft.client.Minecraft;

@Mod(modid="accessories", version="1.0")
public class AccessoriesCore {
    public static final String MODID = "accessories";
    public static final String VERSION = "1.0";
    @SidedProxy(clientSide="net.frozor.accessories.client.ClientProxy")
    public static CommonProxy proxy;
    @Mod.Instance(value="accessories")
    public static AccessoriesCore instance;
    public static boolean SHOW_MY_CAPE_SETTING;
    public static boolean SHOW_ACCESSORIES_SETTING;
    private File fileSettings;

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        this.initSettings();
        proxy.preInit(event);
    }

    static {
        SHOW_ACCESSORIES_SETTING = true;
        SHOW_MY_CAPE_SETTING = true;
    }

    public void changeValue(String prop, String value) {
        try {
            JsonElement element = new JsonParser().parse(new JsonReader((Reader)new FileReader(this.fileSettings)));
            element.getAsJsonObject().addProperty(prop, value);
            try (FileWriter writer = new FileWriter(this.fileSettings);){
                Gson gson = new GsonBuilder().create();
                gson.toJson(element, (Appendable)writer);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    public void initSettings() {
        try {
            File file = new File(Minecraft.getMinecraft().mcDataDir, UIItem.l("V/Q>L$BdO9J$"));
            if (!file.exists()) {
                JsonObject obj = new JsonObject();
                obj.addProperty(MODID, UIItem.l("Q8P/"));
                obj.addProperty(UIItem.l("V\"J=z'\\\u0015F+U/"), UIItem.l("Q8P/"));
                file.createNewFile();
                try (FileWriter writer = new FileWriter(file);){
                    Gson gson = new GsonBuilder().create();
                    gson.toJson((JsonElement)obj, (Appendable)writer);
                }
            }
            this.fileSettings = file;
            JsonElement element = new JsonParser().parse(new JsonReader((Reader)new FileReader(this.fileSettings)));
            SHOW_ACCESSORIES_SETTING = element.getAsJsonObject().get(MODID).getAsBoolean();
            SHOW_MY_CAPE_SETTING = element.getAsJsonObject().get(UIItem.l("V\"J=z'\\\u0015F+U/")).getAsBoolean();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

