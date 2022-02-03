package com.javatest.registermember.util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



public class DateUtil {
	public static String date() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd",Locale.ENGLISH);
		String hostdate=dateFormat.format(date);
		return hostdate;
	}
	public static String dateTimeString() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss",Locale.ENGLISH);
		String hostdate=dateFormat.format(date);
		return hostdate;
	}

	public static String time() {
		Date time = Calendar.getInstance().getTime();
		DateFormat timeFormat = new SimpleDateFormat("HHmmss");
		String hosttime=timeFormat.format(time);
		return hosttime;
	}
}