package com.structis.fichesst.server.util;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtil {
	public static String getLogonedUserName(String userName) {
		char userDelimiter = '\\';
		if( userName != null ) {
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
		if( userLogin != null&&userLogin.toLowerCase().contains("bouygues-construction.com") ) {
			return "bycn\\"+userLogin.substring(0, userLogin.indexOf(userDelimiter)).toLowerCase();
		}
		if (userLogin!=null&&userLogin.toLowerCase().contains("intl.bync")) {
			return "intdomain\\"+userLogin.substring(0,userLogin.indexOf(userDelimiter)).toLowerCase();
		}
		if (userLogin!=null&&userLogin.toLowerCase().contains("byefe.com")) {
			return "intl\\"+userLogin.substring(0,userLogin.indexOf(userDelimiter)).toLowerCase();
		}
		if (userLogin!=null&&userLogin.toLowerCase().contains("bouygues-batiment.com")) {
			return "bbadomain\\"+userLogin.substring(0,userLogin.indexOf(userDelimiter)).toLowerCase();
		}
		if (userLogin!=null&&userLogin.toLowerCase().contains("bouyguestp.com")) {
			return "btpdomain\\"+userLogin.substring(0,userLogin.indexOf(userDelimiter)).toLowerCase();
		}
		if (userLogin!=null&&userLogin.toLowerCase().contains("etde.fr")) {
			return "etddomain\\"+userLogin.substring(0,userLogin.indexOf(userDelimiter)).toLowerCase();
		}
		if (userLogin!=null&&userLogin.toLowerCase().contains("bouyguesasia.com")) {
			return "intl\\"+userLogin.substring(0,userLogin.indexOf(userDelimiter)).toLowerCase();
		}
		return userLogin;
	}

	public static boolean isNullOrEmpty(String value) {
		return(value == null || value.trim() == "");
	}

	public static boolean isNullOrEmpty(Integer value) {
		return(value == null);
	}

	public static boolean isNullOrEmpty(Long value) {
		return(value == null);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Collection collection) {
		return collection == null || collection.size() == 0;
	}

	public static boolean isNullOrEmpty(Object[] array) {
		return array == null || array.length == 0;
	}
	
	public static boolean isValidTextField(String text){
	
		Pattern p=Pattern.compile("^bycn\\\\");
		Matcher m=p.matcher(text);
		boolean found=m.find();
		return found;
	}
	

}
