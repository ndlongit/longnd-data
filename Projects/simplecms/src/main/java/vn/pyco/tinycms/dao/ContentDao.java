// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao;

import vn.pyco.commons.dao.GenericDao;
import vn.pyco.tinycms.model.Content;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public interface ContentDao extends GenericDao<Content, Integer> {
    String SERVICE_ID = "contentDao";

    Content getObjectByCriteria(String name, String value);

    Content getObjectByCriteria(String[] names, String[] values);
}
