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

public class ModelFrost
extends ModelBase {
    ModelRenderer frostBody;
    ModelRenderer horn1;
    ModelRenderer horn2;
    ModelRenderer horn3;
    ModelRenderer horn4;
    ModelRenderer shard1;
    ModelRenderer shard2;
    ModelRenderer shard3;
    ModelRenderer shard4;

    public ModelFrost() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.frostBody = new ModelRenderer((ModelBase)this, 0, 11);
        this.frostBody.addBox(0.0f, 0.0f, 0.0f, 8, 8, 2);
        this.frostBody.setRotationPoint(-4.0f, 10.0f, -2.0f);
        this.frostBody.setTextureSize(64, 32);
        this.frostBody.mirror = true;
        this.setRotation(this.frostBody, 0.0f, 0.0f, 0.0f);
        this.horn1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.horn1.addBox(0.0f, 0.0f, 0.0f, 8, 1, 2);
        this.horn1.setRotationPoint(-9.0f, 5.0f, -2.0f);
        this.horn1.setTextureSize(64, 32);
        this.horn1.mirror = true;
        this.setRotation(this.horn1, 0.0f, 0.0f, 0.7853982f);
        this.horn2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.horn2.addBox(0.0f, 0.0f, 0.0f, 8, 1, 2);
        this.horn2.setRotationPoint(-9.0f, 22.0f, -2.0f);
        this.horn2.setTextureSize(64, 32);
        this.horn2.mirror = true;
        this.setRotation(this.horn2, 0.0f, 0.0f, -0.7853982f);
        this.horn3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.horn3.addBox(0.0f, 0.0f, 0.0f, 7, 1, 2);
        this.horn3.setRotationPoint(4.0f, 17.0f, -2.0f);
        this.horn3.setTextureSize(64, 32);
        this.horn3.mirror = true;
        this.setRotation(this.horn3, 0.0f, 0.0f, 0.7853982f);
        this.horn4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.horn4.addBox(0.0f, 0.0f, 0.0f, 8, 1, 2);
        this.horn4.setRotationPoint(3.0f, 11.0f, -2.0f);
        this.horn4.setTextureSize(64, 32);
        this.horn4.mirror = true;
        this.setRotation(this.horn4, 0.0f, 0.0f, -0.7853982f);
        this.shard1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shard1.addBox(0.0f, 0.0f, 0.0f, 14, 1, 2);
        this.shard1.setRotationPoint(-7.0f, 7.0f, -2.0f);
        this.shard1.setTextureSize(64, 32);
        this.shard1.mirror = true;
        this.setRotation(this.shard1, 0.0f, 0.0f, 0.0f);
        this.shard2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shard2.addBox(0.0f, 0.0f, 0.0f, 14, 1, 2);
        this.shard2.setRotationPoint(-7.0f, 20.53333f, -2.0f);
        this.shard2.setTextureSize(64, 32);
        this.shard2.mirror = true;
        this.setRotation(this.shard2, 0.0f, 0.0f, 0.0f);
        this.shard3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shard3.addBox(0.0f, 0.0f, 0.0f, 14, 1, 2);
        this.shard3.setRotationPoint(-7.0f, 7.0f, -2.0f);
        this.shard3.setTextureSize(64, 32);
        this.shard3.mirror = true;
        this.setRotation(this.shard3, 0.0f, 0.0f, 1.5707964f);
        this.shard4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shard4.addBox(0.0f, 0.0f, 0.0f, 14, 1, 2);
        this.shard4.setRotationPoint(8.0f, 7.0f, -2.0f);
        this.shard4.setTextureSize(64, 32);
        this.shard4.mirror = true;
        this.setRotation(this.shard4, 0.0f, 0.0f, 1.5707964f);
    }

    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
        super.render(var1, var2, var3, var4, var5, var6, var7);
        this.frostBody.render(var7);
        this.horn1.render(var7);
        this.horn2.render(var7);
        this.horn3.render(var7);
        this.horn4.render(var7);
        this.shard1.render(var7);
        this.shard2.render(var7);
        this.shard3.render(var7);
        this.shard4.render(var7);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }
}

