/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.tile;

import com.meteor.extrabotany.common.block.BlockElfPool;
import com.meteor.extrabotany.common.block.tile.TileElfPool;
import cpw.mods.fml.client.registry.RenderingRegistry;
import java.awt.Color;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.mana.IPoolOverlayProvider;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.handler.MultiblockRenderHandler;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.client.model.ModelPool;

public class RenderTileElfPool
extends TileEntitySpecialRenderer {
    public static final int ID = RenderingRegistry.getNextAvailableRenderId();
    private static final ResourceLocation texture = new ResourceLocation("extrabotania:textures/models/pool.png");
    private static final ResourceLocation textureInf = new ResourceLocation("extrabotania:textures/models/pool.png");
    private static final ResourceLocation textureDil = new ResourceLocation("extrabotania:textures/models/pool.png");
    private static final ModelPool model = new ModelPool();
    RenderItem renderItem = new RenderItem();
    public static int forceMeta = 0;
    public static boolean forceMana = false;
    public static int forceManaNumber = -1;

    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
        IIcon overlay;
        Block below;
        int cap;
        boolean dil = false;
        boolean inf = false;
        TileElfPool pool = (TileElfPool)tileentity;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)32826);
        float a = MultiblockRenderHandler.rendering ? 0.6f : 1.0f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        boolean bl = tileentity.getWorldObj() == null ? forceMeta == 1 : (inf = tileentity.getBlockMetadata() == 1);
        boolean bl2 = tileentity.getWorldObj() == null ? forceMeta == 2 : (dil = tileentity.getBlockMetadata() == 2);
        boolean fab = tileentity.getWorldObj() == null ? forceMeta == 3 : tileentity.getBlockMetadata() == 3;
        Minecraft.getMinecraft().renderEngine.bindTexture(inf ? textureInf : (dil ? textureDil : texture));
        GL11.glTranslatef((float)0.5f, (float)1.5f, (float)0.5f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        if (fab) {
            float time = (float)ClientTickHandler.ticksInGame + ClientTickHandler.partialTicks;
            if (tileentity != null) {
                time += (float)new Random(tileentity.xCoord ^ tileentity.yCoord ^ tileentity.zCoord).nextInt(100000);
            }
            Color color = Color.getHSBColor(time * 0.005f, 0.6f, 1.0f);
            GL11.glColor4ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()), (byte)-1);
        } else {
            int color = pool.color;
            float[] acolor = EntitySheep.fleeceColorTable[color];
            GL11.glColor4f((float)acolor[0], (float)acolor[1], (float)acolor[2], (float)a);
        }
        model.render();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glEnable((int)32826);
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        int mana = pool.getCurrentMana();
        if (forceManaNumber > -1) {
            mana = forceManaNumber;
        }
        if ((cap = pool.manaCap) == -1) {
            cap = 15000000;
        }
        float waterLevel = (float)mana / (float)cap * 0.4f;
        if (forceMana) {
            waterLevel = 0.4f;
        }
        float s = 0.0625f;
        float v = 0.125f;
        float w = -v * 3.5f;
        if (pool.getWorldObj() != null && (below = pool.getWorldObj().getBlock(pool.xCoord, pool.yCoord - 1, pool.zCoord)) instanceof IPoolOverlayProvider && (overlay = ((IPoolOverlayProvider)below).getIcon(pool.getWorldObj(), pool.xCoord, pool.yCoord - 1, pool.zCoord)) != null) {
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glDisable((int)3008);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(a * (float)((Math.sin((double)((float)ClientTickHandler.ticksInGame + f) / 20.0) + 1.0) * 0.3 + 0.2)));
            GL11.glTranslatef((float)-0.5f, (float)-1.4300001f, (float)-0.5f);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScalef((float)s, (float)s, (float)s);
            this.renderIcon(0, 0, overlay, 16, 16, 240);
            GL11.glEnable((int)3008);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
        if (waterLevel > 0.0f) {
            s = 0.0546875f;
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glDisable((int)3008);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
            GL11.glTranslatef((float)w, (float)(-1.0f - (0.43f - waterLevel)), (float)w);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScalef((float)s, (float)s, (float)s);
            ShaderHelper.useShader((int)ShaderHelper.manaPool);
            this.renderIcon(0, 0, BlockElfPool.manaIcon, 16, 16, 240);
            ShaderHelper.releaseShader();
            GL11.glEnable((int)3008);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        forceMeta = 0;
        forceMana = false;
        forceManaNumber = -1;
    }

    public void renderIcon(int par1, int par2, IIcon par3Icon, int par4, int par5, int brightness) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setBrightness(brightness);
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par5), 0.0, (double)par3Icon.getMinU(), (double)par3Icon.getMaxV());
        tessellator.addVertexWithUV((double)(par1 + par4), (double)(par2 + par5), 0.0, (double)par3Icon.getMaxU(), (double)par3Icon.getMaxV());
        tessellator.addVertexWithUV((double)(par1 + par4), (double)(par2 + 0), 0.0, (double)par3Icon.getMaxU(), (double)par3Icon.getMinV());
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), 0.0, (double)par3Icon.getMinU(), (double)par3Icon.getMinV());
        tessellator.draw();
    }
}

