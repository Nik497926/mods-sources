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

public class ModelStonewalker
extends ModelBase {
    ModelRenderer leftBackLeg;
    ModelRenderer rightBackLeg;
    ModelRenderer leftFrontLeg;
    ModelRenderer rightFrontLeg;
    ModelRenderer body;
    ModelRenderer limb2;
    ModelRenderer limb1;
    ModelRenderer head;

    public ModelStonewalker() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.leftBackLeg = new ModelRenderer((ModelBase)this, 0, 0);
        this.leftBackLeg.addBox(0.0f, -1.0f, -1.0f, 2, 13, 2);
        this.leftBackLeg.setRotationPoint(6.0f, 12.0f, 7.0f);
        this.leftBackLeg.setTextureSize(64, 32);
        this.leftBackLeg.mirror = true;
        this.setRotation(this.leftBackLeg, 0.0f, 0.0f, 0.0f);
        this.rightBackLeg = new ModelRenderer((ModelBase)this, 0, 0);
        this.rightBackLeg.addBox(-2.0f, -1.0f, -1.0f, 2, 13, 2);
        this.rightBackLeg.setRotationPoint(-6.0f, 12.0f, 7.0f);
        this.rightBackLeg.setTextureSize(64, 32);
        this.rightBackLeg.mirror = true;
        this.setRotation(this.rightBackLeg, 0.0f, 0.0f, 0.0f);
        this.leftFrontLeg = new ModelRenderer((ModelBase)this, 0, 0);
        this.leftFrontLeg.addBox(0.0f, -1.0f, -1.0f, 2, 13, 2);
        this.leftFrontLeg.setRotationPoint(6.0f, 12.0f, -7.0f);
        this.leftFrontLeg.setTextureSize(64, 32);
        this.leftFrontLeg.mirror = true;
        this.setRotation(this.leftFrontLeg, 0.0f, 0.0f, 0.0f);
        this.rightFrontLeg = new ModelRenderer((ModelBase)this, 0, 0);
        this.rightFrontLeg.addBox(-2.0f, -1.0f, -1.0f, 2, 13, 2);
        this.rightFrontLeg.setRotationPoint(-6.0f, 12.0f, -7.0f);
        this.rightFrontLeg.setTextureSize(64, 32);
        this.rightFrontLeg.mirror = true;
        this.setRotation(this.rightFrontLeg, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 15);
        this.body.addBox(0.0f, 0.0f, 0.0f, 4, 16, 4);
        this.body.setRotationPoint(-2.0f, 5.0f, -2.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.limb2 = new ModelRenderer((ModelBase)this, 8, 0);
        this.limb2.addBox(-10.5f, 0.0f, -2.0f, 20, 2, 2);
        this.limb2.setRotationPoint(0.0f, 14.0f, 1.0f);
        this.limb2.setTextureSize(64, 32);
        this.limb2.mirror = true;
        this.setRotation(this.limb2, 0.0f, -0.7853982f, 0.0f);
        this.limb1 = new ModelRenderer((ModelBase)this, 8, 0);
        this.limb1.addBox(-8.5f, 0.0f, -1.0f, 20, 2, 2);
        this.limb1.setRotationPoint(-1.0f, 14.0f, 1.0f);
        this.limb1.setTextureSize(64, 32);
        this.limb1.mirror = true;
        this.setRotation(this.limb1, 0.0f, 0.7853982f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 35);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 5.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.leftBackLeg.render(f5);
        this.rightBackLeg.render(f5);
        this.leftFrontLeg.render(f5);
        this.rightFrontLeg.render(f5);
        this.body.render(f5);
        this.limb2.render(f5);
        this.limb1.render(f5);
        this.head.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.head.rotateAngleY = f4 / 57.295776f;
        this.head.rotateAngleX = f5 / 57.295776f;
        this.rightFrontLeg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * f1;
        this.leftFrontLeg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * f1;
        this.leftBackLeg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * f1;
        this.rightBackLeg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * f1;
    }
}

