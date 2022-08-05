/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.Awake;

import com.meteor.extrabotany.client.gui.Awake.ContainerGuiElf;
import com.meteor.extrabotany.client.inventory.ContainerAwakeElf;
import com.meteor.extrabotany.common.block.tile.TileBlockElfUpdater;
import com.meteor.extrabotany.common.core.network.MessageHandleGuiButtonPress;
import com.meteor.extrabotany.common.core.network.NetworkHandler2;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

@SideOnly(value= Side.CLIENT)
public class GuiElf extends ContainerGuiElf {
    private TileBlockElfUpdater tile;
    private EntityPlayer onPlayer;
    private GuiButton upButton;

    public GuiElf(InventoryPlayer playerInventory, TileBlockElfUpdater te) {
        super(new ContainerAwakeElf(playerInventory, te), "chest", te);
        this.tile = te;
        this.onPlayer = playerInventory.player;
    }

    public void initGui() {
        super.initGui();
        this.upButton = new GuiButton(0, this.guiLeft + 102, this.guiTop + 85, 70, 20, I18n.format((String)"\u00a76\u00a7l\u0412\u043e\u0437\u0432\u044b\u0441\u0438\u0442\u044c", (Object[])new Object[0]));
        this.buttonList.add(this.upButton);
    }

    public void updateScreen() {
        super.updateScreen();
        ItemStack shema = this.inventorySlots.getSlot(0).getStack();
        ItemStack elfirium2 = this.inventorySlots.getSlot(1).getStack();
        ItemStack input = this.inventorySlots.getSlot(2).getStack();
        ItemStack output = this.inventorySlots.getSlot(3).getStack();
        this.upButton.visible = this.tile.checkItem(shema, 0) != false && this.tile.checkItem(elfirium2, 1) != false && this.tile.checkItem(input, 2) != false && this.tile.checkItem(output, 3) != false;
    }

    protected void actionPerformed(GuiButton button) {
        NetworkHandler2.INSTANCE2.sendToServer((IMessage)new MessageHandleGuiButtonPress(this.tile, button.id));
    }
}

