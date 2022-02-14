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

public class ModelWatcherStatue
extends DivineModel {
    ModelRenderer Head = new DivineRenderer(this, 0, 0);
    ModelRenderer Jaw;
    ModelRenderer Tentacle11;
    ModelRenderer Tentacle12;
    ModelRenderer Tentacle21;
    ModelRenderer Tentacle22;
    ModelRenderer Support;
    ModelRenderer Tentacle32;
    ModelRenderer Tentacle31;

    public ModelWatcherStatue() {
        super("statueWatcher", 64, 32);
        this.Head.addBox(-5.0f, -5.0f, -5.0f, 10, 10, 10);
        this.Head.setRotationPoint(0.0f, 13.0f, 0.0f);
        this.Head.setTextureSize(64, 32);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, -1.570796f, 0.0f);
        this.Jaw = new DivineRenderer(this, 0, 20);
        this.Jaw.addBox(-5.0f, 0.0f, -10.0f, 10, 1, 10);
        this.Jaw.setRotationPoint(0.0f, 18.0f, 5.0f);
        this.Jaw.setTextureSize(64, 32);
        this.Jaw.mirror = true;
        this.setRotation(this.Jaw, 0.1745329f, 0.0f, 0.0f);
        this.Tentacle11 = new DivineRenderer(this, 40, 0);
        this.Tentacle11.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 3);
        this.Tentacle11.setRotationPoint(5.0f, 13.0f, 0.0f);
        this.Tentacle11.setTextureSize(64, 32);
        this.Tentacle11.mirror = true;
        this.setRotation(this.Tentacle11, 0.0f, 1.570796f, 0.0f);
        this.Tentacle12 = new DivineRenderer(this, 40, 6);
        this.Tentacle12.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle12.setRotationPoint(9.0f, 13.0f, 0.0f);
        this.Tentacle12.setTextureSize(64, 32);
        this.Tentacle12.mirror = true;
        this.setRotation(this.Tentacle12, 0.0f, 4.712389f, 0.0f);
        this.Tentacle21 = new DivineRenderer(this, 40, 0);
        this.Tentacle21.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 3);
        this.Tentacle21.setRotationPoint(-5.0f, 13.0f, 0.0f);
        this.Tentacle21.setTextureSize(64, 32);
        this.Tentacle21.mirror = true;
        this.setRotation(this.Tentacle21, 0.0f, 4.712389f, 0.0f);
        this.Tentacle22 = new DivineRenderer(this, 40, 6);
        this.Tentacle22.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle22.setRotationPoint(-9.0f, 13.0f, 0.0f);
        this.Tentacle22.setTextureSize(64, 32);
        this.Tentacle22.mirror = true;
        this.setRotation(this.Tentacle22, 0.0f, 4.712389f, 0.0f);
        this.Support = new DivineRenderer(this, 52, 0);
        this.Support.addBox(-1.0f, -3.0f, -1.0f, 2, 4, 2);
        this.Support.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.Support.setTextureSize(64, 32);
        this.Support.mirror = true;
        this.setRotation(this.Support, 0.0f, 0.0f, 0.0f);
        this.Tentacle32 = new DivineRenderer(this, 40, 6);
        this.Tentacle32.addBox(-4.0f, -1.0f, -1.0f, 5, 2, 2);
        this.Tentacle32.setRotationPoint(0.0f, 4.0f, 0.0f);
        this.Tentacle32.setTextureSize(64, 32);
        this.Tentacle32.mirror = true;
        this.setRotation(this.Tentacle32, 0.0f, 4.712389f, 0.0f);
        this.Tentacle31 = new DivineRenderer(this, 52, 0);
        this.Tentacle31.addBox(-1.0f, -3.0f, -1.0f, 2, 3, 2);
        this.Tentacle31.setRotationPoint(0.0f, 8.0f, 0.0f);
        this.Tentacle31.setTextureSize(64, 32);
        this.Tentacle31.mirror = true;
        this.setRotation(this.Tentacle31, 0.0f, 0.0f, 0.0f);
    }
}

