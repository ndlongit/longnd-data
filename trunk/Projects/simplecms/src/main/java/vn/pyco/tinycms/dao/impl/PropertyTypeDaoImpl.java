// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao.impl;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.restriction.Restrictions;
import vn.pyco.commons.dao.impl.GenericDaoImpl;
import vn.pyco.tinycms.dao.PropertyTypeDao;
import vn.pyco.tinycms.model.PropertyType;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Repository(PropertyTypeDao.SERVICE_ID)
public class PropertyTypeDaoImpl extends GenericDaoImpl<PropertyType, Integer>
                                implements PropertyTypeDao {
    @Override
    public PropertyType getPropertyTypeByName(String name) {
        PropertyType result = null;
        try {
            Criteria criteria = createCriteria();
            criteria.add(Restrictions.eq(PropertyType.PROP_NAME, name));
            result = (PropertyType) findUniqueByCriteria(criteria);

        } catch (NoResultException e) {
            return null;
        }

        return result;
    }
}
