/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.tile;

import com.meteor.extrabotany.common.block.tile.TileBlockSpawner;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderTileBlockSpawner
extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float pticks) {
        if (tile != null && tile instanceof TileBlockSpawner && ((TileBlockSpawner)tile).getCurrent() < ((TileBlockSpawner)tile).getLast() + 10800000L) {
            Long last = ((TileBlockSpawner)tile).getLast() + 10800000L;
            Long lsr = last - ((TileBlockSpawner)tile).getCurrent();
            if (lsr < 0L) {
                return;
            }
            lsr = lsr / 1000L;
            String hour = this.addZero((int)Math.floor(lsr / 3600L));
            lsr = lsr % 3600L;
            String min = this.addZero((int)Math.floor(lsr / 60L));
            String sec = this.addZero((int)Math.floor(lsr % 60L));
            String title = "\u00a7f\u0421\u043f\u0430\u0432\u043d \u041e\u0434\u0438\u043d\u0430 \u0447\u0435\u0440\u0435\u0437 " + hour + ":" + min + ":" + sec;
            FontRenderer fontrenderer = RenderManager.instance.getFontRenderer();
            float f = 0.75f;
            float f1 = 0.016666668f * f;
            float fOffset = 0.0f;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 3.2625f + fOffset), (float)((float)z + 0.5f));
            GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-RenderManager.instance.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)RenderManager.instance.playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScalef((float)(-f1), (float)(-f1), (float)f1);
            GL11.glScalef((float)3.0f, (float)3.0f, (float)3.0f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            fontrenderer.drawString(title, -fontrenderer.getStringWidth(title) / 2, 0, new Color(0xFFFFFF).getRGB());
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable((int)2896);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
    }

    String addZero(int a) {
        return a > 9 ? "" + a : "0" + a;
    }
}

