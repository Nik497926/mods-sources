package mireille.client.gui;

import cpw.mods.fml.relauncher.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import java.util.*;

@SideOnly(Side.CLIENT)
public class GuiImage extends GuiScreen
{
    private static FontRenderer fontRenderer;
    static Map boundTextures;
    private static Gui GuiI;
    
    public static void bindTexture(final String texture) {
        final ResourceLocation rl = new ResourceLocation(texture);
        Minecraft.getMinecraft().renderEngine.bindTexture(rl);
    }
    
    public static void bindTexture(final String mod, final String texture) {
        final ResourceLocation rl = new ResourceLocation(mod, texture);
        Minecraft.getMinecraft().renderEngine.bindTexture(rl);
    }
    
    public static void drawString(final String text, final int x, final int y, final int color, final boolean mid, final boolean shadow) {
        final int offset = GuiImage.fontRenderer.getStringWidth(text);
        if (mid) {
            GuiImage.fontRenderer.drawString(text, x - offset / 2, y - 1, color, shadow);
        }
        else {
            GuiImage.fontRenderer.drawString(text, x, y, color, shadow);
        }
    }
    
    public static void drawImage(final int x, final int y, final int x_image, final int y_image, final int w_image, final int h_image, final float scale) {
        final float var1 = x + (w_image - w_image * scale) / 2.0f;
        final float var2 = y + (h_image - h_image * scale) / 2.0f;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glTranslatef(var1, var2, 0.0f);
        GL11.glScalef(scale, scale, 1.0f);
        GuiImage.GuiI.drawTexturedModalRect(0, 0, x_image, y_image, w_image, h_image);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    static {
        GuiImage.fontRenderer = Minecraft.getMinecraft().fontRenderer;
        GuiImage.boundTextures = new HashMap();
        GuiImage.GuiI = (Gui)new GuiImage();
    }
}
