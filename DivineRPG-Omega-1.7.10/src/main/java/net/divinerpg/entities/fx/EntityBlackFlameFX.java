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
public class EntityBlackFlameFX
extends EntityFX {
    private float flameScale;

    public EntityBlackFlameFX(World p_i1209_1_, double p_i1209_2_, double p_i1209_4_, double p_i1209_6_, double p_i1209_8_, double p_i1209_10_, double p_i1209_12_) {
        super(p_i1209_1_, p_i1209_2_, p_i1209_4_, p_i1209_6_, p_i1209_8_, p_i1209_10_, p_i1209_12_);
        this.motionX = this.motionX * (double)0.01f + p_i1209_8_;
        this.motionY = this.motionY * (double)0.01f + p_i1209_10_;
        this.motionZ = this.motionZ * (double)0.01f + p_i1209_12_;
        double d6 = p_i1209_2_ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05f);
        d6 = p_i1209_4_ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05f);
        d6 = p_i1209_6_ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05f);
        this.flameScale = this.particleScale;
        this.particleBlue = 0.0f;
        this.particleGreen = 0.0f;
        this.particleRed = 0.0f;
        this.particleMaxAge = (int)(8.0 / (Math.random() * 0.8 + 0.2)) + 4;
        this.noClip = true;
        this.setParticleTextureIndex(48);
    }

    public void renderParticle(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {
        float f6 = ((float)this.particleAge + p_70539_2_) / (float)this.particleMaxAge;
        this.particleScale = this.flameScale * (1.0f - f6 * f6 * 0.5f);
        super.renderParticle(p_70539_1_, p_70539_2_, p_70539_3_, p_70539_4_, p_70539_5_, p_70539_6_, p_70539_7_);
    }

    public int getBrightnessForRender(float p_70070_1_) {
        float f1 = ((float)this.particleAge + p_70070_1_) / (float)this.particleMaxAge;
        if (f1 < 0.0f) {
            f1 = 0.0f;
        }
        if (f1 > 1.0f) {
            f1 = 1.0f;
        }
        int i = super.getBrightnessForRender(p_70070_1_);
        int j = i & 0xFF;
        int k = i >> 16 & 0xFF;
        if ((j += (int)(f1 * 15.0f * 16.0f)) > 240) {
            j = 240;
        }
        return j | k << 16;
    }

    public float getBrightness(float p_70013_1_) {
        float f1 = ((float)this.particleAge + p_70013_1_) / (float)this.particleMaxAge;
        if (f1 < 0.0f) {
            f1 = 0.0f;
        }
        if (f1 > 1.0f) {
            f1 = 1.0f;
        }
        float f2 = super.getBrightness(p_70013_1_);
        return f2 * f1 + (1.0f - f1);
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= (double)0.96f;
        this.motionY *= (double)0.96f;
        this.motionZ *= (double)0.96f;
        if (this.onGround) {
            this.motionX *= (double)0.7f;
            this.motionZ *= (double)0.7f;
        }
    }
}

