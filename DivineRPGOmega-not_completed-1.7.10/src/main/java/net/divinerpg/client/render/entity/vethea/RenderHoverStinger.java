/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.entity.vethea;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.client.render.RenderDivineMob;
import net.divinerpg.entities.vethea.EntityHoverStinger;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderHoverStinger
extends RenderDivineMob {
    private float scale = 0.35f;

    public RenderHoverStinger(ModelBase var1, float var2, ResourceLocation loc) {
        super(var1, var2, loc);
    }

    public void renderHoverStinger(EntityHoverStinger var1, double var2, double var4, double var6, float var8, float var9) {
        super.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
    }

    public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
        this.renderHoverStinger((EntityHoverStinger)var1, var2, var4, var6, var8, var9);
    }

    protected void preRenderScale(EntityHoverStinger var1, float var2) {
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
    }

    @Override
    public void preRenderCallback(EntityLivingBase var1, float var2) {
        this.preRenderScale((EntityHoverStinger)var1, var2);
    }

    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
        this.renderHoverStinger((EntityHoverStinger)var1, var2, var4, var6, var8, var9);
    }
}

