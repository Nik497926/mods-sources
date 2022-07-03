/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui;

import com.meteor.extrabotany.client.gui.GuiDrop;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.proxy.ClientProxy;

public class Drop
extends Gui {
    private int x;
    private int waveX;
    private int y;
    private int index;
    private boolean dead;
    private int friction;
    private int frictionTemp;
    private long initTime = Minecraft.getSystemTime();
    private int rotation;

    public Drop(int screenW, int screenH) {
        this.index = ClientProxy.jingleTheBells ? GuiDrop.RNG.nextInt(2) * 16 : 100;
        this.friction = this.frictionTemp = GuiDrop.RNG.nextInt(5);
        this.x = GuiDrop.RNG.nextInt(screenW + 16) - 16;
        this.y = -16;
    }

    public void draw(int screenW, int screenH, int mouseX, int mouseY) {
        if (!this.isDead()) {
            int posX = mouseX - this.waveX;
            int posY = mouseY - this.y;
            if (Math.abs(posX) < 50 && Math.abs(posY) < 50) {
                int str = (int)Math.round((1.0 - (double)0.02f * Math.sqrt(posX * posX + posY * posY)) * 3.0);
                this.x += posX > 0 ? -str : str;
                this.y += posY > 0 ? -str : str;
                this.waveX += posX > 0 ? -str : str;
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)this.waveX + 7.5f), (float)((float)this.y + 7.5f), (float)0.0f);
            GL11.glRotatef((float)this.rotation, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)-7.5f, (float)-7.5f, (float)0.0f);
            this.drawTexturedModalRect(0, 0, this.index, 0, 16, 16);
            GL11.glScalef((float)0.35f, (float)0.35f, (float)0.35f);
            GL11.glPopMatrix();
            if (this.frictionTemp-- <= 0) {
                this.frictionTemp = this.friction;
                ++this.y;
            }
            this.waveX = this.x - (int)(Math.sin((float)(Minecraft.getSystemTime() - this.initTime) / (200.0f + 100.0f * (float)this.friction)) * 20.0);
            this.rotation += 2;
            if (this.y > screenH) {
                this.setDead();
            }
        }
    }

    public void setDead() {
        this.dead = true;
    }

    public boolean isDead() {
        return this.dead;
    }
}

