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
package net.divinerpg.entities.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.entities.lelyetia.EntityGrobbler;
import net.divinerpg.entities.lelyetia.modelGrobbler;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderGrobbler
extends RenderLiving {
    private static final ResourceLocation EntityTexture = new ResourceLocation("divinerpg:textures/mobs/grobbler.png");
    protected modelGrobbler model;
    private float scale;

    public RenderGrobbler(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
        this.model = (modelGrobbler)this.mainModel;
        this.scale = 2.0f;
    }

    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        this.preRenderCallback((EntityGrobbler)par1EntityLivingBase, par2);
    }

    protected void preRenderCallback(EntityGrobbler par1EntityVoliant, float par2) {
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return EntityTexture;
    }
}

