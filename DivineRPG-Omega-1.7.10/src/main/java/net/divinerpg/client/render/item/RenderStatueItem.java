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

import net.divinerpg.blocks.base.BlockStatue;
import net.divinerpg.client.model.DivineModel;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderStatueItem
implements IItemRenderer {
    private DivineModel model;
    private ResourceLocation texture;

    public RenderStatueItem(BlockStatue statueBlock) {
        this.model = statueBlock.getModel();
        this.texture = statueBlock.getTexture();
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        GL11.glPushMatrix();
        GL11.glScalef((float)-0.6f, (float)-0.6f, (float)0.6f);
        switch (type) {
            case INVENTORY: {
                GL11.glTranslatef((float)0.0f, (float)0.12f, (float)0.0f);
                break;
            }
            case EQUIPPED: {
                GL11.glTranslatef((float)-0.8f, (float)-0.2f, (float)0.7f);
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                GL11.glTranslatef((float)0.0f, (float)-0.7f, (float)0.7f);
            }
        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
        this.model.render(0.0625f);
        GL11.glPopMatrix();
    }
}

