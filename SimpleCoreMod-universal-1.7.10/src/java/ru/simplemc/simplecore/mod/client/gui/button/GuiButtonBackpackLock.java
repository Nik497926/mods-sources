/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.gui.button;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.simplemc.simplecore.mod.client.gui.GuiContainerBackpack;
import ru.simplemc.simplecore.mod.common.item.ItemBackpack;

public class GuiButtonBackpackLock
extends GuiButton {
    private static final ResourceLocation GUI_BACKPACK_BUTTON_TEXTURES = new ResourceLocation("simplecore", "textures/gui/backpack_inventory_buttons.png");
    private final List<String> hoveringTextLines = new ArrayList<String>();
    private final GuiContainerBackpack gui;

    public GuiButtonBackpackLock(int x, int y, GuiContainerBackpack gui) {
        super(0, x, y, 18, 18, "Locked Button");
        this.gui = gui;
    }

    private void updateHoveringText() {
        this.hoveringTextLines.clear();
        if (ItemBackpack.isLocked(this.gui.getPlayer().getHeldItem())) {
            this.hoveringTextLines.add(I18n.format((String)"gui.backpack.button.lock.locked", (Object[])new Object[0]));
            this.hoveringTextLines.add("");
            this.hoveringTextLines.add(I18n.format((String)"gui.backpack.button.lock.locked.line.0", (Object[])new Object[0]));
            this.hoveringTextLines.add(I18n.format((String)"gui.backpack.button.lock.locked.line.1", (Object[])new Object[0]));
            this.hoveringTextLines.add(I18n.format((String)"gui.backpack.button.lock.locked.line.2", (Object[])new Object[0]));
            this.hoveringTextLines.add(I18n.format((String)"gui.backpack.button.lock.locked.line.3", (Object[])new Object[0]));
        } else {
            this.hoveringTextLines.add(I18n.format((String)"gui.backpack.button.lock.unlocked", (Object[])new Object[0]));
            this.hoveringTextLines.add("");
            this.hoveringTextLines.add(I18n.format((String)"gui.backpack.button.lock.unlocked.line.0", (Object[])new Object[0]));
            this.hoveringTextLines.add(I18n.format((String)"gui.backpack.button.lock.unlocked.line.1", (Object[])new Object[0]));
            this.hoveringTextLines.add(I18n.format((String)"gui.backpack.button.lock.unlocked.line.2", (Object[])new Object[0]));
            this.hoveringTextLines.add(I18n.format((String)"gui.backpack.button.lock.unlocked.line.3", (Object[])new Object[0]));
        }
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        mc.getTextureManager().bindTexture(GUI_BACKPACK_BUTTON_TEXTURES);
        this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, this.getHoverState(this.field_146123_n) * this.height - this.height, this.width, this.height);
        this.mouseDragged(mc, mouseX, mouseY);
    }

    public void func_146111_b(int mouseX, int mouseY) {
        if (this.field_146123_n) {
            this.updateHoveringText();
            this.gui.drawHoveringText(this.hoveringTextLines, mouseX, mouseY);
        }
    }
}

