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
package net.divinerpg.client.render.entity.iceika;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.client.render.entity.iceika.model.ModelFrosty;
import net.divinerpg.entities.iceika.EntityFrosty;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(value=Side.CLIENT)
public class RenderFrosty
extends RenderLiving {
    public RenderFrosty() {
        super((ModelBase)new ModelFrosty(), 1.0f);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof EntityFrosty && ((EntityFrosty)entity).angerLevel > 0) {
            return EntityResourceLocation.FROSTY_ANGRY;
        }
        return EntityResourceLocation.FROSTY;
    }
}

