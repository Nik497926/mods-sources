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

public class ModelEnderTriplets
extends ModelBase {
    ModelRenderer Head;
    ModelRenderer Jaw;
    ModelRenderer Tentacle11;
    ModelRenderer Tentacle12;
    ModelRenderer Tentacle31;
    ModelRenderer Tentacle511;
    ModelRenderer Head1;
    ModelRenderer Jaw1;
    ModelRenderer Tentacle132;
    ModelRenderer Tentacle122;
    ModelRenderer Tentacle121;
    ModelRenderer Tentacle131;
    ModelRenderer Jaw2;
    ModelRenderer Head2;
    ModelRenderer Tentacle211;
    ModelRenderer Tentacle212;
    ModelRenderer Tentacle231;
    ModelRenderer Tentacle232;
    ModelRenderer Tentacle222;
    ModelRenderer Tentacle221;
    ModelRenderer Tentacle332;
    ModelRenderer Tentacle432;
    ModelRenderer Tentacle311;
    ModelRenderer Tentacle411;

    public ModelEnderTriplets() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.addBox(-5.0f, -5.0f, -5.0f, 10, 10, 10);
        this.Head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Head.setTextureSize(64, 32);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, -1.5707964f, 0.0f);
        this.Jaw = new ModelRenderer((ModelBase)this, 0, 20);
        this.Jaw.addBox(-5.0f, 0.0f, -10.0f, 10, 1, 10);
        this.Jaw.setRotationPoint(0.0f, 5.0f, 5.0f);
        this.Jaw.setTextureSize(64, 32);
        this.Jaw.mirror = true;
        this.setRotation(this.Jaw, 0.1745329f, 0.0f, 0.0f);
        this.Tentacle11 = new ModelRenderer((ModelBase)this, 40, 0);
        this.Tentacle11.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 3);
        this.Tentacle11.setRotationPoint(5.0f, 0.0f, 0.0f);
        this.Tentacle11.setTextureSize(64, 32);
        this.Tentacle11.mirror = true;
        this.setRotation(this.Tentacle11, 0.0f, 1.5707964f, 0.0f);
        this.Tentacle12 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle12.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle12.setRotationPoint(9.0f, 0.0f, 0.0f);
        this.Tentacle12.setTextureSize(64, 32);
        this.Tentacle12.mirror = true;
        this.setRotation(this.Tentacle12, 0.0f, 4.712389f, 0.0f);
        this.Tentacle31 = new ModelRenderer((ModelBase)this, 52, 0);
        this.Tentacle31.addBox(-1.0f, -3.0f, -1.0f, 2, 3, 2);
        this.Tentacle31.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.Tentacle31.setTextureSize(64, 32);
        this.Tentacle31.mirror = true;
        this.setRotation(this.Tentacle31, 0.0f, 0.0f, 0.0f);
        this.Tentacle511 = new ModelRenderer((ModelBase)this, 11, 9);
        this.Tentacle511.addBox(-1.0f, -1.0f, 0.0f, 3, 2, 9);
        this.Tentacle511.setRotationPoint(-5.0f, 15.0f, 4.0f);
        this.Tentacle511.setTextureSize(64, 32);
        this.Tentacle511.mirror = true;
        this.setRotation(this.Tentacle511, 0.0f, 1.5707964f, 0.0f);
        this.Head1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head1.addBox(-5.0f, -5.0f, -5.0f, 10, 10, 10);
        this.Head1.setRotationPoint(-8.0f, 16.0f, 0.0f);
        this.Head1.setTextureSize(64, 32);
        this.Head1.mirror = true;
        this.setRotation(this.Head1, 0.0f, -1.5707964f, 0.0f);
        this.Jaw1 = new ModelRenderer((ModelBase)this, 0, 20);
        this.Jaw1.addBox(-5.0f, 0.0f, -10.0f, 10, 1, 10);
        this.Jaw1.setRotationPoint(-8.0f, 21.0f, 5.0f);
        this.Jaw1.setTextureSize(64, 32);
        this.Jaw1.mirror = true;
        this.setRotation(this.Jaw1, 0.1745329f, 0.0f, 0.0f);
        this.Tentacle132 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle132.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle132.setRotationPoint(8.0f, 7.0f, 0.0f);
        this.Tentacle132.setTextureSize(64, 32);
        this.Tentacle132.mirror = true;
        this.setRotation(this.Tentacle132, 0.0f, 4.712389f, 0.0f);
        this.Tentacle122 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle122.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle122.setRotationPoint(-9.0f, 0.0f, 0.0f);
        this.Tentacle122.setTextureSize(64, 32);
        this.Tentacle122.mirror = true;
        this.setRotation(this.Tentacle122, 0.0f, 4.712389f, 0.0f);
        this.Tentacle121 = new ModelRenderer((ModelBase)this, 40, 0);
        this.Tentacle121.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 3);
        this.Tentacle121.setRotationPoint(-5.0f, 0.0f, 0.0f);
        this.Tentacle121.setTextureSize(64, 32);
        this.Tentacle121.mirror = true;
        this.setRotation(this.Tentacle121, 0.0f, 4.712389f, 0.0f);
        this.Tentacle131 = new ModelRenderer((ModelBase)this, 52, 0);
        this.Tentacle131.addBox(-1.0f, -3.0f, -1.0f, 2, 3, 2);
        this.Tentacle131.setRotationPoint(-8.0f, 11.0f, 0.0f);
        this.Tentacle131.setTextureSize(64, 32);
        this.Tentacle131.mirror = true;
        this.setRotation(this.Tentacle131, 0.0f, 0.0f, 0.0f);
        this.Jaw2 = new ModelRenderer((ModelBase)this, 0, 20);
        this.Jaw2.addBox(-5.0f, 0.0f, -10.0f, 10, 1, 10);
        this.Jaw2.setRotationPoint(8.0f, 21.0f, 5.0f);
        this.Jaw2.setTextureSize(64, 32);
        this.Jaw2.mirror = true;
        this.setRotation(this.Jaw2, 0.1745329f, 0.0f, 0.0f);
        this.Head2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head2.addBox(-5.0f, -5.0f, -5.0f, 10, 10, 10);
        this.Head2.setRotationPoint(8.0f, 16.0f, 0.0f);
        this.Head2.setTextureSize(64, 32);
        this.Head2.mirror = true;
        this.setRotation(this.Head2, 0.0f, -1.5707964f, 0.0f);
        this.Tentacle211 = new ModelRenderer((ModelBase)this, 40, 0);
        this.Tentacle211.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 3);
        this.Tentacle211.setRotationPoint(13.0f, 16.0f, 0.0f);
        this.Tentacle211.setTextureSize(64, 32);
        this.Tentacle211.mirror = true;
        this.setRotation(this.Tentacle211, 0.0f, 1.5707964f, 0.0f);
        this.Tentacle212 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle212.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle212.setRotationPoint(17.0f, 16.0f, 0.0f);
        this.Tentacle212.setTextureSize(64, 32);
        this.Tentacle212.mirror = true;
        this.setRotation(this.Tentacle212, 0.0f, 4.712389f, 0.0f);
        this.Tentacle231 = new ModelRenderer((ModelBase)this, 52, 0);
        this.Tentacle231.addBox(-1.0f, -3.0f, -1.0f, 2, 3, 2);
        this.Tentacle231.setRotationPoint(8.0f, 11.0f, 0.0f);
        this.Tentacle231.setTextureSize(64, 32);
        this.Tentacle231.mirror = true;
        this.setRotation(this.Tentacle231, 0.0f, 0.0f, 0.0f);
        this.Tentacle232 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle232.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle232.setRotationPoint(0.0f, -9.0f, 0.0f);
        this.Tentacle232.setTextureSize(64, 32);
        this.Tentacle232.mirror = true;
        this.setRotation(this.Tentacle232, 0.0f, 4.712389f, 0.0f);
        this.Tentacle222 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle222.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle222.setRotationPoint(-17.0f, 16.0f, 0.0f);
        this.Tentacle222.setTextureSize(64, 32);
        this.Tentacle222.mirror = true;
        this.setRotation(this.Tentacle222, 0.0f, 4.712389f, 0.0f);
        this.Tentacle221 = new ModelRenderer((ModelBase)this, 40, 0);
        this.Tentacle221.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 3);
        this.Tentacle221.setRotationPoint(-13.0f, 16.0f, 0.0f);
        this.Tentacle221.setTextureSize(64, 32);
        this.Tentacle221.mirror = true;
        this.setRotation(this.Tentacle221, 0.0f, 4.712389f, 0.0f);
        this.Tentacle332 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle332.addBox(0.0f, 0.0f, 0.0f, 5, 2, 2);
        this.Tentacle332.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Tentacle332.setTextureSize(64, 32);
        this.Tentacle332.mirror = true;
        this.setRotation(this.Tentacle332, 0.0f, 4.712389f, 0.0f);
        this.Tentacle432 = new ModelRenderer((ModelBase)this, 40, 6);
        this.Tentacle432.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle432.setRotationPoint(-8.0f, 7.0f, 0.0f);
        this.Tentacle432.setTextureSize(64, 32);
        this.Tentacle432.mirror = true;
        this.setRotation(this.Tentacle432, 0.0f, 4.712389f, 0.0f);
        this.Tentacle311 = new ModelRenderer((ModelBase)this, 11, 10);
        this.Tentacle311.addBox(-1.0f, -1.0f, 0.0f, 3, 2, 9);
        this.Tentacle311.setRotationPoint(0.0f, 6.0f, 4.0f);
        this.Tentacle311.setTextureSize(64, 32);
        this.Tentacle311.mirror = true;
        this.setRotation(this.Tentacle311, -0.8028515f, 1.5707964f, 0.0f);
        this.Tentacle411 = new ModelRenderer((ModelBase)this, 11, 10);
        this.Tentacle411.addBox(-1.0f, -1.0f, 0.0f, 3, 2, 9);
        this.Tentacle411.setRotationPoint(-6.0f, 12.0f, 4.0f);
        this.Tentacle411.setTextureSize(64, 32);
        this.Tentacle411.mirror = true;
        this.setRotation(this.Tentacle411, 0.8028515f, 1.5707964f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Head.render(f5);
        this.Jaw.render(f5);
        this.Tentacle11.render(f5);
        this.Tentacle12.render(f5);
        this.Tentacle31.render(f5);
        this.Tentacle511.render(f5);
        this.Head1.render(f5);
        this.Jaw1.render(f5);
        this.Tentacle132.render(f5);
        this.Tentacle122.render(f5);
        this.Tentacle121.render(f5);
        this.Tentacle131.render(f5);
        this.Jaw2.render(f5);
        this.Head2.render(f5);
        this.Tentacle211.render(f5);
        this.Tentacle212.render(f5);
        this.Tentacle231.render(f5);
        this.Tentacle232.render(f5);
        this.Tentacle222.render(f5);
        this.Tentacle221.render(f5);
        this.Tentacle332.render(f5);
        this.Tentacle432.render(f5);
        this.Tentacle311.render(f5);
        this.Tentacle411.render(f5);
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

