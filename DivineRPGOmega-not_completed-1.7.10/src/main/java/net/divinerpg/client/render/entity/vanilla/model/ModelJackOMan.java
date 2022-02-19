/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.entity.vanilla.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelJackOMan
extends ModelBiped {
    public ModelJackOMan() {
        this.textureHeight = 128;
        this.textureWidth = 64;
        this.bipedHead = new ModelRenderer((ModelBase)this, 0, 96);
        this.bipedHead.addBox(-8.0f, -16.0f, -8.0f, 16, 16, 16, 2.0f);
        this.bipedHead.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedCloak = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedCloak.addBox(-5.0f, 0.0f, -1.0f, 10, 16, 1, 0.0f);
        this.bipedEars = new ModelRenderer((ModelBase)this, 24, 0);
        this.bipedEars.addBox(-3.0f, -6.0f, -1.0f, 6, 6, 1, 0.0f);
        this.bipedHeadwear = new ModelRenderer((ModelBase)this, 32, 0);
        this.bipedHeadwear.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.5f);
        this.bipedHeadwear.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedBody = new ModelRenderer((ModelBase)this, 16, 16);
        this.bipedBody.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        this.bipedBody.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedRightArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.bipedRightArm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.bipedRightArm.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.bipedLeftArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.bipedLeftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.bipedRightLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedRightLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.bipedRightLeg.setRotationPoint(-1.9f, 12.0f, 0.0f);
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.bipedLeftLeg.setRotationPoint(1.9f, 12.0f, 0.0f);
    }

    public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, entity);
        this.bipedBody.render(par7);
        this.bipedRightArm.render(par7);
        this.bipedLeftArm.render(par7);
        this.bipedRightLeg.render(par7);
        this.bipedLeftLeg.render(par7);
        this.bipedHeadwear.render(par7);
        GL11.glPushMatrix();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        this.bipedHead.render(par7);
        GL11.glPopMatrix();
    }
}

