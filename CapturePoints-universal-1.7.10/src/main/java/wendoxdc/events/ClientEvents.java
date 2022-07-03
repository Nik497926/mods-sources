package wendoxdc.events;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import wendoxdc.gui.GuiEntry;
import wendoxdc.init.CaptureMod;
import wendoxdc.modules.Capture;
import wendoxdc.utils.CustomFont;
import wendoxdc.utils.CustomFontRenderer;
import wendoxdc.utils.Util;

public class ClientEvents {
	public static ArrayList<Capture> captsInWorld = new ArrayList<Capture>();
	public static Capture target;
	public static float percent, currentPercent;

	@SubscribeEvent
	public void tick(TickEvent.ClientTickEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.theWorld != null && Keyboard.isKeyDown(Keyboard.KEY_INSERT) && CaptureMod.isOP)
			mc.displayGuiScreen(new GuiEntry());
		if (currentPercent < percent) {
			float modifier = Math.abs(percent - currentPercent);
			currentPercent = currentPercent + 0.04F * modifier;
		}
		if (currentPercent > percent) {
			float modifier = Math.abs(percent - currentPercent);
			currentPercent = (float) (currentPercent - 0.04F * modifier);
		}
		if (mc.thePlayer != null && mc.thePlayer.ticksExisted % 10 == 0) {
			double x = mc.thePlayer.posX;
			double y = mc.thePlayer.posY;
			double z = mc.thePlayer.posZ;
			boolean setted = false;
			for (Capture c : captsInWorld) {
				if (c.dimId == mc.thePlayer.dimension) {
					if (x > c.x - c.radius && z > c.z - c.radius && x < c.x + c.radius && z < c.z + c.radius) {
						target = c;
						setted = true;
					}
				}
			}
			if (!setted)
				target = null;
		}
	}

	@SubscribeEvent
	public void hud(RenderGameOverlayEvent event) {
		if (event.type == RenderGameOverlayEvent.ElementType.TEXT)
			if (target != null) {
				int width = event.resolution.getScaledWidth();
				int height = event.resolution.getScaledHeight();
				int widthinfo = 160;
				int heighttext = 25;
				Util.drawRect(width / 2 - widthinfo - 1, heighttext - 11, width / 2 + widthinfo + 1, heighttext + 1,
						Util.color(50, 50, 50, 255));
				Util.drawRect(width / 2 - widthinfo, heighttext - 10, width / 2 + widthinfo, heighttext,
						Util.color(20, 20, 20, 255));
				try {
					String display = "";
					boolean canCapt = false;
					if (target.currentCooldown < target.cooldown) {
						float currentCooldown = target.currentCooldown;
						if (currentCooldown > target.cooldown)
							currentCooldown = target.cooldown;
						int seconds = (int) (target.cooldown - currentCooldown);
						int minutes = 0;
						int hours = 0;
						while (seconds > 60) {
							seconds = seconds - 60;
							minutes++;
						}
						while (minutes > 60) {
							minutes = minutes - 60;
							hours++;
						}
						StringBuilder builder = new StringBuilder();
						if (hours != 0) {
							builder.append(" " + hours + " ч ");
						}
						if (minutes != 0) {
							builder.append(" " + minutes + " м");
						}
						builder.append(" " + seconds + " с");
						display = "Точка §c" + target.name + " §fбудет реактивирована через§c" + builder.toString();
						percent = currentCooldown / target.cooldown;
					} else {
						percent = (float) target.timeCapt / (float) target.maxTimeCapt;
						if (target.timeCapt == target.maxTimeCapt - 1)
							percent = 1;
						canCapt = true;
						if (target.targetPlayer.length() < 2)
							display = "Точка §c" + target.name + " §fготова к захвату";
						else
							display = "Игрок §c" + target.targetPlayer + " §fзахватит точку §c" + target.name + " §fчерез §c"
									+ (target.maxTimeCapt - target.timeCapt) + " с";

					}
					Util.drawGradientRect(width / 2 - (widthinfo * currentPercent), heighttext - 10 + 1, width / 2,
							heighttext - 1, target.color, target.color);
					Util.drawGradientRect(width / 2, heighttext - 10 + 1, width / 2 + (widthinfo * currentPercent),
							heighttext - 1, target.color, target.color);
					if (!canCapt) {
					}
					if (!target.onlineItsOk) {
						percent = 1;
						display = "Точка §c" + target.name + " §fактивируется при " + target.minOnline
								+ " человек на сервере.";
					}
					GL11.glPushMatrix();
					CustomFont font = new CustomFont("gui", 20);
					CustomFontRenderer.drawString(display,
							width / 2 - CustomFontRenderer.getStringWidth(font, display) / 2, 0, font);
					GL11.glPopMatrix();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
}
