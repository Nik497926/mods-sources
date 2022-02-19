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

public class ModelWitherspine
extends ModelBase {
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer body;
    public ModelRenderer spine1;
    public ModelRenderer spine2;
    public ModelRenderer rib3;
    public ModelRenderer spineNeck;
    public ModelRenderer head;
    public ModelRenderer rib1;
    public ModelRenderer rip2;
    public ModelRenderer rib3_1;

    public ModelWitherspine() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.leg2 = new ModelRenderer((ModelBase)this, 0, 14);
        this.leg2.setRotationPoint(5.0f, 12.0f, 0.0f);
        this.leg2.addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6, 0.0f);
        this.spine2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.spine2.setRotationPoint(-1.5f, -12.6f, 6.0f);
        this.spine2.addBox(0.0f, 0.0f, 0.0f, 3, 10, 3, 0.0f);
        this.setRotateAngle(this.spine2, -0.10471976f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 12, 0);
        this.body.setRotationPoint(-9.0f, 6.0f, -4.0f);
        this.body.addBox(0.0f, 0.0f, 0.0f, 18, 6, 8, 0.0f);
        this.rib1 = new ModelRenderer((ModelBase)this, 26, 20);
        this.rib1.setRotationPoint(-8.0f, -1.4f, 2.4f);
        this.rib1.addBox(0.0f, 0.0f, 0.0f, 16, 3, 2, 0.0f);
        this.setRotateAngle(this.rib1, -0.4537856f, 0.0f, 0.0f);
        this.spine1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.spine1.setRotationPoint(-1.5f, -3.0f, 5.0f);
        this.spine1.addBox(0.0f, 0.0f, 0.0f, 3, 10, 3, 0.0f);
        this.setRotateAngle(this.spine1, -0.4537856f, -0.0f, 0.0f);
        this.rip2 = new ModelRenderer((ModelBase)this, 26, 20);
        this.rip2.setRotationPoint(-8.0f, -10.0f, 3.8f);
        this.rip2.addBox(0.0f, 0.0f, 0.0f, 16, 3, 2, 0.0f);
        this.setRotateAngle(this.rip2, -0.10471976f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 14);
        this.leg1.setRotationPoint(-5.0f, 12.0f, 0.0f);
        this.leg1.addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6, 0.0f);
        this.spineNeck = new ModelRenderer((ModelBase)this, 0, 0);
        this.spineNeck.setRotationPoint(-1.5f, -29.4f, -1.5f);
        this.spineNeck.addBox(0.0f, 0.0f, 0.0f, 3, 10, 3, 0.0f);
        this.setRotateAngle(this.spineNeck, 0.6981317f, 0.0f, 0.0f);
        this.rib3_1 = new ModelRenderer((ModelBase)this, 26, 20);
        this.rib3_1.setRotationPoint(-8.0f, -19.0f, 3.2f);
        this.rib3_1.addBox(0.0f, 0.0f, 0.0f, 16, 3, 2, 0.0f);
        this.setRotateAngle(this.rib3_1, 0.12217305f, 0.0f, 0.0f);
        this.rib3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.rib3.setRotationPoint(-1.5f, -22.5f, 4.8f);
        this.rib3.addBox(0.0f, 0.0f, 0.0f, 3, 10, 3, 0.0f);
        this.setRotateAngle(this.rib3, 0.12217305f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 32);
        this.head.setRotationPoint(0.0f, -29.0f, 0.0f);
        this.head.addBox(-5.0f, -5.0f, -8.0f, 10, 10, 10, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.leg2.render(f5);
        this.spine2.render(f5);
        this.body.render(f5);
        this.rib1.render(f5);
        this.spine1.render(f5);
        this.rip2.render(f5);
        this.leg1.render(f5);
        this.spineNeck.render(f5);
        this.rib3_1.render(f5);
        this.rib3.render(f5);
        this.head.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.head.rotateAngleY = f3 / 57.29578f;
        this.head.rotateAngleX = f4 / 57.29578f;
        this.leg1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * f1;
        this.leg2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * f1;
    }
}

