/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.entity.iceika.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelFrosty
extends ModelBase {
    ModelRenderer hatBase;
    ModelRenderer bottom;
    ModelRenderer rightFinger1;
    ModelRenderer middle;
    ModelRenderer rightArm;
    ModelRenderer leftArm;
    ModelRenderer rightFinger3;
    ModelRenderer rightFinger2;
    ModelRenderer leftFinger2;
    ModelRenderer leftFinger3;
    ModelRenderer leftFinger1;
    ModelRenderer hatTop;
    ModelRenderer head;

    public ModelFrosty() {
        this.textureWidth = 256;
        this.textureHeight = 32;
        this.hatBase = new ModelRenderer((ModelBase)this, 105, 18);
        this.hatBase.addBox(0.0f, 0.0f, 0.0f, 12, 2, 12);
        this.hatBase.setRotationPoint(-6.0f, -10.0f, -6.0f);
        this.hatBase.setTextureSize(256, 32);
        this.hatBase.mirror = true;
        this.setRotation(this.hatBase, 0.0f, 0.0f, 0.0f);
        this.bottom = new ModelRenderer((ModelBase)this, 0, 0);
        this.bottom.addBox(0.0f, 0.0f, 0.0f, 16, 13, 16);
        this.bottom.setRotationPoint(-8.0f, 11.0f, -8.0f);
        this.bottom.setTextureSize(256, 32);
        this.bottom.mirror = true;
        this.setRotation(this.bottom, 0.0f, 0.0f, 0.0f);
        this.rightFinger1 = new ModelRenderer((ModelBase)this, 51, 0);
        this.rightFinger1.addBox(-1.0f, 7.0f, -16.0f, 1, 1, 5);
        this.rightFinger1.setRotationPoint(-5.0f, 2.0f, 3.0f);
        this.rightFinger1.setTextureSize(256, 32);
        this.rightFinger1.mirror = true;
        this.setRotation(this.rightFinger1, -0.2093362f, 0.6578549f, 0.0f);
        this.middle = new ModelRenderer((ModelBase)this, 159, 0);
        this.middle.addBox(0.0f, 0.0f, 0.0f, 12, 13, 12);
        this.middle.setRotationPoint(-6.0f, 1.0f, -6.0f);
        this.middle.setTextureSize(256, 32);
        this.middle.mirror = true;
        this.setRotation(this.middle, 0.0f, 0.0f, 0.0f);
        this.rightArm = new ModelRenderer((ModelBase)this, 66, 0);
        this.rightArm.addBox(0.0f, 0.0f, -12.0f, 2, 2, 12);
        this.rightArm.setRotationPoint(-6.0f, 2.0f, 2.0f);
        this.rightArm.setTextureSize(256, 32);
        this.rightArm.mirror = true;
        this.setRotation(this.rightArm, 0.3490659f, 0.7853982f, 0.0f);
        this.leftArm = new ModelRenderer((ModelBase)this, 66, 0);
        this.leftArm.addBox(0.0f, 0.0f, -12.0f, 2, 2, 12);
        this.leftArm.setRotationPoint(5.0f, 2.0f, 2.0f);
        this.leftArm.setTextureSize(256, 32);
        this.leftArm.mirror = true;
        this.setRotation(this.leftArm, 0.3490659f, -0.7853982f, 0.0f);
        this.rightFinger3 = new ModelRenderer((ModelBase)this, 51, 0);
        this.rightFinger3.addBox(4.0f, -2.0f, -15.0f, 1, 1, 5);
        this.rightFinger3.setRotationPoint(-5.0f, 2.0f, 3.0f);
        this.rightFinger3.setTextureSize(256, 32);
        this.rightFinger3.mirror = true;
        this.setRotation(this.rightFinger3, 0.6457718f, 1.178355f, 0.0f);
        this.rightFinger2 = new ModelRenderer((ModelBase)this, 51, 0);
        this.rightFinger2.addBox(-5.0f, -6.0f, -17.0f, 1, 1, 5);
        this.rightFinger2.setRotationPoint(-5.0f, 2.0f, 3.0f);
        this.rightFinger2.setTextureSize(256, 32);
        this.rightFinger2.mirror = true;
        this.setRotation(this.rightFinger2, 0.7944863f, 0.3232474f, 0.0f);
        this.leftFinger2 = new ModelRenderer((ModelBase)this, 51, 0);
        this.leftFinger2.addBox(-2.0f, -6.0f, -17.0f, 1, 1, 5);
        this.leftFinger2.setRotationPoint(4.0f, 2.0f, 3.0f);
        this.leftFinger2.setTextureSize(256, 32);
        this.leftFinger2.mirror = true;
        this.setRotation(this.leftFinger2, 0.7944863f, -1.029641f, 0.0f);
        this.leftFinger3 = new ModelRenderer((ModelBase)this, 51, 0);
        this.leftFinger3.addBox(7.0f, -2.0f, -15.0f, 1, 1, 5);
        this.leftFinger3.setRotationPoint(4.0f, 2.0f, 3.0f);
        this.leftFinger3.setTextureSize(256, 32);
        this.leftFinger3.mirror = true;
        this.setRotation(this.leftFinger3, 0.6457718f, -0.1745329f, 0.0f);
        this.leftFinger1 = new ModelRenderer((ModelBase)this, 51, 0);
        this.leftFinger1.addBox(2.0f, 7.0f, -16.0f, 1, 1, 5);
        this.leftFinger1.setRotationPoint(4.0f, 2.0f, 3.0f);
        this.leftFinger1.setTextureSize(256, 32);
        this.leftFinger1.mirror = true;
        this.setRotation(this.leftFinger1, -0.2093362f, -0.6950335f, 0.0f);
        this.hatTop = new ModelRenderer((ModelBase)this, 65, 17);
        this.hatTop.addBox(0.0f, 0.0f, 0.0f, 8, 7, 8);
        this.hatTop.setRotationPoint(-4.0f, -17.0f, -4.0f);
        this.hatTop.setTextureSize(256, 32);
        this.hatTop.mirror = true;
        this.setRotation(this.hatTop, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 96, 0);
        this.head.addBox(0.0f, 0.0f, 0.0f, 8, 9, 8);
        this.head.setRotationPoint(-4.0f, -8.0f, -4.0f);
        this.head.setTextureSize(256, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)3008);
        this.hatBase.render(f5);
        this.rightFinger1.render(f5);
        this.bottom.render(f5);
        this.middle.render(f5);
        this.head.render(f5);
        this.rightArm.render(f5);
        this.leftArm.render(f5);
        this.rightFinger3.render(f5);
        this.rightFinger2.render(f5);
        this.leftFinger2.render(f5);
        this.leftFinger3.render(f5);
        this.leftFinger1.render(f5);
        this.hatTop.render(f5);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}

