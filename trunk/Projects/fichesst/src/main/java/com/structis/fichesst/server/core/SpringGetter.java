package com.structis.fichesst.server.core;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringGetter {

	private static final String SPRING_CONTEXT_FOLDER = "com/structis/fichesst/server/spring";

	private static final String CONTEXT_BEAN = "bean-context";

	public static Object getBean(ServletContext context, String name) {
		ClassPathXmlApplicationContext ecoContext = (ClassPathXmlApplicationContext) WebApplicationContextUtils.getWebApplicationContext(
				context).getBean(CONTEXT_BEAN);
		BeanFactory beanFactory = ecoContext.getBeanFactory();
		Object object = beanFactory.getBean(name);

		return object;
	}

	public static Object getBatchBean(String name) {
		ClassPathXmlApplicationContext batchContext = new ClassPathXmlApplicationContext(
				"classpath:" + SPRING_CONTEXT_FOLDER + "/applicationContext.xml",
				"classpath:" + SPRING_CONTEXT_FOLDER + "/persistance-context.xml",
				"classpath:" + SPRING_CONTEXT_FOLDER + "/manager-context.xml",
				"classpath:" + SPRING_CONTEXT_FOLDER + "/properties-context.xml");
		return batchContext.getBean(name);
	}

	public static Object getBean(WebApplicationContext wac, String name) {
		ClassPathXmlApplicationContext ecoContext = (ClassPathXmlApplicationContext) wac.getBean(CONTEXT_BEAN);
		BeanFactory beanFactory = ecoContext.getBeanFactory();
		Object object = beanFactory.getBean(name);

		return object;
	}

	public static Object getBirtBean(String name) {
		ClassPathXmlApplicationContext batchContext = new ClassPathXmlApplicationContext(
				"classpath:" + SPRING_CONTEXT_FOLDER + "/properties-context.xml");
		return batchContext.getBean(name);
	}
}
