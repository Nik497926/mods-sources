/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class GunRenderer
implements IItemRenderer {
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private TextureManager texturemanager;

    public GunRenderer(GameSettings gameSettings, TextureManager textureManager) {
        this.texturemanager = textureManager;
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return type == IItemRenderer.ItemRenderType.ENTITY || type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON || type == IItemRenderer.ItemRenderType.INVENTORY;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return type == IItemRenderer.ItemRenderType.ENTITY && (helper == IItemRenderer.ItemRendererHelper.ENTITY_ROTATION || helper == IItemRenderer.ItemRendererHelper.ENTITY_BOBBING);
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            float scale = 2.5f;
            GL11.glScalef((float)scale, (float)scale, (float)scale);
            GL11.glTranslatef((float)0.55f, (float)-0.2f, (float)0.15f);
            if (item.getItemUseAction() == EnumAction.bow) {
                GL11.glRotatef((float)35.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            IIcon entityItem = ((EntityLivingBase)data[1]).getItemIcon(item, 0);
            this.renderItemEquipped(entityItem, item);
        } else if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
            float scale = 1.5f;
            GL11.glScalef((float)scale, (float)scale, (float)scale);
            GL11.glTranslatef((float)0.345f, (float)-0.1f, (float)0.0f);
            if (item.getItemUseAction() == EnumAction.bow) {
                GL11.glRotatef((float)35.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            IIcon entityItem = ((EntityLivingBase)data[1]).getItemIcon(item, 0);
            this.renderItemEquipped(entityItem, item);
        } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
            float scale = 1.5f;
            GL11.glScalef((float)scale, (float)scale, (float)scale);
            GL11.glTranslatef((float)0.0f, (float)0.15f, (float)0.0f);
            EntityItem entityItem1 = (EntityItem)data[1];
            this.renderDroppedItem(entityItem1, item);
        } else if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            this.renderInventoryItem(item, (RenderBlocks)data[0]);
        }
    }

    private void renderInventoryItem(ItemStack itemStack, RenderBlocks renderBlocks) {
        IIcon iicon = itemStack.getItem().getIcon(itemStack, -1);
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3008);
        RenderItem.getInstance().renderIcon(0, 0, iicon, 16, 16);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)2896);
        if (itemStack.hasEffect(0)) {
            RenderItem.getInstance().renderEffect(this.texturemanager, 0, 0);
        }
        GL11.glEnable((int)2896);
    }

    private void renderItemEquipped(IIcon iicon, ItemStack par2ItemStack) {
        int par3 = 0;
        if (iicon == null) {
            GL11.glPopMatrix();
        } else {
            this.texturemanager.getTexture(this.texturemanager.getResourceLocation(par2ItemStack.getItemSpriteNumber()));
            TextureUtil.func_152777_a((boolean)false, (boolean)false, (float)1.0f);
            Tessellator tessellator = Tessellator.instance;
            float f = iicon.getMinU();
            float f1 = iicon.getMaxU();
            float f2 = iicon.getMinV();
            float f3 = iicon.getMaxV();
            ItemRenderer.renderItemIn2D((Tessellator)tessellator, (float)f1, (float)f2, (float)f, (float)f3, (int)iicon.getIconWidth(), (int)iicon.getIconHeight(), (float)0.0625f);
            if (par2ItemStack.hasEffect(par3)) {
                GL11.glDepthFunc((int)514);
                GL11.glDisable((int)2896);
                this.texturemanager.bindTexture(RES_ITEM_GLINT);
                GL11.glEnable((int)3042);
                OpenGlHelper.glBlendFunc((int)768, (int)1, (int)1, (int)0);
                float f7 = 0.76f;
                GL11.glColor4f((float)(0.5f * f7), (float)(0.25f * f7), (float)(0.8f * f7), (float)1.0f);
                GL11.glMatrixMode((int)5890);
                GL11.glPushMatrix();
                float f8 = 0.125f;
                GL11.glScalef((float)f8, (float)f8, (float)f8);
                float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0f * 8.0f;
                GL11.glTranslatef((float)f9, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)-50.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                ItemRenderer.renderItemIn2D((Tessellator)tessellator, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)256, (int)256, (float)0.0625f);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef((float)f8, (float)f8, (float)f8);
                f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0f * 8.0f;
                GL11.glTranslatef((float)(-f9), (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                ItemRenderer.renderItemIn2D((Tessellator)tessellator, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)256, (int)256, (float)0.0625f);
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5888);
                GL11.glDisable((int)3042);
                GL11.glEnable((int)2896);
                GL11.glDepthFunc((int)515);
            }
            this.texturemanager.getTexture(this.texturemanager.getResourceLocation(par2ItemStack.getItemSpriteNumber()));
            TextureUtil.func_147945_b();
        }
    }

    private void renderDroppedItem(EntityItem entityItem, ItemStack item) {
        Tessellator tessellator = Tessellator.instance;
        float f9 = 0.5f;
        float f10 = 0.25f;
        GL11.glPushMatrix();
        float f12 = 0.0625f;
        float f11 = 0.021875f;
        GL11.glTranslatef((float)(-f9), (float)(-f10), (float)(-(f12 + f11)));
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(f12 + f11));
        this.texturemanager.getTexture(TextureMap.locationItemsTexture);
        IIcon par2Icon = item.getIconIndex();
        ItemRenderer.renderItemIn2D((Tessellator)tessellator, (float)par2Icon.getMaxU(), (float)par2Icon.getMinV(), (float)par2Icon.getMinU(), (float)par2Icon.getMaxV(), (int)par2Icon.getIconWidth(), (int)par2Icon.getIconHeight(), (float)f12);
        GL11.glPopMatrix();
    }
}

