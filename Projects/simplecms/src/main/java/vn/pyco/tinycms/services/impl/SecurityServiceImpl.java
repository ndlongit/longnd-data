// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services.impl;

import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import vn.pyco.tinycms.model.User;
import vn.pyco.tinycms.services.SecurityService;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Service(SecurityService.SERVICE_ID)
public class SecurityServiceImpl implements SecurityService {
    private PasswordEncoder _encoder = new Md5PasswordEncoder();
    
    public void setPasswordEncoder(PasswordEncoder encoder) {
        _encoder = encoder;
    }
    
    /*
     * @see vn.pyco.tinycms.services.SecurityService#getCurrentUsername()
     */
    @Override
    public String getCurrentUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext.getAuthentication() == null) {
            return null;
        }
        
        Object userDetails = securityContext.getAuthentication().getPrincipal();
        
        if (userDetails instanceof String) {
            return (String) userDetails;
        }
        
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }
        
        return null;
    }
    
    /*
     * @see vn.pyco.tinycms.services.SecurityService#encodePassword(vn.pyco.tinycms.model.User)
     */
    @Override
    public void encodePassword(User user) {
        user.setPassword(_encoder.encodePassword(user.getPassword(), user.getUsername()));
    }
}
