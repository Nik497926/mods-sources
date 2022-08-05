/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.tile;

import com.meteor.extrabotany.ExtraBotany;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.handler.MultiblockRenderHandler;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.client.model.IPylonModel;
import vazkii.botania.client.model.ModelPylon;
import vazkii.botania.client.model.ModelPylonOld;
import vazkii.botania.common.core.handler.ConfigHandler;

@SideOnly(value= Side.CLIENT)
public class RenderTileExPylon
extends TileEntitySpecialRenderer {
    private static final ResourceLocation textureOld = new ResourceLocation(ExtraBotany.modid, "textures/blocks/models/pylon4.png");
    private static final ResourceLocation texture = new ResourceLocation(ExtraBotany.modid, "textures/blocks/models/pylon4.png");
    private static final ResourceLocation textureGreenOld = new ResourceLocation(ExtraBotany.modid, "textures/blocks/models/pylon5.png");
    private static final ResourceLocation textureGreen = new ResourceLocation(ExtraBotany.modid, "textures/blocks/models/pylon5.png");
    private static final ResourceLocation texturePinkOld = new ResourceLocation("botania:textures/model/pylonOld2.png");
    private static final ResourceLocation texturePink = new ResourceLocation("botania:textures/model/pylon2.png");
    IPylonModel model;
    public static boolean green = false;
    public static boolean pink = false;
    public static boolean renderNew = true;

    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float pticks) {
        double worldTime;
        if (this.model == null) {
            this.model = !renderNew ? new ModelPylonOld() : new ModelPylon();
        }
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        float a = MultiblockRenderHandler.rendering ? 0.6f : 1.0f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
        if (tileentity.getWorldObj() != null) {
            green = tileentity.getBlockMetadata() == 1;
            boolean bl = pink = tileentity.getBlockMetadata() == 2;
        }
        if (!renderNew) {
            Minecraft.getMinecraft().renderEngine.bindTexture(pink ? texturePinkOld : (green ? textureGreenOld : textureOld));
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(pink ? texturePink : (green ? textureGreen : texture));
        }
        double d = worldTime = tileentity.getWorldObj() == null ? 0.0 : (double)((float)ClientTickHandler.ticksInGame + pticks);
        if (tileentity != null) {
            worldTime += (double)new Random(tileentity.xCoord ^ tileentity.yCoord ^ tileentity.zCoord).nextInt(360);
        }
        if (!renderNew) {
            GL11.glTranslated((double)(d0 + 0.5), (double)(d1 + 2.2), (double)(d2 + 0.5));
            GL11.glScalef((float)1.0f, (float)-1.5f, (float)-1.0f);
        } else {
            GL11.glTranslated((double)(d0 + 0.2 + (green ? -0.1 : 0.0)), (double)(d1 + 0.05), (double)(d2 + 0.8 + (green ? 0.1 : 0.0)));
            float scale = green ? 0.8f : 0.6f;
            GL11.glScalef((float)scale, (float)0.6f, (float)scale);
        }
        if (!green) {
            GL11.glPushMatrix();
            if (renderNew) {
                GL11.glTranslatef((float)0.5f, (float)0.0f, (float)-0.5f);
            }
            GL11.glRotatef((float)((float)worldTime * 1.5f), (float)0.0f, (float)1.0f, (float)0.0f);
            if (renderNew) {
                GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)0.5f);
            }
            this.model.renderRing();
            GL11.glTranslated((double)0.0, (double)(Math.sin(worldTime / 20.0) / 20.0 - 0.025), (double)0.0);
            this.model.renderGems();
            GL11.glPopMatrix();
        }
        GL11.glPushMatrix();
        GL11.glTranslated((double)0.0, (double)(Math.sin(worldTime / 20.0) / 17.5), (double)0.0);
        if (renderNew) {
            GL11.glTranslatef((float)0.5f, (float)0.0f, (float)-0.5f);
        }
        GL11.glRotatef((float)((float)(-worldTime)), (float)0.0f, (float)1.0f, (float)0.0f);
        if (renderNew) {
            GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)0.5f);
        }
        GL11.glDisable((int)2884);
        this.model.renderCrystal();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
        if (!ShaderHelper.useShaders()) {
            int light = 0xF000F0;
            int lightmapX = light % 65536;
            int lightmapY = light / 65536;
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)lightmapX, (float)lightmapY);
            float alpha = (float)((Math.sin(worldTime / 20.0) / 2.0 + 0.5) / (ConfigHandler.oldPylonModel ? 1.0 : 2.0));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(a * (alpha + 0.183f)));
        }
        GL11.glDisable((int)3008);
        GL11.glScalef((float)1.1f, (float)1.1f, (float)1.1f);
        if (renderNew) {
            GL11.glTranslatef((float)-0.05f, (float)-0.1f, (float)0.05f);
        } else {
            GL11.glTranslatef((float)0.0f, (float)-0.09f, (float)0.0f);
        }
        ShaderHelper.useShader((int)ShaderHelper.pylonGlow);
        this.model.renderCrystal();
        ShaderHelper.releaseShader();
        GL11.glEnable((int)3008);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }
}

