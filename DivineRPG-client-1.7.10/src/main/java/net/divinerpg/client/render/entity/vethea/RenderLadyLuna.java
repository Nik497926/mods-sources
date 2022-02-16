/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.util.ResourceLocation
 */
package net.divinerpg.client.render.entity.vethea;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.client.render.RenderDivineMob;
import net.divinerpg.client.render.entity.vethea.model.LadyLuna;
import net.divinerpg.entities.base.DivineBossStatus;
import net.divinerpg.entities.vethea.EntityLadyLuna;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

@SideOnly(value=Side.CLIENT)
public class RenderLadyLuna
extends RenderDivineMob {
    private ModelBase model = new LadyLuna();

    public RenderLadyLuna(ModelBase var1, float var2, ResourceLocation loc) {
        super(var1, var2, loc);
    }

    public void renderLadyLuna(EntityLadyLuna var1, double var2, double var4, double var6, float var8, float var9) {
        this.mainModel = this.model;
        DivineBossStatus.setBossStatus(var1, 21);
        super.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
    }

    public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
        this.renderLadyLuna((EntityLadyLuna)var1, var2, var4, var6, var8, var9);
    }

    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
        this.renderLadyLuna((EntityLadyLuna)var1, var2, var4, var6, var8, var9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        EntityLadyLuna boss = (EntityLadyLuna)entity;
        if (boss.getProtectionType() == 1) {
            return EntityResourceLocation.LADY_LUNA_ARCANIC;
        }
        if (boss.getProtectionType() == 2) {
            return EntityResourceLocation.LADY_LUNA_RANGE;
        }
        return EntityResourceLocation.LADY_LUNA_MELEE;
    }
}

