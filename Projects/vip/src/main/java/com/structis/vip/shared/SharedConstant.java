package com.structis.vip.shared;

public class SharedConstant {

    public static final String ENTITE_ID_BYEFE = "015";
    public static final String ENTITE_ID_BYTP = "016";
    public static final String ENTITE_ID_ETDE = "327";

    public static final String[] ENTITE_ID_BELONGS_BYEFE = { "015", "016" };

    public final static String SUCCESS_LIST = "SUCCESS_LIST";
    public final static String ERROR_LIST = "ERROR_LIST";
    public static final int REFER_PERIMETRE = 1;
    public static final int REFER_DELEGATION = 2;
    public static final int REFER_CONTROL = 4;
    public static final int REFER_COLLABORATEUR = 8;
    public static final int REFER_USER = 16;

    public static enum RunMode {
        DEVELOPMENT("DEV"), PRODUCTION("PROD"), TESTING("TEST");

        String value;

        RunMode(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    };
}
