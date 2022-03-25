package ru.obvilion.additionpanels.client.gui;

import ic2.core.GuiIconButton;
import ic2.core.IC2;
import ic2.core.util.GuiTooltipHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import ru.obvilion.additionpanels.common.container.ContainerAdvancedMFSU;

public class GuiElectric extends GuiContainer {
    public ContainerAdvancedMFSU containerElectricBlock;

    private static final ResourceLocation background;

    static {
        background = new ResourceLocation(IC2.textureDomain, "textures/gui/GUIElectricBlock.png");
    }

    private final String armorInv;
    private final String level;

    public GuiElectric(ContainerAdvancedMFSU containerElectricBlock) {
        super(containerElectricBlock);
        this.ySize = 196;
        this.containerElectricBlock = containerElectricBlock;
        this.armorInv = StatCollector.translateToLocal("ic2.EUStorage.gui.info.armor");
        this.level = StatCollector.translateToLocal("ic2.EUStorage.gui.info.level");
    }

    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiIconButton(0, (this.width - this.xSize) / 2 + 152, (this.height - this.ySize) / 2 + 4, 20, 20, new ItemStack(Items.redstone), true));
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String formatName = I18n.format(this.containerElectricBlock.base.getInventoryName());
        this.fontRendererObj.drawString(formatName, (this.xSize - this.fontRendererObj.getStringWidth(formatName)) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.armorInv, 8, this.ySize - 126 + 3, 4210752);
        this.fontRendererObj.drawString(this.level, 79, 25, 4210752);
        int e = (int) Math.min(containerElectricBlock.base.energy, containerElectricBlock.base.maxStorage);
        this.fontRendererObj.drawString(" " + e, 110, 35, 4210752);
        this.fontRendererObj.drawString("/" + containerElectricBlock.base.maxStorage, 110, 45, 4210752);
        String output = StatCollector.translateToLocalFormatted("ic2.EUStorage.gui.info.output", containerElectricBlock.base.output);
        this.fontRendererObj.drawString(output, 85, 60, 4210752);
        GuiTooltipHelper.drawAreaTooltip(par1 - this.guiLeft, par2 - this.guiTop, containerElectricBlock.base.getredstoneMode(), 153, 3, 172, 22);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int j = (this.width - this.xSize) / 2;
        int k = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
        if (containerElectricBlock.base.energy > 0.0D) {
            int i1 = (int) (24.0F * containerElectricBlock.base.getChargeLevel());
            this.drawTexturedModalRect(j + 79, k + 34, 176, 14, i1 + 1, 16);
        }

    }

    protected void actionPerformed(GuiButton guibutton) {
        super.actionPerformed(guibutton);
        if (guibutton.id == 0) {
            IC2.network.get().initiateClientTileEntityEvent(containerElectricBlock.base, 0);
        }

    }
}
