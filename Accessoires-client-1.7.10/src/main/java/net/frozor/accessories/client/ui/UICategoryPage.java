/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.ui;

import java.util.ArrayList;
import java.util.List;
import net.frozor.accessories.client.ClientProxy;
import net.frozor.accessories.client.RenderHelper;
import net.frozor.accessories.client.item.AccessoryItem;
import net.frozor.accessories.client.item.CategoryType;
import net.frozor.accessories.client.item.animation.AbstractAnimation;
import net.frozor.accessories.client.ui.EnumAction;
import net.frozor.accessories.client.ui.IPage;
import net.frozor.accessories.client.ui.UIAccessorySidebar;
import net.frozor.accessories.client.ui.UIButton;
import net.frozor.accessories.client.ui.UIItem;
import net.frozor.accessories.client.ui.UIScroll;
import net.frozor.accessories.client.ui.UIViewerPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class UICategoryPage
implements IPage {
    private CategoryType categoryType;
    private long lastTime;
    private List<UIButton> buttons = new ArrayList<UIButton>();
    private UIItem hoveredItem;
    private float scaleFactor;
    public int numer = 0;
    private Minecraft mc;
    private List<UIItem> items = new ArrayList<UIItem>();
    private int lastScrollStageInit;
    private UIAccessorySidebar parent;
    public UIScroll scroll;
    private float rotation;

    @Override
    public void actionPerformed(GuiButton button) {
    }

    public void OnPageItems() {
        this.items.clear();
        for (AccessoryItem item : ClientProxy.equipManager.items.get((Object)this.categoryType).values()) {
            this.items.add(new UIItem(item, this.scaleFactor));
        }
    }

    @Override
    public EnumAction mouseClicked(int mouseX, int mouseY, int click) {
        for (UIButton button : this.buttons) {
            if (!button.mousePressed(this.mc, mouseX, mouseY) || button.getAction() == EnumAction.NONE) continue;
            button.func_146113_a(this.mc.getSoundHandler());
            return button.getAction();
        }
        if (this.hoveredItem != null) {
            AccessoryItem item = this.hoveredItem.getItem();
            UIViewerPage viewerPage = new UIViewerPage(this.parent, item);
            viewerPage.init(this.parent.marginLeft, this.parent.marginTop, this.scaleFactor);
            viewerPage.setLastStagePage(this.scroll.getStage());
            this.parent.page = viewerPage;
            switch (item.getType()) {
                case HEAD: {
                    ClientProxy.equipManager.getPreviewData().setHEAD(item);
                    break;
                }
                case FACE: {
                    ClientProxy.equipManager.getPreviewData().setFACE(item);
                    break;
                }
                case BODY: {
                    ClientProxy.equipManager.getPreviewData().setBODY(item);
                    break;
                }
                case FAMILIAR: {
                    ClientProxy.equipManager.getPreviewData().setFAMILIAR(item);
                }
            }
            return EnumAction.VIEW;
        }
        return EnumAction.NONE;
    }

    @Override
    public String getPageName() {
        return this.categoryType.getName();
    }

    public UICategoryPage(CategoryType categoryType, UIAccessorySidebar parent) {
        this.categoryType = categoryType;
        this.scaleFactor = 1.0f;
        this.mc = Minecraft.getMinecraft();
        this.parent = parent;
        this.lastScrollStageInit = -1;
    }

    public UICategoryPage(CategoryType categoryType, UIAccessorySidebar parent, int lastScrollStage) {
        this.categoryType = categoryType;
        this.scaleFactor = 1.0f;
        this.mc = Minecraft.getMinecraft();
        this.parent = parent;
        this.lastScrollStageInit = lastScrollStage;
    }

    @Override
    public void handleMouseInput() {
        int wheelState = Mouse.getEventDWheel();
        if (wheelState != 0) {
            int newStage = 0;
            newStage = wheelState > 0 ? Math.max(0, this.scroll.getStage() - 1) : Math.min(this.scroll.getMaxStage(), this.scroll.getStage() + 1);
            this.scroll.setStage(newStage);
        }
    }

    @Override
    public int getMarginLeft() {
        return 120;
    }

    @Override
    public void drawPage(Minecraft mc, float marginLeft, float marginTop, int mouseX, int mouseY, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(marginLeft + 82.0f * this.scaleFactor), (float)marginTop, (float)0.0f);
        GL11.glScalef((float)this.scaleFactor, (float)this.scaleFactor, (float)this.scaleFactor);
        RenderHelper.drawRect(163.0, 0.0, 0.0, 121.0, 0x59000000);
        RenderHelper.drawRect(162.0, 0.0, 0.0, 120.0, 0x59000000);
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
        mc.fontRenderer.drawStringWithShadow(this.categoryType.getName(), -(mc.fontRenderer.getStringWidth(this.categoryType.getName()) / 2), 0, -1);
        GL11.glPopMatrix();
        if (this.rotation >= 360.0f) {
            this.rotation = 0.0f;
        }
        long time = System.nanoTime();
        double deltaTime = (double)(time - this.lastTime) / 1.0E9;
        this.lastTime = time;
        this.rotation = (float)((double)this.rotation + deltaTime * 40.0);
        GL11.glTranslatef((float)2.0f, (float)16.0f, (float)0.0f);
        int rows = 0;
        int count = 0;
        for (int i = this.numer = this.scroll.getStage() * 15; i < this.numer + 15 && i < this.items.size(); ++i) {
            boolean hovered;
            boolean bl = hovered = (float)mouseX >= marginLeft + 82.0f * this.scaleFactor + 2.0f * this.scaleFactor + (float)count * (32.0f * this.scaleFactor) && (float)mouseY >= marginTop + 16.0f * this.scaleFactor + (float)rows * (32.0f * this.scaleFactor) && (float)mouseX < marginLeft + 82.0f * this.scaleFactor + 2.0f * this.scaleFactor + (float)count * (32.0f * this.scaleFactor) + 30.0f * this.scaleFactor && (float)mouseY < marginTop + 16.0f * this.scaleFactor + (float)rows * (32.0f * this.scaleFactor) + 30.0f * this.scaleFactor;
            if (hovered) {
                this.hoveredItem = this.items.get(i);
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(32 * count), (float)(32 * rows), (float)0.0f);
            if (this.items.get(i).getItem().isHas()) {
                RenderHelper.drawRect(30.0, 0.0, 0.0, 30.0, hovered ? 654105381 : 452778789);
                RenderHelper.drawRect(29.0, 1.0, 1.0, 29.0, hovered ? 1727847205 : 1308416805);
            } else {
                RenderHelper.drawRect(30.0, 0.0, 0.0, 30.0, hovered ? 0x26FFFFFF : 0x1AFFFFFF);
                RenderHelper.drawRect(29.0, 1.0, 1.0, 29.0, hovered ? 0x26FFFFFF : 0x1AFFFFFF);
            }
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)50.0f);
            if (this.items.get(i).getItem().isNews()) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)-1.0f, (float)-1.0f, (float)0.0f);
                GL11.glEnable((int)3042);
                GL11.glEnable((int)3008);
                RenderHelper.drawRect(14.0, 0.0, 0.0, 4.0, -65536);
                GL11.glScalef((float)0.425f, (float)0.425f, (float)0.425f);
                mc.fontRenderer.drawString(UIItem.l("\u0457\u041b\u0478\u046e\u0473"), 1, 1, -328966);
                GL11.glDisable((int)3042);
                GL11.glDisable((int)3008);
                GL11.glPopMatrix();
            }
            this.items.get(i).draw(mc, 0, 0, mouseX, mouseY, partialTicks, this.rotation);
            GL11.glPopMatrix();
            if (++count < 5) continue;
            ++rows;
            count = 0;
        }
        GL11.glTranslatef((float)160.0f, (float)0.0f, (float)0.0f);
        this.scroll.draw();
        GL11.glPopMatrix();
        for (UIButton button : this.buttons) {
            button.drawButton(mc, mouseX, mouseY);
        }
    }

    @Override
    public void init(float marginLeft, float marginTop, float scaleFactor) {
        this.scaleFactor = scaleFactor;
        this.buttons.clear();
        this.buttons.add(new UIButton(EnumAction.BACK, AbstractAnimation.l("\u043d\u0451\u0417\u0451\u0414"), (int)(marginLeft + 82.0f * scaleFactor), (int)(marginTop + 120.0f * scaleFactor + 3.0f), 47, 14, scaleFactor));
        this.scroll = new UIScroll((ClientProxy.equipManager.items.get((Object)this.categoryType).size() - 1) / 15);
        if (this.lastScrollStageInit != -1) {
            this.scroll.setStage(this.lastScrollStageInit);
            this.lastScrollStageInit = -1;
        }
        this.OnPageItems();
    }
}

