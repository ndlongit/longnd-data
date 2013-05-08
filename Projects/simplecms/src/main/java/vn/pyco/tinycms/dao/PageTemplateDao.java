// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao;

import vn.pyco.commons.dao.GenericDao;
import vn.pyco.tinycms.model.PageTemplate;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public interface PageTemplateDao extends GenericDao<PageTemplate, Integer> {
    String SERVICE_ID = "pageTemplateDao";
}
