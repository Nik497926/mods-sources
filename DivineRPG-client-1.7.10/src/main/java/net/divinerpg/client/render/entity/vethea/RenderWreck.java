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
 *  net.minecraft.util.ResourceLocation
 */
package net.divinerpg.client.render.entity.vethea;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.client.render.entity.vethea.model.WreckForm1;
import net.divinerpg.client.render.entity.vethea.model.WreckForm2;
import net.divinerpg.client.render.entity.vethea.model.WreckForm3;
import net.divinerpg.entities.base.DivineBossStatus;
import net.divinerpg.entities.vethea.EntityWreck;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

@SideOnly(value=Side.CLIENT)
public class RenderWreck
extends RenderLiving {
    private ModelBase form1 = new WreckForm1();
    private ModelBase form2 = new WreckForm2();
    private ModelBase form3 = new WreckForm3();

    public RenderWreck(ModelBase var1, float var2) {
        super(var1, var2);
    }

    public void renderWreck(EntityWreck entity, double var2, double var4, double var6, float var8, float var9) {
        this.mainModel = entity.getAbilityType() == 0 ? this.form1 : (entity.getAbilityType() == 1 ? this.form2 : this.form3);
        DivineBossStatus.setBossStatus(entity, 22);
        super.doRender((EntityLiving)entity, var2, var4, var6, var8, var9);
    }

    public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
        this.renderWreck((EntityWreck)var1, var2, var4, var6, var8, var9);
    }

    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
        this.renderWreck((EntityWreck)var1, var2, var4, var6, var8, var9);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        EntityWreck wreck = (EntityWreck)entity;
        if (wreck.getAbilityType() == 0) {
            return new ResourceLocation("divinerpg:textures/mobs/wreckMelee.png");
        }
        if (wreck.getAbilityType() == 1) {
            return new ResourceLocation("divinerpg:textures/mobs/wreckArcanic.png");
        }
        if (wreck.getAbilityType() == 2) {
            return new ResourceLocation("divinerpg:textures/mobs/wreckRanged.png");
        }
        return new ResourceLocation("divinerpg:textures/mobs/wreckMelee.png");
    }
}

