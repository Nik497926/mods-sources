/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.MinecraftForgeClient
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.entity.twilight;

import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.client.render.entity.twilight.model.ModelSunArcher;
import net.divinerpg.items.base.ItemModBow;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

public class RenderSunArcher
extends RenderLiving {
    private float scale;
    public int bossTextureID;

    public RenderSunArcher() {
        super((ModelBase)new ModelSunArcher(), 0.25f);
    }

    protected void renderEquippedItems(EntityLivingBase var1, float var2) {
        super.renderEquippedItems(var1, var2);
        ItemStack var3 = var1.getHeldItem();
        if (var3 != null) {
            GL11.glPushMatrix();
            ((ModelSunArcher)this.mainModel).armRight.postRender(0.0625f);
            GL11.glTranslatef((float)-0.0625f, (float)0.4375f, (float)0.0625f);
            IItemRenderer var5 = MinecraftForgeClient.getItemRenderer((ItemStack)var3, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            boolean var6 = var5 != null && var5.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, var3, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            float var4 = 0.38f;
            GL11.glTranslatef((float)0.05f, (float)0.3f, (float)-0.25f);
            GL11.glScalef((float)var4, (float)(-var4), (float)var4);
            if (var3.getItem() instanceof ItemModBow) {
                GL11.glRotatef((float)120.0f, (float)-0.3f, (float)1.0f, (float)-1.2f);
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

    protected ResourceLocation getEntityTexture(Entity e) {
        return EntityResourceLocation.sunArcher;
    }
}

