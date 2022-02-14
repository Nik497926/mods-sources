/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.arcana.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelRejuvGolem
extends ModelBase {
    ModelRenderer head;
    ModelRenderer rightshoulder;
    ModelRenderer leftshoulder;
    ModelRenderer middle;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;

    public ModelRejuvGolem() {
        this.textureWidth = 128;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 4, 8, 8);
        this.head.setRotationPoint(2.0f, 7.0f, 0.0f);
        this.head.setTextureSize(128, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.rightshoulder = new ModelRenderer((ModelBase)this, 73, 3);
        this.rightshoulder.addBox(-4.0f, -6.0f, -3.0f, 6, 5, 6);
        this.rightshoulder.setRotationPoint(-6.0f, 11.0f, 0.0f);
        this.rightshoulder.setTextureSize(128, 32);
        this.rightshoulder.mirror = true;
        this.setRotation(this.rightshoulder, -0.6981317f, 0.0f, 0.0f);
        this.leftshoulder = new ModelRenderer((ModelBase)this, 73, 3);
        this.leftshoulder.addBox(-2.0f, -6.0f, -3.0f, 6, 5, 6);
        this.leftshoulder.setRotationPoint(6.0f, 11.0f, 0.0f);
        this.leftshoulder.setTextureSize(128, 32);
        this.leftshoulder.mirror = true;
        this.setRotation(this.leftshoulder, -0.6981317f, 0.0f, 0.0f);
        this.middle = new ModelRenderer((ModelBase)this, 68, 18);
        this.middle.addBox(-4.0f, 0.0f, -2.0f, 8, 5, 8);
        this.middle.setRotationPoint(0.0f, 7.0f, -2.0f);
        this.middle.setTextureSize(128, 32);
        this.middle.mirror = true;
        this.setRotation(this.middle, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 25, 10);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 10, 12, 10);
        this.body.setRotationPoint(-1.0f, 12.0f, -3.0f);
        this.body.setTextureSize(128, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.setRotationPoint(-6.0f, 11.0f, 0.0f);
        this.rightarm.setTextureSize(128, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, -0.6981317f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.setRotationPoint(6.0f, 11.0f, 0.0f);
        this.leftarm.setTextureSize(128, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, -0.6981317f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.rightshoulder.render(f5);
        this.leftshoulder.render(f5);
        this.middle.render(f5);
        this.body.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.rightarm.rotateAngleX = this.rightshoulder.rotateAngleX = MathHelper.cos((float)f) * f1 * 0.75f - 0.6981317f;
        this.leftarm.rotateAngleX = this.leftshoulder.rotateAngleX = MathHelper.cos((float)(f + (float)Math.PI)) * f1 * 0.75f - 0.6981317f;
    }
}

