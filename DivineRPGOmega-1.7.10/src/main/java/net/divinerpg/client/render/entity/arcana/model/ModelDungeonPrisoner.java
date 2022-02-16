/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.arcana.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelDungeonPrisoner
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer headp2;
    ModelRenderer headp3;

    public ModelDungeonPrisoner() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 32, 0);
        this.head.addBox(-4.0f, -10.0f, -4.0f, 8, 10, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, 5.0f, 0.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.setRotationPoint(-5.0f, 7.0f, 0.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.setRotationPoint(5.0f, 7.0f, 0.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 7, 4);
        this.rightleg.setRotationPoint(-2.0f, 17.0f, 0.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 7, 4);
        this.leftleg.setRotationPoint(2.0f, 17.0f, 0.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.headp2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.headp2.addBox(-5.0f, 0.0f, -4.0f, 10, 5, 8);
        this.headp2.setRotationPoint(0.0f, -10.0f, 0.0f);
        this.headp2.setTextureSize(64, 32);
        this.headp2.mirror = true;
        this.setRotation(this.headp2, (float)Math.PI, 0.0f, 0.0f);
        this.headp3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.headp3.addBox(-5.0f, -5.0f, -4.0f, 10, 5, 8);
        this.headp3.setRotationPoint(0.0f, 5.0f, 0.0f);
        this.headp3.setTextureSize(64, 32);
        this.headp3.mirror = true;
        this.setRotation(this.headp3, 0.0f, 0.0f, 0.0f);
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
        this.headp2.render(f5);
        this.headp3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.head.rotateAngleY = var4 / 57.295776f;
        this.head.rotateAngleX = var5 / 57.295776f;
        this.headp2.rotateAngleY = var4 / 57.295776f;
        this.headp2.rotateAngleX = var5 / 57.295776f;
        this.headp3.rotateAngleY = var4 / 57.295776f;
        this.headp3.rotateAngleX = var5 / 57.295776f;
        this.rightarm.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 2.0f * var2 * 0.5f;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 2.0f * var2 * 0.5f;
        this.rightarm.rotateAngleZ = 0.0f;
        this.leftarm.rotateAngleZ = 0.0f;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.leftleg.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
        this.rightleg.rotateAngleY = 0.0f;
        this.leftleg.rotateAngleY = 0.0f;
        this.rightarm.rotateAngleY = 0.0f;
        this.leftarm.rotateAngleY = 0.0f;
        this.rightarm.rotateAngleZ += MathHelper.cos((float)(var3 * 0.09f)) * 0.05f + 0.05f;
        this.leftarm.rotateAngleZ -= MathHelper.cos((float)(var3 * 0.09f)) * 0.05f + 0.05f;
        this.rightarm.rotateAngleX += MathHelper.sin((float)(var3 * 0.067f)) * 0.05f;
        this.leftarm.rotateAngleX -= MathHelper.sin((float)(var3 * 0.067f)) * 0.05f;
    }
}

