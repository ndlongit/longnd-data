// [LICENCE-HEADER]
//
package vn.pyco.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
public final class StringHelper {
    public static List<String> toList(String source, String delimiter) {
        List<String> list = new ArrayList<String>();
        if (isNotEmpty(source)) {
            StringTokenizer tokenizer = new StringTokenizer(source, delimiter);
            while (tokenizer.hasMoreTokens()) {
                list.add(tokenizer.nextToken());
            }
        }
        return list;
    }

    public static boolean isNotEmpty(String source) {
        return (source != null && !("").equals(source.trim()));
    }

    public static boolean toBoolean(String value) {
        return "true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value);
    }

    public static String toHexString(byte[] data) {
        StringBuffer hex = new StringBuffer();
        for (int b : data) {
            hex.append(Integer.toHexString(0xFF & b));
        }
        return hex.toString();
    }

}
