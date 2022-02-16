/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.item;

import net.divinerpg.client.render.block.model.ModelDemonFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRenderDemonFurnace
implements IItemRenderer {
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("divinerpg:textures/model/demonFurnace.png"));
        GL11.glPushMatrix();
        switch (type) {
            case EQUIPPED: {
                GL11.glTranslatef((float)0.4f, (float)0.4f, (float)0.4f);
                GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
                GL11.glRotatef((float)180.0f, (float)0.3f, (float)-10.0f, (float)0.0f);
                break;
            }
            case INVENTORY: {
                GL11.glTranslatef((float)-0.2f, (float)-0.4f, (float)0.0f);
                GL11.glScalef((float)0.7f, (float)0.7f, (float)0.7f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)-10.0f, (float)0.0f);
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
                break;
            }
            case ENTITY: {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.0f);
                GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
                break;
            }
        }
        new ModelDemonFurnace().render(0.0652f);
        GL11.glPopMatrix();
    }
}

