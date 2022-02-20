/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.ui;

import net.frozor.accessories.client.item.AccessoryItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import org.lwjgl.opengl.GL11;

public class UIItem {
    private float scaleFactor;
    private AccessoryItem item;
    private boolean hovered;

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public void draw(Minecraft mc, int xPosition, int yPosition, int mouseX, int mouseY, float particleTicks, float rotation) {
        GL11.glEnable((int)32826);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glBlendFunc((int)770, (int)771);
        RenderHelper.enableStandardItemLighting();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        mc.getTextureManager().bindTexture(this.item.getTexture());
        GL11.glTranslatef((float)15.0f, (float)15.0f, (float)0.0f);
        GL11.glRotatef((float)-25.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glPushMatrix();
        GL11.glScalef((float)-1.2f, (float)-1.2f, (float)-1.2f);
        GL11.glRotatef((float)(-rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        AccessoryItem.CustomPreferences prefs = this.item.getPreferencesGui();
        GL11.glTranslatef((float)(-prefs.getTranslation()[0]), (float)prefs.getTranslation()[1], (float)(-prefs.getTranslation()[2]));
        GL11.glScalef((float)prefs.getScale()[0], (float)prefs.getScale()[1], (float)prefs.getScale()[2]);
        GL11.glTranslatef((float)8.0f, (float)-8.0f, (float)8.0f);
        this.item.getModel().renderAll();
        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
    }

    public AccessoryItem getItem() {
        return this.item;
    }

    public UIItem(AccessoryItem item, float scaleFactor) {
        this.item = item;
        this.scaleFactor = scaleFactor;
    }

    public boolean isHovered() {
        return this.hovered;
    }
}

