// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.restriction.Restrictions;
import vn.pyco.commons.dao.impl.GenericDaoImpl;
import vn.pyco.tinycms.dao.AuthorityDao;
import vn.pyco.tinycms.model.Authority;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Repository(AuthorityDao.SERVICE_ID)
public class AuthorityDaoImpl extends GenericDaoImpl<Authority, Integer> implements AuthorityDao {
    private static final Logger logger = LoggerFactory.getLogger(AuthorityDaoImpl.class);
    
    /*
     * @see vn.pyco.tinycms.dao.AuthorityDao#getByName(java.lang.String)
     */
    @Override
    public Authority getByName(String authority) {
        Authority result = null;
        try {
            Criteria criteria = createCriteria();
            criteria.add(Restrictions.eq(Authority.PROP_AUTHORITY, authority));
            result = (Authority) findUniqueByCriteria(criteria);
            
        } catch (Exception e) {
            logger.error("Could not get authority from name=" + authority);
            return null;
        }
        
        return result;
    }
}
