/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render;

import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import com.meteor.extrabotany.common.item.equipment.shield.ItemShieldGeneratorBase;
import com.meteor.extrabotany.common.lib.LibReference;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.helper.ShaderHelper;

@SideOnly(value=Side.CLIENT)
public class RenderShield {
    public static final ResourceLocation shieldBar = LibReference.BAR_SHIELD;
    private static ResourceLocation textureShield = LibReference.SHIELD;
    private static ResourceLocation textureDurance = LibReference.POTION_DURANCE;
    private static ResourceLocation customScope = LibReference.CUSTOM_SCOPE;
    int cx = ConfigHandler.shieldDisplayX;
    int cy = ConfigHandler.shieldDisplayY;
    double zLevel = 0.0;

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onDrawScreenPre(RenderGameOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getMinecraft();
        Profiler profiler = mc.mcProfiler;
        if (event.type == RenderGameOverlayEvent.ElementType.HEALTH) {
            profiler.startSection("extrabotania-HUD");
            profiler.endSection();
        }
    }

    @SubscribeEvent
    public void onDrawScreenPost(RenderGameOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getMinecraft();
        Profiler profiler = mc.mcProfiler;
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            profiler.startSection("extrabotania-HUD");
            if (ItemShieldGeneratorBase.getShieldGenerator((EntityPlayer)mc.thePlayer) != null) {
                profiler.startSection("shieldBar");
                if (!ConfigHandler.anotherShieldRender) {
                    this.renderShieldB(event.resolution);
                } else {
                    this.renderShield(event.resolution);
                }
                profiler.endSection();
            }
            profiler.endSection();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    private void renderShieldB(ScaledResolution res) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int scaledWidth = scaledresolution.getScaledWidth();
        int scaledHeight = scaledresolution.getScaledHeight();
        mc.getTextureManager().bindTexture(shieldBar);
        float s = ItemShieldGeneratorBase.getShieldAmount((EntityPlayer)mc.thePlayer) / 2.0f;
        boolean highlight = ItemShieldGeneratorBase.getShieldCD((EntityPlayer)mc.thePlayer) > 0;
        this.drawTexturedModalRect(scaledWidth / 2 - 121 + this.cx, scaledHeight - 19 + this.cy, 0, highlight ? 9 : 0, 9, 9);
        mc.fontRenderer.drawStringWithShadow("x" + (int)(s * 2.0f) / 2, scaledWidth / 2 - 110 + this.cx, scaledHeight - 19 + this.cy, 0xFFFFFF);
    }

    private void renderShield(ScaledResolution res) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int scaledWidth = scaledresolution.getScaledWidth();
        int scaledHeight = scaledresolution.getScaledHeight();
        int xBasePos = scaledWidth / 2 - 91 + this.cx;
        int yBasePos = scaledHeight - 39 + this.cy;
        boolean highlight = ItemShieldGeneratorBase.getShieldCD((EntityPlayer)mc.thePlayer) > 0;
        mc.getTextureManager().bindTexture(shieldBar);
        float s1 = ItemShieldGeneratorBase.getShieldAmount((EntityPlayer)mc.thePlayer);
        float ss1 = s1 / 2.0f;
        int r = 0;
        while ((float)r < s1 / 20.0f) {
            int ra = r * 10;
            if (s1 > 0.0f) {
                int i = 0;
                while ((float)i < Math.min(ss1 - (float)ra, 10.0f)) {
                    if (s1 > (float)(1 + ra * 2)) {
                        this.drawTexturedModalRect(xBasePos + 8 * Math.min(ss1 % 1.0f == 0.0f ? i : (ss1 > (float)(10 + ra) ? i : Math.max(i - 1, 0)), 9), yBasePos - ra, 0, highlight ? 9 : 0, 9, 9);
                    }
                    if (ss1 % 1.0f != 0.0f && ss1 <= (float)(10 + ra)) {
                        this.drawTexturedModalRect(xBasePos + 8 * Math.min(i, 9), yBasePos - ra, 9, highlight ? 9 : 0, 9, 9);
                    }
                    ++i;
                }
            }
            ++r;
        }
    }

    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), this.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), this.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), this.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), this.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
        tessellator.draw();
    }

    public static void translateToFootLevel(EntityPlayer player) {
        GL11.glTranslated((double)0.0, (double)2.0, (double)0.0);
    }

    @SideOnly(value=Side.CLIENT)
    public static void renderShield(Entity player, float partialTicks, Boolean def) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glShadeModel((int)7425);
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2884);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(def != false ? textureShield : textureDurance);
        if (player != null && player instanceof EntityPlayer) {
            RenderShield.translateToFootLevel((EntityPlayer)player);
        }
        if (!def.booleanValue()) {
            GL11.glTranslatef((float)0.0f, (float)-1.5f, (float)0.0f);
        } else {
            GL11.glTranslatef((float)0.0f, (float)-0.5f, (float)0.0f);
        }
        GL11.glScalef((float)1.3f, (float)1.3f, (float)1.3f);
        Tessellator tes = Tessellator.instance;
        ShaderHelper.useShader((int)ShaderHelper.halo);
        tes.startDrawingQuads();
        tes.setColorRGBA_F(0.0f, 0.0f, 0.0f, 0.5f);
        tes.addVertexWithUV(-0.85, 0.0, -0.85, 0.0, 0.0);
        tes.addVertexWithUV(-0.85, 0.0, 0.85, 0.0, 1.0);
        tes.addVertexWithUV(0.85, 0.0, 0.85, 1.0, 1.0);
        tes.addVertexWithUV(0.85, 0.0, -0.85, 1.0, 0.0);
        tes.draw();
        ShaderHelper.releaseShader();
        GL11.glEnable((int)2896);
        GL11.glShadeModel((int)7424);
        GL11.glEnable((int)2884);
    }

    @SideOnly(value=Side.CLIENT)
    public static void renderShield(Entity player, float partialTicks, Boolean def, Boolean cus) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glShadeModel((int)7425);
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2884);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (cus.booleanValue()) {
            Minecraft.getMinecraft().renderEngine.bindTexture(customScope);
        }
        if (player != null && player instanceof EntityPlayer) {
            RenderShield.translateToFootLevel((EntityPlayer)player);
        }
        GL11.glTranslatef((float)0.0f, (float)-1.5f, (float)0.0f);
        GL11.glScalef((float)1.3f, (float)1.3f, (float)1.3f);
        Tessellator tes = Tessellator.instance;
        ShaderHelper.useShader((int)ShaderHelper.halo);
        tes.startDrawingQuads();
        tes.setColorRGBA_F(0.0f, 0.0f, 0.0f, 0.5f);
        tes.addVertexWithUV(-0.85, 0.0, -0.85, 0.0, 0.0);
        tes.addVertexWithUV(-0.85, 0.0, 0.85, 0.0, 1.0);
        tes.addVertexWithUV(0.85, 0.0, 0.85, 1.0, 1.0);
        tes.addVertexWithUV(0.85, 0.0, -0.85, 1.0, 0.0);
        tes.draw();
        ShaderHelper.releaseShader();
        GL11.glEnable((int)2896);
        GL11.glShadeModel((int)7424);
        GL11.glEnable((int)2884);
    }

    @SideOnly(value=Side.CLIENT)
    public static void renderShield2(Entity player, float partialTicks, ResourceLocation res) {
        if (res == null) {
            return;
        }
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glShadeModel((int)7425);
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2884);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(res);
        if (player != null && player instanceof EntityPlayer) {
            RenderShield.translateToFootLevel((EntityPlayer)player);
        }
        GL11.glTranslatef((float)0.0f, (float)-0.5f, (float)0.0f);
        GL11.glScalef((float)1.3f, (float)1.3f, (float)1.3f);
        Tessellator tes = Tessellator.instance;
        ShaderHelper.useShader((int)ShaderHelper.halo);
        tes.startDrawingQuads();
        tes.addVertexWithUV(-0.85, 0.0, -0.85, 0.0, 0.0);
        tes.addVertexWithUV(-0.85, 0.0, 0.85, 0.0, 1.0);
        tes.addVertexWithUV(0.85, 0.0, 0.85, 1.0, 1.0);
        tes.addVertexWithUV(0.85, 0.0, -0.85, 1.0, 0.0);
        tes.draw();
        ShaderHelper.releaseShader();
        GL11.glEnable((int)2896);
        GL11.glShadeModel((int)7424);
        GL11.glEnable((int)2884);
    }
}

