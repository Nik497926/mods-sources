/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderDivineMob
extends RenderLiving {
    private ResourceLocation texture;
    private float scale = 1.0f;

    public RenderDivineMob(ModelBase model, float shadowSize, ResourceLocation tex) {
        super(model, shadowSize);
        this.texture = tex;
    }

    public RenderDivineMob(ModelBase model, float shadowSize, float scale, ResourceLocation tex) {
        super(model, shadowSize);
        this.texture = tex;
        this.scale = scale;
    }

    public RenderDivineMob(ModelBase model, ResourceLocation tex) {
        this(model, 0.0f, tex);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.texture;
    }

    public void preRenderCallback(EntityLivingBase entity, float f) {
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
    }
}

