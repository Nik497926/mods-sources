/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package info.jbcs.minecraft.vending.gui;

import info.jbcs.minecraft.vending.Vending;
import info.jbcs.minecraft.vending.gui.GuiEdit;
import info.jbcs.minecraft.vending.gui.GuiExButton;
import info.jbcs.minecraft.vending.gui.GuiLabel;
import info.jbcs.minecraft.vending.gui.GuiScreenPlus;
import info.jbcs.minecraft.vending.network.MsgWrench;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class GuiWrenchVendingMachine
extends GuiScreenPlus {
    GuiEdit ownerNameEdit;
    GuiExButton infiniteButton;
    TileEntityVendingMachine entity;
    boolean infinite;

    public GuiWrenchVendingMachine(World world, int x, int y, int z, EntityPlayer entityplayer) {
        super(166, 120, "vending:textures/wrench-gui.png");
        this.addChild(new GuiLabel(9, 9, StatCollector.translateToLocal((String)"gui.vendingBlock.settings")));
        this.addChild(new GuiLabel(9, 29, StatCollector.translateToLocal((String)"gui.vendingBlock.owner")));
        this.ownerNameEdit = new GuiEdit(16, 43, 138, 13);
        this.addChild(this.ownerNameEdit);
        this.infiniteButton = new GuiExButton(9, 64, 148, 20, ""){

            @Override
            public void onClick() {
                GuiWrenchVendingMachine.this.infinite = !GuiWrenchVendingMachine.this.infinite;
                this.caption = StatCollector.translateToLocal((String)"gui.vendingBlock.infinite") + ": " + (GuiWrenchVendingMachine.this.infinite ? StatCollector.translateToLocal((String)"gui.vendingBlock.yes") : StatCollector.translateToLocal((String)"gui.vendingBlock.no"));
            }
        };
        this.addChild(this.infiniteButton);
        this.addChild(new GuiExButton(9, 91, 148, 20, StatCollector.translateToLocal((String)"gui.vendingBlock.apply")){

            @Override
            public void onClick() {
                MsgWrench msg = new MsgWrench(GuiWrenchVendingMachine.this.entity, GuiWrenchVendingMachine.this.infinite, GuiWrenchVendingMachine.this.ownerNameEdit.getText());
                Vending.instance.messagePipeline.sendToServer(msg);
                GuiWrenchVendingMachine.this.mc.thePlayer.closeScreen();
            }
        });
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof TileEntityVendingMachine)) {
            return;
        }
        this.entity = (TileEntityVendingMachine)tileEntity;
        this.ownerNameEdit.setText(this.entity.ownerName);
        this.infinite = !this.entity.infinite;
        this.infiniteButton.onClick();
    }
}

