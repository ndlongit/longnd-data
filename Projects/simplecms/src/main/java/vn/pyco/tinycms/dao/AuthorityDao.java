// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao;

import vn.pyco.commons.dao.GenericDao;
import vn.pyco.tinycms.model.Authority;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public interface AuthorityDao extends GenericDao<Authority, Integer>  {
    String SERVICE_ID = "authorityDao";
    
    Authority getByName(String authority);
}
