/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.entity.euca.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDynaster
extends ModelBase {
    private ModelRenderer head;
    private ModelRenderer hover1;
    private ModelRenderer hover2;
    private ModelRenderer hover3;
    private ModelRenderer hover4;

    public ModelDynaster() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 16.0f, 0.0f);
        this.head.setTextureSize(64, 64);
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.hover1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.hover1.addBox(-7.0f, 0.0f, -1.0f, 8, 6, 8);
        this.hover1.setRotationPoint(-3.0f, 16.0f, 3.0f);
        this.hover1.setTextureSize(64, 64);
        this.setRotation(this.hover1, 0.3490659f, 0.0f, 0.0f);
        this.hover2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.hover2.addBox(-1.0f, 0.0f, -1.0f, 8, 6, 8);
        this.hover2.setRotationPoint(3.0f, 16.0f, 3.0f);
        this.hover2.setTextureSize(64, 64);
        this.setRotation(this.hover2, 0.3490659f, 0.0f, 0.0f);
        this.hover3 = new ModelRenderer((ModelBase)this, 0, 16);
        this.hover3.addBox(0.0f, 0.0f, -7.0f, 8, 6, 8);
        this.hover3.setRotationPoint(2.0f, 16.0f, -3.0f);
        this.hover3.setTextureSize(64, 64);
        this.setRotation(this.hover3, 0.3490659f, 0.0f, 0.0f);
        this.hover4 = new ModelRenderer((ModelBase)this, 0, 16);
        this.hover4.addBox(-7.0f, 0.0f, -7.0f, 8, 6, 8);
        this.hover4.setRotationPoint(-3.0f, 16.0f, -3.0f);
        this.hover4.setTextureSize(64, 64);
        this.setRotation(this.hover4, 0.3490659f, 0.0f, 0.0f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.hover1.render(f5);
        this.hover2.render(f5);
        this.hover3.render(f5);
        this.hover4.render(f5);
    }
}

