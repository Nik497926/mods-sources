/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.terra;

import com.meteor.extrabotany.client.gui.terra.ClientContainer;
import com.meteor.extrabotany.client.gui.terra.ServerContainerB;
import com.meteor.extrabotany.common.block.tile.TileAutoPlate;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiTerra
extends ClientContainer {
    private TileAutoPlate tile;
    private EntityPlayer onPlayer;
    private GuiButton upButton;
    private ResourceLocation guiTexture;

    public GuiTerra(InventoryPlayer playerInventory, TileAutoPlate te) {
        super(new ServerContainerB(playerInventory, te), "chest", te);
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

