/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.entity.vanilla.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelReaper
extends ModelBase {
    ModelRenderer head;
    ModelRenderer shoulderthing;
    ModelRenderer rib1;
    ModelRenderer rib2;
    ModelRenderer spine;
    ModelRenderer spine2;
    ModelRenderer wingthing;
    ModelRenderer wingthing1;
    ModelRenderer wingthing2;
    ModelRenderer wingthing3;
    ModelRenderer wingthing4;
    ModelRenderer wingthing5;
    ModelRenderer wingthing6;
    ModelRenderer wingend;
    ModelRenderer wingend1;
    ModelRenderer wingend2;
    ModelRenderer wingend3;
    ModelRenderer wingend4;
    ModelRenderer wingend5;
    ModelRenderer wingend6;

    public ModelReaper() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.head.setTextureSize(64, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.shoulderthing = new ModelRenderer((ModelBase)this, 16, 20);
        this.shoulderthing.addBox(-4.0f, 0.0f, -2.0f, 16, 3, 4);
        this.shoulderthing.setRotationPoint(-4.0f, 0.0f, 0.0f);
        this.shoulderthing.setTextureSize(64, 64);
        this.shoulderthing.mirror = true;
        this.setRotation(this.shoulderthing, 0.0f, 0.0f, 0.0f);
        this.rib1 = new ModelRenderer((ModelBase)this, 0, 46);
        this.rib1.addBox(-2.0f, 0.0f, -2.0f, 12, 2, 2);
        this.rib1.setRotationPoint(-4.0f, 4.0f, 1.0f);
        this.rib1.setTextureSize(64, 64);
        this.rib1.mirror = true;
        this.setRotation(this.rib1, 0.0f, 0.0f, 0.0f);
        this.rib2 = new ModelRenderer((ModelBase)this, 0, 46);
        this.rib2.addBox(0.0f, 0.0f, 0.0f, 12, 2, 2);
        this.rib2.setRotationPoint(-6.0f, 7.0f, -1.0f);
        this.rib2.setTextureSize(64, 64);
        this.rib2.mirror = true;
        this.setRotation(this.rib2, 0.0f, 0.0f, 0.0f);
        this.spine = new ModelRenderer((ModelBase)this, 0, 20);
        this.spine.addBox(-2.0f, 0.0f, -2.0f, 3, 6, 3);
        this.spine.setRotationPoint(0.5f, 3.0f, 0.5f);
        this.spine.setTextureSize(64, 64);
        this.spine.mirror = true;
        this.setRotation(this.spine, 0.0f, 0.0f, 0.0f);
        this.spine2 = new ModelRenderer((ModelBase)this, 0, 36);
        this.spine2.addBox(0.0f, 0.0f, 0.0f, 3, 6, 3);
        this.spine2.setRotationPoint(-1.5f, 9.2f, -1.5f);
        this.spine2.setTextureSize(64, 64);
        this.spine2.mirror = true;
        this.setRotation(this.spine2, 0.6283185f, 0.0f, 0.0f);
        this.wingthing = new ModelRenderer((ModelBase)this, 40, 32);
        this.wingthing.addBox(-3.0f, -2.0f, -2.0f, 3, 12, 0);
        this.wingthing.setRotationPoint(-5.0f, 1.0f, 2.0f);
        this.wingthing.setTextureSize(64, 64);
        this.wingthing.mirror = true;
        this.setRotation(this.wingthing, 0.0f, 0.0f, 0.6981317f);
        this.wingthing1 = new ModelRenderer((ModelBase)this, 40, 32);
        this.wingthing1.addBox(0.0f, -2.0f, -2.0f, 3, 12, 0);
        this.wingthing1.setRotationPoint(5.0f, 1.0f, 2.0f);
        this.wingthing1.setTextureSize(64, 64);
        this.wingthing1.mirror = true;
        this.setRotation(this.wingthing1, 0.0f, 0.0f, -0.6981317f);
        this.wingthing2 = new ModelRenderer((ModelBase)this, 40, 32);
        this.wingthing2.addBox(0.0f, 0.0f, 0.0f, 3, 12, 0);
        this.wingthing2.setRotationPoint(-2.0f, -2.0f, 0.0f);
        this.wingthing2.setTextureSize(64, 64);
        this.wingthing2.mirror = true;
        this.setRotation(this.wingthing2, 0.0f, 0.0f, 1.570796f);
        this.wingthing3 = new ModelRenderer((ModelBase)this, 40, 32);
        this.wingthing3.addBox(0.0f, 0.0f, 0.0f, 3, 12, 0);
        this.wingthing3.setRotationPoint(14.0f, -2.0f, 0.0f);
        this.wingthing3.setTextureSize(64, 64);
        this.wingthing3.mirror = true;
        this.setRotation(this.wingthing3, 0.0f, 0.0f, 1.570796f);
        this.wingthing4 = new ModelRenderer((ModelBase)this, 40, 32);
        this.wingthing4.addBox(0.0f, 0.0f, 0.0f, 3, 12, 0);
        this.wingthing4.setRotationPoint(-4.0f, 0.0f, 0.0f);
        this.wingthing4.setTextureSize(64, 64);
        this.wingthing4.mirror = true;
        this.setRotation(this.wingthing4, 0.0f, 0.0f, 2.443461f);
        this.wingthing5 = new ModelRenderer((ModelBase)this, 40, 32);
        this.wingthing5.addBox(0.0f, 0.0f, 0.0f, 3, 12, 0);
        this.wingthing5.setRotationPoint(6.0f, 2.0f, 0.0f);
        this.wingthing5.setTextureSize(64, 64);
        this.wingthing5.mirror = true;
        this.setRotation(this.wingthing5, 0.0f, 0.0f, -2.443461f);
        this.wingthing6 = new ModelRenderer((ModelBase)this, 40, 32);
        this.wingthing6.addBox(0.0f, 0.0f, 0.0f, 3, 12, 0);
        this.wingthing6.setRotationPoint(-1.5f, -12.0f, 0.0f);
        this.wingthing6.setTextureSize(64, 64);
        this.wingthing6.mirror = true;
        this.setRotation(this.wingthing6, 0.0f, 0.0f, 0.0f);
        this.wingend = new ModelRenderer((ModelBase)this, 32, 0);
        this.wingend.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.wingend.setRotationPoint(9.0f, -2.5f, -2.0f);
        this.wingend.setTextureSize(64, 64);
        this.wingend.mirror = true;
        this.setRotation(this.wingend, 0.0f, 0.0f, 0.0f);
        this.wingend1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.wingend1.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.wingend1.setRotationPoint(-13.0f, -2.5f, -2.0f);
        this.wingend1.setTextureSize(64, 64);
        this.wingend1.mirror = true;
        this.setRotation(this.wingend1, 0.0f, 0.0f, 0.0f);
        this.wingend2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.wingend2.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.wingend2.setRotationPoint(-2.0f, -11.0f, -2.0f);
        this.wingend2.setTextureSize(64, 64);
        this.wingend2.mirror = true;
        this.setRotation(this.wingend2, 0.0f, 0.0f, 0.0f);
        this.wingend3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.wingend3.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.wingend3.setRotationPoint(11.0f, 3.0f, -2.0f);
        this.wingend3.setTextureSize(64, 64);
        this.wingend3.mirror = true;
        this.setRotation(this.wingend3, 0.0f, 0.0f, 0.8726646f);
        this.wingend4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.wingend4.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.wingend4.setRotationPoint(-14.0f, 6.0f, -2.0f);
        this.wingend4.setTextureSize(64, 64);
        this.wingend4.mirror = true;
        this.setRotation(this.wingend4, 0.0f, 0.0f, -0.8726646f);
        this.wingend5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.wingend5.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.wingend5.setRotationPoint(-11.0f, -9.0f, -2.0f);
        this.wingend5.setTextureSize(64, 64);
        this.wingend5.mirror = true;
        this.setRotation(this.wingend5, 0.0f, 0.0f, 0.8726646f);
        this.wingend6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.wingend6.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.wingend6.setRotationPoint(11.0f, -9.0f, -2.0f);
        this.wingend6.setTextureSize(64, 64);
        this.wingend6.mirror = true;
        this.setRotation(this.wingend6, 0.0f, 0.0f, 0.6981317f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.shoulderthing.render(f5);
        this.rib1.render(f5);
        this.rib2.render(f5);
        this.spine.render(f5);
        this.spine2.render(f5);
        this.wingthing.render(f5);
        this.wingthing1.render(f5);
        this.wingthing2.render(f5);
        this.wingthing3.render(f5);
        this.wingthing4.render(f5);
        this.wingthing5.render(f5);
        this.wingthing6.render(f5);
        this.wingend.render(f5);
        this.wingend1.render(f5);
        this.wingend2.render(f5);
        this.wingend3.render(f5);
        this.wingend4.render(f5);
        this.wingend5.render(f5);
        this.wingend6.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        this.head.rotateAngleY = f4 / 57.295776f;
        this.head.rotateAngleX = f5 / 57.295776f;
    }
}

