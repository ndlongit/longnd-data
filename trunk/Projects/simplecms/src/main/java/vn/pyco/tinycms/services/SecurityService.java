// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services;

import vn.pyco.tinycms.model.User;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public interface SecurityService {
   String SERVICE_ID = "securityService";
    
   String getCurrentUsername();
   
   void encodePassword(User user);
}
