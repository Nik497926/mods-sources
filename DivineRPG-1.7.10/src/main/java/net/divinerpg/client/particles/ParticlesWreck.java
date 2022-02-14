/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EntityPortalFX
 *  net.minecraft.world.World
 */
package net.divinerpg.client.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class ParticlesWreck
extends EntityPortalFX {
    public ParticlesWreck(World parWorld, double parX, double parY, double parZ, double parMotionX, double parMotionY, double parMotionZ) {
        super(parWorld, parX, parY, parZ, parMotionX, parMotionY, parMotionZ);
        this.setParticleTextureIndex((int)(Math.random() * 7.0) + 160);
        this.particleScale = 0.9f;
        this.setRBGColorF(0.2f, 0.0f, 0.8f);
    }
}

