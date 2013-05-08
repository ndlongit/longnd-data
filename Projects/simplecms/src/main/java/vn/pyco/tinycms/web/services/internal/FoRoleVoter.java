// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.services.internal;

import java.util.List;

import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttribute;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.vote.RoleVoter;

import vn.pyco.commons.utils.StringHelper;
import vn.pyco.tinycms.model.Authority;
import vn.pyco.tinycms.model.Node;
import vn.pyco.tinycms.model.User.UserType;
import vn.pyco.tinycms.services.impl.UserDetailsImpl;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class FoRoleVoter extends RoleVoter {
    /*
     * @see org.springframework.security.vote.RoleVoter#vote(org.springframework.security.Authentication, java.lang.Object, org.springframework.security.ConfigAttributeDefinition)
     */
    @Override
    public int vote(Authentication authentication, Object object, ConfigAttributeDefinition config) {
        if (!(object instanceof Node)) {
            return ACCESS_ABSTAIN;
        }
        
        Node node = (Node) object;
        if (!node.isSecured()) {
            return ACCESS_GRANTED;
        }
        
        UserDetailsImpl details = getUserDetails(authentication);
        if (details == null) {
            return ACCESS_DENIED;
        }
        
        for (Object obj : config.getConfigAttributes()) {
            ConfigAttribute attribute = (ConfigAttribute) obj;
            List<String> subAttibutes = StringHelper.toList(attribute.getAttribute(), ",");
            
            if (subAttibutes.contains(Authority.ROLE_FOUSER) && details.getUser().getType() != UserType.NA) {
                return ACCESS_GRANTED;
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