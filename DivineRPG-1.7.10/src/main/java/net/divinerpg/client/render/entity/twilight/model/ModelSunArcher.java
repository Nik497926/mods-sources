/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.twilight.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSunArcher
extends ModelBase {
    ModelRenderer legRight;
    ModelRenderer legLeft;
    ModelRenderer body;
    ModelRenderer footRight;
    ModelRenderer footLeft;
    ModelRenderer spine1;
    ModelRenderer spine2;
    ModelRenderer spine3;
    ModelRenderer spine4;
    ModelRenderer spine5;
    ModelRenderer head;
    public ModelRenderer armRight;
    ModelRenderer armBandRight;
    ModelRenderer armLeft;
    ModelRenderer armBandLeft;

    public ModelSunArcher() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.legRight = new ModelRenderer((ModelBase)this, 0, 0);
        this.legRight.addBox(-1.0f, 0.0f, -1.0f, 2, 13, 2);
        this.legRight.setRotationPoint(-4.0f, 11.0f, 1.0f);
        this.legRight.setTextureSize(128, 64);
        this.legRight.mirror = true;
        this.setRotation(this.legRight, 0.0f, 0.0f, 0.0f);
        this.legLeft = new ModelRenderer((ModelBase)this, 0, 0);
        this.legLeft.addBox(-1.0f, 0.0f, -1.0f, 2, 13, 2);
        this.legLeft.setRotationPoint(4.0f, 11.0f, 1.0f);
        this.legLeft.setTextureSize(128, 64);
        this.legLeft.mirror = true;
        this.setRotation(this.legLeft, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 43);
        this.body.addBox(0.0f, 0.0f, 0.0f, 12, 16, 5);
        this.body.setRotationPoint(-6.0f, -5.0f, -1.5f);
        this.body.setTextureSize(128, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.footRight = new ModelRenderer((ModelBase)this, 0, 15);
        this.footRight.addBox(-1.5f, 12.0f, -2.5f, 3, 1, 4);
        this.footRight.setRotationPoint(-4.0f, 11.0f, 1.0f);
        this.footRight.setTextureSize(128, 64);
        this.footRight.mirror = true;
        this.setRotation(this.footRight, 0.0f, 0.0f, 0.0f);
        this.footLeft = new ModelRenderer((ModelBase)this, 0, 15);
        this.footLeft.addBox(-1.5f, 12.0f, -2.5f, 3, 1, 4);
        this.footLeft.setRotationPoint(4.0f, 11.0f, 1.0f);
        this.footLeft.setTextureSize(128, 64);
        this.footLeft.mirror = true;
        this.setRotation(this.footLeft, 0.0f, 0.0f, 0.0f);
        this.spine1 = new ModelRenderer((ModelBase)this, 8, 0);
        this.spine1.addBox(0.0f, -4.0f, 0.0f, 3, 4, 1);
        this.spine1.setRotationPoint(-1.5f, 0.0f, 2.5f);
        this.spine1.setTextureSize(128, 64);
        this.spine1.mirror = true;
        this.setRotation(this.spine1, -0.5235988f, 0.0f, 0.0f);
        this.spine2 = new ModelRenderer((ModelBase)this, 8, 0);
        this.spine2.addBox(0.0f, -4.0f, 0.0f, 3, 4, 1);
        this.spine2.setRotationPoint(-1.5f, 3.0f, 2.5f);
        this.spine2.setTextureSize(128, 64);
        this.spine2.mirror = true;
        this.setRotation(this.spine2, -0.5235988f, 0.0f, 0.0f);
        this.spine3 = new ModelRenderer((ModelBase)this, 8, 0);
        this.spine3.addBox(0.0f, -4.0f, 0.0f, 3, 4, 1);
        this.spine3.setRotationPoint(-1.5f, 6.0f, 2.5f);
        this.spine3.setTextureSize(128, 64);
        this.spine3.mirror = true;
        this.setRotation(this.spine3, -0.5235988f, 0.0f, 0.0f);
        this.spine4 = new ModelRenderer((ModelBase)this, 8, 0);
        this.spine4.addBox(0.0f, -4.0f, 0.0f, 3, 4, 1);
        this.spine4.setRotationPoint(-1.5f, 9.0f, 2.5f);
        this.spine4.setTextureSize(128, 64);
        this.spine4.mirror = true;
        this.setRotation(this.spine4, -0.5235988f, 0.0f, 0.0f);
        this.spine5 = new ModelRenderer((ModelBase)this, 8, 0);
        this.spine5.addBox(0.0f, -4.0f, 0.0f, 3, 4, 1);
        this.spine5.setRotationPoint(-1.5f, -3.0f, 2.5f);
        this.spine5.setTextureSize(128, 64);
        this.spine5.mirror = true;
        this.setRotation(this.spine5, -0.5235988f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 31);
        this.head.addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
        this.head.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.head.setTextureSize(128, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.armRight = new ModelRenderer((ModelBase)this, 16, 0);
        this.armRight.addBox(-1.0f, 0.0f, -1.0f, 2, 12, 2);
        this.armRight.setRotationPoint(-7.0f, -4.0f, 1.0f);
        this.armRight.setTextureSize(128, 64);
        this.armRight.mirror = true;
        this.setRotation(this.armRight, -1.308997f, 0.0f, 0.0f);
        this.armBandRight = new ModelRenderer((ModelBase)this, 24, 0);
        this.armBandRight.addBox(-1.5f, 3.0f, -1.5f, 3, 6, 3);
        this.armBandRight.setRotationPoint(-7.0f, -4.0f, 1.0f);
        this.armBandRight.setTextureSize(128, 64);
        this.armBandRight.mirror = true;
        this.setRotation(this.armBandRight, -1.308997f, 0.0f, 0.0f);
        this.armLeft = new ModelRenderer((ModelBase)this, 16, 0);
        this.armLeft.addBox(-1.0f, 0.0f, -1.0f, 2, 12, 2);
        this.armLeft.setRotationPoint(6.5f, -4.0f, 1.0f);
        this.armLeft.setTextureSize(128, 64);
        this.armLeft.mirror = true;
        this.setRotation(this.armLeft, 0.0f, 0.0f, -0.0872665f);
        this.armBandLeft = new ModelRenderer((ModelBase)this, 24, 0);
        this.armBandLeft.addBox(-1.5f, 3.0f, -1.5f, 3, 6, 3);
        this.armBandLeft.setRotationPoint(6.5f, -4.0f, 1.0f);
        this.armBandLeft.setTextureSize(128, 64);
        this.armBandLeft.mirror = true;
        this.setRotation(this.armBandLeft, 0.0f, 0.0f, -0.0872665f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.legRight.render(f5);
        this.legLeft.render(f5);
        this.body.render(f5);
        this.footRight.render(f5);
        this.footLeft.render(f5);
        this.spine1.render(f5);
        this.spine2.render(f5);
        this.spine3.render(f5);
        this.spine4.render(f5);
        this.spine5.render(f5);
        this.head.render(f5);
        this.armRight.render(f5);
        this.armBandRight.render(f5);
        this.armLeft.render(f5);
        this.armBandLeft.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.legLeft.rotateAngleX = this.footLeft.rotateAngleX = MathHelper.cos((float)f) * f1;
        this.legRight.rotateAngleX = this.footRight.rotateAngleX = MathHelper.cos((float)(f + (float)Math.PI)) * f1;
        this.armRight.rotateAngleX = this.armBandRight.rotateAngleX = -1.308997f + MathHelper.cos((float)f) * f1 * 0.3f;
        this.armLeft.rotateAngleX = this.armBandLeft.rotateAngleX = MathHelper.cos((float)f) * f1;
    }
}

