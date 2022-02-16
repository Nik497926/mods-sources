/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.blocks.vethea.container.ContainerDreamLamp;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiDreamLamp
extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation("divinerpg:textures/gui/dreamLamp.png");
    private IInventory playerInv;
    private IInventory blockInv;

    public GuiDreamLamp(IInventory player, IInventory block) {
        super((Container)new ContainerDreamLamp(player, block));
        this.playerInv = player;
        this.blockInv = block;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString(this.blockInv.hasCustomInventoryName() ? this.blockInv.getInventoryName() : I18n.format((String)this.blockInv.getInventoryName(), (Object[])new Object[0]), 12, 8, 7317824);
        this.fontRendererObj.drawString(this.playerInv.hasCustomInventoryName() ? this.playerInv.getInventoryName() : I18n.format((String)this.playerInv.getInventoryName(), (Object[])new Object[0]), 12, this.ySize - 96, 7317824);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int par3) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("divinerpg:textures/gui/dreamLamp.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        if (this.blockInv.getStackInSlot(0) != null && this.blockInv.getStackInSlot(0).getItem() == VetheaItems.acid) {
            this.drawTexturedModalRect(k + 81, l + 31, 176, 0, 13, 13);
        }
    }
}

