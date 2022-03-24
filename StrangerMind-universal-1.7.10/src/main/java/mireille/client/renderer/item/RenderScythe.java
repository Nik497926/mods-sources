package mireille.client.renderer.item;

import net.minecraftforge.client.*;
import cpw.mods.fml.relauncher.*;
import mireille.client.model.skins.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;

@SideOnly(Side.CLIENT)
public class RenderScythe implements IItemRenderer
{
    private static ModelSkythe model;
    private static ResourceLocation texture;
    
    public RenderScythe(final ModelSkythe model, final ResourceLocation texture) {
        RenderScythe.model = model;
        RenderScythe.texture = texture;
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
            GL11.glPushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(RenderScythe.texture);
            GL11.glRotatef(-135.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(-45.0f, 0.0f, 0.0f, 1.0f);
            GL11.glScalef(0.8f, 0.8f, 0.8f);
            GL11.glTranslatef(-0.5f, 0.5f, 0.0f);
            RenderScythe.model.render(0.0625f);
            GL11.glPopMatrix();
        }
        else if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glPushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(RenderScythe.texture);
            GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(-90.0f, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(0.0f, -1.0f, 0.0f);
            RenderScythe.model.render(0.0625f);
            GL11.glPopMatrix();
        }
        else {
            GL11.glPushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(RenderScythe.texture);
            GL11.glRotatef(80.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(-40.0f, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(-90.0f, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(-0.1f, -0.2f, -0.7f);
            RenderScythe.model.render(0.0625f);
            GL11.glPopMatrix();
        }
    }
}
