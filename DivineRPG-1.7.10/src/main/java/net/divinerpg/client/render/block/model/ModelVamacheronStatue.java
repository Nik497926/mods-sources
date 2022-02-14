/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelRenderer
 */
package net.divinerpg.client.render.block.model;

import net.divinerpg.client.model.DivineModel;
import net.divinerpg.client.model.DivineRenderer;
import net.minecraft.client.model.ModelRenderer;

public class ModelVamacheronStatue
extends DivineModel {
    ModelRenderer head = new DivineRenderer(this, 0, 0);
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer horn1;
    ModelRenderer horn2;
    ModelRenderer Horn;
    ModelRenderer Shape17;
    ModelRenderer head2;
    ModelRenderer horn3;
    ModelRenderer horn4;
    ModelRenderer Horn5;
    ModelRenderer Shape1;

    public ModelVamacheronStatue() {
        super("statueVamacheron", 64, 32);
        this.head.addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
        this.head.setRotationPoint(-6.0f, 4.0f, -8.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new DivineRenderer(this, 18, 4);
        this.body.addBox(-6.0f, -10.0f, -7.0f, 8, 18, 10);
        this.body.setRotationPoint(2.0f, 5.0f, 2.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 1.570796f, 0.0f, 0.0f);
        this.leg1 = new DivineRenderer(this, 0, 16);
        this.leg1.addBox(-3.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leg1.setRotationPoint(-2.0f, 12.0f, 7.0f);
        this.leg1.setTextureSize(64, 32);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.4833219f, 0.0f, 0.0f);
        this.leg2 = new DivineRenderer(this, 0, 16);
        this.leg2.addBox(-1.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leg2.setRotationPoint(2.0f, 12.0f, 7.0f);
        this.leg2.setTextureSize(64, 32);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, -0.2230717f, 0.0f, 0.0f);
        this.leg2.mirror = false;
        this.leg3 = new DivineRenderer(this, 0, 16);
        this.leg3.addBox(-3.0f, 0.0f, -3.0f, 4, 12, 4);
        this.leg3.setRotationPoint(-2.0f, 12.0f, -5.0f);
        this.leg3.setTextureSize(64, 32);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.2974289f, 0.0f, 0.0f);
        this.leg4 = new DivineRenderer(this, 0, 16);
        this.leg4.addBox(-1.0f, 0.0f, -3.0f, 4, 12, 4);
        this.leg4.setRotationPoint(2.0f, 12.0f, -5.0f);
        this.leg4.setTextureSize(64, 32);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, -0.3717861f, 0.0f, 0.0f);
        this.horn1 = new DivineRenderer(this, 54, 0);
        this.horn1.addBox(-4.0f, -5.0f, -4.0f, 1, 3, 3);
        this.horn1.setRotationPoint(-6.0f, 2.0f, -8.0f);
        this.horn1.setTextureSize(64, 32);
        this.horn1.mirror = true;
        this.setRotation(this.horn1, 0.0f, 0.0f, 0.0f);
        this.horn2 = new DivineRenderer(this, 55, 0);
        this.horn2.addBox(3.0f, -5.0f, -4.0f, 1, 3, 3);
        this.horn2.setRotationPoint(-6.0f, 2.0f, -8.0f);
        this.horn2.setTextureSize(64, 32);
        this.horn2.mirror = true;
        this.setRotation(this.horn2, 0.0f, 0.0f, 0.0f);
        this.Horn = new DivineRenderer(this, 55, 7);
        this.Horn.addBox(0.0f, 0.0f, 0.0f, 2, 7, 2);
        this.Horn.setRotationPoint(-7.0f, 0.0f, -20.0f);
        this.Horn.setTextureSize(64, 32);
        this.Horn.mirror = true;
        this.setRotation(this.Horn, 1.22173f, 0.0f, 0.0f);
        this.Shape17 = new DivineRenderer(this, 9, 0);
        this.Shape17.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.Shape17.setRotationPoint(-7.0f, 3.0f, -15.0f);
        this.Shape17.setTextureSize(64, 32);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0f, 0.0f, 0.0f);
        this.head2 = new DivineRenderer(this, 0, 0);
        this.head2.addBox(0.0f, 0.0f, 0.0f, 8, 8, 6);
        this.head2.setRotationPoint(2.0f, 0.0f, -14.0f);
        this.head2.setTextureSize(64, 32);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.0f, 0.0f);
        this.horn3 = new DivineRenderer(this, 55, 0);
        this.horn3.addBox(0.0f, 0.0f, 0.0f, 1, 3, 3);
        this.horn3.setRotationPoint(2.0f, -3.0f, -12.0f);
        this.horn3.setTextureSize(64, 32);
        this.horn3.mirror = true;
        this.setRotation(this.horn3, 0.0f, 0.0f, 0.0f);
        this.horn4 = new DivineRenderer(this, 55, 0);
        this.horn4.addBox(0.0f, 0.0f, 0.0f, 1, 3, 3);
        this.horn4.setRotationPoint(9.0f, -3.0f, -12.0f);
        this.horn4.setTextureSize(64, 32);
        this.horn4.mirror = true;
        this.setRotation(this.horn4, 0.0f, 0.0f, 0.0f);
        this.Horn5 = new DivineRenderer(this, 55, 7);
        this.Horn5.addBox(0.0f, 0.0f, 0.0f, 2, 7, 2);
        this.Horn5.setRotationPoint(5.0f, 0.0f, -20.0f);
        this.Horn5.setTextureSize(64, 32);
        this.Horn5.mirror = true;
        this.setRotation(this.Horn5, 1.22173f, 0.0f, 0.0f);
        this.Shape1 = new DivineRenderer(this, 9, 0);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.Shape1.setRotationPoint(5.0f, 3.0f, -15.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
    }
}

