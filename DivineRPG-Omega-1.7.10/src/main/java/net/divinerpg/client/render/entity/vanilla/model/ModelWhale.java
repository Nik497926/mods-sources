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

public class ModelWhale
extends ModelBase {
    ModelRenderer body;
    ModelRenderer flipperL;
    ModelRenderer flipperR;
    ModelRenderer tailConnector;
    ModelRenderer tail;

    public ModelWhale() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.body = new ModelRenderer((ModelBase)this, 0, 0);
        this.body.addBox(0.0f, 0.0f, 0.0f, 8, 6, 24);
        this.body.setRotationPoint(-5.0f, 18.0f, -9.0f);
        this.body.setTextureSize(64, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.flipperL = new ModelRenderer((ModelBase)this, 0, 42);
        this.flipperL.addBox(0.0f, 0.0f, -3.0f, 6, 2, 6);
        this.flipperL.setRotationPoint(2.0f, 21.0f, 0.0f);
        this.flipperL.setTextureSize(64, 64);
        this.flipperL.mirror = true;
        this.setRotation(this.flipperL, 0.0f, 0.0f, 0.0f);
        this.flipperR = new ModelRenderer((ModelBase)this, 0, 42);
        this.flipperR.addBox(-6.0f, 0.0f, -3.0f, 6, 2, 6);
        this.flipperR.setRotationPoint(-4.0f, 21.0f, 0.0f);
        this.flipperR.setTextureSize(64, 64);
        this.flipperR.mirror = true;
        this.setRotation(this.flipperR, 0.0f, 0.0f, 0.0f);
        this.tailConnector = new ModelRenderer((ModelBase)this, 0, 42);
        this.tailConnector.addBox(-3.0f, 0.0f, 0.0f, 6, 2, 6);
        this.tailConnector.setRotationPoint(-1.0f, 19.0f, 15.0f);
        this.tailConnector.setTextureSize(64, 64);
        this.tailConnector.mirror = true;
        this.setRotation(this.tailConnector, 0.0f, 0.0f, 0.0f);
        this.tail = new ModelRenderer((ModelBase)this, 0, 58);
        this.tail.addBox(-6.0f, 0.0f, 0.0f, 12, 2, 4);
        this.tail.setRotationPoint(-1.0f, 19.0f, 21.0f);
        this.tail.setTextureSize(64, 64);
        this.tail.mirror = true;
        this.setRotation(this.tail, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
        super.render(var1, var2, var3, var4, var5, var6, var7);
        this.body.render(var7);
        this.flipperL.render(var7);
        this.flipperR.render(var7);
        this.tailConnector.render(var7);
        this.tail.render(var7);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }
}

