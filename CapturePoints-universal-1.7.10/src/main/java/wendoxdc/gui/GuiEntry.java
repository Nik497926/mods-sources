package wendoxdc.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.config.GuiSlider;
import cpw.mods.fml.client.config.GuiSlider.ISlider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import wendoxdc.gui.buttons.CustomGuiButton;
import wendoxdc.gui.buttons.CustomTextField;
import wendoxdc.init.CaptureMod;
import wendoxdc.modules.Capture;
import wendoxdc.utils.CustomFont;
import wendoxdc.utils.CustomFontRenderer;
import wendoxdc.utils.Util;

public class GuiEntry extends CustomGuiScreen {
	public static CustomTextField name, x, z, radius, color, maxTimeCapt, cooldown, minOnline, radiusCapt, maxHeight,
			itemsDrop, itemRemoveList, addItem, removePoint;

	@Override
	public void initGui() {
		this.buttonList.clear();
		this.textFields.clear();
		Minecraft.getMinecraft().gameSettings.guiScale = 2;
		Keyboard.enableRepeatEvents(true);
		this.textFields.add(name = new CustomTextField(width / 2 - 225, height / 2 - 70, 80, 15));
		this.textFields.add(x = new CustomTextField(width / 2 - 225, height / 2 - 35, 80, 15));
		this.textFields.add(z = new CustomTextField(width / 2 - 225, height / 2, 80, 15));
		this.textFields.add(radius = new CustomTextField(width / 2 - 225, height / 2 + 35, 80, 15));
		this.textFields.add(color = new CustomTextField(width / 2 - 225, height / 2 + 70, 80, 15));
		this.textFields.add(maxTimeCapt = new CustomTextField(width / 2 - 225, height / 2 + 105, 80, 15));
		this.textFields.add(cooldown = new CustomTextField(width / 2 - 100, height / 2 + 35, 80, 15));
		this.textFields.add(minOnline = new CustomTextField(width / 2 - 100, height / 2 + 70, 80, 15));
		this.textFields.add(radiusCapt = new CustomTextField(width / 2 - 100, height / 2 + 105, 80, 15));
		this.textFields.add(maxHeight = new CustomTextField(width / 2 + 25, height / 2 + 70, 80, 15));
		this.textFields.add(itemsDrop = new CustomTextField(width / 2 + 25, height / 2 + 105, 80, 15));
		this.textFields.add(itemRemoveList = new CustomTextField(width / 2 + 130, height / 2 - 105, 80, 15));
		this.textFields.add(addItem = new CustomTextField(width / 2 + 130, height / 2 - 40, 80, 15));
		this.textFields.add(removePoint = new CustomTextField(width / 2 + 130, height / 2 + 20, 80, 15));
		this.buttonList.add(new GuiButton(1, width / 2 - 130, height / 2 - 75, 100, 20, "оформить вкид"));
		this.buttonList.add(new GuiButton(2, width / 2 + 130, height / 2 - 75, 100, 20, "удалить предметы"));
		this.buttonList.add(new GuiButton(3, width / 2 + 130, height / 2 - 15, 100, 20, "добавить айтемус"));
		this.buttonList.add(new GuiButton(4, width / 2 + 130, height / 2 + 40, 100, 20, "удалить поинт"));
	}

	@Override
	public void drawScreen(int x, int y, float part) {
		Util.drawRect(width / 2 - 250, height / 2 - 120, width / 2 + 250, height / 2 + 150,
				Util.color(26, 29, 38, 230));
		Util.drawRect(width / 2 - 250, height / 2 - 150, width / 2 + 250, height / 2 - 120,
				Util.color(27, 31, 39, 255));
		Util.drawRect(width / 2 - 240, height / 2 - 110, width / 2 + 240, height / 2 + 140,
				Util.color(26, 29, 38, 255));
		CustomFontRenderer.drawString("ZonixCraft админ меню каптов гыгы гага", width / 2 - 240, height / 2 - 140,
				CustomFont.title);
		CustomFontRenderer.drawString("Добавить капт ура ура оле оле росия в перед", width / 2 - 225, height / 2 - 100,
				CustomFont.title);
		CustomFontRenderer.drawString("имя капта ок?", width / 2 - 225, height / 2 - 85, CustomFont.gui);
		CustomFontRenderer.drawString("этот x который понял ну типа x корды ну если ты не понял ты даун",
				width / 2 - 225, height / 2 - 50, CustomFont.gui);
		CustomFontRenderer.drawString("этот z который понял ну типа z корды ну если ты не понял ты даун",
				width / 2 - 225, height / 2 - 15, CustomFont.gui);
		CustomFontRenderer.drawString("это радиус ок", width / 2 - 225, height / 2 + 20, CustomFont.gui);
		CustomFontRenderer.drawString("это цвет ок hex", width / 2 - 225, height / 2 + 55, CustomFont.gui);
		CustomFontRenderer.drawString("время для захвата точки в сек.", width / 2 - 225, height / 2 + 90,
				CustomFont.gui);
		CustomFontRenderer.drawString("кулдаун точки в сек.", width / 2 - 100, height / 2 + 20, CustomFont.gui);
		CustomFontRenderer.drawString("минимальный онлайн", width / 2 - 100, height / 2 + 55, CustomFont.gui);
		CustomFontRenderer.drawString("радиус захвата точки", width / 2 - 100, height / 2 + 90, CustomFont.gui);
		CustomFontRenderer.drawString("макс. высота", width / 2 + 25, height / 2 + 55, CustomFont.gui);
		CustomFontRenderer.drawString("макс. колво айтемов", width / 2 + 25, height / 2 + 90, CustomFont.gui);
		CustomFontRenderer.drawString("Имя капта", width / 2 + 130, height / 2 - 87, CustomFont.gui);
		CustomFontRenderer.drawString("Имя капта", width / 2 + 130, height / 2 - 25, CustomFont.gui);
		CustomFontRenderer.drawString("Имя капта", width / 2 + 130, height / 2 + 10, CustomFont.gui);
		super.drawScreen(x, y, part);

	}

	@Override
	public void actionPerformed(GuiButton button) {
		if (button.id == 1) {
			try {
				CaptureMod.network.sendToServer(CaptureMod.network.createPacket(0, name.getText(),
						Integer.valueOf(x.getText()), Integer.valueOf(z.getText()), Integer.valueOf(radius.getText()),
						Integer.valueOf(color.getText()), Integer.valueOf(cooldown.getText()),
						Integer.valueOf(minOnline.getText()), Integer.valueOf(radiusCapt.getText()),
						Integer.valueOf(maxHeight.getText()), Integer.valueOf(itemsDrop.getText()),
						Integer.valueOf(maxTimeCapt.getText())));
			} catch (Exception e) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(
						new ChatComponentText("миша ты тупая хуйня заполни по новой ex : " + e.getLocalizedMessage()));
			}
		}
		if (button.id == 2) {
			try {
				CaptureMod.network.sendToServer(CaptureMod.network.createPacket(1, itemRemoveList.getText()));
			} catch (Exception e) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(
						new ChatComponentText("миша ты тупая хуйня заполни по новой ex : " + e.getLocalizedMessage()));
			}
		}
		if (button.id == 3) {
			try {
				CaptureMod.network.sendToServer(CaptureMod.network.createPacket(2, addItem.getText()));
			} catch (Exception e) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(
						new ChatComponentText("миша ты тупая хуйня заполни по новой ex : " + e.getLocalizedMessage()));
			}
		}
		if (button.id == 4) {
			try {
				CaptureMod.network.sendToServer(CaptureMod.network.createPacket(3, removePoint.getText()));
			} catch (Exception e) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(
						new ChatComponentText("миша ты тупая хуйня заполни по новой ex : " + e.getLocalizedMessage()));
			}
		}
	}

	public void handleMouseInput() {
		super.handleMouseInput();
	}

	@Override
	public void mouseClicked(int x, int y, int button) {
		super.mouseClicked(x, y, button);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
	}

}
