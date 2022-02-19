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

public class ModelHusk
extends ModelBase {
    ModelRenderer head;
    ModelRenderer tooth2;
    ModelRenderer tooth1;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer leg2;
    ModelRenderer leg1;
    ModelRenderer body;

    public ModelHusk() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
        this.head.setRotationPoint(0.0f, 4.0f, -7.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.tooth2 = new ModelRenderer((ModelBase)this, 22, 0);
        this.tooth2.addBox(2.0f, -7.0f, 6.0f, 1, 3, 1);
        this.tooth2.setRotationPoint(0.0f, 3.0f, -6.0f);
        this.tooth2.setTextureSize(64, 32);
        this.tooth2.mirror = true;
        this.setRotation(this.tooth2, -3.141593f, 0.0f, 0.0f);
        this.tooth1 = new ModelRenderer((ModelBase)this, 22, 0);
        this.tooth1.addBox(-3.0f, -7.0f, 6.0f, 1, 3, 1);
        this.tooth1.setRotationPoint(0.0f, 3.0f, -6.0f);
        this.tooth1.setTextureSize(64, 32);
        this.tooth1.mirror = true;
        this.setRotation(this.tooth1, -3.141593f, 0.0f, 0.0f);
        this.leg3 = new ModelRenderer((ModelBase)this, 0, 13);
        this.leg3.addBox(-3.0f, 0.0f, -3.0f, 4, 15, 4);
        this.leg3.setRotationPoint(-3.0f, 9.0f, -5.0f);
        this.leg3.setTextureSize(64, 32);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.0f, 0.0f, 0.0f);
        this.leg4 = new ModelRenderer((ModelBase)this, 0, 13);
        this.leg4.addBox(-1.0f, 0.0f, -3.0f, 4, 15, 4);
        this.leg4.setRotationPoint(3.0f, 9.0f, -5.0f);
        this.leg4.setTextureSize(64, 32);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.0f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg2.addBox(-1.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leg2.setRotationPoint(3.0f, 12.0f, 5.0f);
        this.leg2.setTextureSize(64, 32);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg1.addBox(-3.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leg1.setRotationPoint(-3.0f, 12.0f, 5.0f);
        this.leg1.setTextureSize(64, 32);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 18, 4);
        this.body.addBox(-6.0f, -10.0f, -7.0f, 12, 18, 10);
        this.body.setRotationPoint(0.0f, 5.0f, 2.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 1.362596f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.tooth2.render(f5);
        this.tooth1.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
        this.leg2.render(f5);
        this.leg1.render(f5);
        this.body.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.leg1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leg2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.leg3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leg4.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
    }
}

