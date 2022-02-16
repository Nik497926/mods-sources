/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelHat
extends ModelBase {
    public ModelRenderer top = new ModelRenderer((ModelBase)this, 0, 0).setTextureSize(128, 128);
    public ModelRenderer bottom;

    public ModelHat() {
        this.top.addBox(4.0f, 1.0f, 4.0f, 8, 8, 8, 0.0f);
        this.bottom = new ModelRenderer((ModelBase)this, 32, 0).setTextureSize(128, 128);
        this.bottom.addBox(2.0f, 0.0f, 2.0f, 12, 2, 12, 0.0f);
    }

    public void renderAll() {
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)-1.0f, (float)-0.6f, (float)0.0f);
        this.top.render(0.0625f);
        GL11.glPopMatrix();
        this.bottom.render(0.0625f);
    }
}

