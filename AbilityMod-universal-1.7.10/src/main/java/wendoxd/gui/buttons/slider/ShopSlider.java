package wendoxd.gui.buttons.slider;

import net.minecraft.client.Minecraft;
import wendoxd.utils.Util;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.config.GuiButtonExt;

/**
 * This class is blatantly stolen from iChunUtils with permission.
 *
 * @author iChun
 */
public class ShopSlider extends GuiButtonExt {
	/** The value of this slider control. */
	public double sliderValue = 1.0F;

	public String dispString = "";

	/** Is this slider control being dragged. */
	public boolean dragging = false;
	public boolean showDecimal = true;

	public double minValue = 0.0D;
	public double maxValue = 5.0D;
	public int precision = 1;

	public ISlider parent = null;

	public String suffix = "";

	public boolean drawString = true;

	public ShopSlider(int id, int xPos, int yPos, int width, int height, String prefix, String suf, double minVal,
			double maxVal, double currentVal, boolean showDec, boolean drawStr) {
		this(id, xPos, yPos, width, height, prefix, suf, minVal, maxVal, currentVal, showDec, drawStr, null);
	}

	public ShopSlider(int id, int xPos, int yPos, int width, int height, String prefix, String suf, double minVal,
			double maxVal, double currentVal, boolean showDec, boolean drawStr, ISlider par) {
		super(id, xPos, yPos, width, height, prefix);
		minValue = minVal;
		maxValue = maxVal;
		sliderValue = (currentVal - minValue) / (maxValue - minValue);
		dispString = prefix;
		parent = par;
		suffix = suf;
		showDecimal = showDec;
		String val;

		if (showDecimal) {
			val = Double.toString(sliderValue * (maxValue - minValue) + minValue);
			precision = Math.min(val.substring(val.indexOf(".") + 1).length(), 4);
		} else {
			val = Integer.toString((int) Math.round(sliderValue * (maxValue - minValue) + minValue));
			precision = 0;
		}

		displayString = dispString + val + suffix;

		drawString = drawStr;
		if (!drawString) {
			displayString = "";
		}
	}

	public ShopSlider(int id, int xPos, int yPos, String displayStr, double minVal, double maxVal, double currentVal,
			ISlider par) {
		this(id, xPos, yPos, 150, 20, displayStr, "", minVal, maxVal, currentVal, true, true, par);
	}

	/**
	 * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this
	 * button and 2 if it IS hovering over this button.
	 */
	/**
	 * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this
	 * button and 2 if it IS hovering over this button.
	 */
	@Override
	public int getHoverState(boolean par1) {
		return 0;
	}

	/**
	 * Fired when the mouse button is dragged. Equivalent of
	 * MouseListener.mouseDragged(MouseEvent e).
	 */
	/**
	 * Fired when the mouse button is dragged. Equivalent of
	 * MouseListener.mouseDragged(MouseEvent e).
	 */
	@Override
	protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {
		if (this.visible) {
			if (this.dragging) {
				this.sliderValue = (par3 - (this.yPosition)) / (float) (this.height);
				updateSlider();
			}
			Util.drawRect(xPosition - 0.3F, yPosition - 0.3F, xPosition + width + 0.3F, yPosition + height + 0.3F, Util.color(50, 29, 30, 255));
			Util.drawRect(xPosition, yPosition, xPosition + width, yPosition + height, Util.color(30, 29, 30, 255));
			int min = Math.max(yPosition + 1,
					Math.min(yPosition + height - 1, yPosition + (int) (sliderValue * height) - 2));
			int max = Math.max(yPosition + 1,
					Math.min(yPosition + height - 1, yPosition + (int) (sliderValue * height) + 2));
			Util.drawGradientRect(xPosition + 1, min, xPosition + width - 1, max,
					dragging ? Util.color(150, 30, 30, 255) : Util.color(100, 30, 30, 255),
					dragging ? Util.color(165, 38, 35, 255) : Util.color(125, 38, 35, 255));
		}
	}

	@Override
	public boolean mousePressed(Minecraft par1Minecraft, int x, int y) {
		if (super.mousePressed(par1Minecraft, x, y)) {
			updateSlider();
			this.dragging = true;
			return true;
		} else {
			return false;
		}
	}

	public void updateSlider() {
		if (this.sliderValue < 0) {
			this.sliderValue = 0;
		}

		if (this.sliderValue > 1) {
			this.sliderValue = 1;
		}

		String val;

		if (showDecimal) {
			val = Double.toString(sliderValue * (maxValue - minValue) + minValue);

			if (val.substring(val.indexOf(".") + 1).length() > precision) {
				val = val.substring(0, val.indexOf(".") + precision + 1);

				if (val.endsWith(".")) {
					val = val.substring(0, val.indexOf(".") + precision);
				}
			} else {
				while (val.substring(val.indexOf(".") + 1).length() < precision) {
					val = val + "0";
				}
			}
		} else {
			val = Integer.toString((int) Math.round(sliderValue * (maxValue - minValue) + minValue));
		}

		if (drawString) {
			displayString = dispString + val + suffix;
		}

		if (parent != null) {
			parent.onChangeSliderValue(this);
		}
	}

	@Override
	public void mouseReleased(int par1, int par2) {
		this.dragging = false;
	}

	public int getValueInt() {
		return (int) Math.round(sliderValue * (maxValue - minValue) + minValue);
	}

	public double getValue() {
		return sliderValue * (maxValue - minValue) + minValue;
	}

	public void setValue(double d) {
		this.sliderValue = (d - minValue) / (maxValue - minValue);
	}

	public static interface ISlider {
		void onChangeSliderValue(ShopSlider slider);
	}
}