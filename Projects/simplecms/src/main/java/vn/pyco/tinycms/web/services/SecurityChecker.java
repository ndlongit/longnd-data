// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.services;

import org.springframework.security.intercept.InterceptorStatusToken;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public interface SecurityChecker {
    /**
     * Check security before calling secured method.
     * 
     * @param object
     *            security object
     * @return status token
     */
    InterceptorStatusToken checkBeforeInvocation(Object object);

    /**
     * Check security after calling secured method.
     * 
     * @param token
     *            status token
     * @param returnedObject
     *            object returned by the method
     * @return object to return from the secured method
     */
    Object checkAfterInvocation(InterceptorStatusToken token, Object returnedObject);
}
