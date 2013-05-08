// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import vn.pyco.commons.utils.StringHelper;

/**
 * Manage all property values that using at system boot phase
 *
 */
public final class BootManager {
    public static final String SYSTEM_HOME_DIR = "system.home-dir";
    public static final String SYSTEM_SETUP = "system.setup";
    public static final String SYSTEM_WEBAPP_DIR = "system.webapp-dir";
        
    private static BootManager bootManager = new BootManager();
    public static BootManager getInstance() {
        return bootManager;
    }
    
    private String _bootFilePath;
    private Properties _properties;
    
    private BootManager() {
    }
    
    /**
     * @param bootFilePath the bootFilePath to set
     */
    public void setBootFilePath(String bootFilePath) {
        if (_bootFilePath != null) {
            throw new IllegalArgumentException("Boot file path is only set once during system runs");
        }
        
        _bootFilePath = bootFilePath;
        _properties = loadBootFile();
    }
    
    public String getHomeDir() {
        return getProperty(SYSTEM_HOME_DIR);
    }
    
    public void setHomeDir(String path) {
        setProperty(SYSTEM_HOME_DIR, path);
    }
    
    public boolean isSetup() {
        String setupText = getProperty(SYSTEM_SETUP);
        if (setupText == null || setupText.isEmpty()) {
            setupText = "true";
        }
        
        return StringHelper.toBoolean(setupText);
    }
    
    public void setSetup(boolean setup) {
        setProperty(SYSTEM_SETUP, String.valueOf(setup));
    }
    
    public String getWebappDir() {
        return getProperty(SYSTEM_WEBAPP_DIR);
    }
    
    public void setWebappDir(String path) {
        setProperty(SYSTEM_WEBAPP_DIR, path);
    }
    
    public String getProperty(String key) {
        return _properties.getProperty(key);
    }
    
    public void setProperty(String key, String value) {
        _properties.setProperty(key, value);
    }
    
    public void save() {
        if (_bootFilePath == null) {
            throw new IllegalArgumentException("Boot file path must be set before saving");
        }
        
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(_bootFilePath);
            _properties.store(outputStream, "");
        } catch (IOException ex) {
            throw new RuntimeException("Cannot save the boot.properties file", ex);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {}
            }
        }
    }
    
    private Properties loadBootFile() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(_bootFilePath);
            properties.load(inputStream);
        } catch (Exception ex) {
            throw new RuntimeException("Cannot load the Boot properties file", ex); 
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {}
            }
        }
        
        return properties;
    }
}
