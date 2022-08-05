/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.flower;

import com.meteor.extrabotany.common.block.tile.TileFlower;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(value= Side.CLIENT)
public class GuiFlower extends ClientContainer {
    private TileFlower tile;
    private EntityPlayer onPlayer;
    private GuiButton upButton;

    public GuiFlower(InventoryPlayer playerInventory, TileFlower te) {
        super(new ServerContainer(playerInventory, te), "chest", te);
        this.tile = te;
        this.onPlayer = playerInventory.player;
    }

    public void drawScreen(int mx, int my, float p_73863_3_) {
        super.drawScreen(mx, my, p_73863_3_);
    }

    public void initGui() {
        super.initGui();
    }

    public void updateScreen() {
        super.updateScreen();
    }

    protected void actionPerformed(GuiButton button) {
    }
}

