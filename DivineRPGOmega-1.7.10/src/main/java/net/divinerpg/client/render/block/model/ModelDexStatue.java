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

public class ModelDexStatue
extends DivineModel {
    ModelRenderer p1 = new DivineRenderer(this, 0, 0);
    ModelRenderer p2;
    ModelRenderer p3;
    ModelRenderer p4;
    ModelRenderer p5;
    ModelRenderer p18;
    ModelRenderer p6;
    ModelRenderer p7;
    ModelRenderer p8;
    ModelRenderer p9;
    ModelRenderer p10;
    ModelRenderer p11;
    ModelRenderer p12;
    ModelRenderer p13;
    ModelRenderer p14;
    ModelRenderer p15;
    ModelRenderer p16;
    ModelRenderer p17;

    public ModelDexStatue() {
        super("statueDex", 64, 32);
        this.p1.addBox(0.0f, 0.0f, 0.0f, 10, 6, 10);
        this.p1.setRotationPoint(-6.0f, 11.0f, 4.0f);
        this.p1.setTextureSize(64, 32);
        this.p1.mirror = true;
        this.setRotation(this.p1, 0.0f, 1.570796f, 0.0f);
        this.p2 = new DivineRenderer(this, 39, 20);
        this.p2.addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.p2.setRotationPoint(-4.0f, 14.0f, 2.0f);
        this.p2.setTextureSize(64, 32);
        this.p2.mirror = true;
        this.setRotation(this.p2, 0.0f, 1.570796f, 0.0f);
        this.p3 = new DivineRenderer(this, 43, 9);
        this.p3.addBox(0.0f, 0.0f, 0.0f, 2, 2, 7);
        this.p3.setRotationPoint(2.0f, 18.0f, 0.0f);
        this.p3.setTextureSize(64, 32);
        this.p3.mirror = true;
        this.setRotation(this.p3, 0.0f, 1.570796f, 0.0f);
        this.p4 = new DivineRenderer(this, 27, 1);
        this.p4.addBox(0.0f, 0.0f, 0.0f, 2, 2, 7);
        this.p4.setRotationPoint(-11.0f, 18.0f, 0.0f);
        this.p4.setTextureSize(64, 32);
        this.p4.mirror = true;
        this.setRotation(this.p4, 0.0f, 1.570796f, 0.0f);
        this.p5 = new DivineRenderer(this, 39, 4);
        this.p5.addBox(0.0f, 0.0f, 0.0f, 2, 2, 10);
        this.p5.setRotationPoint(-11.0f, 14.0f, 0.0f);
        this.p5.setTextureSize(64, 32);
        this.p5.mirror = true;
        this.setRotation(this.p5, -1.570796f, 1.570796f, 0.0f);
        this.p18 = new DivineRenderer(this, 39, 4);
        this.p18.addBox(0.0f, 0.0f, 0.0f, 2, 2, 10);
        this.p18.setRotationPoint(11.0f, 14.0f, 0.0f);
        this.p18.setTextureSize(64, 32);
        this.p18.mirror = true;
        this.setRotation(this.p18, -1.570796f, 1.570796f, 0.0f);
        this.p6 = new DivineRenderer(this, 39, 4);
        this.p6.addBox(-5.0f, 0.0f, 0.0f, 2, 2, 10);
        this.p6.setRotationPoint(15.0f, 11.0f, 1.0f);
        this.p6.setTextureSize(64, 32);
        this.p6.mirror = true;
        this.setRotation(this.p6, -1.570796f, 1.570796f, 0.0f);
        this.p7 = new DivineRenderer(this, 43, 9);
        this.p7.addBox(-5.0f, 0.0f, 0.0f, 2, 2, 4);
        this.p7.setRotationPoint(9.0f, 11.0f, 1.0f);
        this.p7.setTextureSize(64, 32);
        this.p7.mirror = true;
        this.setRotation(this.p7, 0.0f, 1.570796f, 0.0f);
        this.p8 = new DivineRenderer(this, 39, 20);
        this.p8.addBox(-5.0f, 0.0f, 0.0f, 6, 6, 6);
        this.p8.setRotationPoint(-4.0f, 7.0f, 3.0f);
        this.p8.setTextureSize(64, 32);
        this.p8.mirror = true;
        this.setRotation(this.p8, 0.0f, 1.570796f, 0.0f);
        this.p9 = new DivineRenderer(this, 27, 1);
        this.p9.addBox(-5.0f, 0.0f, 0.0f, 2, 2, 4);
        this.p9.setRotationPoint(-15.0f, 11.0f, 1.0f);
        this.p9.setTextureSize(64, 32);
        this.p9.mirror = true;
        this.setRotation(this.p9, 0.0f, 1.570796f, 0.0f);
        this.p10 = new DivineRenderer(this, 39, 4);
        this.p10.addBox(-5.0f, 0.0f, 0.0f, 2, 2, 4);
        this.p10.setRotationPoint(0.0f, 20.0f, 5.0f);
        this.p10.setTextureSize(64, 32);
        this.p10.mirror = true;
        this.setRotation(this.p10, -1.570796f, 1.570796f, 0.0f);
        this.p11 = new DivineRenderer(this, 0, 0);
        this.p11.addBox(-5.0f, 0.0f, 0.0f, 10, 6, 10);
        this.p11.setRotationPoint(-6.0f, 4.0f, 5.0f);
        this.p11.setTextureSize(64, 32);
        this.p11.mirror = true;
        this.setRotation(this.p11, 0.0f, 1.570796f, 0.0f);
        this.p12 = new DivineRenderer(this, 39, 4);
        this.p12.addBox(-5.0f, 0.0f, 0.0f, 2, 2, 10);
        this.p12.setRotationPoint(-15.0f, 1.0f, 1.0f);
        this.p12.setTextureSize(64, 32);
        this.p12.mirror = true;
        this.setRotation(this.p12, -1.570796f, 1.570796f, 0.0f);
        this.p13 = new DivineRenderer(this, 39, 4);
        this.p13.addBox(-5.0f, 0.0f, 0.0f, 2, 2, 10);
        this.p13.setRotationPoint(15.0f, 1.0f, 1.0f);
        this.p13.setTextureSize(64, 32);
        this.p13.mirror = true;
        this.setRotation(this.p13, -1.570796f, 1.570796f, 0.0f);
        this.p14 = new DivineRenderer(this, 27, 1);
        this.p14.addBox(-5.0f, 0.0f, 0.0f, 2, 2, 7);
        this.p14.setRotationPoint(-11.0f, 11.0f, 1.0f);
        this.p14.setTextureSize(64, 32);
        this.p14.mirror = true;
        this.setRotation(this.p14, 0.0f, 1.570796f, 0.0f);
        this.p15 = new DivineRenderer(this, 43, 9);
        this.p15.addBox(-5.0f, 0.0f, 0.0f, 2, 2, 7);
        this.p15.setRotationPoint(2.0f, 11.0f, 1.0f);
        this.p15.setTextureSize(64, 32);
        this.p15.mirror = true;
        this.setRotation(this.p15, 0.0f, 1.570796f, 0.0f);
        this.p16 = new DivineRenderer(this, 39, 4);
        this.p16.addBox(-5.0f, 0.0f, 0.0f, 2, 2, 10);
        this.p16.setRotationPoint(-15.0f, 11.0f, 1.0f);
        this.p16.setTextureSize(64, 32);
        this.p16.mirror = true;
        this.setRotation(this.p16, -1.570796f, 1.570796f, 0.0f);
        this.p17 = new DivineRenderer(this, 39, 4);
        this.p17.addBox(-5.0f, 0.0f, 0.0f, 2, 2, 10);
        this.p17.setRotationPoint(0.0f, 10.0f, 5.0f);
        this.p17.setTextureSize(64, 32);
        this.p17.mirror = true;
        this.setRotation(this.p17, -1.570796f, 1.570796f, 0.0f);
    }
}

