// 
// Decompiled by Procyon v0.5.36
// 

package wendoxd.utils;

public class CustomFont {
	public static CustomFont title = new CustomFont("title", 17);
	public static CustomFont titleScreen = new CustomFont("title", 35);
	public static CustomFont titleScreenName = new CustomFont("title", 55);
	public static CustomFont gui = new CustomFont("gui", 15);
	public static CustomFont guiprice = new CustomFont("gui", 10);
	public static CustomFont special_small = new CustomFont("zonix-font", 13);
	public static CustomFont special_medium = new CustomFont("zonix-font", 35);
	public static CustomFont special_big = new CustomFont("zonix-font", 45);
	public final String font;
	public final int size;

	public CustomFont(final String font, final int size) {
		this.font = font;
		this.size = size;
	}
}
