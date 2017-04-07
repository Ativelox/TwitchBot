/**
 * 
 */
package com.cormag.twitchbot.commons;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class Utils {

	public static final String MESSAGE_REGEX = ".*?SERVER: :(.+)?!.*?:(.*)";

	private static final SimpleDateFormat HOUR_TO_SECOND = new SimpleDateFormat("[HH:mm:ss]");
	private static final SimpleDateFormat YEAR_TO_SECOND = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss");

	public static String getDate() {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		return getDay(Calendar.DAY_OF_MONTH) + " " + YEAR_TO_SECOND.format(stamp);

	}

	public static String getTimestamp() {

		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		return HOUR_TO_SECOND.format(stamp);

	}

	private static String getDay(int numberOfDay) {
		if (numberOfDay == 1) {
			return "Monday";

		} else if (numberOfDay == 2) {
			return "Tuesday";

		} else if (numberOfDay == 3) {
			return "Wednesday";

		} else if (numberOfDay == 4) {
			return "Thursday";

		} else if (numberOfDay == 5) {
			return "Friday";

		} else if (numberOfDay == 6) {
			return "Saturday";

		} else if (numberOfDay == 7) {
			return "Sunday";

		} else {
			throw new AssertionError();
		}

	}

	private Utils() {
	}

}
