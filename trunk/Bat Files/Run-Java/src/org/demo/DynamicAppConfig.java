package org.demo;

import java.util.Properties;

public class DynamicAppConfig extends AppConfig {

	private static String DYNAMIC_CONFIG_FILE_NAME = null;
	private static Properties properties = new Properties();

	static {
		boolean defaultConfigFile = false;
		DYNAMIC_CONFIG_FILE_NAME = System.getProperty("dynamic.app.config");
		if (DYNAMIC_CONFIG_FILE_NAME == null || "".equals(DYNAMIC_CONFIG_FILE_NAME.trim())) {
			defaultConfigFile = true;
			DYNAMIC_CONFIG_FILE_NAME = "conf/DynamicAppConfig.properties";
		}

		System.out.println("Use default Config file: " + defaultConfigFile);
	}

	public static String getValue(String key) {
		loadConfig(DYNAMIC_CONFIG_FILE_NAME, properties);
		return properties.getProperty(key);
	}
}
