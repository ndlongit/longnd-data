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
        StringBuilder sb = new StringBuilder(len);

        char[] caseChars = { 'a', 'A' };
        String[] numChars = new String[10];
        for (byte i = 0; i < numChars.length; i++) {
            numChars[i] = i + "";
        }

        Random rnd = new Random();
        for (int i = 0; i < len; i++) {
            int type = rnd.nextInt(2);
            String aLeter;
            if (type == 0) { // number
                aLeter = numChars[rnd.nextInt(numChars.length)];
            } else {// character
                int charCase = rnd.nextInt(2); // 0 = lower case; 1 = UPER CASE
                aLeter = ((char) (rnd.nextInt(26) + caseChars[charCase])) + "";
            }
            sb.append(aLeter);
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
