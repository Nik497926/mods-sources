/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package net.divinerpg.client.render.entity.vethea;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.client.render.EntityResourceLocation;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(value=Side.CLIENT)
public class RenderGalroid
extends RenderLiving {
    public RenderGalroid(ModelBase var1, float var2) {
        super(var1, var2);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity.isEntityInvulnerable()) {
            return EntityResourceLocation.GALROID_INVULNERABLE;
        }
        return EntityResourceLocation.GALROID;
    }
}

