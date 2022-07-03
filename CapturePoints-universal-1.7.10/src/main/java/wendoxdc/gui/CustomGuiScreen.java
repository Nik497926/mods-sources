package wendoxdc.gui;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import wendoxdc.gui.buttons.CustomGuiButton;
import wendoxdc.gui.buttons.CustomTextField;
import wendoxdc.utils.CustomFont;
import wendoxdc.utils.CustomFontRenderer;

public abstract class CustomGuiScreen extends GuiScreen {

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
		super.updateScreen();
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

	public void drawBackGround() {

	}

}
