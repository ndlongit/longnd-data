// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.services.internal;

import java.lang.reflect.Method;
import java.util.Collection;

import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.intercept.method.MethodDefinitionSource;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class StaticDefinitionSource implements MethodDefinitionSource {

    /*
     * @see org.springframework.security.intercept.method.MethodDefinitionSource#getAttributes(java.lang.reflect.Method, java.lang.Class)
     */
    @Override
    public ConfigAttributeDefinition getAttributes(Method method, Class targetClass) {
        return null;
    }

    /*
     * @see org.springframework.security.intercept.ObjectDefinitionSource#getAttributes(java.lang.Object)
     */
    @Override
    public ConfigAttributeDefinition getAttributes(Object object) throws IllegalArgumentException {
        return (ConfigAttributeDefinition) object;
    }

    /*
     * @see org.springframework.security.intercept.ObjectDefinitionSource#getConfigAttributeDefinitions()
     */
    @Override
    public Collection getConfigAttributeDefinitions() {
        return null;
    }

    /*
     * @see org.springframework.security.intercept.ObjectDefinitionSource#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class clazz) {
        return ConfigAttributeDefinition.class.isAssignableFrom(clazz);
    }
    
}
