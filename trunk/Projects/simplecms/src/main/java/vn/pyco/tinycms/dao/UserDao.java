// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao;

import vn.pyco.commons.dao.GenericDao;
import vn.pyco.tinycms.model.User;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public interface UserDao extends GenericDao<User, Integer> {
    String SERVICE_ID = "userDao";
    
    User getByUsername(String username);
}
