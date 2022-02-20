/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.ui;

import net.frozor.accessories.client.RenderHelper;
import org.lwjgl.opengl.GL11;

public class UIScroll {
    private int stage;
    private int maxStage;
    private double height;

    public UIScroll(int maxStage) {
        this.height = 98.0 / (double)(maxStage + 1);
        this.maxStage = maxStage;
        this.stage = 0;
    }

    public void setStage(int i) {
        this.stage = i;
    }

    public int getStage() {
        return this.stage;
    }

    public double getHeight() {
        return this.height;
    }

    public void draw() {
        GL11.glPushMatrix();
        RenderHelper.drawRect(1.0, 0.0, 0.0, 98.0, -1728053248);
        GL11.glTranslated((double)0.0, (double)((98.0 - this.getHeight()) / (double)this.getMaxStage() * (double)this.getStage()), (double)0.0);
        RenderHelper.drawRect(1.0, 0.0, 0.0, this.getHeight(), -855656395);
        GL11.glPopMatrix();
    }

    public int getMaxStage() {
        return this.maxStage;
    }
}

