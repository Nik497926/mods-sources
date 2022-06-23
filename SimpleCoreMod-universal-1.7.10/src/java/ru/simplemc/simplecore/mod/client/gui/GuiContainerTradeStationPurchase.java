/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import javax.annotation.Nonnull;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.simplemc.simplecore.mod.client.gui.button.GuiButtonPurchase;
import ru.simplemc.simplecore.mod.common.data.TradeData;
import ru.simplemc.simplecore.mod.common.inventory.ContainerTradeStationPurchase;
import ru.simplemc.simplecore.mod.handler.network.NetworkHandler;
import ru.simplemc.simplecore.mod.handler.network.message.TradePurchaseMessage;

public class GuiContainerTradeStationPurchase
extends GuiContainer {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("simplecore", "textures/gui/trade_station_purchase.png");
    private final String title = I18n.format((String)"gui.trade_station.name", (Object[])new Object[0]);
    private final MessagesHandler messagesHandler = new MessagesHandler();
    private final EntityPlayer player;
    private final TradeData data;

    public GuiContainerTradeStationPurchase(ContainerTradeStationPurchase container) {
        super((Container)container);
        this.data = container.getInventoryTradeStation().getTradeStation().getData();
        this.player = container.getInventoryPlayer().player;
        this.xSize = 178;
        this.ySize = 180;
    }

    public void initGui() {
        super.initGui();
        GuiButtonPurchase buttonPurchase = new GuiButtonPurchase(0, this.guiLeft + 8, this.guiTop + 42, this.data.getType(), GUI_TEXTURE);
        buttonPurchase.enabled = !this.data.isOwner(this.player);
        this.buttonList.add(buttonPurchase);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void drawGuiContainerForegroundLayer(int mousePosX, int mousePosY) {
        RenderHelper.disableStandardItemLighting();
        this.fontRendererObj.drawString(this.title, this.xSize / 2 - this.fontRendererObj.getStringWidth(this.title) / 2, 7, 0x404040);
        this.fontRendererObj.drawString(this.data.getPrice().toPlainString(), 50, 25, 5870434);
        this.messagesHandler.canMessagesClean();
        this.messagesHandler.drawMessages();
        RenderHelper.enableGUIStandardItemLighting();
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(GUI_TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    protected void actionPerformed(@Nonnull GuiButton button) {
        if (button.id == 0) {
            NetworkHandler.NETWORK_WRAPPER.sendToServer((IMessage)new TradePurchaseMessage(this.data));
        }
    }

    public MessagesHandler getMessagesHandler() {
        return this.messagesHandler;
    }

    public class MessagesHandler
    extends ArrayList<String> {
        private Long messagesLifetimeTimestamp = null;

        public synchronized void addMessages(String ... lines) {
            this.messagesLifetimeTimestamp = System.currentTimeMillis() + 5000L;
            this.clear();
            for (int i = 0; i < 2 && i < lines.length; ++i) {
                this.add(lines[i]);
            }
        }

        public synchronized void canMessagesClean() {
            if (this.messagesLifetimeTimestamp != null && System.currentTimeMillis() > this.messagesLifetimeTimestamp) {
                this.clear();
            }
        }

        public synchronized void drawMessages() {
            if (this.isEmpty()) {
                GuiContainerTradeStationPurchase.this.fontRendererObj.drawString(GuiContainerTradeStationPurchase.this.player.getCommandSenderName() + ", \u043f\u0440\u0438\u0432\u0435\u0442!", 13, 68, 0xFFFFFF);
                GuiContainerTradeStationPurchase.this.fontRendererObj.drawString("\u0416\u0435\u043b\u0430\u0435\u043c \u043f\u0440\u0438\u044f\u0442\u043d\u044b\u0445 \u043f\u043e\u043a\u0443\u043f\u043e\u043a!", 13, 79, 0xFFFFFF);
            } else {
                for (int i = 0; i < this.size(); ++i) {
                    GuiContainerTradeStationPurchase.this.fontRendererObj.drawString((String)this.get(i), 13, 68 + 11 * i, 0xFFFFFF);
                }
            }
        }
    }
}

