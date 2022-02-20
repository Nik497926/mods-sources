/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.ui;

import net.frozor.accessories.client.ui.EnumAction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public interface IPage {
    public void handleMouseInput();

    public void init(float var1, float var2, float var3);

    public int getMarginLeft();

    public EnumAction mouseClicked(int var1, int var2, int var3);

    public void drawPage(Minecraft var1, float var2, float var3, int var4, int var5, float var6);

    public String getPageName();

    public void actionPerformed(GuiButton var1);
}

