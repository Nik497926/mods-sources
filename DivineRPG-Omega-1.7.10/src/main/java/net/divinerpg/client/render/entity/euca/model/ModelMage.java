/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.entity.euca.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMage
extends ModelBiped {
    ModelRenderer hatBottom;
    ModelRenderer hatMiddle;
    ModelRenderer hatTop;
    ModelRenderer puff;

    public ModelMage() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.bipedHead = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHead.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.bipedHead.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedHead.setTextureSize(128, 64);
        this.bipedHead.mirror = true;
        this.setRotation(this.bipedHead, 0.0f, 0.0f, 0.0f);
        this.bipedBody = new ModelRenderer((ModelBase)this, 16, 16);
        this.bipedBody.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.bipedBody.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedBody.setTextureSize(128, 64);
        this.bipedBody.mirror = true;
        this.setRotation(this.bipedBody, 0.0f, 0.0f, 0.0f);
        this.bipedRightArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.bipedRightArm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.bipedRightArm.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.bipedRightArm.setTextureSize(128, 64);
        this.bipedRightArm.mirror = true;
        this.setRotation(this.bipedRightArm, 0.0f, 0.0f, 0.0f);
        this.bipedLeftArm = new ModelRenderer((ModelBase)this, 40, 32);
        this.bipedLeftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.bipedLeftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.bipedLeftArm.setTextureSize(128, 64);
        this.bipedLeftArm.mirror = true;
        this.setRotation(this.bipedLeftArm, 0.0f, 0.0f, 0.0f);
        this.bipedRightLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedRightLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.bipedRightLeg.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.bipedRightLeg.setTextureSize(128, 64);
        this.bipedRightLeg.mirror = true;
        this.setRotation(this.bipedRightLeg, 0.0f, 0.0f, 0.0f);
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedLeftLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.bipedLeftLeg.setRotationPoint(2.0f, 12.0f, 0.0f);
        this.bipedLeftLeg.setTextureSize(128, 64);
        this.bipedLeftLeg.mirror = true;
        this.setRotation(this.bipedLeftLeg, 0.0f, 0.0f, 0.0f);
        this.hatBottom = new ModelRenderer((ModelBase)this, 56, 0);
        this.hatBottom.addBox(0.0f, 0.0f, 0.0f, 10, 2, 10);
        this.hatBottom.setRotationPoint(-5.0f, -9.0f, -4.8f);
        this.hatBottom.setTextureSize(128, 64);
        this.hatBottom.mirror = true;
        this.setRotation(this.hatBottom, -0.0349066f, 0.0f, 0.0f);
        this.hatMiddle = new ModelRenderer((ModelBase)this, 96, 0);
        this.hatMiddle.addBox(0.0f, 0.0f, 0.0f, 8, 5, 8);
        this.hatMiddle.setRotationPoint(-4.0f, -13.5f, -3.5f);
        this.hatMiddle.setTextureSize(128, 64);
        this.hatMiddle.mirror = true;
        this.setRotation(this.hatMiddle, -0.122173f, 0.0f, 0.0f);
        this.hatTop = new ModelRenderer((ModelBase)this, 32, 0);
        this.hatTop.addBox(0.0f, 0.0f, 0.0f, 6, 4, 6);
        this.hatTop.setRotationPoint(-3.0f, -17.0f, -1.8f);
        this.hatTop.setTextureSize(128, 64);
        this.hatTop.mirror = true;
        this.setRotation(this.hatTop, -0.2268928f, 0.0f, 0.0f);
        this.puff = new ModelRenderer((ModelBase)this, 58, 18);
        this.puff.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.puff.setRotationPoint(-1.0f, -18.0f, 0.5f);
        this.puff.setTextureSize(128, 64);
        this.puff.mirror = true;
        this.setRotation(this.puff, -0.2268928f, 0.0f, 0.0f);
        this.bipedHead.addChild(this.hatBottom);
        this.bipedHead.addChild(this.hatMiddle);
        this.bipedHead.addChild(this.hatTop);
        this.bipedHead.addChild(this.puff);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.bipedHead.render(f5);
        this.bipedBody.render(f5);
        this.bipedRightArm.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedLeftLeg.render(f5);
        this.bipedHeadwear.isHidden = true;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}

