package wendoxd.utils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class CustomFontRenderer {
	public static ArrayList<String> strings = new ArrayList<String>();
	private static final Map cache = new HashMap();
	private static final Map colors = new HashMap();
	public static int guiScale;
	static float fontScale;

	private static UnicodeFont get(CustomFont font) {
		int size = (int) ((float) font.size * fontScale);
		return (UnicodeFont) cache.computeIfAbsent(font.font + size, (o) -> {
			Font s;
			try {
				InputStream uf = Minecraft.getMinecraft().getResourceManager()
						.getResource(new ResourceLocation("wendoxd", "fonts/" + font.font + ".ttf")).getInputStream();
				Throwable symbols = null;

				try {
					s = Font.createFont(0, uf);
				} catch (Throwable var17) {
					symbols = var17;
					throw var17;
				} finally {
					if (uf != null) {
						if (symbols != null) {
							try {
								uf.close();
							} catch (Throwable var15) {
								symbols.addSuppressed(var15);
							}
						} else {
							uf.close();
						}
					}

				}
			} catch (IOException | FontFormatException var19) {
				throw new RuntimeException(font.font, var19);
			}

			UnicodeFont uf1 = new UnicodeFont(s, size, false, false);
			uf1.getEffects().add(new ColorEffect(Color.WHITE));
			String symbols1 = " +=0123456789\u0430\u0431\u0432\u0433\u0434\u0435\u0451\u0436\u0437\u0438\u0439\u043a\u043b\u043c\u043d\u043e\u043f\u0440\u0441\u0442\u0443\u0444\u0445\u0446\u0447\u0448\u0449\u044a\u044b\u044c\u044d\u044e\u044f\u0410\u0411\u0412\u0413\u0414\u0415\u0401\u0416\u0417\u0418\u0419\u041a\u041b\u041c\u041d\u041e\u041f\u0420\u0421\u0422\u0423\u0424\u0425\u0426\u0427\u0428\u0429\u042a\u042b\u042c\u042d\u042e\u042fabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!-_()?,./\"\'[]{}*&^:%$;\u2116@#~`><\u2022";
			uf1.addGlyphs(symbols1);

			try {
				uf1.loadGlyphs();
			} catch (SlickException var16) {
				throw new RuntimeException(font.font, var16);
			}

			uf1.setDisplayListCaching(true);
			return uf1;
		});
	}

	public static int getStringWidth(CustomFont font, String string) {
		if (!string.startsWith("\u00a7")) {
			string = "\u00a7f" + string;
		}

		UnicodeFont uf = get(font);
		StringBuilder result = new StringBuilder();
		String[] width = string.split("\u00a7");
		int var5 = width.length;

		for (int var6 = 0; var6 < var5; ++var6) {
			String s = width[var6];
			if (s != null && s.length() > 1) {
				result.append(s.substring(1));
			}
		}

		int var8 = uf.getWidth(result.toString());
		return (int) ((float) var8 / (fontScale * 2.0F));
	}

	public static int getStringHeight(CustomFont font, String string, int w) {
		if (!string.startsWith("\u00a7")) {
			string = "\u00a7f" + string;
		}

		if (w != -1) {
			w = (int) ((float) w * fontScale * 2.0F);
		}

		int y = 0;
		int width = 0;
		UnicodeFont uf = get(font);
		String[] var6 = string.split("\n");
		int var7 = var6.length;

		for (int var8 = 0; var8 < var7; ++var8) {
			String s1 = var6[var8];
			if (s1 != null && s1.length() != 0) {
				String source = "";
				String[] var11 = s1.split("\u00a7");
				int var12 = var11.length;

				for (int var13 = 0; var13 < var12; ++var13) {
					String s = var11[var13];
					if (s != null && s.length() > 1) {
						String[] var15 = s.substring(1).split(" ");
						int var16 = var15.length;

						for (int var17 = 0; var17 < var16; ++var17) {
							String s2 = var15[var17];
							String t = s2 + " ";
							source = source + t;
							if (w != -1 && width + uf.getWidth(t) > w) {
								y += uf.getHeight(source) + 2;
								width = 0;
							}

							width += uf.getWidth(t);
						}
					}
				}

				y += uf.getHeight(source) + 2;
				width = 0;
			}
		}

		return (int) ((float) y / (fontScale * 2.0F));
	}

	public static void drawString(String string, float x, float y, CustomFont font) {
		drawStringWithMaxWidth(string, x, y, -1, font);
	}

	public static void drawStringWithMaxWidth(String string, float x, float y, int w, final CustomFont font) {
		final float guiScale = CustomFontRenderer.fontScale * 2.0f;
		final float rscale = 1.0f / guiScale;
		x *= (int) guiScale;
		y *= (int) guiScale;
		if (w != -1) {
			w *= (int) guiScale;
		}
		GL11.glScalef(rscale, rscale, 1.0f);
		GL11.glEnable(3042);
		GL11.glDisable(3553);
		GL11.glBlendFunc(770, 771);
		if (!string.startsWith("§")) {
			string = "§f" + string;
		}
		final float sx = x;
		int width = 0;
		final UnicodeFont uf = get(font);
		for (final String s1 : string.split("\n")) {
			if (s1 != null) {
				if (s1.length() != 0) {
					String source = "";
					for (final String s2 : s1.split("§")) {
						if (s2 != null) {
							if (s2.length() > 1) {
								final char col = s2.charAt(0);
								for (final String s3 : s2.substring(1).split(" ")) {
									final String t = s3 + " ";
									source += t;
									if (w != -1 && width + uf.getWidth(t) > w) {
										x = sx;
										y += uf.getHeight(source) + 2;
										width = 0;
									}
									uf.drawString((float) x, (float) y, t, getColor(col));
									width += uf.getWidth(t);
									x += uf.getWidth(t);
								}
							}
						}
					}
					x = sx;
					y += uf.getHeight(source) + 2;
					width = 0;
				}
			}
		}
		GL11.glEnable(3553);
		GL11.glDisable(3042);
		GL11.glScalef(guiScale, guiScale, 1.0f);
	}

	private static org.newdawn.slick.Color getColor(char s) {
		org.newdawn.slick.Color c = (org.newdawn.slick.Color) colors.get(String.valueOf(s));
		return c != null ? c : org.newdawn.slick.Color.white;
	}

	@SubscribeEvent
	public void preRender(RenderTickEvent ev) {
		if (ev.phase == Phase.START) {
			Minecraft mc = Minecraft.getMinecraft();
			CustomFontRenderer.fontScale = 1.0f
					+ ((CustomFontRenderer.guiScale = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight)
							.getScaleFactor()) - 2) / 2.0f;
		}
	}

	private static void registerColors() {
		colors.put("0", new org.newdawn.slick.Color(0));
		colors.put("1", new org.newdawn.slick.Color(170));
		colors.put("2", new org.newdawn.slick.Color('\uaa00'));
		colors.put("3", new org.newdawn.slick.Color('\uaaaa'));
		colors.put("4", new org.newdawn.slick.Color(11141120));
		colors.put("5", new org.newdawn.slick.Color(11141290));
		colors.put("6", new org.newdawn.slick.Color(16755200));
		colors.put("7", new org.newdawn.slick.Color(11184810));
		colors.put("8", new org.newdawn.slick.Color(5592405));
		colors.put("9", new org.newdawn.slick.Color(5592575));
		colors.put("a", new org.newdawn.slick.Color(5635925));
		colors.put("b", new org.newdawn.slick.Color(5636095));
		colors.put("c", new org.newdawn.slick.Color(16733525));
		colors.put("d", new org.newdawn.slick.Color(16733695));
		colors.put("e", new org.newdawn.slick.Color(16777045));
		colors.put("f", new org.newdawn.slick.Color(16777215));
		colors.put("g", new org.newdawn.slick.Color(12632256));
		colors.put("h", new org.newdawn.slick.Color(13467442));
		colors.put("y", new org.newdawn.slick.Color(3178751));
	}

	static {
		FMLCommonHandler.instance().bus().register(new CustomFontRenderer());
		registerColors();
		strings.add("о");
		strings.add("o");
		strings.add("е");
		strings.add("e");
		strings.add("с");
		strings.add("c");
		ArrayList<String> newArrayList = new ArrayList<String>();
		for (String s : strings) {
			newArrayList.add(s.toUpperCase());
		}
		strings.addAll(newArrayList);
	}
}
