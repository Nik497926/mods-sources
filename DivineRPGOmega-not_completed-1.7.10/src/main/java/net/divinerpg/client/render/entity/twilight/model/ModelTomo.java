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

public class ModelTomo
extends ModelBase {
    ModelRenderer body;
    ModelRenderer headtop;
    ModelRenderer headbottom;
    ModelRenderer headright;
    ModelRenderer headleft;
    ModelRenderer hornright;
    ModelRenderer hornleft;
    ModelRenderer sensorright;
    ModelRenderer sensorleft;
    ModelRenderer wingright;
    ModelRenderer wingleft;

    public ModelTomo() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.body = new ModelRenderer((ModelBase)this, 0, 7);
        this.body.addBox(0.0f, 0.0f, 0.0f, 16, 9, 16);
        this.body.setRotationPoint(8.0f, 10.0f, -8.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, -1.5707964f, 0.0f);
        this.headtop = new ModelRenderer((ModelBase)this, 15, 14);
        this.headtop.addBox(0.0f, 0.0f, 0.0f, 2, 2, 16);
        this.headtop.setRotationPoint(8.0f, 10.0f, -10.0f);
        this.headtop.setTextureSize(64, 32);
        this.headtop.mirror = true;
        this.setRotation(this.headtop, 0.0f, -1.5707964f, 0.0f);
        this.headbottom = new ModelRenderer((ModelBase)this, 32, 0);
        this.headbottom.addBox(0.0f, 0.0f, 0.0f, 2, 2, 14);
        this.headbottom.setRotationPoint(7.0f, 16.0f, -10.0f);
        this.headbottom.setTextureSize(64, 32);
        this.headbottom.mirror = true;
        this.setRotation(this.headbottom, 0.0f, -1.5707964f, 0.0f);
        this.headright = new ModelRenderer((ModelBase)this, 54, 0);
        this.headright.addBox(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.headright.setRotationPoint(7.0f, 12.0f, -10.0f);
        this.headright.setTextureSize(64, 32);
        this.headright.mirror = true;
        this.setRotation(this.headright, 0.0f, -1.5707964f, 0.0f);
        this.headleft = new ModelRenderer((ModelBase)this, 54, 0);
        this.headleft.addBox(0.0f, -1.0f, 0.0f, 2, 4, 2);
        this.headleft.setRotationPoint(-5.0f, 13.0f, -10.0f);
        this.headleft.setTextureSize(64, 32);
        this.headleft.mirror = true;
        this.setRotation(this.headleft, 0.0f, -1.5707964f, 0.0f);
        this.hornright = new ModelRenderer((ModelBase)this, 0, 12);
        this.hornright.addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.hornright.setRotationPoint(6.0f, 4.0f, -3.0f);
        this.hornright.setTextureSize(64, 32);
        this.hornright.mirror = true;
        this.setRotation(this.hornright, 0.0f, -1.5707964f, 0.0f);
        this.hornleft = new ModelRenderer((ModelBase)this, 0, 13);
        this.hornleft.addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.hornleft.setRotationPoint(-4.0f, 4.0f, -3.0f);
        this.hornleft.setTextureSize(64, 32);
        this.hornleft.mirror = true;
        this.setRotation(this.hornleft, 0.0f, -1.5707964f, 0.0f);
        this.sensorright = new ModelRenderer((ModelBase)this, 0, 20);
        this.sensorright.addBox(0.0f, 0.0f, 0.0f, 6, 2, 2);
        this.sensorright.setRotationPoint(-4.0f, 4.0f, -9.0f);
        this.sensorright.setTextureSize(64, 32);
        this.sensorright.mirror = true;
        this.setRotation(this.sensorright, 0.0f, -1.5707964f, 0.0f);
        this.sensorleft = new ModelRenderer((ModelBase)this, 0, 20);
        this.sensorleft.addBox(0.0f, 0.0f, 0.0f, 6, 2, 2);
        this.sensorleft.setRotationPoint(6.0f, 4.0f, -9.0f);
        this.sensorleft.setTextureSize(64, 32);
        this.sensorleft.mirror = true;
        this.setRotation(this.sensorleft, 0.0f, -1.5707964f, 0.0f);
        this.wingright = new ModelRenderer((ModelBase)this, 27, 0);
        this.wingright.addBox(0.0f, 0.0f, 0.0f, 10, 2, 8);
        this.wingright.setRotationPoint(-8.0f, 12.0f, -6.0f);
        this.wingright.setTextureSize(64, 32);
        this.wingright.mirror = true;
        this.setRotation(this.wingright, 0.0f, -1.5707964f, 0.0f);
        this.wingleft = new ModelRenderer((ModelBase)this, 29, 0);
        this.wingleft.addBox(0.0f, 0.0f, 0.0f, 10, 2, 8);
        this.wingleft.setRotationPoint(16.0f, 12.0f, -6.0f);
        this.wingleft.setTextureSize(64, 32);
        this.wingleft.mirror = true;
        this.setRotation(this.wingleft, 0.0f, -1.5707964f, 0.0f);
    }

    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
        super.render(var1, var2, var3, var4, var5, var6, var7);
        this.body.render(var7);
        this.headtop.render(var7);
        this.headbottom.render(var7);
        this.headright.render(var7);
        this.headleft.render(var7);
        this.hornright.render(var7);
        this.hornleft.render(var7);
        this.sensorright.render(var7);
        this.sensorleft.render(var7);
        this.wingright.render(var7);
        this.wingleft.render(var7);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }
}

