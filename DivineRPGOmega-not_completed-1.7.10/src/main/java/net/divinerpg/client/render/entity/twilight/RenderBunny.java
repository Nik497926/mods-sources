/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.entity.twilight;

import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.client.render.entity.twilight.model.ModelBunny;
import net.divinerpg.entities.twilight.EntityBunny;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderBunny
extends RenderLiving {
    private static final ResourceLocation bunny = EntityResourceLocation.bunny;
    private static final ResourceLocation bunnyTame = EntityResourceLocation.bunnyTamed;
    private static final ResourceLocation angryBunnyTame = EntityResourceLocation.angryBunnyTamed;
    private float scale = 1.0f;

    public RenderBunny(ModelBase par1ModelBase, float shadowSize) {
        super(par1ModelBase, shadowSize);
    }

    protected ResourceLocation getEntityTexture(Entity e) {
        this.mainModel = new ModelBunny();
        EntityBunny b = (EntityBunny)e;
        ResourceLocation tex = bunny;
        this.scale = 1.0f;
        if (b.isTamed()) {
            if (b.getDataWatcher().getWatchableObjectInt(19) == 1) {
                tex = angryBunnyTame;
                this.scale = 1.2f;
            } else {
                tex = bunnyTame;
            }
        }
        return tex;
    }

    protected void preRenderCallback(EntityLivingBase e, float partialTickTime) {
        super.preRenderCallback(e, partialTickTime);
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
    }
}

