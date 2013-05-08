package com.structis.fichesst.client.util;

import com.google.gwt.user.client.Cookies;

public class CookieUtil {
	public static String CURRENT_CHANTIER = "chantier";
	public static String CURRENTPROJECT_COOKIE = "project";
	
	public static void setCurrentProject(String value){
		Cookies.setCookie(CURRENTPROJECT_COOKIE, value);
	}
	public static String getCurrentProject(){
		return Cookies.getCookie(CURRENTPROJECT_COOKIE);
	}
	
	public static void setCurrentChantier(String value){
		Cookies.setCookie(CURRENT_CHANTIER,value);
	}
	public static String getCurrentChantier(){
		return Cookies.getCookie(CURRENT_CHANTIER);
	}
}
