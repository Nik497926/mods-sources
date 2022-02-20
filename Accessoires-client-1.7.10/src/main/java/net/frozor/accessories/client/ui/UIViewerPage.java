/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.ui;

import java.util.ArrayList;
import java.util.List;
import net.frozor.accessories.client.ClientProxy;
import net.frozor.accessories.client.RenderHelper;
import net.frozor.accessories.client.item.AccessoryItem;
import net.frozor.accessories.client.item.animation.AbstractAnimation;
import net.frozor.accessories.client.network.NetworkHandler;
import net.frozor.accessories.client.network.packet.CPacketAction;
import net.frozor.accessories.client.ui.EnumAction;
import net.frozor.accessories.client.ui.IPage;
import net.frozor.accessories.client.ui.UIAccessorySidebar;
import net.frozor.accessories.client.ui.UIButton;
import net.frozor.accessories.client.ui.UIConfirmPage;
import net.frozor.accessories.client.ui.UIItem;
import net.frozor.accessories.utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

public class UIViewerPage
implements IPage {
    private List<UIButton> buttons = new ArrayList<UIButton>();
    private AccessoryItem item;
    private int lastStagePage;
    private float scaleFactor;
    private Minecraft mc;
    private long lastTime;
    private UIAccessorySidebar parent;
    public UIButton buttonAction;
    private float rotation;

    @Override
    public String getPageName() {
        return this.item.getName();
    }

    @Override
    public void drawPage(Minecraft mc, float marginLeft, float marginTop, int mouseX, int mouseY, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(marginLeft + 82.0f * this.scaleFactor), (float)marginTop, (float)0.0f);
        GL11.glScalef((float)this.scaleFactor, (float)this.scaleFactor, (float)this.scaleFactor);
        RenderHelper.drawRect(163.0, 0.0, 0.0, 121.0, 0x59000000);
        RenderHelper.drawRect(162.0, 0.0, 0.0, 120.0, 0x59000000);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)105.0f, (float)0.0f);
        RenderHelper.drawRect(162.0, 0.0, 0.0, 15.0, 0x59000000);
        GL11.glScalef((float)0.65f, (float)0.65f, (float)0.65f);
        GL11.glPushMatrix();
        GL11.glScalef((float)0.75f, (float)0.75f, (float)0.75f);
        mc.fontRenderer.drawStringWithShadow(UIItem.l("\u00ed\u001d\u045a\u0417\u0408\u041b\u040a"), 4, 4, -1);
        GL11.glPopMatrix();
        mc.fontRenderer.drawStringWithShadow(AbstractAnimation.l("\u00c6E") + this.item.getAuthor(), 6, 12, -1);
        if (this.item.isHas()) {
            GL11.glPushMatrix();
            GL11.glScalef((float)0.75f, (float)0.75f, (float)0.75f);
            mc.fontRenderer.drawStringWithShadow(UIItem.l("\u00edD\u0450\u0466\u0475\u041e\u047f\u0418\u0474"), 328 - mc.fontRenderer.getStringWidth(AbstractAnimation.l("\u0087\u0000\u043a\u0422\u041f\u045a\u0415\u045c\u041e")), 12, -1);
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glScalef((float)0.75f, (float)0.75f, (float)0.75f);
            mc.fontRenderer.drawStringWithShadow(UIItem.l("\u00ed\u0012\u046b\u0467\u0474\u041d\u0476\u041b\u040b\u0467\u0406"), 328 - mc.fontRenderer.getStringWidth(AbstractAnimation.l("\u0087V\u0401\u0423\u041e\u0459\u041c\u045f\u0461\u0423\u046c")), 4, -1);
            GL11.glPopMatrix();
            mc.fontRenderer.drawStringWithShadow(UIItem.l("\u0082|") + Util.formattingBalance(this.item.getPrice()) + AbstractAnimation.l("A\u0004"), 245 - mc.fontRenderer.getStringWidth(UIItem.l("\u0082|") + Util.formattingBalance(this.item.getPrice()) + AbstractAnimation.l("A\u0004")) - 4, 12, -1);
        }
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
        RenderHelper.drawGradientLeftToRight(80, 91, 0x66000000, 0);
        GL11.glTranslatef((float)0.0f, (float)-91.0f, (float)0.0f);
        RenderHelper.drawGradientRightToLeft(80, 91, 0x66000000, 0);
        GL11.glPopMatrix();
        if (this.rotation >= 360.0f) {
            this.rotation = 0.0f;
        }
        long time = System.nanoTime();
        double deltaTime = (double)(time - this.lastTime) / 1.0E9;
        this.lastTime = time;
        this.rotation = (float)((double)this.rotation + deltaTime * 40.0);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)50.0f);
        GL11.glTranslatef((float)81.0f, (float)60.0f, (float)0.0f);
        GL11.glScalef((float)2.5f, (float)2.5f, (float)2.5f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        mc.getTextureManager().bindTexture(this.item.getTexture());
        GL11.glRotatef((float)-25.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        AccessoryItem.CustomPreferences prefs = this.item.getPreferencesGui();
        GL11.glTranslatef((float)(-prefs.getTranslation()[0]), (float)prefs.getTranslation()[1], (float)(-prefs.getTranslation()[2]));
        GL11.glScalef((float)prefs.getScale()[0], (float)prefs.getScale()[1], (float)prefs.getScale()[2]);
        GL11.glTranslatef((float)8.0f, (float)-8.0f, (float)8.0f);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glBlendFunc((int)770, (int)771);
        net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
        this.item.getModel().renderAll();
        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        for (UIButton button : this.buttons) {
            button.drawButton(mc, mouseX, mouseY);
        }
        if (this.buttonAction.getAction() == EnumAction.BUY_PROCESS) {
            if (this.item.isHas()) {
                this.buttonAction.setAction(EnumAction.EQUIP);
                this.buttonAction.displayString = EnumAction.EQUIP.getName();
            } else if (this.item.canTryBuy()) {
                this.buttonAction.setAction(EnumAction.PURCHASE);
                this.buttonAction.displayString = EnumAction.PURCHASE.getName();
            }
        }
        this.buttonAction.drawButton(mc, mouseX, mouseY);
    }

    public AccessoryItem getItem() {
        return this.item;
    }

    public UIViewerPage(UIAccessorySidebar parent, AccessoryItem item) {
        this.item = item;
        this.scaleFactor = 1.0f;
        this.mc = Minecraft.getMinecraft();
        this.parent = parent;
    }

    public void updateButton() {
        boolean isEquip = ClientProxy.equipManager.isEquip(this.item);
        this.updateButton(this.item.isHas() ? (isEquip ? EnumAction.TAKEOFF : EnumAction.EQUIP) : (this.item.canTryBuy() ? EnumAction.PURCHASE : EnumAction.BUY_PROCESS));
    }

    public int getLastStagePage() {
        return this.lastStagePage;
    }

    @Override
    public EnumAction mouseClicked(int mouseX, int mouseY, int click) {
        for (UIButton button : this.buttons) {
            if (!button.mousePressed(this.mc, mouseX, mouseY) || button.getAction() == EnumAction.NONE) continue;
            switch (button.getAction()) {
                case BACK: {
                    ClientProxy.equipManager.getPreviewData().clear();
                    button.func_146113_a(this.mc.getSoundHandler());
                    return button.getAction();
                }
            }
        }
        if (this.buttonAction.mousePressed(this.mc, mouseX, mouseY)) {
            switch (this.buttonAction.getAction()) {
                case PURCHASE: {
                    UIConfirmPage confirmPage = new UIConfirmPage(this.parent, this.item);
                    confirmPage.init(this.parent.marginLeft, this.parent.marginTop, this.scaleFactor);
                    this.parent.page = confirmPage;
                    this.buttonAction.func_146113_a(this.mc.getSoundHandler());
                    break;
                }
                case EQUIP: {
                    CPacketAction packet = new CPacketAction(CPacketAction.Action.EQUIP, this.item.getINDEX());
                    NetworkHandler.sendPacket(packet);
                    this.updateButton(EnumAction.TAKEOFF);
                    this.buttonAction.func_146113_a(this.mc.getSoundHandler());
                    break;
                }
                case TAKEOFF: {
                    CPacketAction packet = new CPacketAction(CPacketAction.Action.TAKEOFF, this.item.getType().name());
                    NetworkHandler.sendPacket(packet);
                    this.updateButton(EnumAction.EQUIP);
                    this.buttonAction.func_146113_a(this.mc.getSoundHandler());
                    break;
                }
            }
        }
        return EnumAction.NONE;
    }

    @Override
    public void actionPerformed(GuiButton button) {
    }

    public void setLastStagePage(int lastStagePage) {
        this.lastStagePage = lastStagePage;
    }

    @Override
    public void handleMouseInput() {
    }

    @Override
    public void init(float marginLeft, float marginTop, float scaleFactor) {
        this.scaleFactor = scaleFactor;
        this.buttons.clear();
        this.buttons.add(new UIButton(EnumAction.BACK, UIItem.l("\u0457\u0415\u047d\u0415\u047e"), (int)(marginLeft + 82.0f * scaleFactor), (int)(marginTop + 120.0f * scaleFactor + 3.0f), 47, 14, scaleFactor));
        this.updateButton();
    }

    public void updateButton(EnumAction action) {
        this.buttonAction = new UIButton(action, action.getName(), (int)(this.parent.marginLeft + 197.0f * this.scaleFactor), (int)(this.parent.marginTop + 120.0f * this.scaleFactor + 3.0f), 47, 14, this.scaleFactor);
    }

    @Override
    public int getMarginLeft() {
        return 120;
    }
}

