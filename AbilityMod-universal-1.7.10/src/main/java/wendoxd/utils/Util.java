package wendoxd.utils;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;

public class Util {
	public static RenderItem itemRender = new RenderItem();

	public static void drawRect(float paramXStart, float paramYStart, float paramXEnd, float paramYEnd,
			int paramColor) {
		float alpha = (float) (paramColor >> 24 & 0xFF) / 255F;
		float red = (float) (paramColor >> 16 & 0xFF) / 255F;
		float green = (float) (paramColor >> 8 & 0xFF) / 255F;
		float blue = (float) (paramColor & 0xFF) / 255F;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);

		GL11.glPushMatrix();
		GL11.glColor4f(red, green, blue, alpha);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(paramXEnd, paramYStart);
		GL11.glVertex2d(paramXStart, paramYStart);
		GL11.glVertex2d(paramXStart, paramYEnd);
		GL11.glVertex2d(paramXEnd, paramYEnd);
		GL11.glEnd();
		GL11.glPopMatrix();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
	}

	public static int color(int r, int g, int b, int alpha) {
		if (r > 255)
			r = 255;
		if (g > 255)
			g = 255;
		if (b > 255)
			b = 255;
		if (r < 0)
			r = 0;
		if (g < 0)
			g = 0;
		if (b < 0)
			b = 0;
		if (alpha > 255)
			alpha = 255;
		return new Color(r, g, b, alpha).getRGB();
	}

	public static void drawItemStack(final ItemStack stack, final int x, final int y) {
		Minecraft mc = Minecraft.getMinecraft();
		GL11.glTranslatef(0.0f, 0.0f, 32.0f);
		itemRender.zLevel = 200.0f;
		FontRenderer font = null;
		if (stack != null) {
		}
		if (font == null) {
			font = mc.fontRenderer;
		}
		itemRender.renderItemAndEffectIntoGUI(font, mc.getTextureManager(), stack, x, y);
		GL11.glTranslatef(itemRender.zLevel = 0.0f, 0.0f, -32.0f);
		RenderHelper.disableStandardItemLighting();
	}

	public static void draw3DItem(ItemStack itemStack, float x, float y, float scale) {
		ItemStack is = itemStack.copy();
		is.stackSize = 1;
		itemStack.setItemDamage(itemStack.getItemDamage());
		EntityItem entityItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, is);
		entityItem.hoverStart = 0;
		GL11.glPushMatrix();

		GL11.glTranslatef(x, y, x);
		if (is.getItem() instanceof ItemBlock) {
			GL11.glRotatef(180, 0, 1, 0);
			GL11.glRotatef(180, 1, 0, 0);
		}
		GL11.glScalef(scale, scale, scale);
		RenderManager.instance.func_147939_a(entityItem, 0, 0, 0.2, 0,
				Minecraft.getMinecraft().thePlayer.ticksExisted % 360, false);

		GL11.glPopMatrix();
	}

	public static int getChance(ItemStack stack) {
		if (stack.stackTagCompound != null) {
			return stack.stackTagCompound.getInteger("chance");
		}
		return 0;
	}

	public static int getRarity(ItemStack stack) {
		if (stack.stackTagCompound != null) {
			return stack.stackTagCompound.getInteger("rarity");
		}
		return 0;
	}

	public static void setChance(ItemStack s, int chance) {
		if (s.stackTagCompound == null)
			s.stackTagCompound = new NBTTagCompound();
		s.stackTagCompound.setInteger("chance", chance);
	}

	public static void setRarity(ItemStack s, int rarity) {
		if (s.stackTagCompound == null)
			s.stackTagCompound = new NBTTagCompound();
		s.stackTagCompound.setInteger("rarity", rarity);
	}

	public static void setCost(ItemStack s, int cost) {
		if (s.stackTagCompound == null)
			s.stackTagCompound = new NBTTagCompound();
		s.stackTagCompound.setInteger("cost", cost);
	}

	public static int getCost(ItemStack s) {
		if (s.stackTagCompound != null)
			return s.stackTagCompound.getInteger("cost");
		return Integer.MAX_VALUE;
	}

	public static ItemStack getWonStack(ArrayList<ItemStack> stacks) {
		Random rnd = new Random();
		double totalChances = 0;
		for (ItemStack s : stacks) {
			int chance = getChance(s);
			totalChances = totalChances + chance;
		}
		ItemStack s$ = new ItemStack(Items.apple);
		int currentWin = rnd.nextInt(100);
		int lastChance = 100;
		ItemStack prev = null;
		for (ItemStack s : stacks) {
			int currentChance = (int) (getChance(s) / totalChances * 100);
			int w = Math.abs(currentWin - currentChance);
			if (lastChance > w) {
				s$ = s;
				lastChance = w;
			} else if (lastChance == w) {
				if (rnd.nextBoolean())
					s$ = s;
				else
					s$ = prev;
			}
			prev = s;
		}
		return s$;
	}

	public static void drawGradientRect(double par1, double par2, double par3, double par4, int col1, int col2) {
		float f = (float) (col1 >> 24 & 0xFF) / 255F;
		float f1 = (float) (col1 >> 16 & 0xFF) / 255F;
		float f2 = (float) (col1 >> 8 & 0xFF) / 255F;
		float f3 = (float) (col1 & 0xFF) / 255F;

		float f4 = (float) (col2 >> 24 & 0xFF) / 255F;
		float f5 = (float) (col2 >> 16 & 0xFF) / 255F;
		float f6 = (float) (col2 >> 8 & 0xFF) / 255F;
		float f7 = (float) (col2 & 0xFF) / 255F;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glShadeModel(GL11.GL_SMOOTH);

		GL11.glPushMatrix();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glVertex2d(par3, par4);
		GL11.glVertex2d(par3, par2);

		GL11.glColor4f(f5, f6, f7, f4);
		GL11.glVertex2d(par1, par2);
		GL11.glVertex2d(par1, par4);
		GL11.glEnd();
		GL11.glPopMatrix();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glShadeModel(GL11.GL_FLAT);
	}

	public static void glScissor(int x, int y, int width, int height) {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int scale = resolution.getScaleFactor();

		int scissorWidth = width * scale;
		int scissorHeight = height * scale;
		int scissorX = x * scale;
		int scissorY = mc.displayHeight - scissorHeight - (y * scale);

		GL11.glScissor(scissorX, scissorY, scissorWidth, scissorHeight);
	}

	public static boolean isOP(EntityPlayerMP player) {
		return MinecraftServer.getServer().getConfigurationManager().func_152596_g(player.getGameProfile());
	}

	public static void drawToolTip(ItemStack stack, int x, int y) {
		List list = stack.getTooltip(Minecraft.getMinecraft().thePlayer,
				Minecraft.getMinecraft().gameSettings.advancedItemTooltips);

		for (int k = 0; k < list.size(); ++k) {
			if (k == 0) {
				list.set(k, stack.getRarity().rarityColor + (String) list.get(k));
			} else {
				list.set(k, EnumChatFormatting.GRAY + (String) list.get(k));
			}
		}

		drawHoveringText(list, x, y, Minecraft.getMinecraft().fontRenderer);
	}

	public static void drawHoveringText(List p_146283_1_, int p_146283_2_, int p_146283_3_, FontRenderer font) {
		if (!p_146283_1_.isEmpty()) {
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			int k = 0;
			Iterator iterator = p_146283_1_.iterator();

			while (iterator.hasNext()) {
				String s = (String) iterator.next();
				int l = font.getStringWidth(s);

				if (l > k) {
					k = l;
				}
			}

			int j2 = p_146283_2_ + 12;
			int k2 = p_146283_3_ - 12;
			int i1 = 8;

			if (p_146283_1_.size() > 1) {
				i1 += 2 + (p_146283_1_.size() - 1) * 10;
			}

			/*
			 * if (j2 + k > width) { j2 -= 28 + k; }
			 * 
			 * if (k2 + i1 + 6 > height) { k2 = height - i1 - 6; }
			 */

			itemRender.zLevel = 300.0F;
			int j1 = -267386864;
			drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
			drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
			drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
			drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
			drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
			int k1 = 1347420415;
			int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
			drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
			drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
			drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
			drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

			for (int i2 = 0; i2 < p_146283_1_.size(); ++i2) {
				String s1 = (String) p_146283_1_.get(i2);
				font.drawStringWithShadow(s1, j2, k2, -1);

				if (i2 == 0) {
					k2 += 2;
				}

				k2 += 10;
			}

			itemRender.zLevel = 0.0F;
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			RenderHelper.enableStandardItemLighting();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		}
	}
}
