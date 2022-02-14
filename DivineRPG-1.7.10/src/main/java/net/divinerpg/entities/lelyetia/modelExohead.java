/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.entities.lelyetia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelExohead
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer head4;
    ModelRenderer head5;
    ModelRenderer head6;

    public modelExohead() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelRenderer((ModelBase)this, 0, 37);
        this.head.addBox(2.0f, -4.0f, -12.0f, 2, 2, 5);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.setTextureSize(64, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 37);
        this.body.addBox(-3.0f, -2.0f, 1.0f, 6, 14, 2);
        this.body.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.body.setTextureSize(64, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-2.0f, -2.0f, -2.0f, 3, 15, 3);
        this.rightarm.setRotationPoint(-5.0f, 4.0f, 0.0f);
        this.rightarm.setTextureSize(64, 64);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 3, 15, 3);
        this.leftarm.setRotationPoint(5.0f, 4.0f, 0.0f);
        this.leftarm.setTextureSize(64, 64);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 3, 10, 3);
        this.rightleg.setRotationPoint(-2.0f, 14.0f, 0.0f);
        this.rightleg.setTextureSize(64, 64);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-1.0f, 0.0f, -2.0f, 3, 10, 3);
        this.leftleg.setRotationPoint(2.0f, 14.0f, 0.0f);
        this.leftleg.setTextureSize(64, 64);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer((ModelBase)this, 16, 27);
        this.body2.addBox(-4.0f, 6.0f, -2.0f, 8, 6, 3);
        this.body2.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.body2.setTextureSize(64, 64);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.0f, 0.0f, 0.0f);
        this.body3 = new ModelRenderer((ModelBase)this, 16, 17);
        this.body3.addBox(-4.0f, -2.0f, -2.0f, 8, 5, 3);
        this.body3.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.body3.setTextureSize(64, 64);
        this.body3.mirror = true;
        this.setRotation(this.body3, 0.0f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer((ModelBase)this, 40, 42);
        this.head2.addBox(-2.0f, -5.0f, 1.0f, 4, 1, 6);
        this.head2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head2.setTextureSize(64, 64);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.0f, 0.0f);
        this.head3 = new ModelRenderer((ModelBase)this, 27, 0);
        this.head3.addBox(-4.0f, -4.0f, -2.0f, 8, 4, 10);
        this.head3.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head3.setTextureSize(64, 64);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.0f, 0.0f, 0.0f);
        this.head4 = new ModelRenderer((ModelBase)this, 40, 42);
        this.head4.addBox(-2.0f, -5.0f, -7.0f, 4, 1, 6);
        this.head4.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head4.setTextureSize(64, 64);
        this.head4.mirror = true;
        this.setRotation(this.head4, 0.0f, 0.0f, 0.0f);
        this.head5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.head5.addBox(-4.0f, -4.0f, -7.0f, 8, 6, 5);
        this.head5.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head5.setTextureSize(64, 64);
        this.head5.mirror = true;
        this.setRotation(this.head5, 0.0f, 0.0f, 0.0f);
        this.head6 = new ModelRenderer((ModelBase)this, 0, 37);
        this.head6.addBox(-4.0f, -4.0f, -12.0f, 2, 2, 5);
        this.head6.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head6.setTextureSize(64, 64);
        this.head6.mirror = true;
        this.setRotation(this.head6, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.head.render(par7);
        this.body.render(par7);
        this.rightarm.render(par7);
        this.leftarm.render(par7);
        this.rightleg.render(par7);
        this.leftleg.render(par7);
        this.body2.render(par7);
        this.body3.render(par7);
        this.head2.render(par7);
        this.head3.render(par7);
        this.head4.render(par7);
        this.head5.render(par7);
        this.head6.render(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.rotateAngleY = par4 / 57.295776f;
        this.head.rotateAngleX = par5 / 54.11268f;
        this.head2.rotateAngleY = par4 / 57.295776f;
        this.head2.rotateAngleX = par5 / 54.11268f;
        this.head3.rotateAngleY = par4 / 57.295776f;
        this.head3.rotateAngleX = par5 / 54.11268f;
        this.head4.rotateAngleY = par4 / 57.295776f;
        this.head4.rotateAngleX = par5 / 54.11268f;
        this.head5.rotateAngleY = par4 / 57.295776f;
        this.head5.rotateAngleX = par5 / 54.11268f;
        this.head6.rotateAngleY = par4 / 57.295776f;
        this.head6.rotateAngleX = par5 / 54.11268f;
        this.rightarm.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.rightarm.rotateAngleZ = 0.0f;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.leftarm.rotateAngleZ = 0.0f;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.rightleg.rotateAngleY = 0.0f;
        this.leftleg.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
    }
}

