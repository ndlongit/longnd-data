// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.services.internal;

import java.util.List;

import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttribute;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.vote.RoleVoter;

import vn.pyco.commons.utils.StringHelper;
import vn.pyco.tinycms.model.Authority;
import vn.pyco.tinycms.model.User.UserType;
import vn.pyco.tinycms.services.impl.UserDetailsImpl;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class BoRoleVoter extends RoleVoter {
    /*
     * @see org.springframework.security.vote.RoleVoter#vote(org.springframework.security.Authentication, java.lang.Object, org.springframework.security.ConfigAttributeDefinition)
     */
    @Override
    public int vote(Authentication authentication, Object object, ConfigAttributeDefinition config) {
        UserDetailsImpl details = getUserDetails(authentication);
        if (details == null) {
            return ACCESS_DENIED;
        }
        
        GrantedAuthority[] authorities = details.getAuthorities();
        for (Object obj : config.getConfigAttributes()) {
            ConfigAttribute attribute = (ConfigAttribute) obj;
            List<String> subAttibutes = StringHelper.toList(attribute.getAttribute(), ",");
            
            if (subAttibutes.contains(Authority.ROLE_BOUSER) && details.getUser().getType() == UserType.BO) {
                return ACCESS_GRANTED;
            }

            if (!supports(attribute)) {
                continue;
            }
            
            for (GrantedAuthority authority : authorities) {
                for (String subAttribute : subAttibutes) {
                    if (subAttribute.trim().equals(authority.getAuthority())) {
                        return ACCESS_GRANTED;
                    }
                }
            }
        }

        return ACCESS_DENIED;
    }
    
    protected UserDetailsImpl getUserDetails(Authentication authentication) {
        if (authentication == null) {
            return null;
        }
        
        return (UserDetailsImpl) authentication.getPrincipal();
    }
}
