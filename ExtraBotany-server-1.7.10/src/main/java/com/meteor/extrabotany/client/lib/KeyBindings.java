/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.lib;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;

@SideOnly(value=Side.CLIENT)
public class KeyBindings {
    public static KeyBinding toolConfig;
    public static KeyBinding knifeInBack;
    public static KeyBinding bloodVictim;
    public static KeyBinding selfSacrifice;
    public static KeyBinding perjurer;

    public static void init() {
        toolConfig = new KeyBinding("key.ExtraBotania.toolConfig", 82, "ExtraBotania");
        ClientRegistry.registerKeyBinding((KeyBinding)toolConfig);
        knifeInBack = new KeyBinding("key.ExtraBotania.knifeInBack", 79, "ExtraBotania");
        ClientRegistry.registerKeyBinding((KeyBinding)knifeInBack);
        bloodVictim = new KeyBinding("key.ExtraBotania.bloodVictim", 200, "ExtraBotania");
        ClientRegistry.registerKeyBinding((KeyBinding)bloodVictim);
        selfSacrifice = new KeyBinding("key.ExtraBotania.selfSacrifice", 208, "ExtraBotania");
        ClientRegistry.registerKeyBinding((KeyBinding)selfSacrifice);
        perjurer = new KeyBinding("key.ExtraBotania.perjurer", 75, "ExtraBotania");
        ClientRegistry.registerKeyBinding((KeyBinding)perjurer);
    }
}

