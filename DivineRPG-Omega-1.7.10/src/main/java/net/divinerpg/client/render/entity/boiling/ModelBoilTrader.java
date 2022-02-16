/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.boiling;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBoilTrader
extends ModelBase {
    ModelRenderer head;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer top;

    public ModelBoilTrader() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.setTextureSize(64, 64);
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 2, 12, 4);
        this.rightarm.setRotationPoint(-3.0f, -8.0f, 0.0f);
        this.rightarm.setTextureSize(64, 64);
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 2, 12, 4);
        this.leftarm.setRotationPoint(5.0f, -8.0f, 0.0f);
        this.leftarm.setTextureSize(64, 64);
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.top = new ModelRenderer((ModelBase)this, 0, 16);
        this.top.addBox(0.0f, 0.0f, 0.0f, 10, 1, 10);
        this.top.setRotationPoint(-5.0f, -9.0f, -5.0f);
        this.top.setTextureSize(64, 64);
        this.setRotation(this.top, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.top.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.rightarm.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.head.rotateAngleY = par4 / 57.295776f;
        this.head.rotateAngleX = par5 / 57.295776f;
    }
}

