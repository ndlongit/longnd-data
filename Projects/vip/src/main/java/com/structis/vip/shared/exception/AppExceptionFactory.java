package com.structis.vip.shared.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory faisant partie du design pattern "Fly Weight" 
 * d'AppException
 * @author th.bernard
 */
public class AppExceptionFactory {

	private static final AppExceptionFactory instance = new AppExceptionFactory();

	private static Map<AppExceptionType, AppException> appExceptionMap = new HashMap<AppExceptionType, AppException>();

	private AppExceptionFactory() {
	}

	public static AppExceptionFactory getInstance() {
		return instance;
	}

	public synchronized AppException getAppException(AppExceptionType type) {
		createAppExceptionIfNeeded(type);
		return appExceptionMap.get(type);
	}

	private void createAppExceptionIfNeeded(AppExceptionType type) {
		if (!appExceptionMap.containsKey(type)) {
			appExceptionMap.put(type, new AppException(type));
		}
	}

}
