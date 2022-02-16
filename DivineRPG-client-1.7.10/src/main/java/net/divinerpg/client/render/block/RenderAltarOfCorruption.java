/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBook
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.blocks.vanilla.container.tileentity.TileEntityAltarOfCorruption;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderAltarOfCorruption
extends TileEntitySpecialRenderer {
    private static final ResourceLocation enchantingTableBookTexture = new ResourceLocation("divinerpg:textures/model/altarBook.png");
    private ModelBook enchantmentBook = new ModelBook();

    public void renderTileEntityEnchantmentTableAt(TileEntityAltarOfCorruption par1TileEntityEnchantmentTable, double par2, double par4, double par6, float par8) {
        float f2;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 0.75f), (float)((float)par6 + 0.5f));
        float f1 = (float)par1TileEntityEnchantmentTable.tickCount + par8;
        GL11.glTranslatef((float)0.0f, (float)(0.1f + MathHelper.sin((float)(f1 * 0.1f)) * 0.01f), (float)0.0f);
        for (f2 = par1TileEntityEnchantmentTable.bookRotation2 - par1TileEntityEnchantmentTable.bookRotationPrev; f2 >= (float)Math.PI; f2 -= (float)Math.PI * 2) {
        }
        while (f2 < (float)(-Math.PI)) {
            f2 += (float)Math.PI * 2;
        }
        float f3 = par1TileEntityEnchantmentTable.bookRotationPrev + f2 * par8;
        GL11.glRotatef((float)(-f3 * 180.0f / (float)Math.PI), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)80.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        this.bindTexture(enchantingTableBookTexture);
        float f4 = par1TileEntityEnchantmentTable.pageFlipPrev + (par1TileEntityEnchantmentTable.pageFlip - par1TileEntityEnchantmentTable.pageFlipPrev) * par8 + 0.25f;
        float f5 = par1TileEntityEnchantmentTable.pageFlipPrev + (par1TileEntityEnchantmentTable.pageFlip - par1TileEntityEnchantmentTable.pageFlipPrev) * par8 + 0.75f;
        f4 = (f4 - (float)MathHelper.truncateDoubleToInt((double)f4)) * 1.6f - 0.3f;
        f5 = (f5 - (float)MathHelper.truncateDoubleToInt((double)f5)) * 1.6f - 0.3f;
        if (f4 < 0.0f) {
            f4 = 0.0f;
        }
        if (f5 < 0.0f) {
            f5 = 0.0f;
        }
        if (f4 > 1.0f) {
            f4 = 1.0f;
        }
        if (f5 > 1.0f) {
            f5 = 1.0f;
        }
        float f6 = par1TileEntityEnchantmentTable.bookSpreadPrev + (par1TileEntityEnchantmentTable.bookSpread - par1TileEntityEnchantmentTable.bookSpreadPrev) * par8;
        GL11.glEnable((int)2884);
        this.enchantmentBook.render((Entity)null, f1, f4, f5, f6, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityEnchantmentTableAt((TileEntityAltarOfCorruption)par1TileEntity, par2, par4, par6, par8);
    }
}

