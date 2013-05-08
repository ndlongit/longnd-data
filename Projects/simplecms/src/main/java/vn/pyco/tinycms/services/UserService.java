// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services;

import org.springframework.security.userdetails.UserDetailsService;

import vn.pyco.tinycms.model.Authority;
import vn.pyco.tinycms.model.User;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public interface UserService extends UserDetailsService {
    String SERVICE_ID = "userService";
    
    Authority getAuthorityByName(String authority);
    
    User getUserByUsername(String username);
    
    User getCurrentUser();

    void saveUser(User user);
}
