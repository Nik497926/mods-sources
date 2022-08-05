/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.aspects;

import com.djgiannuzz.thaumcraftneiplugin.ModItems;
import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.tile.TileExtraAspect;
import java.awt.Color;
import java.util.Arrays;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;

@SideOnly(value= Side.CLIENT)
public class ClientContainer extends GuiContainer {
    private ResourceLocation guiTexture;
    private final TileExtraAspect te;
    private FontRenderer fonts = null;
    private int[][] c0 = new int[][]{{11, 9}, {53, 9}, {93, 9}, {134, 9}, {11, 21}, {53, 21}, {93, 21}, {134, 21}, {11, 33}, {53, 33}, {93, 33}, {134, 33}, {11, 45}, {53, 45}, {93, 45}, {134, 45}, {11, 57}, {53, 57}, {93, 57}, {134, 57}, {11, 69}, {53, 69}, {93, 69}, {134, 69}, {134, 82}};
    private int[] c1 = new int[]{11, 82, 20, 20};

    public ClientContainer(Container container, String guiTextureName, TileExtraAspect inventory) {
        super(container);
        this.te = inventory;
        this.xSize = 175;
        this.ySize = 256;
        this.guiTexture = new ResourceLocation(ExtraBotany.modid + ":textures/gui/aspects.png");
    }

    public FontRenderer getFontRender() {
        return this.fonts;
    }

    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
        if (this.guiTexture != null) {
            this.mc.getTextureManager().bindTexture(this.guiTexture);
            this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
            AspectList list = this.te.getAspects();
            Aspect[] l = list.getAspects();
            boolean uni = this.mc.fontRenderer.getUnicodeFlag();
            this.mc.fontRenderer.setUnicodeFlag(false);
            for (int i = 0; i < l.length; ++i) {
                this.renderVercital(l[i], list.getAmount(l[i]), i, mouseX, mouseY);
                this.renderItem(l[i], list.getAmount(l[i]), i);
            }
            this.mc.fontRenderer.setUnicodeFlag(uni);
        }
    }

    private void renderItem(Aspect asp, int count, int i) {
        if (asp == null) {
            return;
        }
        net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
        GL11.glEnable((int)32826);
        int[] c = this.genCoordItems(i);
        RenderItem.getInstance().renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, this.setItemsWithHUD(asp.getTag(), count), this.guiLeft + c[0], this.guiTop + c[1]);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)70.0f);
        this.mc.fontRenderer.drawString(Integer.toString(count), this.guiLeft + c[0], this.guiTop + c[1] + 12, new Color(0xFFFFFF).getRGB(), false);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)32826);
        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
    }

    private int[] genCoordItems(int i) {
        int[] in = new int[2];
        if (i < 6) {
            in[0] = this.c1[0] + i * 20;
            in[1] = this.c1[1];
            return in;
        }
        if (i < 12) {
            int k = i - 6;
            in[0] = this.c1[0] + k * 20;
            in[1] = this.c1[1] + 20;
        } else if (i < 18) {
            int k = i - 12;
            in[0] = this.c1[0] + k * 20;
            in[1] = this.c1[1] + 40;
        } else if (i < 24) {
            int k = i - 18;
            in[0] = this.c1[0] + k * 20;
            in[1] = this.c1[1] + 60;
        } else if (i < 32) {
            int k = i - 24;
            in[0] = this.c1[0] + k * 20;
            in[1] = this.c1[1] + 60;
        }
        return new int[]{-100000, -100000};
    }

    private void renderVercital(Aspect asp, int count, int i, int mx, int my) {
        if (asp == null) {
            return;
        }
        int m = MathHelper.floor_double((double)((double)count / (double)this.te.getMaxOneAsp() * 33.0));
        m = Math.max(0, Math.min(33, m));
        GL11.glPushMatrix();
        Color c = new Color(asp.getColor());
        GL11.glColor4f((float)((float)c.getRed() / 256.0f), (float)((float)c.getGreen() / 256.0f), (float)((float)c.getBlue() / 256.0f), (float)1.0f);
        this.mc.getTextureManager().bindTexture(this.guiTexture);
        RenderHelper.drawTexturedModalRect((int)(this.guiLeft + this.c0[i][0]), (int)(this.guiTop + this.c0[i][1]), (float)0.0f, (int)179, (int)1, (int)m, (int)6);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
        if (mx >= this.guiLeft + this.c0[i][0] && mx <= this.guiLeft + this.c0[i][0] + 26 && my >= this.guiTop + this.c0[i][1] && my <= this.guiTop + this.c0[i][1] + 6) {
            double d = (double)count / (double)this.te.getMaxOneAsp();
            RenderHelper.renderTooltip((int)mx, (int)my, Arrays.asList("\u0410\u0441\u043f\u0435\u043a\u0442: " + asp.getName(), "\u041a\u043e\u043b-\u0432\u043e: " + count, "\u0417\u0430\u043f\u043e\u043b\u043d\u0435\u043d \u043d\u0430: " + d + "%"));
        }
    }

    private ItemStack setItemsWithHUD(String key, int amount) {
        ItemStack var0 = new ItemStack(ModItems.itemAspect);
        NBTTagList _nbt = new NBTTagList();
        NBTTagCompound _n = new NBTTagCompound();
        _n.setInteger("amount", amount);
        _n.setString("key", key);
        _nbt.appendTag((NBTBase)_n);
        ItemNBTHelper.setList((ItemStack)var0, (String)"Aspects", (NBTTagList)_nbt);
        return var0;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fonts = this.fontRendererObj;
    }
}

