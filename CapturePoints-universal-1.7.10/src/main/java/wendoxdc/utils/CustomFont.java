// 
// Decompiled by Procyon v0.5.36
// 

package wendoxdc.utils;

public class CustomFont {
	public static CustomFont title = new CustomFont("title", 17);
	public static CustomFont gui = new CustomFont("gui", 15);
	public final String font;
	public final int size;

	public CustomFont(final String font, final int size) {
		this.font = font;
		this.size = size;
	}
}
