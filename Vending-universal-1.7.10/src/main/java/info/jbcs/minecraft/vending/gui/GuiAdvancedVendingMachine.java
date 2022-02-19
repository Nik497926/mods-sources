/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Keyboard
 */
package info.jbcs.minecraft.vending.gui;

import info.jbcs.minecraft.vending.General;
import info.jbcs.minecraft.vending.Vending;
import info.jbcs.minecraft.vending.gui.GuiPickBlock;
import info.jbcs.minecraft.vending.gui.GuiVendingMachine;
import info.jbcs.minecraft.vending.gui.IPickBlockHandler;
import info.jbcs.minecraft.vending.inventory.ContainerAdvancedVendingMachine;
import info.jbcs.minecraft.vending.network.MsgAdvVenSetItem;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

public class GuiAdvancedVendingMachine
extends GuiVendingMachine
implements IPickBlockHandler {
    ContainerAdvancedVendingMachine container;
    EntityPlayer player;

    public GuiAdvancedVendingMachine(InventoryPlayer inventoryplayer, TileEntityVendingMachine machine) {
        super(new ContainerAdvancedVendingMachine((IInventory)inventoryplayer, machine));
        this.container = (ContainerAdvancedVendingMachine)this.inventorySlots;
        this.player = inventoryplayer.player;
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    @Override
    public void initGui() {
        super.initGui();
        Keyboard.enableRepeatEvents((boolean)true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(100, this.guiLeft + 30, this.guiTop + 67, 41, 15, StatCollector.translateToLocal((String)"gui.vendingBlock.select")));
    }

    protected void actionPerformed(GuiButton button) {
        if (button.id == 100) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiPickBlock(this.player, ((TileEntityVendingMachine)this.container.entity).getBoughtItems()[0], (GuiScreen)this));
        }
    }

    @Override
    public void blockPicked(ItemStack stack) {
        MsgAdvVenSetItem msg = stack == null ? new MsgAdvVenSetItem(0, 0, 0) : new MsgAdvVenSetItem(General.getItemId(stack.getItem()), stack.stackSize, stack.getItemDamage());
        Vending.instance.messagePipeline.sendToServer(msg);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int a, int b) {
        super.drawGuiContainerForegroundLayer(a, b);
    }
}

