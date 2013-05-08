// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.services.internal;

import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.intercept.InterceptorStatusToken;
import org.springframework.security.intercept.ObjectDefinitionSource;
import org.springframework.security.intercept.method.aopalliance.MethodSecurityInterceptor;

import vn.pyco.tinycms.web.services.SecurityChecker;


/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
public class SecurityCheckerImpl extends MethodSecurityInterceptor implements SecurityChecker {
    private ObjectDefinitionSource _objectDefinitionSource = new StaticDefinitionSource();
    
    /*
     * @see
     * vn.pyco.tinycms.web.services.SecurityChecker#checkBeforeInvocation(java.lang.Object)
     */
    @Override
    public InterceptorStatusToken checkBeforeInvocation(Object object) {
        return beforeInvocation(object);
    }

    /*
     * @see
     * vn.pyco.tinycms.web.services.SecurityChecker#checkAfterInvocation(org.springframework.security.intercept.InterceptorStatusToken, java.lang.Object)
     */
    @Override
    public Object checkAfterInvocation(InterceptorStatusToken token, Object returnedObject) {
        return afterInvocation(token, returnedObject);
    }
    
    /*
     * @see org.springframework.security.intercept.method.aopalliance.MethodSecurityInterceptor#obtainObjectDefinitionSource()
     */
    @Override
    public ObjectDefinitionSource obtainObjectDefinitionSource() {
        return _objectDefinitionSource;
    }
    
    /*
     * @see org.springframework.security.intercept.method.aopalliance.MethodSecurityInterceptor#getSecureObjectClass()
     */
    @Override
    public Class getSecureObjectClass() {
        return ConfigAttributeDefinition.class;
    }
}
