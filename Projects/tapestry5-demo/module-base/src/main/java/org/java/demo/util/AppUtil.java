package org.java.demo.util;

import java.util.Collection;

public final class AppUtil {

	public static boolean isNullOrEmpty(String value) {
		return (value == null || value.trim() == "");
	}

	public static boolean isNullOrEmpty(Integer value) {
		return (value == null);
	}

	public static boolean isNullOrEmpty(Long value) {
		return (value == null);
	}

	@SuppressWarnings("unchecked")
	public static boolean isNullOrEmpty(Collection collection) {
		return collection == null || collection.size() == 0;
	}
}
