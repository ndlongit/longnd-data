package com.structis.fichesst.server.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateMapper implements DateMapperIfc {

	private static final Logger logger = Logger.getLogger(DateMapper.class);

	private static String DATE_FORMAT = "yyyyMMddHHmmss";

	private static String MONTH_FORMAT = "yyyyMM";

	private static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

	public Date getActualDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public String createStringFromDate(Date date) {
		return sdf.format(date);
	}

	public Date createDateFromString(String string) {
		Date date = null;
		try {
			date = sdf.parse(string);
		}
		catch( ParseException e ) {
			logger.error("Erreur dans le  mapping string to date : " + string);
		}
		return date;
	}

	public String getActualStringDate() {
		return createStringFromDate(getActualDate());
	}

	public Date getActualDate(String format) {
		Date result = null;
		SimpleDateFormat formatDate = new SimpleDateFormat(format);
		Date date = getActualDate();
		String stringDate = formatDate.format(date);
		try {
			result = formatDate.parse(stringDate);
		}
		catch( ParseException e ) {
			logger.error("Erreur dans le  mapping string to date : " + format);
		}

		return result;
	}

	public Date getActualMonth() {
		return getActualDate(MONTH_FORMAT);
	}

}
