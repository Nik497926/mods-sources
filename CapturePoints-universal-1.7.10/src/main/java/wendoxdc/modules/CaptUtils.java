package wendoxdc.modules;

import java.util.ArrayList;

public class CaptUtils {
	public static Capture forName(ArrayList<Capture> capts, String target) {
		for (Capture c : capts) {
			if (c.name.equals(target))
				return c;
		}
		return null;
	}
}
