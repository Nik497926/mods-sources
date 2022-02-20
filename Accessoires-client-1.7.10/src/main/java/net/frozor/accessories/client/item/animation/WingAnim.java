/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.item.animation;

import net.frozor.accessories.client.item.animation.AbstractAnimation;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class WingAnim
extends AbstractAnimation {
    private long lastTime;
    private float wings = 0.0f;
    private float rotation;
    private boolean out;

    public void apply() {
        GL11.glRotatef((float)this.wings, (float)0.0f, (float)1.0f, (float)0.0f);
    }

    public float getWings() {
        return this.wings;
    }

    @Override
    public void play(EntityPlayer player, float time) {
        long nanoTime = System.nanoTime();
        double deltaTime = (double)(nanoTime - this.lastTime) / 1.0E9;
        this.lastTime = nanoTime;
        this.rotation = (float)((double)this.rotation + deltaTime * 40.0);
        if (this.out) {
            this.wings = (float)Math.min((double)this.wings + deltaTime * 5.0, 9.0);
            if ((double)this.wings >= 9.0) {
                this.out = false;
            }
        } else {
            this.wings = (float)Math.max((double)this.wings - deltaTime * 10.0, 0.0);
            if ((double)this.wings <= 0.0) {
                this.out = true;
            }
        }
    }
}

