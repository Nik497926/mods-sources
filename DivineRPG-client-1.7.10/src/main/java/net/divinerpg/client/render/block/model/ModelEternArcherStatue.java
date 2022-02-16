/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.block.model;

import net.divinerpg.client.model.DivineModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEternArcherStatue
extends DivineModel {
    public ModelRenderer head;
    public ModelRenderer shoulder6;
    public ModelRenderer[] armsRight = new ModelRenderer[3];
    public ModelRenderer[] armsLeft = new ModelRenderer[3];
    public ModelRenderer rightleg;
    public ModelRenderer leftleg;
    public ModelRenderer body;
    public ModelRenderer shoulder1;
    public ModelRenderer shoulder4;
    public ModelRenderer shoulder3;
    public ModelRenderer shoulder2;
    public ModelRenderer shoulder5;

    public ModelEternArcherStatue() {
        super("eternalArcherStatue", 128, 32);
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.setTextureSize(128, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.shoulder6 = new ModelRenderer((ModelBase)this, 41, 1);
        this.shoulder6.addBox(-4.0f, 0.0f, -2.0f, 10, 3, 4);
        this.shoulder6.setRotationPoint(18.0f, -6.0f, 0.0f);
        this.shoulder6.setTextureSize(128, 32);
        this.shoulder6.mirror = true;
        this.setRotation(this.shoulder6, 0.0f, 0.0f, 0.0f);
        this.armsRight[0] = new ModelRenderer((ModelBase)this, 110, 16);
        this.armsRight[0].addBox(-3.0f, -2.5f, -2.0f, 4, 12, 4);
        this.armsRight[0].setRotationPoint(-21.0f, -1.0f, 0.0f);
        this.armsRight[0].setTextureSize(128, 32);
        this.armsRight[0].mirror = true;
        this.setRotation(this.armsRight[0], 0.0f, 0.0f, 0.0f);
        this.armsLeft[2] = new ModelRenderer((ModelBase)this, 110, 16);
        this.armsLeft[2].addBox(-1.0f, -2.5f, -2.0f, 4, 12, 4);
        this.armsLeft[2].setRotationPoint(21.0f, -1.0f, 0.0f);
        this.armsLeft[2].setTextureSize(128, 32);
        this.armsLeft[2].mirror = true;
        this.setRotation(this.armsLeft[2], 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.setRotationPoint(-3.0f, 12.0f, 0.0f);
        this.rightleg.setTextureSize(128, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.setRotationPoint(3.0f, 12.0f, 0.0f);
        this.leftleg.setTextureSize(128, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.armsRight[2] = new ModelRenderer((ModelBase)this, 71, 16);
        this.armsRight[2].addBox(-3.0f, -2.5f, -2.0f, 4, 12, 4);
        this.armsRight[2].setRotationPoint(-7.0f, 5.0f, 0.0f);
        this.armsRight[2].setTextureSize(128, 32);
        this.armsRight[2].mirror = true;
        this.setRotation(this.armsRight[2], 0.0f, 0.0f, 0.0f);
        this.armsLeft[0] = new ModelRenderer((ModelBase)this, 71, 16);
        this.armsLeft[0].addBox(-1.0f, -2.5f, -2.0f, 4, 12, 4);
        this.armsLeft[0].setRotationPoint(7.0f, 5.0f, 0.0f);
        this.armsLeft[0].setTextureSize(128, 32);
        this.armsLeft[0].mirror = true;
        this.setRotation(this.armsLeft[0], 0.0f, 0.0f, 0.0f);
        this.armsLeft[1] = new ModelRenderer((ModelBase)this, 91, 16);
        this.armsLeft[1].addBox(-1.0f, -2.5f, -2.0f, 4, 12, 4);
        this.armsLeft[1].setRotationPoint(14.0f, 2.0f, 0.0f);
        this.armsLeft[1].setTextureSize(128, 32);
        this.armsLeft[1].mirror = true;
        this.setRotation(this.armsLeft[1], 0.0f, 0.0f, 0.0f);
        this.armsRight[1] = new ModelRenderer((ModelBase)this, 91, 16);
        this.armsRight[1].addBox(-3.0f, -2.5f, -2.0f, 4, 12, 4);
        this.armsRight[1].setRotationPoint(-14.0f, 2.0f, 0.0f);
        this.armsRight[1].setTextureSize(128, 32);
        this.armsRight[1].mirror = true;
        this.setRotation(this.armsRight[1], 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 46, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureSize(128, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.shoulder1 = new ModelRenderer((ModelBase)this, 41, 1);
        this.shoulder1.addBox(-4.0f, 0.0f, -2.0f, 10, 3, 4);
        this.shoulder1.setRotationPoint(-20.0f, -6.0f, 0.0f);
        this.shoulder1.setTextureSize(128, 32);
        this.shoulder1.mirror = true;
        this.setRotation(this.shoulder1, 0.0f, 0.0f, 0.0f);
        this.shoulder4 = new ModelRenderer((ModelBase)this, 19, 24);
        this.shoulder4.addBox(-4.0f, 0.0f, -2.0f, 6, 3, 4);
        this.shoulder4.setRotationPoint(8.0f, 0.0f, 0.0f);
        this.shoulder4.setTextureSize(128, 32);
        this.shoulder4.mirror = true;
        this.setRotation(this.shoulder4, 0.0f, 0.0f, 0.0f);
        this.shoulder3 = new ModelRenderer((ModelBase)this, 19, 24);
        this.shoulder3.addBox(-4.0f, 0.0f, -2.0f, 6, 3, 4);
        this.shoulder3.setRotationPoint(-6.0f, 0.0f, 0.0f);
        this.shoulder3.setTextureSize(128, 32);
        this.shoulder3.mirror = true;
        this.setRotation(this.shoulder3, 0.0f, 0.0f, 0.0f);
        this.shoulder2 = new ModelRenderer((ModelBase)this, 17, 16);
        this.shoulder2.addBox(-4.0f, 0.0f, -2.0f, 10, 3, 4);
        this.shoulder2.setRotationPoint(-13.0f, -3.0f, 0.0f);
        this.shoulder2.setTextureSize(128, 32);
        this.shoulder2.mirror = true;
        this.setRotation(this.shoulder2, 0.0f, 0.0f, 0.0f);
        this.shoulder5 = new ModelRenderer((ModelBase)this, 17, 16);
        this.shoulder5.addBox(-4.0f, 0.0f, -2.0f, 10, 3, 4);
        this.shoulder5.setRotationPoint(11.0f, -3.0f, 0.0f);
        this.shoulder5.setTextureSize(128, 32);
        this.shoulder5.mirror = true;
        this.setRotation(this.shoulder5, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    @Override
    public void render(float scale) {
        this.head.render(scale);
        this.shoulder6.render(scale);
        this.armsRight[0].render(scale);
        this.armsLeft[2].render(scale);
        this.rightleg.render(scale);
        this.leftleg.render(scale);
        this.armsRight[2].render(scale);
        this.armsLeft[0].render(scale);
        this.armsLeft[1].render(scale);
        this.armsRight[1].render(scale);
        this.body.render(scale);
        this.shoulder1.render(scale);
        this.shoulder4.render(scale);
        this.shoulder3.render(scale);
        this.shoulder2.render(scale);
        this.shoulder5.render(scale);
    }

    @Override
    protected void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.rightleg.rotateAngleX = -0.3f;
        this.leftleg.rotateAngleX = 0.3f;
    }
}

