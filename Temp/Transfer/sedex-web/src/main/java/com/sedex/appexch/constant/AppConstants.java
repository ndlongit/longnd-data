package com.sedex.appexch.constant;

public final class AppConstants extends AppConfig {

    private AppConstants() {
    }

    // Running mode: dev|test|prod
    public static final String RUN_MODE;
    public static final String RUN_MODE_DEV = "dev";
    public static final String RUN_MODE_TEST = "test";
    public static final String RUN_MODE_PROD = "prod";
    public static final String BUILD_VERSION;
    public static final String FILE_SEPARATOR;
    public static final String NEW_LINE;

    static {
        RUN_MODE = getString("run.mode");
        BUILD_VERSION = getString("build.version");
        FILE_SEPARATOR = System.getProperty("file.separator");
        NEW_LINE = System.getProperty("line.separator");
    }

    public static final String METHOD_BEGIN = " - Begin";

    public static final String METHOD_END = " - End";

    public static final String DATE_FORMAT = "yyyy/MM/dd";
    public static final String TIME_FORMAT = "kk:mm:ss";
    public static final String DATETIME_FORMAT = DATE_FORMAT + " " + TIME_FORMAT;
}
