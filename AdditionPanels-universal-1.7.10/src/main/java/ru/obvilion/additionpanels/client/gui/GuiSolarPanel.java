package ru.obvilion.additionpanels.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.obvilion.additionpanels.AdditionPanels;
import ru.obvilion.additionpanels.common.container.ContainerSolarPanel;
import ru.obvilion.additionpanels.common.tileentity.TileEntitySolarPanel;

import java.text.NumberFormat;
import java.util.Locale;

public class GuiSolarPanel extends GuiContainer {
    public TileEntitySolarPanel tileentity;
    private static ResourceLocation tex = new ResourceLocation(AdditionPanels.MODID, "textures/gui/solarpanel.png");

    public GuiSolarPanel(InventoryPlayer inventoryplayer, TileEntitySolarPanel tileentitysolarpanel) {
        super(new ContainerSolarPanel(inventoryplayer, tileentitysolarpanel));
        this.tileentity = tileentitysolarpanel;
        this.allowUserInput = false;
        this.xSize = 194;
        this.ySize = 168;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String formatPanelName = I18n.format("tile." + tileentity.panelName +".name", new Object[0]);
        int nmPos = (this.xSize - this.fontRendererObj.getStringWidth(formatPanelName)) / 2;
        this.fontRendererObj.drawString(formatPanelName, nmPos, 7, 7718655);
        String storageString = I18n.format("gui.storage") + ": ";
        String maxOutputString = I18n.format("gui.maxOutput") + ": ";
        String generatingString = I18n.format("gui.generating") + ": ";
        String energyPerTickString = I18n.format("gui.energyPerTick");
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.UK);

        this.fontRendererObj.drawString(storageString + this.tileentity.storage + "/" + this.tileentity.maxStorage, 50, 22, 13487565);
        this.fontRendererObj.drawString(maxOutputString + numberInstance.format(this.tileentity.outputEu).replace(","," ") + " " + energyPerTickString, 50, 32, 13487565);
        this.fontRendererObj.drawString(generatingString + numberInstance.format(this.tileentity.generating).replace(","," ") + " " + energyPerTickString, 50, 42, 13487565);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(tex);
        int h = (this.width - this.xSize) / 2;
        int k = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(h, k, 0, 0, this.xSize, this.ySize);
        if (this.tileentity.storage > 0) {
            int l = this.tileentity.gaugeEnergyScaled(24);
            this.drawTexturedModalRect(h + 19, k + 24, 195, 0, l + 1, 14);
        }

        if (this.tileentity.skyIsVisible) {
            if (this.tileentity.sunIsUp) {
                this.drawTexturedModalRect(h + 24, k + 42, 195, 15, 14, 14);
            } else {
                this.drawTexturedModalRect(h + 24, k + 42, 210, 15, 14, 14);
            }
        }

    }
}
