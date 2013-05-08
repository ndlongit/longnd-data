package com.structis.fichesst.server.service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientPropertiesService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;

@Service("clientPropertiesService")
public class ClientPropertiesServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientPropertiesService {

	@Value("#{appProperties.appName}")
	private String appName;

	@Value("#{appProperties.appVersion}")
	private String appVersion;

	@Override
	public String getVersionInfo() {
		return appVersion;
	}
}
