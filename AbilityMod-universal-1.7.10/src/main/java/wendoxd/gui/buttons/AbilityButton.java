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

public class AbilityButton extends CustomGuiButton {
	public Ability ability;
	public int currentLvl;
	public int maxLvl;
	public String desc;
	public int startX;

	public AbilityButton(int x, int y, int width, int height, Ability ability) {
		super(new Random().nextInt(10000) + 10000, x, y, width, height, ability.name);
		this.ability = ability;
		this.desc = ability.desc;
		this.startX = y;
		if (ClientEvents.getAbility(ability) != null)
			currentLvl = ClientEvents.getAbility(ability).lvl + 1;
		maxLvl = ability.getSize();
		anims.add(new Animation(0, 15));
	}

	public void drawButton(Minecraft mc, int x, int y) {
		int anim = (int) (getAnim(0).intValue());
		this.yPosition = startX - GuiAbilities.slider.getValueInt();
		Util.drawGradientRect(this.xPosition, this.yPosition, this.xPosition + width - 4, this.yPosition + height,
				Util.color(30 + anim, 29 + anim, 30 + anim, 165), Util.color(30 + anim, 29 + anim, 30 + anim, 165));
		Util.drawGradientRect(this.xPosition + width - 4, this.yPosition, this.xPosition + width,
				this.yPosition + height, Util.color(90, 29, 30, 255), Util.color(90, 29, 30, 255));
		// line??
		float factor = ((float) currentLvl / (float) maxLvl);
		Util.drawGradientRect(this.xPosition + 5, this.yPosition + height - 17, this.xPosition + width - 8,
				this.yPosition + height - 11, Util.color(30, 29, 30, 255), Util.color(30, 29, 30, 255));
		Util.drawGradientRect(this.xPosition + 5, this.yPosition + height - 17, this.xPosition + 5 + factor * 118,
				this.yPosition + height - 11, Util.color(90, 29, 30, 255), Util.color(90, 29, 30, 255));
		// buy button
		Util.drawGradientRect(this.xPosition + width - 20, this.yPosition + height - 9, this.xPosition + width - 8,
				this.yPosition + height - 2, Util.color(90, 29, 30, 255), Util.color(90, 29, 30, 255));
		CustomFontRenderer.drawString("g", xPosition + width - 17, this.yPosition + height - 9,
				CustomFont.special_small);
		CustomFont.special_small = new CustomFont("font", 11);
		CustomFontRenderer.drawString(displayString.replace("Максимальное", "Макс."), this.xPosition + 4,
				this.yPosition + 3, CustomFont.title);
		CustomFont shit = new CustomFont("gui", 13);
		CustomFontRenderer.drawString(desc, this.xPosition + 5, this.yPosition + 13, shit);
		String lvl = currentLvl + "/" + maxLvl;
		CustomFontRenderer.drawString(lvl,
				this.xPosition + width / 2 - CustomFontRenderer.getStringWidth(shit, lvl) / 2 - 2, this.yPosition + 32,
				shit);
	}

	public boolean mousePressed(Minecraft mc, int x, int y) {
		boolean click = this.enabled && this.visible && x >= this.xPosition && y >= this.yPosition
				&& x < this.xPosition + this.width && y < this.yPosition + this.height;
		if (click) {
			boolean buy = x > this.xPosition + width - 20 && y > this.yPosition + height - 9
					&& x < this.xPosition + width - 8 && y < this.yPosition + height - 2;
			anims.get(0).value = 0;
			if (buy) {
				AbilityMod.network.sendToServer(AbilityMod.network.createPacket(0, ability.name));
			}
		}
		return click;
	}

}
