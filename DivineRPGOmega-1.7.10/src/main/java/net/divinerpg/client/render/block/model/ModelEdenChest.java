/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package net.divinerpg.client.render.block.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(value=Side.CLIENT)
public class ModelEdenChest
extends ModelBase {
    public ModelRenderer chestLid = new ModelRenderer((ModelBase)this, 0, 0).setTextureSize(64, 64);
    public ModelRenderer chestBelow;
    public ModelRenderer chestKnobLeft;
    public ModelRenderer chestKnobMiddle;
    public ModelRenderer chestKnobRight;

    public ModelEdenChest() {
        this.chestLid.addBox(0.0f, -5.0f, -14.0f, 14, 5, 14, 0.0f);
        this.chestLid.rotationPointX = 1.0f;
        this.chestLid.rotationPointY = 7.0f;
        this.chestLid.rotationPointZ = 15.0f;
        this.chestKnobLeft = new ModelRenderer((ModelBase)this, 0, 0).setTextureSize(64, 64);
        this.chestKnobLeft.addBox(-5.0f, -2.0f, -15.0f, 2, 4, 1, 0.0f);
        this.chestKnobLeft.rotationPointX = 8.0f;
        this.chestKnobLeft.rotationPointY = 7.0f;
        this.chestKnobLeft.rotationPointZ = 15.0f;
        this.chestKnobMiddle = new ModelRenderer((ModelBase)this, 0, 0).setTextureSize(64, 64);
        this.chestKnobMiddle.addBox(-1.0f, -2.0f, -15.0f, 2, 4, 1, 0.0f);
        this.chestKnobMiddle.rotationPointX = 8.0f;
        this.chestKnobMiddle.rotationPointY = 7.0f;
        this.chestKnobMiddle.rotationPointZ = 15.0f;
        this.chestKnobRight = new ModelRenderer((ModelBase)this, 0, 0).setTextureSize(64, 64);
        this.chestKnobRight.addBox(3.0f, -2.0f, -15.0f, 2, 4, 1, 0.0f);
        this.chestKnobRight.rotationPointX = 8.0f;
        this.chestKnobRight.rotationPointY = 7.0f;
        this.chestKnobRight.rotationPointZ = 15.0f;
        this.chestBelow = new ModelRenderer((ModelBase)this, 0, 19).setTextureSize(64, 64);
        this.chestBelow.addBox(0.0f, 0.0f, 0.0f, 14, 10, 14, 0.0f);
        this.chestBelow.rotationPointX = 1.0f;
        this.chestBelow.rotationPointY = 6.0f;
        this.chestBelow.rotationPointZ = 1.0f;
    }

    public void renderAll() {
        this.chestKnobMiddle.rotateAngleX = this.chestKnobLeft.rotateAngleX = this.chestLid.rotateAngleX;
        this.chestKnobRight.rotateAngleX = this.chestKnobLeft.rotateAngleX;
        this.chestLid.render(0.0625f);
        this.chestKnobLeft.render(0.0625f);
        this.chestKnobMiddle.render(0.0625f);
        this.chestKnobRight.render(0.0625f);
        this.chestBelow.render(0.0625f);
    }
}

