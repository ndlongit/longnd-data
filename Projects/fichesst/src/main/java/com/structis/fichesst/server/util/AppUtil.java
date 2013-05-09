package com.structis.fichesst.server.util;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.structis.fichesst.shared.util.Constants;

public class AppUtil {
	public static String getLogonedUserName(String userName) {
		char userDelimiter = '\\';
		if (userName != null) {
			return userName.substring(userName.lastIndexOf(userDelimiter) + 1, userName.length());
		}
		return userName;
	}

	public static boolean match(Matcher matcher, final Pattern pattern, final String toMatch) {
		matcher = pattern.matcher(toMatch);
		return matcher.find();
	}

	public static String getLogonedUserNameSSOKerberos(String userLogin) {
		char userDelimiter = '@';
		if (userLogin != null && userLogin.toLowerCase().contains("bouygues-construction.com")) {
			return "bycn\\" + userLogin.substring(0, userLogin.indexOf(userDelimiter)).toLowerCase();
		}
		if (userLogin != null && userLogin.toLowerCase().contains("intl.bync")) {
			return "intdomain\\" + userLogin.substring(0, userLogin.indexOf(userDelimiter)).toLowerCase();
		}
		if (userLogin != null && userLogin.toLowerCase().contains("byefe.com")) {
			return "intl\\" + userLogin.substring(0, userLogin.indexOf(userDelimiter)).toLowerCase();
		}
		if (userLogin != null && userLogin.toLowerCase().contains("bouygues-batiment.com")) {
			return "bbadomain\\" + userLogin.substring(0, userLogin.indexOf(userDelimiter)).toLowerCase();
		}
		if (userLogin != null && userLogin.toLowerCase().contains("bouyguestp.com")) {
			return "btpdomain\\" + userLogin.substring(0, userLogin.indexOf(userDelimiter)).toLowerCase();
		}
		if (userLogin != null && userLogin.toLowerCase().contains("etde.fr")) {
			return "etddomain\\" + userLogin.substring(0, userLogin.indexOf(userDelimiter)).toLowerCase();
		}
		if (userLogin != null && userLogin.toLowerCase().contains("bouyguesasia.com")) {
			return "intl\\" + userLogin.substring(0, userLogin.indexOf(userDelimiter)).toLowerCase();
		}
		return userLogin;
	}

	public static boolean isNullOrEmpty(String value) {
		return (value == null || value.trim() == "");
	}

	public static boolean isNullOrEmpty(Integer value) {
		return (value == null);
	}

	public static boolean isNullOrEmpty(Long value) {
		return (value == null);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Collection collection) {
		return collection == null || collection.size() == 0;
	}

	public static boolean isNullOrEmpty(Object[] array) {
		return array == null || array.length == 0;
	}

	public static boolean isValidTextField(String text) {

		Pattern p = Pattern.compile("^bycn\\\\");
		Matcher m = p.matcher(text);
		boolean found = m.find();
		return found;
	}

	public static String append(Object... params) {
		String srcParam = "";
		for( Object param : params ) {
			if( param != null ) {
				srcParam += formatValue(param);
			}
			else {
				srcParam += "null";
			}
			srcParam += Constants.SEPRATE;
		}
		return srcParam;
	}

	public static String processNullValue(Object value) {
		if (value == null || "null".equalsIgnoreCase(value.toString())) {
			return "";
		}

		return value.toString();
	}
	
	public static String formatValue(Object value) {		
		DecimalFormat integerFormat = (DecimalFormat)NumberFormat.getNumberInstance(Locale.FRANCE);
		integerFormat.applyPattern(Constants.INTEGER_PATTERN);
		
		DecimalFormat numberFormat = (DecimalFormat)NumberFormat.getNumberInstance(Locale.FRANCE);
		numberFormat.applyPattern(Constants.NUMBER_PATTERN);
		
		Format dateFormat = new SimpleDateFormat(Constants.DATE_PATTERN);

		String result;
		if( value != null ) {
			if( value instanceof Byte || value instanceof Short || value instanceof Integer || value instanceof Long ) {
				result = integerFormat.format(value);
			}
			else if( value instanceof Double || value instanceof Float ) {
				result = numberFormat.format(value);
			}
			else if( value instanceof java.util.Date || value instanceof java.sql.Date ) {
				result = dateFormat.format(value);
			}
			else {
				result = value.toString();
			}
		}
		else {
			result = "";
		}
		return result;
	}
}
