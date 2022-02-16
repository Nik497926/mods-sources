/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelChest
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRendererFrostedChest
implements IItemRenderer {
    private ModelChest model;
    private ResourceLocation texture = new ResourceLocation("divinerpg:textures/model/iceikaChest.png");

    public ItemRendererFrostedChest() {
        this.model = new ModelChest();
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        GL11.glPushMatrix();
        switch (type) {
            case INVENTORY: {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)-0.1f, (float)0.0f);
                break;
            }
            case EQUIPPED: {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)0.1f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-1.0f, (float)-1.0f, (float)-1.0f);
                break;
            }
            case ENTITY: {
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            }
        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
        this.model.renderAll();
        GL11.glPopMatrix();
    }
}

