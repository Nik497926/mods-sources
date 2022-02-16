/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.item;

import net.divinerpg.client.render.block.model.ModelDramixAltar;
import net.divinerpg.client.render.block.model.ModelExtractor;
import net.divinerpg.client.render.block.model.ModelParasectaAltar;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRenderAltar
implements IItemRenderer {
    private String texture;

    public ItemRenderAltar(String tex) {
        this.texture = tex;
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        float scale;
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("divinerpg:textures/model/" + this.texture + ".png"));
        GL11.glPushMatrix();
        if (item.getItem() == Item.getItemFromBlock((Block)ArcanaBlocks.dramixAltar)) {
            scale = 0.1f;
            switch (type) {
                case EQUIPPED: {
                    GL11.glTranslatef((float)0.5f, (float)-0.2f, (float)0.5f);
                    GL11.glScalef((float)scale, (float)scale, (float)scale);
                    break;
                }
                case INVENTORY: {
                    GL11.glTranslatef((float)0.0f, (float)-1.2f, (float)0.0f);
                    GL11.glScalef((float)scale, (float)scale, (float)scale);
                    break;
                }
                case EQUIPPED_FIRST_PERSON: {
                    GL11.glScalef((float)scale, (float)scale, (float)scale);
                    break;
                }
                case ENTITY: {
                    GL11.glScalef((float)scale, (float)scale, (float)scale);
                    break;
                }
            }
            new ModelDramixAltar().render(0.652f);
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        if (item.getItem() == Item.getItemFromBlock((Block)ArcanaBlocks.parasectaAltar)) {
            scale = 0.1f;
            switch (type) {
                case EQUIPPED: {
                    GL11.glTranslatef((float)0.5f, (float)-0.2f, (float)0.5f);
                    GL11.glScalef((float)scale, (float)scale, (float)scale);
                    break;
                }
                case INVENTORY: {
                    GL11.glTranslatef((float)0.0f, (float)-1.2f, (float)0.0f);
                    GL11.glScalef((float)scale, (float)scale, (float)scale);
                    break;
                }
                case EQUIPPED_FIRST_PERSON: {
                    GL11.glScalef((float)scale, (float)scale, (float)scale);
                    break;
                }
                case ENTITY: {
                    GL11.glScalef((float)scale, (float)scale, (float)scale);
                    break;
                }
            }
            new ModelParasectaAltar().render(0.652f);
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        if (item.getItem() == Item.getItemFromBlock((Block)ArcanaBlocks.arcanaExtractor)) {
            switch (type) {
                case EQUIPPED: {
                    GL11.glTranslatef((float)0.5f, (float)0.0f, (float)0.5f);
                    GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
                    break;
                }
                case INVENTORY: {
                    GL11.glTranslatef((float)0.0f, (float)-0.6f, (float)0.0f);
                    GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
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
            new ModelExtractor().render(0.0652f);
        }
        GL11.glPopMatrix();
    }
}

