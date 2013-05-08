// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao.impl;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.restriction.Restrictions;
import vn.pyco.commons.dao.impl.GenericDaoImpl;
import vn.pyco.tinycms.dao.UserDao;
import vn.pyco.tinycms.model.User;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Repository(UserDao.SERVICE_ID)
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {
    /*
     * @see vn.pyco.tinycms.dao.UserDao#getUserByUsername(java.lang.String)
     */
    @Override
    public User getByUsername(String username) {
        User result = null;
        try {
            Criteria criteria = createCriteria();
            criteria.add(Restrictions.eq(User.PROP_USERNAME, username));
            result = (User) findUniqueByCriteria(criteria);
            
        } catch (NoResultException e) {
            return null;
        }
        
        return result;
    }
}
