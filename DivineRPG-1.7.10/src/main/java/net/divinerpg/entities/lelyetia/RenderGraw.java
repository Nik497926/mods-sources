/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.entities.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.entities.boil.EternalBossStatus;
import net.divinerpg.entities.lelyetia.EntityGraw;
import net.divinerpg.entities.lelyetia.modelGraw;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderGraw
extends RenderLiving {
    private static final ResourceLocation EntityTexture = new ResourceLocation("divinerpg:textures/mobs/graw.png");
    protected modelGraw model;
    private float scale;

    public RenderGraw(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
        this.model = (modelGraw)this.mainModel;
        this.scale = 3.0f;
    }

    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        this.preRenderCallback((EntityGraw)par1EntityLivingBase, par2);
    }

    public void renderGraw(EntityGraw var1, double var2, double var4, double var6, float var8, float var9) {
        EternalBossStatus.setBossStatus((EntityLiving)var1, true, 32);
        super.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
    }

    public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
        this.renderGraw((EntityGraw)var1, var2, var4, var6, var8, var9);
    }

    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
        this.renderGraw((EntityGraw)var1, var2, var4, var6, var8, var9);
    }

    protected void preRenderCallback(EntityGraw par1EntityVoliant, float par2) {
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return EntityTexture;
    }
}

