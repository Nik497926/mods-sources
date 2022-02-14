/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class OrangeTrail
extends EntityFX {
    public float portalParticleScale;
    public int last;

    public OrangeTrail(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, int age) {
        super(par1World, par2, par4, par6, par8, par10, par12);
        float n;
        this.last = age;
        ((Entity)this).motionX = par8 + (double)((float)(Math.random() * 2.0 - 1.0) * 0.05f);
        ((Entity)this).motionY = par10 + (double)((float)(Math.random() * 2.0 - 1.0) * 0.05f);
        ((Entity)this).motionZ = par12 + (double)((float)(Math.random() * 2.0 - 1.0) * 0.05f);
        float var14 = this.rand.nextFloat() * 0.6f + 0.4f;
        this.particleScale = n = this.rand.nextFloat() * 0.2f + 0.5f;
        this.portalParticleScale = n;
        this.particleRed *= 0.9f;
        this.particleGreen *= 0.6f;
        this.particleBlue *= 0.0f;
        this.particleScale = this.rand.nextFloat() * this.rand.nextFloat() * 6.0f + 1.0f;
        this.particleMaxAge = (int)(3.0 / (double)this.rand.nextFloat() * 0.8 + 0.2);
    }

    public void onUpdate() {
        ((Entity)this).prevPosX = ((Entity)this).posX;
        ((Entity)this).prevPosY = ((Entity)this).posY;
        ((Entity)this).prevPosZ = ((Entity)this).posZ;
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }
        this.setParticleTextureIndex(7 - this.particleAge * this.last / this.particleMaxAge);
        ((Entity)this).motionY += 0.004;
        ((Entity)this).motionX *= (double)0.9f;
        ((Entity)this).motionY *= (double)0.9f;
        ((Entity)this).motionZ *= (double)0.9f;
        if (((Entity)this).onGround) {
            ((Entity)this).motionX *= (double)0.7f;
            ((Entity)this).motionZ *= (double)0.7f;
        }
    }
}

