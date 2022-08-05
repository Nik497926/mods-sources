/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.entity;

import com.meteor.extrabotany.client.model.ModelAsgard;
import com.meteor.extrabotany.common.entity.EntityAsgard;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(value= Side.CLIENT)
public class RenderAsgardNpc
extends RenderLiving {
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("extrabotania", "textures/entities/asgard.png");

    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE_URL;
    }

    public RenderAsgardNpc() {
        super((ModelBase)new ModelAsgard(), 0.5f);
    }

    protected void rotateAsgardCorpse(EntityAsgard entity, float par2, float par3, float par4) {
        super.rotateCorpse((EntityLivingBase)entity, par2, par3, par4);
    }

    public void doRender(EntityLiving entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderAsgard((EntityAsgard)entity, par2, par4, par6, par8, par9);
    }

    protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        this.rotateAsgardCorpse((EntityAsgard)par1EntityLivingBase, par2, par3, par4);
    }

    public void doRender(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderAsgard((EntityAsgard)par1, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderAsgard((EntityAsgard)entity, par2, par4, par6, par8, par9);
    }

    public void doRenderAsgard(EntityAsgard par1Entity, double par2, double par4, double par6, float par8, float par9) {
        super.doRender((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
    }
}

