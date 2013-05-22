package com.structis.vip.server.core;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringGetter {

    public static Object getBean(ServletContext context, String name) {
        ClassPathXmlApplicationContext ecoContext = (ClassPathXmlApplicationContext) WebApplicationContextUtils.getWebApplicationContext(context)
                .getBean("vip-context");
        BeanFactory beanFactory = ecoContext.getBeanFactory();
        Object object = beanFactory.getBean(name);

        return object;
    }

    public static Object getBatchBean(String name) {
        ClassPathXmlApplicationContext batchContext = new ClassPathXmlApplicationContext(
                "classpath:com/structis/vip/server/spring/applicationContext.xml",
                "classpath:com/structis/vip/server/spring/persistance-context.xml", "classpath:com/structis/vip/server/spring/dozer-context.xml",
                "classpath:com/structis/vip/server/spring/manager-context.xml", "classpath:com/structis/vip/server/spring/properties-context.xml");
        return batchContext.getBean(name);
    }

    public static Object getBean(WebApplicationContext wac, String name) {
        ClassPathXmlApplicationContext ecoContext = (ClassPathXmlApplicationContext) wac.getBean("vip-context");
        BeanFactory beanFactory = ecoContext.getBeanFactory();
        Object object = beanFactory.getBean(name);

        return object;
    }

    public static Object getBirtBean(String name) {
        ClassPathXmlApplicationContext batchContext = new ClassPathXmlApplicationContext(
                "classpath:com/structis/vip/server/spring/properties-context.xml");
        return batchContext.getBean(name);
    }
}
