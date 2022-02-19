/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
 */
package info.jbcs.minecraft.vending.gui;

import info.jbcs.minecraft.vending.GeneralClient;
import info.jbcs.minecraft.vending.gui.IPickBlockHandler;
import info.jbcs.minecraft.vending.gui.Scrollbar;
import info.jbcs.minecraft.vending.inventory.ContainerPickBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class GuiPickBlock
extends GuiContainer {
    Scrollbar scrollbar;
    ContainerPickBlock container;
    GuiScreen parent;

    public GuiPickBlock(EntityPlayer player, ItemStack stack, GuiScreen screen) {
        super((Container)new ContainerPickBlock(player));
        this.ySize = 185;
        this.xSize = 195;
        this.container = (ContainerPickBlock)this.inventorySlots;
        this.container.gui = this;
        this.parent = screen;
        this.container.resultSlot.putStack(stack);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GeneralClient.bind("vending:textures/list_items.png");
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        this.scrollbar.drawButton(this.mc, x, y);
    }

    public void handleMouseInput() {
        super.handleMouseInput();
        this.scrollbar.handleMouseInput();
    }

    public void initGui() {
        super.initGui();
        this.scrollbar = new Scrollbar(200, this.guiLeft + 175, this.guiTop + 18, 12, 124, ""){

            @Override
            public void onScrolled(float off) {
                int columnsNotFitting = GuiPickBlock.this.container.items.size() / GuiPickBlock.this.container.width - GuiPickBlock.this.container.height + 1;
                if (columnsNotFitting < 0) {
                    columnsNotFitting = 0;
                }
                if (columnsNotFitting == 0) {
                    GuiPickBlock.this.scrollbar.active = false;
                    GuiPickBlock.this.scrollbar.offset = 0.0f;
                } else {
                    GuiPickBlock.this.scrollbar.active = true;
                    GuiPickBlock.this.scrollbar.step = 1.0f / (float)columnsNotFitting;
                }
                GuiPickBlock.this.container.scrollTo(off);
            }
        };
        this.buttonList.add(this.scrollbar);
        this.buttonList.add(new GuiButton(100, this.guiLeft + 44, this.guiTop + 151, 70, 20, "\u0412\u044b\u0431\u0440\u0430\u0442\u044c"));
    }

    public void picked(ItemStack stack) {
        if (this.parent == null) {
            return;
        }
        if (this.parent instanceof IPickBlockHandler) {
            ((IPickBlockHandler)this.parent).blockPicked(stack);
        }
    }

    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 100: {
                ItemStack stack = this.container.resultSlot.getStack();
                if (this.parent instanceof IPickBlockHandler) {
                    ((IPickBlockHandler)this.parent).blockPicked(stack);
                }
                Minecraft.getMinecraft().displayGuiScreen(this.parent);
            }
        }
    }
}

