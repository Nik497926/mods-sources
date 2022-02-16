/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.vanilla.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSnapper
extends ModelBase {
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer body;
    ModelRenderer shell;
    ModelRenderer head;
    ModelRenderer shell2;
    ModelRenderer shell3;

    public ModelSnapper() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg1.addBox(0.0f, 0.0f, -1.0f, 2, 4, 2);
        this.leg1.setRotationPoint(-5.5f, 20.0f, -5.0f);
        this.leg1.setTextureSize(128, 64);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg2.addBox(0.0f, 0.0f, -1.0f, 2, 4, 2);
        this.leg2.setRotationPoint(-5.5f, 20.0f, 5.0f);
        this.leg2.setTextureSize(128, 64);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0f, 0.0f, 0.0f);
        this.leg3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg3.addBox(0.0f, 0.0f, -1.0f, 2, 4, 2);
        this.leg3.setRotationPoint(3.5f, 20.0f, -5.0f);
        this.leg3.setTextureSize(128, 64);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.0f, 0.0f, 0.0f);
        this.leg4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg4.addBox(0.0f, 0.0f, -1.0f, 2, 4, 2);
        this.leg4.setRotationPoint(3.5f, 20.0f, 5.0f);
        this.leg4.setTextureSize(128, 64);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 0);
        this.body.addBox(0.0f, 0.0f, 0.0f, 10, 3, 15);
        this.body.setRotationPoint(-5.0f, 17.0f, -7.0f);
        this.body.setTextureSize(128, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.shell = new ModelRenderer((ModelBase)this, 0, 46);
        this.shell.addBox(0.0f, 0.0f, 0.0f, 11, 3, 15);
        this.shell.setRotationPoint(-5.5f, 16.0f, -6.5f);
        this.shell.setTextureSize(128, 64);
        this.shell.mirror = true;
        this.setRotation(this.shell, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 40);
        this.head.addBox(-1.0f, -4.0f, -1.0f, 2, 4, 2);
        this.head.setRotationPoint(0.0f, 19.0f, -6.0f);
        this.head.setTextureSize(128, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 1.308997f, 0.0f, 0.0f);
        this.shell2 = new ModelRenderer((ModelBase)this, 84, 49);
        this.shell2.addBox(0.0f, 0.0f, 0.0f, 9, 1, 13);
        this.shell2.setRotationPoint(-4.5f, 15.0f, -5.0f);
        this.shell2.setTextureSize(128, 64);
        this.shell2.mirror = true;
        this.setRotation(this.shell2, 0.0f, 0.0f, 0.0f);
        this.shell3 = new ModelRenderer((ModelBase)this, 52, 52);
        this.shell3.addBox(0.0f, 0.0f, 0.0f, 7, 1, 11);
        this.shell3.setRotationPoint(-3.5f, 14.0f, -4.0f);
        this.shell3.setTextureSize(128, 64);
        this.shell3.mirror = true;
        this.setRotation(this.shell3, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.leg1.render(f5);
        this.leg2.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
        this.body.render(f5);
        this.shell.render(f5);
        this.head.render(f5);
        this.shell2.render(f5);
        this.shell3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.leg1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * f1;
        this.leg3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * f1;
        this.leg2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * f1;
        this.leg4.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * f1;
        this.head.rotateAngleY = 0.75f * f3 / 57.295776f;
    }
}

