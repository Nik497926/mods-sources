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

public class ModelRockiteGolem
extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer lowerBody;
    public ModelRenderer arm1;
    public ModelRenderer arm2;
    public ModelRenderer leg1;
    public ModelRenderer head;
    public ModelRenderer leg2;

    public ModelRockiteGolem() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.arm1 = new ModelRenderer((ModelBase)this, 60, 58);
        this.arm1.setRotationPoint(0.0f, -7.0f, 0.0f);
        this.arm1.addBox(9.0f, -3.0f, -3.0f, 4, 22, 6, 0.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 60, 0);
        this.leg2.mirror = true;
        this.leg2.setRotationPoint(5.0f, 11.0f, 0.0f);
        this.leg2.addBox(-3.5f, -3.0f, -3.0f, 6, 16, 5, 0.0f);
        this.arm2 = new ModelRenderer((ModelBase)this, 60, 21);
        this.arm2.setRotationPoint(0.0f, -7.0f, 0.0f);
        this.arm2.addBox(-13.0f, -3.0f, -3.0f, 4, 22, 6, 0.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 37, 0);
        this.leg1.setRotationPoint(-4.0f, 11.0f, 0.0f);
        this.leg1.addBox(-3.5f, -3.0f, -3.0f, 6, 16, 5, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 40);
        this.body.setRotationPoint(0.0f, -7.0f, 0.0f);
        this.body.addBox(-9.0f, -2.0f, -6.0f, 18, 12, 11, 0.0f);
        this.lowerBody = new ModelRenderer((ModelBase)this, 0, 70);
        this.lowerBody.setRotationPoint(-0.5f, -7.0f, 0.0f);
        this.lowerBody.addBox(-4.5f, 10.0f, -3.5f, 10, 7, 6, 0.5f);
        this.head = new ModelRenderer((ModelBase)this, 0, 100);
        this.head.setRotationPoint(0.0f, -7.0f, -2.0f);
        this.head.addBox(-8.0f, -6.0f, -8.0f, 16, 10, 8, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.arm1.render(f5);
        this.leg2.render(f5);
        this.arm2.render(f5);
        this.leg1.render(f5);
        this.body.render(f5);
        this.lowerBody.render(f5);
        this.head.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.head.rotateAngleY = f4 / 57.295776f;
        this.head.rotateAngleX = f5 / 57.295776f;
        this.leg1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.leg2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.arm1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.arm2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
    }
}

