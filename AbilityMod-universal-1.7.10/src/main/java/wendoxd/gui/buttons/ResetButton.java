package wendoxd.gui.buttons;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import wendoxd.events.ClientEvents;
import wendoxd.gui.Animation;
import wendoxd.gui.GuiAbilities;
import wendoxd.init.AbilityMod;
import wendoxd.modules.Ability;
import wendoxd.utils.CustomFont;
import wendoxd.utils.CustomFontRenderer;
import wendoxd.utils.Util;

public class ResetButton extends CustomGuiButton {

	public ResetButton(int x, int y, int width, int height, String desc) {
		super(new Random().nextInt(10000) + 10000, x, y, width, height, desc);
		anims.add(new Animation(0, 20));
	}

	public void drawButton(Minecraft mc, int x, int y) {
		int anim = (int) (getAnim(0).intValue());
		Util.drawGradientRect(this.xPosition + width, this.yPosition, this.xPosition + width + 2,
				this.yPosition + height, Util.color(97, 37, 29, 255), Util.color(120, 47, 39, 255));
		Util.drawGradientRect(this.xPosition, this.yPosition, this.xPosition + width, this.yPosition + height,
				Util.color(30 + anim, 29 + anim, 30 + anim, 255), Util.color(30 + anim, 29 + anim, 30 + anim, 255));
		CustomFontRenderer.drawString(displayString,
				this.xPosition + width / 2 - CustomFontRenderer.getStringWidth(CustomFont.gui, displayString) / 2 - 2,
				this.yPosition + 1, CustomFont.gui);
	}

	public boolean mousePressed(Minecraft mc, int x, int y) {
		boolean click = this.enabled && this.visible && x >= this.xPosition && y >= this.yPosition
				&& x < this.xPosition + this.width && y < this.yPosition + this.height;
		if (click) {
			anims.get(0).value = 0;
			AbilityMod.network.sendToServer(AbilityMod.network.createPacket(2, 0));
		}
		return click;
	}

}
