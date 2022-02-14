/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.boiling;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelEscapedConvict
extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer arm1;
    public ModelRenderer leg1;
    public ModelRenderer body;
    public ModelRenderer arm2;
    public ModelRenderer leg2;
    public ModelRenderer horn1;
    public ModelRenderer horn2;

    public ModelEscapedConvict() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.leg2 = new ModelRenderer((ModelBase)this, 16, 48);
        this.leg2.setRotationPoint(1.9f, 12.0f, 0.1f);
        this.leg2.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.horn2 = new ModelRenderer((ModelBase)this, 0, 36);
        this.horn2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.horn2.addBox(-8.0f, -10.0f, 0.0f, 2, 4, 2, 0.0f);
        this.arm1 = new ModelRenderer((ModelBase)this, 40, 16);
        this.arm1.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.arm1.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.setRotation(this.arm1, 0.0f, 0.0f, 0.0f);
        this.arm2 = new ModelRenderer((ModelBase)this, 32, 48);
        this.arm2.setRotationPoint(5.0f, 2.0f, -0.0f);
        this.arm2.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.setRotation(this.arm2, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg1.setRotationPoint(-1.9f, 12.0f, 0.1f);
        this.leg1.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.horn1 = new ModelRenderer((ModelBase)this, 0, 36);
        this.horn1.setRotationPoint(0.0f, -0.0f, 0.0f);
        this.horn1.addBox(6.0f, -10.0f, 0.0f, 2, 4, 2, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, -2, 0);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.addBox(-6.0f, -6.6f, -4.0f, 12, 6, 8, 0.5f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.body.render(f5);
        this.arm1.render(f5);
        this.arm2.render(f5);
        this.leg1.render(f5);
        this.leg2.render(f5);
        this.horn1.render(f5);
        this.horn2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.leg1.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.leg2.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.arm1.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.arm2.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.head.rotateAngleY = par4 / 57.295776f;
        this.head.rotateAngleX = par5 / 57.295776f;
        this.horn1.rotateAngleY = par4 / 57.295776f;
        this.horn1.rotateAngleX = par5 / 57.295776f;
        this.horn2.rotateAngleY = par4 / 57.295776f;
        this.horn2.rotateAngleX = par5 / 57.295776f;
    }
}

