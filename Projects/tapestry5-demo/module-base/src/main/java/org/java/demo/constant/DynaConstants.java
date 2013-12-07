package org.java.demo.constant;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Level;

public abstract class DynaConstants {

    public static final int DEFAULT_MAX_SEARCH_RESULT = 500;

    private static PropertiesConfiguration CONFIG;

    private static final String PROPERTIES_FILE = "AppConfig.properties";
    static {
        try {
            CONFIG = new PropertiesConfiguration(PROPERTIES_FILE);
            CONFIG.setReloadingStrategy(new FileChangedReloadingStrategy());
            CONFIG.setAutoSave(true);
        } catch (ConfigurationException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    // Common methods - Begin
    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        return CONFIG.getString(key, defaultValue);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        return CONFIG.getInt(key, defaultValue);
    }

    public static double getDouble(String key) {
        return getDouble(key, 0.0);
    }

    public static Object getProperty(String key) {
        return CONFIG.getProperty(key);
    }

    public static void setProperty(String key, Object value) {
        CONFIG.setProperty(key, value);
    }

    public static double getDouble(String key, double defaultValue) {
        return CONFIG.getDouble(key, defaultValue);
    }

    // Common methods - End

    public static int maxSearchResult() {
        return CONFIG.getInt("search.result.max", DEFAULT_MAX_SEARCH_RESULT);
    }

    // This logging will be use to output information to debug application
    public static Level getTrackLevel() {
        return new LogLevel(Level.DEBUG_INT + 1);
    }
}

final class LogLevel extends Level {

    public LogLevel(int level) {
        super(level, "TRACK", 0);
    }
}
