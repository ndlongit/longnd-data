package org.java.demo.constant;

public final class AppConstants extends DynaConstants {

    private AppConstants() {
    }

    // Running mode: dev|test|prod
    public static final String RUN_MODE;
    public static final String BUILD_VERSION;

    static {
        RUN_MODE = getString("run.mode");
        BUILD_VERSION = getString("build.version");
    }

    public static final String METHOD_BEGIN = " - Begin";

    public static final String METHOD_END = " - End";
}
