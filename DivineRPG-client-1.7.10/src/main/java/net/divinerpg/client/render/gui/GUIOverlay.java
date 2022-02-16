/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.gui;

import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUIOverlay {
    public static int guiTick = 600;
    private ScaledResolution res;
    private static final ResourceLocation r = new ResourceLocation("divinerpg:textures/gui/armorBar.png");

    public void drawArmor() {
        GL11.glDisable((int)2929);
        this.res = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        int x = this.res.getScaledWidth() / 2 - 11;
        int y = this.res.getScaledHeight() - 49;
        Item boots = null;
        Item body = null;
        Item legs = null;
        Item helmet = null;
        ItemStack stackBoots = Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(0);
        ItemStack stackLegs = Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(1);
        ItemStack stackBody = Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(2);
        ItemStack stackHelmet = Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(3);
        float speedMultiplier = 1.0f;
        boots = stackBoots != null ? stackBoots.getItem() : null;
        body = stackBody != null ? stackBody.getItem() : null;
        legs = stackLegs != null ? stackLegs.getItem() : null;
        helmet = stackHelmet != null ? stackHelmet.getItem() : null;
        int size = 0;
        if (boots == TwilightItemsArmor.haliteBoots && legs == TwilightItemsArmor.haliteLeggings && body == TwilightItemsArmor.haliteChestplate && helmet == TwilightItemsArmor.haliteHelmet) {
            size = 1;
        }
        switch (size) {
            case 1: {
                Minecraft.getMinecraft().getTextureManager().bindTexture(r);
                Util.drawTexturedModalRect(x, y, 0, 0, 9, 9);
                break;
            }
            case 2: {
                Minecraft.getMinecraft().getTextureManager().bindTexture(r);
                Util.drawTexturedModalRect(x, y, 9, 0, 18, 9);
            }
        }
        GL11.glEnable((int)2929);
    }
}

