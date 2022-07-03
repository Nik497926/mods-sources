/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.integration.hugetools;

import com.meteor.extrabotany.client.render.item.GunRenderer;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;

public class ItemRender {
    public static void initHugeItemRender() {
        Minecraft mc = FMLClientHandler.instance().getClient();
        new GunRenderer(mc.gameSettings, mc.getTextureManager());
    }
}

