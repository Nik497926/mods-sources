/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.simplemc.simplecore.mod.client.gui.button.GuiButtonBackpackLock;
import ru.simplemc.simplecore.mod.common.inventory.ContainerBackpack;
import ru.simplemc.simplecore.mod.common.item.ItemBackpack;
import ru.simplemc.simplecore.mod.handler.network.NetworkHandler;
import ru.simplemc.simplecore.mod.handler.network.message.BackpackLockedMessage;

public class GuiContainerBackpack
extends GuiContainer {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("simplecore", "textures/gui/backpack_inventory.png");
    private final EntityPlayer player;

    public GuiContainerBackpack(ContainerBackpack containerBackpack) {
        super((Container)containerBackpack);
        this.player = containerBackpack.getInventoryBackpack().getInventoryPlayer().player;
        this.xSize = 256;
        this.ySize = 256;
    }

    public void initGui() {
        super.initGui();
        if (ItemBackpack.isOwner(this.player.getHeldItem(), this.player)) {
            this.buttonList.add(new GuiButtonBackpackLock(this.guiLeft + 10, this.guiTop + 173, this));
        }
    }

    protected void actionPerformed(@Nonnull GuiButton button) {
        if (button.id == 0) {
            NetworkHandler.NETWORK_WRAPPER.sendToServer((IMessage)new BackpackLockedMessage(this.player.getHeldItem()));
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        for (Object object : this.buttonList) {
            GuiButton button = (GuiButton)object;
            button.func_146111_b(mouseX, mouseY);
        }
    }

    public void drawHoveringText(List<String> hoveringTextLines, int mouseX, int mouseY) {
        super.drawHoveringText(hoveringTextLines, mouseX, mouseY, this.fontRendererObj);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        RenderHelper.disableStandardItemLighting();
        String title = I18n.format((String)"item.simplecore.backpack.name", (Object[])new Object[0]) + " (" + ItemBackpack.getOwner(this.player.getHeldItem()).orElse(I18n.format((String)"gui.backpack.unregistered", (Object[])new Object[0])) + ")";
        this.fontRendererObj.drawString(title, this.xSize / 2 - this.fontRendererObj.getStringWidth(title) / 2, 6, 0x404040);
        RenderHelper.enableGUIStandardItemLighting();
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(GUI_TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        for (Object object : this.buttonList) {
            GuiButton button = (GuiButton)object;
            button.drawButton(this.mc, mouseX, mouseY);
        }
    }

    public EntityPlayer getPlayer() {
        return this.player;
    }
}

