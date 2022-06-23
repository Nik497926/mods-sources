/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.util;

import net.minecraft.client.gui.FontRenderer;

public class GuiUtils {
    public static boolean isElementHovered(int mousePosX, int mousePosY, int elementPosX, int elementPosY, int elementWidth, int elementHeight) {
        return mousePosX >= elementPosX && mousePosY >= elementPosY && mousePosX < elementPosX + elementWidth && mousePosY < elementPosY + elementHeight;
    }

    public static int calcStringCenteredPosition(int guiSizeX, FontRenderer fontRenderer, String value) {
        return guiSizeX / 2 - fontRenderer.getStringWidth(value) / 2;
    }
}

