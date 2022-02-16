/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package net.divinerpg.client.model;

import net.divinerpg.client.model.DivineModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class DivineRenderer
extends ModelRenderer {
    public DivineRenderer(DivineModel modelBase, int xOffset, int yOffset) {
        super((ModelBase)modelBase, xOffset, yOffset);
        modelBase.addPart(this);
    }
}

