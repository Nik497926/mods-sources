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
package net.divinerpg.client.render.entity.vanilla;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.entities.vanilla.EntityHellPig;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(value=Side.CLIENT)
public class RenderHellPig
extends RenderLiving {
    public RenderHellPig(ModelBase par1ModelBase) {
        super(par1ModelBase, 0.0f);
    }

    protected ResourceLocation getEntityTexture(EntityHellPig pig) {
        return pig.isTamed() ? EntityResourceLocation.hellPigTame : (pig.isAngry() ? EntityResourceLocation.hellPigMad : EntityResourceLocation.hellPig);
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.getEntityTexture((EntityHellPig)par1Entity);
    }
}

