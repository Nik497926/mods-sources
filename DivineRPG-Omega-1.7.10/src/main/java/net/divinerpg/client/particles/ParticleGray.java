/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityPortalFX
 *  net.minecraft.world.World
 */
package net.divinerpg.client.particles;

import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.world.World;

public class ParticleGray
extends EntityPortalFX {
    public ParticleGray(World parWorld, double parX, double parY, double parZ, double parMotionX, double parMotionY, double parMotionZ) {
        super(parWorld, parX, parY, parZ, parMotionX, parMotionY, parMotionZ);
        this.setParticleTextureIndex((int)(Math.random() * 7.0) + 160);
        this.particleScale = 0.7f;
        this.setRBGColorF(0.33f, 0.33f, 0.33f);
    }
}

