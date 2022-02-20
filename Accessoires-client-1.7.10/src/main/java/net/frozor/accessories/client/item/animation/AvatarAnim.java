/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.item.animation;

import net.frozor.accessories.client.item.animation.AbstractAnimation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class AvatarAnim
extends AbstractAnimation {
    @Override
    public void play(EntityPlayer player, float time) {
        float sin = MathHelper.sin((float)(time * 0.1f)) * 0.1f;
        float cos = MathHelper.cos((float)(time * 0.1f));
        GL11.glTranslatef((float)0.0f, (float)sin, (float)0.0f);
        GL11.glRotatef((float)(cos * 1.0f), (float)0.0f, (float)1.0f, (float)0.0f);
    }
}

