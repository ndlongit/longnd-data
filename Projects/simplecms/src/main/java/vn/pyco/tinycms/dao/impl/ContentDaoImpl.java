// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao.impl;

import org.springframework.stereotype.Repository;

import vn.pyco.commons.dao.impl.GenericDaoImpl;
import vn.pyco.tinycms.dao.ContentDao;
import vn.pyco.tinycms.model.Content;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Repository(ContentDao.SERVICE_ID)
public class ContentDaoImpl extends GenericDaoImpl<Content, Integer> implements
                                ContentDao {

//    @Override
//    public Content getObjectByCriteria(String name, String value) {
//        String[] names = new String[1];
//        names[0] = name;
//
//        String[] values = new String[1];
//        values[0] = value;
//        return this.getObjectByCriteria(names, values);
//    }
//
//    @Override
//    public Content getObjectByCriteria(String[] names, String[] values) {
//        Content result = null;
//        try {
//            Criteria criteria = createCriteria();
//            for (int i = 0; i < names.length; i++) {
//                criteria.add(Restrictions.eq(names[0], values[0]));
//            }
//            result = (Content) findUniqueByCriteria(criteria);
//
//        } catch (NoResultException e) {
//            return null;
//        }
//
//        return result;
//    }
}
