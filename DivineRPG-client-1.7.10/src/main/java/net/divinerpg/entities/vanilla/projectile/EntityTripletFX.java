/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla.projectile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class EntityTripletFX
extends EntityFX {
    public float portalParticleScale;

    public EntityTripletFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World, par2, par4, par6, par8, par10, par12);
        this.motionX = par8 + (double)((float)(Math.random() * 2.0 - 1.0) * 0.05f);
        this.motionY = par10 + (double)((float)(Math.random() * 2.0 - 1.0) * 0.05f);
        this.motionZ = par12 + (double)((float)(Math.random() * 2.0 - 1.0) * 0.05f);
        float var14 = this.rand.nextFloat() * 0.6f + 0.4f;
        this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 0.2f + 0.5f;
        this.particleGreen = this.particleBlue = 1.0f * var14;
        this.particleRed = this.particleBlue;
        this.particleGreen *= 0.3f;
        this.particleRed *= 0.9f;
        this.particleScale = this.rand.nextFloat() * this.rand.nextFloat() * 6.0f + 1.0f;
        this.particleMaxAge = (int)(16.0 / ((double)this.rand.nextFloat() * 0.8 + 0.2)) + 2;
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }
        this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
        this.motionY += 0.004;
        this.motionX *= (double)0.9f;
        this.motionY *= (double)0.9f;
        this.motionZ *= (double)0.9f;
        if (this.onGround) {
            this.motionX *= (double)0.7f;
            this.motionZ *= (double)0.7f;
        }
    }
}

