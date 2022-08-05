/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;

@SideOnly(value= Side.CLIENT)
public class ItemIcon
implements IIcon {
    private IIcon baseIcon;
    private float myX;
    private float myY;

    public ItemIcon(IIcon blockIcon, float x, float y) {
        this.baseIcon = blockIcon;
        this.myX = x;
        this.myY = y;
    }

    public int getIconWidth() {
        return this.baseIcon.getIconWidth() / 2;
    }

    public int getIconHeight() {
        return this.baseIcon.getIconHeight() / 2;
    }

    public float getMinU() {
        float f = this.baseIcon.getMaxU() - this.baseIcon.getMinU();
        return this.baseIcon.getMinU() + f * this.myX;
    }

    public float getMaxU() {
        float f = this.baseIcon.getMaxU() - this.baseIcon.getMinU();
        return this.baseIcon.getMinU() + f * (this.myX + 0.5f);
    }

    public float getInterpolatedU(double par1) {
        float f = this.getMaxU() - this.getMinU();
        return this.getMinU() + f * (float)par1 / 16.0f;
    }

    public float getMinV() {
        float f = this.baseIcon.getMaxV() - this.baseIcon.getMinV();
        return this.baseIcon.getMinV() + f * this.myY;
    }

    public float getMaxV() {
        float f = this.baseIcon.getMaxV() - this.baseIcon.getMinV();
        return this.baseIcon.getMinV() + f * (this.myY + 0.5f);
    }

    public float getInterpolatedV(double par1) {
        float f = this.getMaxV() - this.getMinV();
        return this.getMinV() + f * (float)par1 / 16.0f;
    }

    public String getIconName() {
        return this.baseIcon.getIconName();
    }
}

