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
package net.divinerpg.client.render.entity.vanilla.model;

import net.divinerpg.entities.vanilla.EntityKobblin;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelKobblin
extends ModelBase {
    ModelRenderer Pad;
    ModelRenderer RArm1;
    ModelRenderer Neck;
    ModelRenderer Body;
    ModelRenderer Head;
    ModelRenderer Tongue;
    ModelRenderer RArm2;
    ModelRenderer RLeg;
    ModelRenderer LArm2;
    ModelRenderer LArm1;
    ModelRenderer LLeg;

    public ModelKobblin() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Pad = new ModelRenderer((ModelBase)this, 0, 0);
        this.Pad.addBox(0.0f, 0.0f, 0.0f, 16, 3, 16);
        this.Pad.setRotationPoint(-8.0f, 5.0f, -8.0f);
        this.Pad.setTextureSize(64, 32);
        this.Pad.mirror = true;
        this.setRotation(this.Pad, 0.0f, 0.0f, 0.0f);
        this.RArm1 = new ModelRenderer((ModelBase)this, 48, 19);
        this.RArm1.addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.RArm1.setRotationPoint(-3.5f, 14.0f, 2.5f);
        this.RArm1.setTextureSize(64, 32);
        this.RArm1.mirror = true;
        this.setRotation(this.RArm1, -0.7853982f, 0.3665191f, 0.0f);
        this.Neck = new ModelRenderer((ModelBase)this, 40, 23);
        this.Neck.addBox(0.0f, 0.0f, 0.0f, 2, 3, 2);
        this.Neck.setRotationPoint(-1.0f, 13.0f, -0.5f);
        this.Neck.setTextureSize(64, 32);
        this.Neck.mirror = true;
        this.setRotation(this.Neck, 0.5235988f, 0.0f, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 20, 19);
        this.Body.addBox(0.0f, 0.0f, 0.0f, 5, 6, 5);
        this.Body.setRotationPoint(-2.5f, 14.0f, 0.5f);
        this.Body.setTextureSize(64, 32);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 19);
        this.Head.addBox(0.0f, 0.0f, 0.0f, 5, 5, 5);
        this.Head.setRotationPoint(-2.5f, 8.0f, -2.5f);
        this.Head.setTextureSize(64, 32);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Tongue = new ModelRenderer((ModelBase)this, 40, 19);
        this.Tongue.addBox(0.0f, 0.0f, 0.0f, 3, 3, 1);
        this.Tongue.setRotationPoint(-1.5f, 12.0f, -2.5f);
        this.Tongue.setTextureSize(64, 32);
        this.Tongue.mirror = true;
        this.setRotation(this.Tongue, -0.7853982f, 0.0f, 0.0f);
        this.RArm2 = new ModelRenderer((ModelBase)this, 48, 24);
        this.RArm2.addBox(-1.5f, 2.0f, 2.5f, 1, 4, 1);
        this.RArm2.setRotationPoint(-3.5f, 14.0f, 2.5f);
        this.RArm2.setTextureSize(64, 32);
        this.RArm2.mirror = true;
        this.setRotation(this.RArm2, -1.570796f, -0.3665191f, 0.0f);
        this.RArm2.mirror = false;
        this.RLeg = new ModelRenderer((ModelBase)this, 48, 19);
        this.RLeg.addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.RLeg.setRotationPoint(-2.0f, 20.0f, 2.5f);
        this.RLeg.setTextureSize(64, 32);
        this.RLeg.mirror = true;
        this.setRotation(this.RLeg, 0.0f, 0.0f, 0.0f);
        this.LArm2 = new ModelRenderer((ModelBase)this, 48, 24);
        this.LArm2.addBox(1.5f, 1.5f, 2.5f, 1, 4, 1);
        this.LArm2.setRotationPoint(2.5f, 14.0f, 2.5f);
        this.LArm2.setTextureSize(64, 32);
        this.LArm2.mirror = true;
        this.setRotation(this.LArm2, -1.570796f, 0.3665191f, 0.0f);
        this.LArm1 = new ModelRenderer((ModelBase)this, 48, 19);
        this.LArm1.addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.LArm1.setRotationPoint(2.5f, 14.0f, 2.5f);
        this.LArm1.setTextureSize(64, 32);
        this.LArm1.mirror = true;
        this.setRotation(this.LArm1, -0.7853982f, -0.3665191f, 0.0f);
        this.LLeg = new ModelRenderer((ModelBase)this, 48, 19);
        this.LLeg.addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.LLeg.setRotationPoint(1.0f, 20.0f, 2.5f);
        this.LLeg.setTextureSize(64, 32);
        this.LLeg.mirror = true;
        this.setRotation(this.LLeg, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        if (entity instanceof EntityKobblin && !((EntityKobblin)entity).getProvoked()) {
            GL11.glTranslatef((float)0.0f, (float)1.125f, (float)0.0f);
        }
        this.Pad.render(f5);
        this.RArm1.render(f5);
        this.Neck.render(f5);
        this.Body.render(f5);
        this.Head.render(f5);
        this.Tongue.render(f5);
        this.RArm2.render(f5);
        this.RLeg.render(f5);
        this.LArm2.render(f5);
        this.LArm1.render(f5);
        this.LLeg.render(f5);
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.RArm1.rotateAngleX = -0.7853982f + MathHelper.cos((float)(f * 0.6662f)) * 0.8f * f1;
        this.RArm2.rotateAngleX = -1.570796f + MathHelper.cos((float)(f * 0.6662f)) * 0.8f * f1;
        this.LArm1.rotateAngleX = -0.7853982f + MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 0.8f * f1;
        this.LArm2.rotateAngleX = -1.570796f + MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 0.8f * f1;
        this.RLeg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * f1;
        this.LLeg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * f1;
    }
}

