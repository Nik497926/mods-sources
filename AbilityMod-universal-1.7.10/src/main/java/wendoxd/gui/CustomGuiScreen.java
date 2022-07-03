package wendoxd.gui;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import wendoxd.gui.buttons.CustomGuiButton;
import wendoxd.gui.buttons.CustomTextField;
import wendoxd.init.AbilityMod;
import wendoxd.utils.CustomFont;
import wendoxd.utils.CustomFontRenderer;
import wendoxd.utils.Util;

public abstract class CustomGuiScreen extends GuiScreen {
	public static int oldGuiScale, tab;
	public GuiScreen callback;
	public ArrayList<CustomTextField> textFields = new ArrayList<CustomTextField>();
	public Minecraft mc = Minecraft.getMinecraft();

	public void keyTyped(char c, int a) {
		if (a == Keyboard.KEY_ESCAPE && callback != null) {
			mc.displayGuiScreen(callback);
			return;
		}
		super.keyTyped(c, a);
		textFields.forEach(w -> {
			w.textboxKeyTyped(c, a);
		});
	}

	public void drawScreen(int x, int y, float part) {
		CustomGuiButton.mouseX = x;
		CustomGuiButton.mouseY = y;
		super.drawScreen(x, y, part);
		textFields.forEach(w -> {
			w.drawTextBox();
		});
	}

	@Override
	public void updateScreen() {
		textFields.forEach(w -> {
			w.updateCursorCounter();
		});
		for (GuiButton b : (ArrayList<GuiButton>) this.buttonList) {
			if (b instanceof CustomGuiButton) {
				CustomGuiButton button = (CustomGuiButton) b;
				button.update();
			}
		}
	}

	@Override
	public void mouseClicked(int x, int y, int button) {
		super.mouseClicked(x, y, button);
		textFields.forEach(w -> {
			w.mouseClicked(x, y, button);
		});
	}

	public boolean doesGuiPauseGame() {
		return false;
	}
}
