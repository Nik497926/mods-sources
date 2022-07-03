package wendoxd.gui.buttons;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import wendoxd.gui.Animation;

public class CustomGuiButton extends GuiButton {
	public static int mouseX, mouseY;
	public ArrayList<Animation> anims = new ArrayList<Animation>();
	public ArrayList desc = new ArrayList<String>();

	public CustomGuiButton(int id, int x, int y, int width, int height, String display) {
		super(id, x, y, width, height, display);
		anims.clear();
	}

	@Override
	public void drawButton(Minecraft mc, int x, int y) {

	}

	public void update() {
		for (Animation a : anims) {
			a.update(outlined());
		}
	}

	public boolean outlined() {
		return mouseX > this.xPosition && mouseY > this.yPosition && mouseX < this.xPosition + this.width
				&& mouseY < this.yPosition + this.height;
	}

	public Animation getAnim(int id) {
		return anims.get(id);
	}
}
