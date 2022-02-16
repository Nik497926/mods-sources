/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.entity.twilight.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCori
extends ModelBase {
    ModelRenderer Head;
    ModelRenderer Tentacle1;
    ModelRenderer Tentacle2;
    ModelRenderer Tentacle3;
    ModelRenderer Tentacle4;
    ModelRenderer Tentacle5;
    ModelRenderer Tentacle6;
    ModelRenderer Tentacle7;
    ModelRenderer Tentacle8;
    ModelRenderer Tentacle9;
    ModelRenderer Tentacle10;
    ModelRenderer Tentacle11;
    ModelRenderer Tentacle12;
    ModelRenderer Tentacle13;
    ModelRenderer Tentacle14;
    ModelRenderer Tentacle15;
    ModelRenderer Tentacle16;
    ModelRenderer Tentacle17;
    ModelRenderer Tentacle18;
    ModelRenderer Tentacle19;
    ModelRenderer Tentacle20;

    public ModelCori() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.addBox(-5.0f, -5.0f, -5.0f, 10, 10, 10);
        this.Head.setRotationPoint(0.0f, 11.0f, 0.0f);
        this.Head.setTextureSize(64, 32);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, -1.570796f, 0.0f);
        this.Tentacle1 = new ModelRenderer((ModelBase)this, 40, 0);
        this.Tentacle1.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 3);
        this.Tentacle1.setRotationPoint(5.0f, 2.0f, 5.0f);
        this.Tentacle1.setTextureSize(64, 32);
        this.Tentacle1.mirror = true;
        this.setRotation(this.Tentacle1, 0.0f, 1.570796f, 0.0f);
        this.Tentacle2 = new ModelRenderer((ModelBase)this, 52, 0);
        this.Tentacle2.addBox(-1.0f, -3.0f, -1.0f, 2, 3, 2);
        this.Tentacle2.setRotationPoint(0.0f, 19.0f, 0.0f);
        this.Tentacle2.setTextureSize(64, 32);
        this.Tentacle2.mirror = true;
        this.setRotation(this.Tentacle2, 0.0f, 0.0f, 0.0f);
        this.Tentacle3 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle3.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle3.setRotationPoint(0.0f, 20.0f, 0.0f);
        this.Tentacle3.setTextureSize(64, 32);
        this.Tentacle3.mirror = true;
        this.setRotation(this.Tentacle3, 0.0f, 4.712389f, 0.0f);
        this.Tentacle4 = new ModelRenderer((ModelBase)this, 11, 0);
        this.Tentacle4.addBox(-1.0f, -3.0f, -1.0f, 2, 5, 2);
        this.Tentacle4.setRotationPoint(6.0f, 17.0f, 5.0f);
        this.Tentacle4.setTextureSize(64, 32);
        this.Tentacle4.mirror = true;
        this.setRotation(this.Tentacle4, 0.0f, 0.0f, 0.0f);
        this.Tentacle5 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle5.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle5.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.Tentacle5.setTextureSize(64, 32);
        this.Tentacle5.mirror = true;
        this.setRotation(this.Tentacle5, 0.0f, 4.712389f, 0.0f);
        this.Tentacle6 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle6.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle6.setRotationPoint(-9.0f, 11.0f, 0.0f);
        this.Tentacle6.setTextureSize(64, 32);
        this.Tentacle6.mirror = true;
        this.setRotation(this.Tentacle6, 0.0f, 4.712389f, 0.0f);
        this.Tentacle7 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle7.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle7.setRotationPoint(9.0f, 11.0f, 0.0f);
        this.Tentacle7.setTextureSize(64, 32);
        this.Tentacle7.mirror = true;
        this.setRotation(this.Tentacle7, 0.0f, 4.712389f, 0.0f);
        this.Tentacle8 = new ModelRenderer((ModelBase)this, 40, 0);
        this.Tentacle8.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 3);
        this.Tentacle8.setRotationPoint(-5.0f, 2.0f, 5.0f);
        this.Tentacle8.setTextureSize(64, 32);
        this.Tentacle8.mirror = true;
        this.setRotation(this.Tentacle8, 0.0f, 4.712389f, 0.0f);
        this.Tentacle9 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle9.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle9.setRotationPoint(9.0f, 2.0f, 5.0f);
        this.Tentacle9.setTextureSize(64, 32);
        this.Tentacle9.mirror = true;
        this.setRotation(this.Tentacle9, 0.0f, 4.712389f, 0.0f);
        this.Tentacle10 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle10.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle10.setRotationPoint(9.0f, 20.0f, 5.0f);
        this.Tentacle10.setTextureSize(64, 32);
        this.Tentacle10.mirror = true;
        this.setRotation(this.Tentacle10, 0.0f, 4.712389f, 0.0f);
        this.Tentacle11 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle11.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle11.setRotationPoint(-9.0f, 20.0f, 5.0f);
        this.Tentacle11.setTextureSize(64, 32);
        this.Tentacle11.mirror = true;
        this.setRotation(this.Tentacle11, 0.0f, 4.712389f, 0.0f);
        this.Tentacle12 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle12.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle12.setRotationPoint(-9.0f, 2.0f, 5.0f);
        this.Tentacle12.setTextureSize(64, 32);
        this.Tentacle12.mirror = true;
        this.setRotation(this.Tentacle12, 0.0f, 4.712389f, 0.0f);
        this.Tentacle13 = new ModelRenderer((ModelBase)this, 40, 0);
        this.Tentacle13.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 3);
        this.Tentacle13.setRotationPoint(-5.0f, 11.0f, 0.0f);
        this.Tentacle13.setTextureSize(64, 32);
        this.Tentacle13.mirror = true;
        this.setRotation(this.Tentacle13, 0.0f, 4.712389f, 0.0f);
        this.Tentacle14 = new ModelRenderer((ModelBase)this, 40, 0);
        this.Tentacle14.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 3);
        this.Tentacle14.setRotationPoint(5.0f, 11.0f, 0.0f);
        this.Tentacle14.setTextureSize(64, 32);
        this.Tentacle14.mirror = true;
        this.setRotation(this.Tentacle14, 0.0f, 1.570796f, 0.0f);
        this.Tentacle15 = new ModelRenderer((ModelBase)this, 40, 0);
        this.Tentacle15.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 3);
        this.Tentacle15.setRotationPoint(-5.0f, 20.0f, 5.0f);
        this.Tentacle15.setTextureSize(64, 32);
        this.Tentacle15.mirror = true;
        this.setRotation(this.Tentacle15, 0.0f, 4.712389f, 0.0f);
        this.Tentacle16 = new ModelRenderer((ModelBase)this, 40, 0);
        this.Tentacle16.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 3);
        this.Tentacle16.setRotationPoint(5.0f, 20.0f, 5.0f);
        this.Tentacle16.setTextureSize(64, 32);
        this.Tentacle16.mirror = true;
        this.setRotation(this.Tentacle16, 0.0f, 1.570796f, 0.0f);
        this.Tentacle17 = new ModelRenderer((ModelBase)this, 52, 0);
        this.Tentacle17.addBox(-1.0f, -3.0f, -1.0f, 2, 3, 2);
        this.Tentacle17.setRotationPoint(0.0f, 6.0f, 0.0f);
        this.Tentacle17.setTextureSize(64, 32);
        this.Tentacle17.mirror = true;
        this.setRotation(this.Tentacle17, 0.0f, 0.0f, 0.0f);
        this.Tentacle18 = new ModelRenderer((ModelBase)this, 11, 0);
        this.Tentacle18.addBox(-1.0f, -3.0f, -1.0f, 2, 5, 2);
        this.Tentacle18.setRotationPoint(-6.0f, 17.0f, 5.0f);
        this.Tentacle18.setTextureSize(64, 32);
        this.Tentacle18.mirror = true;
        this.setRotation(this.Tentacle18, 0.0f, 0.0f, 0.0f);
        this.Tentacle19 = new ModelRenderer((ModelBase)this, 11, 0);
        this.Tentacle19.addBox(-1.0f, -3.0f, -1.0f, 2, 5, 2);
        this.Tentacle19.setRotationPoint(6.0f, 6.0f, 5.0f);
        this.Tentacle19.setTextureSize(64, 32);
        this.Tentacle19.mirror = true;
        this.setRotation(this.Tentacle19, 0.0f, 0.0f, 0.0f);
        this.Tentacle20 = new ModelRenderer((ModelBase)this, 11, 0);
        this.Tentacle20.addBox(-1.0f, -3.0f, -1.0f, 2, 5, 2);
        this.Tentacle20.setRotationPoint(-6.0f, 6.0f, 5.0f);
        this.Tentacle20.setTextureSize(64, 32);
        this.Tentacle20.mirror = true;
        this.setRotation(this.Tentacle20, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.Head.render(f5);
        this.Tentacle1.render(f5);
        this.Tentacle2.render(f5);
        this.Tentacle3.render(f5);
        this.Tentacle4.render(f5);
        this.Tentacle5.render(f5);
        this.Tentacle6.render(f5);
        this.Tentacle7.render(f5);
        this.Tentacle8.render(f5);
        this.Tentacle9.render(f5);
        this.Tentacle10.render(f5);
        this.Tentacle11.render(f5);
        this.Tentacle12.render(f5);
        this.Tentacle13.render(f5);
        this.Tentacle14.render(f5);
        this.Tentacle15.render(f5);
        this.Tentacle16.render(f5);
        this.Tentacle17.render(f5);
        this.Tentacle18.render(f5);
        this.Tentacle19.render(f5);
        this.Tentacle20.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    }
}

