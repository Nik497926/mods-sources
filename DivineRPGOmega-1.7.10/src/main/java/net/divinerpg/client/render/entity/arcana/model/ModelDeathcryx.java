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

public class ModelDeathcryx
extends ModelBase {
    ModelRenderer WolfHead;
    ModelRenderer Body;
    ModelRenderer Mane;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Tail;
    ModelRenderer Ear1;
    ModelRenderer Ear2;
    ModelRenderer Nose;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Leg5;
    ModelRenderer Leg6;
    ModelRenderer Leg7;
    ModelRenderer Leg8;
    ModelRenderer Tail2;

    public ModelDeathcryx() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.WolfHead = new ModelRenderer((ModelBase)this, 0, 0);
        this.WolfHead.addBox(-3.0f, -3.0f, -2.0f, 10, 9, 4);
        this.WolfHead.setRotationPoint(-3.0f, 11.5f, -7.0f);
        this.WolfHead.setTextureSize(64, 32);
        this.WolfHead.mirror = true;
        this.setRotation(this.WolfHead, 0.0f, 0.0f, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 18, 14);
        this.Body.addBox(-4.0f, -2.0f, -3.0f, 6, 9, 6);
        this.Body.setRotationPoint(0.0f, 14.0f, 2.0f);
        this.Body.setTextureSize(64, 32);
        this.Body.mirror = true;
        this.setRotation(this.Body, 1.5707964f, 0.0f, 0.0f);
        this.Mane = new ModelRenderer((ModelBase)this, 21, 0);
        this.Mane.addBox(-4.0f, -3.0f, -3.0f, 8, 10, 7);
        this.Mane.setRotationPoint(-1.0f, 14.0f, -2.0f);
        this.Mane.setTextureSize(64, 32);
        this.Mane.mirror = true;
        this.setRotation(this.Mane, 1.5707964f, 0.0f, 0.0f);
        this.Leg1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg1.addBox(-1.0f, 0.0f, -1.0f, 3, 8, 3);
        this.Leg1.setRotationPoint(-5.5f, 16.0f, 4.0f);
        this.Leg1.setTextureSize(64, 32);
        this.Leg1.mirror = true;
        this.setRotation(this.Leg1, 0.0f, 0.0f, 0.0f);
        this.Leg2 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg2.addBox(-1.0f, 0.0f, -1.0f, 3, 8, 3);
        this.Leg2.setRotationPoint(2.5f, 16.0f, 4.0f);
        this.Leg2.setTextureSize(64, 32);
        this.Leg2.mirror = true;
        this.setRotation(this.Leg2, 0.0f, 0.0f, 0.0f);
        this.Leg3 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg3.addBox(-1.0f, 0.0f, -1.0f, 3, 8, 3);
        this.Leg3.setRotationPoint(-6.5f, 16.0f, -7.0f);
        this.Leg3.setTextureSize(64, 32);
        this.Leg3.mirror = true;
        this.setRotation(this.Leg3, 0.0f, 0.0f, 0.0f);
        this.Leg4 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg4.addBox(-1.0f, 0.0f, -1.0f, 3, 8, 3);
        this.Leg4.setRotationPoint(3.5f, 16.0f, -7.0f);
        this.Leg4.setTextureSize(64, 32);
        this.Leg4.mirror = true;
        this.setRotation(this.Leg4, 0.0f, 0.0f, 0.0f);
        this.Tail = new ModelRenderer((ModelBase)this, 22, 13);
        this.Tail.addBox(-1.0f, 0.0f, -1.0f, 4, 12, 4);
        this.Tail.setRotationPoint(1.0f, 15.0f, 7.0f);
        this.Tail.setTextureSize(64, 32);
        this.Tail.mirror = true;
        this.setRotation(this.Tail, 1.130069f, 0.0f, 0.0f);
        this.Ear1 = new ModelRenderer((ModelBase)this, 16, 14);
        this.Ear1.addBox(-3.0f, -5.0f, 0.0f, 2, 4, 1);
        this.Ear1.setRotationPoint(-2.0f, 9.9f, -7.0f);
        this.Ear1.setTextureSize(64, 32);
        this.Ear1.mirror = true;
        this.setRotation(this.Ear1, 0.3490659f, 0.0f, 0.0f);
        this.Ear2 = new ModelRenderer((ModelBase)this, 16, 14);
        this.Ear2.addBox(1.0f, -5.0f, 0.0f, 2, 4, 1);
        this.Ear2.setRotationPoint(0.0f, 9.9f, -7.0f);
        this.Ear2.setTextureSize(64, 32);
        this.Ear2.mirror = true;
        this.setRotation(this.Ear2, 0.3490659f, 0.0f, 0.0f);
        this.Nose = new ModelRenderer((ModelBase)this, 0, 20);
        this.Nose.addBox(-2.0f, 0.0f, -5.0f, 6, 5, 4);
        this.Nose.setRotationPoint(-2.0f, 11.5f, -8.0f);
        this.Nose.setTextureSize(64, 32);
        this.Nose.mirror = true;
        this.setRotation(this.Nose, 0.0f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 52, 15);
        this.Shape1.addBox(-3.0f, -10.0f, 0.0f, 1, 10, 4);
        this.Shape1.setRotationPoint(0.0f, 11.0f, 4.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, -1.003822f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 52, 15);
        this.Shape2.addBox(-2.0f, 0.0f, 0.0f, 1, 13, 4);
        this.Shape2.setRotationPoint(-1.0f, 0.0f, 7.0f);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, -0.6320364f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 52, 15);
        this.Shape3.addBox(-2.0f, 0.0f, 0.0f, 1, 13, 4);
        this.Shape3.setRotationPoint(-1.0f, -2.0f, -5.466667f);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.1115358f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 52, 15);
        this.Shape4.addBox(0.0f, 0.0f, 0.0f, 2, 13, 4);
        this.Shape4.setRotationPoint(-6.0f, -2.0f, -5.466667f);
        this.Shape4.setTextureSize(64, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.1115358f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 52, 15);
        this.Shape5.addBox(0.0f, 0.0f, 0.0f, 2, 13, 4);
        this.Shape5.setRotationPoint(-6.0f, 0.0f, 7.0f);
        this.Shape5.setTextureSize(64, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, -0.6320364f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 52, 15);
        this.Shape6.addBox(-1.0f, -10.0f, 0.0f, 2, 10, 4);
        this.Shape6.setRotationPoint(-5.0f, 11.0f, 4.0f);
        this.Shape6.setTextureSize(64, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, -1.003822f, 0.0f, 0.0f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 52, 15);
        this.Shape7.addBox(0.0f, 0.0f, 0.0f, 2, 13, 4);
        this.Shape7.setRotationPoint(2.0f, -2.0f, -5.466667f);
        this.Shape7.setTextureSize(64, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.1115358f, 0.0f, 0.0f);
        this.Shape8 = new ModelRenderer((ModelBase)this, 52, 15);
        this.Shape8.addBox(0.0f, 0.0f, 0.0f, 2, 13, 4);
        this.Shape8.setRotationPoint(2.0f, 0.0f, 7.0f);
        this.Shape8.setTextureSize(64, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, -0.6320364f, 0.0f, 0.0f);
        this.Shape9 = new ModelRenderer((ModelBase)this, 52, 15);
        this.Shape9.addBox(-1.0f, -10.0f, 0.0f, 2, 10, 4);
        this.Shape9.setRotationPoint(3.0f, 11.0f, 4.0f);
        this.Shape9.setTextureSize(64, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, -1.003822f, 0.0f, 0.0f);
        this.Shape10 = new ModelRenderer((ModelBase)this, 52, 15);
        this.Shape10.addBox(1.0f, 0.0f, 0.0f, 1, 13, 4);
        this.Shape10.setRotationPoint(-1.0f, -2.0f, -5.466667f);
        this.Shape10.setTextureSize(64, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.1115358f, 0.0f, 0.0f);
        this.Shape11 = new ModelRenderer((ModelBase)this, 52, 15);
        this.Shape11.addBox(1.0f, 0.0f, 0.0f, 1, 13, 4);
        this.Shape11.setRotationPoint(-1.0f, 0.0f, 7.0f);
        this.Shape11.setTextureSize(64, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, -0.6320364f, 0.0f, 0.0f);
        this.Shape12 = new ModelRenderer((ModelBase)this, 52, 15);
        this.Shape12.addBox(0.0f, -10.0f, 0.0f, 1, 10, 4);
        this.Shape12.setRotationPoint(0.0f, 11.0f, 4.0f);
        this.Shape12.setTextureSize(64, 32);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, -1.003822f, 0.0f, 0.0f);
        this.Leg5 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg5.addBox(-1.0f, 0.0f, -1.0f, 3, 8, 3);
        this.Leg5.setRotationPoint(-6.5f, 16.0f, -2.0f);
        this.Leg5.setTextureSize(64, 32);
        this.Leg5.mirror = true;
        this.setRotation(this.Leg5, 0.0f, 0.0f, 0.0f);
        this.Leg6 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg6.addBox(-1.0f, 0.0f, -1.0f, 3, 8, 3);
        this.Leg6.setRotationPoint(-5.5f, 16.0f, 9.0f);
        this.Leg6.setTextureSize(64, 32);
        this.Leg6.mirror = true;
        this.setRotation(this.Leg6, 0.0f, 0.0f, 0.0f);
        this.Leg7 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg7.addBox(-1.0f, 0.0f, -1.0f, 3, 8, 3);
        this.Leg7.setRotationPoint(2.5f, 16.0f, 9.0f);
        this.Leg7.setTextureSize(64, 32);
        this.Leg7.mirror = true;
        this.setRotation(this.Leg7, 0.0f, 0.0f, 0.0f);
        this.Leg8 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg8.addBox(-1.0f, 0.0f, -1.0f, 3, 8, 3);
        this.Leg8.setRotationPoint(3.5f, 16.0f, -2.0f);
        this.Leg8.setTextureSize(64, 32);
        this.Leg8.mirror = true;
        this.setRotation(this.Leg8, 0.0f, 0.0f, 0.0f);
        this.Tail2 = new ModelRenderer((ModelBase)this, 22, 13);
        this.Tail2.addBox(-1.0f, 0.0f, -1.0f, 4, 12, 4);
        this.Tail2.setRotationPoint(-5.0f, 15.0f, 7.0f);
        this.Tail2.setTextureSize(64, 32);
        this.Tail2.mirror = true;
        this.setRotation(this.Tail2, 1.130069f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.WolfHead.render(f5);
        this.Body.render(f5);
        this.Mane.render(f5);
        this.Leg1.render(f5);
        this.Leg2.render(f5);
        this.Leg3.render(f5);
        this.Leg4.render(f5);
        this.Tail.render(f5);
        this.Ear1.render(f5);
        this.Ear2.render(f5);
        this.Nose.render(f5);
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
        this.Shape7.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
        this.Shape10.render(f5);
        this.Shape11.render(f5);
        this.Shape12.render(f5);
        this.Leg5.render(f5);
        this.Leg6.render(f5);
        this.Leg7.render(f5);
        this.Leg8.render(f5);
        this.Tail2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par6) {
        this.Leg1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.Leg2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.Leg3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.Leg4.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.Leg5.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.Leg6.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.Leg7.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.Leg8.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
    }
}

