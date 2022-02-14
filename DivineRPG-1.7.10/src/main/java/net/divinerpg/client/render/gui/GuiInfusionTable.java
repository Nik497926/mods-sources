/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.gui;

import java.awt.Color;
import net.divinerpg.blocks.vethea.container.ContainerInfusionTable;
import net.divinerpg.blocks.vethea.container.tileentity.TileEntityInfusionTable;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiInfusionTable
extends GuiContainer {
    private TileEntityInfusionTable TeInfusionTable;
    private ResourceLocation texture = new ResourceLocation("divinerpg:textures/gui/infusionTable.png");

    public GuiInfusionTable(ContainerInfusionTable container) {
        super((Container)container);
        this.TeInfusionTable = container.tileentity;
    }

    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(this.texture);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
    }

    public void drawScreen(int par1, int par2, float par3) {
        super.drawScreen(par1, par2, par3);
        ItemStack item = null;
        String s = null;
        item = this.TeInfusionTable.getStackInSlot(2);
        if (item != null) {
            s = item.getDisplayName();
            this.drawString(this.fontRendererObj, s, this.width / 2 - 50, this.height / 2 - 65, Color.blue.getRGB());
        }
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    protected void keyTyped(char par1, int par2) {
        if (par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
            this.mc.thePlayer.closeScreen();
        }
    }
}

