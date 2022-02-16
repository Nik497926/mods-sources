/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderBow
implements IItemRenderer {
    public static ResourceLocation texture = new ResourceLocation("%blur%/misc/glint.png");

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return type == IItemRenderer.ItemRenderType.EQUIPPED;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        EntityLivingBase e = (EntityLivingBase)data[1];
        GL11.glPopMatrix();
        boolean renderFirstPerson = false;
        if (e instanceof EntityPlayer && ((EntityPlayer)e).getCommandSenderName().equals(Minecraft.getMinecraft().thePlayer.getCommandSenderName()) && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
            renderFirstPerson = true;
        }
        float f22 = 0.375f;
        GL11.glRotatef((float)-20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)-60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)(1.0f / f22), (float)(1.0f / f22), (float)(1.0f / f22));
        GL11.glTranslatef((float)-0.25f, (float)-0.1875f, (float)0.1875f);
        float f2 = 0.625f;
        GL11.glTranslatef((float)0.0f, (float)0.125f, (float)0.3125f);
        GL11.glRotatef((float)-20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glScalef((float)f2, (float)(-f2), (float)f2);
        GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        this.renderItem(e, item, 0);
        GL11.glPushMatrix();
    }

    public void renderItem(EntityLivingBase par1EntityLiving, ItemStack par2ItemStack, int par3) {
        GL11.glPushMatrix();
        IIcon icon = par1EntityLiving.getItemIcon(par2ItemStack, par3);
        if (icon == null) {
            GL11.glPopMatrix();
            return;
        }
        Tessellator tess = Tessellator.instance;
        float f = icon.getMinU();
        float f1 = icon.getMaxU();
        float f2 = icon.getMinV();
        float f3 = icon.getMaxV();
        float f4 = 0.0f;
        float f5 = 0.3f;
        GL11.glEnable((int)32826);
        GL11.glTranslatef((float)(-f4), (float)(-f5), (float)0.0f);
        float f6 = 1.5f;
        GL11.glScalef((float)f6, (float)f6, (float)f6);
        GL11.glRotatef((float)50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)335.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)-0.9375f, (float)-0.0625f, (float)0.0f);
        ItemRenderer.renderItemIn2D((Tessellator)tess, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.getIconWidth(), (int)icon.getIconHeight(), (float)0.0625f);
        if (par2ItemStack != null && par2ItemStack.hasEffect(1) && par3 == 0) {
            GL11.glDepthFunc((int)514);
            GL11.glDisable((int)2896);
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)768, (int)1);
            float f7 = 0.76f;
            GL11.glColor4f((float)(0.5f * f7), (float)(0.25f * f7), (float)(0.8f * f7), (float)1.0f);
            GL11.glMatrixMode((int)5890);
            GL11.glPushMatrix();
            float f8 = 0.125f;
            GL11.glScalef((float)f8, (float)f8, (float)f8);
            float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0f * 8.0f;
            GL11.glTranslatef((float)f9, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-50.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ItemRenderer.renderItemIn2D((Tessellator)tess, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)256, (int)256, (float)0.0625f);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)f8, (float)f8, (float)f8);
            f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0f * 8.0f;
            GL11.glTranslatef((float)(-f9), (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ItemRenderer.renderItemIn2D((Tessellator)tess, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)256, (int)256, (float)0.0625f);
            GL11.glPopMatrix();
            GL11.glMatrixMode((int)5888);
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glDepthFunc((int)515);
            GL11.glDisable((int)32826);
        }
        GL11.glPopMatrix();
    }
}

