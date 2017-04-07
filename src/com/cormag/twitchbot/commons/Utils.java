/**
 * 
 */
package com.cormag.twitchbot.commons;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class Utils {

	public static final String MESSAGE_REGEX = ".*?SERVER: :(.+)?!.*?:(.*)";

	private static final SimpleDateFormat HOUR_TO_SECOND = new SimpleDateFormat("[HH:mm:ss]");
	private static final SimpleDateFormat YEAR_TO_SECOND = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss");

	public static String getDate() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return getDay(calendar.get(Calendar.DAY_OF_WEEK)) + " " + YEAR_TO_SECOND.format(calendar.getTime());

	}

	public static String getTimestamp() {
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return HOUR_TO_SECOND.format(calendar.getTime());

	}

	private static String getDay(int numberOfDay) {
		if (numberOfDay == 2) {
			return "Monday";

		} else if (numberOfDay == 3) {
			return "Tuesday";

		} else if (numberOfDay == 4) {
			return "Wednesday";

		} else if (numberOfDay == 5) {
			return "Thursday";

		} else if (numberOfDay == 6) {
			return "Friday";

		} else if (numberOfDay == 7) {
			return "Saturday";

		} else if (numberOfDay == 1) {
			return "Sunday";

		} else {
			throw new AssertionError();
		}

	}

	private Utils() {
	}

}
