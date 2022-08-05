/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.flower;

import com.meteor.extrabotany.client.reference.Reference;
import com.meteor.extrabotany.common.block.BlockFlower;
import com.meteor.extrabotany.common.block.tile.TileFlower;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value= Side.CLIENT)
public class ClientContainer extends GuiContainer {
    public ResourceLocation guiTexture;
    private final TileFlower inventory;
    private FontRenderer fonts = null;

    public ClientContainer(Container container, String guiTextureName, TileFlower inventory) {
        super(container);
        BlockFlower.BlockFlowerType type = inventory.type;
        switch (type) {
            case TIER1: {
                this.guiTexture = new ResourceLocation(Reference.MOD_ID_LOWER + ":textures/gui/GUI_ManaFarm_3x3.png");
                break;
            }
            case TIER2: {
                this.guiTexture = new ResourceLocation(Reference.MOD_ID_LOWER + ":textures/gui/GUI_ManaFarm_5x5.png");
                break;
            }
            case TIER3: {
                this.guiTexture = new ResourceLocation(Reference.MOD_ID_LOWER + ":textures/gui/GUI_ManaFarm_7x7.png");
                break;
            }
            case TIER4: {
                this.guiTexture = new ResourceLocation(Reference.MOD_ID_LOWER + ":textures/gui/GUI_ManaFarm_9x9.png");
            }
        }
        this.inventory = inventory;
        this.xSize = 181;
        this.ySize = 256;
    }

    public FontRenderer getFontRender() {
        return this.fonts;
    }

    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
        if (this.guiTexture != null) {
            this.mc.getTextureManager().bindTexture(this.guiTexture);
            this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
            List<Slot> slots = this.inventorySlots.inventorySlots;
            for (Slot sl : slots) {
                int ind = sl.getSlotIndex();
                if (!this.inventory.overgrowthSlots[ind] || sl.inventory instanceof InventoryPlayer) continue;
                this.drawTexturedModalRect(this.guiLeft + sl.xDisplayPosition - 1, this.guiTop + sl.yDisplayPosition - 1, 199, 5, 18, 18);
            }
            int mana = this.inventory.mana;
            int maxMana = TileFlower.MAX_MANA;
            double proc = (double)mana / (double)maxMana * 100.0;
            int p = MathHelper.floor_double((double)(2.46 * proc));
            this.mc.getTextureManager().bindTexture(this.guiTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.drawTexturedModalRect(this.guiLeft + 174, this.guiTop + 251 - p, 189, 5, 3, p);
        }
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fonts = this.fontRendererObj;
    }
}

