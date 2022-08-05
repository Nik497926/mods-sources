/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.autoTrade;

import com.meteor.extrabotany.client.gui.autoTrade.ClientContainer;
import com.meteor.extrabotany.client.gui.autoTrade.ServerContainerT;
import com.meteor.extrabotany.common.block.tile.TileAutoTradeElf;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(value= Side.CLIENT)
public class GuiAutoTrade extends ClientContainer {
    private TileAutoTradeElf tile;
    private EntityPlayer onPlayer;
    private GuiButton upButton;
    private ResourceLocation guiTexture;

    public GuiAutoTrade(InventoryPlayer playerInventory, TileAutoTradeElf te) {
        super(new ServerContainerT(playerInventory, te), "chest", te);
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

