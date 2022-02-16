/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.MinecraftForgeClient
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.entity.twilight;

import net.divinerpg.client.render.RenderLivingCreature;
import net.divinerpg.client.render.entity.twilight.model.ModelDensos;
import net.divinerpg.entities.base.DivineBossStatus;
import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.items.base.ItemModBow;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

public class RenderDAR
extends RenderLivingCreature {
    private float scale;
    public int bossTextureID;

    public RenderDAR(ModelBase var1, float var2, ResourceLocation texture, int id) {
        this(var1, var2, 1.0f, texture, id);
    }

    public RenderDAR(ModelBase var1, float var2, float var3, ResourceLocation texture, int id) {
        super(var1, var2 * var3, texture);
        this.bossTextureID = id;
        this.scale = var3;
    }

    public void preRenderScale(EntityMob var1, float var2) {
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
    }

    protected void preRenderCallback(EntityLivingBase var1, float var2) {
        this.preRenderScale((EntityDivineRPGBoss)var1, var2);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        super.doRender(par1Entity, par2, par4, par6, par8, par9);
        DivineBossStatus.setBossStatus((EntityDivineRPGBoss)par1Entity, this.bossTextureID);
    }

    protected void renderEquippedItems(EntityLivingBase var1, float var2) {
        super.renderEquippedItems(var1, var2);
        ItemStack var3 = var1.getHeldItem();
        if (var3 != null) {
            GL11.glPushMatrix();
            ((ModelDensos)this.mainModel).rightarm.postRender(0.0625f);
            GL11.glTranslatef((float)-0.0625f, (float)0.4375f, (float)0.0625f);
            IItemRenderer var5 = MinecraftForgeClient.getItemRenderer((ItemStack)var3, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            boolean var6 = var5 != null && var5.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, var3, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            float var4 = 0.5f;
            GL11.glTranslatef((float)-0.1f, (float)0.2f, (float)-0.4f);
            GL11.glScalef((float)var4, (float)(-var4), (float)var4);
            if (var3.getItem() instanceof ItemModBow) {
                GL11.glRotatef((float)120.0f, (float)0.0f, (float)1.0f, (float)-0.6f);
            } else {
                GL11.glRotatef((float)-40.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)0.5f, (float)-0.4f, (float)0.0f);
            }
            this.renderManager.itemRenderer.renderItem(var1, var3, 0);
            if (var3.getItem().requiresMultipleRenderPasses()) {
                for (int var7 = 1; var7 < var3.getItem().getRenderPasses(var3.getItemDamage()); ++var7) {
                    this.renderManager.itemRenderer.renderItem(var1, var3, var7);
                }
            }
            GL11.glPopMatrix();
        }
    }
}

