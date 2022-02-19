/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.twilight.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMadivel
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer neckbone;
    ModelRenderer neck;
    ModelRenderer headbone;
    ModelRenderer rightarmbones;
    ModelRenderer leftarmbones;

    public ModelMadivel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 35, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 6, 9, 7);
        this.head.setRotationPoint(1.0f, -13.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, -7.0f, 0.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.setRotationPoint(-5.0f, -5.0f, 0.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.setRotationPoint(5.0f, -5.0f, 0.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 9);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 19, 4);
        this.rightleg.setRotationPoint(-2.0f, 5.0f, 0.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 9);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 19, 4);
        this.leftleg.setRotationPoint(2.0f, 5.0f, 0.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.neckbone = new ModelRenderer((ModelBase)this, 2, 1);
        this.neckbone.addBox(-6.0f, 1.0f, -1.0f, 10, 2, 2);
        this.neckbone.setRotationPoint(1.0f, -13.0f, 0.0f);
        this.neckbone.setTextureSize(64, 32);
        this.neckbone.mirror = true;
        this.setRotation(this.neckbone, 0.0f, 0.0f, 0.0f);
        this.neck = new ModelRenderer((ModelBase)this, 36, 20);
        this.neck.addBox(0.0f, 0.0f, 0.0f, 4, 5, 4);
        this.neck.setRotationPoint(-2.0f, -12.0f, -2.0f);
        this.neck.setTextureSize(64, 32);
        this.neck.mirror = true;
        this.setRotation(this.neck, 0.0f, 0.0f, 0.0f);
        this.headbone = new ModelRenderer((ModelBase)this, 2, 1);
        this.headbone.addBox(-6.0f, -2.0f, 0.0f, 10, 2, 2);
        this.headbone.setRotationPoint(1.0f, -18.0f, -1.0f);
        this.headbone.setTextureSize(64, 32);
        this.headbone.mirror = true;
        this.setRotation(this.headbone, 0.0f, 0.0f, 0.0f);
        this.rightarmbones = new ModelRenderer((ModelBase)this, 40, 24);
        this.rightarmbones.addBox(-8.0f, -2.0f, -1.0f, 6, 3, 2);
        this.rightarmbones.setRotationPoint(-5.0f, -1.0f, 0.0f);
        this.rightarmbones.setTextureSize(64, 32);
        this.rightarmbones.mirror = true;
        this.setRotation(this.rightarmbones, 0.0f, 0.0f, 0.0f);
        this.leftarmbones = new ModelRenderer((ModelBase)this, 40, 24);
        this.leftarmbones.addBox(2.0f, 2.0f, -1.0f, 6, 3, 2);
        this.leftarmbones.setRotationPoint(5.0f, -5.0f, 0.0f);
        this.leftarmbones.setTextureSize(64, 32);
        this.leftarmbones.mirror = true;
        this.setRotation(this.leftarmbones, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.body.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
        this.neckbone.render(f5);
        this.neck.render(f5);
        this.headbone.render(f5);
        this.rightarmbones.render(f5);
        this.leftarmbones.render(f5);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.head.rotateAngleY = var4 / 57.295776f;
        this.head.rotateAngleX = var5 / 57.295776f;
        this.rightarm.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 2.0f * var2 * 0.5f;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 2.0f * var2 * 0.5f;
        this.rightarm.rotateAngleZ = 0.0f;
        this.leftarm.rotateAngleZ = 0.0f;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.leftleg.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
        this.rightleg.rotateAngleY = 0.0f;
        this.leftleg.rotateAngleY = 0.0f;
    }
}

