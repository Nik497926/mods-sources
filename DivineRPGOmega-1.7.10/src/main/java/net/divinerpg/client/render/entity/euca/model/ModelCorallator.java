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

public class ModelCorallator
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer hover;
    ModelRenderer hover1;
    ModelRenderer hover2;
    ModelRenderer hover3;
    ModelRenderer hoverBase;

    public ModelCorallator() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.setTextureSize(128, 128);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 40);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 14, 8, 14);
        this.body.setRotationPoint(-3.0f, 0.0f, -5.0f);
        this.body.setTextureSize(128, 128);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.hover = new ModelRenderer((ModelBase)this, 40, 16);
        this.hover.addBox(-3.0f, -2.0f, -2.0f, 4, 16, 4);
        this.hover.setRotationPoint(-8.0f, 2.0f, 0.0f);
        this.hover.setTextureSize(128, 128);
        this.hover.mirror = true;
        this.setRotation(this.hover, 0.0f, 0.0f, 0.0872665f);
        this.hover1 = new ModelRenderer((ModelBase)this, 40, 16);
        this.hover1.addBox(-1.0f, -2.0f, -2.0f, 4, 16, 4);
        this.hover1.setRotationPoint(8.0f, 2.0f, 0.0f);
        this.hover1.setTextureSize(128, 128);
        this.hover1.mirror = true;
        this.setRotation(this.hover1, 0.0f, 0.0f, -0.0872665f);
        this.hover2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.hover2.addBox(-2.0f, 0.0f, -2.0f, 4, 16, 4);
        this.hover2.setRotationPoint(0.0f, 0.0f, -9.0f);
        this.hover2.setTextureSize(128, 128);
        this.hover2.mirror = true;
        this.setRotation(this.hover2, -0.0872665f, 0.0f, 0.0f);
        this.hover3 = new ModelRenderer((ModelBase)this, 0, 16);
        this.hover3.addBox(-2.0f, 0.0f, -2.0f, 4, 16, 4);
        this.hover3.setRotationPoint(0.0f, 0.0f, 9.0f);
        this.hover3.setTextureSize(128, 128);
        this.hover3.mirror = true;
        this.setRotation(this.hover3, 0.0872665f, 0.0f, 0.0f);
        this.hoverBase = new ModelRenderer((ModelBase)this, 16, 62);
        this.hoverBase.addBox(0.0f, 0.0f, 0.0f, 20, 4, 20);
        this.hoverBase.setRotationPoint(-10.0f, 8.0f, -10.0f);
        this.hoverBase.setTextureSize(128, 128);
        this.hoverBase.mirror = true;
        this.setRotation(this.hoverBase, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.body.render(f5);
        this.hover.render(f5);
        this.hover1.render(f5);
        this.hover2.render(f5);
        this.hover3.render(f5);
        this.hoverBase.render(f5);
    }

    protected void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
    }
}

