// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.services.internal;

import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttributeDefinition;

import vn.pyco.tinycms.services.impl.UserDetailsImpl;


/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class SaRoleVoter extends BoRoleVoter {
    /*
     * @see vn.pyco.tinycms.web.services.security.BoRoleVoter#vote(org.springframework.security.Authentication, java.lang.Object, org.springframework.security.ConfigAttributeDefinition)
     */
    @Override
    public int vote(Authentication authentication, Object object, ConfigAttributeDefinition config) {
        UserDetailsImpl userDetails = getUserDetails(authentication);
        if (userDetails != null && userDetails.getUser().isSA()) {
            return ACCESS_GRANTED;
        }
        
        return ACCESS_DENIED;
    }
}
