/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.math.BigDecimal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import ru.simplemc.simplecore.mod.common.data.TradeData;
import ru.simplemc.simplecore.mod.common.data.TradeType;
import ru.simplemc.simplecore.mod.common.tileentity.TileEntityTradeStation;

@SideOnly(value=Side.CLIENT)
public class TileEntityTradeStationRenderer
extends TileEntitySpecialRenderer {
    private static final float TEXT_FONT_SIZE = 0.008f;
    private final RenderItem itemRenderer = new RenderItem(){

        public boolean shouldBob() {
            return false;
        }

        public boolean shouldSpreadItems() {
            return false;
        }

        public byte getMiniBlockCount(ItemStack stack, byte original) {
            return 1;
        }

        public byte getMiniItemCount(ItemStack stack, byte original) {
            return 1;
        }
    };

    public TileEntityTradeStationRenderer() {
        this.itemRenderer.setRenderManager(RenderManager.instance);
    }

    private void renderText(TileEntityTradeStation tradeStation, ItemStack itemStack, double x, double y, double z, int facing) {
        GL11.glPushMatrix();
        float textPosX = (float)(x + (double)1.01f);
        float textPosZ = (float)(z + (double)0.86f);
        float textRotateX = 60.0f;
        float rotation = -60.0f;
        if (facing == 0) {
            textPosX = (float)(x + (double)0.14f);
            textPosZ = (float)(z + (double)1.01f);
            rotation = 0.0f;
        }
        if (facing == 1) {
            textPosX = (float)(x - (double)0.01f);
            textPosZ = (float)(z + 0.14);
            rotation = 60.0f;
        }
        if (facing == 2) {
            textPosX = (float)(x + 0.86);
            textPosZ = (float)(z - 0.01);
            textRotateX = 0.0f;
            rotation = 0.0f;
        }
        RenderHelper.disableStandardItemLighting();
        GL11.glTranslatef((float)textPosX, (float)((float)y + 0.87f), (float)textPosZ);
        GL11.glRotatef((float)180.0f, (float)textRotateX, (float)0.0f, (float)rotation);
        GL11.glScalef((float)0.008f, (float)0.008f, (float)0.008f);
        FontRenderer fontRenderer = this.field_147501_a.getFontRenderer();
        TradeData data = tradeStation.getData();
        if (data.isOwned()) {
            fontRenderer.drawString("\u00a7l\u00a7o\u041c\u0410\u0413\u0410\u0417\u0418\u041d", 0, 0, 14455808, false);
            if (data.isInfinity()) {
                fontRenderer.drawString("\u00a7l\u00a7o\u0421\u0415\u0420\u0412\u0415\u0420\u0410", 0, 10, 14455808, false);
            } else {
                fontRenderer.drawString("\u00a7l\u00a7o" + data.getOwner().toUpperCase(), 0, 10, 14455808, false);
            }
            if (itemStack != null && data.getPrice().compareTo(BigDecimal.ZERO) >= 1) {
                fontRenderer.drawString(data.getType() == TradeType.BUY ? "\u00a7o\u041f\u0420\u041e\u0414\u0410\u0415\u0422\u0421\u042f \u0417\u0410:" : "\u00a7o\u0421\u041a\u0423\u041f\u0410\u0415\u0422\u0421\u042f \u0417\u0410:", 0, 24, 0x343434, false);
                fontRenderer.drawString("\u00a7l" + data.getPrice(), 0, 34, 4927275, false);
                fontRenderer.drawString("\u00a7o\u041a\u041e\u041b\u0418\u0427\u0415\u0421\u0422\u0412\u041e:", 0, 48, 0x343434, false);
                fontRenderer.drawString("\u00a7l" + itemStack.stackSize, 0, 58, 4927275, false);
                if (data.getType() == TradeType.BUY) {
                    fontRenderer.drawString("\u00a7o\u0412 \u041d\u0410\u041b\u0418\u0427\u0418\u0418:", 0, 72, 0x343434, false);
                } else {
                    fontRenderer.drawString("\u00a7o\u0421\u041a\u0423\u041f\u041b\u0415\u041d\u041e \u0412\u0421\u0415\u0413\u041e: ", 0, 72, 0x343434, false);
                }
                fontRenderer.drawString("\u00a7l" + (data.getType().equals((Object)TradeType.BUY) ? data.getAvailableTradeItemsCount() : data.getTransactions()), 0, 82, 4927275, false);
            } else {
                fontRenderer.drawString("\u0412 \u0434\u0430\u043d\u043d\u044b\u0439 \u043c\u043e\u043c\u0435\u043d\u0442", 0, 38, 15676706, false);
                fontRenderer.drawString("\u0442\u043e\u0440\u0433\u043e\u0432\u0430\u044f \u0442\u043e\u0447\u043a\u0430", 0, 48, 15676706, false);
                fontRenderer.drawString("\u043d\u0435 \u0430\u043a\u0442\u0438\u0432\u043d\u0430. :(", 0, 58, 15676706, false);
            }
        } else {
            fontRenderer.drawString("\u00a7l\u0412\u041d\u0418\u041c\u0410\u041d\u0418\u0415!", 0, 0, 15676706, false);
            fontRenderer.drawString("\u0421\u0442\u0430\u043d\u0446\u0438\u044f \u043d\u0435", 0, 14, 15676706, false);
            fontRenderer.drawString("\u0437\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043e\u0432\u0430\u043d\u0430!", 0, 24, 15676706, false);
        }
        GL11.glPopMatrix();
        RenderHelper.enableStandardItemLighting();
    }

    public void renderItemStack(TradeData data, float x, float y, float z) {
        if (data.isCanRenderItem() && data.getItemStack() != null) {
            EntityItem entity = new EntityItem(null, 0.0, 0.0, 0.0, data.getItemStack());
            entity.hoverStart = 0.0f;
            GL11.glPushMatrix();
            GL11.glDisable((int)2896);
            if (data.getItemStack().getItem() instanceof ItemBlock) {
                GL11.glTranslatef((float)(x + 0.5f), (float)(y + (data.isCanRenderItemHigher() ? 1.5f : 1.325f)), (float)(z + 0.5f));
                GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            } else {
                GL11.glTranslatef((float)(x + 0.5f), (float)(y + (data.isCanRenderItemHigher() ? 1.45f : 1.325f)), (float)(z + 0.5f));
                GL11.glScalef((float)0.7f, (float)0.7f, (float)0.7f);
            }
            GL11.glRotatef((float)((float)Minecraft.getMinecraft().renderViewEntity.ticksExisted % 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glPushAttrib((int)8256);
            RenderHelper.enableStandardItemLighting();
            this.itemRenderer.doRender(entity, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            RenderHelper.disableStandardItemLighting();
            GL11.glPopAttrib();
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
        }
    }

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        TileEntityTradeStation tradeStation = (TileEntityTradeStation)tileEntity;
        int blockMeta = tradeStation.getBlockMetadata();
        this.renderText(tradeStation, tradeStation.getData().getItemStack(), x, y, z, blockMeta);
        this.renderItemStack(tradeStation.getData(), (float)x, (float)y, (float)z);
    }
}

