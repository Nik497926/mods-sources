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

public class ModelCaveling
extends ModelBase {
    ModelRenderer headPiece;
    ModelRenderer head;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;

    public ModelCaveling() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.headPiece = new ModelRenderer((ModelBase)this, 0, 0);
        this.headPiece.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.headPiece.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.headPiece.setTextureSize(64, 64);
        this.headPiece.mirror = true;
        this.setRotation(this.headPiece, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 16);
        this.head.addBox(-3.0f, -3.0f, -6.0f, 6, 6, 6);
        this.head.setRotationPoint(0.0f, 8.0f, 0.0f);
        this.head.setTextureSize(64, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, -1.570796f, 0.0f);
        this.head1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.head1.addBox(-3.0f, -3.0f, -6.0f, 6, 6, 6);
        this.head1.setRotationPoint(0.0f, 8.0f, 0.0f);
        this.head1.setTextureSize(64, 64);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.0f, 3.141593f, 0.0f);
        this.head2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.head2.addBox(-3.0f, -3.0f, -6.0f, 6, 6, 6);
        this.head2.setRotationPoint(0.0f, 8.0f, 0.0f);
        this.head2.setTextureSize(64, 64);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 1.570796f, 0.0f);
        this.head3 = new ModelRenderer((ModelBase)this, 0, 16);
        this.head3.addBox(-3.0f, -3.0f, -6.0f, 6, 6, 6);
        this.head3.setRotationPoint(0.0f, 8.0f, 0.0f);
        this.head3.setTextureSize(64, 64);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 28);
        this.body.addBox(-6.0f, 0.0f, -6.0f, 12, 4, 12);
        this.body.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.body.setTextureSize(64, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 0, 32);
        this.rightarm.addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
        this.rightarm.setRotationPoint(-5.0f, 16.0f, 5.0f);
        this.rightarm.setTextureSize(64, 64);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 0, 32);
        this.leftarm.addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
        this.leftarm.setRotationPoint(5.0f, 16.0f, 5.0f);
        this.leftarm.setTextureSize(64, 64);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 32);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
        this.rightleg.setRotationPoint(-5.0f, 16.0f, -5.0f);
        this.rightleg.setTextureSize(64, 64);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 32);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
        this.leftleg.setRotationPoint(5.0f, 16.0f, -5.0f);
        this.leftleg.setTextureSize(64, 64);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.headPiece.render(f5);
        this.head.render(f5);
        this.head1.render(f5);
        this.head2.render(f5);
        this.head3.render(f5);
        this.body.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.rightleg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * f1;
        this.leftleg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * f1;
        this.rightarm.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * f1;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * f1;
    }
}

