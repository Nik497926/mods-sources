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

public class ModelAncientEntity
extends ModelBase {
    ModelRenderer head;
    ModelRenderer legr;
    ModelRenderer legl;
    ModelRenderer legL2B;
    ModelRenderer body2;
    ModelRenderer body;
    ModelRenderer legR2B;
    ModelRenderer legL2T;
    ModelRenderer legR2T;
    ModelRenderer legR2M;
    ModelRenderer legL2M;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer hornbottoml;
    ModelRenderer horntopl;
    ModelRenderer hornbottomr;
    ModelRenderer horntopr;

    public ModelAncientEntity() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
        this.head.setRotationPoint(0.0f, -13.0f, -19.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.legr = new ModelRenderer((ModelBase)this, 0, 16);
        this.legr.addBox(-3.0f, 0.0f, -2.0f, 4, 12, 4);
        this.legr.setRotationPoint(-7.0f, 12.0f, 4.0f);
        this.legr.setTextureSize(64, 32);
        this.legr.mirror = true;
        this.setRotation(this.legr, 0.0f, 0.0f, 0.0f);
        this.legl = new ModelRenderer((ModelBase)this, 0, 16);
        this.legl.addBox(-1.0f, 0.0f, -2.0f, 4, 12, 4);
        this.legl.setRotationPoint(7.0f, 12.0f, 4.0f);
        this.legl.setTextureSize(64, 32);
        this.legl.mirror = true;
        this.setRotation(this.legl, 0.0f, 0.0f, 0.0f);
        this.legL2B = new ModelRenderer((ModelBase)this, 0, 19);
        this.legL2B.addBox(-1.0f, 24.0f, -2.0f, 3, 9, 2);
        this.legL2B.setRotationPoint(7.0f, -9.0f, -16.0f);
        this.legL2B.setTextureSize(64, 32);
        this.legL2B.mirror = true;
        this.setRotation(this.legL2B, 0.0f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer((ModelBase)this, 18, 4);
        this.body2.addBox(-6.0f, -10.0f, -7.0f, 12, 18, 10);
        this.body2.setRotationPoint(0.0f, -7.6f, -10.7f);
        this.body2.setTextureSize(64, 32);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.7853982f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 18, 4);
        this.body.addBox(-6.0f, -10.0f, -7.0f, 12, 18, 10);
        this.body.setRotationPoint(0.0f, 5.0f, 2.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.7853982f, 0.0f, 0.0f);
        this.legR2B = new ModelRenderer((ModelBase)this, 0, 19);
        this.legR2B.addBox(-2.0f, 24.0f, -2.0f, 3, 9, 2);
        this.legR2B.setRotationPoint(-7.0f, -9.0f, -16.0f);
        this.legR2B.setTextureSize(64, 32);
        this.legR2B.mirror = true;
        this.setRotation(this.legR2B, 0.0f, 0.0f, 0.0f);
        this.legL2T = new ModelRenderer((ModelBase)this, 0, 14);
        this.legL2T.addBox(-1.0f, 0.0f, -4.0f, 6, 12, 6);
        this.legL2T.setRotationPoint(7.0f, -9.0f, -16.0f);
        this.legL2T.setTextureSize(64, 32);
        this.legL2T.mirror = true;
        this.setRotation(this.legL2T, 0.0f, 0.0f, 0.0f);
        this.legR2T = new ModelRenderer((ModelBase)this, 0, 14);
        this.legR2T.addBox(-5.0f, 0.0f, -4.0f, 6, 12, 6);
        this.legR2T.setRotationPoint(-7.0f, -9.0f, -16.0f);
        this.legR2T.setTextureSize(64, 32);
        this.legR2T.mirror = true;
        this.setRotation(this.legR2T, 0.0f, 0.0f, 0.0f);
        this.legR2M = new ModelRenderer((ModelBase)this, 0, 16);
        this.legR2M.addBox(-3.0f, 12.0f, -3.0f, 4, 12, 4);
        this.legR2M.setRotationPoint(-7.0f, -9.0f, -16.0f);
        this.legR2M.setTextureSize(64, 32);
        this.legR2M.mirror = true;
        this.setRotation(this.legR2M, 0.0f, 0.0f, 0.0f);
        this.legL2M = new ModelRenderer((ModelBase)this, 0, 16);
        this.legL2M.addBox(-1.0f, 12.0f, -3.0f, 4, 12, 4);
        this.legL2M.setRotationPoint(7.0f, -9.0f, -16.0f);
        this.legL2M.setTextureSize(64, 32);
        this.legL2M.mirror = true;
        this.setRotation(this.legL2M, 0.0f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 34, 4);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 6, 11, 1);
        this.Shape1.setRotationPoint(-3.0f, 9.0f, 1.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 2.119181f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 34, 4);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 6, 11, 1);
        this.Shape2.setRotationPoint(-3.0f, 6.0f, -1.0f);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 2.119181f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 34, 4);
        this.Shape3.addBox(0.0f, 0.0f, 0.0f, 6, 11, 1);
        this.Shape3.setRotationPoint(-3.0f, 3.0f, -3.0f);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 2.119181f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 34, 4);
        this.Shape4.addBox(0.0f, 0.0f, 0.0f, 6, 11, 1);
        this.Shape4.setRotationPoint(-3.0f, 0.0f, -5.0f);
        this.Shape4.setTextureSize(64, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 2.119181f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 34, 4);
        this.Shape5.addBox(0.0f, 0.0f, 0.0f, 6, 11, 1);
        this.Shape5.setRotationPoint(-3.0f, -3.0f, -7.0f);
        this.Shape5.setTextureSize(64, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 2.119181f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 34, 4);
        this.Shape6.addBox(0.0f, 0.0f, 0.0f, 6, 11, 1);
        this.Shape6.setRotationPoint(-3.0f, -6.0f, -9.0f);
        this.Shape6.setTextureSize(64, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 2.119181f, 0.0f, 0.0f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 34, 4);
        this.Shape7.addBox(0.0f, 0.0f, 0.0f, 6, 11, 1);
        this.Shape7.setRotationPoint(-3.0f, -9.0f, -11.0f);
        this.Shape7.setTextureSize(64, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 2.119181f, 0.0f, 0.0f);
        this.Shape8 = new ModelRenderer((ModelBase)this, 34, 4);
        this.Shape8.addBox(0.0f, 0.0f, 0.0f, 6, 11, 1);
        this.Shape8.setRotationPoint(-3.0f, -15.0f, -15.0f);
        this.Shape8.setTextureSize(64, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 2.119181f, 0.0f, 0.0f);
        this.Shape9 = new ModelRenderer((ModelBase)this, 34, 4);
        this.Shape9.addBox(0.0f, 0.0f, 0.0f, 6, 11, 1);
        this.Shape9.setRotationPoint(-3.0f, -12.0f, -13.0f);
        this.Shape9.setTextureSize(64, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 2.119181f, 0.0f, 0.0f);
        this.hornbottoml = new ModelRenderer((ModelBase)this, 33, 17);
        this.hornbottoml.addBox(0.0f, 0.0f, -6.0f, 1, 1, 6);
        this.hornbottoml.setRotationPoint(4.0f, -19.0f, -20.0f);
        this.hornbottoml.setTextureSize(64, 32);
        this.hornbottoml.mirror = true;
        this.setRotation(this.hornbottoml, 0.9666439f, 0.0f, 0.0f);
        this.horntopl = new ModelRenderer((ModelBase)this, 33, 17);
        this.horntopl.addBox(0.0f, 0.0f, -12.0f, 1, 1, 12);
        this.horntopl.setRotationPoint(4.0f, -19.0f, -20.0f);
        this.horntopl.setTextureSize(64, 32);
        this.horntopl.mirror = true;
        this.setRotation(this.horntopl, 0.0f, 0.0f, 0.0f);
        this.hornbottomr = new ModelRenderer((ModelBase)this, 33, 17);
        this.hornbottomr.addBox(0.0f, 0.0f, -6.0f, 1, 1, 6);
        this.hornbottomr.setRotationPoint(-5.0f, -19.0f, -20.0f);
        this.hornbottomr.setTextureSize(64, 32);
        this.hornbottomr.mirror = true;
        this.setRotation(this.hornbottomr, 0.9666439f, 0.0f, 0.0f);
        this.horntopr = new ModelRenderer((ModelBase)this, 33, 17);
        this.horntopr.addBox(0.0f, 0.0f, -12.0f, 1, 1, 12);
        this.horntopr.setRotationPoint(-5.0f, -19.0f, -20.0f);
        this.horntopr.setTextureSize(64, 32);
        this.horntopr.mirror = true;
        this.setRotation(this.horntopr, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.legr.render(f5);
        this.legl.render(f5);
        this.legL2B.render(f5);
        this.body2.render(f5);
        this.body.render(f5);
        this.legR2B.render(f5);
        this.legL2T.render(f5);
        this.legR2T.render(f5);
        this.legR2M.render(f5);
        this.legL2M.render(f5);
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
        this.Shape7.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
        this.hornbottoml.render(f5);
        this.horntopl.render(f5);
        this.hornbottomr.render(f5);
        this.horntopr.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.legr.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.legR2B.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.legR2M.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.legR2T.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.legl.rotateAngleX = MathHelper.cos((float)((float)((double)(var1 * 0.6662f) + Math.PI))) * 1.4f * var2;
        this.legL2B.rotateAngleX = MathHelper.cos((float)((float)((double)(var1 * 0.6662f) + Math.PI))) * 1.4f * var2;
        this.legL2M.rotateAngleX = MathHelper.cos((float)((float)((double)(var1 * 0.6662f) + Math.PI))) * 1.4f * var2;
        this.legL2T.rotateAngleX = MathHelper.cos((float)((float)((double)(var1 * 0.6662f) + Math.PI))) * 1.4f * var2;
    }
}

