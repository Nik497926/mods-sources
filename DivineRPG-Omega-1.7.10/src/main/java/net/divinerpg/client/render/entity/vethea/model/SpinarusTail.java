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

public class SpinarusTail
extends ModelBase {
    ModelRenderer part1;
    ModelRenderer part2;
    ModelRenderer part3;

    public SpinarusTail() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.part1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part1.addBox(-5.0f, -2.0f, -10.0f, 10, 4, 10);
        this.part1.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part1.setTextureSize(64, 32);
        this.part1.mirror = true;
        this.setRotation(this.part1, 0.0f, 0.0f, 0.0f);
        this.part2 = new ModelRenderer((ModelBase)this, 0, 14);
        this.part2.addBox(-4.0f, -1.0f, 0.0f, 8, 3, 5);
        this.part2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part2.setTextureSize(64, 32);
        this.part2.mirror = true;
        this.setRotation(this.part2, 0.0f, 0.0f, 0.0f);
        this.part3 = new ModelRenderer((ModelBase)this, 0, 22);
        this.part3.addBox(-4.0f, -1.0f, 0.0f, 6, 1, 5);
        this.part3.setRotationPoint(1.0f, 1.0f, 5.0f);
        this.part3.setTextureSize(64, 32);
        this.part3.mirror = true;
        this.setRotation(this.part3, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.part1.render(f5);
        this.part2.render(f5);
        this.part3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    }
}

