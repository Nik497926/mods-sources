package wendoxd.gui;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.config.GuiSlider;
import cpw.mods.fml.client.config.GuiSlider.ISlider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import wendoxd.events.ClientEvents;
import wendoxd.gui.buttons.CustomGuiButton;
import wendoxd.gui.buttons.CustomTextField;
import wendoxd.gui.buttons.AbilityButton;
import wendoxd.gui.buttons.ResetButton;
import wendoxd.gui.buttons.slider.ShopSlider;
import wendoxd.modules.Ability;
import wendoxd.utils.CustomFont;
import wendoxd.utils.CustomFontRenderer;
import wendoxd.utils.RenderPlayerUtil;
import wendoxd.utils.Util;

public class GuiAbilities extends CustomGuiScreen {
	public static ShopSlider slider;
	public static int timeInGui = 0;
	private boolean zoom = true;
	private float anim;

	@Override
	public void initGui() {
		timeInGui = 0;
		this.buttonList.clear();
		this.textFields.clear();
		Minecraft.getMinecraft().gameSettings.limitFramerate = 60;
		int i = 0;
		for (Ability a : ClientEvents.abilities) {
			this.buttonList.add(new AbilityButton(this.width / 2 - 165, height / 2 - 65 + (i * 44), 131, 42, a));
			i++;
		}
		if (slider != null) {
			slider.xPosition = width / 2 - 30;
			slider.yPosition = height / 2 - 65;
		}
		this.buttonList.add(slider == null
				? slider = new ShopSlider(0, width / 2 - 30, height / 2 - 65, 7, 165, "", "", 0, 276, 0, false, false)
				: slider);
		this.buttonList.add(new ResetButton(width / 2 + 40, height / 2 + 88, 80, 13, "Сбросить все"));
		Keyboard.enableRepeatEvents(true);
	}

	@Override
	public void drawScreen(int x, int y, float part) {
		timeInGui++;
		if (timeInGui < 90) {
			float y$ = 0.1f;
			if (zoom) {
				if (anim < 2) {
					anim += y$;
				} else {
					zoom = false;
				}
			} else {
				if (anim > 0) {
					anim -= y$;
				} else {
					zoom = true;
				}
			}
			Util.drawRect(0, 0, width, height, Util.color(20, 20, 20, 150));
			{
				CustomFont titleName = CustomFont.titleScreenName;
				String text = "ZonixCraft";
				CustomFontRenderer.drawString("§c" + text,
						width / 2 - CustomFontRenderer.getStringWidth(titleName, text) / 2, height / 2 - 20.1F + anim,
						titleName);
				CustomFontRenderer.drawString("§9" + text,
						width / 2 - CustomFontRenderer.getStringWidth(titleName, text) / 2 + anim - 1.5F,
						height / 2 - 20, titleName);
				CustomFontRenderer.drawString(text, width / 2 - CustomFontRenderer.getStringWidth(titleName, text) / 2,
						height / 2 - 20, titleName);
			}
			{
				CustomFont titleName = CustomFont.title;
				String text = "Загрузка меню";
				CustomFontRenderer.drawString(text, width / 2 - CustomFontRenderer.getStringWidth(titleName, text) / 2,
						height / 2 + 10, titleName);
			}
		} else {
			Util.drawRect(0, 0, width, height, Util.color(30, 30, 30, 100));
			double wheel = -Mouse.getDWheel();
			if (wheel > 1)
				wheel = 1;
			if (wheel < -1)
				wheel = -1;
			double newValue = slider.sliderValue + wheel / 15;
			if (newValue > 1)
				newValue = 1;
			if (newValue < 0)
				newValue = 0;
			slider.sliderValue = newValue;
			CustomGuiButton.mouseX = x;
			CustomGuiButton.mouseY = y;
			float widthmenu = 180;
			float heightmenu = 115;
			// main
			Util.drawGradientRect(width / 2 - widthmenu, height / 2 - heightmenu, width / 2 + widthmenu,
					height / 2 + heightmenu, Util.color(37, 20, 33, 105), Util.color(29, 16, 30, 105));
			// left title
			Util.drawGradientRect(width / 2 - widthmenu + 10, height / 2 - heightmenu + 9, width / 2 - widthmenu + 100,
					height / 2 - heightmenu + 37, Util.color(20, 12, 19, 135), Util.color(20, 12, 19, 135));
			// right title
			Util.drawGradientRect(width / 2 - widthmenu + 110, height / 2 - heightmenu + 9, width / 2 + widthmenu - 8,
					height / 2 - heightmenu + 37, Util.color(20, 12, 19, 135), Util.color(20, 12, 19, 135));
			// exp
			Util.drawGradientRect(width / 2 - widthmenu + 115, height / 2 - heightmenu + 19, width / 2 + widthmenu - 14,
					height / 2 - heightmenu + 26, Util.color(20, 20, 20, 125), Util.color(20, 20, 20, 125));
			Util.drawGradientRect(width / 2 - widthmenu + 115, height / 2 - heightmenu + 19,
					width / 2 - widthmenu + 115 + ((float) 231 / (float) Ability.maxLvls) * ClientEvents.currentLvls,
					height / 2 - heightmenu + 26, Util.color(97, 37, 29, 255), Util.color(120, 47, 39, 255));
			{
				String exp = "§f" + ClientEvents.currentLvls + " из " + Ability.maxLvls;
				CustomFontRenderer.drawString(exp,
						width / 2 + (int) widthmenu - 130
								- CustomFontRenderer.getStringWidth(CustomFont.guiprice, exp) / 2,
						height / 2 - (int) heightmenu + 19, CustomFont.guiprice);
			}
			// left ab
			Util.drawGradientRect(width / 2 - widthmenu + 10, height / 2 - heightmenu + 43, width / 2 - widthmenu + 160,
					height / 2 + heightmenu - 9, Util.color(20, 12, 19, 135), Util.color(20, 12, 19, 135));
			// right info
			Util.drawGradientRect(width / 2 - widthmenu + 165, height / 2 - heightmenu + 43, width / 2 + widthmenu - 8,
					height / 2 + heightmenu - 9, Util.color(20, 12, 19, 135), Util.color(20, 12, 19, 135));
			String lvl = "§c" + Minecraft.getMinecraft().thePlayer.experienceLevel + " §fУРОВЕНЬ";
			CustomFontRenderer.drawString(lvl,
					width / 2 - (int) widthmenu + 54 - CustomFontRenderer.getStringWidth(CustomFont.title, lvl) / 2,
					height / 2 - (int) heightmenu + 13, CustomFont.title);
			String name = "Игрок " + Minecraft.getMinecraft().thePlayer.getCommandSenderName();
			CustomFontRenderer.drawString(name,
					width / 2 - (int) widthmenu + 54 - CustomFontRenderer.getStringWidth(CustomFont.gui, name) / 2,
					height / 2 - (int) heightmenu + 24, CustomFont.gui);
			RenderPlayerUtil.drawPlayerModel(width / 2 + 80, height / 2 - 5, 30, 0, 0,
					Minecraft.getMinecraft().thePlayer);
			int i = 0;
			for (Ability a : ClientEvents.abilities) {
				Util.drawGradientRect(width / 2 + ((i / 5) * 80) + 5, height / 2 + 10 + ((i % 5) * 17),
						width / 2 + 75 + ((i / 5) * 80), height / 2 + 15 + ((i % 5) * 17), Util.color(20, 20, 20, 125),
						Util.color(20, 20, 20, 125));
				Ability my = ClientEvents.getAbility(a);
				double maxModifier = a.modifiers.get(a.modifiers.size() - 1);
				boolean opened = my != null;
				double modifier = 1;
				double perc = 0;
				try {
					if (opened) {
						modifier = Ability.getModifier(a, my);
						perc = (modifier / maxModifier);
					} else {
						perc = 1;
					}
					if (opened)
						Util.drawGradientRect(width / 2 + ((i / 5) * 80) + 5, height / 2 + 10 + ((i % 5) * 17),
								width / 2 + 5 + ((i / 5) * 80) + (perc * 70), height / 2 + 15 + ((i % 5) * 17),
								Util.color(97, 37, 29, 255), Util.color(120, 47, 39, 255));
				} catch (Exception e) {
					e.printStackTrace();
				}
				String ab = "§f" + a.name;
				CustomFontRenderer.drawString(ab.replace("Максимальное", "Макс.").replace("перемещения", ""),
						width / 2 + ((i / 5) * 80) + 5, height / 2 + ((i % 5) * 17), CustomFont.gui);
				CustomFontRenderer.drawString(modifier + "/" + maxModifier, width / 2 + ((i / 5) * 80) + 6,
						height / 2 + ((i % 5) * 17) + 9, new CustomFont("gui", 10));
				i++;
			}
			GL11.glEnable(GL11.GL_SCISSOR_TEST);
			glScissor(0, height / 2 - 65, width, 165);
			super.drawScreen(x, y, part);
			GL11.glDisable(GL11.GL_SCISSOR_TEST);
			if (y > height / 2 - 65 && y < height / 2 - 65 + 165) {
				for (GuiButton buttons : (ArrayList<GuiButton>) this.buttonList) {
					if (buttons instanceof CustomGuiButton) {
						CustomGuiButton b = (CustomGuiButton) buttons;
						if (b.outlined() && b instanceof AbilityButton) {
							String desc = "";
							AbilityButton a = (AbilityButton) b;
							try {
								desc = "Стоимость " + ((AbilityButton) b).ability.costs.get(a.currentLvl) + " лвл(ов)";
							} catch (Exception e) {
								if (e instanceof IndexOutOfBoundsException)
									desc = "Макс. уровень";
							}
							Util.drawGradientRect(x + 10, y - 5,
									x + CustomFontRenderer.getStringWidth(CustomFont.gui, desc) + 14, y + 10,
									Util.color(30, 30, 30, 185), Util.color(30, 30, 30, 185));
							CustomFontRenderer.drawString(desc, x + 12, y - 2, CustomFont.gui);
						}
					}
				}
			}
		}
	}

	@Override
	public void actionPerformed(GuiButton button) {
	}

	public void handleMouseInput() {
		super.handleMouseInput();
	}

	@Override
	public void mouseClicked(int x, int y, int button) {
		if (timeInGui < 90)
			return;
		super.mouseClicked(x, y, button);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
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

}
