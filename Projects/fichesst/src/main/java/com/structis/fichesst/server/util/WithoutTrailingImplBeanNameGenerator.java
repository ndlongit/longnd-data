/*
 * Generated by Celerio, a Jaxio tool. http://www.jaxio.com/
 */
package com.structis.fichesst.server.util;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

public class WithoutTrailingImplBeanNameGenerator extends AnnotationBeanNameGenerator {
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		String beanName = super.generateBeanName(definition, registry);
		if( !beanName.endsWith("Impl") ) {
			return beanName;
		}
		return beanName.substring(0, beanName.length() - "Impl".length());
	}
}