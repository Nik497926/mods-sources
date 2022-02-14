/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.IMerchant
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.C17PacketCustomPayload
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.village.MerchantRecipe
 *  net.minecraft.village.MerchantRecipeList
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import net.divinerpg.blocks.base.tileentity.container.ContainerDivineMerchant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiDivineMerchant
extends GuiContainer {
    private static final Logger logger = LogManager.getLogger();
    private IMerchant theIMerchant;
    private ModMerchantButton nextRecipeButtonIndex;
    private ModMerchantButton previousRecipeButtonIndex;
    private int currentRecipeIndex = 0;
    protected String name;
    private String texture;

    public GuiDivineMerchant(ContainerDivineMerchant container, IMerchant mer, String name, String tex) {
        super((Container)container);
        this.theIMerchant = mer;
        this.texture = tex;
        this.name = name;
    }

    public void initGui() {
        super.initGui();
        int var1 = (this.width - this.xSize) / 2;
        int var2 = (this.height - this.ySize) / 2;
        this.nextRecipeButtonIndex = new ModMerchantButton(1, var1 + 120 + 27, var2 + 24 - 1, true, this.texture);
        this.buttonList.add(this.nextRecipeButtonIndex);
        this.previousRecipeButtonIndex = new ModMerchantButton(2, var1 + 36 - 19, var2 + 24 - 1, false, this.texture);
        this.buttonList.add(this.previousRecipeButtonIndex);
        this.nextRecipeButtonIndex.enabled = false;
        this.previousRecipeButtonIndex.enabled = false;
    }

    protected void drawGuiContainerForegroundLayer(int var1, int var2) {
        this.fontRendererObj.drawString(this.name, 56, 6, 0x404040);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, this.ySize - 96 + 2, 0x404040);
    }

    public void updateScreen() {
        MerchantRecipeList var1;
        super.updateScreen();
        if (this.theIMerchant != null && (var1 = this.theIMerchant.getRecipes((EntityPlayer)this.mc.thePlayer)) != null) {
            this.nextRecipeButtonIndex.enabled = this.currentRecipeIndex < var1.size() - 1;
            this.previousRecipeButtonIndex.enabled = this.currentRecipeIndex > 0;
        }
    }

    protected void actionPerformed(GuiButton var1) {
        try {
            boolean var2 = false;
            if (var1 == this.nextRecipeButtonIndex) {
                ++this.currentRecipeIndex;
                var2 = true;
            } else if (var1 == this.previousRecipeButtonIndex) {
                --this.currentRecipeIndex;
                var2 = true;
            }
            if (var2) {
                ((ContainerDivineMerchant)this.inventorySlots).setCurrentRecipeIndex(this.currentRecipeIndex);
                ByteArrayOutputStream var3 = new ByteArrayOutputStream();
                DataOutputStream var4 = new DataOutputStream(var3);
                var4.writeInt(this.currentRecipeIndex);
                this.mc.getNetHandler().addToSendQueue((Packet)new C17PacketCustomPayload("MC|TrSel", var3.toByteArray()));
            }
        }
        catch (Exception var6) {
            logger.error("Couldn't send trade info", (Throwable)var6);
        }
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        int i1;
        MerchantRecipe merchantrecipe;
        MerchantRecipeList merchantrecipelist;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("divinerpg:textures/gui/" + this.texture + ".png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        if (this.theIMerchant != null && (merchantrecipelist = this.theIMerchant.getRecipes((EntityPlayer)this.mc.thePlayer)) != null && !merchantrecipelist.isEmpty() && (merchantrecipe = (MerchantRecipe)merchantrecipelist.get(i1 = this.currentRecipeIndex)).isRecipeDisabled() && this.mc != null) {
            this.mc.getTextureManager().bindTexture(new ResourceLocation("divinerpg:textures/gui/" + this.texture + ".png"));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)2896);
            this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 21, 212, 0, 28, 21);
            this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 51, 212, 0, 28, 21);
        }
    }

    public void drawScreen(int par1, int par2, float par3) {
        MerchantRecipeList merchantrecipelist;
        super.drawScreen(par1, par2, par3);
        if (this.theIMerchant != null && this.mc.thePlayer != null && (merchantrecipelist = this.theIMerchant.getRecipes((EntityPlayer)this.mc.thePlayer)) != null && !merchantrecipelist.isEmpty() && this.mc.getTextureManager() != null) {
            int k = (this.width - this.xSize) / 2;
            int l = (this.height - this.ySize) / 2;
            int i1 = this.currentRecipeIndex;
            MerchantRecipe merchantrecipe = (MerchantRecipe)merchantrecipelist.get(i1);
            GL11.glPushMatrix();
            ItemStack itemstack = merchantrecipe.getItemToBuy();
            ItemStack itemstack1 = merchantrecipe.getSecondItemToBuy();
            ItemStack itemstack2 = merchantrecipe.getItemToSell();
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glDisable((int)2896);
            GL11.glEnable((int)32826);
            GL11.glEnable((int)2903);
            GL11.glEnable((int)2896);
            GuiDivineMerchant.itemRender.zLevel = 100.0f;
            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, k + 36, l + 24);
            itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, k + 36, l + 24);
            if (itemstack1 != null) {
                itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack1, k + 62, l + 24);
                itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack1, k + 62, l + 24);
            }
            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack2, k + 120, l + 24);
            itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack2, k + 120, l + 24);
            GuiDivineMerchant.itemRender.zLevel = 0.0f;
            GL11.glDisable((int)2896);
            if (this.func_146978_c(36, 24, 16, 16, par1, par2)) {
                this.renderToolTip(itemstack, par1, par2);
            } else if (itemstack1 != null && this.func_146978_c(62, 24, 16, 16, par1, par2)) {
                this.renderToolTip(itemstack1, par1, par2);
            } else if (this.func_146978_c(120, 24, 16, 16, par1, par2)) {
                this.renderToolTip(itemstack2, par1, par2);
            }
            GL11.glPopMatrix();
            GL11.glEnable((int)2896);
            GL11.glEnable((int)2929);
            RenderHelper.enableStandardItemLighting();
        }
    }

    public IMerchant getIMerchant() {
        return this.theIMerchant;
    }

    @SideOnly(value=Side.CLIENT)
    static class ModMerchantButton
    extends GuiButton {
        private final boolean rev;
        private static String texture;

        public ModMerchantButton(int par1, int par2, int par3, boolean par4, String tex) {
            super(par1, par2, par3, 12, 19, "");
            texture = tex;
            this.rev = par4;
        }

        public void drawButton(Minecraft mc, int x, int y) {
            if (this.visible) {
                mc.getTextureManager().bindTexture(new ResourceLocation("divinerpg:textures/gui/" + texture + ".png"));
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                boolean flag = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
                int k = 0;
                int l = 176;
                if (!this.enabled) {
                    l += this.width * 2;
                } else if (flag) {
                    l += this.width;
                }
                if (!this.rev) {
                    k += this.height;
                }
                this.drawTexturedModalRect(this.xPosition, this.yPosition, l, k, this.width, this.height);
            }
        }
    }
}

