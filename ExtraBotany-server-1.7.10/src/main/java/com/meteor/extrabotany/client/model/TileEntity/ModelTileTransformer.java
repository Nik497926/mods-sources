/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.model.TileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(value=Side.CLIENT)
public class ModelTileTransformer
extends ModelBase {
    ModelRenderer place;
    ModelRenderer fPillar;
    ModelRenderer bPillar;
    ModelRenderer rPillar;
    ModelRenderer lPillar;
    ModelRenderer topPlace;
    ModelRenderer center;

    public ModelTileTransformer() {
        this.textureHeight = 64;
        this.textureWidth = 64;
        this.place = new ModelRenderer((ModelBase)this, 0, 0);
        this.place.addBox(0.0f, 0.0f, 0.0f, 16, 1, 16);
        this.place.setRotationPoint(-8.0f, 23.0f, -8.0f);
        this.place.setTextureSize(64, 64);
        this.setRotation(this.place, 0.0f, 0.0f, 0.0f);
        this.setTextureOffset("fPillar.b0", 0, 55);
        this.setTextureOffset("fPillar.b1", 0, 58);
        this.setTextureOffset("fPillar.b2", 0, 60);
        this.setTextureOffset("fPillar.b3", 56, 59);
        this.setTextureOffset("fPillar.b4", 0, 60);
        this.setTextureOffset("fPillar.b5", 0, 55);
        this.setTextureOffset("fPillar.b6", 0, 57);
        this.fPillar = new ModelRenderer((ModelBase)this, "fPillar");
        this.fPillar.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.setRotation(this.fPillar, 0.0f, 0.0f, 0.0f);
        this.fPillar.setTextureSize(64, 64);
        this.fPillar.addBox("b0", -1.0f, 22.0f, -8.0f, 2, 1, 6);
        this.fPillar.addBox("b1", -1.0f, 21.0f, -8.0f, 2, 1, 4);
        this.fPillar.addBox("b2", -1.0f, 19.0f, -8.0f, 2, 2, 2);
        this.fPillar.addBox("b3", -1.0f, 16.0f, -8.0f, 2, 2, 2);
        this.fPillar.addBox("b4", -1.0f, 13.0f, -8.0f, 2, 2, 2);
        this.fPillar.addBox("b5", -1.0f, 12.0f, -8.0f, 2, 1, 4);
        this.fPillar.addBox("b6", -1.0f, 11.0f, -8.0f, 2, 1, 6);
        this.setTextureOffset("bPillar.b0", 0, 57);
        this.setTextureOffset("bPillar.b1", 0, 56);
        this.setTextureOffset("bPillar.b2", 0, 55);
        this.setTextureOffset("bPillar.b3", 56, 59);
        this.setTextureOffset("bPillar.b4", 0, 57);
        this.setTextureOffset("bPillar.b5", 0, 58);
        this.setTextureOffset("bPillar.b6", 0, 56);
        this.bPillar = new ModelRenderer((ModelBase)this, "bPillar");
        this.bPillar.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.setRotation(this.bPillar, 0.0f, 0.0f, 0.0f);
        this.bPillar.setTextureSize(64, 64);
        this.bPillar.addBox("b0", -1.0f, 22.0f, 2.0f, 2, 1, 6);
        this.bPillar.addBox("b1", -1.0f, 21.0f, 4.0f, 2, 1, 4);
        this.bPillar.addBox("b2", -1.0f, 19.0f, 6.0f, 2, 2, 2);
        this.bPillar.addBox("b3", -1.0f, 16.0f, 6.0f, 2, 2, 2);
        this.bPillar.addBox("b4", -1.0f, 13.0f, 6.0f, 2, 2, 2);
        this.bPillar.addBox("b5", -1.0f, 12.0f, 4.0f, 2, 1, 4);
        this.bPillar.addBox("b6", -1.0f, 11.0f, 2.0f, 2, 1, 6);
        this.setTextureOffset("rPillar.b0", 0, 60);
        this.setTextureOffset("rPillar.b1", 0, 59);
        this.setTextureOffset("rPillar.b2", 0, 60);
        this.setTextureOffset("rPillar.b3", 56, 59);
        this.setTextureOffset("rPillar.b4", 0, 58);
        this.setTextureOffset("rPillar.b5", 0, 59);
        this.setTextureOffset("rPillar.b6", 0, 60);
        this.rPillar = new ModelRenderer((ModelBase)this, "rPillar");
        this.rPillar.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.setRotation(this.rPillar, 0.0f, 0.0f, 0.0f);
        this.rPillar.setTextureSize(64, 64);
        this.rPillar.addBox("b0", -8.0f, 22.0f, -1.0f, 6, 1, 2);
        this.rPillar.addBox("b1", -8.0f, 21.0f, -1.0f, 4, 1, 2);
        this.rPillar.addBox("b2", -8.0f, 19.0f, -1.0f, 2, 2, 2);
        this.rPillar.addBox("b3", -8.0f, 16.0f, -1.0f, 2, 2, 2);
        this.rPillar.addBox("b4", -8.0f, 13.0f, -1.0f, 2, 2, 2);
        this.rPillar.addBox("b5", -8.0f, 12.0f, -1.0f, 4, 1, 2);
        this.rPillar.addBox("b6", -8.0f, 11.0f, -1.0f, 6, 1, 2);
        this.setTextureOffset("lPillar.b0", 0, 61);
        this.setTextureOffset("lPillar.b1", 0, 60);
        this.setTextureOffset("lPillar.b2", 0, 59);
        this.setTextureOffset("lPillar.b3", 56, 59);
        this.setTextureOffset("lPillar.b4", 0, 60);
        this.setTextureOffset("lPillar.b5", 0, 57);
        this.setTextureOffset("lPillar.b6", 0, 55);
        this.lPillar = new ModelRenderer((ModelBase)this, "lPillar");
        this.lPillar.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.setRotation(this.lPillar, 0.0f, 0.0f, 0.0f);
        this.lPillar.setTextureSize(64, 64);
        this.lPillar.addBox("b0", 2.0f, 22.0f, -1.0f, 6, 1, 2);
        this.lPillar.addBox("b1", 4.0f, 21.0f, -1.0f, 4, 1, 2);
        this.lPillar.addBox("b2", 6.0f, 19.0f, -1.0f, 2, 2, 2);
        this.lPillar.addBox("b3", 6.0f, 16.0f, -1.0f, 2, 2, 2);
        this.lPillar.addBox("b4", 6.0f, 13.0f, -1.0f, 2, 2, 2);
        this.lPillar.addBox("b5", 4.0f, 12.0f, -1.0f, 4, 1, 2);
        this.lPillar.addBox("b6", 2.0f, 11.0f, -1.0f, 6, 1, 2);
        this.setTextureOffset("topPlace.b0", 0, 0);
        this.setTextureOffset("topPlace.b1", 0, 0);
        this.setTextureOffset("topPlace.b2", 0, 0);
        this.setTextureOffset("topPlace.b3", 0, 0);
        this.setTextureOffset("topPlace.b4", 0, 0);
        this.setTextureOffset("topPlace.b5", 0, 0);
        this.setTextureOffset("topPlace.b6", 0, 0);
        this.setTextureOffset("topPlace.b7", 0, 0);
        this.setTextureOffset("topPlace.b8", 0, 0);
        this.setTextureOffset("topPlace.b9", 0, 0);
        this.setTextureOffset("topPlace.b10", 0, 0);
        this.setTextureOffset("topPlace.b11", 0, 0);
        this.setTextureOffset("topPlace.b12", 0, 0);
        this.setTextureOffset("topPlace.b13", 0, 0);
        this.setTextureOffset("topPlace.b14", 0, 0);
        this.setTextureOffset("topPlace.b15", 0, 0);
        this.setTextureOffset("topPlace.b16", 0, 0);
        this.setTextureOffset("topPlace.b17", 0, 0);
        this.setTextureOffset("topPlace.b18", 0, 0);
        this.setTextureOffset("topPlace.b19", 0, 0);
        this.setTextureOffset("topPlace.b20", 0, 0);
        this.topPlace = new ModelRenderer((ModelBase)this, "topPlace");
        this.topPlace.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.setRotation(this.topPlace, 0.0f, 0.0f, 0.0f);
        this.topPlace.setTextureSize(64, 64);
        this.topPlace.addBox("b0", 7.0f, 9.0f, -8.0f, 1, 14, 1);
        this.topPlace.addBox("b1", 7.0f, 8.0f, -8.0f, 1, 1, 1);
        this.topPlace.addBox("b2", 7.0f, 8.0f, -7.0f, 1, 1, 1);
        this.topPlace.addBox("b3", 6.0f, 8.0f, -8.0f, 1, 1, 1);
        this.topPlace.addBox("b4", 7.0f, 9.0f, 7.0f, 1, 14, 1);
        this.topPlace.addBox("b5", 7.0f, 8.0f, 7.0f, 1, 1, 1);
        this.topPlace.addBox("b6", 6.0f, 8.0f, 7.0f, 1, 1, 1);
        this.topPlace.addBox("b7", 7.0f, 8.0f, 6.0f, 1, 1, 1);
        this.topPlace.addBox("b8", -8.0f, 9.0f, 7.0f, 1, 14, 1);
        this.topPlace.addBox("b9", -8.0f, 8.0f, 7.0f, 1, 1, 1);
        this.topPlace.addBox("b10", -8.0f, 8.0f, 6.0f, 1, 1, 1);
        this.topPlace.addBox("b11", -7.0f, 8.0f, 7.0f, 1, 1, 1);
        this.topPlace.addBox("b12", -8.0f, 9.0f, -8.0f, 1, 14, 1);
        this.topPlace.addBox("b13", -8.0f, 8.0f, -8.0f, 1, 1, 1);
        this.topPlace.addBox("b14", -7.0f, 8.0f, -8.0f, 1, 1, 1);
        this.topPlace.addBox("b15", -8.0f, 8.0f, -7.0f, 1, 1, 1);
        this.topPlace.addBox("b16", 4.0f, 9.0f, -5.0f, 2, 1, 2);
        this.topPlace.addBox("b17", 4.0f, 9.0f, 4.0f, 2, 1, 2);
        this.topPlace.addBox("b18", -5.0f, 9.0f, 4.0f, 2, 1, 2);
        this.topPlace.addBox("b19", -5.0f, 9.0f, -5.0f, 2, 1, 2);
        this.topPlace.addBox("b20", -1.0f, 8.0f, -1.0f, 2, 1, 2);
        this.setTextureOffset("center.b0", 20, 30);
        this.setTextureOffset("center.b1", 30, 30);
        this.setTextureOffset("center.b2", 19, 35);
        this.setTextureOffset("center.b3", 23, 30);
        this.setTextureOffset("center.b4", 30, 40);
        this.setTextureOffset("center.b5", 20, 43);
        this.setTextureOffset("center.b6", 30, 43);
        this.setTextureOffset("center.b7", 19, 48);
        this.setTextureOffset("center.b8", 23, 43);
        this.setTextureOffset("center.b9", 30, 53);
        this.setTextureOffset("center.b10", 44, 17);
        this.setTextureOffset("center.b11", 54, 17);
        this.setTextureOffset("center.b12", 43, 22);
        this.setTextureOffset("center.b13", 47, 17);
        this.setTextureOffset("center.b14", 54, 27);
        this.setTextureOffset("center.b15", 44, 30);
        this.setTextureOffset("center.b16", 54, 30);
        this.setTextureOffset("center.b17", 43, 35);
        this.setTextureOffset("center.b18", 47, 30);
        this.setTextureOffset("center.b19", 54, 40);
        this.setTextureOffset("center.b20", 44, 43);
        this.setTextureOffset("center.b21", 54, 43);
        this.setTextureOffset("center.b22", 43, 48);
        this.setTextureOffset("center.b23", 47, 43);
        this.setTextureOffset("center.b24", 54, 53);
        this.center = new ModelRenderer((ModelBase)this, "center");
        this.center.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.setRotation(this.center, 0.0f, 0.0f, 0.0f);
        this.center.setTextureSize(64, 64);
        this.center.addBox("b0", -0.5f, 19.0f, -0.5f, 1, 1, 1);
        this.center.addBox("b1", -1.0f, 18.0f, -1.0f, 2, 1, 2);
        this.center.addBox("b2", -1.5f, 16.0f, -1.5f, 3, 2, 3);
        this.center.addBox("b3", -1.0f, 15.0f, -1.0f, 2, 1, 2);
        this.center.addBox("b4", -0.5f, 14.0f, -0.5f, 1, 1, 1);
        this.center.addBox("b5", -0.5f, 18.0f, -0.5f, 1, 1, 1);
        this.center.addBox("b6", -1.0f, 17.0f, -1.0f, 2, 1, 2);
        this.center.addBox("b7", -1.5f, 15.0f, -1.5f, 3, 2, 3);
        this.center.addBox("b8", -1.0f, 14.0f, -1.0f, 2, 1, 2);
        this.center.addBox("b9", -0.5f, 13.0f, -0.5f, 1, 1, 1);
        this.center.addBox("b10", -0.5f, 17.0f, -0.5f, 1, 1, 1);
        this.center.addBox("b11", -1.0f, 16.0f, -1.0f, 2, 1, 2);
        this.center.addBox("b12", -1.5f, 14.0f, -1.5f, 3, 2, 3);
        this.center.addBox("b13", -1.0f, 13.0f, -1.0f, 2, 1, 2);
        this.center.addBox("b14", -0.5f, 12.0f, -0.5f, 1, 1, 1);
        this.center.addBox("b15", -0.5f, 20.0f, -0.5f, 1, 1, 1);
        this.center.addBox("b16", -1.0f, 19.0f, -1.0f, 2, 1, 2);
        this.center.addBox("b17", -1.5f, 17.0f, -1.5f, 3, 2, 3);
        this.center.addBox("b18", -1.0f, 16.0f, -1.0f, 2, 1, 2);
        this.center.addBox("b19", -0.5f, 15.0f, -0.5f, 1, 1, 1);
        this.center.addBox("b20", -0.5f, 21.0f, -0.5f, 1, 1, 1);
        this.center.addBox("b21", -1.0f, 20.0f, -1.0f, 2, 1, 2);
        this.center.addBox("b22", -1.5f, 18.0f, -1.5f, 3, 2, 3);
        this.center.addBox("b23", -1.0f, 17.0f, -1.0f, 2, 1, 2);
        this.center.addBox("b24", -0.5f, 16.0f, -0.5f, 1, 1, 1);
    }

    public void render() {
        float f = 0.0625f;
        this.place.render(f);
        this.fPillar.render(f);
        this.bPillar.render(f);
        this.rPillar.render(f);
        this.lPillar.render(f);
        this.topPlace.render(f);
        this.center.render(f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
    }
}

