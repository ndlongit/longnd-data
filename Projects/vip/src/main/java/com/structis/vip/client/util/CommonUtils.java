package com.structis.vip.client.util;

import java.util.Random;

import com.google.gwt.i18n.client.NumberFormat;
import com.structis.vip.client.constant.ConstantClient;

public class CommonUtils {

    public static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static Random rnd = new Random();
    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static final String DATE_PATTERN_SYNC = "yyyy/MM/dd";
    public static final String NUMBER_FORMAT = "###,##0.00";

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static String formatNumber(Float val) {
        if (val != null) {
            return NumberFormat.getFormat(NUMBER_FORMAT).format(val);
        } else {
            return "";
        }
    }

    public static boolean isChantierType(String typeName) {
        if (typeName == null)
            return false;
        if (typeName.toUpperCase().startsWith(ConstantClient.PERIMETER_TYPE_NAME_IS_CHANTIER.toUpperCase())) {
            return true;
        }
        return false;
    }

    public static boolean belongsBYEFEGroup(String entId) {
        for (String id : ConstantClient.ENTITE_ID_BELONGS_BYEFE) {
            if (id.equals(entId)) {
                return true;
            }
        }
        return false;
    }
}
