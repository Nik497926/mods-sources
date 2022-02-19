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

public class ModelTwilightStatue
extends DivineModel {
    ModelRenderer head = new DivineRenderer(this, 0, 0);
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer connector;
    ModelRenderer head2;
    ModelRenderer Spear2;
    ModelRenderer Shape1;
    ModelRenderer head3;
    ModelRenderer connector1;
    ModelRenderer connector2;

    public ModelTwilightStatue() {
        super("statueTwilightDemon", 64, 32);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new DivineRenderer(this, 16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm = new DivineRenderer(this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, -5.576792f, 0.0f, 0.0f);
        this.leftarm = new DivineRenderer(this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, -0.669215f, 0.0f);
        this.rightleg = new DivineRenderer(this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new DivineRenderer(this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.setRotationPoint(2.0f, 12.0f, 0.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, -0.5948578f, 0.0f);
        this.connector = new DivineRenderer(this, 0, 20);
        this.connector.addBox(0.0f, 0.0f, 0.0f, 4, 4, 6);
        this.connector.setRotationPoint(6.0f, -6.0f, -11.0f);
        this.connector.setTextureSize(64, 32);
        this.connector.mirror = true;
        this.setRotation(this.connector, 0.0f, 0.0f, 0.0f);
        this.head2 = new DivineRenderer(this, 32, 0);
        this.head2.addBox(1.0f, 0.0f, -3.0f, 8, 8, 8);
        this.head2.setRotationPoint(4.0f, -8.0f, -16.0f);
        this.head2.setTextureSize(64, 32);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, -0.3717861f, 0.0f);
        this.Spear2 = new DivineRenderer(this, -2, 0);
        this.Spear2.addBox(1.0f, 0.0f, 0.0f, 1, 1, 13);
        this.Spear2.setRotationPoint(5.5f, 10.0f, -1.0f);
        this.Spear2.setTextureSize(64, 32);
        this.Spear2.mirror = true;
        this.setRotation(this.Spear2, 3.141593f, -0.669215f, 0.0f);
        this.Shape1 = new DivineRenderer(this, 0, 0);
        this.Shape1.addBox(1.0f, -4.0f, -6.0f, 1, 1, 13);
        this.Shape1.setRotationPoint(-7.0f, 10.0f, -1.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, -2.435199f, 0.0f, 0.0f);
        this.head3 = new DivineRenderer(this, 32, 0);
        this.head3.addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
        this.head3.setRotationPoint(-11.4f, -8.0f, -16.0f);
        this.head3.setTextureSize(64, 32);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.0f, 0.0f, 0.0f);
        this.connector1 = new DivineRenderer(this, 0, 20);
        this.connector1.addBox(0.0f, 0.0f, 0.0f, 4, 4, 6);
        this.connector1.setRotationPoint(-10.0f, -6.0f, -11.0f);
        this.connector1.setTextureSize(64, 32);
        this.connector1.mirror = true;
        this.setRotation(this.connector1, 0.0f, 0.0f, 0.0f);
        this.connector2 = new DivineRenderer(this, 0, 20);
        this.connector2.addBox(-4.0f, 0.0f, 0.0f, 20, 4, 4);
        this.connector2.setRotationPoint(-6.0f, -6.0f, -5.0f);
        this.connector2.setTextureSize(64, 32);
        this.connector2.mirror = true;
        this.setRotation(this.connector2, 0.0f, 0.0f, 0.0f);
    }
}

