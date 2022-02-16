/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package net.divinerpg.client.render.entity.vanilla;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(value=Side.CLIENT)
public class RenderModBiped
extends RenderBiped {
    private ResourceLocation texture;

    public RenderModBiped(ModelBase model, ResourceLocation tex) {
        super((ModelBiped)model, 0.5f);
        this.texture = tex;
    }

    protected ResourceLocation getEntityTexture() {
        return this.texture;
    }

    public void renderMob(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
        super.doRender(var1, var2, var4, var6, var8, var9);
    }

    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
        this.renderMob((EntityLivingBase)var1, var2, var4, var6, var8, var9);
    }

    protected ResourceLocation getEntityTexture(EntityLiving par1EntityLiving) {
        return this.getEntityTexture();
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.getEntityTexture();
    }
}

