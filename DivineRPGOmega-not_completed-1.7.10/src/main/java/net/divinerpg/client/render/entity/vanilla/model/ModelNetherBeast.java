/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.vanilla.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelNetherBeast
extends ModelBase {
    public ModelRenderer body1;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg1a;
    public ModelRenderer leg2_1;
    public ModelRenderer head;
    public ModelRenderer horn1;
    public ModelRenderer horn2;
    public ModelRenderer body2;

    public ModelNetherBeast() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.setRotationPoint(0.0f, 5.0f, -8.0f);
        this.head.addBox(-4.0f, 0.0f, -4.2f, 8, 8, 6, 0.0f);
        this.body1 = new ModelRenderer((ModelBase)this, 18, 16);
        this.body1.setRotationPoint(0.0f, 15.1f, -1.0f);
        this.body1.addBox(-6.0f, -10.0f, -7.0f, 12, 12, 10, 0.0f);
        this.setRotation(this.body1, -0.13665928f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 45, 45);
        this.leg2.setRotationPoint(4.6f, 12.0f, -5.2f);
        this.leg2.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.horn2 = new ModelRenderer((ModelBase)this, 40, 0);
        this.horn2.setRotationPoint(0.0f, 5.0f, -8.0f);
        this.horn2.addBox(3.0f, -4.0f, -4.0f, 2, 6, 2, 0.0f);
        this.setRotation(this.horn2, 0.41887903f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer((ModelBase)this, 0, 40);
        this.body2.setRotationPoint(-5.0f, 6.5f, 0.0f);
        this.body2.addBox(0.0f, 0.0f, 0.0f, 10, 10, 11, 0.0f);
        this.setRotation(this.body2, -0.12409291f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg1.setRotationPoint(4.0f, 12.0f, 7.0f);
        this.leg1.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.leg1a = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg1a.setRotationPoint(-4.0f, 12.0f, 7.0f);
        this.leg1a.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.leg2_1 = new ModelRenderer((ModelBase)this, 45, 45);
        this.leg2_1.setRotationPoint(-4.6f, 12.0f, -5.2f);
        this.leg2_1.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.horn1 = new ModelRenderer((ModelBase)this, 40, 0);
        this.horn1.setRotationPoint(0.0f, 5.0f, -8.0f);
        this.horn1.addBox(-5.0f, -4.0f, -4.0f, 2, 6, 2, 0.0f);
        this.setRotation(this.horn1, 0.41887903f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.body1.render(f5);
        this.leg2.render(f5);
        this.horn2.render(f5);
        this.body2.render(f5);
        this.leg1.render(f5);
        this.leg1a.render(f5);
        this.leg2_1.render(f5);
        this.horn1.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.rotateAngleY = par4 / 57.295776f;
        this.head.rotateAngleX = par5 / 57.295776f;
        this.horn1.rotateAngleY = par4 / 57.295776f;
        this.horn1.rotateAngleX = par5 / 57.295776f;
        this.horn2.rotateAngleY = par4 / 57.295776f;
        this.horn2.rotateAngleX = par5 / 57.295776f;
        this.leg1.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.leg1a.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.leg2.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.leg2_1.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
    }
}

