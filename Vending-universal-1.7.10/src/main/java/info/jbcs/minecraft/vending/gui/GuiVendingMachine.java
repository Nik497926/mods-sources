/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package info.jbcs.minecraft.vending.gui;

import info.jbcs.minecraft.vending.GeneralClient;
import info.jbcs.minecraft.vending.inventory.ContainerVendingMachine;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiVendingMachine
extends GuiContainer {
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
    }

    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
        for (int i1 = 0; i1 < this.inventorySlots.inventorySlots.size(); ++i1) {
            Slot slot = (Slot)this.inventorySlots.inventorySlots.get(i1);
            if (!this.isMouseOverSlot1(slot, p_73863_1_, p_73863_2_) || !slot.func_111238_b()) continue;
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2929);
            int j1 = slot.xDisplayPosition;
            int k1 = slot.yDisplayPosition;
            GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)false);
            this.drawGradientRect(j1, k1, j1 + 16, k1 + 16, 0xFF00FF, 0xFF0000);
            GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)2929);
        }
    }

    private boolean isMouseOverSlot1(Slot p_146981_1_, int p_146981_2_, int p_146981_3_) {
        return this.func_146978_c(p_146981_1_.xDisplayPosition, p_146981_1_.yDisplayPosition, 16, 16, p_146981_2_, p_146981_3_);
    }

    public GuiVendingMachine(InventoryPlayer inventoryplayer, TileEntityVendingMachine machine) {
        super((Container)new ContainerVendingMachine((IInventory)inventoryplayer, machine));
    }

    public GuiVendingMachine(Container c) {
        super(c);
    }

    protected void drawGuiContainerForegroundLayer(int a, int b) {
        FontRenderer fontRenderer = this.fontRendererObj;
        String title = StatCollector.translateToLocal((String)"gui.vendingBlock.storage");
        fontRenderer.drawString(title, 87 - fontRenderer.getStringWidth(title) / 2, 6, 0x404040);
        fontRenderer.drawString(StatCollector.translateToLocal((String)"gui.vendingBlock.selling"), 107, 19, 0x404040);
        fontRenderer.drawString(StatCollector.translateToLocal((String)"gui.vendingBlock.buying"), 29, 19, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int a, int b) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GeneralClient.bind("vending:textures/vending-gui.png");
        this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);
    }

    public void onGuiClosed() {
    }
}

