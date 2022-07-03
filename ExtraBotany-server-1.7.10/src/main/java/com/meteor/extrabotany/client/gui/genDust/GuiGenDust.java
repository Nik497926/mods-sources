/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.genDust;

import com.meteor.extrabotany.client.gui.genDust.ClientContainer;
import com.meteor.extrabotany.client.gui.genDust.ServerContainer;
import com.meteor.extrabotany.common.block.tile.TileGenDust;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiGenDust
extends ClientContainer {
    private TileGenDust tile;
    private EntityPlayer onPlayer;
    private GuiButton upButton;
    private ResourceLocation guiTexture;

    public GuiGenDust(InventoryPlayer playerInventory, TileGenDust te) {
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

