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

public class ModelObserver
extends ModelBiped {
    ModelRenderer head;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer arm1;
    ModelRenderer arm2;

    public ModelObserver() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-3.5f, -3.5f, -3.5f, 7, 7, 7);
        this.head.setRotationPoint(0.0f, 4.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.head1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.head1.addBox(-4.5f, -2.5f, -3.5f, 7, 7, 7);
        this.head1.setRotationPoint(1.0f, -9.0f, 0.0f);
        this.head1.setTextureSize(64, 32);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.0f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.head2.addBox(3.5f, 3.5f, 3.5f, 7, 7, 7);
        this.head2.setRotationPoint(-7.0f, 9.0f, -7.0f);
        this.head2.setTextureSize(64, 32);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.0f, 0.0f);
        this.arm1 = new ModelRenderer((ModelBase)this, 14, 14);
        this.arm1.addBox(-1.0f, -4.5f, -1.0f, 2, 5, 2);
        this.arm1.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.arm1.setTextureSize(64, 32);
        this.arm1.mirror = true;
        this.setRotation(this.arm1, 0.0f, 0.0f, 0.0f);
        this.arm2 = new ModelRenderer((ModelBase)this, 14, 14);
        this.arm2.addBox(-1.0f, -1.0f, -1.0f, 2, 5, 2);
        this.arm2.setRotationPoint(0.0f, 8.5f, 0.0f);
        this.arm2.setTextureSize(64, 32);
        this.arm2.mirror = true;
        this.setRotation(this.arm2, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.head1.render(f5);
        this.head2.render(f5);
        this.arm1.render(f5);
        this.arm2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
    }
}

