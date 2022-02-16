/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.entity.boiling;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelScreamer
extends ModelBiped {
    ModelRenderer body;
    ModelRenderer arm1;
    ModelRenderer arm2;
    ModelRenderer arm3;
    ModelRenderer arm4;

    public ModelScreamer() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.body = new ModelRenderer((ModelBase)this, 0, 0);
        this.body.addBox(-3.5f, -3.5f, -3.5f, 7, 7, 7);
        this.body.setRotationPoint(0.0f, 4.0f, 0.0f);
        this.body.setTextureSize(64, 32);
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.arm1 = new ModelRenderer((ModelBase)this, 14, 14);
        this.arm1.addBox(-1.0f, -4.5f, -1.0f, 2, 5, 2);
        this.arm1.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.arm1.setTextureSize(64, 32);
        this.setRotation(this.arm1, 0.0f, 0.0f, 0.0f);
        this.arm2 = new ModelRenderer((ModelBase)this, 14, 14);
        this.arm2.addBox(-1.0f, -1.0f, -1.0f, 2, 5, 2);
        this.arm2.setRotationPoint(0.0f, 8.5f, 0.0f);
        this.arm2.setTextureSize(64, 32);
        this.setRotation(this.arm2, 0.0f, 0.0f, 0.0f);
        this.arm3 = new ModelRenderer((ModelBase)this, 0, 21);
        this.arm3.addBox(-1.0f, -1.0f, -6.0f, 2, 2, 7);
        this.arm3.setRotationPoint(0.0f, -3.5f, 0.0f);
        this.arm3.setTextureSize(64, 32);
        this.setRotation(this.arm3, 0.0f, 0.0f, 0.0f);
        this.arm4 = new ModelRenderer((ModelBase)this, 0, 21);
        this.arm4.addBox(-1.0f, -1.0f, -6.0f, 2, 2, 7);
        this.arm4.setRotationPoint(0.0f, 11.5f, 0.0f);
        this.arm4.setTextureSize(64, 32);
        this.setRotation(this.arm4, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.body.render(f5);
        this.arm1.render(f5);
        this.arm2.render(f5);
        this.arm3.render(f5);
        this.arm4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
    }
}

