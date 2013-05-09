package com.structis.fichesst.client.util;

import com.google.gwt.user.client.Cookies;

public class CookieUtil {
	public static String CURRENT_CHANTIER = "chantier";
	public static String CURRENTPROJECT_COOKIE = "project";
	public static String CURRENT_USER = "user";
	public static String CURRENT_ROLE = "role";
	public static String CURRENT_TRANSFERTPP = "transertpp";
	public static String CURRENT_FICHEST = "fichest";
	public static String CURRENT_IDSITRAVAUX = "idSiTravaux";

	public static void setCurrentProject(String value) {
		Cookies.setCookie(CURRENTPROJECT_COOKIE, value);
	}

	public static String getCurrentProject() {
		return Cookies.getCookie(CURRENTPROJECT_COOKIE);
	}

	public static void setCurrentChantier(String value) {
		Cookies.setCookie(CURRENT_CHANTIER, value);
	}

	public static String getCurrentChantier() {
		return Cookies.getCookie(CURRENT_CHANTIER);
	}

	public static void setCurrentUser(String value) {
		Cookies.setCookie(CURRENT_USER, value);
	}

	public static String getCurrentUser() {
		return Cookies.getCookie(CURRENT_USER);
	}

	public static void setCurrentRole(String value) {
		Cookies.setCookie(CURRENT_ROLE, value);
	}

	public static String getCurrentRole() {
		return Cookies.getCookie(CURRENT_ROLE);
	}

	public static void setCurrentTransfertpp(String value) {
		Cookies.setCookie(CURRENT_TRANSFERTPP, value);
	}

	public static String getCurrentTransfertpp() {
		return Cookies.getCookie(CURRENT_TRANSFERTPP);
	}

	public static void setCurrentFichest(String value) {
		Cookies.setCookie(CURRENT_FICHEST, value);
	}

	public static String getCurrentFichest() {
		return Cookies.getCookie(CURRENT_FICHEST);
	}

	public static void setCurrentIdSiTravaux(String value) {
		Cookies.setCookie(CURRENT_IDSITRAVAUX, value);
	}

	public static String getCurrentIdSiTravaux() {
		return Cookies.getCookie(CURRENT_IDSITRAVAUX);
	}

}
