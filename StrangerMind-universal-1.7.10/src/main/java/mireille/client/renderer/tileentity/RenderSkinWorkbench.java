package mireille.client.renderer.tileentity;

import net.minecraft.client.renderer.tileentity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import mireille.client.model.skins.*;
import net.minecraft.tileentity.*;
import java.awt.*;
import cpw.mods.fml.client.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import java.util.*;

@SideOnly(Side.CLIENT)
public class RenderSkinWorkbench extends TileEntitySpecialRenderer
{
    private static final ResourceLocation modelTexture;
    private static final ResourceLocation sunNode;
    public static final ResourceLocation nodetex;
    private static ModelSkinWorkbench model;
    private static Map textureSizeCache;
    public int ticker;
    
    public void renderCore(final TileEntity te, final double x, final double y, final double z, final float partialTicks) {
        final int size1 = 64;
        ++this.ticker;
        if (this.ticker > 161) {
            this.ticker = 1;
        }
        final float f1 = ActiveRenderInfo.rotationX;
        final float f2 = ActiveRenderInfo.rotationXZ;
        final float f3 = ActiveRenderInfo.rotationZ;
        final float f4 = ActiveRenderInfo.rotationYZ;
        final float f5 = ActiveRenderInfo.rotationXY;
        final float scaleCore = 0.35f;
        final float posX = (float)x + 0.5f;
        final float posY = (float)y + 0.5f;
        final float posZ = (float)z + 0.5f;
        final Tessellator tessellator = Tessellator.instance;
        new Color(12648447);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(RenderSkinWorkbench.sunNode);
        final long nt = System.nanoTime();
        final int i = (int)((nt / 40000000L + x) % 32.0);
        final float size2 = size1 * 8.0f;
        final float float_sizeMinus0_01 = size1 - 0.01f;
        final float x2 = (i % 8 * size1 + 0.0f) / size2;
        final float x3 = (i % 8 * size1 + float_sizeMinus0_01) / size2;
        final float x4 = (i / 8 * size1 + 0.0f) / size2;
        final float x5 = (i / 8 * size1 + float_sizeMinus0_01) / size2;
        tessellator.startDrawingQuads();
        tessellator.setBrightness(220);
        tessellator.addVertexWithUV((double)(posX - f1 * scaleCore - f4 * scaleCore), (double)(posY - f2 * scaleCore), (double)(posZ - f3 * scaleCore - f5 * scaleCore), (double)x3, (double)x5);
        tessellator.addVertexWithUV((double)(posX - f1 * scaleCore + f4 * scaleCore), (double)(posY + f2 * scaleCore), (double)(posZ - f3 * scaleCore + f5 * scaleCore), (double)x3, (double)x4);
        tessellator.addVertexWithUV((double)(posX + f1 * scaleCore + f4 * scaleCore), (double)(posY + f2 * scaleCore), (double)(posZ + f3 * scaleCore + f5 * scaleCore), (double)x2, (double)x4);
        tessellator.addVertexWithUV((double)(posX + f1 * scaleCore - f4 * scaleCore), (double)(posY - f2 * scaleCore), (double)(posZ + f3 * scaleCore - f5 * scaleCore), (double)x2, (double)x5);
        tessellator.draw();
    }
    
    public void renderTileEntityAt(final TileEntity entity, final double x, final double y, final double z, final float partialTick) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        GL11.glTranslatef(-0.5f, -1.5f, 0.5f);
        Minecraft.getMinecraft().renderEngine.bindTexture(RenderSkinWorkbench.modelTexture);
        GL11.glDisable(2896);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f);
        RenderSkinWorkbench.model.renderModel(0.0625f);
        GL11.glEnable(2896);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glDisable(2896);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        this.renderCore(entity, x, y, z, partialTick);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glPopMatrix();
    }
    
    static {
        modelTexture = new ResourceLocation("mireille:model/skins/skin_workbench.png");
        sunNode = new ResourceLocation("mireille:model/sun_node.png");
        nodetex = new ResourceLocation("mireille:model/nodes.png");
        RenderSkinWorkbench.model = new ModelSkinWorkbench();
        RenderSkinWorkbench.textureSizeCache = new HashMap();
    }
}
