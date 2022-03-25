//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.obvilion.additionpanels.client.gui;

import appeng.api.config.Settings;
import appeng.api.config.YesNo;
import appeng.client.gui.implementations.GuiUpgradeable;
import appeng.client.gui.widgets.GuiImgButton;
import appeng.client.gui.widgets.GuiTabButton;
import appeng.client.gui.widgets.GuiToggleButton;
import appeng.core.localization.GuiText;
import appeng.core.sync.GuiBridge;
import appeng.core.sync.network.NetworkHandler;
import appeng.core.sync.packets.PacketConfigButton;
import appeng.core.sync.packets.PacketSwitchGuis;
import appeng.helpers.IInterfaceHost;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import ru.obvilion.additionpanels.AdditionPanels;
import ru.obvilion.additionpanels.common.container.ContainerInterface;
import ru.obvilion.additionpanels.common.utils.InterfaceData;

public class GuiInterface extends GuiUpgradeable {
    private GuiTabButton priority;
    private GuiImgButton BlockMode;
    private GuiToggleButton interfaceMode;

    private final int size;
    private final InterfaceData data;
    private static final ResourceLocation x18 = new ResourceLocation(AdditionPanels.MODID, "textures/gui/interface_x18.png");
    private static final ResourceLocation x27 = new ResourceLocation(AdditionPanels.MODID, "textures/gui/interface_x27.png");
    private static final ResourceLocation x36 = new ResourceLocation(AdditionPanels.MODID, "textures/gui/interface_x36.png");

    public GuiInterface(InventoryPlayer inventoryPlayer, IInterfaceHost te) {
        super(new ContainerInterface(inventoryPlayer, te));
        this.size = te.getInterfaceDuality().getPatterns().getSizeInventory();
        data= InterfaceData.getFromPatterSize(size);
        this.ySize = data.height;
    }

    protected void addButtons() {
        this.priority = new GuiTabButton(this.guiLeft + 154, this.guiTop, 66, GuiText.Priority.getLocal(), itemRender);
        this.buttonList.add(this.priority);
        this.BlockMode = new GuiImgButton(this.guiLeft - 18, this.guiTop + 8, Settings.BLOCK, YesNo.NO);
        this.buttonList.add(this.BlockMode);
        this.interfaceMode = new GuiToggleButton(this.guiLeft - 18, this.guiTop + 26, 84, 85, GuiText.InterfaceTerminal.getLocal(), GuiText.InterfaceTerminalHint.getLocal());
        this.buttonList.add(this.interfaceMode);
    }


    @Override
    public void drawBG(int offsetX, int offsetY, int mouseX, int mouseY) {
        this.handleButtonVisibility();
        switch (size){
            case 18:
                mc.getTextureManager().bindTexture(x18);
                break;
            case 27:
                mc.getTextureManager().bindTexture(x27);
                break;

            case 36:
                mc.getTextureManager().bindTexture(x36);
                break;
            default:
                this.bindTexture(this.getBackground());

        }

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, 177, this.ySize);
        if (this.drawUpgrades()) {
            this.drawTexturedModalRect(offsetX + 177, offsetY, 177, 0, 35, 14 + this.cvb.availableUpgrades() * 18);
        }

        if (this.hasToolbox()) {
            this.drawTexturedModalRect(offsetX + 178, offsetY + this.ySize - 90, 178, this.ySize - data.n, 68, 68);
        }
    }

    public void drawFG(int offsetX, int offsetY, int mouseX, int mouseY) {
        if (this.BlockMode != null) {
            this.BlockMode.set(((ContainerInterface) this.cvb).getBlockingMode());
        }

        if (this.interfaceMode != null) {
            this.interfaceMode.setState(((ContainerInterface) this.cvb).getInterfaceTerminalMode() == YesNo.YES);
        }

        this.fontRendererObj.drawString(this.getGuiDisplayName(GuiText.Interface.getLocal()), 8, 6, 4210752);
        if (data.yConfig -10 > 10){
            this.fontRendererObj.drawString(GuiText.Config.getLocal(), 8, data.yConfig -10, 4210752);

        }
        this.fontRendererObj.drawString(GuiText.StoredItems.getLocal(), 8, data.yPattern - 20, 4210752);
        this.fontRendererObj.drawString(GuiText.Patterns.getLocal(), 8, data.yPattern - 10, 4210752);
        this.fontRendererObj.drawString(GuiText.inventory.getLocal(), 8, this.ySize - 96 + 3, 4210752);
    }

    protected String getBackground() {
        return "guis/interface.png";
    }

    protected void actionPerformed(GuiButton btn) {
        super.actionPerformed(btn);
        boolean backwards = Mouse.isButtonDown(1);
        if (btn == this.priority) {
            NetworkHandler.instance.sendToServer(new PacketSwitchGuis(GuiBridge.GUI_PRIORITY));
        }

        if (btn == this.interfaceMode) {
            NetworkHandler.instance.sendToServer(new PacketConfigButton(Settings.INTERFACE_TERMINAL, backwards));
        }

        if (btn == this.BlockMode) {
            NetworkHandler.instance.sendToServer(new PacketConfigButton(this.BlockMode.getSetting(), backwards));
        }

    }

}
