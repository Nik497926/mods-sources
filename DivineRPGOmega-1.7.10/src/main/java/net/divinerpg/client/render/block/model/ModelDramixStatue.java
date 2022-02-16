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

public class ModelDramixStatue
extends DivineModel {
    ModelRenderer Leg_Right = new DivineRenderer(this, 0, 22);
    ModelRenderer Leg_Left;
    ModelRenderer Arm_Right;
    ModelRenderer Arm_Left;
    ModelRenderer Head;
    ModelRenderer Chest;

    public ModelDramixStatue() {
        super("statueDramix", 64, 64);
        this.Leg_Right.addBox(-2.0f, -2.0f, -3.0f, 5, 15, 6);
        this.Leg_Right.setRotationPoint(-4.0f, 11.0f, 1.0f);
        this.Leg_Right.setTextureSize(64, 64);
        this.Leg_Right.mirror = true;
        this.setRotation(this.Leg_Right, 0.0f, 0.0f, 0.0f);
        this.Leg_Left = new DivineRenderer(this, 0, 22);
        this.Leg_Left.addBox(-3.0f, -2.0f, -3.0f, 5, 15, 6);
        this.Leg_Left.setRotationPoint(4.0f, 11.0f, 1.0f);
        this.Leg_Left.setTextureSize(64, 64);
        this.Leg_Left.mirror = true;
        this.setRotation(this.Leg_Left, -0.8922867f, 0.0f, 0.0f);
        this.Arm_Right = new DivineRenderer(this, 22, 22);
        this.Arm_Right.addBox(-2.0f, -2.0f, -3.0f, 4, 16, 6);
        this.Arm_Right.setRotationPoint(-8.0f, -5.0f, 1.0f);
        this.Arm_Right.setTextureSize(64, 64);
        this.Arm_Right.mirror = true;
        this.setRotation(this.Arm_Right, -2.342252f, 0.0f, 0.0f);
        this.Arm_Right.mirror = false;
        this.Arm_Left = new DivineRenderer(this, 22, 22);
        this.Arm_Left.addBox(-2.0f, -2.0f, -3.0f, 4, 16, 6);
        this.Arm_Left.setRotationPoint(8.0f, -5.0f, 1.0f);
        this.Arm_Left.setTextureSize(64, 64);
        this.Arm_Left.mirror = true;
        this.setRotation(this.Arm_Left, -2.342252f, 0.0f, 0.0f);
        this.Head = new DivineRenderer(this, 36, 0);
        this.Head.addBox(-3.0f, -5.0f, -3.0f, 6, 10, 6);
        this.Head.setRotationPoint(0.0f, -12.0f, 1.0f);
        this.Head.setTextureSize(64, 64);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Chest = new DivineRenderer(this, 0, 0);
        this.Chest.addBox(-6.0f, -7.0f, -2.0f, 12, 16, 6);
        this.Chest.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Chest.setTextureSize(64, 64);
        this.Chest.mirror = true;
        this.setRotation(this.Chest, 0.0f, 0.0f, 0.0f);
    }
}

