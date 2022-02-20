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

    public static String l(String s) {
        int n = s.length();
        int n2 = n - 1;
        char[] cArray = new char[n];
        int n3 = 5 << 3 ^ 3;
        int cfr_ignored_0 = (3 ^ 5) << 4 ^ 3;
        int n4 = n2;
        int n5 = (3 ^ 5) << 4 ^ (2 ^ 5);
        while (n4 >= 0) {
            int n6 = n2--;
            cArray[n6] = (char)(s.charAt(n6) ^ n5);
            if (n2 < 0) break;
            int n7 = n2--;
            cArray[n7] = (char)(s.charAt(n7) ^ n3);
            n4 = n2;
        }
        return new String(cArray);
    }

    public int getMaxStage() {
        return this.maxStage;
    }
}

