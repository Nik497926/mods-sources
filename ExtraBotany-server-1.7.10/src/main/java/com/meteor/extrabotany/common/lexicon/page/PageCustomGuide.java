/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.lexicon.page;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Desktop;
import java.net.URI;
import net.minecraft.client.gui.GuiButton;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.common.lexicon.page.PageText;

public class PageCustomGuide
extends PageText {
    GuiButton button;
    String url;
    String tButton;

    public PageCustomGuide(String unlocalizedName, String surl, String textButton) {
        super(unlocalizedName);
        this.url = surl;
        this.tButton = textButton;
    }

    @SideOnly(value=Side.CLIENT)
    public void onOpened(IGuiLexiconEntry gui) {
        this.button = new GuiButton(101, gui.getLeft() + 30, gui.getTop() + gui.getHeight() - 50, gui.getWidth() - 60, 20, this.tButton);
        gui.getButtonList().add(this.button);
    }

    @SideOnly(value=Side.CLIENT)
    public void onClosed(IGuiLexiconEntry gui) {
        gui.getButtonList().remove(this.button);
    }

    @SideOnly(value=Side.CLIENT)
    public void onActionPerformed(IGuiLexiconEntry gui, GuiButton button) {
        if (button == this.button && Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(this.url));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }
}

