/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.vethea.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class MysteriousMan
extends ModelBase {
    ModelRenderer head;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer body;
    ModelRenderer neck;
    ModelRenderer facemask1;
    ModelRenderer facemask2;
    ModelRenderer facemask3;
    ModelRenderer facemask4;

    public MysteriousMan() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, -2.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.setRotationPoint(2.0f, 12.0f, 0.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.neck = new ModelRenderer((ModelBase)this, 20, 18);
        this.neck.addBox(-4.0f, 0.0f, -2.0f, 4, 2, 4);
        this.neck.setRotationPoint(2.0f, -2.0f, 0.0f);
        this.neck.setTextureSize(64, 32);
        this.neck.mirror = true;
        this.setRotation(this.neck, 0.0f, 0.0f, 0.0f);
        this.facemask1 = new ModelRenderer((ModelBase)this, 37, 3);
        this.facemask1.addBox(-4.0f, 0.0f, -2.0f, 8, 2, 0);
        this.facemask1.setRotationPoint(0.0f, -2.0f, -2.0f);
        this.facemask1.setTextureSize(64, 32);
        this.facemask1.mirror = true;
        this.setRotation(this.facemask1, 0.0f, 0.0f, 0.0f);
        this.facemask2 = new ModelRenderer((ModelBase)this, 37, 3);
        this.facemask2.addBox(-4.0f, 0.0f, -2.0f, 8, 2, 0);
        this.facemask2.setRotationPoint(0.0f, -2.0f, 6.0f);
        this.facemask2.setTextureSize(64, 32);
        this.facemask2.mirror = true;
        this.setRotation(this.facemask2, 0.0f, 0.0f, 0.0f);
        this.facemask3 = new ModelRenderer((ModelBase)this, 37, 3);
        this.facemask3.addBox(4.0f, 0.0f, -2.0f, 0, 2, 8);
        this.facemask3.setRotationPoint(0.0f, -2.0f, -2.0f);
        this.facemask3.setTextureSize(64, 32);
        this.facemask3.mirror = true;
        this.setRotation(this.facemask3, 0.0f, 0.0f, 0.0f);
        this.facemask4 = new ModelRenderer((ModelBase)this, 37, 3);
        this.facemask4.addBox(-4.0f, 0.0f, -2.0f, 0, 2, 8);
        this.facemask4.setRotationPoint(0.0f, -2.0f, -2.0f);
        this.facemask4.setTextureSize(64, 32);
        this.facemask4.mirror = true;
        this.setRotation(this.facemask4, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
        this.body.render(f5);
        this.neck.render(f5);
        this.facemask1.render(f5);
        this.facemask2.render(f5);
        this.facemask3.render(f5);
        this.facemask4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.leftleg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.rightarm.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
    }
}

