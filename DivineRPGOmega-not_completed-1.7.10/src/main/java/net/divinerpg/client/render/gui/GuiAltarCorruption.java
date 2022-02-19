/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.model.ModelBook
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnchantmentNameParts
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.Project
 */
package net.divinerpg.client.render.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.blocks.vanilla.container.ContainerAltarCorruption;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnchantmentNameParts;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

@SideOnly(value=Side.CLIENT)
public class GuiAltarCorruption
extends GuiContainer {
    private static final ResourceLocation tex1 = new ResourceLocation("textures/gui/container/enchanting_table.png");
    private static final ResourceLocation tex2 = new ResourceLocation("textures/entity/enchanting_table_book.png");
    private static final ModelBook bookModel = new ModelBook();
    private Random rand = new Random();
    private ContainerAltarCorruption container = (ContainerAltarCorruption)this.inventorySlots;
    public int flipPages;
    public float bookX;
    public float bookX1;
    public float bookY;
    public float bookY1;
    public float bookZ;
    public float bookZ1;
    private ItemStack itemstack;

    public GuiAltarCorruption(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5) {
        super((Container)new ContainerAltarCorruption(par1InventoryPlayer, par2World, par3, par4, par5));
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        this.fontRendererObj.drawString("Altar of Corruption", 12, 5, 0x404040);
        this.fontRendererObj.drawString(I18n.format((String)"container.inventory", (Object[])new Object[0]), 8, this.ySize - 96 + 2, 0x404040);
    }

    public void updateScreen() {
        super.updateScreen();
        ItemStack itemstack = this.inventorySlots.getSlot(0).getStack();
        if(!ItemStack.areItemStacksEqual(itemstack, this.itemstack)) {
            this.itemstack = itemstack;
            do this.bookY += (float)(this.rand.nextInt(4) - this.rand.nextInt(4));
            while (this.bookX <= this.bookY + 1.0F && this.bookX >= this.bookY - 1.0F);
        }

        ++this.flipPages;
        this.bookX1 = this.bookX;
        this.bookZ1 = this.bookZ;
        boolean flag = false;

        for(int i = 0; i < 3; ++i) {
            if(this.container.enchantLevels[i] != 0)
                flag = true;
        }

        if(flag) this.bookZ += 0.2F;
        else this.bookZ -= 0.2F;
        if(this.bookZ < 0.0F) this.bookZ = 0.0F;
        if(this.bookZ > 1.0F) this.bookZ = 1.0F;
        float f1 = (this.bookY - this.bookX) * 0.4F;
        float f = 0.2F;
        if(f1 < -f) f1 = -f;
        if(f1 > f) f1 = f;
        this.bookY1 += (f1 - this.bookY1) * 0.9F;
        this.bookX += this.bookY1;
    }

    protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);
        int l = (this.width - this.xSize) / 2;
        int i1 = (this.height - this.ySize) / 2;
        for (int j1 = 0; j1 < 3; ++j1) {
            int k1 = par1 - (l + 60);
            int l1 = par2 - (i1 + 14 + 19 * j1);
            if (k1 < 0 || l1 < 0 || k1 >= 108 || l1 >= 19 || !this.container.enchantItem((EntityPlayer)this.mc.thePlayer, j1)) continue;
            this.mc.playerController.sendEnchantPacket(this.container.windowId, j1);
        }
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(tex1);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        GL11.glPushMatrix();
        GL11.glMatrixMode((int)5889);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        GL11.glViewport((int)((scaledresolution.getScaledWidth() - 320) / 2 * scaledresolution.getScaleFactor()), (int)((scaledresolution.getScaledHeight() - 240) / 2 * scaledresolution.getScaleFactor()), (int)(320 * scaledresolution.getScaleFactor()), (int)(240 * scaledresolution.getScaleFactor()));
        GL11.glTranslatef((float)-0.34f, (float)0.23f, (float)0.0f);
        Project.gluPerspective((float)90.0f, (float)1.3333334f, (float)9.0f, (float)80.0f);
        float f1 = 1.0f;
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        RenderHelper.enableStandardItemLighting();
        GL11.glTranslatef((float)0.0f, (float)3.3f, (float)-16.0f);
        GL11.glScalef((float)f1, (float)f1, (float)f1);
        float f2 = 5.0f;
        GL11.glScalef((float)f2, (float)f2, (float)f2);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(tex2);
        GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        float f3 = this.bookZ1 + (this.bookZ - this.bookZ1) * f;
        GL11.glTranslatef((float)((1.0f - f3) * 0.2f), (float)((1.0f - f3) * 0.1f), (float)((1.0f - f3) * 0.25f));
        GL11.glRotatef((float)((-1.0f - f3) * 90.0f - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        float f4 = this.bookX1 + (this.bookX - this.bookX1) * f + 0.25f;
        float f5 = this.bookX1 + (this.bookX - this.bookX1) * f + 0.75f;
        f4 = (f4 - (float)MathHelper.truncateDoubleToInt((double)f4)) * 1.6f - 0.3f;
        f5 = (f5 - (float)MathHelper.truncateDoubleToInt((double)f5)) * 1.6f - 0.3f;
        if (f4 < 0.0f) {
            f4 = 0.0f;
        }
        if (f5 < 0.0f) {
            f5 = 0.0f;
        }
        if (f4 > 1.0f) {
            f4 = 1.0f;
        }
        if (f5 > 1.0f) {
            f5 = 1.0f;
        }
        GL11.glEnable((int)32826);
        bookModel.render((Entity)null, 0.0f, f4, f5, f3, 0.0f, 0.0625f);
        GL11.glDisable((int)32826);
        RenderHelper.disableStandardItemLighting();
        GL11.glMatrixMode((int)5889);
        GL11.glViewport((int)0, (int)0, (int)this.mc.displayWidth, (int)this.mc.displayHeight);
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5888);
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        EnchantmentNameParts.instance.reseedRandomGenerator(this.container.nameSeed);
        for (int i1 = 0; i1 < 3; ++i1) {
            String s = EnchantmentNameParts.instance.generateNewRandomName();
            this.zLevel = 0.0f;
            this.mc.getTextureManager().bindTexture(tex1);
            int j1 = this.container.enchantLevels[i1];
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            if (j1 == 0) {
                this.drawTexturedModalRect(k + 60, l + 14 + 19 * i1, 0, 185, 108, 19);
                continue;
            }
            String s1 = "" + j1;
            FontRenderer fontrenderer = this.mc.standardGalacticFontRenderer;
            int k1 = 6839882;
            if (this.mc.thePlayer.experienceLevel < j1 && !this.mc.thePlayer.capabilities.isCreativeMode) {
                this.drawTexturedModalRect(k + 60, l + 14 + 19 * i1, 0, 185, 108, 19);
                fontrenderer.drawSplitString(s, k + 62, l + 16 + 19 * i1, 104, (k1 & 0xFEFEFE) >> 1);
                fontrenderer = this.mc.fontRenderer;
                k1 = 4226832;
                fontrenderer.drawStringWithShadow(s1, k + 62 + 104 - fontrenderer.getStringWidth(s1), l + 16 + 19 * i1 + 7, k1);
                continue;
            }
            int l1 = i - (k + 60);
            int i2 = j - (l + 14 + 19 * i1);
            if (l1 >= 0 && i2 >= 0 && l1 < 108 && i2 < 19) {
                this.drawTexturedModalRect(k + 60, l + 14 + 19 * i1, 0, 204, 108, 19);
                k1 = 0xFFFF80;
            } else {
                this.drawTexturedModalRect(k + 60, l + 14 + 19 * i1, 0, 166, 108, 19);
            }
            fontrenderer.drawSplitString(s, k + 62, l + 16 + 19 * i1, 104, k1);
            fontrenderer = this.mc.fontRenderer;
            k1 = 8453920;
            fontrenderer.drawStringWithShadow(s1, k + 62 + 104 - fontrenderer.getStringWidth(s1), l + 16 + 19 * i1 + 7, k1);
        }
    }
}

