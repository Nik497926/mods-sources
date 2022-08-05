/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.entity;

import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.ARBShaderObjects;
import vazkii.botania.api.boss.IBotaniaBoss;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.client.core.handler.BossBarHandler;
import vazkii.botania.client.core.helper.ShaderHelper;

@SideOnly(value= Side.CLIENT)
public class RenderGaiaIII
extends RenderBiped {
    public static float DEFAULT_GRAIN_INTENSITY = 0.05f;
    public static float DEFAULT_DISFIGURATION = 0.025f;
    public static float grainIntensity = DEFAULT_GRAIN_INTENSITY;
    public static float disfiguration = DEFAULT_DISFIGURATION;
    public static ShaderCallback callback = new ShaderCallback(){

        public void call(int shader) {
            int disfigurationUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"disfiguration");
            ARBShaderObjects.glUniform1fARB((int)disfigurationUniform, (float)disfiguration);
            int grainIntensityUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"grainIntensity");
            ARBShaderObjects.glUniform1fARB((int)grainIntensityUniform, (float)grainIntensity);
        }
    };
    public static ShaderCallback defaultCallback = new ShaderCallback(){

        public void call(int shader) {
            int disfigurationUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"disfiguration");
            ARBShaderObjects.glUniform1fARB((int)disfigurationUniform, (float)DEFAULT_DISFIGURATION);
            int grainIntensityUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"grainIntensity");
            ARBShaderObjects.glUniform1fARB((int)grainIntensityUniform, (float)DEFAULT_GRAIN_INTENSITY);
        }
    };

    public RenderGaiaIII() {
        super(new ModelBiped(0.5f), 0.0f);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        EntityGaiaIII dopple = (EntityGaiaIII)par1Entity;
        BossBarHandler.setCurrentBoss((IBotaniaBoss)dopple);
        int invulTime = dopple.getInvulTime();
        if (invulTime > 0) {
            grainIntensity = invulTime > 20 ? 1.0f : (float)invulTime * 0.05f;
            disfiguration = grainIntensity * 0.3f;
        } else {
            disfiguration = (0.025f + (float)dopple.hurtTime * 0.0425f) / 2.0f;
            grainIntensity = 0.05f + (float)dopple.hurtTime * 0.085f;
        }
        ShaderHelper.useShader((int)ShaderHelper.doppleganger, (ShaderCallback)callback);
        super.doRender(par1Entity, par2, par4, par6, par8, par9);
        ShaderHelper.releaseShader();
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return Minecraft.getMinecraft().thePlayer.getLocationSkin();
    }
}

