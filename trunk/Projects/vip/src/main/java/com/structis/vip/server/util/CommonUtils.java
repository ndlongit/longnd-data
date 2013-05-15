package com.structis.vip.server.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class CommonUtils {
	public static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static Random rnd = new Random();
	public static final String DATE_PATTERN = "dd/MM/yyyy";
	public static final String DATE_PATTERN_SYNC = "yyyy/MM/dd";
	
	public static String formatDateToString(Date date) {
		if (date == null) return "";
		return formatDateToStringWithPatter(date, DATE_PATTERN);
	}
	
	public static String formatDateToStringWithPatter(Date date, String pattern) {
		if (date == null) return "";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	public static Date parseToDate(String date) {
		Date ret = null;
		if (date != null && date.length() >= 10) {
			try {
				SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN_SYNC);
				ret = format.parse(date.trim());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	public static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
	public static String formatFloat(Float val) {
		if (val == null) return "";
		DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(Locale.FRANCE);
//		unusualSymbols.setDecimalSeparator(',');
//		unusualSymbols.setGroupingSeparator(' ');
		DecimalFormat format = new DecimalFormat("###,###.00",unusualSymbols );
		return format.format(val);
	}
}
