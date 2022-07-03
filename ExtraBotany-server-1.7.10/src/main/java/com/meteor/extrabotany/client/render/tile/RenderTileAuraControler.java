/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.tile;

import com.meteor.extrabotany.common.block.tile.TileAuraControler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.config.ConfigItems;
import vazkii.botania.common.core.helper.ItemNBTHelper;

@SideOnly(value=Side.CLIENT)
public class RenderTileAuraControler
extends TileEntitySpecialRenderer {
    private int ticker = 0;

    public void renderTileEntityAt(TileEntity tileEntity, double d0, double d1, double d2, float pticks) {
        if (tileEntity != null && tileEntity instanceof TileAuraControler) {
            ItemStack var0 = null;
            if (((TileAuraControler)tileEntity).getStackInSlot(0) == null) {
                return;
            }
            var0 = ((TileAuraControler)tileEntity).getStackInSlot(0).copy();
            EntityItem entityitem = null;
            float ticks = (float)Minecraft.getMinecraft().renderViewEntity.ticksExisted + pticks;
            GL11.glPushMatrix();
            float h = MathHelper.sin((float)(ticks % 32767.0f / 16.0f)) * 0.05f;
            GL11.glTranslatef((float)((float)d0 + 0.5f), (float)((float)d1 + 0.15f + h), (float)((float)d2 + 0.5f));
            GL11.glRotatef((float)(ticks % 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            if (var0.getItem() instanceof ItemBlock) {
                GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
            } else {
                GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            }
            var0.stackSize = 1;
            entityitem = new EntityItem(tileEntity.getWorldObj(), 0.0, 0.0, 0.0, var0);
            entityitem.hoverStart = 0.0f;
            RenderManager.instance.renderEntityWithPosYaw((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            if (!Minecraft.isFancyGraphicsEnabled()) {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                RenderManager.instance.renderEntityWithPosYaw((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            }
            GL11.glPopMatrix();
        }
    }

    private ItemStack getNode() {
        ItemStack var0 = new ItemStack(ConfigItems.itemJarNode);
        ItemNBTHelper.setInt((ItemStack)var0, (String)"nodetype", (int)0);
        ItemNBTHelper.setInt((ItemStack)var0, (String)"nodemod", (int)1);
        ItemNBTHelper.setString((ItemStack)var0, (String)"nodeid", (String)"0:333:5:813");
        return var0;
    }
}

