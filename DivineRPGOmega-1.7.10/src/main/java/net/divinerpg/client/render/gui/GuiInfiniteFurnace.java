/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.blocks.base.tileentity.TileEntityInfiniteFurnace;
import net.divinerpg.blocks.base.tileentity.container.ContainerInfiniteFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiInfiniteFurnace
extends GuiContainer {
    private TileEntityInfiniteFurnace tileFurnace;
    private String name;
    private String texture;

    public GuiInfiniteFurnace(InventoryPlayer par1InventoryPlayer, TileEntityInfiniteFurnace par2TileEntityFurnace, String name, String texture) {
        super((Container)new ContainerInfiniteFurnace(par1InventoryPlayer, par2TileEntityFurnace));
        this.tileFurnace = par2TileEntityFurnace;
        this.name = name;
        this.texture = texture;
    }

    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        String s = this.name;
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 0x404040);
        s = "Inventory";
        this.fontRendererObj.drawString(s, 8, this.ySize - 96 + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int p_146j976_3_) {
        int i1;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("divinerpg:textures/gui/" + this.texture + ".png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        if (this.tileFurnace.isBurning()) {
            i1 = this.tileFurnace.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }
        i1 = this.tileFurnace.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }
}

