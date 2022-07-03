package wendoxdc.gui;

public class Animation {
	public Animation(double min, double max) {
		this.min = min;
		this.max = max;
		this.add = 2;
	}

	public double add, value, min, max;

	public double doubleValue() {
		return value;
	}

	public int intValue() {
		return (int) value;
	}

	public float floatValue() {
		return (float) value;
	}

	public void update(boolean outlined) {
		if (outlined) {
			if (value <= max) {
				value = value + add;
			}
		} else {
			if (value >= min) {
				value = value - add;
			}
		}
	}
}
