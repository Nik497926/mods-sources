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

public class ModelDungeonDemon
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer headp2;
    ModelRenderer headp3;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer backplate;
    ModelRenderer restplate;
    ModelRenderer connector;
    ModelRenderer shape9;
    ModelRenderer shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;

    public ModelDungeonDemon() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 32, 0);
        this.head.addBox(-4.0f, -10.0f, -4.0f, 8, 10, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 5, 4);
        this.body.setRotationPoint(0.0f, 9.0f, 2.0f);
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
        this.Shape1 = new ModelRenderer((ModelBase)this, 46, 14);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.Shape1.setRotationPoint(0.0f, 4.0f, 4.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, -1.5707964f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 46, 14);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.Shape2.setRotationPoint(0.0f, -2.0f, 4.0f);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, -1.5707964f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 46, 14);
        this.Shape3.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.Shape3.setRotationPoint(0.0f, 0.0f, 4.0f);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, -1.5707964f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 46, 14);
        this.Shape4.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.Shape4.setRotationPoint(0.0f, 2.0f, 4.0f);
        this.Shape4.setTextureSize(64, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0f, 0.0f, -1.5707964f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 46, 14);
        this.Shape5.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.Shape5.setRotationPoint(0.0f, 10.0f, 4.0f);
        this.Shape5.setTextureSize(64, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, -1.5707964f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 46, 14);
        this.Shape6.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.Shape6.setRotationPoint(0.0f, 12.0f, 4.0f);
        this.Shape6.setTextureSize(64, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, -1.5707964f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 46, 14);
        this.Shape7.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.Shape7.setRotationPoint(0.0f, 8.0f, 4.0f);
        this.Shape7.setTextureSize(64, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, -1.5707964f);
        this.Shape8 = new ModelRenderer((ModelBase)this, 46, 14);
        this.Shape8.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.Shape8.setRotationPoint(0.0f, 6.0f, 4.0f);
        this.Shape8.setTextureSize(64, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, -1.5707964f);
        this.backplate = new ModelRenderer((ModelBase)this, 16, 16);
        this.backplate.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.backplate.setRotationPoint(0.0f, 5.0f, 0.0f);
        this.backplate.setTextureSize(64, 32);
        this.backplate.mirror = true;
        this.setRotation(this.backplate, 0.0f, 0.0f, 0.0f);
        this.restplate = new ModelRenderer((ModelBase)this, 16, 16);
        this.restplate.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.restplate.setRotationPoint(0.0f, -3.0f, 6.0f);
        this.restplate.setTextureSize(64, 32);
        this.restplate.mirror = true;
        this.setRotation(this.restplate, 0.0f, 0.0f, 0.0f);
        this.connector = new ModelRenderer((ModelBase)this, 16, 16);
        this.connector.addBox(-4.0f, 0.0f, -2.0f, 8, 5, 4);
        this.connector.setRotationPoint(0.0f, 9.0f, 6.0f);
        this.connector.setTextureSize(64, 32);
        this.connector.mirror = true;
        this.setRotation(this.connector, 0.0f, 0.0f, 0.0f);
        this.shape9 = new ModelRenderer((ModelBase)this, 46, 14);
        this.shape9.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.shape9.setRotationPoint(1.0f, -3.0f, 5.0f);
        this.shape9.setTextureSize(64, 32);
        this.shape9.mirror = true;
        this.setRotation(this.shape9, 0.0f, 0.0f, 1.5707964f);
        this.shape10 = new ModelRenderer((ModelBase)this, 46, 14);
        this.shape10.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.shape10.setRotationPoint(1.0f, -1.0f, 5.0f);
        this.shape10.setTextureSize(64, 32);
        this.shape10.mirror = true;
        this.setRotation(this.shape10, 0.0f, 0.0f, 1.5707964f);
        this.Shape11 = new ModelRenderer((ModelBase)this, 46, 14);
        this.Shape11.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.Shape11.setRotationPoint(1.0f, 1.0f, 5.0f);
        this.Shape11.setTextureSize(64, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0f, 0.0f, 1.5707964f);
        this.Shape12 = new ModelRenderer((ModelBase)this, 46, 14);
        this.Shape12.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.Shape12.setRotationPoint(1.0f, 3.0f, 5.0f);
        this.Shape12.setTextureSize(64, 32);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0f, 0.0f, 1.5707964f);
        this.Shape13 = new ModelRenderer((ModelBase)this, 46, 14);
        this.Shape13.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.Shape13.setRotationPoint(1.0f, 5.0f, 5.0f);
        this.Shape13.setTextureSize(64, 32);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0f, 0.0f, 1.5707964f);
        this.Shape14 = new ModelRenderer((ModelBase)this, 46, 14);
        this.Shape14.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.Shape14.setRotationPoint(1.0f, 7.0f, 5.0f);
        this.Shape14.setTextureSize(64, 32);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0f, 0.0f, 1.5707964f);
        this.Shape15 = new ModelRenderer((ModelBase)this, 46, 14);
        this.Shape15.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.Shape15.setRotationPoint(1.0f, 9.0f, 5.0f);
        this.Shape15.setTextureSize(64, 32);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0f, 0.0f, 1.5707964f);
        this.Shape16 = new ModelRenderer((ModelBase)this, 46, 14);
        this.Shape16.addBox(0.0f, 0.0f, 0.0f, 1, 17, 1);
        this.Shape16.setRotationPoint(1.0f, 11.0f, 5.0f);
        this.Shape16.setTextureSize(64, 32);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0f, 0.0f, 1.5707964f);
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
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
        this.Shape7.render(f5);
        this.Shape8.render(f5);
        this.backplate.render(f5);
        this.restplate.render(f5);
        this.connector.render(f5);
        this.shape9.render(f5);
        this.shape10.render(f5);
        this.Shape11.render(f5);
        this.Shape12.render(f5);
        this.Shape13.render(f5);
        this.Shape14.render(f5);
        this.Shape15.render(f5);
        this.Shape16.render(f5);
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

