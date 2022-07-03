/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.autoDaisy;

import com.meteor.extrabotany.client.gui.autoDaisy.ClientContainer;
import com.meteor.extrabotany.client.gui.autoDaisy.ServerContainerA;
import com.meteor.extrabotany.common.block.tile.TileAutoDaisy;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiDaisy
extends ClientContainer {
    private TileAutoDaisy tile;
    private EntityPlayer onPlayer;
    private GuiButton upButton;
    private ResourceLocation guiTexture;

    public GuiDaisy(InventoryPlayer playerInventory, TileAutoDaisy te) {
        super(new ServerContainerA(playerInventory, te), "chest", te);
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

