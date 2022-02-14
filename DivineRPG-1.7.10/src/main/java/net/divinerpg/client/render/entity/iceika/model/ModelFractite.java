/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.entity.iceika.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelFractite
extends ModelBase {
    ModelRenderer frostBody;
    ModelRenderer shard1;
    ModelRenderer shard2;
    ModelRenderer shard7;
    ModelRenderer shard8;
    ModelRenderer shard4;
    ModelRenderer shard3;
    ModelRenderer shard5;
    ModelRenderer shard6;

    public ModelFractite() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.frostBody = new ModelRenderer((ModelBase)this, 0, 11);
        this.frostBody.addBox(0.0f, 0.0f, 0.0f, 8, 8, 6);
        this.frostBody.setRotationPoint(-4.0f, 5.0f, -4.0f);
        this.frostBody.setTextureSize(64, 32);
        this.frostBody.mirror = true;
        this.setRotation(this.frostBody, 0.0f, 0.0f, 0.0f);
        this.shard1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shard1.addBox(8.0f, 0.0f, 0.0f, 9, 1, 2);
        this.shard1.setRotationPoint(1.0f, 8.0f, -2.0f);
        this.shard1.setTextureSize(64, 32);
        this.shard1.mirror = true;
        this.setRotation(this.shard1, 0.0f, 0.0f, -0.7853982f);
        this.shard2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shard2.addBox(-19.0f, 0.0f, 0.0f, 9, 1, 2);
        this.shard2.setRotationPoint(0.0f, 9.0f, -2.0f);
        this.shard2.setTextureSize(64, 32);
        this.shard2.mirror = true;
        this.setRotation(this.shard2, 0.0f, 0.0f, -0.7853982f);
        this.shard7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shard7.addBox(10.0f, 0.0f, 0.0f, 10, 1, 2);
        this.shard7.setRotationPoint(0.0f, 9.0f, -2.0f);
        this.shard7.setTextureSize(64, 32);
        this.shard7.mirror = true;
        this.setRotation(this.shard7, 0.0f, 0.0f, 0.7853982f);
        this.shard8 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shard8.addBox(-19.0f, 0.0f, 0.0f, 10, 1, 2);
        this.shard8.setRotationPoint(-1.0f, 9.0f, -2.0f);
        this.shard8.setTextureSize(64, 32);
        this.shard8.mirror = true;
        this.setRotation(this.shard8, 0.0f, 0.0f, 0.7853982f);
        this.shard4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shard4.addBox(-6.0f, -8.0f, 0.0f, 15, 1, 2);
        this.shard4.setRotationPoint(0.0f, 8.0f, -2.0f);
        this.shard4.setTextureSize(64, 32);
        this.shard4.mirror = true;
        this.setRotation(this.shard4, 0.0f, 0.0f, 1.570796f);
        this.shard3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shard3.addBox(-7.0f, 7.0f, 0.0f, 15, 1, 2);
        this.shard3.setRotationPoint(0.0f, 9.0f, -2.0f);
        this.shard3.setTextureSize(64, 32);
        this.shard3.mirror = true;
        this.setRotation(this.shard3, 0.0f, 0.0f, 1.570796f);
        this.shard5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shard5.addBox(-8.0f, -7.0f, 0.0f, 14, 1, 2);
        this.shard5.setRotationPoint(1.0f, 9.0f, -2.0f);
        this.shard5.setTextureSize(64, 32);
        this.shard5.mirror = true;
        this.setRotation(this.shard5, 0.0f, 0.0f, 0.0f);
        this.shard6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shard6.addBox(-7.0f, 7.0f, 0.0f, 14, 1, 2);
        this.shard6.setRotationPoint(0.0f, 9.0f, -2.0f);
        this.shard6.setTextureSize(64, 32);
        this.shard6.mirror = true;
        this.setRotation(this.shard6, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        this.frostBody.render(f5);
        this.shard1.render(f5);
        this.shard2.render(f5);
        this.shard7.render(f5);
        this.shard8.render(f5);
        this.shard4.render(f5);
        this.shard3.render(f5);
        this.shard5.render(f5);
        this.shard6.render(f5);
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par2, float par3, float par4, float par5, float par6, float par7, Entity entity) {
        this.shard1.rotateAngleX = MathHelper.cos((float)(par4 * 0.1f)) * (float)Math.PI;
        this.shard2.rotateAngleX = MathHelper.cos((float)(par4 * 0.1f)) * (float)Math.PI;
        this.shard3.rotateAngleX = MathHelper.cos((float)(par4 * 0.1f)) * (float)Math.PI;
        this.shard4.rotateAngleX = MathHelper.cos((float)(par4 * 0.1f)) * (float)Math.PI;
        this.shard5.rotateAngleX = MathHelper.cos((float)(par4 * 0.1f)) * (float)Math.PI;
        this.shard6.rotateAngleX = MathHelper.cos((float)(par4 * 0.1f)) * (float)Math.PI;
        this.shard7.rotateAngleX = MathHelper.cos((float)(par4 * 0.1f)) * (float)Math.PI;
        this.shard8.rotateAngleX = MathHelper.cos((float)(par4 * 0.1f)) * (float)Math.PI;
    }
}

