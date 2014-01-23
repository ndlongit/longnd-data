package org.java.demo.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public final class AppUtil {

    @SuppressWarnings("rawtypes")
    public static boolean isNullOrEmpty(Object value) {
        if (value == null) {
            return true;
        }

        if (value instanceof String) {
            return (value.toString().trim().equals(""));
        } else if (value instanceof Collection) {
            Collection c = (Collection) value;
            if (c.isEmpty()) {
                return true;
            }

            for (Object obj : c) {
                if (!isNullOrEmpty(obj)) {
                    return false;
                }
            }
            
            return true;
        } else if (value instanceof Map) {
            Map m = (Map) value;
            if (m.isEmpty()) {
                return true;
            }
            
            Iterator iterator = m.keySet().iterator();
            while (iterator.hasNext()) {
                if (!isNullOrEmpty(iterator.next())) {
                    return false;
                }
            }
            
            return true;
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
