/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import javax.annotation.Nonnull;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import ru.simplemc.simplecore.mod.client.gui.button.GuiButtonSettingsInventory;
import ru.simplemc.simplecore.mod.client.gui.button.GuiButtonSettingsPurchaseMode;
import ru.simplemc.simplecore.mod.client.gui.button.GuiTextFieldSettingsPrice;
import ru.simplemc.simplecore.mod.common.data.TradeData;
import ru.simplemc.simplecore.mod.common.inventory.ContainerTradeStationSettings;
import ru.simplemc.simplecore.mod.handler.network.NetworkHandler;
import ru.simplemc.simplecore.mod.handler.network.message.TradeSettingsApplyMessage;
import ru.simplemc.simplecore.mod.handler.network.message.TradeStationInventoryMessage;

public class GuiContainerTradeStationSettings
extends GuiContainer {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("simplecore", "textures/gui/trade_station_settings.png");
    private final String title = I18n.format((String)"gui.trade_station.settings.name", (Object[])new Object[0]);
    private final TradeData data;
    private GuiTextFieldSettingsPrice priceTextField;

    public GuiContainerTradeStationSettings(ContainerTradeStationSettings container) {
        super((Container)container);
        this.data = container.getInventoryTradeStation().getTradeStation().getData().copy();
        this.xSize = 178;
        this.ySize = 221;
    }

    public void initGui() {
        super.initGui();
        Keyboard.enableRepeatEvents((boolean)true);
        this.priceTextField = new GuiTextFieldSettingsPrice(this.fontRendererObj, this.guiLeft + 50, this.guiTop + 25, this.data);
        this.buttonList.add(new GuiButtonSettingsPurchaseMode(0, this.guiLeft + 8, this.guiTop + 46, this.data));
        this.buttonList.add(new GuiButtonSettingsInventory(1, this.guiLeft + 8, this.guiTop + 70));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        RenderHelper.disableStandardItemLighting();
        this.fontRendererObj.drawString(this.title, this.xSize / 2 - this.fontRendererObj.getStringWidth(this.title) / 2, 6, 0x404040);
        this.fontRendererObj.drawString("\u0422\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0439: \u00a7l" + this.data.getTransactions(), 12, 104, 0xFFFFFF);
        this.fontRendererObj.drawString("\u041e\u0431\u043e\u0440\u043e\u0442: \u00a7l" + this.data.getTurnover(), 12, 115, 0xFFFFFF);
        RenderHelper.enableGUIStandardItemLighting();
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(GUI_TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        this.priceTextField.drawTextBox();
    }

    protected void actionPerformed(@Nonnull GuiButton button) {
        if (button.id == 0) {
            ((GuiButtonSettingsPurchaseMode)button).actionPerformed();
        }
        if (button.id == 1) {
            NetworkHandler.NETWORK_WRAPPER.sendToServer((IMessage)new TradeStationInventoryMessage(this.data.getPos()));
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.priceTextField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    protected void keyTyped(char typedChar, int keyCode) {
        if (this.priceTextField.textboxKeyTyped(typedChar, keyCode)) {
            return;
        }
        super.keyTyped(typedChar, keyCode);
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents((boolean)false);
        NetworkHandler.NETWORK_WRAPPER.sendToServer((IMessage)new TradeSettingsApplyMessage(this.data));
    }
}

