package org.demo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class AppConfig {

	private static String CONFIG_FILE_NAME = null;
	private static Properties properties = new Properties();

	static {
		boolean defaultConfigFile = false;

		CONFIG_FILE_NAME = System.getProperty("app.config");
		if (CONFIG_FILE_NAME == null || "".equals(CONFIG_FILE_NAME.trim())) {
			defaultConfigFile = true;
			CONFIG_FILE_NAME = "conf/AppConfig.properties";
		}

		System.out.println("Use default Config file: " + defaultConfigFile);
		loadConfig(CONFIG_FILE_NAME, properties);
	}

	protected static void loadConfig(String configFileName, Properties properties) {
		FileInputStream inputStrim = null;
		File configFile = new File(configFileName);
		try {
			System.out.println("Config file location: " + configFile.getCanonicalPath());

			if (configFile.exists()) {
				inputStrim = new FileInputStream(configFileName);
				properties.load(inputStrim);
				inputStrim.close();
			} else {
				System.out.println("File '" + configFile.getCanonicalPath() + "' does not exist");
			}
		} catch (Exception e) {
			inputStrim = null;
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		return properties.getProperty(key);
	}
}
