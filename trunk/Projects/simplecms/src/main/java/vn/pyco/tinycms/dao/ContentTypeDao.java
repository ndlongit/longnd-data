// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao;

import vn.pyco.commons.dao.GenericDao;
import vn.pyco.tinycms.model.ContentType;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
public interface ContentTypeDao extends GenericDao<ContentType, Integer> {
    String SERVICE_ID = "contentTypeDao";

    ContentType getObjectByCriteria(String name, String value);

    ContentType getObjectByCriteria(String[] names, String[] values);
}
