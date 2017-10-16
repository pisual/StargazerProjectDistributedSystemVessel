package com.stargazerproject.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	public static String IDTime(String timeForm) {
		DateFormat dateFormat = new SimpleDateFormat(timeForm);
		return dateFormat.format(new Date());
	}
}
