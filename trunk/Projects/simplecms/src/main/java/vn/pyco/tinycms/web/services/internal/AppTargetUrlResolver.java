// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.services.internal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.Authentication;
import org.springframework.security.ui.TargetUrlResolverImpl;
import org.springframework.security.ui.savedrequest.SavedRequest;

import vn.pyco.tinycms.utils.SecurityConstants;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class AppTargetUrlResolver extends TargetUrlResolverImpl {
    /*
     * @see org.springframework.security.ui.TargetUrlResolverImpl#determineTargetUrl(org.springframework.security.ui.savedrequest.SavedRequest, javax.servlet.http.HttpServletRequest, org.springframework.security.Authentication)
     */
    @Override
    public String determineTargetUrl(SavedRequest savedRequest,
                                    HttpServletRequest currentRequest,
                                    Authentication auth) {
        Object targetUrl = currentRequest.getSession().getAttribute(SecurityConstants.FO_TARGET_URL);
        if (targetUrl != null) {
            return (String) targetUrl;
        }        
        
        return super.determineTargetUrl(savedRequest, currentRequest, auth);
    }
}
