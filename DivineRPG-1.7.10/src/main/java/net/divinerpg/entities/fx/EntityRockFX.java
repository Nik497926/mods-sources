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
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class EntityRockFX
extends EntityFX {
    private float portalParticleScale;
    private double portalPosX;
    private double portalPosY;
    private double portalPosZ;

    public EntityRockFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
        super(var1, var2, var4, var6, var8, var10, var12);
        this.motionX = var8;
        this.motionY = var10;
        this.motionZ = var12;
        this.portalPosX = this.posX = var2;
        this.portalPosY = this.posY = var4;
        this.portalPosZ = this.posZ = var6;
        float var14 = this.rand.nextFloat() * 0.6f + 1.2f;
        this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 0.2f + 0.5f;
        this.particleBlue = 0.3f;
        this.particleGreen = 0.3f;
        this.particleRed = 0.3f;
        this.particleMaxAge = (int)(Math.random() * 10.0) + 40;
        this.noClip = true;
        this.setParticleTextureIndex((int)(Math.random() * 8.0));
    }

    public int getFXLayer() {
        return 0;
    }

    public void renderParticle(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {
        float var8 = ((float)this.particleAge + p_70539_2_) / (float)this.particleMaxAge * 3.0f;
        var8 = 1.0f - var8;
        var8 *= var8;
        var8 = 1.0f - var8;
        this.particleScale = this.portalParticleScale * var8;
        super.renderParticle(p_70539_1_, p_70539_2_, p_70539_3_, p_70539_4_, p_70539_5_, p_70539_6_, p_70539_7_);
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

