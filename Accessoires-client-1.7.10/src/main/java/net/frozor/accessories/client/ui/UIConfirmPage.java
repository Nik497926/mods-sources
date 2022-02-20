/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.ui;

import java.util.ArrayList;
import java.util.List;
import net.frozor.accessories.client.RenderHelper;
import net.frozor.accessories.client.item.AccessoryItem;
import net.frozor.accessories.client.network.NetworkHandler;
import net.frozor.accessories.client.network.packet.CPacketAction;
import net.frozor.accessories.client.ui.EnumAction;
import net.frozor.accessories.client.ui.IPage;
import net.frozor.accessories.client.ui.UIAccessorySidebar;
import net.frozor.accessories.client.ui.UIButton;
import net.frozor.accessories.client.ui.UIItem;
import net.frozor.accessories.client.ui.UIViewerPage;
import net.frozor.accessories.utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

public class UIConfirmPage
implements IPage {
    private UIAccessorySidebar parent;
    private List<UIButton> buttons = new ArrayList<UIButton>();
    private float scaleFactor;
    private AccessoryItem item;
    private Minecraft mc;

    @Override
    public void handleMouseInput() {
    }

    @Override
    public void actionPerformed(GuiButton guiButton) {
    }

    @Override
    public EnumAction mouseClicked(int mouseX, int mouseY, int click) {
        for (UIButton button : this.buttons) {
            if (!button.mousePressed(this.mc, mouseX, mouseY)) continue;
            if (button.getAction() == EnumAction.CONFIRM) {
                CPacketAction packet = new CPacketAction(CPacketAction.Action.PURCHASE, this.item.getINDEX());
                NetworkHandler.sendPacket(packet);
                this.item.updateTryBuy();
                UIViewerPage viewerPage = new UIViewerPage(this.parent, this.item);
                viewerPage.init(this.parent.marginLeft, this.parent.marginTop, this.scaleFactor);
                this.parent.page = viewerPage;
                viewerPage.buttonAction.setAction(EnumAction.BUY_PROCESS);
                continue;
            }
            if (button.getAction() != EnumAction.CANCEL) continue;
            UIViewerPage viewerPage = new UIViewerPage(this.parent, this.item);
            viewerPage.init(this.parent.marginLeft, this.parent.marginTop, this.scaleFactor);
            this.parent.page = viewerPage;
            return EnumAction.NONE;
        }
        return EnumAction.NONE;
    }

    @Override
    public int getMarginLeft() {
        return 120;
    }

    public UIConfirmPage(UIAccessorySidebar parent, AccessoryItem item) {
        this.item = item;
        this.scaleFactor = 1.0f;
        this.mc = Minecraft.getMinecraft();
        this.parent = parent;
    }

    public AccessoryItem getItem() {
        return this.item;
    }

    @Override
    public void drawPage(Minecraft mc, float marginLeft, float marginTop, int mouseX, int mouseY, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(marginLeft + 82.0f * this.scaleFactor), (float)marginTop, (float)0.0f);
        GL11.glScalef((float)this.scaleFactor, (float)this.scaleFactor, (float)this.scaleFactor);
        RenderHelper.drawRect(163.0, 0.0, 0.0, 61.0, 0x59000000);
        RenderHelper.drawRect(162.0, 0.0, 0.0, 60.0, 0x59000000);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)45.0f, (float)0.0f);
        RenderHelper.drawRect(162.0, 0.0, 0.0, 15.0, 0x59000000);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)81.0f, (float)0.0f, (float)0.0f);
        RenderHelper.drawGradientLeftToRight(80, 14, -872415232, 0);
        GL11.glTranslatef((float)0.0f, (float)-14.0f, (float)0.0f);
        RenderHelper.drawGradientRightToLeft(80, 14, -872415232, 0);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)81.0f, (float)5.0f, (float)0.0f);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glScalef((float)0.65f, (float)0.65f, (float)0.65f);
        mc.fontRenderer.drawStringWithShadow(Util.rus(this.item.getName()), -(mc.fontRenderer.getStringWidth(Util.rus(this.item.getName())) / 2), 0, -1);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)81.0f, (float)14.0f, (float)0.0f);
        RenderHelper.drawGradientLeftToRight(80, 31, 0x66000000, 0);
        GL11.glTranslatef((float)0.0f, (float)-31.0f, (float)0.0f);
        RenderHelper.drawGradientRightToLeft(80, 31, 0x66000000, 0);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)81.0f, (float)25.0f, (float)0.0f);
        GL11.glScalef((float)0.64f, (float)0.64f, (float)0.64f);
        mc.fontRenderer.drawStringWithShadow(UIItem.l("\u0082,\u0437\u0401\u0005\u0479\u041b\u0408\u041b\u0478\u046ej\u041a\u0474\u0467\u040a\u0415\u0408\u041d\u0408\u0469j\u0082/") + Util.formattingBalance(this.item.getPrice()) + UIItem.l("\u0005\u00edC\u0467\u041f\u0474\u0418u"), -(mc.fontRenderer.getStringWidth(UIItem.l("\u0082,\u0437\u0401\u0005\u0479\u041b\u0408\u041b\u0478\u046ej\u041a\u0474\u0467\u040a\u0415\u0408\u041d\u0408\u0469j\u0082/") + Util.formattingBalance(this.item.getPrice()) + UIItem.l("\u0005\u00edC\u0467\u041f\u0474\u0418u")) / 2), 0, -1);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        for (UIButton button : this.buttons) {
            button.drawButton(mc, mouseX, mouseY);
        }
    }

    @Override
    public void init(float marginLeft, float marginTop, float scaleFactor) {
        this.scaleFactor = scaleFactor;
        this.buttons.clear();
        this.buttons.add(new UIButton(EnumAction.CONFIRM, Util.rus(UIItem.l("\u0405\u0415\u0405\u041f\u0405\u04db\u0404\u2050\u0405\u044c\u0405\u00ff\u0404\u0448\u0405\u04db\u0405\u041b\u0404\u2050\u0404\u0440")), (int)(marginLeft + 82.0f * scaleFactor), (int)(marginTop + 44.0f * scaleFactor + 3.0f), 50, 14, scaleFactor));
        this.buttons.add(new UIButton(EnumAction.CANCEL, Util.rus(UIItem.l("\u0405\u0411\u0404\u2050\u0405\u0412\u0405\u00ff\u0405\u044f\u0405\u00fa")), (int)(marginLeft + 197.0f * scaleFactor), (int)(marginTop + 44.0f * scaleFactor + 3.0f), 47, 14, scaleFactor));
    }

    @Override
    public String getPageName() {
        return this.item.getName();
    }
}

