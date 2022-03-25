package ru.obvilion.additionpanels.client.gui;

import ic2.core.GuiIC2;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import ru.obvilion.additionpanels.AdditionPanels;
import ru.obvilion.additionpanels.common.container.ContainerFruitGenerator;
import ru.obvilion.additionpanels.common.tileentity.TileEntityFruitGenerator;

public class GuiFruitGenerator extends GuiIC2 {
    private static final ResourceLocation tex = new ResourceLocation(AdditionPanels.MODID, "textures/gui/fruitgenerator.png");
    private final ContainerFruitGenerator cnt;
    public GuiFruitGenerator(ContainerFruitGenerator container) {
        super(container,203);
        this.cnt = container;

    }
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        super.drawGuiContainerBackgroundLayer(f, x, y);
        int chargeLevel = (int)(14.0F * ((TileEntityFruitGenerator)this.container.base).getChargeLevel());
        if (chargeLevel > 0) {
            this.drawTexturedModalRect(this.xoffset + 9, this.yoffset + 67 - chargeLevel, 176, 14 - chargeLevel, 14, chargeLevel);
        }

    }

    @Override
    public String getName() {
        return StatCollector.translateToLocal(cnt.base.getInventoryName());
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return tex;
    }

}
