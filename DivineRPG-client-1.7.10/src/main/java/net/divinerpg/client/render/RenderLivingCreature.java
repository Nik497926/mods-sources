/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package net.divinerpg.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLivingCreature
extends RenderLiving {
    private ResourceLocation texture;

    public RenderLivingCreature(ModelBase par1ModelBase, float shadowSize, ResourceLocation resourceLoc) {
        super(par1ModelBase, shadowSize);
        this.texture = resourceLoc;
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.texture;
    }
}

