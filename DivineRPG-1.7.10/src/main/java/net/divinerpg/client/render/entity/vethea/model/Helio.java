/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.entity.vethea.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Helio
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body1;
    ModelRenderer leftarmp1;
    ModelRenderer rightlegt;
    ModelRenderer leftlegt;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer body5;
    ModelRenderer body6;
    ModelRenderer leftlegm;
    ModelRenderer rightlegm;
    ModelRenderer leftleg;
    ModelRenderer rightleg;
    ModelRenderer leftarmp2;
    ModelRenderer leftarmp3;
    ModelRenderer leftarmp4;
    ModelRenderer leftarmp5;
    ModelRenderer leftarmp6;

    public Helio() {
        this.textureWidth = 256;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.setTextureSize(256, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body1 = new ModelRenderer((ModelBase)this, 76, 0);
        this.body1.addBox(-4.0f, 0.0f, -2.0f, 6, 10, 3);
        this.body1.setRotationPoint(7.0f, 0.0f, -1.0f);
        this.body1.setTextureSize(256, 32);
        this.body1.mirror = true;
        this.setRotation(this.body1, 0.0f, 0.0f, 0.0f);
        this.leftarmp1 = new ModelRenderer((ModelBase)this, 226, 6);
        this.leftarmp1.addBox(-1.0f, -3.0f, -3.0f, 8, 6, 6);
        this.leftarmp1.setRotationPoint(10.0f, 2.0f, 0.0f);
        this.leftarmp1.setTextureSize(256, 32);
        this.leftarmp1.mirror = true;
        this.setRotation(this.leftarmp1, 0.0f, 0.0f, 0.0f);
        this.rightlegt = new ModelRenderer((ModelBase)this, 199, 17);
        this.rightlegt.addBox(-3.0f, -2.0f, -3.0f, 6, 5, 6);
        this.rightlegt.setRotationPoint(-6.0f, 12.0f, 0.0f);
        this.rightlegt.setTextureSize(256, 32);
        this.rightlegt.mirror = true;
        this.setRotation(this.rightlegt, 0.0f, 0.0f, 0.0f);
        this.leftlegt = new ModelRenderer((ModelBase)this, 199, 17);
        this.leftlegt.addBox(-3.0f, -2.0f, -3.0f, 6, 5, 6);
        this.leftlegt.setRotationPoint(6.0f, 12.0f, 0.0f);
        this.leftlegt.setTextureSize(256, 32);
        this.leftlegt.mirror = true;
        this.setRotation(this.leftlegt, 0.0f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer((ModelBase)this, 76, 0);
        this.body2.addBox(-4.0f, 0.0f, -2.0f, 6, 10, 3);
        this.body2.setRotationPoint(7.0f, 0.0f, 2.0f);
        this.body2.setTextureSize(256, 32);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.0f, 0.0f, 0.0f);
        this.body3 = new ModelRenderer((ModelBase)this, 58, 0);
        this.body3.addBox(-4.0f, 0.0f, -2.0f, 6, 12, 3);
        this.body3.setRotationPoint(1.0f, -2.0f, -1.0f);
        this.body3.setTextureSize(256, 32);
        this.body3.mirror = true;
        this.setRotation(this.body3, 0.0f, 0.0f, 0.0f);
        this.body4 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body4.addBox(-4.0f, 0.0f, -2.0f, 6, 12, 3);
        this.body4.setRotationPoint(1.0f, -2.0f, 2.0f);
        this.body4.setTextureSize(256, 32);
        this.body4.mirror = true;
        this.setRotation(this.body4, 0.0f, 0.0f, 0.0f);
        this.body5 = new ModelRenderer((ModelBase)this, 40, 0);
        this.body5.addBox(-4.0f, 0.0f, -2.0f, 6, 10, 3);
        this.body5.setRotationPoint(-5.0f, 0.0f, -1.0f);
        this.body5.setTextureSize(256, 32);
        this.body5.mirror = true;
        this.setRotation(this.body5, 0.0f, 0.0f, 0.0f);
        this.body6 = new ModelRenderer((ModelBase)this, 40, 0);
        this.body6.addBox(-4.0f, 0.0f, -2.0f, 6, 10, 3);
        this.body6.setRotationPoint(-5.0f, 0.0f, 2.0f);
        this.body6.setTextureSize(256, 32);
        this.body6.mirror = true;
        this.setRotation(this.body6, 0.0f, 0.0f, 0.0f);
        this.leftlegm = new ModelRenderer((ModelBase)this, 97, 0);
        this.leftlegm.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftlegm.setRotationPoint(6.0f, 12.0f, 0.0f);
        this.leftlegm.setTextureSize(256, 32);
        this.leftlegm.mirror = true;
        this.setRotation(this.leftlegm, 0.0f, 0.0f, 0.0f);
        this.rightlegm = new ModelRenderer((ModelBase)this, 97, 0);
        this.rightlegm.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightlegm.setRotationPoint(-6.0f, 12.0f, 0.0f);
        this.rightlegm.setTextureSize(256, 32);
        this.rightlegm.mirror = true;
        this.setRotation(this.rightlegm, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 97, 20);
        this.leftleg.addBox(-3.0f, 7.0f, -3.0f, 6, 5, 6);
        this.leftleg.setRotationPoint(6.0f, 12.0f, 0.0f);
        this.leftleg.setTextureSize(256, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 97, 20);
        this.rightleg.addBox(-3.0f, 7.0f, -3.0f, 6, 5, 6);
        this.rightleg.setRotationPoint(-6.0f, 12.0f, 0.0f);
        this.rightleg.setTextureSize(256, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftarmp2 = new ModelRenderer((ModelBase)this, 140, 11);
        this.leftarmp2.addBox(3.0f, 10.0f, 10.0f, 12, 9, 12);
        this.leftarmp2.setRotationPoint(10.0f, 2.0f, 0.0f);
        this.leftarmp2.setTextureSize(256, 32);
        this.leftarmp2.mirror = true;
        this.setRotation(this.leftarmp2, -1.570796f, 0.6108652f, 0.0f);
        this.leftarmp3 = new ModelRenderer((ModelBase)this, 36, 16);
        this.leftarmp3.addBox(1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarmp3.setRotationPoint(10.0f, 2.0f, 0.0f);
        this.leftarmp3.setTextureSize(256, 32);
        this.leftarmp3.mirror = true;
        this.setRotation(this.leftarmp3, 0.0f, 0.0f, 0.0f);
        this.leftarmp4 = new ModelRenderer((ModelBase)this, 69, 17);
        this.leftarmp4.addBox(0.0f, 6.0f, 0.0f, 6, 9, 6);
        this.leftarmp4.setRotationPoint(10.0f, 2.0f, 0.0f);
        this.leftarmp4.setTextureSize(256, 32);
        this.leftarmp4.mirror = true;
        this.setRotation(this.leftarmp4, -0.5235988f, 0.0f, 0.0f);
        this.leftarmp5 = new ModelRenderer((ModelBase)this, 116, 1);
        this.leftarmp5.addBox(1.0f, 9.0f, 3.0f, 8, 9, 8);
        this.leftarmp5.setRotationPoint(10.0f, 2.0f, 0.0f);
        this.leftarmp5.setTextureSize(256, 32);
        this.leftarmp5.mirror = true;
        this.setRotation(this.leftarmp5, -0.8726646f, 0.2974289f, 0.0f);
        this.leftarmp6 = new ModelRenderer((ModelBase)this, 193, 10);
        this.leftarmp6.addBox(0.0f, 9.0f, 9.0f, 10, 9, 10);
        this.leftarmp6.setRotationPoint(10.0f, 2.0f, 0.0f);
        this.leftarmp6.setTextureSize(256, 32);
        this.leftarmp6.mirror = true;
        this.setRotation(this.leftarmp6, -1.396263f, 0.2974289f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.body1.render(f5);
        this.leftarmp1.render(f5);
        this.rightlegt.render(f5);
        this.leftlegt.render(f5);
        this.body2.render(f5);
        this.body3.render(f5);
        this.body4.render(f5);
        this.body5.render(f5);
        this.body6.render(f5);
        this.leftlegm.render(f5);
        this.rightlegm.render(f5);
        this.leftleg.render(f5);
        this.rightleg.render(f5);
        this.leftarmp2.render(f5);
        this.leftarmp3.render(f5);
        this.leftarmp4.render(f5);
        this.leftarmp5.render(f5);
        this.leftarmp6.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    }
}

