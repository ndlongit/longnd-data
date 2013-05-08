// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.services.internal;

import java.io.IOException;

import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestHandler;
import org.apache.tapestry5.services.Response;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.annotation.Secured;

import vn.pyco.tinycms.web.services.SecurityChecker;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class SecuredPageFilter implements RequestFilter {
    private final ComponentClassResolver _resolver;
    private final SecurityChecker _securityChecker;
    
    public SecuredPageFilter(ComponentClassResolver resolver, SecurityChecker securityChecker) {
        _resolver = resolver;
        _securityChecker = securityChecker;
    }
    
    /*
     * @see org.apache.tapestry5.services.RequestFilter#service(org.apache.tapestry5.services.Request, org.apache.tapestry5.services.Response, org.apache.tapestry5.services.RequestHandler)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean service(Request request, Response response, RequestHandler handler) throws IOException {
        String path = request.getPath().substring(1);

        if (!path.isEmpty() && !path.startsWith("assets/")) {
            int index = path.indexOf(".");
            if (index != -1) {
                path = path.substring(0, index);
            }
            
            try {
                String className = getClassName(path);
                if (className.length() != 0) {
                    Class clazz = Class.forName(className);
                    Object annotation = clazz.getAnnotation(Secured.class);
                    if (annotation != null) {
                        String[] values = ((Secured) annotation).value();
                        ConfigAttributeDefinition attributeDef = new ConfigAttributeDefinition(values); 
                        _securityChecker.checkBeforeInvocation(attributeDef);
                    }
                }
            } catch (ClassNotFoundException e) {
                // no-op
            }
        }
        return handler.service(request, response);
    }
    
    private String getClassName(String path) {
        String clazzName;
        if (_resolver.isPageName(path)) {
            clazzName = _resolver.resolvePageNameToClassName(path);
        } else {
            int index = path.lastIndexOf("/");
            if (index != -1) {
                path = path.substring(0, index);
                return getClassName(path);
            } else {
                return "";
            }
        }
        return clazzName;
    }
}
