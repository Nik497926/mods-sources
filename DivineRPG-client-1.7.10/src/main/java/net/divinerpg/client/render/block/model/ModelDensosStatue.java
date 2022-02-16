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

public class ModelDensosStatue
extends DivineModel {
    ModelRenderer head = new DivineRenderer(this, 0, 0);
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer head4;
    ModelRenderer head5;

    public ModelDensosStatue() {
        super("statueDensos", 64, 32);
        this.head.addBox(-11.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(1.0f, 0.0f, -2.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new DivineRenderer(this, 16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, 0.0f, -4.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.3346075f, 0.0f, 0.0f);
        this.rightarm = new DivineRenderer(this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.setRotationPoint(-5.0f, 2.0f, -3.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, -0.6934844f, 0.0f, 0.0f);
        this.leftarm = new DivineRenderer(this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.setRotationPoint(5.0f, 2.0f, -3.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.669215f, 0.0f, 0.0f);
        this.rightleg = new DivineRenderer(this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, -0.2602503f, 0.0f, 0.0f);
        this.leftleg = new DivineRenderer(this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.setRotationPoint(2.0f, 12.0f, 0.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.4461433f, 0.0f, 0.0f);
        this.head2 = new DivineRenderer(this, 0, 0);
        this.head2.addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
        this.head2.setRotationPoint(1.466667f, -8.0f, -6.0f);
        this.head2.setTextureSize(64, 32);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.0f, 0.0f);
        this.head3 = new DivineRenderer(this, 0, 0);
        this.head3.addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
        this.head3.setRotationPoint(-4.0f, -16.0f, -2.0f);
        this.head3.setTextureSize(64, 32);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.0f, 0.0f, 0.0f);
        this.head4 = new DivineRenderer(this, 0, 0);
        this.head4.addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
        this.head4.setRotationPoint(-14.0f, -16.0f, -2.0f);
        this.head4.setTextureSize(64, 32);
        this.head4.mirror = true;
        this.setRotation(this.head4, 0.0f, 0.0f, 0.0f);
        this.head5 = new DivineRenderer(this, 0, 0);
        this.head5.addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
        this.head5.setRotationPoint(6.0f, -16.0f, -2.0f);
        this.head5.setTextureSize(64, 32);
        this.head5.mirror = true;
        this.setRotation(this.head5, 0.0f, 0.0f, 0.0f);
    }
}

