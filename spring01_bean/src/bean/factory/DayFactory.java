package bean.factory;

import java.util.Calendar;

public class DayFactory {
	
	public static Week getInstance() {
		Calendar cal = Calendar.getInstance();
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
			case 1: return new Sunday();
			case 2: return new Monday();
			case 3: return new Tuesday();
			case 4: return new Wednsday();
			case 5: return new Thursday();
			case 6: return new Friday();
			default: return new Saturday();
		}
	}
}
