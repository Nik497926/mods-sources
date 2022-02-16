/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class EntityColoredFX
extends EntityFX {
    private float portalParticleScale;
    private double portalPosX;
    private double portalPosY;
    private double portalPosZ;
    public boolean bigger;
    public boolean shortLived;

    public EntityColoredFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, Color c) {
        super(var1, var2, var4, var6, var8, var10, var12);
        this.motionX = var8;
        this.motionY = var10;
        this.motionZ = var12;
        this.portalPosX = this.posX = var2;
        this.portalPosY = this.posY = var4;
        this.portalPosZ = this.posZ = var6;
        float var14 = this.rand.nextFloat() * 0.6f + 0.4f;
        this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 0.2f + 0.5f;
        this.particleRed = (float)c.getRed() / 255.0f;
        this.particleGreen = (float)c.getGreen() / 255.0f;
        this.particleBlue = (float)c.getBlue() / 255.0f;
        this.particleMaxAge = (int)(Math.random() * 10.0) + 40;
        this.noClip = true;
        this.setParticleTextureIndex((int)(Math.random() * 8.0));
    }

    public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
        float var8 = ((float)this.particleAge + var2) / (float)this.particleMaxAge;
        var8 = 1.0f - var8;
        var8 *= var8;
        var8 = 1.0f - var8;
        this.particleScale = this.bigger ? this.portalParticleScale * var8 * 7.0f : this.portalParticleScale * var8;
        if (this.shortLived) {
            this.particleAge += 2;
        }
        super.renderParticle(var1, var2, var3, var4, var5, var6, var7);
    }

    public int getBrightnessForRender(float var1) {
        int var2 = super.getBrightnessForRender(var1);
        float var3 = (float)this.particleAge / (float)this.particleMaxAge;
        var3 *= var3;
        var3 *= var3;
        int var4 = var2 & 0xFF;
        int var5 = var2 >> 16 & 0xFF;
        if ((var5 += (int)(var3 * 15.0f * 16.0f)) > 240) {
            var5 = 240;
        }
        return var4 | var5 << 16;
    }

    public float getBrightness(float var1) {
        float var2 = super.getBrightness(var1);
        float var3 = (float)this.particleAge / (float)this.particleMaxAge;
        var3 = var3 * var3 * var3 * var3;
        return var2 * (1.0f - var3) + var3;
    }

    public void onUpdate() {
        float var1;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        float var2 = var1 = (float)this.particleAge / (float)this.particleMaxAge;
        var1 = -var1 + var1 * var1 * 2.0f;
        var1 = 1.0f - var1;
        this.posX = this.portalPosX + this.motionX * (double)var1;
        this.posY = this.portalPosY + this.motionY * (double)var1 + (double)(1.0f - var2);
        this.posZ = this.portalPosZ + this.motionZ * (double)var1;
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }
    }
}

