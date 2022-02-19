/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.entity.arcana.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRoamer
extends ModelBase {
    ModelRenderer Main_Body1;
    ModelRenderer Lower_Body;
    ModelRenderer Arm2;
    ModelRenderer Armtop1;
    ModelRenderer Armtop2;
    ModelRenderer Arm1;
    ModelRenderer Main_Body;
    ModelRenderer Mid_Body;
    ModelRenderer TopBody;
    ModelRenderer Legbottom1;
    ModelRenderer Legtop1;
    ModelRenderer Legbottom2;
    ModelRenderer Legtop2;

    public ModelRoamer() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Main_Body1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Main_Body1.addBox(0.0f, 0.0f, 0.0f, 10, 6, 10);
        this.Main_Body1.setRotationPoint(2.0f, -5.0f, 2.0f);
        this.Main_Body1.setTextureSize(64, 32);
        this.Main_Body1.mirror = true;
        this.setRotation(this.Main_Body1, 0.0f, 1.5707964f, 0.0f);
        this.Lower_Body = new ModelRenderer((ModelBase)this, 39, 20);
        this.Lower_Body.addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.Lower_Body.setRotationPoint(-4.0f, -5.0f, 0.0f);
        this.Lower_Body.setTextureSize(64, 32);
        this.Lower_Body.mirror = true;
        this.setRotation(this.Lower_Body, 0.0f, 1.5707964f, 0.0f);
        this.Arm2 = new ModelRenderer((ModelBase)this, 14, 18);
        this.Arm2.addBox(0.0f, 0.0f, 0.0f, 2, 2, 5);
        this.Arm2.setRotationPoint(5.0f, 4.0f, -2.0f);
        this.Arm2.setTextureSize(64, 32);
        this.Arm2.mirror = true;
        this.setRotation(this.Arm2, -1.5707964f, 1.5707964f, 0.0f);
        this.Armtop1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Armtop1.addBox(0.0f, 0.0f, 0.0f, 2, 2, 5);
        this.Armtop1.setRotationPoint(0.0f, 2.0f, -2.0f);
        this.Armtop1.setTextureSize(64, 32);
        this.Armtop1.mirror = true;
        this.setRotation(this.Armtop1, 0.0f, 1.5707964f, 0.0f);
        this.Armtop2 = new ModelRenderer((ModelBase)this, 0, 25);
        this.Armtop2.addBox(0.0f, 0.0f, 0.0f, 2, 2, 5);
        this.Armtop2.setRotationPoint(-7.0f, 2.0f, -2.0f);
        this.Armtop2.setTextureSize(64, 32);
        this.Armtop2.mirror = true;
        this.setRotation(this.Armtop2, 0.0f, 1.5707964f, 0.0f);
        this.Arm1 = new ModelRenderer((ModelBase)this, 14, 25);
        this.Arm1.addBox(0.0f, 0.0f, 0.0f, 2, 2, 5);
        this.Arm1.setRotationPoint(-5.0f, 4.0f, -2.0f);
        this.Arm1.setTextureSize(64, 32);
        this.Arm1.mirror = true;
        this.setRotation(this.Arm1, -1.5707964f, 1.5707964f, 0.0f);
        this.Main_Body = new ModelRenderer((ModelBase)this, 0, 0);
        this.Main_Body.addBox(0.0f, 0.0f, 0.0f, 10, 6, 10);
        this.Main_Body.setRotationPoint(-14.0f, -5.0f, 2.0f);
        this.Main_Body.setTextureSize(64, 32);
        this.Main_Body.mirror = true;
        this.setRotation(this.Main_Body, 0.0f, 1.5707964f, 0.0f);
        this.Mid_Body = new ModelRenderer((ModelBase)this, 39, 20);
        this.Mid_Body.addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.Mid_Body.setRotationPoint(-4.0f, 7.0f, 0.0f);
        this.Mid_Body.setTextureSize(64, 32);
        this.Mid_Body.mirror = true;
        this.setRotation(this.Mid_Body, 0.0f, 1.5707964f, 0.0f);
        this.TopBody = new ModelRenderer((ModelBase)this, 39, 20);
        this.TopBody.addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.TopBody.setRotationPoint(-4.0f, 1.0f, 0.0f);
        this.TopBody.setTextureSize(64, 32);
        this.TopBody.mirror = true;
        this.setRotation(this.TopBody, 0.0f, 1.5707964f, 0.0f);
        this.Legbottom1 = new ModelRenderer((ModelBase)this, 14, 25);
        this.Legbottom1.addBox(0.0f, 0.0f, 0.0f, 2, 2, 5);
        this.Legbottom1.setRotationPoint(-5.0f, 13.0f, -2.0f);
        this.Legbottom1.setTextureSize(64, 32);
        this.Legbottom1.mirror = true;
        this.setRotation(this.Legbottom1, -1.5707964f, 1.5707964f, 0.0f);
        this.Legtop1 = new ModelRenderer((ModelBase)this, 0, 25);
        this.Legtop1.addBox(0.0f, 0.0f, 0.0f, 2, 2, 5);
        this.Legtop1.setRotationPoint(-7.0f, 11.0f, -2.0f);
        this.Legtop1.setTextureSize(64, 32);
        this.Legtop1.mirror = true;
        this.setRotation(this.Legtop1, 0.0f, 1.5707964f, 0.0f);
        this.Legbottom2 = new ModelRenderer((ModelBase)this, 14, 18);
        this.Legbottom2.addBox(0.0f, 0.0f, 0.0f, 2, 2, 5);
        this.Legbottom2.setRotationPoint(5.0f, 13.0f, -2.0f);
        this.Legbottom2.setTextureSize(64, 32);
        this.Legbottom2.mirror = true;
        this.setRotation(this.Legbottom2, -1.5707964f, 1.5707964f, 0.0f);
        this.Legtop2 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Legtop2.addBox(0.0f, 0.0f, 0.0f, 2, 2, 5);
        this.Legtop2.setRotationPoint(0.0f, 11.0f, -2.0f);
        this.Legtop2.setTextureSize(64, 32);
        this.Legtop2.mirror = true;
        this.setRotation(this.Legtop2, 0.0f, 1.5707964f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Main_Body1.render(f5);
        this.Lower_Body.render(f5);
        this.Arm2.render(f5);
        this.Armtop1.render(f5);
        this.Armtop2.render(f5);
        this.Arm1.render(f5);
        this.Main_Body.render(f5);
        this.Mid_Body.render(f5);
        this.TopBody.render(f5);
        this.Legbottom1.render(f5);
        this.Legtop1.render(f5);
        this.Legbottom2.render(f5);
        this.Legtop2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par6) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, par6);
    }
}

