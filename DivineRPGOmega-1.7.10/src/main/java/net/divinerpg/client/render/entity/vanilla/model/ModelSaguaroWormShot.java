/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package net.divinerpg.client.render.entity.vanilla.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(value=Side.CLIENT)
public class ModelSaguaroWormShot
extends ModelBase {
    public ModelRenderer box = new ModelRenderer((ModelBase)this, 0, 0).setTextureSize(16, 16);

    public ModelSaguaroWormShot() {
        this.box.addBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.box.rotationPointX = 0.0f;
        this.box.rotationPointY = 0.0f;
        this.box.rotationPointZ = 0.0f;
    }

    public void renderAll() {
        this.box.render(0.0625f);
    }
}

