package mireille.client.renderer.item;

import net.minecraftforge.client.*;
import cpw.mods.fml.relauncher.*;
import mireille.client.model.skins.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import java.util.*;
import mireille.client.handler.*;
import net.minecraft.client.renderer.*;

@SideOnly(Side.CLIENT)
public class RenderLiveMolot implements IItemRenderer
{
    private static ModelMolot model;
    private static ResourceLocation texture;
    private static int ticks;
    private static int oldTime;
    private static int meta;
    
    public RenderLiveMolot(final ModelMolot model, final ResourceLocation texture) {
        RenderLiveMolot.model = model;
        RenderLiveMolot.texture = texture;
    }
    
    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return true;
    }
    
    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
        return type == IItemRenderer.ItemRenderType.INVENTORY || (type == IItemRenderer.ItemRenderType.ENTITY && helper == IItemRenderer.ItemRendererHelper.ENTITY_ROTATION);
    }
    
    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        renderSkin(type, item, data);
    }
    
    public static void renderSkin(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            Minecraft.getMinecraft().renderEngine.bindTexture(RenderLiveMolot.texture);
            GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(-40.0f, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(0.0f, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(-0.0f, -0.7f, 0.0f);
            RenderLiveMolot.model.render(0.0625f);
            setDragonEffect();
        }
        else if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glPushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(RenderLiveMolot.texture);
            GL11.glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(-135.0f, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(-0.7f, -0.7f, 0.0f);
            RenderLiveMolot.model.render(0.0625f);
            GL11.glPopMatrix();
        }
        else {
            Minecraft.getMinecraft().renderEngine.bindTexture(RenderLiveMolot.texture);
            GL11.glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(-135.0f, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(-0.7f, -0.7f, 0.0f);
            RenderLiveMolot.model.render(0.0625f);
            setDragonEffect();
        }
    }
    
    public static void setDragonEffect() {
        final Random random = new Random(123141L);
        final Tessellator tessellator = Tessellator.instance;
        GL11.glPushMatrix();
        final int time = (int)(Minecraft.getMinecraft().theWorld.getWorldTime() % 20L);
        if (RenderLiveMolot.oldTime != time) {
            RenderLiveMolot.oldTime = time;
            ++RenderLiveMolot.ticks;
        }
        final float f1 = (RenderLiveMolot.ticks + ClientRenderHandler.partialTicks) / 300.0f;
        float f2 = 0.0f;
        if (f1 > 0.6f) {
            f2 = (f1 - 0.6f) / 0.2f;
        }
        GL11.glDisable(2896);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f);
        GL11.glDisable(3553);
        GL11.glShadeModel(7425);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        GL11.glDisable(3008);
        GL11.glEnable(2884);
        GL11.glTranslatef(0.0f, 0.3f, 0.0f);
        GL11.glScalef(0.05f, 0.05f, 0.05f);
        for (int i = 0; i < 20; ++i) {
            GL11.glRotatef(random.nextFloat() * 360.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(random.nextFloat() * 360.0f, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(random.nextFloat() * 360.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(random.nextFloat() * 360.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(random.nextFloat() * 360.0f, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(random.nextFloat() * 360.0f + f1 * 90.0f, 0.0f, 0.0f, 1.0f);
            tessellator.startDrawing(6);
            final float f3 = random.nextFloat() * 20.0f + 5.0f + 10.0f;
            final float f4 = random.nextFloat() * 2.0f + 1.0f + 2.0f;
            tessellator.setColorRGBA_I(16776960, 255);
            tessellator.addVertex(0.0, 0.0, 0.0);
            tessellator.setColorRGBA_I(25600, 0);
            tessellator.addVertex(-0.866 * f4, (double)f3, (double)(-0.5f * f4));
            tessellator.addVertex(0.866 * f4, (double)f3, (double)(-0.5f * f4));
            tessellator.addVertex(0.0, (double)f3, (double)(1.0f * f4));
            tessellator.addVertex(-0.866 * f4, (double)f3, (double)(-0.5f * f4));
            tessellator.draw();
        }
        GL11.glDisable(2884);
        GL11.glDisable(3042);
        GL11.glShadeModel(7424);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        GL11.glEnable(2896);
        GL11.glPopMatrix();
    }
    
    static {
        RenderLiveMolot.ticks = 0;
        RenderLiveMolot.oldTime = 0;
    }
}
