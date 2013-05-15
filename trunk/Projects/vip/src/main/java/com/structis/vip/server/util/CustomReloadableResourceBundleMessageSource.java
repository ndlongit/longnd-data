package com.structis.vip.server.util;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;


public class CustomReloadableResourceBundleMessageSource extends
		ReloadableResourceBundleMessageSource {

	public Enumeration<Object> getKeys(Locale locale){
		PropertiesHolder holder = this.getMergedProperties(locale);
		Properties props = holder.getProperties();
		Enumeration<Object> keys = props.keys();
		
//		while(keys.hasMoreElements()){
//			String key = (String)keys.nextElement();
//			this.getMessage(key, null, locale);
//		}
		
		return keys;
	}
}
