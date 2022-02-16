/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.entity.vanilla.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShark
extends ModelBase {
    ModelRenderer bodyTop;
    ModelRenderer flipperL;
    ModelRenderer flipperR;
    ModelRenderer tailConnector;
    ModelRenderer tailTop;
    ModelRenderer tailBottom;
    ModelRenderer fin;
    ModelRenderer bodyBottom;
    ModelRenderer bodyMiddle;
    ModelRenderer bodyBack;

    public ModelShark() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.bodyTop = new ModelRenderer((ModelBase)this, 0, 0);
        this.bodyTop.addBox(0.0f, 0.0f, 0.0f, 8, 2, 26);
        this.bodyTop.setRotationPoint(-5.0f, 18.0f, -11.0f);
        this.bodyTop.setTextureSize(64, 64);
        this.bodyTop.mirror = true;
        this.setRotation(this.bodyTop, 0.0f, 0.0f, 0.0f);
        this.flipperL = new ModelRenderer((ModelBase)this, 0, 58);
        this.flipperL.addBox(0.0f, 0.0f, -3.0f, 10, 2, 4);
        this.flipperL.setRotationPoint(1.0f, 21.0f, 2.0f);
        this.flipperL.setTextureSize(64, 64);
        this.flipperL.mirror = true;
        this.setRotation(this.flipperL, 0.0f, -0.6108652f, 0.0f);
        this.flipperR = new ModelRenderer((ModelBase)this, 0, 58);
        this.flipperR.addBox(-6.0f, 0.0f, -3.0f, 10, 2, 4);
        this.flipperR.setRotationPoint(-6.0f, 21.0f, 4.0f);
        this.flipperR.setTextureSize(64, 64);
        this.flipperR.mirror = true;
        this.setRotation(this.flipperR, 0.0f, 0.6108652f, 0.0f);
        this.tailConnector = new ModelRenderer((ModelBase)this, 0, 2);
        this.tailConnector.addBox(-2.0f, -1.0f, 0.0f, 4, 2, 5);
        this.tailConnector.setRotationPoint(-1.0f, 21.0f, 16.0f);
        this.tailConnector.setTextureSize(64, 64);
        this.tailConnector.mirror = true;
        this.setRotation(this.tailConnector, 0.0f, 0.0f, 1.5707964f);
        this.tailTop = new ModelRenderer((ModelBase)this, 0, 0);
        this.tailTop.addBox(-1.0f, -1.0f, 0.0f, 2, 3, 8);
        this.tailTop.setRotationPoint(-1.0f, 20.0f, 20.0f);
        this.tailTop.setTextureSize(64, 64);
        this.tailTop.mirror = true;
        this.setRotation(this.tailTop, 0.6108652f, 0.0f, 0.0f);
        this.tailBottom = new ModelRenderer((ModelBase)this, 0, 0);
        this.tailBottom.addBox(-1.0f, -1.0f, 0.0f, 2, 3, 8);
        this.tailBottom.setRotationPoint(-1.0f, 21.0f, 19.0f);
        this.tailBottom.setTextureSize(64, 64);
        this.tailBottom.mirror = true;
        this.setRotation(this.tailBottom, -0.6108652f, 0.0f, 0.0f);
        this.fin = new ModelRenderer((ModelBase)this, 0, 0);
        this.fin.addBox(0.0f, 0.0f, 0.0f, 2, 3, 8);
        this.fin.setRotationPoint(-2.0f, 18.0f, 0.0f);
        this.fin.setTextureSize(64, 64);
        this.fin.mirror = true;
        this.setRotation(this.fin, 0.9599311f, 0.0f, 0.0f);
        this.bodyBottom = new ModelRenderer((ModelBase)this, 0, 32);
        this.bodyBottom.addBox(0.0f, 0.0f, 0.0f, 8, 2, 24);
        this.bodyBottom.setRotationPoint(-5.0f, 22.0f, -9.0f);
        this.bodyBottom.setTextureSize(64, 64);
        this.bodyBottom.mirror = true;
        this.setRotation(this.bodyBottom, 0.0f, 0.0f, 0.0f);
        this.bodyMiddle = new ModelRenderer((ModelBase)this, 3, 1);
        this.bodyMiddle.addBox(0.0f, 0.0f, 0.0f, 8, 2, 21);
        this.bodyMiddle.setRotationPoint(-5.0f, 20.0f, -6.0f);
        this.bodyMiddle.setTextureSize(64, 64);
        this.bodyMiddle.mirror = true;
        this.setRotation(this.bodyMiddle, 0.0f, 0.0f, 0.0f);
        this.bodyBack = new ModelRenderer((ModelBase)this, 24, 8);
        this.bodyBack.addBox(0.0f, 0.0f, 0.0f, 8, 6, 1);
        this.bodyBack.setRotationPoint(-5.0f, 18.0f, 15.0f);
        this.bodyBack.setTextureSize(64, 64);
        this.bodyBack.mirror = true;
        this.setRotation(this.bodyBack, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
        super.render(var1, var2, var3, var4, var5, var6, var7);
        this.bodyTop.render(var7);
        this.flipperL.render(var7);
        this.flipperR.render(var7);
        this.tailConnector.render(var7);
        this.tailTop.render(var7);
        this.tailBottom.render(var7);
        this.fin.render(var7);
        this.bodyBottom.render(var7);
        this.bodyMiddle.render(var7);
        this.bodyBack.render(var7);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }
}

