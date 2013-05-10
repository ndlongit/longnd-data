package org.java.demo.util;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

public final class AppUtil {

    @SuppressWarnings("rawtypes")
    public static boolean isNullOrEmpty(Object value) {
        if (value == null) {
            return true;
        }

        if (value instanceof String) {
            return (value.toString().trim() == "");
        } else if (value instanceof Collection) {
            return ((Collection) value).isEmpty();
        } else if (value instanceof Map) {
            return ((Map) value).isEmpty();
        } else {
            return false;
        }
    }

    public static String randomString(int len) {
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(str.charAt(rnd.nextInt(str.length())));
        }
        return sb.toString();
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("^(0|[1-9]\\d*)(\\.\\d+)?$");
    }

    public static boolean isInteger(String str) {
        return str != null && str.matches("^(0|[1-9]\\d*)$");
    }
}
