/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.entity.RenderExMachine;

import com.meteor.extrabotany.client.model.ModelExMachine;
import com.meteor.extrabotany.client.render.RenderShield;
import com.meteor.extrabotany.common.entity.EntityExMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.boss.IBotaniaBoss;
import vazkii.botania.client.core.handler.BossBarHandler;

@SideOnly(value=Side.CLIENT)
public class RenderExMachine
extends RenderLiving {
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("extrabotania", "textures/entities/exmachine.png");

    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE_URL;
    }

    public RenderExMachine() {
        super((ModelBase)new ModelExMachine(), 0.5f);
    }

    protected void rotateExMachineCorpse(EntityExMachine entity, float par2, float par3, float par4) {
        super.rotateCorpse((EntityLivingBase)entity, par2, par3, par4);
    }

    public void doRender(EntityLiving entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderExMachine((EntityExMachine)entity, par2, par4, par6, par8, par9);
    }

    protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        this.rotateExMachineCorpse((EntityExMachine)par1EntityLivingBase, par2, par3, par4);
    }

    public void doRender(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderExMachine((EntityExMachine)par1, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderExMachine((EntityExMachine)entity, par2, par4, par6, par8, par9);
    }

    public void doRenderExMachine(EntityExMachine par1Entity, double par2, double par4, double par6, float par8, float par9) {
        EntityExMachine dopple = par1Entity;
        BossBarHandler.setCurrentBoss((IBotaniaBoss)dopple);
        if (dopple.getStage() == 11) {
            List<EntityPlayer> pl = dopple.getPlayersAround();
            for (EntityPlayer pls : pl) {
                RenderShield.renderShield((Entity)pls, 1.0f, false);
            }
        } else if (dopple.getStage() == 12) {
            List<EntityPlayer> pl = dopple.getPlayersAround();
            for (EntityPlayer pls : pl) {
                RenderShield.renderShield((Entity)pls, 1.0f, false, true);
            }
        }
        super.doRender((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
    }
}

