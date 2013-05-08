// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao.impl;

import org.springframework.stereotype.Repository;

import vn.pyco.commons.dao.impl.GenericDaoImpl;
import vn.pyco.tinycms.dao.PageTemplateDao;
import vn.pyco.tinycms.model.PageTemplate;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Repository(PageTemplateDao.SERVICE_ID)
public class PageTemplateDaoImpl extends GenericDaoImpl<PageTemplate, Integer> implements PageTemplateDao {
}
