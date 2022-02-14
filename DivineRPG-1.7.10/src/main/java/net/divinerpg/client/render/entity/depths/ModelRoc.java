/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.depths;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelRoc
extends ModelBase {
    ModelRenderer Body;
    ModelRenderer Neck;
    ModelRenderer Head;
    ModelRenderer Beak;
    ModelRenderer Feet1;
    ModelRenderer Feet2;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Feather;
    ModelRenderer Feather1;
    ModelRenderer Feather2;

    public ModelRoc() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Body = new ModelRenderer((ModelBase)this, 32, 0);
        this.Body.addBox(-4.0f, -8.0f, -4.0f, 8, 6, 8);
        this.Body.setRotationPoint(0.0f, 21.0f, 0.0f);
        this.Body.setTextureSize(64, 64);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.Neck = new ModelRenderer((ModelBase)this, 44, 21);
        this.Neck.addBox(0.0f, 0.0f, 0.0f, 2, 7, 2);
        this.Neck.setRotationPoint(-1.0f, 9.0f, -6.0f);
        this.Neck.setTextureSize(64, 64);
        this.Neck.mirror = true;
        this.setRotation(this.Neck, 0.1858931f, 0.0f, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.addBox(-3.0f, -6.0f, -5.0f, 6, 6, 8);
        this.Head.setRotationPoint(0.0f, 10.0f, -5.0f);
        this.Head.setTextureSize(64, 64);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Beak = new ModelRenderer((ModelBase)this, 43, 32);
        this.Beak.addBox(-3.0f, -2.0f, -9.0f, 6, 2, 4);
        this.Beak.setRotationPoint(0.0f, 10.0f, -5.0f);
        this.Beak.setTextureSize(64, 64);
        this.Beak.mirror = true;
        this.setRotation(this.Beak, 0.0f, 0.0f, 0.0f);
        this.Feet1 = new ModelRenderer((ModelBase)this, 44, 16);
        this.Feet1.addBox(0.0f, 5.0f, -2.0f, 3, 0, 2);
        this.Feet1.setRotationPoint(-3.0f, 19.0f, -1.0f);
        this.Feet1.setTextureSize(64, 64);
        this.Feet1.mirror = true;
        this.setRotation(this.Feet1, 0.0f, 0.0f, 0.0f);
        this.Feet2 = new ModelRenderer((ModelBase)this, 44, 16);
        this.Feet2.addBox(-2.0f, 5.0f, -2.0f, 3, 0, 2);
        this.Feet2.setRotationPoint(2.0f, 19.0f, -1.0f);
        this.Feet2.setTextureSize(64, 64);
        this.Feet2.mirror = true;
        this.setRotation(this.Feet2, 0.0f, 0.0f, 0.0f);
        this.Leg1 = new ModelRenderer((ModelBase)this, 56, 24);
        this.Leg1.addBox(0.0f, 0.0f, 0.0f, 1, 5, 1);
        this.Leg1.setRotationPoint(-2.0f, 19.0f, -1.0f);
        this.Leg1.setTextureSize(64, 64);
        this.Leg1.mirror = true;
        this.setRotation(this.Leg1, 0.0f, 0.0f, 0.0f);
        this.Leg2 = new ModelRenderer((ModelBase)this, 56, 24);
        this.Leg2.addBox(-1.0f, 0.0f, 0.0f, 1, 5, 1);
        this.Leg2.setRotationPoint(2.0f, 19.0f, -1.0f);
        this.Leg2.setTextureSize(64, 64);
        this.Leg2.mirror = true;
        this.setRotation(this.Leg2, 0.0f, 0.0f, 0.0f);
        this.Feather = new ModelRenderer((ModelBase)this, 1, 16);
        this.Feather.addBox(0.0f, 0.0f, 0.0f, 19, 14, 0);
        this.Feather.setRotationPoint(-9.0f, 1.0f, 4.0f);
        this.Feather.setTextureSize(64, 64);
        this.Feather.mirror = true;
        this.setRotation(this.Feather, 0.0f, 0.0f, 0.0f);
        this.Feather1 = new ModelRenderer((ModelBase)this, 1, 16);
        this.Feather1.addBox(0.0f, 0.0f, 0.0f, 19, 14, 0);
        this.Feather1.setRotationPoint(4.0f, 1.0f, 5.0f);
        this.Feather1.setTextureSize(64, 64);
        this.Feather1.mirror = true;
        this.setRotation(this.Feather1, -0.0698132f, 0.0f, 0.6981317f);
        this.Feather2 = new ModelRenderer((ModelBase)this, 1, 16);
        this.Feather2.addBox(0.0f, 0.0f, 0.0f, 19, 14, 0);
        this.Feather2.setRotationPoint(-18.0f, 12.5f, 5.0f);
        this.Feather2.setTextureSize(64, 64);
        this.Feather2.mirror = true;
        this.setRotation(this.Feather2, -0.0698132f, 0.0f, -0.6981317f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Body.render(f5);
        this.Neck.render(f5);
        this.Head.render(f5);
        this.Beak.render(f5);
        this.Feet1.render(f5);
        this.Feet2.render(f5);
        this.Leg1.render(f5);
        this.Leg2.render(f5);
        this.Feather.render(f5);
        this.Feather1.render(f5);
        this.Feather2.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.Head.rotateAngleY = f4 / 57.295776f;
        this.Beak.rotateAngleY = f4 / 57.295776f;
        this.Leg2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * f1;
        this.Leg1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * f1;
        this.Feet2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * f1;
        this.Feet1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * f1;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}

