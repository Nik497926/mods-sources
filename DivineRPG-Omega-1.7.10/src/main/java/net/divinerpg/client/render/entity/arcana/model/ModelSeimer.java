/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.entity.arcana.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSeimer
extends ModelBase {
    ModelRenderer body;
    ModelRenderer headtop;
    ModelRenderer headbottom;
    ModelRenderer headright;
    ModelRenderer headleft;
    ModelRenderer hornright;
    ModelRenderer hornright2;
    ModelRenderer sensorright2;
    ModelRenderer sensorleft;
    ModelRenderer wingright;
    ModelRenderer wingleft;
    ModelRenderer wingleft1;
    ModelRenderer wingright2;
    ModelRenderer hornleft;
    ModelRenderer sensorright;
    ModelRenderer hornleft2;
    ModelRenderer sensorleft2;

    public ModelSeimer() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.body = new ModelRenderer((ModelBase)this, 0, 7);
        this.body.addBox(0.0f, 0.0f, 0.0f, 16, 9, 16);
        this.body.setRotationPoint(8.0f, 10.0f, -8.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, -1.570796f, 0.0f);
        this.headtop = new ModelRenderer((ModelBase)this, 15, 14);
        this.headtop.addBox(0.0f, 0.0f, 0.0f, 2, 2, 16);
        this.headtop.setRotationPoint(8.0f, 10.0f, -10.0f);
        this.headtop.setTextureSize(64, 32);
        this.headtop.mirror = true;
        this.setRotation(this.headtop, 0.0f, -1.570796f, 0.0f);
        this.headbottom = new ModelRenderer((ModelBase)this, 32, 0);
        this.headbottom.addBox(0.0f, 0.0f, 0.0f, 2, 2, 14);
        this.headbottom.setRotationPoint(7.0f, 16.0f, -10.0f);
        this.headbottom.setTextureSize(64, 32);
        this.headbottom.mirror = true;
        this.setRotation(this.headbottom, 0.0f, -1.570796f, 0.0f);
        this.headright = new ModelRenderer((ModelBase)this, 54, 0);
        this.headright.addBox(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.headright.setRotationPoint(7.0f, 12.0f, -10.0f);
        this.headright.setTextureSize(64, 32);
        this.headright.mirror = true;
        this.setRotation(this.headright, 0.0f, -1.570796f, 0.0f);
        this.headleft = new ModelRenderer((ModelBase)this, 54, 0);
        this.headleft.addBox(0.0f, -1.0f, 0.0f, 2, 4, 2);
        this.headleft.setRotationPoint(-5.0f, 13.0f, -10.0f);
        this.headleft.setTextureSize(64, 32);
        this.headleft.mirror = true;
        this.setRotation(this.headleft, 0.0f, -1.570796f, 0.0f);
        this.hornright = new ModelRenderer((ModelBase)this, 0, 12);
        this.hornright.addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.hornright.setRotationPoint(6.0f, 4.0f, -3.0f);
        this.hornright.setTextureSize(64, 32);
        this.hornright.mirror = true;
        this.setRotation(this.hornright, 0.0f, -1.570796f, 0.0f);
        this.hornright2 = new ModelRenderer((ModelBase)this, 0, 13);
        this.hornright2.addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.hornright2.setRotationPoint(3.0f, 4.0f, -3.0f);
        this.hornright2.setTextureSize(64, 32);
        this.hornright2.mirror = true;
        this.setRotation(this.hornright2, 0.0f, -1.570796f, 0.0f);
        this.sensorright2 = new ModelRenderer((ModelBase)this, 0, 20);
        this.sensorright2.addBox(0.0f, 0.0f, 0.0f, 6, 2, 2);
        this.sensorright2.setRotationPoint(3.0f, 4.0f, -9.0f);
        this.sensorright2.setTextureSize(64, 32);
        this.sensorright2.mirror = true;
        this.setRotation(this.sensorright2, 0.0f, -1.570796f, 0.0f);
        this.sensorleft = new ModelRenderer((ModelBase)this, 0, 20);
        this.sensorleft.addBox(0.0f, 0.0f, 0.0f, 6, 2, 2);
        this.sensorleft.setRotationPoint(6.0f, 4.0f, -9.0f);
        this.sensorleft.setTextureSize(64, 32);
        this.sensorleft.mirror = true;
        this.setRotation(this.sensorleft, 0.0f, -1.570796f, 0.0f);
        this.wingright = new ModelRenderer((ModelBase)this, 27, 0);
        this.wingright.addBox(0.0f, 0.0f, 0.0f, 10, 2, 8);
        this.wingright.setRotationPoint(-8.0f, 16.0f, -6.0f);
        this.wingright.setTextureSize(64, 32);
        this.wingright.mirror = true;
        this.setRotation(this.wingright, 0.0f, -1.570796f, 0.0f);
        this.wingleft = new ModelRenderer((ModelBase)this, 29, 0);
        this.wingleft.addBox(0.0f, 0.0f, 0.0f, 10, 2, 8);
        this.wingleft.setRotationPoint(16.0f, 16.0f, -6.0f);
        this.wingleft.setTextureSize(64, 32);
        this.wingleft.mirror = true;
        this.setRotation(this.wingleft, 0.0f, -1.570796f, 0.0f);
        this.wingleft1 = new ModelRenderer((ModelBase)this, 29, 0);
        this.wingleft1.addBox(0.0f, 0.0f, 0.0f, 10, 2, 8);
        this.wingleft1.setRotationPoint(16.0f, 12.0f, -6.0f);
        this.wingleft1.setTextureSize(64, 32);
        this.wingleft1.mirror = true;
        this.setRotation(this.wingleft1, 0.0f, -1.570796f, 0.0f);
        this.wingright2 = new ModelRenderer((ModelBase)this, 27, 0);
        this.wingright2.addBox(0.0f, 0.0f, 0.0f, 10, 2, 8);
        this.wingright2.setRotationPoint(-8.0f, 12.0f, -6.0f);
        this.wingright2.setTextureSize(64, 32);
        this.wingright2.mirror = true;
        this.setRotation(this.wingright2, 0.0f, -1.570796f, 0.0f);
        this.hornleft = new ModelRenderer((ModelBase)this, 0, 13);
        this.hornleft.addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.hornleft.setRotationPoint(-4.0f, 4.0f, -3.0f);
        this.hornleft.setTextureSize(64, 32);
        this.hornleft.mirror = true;
        this.setRotation(this.hornleft, 0.0f, -1.570796f, 0.0f);
        this.sensorright = new ModelRenderer((ModelBase)this, 0, 20);
        this.sensorright.addBox(0.0f, 0.0f, 0.0f, 6, 2, 2);
        this.sensorright.setRotationPoint(-4.0f, 4.0f, -9.0f);
        this.sensorright.setTextureSize(64, 32);
        this.sensorright.mirror = true;
        this.setRotation(this.sensorright, 0.0f, -1.570796f, 0.0f);
        this.hornleft2 = new ModelRenderer((ModelBase)this, 0, 13);
        this.hornleft2.addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.hornleft2.setRotationPoint(-1.0f, 4.0f, -3.0f);
        this.hornleft2.setTextureSize(64, 32);
        this.hornleft2.mirror = true;
        this.setRotation(this.hornleft2, 0.0f, -1.570796f, 0.0f);
        this.sensorleft2 = new ModelRenderer((ModelBase)this, 0, 20);
        this.sensorleft2.addBox(0.0f, 0.0f, 0.0f, 6, 2, 2);
        this.sensorleft2.setRotationPoint(-1.0f, 4.0f, -9.0f);
        this.sensorleft2.setTextureSize(64, 32);
        this.sensorleft2.mirror = true;
        this.setRotation(this.sensorleft2, 0.0f, -1.570796f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.body.render(f5);
        this.headtop.render(f5);
        this.headbottom.render(f5);
        this.headright.render(f5);
        this.headleft.render(f5);
        this.hornright.render(f5);
        this.hornright2.render(f5);
        this.sensorright2.render(f5);
        this.sensorleft.render(f5);
        this.wingright.render(f5);
        this.wingleft.render(f5);
        this.wingleft1.render(f5);
        this.wingright2.render(f5);
        this.hornleft.render(f5);
        this.sensorright.render(f5);
        this.hornleft2.render(f5);
        this.sensorleft2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    }
}

